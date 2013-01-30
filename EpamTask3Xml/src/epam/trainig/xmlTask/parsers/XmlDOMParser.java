package epam.trainig.xmlTask.parsers;

import epam.trainig.xmlTask.convertedFromXsd.Drug;
import epam.trainig.xmlTask.convertedFromXsd.Medicine;
import epam.trainig.xmlTask.convertedFromXsd.ObjectFactory;
import epam.trainig.xmlTask.convertedFromXsd.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Class that parses XML document using DOM Parser
 * 
 * @author dima
 * 
 */

//этот класс лучше назвать DOMXmlParser, так как его предок называется XmlParserб значит надо просто прибавить суффикс DOM
public class XmlDOMParser extends XmlParser {
	//модификатор доступа?
    Medicine currMedicine = null;

	@Override
	/**
	 * Parses XML file using SAX Parser and puts gotten information to
	 * appropriate java class
	 * 
	 * @param xmlSource
	 *            string URL to XML file that parses
	 */
	public void parseXML(String xmlSource) {
		NodeList drugList = null;
		Document doc = null;
		currMedicine = new Medicine();
		Drug currDrug = null;

		ObjectFactory ob = new ObjectFactory();
		try {

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(xmlSource);
		} catch (Exception e) {
			e.printStackTrace();
		}
		drugList = doc.getElementsByTagName("drug");

		for (int i = 0; i < drugList.getLength(); i++) {
			NodeList analogsList = null;
			NodeList versionList = null;
			currDrug = ob.createDrug();
			Element medicineElement = (Element) drugList.item(i);
			analogsList = (medicineElement.getElementsByTagName("analogs"));
			versionList = (medicineElement.getElementsByTagName("versions"));
			currDrug.setId(medicineElement.getAttribute("id"));
			currDrug.setCountry(medicineElement.getAttribute("country"));
			currDrug.setName(getBabyValue(medicineElement, "name"));
			currDrug.setPharm(getBabyValue(medicineElement, "pharm"));
			currDrug.setGroup(getBabyValue(medicineElement, "group"));

			for (int j = 0; j < analogsList.getLength(); j++)
				currDrug.getAnalogs().add(analogsList.item(j).getTextContent());

			for (int k = 0; k < versionList.getLength(); k++) {
				Version currVersion = ob.createVersion();

				Element versionElement = (Element) versionList.item(k);
				currVersion.setNumber(versionElement.getAttribute("number"));
				currVersion.setConsistence(versionElement
						.getAttribute("consistence"));
				currVersion.setPharm(versionElement.getAttribute("pharm"));
				currDrug.getVersions().add(currVersion);
				// Set Certificate
				Version.Certificate currCertificate = ob
						.createVersionCertificate();
				currCertificate.setNumber(getBabyAttrValue(versionElement,
						"certificate", "number"));
				currCertificate.setExpdate(getBabyAttrValue(versionElement,
						"certificate", "expdate"));
				currCertificate.setIssuedate(getBabyAttrValue(versionElement,
						"certificate", "issuedate"));
				currCertificate.setRegOrganization(getBabyAttrValue(
						versionElement, "certificate", "reg_organization"));
				currVersion.setCertificate(currCertificate);
				// Set Package
				Version.Package currPackage = ob.createVersionPackage();
				currPackage.setAmount(getBabyAttrValue(versionElement,
						"package", "amount"));
				currPackage.setTop(getBabyAttrValue(versionElement, "package",
						"top"));
				currPackage.setPrice(getBabyAttrValue(versionElement,
						"package", "price"));
				currVersion.setPackage(currPackage);
				// Set Dosage
				Version.Dosage currDosage = ob.createVersionDosage();
				currDosage.setValue(getBabyAttrValue(versionElement, "dosage",
						"value"));
				currDosage.setPeriod(getBabyAttrValue(versionElement, "dosage",
						"period"));
				currVersion.setDosage(currDosage);
			}
			currMedicine.addDrug(currDrug);

		}
	}

	/**
	 * Gives child Element based on its name and parent Element. 
	 * @param parent
	 *            Parant of Element that we need
	 * @param childName
	 *            Name of child Element that we need
	 * @return child Element based on its name and parent Element
	 */
	private static Element getBaby(Element parent, String childName) {
		NodeList nlist = parent.getElementsByTagName(childName);
        //лишняя локальная переменная, можно просто return (Element) nlist.item(0);
        Element child = (Element) nlist.item(0);
		return child;
	}
	/**
	 * Gives String value of child Element based on its name and 
	 * parent Element. 
	 * @param parent
	 *            Parant of Element that we need
	 * @param childName
	 *            Name of child Element that we need
	 * @return String value of child Element based on its name and parent 
	 * Element
	 */
	private static String getBabyValue(Element parent, String childName) {
		Element child = getBaby(parent, childName);
		Node node = child.getFirstChild();

        //лишняя локальная переменная, можно просто return node.getNodeValue();
        String value = node.getNodeValue();
		return value;
	}
	/**
	 * Gives child Element based on its name and parent Element. 
	 * @param parent
	 *            Parant of Element that we need
	 * @param childName
	 *            Name of child Element that we need
	 * @return String attribute's value of child Element based on its name,
	 *  name of child Element and parent Element
	 */
	private static String getBabyAttrValue(Element parent, String childName,
			String attrName) {
		Element child = getBaby(parent, childName);
        //то же самое
		String value = child.getAttribute(attrName);
		return value;
	}
	/**
	 * Before using this method use method parseXML(String xmlSource) to get
	 * appropriate results 
	 * @return instance of object Medicine which has all information from XML
	 *         document that was parsed
	 * 
	 */
	public Medicine getResult() {
		return currMedicine;
	}
}