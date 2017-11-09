<template lang="html">
  <div class="request-finish--container">
    <el-dialog title = "Request to finish" :visible.sync="dialogRequestClicked">
      <el-row>
        <div align="left">
          <b>Reason</b>
        </div>
      </el-row>

      <el-row>
        <div class="update-project--value">
          <textarea v-model="reason" class="textarea"></textarea>
        </div>
      </el-row>

      <el-row>
        <div class="request-finish--button-body" align="right">
          <button class="button is-dark" @click="requestFinish()">
            Submit
          </button>
        </div>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  props: ['idCard', 'idUser', 'dialogRequestClicked'],
  data () {
    return {
      reason: ''
    }
  },
  methods: {
    // TODO Bug when close can't reopen the dialog
    async requestFinish () {
      let requestResponse = await Axios.post(`http://localhost:8091/request/finish/${this.idCard}/${this.reason}/${this.idUser}`)
      if (requestResponse.data === true) {
        this.$router.replace({ path: 'my-project' })
      } else {
        alert('failed to request finish!')
      }
    }
  }
}
</script>

<style scoped>
.request-finish--button-body {
  padding: 15px;
}
</style>
