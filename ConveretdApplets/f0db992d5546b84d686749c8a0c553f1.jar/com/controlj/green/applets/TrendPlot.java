// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import com.controlj.green.applets.label.TextLine;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Button;
import java.io.InputStream;
import java.io.FileNotFoundException;
import com.controlj.green.applets.comm.TrendDataRequest;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.TimeZone;
import java.util.SimpleTimeZone;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.Locale;
import java.awt.Panel;
import java.awt.Label;
import com.controlj.green.applets.comm.ServletCommunicator;
import com.controlj.green.applets.draw.Markers;
import java.applet.Applet;

public class TrendPlot extends Applet
{
    public static final int NO_DEBUG = 0;
    public static final int GENERAL = 1;
    public static final int DETAIL = 2;
    public static final int VERBOSE = 3;
    public static int traceLevel;
    public static Class traceMeOnly;
    String globalFontFamily;
    boolean standardTime;
    int standardDate;
    String separator;
    public GraphHandler graphHandler;
    private Markers markers;
    ServletCommunicator dataCommunicator;
    ServletCommunicator propertiesCommunicator;
    ServletCommunicator errorCommunicator;
    Label statusLabel;
    Panel mainPanel;
    boolean delayload;
    boolean isSetpointTrend;
    boolean finishedStarting;
    static /* synthetic */ Class class$com$controlj$green$applets$TrendPlot;
    
    public TrendPlot() {
        this.globalFontFamily = "Arial";
        this.delayload = false;
        this.isSetpointTrend = false;
        this.finishedStarting = false;
    }
    
    public static void trace(final Class from, final String msg) {
        if (TrendPlot.traceMeOnly == null || from == TrendPlot.traceMeOnly) {
            System.out.println(from.getName() + " - " + msg);
        }
    }
    
    public void init() {
        try {
            String language = this.tryGettingParameter("language");
            String country = this.tryGettingParameter("country");
            if (language == null) {
                language = "en";
            }
            if (country == null) {
                country = "";
            }
            if (language.equals("zh") || language.equals("ko") || language.equals("th")) {
                this.globalFontFamily = "Serif";
            }
            this.setLocale(new Locale(language, country));
            if (TrendPlot.traceLevel >= 2) {
                trace(this.getClass(), "Using locale: " + this.getLocale());
            }
            final String standardTimeParam = this.tryGettingParameter("standardTime");
            this.standardTime = "true".equals(standardTimeParam);
            final String standardDateParam = this.tryGettingParameter("standardDate");
            try {
                this.standardDate = Integer.parseInt(standardDateParam);
                if (this.standardDate == 0) {
                    this.standardDate = 1;
                }
            }
            catch (NumberFormatException e2) {
                this.standardDate = 1;
            }
            final String dateSeparatorParam = this.tryGettingParameter("dateSeparator");
            if (dateSeparatorParam == null) {
                this.separator = "/";
            }
            else {
                this.separator = dateSeparatorParam;
            }
            final String wbs = this.tryGettingParameter("wbs");
            String location = this.tryGettingParameter("location");
            if (location == null) {
                location = "0";
            }
            if (location.equals("null")) {
                final TrendResource resource = this.getTrendResource();
                final TrendProperties trendprop = this.getTrendProperties();
                trendprop.setDisplayTextResource(resource);
                this.displayErrorMessage(trendprop.getDisplayText("Trend_Graph_Error"));
                final String errormessage = " ";
                this.notifyUserOfError(errormessage, 2004);
            }
            if (this.dataCommunicator == null) {
                final URL errorRequestURL = new URL(this.getCodeBase(), "/_common/servlet/lvl5/action");
                this.errorCommunicator = new ServletCommunicator(errorRequestURL, "wbs=" + wbs + "&action=notifyuser");
            }
            if (this.propertiesCommunicator == null) {
                final String setpointTrend = this.tryGettingParameter("setpointtrend");
                this.isSetpointTrend = (setpointTrend != null && setpointTrend.equals("true"));
                final URL propertiesServletURL = new URL(this.getCodeBase(), "/_common/servlet/lvl5/trendproperties");
                this.propertiesCommunicator = new ServletCommunicator(propertiesServletURL, "wbs=" + wbs + "&path=" + location + "&setpoint=" + this.isSetpointTrend);
            }
            if (this.dataCommunicator == null) {
                String path = location;
                if (location.indexOf(",") >= 0 || location.indexOf(";") >= 0) {
                    path = ".";
                }
                final URL trendServletURL = new URL(this.getCodeBase(), "/_common/servlet/lvl5/trendservlet");
                this.dataCommunicator = new ServletCommunicator(trendServletURL, "wbs=" + wbs + "&path=" + path);
            }
            this.graphHandler = new GraphHandler(this);
            if (this.markers == null) {
                this.markers = this.getMarkersFromFile();
            }
            (this.statusLabel = new Label("", 1)).setFont(new Font(this.globalFontFamily, 0, 15));
            this.statusLabel.setForeground(Color.green);
            this.setBackground(Color.black);
            this.setLayout(new BorderLayout());
            this.add("Center", this.statusLabel);
            this.statusLabel.setVisible(true);
            this.setVisible(true);
            final String delayLoad = this.tryGettingParameter("delayload");
            this.delayload = (delayLoad != null && delayLoad.equals("true"));
        }
        catch (Exception e) {
            System.out.println("exception in init" + e.toString());
            e.printStackTrace();
        }
    }
    
