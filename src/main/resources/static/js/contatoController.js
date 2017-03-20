angular.module("contatoApp").controller("contatoController",function($scope, $window, $http,contatosAPI){
	$scope.selected = undefined;
	$scope.states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
	$scope.contatos = [];
	$scope.salvarContato = function(contato){
		contatosAPI.saveContato(contato).then(function(response){
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
	
	$scope.lookupContact = function(nome){
		contatosAPI.getContatoByName(nome).then(function(response){
			$scope.selected = response.data;
			console.log(response);
		},function(error){
			$window.alert(error);
		});
	 		
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