<template lang="html">
  <div class="create-project-card--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>New Card +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border" @click="cancle()">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <div class="create-project-card--alert">
          <template>
            <el-alert v-if="alertInfo" type="info" show-icon :closable="false"
              title="Please fill all information below">
            </el-alert>
            <el-alert v-if="alertError" type="error" show-icon :closable="false"
              title="Please fill all information to create project card">
            </el-alert>
            <el-alert v-if="alertErrorDuplicate" type="error" show-icon :closable="false"
              title="Duplicate project card name">
            </el-alert>
            <el-alert v-if="disabledInput" type="success" show-icon :closable="false"
              title="Add participant success">
            </el-alert>
          </template>
        </div>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <input v-model="cardName" class="input title-field" type="text" placeholder="Card name" :disabled="disabledInput">
      </div>
      <div class="column">
        <b-select v-model="project" align="right" placeholder="Project" :disabled="disabledInput">
          <option
            v-for="option in allProject"
            :value="option"
            :key="option">
            {{ option }}
          </option>
        </b-select>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea v-model="description" class="textarea" placeholder="Description" :disabled="disabledInput"></textarea>
      </div>
    </div>

    <div class="columns">
      <div class="column is-one-quarters">
        <el-date-picker
          :disabled="disabledInput"
          v-model="date"
          type="daterange"
          range-separator=" to "
          placeholder="Pick date range"
          :picker-options="startDateOption">
        </el-date-picker>
      </div>
      <div class="column" align="right">
        <button class="button" style="width: 200px" @click="addParticipant()" :disabled="disabledInput">
          <span> Add Participants + </span>
        </button>
      </div>
    </div>

    <section class="create-project-card--table-body">
      <b-table
          class="create-project-card--table"
          :data="tableDataInternal"
          :paginated="true"
          :per-page="5"
          :loading="isInternalLoading"
          default-sort="title">
          <template scope="props">
              <b-table-column field="department" label="Department" width="150" sortable centered>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="name" label="Name" width="250" sortable>
                  {{ props.row.user }}
              </b-table-column>

              <b-table-column field="position" label="Position" sortable>
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
    <section class="create-project-card--table-body">
      <b-table
          class="create-project-card--table"
          :data="tableDataExternal"
          :paginated="true"
          :per-page="5"
          :loading="isExternalLoading"
          default-sort="title">
          <template scope="props">
              <b-table-column field="department" label="Department" width="150" sortable centered>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="name" label="Name" width="250" sortable>
                  {{ props.row.user }}
              </b-table-column>

              <b-table-column field="position2" label="Position" sortable>
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
          <a class="button is-dark" @click="createCard()">Create Card</a>
      </div>
    </div>
  </div>
</template>

