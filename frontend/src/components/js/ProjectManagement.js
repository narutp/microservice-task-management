import UpdateProject from '@/components/Dialog/UpdateProject'
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'name': 'Test Project 1', 'registeredDate': '1', 'idUser': 'Boo' },
      { 'no': 2, 'name': 'Test Project 2', 'registeredDate': '2', 'idUser': 's' }],
      arrLength: 0,
      updateProjectList: {
        dialogClicked: false,
        projectName: '',
        projectDescription: '',
        projectId: ''
      }
    }
  },
  async mounted () {
    let response = await Axios.get(`//210.121.158.162:8091/get/all-project/`)
    console.log(response.data)
    this.tableData = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = response.data[i].idUser
      let nameResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${id}`)
      console.log(nameResponse)
      this.tableData[i].idUser = nameResponse.data.name
    }
  },
  methods: {
    createProject () {
      this.$router.replace({ path: '/create-project' })
    },
    updateProject (row) {
      this.updateProjectList.dialogClicked = true
      this.updateProjectList.projectName = row.name
      this.updateProjectList.projectDescription = row.description
      this.updateProjectList.projectId = row.idProject
    }
  },
  components: {
    UpdateProject
  }
}
