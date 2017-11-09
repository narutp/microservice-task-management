<template lang="html">
  <div class="create-task-card--container" align="left">

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
        <input v-model="cardName" class="input title-field" type="text" placeholder="Card name">
      </div>
      <div class="column">
        <b-select v-model="project" align="right" placeholder="Project">
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
        <textarea v-model="description" class="textarea" placeholder="Description"></textarea>
      </div>
    </div>

    <div class="columns">
      <div class="column is-one-quarters">
        <el-date-picker
          v-model="startDate"
          type="date"
          range-separator=" to "
          placeholder="Start date">
        </el-date-picker>
      </div>
      <div class="column is-one-quarters">
        <el-date-picker
          v-model="endDate"
          type="date"
          range-separator=" to "
          placeholder="End date">
        </el-date-picker>
      </div>
      <div class="column" align="right">
        <button class="button" style="width: 200px" @click="addParticipant()">
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

              <b-table-column field="email" label="Registered Date" sortable>
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

              <b-table-column field="email" label="Registered Date" sortable>
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
          <a class="button is-dark">Create Card</a>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
import moment from 'moment'
export default {
  data () {
    // TODO Bug when input data into the table, the table must already have temp data
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
      cardName: '',
      project: '',
      description: '',
      startDate: '',
      endDate: '',
      allProject: [],
      internalArrLength: '',
      externalArrLength: ''
    }
  },
  methods: {
    async addParticipant () {
      let idUser = localStorage.getItem('user_userId')
      let sDate = moment(this.startDate).format('YYYY-MM-DD')
      let eDate = moment(this.endDate).format('YYYY-MM-DD')
      let response = await Axios.get(`http://localhost:8091/create/card/${idUser}/${this.project}/${this.cardName}/${this.description}/${sDate}/${eDate}`)
      let idCard = response.data
      localStorage.setItem('id_create_card', idCard)
      console.log(idCard)
      let idDepartmentResponse = await Axios.get(`http://localhost:8091/get/idDepartment/card/${idCard}`)
      let idDepartment = idDepartmentResponse.data
      // console.log(idDepartmentResponse)

      localStorage.setItem('id_department_owner_card', idDepartment)
      this.$router.replace({ path: '/add-participants' })
    },
    async cancle () {
      let idCard = localStorage.getItem('id_create_card')
      let cancleResponse = await Axios.post(`http://localhost:8091/delete/card/${idCard}`)
      if (cancleResponse.data === true) {
        this.$router.replace({ path: '/my-project' })
      } else {
        alert('failed')
      }
    }
  },
  // TODO can't get all project at once
  async mounted () {
    let response = await Axios.get(`http://localhost:8091/get/all-project/`)
    for (let i = 0; i < response.data.length; i++) {
      this.allProject[i] = response.data[i].name
    }

    // get card to show participants inside the card
    let idCard = localStorage.getItem('id_create_card')
    let cardResponse = await Axios.get(`http://localhost:8091/get/card/${idCard}`)
    console.log(cardResponse.data)

    // get arr length of both internal and external user in a card to find their names
    this.internalArrLength = cardResponse.data.internalParticipants.length
    this.externalArrLength = cardResponse.data.externalParticipants.length

    // set value of internal participant table
    for (let i = 0; i < this.internalArrLength; i++) {
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
    for (let i = 0; i < this.externalArrLength; i++) {
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
  }
}
</script>

<style scoped>
.create-task-card--container {
  background-color: #fff;
  padding: 30px;
}
</style>
