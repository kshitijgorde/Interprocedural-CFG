// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.xml.sax.SAXException;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.QName;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.XMLChar;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xml.serializer.SerializationHandler;
import javax.xml.transform.TransformerException;
import java.util.Vector;

public class ElemElement extends ElemUse
{
    protected AVT m_name_avt;
    protected AVT m_namespace_avt;
    
    public ElemElement() {
        this.m_name_avt = null;
        this.m_namespace_avt = null;
    }
    
    public void setName(final AVT v) {
        this.m_name_avt = v;
    }
    
    public AVT getName() {
        return this.m_name_avt;
    }
    
    public void setNamespace(final AVT v) {
        this.m_namespace_avt = v;
    }
    
    public AVT getNamespace() {
        return this.m_namespace_avt;
    }
    
    public void compose(final StylesheetRoot sroot) throws TransformerException {
        super.compose(sroot);
        final StylesheetRoot.ComposeState cstate = sroot.getComposeState();
        final Vector vnames = cstate.getVariableNames();
        if (null != this.m_name_avt) {
            this.m_name_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
        if (null != this.m_namespace_avt) {
            this.m_namespace_avt.fixupVariables(vnames, cstate.getGlobalsSize());
        }
    }
    
    public int getXSLToken() {
        return 46;
    }
    
    public String getNodeName() {
        return "element";
    }
    
    protected String resolvePrefix(final SerializationHandler rhandler, final String prefix, final String nodeNamespace) throws TransformerException {
        return prefix;
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEvent(this);
        }
        final SerializationHandler rhandler = transformer.getSerializationHandler();
        final XPathContext xctxt = transformer.getXPathContext();
        final int sourceNode = xctxt.getCurrentNode();
        String nodeName = (this.m_name_avt == null) ? null : this.m_name_avt.evaluate(xctxt, sourceNode, this);
        String prefix = null;
        String nodeNamespace = "";
        if (nodeName != null && !this.m_name_avt.isSimple() && !XMLChar.isValidQName(nodeName)) {
            transformer.getMsgMgr().warn(this, "WG_ILLEGAL_ATTRIBUTE_VALUE", new Object[] { "name", nodeName });
            nodeName = null;
        }
        else if (nodeName != null) {
            prefix = QName.getPrefixPart(nodeName);
            if (null != this.m_namespace_avt) {
                nodeNamespace = this.m_namespace_avt.evaluate(xctxt, sourceNode, this);
                if (null == nodeNamespace || (prefix != null && prefix.length() > 0 && nodeNamespace.length() == 0)) {
                    transformer.getMsgMgr().error(this, "ER_NULL_URI_NAMESPACE");
                }
                else {
                    prefix = this.resolvePrefix(rhandler, prefix, nodeNamespace);
                    if (null == prefix) {
                        prefix = "";
                    }
                    if (prefix.length() > 0) {
                        nodeName = prefix + ":" + QName.getLocalPart(nodeName);
                    }
                    else {
                        nodeName = QName.getLocalPart(nodeName);
                    }
                }
            }
            else {
                try {
                    nodeNamespace = this.getNamespaceForPrefix(prefix);
                    if (null == nodeNamespace && prefix.length() == 0) {
                        nodeNamespace = "";
                    }
                    else if (null == nodeNamespace) {
                        transformer.getMsgMgr().warn(this, "WG_COULD_NOT_RESOLVE_PREFIX", new Object[] { prefix });
                        nodeName = null;
                    }
                }
                catch (Exception ex) {
                    transformer.getMsgMgr().warn(this, "WG_COULD_NOT_RESOLVE_PREFIX", new Object[] { prefix });
                    nodeName = null;
                }
            }
        }
        this.constructNode(nodeName, prefix, nodeNamespace, transformer);
        if (TransformerImpl.S_DEBUG) {
            transformer.getTraceManager().fireTraceEndEvent(this);
        }
    }
    
    void constructNode(final String nodeName, final String prefix, final String nodeNamespace, final TransformerImpl transformer) throws TransformerException {
        try {
            final SerializationHandler rhandler = transformer.getResultTreeHandler();
            boolean shouldAddAttrs;
            if (null == nodeName) {
                shouldAddAttrs = false;
            }
            else {
                if (null != prefix) {
                    rhandler.startPrefixMapping(prefix, nodeNamespace, true);
                }
                rhandler.startElement(nodeNamespace, QName.getLocalPart(nodeName), nodeName);
                super.execute(transformer);
                shouldAddAttrs = true;
            }
            transformer.executeChildTemplates(this, shouldAddAttrs);
            if (null != nodeName) {
                rhandler.endElement(nodeNamespace, QName.getLocalPart(nodeName), nodeName);
                if (null != prefix) {
                    rhandler.endPrefixMapping(prefix);
                }
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
    }
    
    protected void callChildVisitors(final XSLTVisitor visitor, final boolean callAttrs) {
        if (callAttrs) {
            if (null != this.m_name_avt) {
                this.m_name_avt.callVisitors(visitor);
            }
            if (null != this.m_namespace_avt) {
                this.m_namespace_avt.callVisitors(visitor);
            }
        }
        super.callChildVisitors(visitor, callAttrs);
    }
}
