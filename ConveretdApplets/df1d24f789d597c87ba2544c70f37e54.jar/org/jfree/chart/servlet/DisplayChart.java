// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.servlet;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class DisplayChart extends HttpServlet
{
    public void init() throws ServletException {
    }
    
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        String filename = request.getParameter("filename");
        if (filename == null) {
            throw new ServletException("Parameter 'filename' must be supplied");
        }
        filename = ServletUtilities.searchReplace(filename, "..", "");
        final File file = new File(System.getProperty("java.io.tmpdir"), filename);
        if (!file.exists()) {
            throw new ServletException("File '" + file.getAbsolutePath() + "' does not exist");
        }
        boolean isChartInUserList = false;
        final ChartDeleter chartDeleter = (ChartDeleter)session.getAttribute("JFreeChart_Deleter");
        if (chartDeleter != null) {
            isChartInUserList = chartDeleter.isChartAvailable(filename);
        }
        boolean isChartPublic = false;
        if (filename.length() >= 6 && filename.substring(0, 6).equals("public")) {
            isChartPublic = true;
        }
        if (isChartInUserList || isChartPublic) {
            ServletUtilities.sendTempFile(file, response);
            return;
        }
        throw new ServletException("Chart image not found");
    }
}
