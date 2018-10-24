(function () {
    //'use strict'

    angular
        .module('app')
        .controller('usersController', usersController);

    function usersController($scope, rolesDataService) {
        angular.extend($scope, {
        });

        alert(111111);
    }
})();