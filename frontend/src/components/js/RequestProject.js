import ApproveRequestDialog from '@/components/Dialog/ApproveRequestDialog'
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'idProject': '', 'idProjectCard': '', 'date': 'a', 'type': 'a', 'idRequester': '' }],
      idUser: '',
      isPaginationSimple: false,
      isPaginated: true,
      approveObj: {
        projectName: '',
        cardName: '',
        dialogClicked: false,
        reason: ''
      }
    }
  },
  async mounted () {
    this.idUser = localStorage.getItem('user_userId')
    let requestResponse = await Axios.get(`//210.121.158.162:8091/get/all-termination-request/idUser?idUser=${this.idUser}`)
    console.log(requestResponse)
    this.tableData = requestResponse.data
    let requestArr = requestResponse.data.length

    for (let i = 0; i < requestArr; i++) {
      let idProject = requestResponse.data[i].idProject
      let idProjectCard = requestResponse.data[i].idProjectCard
      let idRequester = requestResponse.data[i].idRequester
      let projectResponse = await Axios.get(`//210.121.158.162:8091/get/project?idProject=${idProject}`)
      let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/idProjectCard?idProjectCard=${idProjectCard}`)
      let requesterResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idRequester}`)
      this.tableData[i].idProject = projectResponse.data.name
      this.tableData[i].idProjectCard = cardResponse.data.name
      this.tableData[i].idRequester = requesterResponse.data.name
    }

    // console.log(projectResponse)

    // let arrProject = projectResponse.data.length
  },
  methods: {
    createCard () {
      this.$router.replace({ path: '/create-card' })
    },
    async approveRequest (row) {
      this.approveObj.dialogClicked = true
      this.approveObj.projectName = row.idProject
      this.approveObj.cardName = row.idProjectCard
      let cardResponse = await Axios.get(`//210.121.158.162:8091/get/project-card/projectName?projectName=${this.approveObj.projectName}&projectCardName=${this.approveObj.cardName}`)
      this.approveObj.reason = cardResponse.data.submitReason
    }
  },
  components: {
    ApproveRequestDialog
  }
}
