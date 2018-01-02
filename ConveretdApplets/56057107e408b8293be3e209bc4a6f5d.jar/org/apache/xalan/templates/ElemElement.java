// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xpath.XPathContext;
import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemElement extends ElemUse
{
    private AVT m_name_avt;
    private AVT m_namespace_avt;
    private String m_prefix;
    
    public ElemElement() {
        this.m_name_avt = null;
        this.m_namespace_avt = null;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            boolean shouldAddAttrs = true;
            final ResultTreeHandler rhandler = transformer.getResultTreeHandler();
            final XPathContext xctxt = transformer.getXPathContext();
            String elemName = this.m_name_avt.evaluate(xctxt, sourceNode, this);
            final int indexOfNSSep = elemName.indexOf(58);
            String prefix = null;
            String elemNameSpace = null;
            if (elemName.length() == 0) {
                elemName = null;
            }
            if (elemName != null) {
                prefix = ((indexOfNSSep > 0) ? elemName.substring(0, indexOfNSSep) : "");
                try {
                    elemNameSpace = this.getNamespaceForPrefix(prefix);
                    if (elemNameSpace == null && indexOfNSSep <= 0) {
                        elemNameSpace = "";
                    }
                    final int len = elemNameSpace.length();
                    if ((len > 0 && (indexOfNSSep + 1 == len || !this.isValidNCName(elemName.substring(indexOfNSSep + 1)))) || (indexOfNSSep <= 0 && !this.isValidNCName(elemName))) {
                        transformer.getMsgMgr().warn(this, 24, new Object[] { elemName });
                        elemName = null;
                    }
                }
                catch (Exception ex) {
                    transformer.getMsgMgr().warn(this, 22, new Object[] { prefix });
                    shouldAddAttrs = false;
                    elemName = null;
                }
            }
            else if (elemName == null) {
                transformer.getMsgMgr().warn(this, 24, new Object[] { elemName });
                elemName = null;
            }
            else if (elemName.length() == 0 || !this.isValidNCName(elemName)) {
                transformer.getMsgMgr().warn(this, 22, new Object[] { elemName });
                elemName = null;
            }
            if (elemName != null) {
                if (this.m_namespace_avt != null) {
                    elemNameSpace = this.m_namespace_avt.evaluate(xctxt, sourceNode, this);
                    if (elemNameSpace == null) {
                        elemNameSpace = "";
                    }
                    if (prefix != null) {
                        String nsPrefix = rhandler.getPrefix(elemNameSpace);
                        if (nsPrefix == null) {
                            nsPrefix = "";
                        }
                    }
                    if (prefix == null) {
                        prefix = "";
                    }
                    if (prefix.length() > 0) {
                        elemName = String.valueOf(prefix) + ":" + QName.getLocalPart(elemName);
                    }
                }
                else if (prefix != null && elemNameSpace == null) {
                    transformer.getMsgMgr().warn(this, 22, new Object[] { prefix });
                    elemName = null;
                }
                if (elemName != null) {
                    this.executeNSDecls(transformer);
                    if (prefix != null) {
                        rhandler.startPrefixMapping(prefix, elemNameSpace, true);
                    }
                    rhandler.startElement(elemNameSpace, QName.getLocalPart(elemName), elemName);
                }
            }
            if (elemName == null) {
                shouldAddAttrs = false;
            }
            if (shouldAddAttrs) {
                super.execute(transformer, sourceNode, mode);
            }
            transformer.executeChildTemplates(this, sourceNode, mode, shouldAddAttrs);
            if (elemName != null) {
                rhandler.endElement(elemNameSpace, QName.getLocalPart(elemName), elemName);
                this.unexecuteNSDecls(transformer);
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public AVT getName() {
        return this.m_name_avt;
    }
    
    public AVT getNamespace() {
        return this.m_namespace_avt;
    }
    
    public String getNodeName() {
        return "element";
    }
    
    public int getXSLToken() {
        return 46;
    }
    
    public void setName(final AVT v) {
        this.m_name_avt = v;
    }
    
    public void setNamespace(final AVT v) {
        this.m_namespace_avt = v;
    }
}
