// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.manager;

import javax.management.NotificationFilter;
import javax.naming.Context;
import org.jboss.util.naming.Util;
import org.jboss.jmx.adaptor.rmi.RMIRemoteMBeanProxy;
import javax.naming.InitialContext;
import java.util.Collection;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import java.util.Arrays;
import javax.management.Notification;
import javax.management.MBeanServer;
import java.util.Iterator;
import org.jboss.console.manager.interfaces.impl.HttpLinkTreeAction;
import org.jboss.console.manager.interfaces.impl.MBeanAction;
import javax.management.ObjectName;
import org.jboss.console.manager.interfaces.impl.SeparatorTreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;
import org.jboss.console.manager.interfaces.impl.SimpleTreeNodeMenuEntryImpl;
import org.jboss.console.navtree.RefreshTreeAction;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.manager.interfaces.impl.DefaultTreeInfo;
import java.util.HashSet;
import org.jboss.console.manager.interfaces.TreeInfo;
import org.jboss.console.manager.interfaces.ConsolePlugin;
import org.jboss.system.Registry;
import org.jboss.console.manager.interfaces.impl.MBeanResource;
import java.util.HashMap;
import org.jboss.console.manager.interfaces.ManageableResource;
import java.util.ArrayList;
import javax.management.NotificationListener;
import org.jboss.system.ServiceMBeanSupport;

public class PluginManager extends ServiceMBeanSupport implements PluginManagerMBean, NotificationListener
{
    public static String PLUGIN_MANAGER_NAME;
    protected ArrayList plugins;
    protected ManageableResource bootstrapResource;
    public String jndiName;
    protected long treeVersion;
    protected HashMap currentTrees;
    protected String mainLogoUrl;
    protected String mainLinkUrl;
    protected boolean enableShutdown;
    
    public PluginManager() {
        this.plugins = new ArrayList();
        this.bootstrapResource = null;
        this.jndiName = "console/PluginManager";
        this.treeVersion = 0L;
        this.currentTrees = new HashMap();
        this.mainLogoUrl = "/web-console/images/jboss.gif";
        this.mainLinkUrl = "http://www.jboss.org/forums/";
        this.enableShutdown = true;
    }
    
    public void createService() throws Exception {
        this.bootstrapResource = new MBeanResource(this.getServiceName(), this.getClass().toString());
    }
    
    public void startService() throws Exception {
        this.bindProxyInJndi();
        Registry.bind(PluginManager.PLUGIN_MANAGER_NAME = this.getServiceName().toString(), this);
        this.initNotificationReception();
    }
    
    public void stopService() {
        Registry.unbind(this.getServiceName().toString());
    }
    
    public void registerPlugin(final String consolePluginClassName) throws Exception {
        final Class pluginClass = Thread.currentThread().getContextClassLoader().loadClass(consolePluginClassName);
        final ConsolePlugin plugin = pluginClass.newInstance();
        this.registerPlugin(plugin);
    }
    
    public synchronized void registerPlugin(final ConsolePlugin plugin) {
        this.plugins.add(plugin);
        this.regenerateAdminTree();
    }
    
    public synchronized void unregisterPlugin(final ConsolePlugin plugin) {
        this.plugins.remove(plugin);
        this.regenerateAdminTree();
    }
    
    public synchronized void regenerateAdminTree() {
        this.currentTrees.clear();
    }
    
    public synchronized void regenerateAdminTreeForProfile(final String profile) {
        this.currentTrees.remove(profile);
    }
    
    public synchronized TreeInfo getTreeForProfile(final String profile) {
        TreeInfo currentTree = this.currentTrees.get(profile);
        if (currentTree == null) {
            final HashSet resourcesToManage = new HashSet();
            final TreeInfo result = new DefaultTreeInfo();
            final ArrayList pluginsSubset = this.getPluginsSubsetForProfile(profile);
            final HashSet resourcesAlreadyScanned = new HashSet();
            result.setRootResources(new ManageableResource[] { this.bootstrapResource });
            resourcesToManage.add(this.bootstrapResource);
            while (resourcesToManage.size() > 0) {
                final ManageableResource currentResource = resourcesToManage.iterator().next();
                resourcesToManage.remove(currentResource);
                resourcesAlreadyScanned.add(currentResource);
                final Iterator iter = this.getTreesForResource(currentResource, profile, pluginsSubset);
                while (iter.hasNext()) {
                    final TreeNode subTree = iter.next();
                    result.addTreeToResource(currentResource, subTree);
                    final HashSet subResources = this.findSubResources(subTree);
                    if (subResources != null && subResources.size() > 0) {
                        for (final ManageableResource subRes : subResources) {
                            if (!resourcesAlreadyScanned.contains(subRes)) {
                                resourcesToManage.add(subRes);
                            }
                        }
                    }
                }
            }
            result.setTreeVersion(++this.treeVersion);
            try {
                final TreeNodeMenuEntry[] base = { new SimpleTreeNodeMenuEntryImpl("Update tree", new RefreshTreeAction(false)), new SimpleTreeNodeMenuEntryImpl("Force update tree", new RefreshTreeAction(true)) };
                if (this.enableShutdown) {
                    result.setRootMenus(new TreeNodeMenuEntry[] { base[0], base[1], new SeparatorTreeNodeMenuEntry(), new SimpleTreeNodeMenuEntryImpl("Shutdown JBoss instance", new MBeanAction(new ObjectName("jboss.system:type=Server"), "shutdown", new Object[0], new String[0])), new SimpleTreeNodeMenuEntryImpl("Shutdown and Restart JBoss instance", new MBeanAction(new ObjectName("jboss.system:type=Server"), "exit", new Object[] { new Integer(10) }, new String[] { "int" })), new SimpleTreeNodeMenuEntryImpl("HALT and Restart JBoss instance", new MBeanAction(new ObjectName("jboss.system:type=Server"), "halt", new Object[] { new Integer(10) }, new String[] { "int" })) });
                }
                else {
                    result.setRootMenus(base);
                }
                result.setHomeAction(new HttpLinkTreeAction(this.mainLinkUrl));
                result.setIconUrl(this.mainLogoUrl);
            }
            catch (Exception ex) {}
            currentTree = result;
            this.currentTrees.put(profile, currentTree);
        }
        return currentTree;
    }
    
