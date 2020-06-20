'use strict';

var Login = Login || {

  bindLogin : function() {
    $("#login").bind("click", function(event) {
      $('form').submit();
      return;
    })
  },

}

$(document).ready(function() {
  Login.bindLogin();
});
