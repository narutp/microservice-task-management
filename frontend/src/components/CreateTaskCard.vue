<template lang="html">
  <div class="create-task-card--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>New Card +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border">
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
        <button class="button" style="width: 200px" @click="addInternal()">
          <span> Add Internal + </span>
        </button>
        <button class="button" style="width: 200px" @click="addExternal()">
          <span> Add External + </span>
        </button>
      </div>
    </div>

    <section class="my-task-table--body">
      <b-table
          class="my-task--table"
          :data="tableData"
          default-sort="title">
          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="taskName" label="Name" sortable>
                  {{ props.row.taskName }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Department" sortable>
                  {{ props.row.taskCardName }}
              </b-table-column>

              <b-table-column field="writer" label="Position" sortable>
                  {{ props.row.taskCardName }}
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="status" label="Status" sortable centered>
                  <span class="tag is-info" v-if="props.row.status === 'INTERNAL'">
                      {{ props.row.status }}
                  </span>
                  <span class="tag is-warning" v-if="props.row.status === 'EXTERNAL'">
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
    return {
      tableData: [{ 'no': 1, 'taskName': 'Makhamwan', 'taskCardName': 'Department A', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'INTERNAL' },
      { 'no': 2, 'taskName': 'Net', 'taskCardName': 'Department A', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'INTERNAL' },
      { 'no': 3, 'taskName': 'Boss', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' },
      { 'no': 4, 'taskName': 'Prang', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' }],
      cardName: '',
      project: '',
      description: '',
      startDate: '',
      endDate: '',
      allProject: []
    }
  },
  methods: {
    async addInternal () {
      let idUser = localStorage.getItem('user_userId')
      let sDate = moment(this.startDate).format('YYYY-MM-DD')
      let eDate = moment(this.endDate).format('YYYY-MM-DD')
      console.log(idUser)
      let response = await Axios.get(`http://localhost:8091/create/card/${idUser}/${this.project}/${this.cardName}/${this.description}/${sDate}/${eDate}`)
      let idCard = response.data
      // console.log(idCard)
      let idDepartmentResponse = await Axios.get(`http://localhost:8091/get/idDepartment/card/${idCard}`)
      let idDepartment = idDepartmentResponse.data
      // console.log(idDepartmentResponse)
      localStorage.setItem('id_department_owner_card', idDepartment)
      this.$router.replace({ path: '/add-participants' })
    },
    addExternal () {
    }
  },
  // beforeCreate () {
  //   let self = this
  //   Axios.get(`http://localhost:8091/get/all-project/`).then(function (response) {
  //     console.log(response.data.name)
  //     self.allProject = response.data
  //     console.log(self.allProject)
  //   }).catch(function (error) {
  //     self.back()
  //     console.log(error)
  //   })
  // }
  async beforeCreate () {
    let response = await Axios.get(`http://localhost:8091/get/all-project/`)
    for (let i = 0; i < response.data.length; i++) {
      this.allProject[i] = response.data[i].name
    }
  }
}
</script>

<style scoped>
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
