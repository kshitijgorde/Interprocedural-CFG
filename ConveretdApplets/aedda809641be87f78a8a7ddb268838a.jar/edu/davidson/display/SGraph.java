// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import edu.davidson.numerics.Parser;
import edu.davidson.graph.Markers;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.InputStream;
import java.applet.Applet;
import java.net.URL;
import edu.davidson.tools.SDataSource;
import edu.davidson.tools.SUtil;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Component;
import edu.davidson.graphics.Util;
import edu.davidson.graph.DataSet;
import java.util.Enumeration;
import java.awt.Image;
import java.awt.Font;
import edu.davidson.graph.TextLine;
import edu.davidson.tools.SApplet;
import java.awt.Color;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import edu.davidson.graph.Axis;
import edu.davidson.tools.SDataListener;
import edu.davidson.tools.SStepable;
import edu.davidson.graph.Graph2D;

public class SGraph extends Graph2D implements SStepable, Cloneable, SDataListener, Runnable, SScalable
{
    private boolean interrupted;
    private boolean newData;
    Object delayLock;
    private Thread delayThread;
    public Axis xaxis;
    public Axis yaxis;
    int[] borders;
    Format format;
    private boolean autoRefresh;
    private MouseMotionAdapter mouseMotionAdapter;
    private MouseAdapter mouseAdapter;
    private int boxWidth;
    private boolean sampleData;
    private Vector dataSetSeries;
    private Vector functions;
    private Vector cfunctions;
    private double[] dPoint;
    private String labelX;
    private String labelY;
    private Color labelXColor;
    private Color labelYColor;
    private Color labelXTitleColor;
    private Color labelYTitleColor;
    private double defaultMarkerScale;
    private boolean enableMouse;
    private boolean enableClone;
    private boolean enableCoordDisplay;
    private SApplet parentSApplet;
    private String titleStr;
    private TextLine title;
    private Color titleColor;
    private Color dataBackground;
    private boolean mouseDrag;
    private int mouseX;
    private int mouseY;
    private Thing dragThing;
    private TrailThing trailThing;
    private boolean sketchMode;
    private Font boldFont;
    boolean timeDisplay;
    Image sketchImage;
    Vector things;
    Image offScreenImage;
    
