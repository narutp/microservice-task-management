<template lang="html">
  <div class="update-participants--container" align="left">
    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Update participants +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border" @click="cancleUpdateParticipants()">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <!--  Internal Participants -->
    <div class="columns">
      <div class="column">
        <input v-model="temp" class="input" type="text" placeholder="Card name">
      </div>

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
        <input v-model="temp2" class="input" type="text" placeholder="Card name">
      </div>

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
      temp: '',
      temp2: '',
      idCard: '',
      internalUser: '',
      internalUserList: [],
      internalAddList: [],
      externalUser: '',
      externalUserList: [],
      externalAddList: []
    }
  },
  // TODO can't get data in select at first
  async mounted () {
    this.idCard = localStorage.getItem('id_create_card')
    let cardResponse = await Axios.get(`http://localhost:8091/get/project-card/idProjectCard?idProjectCard=${this.idCard}`)
    console.log(cardResponse.data)
    let internalArr = cardResponse.data.internalParticipants.length
    let externalArr = cardResponse.data.externalParticipants.length

    // TODO duplicate participant to add and can add same participant
    for (let i = 0; i < internalArr; i++) {
      let idInternalUser = cardResponse.data.internalParticipants[i]
      let userResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idInternalUser}`)
      this.internalAddList.push(userResponse.data.name)
    }

    for (let i = 0; i < externalArr; i++) {
      let idExternalUser = cardResponse.data.externalParticipants[i]
      let userResponse = await Axios.get(`http://localhost:8090/get/user/id?id=${idExternalUser}`)
      this.externalAddList.push(userResponse.data.name)
    }

    let idDepartment = localStorage.getItem('id_department_owner_card')
    let internalResponse = await Axios.get(`http://localhost:8090/get/internal-user-list?idDepartment=${idDepartment}`)
    for (let i = 0; i < internalResponse.data.length; i++) {
      this.internalUserList[i] = internalResponse.data[i].name
    }

    let externalResponse = await Axios.get(`http://localhost:8090/get/external-user-list?idDepartment=${idDepartment}`)
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
    async cancleUpdateParticipants () {
      let cancleResponse = await Axios.post(`http://localhost:8091/delete/project-card/idProjectCard?idProjectCard=${this.idCard}`)
      if (cancleResponse.data === true) {
        this.$router.replace({ path: '/create-card' })
      } else {
        alert('failed')
      }
    },
    async save () {
      let idInternalUserListResponse = await Axios.get(`http://localhost:8090/get/idUserList?nameList=${this.internalAddList}`)
      let idExternalUserListResponse = await Axios.get(`http://localhost:8090/get/idUserList?nameList=${this.externalAddList}`)

      let response = await Axios.post(`http://localhost:8091/add/participants?idProjectCard=${this.idCard}&idInternalUserList=${idInternalUserListResponse.data}&idExternalUserList=${idExternalUserListResponse.data}`)
      if (response.data === true) {
        this.$router.replace({ path: '/update-card' })
      } else {
        alert('failed')
      }
    }
  }
}
</script>

<style scoped>
.update-participants--container {
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
