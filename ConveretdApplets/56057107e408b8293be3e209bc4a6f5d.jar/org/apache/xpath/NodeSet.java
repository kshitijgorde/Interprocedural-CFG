// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.w3c.dom.DOMException;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.Node;
import org.apache.xpath.axes.ContextNodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.w3c.dom.NodeList;
import org.apache.xml.utils.NodeVector;

public class NodeSet extends NodeVector implements NodeList, NodeIterator, Cloneable, ContextNodeList
{
    protected transient int m_next;
    protected transient boolean m_mutable;
    protected transient boolean m_cacheNodes;
    private transient int m_last;
    
    public NodeSet() {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
    }
    
    public NodeSet(final int blocksize) {
        super(blocksize);
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
    }
    
    public NodeSet(final NodeSet nodelist) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
        this.addNodes((NodeIterator)nodelist);
    }
    
    public NodeSet(final Node node) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
        this.addNode(node);
    }
    
    public NodeSet(final NodeList nodelist) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
        this.addNodes(nodelist);
    }
    
    public NodeSet(final NodeIterator ni) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_last = 0;
        this.addNodes(ni);
    }
    
    public void addElement(final Node value) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.addElement(value);
    }
    
    public void addNode(final Node n) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        this.addElement(n);
    }
    
    public int addNodeInDocOrder(final Node node, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        return this.addNodeInDocOrder(node, true, support);
    }
    
    public int addNodeInDocOrder(final Node node, final boolean test, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        int insertIndex = -1;
        if (test) {
            final int size = this.size();
            int i;
            for (i = size - 1; i >= 0; --i) {
                final Node child = this.elementAt(i);
                if (child == node) {
                    i = -2;
                    break;
                }
                if (!support.getDOMHelper().isNodeAfter(node, child)) {
                    break;
                }
            }
            if (i != -2) {
                insertIndex = i + 1;
                this.insertElementAt(node, insertIndex);
            }
        }
        else {
            insertIndex = this.size();
            boolean foundit = false;
            for (int i = 0; i < insertIndex; ++i) {
                if (this.item(i).equals(node)) {
                    foundit = true;
                    break;
                }
            }
            if (!foundit) {
                this.addElement(node);
            }
        }
        return insertIndex;
    }
    
    public void addNodes(final NodeSet ns) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        this.addNodes((NodeIterator)ns);
    }
    
    public void addNodes(final NodeList nodelist) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        if (nodelist != null) {
            for (int nChildren = nodelist.getLength(), i = 0; i < nChildren; ++i) {
                final Node obj = nodelist.item(i);
                if (obj != null) {
                    this.addElement(obj);
                }
            }
        }
    }
    
    public void addNodes(final NodeIterator iterator) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        if (iterator != null) {
            Node obj;
            while ((obj = iterator.nextNode()) != null) {
                this.addElement(obj);
            }
        }
    }
    
    private boolean addNodesInDocOrder(final int start, final int end, int testIndex, final NodeList nodelist, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        final boolean foundit = false;
        final Node node = nodelist.item(testIndex);
        int i = end;
        while (i >= start) {
            final Node child = this.elementAt(i);
            if (child == node) {
                i = -2;
                break;
            }
            if (!support.getDOMHelper().isNodeAfter(node, child)) {
                this.insertElementAt(node, i + 1);
                if (--testIndex <= 0) {
                    break;
                }
                final boolean foundPrev = this.addNodesInDocOrder(0, i, testIndex, nodelist, support);
                if (!foundPrev) {
                    this.addNodesInDocOrder(i, this.size() - 1, testIndex, nodelist, support);
                    break;
                }
                break;
            }
            else {
                --i;
            }
        }
        if (i == -1) {
            this.insertElementAt(node, 0);
        }
        return foundit;
    }
    
    public void addNodesInDocOrder(final NodeList nodelist, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        for (int nChildren = nodelist.getLength(), i = 0; i < nChildren; ++i) {
            final Node node = nodelist.item(i);
            if (node != null) {
                this.addNodeInDocOrder(node, support);
            }
        }
    }
    
    public void addNodesInDocOrder(final NodeIterator iterator, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        Node node;
        while ((node = iterator.nextNode()) != null) {
            this.addNodeInDocOrder(node, support);
        }
    }
    
    public void appendNodes(final NodeVector nodes) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.appendNodes(nodes);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NodeSet clone = (NodeSet)super.clone();
        return clone;
    }
    
    public NodeIterator cloneWithReset() throws CloneNotSupportedException {
        final NodeSet clone = (NodeSet)this.clone();
        clone.reset();
        return clone;
    }
    
    public boolean contains(final Node s) {
        this.runTo(-1);
        return super.contains(s);
    }
    
    public void detach() {
    }
    
    public Node elementAt(final int i) {
        this.runTo(i);
        return super.elementAt(i);
    }
    
    public Node getCurrentNode() {
        if (!this.m_cacheNodes) {
            throw new RuntimeException("This NodeSet can not do indexing or counting functions!");
        }
        final int saved = this.m_next;
        final Node n = this.elementAt(this.m_next - 1);
        this.m_next = saved;
        return n;
    }
    
    public int getCurrentPos() {
        return this.m_next;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public NodeFilter getFilter() {
        return null;
    }
    
    public int getLast() {
        return this.m_last;
    }
    
    public int getLength() {
        this.runTo(-1);
        return this.size();
    }
    
    public Node getRoot() {
        return null;
    }
    
    public boolean getShouldCacheNodes() {
        return this.m_cacheNodes;
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public int indexOf(final Node elem) {
        this.runTo(-1);
        return super.indexOf(elem);
    }
    
    public int indexOf(final Node elem, final int index) {
        this.runTo(-1);
        return super.indexOf(elem, index);
    }
    
    public void insertElementAt(final Node value, final int at) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.insertElementAt(value, at);
    }
    
    public void insertNode(final Node n, final int pos) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        this.insertElementAt(n, pos);
    }
    
    public boolean isFresh() {
        return this.m_next == 0;
    }
    
    public Node item(final int index) {
        this.runTo(index);
        return this.elementAt(index);
    }
    
    public Node nextNode() throws DOMException {
        if (this.m_next < this.size()) {
            final Node next = this.elementAt(this.m_next);
            ++this.m_next;
            return next;
        }
        return null;
    }
    
    public Node previousNode() throws DOMException {
        if (!this.m_cacheNodes) {
            throw new RuntimeException("This NodeSet can not iterate to a previous node!");
        }
        if (this.m_next - 1 > 0) {
            --this.m_next;
            return this.elementAt(this.m_next);
        }
        return null;
    }
    
    public void removeAllElements() {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.removeAllElements();
    }
    
    public boolean removeElement(final Node s) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        return super.removeElement(s);
    }
    
    public void removeElementAt(final int i) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.removeElementAt(i);
    }
    
    public void removeNode(final Node n) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        this.removeElement(n);
    }
    
    public void reset() {
        this.m_next = 0;
    }
    
    public void runTo(final int index) {
        if (!this.m_cacheNodes) {
            throw new RuntimeException("This NodeSet can not do indexing or counting functions!");
        }
        if (index >= 0 && this.m_next < super.m_firstFree) {
            this.m_next = index;
        }
        else {
            this.m_next = super.m_firstFree - 1;
        }
    }
    
    public void setCurrentPos(final int i) {
        if (!this.m_cacheNodes) {
            throw new RuntimeException("This NodeSet can not do indexing or counting functions!");
        }
        this.m_next = i;
    }
    
    public void setElementAt(final Node node, final int index) {
        if (!this.m_mutable) {
            throw new RuntimeException("This NodeSet is not mutable!");
        }
        super.setElementAt(node, index);
    }
    
    public void setLast(final int last) {
        this.m_last = last;
    }
    
    public void setShouldCacheNodes(final boolean b) {
        if (!this.isFresh()) {
            throw new RuntimeException("Can not call setShouldCacheNodes after nextNode has been called!");
        }
        this.m_cacheNodes = b;
        this.m_mutable = true;
    }
    
    public int size() {
        return super.size();
    }
}
