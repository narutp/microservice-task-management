<template lang="html">
  <div class="department-task--container">
    <section class="department-task-table--body">
      <div class="department-task--button" align="right">
        <button class="button is-primary" @click="registerTask()">
          <i class="fa fa-plus" aria-hidden="true"> Create Task</i>
        </button>
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

              <b-table-column field="taskName" label="Task Name" sortable>
                  {{ props.row.taskName }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Task Card Name" sortable>
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
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'taskName': 'Test Task1', 'taskCardName': 'Test Task Card1', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 2, 'taskName': 'Test Task2', 'taskCardName': 'Test Task Card2', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 3, 'taskName': 'Net Task', 'taskCardName': 'Net Task Card', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'Request to finish' }],
      isPaginated: true,
      isPaginationSimple: false
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
