<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <column-chart :data="userColumnChart"></column-chart>
    </div>
    <el-dialog
      title="Position from Each department"
      :visible.sync="dialogVisible"
      width="100%">
      <div class="in-modal-mask">
        <div v-for="n in 4" class="small-chart">
          <span> Department {{departmentList[n-1]}}</span>
          <bar-chart :data="positionBarChart[n-1]"></bar-chart>
          <br>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Axios from 'axios'

export default {
  data () {
    return {
      dialogVisible: false,
      userColumnChart: [{'name': '', 'numUser': 0}],
      positionBarChart: [],
      departmentList: []
    }
  },
  async mounted () {
    // Department
    let response = await Axios.get(`//210.121.158.165:8090/get/all-department`)
    // console.log(response)
    this.arrLength = response.data.length
    var datasets = []
    var datasets2 = []
    var dataCollections = []

    for (let i = 0; i < this.arrLength; i++) {
      let data = []
      // console.log('data: ' + data)
      let departmentName = response.data[i].name
      this.departmentList.push(departmentName)
      // console.log('id : ' + departmentName)
      let userList = await Axios.get(`//210.121.158.165:8090/get/idUser?departmentName=${departmentName}`)
      // console.log(userList)
      data.push(departmentName, userList.data.length)
      datasets.push(data)
      // console.log(datasets)
    }
    this.userColumnChart = datasets

    for (let j = 0; j < this.arrLength; j++) {
      let positionResponse = await Axios.get(`//210.121.158.165:8090/get/all-position`)
      this.positionLength = positionResponse.data.length
      let departmentName = response.data[j].name
      for (let i = 0; i < this.positionLength; i++) {
        let data = []
        let positionName = positionResponse.data[i].name
        // console.log(departmentName)
        let numPositionResponse = await Axios.get(`//210.121.158.165:8090/get/count/position-in-department?departmentName=${departmentName}&positionName=${positionName}`)
        // console.log(numPositionResponse.data)
        data.push(positionName, numPositionResponse.data)
        datasets2.push(data)
      }
      dataCollections.push(datasets2)
      datasets2 = []
    }
    this.positionBarChart = dataCollections
    // console.log(dataCollections)
  },
  methods: {
  },
  components: {
  }
}
</script>

<style scoped>
  .small-chart {
    display: inline-block;
    width: 300px;
  }
  .modal--container {
    text-align: center;
  }
</style>
