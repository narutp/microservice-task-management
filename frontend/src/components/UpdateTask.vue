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
        <span><b>{{projectName}}</b></span><hr>
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

        <div class="columns">
          <div class="column">
            <input v-model="projectName" class="input title-field" type="text">
          </div>
        </div>

        <div class="columns">
          <div class="column">
            <textarea v-model="projectDescription" class="textarea"></textarea>
          </div>
        </div>

        <div class="columns" align="center">
          <div class="column">
              <a class="button is-dark">Update task</a>
          </div>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// import Axios from 'axios'
export default {
  data () {
    return {
    }
  },
  props: ['projectName', 'dialogClicked', 'projectDescription']
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
