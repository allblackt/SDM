package com.tudor.sdm.test.xslfo;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.testng.annotations.Test;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;

public class fo1 {
	@Test
	public void testOutput() throws IOException, FOPException, TransformerException {
		// the XSL FO file
		File xsltfile = new File(getClass().getResource( "HelloWorld.xsl").getFile());
		// the XML file from which we take the name
		StreamSource source = new StreamSource(new File(getClass().getResource("Hello.xml").getFile()));
		// creation of transform source
		StreamSource transformSource = new StreamSource(xsltfile);
		// create an instance of fop factory
		FopFactory fopFactory = FopFactory.newInstance();
		// a user agent is needed for transformation
		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		// to store output
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		Transformer xslfoTransformer;
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			
			xslfoTransformer = factory.newTransformer(transformSource); //getTransformer(transformSource);
			// Construct fop with desired output format
			Fop fop;
			try {
				fop = fopFactory.newFop("", foUserAgent,
						outStream);
				// Resulting SAX events (the generated FO)
				// must be piped through to FOP
				Result res = new SAXResult(fop.getDefaultHandler());

				// Start XSLT transformation and FOP processing
				try {
					// everything will happen here..
					xslfoTransformer.transform(source, res);
					// if you want to get the PDF bytes, use the following code
					// return outStream.toByteArray();

					// if you want to save PDF file use the following code
					
					 File pdffile = new File("Result.pdf");
					 OutputStream out = new FileOutputStream(pdffile); 
					 out = new BufferedOutputStream(out);
					 FileOutputStream str = new FileOutputStream(pdffile);
					 str.write(outStream.toByteArray()); str.close();
					 out.close();
					 
				} catch (TransformerException e) {
					throw e;
				}
			} catch (FOPException e) {
				throw e;
			}
		} catch (TransformerConfigurationException e) {
			throw e;
		} catch (TransformerFactoryConfigurationError e) {
			throw e;
		}
	}
	private Transformer getTransformer(StreamSource streamSource)
	{
		// setup the xslt transformer
		TransformerFactoryImpl impl = 
				new TransformerFactoryImpl();

		try {
			return impl.newTransformer(streamSource);

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

}
