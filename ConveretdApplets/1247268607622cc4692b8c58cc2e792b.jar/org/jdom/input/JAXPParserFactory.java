// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.input;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import javax.xml.parsers.SAXParser;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom.JDOMException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;
import java.util.Map;

class JAXPParserFactory
{
    private static final String CVS_ID = "@(#) $RCSfile: JAXPParserFactory.java,v $ $Revision: 1.6 $ $Date: 2007/11/10 05:29:00 $ $Name: jdom_1_1_1 $";
    private static final String JAXP_SCHEMA_LANGUAGE_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String JAXP_SCHEMA_LOCATION_PROPERTY = "http://java.sun.com/xml/jaxp/properties/schemaSource";
    
    public static XMLReader createParser(final boolean validating, final Map features, final Map properties) throws JDOMException {
        try {
            SAXParser parser = null;
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(validating);
            factory.setNamespaceAware(true);
            try {
                parser = factory.newSAXParser();
            }
            catch (ParserConfigurationException e) {
                throw new JDOMException("Could not allocate JAXP SAX Parser", e);
            }
            setProperty(parser, properties, "http://java.sun.com/xml/jaxp/properties/schemaLanguage");
            setProperty(parser, properties, "http://java.sun.com/xml/jaxp/properties/schemaSource");
            return parser.getXMLReader();
        }
        catch (SAXException e2) {
            throw new JDOMException("Could not allocate JAXP SAX Parser", e2);
        }
    }
    
    private static void setProperty(final SAXParser parser, final Map properties, final String name) throws JDOMException {
        try {
            if (properties.containsKey(name)) {
                parser.setProperty(name, properties.get(name));
            }
        }
        catch (SAXNotSupportedException e) {
            throw new JDOMException(name + " property not supported for JAXP parser " + parser.getClass().getName());
        }
        catch (SAXNotRecognizedException e2) {
            throw new JDOMException(name + " property not recognized for JAXP parser " + parser.getClass().getName());
        }
    }
}
