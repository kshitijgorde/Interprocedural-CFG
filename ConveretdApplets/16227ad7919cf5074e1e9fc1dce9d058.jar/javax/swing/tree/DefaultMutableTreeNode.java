// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.tree;

import java.util.EmptyStackException;
import java.util.Stack;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.Enumeration;
import java.io.Serializable;

public class DefaultMutableTreeNode implements Cloneable, MutableTreeNode, Serializable
{
    public static final Enumeration EMPTY_ENUMERATION;
    protected MutableTreeNode parent;
    protected Vector children;
    protected transient Object userObject;
    protected boolean allowsChildren;
    
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
    
    public void add(final MutableTreeNode mutableTreeNode) {
        if (mutableTreeNode != null && mutableTreeNode.getParent() == this) {
            this.insert(mutableTreeNode, this.getChildCount() - 1);
        }
        else {
            this.insert(mutableTreeNode, this.getChildCount());
        }
    }
    
    public Enumeration breadthFirstEnumeration() {
        return new BreadthFirstEnumeration(this);
    }
    
    public Enumeration children() {
        if (this.children == null) {
            return DefaultMutableTreeNode.EMPTY_ENUMERATION;
        }
        return this.children.elements();
    }
    
    public Object clone() {
        DefaultMutableTreeNode defaultMutableTreeNode;
        try {
            defaultMutableTreeNode = (DefaultMutableTreeNode)super.clone();
            defaultMutableTreeNode.children = null;
            defaultMutableTreeNode.parent = null;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        return defaultMutableTreeNode;
    }
    
    public Enumeration depthFirstEnumeration() {
        return this.postorderEnumeration();
    }
    
    public boolean getAllowsChildren() {
        return this.allowsChildren;
    }
    
    public TreeNode getChildAfter(final TreeNode treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        final int index = this.getIndex(treeNode);
        if (index == -1) {
            throw new IllegalArgumentException("node is not a child");
        }
        if (index < this.getChildCount() - 1) {
            return this.getChildAt(index + 1);
        }
        return null;
    }
    
    public TreeNode getChildAt(final int n) {
        if (this.children == null) {
            throw new ArrayIndexOutOfBoundsException("node has no children");
        }
        return this.children.elementAt(n);
    }
    
    public TreeNode getChildBefore(final TreeNode treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        final int index = this.getIndex(treeNode);
        if (index == -1) {
            throw new IllegalArgumentException("argument is not a child");
        }
        if (index > 0) {
            return this.getChildAt(index - 1);
        }
        return null;
    }
    
    public int getChildCount() {
        if (this.children == null) {
            return 0;
        }
        return this.children.size();
    }
    
    public int getDepth() {
        DefaultMutableTreeNode nextElement = null;
        final Enumeration breadthFirstEnumeration = this.breadthFirstEnumeration();
        while (breadthFirstEnumeration.hasMoreElements()) {
            nextElement = breadthFirstEnumeration.nextElement();
        }
        if (nextElement == null) {
            throw new InternalError("nodes should be null");
        }
        return nextElement.getLevel() - this.getLevel();
    }
    
    public TreeNode getFirstChild() {
        if (this.getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return this.getChildAt(0);
    }
    
    public DefaultMutableTreeNode getFirstLeaf() {
        DefaultMutableTreeNode defaultMutableTreeNode;
        for (defaultMutableTreeNode = this; !defaultMutableTreeNode.isLeaf(); defaultMutableTreeNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getFirstChild()) {}
        return defaultMutableTreeNode;
    }
    
    public int getIndex(final TreeNode treeNode) {
        if (treeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!this.isNodeChild(treeNode)) {
            return -1;
        }
        return this.children.indexOf(treeNode);
    }
    
    public TreeNode getLastChild() {
        if (this.getChildCount() == 0) {
            throw new NoSuchElementException("node has no children");
        }
        return this.getChildAt(this.getChildCount() - 1);
    }
    
    public DefaultMutableTreeNode getLastLeaf() {
        DefaultMutableTreeNode defaultMutableTreeNode;
        for (defaultMutableTreeNode = this; !defaultMutableTreeNode.isLeaf(); defaultMutableTreeNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getLastChild()) {}
        return defaultMutableTreeNode;
    }
    
    public int getLeafCount() {
        int n = 0;
        final Enumeration breadthFirstEnumeration = this.breadthFirstEnumeration();
        while (breadthFirstEnumeration.hasMoreElements()) {
            if (breadthFirstEnumeration.nextElement().isLeaf()) {
                ++n;
            }
        }
        if (n < 1) {
            throw new InternalError("tree has zero leaves");
        }
        return n;
    }
    
    public int getLevel() {
        int n = 0;
        TreeNode parent = this;
        while ((parent = parent.getParent()) != null) {
            ++n;
        }
        return n;
    }
    
    public DefaultMutableTreeNode getNextLeaf() {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        final DefaultMutableTreeNode nextSibling = this.getNextSibling();
        if (nextSibling != null) {
            return nextSibling.getFirstLeaf();
        }
        return defaultMutableTreeNode.getNextLeaf();
    }
    
    public DefaultMutableTreeNode getNextNode() {
        if (this.getChildCount() != 0) {
            return (DefaultMutableTreeNode)this.getChildAt(0);
        }
        final DefaultMutableTreeNode nextSibling = this.getNextSibling();
        if (nextSibling == null) {
            for (DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent(); defaultMutableTreeNode != null; defaultMutableTreeNode = (DefaultMutableTreeNode)defaultMutableTreeNode.getParent()) {
                final DefaultMutableTreeNode nextSibling2 = defaultMutableTreeNode.getNextSibling();
                if (nextSibling2 != null) {
                    return nextSibling2;
                }
            }
            return null;
        }
        return nextSibling;
    }
    
    public DefaultMutableTreeNode getNextSibling() {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent();
        TreeNode treeNode;
        if (defaultMutableTreeNode == null) {
            treeNode = null;
        }
        else {
            treeNode = defaultMutableTreeNode.getChildAfter(this);
        }
        if (treeNode != null && !this.isNodeSibling(treeNode)) {
            throw new InternalError("child of parent is not a sibling");
        }
        return (DefaultMutableTreeNode)treeNode;
    }
    
    public TreeNode getParent() {
        return this.parent;
    }
    
    public TreeNode[] getPath() {
        return this.getPathToRoot(this, 0);
    }
    
    protected TreeNode[] getPathToRoot(final TreeNode treeNode, int n) {
        TreeNode[] pathToRoot;
        if (treeNode == null) {
            if (n == 0) {
                return null;
            }
            pathToRoot = new TreeNode[n];
        }
        else {
            ++n;
            pathToRoot = this.getPathToRoot(treeNode.getParent(), n);
            pathToRoot[pathToRoot.length - n] = treeNode;
        }
        return pathToRoot;
    }
    
    public DefaultMutableTreeNode getPreviousLeaf() {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        final DefaultMutableTreeNode previousSibling = this.getPreviousSibling();
        if (previousSibling != null) {
            return previousSibling.getLastLeaf();
        }
        return defaultMutableTreeNode.getPreviousLeaf();
    }
    
    public DefaultMutableTreeNode getPreviousNode() {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent();
        if (defaultMutableTreeNode == null) {
            return null;
        }
        final DefaultMutableTreeNode previousSibling = this.getPreviousSibling();
        if (previousSibling == null) {
            return defaultMutableTreeNode;
        }
        if (previousSibling.getChildCount() == 0) {
            return previousSibling;
        }
        return previousSibling.getLastLeaf();
    }
    
    public DefaultMutableTreeNode getPreviousSibling() {
        final DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode)this.getParent();
        TreeNode treeNode;
        if (defaultMutableTreeNode == null) {
            treeNode = null;
        }
        else {
            treeNode = defaultMutableTreeNode.getChildBefore(this);
        }
        if (treeNode != null && !this.isNodeSibling(treeNode)) {
            throw new InternalError("child of parent is not a sibling");
        }
        return (DefaultMutableTreeNode)treeNode;
    }
    
    public TreeNode getRoot() {
        TreeNode parent = this;
        TreeNode treeNode;
        do {
            treeNode = parent;
            parent = parent.getParent();
        } while (parent != null);
        return treeNode;
    }
    
    public TreeNode getSharedAncestor(final DefaultMutableTreeNode defaultMutableTreeNode) {
        if (defaultMutableTreeNode == this) {
            return this;
        }
        if (defaultMutableTreeNode == null) {
            return null;
        }
        final int level = this.getLevel();
        final int level2 = defaultMutableTreeNode.getLevel();
        int i;
        TreeNode treeNode;
        TreeNode parent;
        if (level2 > level) {
            i = level2 - level;
            treeNode = defaultMutableTreeNode;
            parent = this;
        }
        else {
            i = level - level2;
            treeNode = this;
            parent = defaultMutableTreeNode;
        }
        while (i > 0) {
            treeNode = treeNode.getParent();
            --i;
        }
        while (treeNode != parent) {
            treeNode = treeNode.getParent();
            parent = parent.getParent();
            if (treeNode == null) {
                if (treeNode != null || parent != null) {
                    throw new InternalError("nodes should be null");
                }
                return null;
            }
        }
        return treeNode;
    }
    
    public int getSiblingCount() {
        final TreeNode parent = this.getParent();
        if (parent == null) {
            return 1;
        }
        return parent.getChildCount();
    }
    
    public Object getUserObject() {
        return this.userObject;
    }
    
    public Object[] getUserObjectPath() {
        final TreeNode[] path = this.getPath();
        final Object[] array = new Object[path.length];
        for (int i = 0; i < path.length; ++i) {
            array[i] = ((DefaultMutableTreeNode)path[i]).getUserObject();
        }
        return array;
    }
    
    public void insert(final MutableTreeNode mutableTreeNode, final int n) {
        if (!this.allowsChildren) {
            throw new IllegalStateException("node does not allow children");
        }
        if (mutableTreeNode == null) {
            throw new IllegalArgumentException("new child is null");
        }
        if (this.isNodeAncestor(mutableTreeNode)) {
            throw new IllegalArgumentException("new child is an ancestor");
        }
        final MutableTreeNode mutableTreeNode2 = (MutableTreeNode)mutableTreeNode.getParent();
        if (mutableTreeNode2 != null) {
            mutableTreeNode2.remove(mutableTreeNode);
        }
        mutableTreeNode.setParent(this);
        if (this.children == null) {
            this.children = new Vector();
        }
        this.children.insertElementAt(mutableTreeNode, n);
    }
    
    public boolean isLeaf() {
        return this.getChildCount() == 0;
    }
    
    public boolean isNodeAncestor(final TreeNode treeNode) {
        if (treeNode == null) {
            return false;
        }
        TreeNode parent = this;
        while (parent != treeNode) {
            if ((parent = parent.getParent()) == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isNodeChild(final TreeNode treeNode) {
        return treeNode != null && this.getChildCount() != 0 && treeNode.getParent() == this;
    }
    
    public boolean isNodeDescendant(final DefaultMutableTreeNode defaultMutableTreeNode) {
        return defaultMutableTreeNode != null && defaultMutableTreeNode.isNodeAncestor(this);
    }
    
    public boolean isNodeRelated(final DefaultMutableTreeNode defaultMutableTreeNode) {
        return defaultMutableTreeNode != null && this.getRoot() == defaultMutableTreeNode.getRoot();
    }
    
    public boolean isNodeSibling(final TreeNode treeNode) {
        boolean b;
        if (treeNode == null) {
            b = false;
        }
        else if (treeNode == this) {
            b = true;
        }
        else {
            final TreeNode parent = this.getParent();
            b = (parent != null && parent == treeNode.getParent());
            if (b && !((DefaultMutableTreeNode)this.getParent()).isNodeChild(treeNode)) {
                throw new InternalError("sibling has different parent");
            }
        }
        return b;
    }
    
    public boolean isRoot() {
        return this.getParent() == null;
    }
    
    public Enumeration pathFromAncestorEnumeration(final TreeNode treeNode) {
        return new PathBetweenNodesEnumeration(treeNode, this);
    }
    
    public Enumeration postorderEnumeration() {
        return new PostorderEnumeration(this);
    }
    
    public Enumeration preorderEnumeration() {
        return new PreorderEnumeration(this);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final Object[] array = (Object[])objectInputStream.readObject();
        if (array.length > 0 && array[0].equals("userObject")) {
            this.userObject = array[1];
        }
    }
    
    public void remove(final int n) {
        final MutableTreeNode mutableTreeNode = (MutableTreeNode)this.getChildAt(n);
        this.children.removeElementAt(n);
        mutableTreeNode.setParent(null);
    }
    
    public void remove(final MutableTreeNode mutableTreeNode) {
        if (mutableTreeNode == null) {
            throw new IllegalArgumentException("argument is null");
        }
        if (!this.isNodeChild(mutableTreeNode)) {
            throw new IllegalArgumentException("argument is not a child");
        }
        this.remove(this.getIndex(mutableTreeNode));
    }
    
    public void removeAllChildren() {
        for (int i = this.getChildCount() - 1; i >= 0; --i) {
            this.remove(i);
        }
    }
    
    public void removeFromParent() {
        final MutableTreeNode mutableTreeNode = (MutableTreeNode)this.getParent();
        if (mutableTreeNode != null) {
            mutableTreeNode.remove(this);
        }
    }
    
    public void setAllowsChildren(final boolean allowsChildren) {
        if (allowsChildren != this.allowsChildren && !(this.allowsChildren = allowsChildren)) {
            this.removeAllChildren();
        }
    }
    
    public void setParent(final MutableTreeNode parent) {
        this.parent = parent;
    }
    
    public void setUserObject(final Object userObject) {
        this.userObject = userObject;
    }
    
    public String toString() {
        if (this.userObject == null) {
            return null;
        }
        return this.userObject.toString();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Object[] array;
        if (this.userObject != null && this.userObject instanceof Serializable) {
            array = new Object[] { "userObject", this.userObject };
        }
        else {
            array = new Object[0];
        }
        objectOutputStream.writeObject(array);
    }
    
    final class PreorderEnumeration implements Enumeration
    {
        protected Stack stack;
        
        public PreorderEnumeration(final TreeNode treeNode) {
            final Vector<TreeNode> vector = new Vector<TreeNode>(1);
            vector.addElement(treeNode);
            (this.stack = new Stack()).push(vector.elements());
        }
        
        public boolean hasMoreElements() {
            return !this.stack.empty() && this.stack.peek().hasMoreElements();
        }
        
        public Object nextElement() {
            final Enumeration<TreeNode> enumeration = this.stack.peek();
            final TreeNode treeNode = enumeration.nextElement();
            final Enumeration children = treeNode.children();
            if (!enumeration.hasMoreElements()) {
                this.stack.pop();
            }
            if (children.hasMoreElements()) {
                this.stack.push(children);
            }
            return treeNode;
        }
    }
    
    final class PostorderEnumeration implements Enumeration
    {
        protected TreeNode root;
        protected Enumeration children;
        protected Enumeration subtree;
        
        public PostorderEnumeration(final TreeNode root) {
            this.root = root;
            this.children = this.root.children();
            this.subtree = DefaultMutableTreeNode.EMPTY_ENUMERATION;
        }
        
        public boolean hasMoreElements() {
            return this.root != null;
        }
        
        public Object nextElement() {
            Object o;
            if (this.subtree.hasMoreElements()) {
                o = this.subtree.nextElement();
            }
            else if (this.children.hasMoreElements()) {
                this.subtree = new PostorderEnumeration(this.children.nextElement());
                o = this.subtree.nextElement();
            }
            else {
                o = this.root;
                this.root = null;
            }
            return o;
        }
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
    
    final class PathBetweenNodesEnumeration implements Enumeration
    {
        protected Stack stack;
        
        public PathBetweenNodesEnumeration(final TreeNode treeNode, final TreeNode treeNode2) {
            if (treeNode == null || treeNode2 == null) {
                throw new IllegalArgumentException("argument is null");
            }
            (this.stack = new Stack()).push(treeNode2);
            TreeNode parent = treeNode2;
            while (parent != treeNode) {
                parent = parent.getParent();
                if (parent == null && treeNode2 != treeNode) {
                    throw new IllegalArgumentException("node " + treeNode + " is not an ancestor of " + treeNode2);
                }
                this.stack.push(parent);
            }
        }
        
        public boolean hasMoreElements() {
            return this.stack.size() > 0;
        }
        
        public Object nextElement() {
            try {
                return this.stack.pop();
            }
            catch (EmptyStackException ex) {
                throw new NoSuchElementException("No more elements");
            }
        }
    }
}
