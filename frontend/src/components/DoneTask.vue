<template lang="html">
  <div class="done-task--container">
    <section class="done-task-table--body">
      <div class="done-task--button" align="right">
      </div>
      <b-table
          class="done-task--table"
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
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': '', 'project': '', 'card': '', 'finishDate': '', 'owner': '', 'status': '' }],
      isPaginated: true,
      isPaginationSimple: false
    }
  },
  async mounted () {
    let idUser = localStorage.getItem('user_userId')
    let cardResponse = await Axios.get(`http://localhost:8091/get/finished-card/${idUser}`)
    console.log(cardResponse)
    this.tableData = cardResponse.data
  }
}
</script>

<style scoped>
.done-task--container {
  background-color: #fff;
  padding: 30px;
}
.done-task--table {
  margin-top: 20px;
}
</style>
