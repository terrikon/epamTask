package epam.trainig.xmlTask.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import epam.trainig.xmlTask.convertedFromXsd.*;

/**
 * Class which contains methods and rules in which describes how to parse xml
 * document using SAX parser.
 * 
 * @author dima
 * 
 */
public class SAXOne extends DefaultHandler {
	ObjectFactory factory = new ObjectFactory();
	Medicine med = factory.createMedicine();
	Drug curr;
	Version currVersion;
	Version.Certificate currCertificate;
	Version.Package currPackage;
	Version.Dosage currDosage;
	MedicineEnum currEnum = null;

	public Medicine getMed() {
		return med;
	}

	/**
	 * Actions which are doing when start of document found.
	 */
	public void startDocument() {

	}

	/**
	 * Actions which are doing when start of tag found. For example if found tag
	 * is <drug> then creates new instance of Java Class Drug and fields fills
	 * with parsed information.
	 */
	public void startElement(String namespaceURI, String localName,
			String rawName, Attributes attrs) {
		if (rawName.equals("drug")) {
			curr = factory.createDrug();
			curr.setId(attrs.getValue("id"));
			curr.setCountry(attrs.getValue("country"));
		}

		if (rawName.equals("versions")) {
			currVersion = factory.createVersion();
			currVersion.setNumber(attrs.getValue("number"));
			currVersion.setConsistence(attrs.getValue("consistence"));
			currVersion.setPharm(attrs.getValue("pharm"));
		}
		if (rawName.equals("certificate")) {
			currCertificate = factory.createVersionCertificate();
			currCertificate.setNumber(attrs.getValue("number"));
			currCertificate.setIssuedate(attrs.getValue("issuedate"));
			currCertificate.setExpdate(attrs.getValue("expdate"));
			currCertificate.setRegOrganization(attrs
					.getValue("reg_organization"));

		}
		if (rawName.equals("package")) {
			currPackage = factory.createVersionPackage();
			currPackage.setAmount(attrs.getValue("amount"));
			currPackage.setPrice(attrs.getValue("price"));
			currPackage.setTop(attrs.getValue("top"));
		}
		if (rawName.equals("dosage")) {
			currDosage = factory.createVersionDosage();
			currDosage.setPeriod(attrs.getValue("period"));
			currDosage.setValue(attrs.getValue("value"));
		}
		if ((rawName.equals("name")) || (rawName.equals("group"))
				|| (rawName.equals("pharm")) || (rawName.equals("analogs"))) {

			currEnum = MedicineEnum.valueOf(rawName.toUpperCase());

		}

	}

	/**
	 * Actions which are doing when text content is found in XML
	 */
	public void characters(char ch[], int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currEnum == null)
			return;

		// System.out.println(s);

		switch (currEnum) {

		case NAME:
			if (length != 0)
				curr.setName(s);
			break;
		case GROUP:

			curr.setGroup(s);

			break;
		case PHARM:
			curr.setPharm(s);

			break;
		case ANALOGS:
			curr.getAnalogs().add(s);

			break;
		default:

			break;

		}
	}

	/**
	 * Actions which are doing when end of tag found. For example if found tag
	 * is </drug> then to instance of Java Class Medicine in List<Drug> adds
	 * instance of Drug object with fields filled with parsed information during
	 * methods startElement and characters.
	 */
	public void endElement(String namespaceURI, String localName, String rawName) {

		if (rawName.equals("drug")) {
			med.addDrug(curr);
		}
		if (rawName.equals("versions")) {
			curr.getVersions().add(currVersion);
		}
		if (rawName.equals("certificate")) {
			currVersion.setCertificate(currCertificate);
		}
		if (rawName.equals("package")) {
			currVersion.setPackage(currPackage);
		}
		if (rawName.equals("dosage")) {
			currVersion.setDosage(currDosage);
		}
		currEnum = null;
	}

	/**
	 * Actions which are doing when end of document found.
	 */
	public void endDocument() {

	}

}
