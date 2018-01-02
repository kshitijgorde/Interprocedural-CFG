// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.interfaces.impl.MBeanResource;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.management.j2ee.Servlet;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.management.j2ee.WebModuleMBean;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import javax.management.ObjectName;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class WebModuleLister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = -8019251323455453105L;
    protected static final String JMX_JSR77_DOMAIN = "jboss.management.local";
    
    ResourceTreeNode[] createBeans(final ObjectName parent) throws Exception {
        final WebModuleMBean wmProxy = (WebModuleMBean)MBeanProxyExt.create(WebModuleMBean.class, parent, this.getMBeanServer());
        final String[] servletsObjectName = wmProxy.getservlets();
        final ResourceTreeNode[] servlets = new ResourceTreeNode[servletsObjectName.length];
        for (int i = 0; i < servletsObjectName.length; ++i) {
            final ObjectName objectName = new ObjectName(servletsObjectName[i]);
            final String name = objectName.getKeyProperty("name");
            servlets[i] = this.createResourceNode(name, "'" + name + "' Servlet", "images/serviceset.gif", "Servlet.jsp?ObjectName=" + this.encode(objectName.toString()), null, null, null, objectName.toString(), Servlet.class.getName());
        }
        return servlets;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final ObjectName objName = ((MBeanResource)resource).getObjectName();
            return this.createTreeNode(objName.getKeyProperty("name"), "", "images/spirale.gif", "WebModule.jsp?ObjectName=" + this.encode(objName.toString()), null, null, this.createBeans(objName)).setMasterNode(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.checker);
            return null;
        }
    }
}
