<template lang="html">
  <div class="content" align="left">
    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>New Project +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
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
      userId: ''
    }
  },
  created () {
    this.userId = this.getUser.idUser
  },
  methods: {
    createProject () {
      // task api
      let self = this
      Axios.post(`http://localhost:8091/create/project/${this.projectName}/${this.projectDescription}/${this.userId}`).then(function (response) {
        self.$router.replace({ path: '/project-management' })
      }).catch(function (error) {
        console.log(error)
      })
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
