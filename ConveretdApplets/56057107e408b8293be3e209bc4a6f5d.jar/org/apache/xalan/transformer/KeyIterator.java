// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.w3c.dom.DOMException;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.PrefixResolver;
import org.w3c.dom.Node;
import java.util.Vector;
import org.apache.xml.utils.QName;
import org.apache.xpath.axes.LocPathIterator;

public class KeyIterator extends LocPathIterator
{
    private KeyTable m_keyTable;
    private QName m_name;
    private transient boolean m_lookForMoreNodes;
    private Vector m_keyDeclarations;
    
    public KeyIterator(final Node doc, final PrefixResolver nscontext, final QName name, final Vector keyDeclarations, final XPathContext xctxt) {
        super(nscontext);
        this.m_lookForMoreNodes = true;
        this.initContext(xctxt);
        this.m_name = name;
        this.m_keyDeclarations = keyDeclarations;
        this.setLastUsedWalker(super.m_firstWalker = new KeyWalker(this));
    }
    
    void addRefNode(final String ref, final Node node) {
        this.m_keyTable.addRefNode(ref, node);
    }
    
    public Vector getKeyDeclarations() {
        return this.m_keyDeclarations;
    }
    
    boolean getLookForMoreNodes() {
        return this.m_lookForMoreNodes;
    }
    
    public QName getName() {
        return this.m_name;
    }
    
    public Node nextNode() throws DOMException {
        final Node n = super.nextNode();
        return n;
    }
    
    void setKeyTable(final KeyTable keyTable) {
        this.m_keyTable = keyTable;
    }
    
    void setLookForMoreNodes(final boolean b) {
        this.m_lookForMoreNodes = b;
    }
    
    public void setLookupKey(final String lookupKey) {
        ((KeyWalker)super.m_firstWalker).m_lookupKey = lookupKey;
        super.m_firstWalker.setRoot((this.getContext().getNodeType() == 9) ? this.getContext() : this.getContext().getOwnerDocument());
        this.setLastUsedWalker(super.m_firstWalker);
        this.setNextPosition(0);
    }
}
