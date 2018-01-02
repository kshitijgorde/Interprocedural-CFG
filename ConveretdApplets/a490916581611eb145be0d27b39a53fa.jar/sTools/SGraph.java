// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

import sTools.graph.Markers;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import sTools.graph.TextLine;
import sTools.graph.DataSet;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import sTools.graph.Axis;
import sTools.graph.Graph2D;

public class SGraph extends Graph2D implements Cloneable, SDataListener
{
    Axis xaxis;
    Axis yaxis;
    int[] borders;
    Format format;
    private boolean autoRefresh;
    private MouseMotionAdapter mouseMotionAdapter;
    private MouseAdapter mouseAdapter;
    private int boxWidth;
    private boolean sampleData;
    private Vector dataSetSeries;
    private Vector functions;
    private double[] dPoint;
    private DataSet defaultData;
    private String labelX;
    private String labelY;
    private double defaultMarkerScale;
    private boolean enableMouse;
    private SApplet parentSApplet;
    private String titleStr;
    private TextLine title;
    
    public SGraph() {
        this.borders = new int[] { 0, 10, 10, 0 };
        this.format = new Format("%-+6.2g");
        this.autoRefresh = true;
        this.boxWidth = 0;
        this.sampleData = true;
        this.dataSetSeries = new Vector();
        this.functions = new Vector();
        this.dPoint = new double[2];
        this.defaultData = null;
        this.labelX = "X Axis";
        this.labelY = "Y Axis";
        this.defaultMarkerScale = 1.0;
        this.enableMouse = false;
        this.titleStr = null;
        (this.title = new TextLine()).setFontStyle(1);
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
        (this.xaxis = this.createAxis(5)).setTitleColor(Color.black);
        this.xaxis.setLabelColor(Color.black);
        this.xaxis.setTitleText(this.labelX);
        this.xaxis.setTitleFont(new Font("TimesRoman", 0, 12));
        this.xaxis.setLabelFont(new Font("Helvetica", 0, 10));
        (this.yaxis = this.createAxis(2)).setTitleColor(Color.black);
        this.yaxis.setLabelColor(Color.black);
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
    }
    
