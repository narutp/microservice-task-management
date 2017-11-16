<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <column-chart :data="userColumnChart"></column-chart>
    </div>
    <el-dialog
      title="Position from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
        <div v-for="item in items" class="small-chart">
          <bar-chart :data="projectBarChart"></bar-chart>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
import HorizontalBarPosition from '@/components/Chart/HorizontalBarPosition'
import Axios from 'axios'

export default {
  data () {
    return {
      dialogVisible: false,
      userColumnChart: [{'name': '', 'numUser': 0}],
      items: [
        {'name': '', 'idDepartment': 0},
        {'name': '', 'idDepartment': 0}
      ],
      projectBarChart: [{'name': '', 'numUser': 0}]
    }
  },
  async mounted () {
    // Department
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    console.log(response)
    this.arrLength = response.data.length
    var datasets = []
    for (let i = 0; i < this.arrLength; i++) {
      let data = []
      console.log('data: ' + data)
      let departmentName = response.data[i].name
      console.log('id : ' + departmentName)
      let numUserResponse = await Axios.get(`http://localhost:8090/get/idUser?departmentName=${departmentName}`)
      console.log(numUserResponse)
      data.push(departmentName, numUserResponse.data.length)
      datasets.push(data)
    }
    this.userColumnChart = datasets
  },
  methods: {
  },
  components: {
    HorizontalBarPosition
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
