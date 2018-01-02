// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.util.Enumeration;
import org.apache.xpath.XPathContext;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.utils.PrefixResolver;
import org.xml.sax.SAXException;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.StringVector;
import java.util.Vector;

public class ElemLiteralResult extends ElemUse
{
    private boolean isLiteralResultAsStylesheet;
    private Vector m_avts;
    private Vector m_xslAttr;
    private String m_namespace;
    private String m_localName;
    private String m_rawName;
    private StringVector m_ExtensionElementURIs;
    private String m_version;
    private StringVector m_excludeResultPrefixes;
    
    public ElemLiteralResult() {
        this.isLiteralResultAsStylesheet = false;
        this.m_avts = null;
        this.m_xslAttr = null;
    }
    
    public void setIsLiteralResultAsStylesheet(final boolean b) {
        this.isLiteralResultAsStylesheet = b;
    }
    
    public boolean getIsLiteralResultAsStylesheet() {
        return this.isLiteralResultAsStylesheet;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                avt.fixupVariables(vnames, cstate.getGlobalsSize());
            }
        }
    }
    
    public void addLiteralResultAttribute(final AVT avt) {
        if (null == this.m_avts) {
            this.m_avts = new Vector();
        }
        this.m_avts.addElement(avt);
    }
    
    public void addLiteralResultAttribute(final String att) {
        if (null == this.m_xslAttr) {
            this.m_xslAttr = new Vector();
        }
        this.m_xslAttr.addElement(att);
    }
    
    public void setXmlSpace(final AVT avt) {
        this.addLiteralResultAttribute(avt);
        final String val = avt.getSimpleString();
        if (val.equals("default")) {
            super.setXmlSpace(2);
        }
        else if (val.equals("preserve")) {
            super.setXmlSpace(1);
        }
    }
    
    public AVT getLiteralResultAttribute(final String name) {
        if (null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                if (avt.getRawName().equals(name)) {
                    return avt;
                }
            }
        }
        return null;
    }
    
    public boolean containsExcludeResultPrefix(String prefix, final String uri) {
        if (uri == null || (null == this.m_excludeResultPrefixes && null == this.m_ExtensionElementURIs)) {
            return super.containsExcludeResultPrefix(prefix, uri);
        }
        if (prefix.length() == 0) {
            prefix = "#default";
        }
        if (this.m_excludeResultPrefixes != null) {
            for (int i = 0; i < this.m_excludeResultPrefixes.size(); ++i) {
                if (uri.equals(this.getNamespaceForPrefix(this.m_excludeResultPrefixes.elementAt(i)))) {
                    return true;
                }
            }
        }
        return (this.m_ExtensionElementURIs != null && this.m_ExtensionElementURIs.contains(uri)) || super.containsExcludeResultPrefix(prefix, uri);
    }
    
    public void resolvePrefixTables() throws TransformerException {
        super.resolvePrefixTables();
        final StylesheetRoot stylesheet = this.getStylesheetRoot();
        if (null != this.m_namespace && this.m_namespace.length() > 0) {
            final NamespaceAlias nsa = stylesheet.getNamespaceAliasComposed(this.m_namespace);
            if (null != nsa) {
                this.m_namespace = nsa.getResultNamespace();
                final String resultPrefix = nsa.getStylesheetPrefix();
                if (null != resultPrefix && resultPrefix.length() > 0) {
                    this.m_rawName = resultPrefix + ":" + this.m_localName;
                }
                else {
                    this.m_rawName = this.m_localName;
                }
            }
        }
        if (null != this.m_avts) {
            for (int n = this.m_avts.size(), i = 0; i < n; ++i) {
                final AVT avt = this.m_avts.elementAt(i);
                final String ns = avt.getURI();
                if (null != ns && ns.length() > 0) {
                    final NamespaceAlias nsa2 = stylesheet.getNamespaceAliasComposed(this.m_namespace);
                    if (null != nsa2) {
                        final String namespace = nsa2.getResultNamespace();
                        final String resultPrefix2 = nsa2.getStylesheetPrefix();
                        String rawName = avt.getName();
                        if (null != resultPrefix2 && resultPrefix2.length() > 0) {
                            rawName = resultPrefix2 + ":" + rawName;
                        }
                        avt.setURI(namespace);
                        avt.setRawName(rawName);
                    }
                }
            }
        }
    }
    
    boolean needToCheckExclude() {
        if (null == this.m_excludeResultPrefixes && null == super.m_prefixTable && this.m_ExtensionElementURIs == null) {
            return false;
        }
        if (null == super.m_prefixTable) {
            super.m_prefixTable = new Vector();
        }
        return true;
    }
    
    public void setNamespace(String ns) {
        if (null == ns) {
            ns = "";
        }
        this.m_namespace = ns;
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public void setLocalName(final String localName) {
        this.m_localName = localName;
    }
    
    public String getLocalName() {
        return this.m_localName;
    }
    
    public void setRawName(final String rawName) {
        this.m_rawName = rawName;
    }
    
    public String getRawName() {
        return this.m_rawName;
    }
    
    public String getPrefix() {
        final int len = this.m_rawName.length() - this.m_localName.length() - 1;
        return (len > 0) ? this.m_rawName.substring(0, len) : "";
    }
    
    public void setExtensionElementPrefixes(final StringVector v) {
        this.m_ExtensionElementURIs = v;
    }
    
    public String getExtensionElementPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (null == this.m_ExtensionElementURIs) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExtensionElementURIs.elementAt(i);
    }
    
    public int getExtensionElementPrefixCount() {
        return (null != this.m_ExtensionElementURIs) ? this.m_ExtensionElementURIs.size() : 0;
    }
    
    public boolean containsExtensionElementURI(final String uri) {
        return null != this.m_ExtensionElementURIs && this.m_ExtensionElementURIs.contains(uri);
    }
    
    public int getXSLToken() {
        return 77;
    }
    
    public String getNodeName() {
        return this.m_rawName;
    }
    
    public void setVersion(final String v) {
        this.m_version = v;
    }
    
    public String getVersion() {
        return this.m_version;
    }
    
    public void setExcludeResultPrefixes(final StringVector v) {
        this.m_excludeResultPrefixes = v;
    }
    
    private boolean excludeResultNSDecl(final String prefix, final String uri) throws TransformerException {
        return null != this.m_excludeResultPrefixes && this.containsExcludeResultPrefix(prefix, uri);
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final SerializationHandler rhandler = transformer.getSerializationHandler();
        try {
            if (TransformerImpl.S_DEBUG) {
                rhandler.flushPending();
                transformer.getTraceManager().fireTraceEvent(this);
            }
            rhandler.startPrefixMapping(this.getPrefix(), this.getNamespace());
            this.executeNSDecls(transformer);
            rhandler.startElement(this.getNamespace(), this.getLocalName(), this.getRawName());
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        TransformerException tException = null;
        try {
            super.execute(transformer);
            if (null != this.m_avts) {
                final int nAttrs = this.m_avts.size();
                for (int i = nAttrs - 1; i >= 0; --i) {
                    final AVT avt = this.m_avts.elementAt(i);
                    final XPathContext xctxt = transformer.getXPathContext();
                    final int sourceNode = xctxt.getCurrentNode();
                    final String stringedValue = avt.evaluate(xctxt, sourceNode, this);
                    if (null != stringedValue) {
                        rhandler.addAttribute(avt.getURI(), avt.getName(), avt.getRawName(), "CDATA", stringedValue);
                    }
                }
            }
            transformer.executeChildTemplates(this, true);
        }
        catch (TransformerException te) {
            tException = te;
        }
        catch (SAXException se2) {
            tException = new TransformerException(se2);
        }
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEndEvent(this);
            }
            rhandler.endElement(this.getNamespace(), this.getLocalName(), this.getRawName());
        }
        catch (SAXException se3) {
            if (tException != null) {
                throw tException;
            }
            throw new TransformerException(se3);
        }
        if (tException != null) {
            throw tException;
        }
        this.unexecuteNSDecls(transformer);
        try {
            rhandler.endPrefixMapping(this.getPrefix());
        }
        catch (SAXException se3) {
            throw new TransformerException(se3);
        }
    }
    
    public Enumeration enumerateLiteralResultAttributes() {
        return (null == this.m_avts) ? null : this.m_avts.elements();
    }
    
    protected boolean accept(final XSLTVisitor visitor) {
        return visitor.visitLiteralResultElement(this);
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs && null != this.m_avts) {
            final int nAttrs = this.m_avts.size();
            for (int i = nAttrs - 1; i >= 0; --i) {
                final AVT avt = this.m_avts.elementAt(i);
                avt.callVisitors(visitor);
            }
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}
