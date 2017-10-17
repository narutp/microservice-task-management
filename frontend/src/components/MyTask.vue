<template lang="html">
  <div class="my-task--container">
    <section class="my-task-table--body">
      <div class="my-task--button" align="right">
        <button class="button is-primary" @click="createCard()">
          <i class="fa fa-plus" aria-hidden="true"> Create Card</i>
        </button>
      </div>
      <b-table
          class="my-task--table"
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
      tableData: [{ 'no': 1, 'taskName': 'Microservice', 'taskCardName': 'Login authentication test', 'registeredDate': '2017-08-23', 'writer': 'Net', 'status': 'In progress' },
    { 'no': 2, 'taskName': 'Big Data', 'taskCardName': 'Register Card', 'registeredDate': '2017-08-1', 'writer': 'Bob', 'status': 'In progress' },
    { 'no': 3, 'taskName': 'Net Experiment', 'taskCardName': 'Net Project Card', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'Request to finish' },
  { 'no': 3, 'taskName': 'Machine Learning', 'taskCardName': 'Decoration CSS', 'registeredDate': '2017-07-21', 'writer': 'Net', 'status': 'In progress' }],
      isPaginated: true,
      isPaginationSimple: false
    }
  },
  methods: {
    createCard () {
      this.$router.replace({ path: '/create-card' })
    }
  }
}
</script>

<style scoped>
.my-task--container {
  background-color: #fff;
  padding: 30px;
}
.my-task--table {
  margin-top: 20px;
}
</style>
