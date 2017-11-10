import { Bar } from 'vue-chartjs'
// import the component - chart you need

export default Bar.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Department A', 'Department B', 'Department C', 'Department D'],
      datasets: [
        {
          label: ['Participants'],
          backgroundColor: '#585858',
          data: [12, 20, 12, 18]
        }
      ]
    })
  }
})
