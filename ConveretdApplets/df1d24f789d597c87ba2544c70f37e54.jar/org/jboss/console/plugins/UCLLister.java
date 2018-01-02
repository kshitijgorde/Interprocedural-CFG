// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import javax.management.ObjectInstance;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class UCLLister extends AbstractPluginWrapper
{
    ResourceTreeNode createUCLSubResource(final ObjectInstance instance) throws Exception {
        final String uclName = instance.getObjectName().getKeyProperty("UCL");
        return this.createResourceNode("UCL " + uclName, "UCL with id " + uclName, "images/service.gif", "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + this.encode(instance.getObjectName().toString()), null, null, null, instance.getObjectName().toString(), instance.getClassName());
    }
    
    ResourceTreeNode[] createUCLSubResources() throws Exception {
        final ObjectInstance[] insts = this.getMBeansForClass("jmx.loading:*", "org.jboss.mx.loading.UnifiedClassLoader3");
        final ResourceTreeNode[] result = new ResourceTreeNode[insts.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = this.createUCLSubResource(insts[i]);
        }
        return result;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            return this.createTreeNode("Unified ClassLoaders", "Display all JBoss UCLs", "images/recycle.gif", null, null, null, this.createUCLSubResources());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
