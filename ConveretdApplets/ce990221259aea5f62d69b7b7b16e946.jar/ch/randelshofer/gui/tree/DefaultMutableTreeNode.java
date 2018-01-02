// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui.tree;

import java.util.Stack;
import java.util.NoSuchElementException;
import ch.randelshofer.util.EmptyEnumeration;
import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

public class DefaultMutableTreeNode implements Cloneable, Serializable
{
    protected DefaultMutableTreeNode parent;
    protected Vector children;
    protected transient Object userObject;
    protected boolean allowsChildren;
    
    public DefaultMutableTreeNode() {
        this(null);
    }
    
    public DefaultMutableTreeNode(final Object o) {
        this(o, true);
    }
    
    public DefaultMutableTreeNode(final Object userObject, final boolean allowsChildren) {
        this.parent = null;
        this.allowsChildren = allowsChildren;
        this.userObject = userObject;
    }
    
    public void insert(final DefaultMutableTreeNode defaultMutableTreeNode, final int n) {
        if (!this.allowsChildren) {
            throw new IllegalStateException("node does not allow children");
        }
        if (defaultMutableTreeNode == null) {
            throw new IllegalArgumentException("new child is null");
        }
        if (this.isNodeAncestor(defaultMutableTreeNode)) {
            throw new IllegalArgumentException("new child is an ancestor");
        }
        final DefaultMutableTreeNode parent = defaultMutableTreeNode.getParent();
        if (parent != null) {
            parent.remove(defaultMutableTreeNode);
        }
        defaultMutableTreeNode.setParent(this);
        if (this.children == null) {
            this.children = new Vector();
        }
        this.children.insertElementAt(defaultMutableTreeNode, n);
    }
    
    public void remove(final int n) {
        final DefaultMutableTreeNode child = this.getChildAt(n);
        this.children.removeElementAt(n);
        child.setParent(null);
    }
    
    public void setParent(final DefaultMutableTreeNode parent) {
        this.parent = parent;
    }
    
    public DefaultMutableTreeNode getParent() {
        return this.parent;
    }
    
    public DefaultMutableTreeNode getChildAt(final int n) {
        if (this.children == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return this.children.elementAt(n);
    }
    
    public int getChildCount() {
        if (this.children == null) {
            return 0;
        }
        return this.children.size();
    }
    
    public int getIndex(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!this.isNodeChild(defaultMutableTreeNode)) {
            return -1;
        }
        return this.children.indexOf(defaultMutableTreeNode);
    }
    
    public Enumeration children() {
        if (this.children == null) {
            return EmptyEnumeration.EMPTY_ENUMERATION;
        }
        return this.children.elements();
    }
    
    public void setAllowsChildren(final boolean allowsChildren) {
        if (allowsChildren != this.allowsChildren && !(this.allowsChildren = allowsChildren)) {
            this.removeAllChildren();
        }
    }
    
    public boolean getAllowsChildren() {
        return this.allowsChildren;
    }
    
    public void setUserObject(final Object userObject) {
        this.userObject = userObject;
    }
    
    public Object getUserObject() {
        return this.userObject;
    }
    
    public void removeFromParent() {
        final DefaultMutableTreeNode parent = this.getParent();
        if (parent != null) {
            parent.remove(this);
        }
    }
    
