// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.interfaces.ManageableResource;
import javax.management.ObjectInstance;
import javax.management.AttributeNotFoundException;
import javax.management.QueryExp;
import org.jboss.management.j2ee.J2EEServer;
import org.jboss.mx.util.MBeanProxyExt;
import org.jboss.management.j2ee.J2EEServerMBean;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class JSR77Lister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = -5466799611043095874L;
    public static final String[] DEFAULT_SUFFIX_ORDER;
    
    TreeNode createSubResources(final String[] resources) throws Exception {
        final ResourceTreeNode[] deployed = new ResourceTreeNode[resources.length];
        for (int i = 0; i < resources.length; ++i) {
            final ObjectName objectName = new ObjectName(resources[i]);
            deployed[i] = this.createResourceNode(objectName.getKeyProperty("name"), "J2EE Resource", null, null, null, null, null, resources[i].toString(), this.mbeanServer.getMBeanInfo(objectName).getClassName()).setVisibility(2);
        }
        return this.createTreeNode("J2EE Resources", "J2EE Resources", "images/spirale.gif", null, null, null, deployed);
    }
    
    ResourceTreeNode[] createDeployedObjects(final String[] resources) throws Exception {
        final ArrayList deployed = new ArrayList();
        for (int i = 0; i < resources.length; ++i) {
            final ObjectName objectName = new ObjectName(resources[i]);
            deployed.add(this.createResourceNode(objectName.getKeyProperty("name"), "", "images/EspressoMaker.gif", null, null, null, null, resources[i].toString(), this.mbeanServer.getMBeanInfo(objectName).getClassName()).setVisibility(2));
        }
        Collections.sort((List<Object>)deployed, new ListerSorter());
        return deployed.toArray(new ResourceTreeNode[deployed.size()]);
    }
    
    ResourceTreeNode createServer(final String serverName) throws Exception {
        final ObjectName objectName = new ObjectName(serverName);
        final J2EEServerMBean serv = (J2EEServerMBean)MBeanProxyExt.create(J2EEServerMBean.class, objectName, this.getMBeanServer());
        final String[] deployedON = serv.getdeployedObjects();
        final ResourceTreeNode[] subResArray = this.createDeployedObjects(deployedON);
        return this.createResourceNode(serv.getserverVendor() + " - " + serv.getserverVersion(), objectName.getKeyProperty("name"), "images/database.gif", null, null, new TreeNode[] { this.createSubResources(serv.getresources()) }, subResArray, serverName.toString(), J2EEServer.class.getName());
    }
    
    ResourceTreeNode[] createServers(final ObjectName domain) throws Exception {
        final String[] serversObjectNames = (String[])this.getMBeanServer().getAttribute(domain, "servers");
        final ArrayList servers = new ArrayList();
        for (int i = 0; i < serversObjectNames.length; ++i) {
            servers.add(this.createServer(serversObjectNames[i]));
        }
        return servers.toArray(new ResourceTreeNode[servers.size()]);
    }
    
    TreeNode createDomain(final ObjectName domain) throws Exception {
        return this.createTreeNode(domain.getKeyProperty("name"), "", "images/spirale.gif", null, null, null, this.createServers(domain));
    }
    
    TreeNode[] createDomains() {
        final ObjectInstance[] insts = this.getMBeansForQuery("*:j2eeType=J2EEDomain,*", null);
        final ArrayList domainsCreated = new ArrayList();
        for (int i = 0; i < insts.length; ++i) {
            final ObjectName objectName = insts[i].getObjectName();
            try {
                if (objectName.getDomain().equals(objectName.getKeyProperty("name"))) {
                    domainsCreated.add(this.createDomain(objectName));
                }
            }
            catch (AttributeNotFoundException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage() + "; mbean '" + objectName + "' not a proper j2eeType=J2EEDomain");
            }
            catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        final TreeNode[] domains = domainsCreated.toArray(new TreeNode[domainsCreated.size()]);
        return domains;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            return this.createTreeNode("J2EE Domains", "Display JSR-77 Managed Objects", "images/elements32.gif", null, null, this.createDomains(), null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        DEFAULT_SUFFIX_ORDER = new String[] { "ear", "jar", "war", "sar", "rar", "ds.xml", "service.xml", "wsr", "zip" };
    }
    
    public class ListerSorter implements Comparator
    {
        protected String[] suffixOrder;
        
        public ListerSorter(final String[] suffixOrder) {
            this.suffixOrder = suffixOrder;
        }
        
        public ListerSorter(final JSR77Lister jsr77Lister) {
            this(jsr77Lister, JSR77Lister.DEFAULT_SUFFIX_ORDER);
        }
        
        public int compare(final Object o1, final Object o2) {
            return this.getExtensionIndex((ResourceTreeNode)o1) - this.getExtensionIndex((ResourceTreeNode)o2);
        }
        
        public int getExtensionIndex(final ResourceTreeNode node) {
            String name = node.getName();
            if (name == null) {
                name = "";
            }
            int i;
            for (i = 0; i < this.suffixOrder.length && !name.endsWith(this.suffixOrder[i]); ++i) {}
            return i;
        }
    }
}
