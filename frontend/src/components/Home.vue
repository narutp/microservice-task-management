<template lang="html">
  <div class="">
    <button type="button" name="button" @click="get()">A</button>
    <new-chart></new-chart>
    {{ getUser }}
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'
import NewChart from '@/components/NewChart'
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
  },
  components: {
    NewChart
  }
}
</script>

<style lang="css">
</style>
