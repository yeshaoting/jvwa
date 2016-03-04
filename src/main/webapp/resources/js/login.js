'use strict';

var Login = Login || {

  bindLogin : function() {
    $("#login").bind("click", function(event) {
      
      var isRemember = $("#isRemember").is(":checked");
      $("#isRemember").val(isRemember);
      $('form').submit();
      return;
    })
  },

}

$(document).ready(function() {
  Login.bindLogin();
});
