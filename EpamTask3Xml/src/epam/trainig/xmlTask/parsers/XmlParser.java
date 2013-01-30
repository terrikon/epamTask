package epam.trainig.xmlTask.parsers;

import epam.trainig.xmlTask.convertedFromXsd.Medicine;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

/**
 * Abstract class which every XML Parser should extends.
 *
 * @author dima
 */

public abstract class XmlParser {
    /**
     * Parses XML file and puts gotten information to appropriate java class
     *
     * @param xmlSource string URL to XML file that parses
     */
    abstract public void parseXML(String xmlSource);

    /**
     * @return instance of object Medicine which has all information from XML
     *         document that was parsed
     */
    abstract public Medicine getResult();

    /**
     * Checks is xml file appropriate to xsd schema or not
     *
     * @param xmlSourse    string URL to XML file that checks
     * @param schemaSource string URL to xsd file based on which XML file is checking
     * @return true if xml file is appropriate to xsd schema
     */
    public boolean checkXML(String xmlSourse, File schemaSource) {
         boolean result = false;
         try {
             SchemaFactory sf = SchemaFactory
                     .newInstance("http://www.w3.org/2001/XMLSchema");

             Schema schema = sf.newSchema(schemaSource);
             Validator validator = schema.newValidator();
             Source source = new StreamSource(xmlSourse);
             try {
                 validator.validate(source);
                 result = true;
             } catch (SAXException ex) {
                 ex.printStackTrace();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return result;
     }
    //в это методе лучше так исключения ловить и catch (Exception e) - дурной тон, лучше указывать конкретное исключение, которое ты собираешься ловить

    /*public boolean checkXML(String xmlSourse, File schemaSource) {
        boolean result = false;
        try {
            SchemaFactory sf = SchemaFactory
                .newInstance("http://www.w3.org/2001/XMLSchema");

            Schema schema = sf.newSchema(schemaSource);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlSourse);

            validator.validate(source);
            result = true;
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }*/
}