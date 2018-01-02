// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.console.manager.interfaces.ManageableResource;
import java.util.Iterator;
import org.jboss.console.plugins.helpers.jmx.DomainData;
import java.util.ArrayList;
import org.jboss.console.plugins.helpers.jmx.Server;
import org.jboss.console.plugins.helpers.jmx.MBeanData;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNodeMenuEntryImpl;
import org.jboss.console.manager.interfaces.impl.GraphMBeanAttributeAction;
import org.jboss.console.manager.interfaces.TreeNode;
import javax.management.ObjectName;
import javax.management.MBeanAttributeInfo;
import java.util.HashSet;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class MBeansLister extends AbstractPluginWrapper
{
    private static HashSet graphableClasses;
    
    TreeNode createJmxAttributeSubResource(final MBeanAttributeInfo attr, final ObjectName mbeanName) throws Exception {
        TreeNodeMenuEntry[] entries = null;
        if (MBeansLister.graphableClasses.contains(attr.getType())) {
            final SimpleTreeNodeMenuEntryImpl entry = new SimpleTreeNodeMenuEntryImpl("graph", new GraphMBeanAttributeAction(mbeanName, attr.getName()));
            final SimpleTreeNodeMenuEntryImpl entry2 = new SimpleTreeNodeMenuEntryImpl("create monitor", new HttpLinkTreeAction("/web-console/createThresholdMonitor.jsp?attribute=" + attr.getName() + "&objectName=" + this.encode(mbeanName.toString())));
            final SimpleTreeNodeMenuEntryImpl entry3 = new SimpleTreeNodeMenuEntryImpl("create snapshot", new HttpLinkTreeAction("/web-console/createSnapshot.jsp?attribute=" + attr.getName() + "&objectName=" + this.encode(mbeanName.toString())));
            entries = new TreeNodeMenuEntry[] { entry, entry2, entry3 };
        }
        else if (attr.getType().equals("String") || attr.getType().equals("java.lang.String")) {
            final SimpleTreeNodeMenuEntryImpl entry = new SimpleTreeNodeMenuEntryImpl("create monitor", new HttpLinkTreeAction("/web-console/createStringThresholdMonitor.jsp?attribute=" + attr.getName() + "&objectName=" + this.encode(mbeanName.toString())));
            entries = new TreeNodeMenuEntry[] { entry };
        }
        return this.createTreeNode(attr.getName(), attr.getDescription(), "images/container.gif", "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + this.encode("" + mbeanName), entries, null, null);
    }
    
    TreeNode createJmxMBeanSubResources(final MBeanData data) throws Exception {
        final String name = "" + data.getObjectName();
        String displayName = data.getName();
        if (displayName == null) {
            final int index = name.indexOf(":");
            displayName = ((index >= 0) ? name.substring(index + 1) : name);
        }
        final MBeanAttributeInfo[] attributes = data.getMetaData().getAttributes();
        final TreeNode[] attrNodes = new TreeNode[attributes.length];
        for (int i = 0; i < attributes.length; ++i) {
            attrNodes[i] = this.createJmxAttributeSubResource(attributes[i], data.getObjectName());
        }
        return this.createTreeNode(displayName, name, "images/server.gif", "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + this.encode(name), null, attrNodes, null);
    }
    
    TreeNode[] createJmxDomainsSubNodes() throws Exception {
        final Iterator mbeans = Server.getDomainData(null);
        TreeNode[] result = null;
        final ArrayList domains = new ArrayList();
        while (mbeans.hasNext()) {
            final DomainData domainData = mbeans.next();
            final String domainName = domainData.getDomainName();
            final MBeanData[] data = domainData.getData();
            final TreeNode[] subResources = new TreeNode[data.length];
            for (int d = 0; d < data.length; ++d) {
                subResources[d] = this.createJmxMBeanSubResources(data[d]);
            }
            final TreeNodeMenuEntry[] menu = this.createMenus(new String[] { "Number of MBeans: " + data.length, null });
            domains.add(this.createTreeNode(domainName, "MBeans for domain " + domainName, "images/serviceset.gif", null, menu, subResources, null));
        }
        if (domains.size() == 0) {
            result = null;
        }
        else {
            result = domains.toArray(new TreeNode[domains.size()]);
        }
        return result;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            return this.createTreeNode("JMX MBeans", "Display all JMX MBeans", "images/flash.gif", "/jmx-console/HtmlAdaptor?action=displayMBeans", null, this.createJmxDomainsSubNodes(), null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        (MBeansLister.graphableClasses = new HashSet()).add("java.lang.Integer");
        MBeansLister.graphableClasses.add("java.lang.Short");
        MBeansLister.graphableClasses.add("java.lang.Double");
        MBeansLister.graphableClasses.add("java.lang.Float");
        MBeansLister.graphableClasses.add("java.lang.Long");
        MBeansLister.graphableClasses.add("int");
        MBeansLister.graphableClasses.add("short");
        MBeansLister.graphableClasses.add("double");
        MBeansLister.graphableClasses.add("float");
        MBeansLister.graphableClasses.add("long");
    }
}
