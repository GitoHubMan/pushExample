
angular
    .module('app', ['ui.router', 'ngMaterial', 'ngAnimate', 'ngAria'])
    .config(config)
    .run(run)
    .service('authInterceptor', function($q, $state, $rootScope) {
        var service = this;
        service.responseError = function(response) {
            if (response.status === 401 && $rootScope.isAuthenticated != null && $rootScope.isAuthenticated === true){
                $state.go('login');
            }
            return $q.reject(response);
        };

        // return {
        //     request: function(config) {
        //         console.log('in the interceptor');
        //         //config.headers = config.headers || {};
        //
        //         return config || $q.when(config);
        //     },
        //     response: function(response) {
        //         if (response.status === 401) {
        //             //  Redirect user to login page / signup Page.
        //             //$location.path('/login');
        //         }
        //         return response || $q.when(response);
        //     }
        // };
    });


function config($stateProvider, $mdIconProvider, $httpProvider){
    $stateProvider
        .state('login', {
            title: 'login',
            url: '/login',
            templateUrl: 'resources/app/login/login.html',
            controller: 'loginController'
        })
        .state('main', {
            title: 'mainState',
            url: '/main',
            templateUrl: 'resources/app/main/main.html',
            controller: 'mainController'
        });
    
    $mdIconProvider.defaultIconSet('resources/mdi-icons.svg', 24);
    $httpProvider.interceptors.push('authInterceptor');
}

function run($rootScope, $state, loginDataService) {
    $rootScope.$state = $state;
    $rootScope.logout = logout;

    //$rootScope.apiBaseUrl = '/pushapp-bestnews';
    $rootScope.apiBaseUrl = '';

    loginDataService.getCurrentUser()
        .then(function (response) {
            if(response.status === 200) {
                $rootScope.isAuthenticated = !loginDataService.isAnonymous(response.data);
            } else {
                $rootScope.isAuthenticated = false;
                $state.go('login');
                //$state.go('main');
            }
        });
    
    function logout() {
        loginDataService.logout()
            .then(function (value) {
                $state.go('login');
            });
    }
}
