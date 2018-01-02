// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.dom;

import org.xml.sax.XMLReader;
import org.xml.sax.InputSource;
import org.w3c.dom.Node;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.DTDHandler;
import org.apache.xml.utils.SystemIDResolver;
import org.apache.xml.dtm.DTMException;
import org.apache.xml.res.XMLMessages;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXSource;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.XMLStringFactory;
import org.apache.xalan.xsltc.trax.DOM2SAX;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.ref.DTMManagerDefault;

public class XSLTCDTMManager extends DTMManagerDefault
{
    private static final String DEFAULT_CLASS_NAME = "org.apache.xalan.xsltc.dom.XSLTCDTMManager";
    private static final String DEFAULT_PROP_NAME = "org.apache.xalan.xsltc.dom.XSLTCDTMManager";
    private static final boolean DUMPTREE = false;
    private static final boolean DEBUG = false;
    static /* synthetic */ Class class$org$apache$xalan$xsltc$dom$XSLTCDTMManager;
    
    public static XSLTCDTMManager newInstance() {
        return new XSLTCDTMManager();
    }
    
    public static Class getDTMManagerClass() {
        final Class mgrClass = ObjectFactory.lookUpFactoryClass("org.apache.xalan.xsltc.dom.XSLTCDTMManager", null, "org.apache.xalan.xsltc.dom.XSLTCDTMManager");
        return (mgrClass != null) ? mgrClass : ((XSLTCDTMManager.class$org$apache$xalan$xsltc$dom$XSLTCDTMManager == null) ? (XSLTCDTMManager.class$org$apache$xalan$xsltc$dom$XSLTCDTMManager = class$("org.apache.xalan.xsltc.dom.XSLTCDTMManager")) : XSLTCDTMManager.class$org$apache$xalan$xsltc$dom$XSLTCDTMManager);
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing) {
        return this.getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, true, false);
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing, final boolean buildIdIndex) {
        return this.getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, buildIdIndex, false);
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing, final boolean buildIdIndex, final boolean newNameTable) {
        return this.getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, false, 0, buildIdIndex, newNameTable);
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing, final boolean hasUserReader, final int size, final boolean buildIdIndex) {
        return this.getDTM(source, unique, whiteSpaceFilter, incremental, doIndexing, hasUserReader, size, buildIdIndex, false);
    }
    
    public DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, final boolean incremental, final boolean doIndexing, boolean hasUserReader, final int size, final boolean buildIdIndex, final boolean newNameTable) {
        final int dtmPos = this.getFirstFreeDTMID();
        final int documentID = dtmPos << 16;
        if (null != source && source instanceof DOMSource) {
            final DOMSource domsrc = (DOMSource)source;
            final Node node = domsrc.getNode();
            final DOM2SAX dom2sax = new DOM2SAX(node);
            SAXImpl dtm;
            if (size <= 0) {
                dtm = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, 512, buildIdIndex, newNameTable);
            }
            else {
                dtm = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, size, buildIdIndex, newNameTable);
            }
            dtm.setDocumentURI(source.getSystemId());
            this.addDTM(dtm, dtmPos, 0);
            dom2sax.setContentHandler(dtm);
            try {
                dom2sax.parse();
            }
            catch (RuntimeException re) {
                throw re;
            }
            catch (Exception e) {
                throw new WrappedRuntimeException(e);
            }
            return dtm;
        }
        final boolean isSAXSource = null == source || source instanceof SAXSource;
        final boolean isStreamSource = null != source && source instanceof StreamSource;
        if (!isSAXSource && !isStreamSource) {
            throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[] { source }));
        }
        InputSource xmlSource;
        XMLReader reader;
        if (null == source) {
            xmlSource = null;
            reader = null;
            hasUserReader = false;
        }
        else {
            reader = this.getXMLReader(source);
            xmlSource = SAXSource.sourceToInputSource(source);
            String urlOfSource = xmlSource.getSystemId();
            if (null != urlOfSource) {
                try {
                    urlOfSource = SystemIDResolver.getAbsoluteURI(urlOfSource);
                }
                catch (Exception e) {
                    System.err.println("Can not absolutize URL: " + urlOfSource);
                }
                xmlSource.setSystemId(urlOfSource);
            }
        }
        SAXImpl dtm2;
        if (size <= 0) {
            dtm2 = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, 512, buildIdIndex, newNameTable);
        }
        else {
            dtm2 = new SAXImpl(this, source, documentID, whiteSpaceFilter, null, doIndexing, size, buildIdIndex, newNameTable);
        }
        this.addDTM(dtm2, dtmPos, 0);
        if (null == reader) {
            return dtm2;
        }
        reader.setContentHandler(dtm2.getBuilder());
        if (!hasUserReader || null == reader.getDTDHandler()) {
            reader.setDTDHandler(dtm2);
        }
        if (!hasUserReader || null == reader.getErrorHandler()) {
            reader.setErrorHandler(dtm2);
        }
        try {
            reader.setProperty("http://xml.org/sax/properties/lexical-handler", dtm2);
        }
        catch (SAXNotRecognizedException e3) {}
        catch (SAXNotSupportedException ex) {}
        try {
            reader.parse(xmlSource);
        }
        catch (RuntimeException re2) {
            throw re2;
        }
        catch (Exception e2) {
            throw new WrappedRuntimeException(e2);
        }
        finally {
            if (!hasUserReader) {
                this.releaseXMLReader(reader);
            }
        }
        return dtm2;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
