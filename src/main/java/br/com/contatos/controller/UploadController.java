package br.com.contatos.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

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
           treeWalk(document);
           


        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return "index";
    }
	
	public void treeWalk(Document document) {
        treeWalk( document.getRootElement() );
    }

    public void treeWalk(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
            	Element el = (Element) node;
            	System.out.println(el.attributes());
                treeWalk( (Element) node );
            }
            else {
                System.out.println(node.getStringValue());
            }
        }
    }

}
