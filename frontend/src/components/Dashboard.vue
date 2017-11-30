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
          :loading="isLoading"
          default-sort="title">
          <template scope="props">
            <b-table-column field="name" label="Department">
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
          :loading="isLoading"
          default-sort="title">
          <template scope="props">
            <b-table-column field="name" label="Departments">
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

export default {
  data () {
    return {
      arrLength: 0,
      dialogVisible: false,
      tableUserData: [{ 'name': '', 'numUser': '' }],
      tableProjectData: [{ 'name': 'DapartmentName', 'numProject': '', 'numProjectCard': '' }],
      isLoading: true
    }
  },
  async mounted () {
    let response = await Axios.get(`//210.121.158.165:8090/get/all-department`)
    console.log(response)

    this.tableUserData = response.data
    this.tableProjectData = response.data
    let arrLength = response.data.length

    for (let i = 0; i < arrLength; i++) {
      let departmentName = response.data[i].name
      let userListResponse = await Axios.get(`//210.121.158.165:8090/get/idUser?departmentName=${departmentName}`)
      let countUser = userListResponse.data.length
      setTimeout(() => {
        this.isLoading = false
      }, 2 * 1000)
      this.tableUserData[i].numUser = countUser
      let projectResponse = await Axios.get(`//210.121.158.162:8091/get/department-project?idUserList=${userListResponse.data}`)
      // console.log(projectResponse)
      this.tableProjectData[i].numProject = projectResponse.data.length

      let cardResponse = await Axios.get(`//210.121.158.162:8091/get/department-project-card?idUserList=${userListResponse.data}`)
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
