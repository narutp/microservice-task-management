<template lang="html">
  <div class="home--container">
    <el-row class="home--chart">
      <el-col :span="12">
        <horizontal-bar-participant></horizontal-bar-participant>
      </el-col>
      <el-col :span="12">
        <doughnut-department></doughnut-department>
      </el-col>
      <bar-task :height="120"></bar-task>
    </el-row>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import BarTask from '@/components/Chart/BarTask'
import DoughnutDepartment from '@/components/Chart/DoughnutDepartment'
import HorizontalBarParticipant from '@/components/Chart/HorizontalBarParticipant'
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
    BarTask,
    DoughnutDepartment,
    HorizontalBarParticipant
  }
}
</script>

<style scoped>
.home--container {
  background-color: #fff;
  padding: 30px;
}
</style>
