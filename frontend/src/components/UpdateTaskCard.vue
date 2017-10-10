<template lang="html">
  <div class="content" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Update Task Card </b></h6>
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
        <input class="input title-field" type="text" placeholder="Create mood board">
      </div>
      <div class="column" align="right">
        <b-dropdown style="margin-top: 8px" align="left">
            <button class="button" slot="trigger" style="width: 200px">
              <span> Microservice </span>
              <b-icon icon="arrow_drop_down"></b-icon>
            </button>
            <b-dropdown-item>Microservice</b-dropdown-item>
            <b-dropdown-item>Embedded System</b-dropdown-item>
            <b-dropdown-item>Digital UK</b-dropdown-item>
        </b-dropdown>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea class="textarea" placeholder="1st page is okay.
2nd page * add popup on this page to let interns know it's POPUP!!

3rd page No idea as well T ^ T

4nd page
- Add confirmation popup requirement when you want to delete something
- Page selection should be like this << < 1 2 3 4 5 ... > >>
• <<, >> mean Go to first page/last page
• <,> mean Go to next 5 pages
• ... mean go to xxx page (Popup and input the number)
- Should we add scroll bar in panel on the left?
4.1~4.2 is Okay"></textarea>
      </div>
    </div>

    <div class="columns">
      <div class="column is-three-quarters" align="left">
        <b-dropdown style="margin-top: 8px" align="left">
            <button class="button" slot="trigger" style="width: 200px">
              <span> Start date </span>
              <b-icon icon="arrow_drop_down"></b-icon>
            </button>
            <b-dropdown-item>Microservice</b-dropdown-item>
            <b-dropdown-item>Embedded System</b-dropdown-item>
            <b-dropdown-item>Digital UK</b-dropdown-item>
        </b-dropdown>
        <b-dropdown style="margin-top: 8px" align="left">
            <button class="button" slot="trigger" style="width: 200px">
              <span> Finish date </span>
              <b-icon icon="arrow_drop_down"></b-icon>
            </button>
            <b-dropdown-item>Microservice</b-dropdown-item>
            <b-dropdown-item>Embedded System</b-dropdown-item>
            <b-dropdown-item>Digital UK</b-dropdown-item>
        </b-dropdown>

      </div>
      <div class="column" align="right">
        <button class="button" style="width: 200px">
          <span> Add participants + </span>
        </button>
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
          <a class="button is-dark">Create task card</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{ 'no': 1, 'taskName': 'Makhamwan', 'taskCardName': 'Department A', 'registeredDate': '2017-09-17', 'writer': 'Boo', 'status': 'INTERNAL' },
    { 'no': 2, 'taskName': 'Net', 'taskCardName': 'Department A', 'registeredDate': '2017-10-1', 'writer': 'Boo', 'status': 'INTERNAL' },
    { 'no': 3, 'taskName': 'Boss', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' },
    { 'no': 4, 'taskName': 'Prang', 'taskCardName': 'Department B', 'registeredDate': '2017-10-4', 'writer': 'Net', 'status': 'EXTERNAL' }],
      isPaginated: true,
      isPaginationSimple: false
    }
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
