<template lang="html">
  <div class="content" align="center">
    <div class="small-chart">
      <span> Department A </span>
      <pie-project></pie-project>
    </div>
    <div class="small-chart">
      <span> Department B </span>
      <pie-project></pie-project>
    </div>
    <br>
    <br>
    <div class="small-chart">
      <span> Department C </span>
      <pie-project></pie-project>
    </div>
    <div class="small-chart">
      <span> Department D </span>
      <pie-project></pie-project>
    </div>
  </div>
</template>

<script>
import PieProject from '@/components/Chart/PieProject'

export default {
  data () {
    return {
    }
  },
  methods: {
  },
  components: {
    PieProject
  }
}
</script>

<style scoped>
  .small-chart {
    display: inline-block;
    width: 250px;
  }
  .modal--container {
    text-align: center;
    width: 600px;
  }
</style>
