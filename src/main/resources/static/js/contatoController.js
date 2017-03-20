angular.module("contatoApp").controller("contatoController",function($scope, $window, $http,contatosAPI){
	$scope.contatos = [];
	$scope.contato;
	$scope.salvarContato = function(contato){
		contatosAPI.saveContato(contato).then(function(response){
			 if(response.data.codigo == 1){
				 $scope.contato = null;
				 $scope.successMessage = "Contato inserido com sucesso";
				 carregarContatos();
				// $window.alert(response.data.mensagem);
			 }else{
				 $scope.errorMessage = "Erro ao inserir contato";
				 
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
	
	$scope.limparCampos = function(){
		 $scope.contato = null;
	};
	
	var carregarContatos = function(){
		contatosAPI.getContatos().then(function(response){
					$scope.contatos = response.data;
			},function(err){
				$window.alert(err);
			});
	};
	
	
	  $scope.lookupContact = function(nome) {
			return contatosAPI.getContatoByName(nome).then(function(response){
					return response.data;
			},function(error){
				$window.alert(error);
			});
		  };
		  	
	
	carregarContatos();
	
});