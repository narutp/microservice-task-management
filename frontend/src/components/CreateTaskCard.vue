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
      <div class="column" align="right">
        <b-dropdown v-model="project" style="margin-top: 8px" align="left">
            <button class="button" slot="trigger" style="width: 200px">
              <span> Select Project </span>
              <b-icon icon="arrow_drop_down"></b-icon>
            </button>
            <b-dropdown-item>Microservice</b-dropdown-item>
            <b-dropdown-item>Big Data</b-dropdown-item>
            <b-dropdown-item>Net Experiment</b-dropdown-item>
            <b-dropdown-item>Machine Learning</b-dropdown-item>
        </b-dropdown>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea v-model="description" class="textarea" placeholder="Description"></textarea>
      </div>
    </div>

    <div class="columns">
      <div class="column is-three-quarters">
        <el-date-picker
          v-model="dateRange"
          type="daterange"
          range-separator=" to "
          placeholder="Start date - End date">
        </el-date-picker>
      </div>
      <div class="column" align="right">
        <button class="button" style="width: 200px" @click="addParticipant()">
          <span> Add Participants + </span>
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
      dateRange: '',
      allProject: []
    }
  },
  methods: {
    addParticipant () {
      this.$router.replace({ path: '/add-participants' })
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
    console.log(this.allProject)
  }
}
</script>

<style scoped>
.create-section {
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
}
</style>
