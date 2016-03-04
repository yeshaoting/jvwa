'use strict';

/*
 * var app = angular.module('app', [ 'ngAnimate', 'ngCookies', 'ngResource',
 * 'ngSanitize', 'ngTouch', 'ngStorage', 'ui.router', 'ui.bootstrap', 'ui.load',
 * 'ui.jq', 'ui.validate', 'oc.lazyLoad', 'pascalprecht.translate' ]);
 */

var app = angular.module('app', [ 'ngSanitize', 'ngRoute', 'ui.bootstrap', 'ui.utils', 'ui.router', 'ui.select', 'angularFileUpload' ]);

app.filter('propsFilter', function() {
  return function(items, props) {
    var out = [];

    if (angular.isArray(items)) {
      items.forEach(function(item) {
        var itemMatches = false;

        var keys = Object.keys(props);
        for (var i = 0; i < keys.length; i++) {
          var prop = keys[i];
          var text = props[prop].toLowerCase();
          if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
            itemMatches = true;
            break;
          }
        }

        if (itemMatches) {
          out.push(item);
        }
      });
    } else {
      // Let the output be the input untouched
      out = items;
    }

    return out;
  };
});

app.filter('showModuleTypeName', function() {
  return function(text) {
    return Constants.moduleTypeMap[text];
  }
});

app.filter('showTemplateTypeName', function() {
  return function(text) {
    return Constants.templateTypeMap[text];
  }
});

app.filter('showLinkTypeName', function() {
  return function(text) {
    return Constants.linkTypeMap[text];
  }
});

app.filter('showNodeTypeName', function() {
  return function(text) {
    return Constants.nodeTypeMap[text];
  }
});


app.filter('ceil', function() {
  return function(text) {
    return Math.ceil(text);
  }
});

app.filter('encodeURI', function() {
  return function(text) {
    return window.encodeURI(text);
  }
});



