// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm;

import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import javax.xml.transform.Source;
import org.apache.xml.res.XMLMessages;
import org.apache.xml.utils.XMLStringFactory;

public abstract class DTMManager
{
    private static final String defaultPropName = "org.apache.xml.dtm.DTMManager";
    private static String defaultClassName;
    protected XMLStringFactory m_xsf;
    public static boolean m_incremental;
    private static boolean debug;
    public static final int IDENT_DTM_NODE_BITS = 16;
    public static final int IDENT_NODE_DEFAULT = 65535;
    public static final int IDENT_DTM_DEFAULT = -65536;
    public static final int IDENT_MAX_DTMS = 65536;
    
    protected DTMManager() {
        this.m_xsf = null;
    }
    
    public XMLStringFactory getXMLStringFactory() {
        return this.m_xsf;
    }
    
    public void setXMLStringFactory(final XMLStringFactory xsf) {
        this.m_xsf = xsf;
    }
    
    public static DTMManager newInstance(final XMLStringFactory xsf) throws DTMConfigurationException {
        DTMManager factoryImpl = null;
        try {
            factoryImpl = (DTMManager)ObjectFactory.createObject("org.apache.xml.dtm.DTMManager", DTMManager.defaultClassName);
        }
        catch (ObjectFactory.ConfigurationError e) {
            throw new DTMConfigurationException(XMLMessages.createXMLMessage("ER_NO_DEFAULT_IMPL", null), e.getException());
        }
        if (factoryImpl == null) {
            throw new DTMConfigurationException(XMLMessages.createXMLMessage("ER_NO_DEFAULT_IMPL", null));
        }
        factoryImpl.setXMLStringFactory(xsf);
        return factoryImpl;
    }
    
    public abstract DTM getDTM(final Source p0, final boolean p1, final DTMWSFilter p2, final boolean p3, final boolean p4);
    
    public abstract DTM getDTM(final int p0);
    
    public abstract int getDTMHandleFromNode(final Node p0);
    
    public abstract DTM createDocumentFragment();
    
    public abstract boolean release(final DTM p0, final boolean p1);
    
    public abstract DTMIterator createDTMIterator(final Object p0, final int p1);
    
    public abstract DTMIterator createDTMIterator(final String p0, final PrefixResolver p1);
    
    public abstract DTMIterator createDTMIterator(final int p0, final DTMFilter p1, final boolean p2);
    
    public abstract DTMIterator createDTMIterator(final int p0);
    
    public static synchronized boolean getIncremental() {
        return DTMManager.m_incremental;
    }
    
    public static synchronized void setIncremental(final boolean incremental) {
        DTMManager.m_incremental = incremental;
    }
    
    public abstract int getDTMIdentity(final DTM p0);
    
    public int getDTMIdentityMask() {
        return -65536;
    }
    
    public int getNodeIdentityMask() {
        return 65535;
    }
    
    static {
        DTMManager.defaultClassName = "org.apache.xml.dtm.ref.DTMManagerDefault";
        DTMManager.m_incremental = false;
        try {
            DTMManager.debug = (System.getProperty("dtm.debug") != null);
        }
        catch (SecurityException ex) {}
    }
}
