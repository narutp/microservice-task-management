<template lang="html">
  <div class="task-management--container">
    <section class="task-management-table--body">
      <div class="task-management--button" align="right">
        <button class="button is-primary" @click="createProject()">
          <i class="fa fa-plus" aria-hidden="true"> Create Project</i>
        </button>
      </div>
      <b-table
          class="task-management--table"
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
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'taskName': 'Test Project 1', 'taskCardName': 'Test Card 1', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 2, 'taskName': 'Test Project 2', 'taskCardName': 'Test Card 2', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'In progress' },
    { 'no': 3, 'taskName': 'Net Project', 'taskCardName': 'Net Card', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'Request to finish' }],
      isPaginated: true,
      isPaginationSimple: false
    }
  },
  methods: {
    createProject () {
      this.$router.replace({ path: '/create-project' })
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
</style>
