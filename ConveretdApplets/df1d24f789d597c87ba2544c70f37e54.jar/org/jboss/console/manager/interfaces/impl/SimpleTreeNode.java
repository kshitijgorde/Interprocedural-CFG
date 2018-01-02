// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.TreeNode;

public class SimpleTreeNode implements TreeNode
{
    protected String name;
    protected String description;
    protected String icon;
    protected TreeAction action;
    protected TreeNodeMenuEntry[] menuEntries;
    protected TreeNode[] subNodes;
    protected ResourceTreeNode[] nodeManagableResources;
    protected boolean isMaster;
    
    public SimpleTreeNode() {
        this.name = null;
        this.description = null;
        this.icon = null;
        this.action = null;
        this.menuEntries = null;
        this.subNodes = null;
        this.nodeManagableResources = null;
        this.isMaster = false;
    }
    
    public SimpleTreeNode(final String name, final String description, final String icon, final TreeAction action, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] nodeManagableResources) {
        this.name = null;
        this.description = null;
        this.icon = null;
        this.action = null;
        this.menuEntries = null;
        this.subNodes = null;
        this.nodeManagableResources = null;
        this.isMaster = false;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.action = action;
        this.menuEntries = menuEntries;
        this.subNodes = subNodes;
        this.nodeManagableResources = nodeManagableResources;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getIcon() {
        return this.icon;
    }
    
    public TreeAction getAction() {
        return this.action;
    }
    
    public TreeNodeMenuEntry[] getMenuEntries() {
        return this.menuEntries;
    }
    
    public TreeNode[] getSubNodes() {
        return this.subNodes;
    }
    
    public ResourceTreeNode[] getNodeManagableResources() {
        return this.nodeManagableResources;
    }
    
    public boolean isMasterNode() {
        return this.isMaster;
    }
    
    public TreeNode setMasterNode(final boolean master) {
        this.isMaster = master;
        return this;
    }
}
