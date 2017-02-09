/**
 * Created by Thomas on 5/28/2015.
 */
var app = angular.module('groceryListApp', ["ngRoute"]);

app.config(function($routeProvider){
    $routeProvider
        .when("/",{
            templateUrl: "views/categoryList.html",
            controller: "GroceryListItemsController"
        })
        .when("/addItem",{
            templateUrl: "views/addItem.html",
            controller: "GroceryListItemsController"
        })
        .when("/addItem/:id/",{
            templateUrl: "views/addItem.html",
            controller: "GroceryListItemsController"
        })
        .when("/listProducts/:id/",{
            templateUrl: "views/listProducts.html",
            controller: "listProductsController"
        })
        .when("/addProduct/:id",{
            templateUrl: "views/addProduct.html",
            controller: "addProductsController"
        })
        .when("/listPhotos/:id/",{
            templateUrl: "views/photosList.html",
            controller: "listPhotosController"
        })
        .when("/addPhoto/:id",{
            templateUrl: "views/addPhoto.html",
            controller: "addPhotosController"
        })
        .otherwise({
            redirectTo: "/"
        })
});

app.service("GroceryService", function(){

    var groceryService = {};


    groceryService.getNewId = function(){
        if(groceryService.newId) {
            groceryService.newId++;
            return groceryService.newId;
        }
        else {

        }
    }
    groceryService.save = function(entry){
        groceryService.groceryItems.push(entry);
    };

    return groceryService;

});

app.controller("HomeController", ["$scope", function($scope) {

    $scope.appTitle = "Grocery List"

}]);

app.controller("GroceryListItemsController", ["$http","$scope", "$routeParams", "$location", "GroceryService", function($http, $scope, $routeParams, $location, GroceryService){

	
	
      $http.get('http://localhost:8080/demos/resources/category').success(function(response){
	   $scope.groceryItems =  response;
	});
    

    $scope.save = function(){
    	
    	$http.post('http://localhost:8080/demos/resources/category',$scope.groceryItem).success(function(response){
			alert(response.status);
		});
		
        $location.path("/");
    }

}]);


app.controller("listProductsController", ["$http","$scope", "$routeParams", "$location", "GroceryService", function($http, $scope, $routeParams, $location, GroceryService){

	$scope.id = $routeParams.id;
	
	
    $http.get("http://localhost:8080/demos/resources/category/products/"+$routeParams.id).success(function(response){
	   $scope.products =  response.extProd;
	});


}]);


app.controller("addProductsController", ["$http","$scope", "$routeParams", "$location", "GroceryService", function($http, $scope, $routeParams, $location, GroceryService){

	$scope.id = $routeParams.id;
	
    $scope.saveProduct = function(){
  	
    	$http.post("http://localhost:8080/demos/resources/category/"+$routeParams.id+"/products/",$scope.product).success(function(response){
			alert(response.status);
		});
		
    	$location.path("/");
    }

}]);
    
    
    app.controller("listPhotosController", ["$http","$scope", "$routeParams", "$location", "GroceryService", function($http, $scope, $routeParams, $location, GroceryService){

    	$scope.id = $routeParams.id;
    	
    	
        $http.get("http://localhost:8080/demos/resources/products/photos/"+$routeParams.id).success(function(response){
    	   $scope.photos =  response.extPhoto;
    	});


    }]);
    
    app.directive('fileModel', ['$parse', function ($parse) {
        return {
           restrict: 'A',
           link: function(scope, element, attrs) {
              var model = $parse(attrs.fileModel);
              var modelSetter = model.assign;
              
              element.bind('change', function(){
                 scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                 });
              });
           }
        };
     }]);
  
    app.service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function(file, uploadUrl){
           var fd = new FormData();
           fd.append('file', file);
        
           $http.post(uploadUrl, fd, {
              transformRequest: angular.identity,
              headers: {'Content-Type': undefined}
           })
        
           .success(function(){
           })
        
           .error(function(){
           });
        }
     }]);
  

    app.controller("addPhotosController", ["$http","$scope", "$routeParams", "$location", "fileUpload", function($http, $scope, $routeParams, $location, fileUpload){

    	$scope.id = $routeParams.id;
    	
        $scope.savePhoto = function(){
      	
        	$http.post("http://localhost:8080/demos/resources/products/"+$routeParams.id+"/photos/",$scope.photo).success(function(response){
    			alert(response.status);
    		});
    		
        	$location.path("/");
        }
        
        $scope.uploadFile = function(){
            var file = $scope.myFile;
            
            console.log('file is ' );
            console.dir(file);
            
            var uploadUrl = "http://localhost:8080/demos/resources/products/photos/upload";
            fileUpload.uploadFileToUrl(file, uploadUrl);
         };
    }]);   