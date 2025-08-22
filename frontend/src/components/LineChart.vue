<template>
  <div class="chart-container">
    <canvas ref="canvas"></canvas>
  </div>
</template>

<script>
import { onMounted, ref, watch } from 'vue'
import {
  Chart, LineController, LineElement, PointElement,
  LinearScale, Title, CategoryScale, Tooltip, Legend
} from 'chart.js'

Chart.register(LineController, LineElement, PointElement, LinearScale, Title, CategoryScale, Tooltip, Legend)

export default {
  name: "LineChart",
  props: { chartData: Object },
  setup(props) {
    const canvas = ref(null)
    let chart = null

    const renderChart = () => {
      if (!props.chartData || !props.chartData.labels) return
      if (chart) chart.destroy()

      chart = new Chart(canvas.value, {
        type: 'line',
        data: {
          labels: props.chartData.labels,   // 👉 x축 (일자)
          datasets: props.chartData.datasets // 👉 y축 데이터 (종가)
        },
        options: {
          responsive: true,
          maintainAspectRatio: false,
          interaction: {
            mode: "index",
            intersect: false, // 선 위에 정확히 올리지 않아도 툴팁 뜸
          },
          plugins: {
            title: {
              display: true,
              text: "일자별 종가 추이"
            },
            tooltip: {
              enabled: true,
              callbacks: {
                label: function(context) {
                  let value = context.raw.toLocaleString() + " 원"; // 종가 표시
                  return `종가: ${value}`;
                }
              }
            },
            legend: {
              display: false // "종가" 라벨은 안 보이게 (필요하면 true로 바꿔)
            }
          },
          scales: {
            x: { title: { display: true, text: "일자" }},
            y: { title: { display: true, text: "종가 (원)" }}
          }
        }
      })
    }

    onMounted(renderChart)
    watch(() => props.chartData, renderChart, { deep: true })

    return { canvas }
  }
}
</script>

<style scoped>
.chart-container {
  width: 100%;
  height: 400px;
}
</style>
