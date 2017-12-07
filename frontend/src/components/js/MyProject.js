import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'idProject': 'Microservice', 'name': 'Login', 'registeredDate': '2017-08-23', 'idUser': 'Net', 'status': 'In progress' }],
      isPaginationSimple: false,
      arrLength: '',
      arrProject: []
    }
  },
  methods: {
    createCard () {
      this.$router.replace({ path: '/create-card' })
    },
    updateCard (card) {
      localStorage.setItem('card_update', card.idProjectCard)
      this.$router.replace({ path: '/update-card' })
    }
  },
  async mounted () {
    let id = localStorage.getItem('user_userId')
    let cardResponse = await Axios.get(`//210.121.158.162:8091/get/all-project-card/idUser?idUser=${id}`)
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
    // console.log(cardResponse.data)
    // for (let i = 0; i < this.arrLength; i++) {
    //   let id = cardResponse.data[i].idUser
    //   let nameResponse = await Axios.get(`http://localhost:8090/get/user/id/${id}`)
    //   this.tableData[i].idUser = nameResponse.data.name
    // }
  }
}