<script src="@/components/js/CreateProjectCard">
// import Axios from 'axios'
// import moment from 'moment'
// export default {
//   data () {
//     return {
//       tableDataInternal: [{ 'user': '', 'department': '', 'position': '', 'email': '' }],
//       tableDataExternal: [{ 'user': '', 'department': '', 'position': '', 'email': '' }],
//       cardName: '',
//       project: '',
//       description: '',
//       date: '',
//       isInternalLoading: true,
//       isExternalLoading: true,
//       // disabled input is false when come in first time, but will be true if already add participant
//       disabledInput: false,
//       allProject: [],
//       internalArrLength: '',
//       externalArrLength: '',
//       alertInfo: true,
//       alertError: false,
//       alertErrorDuplicate: false,
//       startDateOption: {
//         disabledDate (time) {
//           return time.getTime() < Date.now() - 8.64e7
//         }
//       }
//     }
//   },
//   methods: {
//     async addParticipant () {
//       // check if user fill all details except description or else the alert error will occur
//       if (this.cardName === '' || this.project === '' || this.date[0] === undefined) {
//         this.alertError = true
//         this.alertInfo = false
//       }
//       // check if the project card name is same in database
//       let checkDuplicateResponse = await Axios.get(`//210.121.158.162:8091/check/project-card-name?projectName=${this.project}&projectCardName=${this.cardName}`)
//       // false is already have this project name
//
//       if (checkDuplicateResponse.data === true) {
//         let idUser = localStorage.getItem('user_userId')
//         let response = await Axios.post(`//210.121.158.162:8091/create/project-card?idUser=${idUser}&projectName=${this.project}&name=${this.cardName}&description=${this.description}&startDate=${this.date[0]}&endDate=${this.date[1]}`)
//         let idCard = ''
//         if (response.data === true) {
//           let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/projectName?projectName=${this.project}&projectCardName=${this.cardName}`)
//           idCard = cardResponse.data.idProjectCard
//         } else {
//           // if response failed, the alert error occur
//           this.alertError = true
//           this.alertInfo = false
//         }
//         localStorage.setItem('id_create_card', idCard)
//         let idDepartmentResponse = await Axios.get(`//210.121.158.162:8091/get/idDepartment/project-card?idProjectCard=${idCard}`)
//         let idDepartment = idDepartmentResponse.data
//         // console.log(idDepartmentResponse)
//
//         localStorage.setItem('id_department_owner_card', idDepartment)
//         this.$router.replace({ path: '/add-participants' })
//       }
//       this.alertError = false
//       this.alertInfo = false
//       this.alertErrorDuplicate = true
//     },
//     async cancle () {
//       let idCard = localStorage.getItem('id_create_card')
//       let cancleResponse = await Axios.post(`//210.121.158.162:8091/delete/project-card?idProjectCard=${idCard}`)
//       if (cancleResponse.data === true) {
//         this.$router.replace({ path: '/my-project' })
//       } else {
//         alert('failed')
//       }
//     },
//     createCard () {
//       localStorage.setItem('id_create_card', 'null')
//       this.$router.replace({ path: 'my-project' })
//     }
//   },
//   async mounted () {
//     let response = await Axios.get(`//210.121.158.162:8091/get/all-project/`)
//
//     // can now get project at once by set all project array to equal to data that recieve first
//     // then set it to equal to project name
//     this.allProject = response.data
//     for (let i = 0; i < response.data.length; i++) {
//       this.allProject[i] = response.data[i].name
//     }
//
//     // get card to show participants inside the card
//     let idCard = localStorage.getItem('id_create_card')
//     console.log(idCard)
//     // if id card = null -> didn't create card and add participant yet so id card doesn't exist
//     // else -> already add participants and back to create card
//     if (idCard === null || idCard === 'null') {
//       this.disabledInput = false
//     } else {
//       this.disabledInput = true
//       this.alertInfo = false
//       // get array string of participant
//       let internalResponse = await Axios.get(`//210.121.158.162:8091/get/internal-participants?idProjectCard=${idCard}`)
//       let externalResponse = await Axios.get(`//210.121.158.162:8091/get/external-participants?idProjectCard=${idCard}`)
//
//       // then find length of the array to find User object to put in the table
//       let externalArrLength = externalResponse.data.length
//       let internalArrLength = internalResponse.data.length
//
//       // this array will keep all information of list of user
//       let externalUserArr = []
//       let internalUserArr = []
//
//       for (let i = 0; i < internalArrLength; i++) {
//         let idUser = internalResponse.data[i]
//         let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
//         internalUserArr.push(userResponse.data)
//       }
//       // set internal table attribute to link with the array
//       this.tableDataInternal = internalUserArr
//
//       for (let i = 0; i < externalArrLength; i++) {
//         let idUser = externalResponse.data[i]
//         let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
//         externalUserArr.push(userResponse.data)
//       }
//       // set external table attribute to link with the array
//       this.tableDataExternal = externalUserArr
//
//       // for loop to put exact information into the internal table
//       for (let i = 0; i < internalUserArr.length; i++) {
//         this.tableDataInternal[i].user = internalUserArr[i].name
//         this.tableDataInternal[i].email = internalUserArr[i].email
//
//         // get position by positionId
//         let idPosition = internalUserArr[i].idPosition
//         let internalPositionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)
//
//         // get department by departmentId
//         let idDepartment = internalUserArr[i].idDepartment
//         let internalDepartmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)
//
//         // set position name and department name into table
//         setTimeout(() => {
//           this.isInternalLoading = false
//         }, 2 * 1000)
//         this.tableDataInternal[i].position = internalPositionResponse.data.name
//         this.tableDataInternal[i].department = internalDepartmentResponse.data.name
//       }
//
//       // then for loop again to put exact information into the table
//       for (let i = 0; i < externalUserArr.length; i++) {
//         this.tableDataExternal[i].user = externalUserArr[i].name
//         this.tableDataExternal[i].email = externalUserArr[i].email
//         // get position by positionId
//         let idPosition = externalUserArr[i].idPosition
//         let externalPositionResponse = await Axios.get(`//210.121.158.165:8090/get/position/id?id=${idPosition}`)
//
//         // get department by departmentId
//         let idDepartment = externalUserArr[i].idDepartment
//         let externalDepartmentResponse = await Axios.get(`//210.121.158.165:8090/get/department/id?id=${idDepartment}`)
//
//         // set position name and department name into table
//         setTimeout(() => {
//           this.isExternalLoading = false
//         }, 2 * 1000)
//         this.tableDataExternal[i].position = externalPositionResponse.data.name
//         this.tableDataExternal[i].department = externalDepartmentResponse.data.name
//       }
//     }
//     // let cardResponse = await Axios.get(`http://localhost:8091/get/project-card/idProjectCard?idProjectCard=${idCard}`)
//
//     // console.log(cardResponse.data)
//     // get arr length of both internal and external user in a card to find their names
//     // this.internalArrLength = cardResponse.data.internalParticipants.length
//     // this.externalArrLength = cardResponse.data.externalParticipants.length
//     //
//     // // set value of internal participant table
//     // for (let i = 0; i < this.internalArrLength; i++) {
//     //   let idInternalUser = cardResponse.data.internalParticipants[i]
//     //   let internalNameResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idInternalUser}`)
//     //   let internalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id?id=${internalNameResponse.data.idPosition}`)
//     //   let internalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id?id=${internalNameResponse.data.idDepartment}`)
//     //   this.tableData[i].user = internalNameResponse.data.name
//     //   this.tableData[i].email = internalNameResponse.data.email
//     //   this.tableData[i].status = 'INTERNAL'
//     //   this.tableData[i].position = internalPositionResponse.data.name
//     //   this.tableData[i].department = internalDepartmentResponse.data.name
//     // }
//     // // set value of external participant table
//     // for (let i = 0; i < this.externalArrLength; i++) {
//     //   let idExternalUser = cardResponse.data.externalParticipants[i]
//     //   let externalNameResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idExternalUser}`)
//     //   let externalPositionResponse = await Axios.get(`http://localhost:8090/get/position/id?id=${externalNameResponse.data.idPosition}`)
//     //   let externalDepartmentResponse = await Axios.get(`http://localhost:8090/get/department/id?id=${externalNameResponse.data.idDepartment}`)
//     //   this.tableData2[i].user = externalNameResponse.data.name
//     //   this.tableData2[i].email = externalNameResponse.data.email
//     //   this.tableData2[i].status = 'EXTERNAL'
//     //   this.tableData2[i].position = externalPositionResponse.data.name
//     //   this.tableData2[i].department = externalDepartmentResponse.data.name
//     // }
//   },
//   // change format of date each time that click
//   watch: {
//     'date': function (val) {
//       this.date[0] = moment(val[0]).format('YYYY-MM-DD')
//       this.date[1] = moment(val[1]).format('YYYY-MM-DD')
//     }
//   }
// }
</script>

<style scoped>
.create-project-card--container {
  background-color: #fff;
  padding: 30px;
}
</style>
