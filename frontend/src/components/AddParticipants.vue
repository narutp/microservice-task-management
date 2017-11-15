<template lang="html">
  <div class="add-participants--container" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Add participants +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border" @click="cancleAddParticipants()">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <!--  Internal Participants -->
    <div class="columns">
      <div class="column">
        <section class="my-task-table--body">
          <b-select v-model="internalUser" placeholder="Select a name">
            <option
              v-for="option in internalUserList"
              :value="option"
              :key="option">
              {{ option }}
            </option>
          </b-select>
        </section>
      </div>
      <div class="column">
        <button class="button is-info" @click="addInternalParticipant()">
          <span>Add Internal Participants</span>
        </button>
      </div>
    </div>

    <!--  external participants  -->
    <div class="columns">
      <div class="column">
        <section class="my-task-table--body">
          <b-select v-model="externalUser" placeholder="Select external">
            <option
              v-for="options in externalUserList"
              :value="options"
              :key="options">
              {{ options }}
            </option>
          </b-select>
        </section>
      </div>
      <div class="column">
        <button class="button is-info" @click="addExternalParticipant()">
          <span>Add External Participants</span>
        </button>
      </div>
    </div>

    <!--  Can't remove tag ?? -->
    <section class="add-participants-tag--body">
      <el-tag
        v-for="tag in internalAddList"
        :key="tag"
        closable
        type="success">
        {{tag}}
      </el-tag>
    </section>
    <section class="add-participants-tag--body">
      <el-tag
        v-for="tags in externalAddList"
        :key="tags"
        closable
        @close="handleClose(tag)"
        type="primary">
        {{tags}}
      </el-tag>
    </section>
    <hr>
    <button class="button is-success" @click="save()">
      <span>Save</span>
    </button>
  </div>
</template>

<script>
import Axios from 'axios'
export default {
  data () {
    return {
      isPaginated: true,
      isPaginationSimple: false,
      internalUser: '',
      internalUserList: [],
      internalAddList: [],
      externalUser: '',
      externalUserList: [],
      externalAddList: []
    }
  },
  async mounted () {
    let idDepartment = localStorage.getItem('id_department_owner_card')
    let internalResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${idDepartment}`)
    // can now get list at once by set all list array to equal to data that recieve first
    // then set it (replace) it to equal to list name
    this.internalUserList = internalResponse.data
    for (let i = 0; i < internalResponse.data.length; i++) {
      this.internalUserList[i] = internalResponse.data[i].name
    }

    let externalResponse = await Axios.get(`http://localhost:8090/get/external-user-list/department/${idDepartment}`)
    this.externalUserList = externalResponse.data
    for (let j = 0; j < externalResponse.data.length; j++) {
      this.externalUserList[j] = externalResponse.data[j].name
    }
  },
  methods: {
    addInternalParticipant () {
      this.internalAddList.push(this.internalUser)
    },
    addExternalParticipant () {
      this.externalAddList.push(this.externalUser)
    },
    async cancleAddParticipants () {
      let idCard = localStorage.getItem('id_create_card')
      let cancleResponse = await Axios.post(`http://localhost:8091/delete/project-card/${idCard}`)
      if (cancleResponse.data === true) {
        this.$router.replace({ path: '/create-card' })
      } else {
        alert('failed')
      }
    },
    async save () {
      let idCard = localStorage.getItem('id_create_card')
      // For add only each internal or external by sending space instead of undefine empty obj to server
      if (this.internalAddList.length === 0) {
        this.internalAddList.push('null')
      }
      if (this.externalAddList.length === 0) {
        this.externalAddList.push('null')
      }
      console.log(this.internalAddList)
      console.log(this.externalAddList)

      let self = this
      let idInternalUserListResponse = await Axios.get(`http://localhost:8090/get/idUserList/nameList/${this.internalAddList}`)
      let idExternalUserListResponse = await Axios.get(`http://localhost:8090/get/idUserList/nameList/${this.externalAddList}`)
      console.log(idExternalUserListResponse.data)
      let response = await Axios.post(`http://localhost:8091/add/participants/${idCard}/${idInternalUserListResponse.data}/${idExternalUserListResponse.data}`)
      if (response.data === true) {
        let historyResponse = await Axios.post(`http://localhost:8090/add/history/${idCard}/${idInternalUserListResponse.data}/${idExternalUserListResponse.data}`)
        if (historyResponse.data === true) {
          self.$router.replace({ path: '/create-card' })
        } else {
          alert('failed to add history')
        }
      } else {
        alert('failed to add participants')
      }
    },
    async addHistory () {

    }
  }
}
</script>

<style scoped>
.add-participants--container {
  background-color: #fff;
  padding: 30px;
}
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
