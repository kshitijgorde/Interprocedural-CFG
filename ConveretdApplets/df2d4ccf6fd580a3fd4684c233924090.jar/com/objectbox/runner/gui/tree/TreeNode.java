// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.tree;

import java.util.Vector;
import com.objectbox.runner.util.JBLogger;
import java.util.NoSuchElementException;
import java.util.Enumeration;
import java.io.Serializable;

public class TreeNode implements Serializable
{
    protected TreeNode m_pParent;
    protected TreeNode m_pNextSibling;
    protected TreeNode m_pPrevSibling;
    protected TreeNode m_pFirstChild;
    public static final int TREENODE_FIRST = 0;
    public static final int TREENODE_LAST = 1;
    public static final int TREENODE_SORT = 2;
    public static final Enumeration EMPTY_ENUMERATION;
    
    static {
        EMPTY_ENUMERATION = new Enumeration() {
            public boolean hasMoreElements() {
                return false;
            }
            
            public Object nextElement() {
                throw new NoSuchElementException("No more elements");
            }
        };
    }
    
    public boolean addChild(final TreeNode treeNode) {
        return this.addChild(treeNode, null);
    }
    
    public boolean addChild(final TreeNode treeNode, int n) {
        if (n == 2) {
            JBLogger.log("WARNING:  haven't implemented TREENODE_SORT");
            n = 1;
        }
        treeNode.m_pParent = this;
        if (this.m_pFirstChild == null) {
            this.m_pFirstChild = treeNode;
            treeNode.m_pNextSibling = null;
            treeNode.m_pPrevSibling = null;
        }
        else if (n == 1) {
            TreeNodeX pPrevSibling;
            for (pPrevSibling = (TreeNodeX)this.m_pFirstChild; pPrevSibling.m_pNextSibling != null; pPrevSibling = (TreeNodeX)pPrevSibling.m_pNextSibling) {}
            pPrevSibling.m_pNextSibling = treeNode;
            treeNode.m_pPrevSibling = pPrevSibling;
            treeNode.m_pNextSibling = null;
        }
        else if (n == 0) {
            treeNode.m_pPrevSibling = null;
            treeNode.m_pNextSibling = this.m_pFirstChild;
            this.m_pFirstChild.m_pPrevSibling = treeNode;
            this.m_pFirstChild = treeNode;
        }
        return true;
    }
    
    public boolean addChild(final TreeNode pNextSibling, final TreeNode pPrevSibling) {
        if (pPrevSibling == null) {
            return this.addChild(pNextSibling, 1);
        }
        pNextSibling.m_pParent = this;
        if (this.m_pFirstChild == null) {
            this.m_pFirstChild = pNextSibling;
            pNextSibling.m_pNextSibling = null;
            pNextSibling.m_pPrevSibling = null;
        }
        else {
            boolean b = false;
            for (TreeNodeX treeNodeX = (TreeNodeX)this.m_pFirstChild; treeNodeX != null; treeNodeX = (TreeNodeX)treeNodeX.m_pNextSibling) {
                if (treeNodeX == pPrevSibling) {
                    b = true;
                    break;
                }
            }
            if (!b) {
                return false;
            }
            pNextSibling.m_pNextSibling = pPrevSibling.m_pNextSibling;
            pNextSibling.m_pPrevSibling = pPrevSibling;
            if (pPrevSibling.m_pNextSibling != null) {
                pPrevSibling.m_pNextSibling.m_pPrevSibling = pNextSibling;
            }
            pPrevSibling.m_pNextSibling = pNextSibling;
        }
        return true;
    }
    
    public Enumeration breadthFirstEnumeration() {
        return new BreadthFirstEnumeration(this);
    }
    
    public Enumeration children() {
        if (this.m_pFirstChild == null) {
            return TreeNode.EMPTY_ENUMERATION;
        }
        final Vector<TreeNode> vector = new Vector<TreeNode>();
        for (TreeNode treeNode = this.m_pFirstChild; treeNode != null; treeNode = treeNode.getNextSibling()) {
            vector.addElement(treeNode);
        }
        return vector.elements();
    }
    
