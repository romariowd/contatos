package br.com.contatos.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController {
	
	 @RequestMapping(value = "/download", method = RequestMethod.GET)
	    public void getFile(HttpServletResponse response) throws IOException {
		 java.io.File f = new java.io.File("C:\\Users\\Romario\\Desktop\\contatosjabx.xml");
		 response.setContentType("application/force-download");
		 response.setContentLength((int)f.length());
		 response.setHeader("Content-Disposition", "attachment; filename=" + f.getName()); 
		 response.setHeader("Content-Transfer-Encoding", "application/xml");
		 
	            String src= "C:\\Users\\Romario\\Desktop\\contatosjabx.xml";
	            InputStream is = new FileInputStream(src);
	            IOUtils.copy(is, response.getOutputStream());
	            response.flushBuffer();
	    }

}
