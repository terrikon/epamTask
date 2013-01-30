package epam.trainig.xmlTask.parsers;

import java.io.File;

/**
 * Class which prints in console results of XMLParser instance
 * 
 * @author dima
 * 
 */

public class ParserRunner {
	public static void main(String[] args) {
		XmlParser sax = new XmlSAXParser();
		XmlParser dom = new XmlDOMParser();

		File schema = new File(
				"/home/dima/workspace/EpamTask3Xml/src/epam/trainig/"
						+ "xmlTask/xmlFiles/Medicine.xsd");

		String xmlURL = "/home/dima/workspace/EpamTask3Xml/src/epam/trainig/"
				+ "xmlTask/xmlFiles/Medicine.xml";
		boolean check = false;

		// Using DOM parser
		/*
		 * check = dom.checkXML(xmlURL, schema); if (check == true) {
		 * dom.parseXML(xmlURL); } else { System.out.println("\nXML  " + xmlURL
		 * + "  is not valid"); }
		 * System.out.println("Using DOM parser"+"\n"+dom.getResult());
		 */

		// Using SAX parser
		check = sax.checkXML(xmlURL, schema);
		if (check == true) {
			sax.parseXML(xmlURL);
		} else {
			System.out.println("\nXML  " + xmlURL + "  is not valid");
		}
		System.out.println("Using SAX parser" + "\n" + sax.getResult());

	}
}
