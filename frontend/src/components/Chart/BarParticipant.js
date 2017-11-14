import {Bar} from 'vue-chartjs'

export default {
  extends: Bar,
  data () {
    return {
      datacollection: {
        labels: ['Department A', 'Department B', 'Department C', 'Department D'],
        datasets: [
          {
            label: ['Participants'],
            backgroundColor: '#585858',
            data: [12, 20, 12, 18]
          }
        ]
      }
    }
  },
  mounted () {
    this.renderChart(this.datacollection, {responsive: true, maintainAspectRatio: false})
  }
}
