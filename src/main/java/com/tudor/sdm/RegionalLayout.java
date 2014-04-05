package com.tudor.sdm;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tudor on 4/4/14.
 * Loads a districtLayout of districts and cities from an XML.
 */
public class RegionalLayout {
    private static RegionalLayout instance = null;

    private Map<String, List<String>> districtLayout = null;
    private List<String> countryList = null;
    private String defaultCountry;

    private static final Logger log = Logger.getLogger(RegionalLayout.class.getName());

    //TODO: softcode this
    private String locale = "ro_RO";


    private RegionalLayout() {
        try {
            initRegionalLayoutMap();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static RegionalLayout get() {
        if(instance == null) {
            instance = new RegionalLayout();
        }
        return instance;
    }

    public Map<String, List<String>> getDistrictLayout() {
        return districtLayout;
    }

    public List<String> getCountryList() {
        return countryList;
    }

    public String getDefaultCountry() {
        return defaultCountry;
    }


    private void initRegionalLayoutMap() throws ParserConfigurationException, SAXException, IOException {
        File f = new File(getClass().getResource("/lang/regionalLayout-" + locale + ".xml").getFile());
        log.debug("Attempting to load regional districtLayout file from " + f.getAbsolutePath());
        if(f.exists()) {
            readLayoutFromFile(f);
        } else {
            log.warn("Regional districtLayout file was not found at " + f.getAbsolutePath());
            throw new IllegalArgumentException(String.format(Constants.DefaultErrorMessages.LANG_NOT_FOUND.toString(), locale));
        }
    }

    private void readLayoutFromFile(File f)
            throws IOException, SAXException, ParserConfigurationException {

        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(f);

        districtLayout = readDistrictMapping(doc);
        countryList = readCountryList(doc);
        defaultCountry = readDefaultCountry(doc);
    }

    private Map<String, List<String>> readDistrictMapping(Document doc) {
        Map<String, List<String>> readLayout = new HashMap<>();
        NodeList districts = doc.getElementsByTagName("district");
        if(districts != null) {
            for(int i=0; i < districts.getLength(); i++) {
                List<String> cities = new ArrayList<>();
                Element district = (Element) districts.item(i);

                for (int j = 0; j < district.getChildNodes().getLength(); j++) {
                    Node node = district.getChildNodes().item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element city = (Element) node;
                        cities.add(city.getAttribute("name"));
                    }
                }
                readLayout.put(district.getAttribute("name"), cities);
            }
        }
        return readLayout;
    }

    private List<String> readCountryList(Document doc) throws UnsupportedEncodingException {
        List<String> readCountries = new ArrayList<>();
        Node countries = doc.getElementsByTagName("countries").item(0);
        for (String country : countries.getFirstChild().getNodeValue().split(",")) {
            readCountries.add(country.trim());
        }
        return readCountries;
    }

    private String readDefaultCountry(Document doc) throws UnsupportedEncodingException {
        String defaultCountryName = null;
        Node defaultCountry = doc.getElementsByTagName("defaultCountry").item(0);
        defaultCountryName = defaultCountry.getFirstChild().getNodeValue();
        log.debug(String.format("Found default country %s", defaultCountryName));
        return new String(defaultCountryName.trim());
    }
}
