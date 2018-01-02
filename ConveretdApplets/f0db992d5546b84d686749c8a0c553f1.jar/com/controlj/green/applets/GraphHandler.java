// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import netscape.javascript.JSObject;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import com.controlj.green.applets.draw.Range;
import java.util.Date;
import com.controlj.green.applets.comm.TrendDataRequest;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.applet.Applet;
import com.controlj.green.applets.comm.DataRequester;
import com.controlj.green.applets.comm.PollTrends;
import java.awt.Panel;
import java.awt.Label;
import com.controlj.green.applets.comm.ServletCommunicator;
import java.net.URL;
import java.util.Vector;

public class GraphHandler
{
    public Vector registeredSubgraphs;
    public TrendProperties trendproperties;
    private String loadingDataStatusMessage;
    public URL appletCodeBase;
    public ServletCommunicator dataCommunicator;
    public static double XMAX_ZOOM_LIMIT;
    public static double XMIN_ZOOM_LIMIT;
    public static double DATA_SCREENS_TO_BUFFER_PER_SIDE;
    public static double XPAN_SCREEN_PERCENTAGE;
    public static double YPAN_SCREEN_PERCENTAGE;
    public static double REMOVE_PERCENTAGE;
    public static int MAX_NUMBER_OF_DATAPOINTS;
    public static double YAXIS_PADDING_MULITPLIER;
    private double paddingPctLeft;
    private double paddingPctRight;
    private boolean printMode;
    private boolean historyOnlyEnabled;
    private boolean markersEnabled;
    private Label graphtitle;
    private Label updateLabel;
    private Panel headerPanel;
    private PollTrends currentDataPoller;
    private Thread currentDataPollerThread;
    private DataRequester dataRequester;
    private Thread dataRequesterThread;
    private Applet applet;
    private JumpPanel jumpPanel;
    static final char[] jsCharList;
    static final String[] jsStringList;
    
    public double getPaddingPctLeft() {
        return this.paddingPctLeft;
    }
    
    public void setPaddingPctLeft(final double paddingPctLeft) {
        this.paddingPctLeft = paddingPctLeft;
    }
    
    public double getPaddingPctRight() {
        return this.paddingPctRight;
    }
    
    public void setPaddingPctRight(final double paddingPctRight) {
        this.paddingPctRight = paddingPctRight;
    }
    
    public Applet getApplet() {
        return this.applet;
    }
    
    public JumpPanel getJumpPanel() {
        return this.jumpPanel;
    }
    
