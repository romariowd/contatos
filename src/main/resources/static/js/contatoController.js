angular.module("contatoApp").controller("contatoController",function($scope, $window, $http,contatosAPI){
	$scope.contatos = [];

	$scope.salvarContato = function(contato){
		var response = contatosAPI.saveContato(contato).then(function(response){
			 if(response.data.codigo == 1){
				 carregarContatos();
				 $window.alert(response.data.mensagem);
			 }else{
				 $window.alert(response.data.mensagem);
				 
			 }
		},function(error){
			$window.alert(error);
		});
	 		
	};
	
	$scope.editarContato = function(contato){
		console.log("editar " + contato.nome);
	};
	
	$scope.deletarContato = function(contato){
		console.log("deletar " + contato.nome);
	};
	
	var carregarContatos = function(){
		contatosAPI.getContatos().then(function(response){
					$scope.contatos = response.data;
			},function(err){
				$window.alert(err);
			});
	};
	
	
	carregarContatos();
	
});