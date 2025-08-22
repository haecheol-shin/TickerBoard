<template>
  <div id="app">
    <!-- 고정 헤더 -->
    <header class="app-header">
      <h1 @click="$router.push('/')">📊 TickerBoard</h1>
    </header>

    <!-- 페이지 내용 -->
    <main class="app-main">
      <router-view />
    </main>
  </div>
</template>

<script setup>
// App.vue 자체에서는 import 필요 없음
</script>

<style scoped>
#app {
  font-family: 'Helvetica Neue', Arial, sans-serif;
  background-color: #f5f6fa;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

.app-header {
  background-color: #2c3e50;
  color: white;
  padding: 15px 30px;
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}

.app-main {
  flex: 1;
  padding: 20px;
}

h1 {
  text-align: center;
}
</style>