    public SGraph(final SApplet parentSApplet) {
        this();
        this.parentSApplet = parentSApplet;
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
            this.repaint();
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
            this.repaint();
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
            this.repaint();
        }
    }
    
    public void setAutoscaleX(final boolean b) {
        if (!b == this.xaxis.isManualRange()) {
            return;
        }
        this.xaxis.setManualRange(!b);
        if (this.autoRefresh) {
            this.repaint();
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
            this.repaint();
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
            this.repaint();
        }
    }
    
    public boolean isDrawZero() {
        return super.drawzero;
    }
    
    public synchronized void setSeriesLegend(final int n, final Color color, final int n2, final int n3, final String s) {
        Series series = null;
        boolean b = false;
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            series = (Series)this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                b = true;
                break;
            }
        }
        DataSet set;
        if (!b) {
            this.dPoint[0] = 0.0;
            this.dPoint[1] = 0.0;
            set = this.attachArray(n, this.dPoint).getDataSet();
            set.deleteData();
        }
        else {
            set = series.data;
        }
        set.legend(n2, n3, s);
        if (color != null) {
            set.legendColor(color);
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized void setSeriesLegend(final int n, final int n2, final int n3, final String s) {
        final Color black = Color.black;
        this.setSeriesLegend(n, this.createSeries(n).data.markercolor, n2, n3, s);
    }
    
    public synchronized void setSeriesLegendColor(final int n, final Color color) {
        this.createSeries(n).data.legendColor(color);
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized void setSeriesStyle(final int n, final Color color, final boolean b, final int marker) {
        Series series = null;
        boolean b2 = false;
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            series = (Series)this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                b2 = true;
                break;
            }
        }
        DataSet set;
        if (!b2) {
            this.dPoint[0] = 0.0;
            this.dPoint[1] = 0.0;
            set = this.attachArray(n, this.dPoint).getDataSet();
            set.deleteData();
        }
        else {
            set = series.data;
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
            this.repaint();
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
            this.repaint();
        }
    }
    
    public String getBorders() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("").concat(String.valueOf(this.borders[0]))).concat(String.valueOf(','))).concat(String.valueOf(this.borders[1]))).concat(String.valueOf(','))).concat(String.valueOf(this.borders[2]))).concat(String.valueOf(','))).concat(String.valueOf(this.borders[3]));
    }
    
    public int getID() {
        return this.hashCode();
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
    
    public void setLabelX(final String s) {
        this.xaxis.setTitleColor(Color.black);
        this.xaxis.setTitleText(s);
        this.labelX = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setLabelX(final String s, final Color titleColor) {
        this.xaxis.setTitleColor(titleColor);
        this.xaxis.setTitleText(s);
        this.labelX = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public String getLabelX() {
        return this.labelX;
    }
    
    public void setLabelY(final String s) {
        this.yaxis.setTitleColor(Color.black);
        this.yaxis.setTitleText(s);
        this.labelY = s;
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
    
    public void setLabelY(final String s, final Color titleColor) {
        this.yaxis.setTitleColor(titleColor);
        this.yaxis.setTitleText(s);
        this.labelY = s;
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public String getLabelY() {
        return this.labelY;
    }
    
    public void setMarkerSize(final double defaultMarkerScale) {
        this.defaultMarkerScale = defaultMarkerScale;
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
    
    public double getMinX() {
        return this.xaxis.minimum;
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
    
    public double getMinY() {
        return this.yaxis.minimum;
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
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                return mathFunction;
            }
        }
        return null;
    }
    
    private Series getSeriesFromSID(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
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
    
    public int getIDFromSID(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                return series.hashCode();
            }
        }
        return 0;
    }
    
    public void setObjectColor(final int n, final Color linecolor) {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                mathFunction.color = linecolor;
                if (this.autoRefresh) {
                    this.repaint();
                }
                return;
            }
        }
        final Enumeration<Series> elements2 = this.dataSetSeries.elements();
        while (elements2.hasMoreElements()) {
            final Series series = elements2.nextElement();
            if (series.hashCode() == n) {
                series.data.markercolor = linecolor;
                series.data.linecolor = linecolor;
                if (this.autoRefresh) {
                    this.repaint();
                }
            }
        }
    }
    
    public void setOwner(final SApplet parentSApplet) {
        this.parentSApplet = parentSApplet;
    }
    
    public SApplet getOwner() {
        return this.parentSApplet;
    }
    
    public void deleteAllFunctions() {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            SApplet.dataSources.remove(Integer.toString(elements.nextElement().hashCode()));
        }
        this.functions.removeAllElements();
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void deleteFunction(final int n) {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                SApplet.dataSources.remove(Integer.toString(mathFunction.hashCode()));
                this.functions.removeElement(mathFunction);
                break;
            }
        }
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void deleteAllSeries() {
        final Enumeration<Series> elements = this.dataSetSeries.elements();
        while (elements.hasMoreElements()) {
            SApplet.dataSources.remove(Integer.toString(elements.nextElement().hashCode()));
        }
        this.dataSetSeries.removeAllElements();
        this.detachDataSets();
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        this.defaultData = null;
        this.makeDefaultData();
        if (!this.xaxis.isManualRange()) {
            this.setMinMaxX(0.0, 1.0);
        }
        if (!this.yaxis.isManualRange()) {
            this.setMinMaxY(0.0, 1.0);
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
                SApplet.dataSources.remove(Integer.toString(series.hashCode()));
                this.dataSetSeries.removeElement(series);
                this.detachDataSet(series.data);
                break;
            }
        }
        if (this.dataSetSeries.size() == 0) {
            this.detachDataSets();
            this.defaultData = null;
            this.makeDefaultData();
        }
        if (this.parentSApplet != null) {
            this.parentSApplet.cleanupDataConnections();
        }
        if (b && this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void setFunctionRange(final int n, final double n2, final double n3, final int n4) {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            if (mathFunction.hashCode() == n) {
                mathFunction.setFunctionRange(n2, n3, n4);
            }
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized boolean changeFunctionString(final int n, final String string) {
        final MathFunction function = this.getFunction(n);
        if (function == null) {
            return false;
        }
        if (!function.setString(string)) {
            return false;
        }
        function.setScale();
        if (this.autoRefresh) {
            this.repaint();
        }
        return true;
    }
    
    public Object clone() {
        final SGraph sGraph = new SGraph(this.parentSApplet);
        sGraph.autoRefresh = this.autoRefresh;
        sGraph.setSampleData(false);
        sGraph.setTitle(this.titleStr);
        sGraph.setAutoscaleX(this.isAutoscaleX());
        sGraph.setAutoscaleY(this.isAutoscaleY());
        sGraph.setLabelX(this.labelX);
        sGraph.setLabelY(this.labelY);
        sGraph.setMinMaxX(this.getMinX(), this.getMaxX());
        sGraph.setMinMaxY(this.getMinY(), this.getMaxY());
        sGraph.defaultMarkerScale = this.defaultMarkerScale;
        sGraph.drawgrid = this.isDrawGrid();
        sGraph.drawzero = this.isDrawZero();
        sGraph.enableMouse = this.enableMouse;
        sGraph.deleteAllSeries();
        for (int i = 0; i < this.dataSetSeries.size(); ++i) {
            sGraph.addDataSeries((Series)this.dataSetSeries.elementAt(i));
        }
        sGraph.deleteAllFunctions();
        final Enumeration<MathFunction> elements = (Enumeration<MathFunction>)this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            final int addFunction = sGraph.addFunction(mathFunction.getFunctionStr());
            sGraph.setFunctionRange(addFunction, mathFunction.xmin, mathFunction.xmax, mathFunction.numPts);
            sGraph.setObjectColor(addFunction, mathFunction.color);
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
        Series emptySeries = null;
        boolean b = false;
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            emptySeries = (Series)this.dataSetSeries.elementAt(i);
            if (n == emptySeries.sid) {
                b = true;
                break;
            }
        }
        if (!b) {
            emptySeries = this.makeEmptySeries(n);
        }
        return emptySeries;
    }
    
    public void clearAllData() {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            ((Series)this.dataSetSeries.elementAt(i)).data.deleteData();
        }
        this.makeDefaultData();
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void clearSeries(final int n) {
        this.clearSeriesData(n);
    }
    
    public void clearSeriesData(final int n) {
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            final Series series = this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                series.data.deleteData();
                series.setOwner(this.parentSApplet);
                break;
            }
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public void adjustScale() {
        if (this.yaxis.isManualRange()) {
            return;
        }
        double minimum = this.yaxis.minimum;
        double maximum = this.yaxis.maximum;
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            final MathFunction mathFunction = elements.nextElement();
            minimum = Math.min(mathFunction.ymin, minimum);
            maximum = Math.max(mathFunction.ymax, maximum);
        }
        this.yaxis.minimum = minimum;
        this.yaxis.maximum = maximum;
        this.yaxis.calculateGridLabels();
    }
    
    public void paintFunctions(final Graphics graphics, final Rectangle rectangle) {
        final Enumeration<MathFunction> elements = this.functions.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(graphics, rectangle);
        }
    }
    
    public void paintOffScreen(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        if (width < 10 || height < 10) {
            graphics.fillRect(0, 0, width, height);
            return;
        }
        final Image image = this.createImage(width, height);
        final Graphics graphics2 = image.getGraphics();
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, width, height, this);
        graphics2.dispose();
    }
    
    public void paintLast(final Graphics graphics, final Rectangle rectangle) {
        final Enumeration<Series> elements = this.dataSetSeries.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paintLastPoint(graphics, rectangle);
        }
        if (this.titleStr != null) {
            graphics.setColor(Color.black);
            this.title.draw(graphics, rectangle.x + rectangle.width / 2, rectangle.y + Math.max(10 + rectangle.height / 20, 16), 0);
        }
    }
    
    public int pixFromX(final double n) {
        return this.xaxis.getInteger(n);
    }
    
    public int pixFromY(final double n) {
        return this.yaxis.getInteger(n);
    }
    
    public double xFromPix(final int n) {
        return this.xaxis.getDouble(n);
    }
    
    public double yFromPix(final int n) {
        return this.yaxis.getDouble(n);
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
            System.out.println("Erroe: DataSet not created in SGraph.addDataSetSeries.");
            return null;
        }
        dataSetFromSID.markercolor = series.getDataSet().markercolor;
        dataSetFromSID.linestyle = series.getDataSet().linestyle;
        dataSetFromSID.linecolor = series.getDataSet().linecolor;
        dataSetFromSID.markercolor = series.getDataSet().markercolor;
        dataSetFromSID.markerscale = series.getDataSet().markerscale;
        dataSetFromSID.marker = series.getDataSet().marker;
        dataSetFromSID.legend(series.getDataSet().getLegend_ix(), series.getDataSet().getLegend_iy(), series.getDataSet().getLegend());
        return dataSetFromSID;
    }
    
    public synchronized void addDatum(final int n, final double n2, final double n3) {
        boolean b = false;
        Series attachArray = null;
        boolean b2 = false;
        final double minimum = this.xaxis.minimum;
        final double maximum = this.xaxis.maximum;
        final double minimum2 = this.yaxis.minimum;
        final double maximum2 = this.yaxis.maximum;
        if (n2 < minimum || n2 > maximum || n3 < minimum2 || n3 > maximum2) {
            b = true;
        }
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            attachArray = (Series)this.dataSetSeries.elementAt(i);
            if (n == attachArray.sid) {
                b2 = true;
                break;
            }
        }
        this.dPoint[0] = n2;
        this.dPoint[1] = n3;
        if (!b2) {
            attachArray = this.attachArray(n, this.dPoint);
            b = true;
        }
        else {
            try {
                if (attachArray.data.dataPoints() == 0) {
                    this.removeDefaultData();
                    b = true;
                }
                attachArray.data.append(this.dPoint, 1);
            }
            catch (Exception ex) {
                System.out.println("Error appending Data!");
            }
        }
        if (minimum != this.xaxis.minimum || maximum != this.xaxis.maximum || minimum2 != this.yaxis.minimum || maximum2 != this.yaxis.maximum) {
            b = true;
        }
        if (!this.autoRefresh) {
            return;
        }
        if (super.datarect == null) {
            return;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics == null) {
            return;
        }
        if (attachArray.enableLPCursor) {
            this.paintOffScreen(graphics);
        }
        else if (this.xaxis.isManualRange() && this.yaxis.isManualRange()) {
            attachArray.data.draw_data(graphics, super.datarect);
            attachArray.paintLastPoint(graphics, super.datarect);
        }
        else if (b) {
            this.paintOffScreen(graphics);
        }
        else {
            attachArray.data.draw_data(graphics, super.datarect);
            attachArray.paintLastPoint(graphics, super.datarect);
        }
        graphics.dispose();
    }
    
    public synchronized void addData(final int n, final double[] array, final double[] array2) {
        this.addData(n, array, array2, array.length);
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
        Series series = null;
        boolean b = false;
        for (int size = this.dataSetSeries.size(), i = 0; i < size; ++i) {
            series = (Series)this.dataSetSeries.elementAt(i);
            if (n == series.sid) {
                b = true;
                break;
            }
        }
        final double[] array3 = new double[2 * length];
        int j;
        for (int n3 = j = 0; j < length; ++j, n3 += 2) {
            array3[n3] = array[j];
            array3[n3 + 1] = array2[j];
        }
        if (!b) {
            this.attachArray(n, array3);
        }
        else {
            try {
                series.data.append(array3, length);
            }
            catch (Exception ex) {
                System.out.println("Error appending Data!");
            }
        }
        if (this.autoRefresh) {
            this.repaint();
        }
    }
    
    public synchronized int addFunction(final String s) {
        final MathFunction mathFunction = new MathFunction(s);
        this.functions.addElement(mathFunction);
        if (super.datarect != null) {
            final Rectangle datarect = super.datarect;
            mathFunction.xmin = this.xFromPix(datarect.x);
            mathFunction.xmax = this.xFromPix(datarect.x + datarect.width - 1);
            mathFunction.numPts = datarect.width;
            mathFunction.setScale();
        }
        if (this.autoRefresh) {
            this.repaint();
        }
        return mathFunction.hashCode();
    }
    
    public void drawCoords(final int n, final int n2) {
        final String concat = String.valueOf(String.valueOf(String.valueOf("").concat(String.valueOf(this.format.form(this.xFromPix(n))))).concat(String.valueOf(" , "))).concat(String.valueOf(this.format.form(this.yFromPix(n2))));
        final Graphics graphics = this.getGraphics();
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.yellow);
        this.boxWidth = Math.max(20 + graphics.getFontMetrics(graphics.getFont()).stringWidth(concat), this.boxWidth);
        graphics.fillRect(0, bounds.height - 20, this.boxWidth, 20);
        graphics.setColor(Color.black);
        graphics.drawString(concat, 10, bounds.height - 5);
        graphics.dispose();
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
    
    void sGraph_mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            final SGraphFrame sGraphFrame = new SGraphFrame((SGraph)this.clone());
            sGraphFrame.setSize(this.getSize().width, this.getSize().height);
            sGraphFrame.show();
        }
        else {
            this.drawCoords(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    void sGraph_mouseDragged(final MouseEvent mouseEvent) {
        this.drawCoords(mouseEvent.getX(), mouseEvent.getY());
    }
    
    void sGraph_mouseReleased(final MouseEvent mouseEvent) {
        this.repaint(0, this.getBounds().height - 20, this.boxWidth, 20);
        this.boxWidth = 0;
    }
    
    public void sGraph_mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(1));
    }
    
    public void sGraph_mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void sGraph_mouseMoved(final MouseEvent mouseEvent) {
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
        this.removeDefaultData();
        return series;
    }
    
    private void makeDefaultData() {
        if (this.defaultData != null) {
            return;
        }
        this.defaultData = this.loadDataSet(new double[] { 0.0, 0.0 }, 1);
        this.xaxis.attachDataSet(this.defaultData);
        this.yaxis.attachDataSet(this.defaultData);
    }
    
    private void removeDefaultData() {
        if (this.defaultData == null) {
            return;
        }
        this.detachDataSet(this.defaultData);
        this.defaultData = null;
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
    
    class Series implements SDataSource
    {
        private DataSet data;
        private int sid;
        boolean enableLPCursor;
        String[] varStrings;
        SApplet owner;
        
        public Series(final int sid, final DataSet data) {
            this.enableLPCursor = false;
            this.varStrings = new String[] { "x", "y", "u", "v" };
            this.owner = null;
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
        
        void paintLastPoint(final Graphics graphics, final Rectangle rectangle) {
            final int dataPoints = this.data.dataPoints();
            if (dataPoints < 1 || !this.enableLPCursor) {
                return;
            }
            final double[] point = this.data.getPoint(dataPoints - 1);
            final int pixFromX = SGraph.this.pixFromX(point[0]);
            final int pixFromY = SGraph.this.pixFromY(point[1]);
            graphics.setColor(Color.red);
            graphics.fillOval(pixFromX - 2 - 1, pixFromY - 2 - 1, 5, 5);
            graphics.setColor(Color.black);
            graphics.drawOval(pixFromX - 2 - 1, pixFromY - 2 - 1, 5, 5);
        }
        
        double[] getX() {
            final int dataPoints = this.data.dataPoints();
            final double[] array = new double[dataPoints];
            for (int i = 0; i < dataPoints; ++i) {
                array[i] = this.data.getData()[2 * i];
            }
            return array;
        }
        
        double[] getY() {
            final int dataPoints = this.data.dataPoints();
            final double[] array = new double[dataPoints];
            for (int i = 0; i < dataPoints; ++i) {
                array[i] = this.data.getData()[2 * i + 1];
            }
            return array;
        }
        
        int getNumpts() {
            return this.data.dataPoints();
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
    
    class MathFunction implements Cloneable, SDataSource
    {
        Parser parser;
        Color color;
        int numPts;
        double xmin;
        double xmax;
        double ymin;
        double ymax;
        boolean fixedRange;
        String[] varStrings;
        SApplet owner;
        
        MathFunction(String trim) {
            this.parser = null;
            this.color = Color.black;
            this.numPts = 100;
            this.xmin = 0.0;
            this.xmax = 0.0;
            this.ymin = 0.0;
            this.ymax = 0.0;
            this.fixedRange = false;
            this.varStrings = new String[] { "x", "y", "v", "a" };
            this.owner = null;
            trim = trim.trim();
            (this.parser = new Parser(1)).defineVariable(1, "x");
            this.parser.define(trim);
            this.parser.parse();
            if (this.parser.getErrorCode() != 0) {
                System.out.println(String.valueOf("Failed to parse f(x)): ").concat(String.valueOf(this.parser)));
                System.out.println(String.valueOf(String.valueOf(String.valueOf("Parse error in MathFunction: ").concat(String.valueOf(this.parser.getErrorString()))).concat(String.valueOf(" at function 1, position "))).concat(String.valueOf(this.parser.getErrorPosition())));
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
        }
        
        public boolean setString(String trim) {
            trim = trim.trim();
            this.parser.define(trim);
            this.parser.parse();
            if (this.parser.getErrorCode() != 0) {
                System.out.println(String.valueOf("Failed to parse f(x)): ").concat(String.valueOf(trim)));
                System.out.println(String.valueOf(String.valueOf(String.valueOf("Parse error in MathFunction: ").concat(String.valueOf(this.parser.getErrorString()))).concat(String.valueOf(" at function 1, position "))).concat(String.valueOf(this.parser.getErrorPosition())));
                return false;
            }
            this.setScale();
            this.owner.clearData(this.hashCode());
            this.owner.updateDataConnection(this.hashCode());
            return true;
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
            this.fixedRange = true;
        }
        
        void paint(final Graphics graphics, final Rectangle rectangle) {
            if (this.fixedRange) {
                this.paint2(graphics, rectangle);
            }
            else {
                this.paint1(graphics, rectangle);
            }
        }
        
        void paint1(final Graphics graphics, final Rectangle rectangle) {
            this.numPts = rectangle.width;
            if (this.numPts < 1) {
                return;
            }
            this.xmin = SGraph.this.xFromPix(rectangle.x);
            this.xmax = SGraph.this.xFromPix(rectangle.x + rectangle.width - 1);
            this.ymin = this.parser.evaluate(this.xmin);
            this.ymax = this.ymin;
            int pixFromY = SGraph.this.pixFromY(this.ymin);
            graphics.setColor(this.color);
            for (int i = 1; i < rectangle.width; ++i) {
                final double evaluate = this.parser.evaluate(SGraph.this.xFromPix(rectangle.x + i));
                if (evaluate < this.ymin) {
                    this.ymin = evaluate;
                }
                if (evaluate > this.ymax) {
                    this.ymax = evaluate;
                }
                final int pixFromY2 = SGraph.this.pixFromY(evaluate);
                graphics.drawLine(rectangle.x + i - 1, pixFromY, rectangle.x + i, pixFromY2);
                pixFromY = pixFromY2;
            }
        }
        
        void paint2(final Graphics graphics, final Rectangle rectangle) {
            if (this.numPts < 1) {
                return;
            }
            final double n = (this.xmax - this.xmin) / (this.numPts - 1);
            double xmin = this.xmin;
            int pixFromX = SGraph.this.pixFromX(xmin);
            this.ymin = this.parser.evaluate(xmin);
            this.ymax = this.ymin;
            int pixFromY = SGraph.this.pixFromY(this.ymin);
            graphics.setColor(this.color);
            for (int i = 1; i < this.numPts; ++i) {
                xmin += n;
                final int pixFromX2 = SGraph.this.pixFromX(xmin);
                final double evaluate = this.parser.evaluate(xmin);
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
        }
        
        public double[][] getVariables() {
            double minimum = SGraph.this.xaxis.minimum;
            final double maximum = SGraph.this.xaxis.maximum;
            final double n = (maximum - minimum) / (this.numPts - 1);
            final double[][] array = new double[this.numPts][4];
            for (int i = 0; i < this.numPts; ++i) {
                array[i][0] = minimum;
                array[i][1] = this.parser.evaluate(minimum);
                minimum += n;
            }
            if (this.numPts < 4) {
                return array;
            }
            for (int j = 2; j < this.numPts - 2; ++j) {
                array[j][2] = (-array[j + 2][1] + 8 * array[j + 1][1] - 8 * array[j - 1][1] + array[j - 2][1]) / n / 12;
                array[j][3] = (-array[j + 2][1] + 16 * array[j + 1][1] - 30 * array[j][1] + 16 * array[j - 1][1] - array[j - 2][1]) / n / n / 12;
            }
            final double minimum2 = SGraph.this.xaxis.minimum;
            array[1][2] = (-array[3][1] + 8 * array[2][1] - 8 * array[0][1] + this.parser.evaluate(minimum2 - n)) / n / 12;
            array[0][2] = (-array[2][1] + 8 * array[1][1] - 8 * this.parser.evaluate(minimum2 - n) + this.parser.evaluate(minimum2 - 2 * n)) / n / 12;
            array[this.numPts - 1][2] = (-this.parser.evaluate(maximum + 2 * n) + 8 * this.parser.evaluate(maximum + n) - 8 * array[this.numPts - 2][1] + array[this.numPts - 3][1]) / n / 12;
            array[this.numPts - 2][2] = (-this.parser.evaluate(maximum + n) + 8 * array[this.numPts - 1][1] - 8 * array[this.numPts - 3][1] + array[this.numPts - 4][1]) / n / 12;
            array[1][3] = (-array[3][1] + 16 * array[2][1] - 30 * array[1][1] + 16 * array[0][1] - this.parser.evaluate(minimum2 - n)) / n / n / 12;
            array[0][3] = (-array[2][1] + 16 * array[1][1] - 30 * array[0][1] + 16 * this.parser.evaluate(minimum2 - n) - this.parser.evaluate(minimum2 - 2 * n)) / n / n / 12;
            array[this.numPts - 1][3] = (-this.parser.evaluate(maximum + 2 * n) + 16 * this.parser.evaluate(maximum + n) - 30 * array[this.numPts - 1][1] + 16 * array[this.numPts - 2][1] - array[this.numPts - 3][1]) / n / n / 12;
            array[this.numPts - 2][3] = (-this.parser.evaluate(maximum + n) + 16 * array[this.numPts - 1][1] - 30 * array[this.numPts - 2][1] + 16 * array[this.numPts - 3][1] - array[this.numPts - 4][1]) / n / n / 12;
            return array;
        }
        
        void setScale() {
            if (this.numPts < 2) {
                return;
            }
            final double n = (this.xmax - this.xmin) / (this.numPts - 1);
            double xmin = this.xmin;
            this.ymin = this.parser.evaluate(xmin);
            this.ymax = this.ymin;
            for (int i = 1; i < this.numPts; ++i) {
                xmin += n;
                final double evaluate = this.parser.evaluate(xmin);
                this.ymin = Math.min(this.ymin, evaluate);
                this.ymax = Math.max(this.ymax, evaluate);
            }
        }
    }
}
