// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.plugins.monitor;

import java.io.OutputStream;
import org.jfree.chart.JFreeChart;
import java.io.PrintWriter;
import javax.management.MBeanServer;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.XYDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.DefaultTableXYDataset;
import org.jfree.data.XYSeries;
import java.util.ArrayList;
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

public class ManageSnapshotServlet extends HttpServlet
{
    static final long serialVersionUID = 128303790912009915L;
    private static final Logger log;
    
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        this.doit(req, resp);
    }
    
    protected void error(final String msg, final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", (Object)("Error: " + msg));
        req.getRequestDispatcher("/manageSnapshot.jsp").forward((ServletRequest)req, (ServletResponse)resp);
    }
    
    protected void doit(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            this.error("unknown action: ", req, resp);
            return;
        }
        action = action.trim();
        final MBeanServer mbeanServer = MBeanServerLocator.locateJBoss();
        String attribute = null;
        ObjectName monitorObjectName;
        try {
            monitorObjectName = new ObjectName(req.getParameter("monitorObjectName"));
            attribute = (String)mbeanServer.getAttribute(monitorObjectName, "ObservedAttribute");
        }
        catch (Exception ex2) {
            this.error("Malformed Monitor ObjectName: " + req.getParameter("monitorObjectName"), req, resp);
            return;
        }
        if (action.equals("Start Snapshot")) {
            final Object[] nullArgs = new Object[0];
            final String[] nullSig = new String[0];
            try {
                mbeanServer.invoke(monitorObjectName, "startSnapshot", nullArgs, nullSig);
            }
            catch (Exception ex) {
                this.error("Problem invoking startSnapshot: " + ex.toString(), req, resp);
                return;
            }
            req.getRequestDispatcher("/manageSnapshot.jsp").forward((ServletRequest)req, (ServletResponse)resp);
            return;
        }
        if (action.equals("Stop Snapshot")) {
            final Object[] nullArgs = new Object[0];
            final String[] nullSig = new String[0];
            try {
                mbeanServer.invoke(monitorObjectName, "endSnapshot", nullArgs, nullSig);
            }
            catch (Exception ex) {
                this.error("Problem invoking endSnapshot: " + ex.toString(), req, resp);
                return;
            }
            req.getRequestDispatcher("/manageSnapshot.jsp").forward((ServletRequest)req, (ServletResponse)resp);
            return;
        }
        if (action.equals("Clear Dataset")) {
            final Object[] nullArgs = new Object[0];
            final String[] nullSig = new String[0];
            try {
                mbeanServer.invoke(monitorObjectName, "clearData", nullArgs, nullSig);
            }
            catch (Exception ex) {
                this.error("Problem invoking clearData: " + ex.toString(), req, resp);
                return;
            }
            req.setAttribute("error", (Object)"Dataset Cleared!");
            req.getRequestDispatcher("/manageSnapshot.jsp").forward((ServletRequest)req, (ServletResponse)resp);
            return;
        }
        if (action.equals("Remove Snapshot")) {
            try {
                ManageSnapshotServlet.log.debug("removing snapshot: " + monitorObjectName.toString());
                mbeanServer.unregisterMBean(monitorObjectName);
                req.getRequestDispatcher("/ServerInfo.jsp").forward((ServletRequest)req, (ServletResponse)resp);
            }
            catch (Exception ex2) {
                this.error("Failed to Remove Monitor: " + ex2.toString(), req, resp);
            }
            return;
        }
        if (action.equals("Show Dataset")) {
            ArrayList data = null;
            long end = 0L;
            long start;
            try {
                data = (ArrayList)mbeanServer.getAttribute(monitorObjectName, "Data");
                start = (long)mbeanServer.getAttribute(monitorObjectName, "StartTime");
                end = (long)mbeanServer.getAttribute(monitorObjectName, "EndTime");
            }
            catch (Exception ex3) {
                this.error("Problem invoking getData: " + ex3.toString(), req, resp);
                return;
            }
            resp.setContentType("text/html");
            final PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<body>");
            writer.println("<b>Start Time:</b> " + start + "ms<br>");
            writer.println("<b>End Time:</b> " + end + "ms<br>");
            writer.println("<b>Total Time:</b> " + (end - start) + "ms<br>");
            writer.println("<br><table border=\"0\">");
            for (int i = 0; i < data.size(); ++i) {
                writer.println("<tr><td>" + data.get(i) + "</td></tr");
            }
            writer.println("</table></body></html>");
            return;
        }
        if (action.equals("Graph Dataset")) {
            ArrayList data = null;
            long end = 0L;
            try {
                data = (ArrayList)mbeanServer.getAttribute(monitorObjectName, "Data");
                final long start = (long)mbeanServer.getAttribute(monitorObjectName, "StartTime");
                end = (long)mbeanServer.getAttribute(monitorObjectName, "EndTime");
            }
            catch (Exception ex3) {
                this.error("Problem invoking getData: " + ex3.toString(), req, resp);
                return;
            }
            final XYSeries set = new XYSeries(attribute, false, false);
            for (int i = 0; i < data.size(); ++i) {
                set.add(new Integer(i), data.get(i));
            }
            final DefaultTableXYDataset dataset = new DefaultTableXYDataset(false);
            dataset.addSeries(set);
            final JFreeChart chart = ChartFactory.createXYLineChart("JMX Attribute: " + attribute, "count", attribute, dataset, PlotOrientation.VERTICAL, true, true, false);
            resp.setContentType("image/png");
            final OutputStream out = (OutputStream)resp.getOutputStream();
            ChartUtilities.writeChartAsPNG(out, chart, 400, 300);
            out.close();
            return;
        }
        this.error("Unknown Action", req, resp);
    }
    
    static {
        log = Logger.getLogger(ManageSnapshotServlet.class);
    }
}
