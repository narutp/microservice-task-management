import { HorizontalBar } from 'vue-chartjs'
// import the component - chart you need

export default HorizontalBar.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Professor', 'Project Manager', 'Student', 'Intern'],
      datasets: [
        {
          label: ['Participants'],
          backgroundColor: '#FF6384',
          data: [12, 20, 12, 18]
        }
      ]
    })
  }
})
