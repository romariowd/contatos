package br.com.contatos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.contatos.Contato;
import br.com.contatos.importcontact.ContactCreator;
import br.com.contatos.importcontact.ContactCreatorFactory;
import br.com.contatos.importcontact.ContactCreatorListener;
import br.com.contatos.importcontact.SourceType;

@Controller
public class UploadController {
	
	
	@PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
           // return "redirect:uploadStatus";
        }
        	
        try {

           SAXReader reader = new SAXReader();
           Document document = reader.read(file.getInputStream());
           ContactCreator creator = ContactCreatorFactory.getContactCreator(SourceType.XML);
           creator.createContactFromSource("C:\\Users\\Romario\\Desktop\\contatos.xml", new ContactCreatorListener() {
			
			@Override
			public void onComplete(List<Contato> contatos) {
				
				
			}
		});
           


        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return "index";
    }
	

}
