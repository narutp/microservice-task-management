<template lang="html">
  <div class="home--container">
    <el-row class="home--chart">
      <br>
      <el-col :span="11">
        <h1><b> Organization </b></h1><br>
        <b-table
            class="home--table"
            :data="tableUserData"
            default-sort="title">
            <template scope="props">
              <b-table-column field="Department" label="Department" width="50" sortable>
                  {{ props.row.department }}
              </b-table-column>
              <b-table-column field="Project" label="Total Internal" width="50" sortable>
                  {{ props.row.internal }}
              </b-table-column>
            </template>
        </b-table>
        <horizontal-bar-participant> </horizontal-bar-participant>
        <br>
      </el-col>
      <el-col :span="2">
        <br>
      </el-col>
      <el-col :span="11">
        <h1><b>Task management</b></h1><br>
        <b-table
            class="home--table"
            :data="tableProjectData"
            default-sort="title">
            <template scope="props">
              <b-table-column field="Department" label="Department" width="50" sortable>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="Project" label="Total Project" sortable>
                  {{ props.row.project }}
              </b-table-column>

              <b-table-column field="TaskCard" label="Total Task Card" sortable>
                  {{ props.row.taskCard }}
              </b-table-column>
            </template>
        </b-table>
        <div @click="dialogVisible = true">
          <doughnut-department></doughnut-department>
        </div>
      </el-col>
    </el-row>
    <el-dialog
      title="Task Card from Each department"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <div class="modal-mask">
        <modal-task> </modal-task>
      </div>
      <span slot="footer" class="dialog-footer">
      </span>
    </el-dialog>
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
import ModalTask from '@/components/ModalTask'
import PieProject from '@/components/Chart/PieProject'
import DoughnutDepartment from '@/components/Chart/DoughnutDepartment'
import HorizontalBarParticipant from '@/components/Chart/HorizontalBarParticipant'
export default {
  data () {
    return {
      dialogVisible: false,
      tableProjectData: [{ 'department': 'A', 'project': 3, 'taskCard': 5 },
      { 'department': 'B', 'project': 13, 'taskCard': 15 },
      { 'department': 'C', 'project': 23, 'taskCard': 25 },
      { 'department': 'D', 'project': 7, 'taskCard': 16 }],
      tableUserData: [{ 'department': 'A', 'internal': 13, 'external': 5 },
      { 'department': 'B', 'internal': 15, 'external': 5 },
      { 'department': 'C', 'internal': 12, 'external': 5 },
      { 'department': 'D', 'internal': 18, 'external': 6 }]
    }
  },
  computed: {
    // ...mapGetters({
    //   getUser: 'GET_USER'
    // })
  },
  methods: {
    // ...mapActions({
    //   setUser: 'SET_USER'
    // })
  },
  components: {
    PieProject,
    DoughnutDepartment,
    HorizontalBarParticipant,
    ModalTask
  }
}
</script>

<style scoped>
.home--container {
  background-color: #fff;
  padding: 30px;
}
.home--table {
  margin-bottom: 30px;
}
.scroll {
  /*scroll-behavior: smooth;*/
}
</style>
