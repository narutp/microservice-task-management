<template lang="html">
  <div class="logout-dialog--container">
    <el-dialog title="Log out" :visible.sync="logoutList.dialogLogoutClicked" width="50%" center>
      <el-row>
        <div class="request-delete--button-body">
          <button class="button is-danger" @click="logout()">
            Yes
          </button>
          <button class="button is-dark" @click="cancle()">
            No
          </button>
        </div>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import Auth from '@/components/Auth'
export default {
  props: ['logoutList'],
  data () {
    return {
    }
  },
  methods: {
    logout () {
      localStorage.clear()
      Auth.authenticate.logout()
      this.$router.replace({ path: '/' })
      this.logoutList.dialogLogoutClicked = false
    },
    cancle () {
      this.logoutList.dialogLogoutClicked = false
    }
  }
}
</script>

<style scoped>
.logout-dialog--container {
  padding: 15px;
}
</style>
