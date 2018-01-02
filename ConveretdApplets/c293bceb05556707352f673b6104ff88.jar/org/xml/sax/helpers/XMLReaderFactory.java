// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import org.xml.sax.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class XMLReaderFactory
{
    public static synchronized XMLReader createXMLReader() throws SAXException {
        final String className = System.getProperty("org.xml.sax.driver");
        if (className != null) {
            return createXMLReader(className);
        }
        Parser parser;
        try {
            parser = ParserFactory.makeParser();
        }
        catch (Exception e) {
            parser = null;
        }
        if (parser == null) {
            throw new SAXException("System property org.xml.sax.driver not specified");
        }
        return new ParserAdapter(parser);
    }
    
    public static synchronized XMLReader createXMLReader(final String className) throws SAXException {
        try {
            return (XMLReader)Class.forName(className).newInstance();
        }
        catch (ClassNotFoundException e1) {
            throw new SAXException("SAX2 driver class " + className + " not found", e1);
        }
        catch (IllegalAccessException e2) {
            throw new SAXException("SAX2 driver class " + className + " found but cannot be loaded", e2);
        }
        catch (InstantiationException e3) {
            throw new SAXException("SAX2 driver class " + className + " loaded but cannot be instantiated (no empty public constructor?)", e3);
        }
        catch (ClassCastException e4) {
            throw new SAXException("SAX2 driver class " + className + " does not implement XMLReader", e4);
        }
    }
}