    public synchronized TreeInfo getUpdateTreeForProfile(final String profile, final long knownVersion) {
        final TreeInfo currentTree = this.currentTrees.get(profile);
        if (this.treeVersion > knownVersion || currentTree == null) {
            return this.getTreeForProfile(profile);
        }
        return null;
    }
    
    public MBeanServer getMBeanServer() {
        return this.server;
    }
    
    public ManageableResource getBootstrapResource() {
        return this.bootstrapResource;
    }
    
    public String getJndiName() {
        return this.jndiName;
    }
    
    public void setJndiName(final String jndiName) {
        this.jndiName = jndiName;
    }
    
    public boolean isEnableShutdown() {
        return this.enableShutdown;
    }
    
    public void setEnableShutdown(final boolean enableShutdown) {
        this.enableShutdown = enableShutdown;
        ++this.treeVersion;
    }
    
    public String getMainLinkUrl() {
        return this.mainLinkUrl;
    }
    
    public void setMainLinkUrl(final String mainLinkUrl) {
        this.mainLinkUrl = mainLinkUrl;
        ++this.treeVersion;
    }
    
    public String getMainLogoUrl() {
        return this.mainLogoUrl;
    }
    
    public void setMainLogoUrl(final String mainLogoUrl) {
        this.mainLogoUrl = mainLogoUrl;
        ++this.treeVersion;
    }
    
    public void handleNotification(final Notification notif, final Object handback) {
        this.regenerateAdminTree();
    }
    
    protected Iterator getTreesForResource(final ManageableResource res, final String profile, final ArrayList pluginsSubset) {
        final ArrayList result = new ArrayList();
        for (int i = 0; i < pluginsSubset.size(); ++i) {
            final ConsolePlugin cp = pluginsSubset.get(i);
            TreeNode node = null;
            try {
                node = cp.getSubTreeForResource(this, profile, res);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            if (node != null) {
                result.add(node);
            }
        }
        return result.iterator();
    }
    
    protected ArrayList getPluginsSubsetForProfile(final String profile) {
        final ArrayList result = new ArrayList();
        for (int i = 0; i < this.plugins.size(); ++i) {
            final ConsolePlugin cp = this.plugins.get(i);
            final String[] set = cp.getSupportedProfiles();
            if (Arrays.asList(set).contains(profile)) {
                result.add(cp);
            }
        }
        return result;
    }
    
    protected HashSet findSubResources(final TreeNode tree) {
        final HashSet result = new HashSet();
        if (tree instanceof ResourceTreeNode) {
            result.add(((ResourceTreeNode)tree).getResource());
        }
        final ResourceTreeNode[] rns = tree.getNodeManagableResources();
        if (rns != null && rns.length > 0) {
            for (int i = 0; i < rns.length; ++i) {
                result.add(rns[i].getResource());
                final HashSet subResult = this.findSubResources(rns[i]);
                if (subResult != null && subResult.size() > 0) {
                    result.addAll(subResult);
                }
            }
        }
        final TreeNode[] ns = tree.getSubNodes();
        if (ns != null && ns.length > 0) {
            for (int j = 0; j < ns.length; ++j) {
                final HashSet subResult2 = this.findSubResources(ns[j]);
                if (subResult2 != null && subResult2.size() > 0) {
                    result.addAll(subResult2);
                }
            }
        }
        return result;
    }
    
    protected void bindProxyInJndi() throws Exception {
        final InitialContext ctx = new InitialContext();
        final Object proxy = RMIRemoteMBeanProxy.create((Class)PluginManagerMBean.class, this.getServiceName(), this.getServer());
        Util.rebind(ctx, this.jndiName, proxy);
    }
    
    protected void initNotificationReception() throws Exception {
        final ObjectName mbsDelegate = new ObjectName("JMImplementation:type=MBeanServerDelegate");
        final NotificationFilter filter = new NotificationFilter() {
            public boolean isNotificationEnabled(final Notification n) {
                return n.getType().equals("JMX.mbean.registered") || n.getType().equals("JMX.mbean.unregistered");
            }
        };
        this.getServer().addNotificationListener(mbsDelegate, this, filter, null);
    }
    
    static {
        PluginManager.PLUGIN_MANAGER_NAME = null;
    }
}
