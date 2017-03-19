package br.com.contatos.importcontact;

public class ContactCreatorFactory {

	private ContactCreatorFactory(){}
	
	public static ContactCreator getContactCreator(SourceType sourceType){
		
		switch(sourceType){
		case XML:
			return new ContactCreatorXML();
		case JSON:
			return new ContactCreatorJSON();
		case PLAIN_TEXT:
			return new ContactCreatorPlainText();
		}
		
		return null;
	}
	
}
