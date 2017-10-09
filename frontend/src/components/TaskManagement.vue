<template lang="html">
  <div class="task-management--container">
    <section class="document-table--body">
      <div class="document-table--button" align="right">
        <button class="button is-primary" @click="generateDocument()">
          <i class="fa fa-plus" aria-hidden="true">  Document</i>
        </button>
        <button class="button is-danger" @click="logout()">
          <i class="fa fa-sign-out" aria-hidden="true"> Log out</i>
        </button>
      </div>
      <b-table
          :data="tableData"
          :paginated="isPaginated"
          :per-page="5"
          default-sort="title">

          <template scope="props">
              <b-table-column field="id" label="ID" width="40" sortable numeric>
                  {{ props.row.id }}
              </b-table-column>

              <b-table-column field="title" label="Title" sortable>
                  {{ props.row.title }}
              </b-table-column>

              <b-table-column field="writer" label="Writer" sortable>
                  {{ props.row.writer }}
              </b-table-column>

              <b-table-column field="createdate" label="Create Date" sortable centered>
                  <span class="tag is-success">
                      {{ formatDate(props.row.createdate) }}
                  </span>
              </b-table-column>

              <b-table-column field="lastdate" label="Last Edit Date" sortable centered>
                  <span class="tag is-success">
                      {{ formatDate(props.row.lastdate) }}
                  </span>
              </b-table-column>

              <b-table-column label="Option" centered>
                <button class="button is-warning is-small" @click="toggleEditDialog(props.row)">
                  <i class="fa fa-pencil-square-o" aria-hidden="true"> Edit</i>
                </button>
                <button class="button is-danger is-small" @click="deleteTable(props.row)">
                  <i class="fa fa-trash-o" aria-hidden="true">Delete</i>
                </button>
              </b-table-column>

              <el-dialog title="Edit document":visible.sync="isEditFormOpen" size="tiny">
                <hr>
                <b-field label="Title">
                    <b-input v-model="tempRow.title"></b-input>
                </b-field>
                <b-field label="Writer">
                    <b-input v-model="tempRow.writer"></b-input>
                </b-field>
                <b-field label="Content">
                    <b-input v-model="tempRow.contents" type="text"></b-input>
                </b-field>
                <b-field label="Password">
                    <b-input v-model="tempRow.password" type="text"></b-input>
                </b-field>
                <button class="button is-primary" @click="edit()">
                  <i class="fa fa-pencil-square-o" aria-hidden="true"> Submit</i>
                </button>
              </el-dialog>

              <!-- {{ props.row.title }} -->
              <!-- <edit-form :tableData="props.row" v-if="isEditFormOpen"></edit-form> -->

              <!-- <b-table-column label="Option">
                  <b-icon pack="fa"
                      :icon="props.row.gender === 'Male' ? 'mars' : 'venus'">
                  </b-icon>
                  {{ props.row.gender }}
              </b-table-column> -->
          </template>
      </b-table>
    </section>
  </div>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{'id': 1, 'title': 'a', 'writer': 'b', 'content': 'c', 'password': 'd', 'createdate': 'e', 'lastdate': 'f'}],
      isPaginated: true,
      isPaginationSimple: false
    }
  }
}
</script>

<style lang="css">
</style>
