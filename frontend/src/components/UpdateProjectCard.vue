<template lang="html">
  <div class="update-project-card--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Update Task Card </b></h6>
      </div>
      <div class="update-project-card--button-request" align="right">
        <button class="button is-info" @click="requestToFinish()">
          <span>Request to finish</span>
        </button>
      </div>
      <div class="update-project-card--button-request" align="right">
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
        <textarea :disabled="!ownerAuthority" v-model="cardDescription" class="textarea">
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
          :disabled="true"
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

    <section class="update-project-card--table-body">
      <b-table
          class="update-project-card--table"
          :data="tableDataInternal"
          :paginated="true"
          :per-page="5"
          :loading="isInternalLoading"
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
                  <span class="tag is-success">
                      Internal
                  </span>
              </b-table-column>
          </template>
      </b-table>
    </section>

    <hr>
    <section class="update-project-card--table-body">
      <b-table
          class="update-project-card--table"
          :data="tableDataExternal"
          :paginated="true"
          :per-page="5"
          :loading="isExternalLoading"
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
                  <span class="tag is-info">
                      External
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
import RequestDeleteDialog from '@/components/Dialog/RequestDeleteDialog'
import RequestFinishDialog from '@/components/Dialog/RequestFinishDialog'
import Axios from 'axios'
import moment from 'moment'
export default {
  data () {
    return {
      tableDataInternal: [{ 'user': '', 'department': '', 'position': '', 'email': '' }],
      tableDataExternal: [{ 'user': '', 'department': '', 'position': '', 'email': '' }],
      isPaginated: true,
      isPaginationSimple: false,
      cardName: '',
      cardDescription: '',
      projectName: '',
      date: '',
      internalList: '',
      externalList: '',
      isInternalLoading: true,
      isExternalLoading: true,
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
      let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/idProjectCard?idProjectCard=${this.idCard}`)
      this.internalList = cardResponse.data.internalParticipants
      this.externalList = cardResponse.data.externalParticipants
      let formatEndDate = moment(this.endDate).format('YYYY-MM-DD')
      let updateResponse = await Axios.post(`//210.121.158.162:8091/update/project-card?idProjectCard=${this.idCard}&name=${this.cardName}&description=${this.cardDescription}&endDate=${formatEndDate}&internalParticipants=${this.internalList}&externalParticipants=${this.externalList}`)

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

    let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/idProjectCard?idProjectCard=${this.idCard}`)
    let projectResponse = await Axios.get(`//210.121.158.162:8091/get/project?idProject=${cardResponse.data.idProject}`)
    let idUserOfCard = cardResponse.data.idUser

    // Check owner authority (can be update or not)
    if (this.idUser === idUserOfCard) {
      this.ownerAuthority = true
    } else {
      this.ownerAuthority = false
    }
    // set card details
    this.cardName = cardResponse.data.name
    this.cardDescription = cardResponse.data.description
    this.startDate = cardResponse.data.startDate
    this.endDate = cardResponse.data.endDate
    this.projectName = projectResponse.data.name

    // get array string of participant
    let internalResponse = await Axios.get(`//210.121.158.162:8091/get/internal-participants?idProjectCard=${this.idCard}`)
    let externalResponse = await Axios.get(`//210.121.158.162:8091/get/external-participants?idProjectCard=${this.idCard}`)

    // then find length of the array to find User object to put in the table
    let externalArrLength = externalResponse.data.length
    let internalArrLength = internalResponse.data.length

    // this array will keep all information of list of user
    let externalUserArr = []
    let internalUserArr = []

    for (let i = 0; i < internalArrLength; i++) {
      let idUser = internalResponse.data[i]
      let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
      internalUserArr.push(userResponse.data)
    }
    // set internal table attribute to link with the array
    this.tableDataInternal = internalUserArr

    for (let i = 0; i < externalArrLength; i++) {
      let idUser = externalResponse.data[i]
      let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
      externalUserArr.push(userResponse.data)
    }
    // set external table attribute to link with the array
    this.tableDataExternal = externalUserArr

    // for loop to put exact information into the internal table
    for (let i = 0; i < internalUserArr.length; i++) {
      this.tableDataInternal[i].user = internalUserArr[i].name
      this.tableDataInternal[i].email = internalUserArr[i].email

      // get position by positionId
      let idPosition = internalUserArr[i].idPosition
      let internalPositionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)

      // get department by departmentId
      let idDepartment = internalUserArr[i].idDepartment
      let internalDepartmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)

      // set position name and department name into table
      setTimeout(() => {
        this.isInternalLoading = false
      }, 2 * 1000)
      this.tableDataInternal[i].position = internalPositionResponse.data.name
      this.tableDataInternal[i].department = internalDepartmentResponse.data.name
    }

    // then for loop again to put exact information into the table
    for (let i = 0; i < externalUserArr.length; i++) {
      this.tableDataExternal[i].user = externalUserArr[i].name
      this.tableDataExternal[i].email = externalUserArr[i].email

      // get position by positionId
      let idPosition = externalUserArr[i].idPosition
      let externalPositionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)

      // get department by departmentId
      let idDepartment = externalUserArr[i].idDepartment
      let externalDepartmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)

      // set position name and department name into table
      setTimeout(() => {
        this.isExternalLoading = false
      }, 2 * 1000)
      this.tableDataExternal[i].position = externalPositionResponse.data.name
      this.tableDataExternal[i].department = externalDepartmentResponse.data.name
    }

    //
    // // get arr length of both internal and external user in a card to find their names
    // let internalArrLength = cardResponse.data.internalParticipants.length
    // let externalArrLength = cardResponse.data.externalParticipants.length
    // // set value of internal participant table
    // for (let i = 0; i < internalArrLength; i++) {
    //   let idInternalUser = cardResponse.data.internalParticipants[i]
    //   let internalNameResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idInternalUser}`)
    //   let internalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id?id=${internalNameResponse.data.idPosition}`)
    //   let internalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id?id=${internalNameResponse.data.idDepartment}`)
    //   this.tableData[i].user = internalNameResponse.data.name
    //   this.tableData[i].email = internalNameResponse.data.email
    //   this.tableData[i].status = 'INTERNAL'
    //   this.tableData[i].position = internalPositionResponse.data.name
    //   this.tableData[i].department = internalDepartmentResponse.data.name
    // }
    // // set value of external participant table
    // for (let i = 0; i < externalArrLength; i++) {
    //   let idExternalUser = cardResponse.data.externalParticipants[i]
    //   let externalNameResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idExternalUser}`)
    //   let externalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id?id=${externalNameResponse.data.idPosition}`)
    //   let externalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id?id=${externalNameResponse.data.idDepartment}`)
    //   this.tableData2[i].user = externalNameResponse.data.name
    //   this.tableData2[i].email = externalNameResponse.data.email
    //   this.tableData2[i].status = 'EXTERNAL'
    //   this.tableData2[i].position = externalPositionResponse.data.name
    //   this.tableData2[i].department = externalDepartmentResponse.data.name
    // }
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
</style>
