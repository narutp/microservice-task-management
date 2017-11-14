<template lang="html">
  <div class="content" align="center">
    <div class="modal-mask" @click="dialogVisible = true">
      <bar-participant> </bar-participant>
    </div>
    <el-dialog
      title="Position from Each department"
      :visible.sync="dialogVisible"
      width="100%"
      :before-close="handleClose">
      <div class="in-modal-mask">
        <div class="small-chart">
          <span> Department A </span>
          <horizontal-bar-position> </horizontal-bar-position>
        </div>
        <div class="small-chart">
          <span> Department B </span>
          <horizontal-bar-position> </horizontal-bar-position>
        </div>
        <br>
        <br>
        <div class="small-chart">
          <span> Department C </span>
          <horizontal-bar-position> </horizontal-bar-position>
        </div>
        <div class="small-chart">
          <span> Department D </span>
          <horizontal-bar-position> </horizontal-bar-position>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
import BarParticipant from '@/components/Chart/BarParticipant'
import HorizontalBarPosition from '@/components/Chart/HorizontalBarPosition'

export default {
  data () {
    return {
      dialogVisible: false
    }
  },
  methods: {
  },
  components: {
    BarParticipant,
    HorizontalBarPosition
  }
}
</script>

<style scoped>
  .small-chart {
    display: inline-block;
    width: 250px;
  }
  .modal--container {
    text-align: center;
    width: 600px;
  }
</style>
