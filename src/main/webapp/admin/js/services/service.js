'use strict';

app.factory('RestAPI', ['$http' , '$browser', function ($http, $browser) {
    let Response = {};
    let baseUrl = $browser.baseHref().concat('/rest/');

    Response.get = function (url) {
        return $http.get(baseUrl.concat(url));
    };

    Response.post = function (url, inData) {
        return $http.post(baseUrl.concat(url), inData);
    };

    return Response;
}]);