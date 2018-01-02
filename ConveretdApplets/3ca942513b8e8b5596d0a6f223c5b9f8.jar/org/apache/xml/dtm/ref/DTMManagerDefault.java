// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xml.dtm.DTMFilter;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.xml.utils.SuballocatedIntVector;
import org.xml.sax.SAXException;
import org.apache.xml.dtm.ref.dom2dtm.DOM2DTMdefaultNamespaceDeclarationNode;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.apache.xml.utils.XMLStringFactory;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.DTDHandler;
import org.xml.sax.ErrorHandler;
import org.apache.xml.dtm.ref.sax2dtm.SAX2DTM;
import org.apache.xml.dtm.ref.sax2dtm.SAX2RTFDTM;
import org.apache.xml.utils.SystemIDResolver;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.sax.SAXSource;
import org.apache.xml.dtm.ref.dom2dtm.DOM2DTM;
import javax.xml.transform.dom.DOMSource;
import org.apache.xml.dtm.DTMWSFilter;
import javax.xml.transform.Source;
import org.apache.xml.dtm.DTMException;
import org.apache.xml.res.XMLMessages;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.xml.utils.XMLReaderManager;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMManager;

public class DTMManagerDefault extends DTMManager
{
    private static final boolean DUMPTREE = false;
    private static final boolean DEBUG = false;
    protected DTM[] m_dtms;
    int[] m_dtm_offsets;
    protected XMLReaderManager m_readerManager;
    protected DefaultHandler m_defaultHandler;
    private ExpandedNameTable m_expandedNameTable;
    
    public synchronized void addDTM(final DTM dtm, final int id) {
        this.addDTM(dtm, id, 0);
    }
    
    public synchronized void addDTM(final DTM dtm, final int id, final int offset) {
        if (id >= 65536) {
            throw new DTMException(XMLMessages.createXMLMessage("ER_NO_DTMIDS_AVAIL", null));
        }
        final int oldlen = this.m_dtms.length;
        if (oldlen <= id) {
            final int newlen = Math.min(id + 256, 65536);
            final DTM[] new_m_dtms = new DTM[newlen];
            System.arraycopy(this.m_dtms, 0, new_m_dtms, 0, oldlen);
            this.m_dtms = new_m_dtms;
            final int[] new_m_dtm_offsets = new int[newlen];
            System.arraycopy(this.m_dtm_offsets, 0, new_m_dtm_offsets, 0, oldlen);
            this.m_dtm_offsets = new_m_dtm_offsets;
        }
        this.m_dtms[id] = dtm;
        this.m_dtm_offsets[id] = offset;
        dtm.documentRegistration();
    }
    
    public synchronized int getFirstFreeDTMID() {
        final int n = this.m_dtms.length;
        for (int i = 1; i < n; ++i) {
            if (null == this.m_dtms[i]) {
                return i;
            }
        }
        return n;
    }
    
    public DTMManagerDefault() {
        this.m_dtms = new DTM[256];
        this.m_dtm_offsets = new int[256];
        this.m_readerManager = null;
        this.m_defaultHandler = new DefaultHandler();
        this.m_expandedNameTable = new ExpandedNameTable();
    }
    
