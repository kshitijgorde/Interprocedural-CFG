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
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class ElemAttribute extends ElemTemplateElement
{
    public AVT m_name_avt;
    public AVT m_namespace_avt;
    
    public ElemAttribute() {
        this.m_name_avt = null;
        this.m_namespace_avt = null;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final int type = ((ElemTemplateElement)newChild).getXSLToken();
        switch (type) {
            default: {
                this.error(4, new Object[] { newChild.getNodeName(), this.getNodeName() });
                return super.appendChild(newChild);
            }
            case 9:
            case 17:
            case 28:
            case 30:
            case 35:
            case 36:
            case 37:
            case 42:
            case 50:
            case 72:
            case 73:
            case 74:
            case 75:
            case 78: {
                return super.appendChild(newChild);
            }
        }
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            final ResultTreeHandler rhandler = transformer.getResultTreeHandler();
            final XPathContext xctxt = transformer.getXPathContext();
            final String origAttrName;
            String attrName = origAttrName = this.m_name_avt.evaluate(xctxt, sourceNode, this);
            final String val = transformer.transformToString(this, sourceNode, mode);
            if (!rhandler.isElementPending()) {
                transformer.getMsgMgr().warn(this, 24, new Object[] { origAttrName });
                return;
            }
            if (attrName == null) {
                return;
            }
            String attrNameSpace = "";
            if (this.m_namespace_avt != null) {
                attrNameSpace = this.m_namespace_avt.evaluate(xctxt, sourceNode, this);
                if (attrNameSpace != null && attrNameSpace.length() > 0) {
                    String prefix = rhandler.getPrefix(attrNameSpace);
                    if (prefix == null) {
                        prefix = rhandler.getNewUniqueNSPrefix();
                        rhandler.startPrefixMapping(prefix, attrNameSpace, false);
                    }
                    attrName = String.valueOf(prefix) + ":" + QName.getLocalPart(attrName);
                }
            }
            else {
                if (QName.isXMLNSDecl(origAttrName)) {
                    final String prefix = QName.getPrefixFromXMLNSDecl(origAttrName);
                    final String ns = rhandler.getURI(prefix);
                    if (ns == null) {
                        rhandler.startPrefixMapping(prefix, val, false);
                    }
                    return;
                }
                String nsprefix = QName.getPrefixPart(origAttrName);
                if (nsprefix == null) {
                    nsprefix = "";
                }
                try {
                    attrNameSpace = this.getNamespaceForPrefix(nsprefix);
                    if (attrNameSpace == null && nsprefix.length() > 0) {
                        transformer.getMsgMgr().warn(this, 22, new Object[] { nsprefix });
                        return;
                    }
                    if (attrNameSpace == null) {
                        attrNameSpace = "";
                    }
                }
                catch (Exception ex) {
                    transformer.getMsgMgr().warn(this, 22, new Object[] { nsprefix });
                    return;
                }
            }
            final String localName = QName.getLocalPart(attrName);
            rhandler.addAttribute(attrNameSpace, localName, attrName, "CDATA", val);
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
        return "attribute";
    }
    
    public int getXSLToken() {
        return 48;
    }
    
    public void setName(final AVT name) {
        this.m_name_avt = name;
    }
    
    public void setNamespace(final AVT name) {
        this.m_namespace_avt = name;
    }
}
