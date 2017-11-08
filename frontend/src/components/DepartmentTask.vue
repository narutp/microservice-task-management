<template lang="html">
  <div class="department-task--container">
    <section class="department-task-table--body">
      <div style="font-size: 18px;">
        <b>Department </b>
      </div>
      <b-table
          class="department-task--table"
          :data="tableData"
          :paginated="isPaginated"
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="taskName" label="Project Name" sortable>
                  {{ props.row.taskName }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Card Name" sortable>
                  {{ props.row.taskCardName }}
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="writer" label="Writer" sortable>
                  {{ props.row.writer }}
              </b-table-column>

              <b-table-column field="status" label="Status" sortable centered>
                  <span class="tag is-info" v-if="props.row.status === 'In progress'">
                      {{ props.row.status }}
                  </span>
                  <span class="tag is-warning" v-if="props.row.status === 'Request to finish'">
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
      tableData: [{ 'no': 1, 'taskName': 'Microservice', 'taskCardName': 'No Idea', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 2, 'taskName': 'Big Data', 'taskCardName': 'Data science part', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 3, 'taskName': 'Small Data', 'taskCardName': 'Data math part', 'registeredDate': '2017-11-1', 'writer': 'Boo', 'status': 'Request to finish' },
    { 'no': 4, 'taskName': 'Machine Learning', 'taskCardName': 'Net Card', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'Request to finish' }],
      isPaginated: true,
      department: ''
    }
  },
  async mounted () {
    let department = localStorage.getItem('user_department')
    // get user list by sending department name of that user
    let userListResponse = await Axios.get(`http://localhost:8090/get/idUser/department/${department}`)
    console.log(userListResponse.data)

    let departmentProjectResponse = await Axios.get(`http://localhost:8091/get/department-project/${userListResponse.data}`)
    console.log(departmentProjectResponse.data)
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
