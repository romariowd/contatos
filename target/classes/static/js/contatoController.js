angular.module("contatoApp").controller("contatoController",function($scope, $window, $http,contatosAPI){
	

	$scope.salvarContato = function(contato){
		var response = contatosAPI.saveContato(contato).then(function(response){
			 if(response.data.codigo == 1){
				 $window.alert(response.data.mensagem);
			 }else{
				 $window.alert(response.data.mensagem);
				 
			 }
		},function(error){
			$window.alert(error);
		});
	 		
	};
	
});