<template lang="html">
  <div class="header--container">
    <nav class="navbar">
      <a class="navbar-item">
        <img src="http://bulma.io/images/bulma-logo.png" alt="Bulma: a modern CSS framework based on Flexbox" width="112" height="28">
      </a>
      <div class="navbar-item has-dropdown">
        <a class="navbar-link">
          Docs
        </a>
        <div class="navbar-dropdown">
          <a class="navbar-item">
            Overview
          </a>
          <a class="navbar-item is-active">
            Elements
          </a>
          <a class="navbar-item">
            Components
          </a>
          <hr class="navbar-divider">
          <div class="navbar-item">
            Version 0.5.1
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
}
</script>

<style scoped>
.navbar {
  background-color: white;
}
</style>
