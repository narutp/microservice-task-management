import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': '', 'idProject': '', 'name': '', 'finishDate': '', 'idUser': '', 'status': '' }],
      isPaginated: true,
      isPaginationSimple: false,
      arrLength: ''
    }
  },
  async mounted () {
    let idUser = localStorage.getItem('user_userId')
    let cardResponse = await Axios.get(`//210.121.158.162:8091/get/finish-project-card?idUser=${idUser}`)
    console.log(cardResponse)
    this.tableData = cardResponse.data
    this.arrLength = cardResponse.data.length

    for (let i = 0; i < this.arrLength; i++) {
      let idProject = cardResponse.data[i].idProject
      let idUser = cardResponse.data[i].idUser
      let projectResponse = await Axios.get(`//210.121.158.162:8091/get/project?idProject=${idProject}`)
      let nameResponse = await Axios.get(`//210.121.158.165:8090/get/user/id?id=${idUser}`)
      this.tableData[i].idProject = projectResponse.data.name
      this.tableData[i].idUser = nameResponse.data.name
    }
  }
}
