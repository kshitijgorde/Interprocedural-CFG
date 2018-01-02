// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.management.j2ee.J2EEApplicationMBean;
import org.jboss.console.manager.interfaces.impl.MBeanResource;
import org.jboss.console.manager.interfaces.ManageableResource;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class J2EEAppLister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = 4168885656223716764L;
    protected static final String JMX_JSR77_DOMAIN = "jboss.management.local";
    
    ResourceTreeNode[] createModules(final String[] modules) throws Exception {
        final ResourceTreeNode[] deployed = new ResourceTreeNode[modules.length];
        for (int i = 0; i < modules.length; ++i) {
            final ObjectName objectName = new ObjectName(modules[i]);
            deployed[i] = this.createResourceNode(objectName.getKeyProperty("name"), "", "images/EspressoMaker.gif", null, null, null, null, modules[i].toString(), this.mbeanServer.getMBeanInfo(objectName).getClassName()).setVisibility(2);
        }
        return deployed;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final ObjectName objName = ((MBeanResource)resource).getObjectName();
            final J2EEApplicationMBean appProxy = (J2EEApplicationMBean)MBeanProxyExt.create(J2EEApplicationMBean.class, objName, this.getMBeanServer());
            return this.createTreeNode(objName.getKeyProperty("name"), "", "images/EspressoMaker.gif", "J2EEApp.jsp?ObjectName=" + this.encode(objName.toString()), null, null, this.createModules(appProxy.getmodules())).setMasterNode(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(this.checker);
            return null;
        }
    }
}
