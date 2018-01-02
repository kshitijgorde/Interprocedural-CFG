// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeInfo;
import org.jboss.console.manager.interfaces.TreeNode;

public class StdNodeWrapper implements NodeWrapper
{
    TreeNode node;
    NodeWrapper[] sons;
    TreeNode[] realSons;
    TreeInfo master;
    String path;
    
    public StdNodeWrapper(final TreeNode node, final TreeInfo master, final String parentPath) {
        this.node = null;
        this.sons = null;
        this.realSons = null;
        this.master = null;
        this.path = null;
        this.node = node;
        this.master = master;
        this.path = parentPath + "/" + this.node.getName();
        TreeNode[] plugged = null;
        if (node instanceof ResourceTreeNode) {
            plugged = master.getTreesForResource(((ResourceTreeNode)node).getResource());
            final int visibility = ((ResourceTreeNode)node).getVisibility();
            if (visibility == 2 && plugged != null && plugged.length > 0) {
                this.node = this.getMasterFromPluggins(plugged);
                plugged = this.removeMasterFromList(plugged, this.node);
            }
        }
        TreeNode[] res = this.node.getNodeManagableResources();
        TreeNode[] sub = this.node.getSubNodes();
        if (res == null) {
            res = new TreeNode[0];
        }
        if (sub == null) {
            sub = new TreeNode[0];
        }
        if (plugged == null) {
            plugged = new TreeNode[0];
        }
        this.realSons = new TreeNode[res.length + sub.length + plugged.length];
        this.sons = new NodeWrapper[res.length + sub.length + plugged.length];
        for (int i = 0; i < res.length; ++i) {
            this.realSons[i] = res[i];
        }
        for (int i = 0; i < sub.length; ++i) {
            this.realSons[res.length + i] = sub[i];
        }
        for (int i = 0; i < plugged.length; ++i) {
            this.realSons[res.length + sub.length + i] = plugged[i];
        }
    }
    
    public Object getChild(final int index) {
        if (index >= this.sons.length) {
            return null;
        }
        if (this.sons[index] == null) {
            this.sons[index] = new StdNodeWrapper(this.realSons[index], this.master, this.path);
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
    
    public String toString() {
        return this.node.getName();
    }
    
    public String getIconUrl() {
        return this.node.getIcon();
    }
    
    public TreeAction getAssociatedAction() {
        return this.node.getAction();
    }
    
    public String getDescription() {
        return this.node.getDescription();
    }
    
    public TreeNodeMenuEntry[] getMenuEntries() {
        return this.node.getMenuEntries();
    }
    
    public String getPath() {
        return this.path;
    }
    
    protected TreeNode getMasterFromPluggins(final TreeNode[] plugged) {
        for (int i = 0; i < plugged.length; ++i) {
            if (plugged[i].isMasterNode()) {
                return plugged[i];
            }
        }
        return plugged[0];
    }
    
    protected TreeNode[] removeMasterFromList(final TreeNode[] all, final TreeNode main) {
        final TreeNode[] result = new TreeNode[all.length - 1];
        int cursor = 0;
        for (int i = 0; i < all.length; ++i) {
            if (all[i] != main) {
                result[cursor] = all[i];
                ++cursor;
            }
        }
        return result;
    }
}
