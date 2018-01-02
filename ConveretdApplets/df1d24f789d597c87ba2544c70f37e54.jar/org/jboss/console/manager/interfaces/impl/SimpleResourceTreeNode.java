// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.ResourceTreeNode;

public class SimpleResourceTreeNode extends SimpleTreeNode implements ResourceTreeNode
{
    protected ManageableResource resource;
    protected int visibility;
    
    public SimpleResourceTreeNode() {
        this.resource = null;
        this.visibility = 0;
    }
    
    public SimpleResourceTreeNode(final String name, final String description, final String icon, final TreeAction action, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] nodeManagableResources) {
        super(name, description, icon, action, menuEntries, subNodes, nodeManagableResources);
        this.resource = null;
        this.visibility = 0;
    }
    
    public SimpleResourceTreeNode(final String name, final String description, final String icon, final TreeAction action, final TreeNodeMenuEntry[] menuEntries, final TreeNode[] subNodes, final ResourceTreeNode[] nodeManagableResources, final ManageableResource resource) {
        super(name, description, icon, action, menuEntries, subNodes, nodeManagableResources);
        this.resource = null;
        this.visibility = 0;
        this.resource = resource;
    }
    
    public ManageableResource getResource() {
        return this.resource;
    }
    
    public int getVisibility() {
        return this.visibility;
    }
    
    public ResourceTreeNode setVisibility(final int visibility) {
        this.visibility = visibility;
        return this;
    }
}
