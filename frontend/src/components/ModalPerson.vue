<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <column-chart :data="tableUserData"></column-chart>
    </div>
    <el-dialog
      title="Position from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
        <div v-for="item in items" class="small-chart">
          <horizontal-bar-position> </horizontal-bar-position>
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
      tableUserData: [{'name': '', 'idDepartment': 0}]
    }
  },
  async mounted () {
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.arrLength = response.data.length
    var datasets = []
    console.log('------')
    for (let i = 0; i < this.arrLength; i++) {
      var data = []
      let id = response.data[i].idDepartment
      let departmentName = response.data[i].name
      let numUserResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
      data.push(departmentName, numUserResponse.data.length)
      console.log(data)
      datasets.push(data)
    }
    console.log(datasets)
    this.tableUserData = datasets
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
