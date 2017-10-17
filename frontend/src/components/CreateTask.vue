<template lang="html">
  <div class="content" align="left">

    <div class="columns">
      <div class="column is-three-quarters">
        <h6 style="margin-top: 16px"><b>New Task +</b></h6>
      </div>
      <div class="column" align="right">
        <button class="button no-border">
          <i class="fa fa-close" aria-hidden="true"></i>
        </button>
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <input class="input title-field" type="text" placeholder="Task name">
      </div>
    </div>

    <div class="columns">
      <div class="column">
        <textarea class="textarea" placeholder="Description"></textarea>
      </div>
    </div>

    <div class="columns" align="center">
      <div class="column">
          <a class="button is-dark">Create task</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
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
