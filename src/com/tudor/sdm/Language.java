package com.tudor.sdm;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tudor.sdm.Constants.StringNames;

public class Language {
	
	private static final Logger log = Logger.getLogger(Language.class.getName());
	
	private static Language instance = new Language();
	private String locale = Constants.Defaults.LANGUAGE.toString();
	private Hashtable<String, String> dict;
	
	private Language() {
		try {
			setLocale(locale);
		} catch (IllegalArgumentException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static Language get() {
		return instance;
	}
	
	public void setLocale(String locale) throws IllegalArgumentException, SAXException, IOException, ParserConfigurationException {
		File f = new File(getClass().getResource("/lang/" + locale + ".xml").getFile());
		log.debug("Attempting to load language file from " + f.getAbsolutePath());
		if(f.exists()) {
			Hashtable<String, String> newDict = readDict(f);
			log.debug("Language file loaded successfully.");
			dict = newDict;
			this.locale = locale;
		} else {
			log.warn("Language file was not found at " + f.getAbsolutePath());
			throw new IllegalArgumentException(String.format(Constants.DefaultErrorMessages.LANG_NOT_FOUND.toString(), locale));
		}
	}
	
	public String getString(StringNames stringName) {
		return dict.get(stringName.toString()).toString();
	}
	
	public String getString(String title) {
		return dict.get(title).toString();
	}
	
	public String getLocale() {
		return locale;
	}

	private Hashtable<String, String> readDict(File f) throws SAXException, IOException, ParserConfigurationException {
		Hashtable<String, String> newDict = new Hashtable<>();
		DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = dBuilder.parse(f);
		NodeList strings = doc.getElementsByTagName("string");
		if(strings != null) {
			for(int i=0; i < strings.getLength(); i++) {
				Node node = strings.item(i);
				newDict.put(node.getAttributes().getNamedItem("name").getNodeValue(), node.getTextContent());
			}
		}
		return newDict;
	}
	
}
