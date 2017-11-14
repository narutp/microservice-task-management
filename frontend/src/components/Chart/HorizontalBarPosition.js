import {HorizontalBar} from 'vue-chartjs'
import Axios from 'axios'

export default {
  extends: HorizontalBar,
  data () {
    return {
      datacollection: {
        labels: ['Department A', 'Department B', 'Department C'],
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
    let departmentArray = ['A', 'B', 'C', 'D']
    let response = await Axios.get(`http://localhost:8090/get/all-department`)
    this.datacollection.labels = response.data
    this.arrLength = response.data.length
    for (let i = 0; i < this.arrLength; i++) {
      let id = response.data[i].idDepartment
      let numUserResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${id}`)
      this.datacollection.labels[i] = departmentArray[i]
      this.datacollection.datasets[0].data[i] = numUserResponse.data.length
      // console.log(this.datacollection.datasets[0].data[i])
    }
    this.renderChart(this.datacollection, {responsive: true})
  }
}
