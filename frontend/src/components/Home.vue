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
                <b-table-column field="department" label="Department" sortable>
                    {{ props.row.name }}
                </b-table-column>

                <b-table-column field="numUser" label="Internal" sortable>
                    {{ props.row.numUser }}
                </b-table-column>
            </template>
        </b-table>

        <b-table
            class="task-management--table"
            :data="tableProjectData"
            :paginated="true"
            :per-page="7"
            default-sort="title">

            <template scope="props">
                <b-table-column field="no" label="No" width="50" sortable numeric centered>
                    {{ props.row.no }}
                </b-table-column>

                <b-table-column field="projectName" label="Project Name" sortable>
                    {{ props.row.name }}
                </b-table-column>

                <b-table-column field="registeredDate" label="Registered Date" sortable>
                    {{ props.row.registeredDate }}
                </b-table-column>

                <b-table-column field="owner" label="Owner" sortable>
                    {{ props.row.idUser }}
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
            class="home--table"
            :data="tableProjectData"
            default-sort="title">
            <template scope="props">
              <b-table-column field="Department" label="Department" width="50" sortable>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="Project" label="Total Project" sortable>
                  {{ props.row.project }}
              </b-table-column>

              <b-table-column field="TaskCard" label="Total Task Card" sortable>
                  {{ props.row.taskCard }}
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
      tableProjectData: [{ 'department': 'A', 'project': 3, 'taskCard': 5 }]
    }
  },
  async mounted () {
    // let response = await Axios.get(`http://localhost:8091/get/all-project/`)
    // this.tableUserData = response.data
    // this.arrLength = response.data.length
    // for (let i = 0; i < this.arrLength; i++) {
    //   let id = response.data[i].idUser
    //   let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${id}`)
    //   console.log(nameResponse)
    //   this.tableUserData[i].idUser = nameResponse.data.name
    // }
    // user
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.tableUserData = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = response.data[i].idDepartment
      let numResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
      console.log(numResponse)
      this.tableUserData[i].numUser = numResponse.data.length
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
