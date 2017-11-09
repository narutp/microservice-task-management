<template lang="html">
  <div class="my-task--container">
    <section class="my-task-table--body">
      <div style="font-size: 18px;">
        <b>My Project</b>
      </div>
      <div class="my-task--button" align="right">
        <button class="button is-primary" @click="createCard()">
          <i class="fa fa-plus" aria-hidden="true"> Create Card</i>
        </button>
      </div>
      <b-table
          class="my-task--table"
          :data="tableData"
          :paginated="true"
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="project" label="Project" sortable>
                  {{ props.row.idProject }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Task Card" sortable>
                  <u><span @click="updateCard()" class="my-task--span-task-name">{{ props.row.name }}</span></u>
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="writer" label="Owner" sortable>
                  {{ props.row.idUser }}
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
      tableData: [{ 'no': 1, 'idProject': 'Microservice', 'name': 'Login', 'registeredDate': '2017-08-23', 'idUser': 'Net', 'status': 'In progress' }],
      isPaginationSimple: false,
      arrLength: '',
      arrProject: []
    }
  },
  methods: {
    createCard () {
      this.$router.replace({ path: '/create-card' })
    },
    updateCard () {
      this.$router.replace({ path: '/update-card' })
    }
  },
  async mounted () {
    let cardResponse = await Axios.get(`http://localhost:8091/get/all-card/`)
    this.tableData = cardResponse.data
    this.arrLength = cardResponse.data.length

    for (let i = 0; i < this.arrLength; i++) {
      let idProject = cardResponse.data[i].idProject
      let idUser = cardResponse.data[i].idUser
      let projectResponse = await Axios.get(`http://localhost:8091/get/project/${idProject}`)
      let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${idUser}`)
      this.tableData[i].idProject = projectResponse.data.name
      this.tableData[i].idUser = nameResponse.data.name
    }
    // console.log(cardResponse.data)
    // for (let i = 0; i < this.arrLength; i++) {
    //   let id = cardResponse.data[i].idUser
    //   let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${id}`)
    //   this.tableData[i].idUser = nameResponse.data.name
    // }
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
.my-task--span-task-name {
  cursor:pointer;
  color:blue;
  text-decoration:underline;
}
</style>
