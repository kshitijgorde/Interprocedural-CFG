// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import javax.management.ObjectInstance;
import javax.management.QueryExp;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNode;
import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.MBeanResource;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class JMSLister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = -2428954274429502892L;
    protected static final String JMX_JSR77_DOMAIN = "jboss.management.local";
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final ObjectName objName = ((MBeanResource)resource).getObjectName();
            final SimpleTreeNode node = this.createTreeNode(objName.getKeyProperty("name"), "", "images/spirale.gif", null, null, this.createDestinations(), null);
            node.setMasterNode(true);
            return node;
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.checker);
            return null;
        }
    }
    
    private TreeNode[] createDestinations() throws Exception {
        final TreeNode[] destinations = { this.createTreeNode("Queues", "", "images/spirale.gif", null, null, null, this.createDestinationItems("Queue")), this.createTreeNode("Topics", "", "images/spirale.gif", null, null, null, this.createDestinationItems("Topic")) };
        return destinations;
    }
    
    private ResourceTreeNode[] createDestinationItems(final String type) throws Exception {
        final ObjectInstance[] insts = this.getMBeansForQuery("jboss.mq.destination:service=" + type + ",*", null);
        final ResourceTreeNode[] destinations = new ResourceTreeNode[insts.length];
        for (int i = 0; i < insts.length; ++i) {
            final ObjectName objName = insts[i].getObjectName();
            destinations[i] = this.createDestinationItem(objName);
        }
        return destinations;
    }
    
    private ResourceTreeNode createDestinationItem(final ObjectName objName) throws Exception {
        final String destinationName = objName.getKeyProperty("name");
        final String type = objName.getKeyProperty("service");
        final String className = this.mbeanServer.getMBeanInfo(objName).getClassName();
        String fileName = "";
        if (type.equalsIgnoreCase("Queue")) {
            fileName = "Queue.jsp";
        }
        else if (type.equalsIgnoreCase("Topic")) {
            fileName = "Topic.jsp";
        }
        final ResourceTreeNode item = this.createResourceNode(destinationName, type, "images/serviceset.gif", fileName + "?ObjectName=" + this.encode(objName.toString()), null, null, null, objName.toString(), className);
        return item;
    }
}
