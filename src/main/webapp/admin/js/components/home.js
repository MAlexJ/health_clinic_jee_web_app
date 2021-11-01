'use strict';

app.component('home', {
    constructor: function () {
        console.log("constructor");
    },
    controller: function (RestAPI, $scope) {

        // API: clients
        RestAPI.get("clients")
            .then(function (response) {
                $scope.clients = response.data.users;
            }, function (reason) {
                console.log(reason);
            });

        // API: doctors
        RestAPI.get("doctors")
            .then(function (response) {
                $scope.doctors = response.data.users;
            }, function (reason) {
                console.log(reason);
            });

        // API: categories
        RestAPI.get("categories")
            .then(function (response) {
                $scope.categories = response.data;
            }, function (reason) {
                console.log(reason);
            });

        $scope.searchClient = function (search_first_name, search_last_name, search_phone) {
            if (isUndefined(search_first_name) && isUndefined(search_last_name) && isUndefined(search_phone)) {
                return;
            }
            let first_name = isUndefined(search_first_name) ? "" : search_first_name;
            let last_name = isUndefined(search_last_name) ? "" : search_last_name;
            RestAPI.get("clients?firstName=" + first_name + "&lastName=" + last_name)
                .then(function (response) {
                    $scope.clients = response.data.users;
                }, function (reason) {
                    console.log(reason);
                });
        };

        $scope.handleClients = function (client) {
            $scope.selected_client = client
        }

        $scope.handleDoctors = function (doctor) {
            $scope.selected_doctor = doctor
        }

        $scope.handleCategory = function (category) {
            console.log(category);
            console.log("id = " + category.id + ", name = " + category.name);
            console.log(">>>> " + category);


            $scope.selected_category_name = category.name;

            RestAPI.get("doctors?category=" + category.id)
                .then(function (response) {
                    $scope.doctors = response.data.users;
                }, function (reason) {
                    console.log(reason);
                });

        }

        function isUndefined(val) {
            return val === undefined || val === '';
        }
    },
    template:
        `<div class="container">
   <div class="row justify-content-md-center" style="padding-top: 10px;">
      <div class="row" style="padding: 20px;">
         <div class="col-lg-7">
            <div class="row">
               <div class="col-lg-12" style="padding: 20px; background: #eceeef; border-radius: 10px; margin-bottom: 10px">
                  <div class="row">
                     <div class="col-lg-12">
                        <h5>Patient</h5>
                     </div>
                     <div class="col-lg-12">
                        <div class="row" style="padding-bottom: 30px; padding-top: 20px">
                           <div class="col-lg-3"><input class="form-control" ng-model="search_first_name" type="text" placeholder="First Name"></div>
                           <div class="col-lg-3"><input class="form-control" ng-model="search_last_name" type="text" placeholder="Last Name"></div>
                           <div class="col-lg-2"><button type="button" ng-click="searchClient(search_first_name, search_last_name, search_phone)" class="btn btn-secondary">Search</button></div>
                           <div class="col-lg-2"><button type="button" class="btn btn-primary">New Client</button></div>
                           <div class="col-lg-2"></div>
                        </div>
                     </div>
                     <div class="col-lg-12">
                        <table class="table table-hover">
                           <thead class="thead-dark">
                              <tr>
                                 <th scope="col">First Name</th>
                                 <th scope="col">Last Name</th>
                                 <th scope="col">Phone</th>
                                 <th scope="col">Date of Birth</th>
                              </tr>
                           </thead>
                           <tbody>
                              <tr ng-repeat="client in clients"  ng-click="handleClients(client)">
                                 <td>{{ client.firstName }}</td>
                                 <td>{{ client.secondName }}</td>
                                 <td>{{ client.phone }}</td>
                                 <td> <span ng-show="client.date">{{ client.date.day }}/{{ client.date.month }}/{{ client.date.year }}</span></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
               <div class="col-lg-12" style="padding: 20px; background: #eceeef; border-radius: 10px; margin-top: 10px">
                  <div class="row">
                     <div class="col-lg-12">
                        <h5>Doctor</h5>
                     </div>
                     <div class="col-lg-12">
                        <div class="row" style="padding-bottom: 30px; padding-top: 20px">
                           <div class="col-lg-12">
                              <div class="dropdown">
                                 <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 Category <span ng-show="selected_category_name">: {{ selected_category_name }}</span> 
                                 </button>
                                 <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a class="dropdown-item" href="#" ng-repeat="category in categories" ng-click="handleCategory(category)">{{ category.name }}</a>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
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
                              <tr ng-repeat="doctor in doctors" ng-click="handleDoctors(doctor)">
                                 <td>{{ doctor.firstName }}</td>
                                 <td>{{ doctor.secondName }}</td>
                                 <td>{{ doctor.room }}</td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-lg-5" >
            <div class="row" style="padding: 20px; background: #eceeef; border-radius: 10px; margin-left: 10px; margin-right: 10px">
               <div class="col-lg-12">
                  <h5>Appoinment</h5>
               </div>
               <div class="col-lg-12">
                  <div class="row" ng-show="selected_client" style="padding-top: 20px;padding-bottom: 20px" >
                     <div class="col-lg-12" >Patient: <b>{{ selected_client.firstName }} {{ selected_client.secondName }}</b></div>
                  </div>
               </div>
               <div class="col-lg-12">
                  <div class="row" ng-show="selected_doctor" style="padding-bottom: 20px" >
                     <div class="col-lg-12" >Doctor: <b>{{ selected_doctor.firstName }} {{ selected_doctor.secondName }}</b></div>
                  </div>
               </div>
               <div class="col-lg-12">
                  <div class="row">
                     <div class="col-lg-12">
                        <div class="input-group mb-3">
                           <span class="input-group-text" id="basic-addon1">Date: </span>
                           <input type="date" class="form-control" aria-describedby="basic-addon1">
                        </div>
                     </div>
                     <div class="col-lg-12">
                        <div class="input-group mb-3">
                           <span class="input-group-text" id="basic-addon1">Time: </span>
                           <input type="time" class="form-control" aria-describedby="basic-addon1">
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-lg-12" style="padding-bottom: 20px">
                  <div class="row">
                     <div class="col-lg-12">Comments: </div>
                     <div class="col-lg-12">
                        <div class="form-floating">
                           <textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea"></textarea>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-lg-12"><button type="button" class="btn btn-success">Create Appoinment</button></div>
            </div>
         </div>
      </div>
   </div>
</div>`
});