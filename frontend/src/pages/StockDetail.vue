<template>
  <div class="detail-container" v-if="stock" :key="$route.fullPath">
    <h2>{{ stock.name }} 상세 정보</h2>

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

<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import LineChart from '@/components/LineChart.vue'

const API_BASE = import.meta.env.VITE_API_BASE_URL

const route = useRoute()
const stock = ref(null)
const chartData = ref({ labels: [], datasets: [] })

const fetchStock = async (ticker) => {
  try {
    const res = await fetch(`${API_BASE}/stocks/${ticker}`) // Docker 컨테이너 이름
    const data = await res.json()

    stock.value = data.result

    if (stock.value?.closePriceList) {
      chartData.value = {
        labels: stock.value.closePriceList.map(p => p.date),
        datasets: [
          {
            label: "종가",
            data: stock.value.closePriceList.map(p => p.closePrice),
            borderColor: "blue",
            backgroundColor: "rgba(0,0,255,0.1)",
            fill: true,
            tension: 0.3
          }
        ]
      }
    }
  } catch (err) {
    console.error("API 호출 실패", err)
  }
}

// 초기 마운트 시 데이터 로딩
onMounted(() => fetchStock(route.params.ticker))

// route.params.ticker 변화 감지

watch(() => route.params.ticker, async (newTicker) => {
  await fetchStock(newTicker)  // 기존 fetchStock 함수 재사용
})

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
.info-box { margin-bottom: 20px; font-size: 16px; }
.loading { text-align: center; margin-top: 50px; font-size: 18px; color: #555; }
</style>
