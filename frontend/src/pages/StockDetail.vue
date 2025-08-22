<template>
  <div class="detail-container" v-if="stock">
    <h2>{{ stock.name }} 상세 정보</h2>

    <!-- 종목 기본 정보 -->
    <div class="info-box">
      <p><strong>티커:</strong> {{ stock.ticker }}</p>
    </div>

    <!-- 차트 -->
    <LineChart v-if="chartData.datasets.length" :chartData="chartData" />
  </div>

  <div v-else class="loading">
    데이터를 불러오는 중입니다...
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import LineChart from '@/components/LineChart.vue'

export default {
  name: "StockDetail",
  components: { LineChart },
  setup() {
    const route = useRoute()
    const stock = ref(null)
    const chartData = ref({
      labels: [],
      datasets: []
    })

    onMounted(async () => {
      try {
        const res = await fetch(`http://localhost:8080/stocks/${route.params.ticker}`)
        const data = await res.json()

        stock.value = data.result

        if (stock.value && stock.value.closePriceList) {
          chartData.value = {
            labels: stock.value.closePriceList.map(p => p.date), // yyyy-mm-dd 그대로
            datasets: [
              {
                label: "종가",
                data: stock.value.closePriceList.map(p => p.closePrice),
                borderColor: "blue",
                backgroundColor: "rgba(0, 0, 255, 0.1)",
                fill: true,
                tension: 0.3
              }
            ]
          }
        }
      } catch (err) {
        console.error("API 호출 실패", err)
      }
    })

    return { stock, chartData }
  }
}
</script>

<style scoped>
.detail-container {
  max-width: 900px;
  margin: auto;
  padding: 20px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.info-box {
  margin-bottom: 20px;
  font-size: 16px;
}

.loading {
  text-align: center;
  margin-top: 50px;
  font-size: 18px;
  color: #555;
}
</style>
