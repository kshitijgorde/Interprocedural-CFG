// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import javax.xml.parsers.FactoryConfigurationError;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.XMLReader;
import java.util.Hashtable;
import javax.xml.parsers.SAXParserFactory;

public class XMLReaderManager
{
    private static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    private static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    private static final XMLReaderManager m_singletonManager;
    private static SAXParserFactory m_parserFactory;
    private ThreadLocal m_readers;
    private Hashtable m_inUse;
    
    public static XMLReaderManager getInstance() {
        return XMLReaderManager.m_singletonManager;
    }
    
    public synchronized XMLReader getXMLReader() throws SAXException {
        if (this.m_readers == null) {
            this.m_readers = new ThreadLocal();
        }
        if (this.m_inUse == null) {
            this.m_inUse = new Hashtable();
        }
        XMLReader reader = this.m_readers.get();
        final boolean threadHasReader = reader != null;
        if (threadHasReader) {
            if (this.m_inUse.get(reader) != Boolean.TRUE) {
                this.m_inUse.put(reader, Boolean.TRUE);
                return reader;
            }
        }
        try {
            try {
                reader = XMLReaderFactory.createXMLReader();
            }
            catch (Exception e) {
                try {
                    if (XMLReaderManager.m_parserFactory == null) {
                        (XMLReaderManager.m_parserFactory = SAXParserFactory.newInstance()).setNamespaceAware(true);
                    }
                    reader = XMLReaderManager.m_parserFactory.newSAXParser().getXMLReader();
                }
                catch (ParserConfigurationException pce) {
                    throw pce;
                }
            }
            try {
                reader.setFeature("http://xml.org/sax/features/namespaces", true);
                reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
            }
            catch (SAXException ex4) {}
        }
        catch (ParserConfigurationException ex) {
            throw new SAXException(ex);
        }
        catch (FactoryConfigurationError ex2) {
            throw new SAXException(ex2.toString());
        }
        catch (NoSuchMethodError ex3) {}
        catch (AbstractMethodError abstractMethodError) {}
        if (!threadHasReader) {
            this.m_readers.set(reader);
            this.m_inUse.put(reader, Boolean.TRUE);
        }
        return reader;
    }
    
    public synchronized void releaseXMLReader(final XMLReader reader) {
        if (this.m_readers.get() == reader) {
            this.m_inUse.put(reader, Boolean.FALSE);
        }
    }
    
    static {
        m_singletonManager = new XMLReaderManager();
    }
}