    public void collapse() {
        this.expand(false);
    }
    
    public void deleteAllChildren() {
        TreeNode firstChild;
        while ((firstChild = this.getFirstChild()) != null) {
            firstChild.detachFromTree();
        }
    }
    
    public void deleteChildren() {
    }
    
    public void detachFromTree() {
        if (this.m_pNextSibling != null) {
            this.m_pNextSibling.m_pPrevSibling = this.m_pPrevSibling;
        }
        if (this.m_pPrevSibling != null) {
            this.m_pPrevSibling.m_pNextSibling = this.m_pNextSibling;
        }
        if (this.m_pParent != null && this.m_pParent.m_pFirstChild == this) {
            this.m_pParent.m_pFirstChild = this.m_pNextSibling;
        }
        this.m_pParent = null;
    }
    
    public void expand() {
        this.expand(true);
    }
    
    public void expand(final boolean b) {
    }
    
    public TreeNode getBottomLeftChild() {
        TreeNode treeNode = this.m_pFirstChild;
        if (treeNode != null) {
            while (treeNode.getFirstChild() != null) {
                treeNode = treeNode.getFirstChild();
            }
        }
        return treeNode;
    }
    
    public TreeNode getBottomRightChild() {
        final TreeNode lastChild = this.getLastChild();
        if (lastChild != null) {
            return lastChild.getBottomRightChild();
        }
        return this;
    }
    
    public int getDistanceFromRoot() {
        TreeNode treeNode;
        int n;
        for (treeNode = this.getParent(), n = 0; treeNode != null; treeNode = treeNode.getParent(), ++n) {}
        return n;
    }
    
    public TreeNode getFirstChild() {
        return this.m_pFirstChild;
    }
    
    public TreeNode getFirstSibling() {
        TreeNode prevSibling;
        for (prevSibling = this; prevSibling.getPrevSibling() != null; prevSibling = prevSibling.getPrevSibling()) {}
        return prevSibling;
    }
    
    public TreeNode getLastChild() {
        final TreeNode firstChild = this.getFirstChild();
        if (firstChild == null) {
            return null;
        }
        return firstChild.getLastSibling();
    }
    
    public TreeNode getLastSibling() {
        TreeNode nextSibling;
        for (nextSibling = this; nextSibling.getNextSibling() != null; nextSibling = nextSibling.getNextSibling()) {}
        return nextSibling;
    }
    
    public TreeNode getNextInDisplayOrder() {
        if (this.getFirstChild() != null) {
            return this.getFirstChild();
        }
        if (this.getNextSibling() != null) {
            return this.getNextSibling();
        }
        TreeNode parent = this;
        while (parent.getParent() != null) {
            parent = parent.getParent();
            if (parent.getNextSibling() != null) {
                return parent.getNextSibling();
            }
        }
        return null;
    }
    
    public TreeNode getNextSibling() {
        return this.m_pNextSibling;
    }
    
    public int getNumDescendents() {
        int n = 0;
        for (TreeNode treeNode = this.getFirstChild(); treeNode != null; treeNode = treeNode.getNextInDisplayOrder()) {
            ++n;
        }
        return n;
    }
    
    public TreeNode getParent() {
        return this.m_pParent;
    }
    
    public TreeNode getPrevInDisplayOrder() {
        if (this.getPrevSibling() == null) {
            return this.getParent();
        }
        final TreeNode bottomRightChild = this.getPrevSibling().getBottomRightChild();
        if (bottomRightChild != null) {
            return bottomRightChild;
        }
        return this.getPrevSibling();
    }
    
    public TreeNode getPrevSibling() {
        return this.m_pPrevSibling;
    }
    
    public TreeNode getRoot() {
        TreeNode pParent;
        for (pParent = this; pParent.m_pParent != null; pParent = pParent.m_pParent) {}
        return pParent;
    }
    
    public boolean hasChildren() {
        return this.m_pFirstChild != null;
    }
    
