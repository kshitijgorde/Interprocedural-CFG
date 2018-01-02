// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins;

import org.jboss.monitor.services.ActiveAlarmTableMBean;
import org.jboss.console.manager.interfaces.ManageableResource;
import java.util.Iterator;
import java.util.Set;
import javax.management.MBeanServer;
import org.jboss.console.manager.interfaces.ResourceTreeNode;
import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import java.util.ArrayList;
import javax.management.QueryExp;
import javax.management.ObjectName;
import org.jboss.mx.util.InstanceOfQueryExp;
import org.jboss.console.manager.interfaces.TreeNode;
import org.jboss.console.plugins.helpers.AbstractPluginWrapper;

public class MonitorLister extends AbstractPluginWrapper
{
    private static final long serialVersionUID = 1625760520837838058L;
    
    private TreeNode[] createMonitorSubNodes() throws Exception {
        final MBeanServer mbeanServer = this.getMBeanServer();
        InstanceOfQueryExp queryExp = null;
        queryExp = new InstanceOfQueryExp("org.jboss.monitor.JBossMonitorMBean");
        final Set monitors = mbeanServer.queryNames(null, queryExp);
        final Iterator mbeans = monitors.iterator();
        TreeNode[] result = null;
        final ArrayList monitorNodes = new ArrayList();
        final String[] emptySig = new String[0];
        final Object[] emptyArgs = new Object[0];
        while (mbeans.hasNext()) {
            final ObjectName mbean = mbeans.next();
            final String monitorName = (String)mbeanServer.getAttribute(mbean, "MonitorName");
            final boolean alerted = (boolean)mbeanServer.invoke(mbean, "alerted", emptyArgs, emptySig);
            final String image = "images/service.gif";
            final Object[] args = { "monitors", monitorName, "-service.xml" };
            final String[] signature = { "java.lang.String", "java.lang.String", "java.lang.String" };
            final Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args, signature);
            final boolean persisted = (boolean)rtn;
            String url = "";
            if (persisted) {
                url = "manageThresholdMonitor.jsp?monitorObjectName=" + this.encode(mbean.toString());
            }
            else {
                url = "/jmx-console/HtmlAdaptor?action=inspectMBean&name=" + this.encode(mbean.toString());
            }
            monitorNodes.add(this.createTreeNode(monitorName, "Alert Monitor " + monitorName, image, url, null, null, null));
        }
        if (monitorNodes.size() == 0) {
            result = null;
        }
        else {
            result = monitorNodes.toArray(new TreeNode[monitorNodes.size()]);
        }
        return result;
    }
    
    private TreeNode[] createSnapshotSubNodes() throws Exception {
        final MBeanServer mbeanServer = this.getMBeanServer();
        InstanceOfQueryExp queryExp = null;
        queryExp = new InstanceOfQueryExp("org.jboss.monitor.SnapshotRecordingMonitorMBean");
        final Set monitors = mbeanServer.queryNames(null, queryExp);
        final Iterator mbeans = monitors.iterator();
        TreeNode[] result = null;
        final ArrayList monitorNodes = new ArrayList();
        while (mbeans.hasNext()) {
            final ObjectName mbean = mbeans.next();
            final String monitorName = (String)mbeanServer.getAttribute(mbean, "MonitorName");
            final String url = "manageSnapshot.jsp?monitorObjectName=" + this.encode(mbean.toString());
            final String image = "images/service.gif";
            monitorNodes.add(this.createTreeNode(monitorName, "Snapshot " + monitorName, image, url, null, null, null));
        }
        if (monitorNodes.size() == 0) {
            result = null;
        }
        else {
            result = monitorNodes.toArray(new TreeNode[monitorNodes.size()]);
        }
        return result;
    }
    
    private TreeNode[] createWebSubNodes() throws Exception {
        final TreeNode[] webSubNodes = { this.createTreeNode("Connector scoreboard", "JBossWeb Connectors status scoreboard", "images/smallnet.gif", "status", null, null, null), this.createTreeNode("Full status", "JBossWeb complete status", "images/smallnet.gif", "status?full=true", null, null, null) };
        return webSubNodes;
    }
    
    protected TreeNode getTreeForResource(final String profile, final ManageableResource resource) {
        try {
            final ArrayList subNodeList = new ArrayList();
            if (this.isActiveAlarmTablePresent()) {
                subNodeList.add(this.createTreeNode("Alarm Table", "Alarm Table", "images/smallnet.gif", "listActiveAlarmTable.jsp", null, null, null));
            }
            subNodeList.add(this.createTreeNode("Monitor Alerts", "Monitor Alerts", "images/smallnet.gif", "listMonitors.jsp", null, this.createMonitorSubNodes(), null));
            subNodeList.add(this.createTreeNode("Snapshots", "Snapshot Monitors", "images/smallnet.gif", null, null, this.createSnapshotSubNodes(), null));
            subNodeList.add(this.createTreeNode("Web Status", "JBossWeb Connectors status", "images/smallnet.gif", null, null, this.createWebSubNodes(), null));
            final TreeNode[] subnodes = subNodeList.toArray(new TreeNode[subNodeList.size()]);
            return this.createTreeNode("Monitoring", "Monitoring", "images/smallnet.gif", null, null, subnodes, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private boolean isActiveAlarmTablePresent() {
        final MBeanServer server = this.getMBeanServer();
        return server.isRegistered(ActiveAlarmTableMBean.OBJECT_NAME);
    }
}
