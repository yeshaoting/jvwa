/**
 * file upload controller
 */
app.controller('FileUploadCtrl', [
    '$scope',
    'FileUploader',
    function($scope, FileUploader) {
      var uploader = $scope.uploader = new FileUploader({
        url : Constants.server_url + '/file/upload'
      });

      // FILTERS

      uploader.filters.push({
        name : 'customFilter',
        fn : function(item /* {File|FileLikeObject} */, options) {
          return this.queue.length < 10;
        }
      });

      // CALLBACKS

      uploader.onWhenAddingFileFailed = function(item /* {File|FileLikeObject} */, filter, options) {
        // console.info('onWhenAddingFileFailed', item, filter, options);
      };
      uploader.onAfterAddingFile = function(fileItem) {
        if (fileItem.file.size > Constants.fileUploadMaxSize) {
          window.Notify.error("文件 " + fileItem.file.name + " 超过最大" + (Constants.fileUploadMaxSize / 1024 / 1024)
              + "MB文件上传限制！");
        }
        // console.info('onAfterAddingFile', fileItem);
      };
      uploader.onAfterAddingAll = function(addedFileItems) {
        // console.info('onAfterAddingAll', addedFileItems);
      };
      uploader.onBeforeUploadItem = function(item) {
        // console.info('onBeforeUploadItem', item);
      };
      uploader.onProgressItem = function(fileItem, progress) {
        // console.info('onProgressItem', fileItem, progress);
      };
      uploader.onProgressAll = function(progress) {
        // console.info('onProgressAll', progress);
      };
      uploader.onSuccessItem = function(fileItem, response, status, headers) {
        // console.info('onSuccessItem', fileItem, response, status, headers);
      };
      uploader.onErrorItem = function(fileItem, response, status, headers) {
        // console.info('onErrorItem', fileItem, response, status, headers);
      };
      uploader.onCancelItem = function(fileItem, response, status, headers) {
        // console.info('onCancelItem', fileItem, response, status, headers);
      };
      uploader.onCompleteItem = function(fileItem, response, status, headers) {
        // console.info('onCompleteItem', fileItem, response, status, headers);
      };
      uploader.onCompleteAll = function() {
        // console.info('onCompleteAll');
      };

      // console.info('uploader', uploader);
    } ]);