    public boolean isAncestor(final TreeNode treeNode) {
        for (TreeNode treeNode2 = this.getParent(); treeNode2 != null; treeNode2 = treeNode2.getParent()) {
            if (treeNode2 == treeNode) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isDescendant(TreeNode parent) {
        if (parent == null) {
            return false;
        }
        while (parent.getParent() != null) {
            parent = parent.getParent();
            if (parent == this) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isExpanded() {
        return false;
    }
    
    public boolean isParent(final TreeNode treeNode) {
        return this.m_pParent == treeNode;
    }
    
    public boolean isSibling(final TreeNode treeNode) {
        for (TreeNode treeNode2 = this.getFirstSibling(); treeNode2 != null; treeNode2 = treeNode2.getNextSibling()) {
            if (treeNode == treeNode2) {
                return true;
            }
        }
        return false;
    }
    
    public boolean onNextSearchNode(final int n, final TreeNode treeNode) {
        return false;
    }
    
    public TreeNode search(final int n) {
        TreeNode treeNode = this.getBottomLeftChild();
        while (treeNode != null) {
            if (this.onNextSearchNode(n, treeNode)) {
                return treeNode;
            }
            if (treeNode.getNextSibling() != null) {
                treeNode = treeNode.getNextSibling();
            }
            else {
                if (treeNode.getParent() == null || treeNode.getParent() == this) {
                    break;
                }
                treeNode = treeNode.getParent();
            }
        }
        return null;
    }
    
    public void setFirstChild(final TreeNode pFirstChild) {
        this.m_pFirstChild = pFirstChild;
    }
    
    public void setNextSibling(final TreeNode pNextSibling) {
        this.m_pNextSibling = pNextSibling;
    }
    
    public void setParent(final TreeNode pParent) {
        this.m_pParent = pParent;
    }
    
    public void setPrevSibling(final TreeNode pPrevSibling) {
        this.m_pPrevSibling = pPrevSibling;
    }
    
    public TreeNode() {
        this.m_pParent = null;
        this.m_pNextSibling = null;
        this.m_pPrevSibling = null;
        this.m_pFirstChild = null;
    }
    
    final class BreadthFirstEnumeration implements Enumeration
    {
        protected Queue queue;
        
        public BreadthFirstEnumeration(final TreeNode treeNode) {
            final Vector<TreeNode> vector = new Vector<TreeNode>(1);
            vector.addElement(treeNode);
            (this.queue = new Queue()).enqueue(vector.elements());
        }
        
        public boolean hasMoreElements() {
            return !this.queue.isEmpty() && ((Enumeration)this.queue.firstObject()).hasMoreElements();
        }
        
        public Object nextElement() {
            final Enumeration enumeration = (Enumeration)this.queue.firstObject();
            final TreeNode treeNode = enumeration.nextElement();
            final Enumeration children = treeNode.children();
            if (!enumeration.hasMoreElements()) {
                this.queue.dequeue();
            }
            if (children.hasMoreElements()) {
                this.queue.enqueue(children);
            }
            return treeNode;
        }
        
        final class Queue
        {
            QNode head;
            QNode tail;
            
            public void enqueue(final Object o) {
                if (this.head == null) {
                    final QNode qNode = new QNode(o, null);
                    this.tail = qNode;
                    this.head = qNode;
                }
                else {
                    this.tail.next = new QNode(o, null);
                    this.tail = this.tail.next;
                }
            }
            
            public Object dequeue() {
                if (this.head == null) {
                    throw new NoSuchElementException("No more elements");
                }
                final Object object = this.head.object;
                final QNode head = this.head;
                this.head = this.head.next;
                if (this.head == null) {
                    this.tail = null;
                }
                else {
                    head.next = null;
                }
                return object;
            }
            
            public Object firstObject() {
                if (this.head == null) {
                    throw new NoSuchElementException("No more elements");
                }
                return this.head.object;
            }
            
            public boolean isEmpty() {
                return this.head == null;
            }
            
            final class QNode
            {
                public Object object;
                public QNode next;
                
                public QNode(final Object object, final QNode next) {
                    this.object = object;
                    this.next = next;
                }
            }
        }
    }
}
