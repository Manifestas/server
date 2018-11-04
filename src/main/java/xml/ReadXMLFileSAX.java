package xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReadXMLFileSAX {

    public static Object read(String xmlFIle) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            SAXHandler saxHandler = new SAXHandler();
            parser.parse(xmlFIle, saxHandler);

            return saxHandler.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
