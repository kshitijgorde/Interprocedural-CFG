// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class XMLReaderFactory
{
    public static XMLReader createXMLReader() throws SAXException {
        final String property = System.getProperty("org.xml.sax.driver");
        if (property != null) {
            return createXMLReader(property);
        }
        Parser parser;
        try {
            parser = ParserFactory.makeParser();
        }
        catch (Exception ex) {
            parser = null;
        }
        if (parser == null) {
            throw new SAXException("System property org.xml.sax.driver not specified");
        }
        return new ParserAdapter(parser);
    }
    
    public static XMLReader createXMLReader(final String s) throws SAXException {
        try {
            return (XMLReader)Class.forName(s).newInstance();
        }
        catch (ClassNotFoundException ex) {
            throw new SAXException("SAX2 driver class " + s + " not found", ex);
        }
        catch (IllegalAccessException ex2) {
            throw new SAXException("SAX2 driver class " + s + " found but cannot be loaded", ex2);
        }
        catch (InstantiationException ex3) {
            throw new SAXException("SAX2 driver class " + s + " loaded but cannot be instantiated (no empty public constructor?)", ex3);
        }
        catch (ClassCastException ex4) {
            throw new SAXException("SAX2 driver class " + s + " does not implement XMLReader", ex4);
        }
    }
}
