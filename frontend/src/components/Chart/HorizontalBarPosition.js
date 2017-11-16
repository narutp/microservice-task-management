import {HorizontalBar} from 'vue-chartjs'

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
  mounted () {
    this.renderChart(this.datacollection, {responsive: true})
  }
}

// export default {
//   extends: HorizontalBar,
//   props: ['data', 'options'],
//   mounted () {
//     this.renderChart(this.data, this.options)
//   }
// }
