<template lang="html">
  <div class="department-task--container">
    <section class="department-task-table--body">
      <div style="font-size: 18px;">
        <b>Department {{department}}</b>
      </div>
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
                  <u><span @click="showDescription(props.row)" class="department-project--span-task-name"> {{ props.row.name }} </span></u>
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
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'name': 'Test Project 1', 'registeredDate': '1', 'idUser': 'Boo' },
      { 'no': 2, 'name': 'Test Project 2', 'registeredDate': '2', 'idUser': 's' }],
      isPaginated: true,
      department: '',
      arrLength: ''
    }
  },
  async mounted () {
    this.department = localStorage.getItem('user_department')
    // get user list by sending department name of that user
    let userListResponse = await Axios.get(`http://localhost:8090/get/idUser/department/${this.department}`)

    let departmentProjectResponse = await Axios.get(`http://localhost:8091/get/department-project/${userListResponse.data}`)
    console.log(departmentProjectResponse.data)
    this.tableData = departmentProjectResponse.data
    this.arrLength = departmentProjectResponse.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = departmentProjectResponse.data[i].idUser
      let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${id}`)
      this.tableData[i].idUser = nameResponse.data.name
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
