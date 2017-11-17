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
          <pie-chart :data="projectPieChart"></pie-chart>
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
      departmentList: []
    }
  },
  async mounted () {
    // Department
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.arrLength = response.data.length
    var datasets = []
    // var datasets2 = []
    // var dataCollections = []

    for (let i = 0; i < this.arrLength; i++) {
      let data = []
      // console.log('data: ' + data)
      let departmentName = response.data[i].name
      this.departmentList.push(departmentName)
      // console.log('id : ' + departmentName)
      let numUserResponse = await Axios.get(`http://localhost:8090/get/idUser?departmentName=${departmentName}`)
      console.log(numUserResponse.data)
      let idUserList = numUserResponse.data
      let departmentProject = await Axios.get(`http://localhost:8091/get/department-project/${idUserList}`)
      // console.log(departmentProject)
      data.push(departmentName, departmentProject.data.length)
      datasets.push(data)
      // console.log(data)
      // console.log(i)
      // console.log(departmentProject)
    }
    // console.log(datasets)
    this.projectPieChart = datasets

    // let numRandom = [0, 2, 4, 11, 3, 4, 12, 17, 5, 10, 23, 2, 7, 11, 8, 9, 22, 1, 3, 8]
    // let c = 0

    // for (let j = 0; j < this.arrLength; j++) {
    //   let cardResponse = await Axios.get(`http://localhost:8091/get/all-project-card/`)
    //   this.cardLength = cardResponse.data.length
    //   console.log(this.cardLength)
    //   for (let i = 0; i < this.positionLength; i++) {
    //     let data = []
    //     let positionName = positionResponse.data[i].name
    //     // let numPositionResponse = await Axios.get(`http://localhost:8090/get/idUser?departmentName=A`)
    //     // console.log(numPositionResponse)
    //     data.push(positionName, numRandom[c])
    //     c += 1
    //     datasets2.push(data)
      // }
    //   dataCollections.push(datasets2)
    //   datasets2 = []
    // }
    // this.positionBarChart = dataCollections
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
