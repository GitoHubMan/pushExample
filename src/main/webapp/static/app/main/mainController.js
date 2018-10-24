

(function () {

    angular
        .module('app')
        .controller('mainController', mainController);

    function mainController($scope, mainDataService, $mdDialog) {
        angular.extend($scope, {
            getSubscriptions : getSubscriptions,
            compareStr : compareStr,
            addSelectedValueToArray : addSelectedValueToArray,
            showSendNotificationDialog: showSendNotificationDialog
        });

        $scope.headingTitle = "Select filters for subscriptions";
        getFilters();
        

        function getFilters() {
            mainDataService.getFilters()
                .then(function (response) {
                    $scope.filters = response.data;
                });
        }

        function getSubscriptions() {
            mainDataService.getSubscriptions($scope.filters)
                .then(function (response) {
                    $scope.subscriptionRes = response.data;
                });
        }

        function compareStr(s1, s2) {
            return angular.equals(s1, s2);
        }

        function addSelectedValueToArray(condition) {
            condition.selectedValues = [];
            condition.selectedValues.push(condition.selectedValue);
        }

        function showSendNotificationDialog() {
            var url = 'resources/app/notifications/send.notification.html';
            $mdDialog.show({
                locals: {
                    subscriptions: $scope.subscriptionRes.subscriptions,
                    conditions: $scope.filters
                },
                clickOutsideToClose: true,
                templateUrl: url,
                controller: 'notificationsController'
            }).then(function (value) {
                console.log(value);
            }).catch(function (reason) {
                console.log(reason);
            });
        }


    }

})();