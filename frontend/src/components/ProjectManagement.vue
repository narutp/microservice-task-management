<template lang="html">
  <div class="project-management--container">
    <section class="project-management--table-body">
      <div style="font-size: 18px;">
        <b>Project Management</b>
      </div>
      <div class="project-management--button" align="right">
        <button class="button is-primary" @click="createProject()">
          <i class="fa fa-plus" aria-hidden="true"> Create Project</i>
        </button>
      </div>
      <!-- {{ tableData }} -->
      <b-table
          class="project-management--table"
          :data="tableData"
          :paginated="true"
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="projectName" label="Project Name" sortable>
                  <u><span @click="updateProject(props.row)" class="project-management--span-project-name"> {{ props.row.name }} </span></u>
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
    <update-project :update-project-list="updateProjectList"></update-project>
  </div>
</template>

<script>
import UpdateProject from '@/components/UpdateProject'
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
    console.log(response.data)
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
    UpdateProject
  }
}
</script>

<style scoped>
/* TODO move all clas-container css to main css */
.project-management--container {
  background-color: #fff;
  padding: 30px;
}
.project-management--table {
  margin-top: 20px;
}
.project-management--span-project-name {
  cursor:pointer;
  color:blue;
  text-decoration:underline;
}
</style>
