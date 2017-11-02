import { HorizontalBar } from 'vue-chartjs'
// import the component - chart you need

export default HorizontalBar.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Department A', 'Department B', 'Department C', 'Department D'],
      datasets: [
        {
          label: ['Internal Participants'],
          backgroundColor: '#FF6384',
          data: [12, 20, 12, 18]
        },
        {
          label: ['External Participants'],
          backgroundColor: '#36A2EB',
          data: [4, 3, 1, 2]
        }
      ]
    })
  }
})
