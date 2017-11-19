<template lang="html">
  <div class="task-management--container">
    <section class="task-management-table--body">
      <div style="font-size: 18px;">
        <b>Project Management</b>
      </div>
      <div class="task-management--button" align="right">
        <button class="button is-primary" @click="createProject()">
          <i class="fa fa-plus" aria-hidden="true"> Create Project</i>
        </button>
      </div>
      <!-- {{ tableData }} -->
      <b-table
          class="task-management--table"
          :data="tableData"
          :paginated="true"
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="projectName" label="Project Name" sortable>
                  <u><span @click="updateProject(props.row)" class="project-management--span-task-name"> {{ props.row.name }} </span></u>
                  <!-- {{ props.row }} -->
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="owner" label="Owner" sortable>
                  {{ props.row.idUser }}
              </b-table-column>
          </template>
      </b-table>
    </section>
    <update-task :update-project-list="updateProjectList"></update-task>
  </div>
</template>

<script>
import UpdateTask from '@/components/UpdateTask'
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'name': 'Test Project 1', 'registeredDate': '1', 'idUser': 'Boo' },
      { 'no': 2, 'name': 'Test Project 2', 'registeredDate': '2', 'idUser': 's' }],
      arrLength: 0,
      updateProjectList: {
        dialogClicked: false,
        projectName: '',
        projectDescription: '',
        projectId: ''
      }
    }
  },
  async mounted () {
    let response = await Axios.get(`http://localhost:8091/get/all-project/`)
    this.tableData = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = response.data[i].idUser
      let nameResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${id}`)
      console.log(nameResponse)
      this.tableData[i].idUser = nameResponse.data.name
    }
  },
  methods: {
    createProject () {
      this.$router.replace({ path: '/create-project' })
    },
    updateProject (row) {
      this.updateProjectList.dialogClicked = true
      this.updateProjectList.projectName = row.name
      this.updateProjectList.projectDescription = row.description
      this.updateProjectList.projectId = row.idProject
    }
  },
  components: {
    UpdateTask
  }
}
</script>

<style scoped>
/* TODO move all clas-container css to main css */
.task-management--container {
  background-color: #fff;
  padding: 30px;
}
.task-management--table {
  margin-top: 20px;
}
.project-management--span-task-name {
  cursor:pointer;
  color:blue;
  text-decoration:underline;
}
</style>
