// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.parser;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import org.jfree.xml.util.Base64;
import org.xml.sax.SAXException;

public class Base64ReadHandler extends AbstractXmlReadHandler
{
    private String encodedObject;
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        this.encodedObject = new String(array, n, n2);
    }
    
    public Object getObject() throws XmlReaderException {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(Base64.decode(this.encodedObject.toCharArray()))).readObject();
        }
        catch (IOException ex) {
            throw new XmlReaderException("Can't read class for <" + this.getTagName() + ">", ex);
        }
        catch (ClassNotFoundException ex2) {
            throw new XmlReaderException("Class not found for <" + this.getTagName() + ">", ex2);
        }
    }
}
