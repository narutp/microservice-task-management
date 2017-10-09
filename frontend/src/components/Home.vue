<template lang="html">
  <div class="home--container">
    <el-row>
      <el-col :span="12">
        <new-chart></new-chart>
      </el-col>
      <el-col :span="12">
        <el-row>
          b
        </el-row>
        <el-row>
          <doughnut-chart></doughnut-chart>
        </el-row>
      </el-col>
    </el-row>

    {{ getUser }}
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import NewChart from '@/components/Chart/NewChart'
import DoughnutChart from '@/components/Chart/DoughnutChart'
export default {
  async mounted () {
    await this.setUser()
  },
  computed: {
    ...mapGetters({
      getUser: 'GET_USER'
    })
  },
  methods: {
    ...mapActions({
      setUser: 'SET_USER'
    })
  },
  components: {
    NewChart,
    DoughnutChart
  }
}
</script>

<style lang="css">
</style>
