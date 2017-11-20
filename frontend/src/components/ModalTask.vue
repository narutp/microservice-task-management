<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <pie-chart :data="projectPieChart"></pie-chart>
    </div>
    <el-dialog
      title="Task Card from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
        <div v-for="n in 4" class="small-chart">
          <span> Department {{departmentList[n-1]}}</span>
          <pie-chart :data="projectCardPieChart[n-1]"></pie-chart>
          <br>
        </div>
      </div>
      <div class="in-modal-mask">
        <div v-for="item in items" class="small-chart">
          <span> {{item.department}} </span>
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
import Axios from 'axios'

export default {
  data () {
    return {
      dialogVisible: false,
      projectPieChart: [{'name': '', 'numProject': 0}],
      projectCardPieChart: [],
      departmentList: []
    }
  },
  async mounted () {
    // Department
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
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
      let userList = await Axios.get(`http://localhost:8090/get/idUser?departmentName=${departmentName}`)
      // console.log(i)
      let projectResponse = await Axios.get(`http://localhost:8091/get/department-project?idUserList=${userList.data}`)
      // console.log(projectResponse.data)
      data.push(departmentName, projectResponse.data.length)
      datasets.push(data)
    }
    this.projectPieChart = datasets

    for (let j = 0; j < this.arrLength; j++) {
      let departmentName = response.data[j].name
      console.log(departmentName)
      let userList = await Axios.get(`http://localhost:8090/get/idUser?departmentName=${departmentName}`)
      // console.log(userList)
      let projectResponse = await Axios.get(`http://localhost:8091/get/department-project?idUserList=${userList.data}`)
      // console.log(projectResponse.data.length)
      let numProject = projectResponse.data.length
      // console.log('numproject')
      // console.log(numProject)
      // project list in each department
      for (let i = 0; i < numProject; i++) {
        let data = []
        let id = projectResponse.data[i].idProject
        console.log(id)
        let projectName = projectResponse.data[i].name
        console.log(projectName)
        let cardResponse = await Axios.get(`http://localhost:8091/get/count/project-card/idProject?idProject=${id}`)
        console.log('cardResponse')
        console.log(cardResponse.data)
        data.push(projectName, cardResponse.data)
        datasets2.push(data)
      }
      dataCollections.push(datasets2)
      datasets2 = []
    }
    console.log(dataCollections)
    this.projectCardPieChart = dataCollections
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
