// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import javax.swing.event.TreeModelEvent;
import javax.swing.tree.TreePath;
import javax.swing.event.TreeModelListener;
import javax.management.ObjectName;
import java.util.Vector;
import org.jboss.console.manager.interfaces.TreeInfo;
import javax.swing.tree.TreeModel;

public class ConsoleTreeModel implements TreeModel
{
    protected TreeInfo tree;
    protected RootWrapper root;
    protected Vector treeModelListeners;
    protected TreeContext context;
    protected TreeReopenerMemory reopenerMemory;
    protected ObjectName targetPM;
    
    public ConsoleTreeModel(final TreeContext context) throws Exception {
        this.tree = null;
        this.root = null;
        this.treeModelListeners = new Vector();
        this.context = null;
        this.reopenerMemory = null;
        this.targetPM = null;
        this.targetPM = new ObjectName(context.getServiceJmxName());
        this.context = context;
        this.tree = this.loadTree();
        this.root = new RootWrapper(this.tree);
    }
    
    public boolean refreshTree(final boolean force) throws Exception {
        TreeInfo tmpTree = null;
        if (!force && this.tree != null) {
            tmpTree = this.conditionalLoadTree(this.tree.getTreeVersion());
        }
        else {
            tmpTree = this.loadTree();
        }
        if (tmpTree != null) {
            final RootWrapper oldRoot = this.root;
            this.tree = tmpTree;
            this.root = new RootWrapper(this.tree);
            this.fireTreeStructureChanged(oldRoot);
            return true;
        }
        return false;
    }
    
    public TreeInfo conditionalLoadTree(final long version) throws Exception {
        return (TreeInfo)this.context.getRemoteMBeanInvoker().invoke(this.targetPM, "getUpdateTreeForProfile", new Object[] { "WEB", new Long(version) }, new String[] { "java.lang.String", "long" });
    }
    
    public TreeInfo loadTree() throws Exception {
        return (TreeInfo)this.context.getRemoteMBeanInvoker().invoke(this.targetPM, "getTreeForProfile", new Object[] { "WEB" }, new String[] { "java.lang.String" });
    }
    
    public void addTreeModelListener(final TreeModelListener l) {
        this.treeModelListeners.addElement(l);
    }
    
    public Object getChild(final Object parent, final int index) {
        final NodeWrapper n = (NodeWrapper)parent;
        return n.getChild(index);
    }
    
    public int getChildCount(final Object parent) {
        final NodeWrapper n = (NodeWrapper)parent;
        return n.getChildCount();
    }
    
    public int getIndexOfChild(final Object parent, final Object child) {
        final NodeWrapper n = (NodeWrapper)parent;
        return n.getIndexOfChild(child);
    }
    
    public Object getRoot() {
        return this.root;
    }
    
    public boolean isLeaf(final Object node) {
        final NodeWrapper n = (NodeWrapper)node;
        return n.isLeaf();
    }
    
    public void removeTreeModelListener(final TreeModelListener l) {
        this.treeModelListeners.removeElement(l);
    }
    
    public void valueForPathChanged(final TreePath path, final Object newValue) {
    }
    
    protected void fireTreeStructureChanged(final RootWrapper oldRoot) {
        final int len = this.treeModelListeners.size();
        final TreeModelEvent e = new TreeModelEvent(this, new Object[] { oldRoot });
        for (int i = 0; i < len; ++i) {
            this.treeModelListeners.elementAt(i).treeStructureChanged(e);
        }
    }
}
