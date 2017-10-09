import { Bar } from 'vue-chartjs'
// import the component - chart you need

export default Bar.extend({
  mounted () {
    // Overwriting base render method with actual data.
    this.renderChart({
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
      datasets: [
        {
          label: ['Internal Participants'],
          backgroundColor: '#FF6384',
          data: [12, 20, 12, 18, 10, 6, 9, 32, 29, 19, 12, 11]
        },
        {
          label: ['External Participants'],
          backgroundColor: '#36A2EB',
          data: [10, 11, 8, 11, 20, 7, 2, 22, 11, 23, 14, 10]
        }
      ]
    })
  }
})
