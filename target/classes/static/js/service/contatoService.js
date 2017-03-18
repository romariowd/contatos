angular.module("contatoApp").factory("contatosAPI",function($http,config){

	var _saveContact = function(contato){
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

		return {
			getContatos:_getContatos,
			saveContato:_saveContact
		};
});