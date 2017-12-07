import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': '', 'idProject': '', 'name': '', 'startDate': '', 'endDate': '', 'idUser': '', 'status': '' }],
      department: '',
      arrLength: ''
    }
  },
  async mounted () {
    this.department = localStorage.getItem('user_department')
    // get user list by sending department name of that user
    let userListResponse = await Axios.get(`//210.121.158.165:8090/get/idUser?departmentName=${this.department}`)
    console.log('user iist : ' + userListResponse)
    let departmentCardResponse = await Axios.get(`//210.121.158.162:8091/get/department-project-card?idUserList=${userListResponse.data}`)
    console.log(departmentCardResponse)
    this.tableData = departmentCardResponse.data
    this.arrLength = departmentCardResponse.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let idUser = departmentCardResponse.data[i].idUser
      let idProject = departmentCardResponse.data[i].idProject
      let nameResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
      let projectResponse = await Axios.get(`//210.121.158.162:8091/get/project?idProject=${idProject}`)
      this.tableData[i].idUser = nameResponse.data.name
      this.tableData[i].idProject = projectResponse.data.name
    }
  }
}
