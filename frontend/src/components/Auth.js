export default ({
  authenticate: {
    loggedIn: true,
    login: function () { this.loggedIn = true },
    logout: function () { this.loggedIn = false }
  }
})
