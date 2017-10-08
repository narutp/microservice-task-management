<template lang="html">
  <div class="">
    <button type="button" name="button" @click="get()">A</button>
    {{ getUser }}
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
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
    })
  }
}
</script>

<style lang="css">
</style>
