<template lang="html">
  <div class="request-delete--container">
    <el-dialog title = "Request to delete" :visible.sync="requestDeleteList.dialogRequestDeleteClicked">
      <el-row>
        <div align="left">
          <b>Reason</b>
        </div>
      </el-row>

      <el-row>
        <div class="request-delete--value">
          <textarea v-model="reason" class="textarea"></textarea>
        </div>
      </el-row>

      <el-row>
        <div class="request-delete--button-body" align="right">
          <button class="button is-dark" @click="requestDelete()">
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
  props: ['requestDeleteList'],
  data () {
    return {
      reason: ''
    }
  },
  methods: {
    async requestDelete () {
      let requestResponse = await Axios.post(`http://localhost:8091/request/delete/${this.requestDeleteList.idCard}/${this.reason}/${this.requestDeleteList.idUser}`)
      if (requestResponse.data === true) {
        this.$router.replace({ path: 'my-project' })
      } else {
        alert('failed to request delete!')
      }
    }
  }
}
</script>

<style scoped>
.request-delete--button-body {
  padding: 15px;
}
</style>
