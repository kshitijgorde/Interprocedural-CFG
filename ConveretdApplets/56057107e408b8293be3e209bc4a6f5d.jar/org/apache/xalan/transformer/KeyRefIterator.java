// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.w3c.dom.DOMException;
import org.apache.xpath.NodeSet;
import org.w3c.dom.Node;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.LocPathIterator;

public class KeyRefIterator extends LocPathIterator
{
    private QName m_name;
    private String m_lookupKey;
    private KeyIterator m_ki;
    
    public KeyRefIterator(final String ref, final KeyIterator ki) {
        super(ki.getPrefixResolver());
        this.setShouldCacheNodes(true);
        this.m_ki = ki;
        this.m_name = ki.getName();
        this.m_lookupKey = ref;
        super.m_execContext = ki.getXPathContext();
        super.m_dhelper = ki.getDOMHelper();
    }
    
    public void addNode(final Node node) {
        final NodeSet m_cachedNodes = this.getCachedNodes();
        if (m_cachedNodes != null && !m_cachedNodes.contains(node)) {
            m_cachedNodes.addElement(node);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final KeyRefIterator clone = (KeyRefIterator)super.clone();
        return clone;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public Node nextNode() throws DOMException {
        if (super.m_foundLast) {
            return null;
        }
        final NodeSet m_cachedNodes = this.getCachedNodes();
        if (m_cachedNodes != null && super.m_next < m_cachedNodes.size()) {
            final Node next = m_cachedNodes.elementAt(super.m_next);
            this.setCurrentPos(++super.m_next);
            return super.m_lastFetched = next;
        }
        Node next = null;
        if (this.m_ki.getLookForMoreNodes()) {
            ((KeyWalker)this.m_ki.getFirstWalker()).m_lookupKey = this.m_lookupKey;
            next = this.m_ki.nextNode();
        }
        if (next != null) {
            super.m_lastFetched = next;
            this.setCurrentPos(++super.m_next);
            return next;
        }
        super.m_foundLast = true;
        return super.m_lastFetched = null;
    }
    
    public void reset() {
        super.reset();
        this.setCurrentPos(0);
    }
}
