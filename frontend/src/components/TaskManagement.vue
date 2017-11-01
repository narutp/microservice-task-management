<template lang="html">
  <div class="task-management--container">
    <section class="task-management-table--body">
      <div class="task-management--button" align="right">
        <button class="button is-primary" @click="createProject()">
          <i class="fa fa-plus" aria-hidden="true"> Create Project</i>
        </button>
      </div>
      {{ tableData }}
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
                  <u><span @click="updateProject()" class="project-management--span-task-name"> {{ props.row.projectName }} </span></u>
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="owner" label="Owner" sortable>
                  {{ props.row.owner }}
              </b-table-column>
          </template>
      </b-table>
    </section>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'projectName': 'Test Project 1', 'registeredDate': '1', 'owner': 'Boo' },
      { 'no': 2, 'projectName': 'Test Project 2', 'registeredDate': '2', 'owner': 'Boo' }]
    }
  },
  beforeCreate () {
    // TODO: problem with core
    var self = this
    Axios.get(`http://localhost:8091/get/all-project/`).then(function (response) {
      self.tableData = response.data
    }).catch(function (error) {
      console.log(error)
    })
  },
  methods: {
    createProject () {
      this.$router.replace({ path: '/create-project' })
    },
    updateProject () {
      this.$router.replace({ path: '/update-project' })
    }
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
