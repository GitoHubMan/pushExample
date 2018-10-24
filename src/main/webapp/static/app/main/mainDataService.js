

(function () {
    'use strict'

    angular
        .module('app')
        .factory('mainDataService', mainDataService);

    function mainDataService($http, $rootScope) {
        return {
            getFilters: getFilters,
            getSubscriptions: getSubscriptions
        };
        

        function getFilters() {
            return $http.get($rootScope.apiBaseUrl + "/api/subscription/filters")
                .catch(function (reason) {
                    console.log(reason);
                });
        }

        function getSubscriptions(filters) {
            return $http({
                url: $rootScope.apiBaseUrl + "/api/subscription",
                method: "POST",
                data: filters
            })
                .catch(function (reason) {
                    console.log(reason);
                });
        }
    }

})();