// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.E;

import java.io.InputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class A extends DefaultHandler
{
    protected StringBuffer A;
    
    public A() {
        this.A = new StringBuffer();
    }
    
    public void startElement(final String s, final String s2, final String s3, final Attributes attributes) throws SAXException {
        this.A.delete(0, this.A.length());
    }
    
    public void characters(final char[] array, final int n, final int n2) throws SAXException {
        this.A.append(array, n, n2);
    }
    
    public void A(final InputSource is) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory.newInstance().newSAXParser().parse(is, this);
    }
    
    public void A(final InputStream byteStream) throws ParserConfigurationException, SAXException, IOException {
        this.A(new InputSource(byteStream));
    }
    
    public String A() {
        return this.A.toString();
    }
}
