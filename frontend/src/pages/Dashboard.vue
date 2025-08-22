<template>
  <div class="container">
    
    <!-- 검색 + 정렬 -->
    <div class="filter-bar">
      <el-input
        v-model="searchInput"
        placeholder="종목명 검색"
        clearable
        style="width: 300px;"
      />
      <el-select v-model="sortKey" placeholder="정렬" style="margin-left: 20px; width: 180px;">
        <el-option label="이름 ↑" value="name_asc" />
        <el-option label="이름 ↓" value="name_desc" />
        <el-option label="티커 ↑" value="ticker_asc" />
        <el-option label="티커 ↓" value="ticker_desc" />
      </el-select>
    </div>

    <!-- 로딩 표시 -->
    <div v-if="loading" class="loading">
        데이터 불러오는 중...
    </div>

    <!-- 카드 그리드 -->
    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col
        v-for="stock in pagedStocks"
        :key="stock.ticker"
        :xs="24" :sm="12" :md="8" :lg="6"
      >

        <!-- 카드 -->
        <router-link
          :to="{ name: 'StockDetail', params: { ticker: stock.ticker } }"
          style="text-decoration: none;"
        >
          <StockCard :stock="stock" />
        </router-link>

      </el-col>
    </el-row>

    <!-- 페이지네이션 -->
    <div class="pagination-wrapper" v-if="!loading && filteredStocks.length">
      <el-pagination
        background
        layout="prev, pager, next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="filteredStocks.length"
        @current-change="handlePageChange"
      />
    </div>
  </div>
</template>

<script>
import { ref, computed, watch, onMounted } from 'vue'
import StockCard from '@/components/StockCard.vue'

function debounce(fn, delay) {
    let timer;
    return function (...args) {
        clearTimeout(timer);
        timer = setTimeout(() => fn.apply(this, args), delay);
    }
}

export default {
    name: "Dashboard",
    components: {
        StockCard: StockCard,

    },
    data() {
        return {
            stocks: [],
            loading: false,
            searchInput: '',
            searchQuery: "",
            sortKey: "",
            currentPage: 1,
            pageSize: 20,
        }
    },
    computed: {
        filteredStocks() {
            return this.stocks
            .filter(s => s.name.toLowerCase().includes(this.searchQuery.toLowerCase()))
            .sort((a, b) => {
                switch (this.sortKey) {
                    case 'name_asc': return a.name.localeCompare(b.name)
                    case 'name_decs': return b.name.localeCompare(a.name)
                    case 'ticker_asc': return a.ticker.localeCompare(b.ticker)
                    case 'ticker_desc': return b.ticker.localeCompare(a.ticker)
                    default: return 0
                }
            })
        },
        pagedStocks() {
            const start = (this.currentPage - 1) * this.pageSize;
            return this.filteredStocks.slice(start, start + this.pageSize)
        },
    },
    methods: {
        handlePageChange(page) {
            this.currentPage = page;
        },
        async fetchStocks() {
            this.loading = true;
            try {
                const res =  await fetch("http://localhost:8080/stocks")
                const data = await res.json()
                console.log("API response", data);
                this.stocks = data.result
            } catch (err) {
                console.error('API 호출 실패', err)
            } finally {
                this.loading = false
            }
        },
        updateSearchQuery() {
            this.searchQuery = this.searchInput
            this.currentPage = 1;
        },
    },
    created() {
        this.fetchStocks()
        this.debouncedSearch = debounce(this.updateSearchQuery, 300)
    },
    watch: {
        searchInput: {
        handler() {
            this.debouncedSearch()
        },
        immediate: false
        }
    }

}
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: auto;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
}

h1 {
  text-align: center;
  margin-bottom: 20px;
  color: #2c3e50;
}

.filter-bar {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
}

.pagination-wrapper {
  text-align: center;
  margin-top: 30px;
}

.loading {
  text-align: center;
  margin-top: 50px;
  font-size: 18px;
  color: #555;
}
</style>
