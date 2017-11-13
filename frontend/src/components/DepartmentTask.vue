<template lang="html">
  <div class="department-task--container">
    <section class="department-task-table--body">
      <div style="font-size: 18px;">
        <b>Department {{department}}</b>
      </div>
      <br>
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
                  {{ props.row.idProject }}
                  <!-- {{ props.row }} -->
              </b-table-column>

              <b-table-column field="card Name" label="Card Name" sortable>
                  {{ props.row.name }}
              </b-table-column>

              <b-table-column field="Start date" label="Start date" sortable>
                  {{ props.row.startDate }}
              </b-table-column>

              <b-table-column field="End date" label="End date" sortable>
                  {{ props.row.endDate }}
              </b-table-column>

              <b-table-column field="owner" label="Owner" sortable>
                  {{ props.row.idUser }}
              </b-table-column>

              <b-table-column field="status" label="Status" sortable>
                <span class="tag is-info" v-if="props.row.status === 'In progress'">
                    {{ props.row.status }}
                </span>
                <span class="tag is-warning" v-if="props.row.status === 'Request to finish'">
                    {{ props.row.status }}
                </span>
                <span class="tag is-danger" v-if="props.row.status === 'Request to delete'">
                    {{ props.row.status }}
                </span>
                <span class="tag is-success" v-if="props.row.status === 'Finish'">
                    {{ props.row.status }}
                </span>
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
      tableData: [{ 'no': '', 'idProject': '', 'name': '', 'startDate': '', 'endDate': '', 'idUser': '', 'status': '' }],
      department: '',
      arrLength: ''
    }
  },
  async mounted () {
    this.department = localStorage.getItem('user_department')
    // get user list by sending department name of that user
    let userListResponse = await Axios.get(`http://localhost:8090/get/idUser/department/${this.department}`)

    let departmentCardResponse = await Axios.get(`http://localhost:8091/get/department-project-card/${userListResponse.data}`)
    console.log(departmentCardResponse)
    this.tableData = departmentCardResponse.data
    this.arrLength = departmentCardResponse.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let idUser = departmentCardResponse.data[i].idUser
      let idProject = departmentCardResponse.data[i].idProject
      let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${idUser}`)
      let projectResponse = await Axios.get(`http://localhost:8091/get/project/${idProject}`)
      this.tableData[i].idUser = nameResponse.data.name
      this.tableData[i].idProject = projectResponse.data.name
    }
  }
}
</script>

<style scoped>
.department-task--container {
  background-color: #fff;
  padding: 30px;
}
.department-task--table {
  margin-top: 20px;
}
</style>
