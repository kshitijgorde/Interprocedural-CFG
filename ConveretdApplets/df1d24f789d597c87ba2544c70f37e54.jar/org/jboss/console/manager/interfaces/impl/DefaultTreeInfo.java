// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager.interfaces.impl;

import java.util.Iterator;
import java.util.ArrayList;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import java.util.HashMap;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.TreeInfo;

public class DefaultTreeInfo implements TreeInfo
{
    protected ManageableResource[] roots;
    protected HashMap resources;
    protected TreeAction homeAction;
    protected String jbossVersion;
    protected long version;
    protected TreeNodeMenuEntry[] rootMenus;
    protected String iconUrl;
    
    public DefaultTreeInfo() {
        this.roots = null;
        this.resources = new HashMap();
        this.homeAction = null;
        this.jbossVersion = null;
        this.version = 0L;
        this.rootMenus = new TreeNodeMenuEntry[0];
        this.iconUrl = null;
        final Package jbossPackage = Package.getPackage("org.jboss");
        this.jbossVersion = jbossPackage.getImplementationTitle() + " " + jbossPackage.getImplementationVersion();
    }
    
    public ManageableResource[] getRootResources() {
        return this.roots;
    }
    
    public void setRootResources(final ManageableResource[] roots) {
        this.roots = roots;
    }
    
    public synchronized TreeNode[] getTreesForResource(final ManageableResource resource) {
        final ArrayList content = this.resources.get(resource);
        if (content == null || content.size() == 0) {
            return null;
        }
        final TreeNode[] result = new TreeNode[content.size()];
        return content.toArray(result);
    }
    
    public synchronized void addTreeToResource(final ManageableResource resource, final TreeNode tree) {
        ArrayList content = this.resources.get(resource);
        if (content == null || content.size() == 0) {
            content = new ArrayList();
            this.resources.put(resource, content);
        }
        if (!content.contains(tree)) {
            content.add(tree);
        }
    }
    
    public TreeAction getHomeAction() {
        return this.homeAction;
    }
    
    public void setHomeAction(final TreeAction homeAction) {
        this.homeAction = homeAction;
    }
    
    public String getDescription() {
        return this.jbossVersion;
    }
    
    public void setRootMenus(final TreeNodeMenuEntry[] menus) {
        this.rootMenus = menus;
    }
    
    public TreeNodeMenuEntry[] getRootMenus() {
        return this.rootMenus;
    }
    
    public String toString() {
        String result = "Root: " + this.roots + "\n";
        for (final ManageableResource key : this.resources.keySet()) {
            final ArrayList content = this.resources.get(key);
            result = result + "  Key: " + key + "\n";
            for (int i = 0; i < content.size(); ++i) {
                result = result + "    Value: " + content.get(i);
            }
            result += "  ----\n";
        }
        return result;
    }
    
    public long getTreeVersion() {
        return this.version;
    }
    
    public void setTreeVersion(final long version) {
        this.version = version;
    }
    
    public String getIconUrl() {
        return this.iconUrl;
    }
    
    public void setIconUrl(final String url) {
        this.iconUrl = url;
    }
}
