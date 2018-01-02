// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import javax.management.MBeanServer;
import java.util.ArrayList;
import javax.management.Attribute;
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

public class ManageStringThresholdMonitorServlet extends HttpServlet
{
    static final long serialVersionUID = -7203943908702660859L;
    private static final Logger log;
    
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void error(final String msg, final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", (Object)("Error: " + msg));
        req.getRequestDispatcher("/manageThresholdMonitor.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String action = req.getParameter("action");
        if (action == null) {
            this.error("unknown action: ", req, resp);
            return;
        }
        final String monitorName = req.getParameter("monitorName").trim();
        final MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
        ObjectName oname = null;
        String attribute = null;
        ObjectName monitorObjectName;
        try {
            monitorObjectName = new ObjectName(req.getParameter("monitorObjectName"));
        }
        catch (Exception ex) {
            this.error("Malformed Monitor ObjectName: " + req.getParameter("monitorObjectName"), req, resp);
            return;
        }
        if (action.trim().equals("Clear Alert")) {
            try {
                ManageStringThresholdMonitorServlet.log.debug("Clearing Alert for monitor: " + monitorObjectName.toString());
                final String[] signature = new String[0];
                final Object[] args = new Object[0];
                mbeanServer.invoke(monitorObjectName, "clearAlert", args, signature);
                req.setAttribute("error", (Object)"Alert cleared");
                req.getRequestDispatcher("/manageStringThresholdMonitor.jsp").forward((ServletRequest)req, (ServletResponse)resp);
                return;
            }
            catch (Exception ex) {
                this.error("Failed to Clear Alert: " + ex.toString(), req, resp);
                return;
            }
        }
        if (action.trim().equals("Remove Monitor")) {
            try {
                ManageStringThresholdMonitorServlet.log.debug("removing monitor: " + monitorObjectName.toString());
                final Object[] args2 = { "monitors", monitorName, "-service.xml" };
                final String[] signature2 = { "java.lang.String", "java.lang.String", "java.lang.String" };
                mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "remove", args2, signature2);
                req.getRequestDispatcher("/ServerInfo.jsp").forward((ServletRequest)req, (ServletResponse)resp);
            }
            catch (Exception ex) {
                this.error("Failed to Remove Monitor: " + ex.toString(), req, resp);
            }
            return;
        }
        try {
            monitorObjectName = new ObjectName(req.getParameter("monitorObjectName"));
            oname = (ObjectName)mbeanServer.getAttribute(monitorObjectName, "ObservedObject");
            attribute = (String)mbeanServer.getAttribute(monitorObjectName, "ObservedAttribute");
        }
        catch (Exception ex) {
            this.error("Malformed Monitor ObjectName: " + req.getParameter("monitorObjectName"), req, resp);
            return;
        }
        final String threshold = req.getParameter("threshold").trim();
        ManageStringThresholdMonitorServlet.log.debug(threshold);
        final boolean enabled = req.getParameter("enabled") != null;
        ManageStringThresholdMonitorServlet.log.debug("Enabled: " + enabled);
        final boolean persisted = req.getParameter("persisted") != null;
        final boolean equality = req.getParameter("equality") != null;
        ManageStringThresholdMonitorServlet.log.debug("Persisted: " + persisted);
        final String period = req.getParameter("period").trim();
        ManageStringThresholdMonitorServlet.log.debug(period);
        long timePeriod = 0L;
        try {
            timePeriod = Long.parseLong(period);
        }
        catch (NumberFormatException e) {
            this.error("Illegal format for watch period.", req, resp);
            return;
        }
        final String[] alerts = req.getParameterValues("alerts");
        if (alerts == null) {
            this.error("you must select at least one alert listener", req, resp);
            return;
        }
        try {
            mbeanServer.setAttribute(monitorObjectName, new Attribute("Threshold", threshold));
            mbeanServer.setAttribute(monitorObjectName, new Attribute("Enabled", new Boolean(enabled)));
            mbeanServer.setAttribute(monitorObjectName, new Attribute("Period", new Long(timePeriod)));
            mbeanServer.setAttribute(monitorObjectName, new Attribute("EqualityTriggersAlert", new Boolean(equality)));
            final ArrayList list = new ArrayList();
            for (int i = 0; i < alerts.length; ++i) {
                list.add(new ObjectName(alerts[i]));
            }
            mbeanServer.setAttribute(monitorObjectName, new Attribute("AlertListeners", list));
        }
        catch (Exception ex2) {
            this.error("Failed to update mbean monitor: " + ex2.toString(), req, resp);
            return;
        }
        if (persisted) {
            try {
                final Object[] args3 = { "monitors", monitorName, "-service.xml" };
                final String[] signature3 = { "java.lang.String", "java.lang.String", "java.lang.String" };
                final Object rtn = mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "isStored", args3, signature3);
                if (!(boolean)rtn) {
                    this.error("Monitor with this name doesn't exist in repository", req, resp);
                    return;
                }
            }
            catch (Exception ex2) {
                this.error("Failed to determine if monitor with that name already exists: " + ex2.toString(), req, resp);
                return;
            }
            final StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            xml.append("<server>\n");
            xml.append("<mbean code=\"org.jboss.monitor.StringThresholdMonitor\"\n");
            xml.append("       name=\"jboss.monitor:service=" + monitorName.replace(' ', '_') + "\">\n");
            xml.append("  <attribute name=\"MonitorName\">" + monitorName + "</attribute>\n");
            try {
                if (mbeanServer.isInstanceOf(oname, "org.jboss.system.ServiceMBean")) {
                    xml.append("  <depends optional-attribute-name=\"ObservedObject\">" + oname + "</depends>\n");
                }
                else {
                    xml.append("  <attribute name=\"ObservedObject\">" + oname + "</attribute>\n");
                }
            }
            catch (Exception ex3) {
                this.error("failed creating service: " + ex3.toString(), req, resp);
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
                final Object[] args4 = { "monitors", monitorName, "-service.xml", xml.toString(), Boolean.TRUE };
                final String[] signature4 = { "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "boolean" };
                mbeanServer.invoke(new ObjectName("jboss.admin:service=DeploymentFileRepository"), "store", args4, signature4);
            }
            catch (Exception ex3) {
                this.error("Failed to create persisted file: " + ex3.toString(), req, resp);
                return;
            }
        }
        req.setAttribute("error", (Object)"Update complete!");
        req.getRequestDispatcher("/manageThresholdMonitor.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    static {
        log = Logger.getLogger(ManageStringThresholdMonitorServlet.class);
    }
}
