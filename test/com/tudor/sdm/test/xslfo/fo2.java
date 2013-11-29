package com.tudor.sdm.test.xslfo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.testng.annotations.Test;

public class fo2 {

	@Test
	public void fotest() throws FOPException, TransformerException, IOException{
		FopFactory fopFactory = FopFactory.newInstance();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("myfile.pdf")));
		
		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
			
			File xsltfile = new File(getClass().getResource( "HelloWorld.xsl").getFile());
			File xmlFile = new File(getClass().getResource("Hello.xml").getFile());
			StreamSource transformationSource = new StreamSource(xsltfile);
			StreamSource dataSource = new StreamSource(xmlFile);
			
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(transformationSource);
			
			Result res = new SAXResult(fop.getDefaultHandler());
			
			transformer.transform(dataSource, res);
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
}
