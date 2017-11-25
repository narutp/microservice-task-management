<template lang="html">
  <div class="dashboard--container">
    <el-row class="dashboard--chart">
      <br>
      <el-col :span="11">
        <h1><b> Organization </b></h1><br>
        <b-table
          class="dashboard--table"
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
          class="dashboard--table"
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
                {{ props.row.numProjectCard }}
            </b-table-column>
          </template>
        </b-table>
        <modal-task> </modal-task>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import ModalTask from '@/components/ModalTask'
import ModalPerson from '@/components/ModalPerson'
import Axios from 'axios'
import Auth from '@/components/Auth'

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
    Auth.authenticate.loggedIn.login()
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.tableUserData = response.data
    this.tableProjectData = response.data

    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      // user data
      let name = response.data[i].name
      // console.log(name)
      let userList = await Axios.get(`http://localhost:8090/get/idUser?departmentName=${name}`)
      this.tableUserData[i].numUser = userList.data.length
      // console.log(userList.data)

      // project data
      let projectResponse = await Axios.get(`http://localhost:8091/get/department-project?idUserList=${userList.data}`)
      // console.log(projectResponse)
      this.tableProjectData[i].numProject = projectResponse.data.length

      let cardResponse = await Axios.get(`http://localhost:8091/get/department-project-card?idUserList=${userList.data}`)
      // console.log(cardResponse.data.length)
      this.tableProjectData[i].numProjectCard = cardResponse.data.length
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
.dashboard--container {
  background-color: #fff;
  padding: 30px;
}
.dashboard--table {
  margin-bottom: 30px;
}
.scroll {
  /*scroll-behavior: smooth;*/
}
</style>
