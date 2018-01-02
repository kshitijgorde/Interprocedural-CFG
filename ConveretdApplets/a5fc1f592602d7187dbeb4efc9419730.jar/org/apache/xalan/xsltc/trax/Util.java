// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import java.io.Reader;
import java.io.InputStream;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.XMLReader;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import javax.xml.transform.dom.DOMSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.helpers.XMLReaderFactory;
import javax.xml.transform.sax.SAXSource;
import org.xml.sax.InputSource;
import javax.xml.transform.Source;
import org.apache.xalan.xsltc.compiler.XSLTC;

public final class Util
{
    public static String baseName(final String name) {
        return org.apache.xalan.xsltc.compiler.util.Util.baseName(name);
    }
    
    public static String noExtName(final String name) {
        return org.apache.xalan.xsltc.compiler.util.Util.noExtName(name);
    }
    
    public static String toJavaName(final String name) {
        return org.apache.xalan.xsltc.compiler.util.Util.toJavaName(name);
    }
    
    public static InputSource getInputSource(final XSLTC xsltc, final Source source) throws TransformerConfigurationException {
        InputSource input = null;
        final String systemId = source.getSystemId();
        try {
            if (source instanceof SAXSource) {
                final SAXSource sax = (SAXSource)source;
                input = sax.getInputSource();
                try {
                    XMLReader reader = sax.getXMLReader();
                    if (reader == null) {
                        try {
                            reader = XMLReaderFactory.createXMLReader();
                        }
                        catch (Exception e) {
                            try {
                                final SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                                parserFactory.setNamespaceAware(true);
                                reader = parserFactory.newSAXParser().getXMLReader();
                            }
                            catch (ParserConfigurationException pce) {
                                throw new TransformerConfigurationException("ParserConfigurationException", pce);
                            }
                        }
                    }
                    reader.setFeature("http://xml.org/sax/features/namespaces", true);
                    reader.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
                    xsltc.setXMLReader(reader);
                }
                catch (SAXNotRecognizedException snre) {
                    throw new TransformerConfigurationException("SAXNotRecognizedException ", snre);
                }
                catch (SAXNotSupportedException snse) {
                    throw new TransformerConfigurationException("SAXNotSupportedException ", snse);
                }
                catch (SAXException se) {
                    throw new TransformerConfigurationException("SAXException ", se);
                }
            }
            else if (source instanceof DOMSource) {
                final DOMSource domsrc = (DOMSource)source;
                final Document dom = (Document)domsrc.getNode();
                final DOM2SAX dom2sax = new DOM2SAX(dom);
                xsltc.setXMLReader(dom2sax);
                input = SAXSource.sourceToInputSource(source);
                if (input == null) {
                    input = new InputSource(domsrc.getSystemId());
                }
            }
            else {
                if (!(source instanceof StreamSource)) {
                    final ErrorMsg err = new ErrorMsg("JAXP_UNKNOWN_SOURCE_ERR");
                    throw new TransformerConfigurationException(err.toString());
                }
                final StreamSource stream = (StreamSource)source;
                final InputStream istream = stream.getInputStream();
                final Reader reader2 = stream.getReader();
                if (istream != null) {
                    input = new InputSource(istream);
                }
                else if (reader2 != null) {
                    input = new InputSource(reader2);
                }
                else {
                    input = new InputSource(systemId);
                }
            }
            input.setSystemId(systemId);
        }
        catch (NullPointerException e2) {
            final ErrorMsg err2 = new ErrorMsg("JAXP_NO_SOURCE_ERR", "TransformerFactory.newTemplates()");
            throw new TransformerConfigurationException(err2.toString());
        }
        catch (SecurityException e3) {
            final ErrorMsg err3 = new ErrorMsg("FILE_ACCESS_ERR", systemId);
            throw new TransformerConfigurationException(err3.toString());
        }
        return input;
    }
}