    public synchronized DTM getDTM(final Source source, final boolean unique, final DTMWSFilter whiteSpaceFilter, boolean incremental, final boolean doIndexing) {
        final XMLStringFactory xstringFactory = super.m_xsf;
        final int dtmPos = this.getFirstFreeDTMID();
        final int documentID = dtmPos << 16;
        if (null != source && source instanceof DOMSource) {
            final DOM2DTM dtm = new DOM2DTM(this, (DOMSource)source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
            this.addDTM(dtm, dtmPos, 0);
            return dtm;
        }
        final boolean isSAXSource = null == source || source instanceof SAXSource;
        final boolean isStreamSource = null != source && source instanceof StreamSource;
        if (isSAXSource || isStreamSource) {
            XMLReader reader = null;
            try {
                InputSource xmlSource;
                if (null == source) {
                    xmlSource = null;
                }
                else {
                    reader = this.getXMLReader(source);
                    xmlSource = SAXSource.sourceToInputSource(source);
                    String urlOfSource = xmlSource.getSystemId();
                    if (null != urlOfSource) {
                        try {
                            urlOfSource = SystemIDResolver.getAbsoluteURI(urlOfSource);
                        }
                        catch (Exception e3) {
                            System.err.println("Can not absolutize URL: " + urlOfSource);
                        }
                        xmlSource.setSystemId(urlOfSource);
                    }
                }
                SAX2DTM dtm2;
                if (source == null && unique && !incremental && !doIndexing) {
                    dtm2 = new SAX2RTFDTM(this, source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
                }
                else {
                    dtm2 = new SAX2DTM(this, source, documentID, whiteSpaceFilter, xstringFactory, doIndexing);
                }
                this.addDTM(dtm2, dtmPos, 0);
                final boolean haveXercesParser = null != reader && reader.getClass().getName().equals("org.apache.xerces.parsers.SAXParser");
                if (haveXercesParser) {
                    incremental = true;
                }
                if (super.m_incremental && incremental) {
                    IncrementalSAXSource coParser = null;
                    if (haveXercesParser) {
                        try {
                            coParser = (IncrementalSAXSource)Class.forName("org.apache.xml.dtm.ref.IncrementalSAXSource_Xerces").newInstance();
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            coParser = null;
                        }
                    }
                    if (coParser == null) {
                        if (null == reader) {
                            coParser = new IncrementalSAXSource_Filter();
                        }
                        else {
                            final IncrementalSAXSource_Filter filter = new IncrementalSAXSource_Filter();
                            filter.setXMLReader(reader);
                            coParser = filter;
                        }
                    }
                    dtm2.setIncrementalSAXSource(coParser);
                    if (null == xmlSource) {
                        return dtm2;
                    }
                    if (null == reader.getErrorHandler()) {
                        reader.setErrorHandler(dtm2);
                    }
                    reader.setDTDHandler(dtm2);
                    try {
                        coParser.startParse(xmlSource);
                    }
                    catch (RuntimeException re) {
                        dtm2.clearCoRoutine();
                        throw re;
                    }
                    catch (Exception e) {
                        dtm2.clearCoRoutine();
                        throw new WrappedRuntimeException(e);
                    }
                }
                else {
                    if (null == reader) {
                        return dtm2;
                    }
                    reader.setContentHandler(dtm2);
                    reader.setDTDHandler(dtm2);
                    if (null == reader.getErrorHandler()) {
                        reader.setErrorHandler(dtm2);
                    }
                    try {
                        reader.setProperty("http://xml.org/sax/properties/lexical-handler", dtm2);
                    }
                    catch (SAXNotRecognizedException e4) {}
                    catch (SAXNotSupportedException ex2) {}
                    try {
                        reader.parse(xmlSource);
                    }
                    catch (RuntimeException re2) {
                        dtm2.clearCoRoutine();
                        throw re2;
                    }
                    catch (Exception e2) {
                        dtm2.clearCoRoutine();
                        throw new WrappedRuntimeException(e2);
                    }
                }
                return dtm2;
            }
            finally {
                if (reader != null && (!super.m_incremental || !incremental)) {
                    reader.setContentHandler(this.m_defaultHandler);
                    reader.setDTDHandler(this.m_defaultHandler);
                    reader.setErrorHandler(this.m_defaultHandler);
                    try {
                        reader.setProperty("http://xml.org/sax/properties/lexical-handler", null);
                    }
                    catch (Exception ex3) {}
                }
                this.releaseXMLReader(reader);
            }
        }
        throw new DTMException(XMLMessages.createXMLMessage("ER_NOT_SUPPORTED", new Object[] { source }));
    }
    
    public synchronized int getDTMHandleFromNode(final Node node) {
        if (null == node) {
            throw new IllegalArgumentException(XMLMessages.createXMLMessage("ER_NODE_NON_NULL", null));
        }
        if (node instanceof DTMNodeProxy) {
            return ((DTMNodeProxy)node).getDTMNodeNumber();
        }
        for (int max = this.m_dtms.length, i = 0; i < max; ++i) {
            final DTM thisDTM = this.m_dtms[i];
            if (null != thisDTM && thisDTM instanceof DOM2DTM) {
                final int handle = ((DOM2DTM)thisDTM).getHandleOfNode(node);
                if (handle != -1) {
                    return handle;
                }
            }
        }
        Node root = node;
        for (Node p = (root.getNodeType() == 2) ? ((Attr)root).getOwnerElement() : root.getParentNode(); p != null; p = p.getParentNode()) {
            root = p;
        }
        final DOM2DTM dtm = (DOM2DTM)this.getDTM(new DOMSource(root), false, null, true, true);
        int handle2;
        if (node instanceof DOM2DTMdefaultNamespaceDeclarationNode) {
            handle2 = dtm.getHandleOfNode(((Attr)node).getOwnerElement());
            handle2 = dtm.getAttributeNode(handle2, node.getNamespaceURI(), node.getLocalName());
        }
        else {
            handle2 = dtm.getHandleOfNode(node);
        }
        if (-1 == handle2) {
            throw new RuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_RESOLVE_NODE", null));
        }
        return handle2;
    }
    
    public synchronized XMLReader getXMLReader(final Source inputSource) {
        try {
            XMLReader reader = (inputSource instanceof SAXSource) ? ((SAXSource)inputSource).getXMLReader() : null;
            if (null == reader) {
                if (this.m_readerManager == null) {
                    this.m_readerManager = XMLReaderManager.getInstance();
                }
                reader = this.m_readerManager.getXMLReader();
            }
            return reader;
        }
        catch (SAXException se) {
            throw new DTMException(se.getMessage(), se);
        }
    }
    
    public synchronized void releaseXMLReader(final XMLReader reader) {
        if (this.m_readerManager != null) {
            this.m_readerManager.releaseXMLReader(reader);
        }
    }
    
    public synchronized DTM getDTM(final int nodeHandle) {
        try {
            return this.m_dtms[nodeHandle >>> 16];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            if (nodeHandle == -1) {
                return null;
            }
            throw e;
        }
    }
    
    public synchronized int getDTMIdentity(final DTM dtm) {
        if (!(dtm instanceof DTMDefaultBase)) {
            for (int n = this.m_dtms.length, i = 0; i < n; ++i) {
                final DTM tdtm = this.m_dtms[i];
                if (tdtm == dtm && this.m_dtm_offsets[i] == 0) {
                    return i << 16;
                }
            }
            return -1;
        }
        final DTMDefaultBase dtmdb = (DTMDefaultBase)dtm;
        if (dtmdb.getManager() == this) {
            return dtmdb.getDTMIDs().elementAt(0);
        }
        return -1;
    }
    
    public synchronized boolean release(final DTM dtm, final boolean shouldHardDelete) {
        if (dtm instanceof SAX2DTM) {
            ((SAX2DTM)dtm).clearCoRoutine();
        }
        if (dtm instanceof DTMDefaultBase) {
            final SuballocatedIntVector ids = ((DTMDefaultBase)dtm).getDTMIDs();
            for (int i = ids.size() - 1; i >= 0; --i) {
                this.m_dtms[ids.elementAt(i) >>> 16] = null;
            }
        }
        else {
            final int j = this.getDTMIdentity(dtm);
            if (j >= 0) {
                this.m_dtms[j >>> 16] = null;
            }
        }
        dtm.documentRelease();
        return true;
    }
    
    public synchronized DTM createDocumentFragment() {
        try {
            final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            final DocumentBuilder db = dbf.newDocumentBuilder();
            final Document doc = db.newDocument();
            final Node df = doc.createDocumentFragment();
            return this.getDTM(new DOMSource(df), true, null, false, false);
        }
        catch (Exception e) {
            throw new DTMException(e);
        }
    }
    
    public synchronized DTMIterator createDTMIterator(final int whatToShow, final DTMFilter filter, final boolean entityReferenceExpansion) {
        return null;
    }
    
    public synchronized DTMIterator createDTMIterator(final String xpathString, final PrefixResolver presolver) {
        return null;
    }
    
    public synchronized DTMIterator createDTMIterator(final int node) {
        return null;
    }
    
    public synchronized DTMIterator createDTMIterator(final Object xpathCompiler, final int pos) {
        return null;
    }
    
    public ExpandedNameTable getExpandedNameTable(final DTM dtm) {
        return this.m_expandedNameTable;
    }
}
