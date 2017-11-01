<template lang="html">
  <div class="home--container">
    <el-row class="home--chart">
      <el-col :span="11">
        <br>
        <b-table
            class="my-task--table"
            :data="tableUserData"
            default-sort="title">

            <template scope="props">
              <b-table-column field="Department" label="Department" width="50" sortable>
                  {{ props.row.department }}
              </b-table-column>

              <b-table-column field="Project" label="Total Internal" sortable>
                  {{ props.row.internal }}
              </b-table-column>

              <b-table-column field="TaskCard" label="Total External" sortable>
                  {{ props.row.external }}
              </b-table-column>
            </template>
        </b-table>
      </el-col>
      <el-col :span="2">
        <br></br>
      </el-col>
      <el-col :span="11">
        <b-table
            class="my-task--table"
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
      </el-col>
      <!-- <bar-task :height="120"></bar-task> -->
    </el-row>
    <el-row>
      <hr>
      <el-col :span="11">
        <horizontal-bar-participant></horizontal-bar-participant>
      </el-col>

      <el-col :span="2">
        <br>
      </el-col>
      <el-col :span="11">
        <doughnut-department></doughnut-department>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// import { mapActions } from 'vuex'
import BarTask from '@/components/Chart/BarTask'
import DoughnutDepartment from '@/components/Chart/DoughnutDepartment'
import HorizontalBarParticipant from '@/components/Chart/HorizontalBarParticipant'
export default {
  data () {
    return {
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
    BarTask,
    DoughnutDepartment,
    HorizontalBarParticipant
  }
}
</script>

<style scoped>
.home--container {
  background-color: #fff;
  padding: 30px;
}
</style>
