// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.ManageableResource;
import java.util.Collection;
import java.util.Arrays;
import java.util.Vector;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeInfo;

public class RootWrapper implements NodeWrapper
{
    TreeInfo tree;
    NodeWrapper[] sons;
    TreeNode[] realSons;
    
    public RootWrapper(final TreeInfo tree) {
        this.tree = null;
        this.sons = null;
        this.realSons = null;
        this.tree = tree;
        final Vector nodes = new Vector();
        final ManageableResource[] roots = tree.getRootResources();
        for (int i = 0; i < roots.length; ++i) {
            final ManageableResource mr = roots[i];
            final TreeNode[] ns = tree.getTreesForResource(mr);
            if (ns != null && ns.length > 0) {
                nodes.addAll(Arrays.asList(ns));
            }
        }
        this.realSons = new TreeNode[nodes.size()];
        this.sons = new NodeWrapper[nodes.size()];
        for (int i = 0; i < this.realSons.length; ++i) {
            this.realSons[i] = nodes.elementAt(i);
        }
    }
    
    public Object getChild(final int index) {
        if (index >= this.sons.length) {
            return null;
        }
        if (this.sons[index] == null) {
            this.sons[index] = new StdNodeWrapper(this.realSons[index], this.tree, "");
        }
        return this.sons[index];
    }
    
    public int getChildCount() {
        return this.realSons.length;
    }
    
    public int getIndexOfChild(final Object child) {
        for (int i = 0; i < this.sons.length; ++i) {
            if (this.sons[i] == child) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean isLeaf() {
        return this.sons.length == 0;
    }
    
    public String getIconUrl() {
        return this.tree.getIconUrl();
    }
    
    public String toString() {
        return "JBoss Management Console";
    }
    
    public TreeAction getAssociatedAction() {
        return this.tree.getHomeAction();
    }
    
    public String getDescription() {
        return this.tree.getDescription();
    }
    
    public TreeNodeMenuEntry[] getMenuEntries() {
        return this.tree.getRootMenus();
    }
    
    public String getPath() {
        return "";
    }
}
