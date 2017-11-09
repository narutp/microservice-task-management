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
          paginated=true
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="taskName" label="Project Name" sortable>
                  {{ props.row.idProject }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Card Name" sortable>
                  {{ props.row.idCard }}
              </b-table-column>

              <b-table-column field="registeredDate" label="Requested Date" sortable>
                  {{ props.row.date }}
              </b-table-column>

              <b-table-column field="status" label="Type" sortable>
                <span class="tag is-warning" v-if="props.row.type === 'Request to finish'">
                    {{ props.row.type }}
                </span>
                <span class="tag is-danger" v-if="props.row.type === 'Request to delete'">
                    {{ props.row.type }}
                </span>
              </b-table-column>

              <b-table-column field="writer" label="Requester" sortable>
                  {{ props.row.idRequester }}
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
      tableData: [{ 'no': 1, 'idProject': '', 'idCard': '', 'date': 'a', 'type': 'a', 'idRequester': '' }],
      idUser: '',
      isPaginationSimple: false
    }
  },
  async mounted () {
    this.idUser = localStorage.getItem('user_userId')
    let requestResponse = await Axios.get(`http://localhost:8091/get/all-request/${this.idUser}`)
    this.tableData = requestResponse.data
    let requestArr = requestResponse.data.length

    for (let i = 0; i < requestArr; i++) {
      let idProject = requestResponse.data[i].idProject
      let idCard = requestResponse.data[i].idCard
      let idRequester = requestResponse.data[i].idRequester
      let projectResponse = await Axios.get(`http://localhost:8091/get/project/${idProject}`)
      let cardResponse = await Axios.get(`http://localhost:8091/get/card/${idCard}`)
      let requesterResponse = await Axios.get(`http://localhost:8090/get/user/id/${idRequester}`)
      this.tableData[i].idProject = projectResponse.data.name
      this.tableData[i].idCard = cardResponse.data.name
      this.tableData[i].idRequester = requesterResponse.data.name
    }
    // console.log(projectResponse)

    // let arrProject = projectResponse.data.length
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
