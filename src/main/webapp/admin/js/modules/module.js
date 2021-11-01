'use strict';

let app = angular.module('app', ['ui.router', 'ngSanitize']);

app.config(function ($locationProvider, $stateProvider, $urlRouterProvider) {
    $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
    let states = [
        {
            name: 'home',
            url: '/',
            component: 'home'
        },
        {
            name: 'appointments',
            url: '/appointments',
            component: 'appointments'
        },
        {
            name: 'management',
            url: '/management',
            component: 'management'
        }
    ];

    states.forEach(function (state) {
        $stateProvider.state(state);
    });
    $urlRouterProvider.otherwise("/");
});