    public void start() {
        try {
            if (TrendPlot.traceLevel >= 1) {
                trace(this.getClass(), "Applet starting");
            }
            final TrendResource resource = this.getTrendResource();
            this.statusLabel.setForeground(Color.white);
            if (this.delayload) {
                this.statusLabel.setText(resource.get("Add_trend_points"));
                return;
            }
            this.statusLabel.setText(resource.get("Loading_trend_graph"));
            this.setCursor(new Cursor(3));
            final TrendProperties trendprop = this.getTrendProperties();
            trendprop.setDisplayTextResource(resource);
            this.statusLabel.setForeground(new Color(trendprop.getColor_GraphTitle(false)));
            this.statusLabel.setText(trendprop.getDisplayText("Loading_trend_data"));
            if (trendprop == null) {
                this.notifyUserOfError("Could not retrieve trend properties.", 2005);
                return;
            }
            if (trendprop.getNumSubGraphs() == 0) {
                this.displayErrorMessage(trendprop.getDisplayText("Add_subgraphs_for_trends"));
                return;
            }
            final TrendProperties.SubGraph[] subgraphProperties = trendprop.getSubGraphs();
            boolean badsetup = true;
            for (int i = 0; i < subgraphProperties.length && badsetup; ++i) {
                if (subgraphProperties[i].getNumDataSeries() > 0) {
                    badsetup = false;
                }
            }
            if (badsetup) {
                this.displayErrorMessage(trendprop.getDisplayText("Add_dataseries_to_subgraph"));
                return;
            }
            this.graphHandler.setProperties(trendprop);
            this.dataCommunicator.setResponseCompressed(true);
            this.graphHandler.setCommunicator(this.dataCommunicator);
            final TrendData data = this.getInitialData(trendprop);
            if (data.getNumSubGraphs() == 0 || subgraphProperties.length != data.getNumSubGraphs()) {
                this.displayErrorMessage(trendprop.getDisplayText("Trend_Graph_Error"));
                this.notifyUserOfError("Could not retrieve trend data.", 2003);
                return;
            }
            final TimeZone myTimeZone = new SimpleTimeZone(0, "MY_GMT");
            TimeZone.setDefault(myTimeZone);
            final TrendData.SubGraph[] subgraphData = data.getAllSubGraphs();
            boolean hasData = false;
            final TrendProperties.SubGraph[] subgraphProp = trendprop.getSubGraphs();
            for (int j = 0; j < subgraphData.length; ++j) {
                GraphInt tempgraph = null;
                try {
                    tempgraph = new GraphInt();
                    tempgraph.initialize(subgraphProp[j], subgraphData[j], trendprop.isXAxis_Autoscale(), trendprop.isGridEnabled(), j == 0, j == subgraphData.length - 1, this.getLocale(), myTimeZone, this.standardDate, this.standardTime, this.separator, resource, this.markers);
                    for (int k = 0; k < subgraphData[j].getNumDataSeries(); ++k) {
                        if (subgraphData[j].getDataSeries(k).getNumRecords() > 0) {
                            hasData = true;
                        }
                    }
                    if (this.isSetpointTrend) {
                        tempgraph.setBorderRight(0);
                    }
                    this.graphHandler.attachGraphs(tempgraph);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.setBackground(new Color(trendprop.getColor_GraphBackground(false)));
            final Label graphtitle = new Label(trendprop.getTitleText());
            graphtitle.setFont(new Font(this.globalFontFamily, 0, trendprop.getTitleFontSize()));
            graphtitle.setAlignment(0);
            this.graphHandler.setGraphTitle(graphtitle);
            final JumpPanel jumpPanel = this.graphHandler.getJumpPanel();
            jumpPanel.setText_Day(trendprop.getDisplayText("Day"));
            jumpPanel.setText_Month(trendprop.getDisplayText("Month"));
            jumpPanel.setText_Year(trendprop.getDisplayText("Year"));
            jumpPanel.setText_InvalidDate(trendprop.getDisplayText("Date_is_invalid"));
            jumpPanel.setText_Go(trendprop.getDisplayText("Go"));
            this.graphHandler.setFont(new Font(this.globalFontFamily, 0, trendprop.getFontSize()));
            this.graphHandler.setPaintMode(false);
            this.setCursor(new Cursor(0));
            this.remove(this.statusLabel);
            final int numSubgraphs = this.graphHandler.getNumSubGraphs();
            (this.mainPanel = new Panel()).setLayout(new GridLayout(numSubgraphs, 0, 0, 0));
            for (int l = 0; l < numSubgraphs; ++l) {
                this.mainPanel.add(this.graphHandler.getGraph(l));
            }
            this.setLayout(new BorderLayout());
            this.add("North", this.graphHandler.getGraphHeader());
            this.add("South", this.graphHandler.getJumpPanel());
            this.add("Center", this.mainPanel);
            this.graphHandler.setPollInterval(10000L);
            this.graphHandler.doSyncronizeInitialRange();
            if (!hasData) {
                this.setBackground(Color.black);
                this.setLayout(new BorderLayout());
                this.add("Center", this.statusLabel);
                this.displayErrorMessage(trendprop.getDisplayText("No_trend_points_to_display"));
            }
        }
        catch (Exception e2) {
            final TrendResource resource2 = this.getTrendResource();
            final TrendProperties trendprop2 = this.getTrendProperties();
            trendprop2.setDisplayTextResource(resource2);
            this.displayErrorMessage(trendprop2.getDisplayText("Fatal_Error_Trend_Applet"));
            System.out.println(e2.toString());
            e2.printStackTrace();
        }
        this.finishedStarting = true;
        if (TrendPlot.traceLevel >= 1) {
            trace(this.getClass(), "Applet started");
        }
    }
    
    public void clear() {
        this.removeAll();
        this.graphHandler.removeAllGraphs();
    }
    
    public void setDataSetPaths(String path) {
        if (TrendPlot.traceLevel >= 1) {
            trace(this.getClass(), "Setting dataset paths to " + path);
        }
        final String wbs = this.getParameter("wbs");
        this.propertiesCommunicator.setBaseRequest("wbs=" + wbs + "&path=" + path);
        if (path.indexOf(",") >= 0 || path.indexOf(";") >= 0) {
            path = ".";
        }
        this.dataCommunicator.setBaseRequest("wbs=" + wbs + "&path=" + path);
        this.delayload = false;
        this.clear();
        this.start();
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    protected TrendResource getTrendResource() {
        final TrendResource i18nResource = new TrendResource(this);
        return i18nResource;
    }
    
    public void paint(final Graphics g) {
        if (this.graphHandler != null) {
            this.graphHandler.synchronizeYAxesWidth();
            super.paint(g);
        }
    }
    
    public GraphHandler getGraphHandler() {
        return this.graphHandler;
    }
    
    public void stop() {
        this.graphHandler.stop();
    }
    
    private String tryGettingParameter(final String paramname) {
        try {
            return this.getParameter(paramname);
        }
        catch (NullPointerException npex) {
            return null;
        }
    }
    
    private TrendData getInitialData(final TrendProperties trendProps) {
        final StringBuffer reqString = TrendDataRequest.createRequest(false);
        final TrendProperties.SubGraph[] subgraphProps = trendProps.getSubGraphs();
        for (int graphIndex = 0; graphIndex < subgraphProps.length; ++graphIndex) {
            TrendDataRequest.addGraphToRequest(reqString, graphIndex == 0);
            if (subgraphProps[graphIndex].getNumDataSeries() == 0) {
                TrendDataRequest.addSeriesToRequest(reqString, "null", 0L, 0L, -1L, true);
            }
            else {
                final TrendProperties.DataSeries[] dataseriesProps = subgraphProps[graphIndex].getAllDataSeries();
                for (int seriesIndex = 0; seriesIndex < dataseriesProps.length; ++seriesIndex) {
                    String path = dataseriesProps[seriesIndex].getSourcePath();
                    if (!dataseriesProps[seriesIndex].isSourcePathIsValid()) {
                        path = "null";
                    }
                    TrendDataRequest.addSeriesToRequest(reqString, path, 0L, 0L, -1L, seriesIndex == 0);
                }
            }
        }
        final Object dataObject = this.dataCommunicator.getObjectFromServlet(reqString.toString());
        TrendData trendData = null;
        if (dataObject != null) {
            trendData = (TrendData)dataObject;
        }
        return trendData;
    }
    
    public boolean setFixedScale(final double ymin, final double ymax, final double step) {
        System.out.println("in setFixedScale");
        if (this.graphHandler.getGraph(0) != null && this.isInitialized()) {
            final GraphInt graph = this.graphHandler.getGraph(0);
            if (this.isSetpointTrend) {
                this.graphHandler.setPaddingPctRight(0.03);
                this.graphHandler.doSyncronizeInitialRange();
                graph.setBorders(5, 0, 2, 0);
                this.remove(this.graphHandler.getGraphHeader());
            }
            this.graphHandler.doZoomToRange(graph.getHorizRange().getValueMin(), graph.getHorizRange().getValueMax(), ymin, ymax, graph);
            graph.fixYAxisScale(ymin, ymax, step);
            this.graphHandler.doRepaintGraphs();
            this.validate();
            this.repaint();
            System.out.println("setFixedScale ran");
            return true;
        }
        return false;
    }
    
    public TrendProperties getTrendProperties() {
        TrendProperties trendprop = null;
        final Object trendpropobj = this.propertiesCommunicator.getObjectFromServlet(null);
        if (trendpropobj != null) {
            trendprop = (TrendProperties)trendpropobj;
            final String font = this.tryGettingParameter("fontsize");
            final String titlefont = this.tryGettingParameter("titlefont");
            if (font != null && !font.equals("null")) {
                trendprop.setFontSize(Integer.parseInt(font));
            }
            if (font != null && !titlefont.equals("null")) {
                trendprop.setTitleFontSize(Integer.parseInt(titlefont));
            }
        }
        return trendprop;
    }
    
    public Markers getMarkersFromFile() {
        InputStream markerStream = null;
        Markers newmarkers = null;
        try {
            URL markerURL = null;
            try {
                markerURL = ((TrendPlot.class$com$controlj$green$applets$TrendPlot == null) ? (TrendPlot.class$com$controlj$green$applets$TrendPlot = class$("com.controlj.green.applets.TrendPlot")) : TrendPlot.class$com$controlj$green$applets$TrendPlot).getResource("/marker.txt");
                markerStream = markerURL.openStream();
            }
            catch (FileNotFoundException fnfex) {
                markerURL = new URL(this.getCodeBase(), "/_common/lvl5/trends/marker.txt");
                markerStream = markerURL.openStream();
            }
            newmarkers = new Markers(markerStream);
        }
        catch (Exception e) {
            System.out.println("Exception : marker file not loaded ");
            e.printStackTrace();
            try {
                if (markerStream != null) {
                    markerStream.close();
                }
            }
            catch (Exception e) {
                System.out.println("Error closing marker stream: " + e.toString());
                e.printStackTrace();
            }
        }
        finally {
            try {
                if (markerStream != null) {
                    markerStream.close();
                }
            }
            catch (Exception e2) {
                System.out.println("Error closing marker stream: " + e2.toString());
                e2.printStackTrace();
            }
        }
        return newmarkers;
    }
    
    public void setMarkers(final Markers markers) {
        this.markers = markers;
    }
    
    public void setErrorCommunicator(final ServletCommunicator errorCommunicator) {
        this.errorCommunicator = errorCommunicator;
    }
    
    public void setPropertiesCommunicator(final ServletCommunicator propertiesCommunicator) {
        this.propertiesCommunicator = propertiesCommunicator;
    }
    
    public void setDataCommunicator(final ServletCommunicator dataCommunicator) {
        this.dataCommunicator = dataCommunicator;
    }
    
    public void displayErrorMessage(final String str) {
        this.statusLabel.setForeground(Color.red);
        this.statusLabel.setText(str);
        this.setCursor(new Cursor(0));
    }
    
    private void notifyUserOfError(final String errorMessage, final int errorNumber) {
        if (this.errorCommunicator == null) {
            System.out.println("Notifying UserOfError: " + errorMessage);
            return;
        }
        this.errorCommunicator.runServlet("infonum=" + errorNumber + "&detailmsg=" + errorMessage);
    }
    
    public boolean sendDispatchPrintEvent() {
        this.graphHandler.setPaintMode(true);
        return true;
    }
    
    public boolean isInitialized() {
        return this.finishedStarting;
    }
    
    public void addI18NTester() {
        final Button button1 = new Button();
        button1.setLabel("I18N Test");
        button1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                TrendPlot.this.displayI18NText();
            }
        });
        this.add("South", button1);
    }
    
