$('#login_form')
  .form({
    fields: {
      account: {
        identifier: 'account',
        rules: [
          {
            type   : 'empty',
            prompt : 'Please enter a account'
          }
        ]
      },
      password: {
        identifier: 'password',
        rules: [
          {
            type   : 'empty',
            prompt : 'Please enter a password'
          }
        ]
      }
    }
  })
;