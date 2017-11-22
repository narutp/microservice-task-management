<template lang="html">
  <div class="content" align="left">
    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>New Project +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border is-danger" @click="back()">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <div class="create-project--alert">
          <template>
            <el-alert v-if="alertInfo" type="info" show-icon :closable="false"
              title="Please fill all information below">
            </el-alert>
            <el-alert v-if="alertError" type="error" show-icon :closable="false"
              title="Please fill all information to create project">
            </el-alert>
          </template>
        </div>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <input v-model="projectName" class="input title-field" type="text" placeholder="Project name">
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea v-model="projectDescription" class="textarea" placeholder="Description"></textarea>
      </div>
    </div>

    <div class="columns" align="center">
      <div class="column">
        <button class="button is-success register--button" @click="createProject()">
          Create Project
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
import { mapGetters } from 'vuex'
export default {
  data () {
    return {
      projectName: '',
      projectDescription: '',
      userId: '',
      alertInfo: true,
      alertError: false
    }
  },
  mounted () {
    this.userId = localStorage.getItem('user_userId')
  },
  methods: {
    async createProject () {
      // task api
      let idDepartment = localStorage.getItem('user_departmentId')
      let response = await Axios.post(`http://localhost:8091/create/project?name=${this.projectName}&description=${this.projectDescription}&idUser=${this.userId}&idDepartment=${idDepartment}`)
      if (response.data === true) {
        this.back()
      } else {
        this.alertInfo = false
        this.alertError = true
      }
    },
    back () {
      this.$router.replace({ path: '/project-management' })
    }
  },
  computed: {
    ...mapGetters({
      getUser: 'GET_USER'
    })
  }
}
</script>

<style scoped>
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