    public SGraph() {
        this.interrupted = false;
        this.newData = false;
        this.delayLock = new Object();
        this.delayThread = null;
        this.borders = new int[] { 0, 10, 10, 0 };
        this.format = new Format("%-+6.2g");
        this.autoRefresh = true;
        this.boxWidth = 0;
        this.sampleData = false;
        this.dataSetSeries = new Vector();
        this.functions = new Vector();
        this.cfunctions = new Vector();
        this.dPoint = new double[2];
        this.labelX = "X Axis";
        this.labelY = "Y Axis";
        this.labelXColor = Color.black;
        this.labelYColor = Color.black;
        this.labelXTitleColor = Color.black;
        this.labelYTitleColor = Color.black;
        this.defaultMarkerScale = 1.0;
        this.enableMouse = false;
        this.enableClone = true;
        this.enableCoordDisplay = true;
        this.titleStr = null;
        this.title = new TextLine();
        this.titleColor = Color.black;
        this.dataBackground = Color.white;
        this.mouseDrag = false;
        this.trailThing = null;
        this.sketchMode = false;
        this.boldFont = new Font("Helvetica", 1, 14);
        this.timeDisplay = false;
        this.sketchImage = null;
        this.things = new Vector();
        this.offScreenImage = null;
        this.title.setFontStyle(1);
        this.title.setFontSize(16);
        this.setDataBackground(Color.white);
        this.setGraphBackground(Color.white);
        this.buildMarkers(4);
        super.drawzero = false;
        super.drawgrid = false;
        super.borderLeft = this.borders[0];
        super.borderTop = this.borders[1];
        super.borderRight = this.borders[2];
        super.borderBottom = this.borders[3];
        (this.xaxis = this.createAxis(5)).setTitleColor(this.labelXTitleColor);
        this.xaxis.setLabelColor(this.labelXColor);
        this.xaxis.setTitleBackground(Color.white);
        this.xaxis.setTitleText(this.labelX);
        this.xaxis.setTitleFont(new Font("TimesRoman", 0, 12));
        this.xaxis.setLabelFont(new Font("Helvetica", 0, 10));
        (this.yaxis = this.createAxis(2)).setTitleColor(this.labelYTitleColor);
        this.yaxis.setLabelColor(this.labelYColor);
        this.yaxis.setTitleBackground(Color.white);
        this.yaxis.setTitleText(this.labelY);
        this.yaxis.setTitleFont(new Font("TimesRoman", 0, 12));
        this.yaxis.setLabelFont(new Font("Helvetica", 0, 10));
        if (this.sampleData) {
            this.makeSampleData(100);
        }
        try {
            SApplet.addDataListener(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        (this.delayThread = new Thread(this)).start();
        this.setFont(this.getFont());
    }
    
    public SGraph(final SApplet parentSApplet) {
        this();
        this.parentSApplet = parentSApplet;
    }
    
    public void step(final double n, final double n2) {
        if (this.autoRefresh && this.timeDisplay) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    boolean isInsideDragableThing(final int n, final int n2) {
        final Enumeration<Thing> elements = this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (!thing.noDrag && thing.isInsideThing(n, n2)) {
                return true;
            }
        }
        return false;
    }
    
    public void setFont(final Font titleFont) {
        if (titleFont == null) {
            return;
        }
        super.setFont(titleFont);
        this.title.setFont(new Font(titleFont.getFamily(), 1, titleFont.getSize() + 2));
        this.xaxis.setTitleFont(titleFont);
        this.yaxis.setTitleFont(titleFont);
        final Font font = new Font(titleFont.getFamily(), 0, titleFont.getSize() - 4);
        this.xaxis.setLabelFont(font);
        this.yaxis.setLabelFont(font);
    }
    
    public void setForeground(final Color labelColor) {
        if (labelColor == null) {
            return;
        }
        super.setForeground(labelColor);
        this.xaxis.setTitleColor(labelColor);
        this.xaxis.setLabelColor(labelColor);
        this.yaxis.setTitleColor(labelColor);
        this.yaxis.setLabelColor(labelColor);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setDataBackground(final Color dataBackground) {
        super.setDataBackground(this.dataBackground = dataBackground);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setGraphBackground(final Color background) {
        if (background == null) {
            return;
        }
        if (this.xaxis != null) {
            this.xaxis.setTitleBackground(background);
        }
        if (this.yaxis != null) {
            this.yaxis.setTitleBackground(background);
        }
        this.setBackground(background);
    }
    
    public Color getDataBackground() {
        return this.dataBackground;
    }
    
    public void setSampleData(final boolean sampleData) {
        if (sampleData == this.sampleData) {
            return;
        }
        this.sampleData = sampleData;
        if (sampleData) {
            this.makeSampleData(100);
        }
        else {
            this.deleteAllSeries();
        }
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isSampleData() {
        return this.sampleData;
    }
    
    public void setDrawGrid(final boolean drawgrid) {
        if (drawgrid == super.drawgrid) {
            return;
        }
        super.drawgrid = drawgrid;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isDrawGrid() {
        return super.drawgrid;
    }
    
    public void setAutoRefresh(final boolean autoRefresh) {
        if (autoRefresh == this.autoRefresh) {
            return;
        }
        this.autoRefresh = autoRefresh;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isAutoRefresh() {
        return this.autoRefresh;
    }
    
    public void setAutoscaleX(final boolean b) {
        if (!b == this.xaxis.isManualRange()) {
            return;
        }
        this.xaxis.setManualRange(!b);
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isAutoscaleX() {
        return !this.xaxis.isManualRange();
    }
    
    public void setAutoscaleY(final boolean b) {
        if (!b == this.yaxis.isManualRange()) {
            return;
        }
        this.yaxis.setManualRange(!b);
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isAutoscaleY() {
        return !this.yaxis.isManualRange();
    }
    
    public void setDrawZero(final boolean drawzero) {
        if (drawzero == super.drawzero) {
            return;
        }
        super.drawzero = drawzero;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isDrawZero() {
        return super.drawzero;
    }
    
    public synchronized void setSeriesLegend(final int n, final Color color, final int n2, final int n3, final String s) {
        final Series seriesFromSID = this.getSeriesFromSID(n);
        DataSet set;
        if (seriesFromSID == null) {
            this.dPoint[0] = 0.0;
            this.dPoint[1] = 0.0;
            set = this.attachArray(n, this.dPoint).getDataSet();
            set.deleteData();
        }
        else {
            set = seriesFromSID.data;
        }
        set.legend(n2, n3, s);
        if (color != null) {
            set.legendColor(color);
        }
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public synchronized void setSeriesLegend(final int n, final int n2, final int n3, final String s) {
        final Color black = Color.black;
        this.setSeriesLegend(n, this.createSeries(n).data.markercolor, n2, n3, s);
    }
    
    public synchronized void setSeriesLegendColor(final int n, final Color color) {
        this.createSeries(n).data.legendColor(color);
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    private static Color colorOf(final String s) {
        if (s.equals("black")) {
            return Color.black;
        }
        if (s.equals("blue")) {
            return Color.blue;
        }
        if (s.equals("cyan")) {
            return Color.cyan;
        }
        if (s.equals("darkGray")) {
            return Color.darkGray;
        }
        if (s.equals("gray")) {
            return Color.gray;
        }
        if (s.equals("green")) {
            return Color.green;
        }
        if (s.equals("lightGray")) {
            return Color.lightGray;
        }
        if (s.equals("magenta")) {
            return Color.magenta;
        }
        if (s.equals("orange")) {
            return Color.orange;
        }
        if (s.equals("pink")) {
            return Color.pink;
        }
        if (s.equals("red")) {
            return Color.red;
        }
        if (s.equals("white")) {
            return Color.white;
        }
        if (s.equals("yellow")) {
            return Color.yellow;
        }
        return Color.white;
    }
    
    public synchronized void setSeriesStyle(final int n, final String s, final boolean b, final int n2) {
        this.setSeriesStyle(n, colorOf(s), b, n2);
    }
    
    public synchronized void setSeriesStyle(final int n, final String s, final boolean b, final int n2, final double n3) {
        this.setSeriesStyle(n, colorOf(s), b, n2, n3);
    }
    
    public synchronized void setSeriesStyle(final int n, final Color color, final boolean b, final int marker, final double markerscale) {
        final Series seriesFromSID = this.getSeriesFromSID(n);
        DataSet set;
        if (seriesFromSID == null) {
            this.dPoint[0] = 0.0;
            this.dPoint[1] = 0.0;
            set = this.attachArray(n, this.dPoint).getDataSet();
            set.deleteData();
        }
        else {
            set = seriesFromSID.data;
        }
        if (b) {
            set.linestyle = 1;
        }
        else {
            set.linestyle = 0;
        }
        set.marker = marker;
        set.markerscale = markerscale;
        set.markercolor = color;
        set.linecolor = color;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public synchronized void setSeriesStyle(final int n, final Color color, final boolean b, final int marker) {
        final Series seriesFromSID = this.getSeriesFromSID(n);
        DataSet set;
        if (seriesFromSID == null) {
            this.dPoint[0] = 0.0;
            this.dPoint[1] = 0.0;
            set = this.attachArray(n, this.dPoint).getDataSet();
            set.deleteData();
        }
        else {
            set = seriesFromSID.data;
        }
        if (b) {
            set.linestyle = 1;
        }
        else {
            set.linestyle = 0;
        }
        set.marker = marker;
        set.markercolor = color;
        set.linecolor = color;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public synchronized void setSeriesStyle(final int n, final boolean b, final int n2) {
        final Color black = Color.black;
        this.setSeriesStyle(n, this.createSeries(n).data.markercolor, b, n2);
    }
    
    public synchronized void setSeriesColor(final int n, final Color color) {
        final Series series = this.createSeries(n);
        series.data.markercolor = color;
        series.data.linecolor = color;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public synchronized void setSeriesSorted(final int n, final boolean sorted) {
        this.createSeries(n).data.setSorted(sorted);
    }
    
    public synchronized void setSeriesStripChart(final int n, final int n2, final boolean b) {
        this.createSeries(n).data.setStripChart(n2, b);
    }
    
    public int setSketchMode(final boolean sketchMode) {
        this.sketchImage = Util.getImage("pencil.gif", Util.getApplet(this));
        if (!(this.sketchMode = sketchMode)) {
            this.trailThing = null;
            return 0;
        }
        this.trailThing = new TrailThing(this.parentSApplet, this, 1);
        this.trailThing.trailSize = 2000;
        return this.trailThing.hashCode();
    }
    
    public synchronized void setShowAxes(final boolean showAxis) {
        this.setShowAxis(showAxis);
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public boolean isShowAxes() {
        return this.isShowAxis();
    }
    
    public synchronized void setSquare(final boolean square) {
        super.square = square;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public boolean isSquare() {
        return super.square;
    }
    
    public String getBorders() {
        return String.valueOf(String.valueOf(new StringBuffer("").append(this.borders[0]).append(',').append(this.borders[1]).append(',').append(this.borders[2]).append(',').append(this.borders[3])));
    }
    
    public int getID() {
        return this.hashCode();
    }
    
    public Thing getThing(final int n) {
        final Enumeration<Thing> elements = this.things.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing.hashCode() == n) {
                return thing;
            }
        }
        return null;
    }
    
    public void setBorders(final String s) {
        boolean b = false;
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ", ; / \\ ( { [ ) } ] \t \n \r");
        if (stringTokenizer.countTokens() < 4) {
            b = true;
        }
        else {
            for (int i = 0; i < 4; ++i) {
                try {
                    this.borders[i] = Integer.parseInt(stringTokenizer.nextToken().trim());
                }
                catch (NumberFormatException ex) {
                    b = true;
                }
            }
        }
        if (!b) {
            super.borderLeft = this.borders[0];
            super.borderTop = this.borders[1];
            super.borderRight = this.borders[2];
            super.borderBottom = this.borders[3];
            if (this.autoRefresh) {
                this.repaint();
            }
        }
        else {
            this.borders[0] = super.borderLeft;
            this.borders[1] = super.borderTop;
            this.borders[2] = super.borderRight;
            this.borders[3] = super.borderBottom;
        }
    }
    
    public void setGutters(final int borderLeft, final int borderTop, final int borderRight, final int borderBottom) {
        super.borderLeft = borderLeft;
        super.borderTop = borderTop;
        super.borderRight = borderRight;
        super.borderBottom = borderBottom;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setLabelX(final String s) {
        this.xaxis.setTitleColor(this.labelXTitleColor);
        this.xaxis.setTitleText(s);
        this.labelX = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setLabelX(final String s, final Color color) {
        this.labelXTitleColor = color;
        this.xaxis.setTitleColor(color);
        this.xaxis.setTitleText(s);
        this.labelX = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public String getLabelX() {
        return this.labelX;
    }
    
    public Color getLabelXTitleColor() {
        return this.labelXTitleColor;
    }
    
    public void setLabelXTitleColor(final Color color) {
        this.labelXTitleColor = color;
        this.xaxis.setTitleColor(color);
        this.xaxis.setTitleText(this.labelX);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public Color getLabelXColor() {
        return this.labelXColor;
    }
    
    public void setLabelXColor(final Color color) {
        this.labelXColor = color;
        this.xaxis.setLabelColor(color);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setLabelY(final String s) {
        this.yaxis.setTitleColor(this.labelYTitleColor);
        this.yaxis.setTitleText(s);
        this.labelY = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setLabelY(final String s, final Color color) {
        this.labelYTitleColor = color;
        this.yaxis.setTitleColor(color);
        this.yaxis.setTitleText(s);
        this.labelY = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public String getLabelY() {
        return this.labelY;
    }
    
    public Color getLabelYTitleColor() {
        return this.labelYTitleColor;
    }
    
    public void setLabelYTitleColor(final Color color) {
        this.labelYTitleColor = color;
        this.yaxis.setTitleColor(color);
        this.yaxis.setTitleText(this.labelY);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public Color getLabelYColor() {
        return this.labelYColor;
    }
    
    public void setLabelYColor(final Color color) {
        this.labelYColor = color;
        this.yaxis.setLabelColor(color);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized void setLastPointMarker(final int n, final boolean enableLPCursor) {
        this.createSeries(n).enableLPCursor = enableLPCursor;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized void setAutoReplaceData(final int n, final boolean autoReplace) {
        this.createSeries(n).autoReplace = autoReplace;
    }
    
    public void setAddRepeatedDatum(final int n, final boolean addRepeatedDatum) {
        this.createSeries(n).addRepeatedDatum = addRepeatedDatum;
    }
    
    public void setMarkerSize(final double defaultMarkerScale) {
        this.defaultMarkerScale = defaultMarkerScale;
    }
    
    public double getMarkerSize() {
        return this.defaultMarkerScale;
    }
    
    public void setDefaultMarkerSize(final double defaultMarkerScale) {
        this.defaultMarkerScale = defaultMarkerScale;
    }
    
    public double getDefaultMarkerSize() {
        return this.defaultMarkerScale;
    }
    
    public void setAllMarkerSizes(final double n) {
        this.defaultMarkerScale = n;
        for (int i = 0; i < this.dataSetSeries.size(); ++i) {
            ((Series)this.dataSetSeries.elementAt(i)).data.markerscale = n;
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMarkerSize(final int n, final double markerscale) {
        this.createSeries(n).data.markerscale = markerscale;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMinMaxX(final double n, final double n2) {
        if (this.xaxis.isManualRange()) {
            this.xaxis.setManualRange(true, n, n2);
        }
        else {
            this.xaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMinXRange(final boolean b, final double n, final double n2) {
        this.xaxis.setMinRange(b, n, n2);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMinYRange(final boolean b, final double n, final double n2) {
        this.yaxis.setMinRange(b, n, n2);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMinX(final double n) {
        if (this.xaxis.isManualRange()) {
            if (n > this.xaxis.maximum) {
                this.xaxis.setManualRange(true, n, n + 1);
            }
            else {
                this.xaxis.setManualRange(true, n, this.xaxis.maximum);
            }
        }
        else {
            this.xaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public double getMinX() {
        return this.xaxis.minimum;
    }
    
    public void setMaxX(final double n) {
        if (this.xaxis.isManualRange()) {
            if (n < this.xaxis.minimum) {
                this.xaxis.setManualRange(true, n - 1, n);
            }
            else {
                this.xaxis.setManualRange(true, this.xaxis.minimum, n);
            }
        }
        else {
            this.xaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public double getMaxX() {
        return this.xaxis.maximum;
    }
    
    public void setMinMaxY(final double n, final double n2) {
        if (this.yaxis.isManualRange()) {
            this.yaxis.setManualRange(true, n, n2);
        }
        else {
            this.yaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setMinY(final double n) {
        if (this.yaxis.isManualRange()) {
            if (n > this.yaxis.maximum) {
                this.yaxis.setManualRange(true, n, n + 1);
            }
            else {
                this.yaxis.setManualRange(true, n, this.yaxis.maximum);
            }
        }
        else {
            this.yaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public double getMinY() {
        return this.yaxis.minimum;
    }
    
    public void setMaxY(final double n) {
        if (this.yaxis.isManualRange()) {
            if (n < this.yaxis.minimum) {
                this.yaxis.setManualRange(true, n - 1, n);
            }
            else {
                this.yaxis.setManualRange(true, this.yaxis.minimum, n);
            }
        }
        else {
            this.yaxis.resetRange();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public double getMaxY() {
        return this.yaxis.maximum;
    }
    
    private DataSet getDataSetFromSID(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                return series.data;
            }
        }
        return null;
    }
    
    public MathFunction getFunction(final int n) {
        final Vector vector;
        synchronized (this.functions) {
            vector = (Vector)this.functions.clone();
        }
        // monitorexit(this.functions)
        final Enumeration<MathFunction> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                return mathFunction;
            }
        }
        return null;
    }
    
    public ComplexFunction getCFunction(final int n) {
        final Vector vector;
        synchronized (this.cfunctions) {
            vector = (Vector)this.cfunctions.clone();
        }
        // monitorexit(this.cfunctions)
        final Enumeration<ComplexFunction> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final ComplexFunction complexFunction = elements.nextElement();
            if (complexFunction.hashCode() == n) {
                return complexFunction;
            }
        }
        return null;
    }
    
    public int getFunctionId(final int n) {
        final Vector vector;
        synchronized (this.functions) {
            vector = (Vector)this.functions.clone();
        }
        // monitorexit(this.functions)
        if (n < 1 || n > vector.size()) {
            return 0;
        }
        return vector.elementAt(n - 1).hashCode();
    }
    
    public int getCFunctionId(final int n) {
        final Vector vector;
        synchronized (this.cfunctions) {
            vector = (Vector)this.cfunctions.clone();
        }
        // monitorexit(this.cfunctions)
        if (n < 1 || n > vector.size()) {
            return 0;
        }
        return vector.elementAt(n - 1).hashCode();
    }
    
    private Series getSeriesFromSID(final int n) {
        final Enumeration<Series> elements = this.dataSetSeries.elements();
        while (elements.hasMoreElements()) {
            final Series series = elements.nextElement();
            if (n == series.sid) {
                return series;
            }
        }
        return null;
    }
    
    public String getTitle() {
        return this.titleStr;
    }
    
    public void setTitle(final String s) {
        this.titleStr = s;
        this.title.setText(s);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setTimeDisplay(final boolean timeDisplay) {
        this.timeDisplay = timeDisplay;
        if (this.timeDisplay) {
            this.parentSApplet.clock.addClockListener(this);
        }
        else {
            this.parentSApplet.clock.removeClockListener(this);
        }
    }
    
    public Color getTitleColor() {
        return this.titleColor;
    }
    
    public void setTitleColor(final Color titleColor) {
        this.titleColor = titleColor;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    int getIDFromDataSet(final DataSet set) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (set == series.data) {
                return series.hashCode();
            }
        }
        return 0;
    }
    
    public int getIDFromSID(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                return series.hashCode();
            }
        }
        return 0;
    }
    
    public int getRegressionID(final int n, final int n2, final int n3) {
        return this.createSeries(n).getRegressionID(n2, n3);
    }
    
    public void setObjectColor(final int n, final Color color) {
        if (n == 0 || n == this.hashCode()) {
            this.setDataBackground(color);
            this.setGraphBackground(color);
            return;
        }
        Vector vector;
        synchronized (this.functions) {
            vector = (Vector)this.functions.clone();
        }
        // monitorexit(this.functions)
        final Enumeration<MathFunction> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                mathFunction.color = color;
                if (!this.autoRefresh) {
                    continue;
                }
                this.repaint();
            }
        }
        final Enumeration<Series> elements2 = this.dataSetSeries.elements();
        while (elements2.hasMoreElements()) {
            final Series series = elements2.nextElement();
            if (series.hashCode() == n) {
                series.data.markercolor = color;
                series.data.linecolor = color;
                if (this.autoRefresh) {
                    this.repaint();
                }
                return;
            }
        }
        synchronized (this.things) {
            vector = (Vector)this.things.clone();
        }
        // monitorexit(this.things)
        final Enumeration<MathFunction> elements3 = vector.elements();
        if (elements3.hasMoreElements()) {
            ((Thing)elements3.nextElement()).setColor(color);
            if (this.autoRefresh) {
                this.repaint();
            }
        }
    }
    
    public void setOwner(final SApplet parentSApplet) {
        this.parentSApplet = parentSApplet;
    }
    
    public SApplet getOwner() {
        return this.parentSApplet;
    }
    
    public int getPixWidth() {
        return this.getSize().width;
    }
    
    public int getPixHeight() {
        return this.getSize().height;
    }
    
    public synchronized void deleteAllFunctions() {
        Vector vector;
        synchronized (this.functions) {
            vector = (Vector)this.functions.clone();
            this.functions.removeAllElements();
        }
        // monitorexit(this.functions)
        final Enumeration<MathFunction> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            this.parentSApplet.removeDataSource(mathFunction.hashCode());
            if (mathFunction.explicitTime) {
                this.parentSApplet.clock.removeClockListener(mathFunction);
            }
        }
        synchronized (this.cfunctions) {
            vector = (Vector)this.cfunctions.clone();
            this.cfunctions.removeAllElements();
        }
        // monitorexit(this.cfunctions)
        final Enumeration<MathFunction> elements2 = vector.elements();
        while (elements2.hasMoreElements()) {
            final ComplexFunction complexFunction = (ComplexFunction)elements2.nextElement();
            this.parentSApplet.removeDataSource(complexFunction.hashCode());
            if (complexFunction.explicitTime) {
                this.parentSApplet.clock.removeClockListener(complexFunction);
            }
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public synchronized void deleteFunction(final int n) {
        synchronized (this.functions) {
            final Enumeration<MathFunction> elements = this.functions.elements();
            while (elements.hasMoreElements()) {
                final MathFunction mathFunction = elements.nextElement();
                if (mathFunction.hashCode() == n) {
                    this.parentSApplet.removeDataSource(mathFunction.hashCode());
                    this.functions.removeElement(mathFunction);
                    if (mathFunction.explicitTime) {
                        this.parentSApplet.clock.removeClockListener(mathFunction);
                        break;
                    }
                    break;
                }
            }
        }
        // monitorexit(this.functions)
        synchronized (this.cfunctions) {
            final Enumeration<ComplexFunction> elements2 = this.cfunctions.elements();
            while (elements2.hasMoreElements()) {
                final ComplexFunction complexFunction = elements2.nextElement();
                if (complexFunction.hashCode() == n) {
                    this.parentSApplet.removeDataSource(complexFunction.hashCode());
                    this.cfunctions.removeElement(complexFunction);
                    if (complexFunction.explicitTime) {
                        this.parentSApplet.clock.removeClockListener(complexFunction);
                        break;
                    }
                    break;
                }
            }
        }
        // monitorexit(this.cfunctions)
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public synchronized void deleteAllSeries() {
        this.clearAllThings();
        final Enumeration<Series> elements = this.dataSetSeries.elements();
        while (elements.hasMoreElements()) {
            final Series series = elements.nextElement();
            if (series.regression != null) {
                SApplet.dataSources.remove(Integer.toString(series.regression.hashCode()));
            }
            SApplet.dataSources.remove(Integer.toString(series.hashCode()));
        }
        this.dataSetSeries.removeAllElements();
        this.detachDataSets();
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        if (!this.xaxis.isManualRange()) {
            this.setMinMaxX(0.0, 1.0);
        }
        if (!this.yaxis.isManualRange()) {
            this.setMinMaxY(0.0, 1.0);
        }
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public void deleteAllNonSeriesData() {
        final Enumeration<DataSet> elements = super.dataset.elements();
        while (elements.hasMoreElements()) {
            final DataSet set = elements.nextElement();
            if (this.getIDFromDataSet(set) == 0) {
                this.detachDataSet(set);
            }
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void deleteSeries(final int n) {
        this.deleteSeries(n, true);
    }
    
    public void deleteSeries(final int n, final boolean b) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                if (series.regression != null) {
                    SApplet.dataSources.remove(Integer.toString(series.regression.hashCode()));
                }
                SApplet.dataSources.remove(Integer.toString(series.hashCode()));
                this.dataSetSeries.removeElement(series);
                this.detachDataSet(series.data);
                break;
            }
        }
        if (this.dataSetSeries.size() == 0) {
            this.detachDataSets();
        }
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        if (b && this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setFunctionRange(final int n, final double n2, final double n3, final int n4) {
        MathFunction mathFunction = null;
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                mathFunction.setFunctionRange(n2, n3, n4);
            }
        }
        final Enumeration<ComplexFunction> elements2 = this.cfunctions.elements();
        while (elements2.hasMoreElements()) {
            final ComplexFunction complexFunction = elements2.nextElement();
            if (complexFunction.hashCode() == n) {
                complexFunction.setFunctionRange(n2, n3);
            }
        }
        if (this.parentSApplet != null) {
            this.parentSApplet.updateDataConnection(mathFunction.hashCode());
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public void setFunctionClip(final int n, final double n2, final double n3) {
        MathFunction mathFunction = null;
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                mathFunction.setFunctionClip(n2, n3);
            }
        }
        if (this.parentSApplet != null) {
            this.parentSApplet.updateDataConnection(mathFunction.hashCode());
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public void setYScaleFromFunction(final int n) {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                this.setMinMaxY(mathFunction.ymin, mathFunction.ymax);
            }
        }
        final Enumeration<ComplexFunction> elements2 = this.cfunctions.elements();
        while (elements2.hasMoreElements()) {
            final ComplexFunction complexFunction = elements2.nextElement();
            if (complexFunction.hashCode() == n) {
                this.setMinMaxY(complexFunction.ymin, complexFunction.ymax);
            }
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public synchronized boolean setFunctionString(final int n, final String string) {
        final MathFunction function = this.getFunction(n);
        if (function == null) {
            return false;
        }
        if (!function.setString(string)) {
            return false;
        }
        function.setScale();
        if (this.parentSApplet != null) {
            this.parentSApplet.updateDataConnection(function.hashCode());
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        return true;
    }
    
    public synchronized boolean setCFunctionStrings(final int n, final String stringRe, final String stringIm) {
        final ComplexFunction cFunction = this.getCFunction(n);
        if (cFunction == null) {
            return false;
        }
        if (!cFunction.setStringRe(stringRe)) {
            return false;
        }
        if (!cFunction.setStringIm(stringIm)) {
            return false;
        }
        cFunction.setScale();
        if (this.parentSApplet != null) {
            this.parentSApplet.updateDataConnection(cFunction.hashCode());
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        return true;
    }
    
    public String getFunctionString(final int n) {
        final MathFunction function = this.getFunction(n);
        if (function == null) {
            return "";
        }
        return function.getFunctionStr();
    }
    
    public String getReFunctionString(final int n) {
        final ComplexFunction cFunction = this.getCFunction(n);
        if (cFunction == null) {
            return "";
        }
        return cFunction.getFunctionStrRe();
    }
    
    public String getImFunctionString(final int n) {
        final ComplexFunction cFunction = this.getCFunction(n);
        if (cFunction == null) {
            return "";
        }
        return cFunction.getFunctionStrIm();
    }
    
    public synchronized boolean setFunctionVariable(final int n, final String variable) {
        final MathFunction function = this.getFunction(n);
        if (function == null) {
            return false;
        }
        function.setVariable(variable);
        return true;
    }
    
    public synchronized boolean setFunction(final int n, final String variable, final String string) {
        final MathFunction function = this.getFunction(n);
        if (function == null) {
            return false;
        }
        if (!function.setString(string)) {
            return false;
        }
        function.setVariable(variable);
        function.setScale();
        if (this.parentSApplet != null) {
            this.parentSApplet.updateDataConnection(function.hashCode());
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        return true;
    }
    
    public Object clone() {
        final SGraph sGraph = new SGraph(this.parentSApplet);
        sGraph.autoRefresh = this.autoRefresh;
        sGraph.setSampleData(false);
        sGraph.setTitle(this.titleStr);
        sGraph.setTitleColor(this.titleColor);
        sGraph.setAutoscaleX(this.isAutoscaleX());
        sGraph.setAutoscaleY(this.isAutoscaleY());
        sGraph.setLabelX(this.labelX, this.labelXTitleColor);
        sGraph.setLabelY(this.labelY, this.labelYTitleColor);
        sGraph.setLabelXColor(this.labelXColor);
        sGraph.setLabelYColor(this.labelYColor);
        sGraph.setMinMaxX(this.getMinX(), this.getMaxX());
        sGraph.setMinMaxY(this.getMinY(), this.getMaxY());
        sGraph.defaultMarkerScale = this.defaultMarkerScale;
        sGraph.drawgrid = this.isDrawGrid();
        sGraph.drawzero = this.isDrawZero();
        sGraph.setEnableMouse(this.enableMouse);
        sGraph.setEnableClone(false);
        sGraph.setEnableCoordDisplay(this.enableCoordDisplay);
        sGraph.deleteAllSeries();
        for (int i = 0; i < this.dataSetSeries.size(); ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            sGraph.addDataSeries(series);
            sGraph.setLastPointMarker(series.sid, series.enableLPCursor);
        }
        sGraph.deleteAllFunctions();
        final Enumeration<MathFunction> elements = (Enumeration<MathFunction>)this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            final int addFunction = sGraph.addFunction(mathFunction.variable, mathFunction.getFunctionStr());
            sGraph.setFunctionRange(addFunction, mathFunction.xmin, mathFunction.xmax, mathFunction.numPts);
            sGraph.setFunctionClip(addFunction, mathFunction.minClip, mathFunction.maxClip);
            sGraph.setObjectColor(addFunction, mathFunction.color);
        }
        final Enumeration<ComplexFunction> elements2 = (Enumeration<ComplexFunction>)this.cfunctions.elements();
        while (elements2.hasMoreElements()) {
            final ComplexFunction complexFunction = elements2.nextElement();
            final int addCFunction = sGraph.addCFunction(complexFunction.variable, complexFunction.getFunctionStrRe(), complexFunction.getFunctionStrRe());
            sGraph.setFunctionRange(addCFunction, complexFunction.xmin, complexFunction.xmax, 400);
            sGraph.setObjectColor(addCFunction, complexFunction.color);
        }
        final boolean manualRange = sGraph.xaxis.manualRange;
        sGraph.xaxis.manualRange = true;
        final boolean manualRange2 = sGraph.yaxis.manualRange;
        sGraph.yaxis.manualRange = true;
        sGraph.setMinMaxX(this.xaxis.minimum, this.xaxis.maximum);
        sGraph.setMinMaxY(this.yaxis.minimum, this.yaxis.maximum);
        sGraph.xaxis.manualRange = manualRange;
        sGraph.yaxis.manualRange = manualRange2;
        return sGraph;
    }
    
    public synchronized Series createSeries(final int n) {
        Series series = this.getSeriesFromSID(n);
        if (series == null) {
            series = this.makeEmptySeries(n);
        }
        return series;
    }
    
    public void clearAllData() {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            ((Series)this.dataSetSeries.elementAt(i)).data.deleteData();
        }
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public void clearSeries(final int n) {
        this.clearSeriesData(n);
    }
    
    public void clearAllSeries() {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            series.data.deleteData();
            series.setOwner(this.parentSApplet);
        }
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    public void clearSeriesData(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid && !series.autoReplace) {
                series.data.deleteData();
                series.setOwner(this.parentSApplet);
                if (!this.autoRefresh) {
                    break;
                }
                synchronized (this.delayLock) {
                    this.newData = true;
                    this.delayLock.notify();
                    // monitorexit(this.delayLock)
                    break;
                }
            }
        }
    }
    
    public void adjustScale() {
        if (this.yaxis.isManualRange()) {
            return;
        }
        double minimum = this.yaxis.minimum;
        double maximum = this.yaxis.maximum;
        Vector vector;
        synchronized (this.functions) {
            vector = (Vector)this.functions.clone();
        }
        // monitorexit(this.functions)
        final Enumeration<MathFunction> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            minimum = Math.min(mathFunction.ymin, minimum);
            maximum = Math.max(mathFunction.ymax, maximum);
        }
        synchronized (this.cfunctions) {
            vector = (Vector)this.cfunctions.clone();
        }
        // monitorexit(this.cfunctions)
        final Enumeration<MathFunction> elements2 = vector.elements();
        while (elements2.hasMoreElements()) {
            final ComplexFunction complexFunction = (ComplexFunction)elements2.nextElement();
            minimum = Math.min(complexFunction.ymin, minimum);
            maximum = Math.max(complexFunction.ymax, maximum);
        }
        if (minimum == maximum) {
            minimum -= 0.5;
            maximum += 0.5;
        }
        this.yaxis.minimum = minimum;
        this.yaxis.maximum = maximum;
        this.yaxis.calculateGridLabels();
    }
    
    public void paintFunctions(final Graphics graphics, final Rectangle rectangle) {
        synchronized (this.functions) {
            final Enumeration<MathFunction> elements = this.functions.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().paint(graphics, rectangle);
            }
        }
        // monitorexit(this.functions)
        synchronized (this.cfunctions) {
            final Enumeration<ComplexFunction> elements2 = this.cfunctions.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().paint(graphics, rectangle);
            }
        }
        // monitorexit(this.cfunctions)
    }
    
    public void repaint() {
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public void startPaintThread() {
        if (this.delayThread != null) {
            return;
        }
        this.interrupted = false;
        this.delayThread = new Thread(this);
        this.newData = true;
        this.delayThread.start();
    }
    
    public void destroy() {
        this.interrupted = true;
        if (this.delayThread == null) {
            return;
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        try {
            this.delayThread.join(5000L);
        }
        catch (InterruptedException ex) {}
        this.delayThread = null;
    }
    
    public void run() {
        while (!this.interrupted) {
            synchronized (this.delayLock) {
                if (!this.newData) {
                    try {
                        this.delayLock.wait();
                    }
                    catch (InterruptedException ex) {}
                }
                this.newData = false;
                if (!this.interrupted) {
                    this.paintOffScreen();
                }
            }
            // monitorexit(this.delayLock)
            if (!this.interrupted) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public void paintOffScreen(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (width < 10 || height < 10) {
            graphics.fillRect(0, 0, width, height);
            return;
        }
        if (this.offScreenImage == null || this.offScreenImage.getWidth(this) != width || this.offScreenImage.getHeight(this) != height) {
            this.offScreenImage = this.createImage(width, height);
        }
        if (this.offScreenImage == null) {
            return;
        }
        synchronized (this.offScreenImage) {
            final Graphics graphics2 = this.offScreenImage.getGraphics();
            this.update(graphics2);
            graphics.drawImage(this.offScreenImage, 0, 0, width, height, this);
            if (this.mouseDrag) {
                this.drawCoords(this.mouseX, this.mouseY);
            }
            graphics2.dispose();
            Toolkit.getDefaultToolkit().sync();
        }
        // monitorexit(this.offScreenImage)
    }
    
    public void paintOffScreen() {
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            this.repaint();
            return;
        }
        this.paintOffScreen(graphics);
        graphics.dispose();
    }
    
    public void paintLast(final Graphics graphics, final Rectangle rectangle) {
        final Vector vector;
        synchronized (this.things) {
            vector = (Vector)this.things.clone();
        }
        // monitorexit(this.things)
        final Enumeration<Thing> elements = vector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(graphics);
        }
        final Enumeration<Series> elements2 = this.dataSetSeries.elements();
        while (elements2.hasMoreElements()) {
            elements2.nextElement().paintLastPoint(graphics, rectangle);
        }
        if (this.titleStr != null) {
            graphics.setColor(this.titleColor);
            this.title.draw(graphics, rectangle.x + rectangle.width / 2, rectangle.y + Math.max(10 + rectangle.height / 20, 16), 0);
        }
        if (this.timeDisplay) {
            this.paintTime(graphics, rectangle);
        }
    }
    
    void paintTime(final Graphics graphics, final Rectangle rectangle) {
        if (!this.timeDisplay || this.parentSApplet == null) {
            return;
        }
        graphics.setClip(new Rectangle(0, 0, this.getBounds().width, this.getBounds().height));
        graphics.setColor(Color.black);
        final Font font = graphics.getFont();
        graphics.setFont(this.boldFont);
        graphics.drawString("Time: ".concat(String.valueOf(String.valueOf(new Format("%7.4g").form(SUtil.chop(this.parentSApplet.clock.getTime(), 1.0E-12))))), 10, this.getBounds().height - 5);
        graphics.setFont(font);
    }
    
    public int pixFromX(final double n) {
        int integer;
        try {
            integer = this.xaxis.getInteger(n);
        }
        catch (Exception ex) {
            return 0;
        }
        return integer;
    }
    
    public int pixFromY(final double n) {
        int integer;
        try {
            integer = this.yaxis.getInteger(n);
        }
        catch (Exception ex) {
            return 0;
        }
        return integer;
    }
    
    public double xFromPix(final int n) {
        double double1;
        try {
            double1 = this.xaxis.getDouble(n);
        }
        catch (Exception ex) {
            return 0.0;
        }
        return double1;
    }
    
    public double yFromPix(final int n) {
        double double1;
        try {
            double1 = this.yaxis.getDouble(n);
        }
        catch (Exception ex) {
            return 0.0;
        }
        return double1;
    }
    
    public synchronized DataSet addDataSet(final double[] array, final int n) {
        final DataSet loadDataSet = this.loadDataSet(array, n);
        this.xaxis.attachDataSet(loadDataSet);
        this.yaxis.attachDataSet(loadDataSet);
        return loadDataSet;
    }
    
    private synchronized DataSet addDataSeries(final Series series) {
        this.addData(series.sid, series.getX(), series.getY(), series.getNumpts());
        final DataSet dataSetFromSID = this.getDataSetFromSID(series.sid);
        if (dataSetFromSID == null) {
            System.out.println("Error: DataSet not created in SGraph.addDataSetSeries.");
            return null;
        }
        dataSetFromSID.markercolor = series.getDataSet().markercolor;
        dataSetFromSID.linestyle = series.getDataSet().linestyle;
        dataSetFromSID.linecolor = series.getDataSet().linecolor;
        dataSetFromSID.markercolor = series.getDataSet().markercolor;
        dataSetFromSID.markerscale = series.getDataSet().markerscale;
        dataSetFromSID.marker = series.getDataSet().marker;
        dataSetFromSID.legend(series.getDataSet().getLegend_ix(), series.getDataSet().getLegend_iy(), series.getDataSet().getLegend());
        dataSetFromSID.legendColor(series.getDataSet().linecolor);
        return dataSetFromSID;
    }
    
    public synchronized void addDatum(final int n, final double n2, final double n3) {
        final Series seriesFromSID = this.getSeriesFromSID(n);
        this.dPoint[0] = n2;
        this.dPoint[1] = n3;
        if (seriesFromSID == null) {
            this.attachArray(n, this.dPoint);
        }
        else {
            try {
                if (seriesFromSID.addRepeatedDatum) {
                    seriesFromSID.data.append(this.dPoint, 1);
                }
                else {
                    final double[] lastPoint = seriesFromSID.getLastPoint();
                    if (lastPoint == null || lastPoint[0] != n2 || lastPoint[1] != n3) {
                        seriesFromSID.data.append(this.dPoint, 1);
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("Error appending Data!");
            }
        }
        if (!this.autoRefresh) {
            return;
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public synchronized void addDatum(final SDataSource sDataSource, final int n, final double n2, final double n3) {
        this.addDatum(n, n2, n3);
    }
    
    public void clearAllThings() {
        final Vector vector;
        synchronized (this.things) {
            vector = (Vector)this.things.clone();
            this.things.removeAllElements();
        }
        // monitorexit(this.things)
        final Enumeration<Thing> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            this.parentSApplet.removeDataListener(thing.hashCode());
            this.parentSApplet.removeDataSource(thing.hashCode());
        }
    }
    
    public int addCursor(final SApplet sApplet, final int n, final double n2, final double n3) {
        final MarkerThing markerThing = new MarkerThing(sApplet, this, n, n2, n3);
        this.things.addElement(markerThing);
        if (this.autoRefresh) {
            this.repaint();
        }
        return markerThing.hashCode();
    }
    
    public int addConnectorLine(final SApplet sApplet, final int n, final int n2) {
        final ConnectorLine connectorLine = new ConnectorLine(sApplet, this, this.getThing(n), this.getThing(n2));
        this.things.addElement(connectorLine);
        if (this.autoRefresh) {
            this.repaint();
        }
        return connectorLine.hashCode();
    }
    
    public synchronized boolean swapZOrder(final int n, final int n2) {
        final Thing thing = this.getThing(n);
        final Thing thing2 = this.getThing(n2);
        if (thing == null || thing2 == null) {
            return false;
        }
        final int index = this.things.indexOf(thing);
        final int index2 = this.things.indexOf(thing2);
        this.things.removeElementAt(index);
        this.things.insertElementAt(thing2, index);
        this.things.removeElementAt(index2);
        this.things.insertElementAt(thing, index2);
        return true;
    }
    
    public int addThing(final Thing thing) {
        this.things.addElement(thing);
        if (this.autoRefresh) {
            this.repaint();
        }
        return thing.hashCode();
    }
    
    public synchronized void addData(final int n, final double[] array, final double[] array2) {
        this.addData(n, array, array2, array.length);
    }
    
    public synchronized void addData(final SDataSource sDataSource, final int n, final double[] array, final double[] array2) {
        if (array == null || array2 == null || array.length != array2.length || array.length == 0) {
            if (this.getSeriesFromSID(n) == null) {
                this.makeEmptySeries(n);
            }
            return;
        }
        final int length = array.length;
        final Series seriesFromSID = this.getSeriesFromSID(n);
        final double[] array3 = new double[2 * length];
        int i;
        for (int n2 = i = 0; i < length; ++i, n2 += 2) {
            array3[n2] = array[i];
            array3[n2 + 1] = array2[i];
        }
        if (seriesFromSID == null) {
            this.attachArray(n, array3);
        }
        else {
            try {
                seriesFromSID.data.replace(array3, length);
            }
            catch (Exception ex) {
                System.out.println("Error appending Data!");
            }
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public synchronized void addData(final int n, final double[] array, final double[] array2, final int n2) {
        if (array == null || array2 == null || array.length != array2.length || n2 == 0) {
            if (this.getSeriesFromSID(n) == null) {
                this.makeEmptySeries(n);
            }
            return;
        }
        int length = array.length;
        if (length > n2) {
            length = n2;
        }
        final Series seriesFromSID = this.getSeriesFromSID(n);
        final double[] array3 = new double[2 * length];
        int i;
        for (int n3 = i = 0; i < length; ++i, n3 += 2) {
            array3[n3] = array[i];
            array3[n3 + 1] = array2[i];
        }
        if (seriesFromSID == null) {
            this.attachArray(n, array3);
        }
        else {
            try {
                if (seriesFromSID.autoReplace) {
                    seriesFromSID.data.replace(array3, length);
                    synchronized (this.delayLock) {
                        this.newData = true;
                        this.delayLock.notify();
                    }
                    // monitorexit(this.delayLock)
                    return;
                }
                seriesFromSID.data.append(array3, length);
            }
            catch (Exception ex) {
                System.out.println("Error appending Data!");
            }
        }
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
    }
    
    public synchronized int addFunction(final String s, final String s2) {
        final MathFunction mathFunction = new MathFunction(s, s2);
        synchronized (this.functions) {
            this.functions.addElement(mathFunction);
            if (super.datarect != null && super.datarect.width > 20) {
                final Rectangle datarect = super.datarect;
                mathFunction.xmin = this.xFromPix(datarect.x);
                mathFunction.xmax = this.xFromPix(datarect.x + datarect.width - 1);
                mathFunction.numPts = datarect.width;
                mathFunction.setScale();
            }
            else {
                mathFunction.xmin = -1.0;
                mathFunction.xmax = 1.0;
                mathFunction.numPts = 200;
                mathFunction.setScale();
            }
        }
        // monitorexit(this.functions)
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        return mathFunction.hashCode();
    }
    
    public synchronized int addCFunction(final String s, final String s2, final String s3) {
        final ComplexFunction complexFunction = new ComplexFunction(s, s2, s3);
        synchronized (this.cfunctions) {
            this.cfunctions.addElement(complexFunction);
            if (super.datarect != null && super.datarect.width > 20) {
                final Rectangle datarect = super.datarect;
                complexFunction.xmin = this.xFromPix(datarect.x);
                complexFunction.xmax = this.xFromPix(datarect.x + datarect.width - 1);
                complexFunction.setScale();
            }
            else {
                complexFunction.xmin = -1.0;
                complexFunction.xmax = 1.0;
                complexFunction.setScale();
            }
        }
        // monitorexit(this.cfunctions)
        synchronized (this.delayLock) {
            this.newData = true;
            this.delayLock.notify();
        }
        // monitorexit(this.delayLock)
        return complexFunction.hashCode();
    }
    
    public synchronized int addFunction(final String s) {
        return this.addFunction("x", s);
    }
    
    public void loadFile(final int n, final String s) {
        boolean b = false;
        Applet applet = this.parentSApplet;
        if (applet == null) {
            applet = Util.getApplet(this);
        }
        if (applet == null) {
            System.out.println("File load failed. Applet not found.");
            return;
        }
        final boolean autoRefresh = this.autoRefresh;
        this.autoRefresh = false;
        this.clearSeries(n);
        try {
            final InputStream openStream = new URL(applet.getCodeBase(), s).openStream();
            this.readFile(n, openStream);
            openStream.close();
        }
        catch (Exception ex2) {
            b = true;
        }
        if (b) {
            try {
                final InputStream openStream2 = new URL(applet.getDocumentBase(), s).openStream();
                this.readFile(n, openStream2);
                openStream2.close();
            }
            catch (Exception ex) {
                System.out.println("file load failed: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                return;
            }
        }
        this.autoRefresh = autoRefresh;
        if (this.autoRefresh) {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
    }
    
    void readFile(int n, final InputStream inputStream) throws IOException {
        final StreamTokenizer streamTokenizer = new StreamTokenizer(inputStream);
        streamTokenizer.eolIsSignificant(true);
        streamTokenizer.commentChar(35);
    Label_0168:
        while (streamTokenizer.ttype != -1) {
            switch (streamTokenizer.nextToken()) {
                default: {
                    break Label_0168;
                }
                case -3: {
                    if ("series".equals(streamTokenizer.sval)) {
                        streamTokenizer.nextToken();
                        n = (int)streamTokenizer.nval;
                        this.clearSeries(n);
                        break;
                    }
                }
                case -2: {
                    final double nval = streamTokenizer.nval;
                    streamTokenizer.nextToken();
                    this.addDatum(n, nval, streamTokenizer.nval);
                    break;
                }
            }
            while (streamTokenizer.ttype != 10 && streamTokenizer.ttype != -1) {
                streamTokenizer.nextToken();
            }
        }
    }
    
    public int plotRegression(final int n, final int n2, final int n3) {
        final double[] slopeIntercept = this.getSeriesFromSID(n).getSlopeIntercept(n2, n3);
        final double n4 = slopeIntercept[0];
        final double n5 = slopeIntercept[1];
        String s;
        if (n5 >= 0) {
            s = String.valueOf(String.valueOf(new StringBuffer("").append(n4).append("*x+").append(n5)));
        }
        else {
            s = String.valueOf(String.valueOf(new StringBuffer("").append(n4).append("*x-").append(Math.abs(n5))));
        }
        return this.addFunction("x", s);
    }
    
    public void drawCoords(final Graphics graphics, final int n, final int n2) {
        if (!this.enableCoordDisplay) {
            return;
        }
        final String value = String.valueOf(String.valueOf(new StringBuffer("").append(this.format.form(this.xFromPix(n))).append(" , ").append(this.format.form(this.yFromPix(n2)))));
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.yellow);
        this.boxWidth = Math.max(20 + graphics.getFontMetrics(graphics.getFont()).stringWidth(value), this.boxWidth);
        graphics.fillRect(0, bounds.height - 20, this.boxWidth, 20);
        graphics.setColor(Color.black);
        graphics.drawString(value, 10, bounds.height - 5);
    }
    
    public void drawCoords(final int n, final int n2) {
        final Graphics graphics = this.getGraphics();
        this.drawCoords(graphics, n, n2);
        graphics.dispose();
    }
    
    public boolean isEnableCoordDisplay() {
        return this.enableCoordDisplay;
    }
    
    public void setEnableCoordDisplay(final boolean enableCoordDisplay) {
        this.enableCoordDisplay = enableCoordDisplay;
    }
    
    public boolean isEnableClone() {
        return this.enableClone;
    }
    
    public void setEnableClone(final boolean enableClone) {
        this.enableClone = enableClone;
    }
    
    public boolean isEnableMouse() {
        return this.enableMouse;
    }
    
    public void setEnableMouse(final boolean enableMouse) {
        if (this.enableMouse == enableMouse) {
            return;
        }
        this.enableMouse = enableMouse;
        if (this.enableMouse) {
            this.addMouseMotionListener(this.mouseMotionAdapter = new SGraph_mouseMotionAdapter(this));
            this.addMouseListener(this.mouseAdapter = new SGraph_mouseAdapter(this));
        }
        else {
            this.removeMouseMotionListener(this.mouseMotionAdapter);
            this.removeMouseListener(this.mouseAdapter);
        }
    }
    
    public void setParentSApplet(final SApplet parentSApplet) {
        this.parentSApplet = parentSApplet;
    }
    
    public void setFormat(final String s) {
        this.format = new Format(s);
    }
    
    public String getFormat() {
        return this.format.toString();
    }
    
    void sGraph_mousePressed(final MouseEvent mouseEvent) {
        if (this.enableClone && (mouseEvent.getModifiers() & 0x4) != 0x0) {
            final SGraphFrame sGraphFrame = new SGraphFrame((SGraph)this.clone());
            sGraphFrame.setSize(this.getSize().width, this.getSize().height);
            sGraphFrame.show();
        }
        else {
            this.mouseX = mouseEvent.getX();
            this.mouseY = mouseEvent.getY();
            this.mouseDrag = true;
            final Graphics graphics = this.getGraphics();
            for (int size = this.things.size(), i = 0; i < size; ++i) {
                final Thing dragThing = this.things.elementAt(i);
                if (!dragThing.noDrag && dragThing.isInsideThing(this.mouseX, this.mouseY)) {
                    this.dragThing = dragThing;
                }
            }
            if (this.dragThing != null) {
                this.sGraph_mouseDragged(mouseEvent);
            }
            else if (this.sketchMode && this.trailThing != null) {
                this.trailThing.clearTrail();
                this.parentSApplet.clearData(this.trailThing.hashCode());
                this.setCursor(Cursor.getPredefinedCursor(1));
                this.sGraph_mouseDragged(mouseEvent);
            }
            graphics.dispose();
            this.drawCoords(this.mouseX, this.mouseY);
        }
    }
    
    void sGraph_mouseDragged(final MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getX();
        this.mouseY = mouseEvent.getY();
        final int pixFromX = this.pixFromX(this.xaxis.maximum);
        final int pixFromX2 = this.pixFromX(this.xaxis.minimum);
        if (this.mouseX < pixFromX2) {
            this.mouseX = pixFromX2;
        }
        else if (this.mouseX > pixFromX - 2) {
            this.mouseX = pixFromX - 2;
        }
        final double xFromPix = this.xFromPix(this.mouseX);
        final int pixFromY = this.pixFromY(this.yaxis.maximum);
        final int pixFromY2 = this.pixFromY(this.yaxis.minimum);
        if (this.mouseY < pixFromY) {
            this.mouseY = pixFromY;
        }
        else if (this.mouseY > pixFromY2 - 2) {
            this.mouseY = pixFromY2 - 2;
        }
        final double yFromPix = this.yFromPix(this.mouseY);
        if (this.dragThing != null) {
            this.dragThing.dragMe(xFromPix, yFromPix);
            if (this.parentSApplet != null) {
                this.parentSApplet.updateDataConnection(this.dragThing.hashCode());
            }
            if (this.parentSApplet != null || !this.parentSApplet.clock.isRunning()) {
                this.paintOffScreen();
            }
        }
        else if (this.sketchMode && this.trailThing != null) {
            this.trailThing.incTrail(Math.max(Math.min(xFromPix, this.xaxis.maximum), this.xaxis.minimum), Math.max(Math.min(yFromPix, this.yaxis.maximum), this.yaxis.minimum));
            final Graphics graphics = this.getGraphics();
            this.paintOffScreen(graphics);
            this.trailThing.paint(graphics);
            if (this.sketchImage != null) {
                graphics.drawImage(this.sketchImage, this.mouseX, this.mouseY - this.sketchImage.getHeight(this), this);
            }
            graphics.dispose();
            this.parentSApplet.updateDataConnection(this.trailThing.hashCode());
        }
        this.drawCoords(this.mouseX, this.mouseY);
    }
    
    void sGraph_mouseReleased(final MouseEvent mouseEvent) {
        this.mouseDrag = false;
        final Rectangle bounds = this.getBounds();
        if (this.dragThing == null) {
            this.repaint(0, bounds.height - 20, this.boxWidth, 20);
        }
        else {
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
        this.dragThing = null;
        this.boxWidth = 0;
        if (this.sketchMode && this.trailThing != null) {
            this.attachDataSet(this.trailThing.dataset);
            this.xaxis.attachDataSet(this.trailThing.dataset);
            this.yaxis.attachDataSet(this.trailThing.dataset);
            synchronized (this.delayLock) {
                this.newData = true;
                this.delayLock.notify();
            }
            // monitorexit(this.delayLock)
        }
        this.sGraph_mouseMoved(mouseEvent);
    }
    
    public void sGraph_mouseEntered(final MouseEvent mouseEvent) {
        if (this.sketchMode) {
            this.setCursor(Cursor.getPredefinedCursor(13));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(1));
        }
    }
    
    public void sGraph_mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void sGraph_mouseMoved(final MouseEvent mouseEvent) {
        if (this.isInsideDragableThing(mouseEvent.getX(), mouseEvent.getY())) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        else if (this.sketchMode) {
            this.setCursor(Cursor.getPredefinedCursor(13));
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(1));
        }
    }
    
    private Series attachArray(final int n, final double[] array) {
        if (array.length < 2) {
            return null;
        }
        final DataSet loadDataSet = this.loadDataSet(array, array.length / 2);
        loadDataSet.linestyle = 1;
        loadDataSet.marker = 3;
        loadDataSet.markerscale = this.defaultMarkerScale;
        loadDataSet.markercolor = new Color(0, 0, 255);
        loadDataSet.linecolor = new Color(0, 0, 255);
        loadDataSet.legendColor(Color.black);
        this.xaxis.attachDataSet(loadDataSet);
        this.yaxis.attachDataSet(loadDataSet);
        final Series series;
        this.dataSetSeries.addElement(series = new Series(n, loadDataSet));
        return series;
    }
    
    private Series makeEmptySeries(final int n) {
        this.dPoint[0] = 0.0;
        this.dPoint[1] = 0.0;
        final Series attachArray = this.attachArray(n, this.dPoint);
        attachArray.getDataSet().deleteData();
        return attachArray;
    }
    
    private void makeSampleData(final int n) {
        final double[] array = new double[2 * n];
        int i;
        for (int n2 = i = 0; i < n; ++i, n2 += 2) {
            array[n2] = n2 - n;
            array[n2 + 1] = 60000 * Math.pow(array[n2] / (n - 2), 2.0);
        }
        this.attachArray(1, array).getDataSet().legend(200, 100, "sample data");
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    private void buildMarkers(final int n) {
        final Markers markers;
        this.setMarkers(markers = new Markers());
        final boolean[] array = new boolean[5];
        final int[] array2 = new int[5];
        final int[] array3 = new int[5];
        array[0] = false;
        array[1] = true;
        array[2] = false;
        array[3] = true;
        array2[0] = n;
        array2[2] = (array2[1] = -n);
        array2[3] = n;
        array3[1] = -(array3[0] = n);
        array3[3] = -(array3[2] = n);
        markers.AddMarker(1, 4, array, array2, array3);
        array[0] = false;
        array[2] = (array[1] = true);
        array[4] = (array[3] = true);
        array2[0] = n;
        array2[2] = (array2[1] = -n);
        array2[4] = (array2[3] = n);
        array3[1] = (array3[0] = n);
        array3[3] = (array3[2] = -n);
        array3[4] = n;
        markers.AddMarker(2, 5, array, array2, array3);
        markers.AddMarker(3, n, Markers.TYPE_CIRCLE);
        markers.AddMarker(4, n, Markers.TYPE_SQUARE);
    }
    
    public class RegressionDataSource implements SDataSource
    {
        String[] regStrings;
        double[][] ds;
        Series series;
        SApplet owner;
        int start;
        int end;
        
        RegressionDataSource(final Series series, final int start, final int end) {
            this.regStrings = new String[] { "m", "b", "dm", "db" };
            this.ds = new double[1][4];
            this.series = null;
            this.owner = null;
            this.start = 0;
            this.end = 0;
            this.owner = SGraph.this.parentSApplet;
            this.series = series;
            this.start = start;
            this.end = end;
            try {
                SApplet.addDataSource(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public double[][] getVariables() {
            this.ds[0][0] = 0.0;
            this.ds[0][1] = 0.0;
            this.ds[0][2] = 0.0;
            this.ds[0][3] = 0.0;
            double n = 0.0;
            double n2 = 0.0;
            double n3 = 0.0;
            double n4 = 0.0;
            final double[] data = this.series.data.getData();
            final int dataPoints = this.series.data.dataPoints();
            int min;
            if (this.end <= this.start) {
                min = dataPoints;
            }
            else {
                min = Math.min(dataPoints, this.end);
            }
            final int n5 = Math.max(1, this.start) - 1;
            if (min - n5 < 2) {
                return this.ds;
            }
            for (int i = n5; i < min; ++i) {
                final double n6 = data[2 * i];
                final double n7 = data[2 * i + 1];
                n += n6;
                n2 += n7;
                n3 += n6 * n6;
                n4 += n6 * n7;
            }
            final double n8 = dataPoints * n3 - n * n;
            if (n8 == 0) {
                this.ds[0][0] = 1.0E64;
                this.ds[0][1] = 1.0E64;
            }
            final double n9 = (dataPoints * n4 - n * n2) / n8;
            final double n10 = (n3 * n2 - n * n4) / n8;
            this.ds[0][0] = n9;
            this.ds[0][1] = n10;
            if (min - n5 < 3) {
                return this.ds;
            }
            double n11 = 0.0;
            for (int j = n5; j < min; ++j) {
                final double n12 = data[2 * j + 1] - n10 - n9 * data[2 * j];
                n11 += n12 * n12;
            }
            this.ds[0][2] = Math.sqrt(n11 * dataPoints / n8 / (dataPoints - 2));
            this.ds[0][3] = Math.sqrt(n11 * n3 / n8 / (dataPoints - 2));
            return this.ds;
        }
        
        public String[] getVarStrings() {
            return this.regStrings;
        }
        
        public int getID() {
            return this.hashCode();
        }
        
        public void setOwner(final SApplet sApplet) {
        }
        
        public SApplet getOwner() {
            return SGraph.this.parentSApplet;
        }
    }
    
    class Series implements SDataSource
    {
        private DataSet data;
        private int sid;
        boolean enableLPCursor;
        boolean addRepeatedDatum;
        String[] varStrings;
        SApplet owner;
        boolean autoReplace;
        RegressionDataSource regression;
        
        public Series(final int sid, final DataSet data) {
            this.enableLPCursor = false;
            this.addRepeatedDatum = true;
            this.varStrings = new String[] { "x", "y", "u", "v" };
            this.owner = null;
            this.autoReplace = false;
            this.regression = null;
            this.sid = sid;
            this.data = data;
            this.owner = SGraph.this.parentSApplet;
            try {
                SApplet.addDataSource(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public void setOwner(final SApplet owner) {
            this.owner = owner;
        }
        
        public SApplet getOwner() {
            return this.owner;
        }
        
        public String[] getVarStrings() {
            return this.varStrings;
        }
        
        DataSet getDataSet() {
            return this.data;
        }
        
        public final int getID() {
            return this.hashCode();
        }
        
        int getRegressionID(final int n, final int n2) {
            if (this.regression == null) {
                this.regression = new RegressionDataSource(this, n, n2);
            }
            return this.regression.getID();
        }
        
        void paintLastPoint(final Graphics graphics, final Rectangle rectangle) {
            if (this.data.dataPoints() < 1 || !this.enableLPCursor) {
                return;
            }
            final double[] lastPoint = this.data.getLastPoint();
            final int pixFromX = SGraph.this.pixFromX(lastPoint[0]);
            final int pixFromY = SGraph.this.pixFromY(lastPoint[1]);
            graphics.setColor(Color.red);
            graphics.fillOval(pixFromX - 2 - 1, pixFromY - 2 - 1, 5, 5);
            graphics.setColor(Color.black);
            graphics.drawOval(pixFromX - 2 - 1, pixFromY - 2 - 1, 5, 5);
        }
        
        double[] getLastPoint() {
            final int dataPoints = this.data.dataPoints();
            if (dataPoints < 1) {
                return null;
            }
            return this.data.getPoint(dataPoints - 1);
        }
        
        double[] getX() {
            final double[] data = this.data.getData();
            final int dataPoints = this.data.dataPoints();
            final double[] array = new double[dataPoints];
            for (int i = 0; i < dataPoints; ++i) {
                array[i] = data[2 * i];
            }
            return array;
        }
        
        double[] getY() {
            final double[] data = this.data.getData();
            final int dataPoints = this.data.dataPoints();
            final double[] array = new double[dataPoints];
            for (int i = 0; i < dataPoints; ++i) {
                array[i] = data[2 * i + 1];
            }
            return array;
        }
        
        int getNumpts() {
            return this.data.dataPoints();
        }
        
        public double[] getSlopeIntercept(final int n, final int n2) {
            final double[] array = new double[2];
            double n3 = 0.0;
            double n4 = 0.0;
            double n5 = 0.0;
            double n6 = 0.0;
            final int dataPoints = this.data.dataPoints();
            int min;
            if (n2 <= n) {
                min = dataPoints;
            }
            else {
                min = Math.min(dataPoints, n2);
            }
            final int n7 = Math.max(1, n) - 1;
            if (min - n7 < 2) {
                return array;
            }
            for (int i = n7; i < min; ++i) {
                final double[] point = this.data.getPoint(i);
                n3 += point[0];
                n4 += point[1];
                n5 += point[0] * point[0];
                n6 += point[0] * point[1];
            }
            array[0] = (dataPoints * n6 - n3 * n4) / (dataPoints * n5 - n3 * n3);
            array[1] = n4 / dataPoints - array[0] * n3 / dataPoints;
            return array;
        }
        
        public double[][] getVariables() {
            final int dataPoints = this.data.dataPoints();
            final double[][] array = new double[dataPoints][4];
            if (dataPoints < 1) {
                return array;
            }
            final double[] point = this.data.getPoint(0);
            array[0][0] = point[0];
            array[0][1] = point[1];
            for (int i = 1; i < dataPoints; ++i) {
                final double[] point2 = this.data.getPoint(i);
                array[i][0] = point2[0];
                array[i][1] = point2[1];
                array[i][2] = array[i][0] - array[i - 1][0];
                array[i][3] = array[i][1] - array[i - 1][1];
            }
            return array;
        }
    }
    
    class MathFunction implements Cloneable, SDataSource, SStepable
    {
        Parser parser;
        private Parser checkFunc;
        boolean explicitTime;
        Color color;
        int numPts;
        double xmin;
        double xmax;
        double ymin;
        double ymax;
        double minClip;
        double maxClip;
        double time;
        boolean fixedRange;
        boolean functionClip;
        String[] varStrings;
        double[][] v;
        SApplet owner;
        String string;
        String variable;
        
        MathFunction(final String s, String trim) {
            this.parser = null;
            this.checkFunc = new Parser(1);
            this.explicitTime = false;
            this.color = Color.black;
            this.numPts = 100;
            this.xmin = 0.0;
            this.xmax = 0.0;
            this.ymin = 0.0;
            this.ymax = 0.0;
            this.maxClip = 0.0;
            this.time = 0.0;
            this.fixedRange = false;
            this.functionClip = false;
            this.varStrings = new String[] { "x", "y", "v", "a" };
            this.v = new double[this.numPts][4];
            this.owner = null;
            this.string = null;
            this.variable = null;
            this.xmin = SGraph.this.xaxis.minimum;
            this.xmax = SGraph.this.xaxis.maximum;
            if (this.xmin >= this.xmax) {
                ++this.xmax;
                this.xmin = this.xmax - 1;
            }
            trim = trim.trim();
            this.variable = new String(s.trim());
            this.string = new String(trim);
            if (!s.equals("t")) {
                this.checkFunctionForTime(this.string);
            }
            (this.parser = new Parser(2)).defineVariable(1, s);
            if (!s.equals("t")) {
                this.parser.defineVariable(2, "t");
            }
            else {
                this.parser.defineVariable(2, "time");
            }
            this.parser.define(trim);
            this.parser.parse();
            if (this.parser.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parser.getErrorString()).append(" at math function, position ").append(this.parser.getErrorPosition()))));
            }
            else {
                this.setScale();
            }
            this.owner = SGraph.this.parentSApplet;
            try {
                SApplet.addDataSource(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.explicitTime) {
                this.owner.clock.addClockListener(this);
            }
        }
        
        public void step(final double n, final double time) {
            this.time = time;
            this.owner.clearData(this.hashCode());
            this.owner.updateDataConnection(this.hashCode());
            if (SGraph.this.autoRefresh) {
                synchronized (SGraph.this.delayLock) {
                    SGraph.this.newData = true;
                    SGraph.this.delayLock.notify();
                }
                // monitorexit(this.this$0.delayLock)
            }
        }
        
        public boolean setString(String trim) {
            trim = trim.trim();
            this.string = new String(trim);
            this.parser.define(trim);
            this.parser.parse();
            if (this.parser.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parser.getErrorString()).append(" at function 1, position ").append(this.parser.getErrorPosition()))));
                return false;
            }
            this.setScale();
            if (this.owner != null) {
                this.owner.clearData(this.hashCode());
                this.owner.updateDataConnection(this.hashCode());
            }
            return true;
        }
        
        String getString() {
            return this.variable;
        }
        
        void setVariable(final String s) {
            this.variable = new String(s.trim());
            this.parser.defineVariable(1, s);
        }
        
        String getVariable() {
            return this.variable;
        }
        
        void checkFunctionForTime(final String s) {
            final String s2 = new String(s);
            this.checkFunc.defineVariable(1, "x");
            this.checkFunc.define(s2.toLowerCase());
            this.checkFunc.parse();
            if (this.checkFunc.getErrorCode() != 0) {
                this.explicitTime = true;
            }
            else {
                this.explicitTime = false;
            }
        }
        
        public void setOwner(final SApplet owner) {
            this.owner = owner;
        }
        
        public SApplet getOwner() {
            return this.owner;
        }
        
        public String[] getVarStrings() {
            return this.varStrings;
        }
        
        public final int getID() {
            return this.hashCode();
        }
        
        Parser getParser() {
            return this.parser;
        }
        
        String getFunctionStr() {
            return this.parser.getFunctionString();
        }
        
        void setFunctionRange(final double xmin, final double xmax, final int numPts) {
            if (numPts < 1) {
                this.fixedRange = false;
                return;
            }
            this.xmin = xmin;
            this.xmax = xmax;
            this.numPts = numPts;
            if (this.numPts != this.v.length) {
                this.v = new double[this.numPts][4];
            }
            this.fixedRange = true;
        }
        
        void setFunctionClip(final double n, final double n2) {
            if (n >= n2) {
                this.functionClip = false;
                return;
            }
            this.minClip = n;
            this.maxClip = n;
            this.functionClip = true;
        }
        
        void paint(final Graphics graphics, final Rectangle rectangle) {
            if (this.fixedRange) {
                this.paint2(graphics, rectangle);
            }
            else {
                this.paint1(graphics, rectangle);
            }
        }
        
        void paint1(final Graphics graphics, final Rectangle clip) {
            this.numPts = clip.width;
            if (this.numPts != this.v.length) {
                this.v = new double[this.numPts][4];
            }
            if (this.numPts < 1) {
                return;
            }
            final Shape clip2 = graphics.getClip();
            graphics.setClip(clip);
            this.xmin = SGraph.this.xFromPix(clip.x);
            this.xmax = SGraph.this.xFromPix(clip.x + clip.width - 1);
            this.ymin = this.parser.evaluate(this.xmin, this.time);
            double ymin = this.ymin;
            this.ymax = this.ymin;
            int pixFromY = SGraph.this.pixFromY(this.ymin);
            graphics.setColor(this.color);
            for (int i = 1; i < clip.width; ++i) {
                final double evaluate = this.parser.evaluate(SGraph.this.xFromPix(clip.x + i), this.time);
                if (!this.functionClip || (evaluate > this.minClip && evaluate < this.maxClip)) {
                    if (evaluate < this.ymin) {
                        this.ymin = evaluate;
                    }
                    if (evaluate > this.ymax) {
                        this.ymax = evaluate;
                    }
                }
                final int pixFromY2 = SGraph.this.pixFromY(evaluate);
                if (!this.functionClip || (ymin > this.minClip && ymin < this.maxClip && evaluate > this.minClip && evaluate < this.maxClip)) {
                    graphics.drawLine(clip.x + i - 1, pixFromY, clip.x + i, pixFromY2);
                }
                pixFromY = pixFromY2;
                ymin = evaluate;
            }
            graphics.setClip(clip2);
        }
        
        void paint2(final Graphics graphics, final Rectangle clip) {
            if (this.numPts < 1) {
                return;
            }
            final Shape clip2 = graphics.getClip();
            graphics.setClip(clip);
            final double n = (this.xmax - this.xmin) / (this.numPts - 1);
            double xmin = this.xmin;
            int pixFromX = SGraph.this.pixFromX(xmin);
            this.ymin = this.parser.evaluate(xmin, this.time);
            this.ymax = this.ymin;
            int pixFromY = SGraph.this.pixFromY(this.ymin);
            graphics.setColor(this.color);
            for (int i = 1; i < this.numPts; ++i) {
                xmin += n;
                final int pixFromX2 = SGraph.this.pixFromX(xmin);
                final double evaluate = this.parser.evaluate(xmin, this.time);
                if (evaluate < this.ymin) {
                    this.ymin = evaluate;
                }
                if (evaluate > this.ymax) {
                    this.ymax = evaluate;
                }
                final int pixFromY2 = SGraph.this.pixFromY(evaluate);
                graphics.drawLine(pixFromX, pixFromY, pixFromX2, pixFromY2);
                pixFromX = pixFromX2;
                pixFromY = pixFromY2;
            }
            graphics.setClip(clip2);
        }
        
        public double[][] getVariables() {
            final double[][] v = this.v;
            final int length = v.length;
            synchronized (SGraph.this.functions) {
                double n = SGraph.this.xaxis.minimum;
                double n2 = SGraph.this.xaxis.maximum;
                if (this.fixedRange) {
                    n = this.xmin;
                    n2 = this.xmax;
                }
                final double n3 = (n2 - n) / (length - 1);
                for (int i = 0; i < length; ++i) {
                    v[i][0] = n;
                    v[i][1] = this.parser.evaluate(n, this.time);
                    n += n3;
                }
                if (length < 4) {
                    // monitorexit(SGraph.access$5(this.this$0))
                    return v;
                }
                for (int j = 2; j < length - 2; ++j) {
                    v[j][2] = (-v[j + 2][1] + 8 * v[j + 1][1] - 8 * v[j - 1][1] + v[j - 2][1]) / n3 / 12;
                    v[j][3] = (-v[j + 2][1] + 16 * v[j + 1][1] - 30 * v[j][1] + 16 * v[j - 1][1] - v[j - 2][1]) / n3 / n3 / 12;
                }
                final double minimum = SGraph.this.xaxis.minimum;
                v[1][2] = (-v[3][1] + 8 * v[2][1] - 8 * v[0][1] + this.parser.evaluate(minimum - n3, this.time)) / n3 / 12;
                v[0][2] = (-v[2][1] + 8 * v[1][1] - 8 * this.parser.evaluate(minimum - n3, this.time) + this.parser.evaluate(minimum - 2 * n3, this.time)) / n3 / 12;
                v[length - 1][2] = (-this.parser.evaluate(n2 + 2 * n3, this.time) + 8 * this.parser.evaluate(n2 + n3, this.time) - 8 * v[length - 2][1] + v[length - 3][1]) / n3 / 12;
                v[length - 2][2] = (-this.parser.evaluate(n2 + n3, this.time) + 8 * v[length - 1][1] - 8 * v[length - 3][1] + v[length - 4][1]) / n3 / 12;
                v[1][3] = (-v[3][1] + 16 * v[2][1] - 30 * v[1][1] + 16 * v[0][1] - this.parser.evaluate(minimum - n3, this.time)) / n3 / n3 / 12;
                v[0][3] = (-v[2][1] + 16 * v[1][1] - 30 * v[0][1] + 16 * this.parser.evaluate(minimum - n3, this.time) - this.parser.evaluate(minimum - 2 * n3, this.time)) / n3 / n3 / 12;
                v[length - 1][3] = (-this.parser.evaluate(n2 + 2 * n3, this.time) + 16 * this.parser.evaluate(n2 + n3, this.time) - 30 * v[length - 1][1] + 16 * v[length - 2][1] - v[length - 3][1]) / n3 / n3 / 12;
                v[length - 2][3] = (-this.parser.evaluate(n2 + n3, this.time) + 16 * v[length - 1][1] - 30 * v[length - 2][1] + 16 * v[length - 3][1] - v[length - 4][1]) / n3 / n3 / 12;
                // monitorexit(SGraph.access$5(this.this$0))
                return v;
            }
        }
        
        void setScale() {
            if (this.numPts < 2) {
                return;
            }
            final double n = (this.xmax - this.xmin) / (this.numPts - 1);
            double xmin = this.xmin;
            final double evaluate = this.parser.evaluate(xmin, this.time);
            if (!this.functionClip || (evaluate > this.minClip && evaluate < this.maxClip)) {
                final double n2 = evaluate;
                this.ymax = n2;
                this.ymin = n2;
            }
            else {
                final double minClip = this.minClip;
                this.ymax = minClip;
                this.ymin = minClip;
            }
            for (int i = 1; i < this.numPts; ++i) {
                xmin += n;
                final double evaluate2 = this.parser.evaluate(xmin, this.time);
                if (!this.functionClip || (evaluate2 > this.minClip && evaluate2 < this.maxClip)) {
                    this.ymin = Math.min(this.ymin, evaluate2);
                    this.ymax = Math.max(this.ymax, evaluate2);
                }
            }
        }
    }
    
    class ComplexFunction implements Cloneable, SDataSource, SStepable
    {
        Parser parserIm;
        Parser parserRe;
        private Parser checkFunc;
        boolean explicitTime;
        Color color;
        double xmin;
        double xmax;
        double ymin;
        double ymax;
        double time;
        boolean fixedRange;
        String[] varStrings;
        double[][] v;
        SApplet owner;
        String stringRe;
        String stringIm;
        String variable;
        
        ComplexFunction(final String s, String trim, String trim2) {
            this.parserIm = null;
            this.parserRe = null;
            this.checkFunc = new Parser(1);
            this.explicitTime = false;
            this.color = Color.black;
            this.xmin = 0.0;
            this.xmax = 0.0;
            this.ymin = 0.0;
            this.ymax = 0.0;
            this.time = 0.0;
            this.fixedRange = false;
            this.varStrings = new String[] { "x", "y", "v", "a" };
            this.v = new double[100][4];
            this.owner = null;
            this.stringRe = null;
            this.stringIm = null;
            this.variable = null;
            this.xmin = SGraph.this.xaxis.minimum;
            this.xmax = SGraph.this.xaxis.maximum;
            if (this.xmin >= this.xmax) {
                ++this.xmax;
                this.xmin = this.xmax - 1;
            }
            trim = trim.trim();
            this.stringRe = new String(trim);
            trim2 = trim2.trim();
            this.stringIm = new String(trim2);
            this.variable = new String(s.trim());
            if (!s.equals("t")) {
                this.explicitTime = (this.checkFunctionForTime(this.stringRe) && this.checkFunctionForTime(this.stringRe));
            }
            (this.parserRe = new Parser(2)).defineVariable(1, s);
            if (!s.equals("t")) {
                this.parserRe.defineVariable(2, "t");
            }
            else {
                this.parserRe.defineVariable(2, "time");
            }
            this.parserRe.define(trim);
            this.parserRe.parse();
            if (this.parserRe.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parserRe.getErrorString()).append(" at math function, position ").append(this.parserRe.getErrorPosition()))));
            }
            (this.parserIm = new Parser(2)).defineVariable(1, s);
            if (!s.equals("t")) {
                this.parserIm.defineVariable(2, "t");
            }
            else {
                this.parserIm.defineVariable(2, "time");
            }
            this.parserIm.define(trim2);
            this.parserIm.parse();
            if (this.parserIm.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim2))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parserIm.getErrorString()).append(" at math function, position ").append(this.parserIm.getErrorPosition()))));
            }
            this.setScale();
            this.owner = SGraph.this.parentSApplet;
            try {
                SApplet.addDataSource(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.explicitTime) {
                this.owner.clock.addClockListener(this);
            }
        }
        
        public void step(final double n, final double time) {
            this.time = time;
            this.owner.clearData(this.hashCode());
            this.owner.updateDataConnection(this.hashCode());
            if (SGraph.this.autoRefresh) {
                synchronized (SGraph.this.delayLock) {
                    SGraph.this.newData = true;
                    SGraph.this.delayLock.notify();
                }
                // monitorexit(this.this$0.delayLock)
            }
        }
        
        public boolean setStringIm(String trim) {
            trim = trim.trim();
            this.stringIm = new String(trim);
            this.parserIm.define(trim);
            this.parserIm.parse();
            if (this.parserIm.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parserIm.getErrorString()).append(" at function 1, position ").append(this.parserIm.getErrorPosition()))));
                return false;
            }
            this.setScale();
            if (this.owner != null) {
                this.owner.clearData(this.hashCode());
                this.owner.updateDataConnection(this.hashCode());
            }
            return true;
        }
        
        public boolean setStringRe(String trim) {
            trim = trim.trim();
            this.stringRe = new String(trim);
            this.parserRe.define(trim);
            this.parserRe.parse();
            if (this.parserRe.getErrorCode() != 0) {
                System.out.println("Failed to parse f(x,t)): ".concat(String.valueOf(String.valueOf(trim))));
                System.out.println(String.valueOf(String.valueOf(new StringBuffer("Parse error in MathFunction: ").append(this.parserRe.getErrorString()).append(" at function 1, position ").append(this.parserRe.getErrorPosition()))));
                return false;
            }
            this.setScale();
            if (this.owner != null) {
                this.owner.clearData(this.hashCode());
                this.owner.updateDataConnection(this.hashCode());
            }
            return true;
        }
        
        String getString() {
            return this.variable;
        }
        
        void setVariable(final String s) {
            this.variable = new String(s.trim());
            this.parserRe.defineVariable(1, s);
            this.parserIm.defineVariable(1, s);
        }
        
        String getVariable() {
            return this.variable;
        }
        
        boolean checkFunctionForTime(final String s) {
            final String s2 = new String(s);
            this.checkFunc.defineVariable(1, "x");
            this.checkFunc.define(s2.toLowerCase());
            this.checkFunc.parse();
            return this.checkFunc.getErrorCode() != 0;
        }
        
        public void setOwner(final SApplet owner) {
            this.owner = owner;
        }
        
        public SApplet getOwner() {
            return this.owner;
        }
        
        public String[] getVarStrings() {
            return this.varStrings;
        }
        
        public final int getID() {
            return this.hashCode();
        }
        
        Parser getParserRe() {
            return this.parserRe;
        }
        
        Parser getParserIm() {
            return this.parserIm;
        }
        
        String getFunctionStrRe() {
            return this.parserRe.getFunctionString();
        }
        
        String getFunctionStrIm() {
            return this.parserIm.getFunctionString();
        }
        
        void setFunctionRange(final double xmin, final double xmax) {
            if (xmin >= xmax) {
                this.fixedRange = false;
                return;
            }
            this.xmin = xmin;
            this.xmax = xmax;
            this.fixedRange = true;
        }
        
        void paint(final Graphics graphics, final Rectangle rectangle) {
            this.paint1(graphics, rectangle);
        }
        
        void paint1(final Graphics graphics, final Rectangle clip) {
            if (clip.width < 3) {
                return;
            }
            final Shape clip2 = graphics.getClip();
            graphics.setClip(clip);
            SGraph.this.xFromPix(clip.x);
            SGraph.this.xFromPix(clip.x + clip.width - 1);
            final double evaluate = this.parserRe.evaluate(this.xmin, this.time);
            final double evaluate2 = this.parserIm.evaluate(this.xmin, this.time);
            this.ymin = Math.sqrt(evaluate * evaluate + evaluate2 * evaluate2);
            this.ymax = this.ymin;
            for (int i = 1; i < clip.width - 1; ++i) {
                final double xFromPix = SGraph.this.xFromPix(clip.x + i);
                if (this.fixedRange) {
                    if (xFromPix < this.xmin) {
                        continue;
                    }
                    if (xFromPix > this.xmax) {
                        continue;
                    }
                }
                final double evaluate3 = this.parserRe.evaluate(xFromPix, this.time);
                final double evaluate4 = this.parserIm.evaluate(xFromPix, this.time);
                final double sqrt = Math.sqrt(evaluate3 * evaluate3 + evaluate4 * evaluate4);
                graphics.setColor(Color.getHSBColor((float)(1 + Math.atan2(evaluate4, evaluate3) / 3.141592653589793) / 2, 1.0f, 1.0f));
                if (sqrt < this.ymin) {
                    this.ymin = sqrt;
                }
                if (sqrt > this.ymax) {
                    this.ymax = sqrt;
                }
                graphics.drawLine(clip.x + i, SGraph.this.pixFromY(-sqrt), clip.x + i, SGraph.this.pixFromY(sqrt));
            }
            graphics.setClip(clip2);
        }
        
        public double[][] getVariables() {
            return this.v;
        }
        
        void setScale() {
            final int n = 500;
            final double n2 = (this.xmax - this.xmin) / (n - 1);
            double xmin = this.xmin;
            final double evaluate = this.parserRe.evaluate(xmin, this.time);
            final double evaluate2 = this.parserIm.evaluate(xmin, this.time);
            this.ymin = Math.sqrt(evaluate * evaluate + evaluate2 * evaluate2);
            this.ymax = this.ymin;
            for (int i = 1; i < n; ++i) {
                xmin += n2;
                final double evaluate3 = this.parserRe.evaluate(xmin, this.time);
                final double evaluate4 = this.parserIm.evaluate(xmin, this.time);
                final double sqrt = Math.sqrt(evaluate3 * evaluate3 + evaluate4 * evaluate4);
                this.ymin = Math.min(this.ymin, sqrt);
                this.ymax = Math.max(this.ymax, sqrt);
            }
        }
    }
}
