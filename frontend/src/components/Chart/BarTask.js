import { Bar } from 'vue-chartjs'
// import the component - chart you need

export default Bar.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['Task 1', 'Task 2', 'Task 3', 'Task 4'],
      datasets: [
        {
          label: ['Total Task Card'],
          backgroundColor: '#00A600',
          data: [12, 20, 12, 18, 10, 6, 9, 32, 29, 19, 12, 11]
        }
      ]
    })
  }
})
