// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.interfaces.impl.MBeanResource;
import org.jboss.console.manager.interfaces.ManageableResource;
import javax.management.ObjectInstance;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNodeMenuEntryImpl;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.management.QueryExp;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import javax.management.ObjectName;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class EJBModuleLister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = 4815998047707352041L;
    protected static final String JMX_JSR77_DOMAIN = "jboss.management.local";
    
    ResourceTreeNode[] createBeans(final ObjectName parent) throws Exception {
        final ObjectInstance[] insts = this.getMBeansForQuery("jboss.management.local:EJBModule=" + parent.getKeyProperty("name") + ",*", null);
        final ResourceTreeNode[] ejbs = new ResourceTreeNode[insts.length];
        for (int i = 0; i < insts.length; ++i) {
            final ObjectName objName = insts[i].getObjectName();
            final String type = objName.getKeyProperty("j2eeType");
            final String ejbName = objName.getKeyProperty("name");
            String containerName = this.createContainerName(objName);
            if (containerName != null) {
                containerName = this.encode(containerName);
                final String containerUrl = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + containerName;
                final TreeNodeMenuEntry[] menus = { new SimpleTreeNodeMenuEntryImpl("View container in other window", new HttpLinkTreeAction(containerUrl, "_blank")) };
                final String j2eeType = objName.getKeyProperty("j2eeType");
                String filename = "EJB.jsp";
                if (j2eeType.equalsIgnoreCase("StatelessSessionBean")) {
                    filename = "StatelessEjb.jsp";
                }
                else if (j2eeType.equalsIgnoreCase("StatefulSessionBean")) {
                    filename = "StatefulEjb.jsp";
                }
                else if (j2eeType.equalsIgnoreCase("EntityBean")) {
                    filename = "EntityEjb.jsp";
                }
                else if (j2eeType.equalsIgnoreCase("MessageDrivenBean")) {
                    filename = "MdbEjb.jsp";
                }
                ejbs[i] = this.createResourceNode(ejbName, type, "images/bean.gif", filename + "?ObjectName=" + this.encode(objName.toString()) + "&ContainerObjectName=" + containerName, menus, null, null, objName.toString(), insts[i].getClassName());
            }
        }
        return ejbs;
    }
    
    protected String createContainerName(final ObjectName objName) throws Exception {
        final String jndiName = (String)this.mbeanServer.getAttribute(objName, "JndiName");
        final String localJndiName = (String)this.mbeanServer.getAttribute(objName, "LocalJndiName");
        String containerUrl = null;
        final String defaultContainerUrl = "jboss.j2ee:service=EJB,jndiName=" + jndiName;
        final ObjectName defaultObjectName = new ObjectName(defaultContainerUrl);
        final String localContainerUrl = "jboss.j2ee:service=EJB,jndiName=" + localJndiName;
        final ObjectName localObjectName = new ObjectName(localContainerUrl);
        if (this.mbeanServer.isRegistered(defaultObjectName)) {
            this.log.debug("Found container with default JNDI name URL for - " + objName.toString());
            containerUrl = defaultContainerUrl;
        }
        else if (this.mbeanServer.isRegistered(localObjectName)) {
            this.log.debug("Found container with local JNDI name URL for - " + objName.toString());
            containerUrl = localContainerUrl;
        }
        else {
            this.log.warn("Unable to find container for - " + objName.toString());
        }
        return containerUrl;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final ObjectName objName = ((MBeanResource)resource).getObjectName();
            return this.createTreeNode(objName.getKeyProperty("name"), "", "images/beans.gif", "EJBModule.jsp?ObjectName=" + this.encode(objName.toString()), null, null, this.createBeans(objName)).setMasterNode(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.checker);
            return null;
        }
    }
}
