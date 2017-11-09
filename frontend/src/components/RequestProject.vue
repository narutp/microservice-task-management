<template lang="html">
  <div class="request-project--container">
    <section class="request-project-table--body">
      <div style="font-size: 18px;">
        <b>Request Project</b>
      </div>
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
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'idCard': '', 'date': '', 'status': ''}],
      idUser: '',
      isPaginated: true,
      isPaginationSimple: false
    }
  },
  async mounted () {
    this.idUser = localStorage.getItem('user_userId')
    let requestResponse = await Axios.get(`http://localhost:8091/get/all-request/${this.idUser}`)
    console.log(requestResponse)
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
