<template lang="html">
  <div class="content" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Add participants +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column limit-height">
        <table class="table">
          <thead>
            <tr>
              <th><abbr title="Position">No</abbr></th>
              <th>Department</th>
            </tr>
          </thead>
          <tbody>
            <tr class="is-selected">
              <th>1</th>
              <th>Department A</th>
            </tr>
            <tr class="is-selected">
              <th>2</th>
              <th>Department B</th>
            </tr>
            <tr>
              <th>3</th>
              <th>Department C</th>
            </tr>
            <tr>
              <th>4</th>
              <th>Department D</th>
            </tr>
            <tr>
              <th>5</th>
              <th>Department E</th>
            </tr>
            <tr>
              <th>6</th>
              <th>Department F</th>
            </tr>
            <tr>
              <th>7</th>
              <th>Department G</th>
            </tr>
            <tr>
              <th>8</th>
              <th>Department H</th>
            </tr>
            <tr>
              <th>9</th>
              <th>Department I</th>
            </tr>
            <tr>
              <th>10</th>
              <th>Department J</th>
            </tr>

          </tbody>
        </table>
      </div>
      <div class="column">
        <section class="my-task-table--body">
          <b-table
              class="my-task--table"
              :data="tableData"
              default-sort="title">
              <template scope="props">
                  <b-table-column field="taskName" label="Name" sortable>
                      {{ props.row.taskName }}
                  </b-table-column>
              </template>
          </b-table>
        </section>
      </div>
    </div>

    <section class="my-task-table--body">
      <b-table
          class="my-task--table"
          :data="tableData"
          default-sort="title">
          <template scope="props">
              <b-table-column field="no" label="No" width="50" sortable numeric centered>
                  {{ props.row.no }}
              </b-table-column>

              <b-table-column field="taskName" label="Name" sortable>
                  {{ props.row.taskName }}
              </b-table-column>

              <b-table-column field="taskCardName" label="Department" sortable>
                  {{ props.row.taskCardName }}
              </b-table-column>

              <b-table-column field="writer" label="Position" sortable>
                  {{ props.row.taskCardName }}
              </b-table-column>

              <b-table-column field="registeredDate" label="Registered Date" sortable>
                  {{ props.row.registeredDate }}
              </b-table-column>

              <b-table-column field="status" label="Status" sortable centered>
                  <span class="tag is-info" v-if="props.row.status === 'INTERNAL'">
                      {{ props.row.status }}
                  </span>
                  <span class="tag is-warning" v-if="props.row.status === 'EXTERNAL'">
                      {{ props.row.status }}
                  </span>
              </b-table-column>
          </template>
      </b-table>
    </section>

    <br>
    <br>
    <div class="columns" align="center">
      <div class="column">
          <a class="button is-dark">Add participants+</a>
      </div>
    </div>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'taskName': 'Makhamwan', 'taskCardName': 'Department A', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'INTERNAL' },
    { 'no': 2, 'taskName': 'Net', 'taskCardName': 'Department A', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'INTERNAL' },
    { 'no': 3, 'taskName': 'Boss', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' },
    { 'no': 4, 'taskName': 'Prang', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' }],
      isPaginated: true,
      isPaginationSimple: false,
      user: []
    }
  },
  async mounted () {
    let idDepartment = localStorage.getItem('id_department_owner_card')
    console.log(idDepartment)
    let response = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${idDepartment}`)
    console.log(response)
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
.limit-height {
  height: 300px;
  overflow-y: scroll;
}

</style>
