// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import javax.management.ObjectName;
import javax.management.MBeanServer;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import org.jboss.monitor.services.ActiveAlarmTableMBean;
import org.jboss.mx.util.MBeanServerLocator;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;

public class AcknowledgeActiveAlarmsServlet extends HttpServlet
{
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        try {
            final MBeanServer server = MBeanServerLocator.locateJBoss();
            final ObjectName target = ActiveAlarmTableMBean.OBJECT_NAME;
            final String system = (String)server.getAttribute(target, "ServerId");
            final String user = System.getProperty("user.name");
            final String alarmId = req.getParameter("alarmId").trim();
            if (alarmId.equals("*")) {
                server.invoke(target, "acknowledgeAll", new Object[] { user, system }, new String[] { "java.lang.String", "java.lang.String" });
            }
            else {
                server.invoke(target, "acknowledge", new Object[] { alarmId, user, system }, new String[] { "java.lang.String", "java.lang.String", "java.lang.String" });
            }
        }
        catch (Exception ex) {
            req.setAttribute("error", (Object)("Error acknowledging alarms: " + ex.toString()));
        }
        req.getRequestDispatcher("/listActiveAlarmTable.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
}
