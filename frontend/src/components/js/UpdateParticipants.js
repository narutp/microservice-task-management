import Axios from 'axios'
export default {
  data () {
    return {
      isPaginated: true,
      isPaginationSimple: false,
      temp: '',
      temp2: '',
      idCard: '',
      internalUser: '',
      internalUserList: [],
      internalAddList: [],
      externalUser: '',
      externalUserList: [],
      externalAddList: []
    }
  },
  async mounted () {
    this.idCard = localStorage.getItem('id_create_card')
    let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/idProjectCard?idProjectCard=${this.idCard}`)
    console.log(cardResponse.data)
    let internalArr = cardResponse.data.internalParticipants.length
    let externalArr = cardResponse.data.externalParticipants.length

    for (let i = 0; i < internalArr; i++) {
      let idInternalUser = cardResponse.data.internalParticipants[i]
      let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idInternalUser}`)
      this.internalAddList.push(userResponse.data.name)
    }

    for (let i = 0; i < externalArr; i++) {
      let idExternalUser = cardResponse.data.externalParticipants[i]
      let userResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idExternalUser}`)
      this.externalAddList.push(userResponse.data.name)
    }

    let idDepartment = localStorage.getItem('id_department_owner_card')
    let internalResponse = await Axios.get(`//210.121.158.165:8090/get/internal-user-list?idDepartment=${idDepartment}`)
    for (let i = 0; i < internalResponse.data.length; i++) {
      this.internalUserList[i] = internalResponse.data[i].name
    }

    let externalResponse = await Axios.get(`//210.121.158.165:8090/get/external-user-list?idDepartment=${idDepartment}`)
    for (let j = 0; j < externalResponse.data.length; j++) {
      this.externalUserList[j] = externalResponse.data[j].name
    }
  },
  methods: {
    addInternalParticipant () {
      this.internalAddList.push(this.internalUser)
    },
    addExternalParticipant () {
      this.externalAddList.push(this.externalUser)
    },
    async cancleUpdateParticipants () {
      let cancleResponse = await Axios.post(`//210.121.158.162:8091/delete/project-card/idProjectCard?idProjectCard=${this.idCard}`)
      if (cancleResponse.data === true) {
        this.$router.replace({ path: '/create-card' })
      } else {
        alert('failed')
      }
    },
    async save () {
      let idInternalUserListResponse = await Axios.get(`//210.121.158.165:8090/get/idUserList?nameList=${this.internalAddList}`)
      let idExternalUserListResponse = await Axios.get(`//210.121.158.165:8090/get/idUserList?nameList=${this.externalAddList}`)

      let response = await Axios.post(`//210.121.158.162:8091/add/participants?idProjectCard=${this.idCard}&idInternalUserList=${idInternalUserListResponse.data}&idExternalUserList=${idExternalUserListResponse.data}`)
      if (response.data === true) {
        this.$router.replace({ path: '/update-card' })
      } else {
        alert('failed')
      }
    }
  }
}
