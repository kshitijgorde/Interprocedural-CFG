// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import javax.management.MBeanServer;
import java.util.ArrayList;
import org.jboss.monitor.StringThresholdMonitor;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import org.jboss.mx.util.MBeanServerLocator;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import javax.servlet.http.HttpServlet;

public class CreateStringThresholdMonitorServlet extends HttpServlet
{
    static final long serialVersionUID = -916010894779881186L;
    private static final Logger log;
    
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void error(final String msg, final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", (Object)("Error: " + msg));
        req.getRequestDispatcher("/createStringThresholdMonitor.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String monitorName = req.getParameter("monitorName").trim();
        CreateStringThresholdMonitorServlet.log.debug(monitorName);
        final String objectName = req.getParameter("objectName").trim();
        CreateStringThresholdMonitorServlet.log.debug(objectName);
        final MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
        ObjectName oname = null;
        try {
            oname = new ObjectName(objectName);
        }
        catch (MalformedObjectNameException e) {
            this.error("Malformed ObjectName ", req, resp);
            return;
        }
        final String attribute = req.getParameter("attribute").trim();
        CreateStringThresholdMonitorServlet.log.debug(attribute);
        Object val = null;
        try {
            val = mbeanServer.getAttribute(oname, attribute);
        }
        catch (Exception e2) {
            this.error("Unable to pull attribute value from MBean, does the attribute exist? ", req, resp);
            return;
        }
        final String threshold = req.getParameter("threshold").trim();
        CreateStringThresholdMonitorServlet.log.debug(threshold);
        final boolean enabled = req.getParameter("enabled") != null;
        CreateStringThresholdMonitorServlet.log.debug("Enabled: " + enabled);
        final boolean persisted = req.getParameter("persisted") != null;
        CreateStringThresholdMonitorServlet.log.debug("Persisted: " + persisted);
        final String period = req.getParameter("period").trim();
        CreateStringThresholdMonitorServlet.log.debug(period);
        long timePeriod = 0L;
        try {
            timePeriod = Long.parseLong(period);
        }
        catch (NumberFormatException e3) {
            this.error("Illegal format for watch period.", req, resp);
            return;
        }
        final boolean equality = req.getParameter("equality") != null;
        final String[] alerts = req.getParameterValues("alerts");
        if (alerts == null) {
            this.error("you must select at least one alert listener", req, resp);
            return;
        }
        if (persisted) {
            try {
                final Object[] args = { "monitors", monitorName, "-service.xml" };
                final String[] signature = { "java.lang.String", "java.lang.String", "java.lang.String" };
                final Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args, signature);
                if (rtn) {
                    this.error("Monitor with this name already exists", req, resp);
                    return;
                }
            }
            catch (Exception ex) {
                this.error("Failed to determine if monitor with that name already exists: " + ex.toString(), req, resp);
                return;
            }
            final StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<server>\n");
            xml.append("<mbean code=\"org.jboss.monitor.StringThresholdMonitor\"\n");
            xml.append("       name=\"jboss.monitor:service=" + monitorName.replace(' ', '_') + "\">\n");
            xml.append("  <attribute name=\"MonitorName\">" + monitorName + "</attribute>\n");
            try {
                if (mbeanServer.isInstanceOf(oname, "org.jboss.system.ServiceMBean")) {
                    xml.append("  <depends optional-attribute-name=\"ObservedObject\">" + objectName + "</depends>\n");
                }
                else {
                    xml.append("  <attribute name=\"ObservedObject\">" + objectName + "</attribute>\n");
                }
            }
            catch (Exception ex2) {
                this.error("failed creating service: " + ex2.toString(), req, resp);
                return;
            }
            xml.append("  <attribute name=\"ObservedAttribute\">" + attribute + "</attribute>\n");
            xml.append("  <depends-list optional-attribute-name=\"AlertListeners\">\n");
            for (int i = 0; i < alerts.length; ++i) {
                xml.append("      <depends-list-element>");
                xml.append(alerts[i].trim());
                xml.append("      </depends-list-element>\n");
            }
            xml.append("  </depends-list>\n");
            xml.append("  <attribute name=\"Threshold\">" + threshold + "</attribute>\n");
            xml.append("  <attribute name=\"Period\">" + timePeriod + "</attribute>\n");
            xml.append("  <attribute name=\"EqualityTriggersAlert\">" + equality + "</attribute>\n");
            xml.append("  <attribute name=\"Enabled\">" + enabled + "</attribute>\n");
            xml.append("</mbean>\n</server>");
            try {
                final Object[] args2 = { "monitors", monitorName, "-service.xml", xml.toString(), Boolean.TRUE };
                final String[] signature2 = { "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "boolean" };
                mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "store", args2, signature2);
            }
            catch (Exception ex2) {
                this.error("Failed to create persisted file: " + ex2.toString(), req, resp);
                return;
            }
        }
        else {
            try {
                final StringThresholdMonitor monitor = new StringThresholdMonitor();
                monitor.setMonitorName(monitorName);
                monitor.setObservedObject(oname);
                monitor.setObservedAttribute(attribute);
                monitor.setThreshold(threshold);
                monitor.setEnabled((boolean)new Boolean(enabled));
                monitor.setPeriod(timePeriod);
                monitor.setEqualityTriggersAlert(equality);
                final ArrayList list = new ArrayList();
                for (int j = 0; j < alerts.length; ++j) {
                    list.add(new ObjectName(alerts[j]));
                }
                monitor.setAlertListeners(list);
                mbeanServer.registerMBean(monitor, new ObjectName("jboss.monitor:name=" + monitorName));
                monitor.create();
                monitor.start();
            }
            catch (Exception ex) {
                this.error("Failed to create non-persisted monitor: " + ex.toString(), req, resp);
            }
        }
        req.getRequestDispatcher("/createStringThresholdMonitorSummary.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    static {
        log = Logger.getLogger(CreateStringThresholdMonitorServlet.class);
    }
}
