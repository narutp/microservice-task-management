<template lang="html">
  <div class="add-participants--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Add participants +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border" @click="cancleAddParticipants()">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <!--  Internal Participants -->
    <div class="columns">
      <div class="column">
        <section class="add-participants--body">
          <b-select v-model="internalUser" placeholder="Select a name">
            <option
              v-for="option in internalUserList"
              :value="option"
              :key="option">
              {{ option }}
            </option>
          </b-select>
        </section>
      </div>
      <div class="column">
        <button class="button is-info" @click="addInternalParticipant()">
          <span>Add Internal Participants</span>
        </button>
      </div>
    </div>

    <!--  external participants  -->
    <div class="columns">
      <div class="column">
        <section class="add-participants--table-body">
          <b-select v-model="externalUser" placeholder="Select external">
            <option
              v-for="options in externalUserList"
              :value="options"
              :key="options">
              {{ options }}
            </option>
          </b-select>
        </section>
      </div>
      <div class="column">
        <button class="button is-info" @click="addExternalParticipant()">
          <span>Add External Participants</span>
        </button>
      </div>
    </div>

    <!--  Can't remove tag ?? -->
    <div class="columns">
      <div class="column">
        <section class="add-participants--table-body">
          <b-table
              class="add-participants--table"
              :data="internalTableData"
              :paginated="true"
              :per-page="5"
              default-sort="title">
              <template scope="props">
                <b-table-column field="list" width="350" label="Internal list" sortable>
                  <span class="tag is-success">
                      {{ props.row }}
                  </span>
                </b-table-column>
                <b-table-column field="option" label="Option" centered>
                  <a class="button is-small is-danger button" @click="deleteInternalParticipant(props.row)">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                  </a>
                </b-table-column>
              </template>
          </b-table>
        </section>
      </div>
      <div class="column">
        <section class="add-participants--table-body">
          <b-table
              class="add-participants--table"
              :data="externalTableData"
              :paginated="true"
              :per-page="5"
              default-sort="title">
              <template scope="props">
                <b-table-column field="list" width="350" label="External list" sortable>
                  <span class="tag is-warning">
                      {{ props.row }}
                  </span>
                </b-table-column>
                <b-table-column field="option" label="Option" centered>
                  <a class="button is-small is-danger button" @click="deleteExternalParticipant(props.row)">
                    <i class="fa fa-trash" aria-hidden="true"></i>
                  </a>
                </b-table-column>
              </template>
          </b-table>
        </section>
      </div>
    </div>
    <hr>
    <button class="button is-success" @click="save()">
      <span>Save</span>
    </button>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      internalTableData: [],
      externalTableData: [],
      isPaginated: true,
      isPaginationSimple: false,
      internalUser: '',
      internalUserList: [],
      externalUser: '',
      externalUserList: []
    }
  },
  async mounted () {
    let idDepartment = localStorage.getItem('id_department_owner_card')
    let internalResponse = await Axios.get(`//210.121.158.165:8090/get/internal-user-list?idDepartment=${idDepartment}`)
    // can now get list at once by set all list array to equal to data that recieve first
    // then set it (replace) it to equal to list name
    this.internalUserList = internalResponse.data
    for (let i = 0; i < internalResponse.data.length; i++) {
      this.internalUserList[i] = internalResponse.data[i].name
    }

    let externalResponse = await Axios.get(`//210.121.158.165:8090/get/external-user-list?idDepartment=${idDepartment}`)
    this.externalUserList = externalResponse.data
    for (let j = 0; j < externalResponse.data.length; j++) {
      this.externalUserList[j] = externalResponse.data[j].name
    }
  },
  methods: {
    addInternalParticipant () {
      this.internalTableData.push(this.internalUser)
    },
    addExternalParticipant () {
      this.externalTableData.push(this.externalUser)
    },
    deleteInternalParticipant (participant) {
      let index = this.internalTableData.indexOf(participant)
      // Prevent null or value below 0
      if (index > -1) {
        this.internalTableData.splice(index, 1)
      }
    },
    deleteExternalParticipant (participant) {
      let index = this.externalTableData.indexOf(participant)
      // Prevent null or value below 0
      if (index > -1) {
        this.externalTableData.splice(index, 1)
      }
    },
    async cancleAddParticipants () {
      let idCard = localStorage.getItem('id_create_card')
      let cancleResponse = await Axios.post(`//210.121.158.162:8091/delete/project-card/idProjectCard?idProjectCard=${idCard}`)
      if (cancleResponse.data === true) {
        this.$router.replace({ path: '/create-card' })
      } else {
        alert('failed')
      }
    },
    async save () {
      let idCard = localStorage.getItem('id_create_card')
      // For add only each internal or external by sending space instead of undefine empty obj to server
      if (this.internalTableData.length === 0) {
        this.internalTableData.push('null')
      }
      if (this.externalTableData.length === 0) {
        this.externalTableData.push('null')
      }

      let self = this
      let idInternalUserListResponse = await Axios.get(`//210.121.158.165:8090/get/idUserList?nameList=${this.internalTableData}`)
      let idExternalUserListResponse = await Axios.get(`//210.121.158.165:8090/get/idUserList?nameList=${this.externalTableData}`)
      let response = await Axios.post(`//210.121.158.162:8091/add/participants?idProjectCard=${idCard}&idInternalUserList=${idInternalUserListResponse.data}&idExternalUserList=${idExternalUserListResponse.data}`)
      if (response.data === true) {
        let historyResponse = await Axios.post(`//210.121.158.165:8090/add/history?idProjectCard=${idCard}&idInternalList=${idInternalUserListResponse.data}&idExternalList=${idExternalUserListResponse.data}`)
        if (historyResponse.data === true) {
          self.$router.replace({ path: '/create-card' })
        } else {
          alert('failed to add history')
        }
      } else {
        alert('failed to add participants')
      }
    },
    async addHistory () {

    }
  }
}
</script>

<style scoped>
.add-participants--container {
  background-color: #fff;
  padding: 30px;
}
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
.limit-height {
  height: 300px;
  overflow-y: scroll;
}

</style>
