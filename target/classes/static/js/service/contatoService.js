angular.module("contatoApp").factory("contatosAPI",function($http,config){

	var _salvarContato = function(contato){
			return $http({
					method:'POST',
					url: config.baseUrl + "/salvar",
					data:contato
				});
	};
		var _getContatos = function(){
			return $http({
					method:'GET',
					url: config.baseUrl + "/obter"
				});
	};
	
	var _getContatoByName = function(nome){
		return $http({
				method:'GET',
				url: config.baseUrl + "/nome/" + nome
			});
};

		return {
			getContatos:_getContatos,
			saveContato:_salvarContato,
			getContatoByName:_getContatoByName
		};
});