'use strict';

app.component('appointments', {
    controller: function (RestAPI, $scope) {
    },
    template:
        `<div class="container">
   <div class="row justify-content-md-center" style="padding-top: 40px;">
      <div class="row" style="padding: 20px; background: #eceeef; border-radius: 10px;">
         <div class="row">
            <div class="col-lg-12">
               <div class="row">
                  <div class="col-lg-12">
                     <table class="table table-hover">
                        <thead class="thead-dark">
                           <tr>
                              <th scope="col">#</th>
                              <th scope="col">Client</th>
                              <th scope="col">Doctor</th>
                              <th scope="col">Date</th>
                              <th scope="col">Time</th>
                              <th scope="col">Information</th>
                           </tr>
                        </thead>
                        <tbody>
                           <tr ng-repeat="doctor in doctors" ng-click="handleDoctors(doctor)">
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>                        
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