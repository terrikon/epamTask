package epam.trainig.xmlTask.parsers;

import epam.trainig.xmlTask.convertedFromXsd.Medicine;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Class that parses XML document using SAX Parser
 * 
 * @author dima
 * 
 */

public class XmlSAXParser extends XmlParser {

    // какой моджификатор доступа?
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

        //объявление SAXParser sp можно внести в try блок
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
