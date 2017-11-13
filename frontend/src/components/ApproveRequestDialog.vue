<template lang="html">
  <div class="approve-request--container">
    <div>
      <el-dialog title = "Approve Request" :visible.sync="approveObj.dialogClicked">
        <el-row>
          <div align="left">
            Project name
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <input v-model="approveObj.projectName" class="input title-field" type="text" disabled>
          </div>
        </el-row>

        <el-row>
          <div align="left">
            Card name
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <input v-model="approveObj.cardName" class="input title-field" disabled>
          </div>
        </el-row>

        <el-row>
          <div align="left">
            Request reason
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <input v-model="this.reason" class="input title-field" disabled>
          </div>
        </el-row>

        <el-row>
          <button class="button is-dark" @click="approveRequest()">
            Accept
          </button>
          <button class="button is-dark" @click="deniedRequest()">
            Denied
          </button>
        </el-row>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      reason: ''
    }
  },
  props: ['approveObj'],
  methods: {
    approveRequest () {
      let response = Axios.post(`http://localhost:8091/approve/request/${this.approveObj.projectName}/${this.approveObj.cardName}`)
      if (response.data === true) {
        this.$router.go({ path: '/request-project', force: true })
      }
    }
  },
  async mounted () {
    let cardResponse = await Axios.get(`http://localhost:8091/get/project-card/${this.approveObj.projectName}/${this.approveObj.cardName}`)
    console.log(cardResponse.data)
    this.reason = cardResponse.data.submitReason
  }
}
</script>

<style scoped>
.update-project--value {
  margin-bottom: 20px;
}
.create-section {
  margin: 20px;
}
.no-border {
  border: 0;
}
.placeholder {
  color: #BDBDBD;
}
.title-field {
  margin-top: 8px;
  margin-bottom: 8px;
  border: 0;
  background-color: #fff;
  height: 48px;
}
textarea {
  margin-top: 8px;
  margin-bottom: 8px;
  border: 0;
  background-color: #fff;
  height: 300px;
}
</style>