    public GraphHandler(final Applet applet) {
        this.registeredSubgraphs = new Vector();
        this.loadingDataStatusMessage = "Loading Data";
        this.paddingPctLeft = 0.0;
        this.paddingPctRight = 0.0;
        this.printMode = false;
        this.historyOnlyEnabled = false;
        this.markersEnabled = false;
        this.currentDataPoller = new PollTrends(this);
        this.dataRequester = new DataRequester(this);
        this.applet = applet;
        (this.jumpPanel = new JumpPanel()).setVisible(false);
        this.jumpPanel.setAcceptListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent e) {
                try {
                    GraphHandler.this.doJumpToTime(GraphHandler.this.jumpPanel.getJumpTime(), GraphHandler.this.jumpPanel.getPrecision());
                    if (GraphHandler.this.registeredSubgraphs.size() > 0) {
                        GraphHandler.this.getGraph(0).requestFocus();
                    }
                }
                catch (JumpPanel.BadDateException bdex) {
                    if (TrendPlot.traceLevel >= 3) {
                        bdex.printStackTrace();
                    }
                }
            }
        });
        this.headerPanel = new Panel();
        this.updateLabel = new Label("Auto");
        this.start();
    }
    
    public void start() {
        (this.dataRequesterThread = new Thread(this.dataRequester)).start();
    }
    
    public void stop() {
        this.stopPollingForCurrentData();
        if (this.dataRequesterThread != null) {
            this.dataRequester.stop();
            this.dataRequesterThread.interrupt();
            this.dataRequesterThread = null;
        }
    }
    
    public boolean isProcessingRequest() {
        return this.dataRequester.isProcessingRequest();
    }
    
    public void doJumpToTime(long time, final long precision) {
        this.doSaveGraphState();
        final StringBuffer request = TrendDataRequest.createRequest(true);
        boolean makeRequest = false;
        long newTime = 0L;
        Range hrange = null;
        time -= time % precision;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            if (hrange == null) {
                hrange = trend.getHorizRange();
                newTime = time + (long)hrange.getValueMin() % precision;
                hrange.setValues(newTime, newTime + hrange.getValueRange());
            }
            trend.getHorizRange().setValues(hrange.getValueMin(), hrange.getValueMax());
            if (trend.addRequestJumpToString(request, newTime, i == 0)) {
                makeRequest = true;
            }
        }
        if (TrendPlot.traceLevel >= 2) {
            TrendPlot.trace(this.getClass(), "Original time   =" + time + " Date: " + new Date(time));
        }
        if (TrendPlot.traceLevel >= 2) {
            TrendPlot.trace(this.getClass(), "Time to jump to =" + newTime + " Date: " + new Date(newTime));
        }
        if (makeRequest) {
            this.beforeRequestingData();
            TrendData response = null;
            final Object dataObject = this.dataCommunicator.getObjectFromServlet(request.toString());
            if (dataObject != null) {
                response = (TrendData)dataObject;
            }
            this.addDataToSubGraphs(response, true, true);
            this.afterRequestingData();
        }
        else {
            this.doRepaintGraphs();
        }
    }
    
    public boolean doToggleHistoricaOnlyMode() {
        this.historyOnlyEnabled = !this.historyOnlyEnabled;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            for (int j = 0; j < trend.getNumDataSets(); ++j) {
                final DataSet set = trend.getDataSetAt(j);
                set.setHistoricalOnly(this.historyOnlyEnabled);
            }
        }
        this.doRepaintGraphs();
        return this.historyOnlyEnabled;
    }
    
    public boolean isHistoryOnlyEnabled() {
        return this.historyOnlyEnabled;
    }
    
    public boolean doToggleJumpPane() {
        if (!this.jumpPanel.isVisible()) {
            final long startOfView = (long)this.getGraph(0).getHorizRange().getValueMin();
            this.jumpPanel.setJumpTime(startOfView);
        }
        this.jumpPanel.setVisible(!this.jumpPanel.isVisible());
        this.applet.validate();
        return this.jumpPanel.isVisible();
    }
    
    public boolean isJumpEnabled() {
        return this.jumpPanel.isVisible();
    }
    
    public boolean doToggleMarkers() {
        this.markersEnabled = !this.markersEnabled;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.setMarkersEnabled(this.markersEnabled);
        }
        this.doRepaintGraphs();
        return this.markersEnabled;
    }
    
    public boolean isMarkersEnabled() {
        return this.markersEnabled;
    }
    
    public void doPan(final boolean panOnX, final boolean negativeDirection, final GraphInt sourceGraph) {
        if (panOnX) {
            double changeInX = sourceGraph.getHorizRange().getValueRange() * GraphHandler.XPAN_SCREEN_PERCENTAGE;
            if (negativeDirection) {
                changeInX = -changeInX;
            }
            this.doTranslation(changeInX, 0.0, sourceGraph);
            this.doAfterTranslation(sourceGraph, !negativeDirection);
        }
        else {
            double changeInY = sourceGraph.getVertRange().getValueRange() * GraphHandler.YPAN_SCREEN_PERCENTAGE;
            if (negativeDirection) {
                changeInY = -changeInY;
            }
            this.doTranslation(0.0, changeInY, sourceGraph);
        }
    }
    
    public void doTranslation(final double xdrag, final double ydrag, final GraphInt sourceGraph) {
        sourceGraph.getVertRange().setValues(sourceGraph.getVertRange().getValueMin() - ydrag, sourceGraph.getVertRange().getValueMax() - ydrag);
        final Range hrange = sourceGraph.getHorizRange();
        hrange.setValues(hrange.getValueMin() - xdrag, hrange.getValueMax() - xdrag);
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.getHorizRange().setValues(hrange.getValueMin(), hrange.getValueMax());
        }
        this.doRepaintGraphs();
    }
    
    public void doAfterTranslation(final GraphInt sourceGraph, final boolean translatedLeft) {
        this.dataRequester.makeRequest(4);
    }
    
    public void doSaveGraphState() {
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.saveGraphState();
        }
    }
    
    public void doRetrieveGraphState() {
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.retrieveGraphState();
        }
        this.doRepaintGraphs();
    }
    
    public void doZoomToExtents() {
        double xminimum = 0.0;
        double xmaximum = 0.0;
        boolean rangeNotSet = true;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            if (trend.hasData()) {
                if (rangeNotSet) {
                    xminimum = trend.getXMinData();
                    xmaximum = trend.getXMaxDataOrSystemTime();
                    rangeNotSet = false;
                }
                else {
                    xminimum = Math.min(xminimum, trend.getXMinData());
                    xmaximum = Math.max(xmaximum, trend.getXMaxDataOrSystemTime());
                }
            }
        }
        if (TrendPlot.traceLevel >= 3) {
            TrendPlot.trace(this.getClass(), "Zooming to extents x= " + xminimum + "," + xmaximum);
        }
        for (int j = 0; j < this.registeredSubgraphs.size(); ++j) {
            final GraphInt trend = this.getGraph(j);
            trend.getHorizRange().setValues(xminimum, xmaximum);
            final double initialymin = trend.getYMinData();
            final double initialymax = trend.getYMaxData();
            double tempvalue = (initialymax - initialymin) * GraphHandler.YAXIS_PADDING_MULITPLIER;
            if (tempvalue == 0.0) {
                if (initialymin == 0.0) {
                    tempvalue = 1.0;
                }
                else {
                    tempvalue = Math.abs(initialymin) * GraphHandler.YAXIS_PADDING_MULITPLIER;
                }
            }
            trend.getVertRange().setValues(initialymin - tempvalue, initialymax + tempvalue);
        }
        this.doRepaintGraphs();
    }
    
    public void doSyncronizeInitialRange() {
        double xminimum = 0.0;
        double xmaximum = 0.0;
        boolean rangeNotSet = true;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            if (trend.hasData()) {
                if (rangeNotSet) {
                    xminimum = trend.getXMinData();
                    xmaximum = trend.getXMaxDataOrSystemTime();
                    rangeNotSet = false;
                }
                else {
                    xminimum = Math.min(xminimum, trend.getXMinData());
                    xmaximum = Math.max(xmaximum, trend.getXMaxDataOrSystemTime());
                }
            }
        }
        if (xmaximum == 0.0) {
            xmaximum = new Date().getTime();
        }
        if (!this.trendproperties.isXAxis_Autoscale() && this.trendproperties.getXAxis_InitialRange() != 0L) {
            xminimum = xmaximum - this.trendproperties.getXAxis_InitialRange();
        }
        if (xminimum == 0.0 && xmaximum > 8.64E7) {
            xminimum = xmaximum - 8.64E7;
        }
        xminimum -= (xmaximum - xminimum) * this.getPaddingPctLeft();
        xmaximum += (xmaximum - xminimum) * this.getPaddingPctRight();
        for (int j = 0; j < this.registeredSubgraphs.size(); ++j) {
            final GraphInt trend = this.getGraph(j);
            trend.setXInitialDataView(xminimum, xmaximum);
            this.setYInitialDataRange(trend);
        }
        this.doResetGraphs();
        this.dataRequester.makeRequest(5);
    }
    
    private void setYInitialDataRange(final GraphInt graph) {
        if (graph.yAutoscale) {
            final double initialymin = graph.getYMinData();
            final double initialymax = graph.getYMaxData();
            double tempvalue = (initialymax - initialymin) * GraphHandler.YAXIS_PADDING_MULITPLIER;
            if (tempvalue == 0.0) {
                if (initialymin == 0.0) {
                    tempvalue = 1.0;
                }
                else {
                    tempvalue = Math.abs(initialymin) * GraphHandler.YAXIS_PADDING_MULITPLIER;
                }
            }
            graph.setYInitialDataView(initialymin - tempvalue, initialymax + tempvalue);
        }
    }
    
    public void attachGraphs(final GraphInt graph) {
        final GraphActionHandler mHandler = new GraphActionHandler(this);
        graph.addKeyListener(mHandler);
        graph.addMouseListener(mHandler);
        graph.addMouseMotionListener(mHandler);
        graph.add(mHandler.getPopupMenu());
        this.registeredSubgraphs.addElement(graph);
    }
    
    public GraphInt getGraph(final int graphnumber) {
        if (graphnumber < 0 || graphnumber >= this.registeredSubgraphs.size()) {
            return null;
        }
        return this.registeredSubgraphs.elementAt(graphnumber);
    }
    
    public void doResetGraphs() {
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            this.setYInitialDataRange(trend);
            trend.resetDataView();
        }
        this.doRepaintGraphs();
    }
    
    public void doZoomToRange(final double xmin, final double xmax, final double ymin, final double ymax, final GraphInt sourceGraph) {
        if (TrendPlot.traceLevel >= 3) {
            TrendPlot.trace(this.getClass(), "Zooming to range min=[" + xmin + ", " + ymin + "] max=[" + xmax + ", " + ymax + "]");
        }
        sourceGraph.getVertRange().setValues(ymin, ymax);
        if (xmax - xmin < GraphHandler.XMIN_ZOOM_LIMIT) {
            return;
        }
        if (this.registeredSubgraphs.size() > 0) {
            for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
                final GraphInt trend = this.getGraph(i);
                trend.getHorizRange().setValues(xmin, xmax);
            }
        }
        this.doRepaintGraphs();
    }
    
    public void doZoomIn(final GraphInt sourceGraph) {
        if (this.registeredSubgraphs.size() > 0) {
            double zoomby = sourceGraph.getHorizRange().getValueRange() / 4.0;
            if (zoomby * 2.0 + sourceGraph.getHorizRange().getValueRange() < GraphHandler.XMIN_ZOOM_LIMIT) {
                if (sourceGraph.getHorizRange().getValueRange() <= GraphHandler.XMAX_ZOOM_LIMIT) {
                    return;
                }
                zoomby = (sourceGraph.getHorizRange().getValueRange() - GraphHandler.XMIN_ZOOM_LIMIT) / 2.0;
            }
            final Range hrange = sourceGraph.getHorizRange();
            hrange.setValues(hrange.getValueMin() + zoomby, hrange.getValueMax() - zoomby);
            for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
                final GraphInt trend = this.getGraph(i);
                trend.getHorizRange().setValues(hrange.getValueMin(), hrange.getValueMax());
            }
            zoomby = sourceGraph.getVertRange().getValueRange() / 4.0;
            sourceGraph.getVertRange().setValues(sourceGraph.getVertRange().getValueMin() + zoomby, sourceGraph.getVertRange().getValueMax() - zoomby);
            this.doRepaintGraphs();
        }
    }
    
    public void doZoomOut(final GraphInt sourceGraph) {
        if (this.registeredSubgraphs.size() > 0) {
            double zoomby = sourceGraph.getHorizRange().getValueRange() / 2.0;
            if (zoomby * 2.0 + sourceGraph.getHorizRange().getValueRange() > GraphHandler.XMAX_ZOOM_LIMIT) {
                if (sourceGraph.getHorizRange().getValueRange() >= GraphHandler.XMAX_ZOOM_LIMIT) {
                    return;
                }
                zoomby = (GraphHandler.XMAX_ZOOM_LIMIT - sourceGraph.getHorizRange().getValueRange()) / 2.0;
            }
            final Range hrange = sourceGraph.getHorizRange();
            hrange.setValues(hrange.getValueMin() - zoomby, hrange.getValueMax() + zoomby);
            for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
                final GraphInt trend = this.getGraph(i);
                trend.getHorizRange().setValues(hrange.getValueMin(), hrange.getValueMax());
            }
            zoomby = sourceGraph.getVertRange().getValueRange() / 2.0;
            sourceGraph.getVertRange().setValues(sourceGraph.getVertRange().getValueMin() - zoomby, sourceGraph.getVertRange().getValueMax() + zoomby);
            this.doRepaintGraphs();
            this.dataRequester.makeRequest(4);
        }
    }
    
    public void doCopyToClipboard() {
        try {
            final JSObject win = JSObject.getWindow(this.getApplet());
            final StringBuffer strBuffer = new StringBuffer();
            strBuffer.append("toClipboard(\"");
            strBuffer.append(this.escapeForJavaScript(this.toExcelString()));
            strBuffer.append("\")");
            win.eval(strBuffer.toString());
        }
        catch (Exception ex) {
            System.out.println("Could not copy to clipboard so printing data here");
            System.out.println(this.toExcelString());
            if (TrendPlot.traceLevel >= 3) {
                ex.printStackTrace();
            }
        }
    }
    
    private String escapeForJavaScript(final String s) {
        if (s == null) {
            return s;
        }
        for (int i = 0; i < GraphHandler.jsCharList.length; ++i) {
            if (s.indexOf(GraphHandler.jsCharList[i]) >= 0) {
                final int length = s.length();
                final StringBuffer buf = new StringBuffer(length + 10);
                int j = 0;
            Label_0049:
                while (j < length) {
                    final char c = s.charAt(j);
                    while (true) {
                        for (int k = 0; k < GraphHandler.jsCharList.length; ++k) {
                            if (c == GraphHandler.jsCharList[k]) {
                                buf.append(GraphHandler.jsStringList[k]);
                                ++j;
                                continue Label_0049;
                            }
                        }
                        buf.append(c);
                        continue;
                    }
                }
                return buf.toString();
            }
        }
        return s;
    }
    
    public void setTranslationCursor(final Cursor cursor) {
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.setCursor(cursor);
        }
    }
    
    public void doRepaintGraphs() {
        this.synchronizeYAxesWidth();
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            trend.repaint();
        }
    }
    
    public void synchronizeYAxesWidth() {
        int maxLeftWidth = 0;
        int maxRightWidth = 0;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            maxLeftWidth = Math.max(trend.getLeftAxisWidth(), maxLeftWidth);
            maxRightWidth = Math.max(trend.getRightAxisWidth(), maxRightWidth);
        }
        for (int j = 0; j < this.registeredSubgraphs.size(); ++j) {
            final GraphInt trend = this.getGraph(j);
            trend.setMinGraphLeftAxisWidth(maxLeftWidth);
            trend.setMinGraphRightAxisWidth(maxRightWidth);
        }
        if (TrendPlot.traceLevel >= 3) {
            TrendPlot.trace(this.getClass(), "Y Axis width set to " + maxLeftWidth);
        }
    }
    
    public boolean getPaintMode() {
        return this.printMode;
    }
    
    public void setPaintMode(final boolean printmode) {
        if (TrendPlot.traceLevel >= 1) {
            TrendPlot.trace(this.getClass(), "Print mode=" + printmode);
        }
        if (this.trendproperties != null) {
            this.printMode = printmode;
            final TrendProperties.SubGraph[] subgraphs = this.trendproperties.getSubGraphs();
            this.getJumpPanel().setVisible(false);
            this.setForeground(new Color(this.trendproperties.getColor_GraphTitle(printmode)));
            this.setBackground(new Color(this.trendproperties.getColor_GraphBackground(printmode)));
            for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
                final GraphInt trend = this.getGraph(i);
                trend.setColors(this.trendproperties, subgraphs[i], printmode);
            }
            this.dataRequester.makeRequest(4);
            this.doRepaintGraphs();
        }
    }
    
    public void setForeground(final Color foreground) {
        this.graphtitle.setForeground(foreground);
        this.headerPanel.setForeground(foreground);
        this.jumpPanel.setForeground(foreground);
        if (this.currentDataPollerThread != null) {
            this.updateLabel.setForeground(foreground);
        }
    }
    
    public void setBackground(final Color background) {
        this.graphtitle.setBackground(background);
        this.headerPanel.setBackground(background);
        this.jumpPanel.setBackground(background);
        this.updateLabel.setBackground(background);
        if (this.currentDataPollerThread == null) {
            this.updateLabel.setForeground(background);
        }
    }
    
    public void setFont(final Font font) {
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt graph = this.getGraph(i);
            graph.setLegendFont(font);
            graph.setLegendFont(font);
            graph.setCtrlClickFont(font);
            graph.setAxisFont(font);
        }
        this.jumpPanel.setFont(font);
        this.updateLabel.setFont(font);
    }
    
    public void setGraphTitle(final Label label) {
        if (this.graphtitle != null) {
            this.headerPanel.remove(this.graphtitle);
        }
        if ((this.graphtitle = label) != null) {
            this.updateLabel.setFont(this.graphtitle.getFont());
            this.updateLabel.setAlignment(2);
            final GridBagLayout layout = new GridBagLayout();
            this.headerPanel.setLayout(layout);
            this.headerPanel.add(this.graphtitle, this.getGridBagConstrants(0, 0, 1.0, 0.0, 10, 0));
            this.headerPanel.add(this.updateLabel, this.getGridBagConstrants(1, 0, 0.0, 0.0, 13, 0));
        }
    }
    
    private GridBagConstraints getGridBagConstrants(final int gridx, final int gridy, final double weightx, final double weighty, final int anchor, final int fill) {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.anchor = anchor;
        constraints.fill = fill;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.ipadx = 0;
        constraints.ipady = 0;
        return constraints;
    }
    
    public Panel getGraphHeader() {
        return this.headerPanel;
    }
    
    public int getNumSubGraphs() {
        return this.registeredSubgraphs.size();
    }
    
    public void setProperties(final TrendProperties properties) {
        this.trendproperties = properties;
        this.loadingDataStatusMessage = this.trendproperties.getDisplayText("Loading_trend_data");
        this.updateLabel.setText(this.trendproperties.getDisplayText("Auto_update"));
    }
    
    public TrendProperties getProperties() {
        return this.trendproperties;
    }
    
    public void removeAllGraphs() {
        this.registeredSubgraphs.removeAllElements();
    }
    
    public void setCommunicator(final ServletCommunicator comm) {
        this.dataCommunicator = comm;
    }
    
    public void doRequestCurrentData() {
        if (this.currentDataPollerThread == null) {
            this.dataRequester.makeRequest(3);
        }
        else {
            this.currentDataPollerThread.interrupt();
        }
    }
    
    public boolean doTogglePollingCurrentData() {
        if (this.currentDataPollerThread == null) {
            this.startPollingForCurrentData();
            this.updateLabel.setForeground(this.graphtitle.getForeground());
        }
        else {
            this.stopPollingForCurrentData();
            this.updateLabel.setForeground(this.graphtitle.getBackground());
        }
        this.applet.validate();
        return this.currentDataPollerThread != null;
    }
    
    public boolean isAutoupdateEnabled() {
        return this.currentDataPollerThread != null;
    }
    
    public void requestCurrentData() {
        this.beforeRequestingData();
        final StringBuffer request = TrendDataRequest.createRequest(true);
        double origRightEdgeOfAllData = 0.0;
        double newRightEdgeOfAllData = 0.0;
        double buffer = 0.0;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            buffer = (trend.getHorizRange().getValueMax() - trend.getHorizRange().getValueMin()) * GraphHandler.DATA_SCREENS_TO_BUFFER_PER_SIDE;
            final double leftEdgeOfView = trend.getHorizRange().getValueMin() - buffer;
            trend.addRequestCurrentToString(request, leftEdgeOfView, i == 0);
            origRightEdgeOfAllData = Math.max(origRightEdgeOfAllData, trend.getXMaxDataOrSystemTime());
        }
        TrendData response = null;
        final Object dataObject = this.dataCommunicator.getObjectFromServlet(request.toString());
        if (dataObject != null) {
            response = (TrendData)dataObject;
        }
        this.addDataToSubGraphs(response, true, false);
        for (int j = 0; j < this.registeredSubgraphs.size(); ++j) {
            final GraphInt trend2 = this.getGraph(j);
            newRightEdgeOfAllData = Math.max(origRightEdgeOfAllData, trend2.getXMaxDataOrSystemTime());
        }
        final double diff = origRightEdgeOfAllData - newRightEdgeOfAllData;
        this.doTranslation(diff, 0.0, this.getGraph(0));
        this.afterRequestingData();
    }
    
    public void setPollInterval(final long interval) {
        this.currentDataPoller.setPollInterval(interval);
    }
    
    public long getPollInterval() {
        return this.currentDataPoller.getPollInterval();
    }
    
    public void startPollingForCurrentData() {
        if (this.currentDataPollerThread == null) {
            (this.currentDataPollerThread = new Thread(this.currentDataPoller)).start();
        }
    }
    
    public void stopPollingForCurrentData() {
        if (this.currentDataPollerThread != null) {
            this.currentDataPoller.stop();
            this.currentDataPollerThread.interrupt();
            this.currentDataPollerThread = null;
        }
    }
    
    private void beforeRequestingData() {
        this.setTranslationCursor(new Cursor(3));
    }
    
    private void afterRequestingData() {
        this.setTranslationCursor(new Cursor(0));
    }
    
    public boolean requestMoreData(final boolean rightSide) {
        final StringBuffer request = TrendDataRequest.createRequest(rightSide);
        boolean makeRequest = false;
        double buffer = 0.0;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            buffer = (trend.getHorizRange().getValueMax() - trend.getHorizRange().getValueMin()) * GraphHandler.DATA_SCREENS_TO_BUFFER_PER_SIDE;
            final double leftEdgeOfView = trend.getHorizRange().getValueMin() - buffer;
            final double rightEdgeOfView = trend.getHorizRange().getValueMax() + buffer;
            if (trend.addRequestToString(request, rightSide, leftEdgeOfView, rightEdgeOfView, i == 0)) {
                makeRequest = true;
            }
        }
        if (makeRequest) {
            this.beforeRequestingData();
            TrendData response = null;
            final Object dataObject = this.dataCommunicator.getObjectFromServlet(request.toString());
            if (dataObject != null) {
                response = (TrendData)dataObject;
            }
            this.addDataToSubGraphs(response, rightSide, false);
            this.afterRequestingData();
        }
        return makeRequest;
    }
    
    public void addDataToSubGraphs(final TrendData trContainer, final boolean addedOnRight, final boolean replaceData) {
        synchronized (this) {
            double minview = 0.0;
            double maxview = 0.0;
            boolean notInitialized = true;
            for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
                final GraphInt trend = this.getGraph(i);
                if (replaceData) {
                    trend.addOrReplaceDataInDataSets(trContainer.getSubGraph(i));
                }
                else {
                    trend.addDataToDataSets(trContainer.getSubGraph(i), addedOnRight);
                }
                if (notInitialized) {
                    minview = trend.getHorizRange().getValueMin();
                    maxview = trend.getHorizRange().getValueMax();
                    notInitialized = false;
                }
                minview = trend.getNewXMinDataView(minview);
                maxview = trend.getNewXMaxDataView(maxview);
            }
            final int totalData = this.getTotalNumberOfDataPoints();
            if (TrendPlot.traceLevel >= 1) {
                TrendPlot.trace(this.getClass(), "Number of points in all graphs: " + totalData);
            }
            if (totalData > GraphHandler.MAX_NUMBER_OF_DATAPOINTS) {
                final double buffer = (maxview - minview) * GraphHandler.DATA_SCREENS_TO_BUFFER_PER_SIDE;
                this.removeDataFromSubgraphs(minview - buffer, maxview + buffer, !addedOnRight);
            }
        }
    }
    
    public void removeDataFromSubgraphs(final double minBoundary, final double maxBoundary, final boolean removeFromRight) {
        double mindata = 0.0;
        double maxdata = 0.0;
        boolean notInitialized = true;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            if (notInitialized) {
                mindata = trend.getXMinData();
                maxdata = trend.getXMaxData();
                notInitialized = false;
            }
            mindata = Math.min(mindata, trend.getXMinData());
            maxdata = Math.max(maxdata, trend.getXMaxData());
        }
        double removeAt;
        if (removeFromRight) {
            removeAt = Math.max(maxdata - (maxdata - mindata) * GraphHandler.REMOVE_PERCENTAGE, maxBoundary);
            if (TrendPlot.traceLevel >= 3) {
                TrendPlot.trace(this.getClass(), "Removing all data after: " + new Date((long)removeAt) + " but maxBoundary is: " + new Date((long)maxBoundary) + " maxdata is currently: " + new Date((long)maxdata));
            }
        }
        else {
            removeAt = Math.min(mindata + (maxdata - mindata) * GraphHandler.REMOVE_PERCENTAGE, minBoundary);
            if (TrendPlot.traceLevel >= 3) {
                TrendPlot.trace(this.getClass(), "Removing all data before: " + new Date((long)removeAt) + " minBoundary is: " + new Date((long)minBoundary) + " mindata is currently: " + new Date((long)mindata));
            }
        }
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend2 = this.getGraph(i);
            trend2.deleteDataPointsAtXValue(removeAt, removeFromRight);
        }
    }
    
    public int getTotalNumberOfDataPoints() {
        int numpoints = 0;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            numpoints += trend.getNumDataPoints();
        }
        return numpoints;
    }
    
    public String toString() {
        final StringBuffer buff = new StringBuffer();
        buff.append("Data Series\tTime\tValue\tType\tFlags\n");
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            final double minRange = trend.getHorizRange().getValueMin();
            final double maxRange = trend.getHorizRange().getValueMax();
            for (int setIndex = 0; setIndex < trend.getNumDataSets(); ++setIndex) {
                final DataSet dataSet = trend.getDataSetAt(setIndex);
                buff.append(dataSet.toStringRange(minRange, maxRange));
            }
        }
        return buff.toString();
    }
    
    public String toExcelString() {
        double minRange = 0.0;
        double maxRange = 0.0;
        final StringBuffer buffer = new StringBuffer();
        buffer.append("Time");
        int numDS = 0;
        for (int i = 0; i < this.registeredSubgraphs.size(); ++i) {
            final GraphInt trend = this.getGraph(i);
            minRange = trend.getHorizRange().getValueMin();
            maxRange = trend.getHorizRange().getValueMax();
            for (int setIndex = 0; setIndex < trend.getNumDataSets(); ++setIndex) {
                final DataSet dataSet = trend.getDataSetAt(setIndex);
                buffer.append("\t" + dataSet.getMetaData().getName());
                ++numDS;
            }
        }
        buffer.append("\n");
        final DataSet[] dataSets = new DataSet[numDS];
        final int[] indexes = new int[numDS];
        numDS = 0;
        for (int j = 0; j < this.registeredSubgraphs.size(); ++j) {
            final GraphInt trend2 = this.getGraph(j);
            for (int setIndex2 = 0; setIndex2 < trend2.getNumDataSets(); ++setIndex2) {
                dataSets[numDS] = trend2.getDataSetAt(setIndex2);
                indexes[numDS] = dataSets[numDS].findFirstXValueGreaterThan(minRange);
                ++numDS;
            }
        }
        boolean continueLooping = true;
        while (continueLooping) {
            double smallestTime = -1.0;
            double smallestExcelTime = -1.0;
            continueLooping = false;
            for (int k = 0; k < dataSets.length; ++k) {
                if (indexes[k] < dataSets[k].getSize() && indexes[k] >= 0) {
                    BNLogRecord record;
                    for (record = dataSets[k].getPoint(indexes[k]); record.isSpecial(); record = dataSets[k].getPoint(indexes[k])) {
                        final int[] array = indexes;
                        final int n = k;
                        ++array[n];
                        if (indexes[k] >= dataSets[k].getSize()) {
                            break;
                        }
                    }
                    if (!record.isSpecial()) {
                        if (smallestTime == -1.0) {
                            smallestExcelTime = record.getDaysSince1900();
                            smallestTime = record.getXValue();
                        }
                        else {
                            smallestExcelTime = Math.min(smallestExcelTime, record.getDaysSince1900());
                            smallestTime = Math.min(smallestTime, record.getXValue());
                        }
                    }
                    continueLooping = true;
                }
            }
            if (smallestTime > maxRange || smallestTime == -1.0) {
                continueLooping = false;
            }
            if (continueLooping) {
                buffer.append(smallestExcelTime);
                for (int k = 0; k < dataSets.length; ++k) {
                    buffer.append("\t");
                    if (indexes[k] < dataSets[k].getSize() && indexes[k] >= 0) {
                        final BNLogRecord record = dataSets[k].getPoint(indexes[k]);
                        if (smallestTime == record.getXValue()) {
                            final int[] array2 = indexes;
                            final int n2 = k;
                            ++array2[n2];
                            if (!record.isSpecial()) {
                                buffer.append(record.getYValue());
                            }
                        }
                    }
                }
                buffer.append("\n");
            }
        }
        return buffer.toString();
    }
    
    static {
        GraphHandler.XMAX_ZOOM_LIMIT = 3.1536E11;
        GraphHandler.XMIN_ZOOM_LIMIT = 100.0;
        GraphHandler.DATA_SCREENS_TO_BUFFER_PER_SIDE = 0.01;
        GraphHandler.XPAN_SCREEN_PERCENTAGE = 0.25;
        GraphHandler.YPAN_SCREEN_PERCENTAGE = 0.25;
        GraphHandler.REMOVE_PERCENTAGE = 0.1;
        GraphHandler.MAX_NUMBER_OF_DATAPOINTS = 160000;
        GraphHandler.YAXIS_PADDING_MULITPLIER = 0.1;
        jsCharList = new char[] { '\'', '\"', '\\', '\n', '\r', ' ' };
        jsStringList = new String[] { "\\'", "\\\"", "\\\\", "\\n", "\\r", "\\u00A0" };
    }
}
