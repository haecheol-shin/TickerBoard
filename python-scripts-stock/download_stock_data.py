from pykrx import stock
import pandas as pd
import os
from datetime import datetime

# Docker 컨테이너에서 외부 볼륨과 연결될 경로
SAVE_DIR = "/app/output/krx_data"
MERGED_FILE = "/app/output/krx_all_merged.xlsx"
ERROR_LOG = "/app/output/error_log.txt"

# 시작일과 실행 시점 기준 오늘 날짜
START_DATE = "2025-01-01"
END_DATE = datetime.today().strftime("%Y-%m-%d")

# 저장 디렉토리 생성
if not os.path.exists(SAVE_DIR):
    os.makedirs(SAVE_DIR)

# 코스피 + 코스닥 종목 리스트 가져오기
def get_krx_stocks():
    tickers = []
    for market in ['KOSPI', 'KOSDAQ']:
        for ticker in stock.get_market_ticker_list(market=market):
            tickers.append((ticker, market))
    return tickers  # (티커, 시장구분)

# 종목명 매핑
def get_stock_name(ticker):
    return stock.get_market_ticker_name(ticker)

# 종목별 시세 다운로드 및 저장
def download_stock_data():
    tickers = get_krx_stocks()
    print(f"[INFO] 전체 종목 수: {len(tickers)}")

    for ticker, market in tickers:  # tqdm 제거, 배치용
        name = get_stock_name(ticker).replace("/", "_").replace(" ", "")
        try:
            df = stock.get_market_ohlcv_by_date(START_DATE, END_DATE, ticker)
            if not df.empty:
                df.reset_index(inplace=True)
                df["티커"] = ticker
                df["종목명"] = name
                df["시장구분"] = market
                df.to_csv(f"{SAVE_DIR}/{name}_{ticker}.csv", index=False)
        except Exception as e:
            print(f"[ERROR] {name}({ticker}) 실패: {e}")
            with open(ERROR_LOG, "a") as f:
                f.write(f"{name}({ticker}) 실패: {e}\n")

# 전체 병합 및 엑셀 저장
def merge_to_excel():
    dfs = []
    for file in os.listdir(SAVE_DIR):
        if file.endswith(".csv"):
            df = pd.read_csv(os.path.join(SAVE_DIR, file))
            dfs.append(df)

    if dfs:
        all_df = pd.concat(dfs, ignore_index=True)
        all_df.to_excel(MERGED_FILE, index=False)
        print(f"[완료] 병합된 엑셀 저장: {MERGED_FILE}")
    else:
        print("[경고] 병합할 데이터 없음")

if __name__ == "__main__":
    print("[1단계] 종목별 시세 다운로드 시작")
    download_stock_data()

    print("[2단계] 모든 종목 병합 및 Excel 저장")
    merge_to_excel()
