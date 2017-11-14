<template lang="html">
  <div class="update-project--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Update Task Card </b></h6>
      </div>
      <div class="update-project--button-request" align="right">
        <button class="button is-info" @click="requestToFinish()">
          <span>Request to finish</span>
        </button>
      </div>
      <div class="update-project--button-request" align="right">
        <button class="button is-danger" @click="requestToDelete()" :disabled="!ownerAuthority">
          <i class="fa fa-trash" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <b>Card</b>
        <input :disabled="!ownerAuthority" v-model="cardName" class="input title-field" type="text" placeholder="Create mood board">
      </div>
      <div class="column" align="right">
        <div align="left">
          <b>Project</b>
        </div>
        <el-input
          v-model="projectName"
          :disabled="true">
        </el-input>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea :disabled="!ownerAuthority" v-model="cardDescription" class="textarea" placeholder="Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.">
        </textarea>
      </div>
    </div>

    <div class="columns">
      <div class="column is-one-quarters">
        <el-date-picker
          v-model="startDate"
          type="date"
          :disabled="true"
          placeholder="Start date">
        </el-date-picker>
      </div>
      <div class="column is-one-quarters">
        <el-date-picker
          v-model="endDate"
          type="date"
          :disabled="!ownerAuthority"
          placeholder="End date">
        </el-date-picker>
      </div>
      <div class="column" align="right">
        <button class="button" style="width: 200px" @click="updateParticipant()" :disabled="!ownerAuthority">
          <span> Add Participants + </span>
        </button>
        <!-- <button class="button" style="width: 200px" @click="addExternal()">
          <span> Add External + </span>
        </button> -->
      </div>
    </div>

    <section class="my-task-table--body">
      <b-table
          class="my-task--table"
          :data="tableData"
          :paginated="true"
          :per-page="5"
          default-sort="title">
          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="department" label="Department" width="150" sortable centered>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="name" label="Name" width="250" sortable>
                  {{ props.row.user }}
              </b-table-column>

              <b-table-column field="writer" label="Position" sortable>
                  {{ props.row.position }}
              </b-table-column>

              <b-table-column field="email" label="Email" sortable>
                  {{ props.row.email }}
              </b-table-column>

              <b-table-column field="status" label="Status"centered>
                  <span class="tag is-success" v-if="props.row.status === 'INTERNAL'">
                      {{ props.row.status }}
                  </span>
              </b-table-column>
          </template>
      </b-table>
    </section>

    <hr>
    <section class="my-task-table--body">
      <b-table
          class="my-task--table"
          :data="tableData2"
          :paginated="true"
          :per-page="5"
          default-sort="title">
          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="department" label="Department" width="150" sortable centered>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="name" label="Name" width="250" sortable>
                  {{ props.row.user }}
              </b-table-column>

              <b-table-column field="writer" label="Position" sortable>
                  {{ props.row.position }}
              </b-table-column>

              <b-table-column field="email" label="Email" sortable>
                  {{ props.row.email }}
              </b-table-column>

              <b-table-column field="status" label="Status"centered>
                  <span class="tag is-info" v-if="props.row.status === 'EXTERNAL'">
                      {{ props.row.status }}
                  </span>
              </b-table-column>
          </template>
      </b-table>
    </section>

    <br>
    <br>
    <div class="columns" align="center">
      <div class="column">
          <a :disabled="!ownerAuthority" class="button is-dark" @click="updateCard()">Update Card</a>
      </div>
    </div>
    <request-finish-dialog :request-finish-list="requestFinishList"></request-finish-dialog>
    <request-delete-dialog :request-delete-list="requestDeleteList"></request-delete-dialog>
  </div>
</template>

