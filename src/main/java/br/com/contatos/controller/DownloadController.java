package br.com.contatos.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.contatos.domain.Contato;
import br.com.contatos.service.ContatoRepository;

@CrossOrigin(origins = "http://127.0.0.1:8080")
@RestController
public class DownloadController {
	
	@Autowired
	private ContatoRepository repository;
	
	 @RequestMapping(value = "contato/download/{nome}", method = RequestMethod.GET)
	    public void getFile(@PathVariable(name="nome") String nome,HttpServletResponse response) throws IOException {
		 Contato contato = repository.findContactByNome(nome);
		 ByteArrayOutputStream os = new ByteArrayOutputStream();
		   try {
	            JAXBContext context = JAXBContext.newInstance(Contato.class);
	            Marshaller m = context.createMarshaller();
	            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	            m.marshal(contato,os);
	        } catch (JAXBException e) {
	            e.printStackTrace();
	        }
		 
	
		 
		 response.setContentType("application/force-download");
		 response.setContentLength((int)os.size());
		 response.setHeader("Content-Disposition", "attachment; filename=" + contato.getNome() + ".xml"); 
		 response.setHeader("Content-Transfer-Encoding", "application/xml");

	            IOUtils.copy(new ByteArrayInputStream(os.toByteArray()), response.getOutputStream());
	            response.flushBuffer();
	    }

}
