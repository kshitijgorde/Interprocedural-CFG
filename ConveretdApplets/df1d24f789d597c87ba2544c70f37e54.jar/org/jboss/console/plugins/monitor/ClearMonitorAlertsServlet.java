// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import java.util.Iterator;
import java.util.Set;
import javax.management.MBeanServer;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.management.QueryExp;
import javax.management.ObjectName;
import org.jboss.mx.util.InstanceOfQueryExp;
import org.jboss.mx.util.MBeanServerLocator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class ClearMonitorAlertsServlet extends HttpServlet
{
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
            InstanceOfQueryExp queryExp = null;
            queryExp = new InstanceOfQueryExp("org.jboss.monitor.JBossMonitorMBean");
            final Set monitors = mbeanServer.queryNames(null, queryExp);
            for (final ObjectName moname : monitors) {
                final Object[] nullArgs = new Object[0];
                final String[] nullSig = new String[0];
                final boolean alerted = (boolean)mbeanServer.invoke(moname, "alerted", nullArgs, nullSig);
                if (alerted) {
                    mbeanServer.invoke(moname, "clearAlert", nullArgs, nullSig);
                }
            }
        }
        catch (Exception ex) {
            req.setAttribute("error", (Object)("Error clearing alerts: " + ex.toString()));
        }
        req.getRequestDispatcher("/listMonitors.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
}
