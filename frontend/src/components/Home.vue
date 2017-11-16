<template lang="html">
  <div class="home--container">
    <el-row class="home--chart">
      <br>
      <el-col :span="11">
        <h1><b> Organization </b></h1><br>
        <b-table
          class="task-management--table"
          :data="tableUserData"
          :paginated="true"
          :per-page="7"
          default-sort="title">
          <template scope="props">
            <b-table-column field="department" label="Department">
                {{ props.row.name }}
            </b-table-column>
            <b-table-column field="numUser" label="Internal">
                {{ props.row.numUser }}
            </b-table-column>
          </template>
        </b-table>
        <modal-person> </modal-person>
        <br>
      </el-col>
      <el-col :span="2">
        <br>
      </el-col>
      <el-col :span="11">
        <h1><b>Task management</b></h1><br>
        <b-table
          class="task-management--table"
          :data="tableProjectData"
          :paginated="true"
          :per-page="7"
          default-sort="title">
          <template scope="props">
            <b-table-column field="department" label="Department">
                {{ props.row.name }}
            </b-table-column>
            <b-table-column field="numProject" label="Projects">
                {{ props.row.numProject }}
            </b-table-column>
            <b-table-column field="numProjectCard" label="Cards">
                {{ props.row.numTaskCard }}
            </b-table-column>
          </template>
        </b-table>
        <modal-task> </modal-task>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
import ModalTask from '@/components/ModalTask'
import ModalPerson from '@/components/ModalPerson'
import Axios from 'axios'
export default {
  data () {
    return {
      arrLength: 0,
      dialogVisible: false,
      tableUserData: [{ 'name': 'DapartmentName', 'numUser': 'Internal' }],
      tableProjectData: [{ 'name': 'DapartmentName', 'numProject': '3', 'numProjectCard': '5' }]
    }
  },
  async mounted () {
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.tableUserData = response.data
    this.tableProjectData = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      // user data
      let id = response.data[i].idDepartment
      let numUserResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
      this.tableUserData[i].numUser = numUserResponse.data.length
      // project data
      let name = response.data[i].name
      let userListResponse = await Axios.get(`http://localhost:8090/get/idUser/department/A`)
      let departmentProjectResponse = await Axios.get(`http://localhost:8091/get/department-project/${userListResponse.data}`)
      let departmentCardResponse = await Axios.get(`http://localhost:8091/get/department-project-card/${userListResponse.data}`)
      console.log(name)
      console.log(departmentProjectResponse)
      console.log(departmentCardResponse)
      this.tableProjectData[i].numProject = departmentProjectResponse.data.length
      this.tableProjectData[i].numProjectCard = departmentCardResponse.data.length
      console.log('---')
    }
  },
  computed: {
    // ...mapGetters({
    //   getUser: 'GET_USER'
    // })
  },
  methods: {
    // ...mapActions({
    //   setUser: 'SET_USER'
    // })
  },
  components: {
    ModalTask,
    ModalPerson
  }
}
</script>

<style scoped>
.home--container {
  background-color: #fff;
  padding: 30px;
}
.home--table {
  margin-bottom: 30px;
}
.scroll {
  /*scroll-behavior: smooth;*/
}
</style>