    public void displayI18NText() {
        final TrendProperties props = this.graphHandler.getProperties();
        final StringBuffer displayString = new StringBuffer();
        displayString.append(props.getDisplayText("Loading_trend_graph"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Loading_trend_data"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Add_subgraphs_for_trends"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Add_dataseries_to_subgraph"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Trend_Graph_Error"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Log_Disabled"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Log_Enabled"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("and_Buffer_purged"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Time_Synchronization"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("sec"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("In_Alarm"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Overridden"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("In_Fault"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Out_Of_Service"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Historian_Disabled"));
        displayString.append("\n");
        displayString.append(props.getDisplayText("Historian_Enabled"));
        displayString.append(props.getDisplayText("Day"));
        displayString.append(props.getDisplayText("Month"));
        displayString.append(props.getDisplayText("Year"));
        displayString.append(props.getDisplayText("Date_is_invalid"));
        displayString.append(props.getDisplayText("Go"));
        final TextLine text = new TextLine(displayString.toString(), new Font(this.globalFontFamily, 0, 12), Color.white, 1);
        text.draw(this.graphHandler.getGraph(0).getGraphics(), 100, 50);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        TrendPlot.traceLevel = 0;
        TrendPlot.traceMeOnly = null;
    }
    
    class COMClassObject
    {
    }
}
