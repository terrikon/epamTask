package epam.trainig.xmlTask.parsers;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import epam.trainig.xmlTask.convertedFromXsd.Medicine;

/**
 * Class that parses XML document using SAX Parser
 * 
 * @author dima
 * 
 */

public class XmlSAXParser extends XmlParser {
	SAXOne saxResult = new SAXOne();

	/**
	 * Parses XML file using SAX Parser and puts gotten information to
	 * appropriate java class
	 * 
	 * @param xmlSource
	 *            string URL to XML file that parses
	 */
	public void parseXML(String xmlSource) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser sp = null;

		try {

			sp = spf.newSAXParser();
			sp.parse(xmlSource, saxResult);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Before using this method use method parseXML(String xmlSource) to get
	 * appropriate results
	 * @return instance of object Medicine which has all information from XML
	 *         document that was parsed
	 * 
	 */
	public Medicine getResult() {
		return saxResult.getMed();
	}
}
