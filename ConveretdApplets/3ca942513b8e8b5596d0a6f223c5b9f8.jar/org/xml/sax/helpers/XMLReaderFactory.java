// 
// Decompiled by Procyon v0.5.30
// 

package org.xml.sax.helpers;

import java.io.InputStream;
import org.xml.sax.SAXException;
import java.io.UnsupportedEncodingException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.xml.sax.XMLReader;

public final class XMLReaderFactory
{
    private static final String property = "org.xml.sax.driver";
    static /* synthetic */ Class class$org$xml$sax$helpers$XMLReaderFactory;
    
    public static XMLReader createXMLReader() throws SAXException {
        String s = null;
        final SecuritySupport instance = SecuritySupport.getInstance();
        final ClassLoader classLoader = NewInstance.getClassLoader();
        try {
            s = instance.getSystemProperty("org.xml.sax.driver");
        }
        catch (Exception ex) {}
        if (s == null) {
            final String s2 = "META-INF/services/org.xml.sax.driver";
            final ClassLoader contextClassLoader = instance.getContextClassLoader();
            InputStream inputStream;
            if (contextClassLoader != null) {
                inputStream = instance.getResourceAsStream(contextClassLoader, s2);
                if (inputStream == null) {
                    inputStream = instance.getResourceAsStream(((XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory == null) ? (XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory = class$("org.xml.sax.helpers.XMLReaderFactory")) : XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory).getClassLoader(), s2);
                }
            }
            else {
                inputStream = instance.getResourceAsStream(((XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory == null) ? (XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory = class$("org.xml.sax.helpers.XMLReaderFactory")) : XMLReaderFactory.class$org$xml$sax$helpers$XMLReaderFactory).getClassLoader(), s2);
            }
            if (inputStream != null) {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                }
                catch (UnsupportedEncodingException ex2) {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                }
                try {
                    s = bufferedReader.readLine();
                    bufferedReader.close();
                }
                catch (Exception ex3) {}
            }
        }
        if (s == null) {
            s = "org.apache.xerces.parsers.SAXParser";
        }
        if (s != null) {
            return loadClass(classLoader, s);
        }
        try {
            return new ParserAdapter(ParserFactory.makeParser());
        }
        catch (Exception ex4) {
            throw new SAXException("Can't create default XMLReader; is system property org.xml.sax.driver set?");
        }
    }
    
    public static XMLReader createXMLReader(final String s) throws SAXException {
        return loadClass(NewInstance.getClassLoader(), s);
    }
    
    private static XMLReader loadClass(final ClassLoader classLoader, final String s) throws SAXException {
        try {
            return (XMLReader)NewInstance.newInstance(classLoader, s);
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
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
