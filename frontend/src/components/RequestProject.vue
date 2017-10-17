<template lang="html">
  <div class="request-project--container">
    <section class="request-project-table--body">
      <div class="request-project--button" align="right">
      </div>
      <b-table
          class="request-project--table"
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

              <b-table-column field="registeredDate" label="Requested Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="writer" label="Requester" sortable>
                  {{ props.row.writer }}
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
      tableData: [{ 'no': 1, 'taskName': 'Microservice', 'taskCardName': 'Login authentication test', 'registeredDate': '2017-08-23', 'writer': 'Boo' },
    { 'no': 2, 'taskName': 'Big Data', 'taskCardName': 'Register Card', 'registeredDate': '2017-08-1', 'writer': 'Boo' },
  { 'no': 3, 'taskName': 'Machine Learning', 'taskCardName': 'Decoration CSS', 'registeredDate': '2017-07-21', 'writer': 'Boo' }],
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
.request-project--container {
  background-color: #fff;
  padding: 30px;
}
.request-project--table {
  margin-top: 20px;
}
</style>
