// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.objects;

import javax.xml.transform.TransformerException;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.XPathContext;
import org.w3c.dom.NodeList;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.dtm.DTMManager;
import org.w3c.dom.Node;

public class XNodeSetForDOM extends XNodeSet
{
    static final long serialVersionUID = -8396190713754624640L;
    Object m_origObj;
    
    public XNodeSetForDOM(final Node node, final DTMManager dtmMgr) {
        super.m_dtmMgr = dtmMgr;
        this.m_origObj = node;
        final int dtmHandle = dtmMgr.getDTMHandleFromNode(node);
        super.m_obj = new NodeSetDTM(dtmMgr);
        ((NodeSetDTM)super.m_obj).addNode(dtmHandle);
    }
    
    public XNodeSetForDOM(final XNodeSet val) {
        super(val);
        if (val instanceof XNodeSetForDOM) {
            this.m_origObj = ((XNodeSetForDOM)val).m_origObj;
        }
    }
    
    public XNodeSetForDOM(final NodeList nodeList, final XPathContext xctxt) {
        super.m_dtmMgr = xctxt.getDTMManager();
        this.m_origObj = nodeList;
        final NodeSetDTM nsdtm = new NodeSetDTM(nodeList, xctxt);
        super.m_last = nsdtm.getLength();
        super.m_obj = nsdtm;
    }
    
    public XNodeSetForDOM(final NodeIterator nodeIter, final XPathContext xctxt) {
        super.m_dtmMgr = xctxt.getDTMManager();
        this.m_origObj = nodeIter;
        final NodeSetDTM nsdtm = new NodeSetDTM(nodeIter, xctxt);
        super.m_last = nsdtm.getLength();
        super.m_obj = nsdtm;
    }
    
    public Object object() {
        return this.m_origObj;
    }
    
    public NodeIterator nodeset() throws TransformerException {
        return (NodeIterator)((this.m_origObj instanceof NodeIterator) ? this.m_origObj : super.nodeset());
    }
    
    public NodeList nodelist() throws TransformerException {
        return (NodeList)((this.m_origObj instanceof NodeList) ? this.m_origObj : super.nodelist());
    }
}
