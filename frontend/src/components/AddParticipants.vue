<template lang="html">
  <div class="content" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>Add participants +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border" @click="cancle()">
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

    <section class="add-participants-tag--body">
      <el-tag
        v-for="tag in internalAddList"
        :key="tag"
        closable
        :type="tag">
        {{tag}}
      </el-tag>
    </section>
    <section class="add-participants-tag--body">
      <el-tag
        v-for="tags in externalAddList"
        :key="tags"
        closable
        :type="tags">
        {{tags}}
      </el-tag>
    </section>
    <br>
    <button class="button is-success" style="width: 200px" @click="save()">
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
    let idDepartment = localStorage.getItem('id_department_owner_card')
    let internalResponse = await Axios.get(`http://localhost:8090/get/internal-user-list/department/${idDepartment}`)
    for (let i = 0; i < internalResponse.data.length; i++) {
      this.internalUserList[i] = internalResponse.data[i].name
    }

    let externalResponse = await Axios.get(`http://localhost:8090/get/external-user-list/department/${idDepartment}`)
    for (let j = 0; j < externalResponse.data.length; j++) {
      this.externalUserList[j] = externalResponse.data[j].name
    }
    console.log(this.externalUserList)
  },
  methods: {
    addInternalParticipant () {
      this.internalAddList.push(this.internalUser)
    },
    addExternalParticipant () {
      this.externalAddList.push(this.externalUser)
    },
    cancle () {
    },
    async save () {
      let idUserListResponse = await Axios.get(`http://localhost:8090/get/idUserList/nameList/${this.internalAddList}`)
      console.log(idUserListResponse.data)
      let idCard = localStorage.getItem('id_create_card')
      let response = await Axios.post(`http://localhost:8091/add/internal-participants/${idCard}/${idUserListResponse.data}`)
      if (response.data === true) {
        this.$router.replace({ path: '/create-card' })
      } else {
        alert('failed')
      }
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
.limit-height {
  height: 300px;
  overflow-y: scroll;
}

</style>
