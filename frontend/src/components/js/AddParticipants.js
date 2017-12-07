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
