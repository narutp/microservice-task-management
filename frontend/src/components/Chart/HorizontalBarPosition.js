import {HorizontalBar} from 'vue-chartjs'
import Axios from 'axios'

export default {
  extends: HorizontalBar,
  data () {
    return {
      datacollection: {
        labels: ['Professor', 'Project Manager', 'Student', 'Intern'],
        datasets: [
          {
            label: ['Participants'],
            backgroundColor: '#FFCE56',
            data: [0, 0, 0, 0]
          }
        ]
      }
    }
  },
  async mounted () {
    let positionArray = ['Professor', 'Project Manager', 'Student', 'Intern']
    let response = await Axios.get(`http://localhost:8090/get/all-position`)
    this.datacollection.labels = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = response.data[i].idDepartment
      // each position in each department
      let numUserResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
      this.datacollection.labels[i] = positionArray[i]
      this.datacollection.datasets[0].data[i] = numUserResponse.data.length
      // console.log(this.datacollection.datasets[0].data[i])
    }
    this.renderChart(this.datacollection, {responsive: true})
  }
}
