import { Doughnut } from 'vue-chartjs'
// import the component - chart you need

export default Doughnut.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Project from Department A', 'Project from Department B', 'Project from C', 'Project from D'],
      datasets: [
        {
          label: 'Department',
          backgroundColor: ['#00A600', '#FF6384', '#36A2EB', '#FFCE56'],
          data: [12, 24, 12, 18]
        }
      ]
    })
  }
})
