// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;

public class XMLValidator
{
    public static String CHIPPER_SCHEMA;
    
    public boolean validate(final Document n) throws IOException, ParserConfigurationException {
        try {
            SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema").newSchema(new StreamSource(this.getClass().getResourceAsStream(XMLValidator.CHIPPER_SCHEMA))).newValidator().validate(new DOMSource(n));
        }
        catch (SAXException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    
    static {
        XMLValidator.CHIPPER_SCHEMA = "chipOrderSchema.xsd";
    }
}
