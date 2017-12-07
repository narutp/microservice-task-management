import ModalTask from '@/components/Dialog/DashboardProjectDialog'
import ModalPerson from '@/components/Dialog/DashboardUserDialog'
import Axios from 'axios'

export default {
  data () {
    return {
      arrLength: 0,
      dialogVisible: false,
      tableUserData: [{ 'name': '', 'numUser': '' }],
      tableProjectData: [{ 'name': 'DapartmentName', 'numProject': '', 'numProjectCard': '' }],
      isLoading: true
    }
  },
  async mounted () {
    let response = await Axios.get(`//210.121.158.165:8090/get/all-department`)

    this.tableUserData = response.data
    this.tableProjectData = response.data
    let arrLength = response.data.length

    for (let i = 0; i < arrLength; i++) {
      let departmentName = response.data[i].name
      let userListResponse = await Axios.get(`//210.121.158.165:8090/get/idUser?departmentName=${departmentName}`)
      let countUser = userListResponse.data.length
      setTimeout(() => {
        this.isLoading = false
      }, 2 * 1000)
      this.tableUserData[i].numUser = countUser
      let projectResponse = await Axios.get(`//210.121.158.162:8091/get/department-project?idUserList=${userListResponse.data}`)
      // console.log(projectResponse)
      this.tableProjectData[i].numProject = projectResponse.data.length

      let cardResponse = await Axios.get(`//210.121.158.162:8091/get/department-project-card?idUserList=${userListResponse.data}`)
      // console.log(cardResponse.data.length)
      this.tableProjectData[i].numProjectCard = cardResponse.data.length
    }
  },
  computed: {
    // ...mapGetters({
    //   getUser: 'GET_USER'
    // })
  },
  methods: {
    // ...mapActions({
    //   setUser: 'SET_USER'
    // })
  },
  components: {
    ModalTask,
    ModalPerson
  }
}
