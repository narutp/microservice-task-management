<template lang="html">
  <div class="home--container">
    <div class="home--chart">
      <el-row>
        <el-col :span="12">
          <bar-task></bar-task>
        </el-col>
        <el-col :span="12">
          <el-row>
            <horizontal-bar-participant></horizontal-bar-participant>
          </el-row>
          <el-row>
            <doughnut-department></doughnut-department>
          </el-row>
        </el-col>
      </el-row>
    </div>

    {{ getUser }}
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

<style lang="css">
</style>
