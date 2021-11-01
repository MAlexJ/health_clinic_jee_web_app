'use strict';

app.component('management', {
    controller: function (RestAPI, $scope) {

        // API: users
        RestAPI.get("users")
            .then(function (response) {
                $scope.users = response.data.users;
            }, function (reason) {
                console.log(reason);
            });

        // API: role
        RestAPI.get("roles")
            .then(function (response) {
                $scope.roles = response.data;
            }, function (reason) {
                console.log(reason);
            });

        $scope.handleUsers = function (user) {
            $scope.selected_user = user
        }

        $scope.handleRole = function (role) {

            $scope.selected_role_name = role;

            RestAPI.get("users?role=" + role)
                .then(function (response) {
                    $scope.users = response.data.users;
                }, function (reason) {
                    console.log(reason);
                });

        }

    },
    template:
        `<div class="container">
   <div class="row justify-content-md-center" style="padding-top: 40px;">
      <div class="row" style="padding: 20px; background: #eceeef; border-radius: 10px;">
         <div class="row">
            <div class="col-lg-12">
               <div class="row" style="padding-bottom: 30px; padding-top: 20px">
                  <div class="col-lg-12">
                     <div class="row">
                        <div class="col-lg-3">
                           <div class="dropdown">
                              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              User type <span ng-show="selected_role_name">: {{ selected_role_name }}</span> 
                              </button>
                              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                 <a class="dropdown-item" href="#" ng-repeat="role in roles" ng-click="handleRole(role)">{{ role }}</a>
                              </div>
                           </div>
                        </div>
                        <div class="col-lg-3">
                           <div class="dropdown">
                              <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                              OrderBy <span ng-show="selected_order_name">: {{ selected_order_name }}</span> 
                              </button>
                              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                 <a class="dropdown-item" href="#" ng-repeat="order in orders" ng-click="handleOrder(order)">{{ ordered.name }}</a>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
            <div class="col-lg-12">
               <div class="row">
                  <div class="col-lg-12">
                     <table class="table table-hover">
                        <thead class="thead-dark">
                           <tr>
                              <th scope="col">First Name</th>
                              <th scope="col">Last Name</th>
                              <th scope="col">Room number</th>
                           </tr>
                        </thead>
                        <tbody>
                           <tr ng-repeat="user in users">
                              <td>{{ user.firstName }}</td>
                              <td>{{ user.secondName }}</td>
                              <td>{{ user.room }}</td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
                  <div class="col-lg-12">
                     <nav aria-label="...">
                        <ul class="pagination pagination-sm justify-content-center">
                           <li class="page-item active" aria-current="page">
                              <span class="page-link">1</span>
                           </li>
                           <li class="page-item"><a class="page-link" href="#">2</a></li>
                           <li class="page-item"><a class="page-link" href="#">3</a></li>
                        </ul>
                     </nav>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>`
});