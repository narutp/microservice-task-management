<template lang="html">
  <!-- <div class="content" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Update Project</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border">
          <i class="fa fa-trash" aria-hidden="true"></i>
        </button>
        <button class="button no-border">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <input class="input title-field" type="text" placeholder="Microservice">
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea class="textarea" placeholder="Microservice project"></textarea>
      </div>
    </div>

    <div class="columns" align="center">
      <div class="column">
          <a class="button is-dark">Update task</a>
      </div>
    </div>
  </div> -->
  <div>
    <div>
      <el-dialog title = "Update Project" :visible.sync="dialogClicked">
        <div class="columns">
          <div class="column" align="right">
            <button class="button no-border">
              <i class="fa fa-trash" aria-hidden="true"></i>
            </button>
            <button class="button no-border">
              <i class="fa fa-close" aria-hidden="true"></i>
            </button>
          </div>
        </div>

        <el-row>
          <div align="left">
            Project Name
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <input v-model="projectName" class="input title-field" type="text">
          </div>
        </el-row>

        <el-row>
          <div align="left">
            Project Description
          </div>
        </el-row>

        <el-row>
          <div class="update-project--value">
            <textarea v-model="projectDescription" class="textarea"></textarea>
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
  props: ['projectName', 'dialogClicked', 'projectDescription', 'projectId'],
  methods: {
    updateProject () {
      let self = this
      Axios.post(`http://localhost:8091/edit/project/${this.projectId}/${this.projectName}/${this.projectDescription}`).then(function (response) {
        self.$router.go({
          path: '/document',
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
