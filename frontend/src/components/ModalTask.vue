<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <doughnut-department></doughnut-department>
    </div>
    <el-dialog
      title="Task Card from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
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
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
import PieProject from '@/components/Chart/PieProject'
import DoughnutDepartment from '@/components/Chart/DoughnutDepartment'

export default {
  data () {
    return {
      dialogVisible: false
    }
  },
  methods: {
  },
  components: {
    PieProject,
    DoughnutDepartment
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
