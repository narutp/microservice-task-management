import { Pie } from 'vue-chartjs'
// import the component - chart you need

export default Pie.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Project 1', 'Project 2', 'Project 3', 'Project 4'],
      datasets: [
        {
          label: 'Task card',
          backgroundColor: ['#00A600', '#FF6384', '#36A2EB', '#FFCE56'],
          data: [12, 24, 12, 18]
        }
      ]
    })
  }
})