    public void remove(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!this.isNodeChild(defaultMutableTreeNode)) {
            throw new IllegalArgumentException("argument is not a child");
        }
        this.remove(this.getIndex(defaultMutableTreeNode));
    }
    
    public void removeAllChildren() {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            this.remove(i);
        }
    }
    
    public void add(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode != null && defaultMutableTreeNode.getParent() == this) {
            this.insert(defaultMutableTreeNode, this.getChildCount() - 1);
        }
        else {
            this.insert(defaultMutableTreeNode, this.getChildCount());
        }
    }
    
    public boolean isNodeAncestor(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == null) {
            return false;
        }
        DefaultMutableTreeNode parent = this;
        while (parent != defaultMutableTreeNode) {
            if ((parent = parent.getParent()) == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isNodeDescendant(final DefaultMutableTreeNode defaultMutableTreeNode) {
        return defaultMutableTreeNode != null && defaultMutableTreeNode.isNodeAncestor(this);
    }
    
    public int getDepth() {
        DefaultMutableTreeNode nextElement = null;
        final Enumeration breadthFirstEnumeration = this.breadthFirstEnumeration();
        while (breadthFirstEnumeration.hasMoreElements()) {
            nextElement = breadthFirstEnumeration.nextElement();
        }
        if (nextElement == null) {
            throw new Error("nodes should be null");
        }
        return nextElement.getLevel() - this.getLevel();
    }
    
    public int getLevel() {
        int n = 0;
        DefaultMutableTreeNode parent = this;
        while ((parent = parent.getParent()) != null) {
            ++n;
        }
        return n;
    }
    
    public Enumeration preorderEnumeration() {
        return new PreorderEnumeration(this);
    }
    
    public Enumeration breadthFirstEnumeration() {
        return new BreadthFirstEnumeration(this);
    }
    
    public boolean isNodeChild(final DefaultMutableTreeNode defaultMutableTreeNode) {
        return defaultMutableTreeNode != null && this.getChildCount() != 0 && defaultMutableTreeNode.getParent() == this;
    }
    
    public String toString() {
        if (this.userObject == null) {
            return null;
        }
        return this.userObject.toString();
    }
    
    public Object clone() {
        DefaultMutableTreeNode defaultMutableTreeNode;
        try {
            defaultMutableTreeNode = (DefaultMutableTreeNode)super.clone();
            defaultMutableTreeNode.children = null;
            defaultMutableTreeNode.parent = null;
        }
        catch (CloneNotSupportedException ex) {
            throw new Error(ex.toString());
        }
        return defaultMutableTreeNode;
    }
    
    final class BreadthFirstEnumeration implements Enumeration
    {
        protected Queue queue;
        
        public BreadthFirstEnumeration(final DefaultMutableTreeNode defaultMutableTreeNode) {
            final Vector<DefaultMutableTreeNode> vector = new Vector<DefaultMutableTreeNode>(1);
            vector.addElement(defaultMutableTreeNode);
            (this.queue = new Queue()).enqueue(vector.elements());
        }
        
        public boolean hasMoreElements() {
            return !this.queue.isEmpty() && ((Enumeration)this.queue.firstObject()).hasMoreElements();
        }
        
        public Object nextElement() {
            final Enumeration enumeration = (Enumeration)this.queue.firstObject();
            final DefaultMutableTreeNode defaultMutableTreeNode = enumeration.nextElement();
            final Enumeration children = defaultMutableTreeNode.children();
            if (!enumeration.hasMoreElements()) {
                this.queue.dequeue();
            }
            if (children.hasMoreElements()) {
                this.queue.enqueue(children);
            }
            return defaultMutableTreeNode;
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
    
    final class PreorderEnumeration implements Enumeration
    {
        protected Stack stack;
        
        public PreorderEnumeration(final DefaultMutableTreeNode defaultMutableTreeNode) {
            final Vector<DefaultMutableTreeNode> vector = new Vector<DefaultMutableTreeNode>(1);
            vector.addElement(defaultMutableTreeNode);
            (this.stack = new Stack()).push(vector.elements());
        }
        
        public boolean hasMoreElements() {
            return !this.stack.empty() && this.stack.peek().hasMoreElements();
        }
        
        public Object nextElement() {
            final Enumeration<DefaultMutableTreeNode> enumeration = this.stack.peek();
            final DefaultMutableTreeNode defaultMutableTreeNode = enumeration.nextElement();
            final Enumeration children = defaultMutableTreeNode.children();
            if (!enumeration.hasMoreElements()) {
                this.stack.pop();
            }
            if (children.hasMoreElements()) {
                this.stack.push(children);
            }
            return defaultMutableTreeNode;
        }
    }
}
