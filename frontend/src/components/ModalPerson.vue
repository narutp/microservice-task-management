<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <bar-participant> </bar-participant>
    </div>
    <el-dialog
      title="Position from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
        <div v-for="item in items" class="small-chart">
          <span> {{item.name}} </span>
          <horizontal-bar-position> </horizontal-bar-position>
          <!-- <horizontal-bar-position
            :labels="['Professor', 'Project Manager', 'Student', 'Intern']",
            :data="tableUserData"
            :options="{responsive: false, maintainAspectRatio: false}"
            :width="250"
            :height="200"
          > </horizontal-bar-position> -->
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
import BarParticipant from '@/components/Chart/BarParticipant'
import HorizontalBarPosition from '@/components/Chart/HorizontalBarPosition'
import Axios from 'axios'

export default {
  data () {
    return {
      dialogVisible: false,
      items: [
        { name: 'Department A' }
      ],
      tableUserData: [{ 'name': 'DapartmentName', 'numUser': 'Internal' }]
    }
  },
  async mounted () {
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.items = response.data
    // this.arrLength = response.data.length
    // for (let i = 0; i < this.arrLength; i++) {
    //   let id = response.data[i].idDepartment
    //   let numUserResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
    //   // get all-project from idDepartment
    //   let numProjectResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
    //   // get all-task-card from idDepartment
    //   let numTaskCardResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
    //   // console.log(numUserResponse.data.length)
    //   // console.log(numProjectResponse.data.length)
    //   // console.log('++++')
    //   this.tableUserData[i].numUser = numUserResponse.data.length
    //   this.tableProjectData[i].numProject = numProjectResponse.data.length
    //   this.tableProjectData[i].numTaskCard = numTaskCardResponse.data.length
    // }
  },
  methods: {
  },
  components: {
    BarParticipant,
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
