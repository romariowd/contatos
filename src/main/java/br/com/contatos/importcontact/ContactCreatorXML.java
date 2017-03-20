package br.com.contatos.importcontact;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import br.com.contatos.domain.Contato;

public class ContactCreatorXML implements ContactCreator {

	List<Contato> contatos = new ArrayList<>();
	Contato contato = new Contato();
	ContactCreatorListener listener;

	@Override
	public void createContactFromSource(String path,ContactCreatorListener listener) {
		if(listener != null)
			this.listener = listener;
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(path));
			createContactFromXML(doc.getRootElement());
			listener.onComplete(contatos);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
	}

	
    public void createContactFromXML(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if(node != null && node.getName() != null && node.getName().equals("contato")){
            	contato = new Contato();
            }
           if ( node instanceof Element ) {
        	   Element el = (Element) node;
   			
   			if(el.getName() != null && !el.getName().equals("")){
   				switch(el.getName()){
   				case "contato":
   					String id = null;
   			    	if(el.attributeCount() == 1 && (id = el.attributeValue("id")) != null){
   			    		contato.setId(Long.parseLong(id));
   			    	}
   				case "nome":
   					contato.setNome(el.getStringValue());
   					break;
   				case "telefone":
   					contato.setTelefone(el.getStringValue());
   					break;
   				case "email":
   					contato.setEmail(el.getStringValue());
   					break;
   				case "empresa":
   					contato.setEmpresa(el.getStringValue());
   					break;
   				case "amigo":
   				Contato amigo = new Contato();
   				if(el.attributeValue("id") != null && !el.attributeValue("id").isEmpty())
   				amigo.setId(Long.parseLong(el.attributeValue("id")));
   				amigo.setEmail(el.attributeValue("email"));
   				contato.getContatos().add(amigo);
   				break;
   				
   				}
   				createContactFromXML((Element)node);
   			}
        	   
            	if(element.getName().equals("contatos")){
            		contatos.add(contato);
    	    	
            	}
            }
        }
    }
	
}
