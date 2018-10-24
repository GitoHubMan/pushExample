(function () {
    'use strict';

    angular
        .module('app')
        .controller('notificationsController', notificationsController);

    function notificationsController($scope, $mdDialog, subscriptions, conditions, notificationsDataService, $rootScope) {

        angular.extend($scope, {
            hide: $mdDialog.hide,
            cancel: $mdDialog.cancel,
            submit: submit,
            subscriptions: subscriptions,
            conditions: conditions
        });

        function submit(notification) {
            try {
                if(notification.actions)
                    notification.actions = JSON.parse(notification.actions);
            } catch (e) {
                console.log(e);
            }
            notificationsDataService.send($scope.conditions, notification)
                .then(function (response) {
                    $mdDialog.hide();
                    $rootScope.sendingres = response.data;
                })
                .catch(function (response) {
                    if(response != null && response.status === 401)
                        $mdDialog.hide();
                    $scope.serverError = response.status + ": " + (response.data ? response.data : response.statusText);
                });
        }
    }
})();
