<template lang="html">
  <div class="done-project--container">
    <section class="done-project--table-body">
      <div style="font-size: 18px;">
        <b>Done Card</b>
      </div>
      <div class="done-project--button" align="right">
      </div>
      <b-table
          class="done-project--table"
          :data="tableData"
          :paginated="isPaginated"
          :per-page="7"
          default-sort="title">

          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="taskName" label="Project Name" sortable>
                  {{ props.row.idProject }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Task Card Name" sortable>
                  {{ props.row.name }}
              </b-table-column>

              <b-table-column field="registeredDate" label="Finish Date" sortable>
                  {{ props.row.finishDate }}
              </b-table-column>

              <b-table-column field="writer" label="Owner" sortable>
                  {{ props.row.idUser }}
              </b-table-column>

              <b-table-column field="status" label="Status" sortable centered>
                  <span class="tag is-success">
                      {{ props.row.status }}
                  </span>
              </b-table-column>
          </template>
      </b-table>
    </section>
  </div>
</template>

<script src="@/components/js/DoneProject">
// import Axios from 'axios'
// export default {
//   data () {
//     return {
//       tableData: [{ 'no': '', 'idProject': '', 'name': '', 'finishDate': '', 'idUser': '', 'status': '' }],
//       isPaginated: true,
//       isPaginationSimple: false,
//       arrLength: ''
//     }
//   },
//   async mounted () {
//     let idUser = localStorage.getItem('user_userId')
//     let cardResponse = await Axios.get(`//210.121.158.162:8091/get/finish-project-card?idUser=${idUser}`)
//     console.log(cardResponse)
//     this.tableData = cardResponse.data
//     this.arrLength = cardResponse.data.length
//
//     for (let i = 0; i < this.arrLength; i++) {
//       let idProject = cardResponse.data[i].idProject
//       let idUser = cardResponse.data[i].idUser
//       let projectResponse = await Axios.get(`//210.121.158.162:8091/get/project?idProject=${idProject}`)
//       let nameResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
//       this.tableData[i].idProject = projectResponse.data.name
//       this.tableData[i].idUser = nameResponse.data.name
//     }
//   }
// }
</script>

<style scoped>
.done-project--container {
  background-color: #fff;
  padding: 30px;
}
.done-project--table {
  margin-top: 20px;
}
</style>
