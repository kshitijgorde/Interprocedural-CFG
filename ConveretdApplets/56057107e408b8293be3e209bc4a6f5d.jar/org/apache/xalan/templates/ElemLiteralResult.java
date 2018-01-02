// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import java.util.Enumeration;
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
    
    public void addLiteralResultAttribute(final String att) {
        if (this.m_xslAttr == null) {
            this.m_xslAttr = new Vector();
        }
        this.m_xslAttr.addElement(att);
    }
    
    public void addLiteralResultAttribute(final AVT avt) {
        if (this.m_avts == null) {
            this.m_avts = new Vector();
        }
        this.m_avts.addElement(avt);
    }
    
    public boolean containsExcludeResultPrefix(String prefix) {
        if (this.m_excludeResultPrefixes == null) {
            return super.containsExcludeResultPrefix(prefix);
        }
        if (prefix.length() == 0) {
            prefix = "#default";
        }
        return this.m_excludeResultPrefixes.contains(prefix) || super.containsExcludeResultPrefix(prefix);
    }
    
    public boolean containsExtensionElementURI(final String uri) {
        return this.m_ExtensionElementURIs != null && this.m_ExtensionElementURIs.contains(uri);
    }
    
    public Enumeration enumerateLiteralResultAttributes() {
        return (this.m_avts == null) ? null : this.m_avts.elements();
    }
    
    private boolean excludeResultNSDecl(final String prefix, final String uri) throws TransformerException {
        return this.m_excludeResultPrefixes != null && this.m_excludeResultPrefixes.contains(prefix);
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            final ResultTreeHandler rhandler = transformer.getResultTreeHandler();
            this.executeNSDecls(transformer);
            rhandler.startElement(this.getNamespace(), this.getLocalName(), this.getRawName());
            try {
                super.execute(transformer, sourceNode, mode);
                if (this.m_avts != null) {
                    final int nAttrs = this.m_avts.size();
                    for (int i = nAttrs - 1; i >= 0; --i) {
                        final AVT avt = this.m_avts.elementAt(i);
                        final XPathContext xctxt = transformer.getXPathContext();
                        final String stringedValue = avt.evaluate(xctxt, sourceNode, this);
                        if (stringedValue != null) {
                            rhandler.addAttribute(avt.getURI(), avt.getName(), avt.getRawName(), "CDATA", stringedValue);
                        }
                    }
                }
                transformer.executeChildTemplates(this, sourceNode, mode);
            }
            catch (TransformerException te) {
                throw te;
            }
            finally {
                rhandler.endElement(this.getNamespace(), this.getLocalName(), this.getRawName());
                this.unexecuteNSDecls(transformer);
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public String getExtensionElementPrefix(final int i) throws ArrayIndexOutOfBoundsException {
        if (this.m_ExtensionElementURIs == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.m_ExtensionElementURIs.elementAt(i);
    }
    
    public int getExtensionElementPrefixCount() {
        return (this.m_ExtensionElementURIs != null) ? this.m_ExtensionElementURIs.size() : 0;
    }
    
    public boolean getIsLiteralResultAsStylesheet() {
        return this.isLiteralResultAsStylesheet;
    }
    
    public AVT getLiteralResultAttribute(final String name) {
        if (this.m_avts != null) {
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
    
    public String getLocalName() {
        return this.m_localName;
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public String getNodeName() {
        return this.m_rawName;
    }
    
    public String getRawName() {
        return this.m_rawName;
    }
    
    public int getXSLToken() {
        return 77;
    }
    
    boolean needToCheckExclude() {
        if (this.m_excludeResultPrefixes == null && super.m_prefixTable == null) {
            return false;
        }
        if (super.m_prefixTable == null) {
            super.m_prefixTable = new Vector();
        }
        return true;
    }
    
    public void resolvePrefixTables() throws TransformerException {
        super.resolvePrefixTables();
        final StylesheetRoot stylesheet = this.getStylesheetRoot();
        if (this.m_namespace != null && this.m_namespace.length() > 0) {
            final NamespaceAlias nsa = stylesheet.getNamespaceAliasComposed(this.m_namespace);
            if (nsa != null) {
                this.m_namespace = nsa.getResultNamespace();
                final String resultPrefix = nsa.getStylesheetPrefix();
                if (resultPrefix != null && resultPrefix.length() > 0) {
                    this.m_rawName = String.valueOf(resultPrefix) + ":" + this.m_localName;
                }
                else {
                    this.m_rawName = this.m_localName;
                }
            }
        }
        if (this.m_avts != null) {
            for (int n = this.m_avts.size(), i = 0; i < n; ++i) {
                final AVT avt = this.m_avts.elementAt(i);
                final String ns = avt.getURI();
                if (ns != null && ns.length() > 0) {
                    final NamespaceAlias nsa2 = stylesheet.getNamespaceAliasComposed(this.m_namespace);
                    if (nsa2 != null) {
                        final String namespace = nsa2.getResultNamespace();
                        final String resultPrefix2 = nsa2.getStylesheetPrefix();
                        String rawName = avt.getName();
                        if (resultPrefix2 != null && resultPrefix2.length() > 0) {
                            rawName = String.valueOf(resultPrefix2) + ":" + rawName;
                        }
                        avt.setURI(namespace);
                        avt.setRawName(rawName);
                    }
                }
            }
        }
    }
    
    public void setExcludeResultPrefixes(final StringVector v) {
        this.m_excludeResultPrefixes = v;
    }
    
    public void setExtensionElementPrefixes(final StringVector v) {
        this.m_ExtensionElementURIs = v;
    }
    
    public void setIsLiteralResultAsStylesheet(final boolean b) {
        this.isLiteralResultAsStylesheet = b;
    }
    
    public void setLocalName(final String localName) {
        this.m_localName = localName;
    }
    
    public void setNamespace(String ns) {
        if (ns == null) {
            ns = "";
        }
        this.m_namespace = ns;
    }
    
    public void setRawName(final String rawName) {
        this.m_rawName = rawName;
    }
    
    public void setVersion(final String v) {
        this.m_version = v;
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
}
