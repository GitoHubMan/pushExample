

(function () {
    'use strict'

    angular
        .module('app')
        .factory('loginDataService', loginDataService);

    function loginDataService($http, $q, $rootScope) {
        return {
            login: login,
            logout: logout,
            getCurrentUser: getCurrentUser,
            isAnonymous: isAnonymous
        };

        function login(username, password) {
            var def = $q.defer();

            $http({
                method: 'POST',
                url: $rootScope.apiBaseUrl + '/api/auth/login',
                headers: {'Content-Type': 'application/x-www-form-urlencoded', 'X-Login-Ajax-Call': 'true'},
                transformRequest: function (obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                withCredentials: true,
                data: {username: username, password: password}
            })
                .then(function (response) {
                    def.resolve("OK");
                }, function (response) {
                    if(response.status === 401) {
                        def.reject("Bad credentials");
                    } else {
                        def.reject("Server return " + response.status + ": " + response.statusText);
                    }
                });

            return def.promise;
        }

        function logout() {
            var deferred = $q.defer();
            $http.post($rootScope.apiBaseUrl + '/api/auth/logout')
                .then(function (response) {
                    $rootScope.isAuthenticated = false;
                    deferred.resolve(response);
                })
                .catch(function(response){
                    deferred.reject();
                });
            return deferred.promise;
        }

        function getCurrentUser() {
            var def = $q.defer();
            
            $http.get($rootScope.apiBaseUrl + '/api/auth/user')
                .then(function (response) {
                    def.resolve(response);
                })
                .catch(function (reason) {
                    def.resolve(reason);
                });
            
            return def.promise;
        }

        function isAnonymous(user) {
            if (angular.isDefined(user)) {
                return user.roles.indexOf("ANONYMOUS") !== -1;
            }
            return true;
        }
    }

})();