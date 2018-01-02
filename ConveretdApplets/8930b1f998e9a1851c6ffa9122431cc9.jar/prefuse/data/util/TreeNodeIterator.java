// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.Node;
import java.util.Iterator;

public class TreeNodeIterator implements Iterator
{
    private Node m_node;
    private Node m_root;
    
    public TreeNodeIterator(final Node node) {
        this.m_root = node;
        this.m_node = node;
    }
    
    public boolean hasNext() {
        return this.m_node != null;
    }
    
    public Object next() {
        Node node;
        if ((node = this.m_node.getChild(0)) == null) {
            if (this.m_node == this.m_root || (node = this.m_node.getNextSibling()) == null) {
                for (node = this.m_node.getParent(); node != this.m_root && node != null; node = node.getParent()) {
                    final Node nextSibling;
                    if ((nextSibling = node.getNextSibling()) != null) {
                        node = nextSibling;
                        break;
                    }
                }
                if (node == this.m_root) {
                    node = null;
                }
            }
        }
        final Node node2 = this.m_node;
        this.m_node = node;
        return node2;
    }
    
    public void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
