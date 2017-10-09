<template lang="html">
  <div class="">
    <button type="button" name="button" @click="get()">A</button>
    {{ getUser }}
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import Axios from 'axios'
export default {
  async mounted () {
    await this.setUser()
  },
  computed: {
    ...mapGetters({
      getUser: 'GET_USER'
    })
  },
  methods: {
    ...mapActions({
      setUser: 'SET_USER'
    }),
    get () {
      Axios.get(`http://localhost:8090/users/`).then(function (response) {
        console.log(response.data)
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style lang="css">
</style>