<script>
import RequestDeleteDialog from '@/components/RequestDeleteDialog'
import RequestFinishDialog from '@/components/RequestFinishDialog'
import Axios from 'axios'
import moment from 'moment'
export default {
  data () {
    return {
      tableData: [{ 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' }],
      tableData2: [{ 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' },
      { 'no': '', 'user': '', 'department': '', 'position': '', 'email': '', 'status': '' }],
      isPaginated: true,
      isPaginationSimple: false,
      cardName: '',
      cardDescription: '',
      projectName: '',
      startDate: '',
      endDate: '',
      internalList: '',
      externalList: '',
      ownerAuthority: false,
      idCard: '',
      idUser: '',
      requestFinishList: {
        idCard: '',
        idUser: '',
        dialogRequestFinishClicked: false
      },
      requestDeleteList: {
        idCard: '',
        idUser: '',
        dialogRequestDeleteClicked: false
      }
    }
  },
  methods: {
    async updateCard () {
      let cardResponse = await Axios.get(`http://localhost:8091/get/project-card/${this.idCard}`)
      this.internalList = cardResponse.data.internalParticipants
      this.externalList = cardResponse.data.externalParticipants
      let formatEndDate = moment(this.endDate).format('YYYY-MM-DD')
      let updateResponse = await Axios.post(`http://localhost:8091/update/project-card/${this.idCard}/${this.cardName}/${this.cardDescription}/${formatEndDate}/${this.internalList}/${this.externalList}`)

      if (updateResponse.data === true) {
        this.$router.replace({ path: '/my-project' })
      } else {
        alert('fail to update card')
      }
    },
    requestToFinish () {
      this.requestFinishList.dialogRequestFinishClicked = true
    },
    requestToDelete () {
      this.requestDeleteList.dialogRequestDeleteClicked = true
    },
    updateParticipant () {
      this.$router.replace({ path: '/update-participants' })
    }
  },
  async mounted () {
    this.idCard = localStorage.getItem('card_update')
    this.idUser = localStorage.getItem('user_userId')
    // Prepare data for sending when click request button
    // Reason that have to put into 1 obj because to mutate the props that been send.
    this.requestFinishList.idCard = this.idCard
    this.requestFinishList.idUser = this.idUser
    this.requestDeleteList.idCard = this.idCard
    this.requestDeleteList.idUser = this.idUser

    let cardResponse = await Axios.get(`http://localhost:8091/get/project-card/${this.idCard}`)
    let idUserOfCard = cardResponse.data.idUser

    // Check owner authority (can be update or not)
    if (this.idUser === idUserOfCard) {
      this.ownerAuthority = true
    } else {
      this.ownerAuthority = false
    }

    this.cardName = cardResponse.data.name
    this.cardDescription = cardResponse.data.description
    this.startDate = cardResponse.data.startDate
    this.endDate = cardResponse.data.endDate

    let projectResponse = await Axios.get(`http://localhost:8091/get/project/${cardResponse.data.idProject}`)
    this.projectName = projectResponse.data.name

    // get arr length of both internal and external user in a card to find their names
    let internalArrLength = cardResponse.data.internalParticipants.length
    let externalArrLength = cardResponse.data.externalParticipants.length
    // set value of internal participant table
    for (let i = 0; i < internalArrLength; i++) {
      let idInternalUser = cardResponse.data.internalParticipants[i]
      let internalNameResponse = await Axios.get(`http://localhost:8090/get/user/id/${idInternalUser}`)
      let internalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id/${internalNameResponse.data.idPosition}`)
      let internalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id/${internalNameResponse.data.idDepartment}`)
      this.tableData[i].user = internalNameResponse.data.name
      this.tableData[i].email = internalNameResponse.data.email
      this.tableData[i].status = 'INTERNAL'
      this.tableData[i].position = internalPositionResponse.data.name
      this.tableData[i].department = internalDepartmentResponse.data.name
    }
    // set value of external participant table
    for (let i = 0; i < externalArrLength; i++) {
      let idExternalUser = cardResponse.data.externalParticipants[i]
      let externalNameResponse = await Axios.get(`http://localhost:8090/get/user/id/${idExternalUser}`)
      let externalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id/${externalNameResponse.data.idPosition}`)
      let externalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id/${externalNameResponse.data.idDepartment}`)
      this.tableData2[i].user = externalNameResponse.data.name
      this.tableData2[i].email = externalNameResponse.data.email
      this.tableData2[i].status = 'EXTERNAL'
      this.tableData2[i].position = externalPositionResponse.data.name
      this.tableData2[i].department = externalDepartmentResponse.data.name
    }
  },
  components: {
    RequestFinishDialog,
    RequestDeleteDialog
  }
}
</script>

<style scoped>
.update-project--button-request-finish {
  margin: 0;
}
/*.create-section {
  margin: 20px;
}
.no-border {
  border: 0;
}
.placeholder {
  color: #BDBDBD;
}
.title-field {
  margin-top: 8px;
  margin-bottom: 8px;
  border: 0;
  background-color: #fff;
  height: 48px;
}
textarea {
  margin-top: 8px;
  margin-bottom: 8px;
  border: 0;
  background-color: #fff;
  height: 300px;
}*/
</style>
