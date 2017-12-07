<template lang="html">
  <div class="update-project--container">
    <div>
      <el-dialog title = "Update Project" :visible.sync="updateProjectList.dialogClicked">
        <el-row>
          <div align="left">
            Project Name
          </div>
        </el-row>

        <el-row>
          <div class="update-project--input">
            <input v-model="updateProjectList.projectName" class="input title-field" type="text">
          </div>
        </el-row>

        <el-row>
          <div align="left">
            Project Description
          </div>
        </el-row>

        <el-row>
          <div class="update-project--input">
            <textarea v-model="updateProjectList.projectDescription" class="textarea"></textarea>
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
    }
  },
  props: ['updateProjectList'],
  methods: {
    updateProject () {
      let self = this
      Axios.post(`//210.121.158.162:8091/edit/project?idProject=${this.updateProjectList.projectId}&name=${this.updateProjectList.projectName}&description=${this.updateProjectList.projectDescription}`).then(function (response) {
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
.update-project--input {
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
