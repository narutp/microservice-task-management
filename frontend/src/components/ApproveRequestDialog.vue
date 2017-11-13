<template lang="html">
  <div class="approve-request--container">
    <div>
      <el-dialog title = "Approve Request" :visible.sync="approveObj.dialogClicked">
        <el-row>
          <div align="left">
            Project Name
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <input v-model="approveObj.projectName" class="input title-field" type="text">
          </div>
        </el-row>

        <el-row>
          <div align="left">
            Project Description
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <textarea v-model="approveObj.cardName" class="textarea"></textarea>
          </div>
        </el-row>

        <el-row>
          <button class="button is-dark" @click="updateProject()">
            Update Project
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
      isCloseDialog: false
    }
  },
  props: ['approveObj'],
  methods: {
    updateProject () {
      let self = this
      Axios.post(`http://localhost:8091/edit/project/${this.projectId}/${this.projectName}/${this.projectDescription}`).then(function (response) {
        self.$router.go({
          path: '/project-management',
          force: true
        })
      }).catch(function (error) {
        console.log(error)
      })
    }
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
