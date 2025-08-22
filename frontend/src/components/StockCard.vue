<template>
    <el-card shadow="hover" class="stock-card" @click="goToDetail">
        <h3>{{ stock.name }}</h3>
        <p>{{ stock.ticker }}</p>
    </el-card>
</template>

<script>
export default {
    name: "StockCardComponent",
    props: {
        stock: {
            type: Object,
            required: true,
        }
    },
    methods: {
      goToDetail() {
        this.$router.push(`/stock/${this.stock.ticker}`)
      }
    }
}

</script>
<style scoped>

.stock-card {
  text-align: center;
  padding: 20px;
  min-height: 120px;
  margin-bottom: 20px; /* 카드 간 세로 간격 */
  border-radius: 8px;
  transition: transform 0.2s;
}

.stock-card:hover {
  transform: translateY(-5px); /* 마우스 올리면 살짝 올라가는 효과 */
}

.stock-card h3 {
  margin-bottom: 10px;
}

.stock-card p {
  margin: 5px 0;
  font-size: 1rem;
  font-weight: 500;
}


    
</style>