// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import javax.management.MBeanServer;
import java.net.URLEncoder;
import org.jboss.monitor.SnapshotRecordingMonitor;
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

public class CreateSnapshotServlet extends HttpServlet
{
    static final long serialVersionUID = -6005190747212975396L;
    private static final Logger log;
    
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void error(final String msg, final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", (Object)("Error: " + msg));
        req.getRequestDispatcher("/createSnapshot.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final String monitorName = req.getParameter("monitorName").trim();
        CreateSnapshotServlet.log.debug(monitorName);
        final String objectName = req.getParameter("objectName").trim();
        CreateSnapshotServlet.log.debug(objectName);
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
        CreateSnapshotServlet.log.debug(attribute);
        Object val = null;
        try {
            val = mbeanServer.getAttribute(oname, attribute);
        }
        catch (Exception e2) {
            this.error("Unable to pull attribute value from MBean, does the attribute exist? ", req, resp);
            return;
        }
        final String period = req.getParameter("period").trim();
        CreateSnapshotServlet.log.debug(period);
        long timePeriod = 0L;
        try {
            timePeriod = Long.parseLong(period);
        }
        catch (NumberFormatException e3) {
            this.error("Illegal format for watch period.", req, resp);
            return;
        }
        try {
            final SnapshotRecordingMonitor monitor = new SnapshotRecordingMonitor();
            monitor.setMonitorName(monitorName);
            monitor.setObservedObject(oname);
            monitor.setObservedAttribute(attribute);
            monitor.setPeriod(timePeriod);
            final ObjectName sname = new ObjectName("jboss.snapshot:name=" + monitorName);
            mbeanServer.registerMBean(monitor, sname);
            resp.sendRedirect("/web-console/manageSnapshot.jsp?monitorObjectName=" + URLEncoder.encode(sname.toString()));
        }
        catch (Exception ex) {
            this.error("Failed to create non-persisted monitor: " + ex.toString(), req, resp);
        }
    }
    
    static {
        log = Logger.getLogger(CreateSnapshotServlet.class);
    }
}
