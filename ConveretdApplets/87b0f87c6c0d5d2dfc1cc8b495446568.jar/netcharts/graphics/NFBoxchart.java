// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParam;
import netcharts.util.NFUtil;
import netcharts.util.NFParamDef;
import java.util.Hashtable;
import java.awt.Rectangle;
import netcharts.util.NFPercentile;
import netcharts.util.NFDataSet;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Graphics;
import java.applet.Applet;
import java.util.Vector;
import java.awt.Color;

public final class NFBoxchart extends NFDataChart
{
    public static final int RAW = 0;
    public static final int SUMMARY = 1;
    public static final int SUMMARYWITHMEAN = 2;
    protected Color MEDIAN_COLOR_DEFAULT;
    protected Color medianColor;
    protected Color OUTLIER_COLOR_DEFAULT;
    protected Color outlierColor;
    protected int DATA_TYPE_DEFAULT;
    protected int dataType;
    protected int BOX_HEIGHT_DEFAULT;
    protected int boxHeight;
    protected double BOX_WIDTH_FACTOR_DEFAULT;
    protected double boxWidthFactor;
    protected double BOX_SYMBOL_WIDTH_FACTOR_DEFAULT;
    protected double boxSymbolWidthFactor;
    public static final int OFF = 0;
    public static final int LINEAR = 1;
    public static final int SQRT = 2;
    protected int BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT;
    protected int boxSymbolWidthRelative;
    protected boolean SHOW_DATA_POINTS_DEFAULT;
    protected boolean showDataPoints;
    protected boolean DRAW_FENCES_DEFAULT;
    protected boolean drawFences;
    protected int BOX_LAYOUT_DEFAULT;
    protected int boxLayout;
    public static final int STANDARD = 0;
    public static final int EDA = 1;
    public static final int TENNINETY = 2;
    public static final int GAUSSIAN = 3;
    protected int PLOT_TYPE_DEFAULT;
    protected int plotType;
    public static final int BOX = 0;
    public static final int LINE = 1;
    protected int WHISKER_TYPE_DEFAULT;
    protected int whiskerType;
    public static final int OVER = 0;
    public static final int UNDER = 1;
    protected int FENCE_POSITION_DEFAULT;
    protected int fencePosition;
    protected boolean NATURAL_DISPLAY_ORDER_DEFAULT;
    protected boolean naturalDisplayOrder;
    protected boolean BOX_FENCES_DEFAULT;
    protected boolean boxFences;
    protected boolean DATA_POINT_JITTER_DEFAULT;
    protected boolean dataPointJitter;
    protected static final int REGION_NONE = -1;
    protected static final int REGION_START = 0;
    protected static final int REGION_STOP = 1;
    protected static final String OUTLIER_SYMBOL_PARAM = "OutlierSymbol";
    protected static final String DATA_POINT_SYMBOL_PARAM = "DataPointSymbol";
    protected static final String MEAN_SYMBOL_PARAM = "MeanSymbol";
    protected Color MEAN_COLOR_DEFAULT;
    protected Color meanColor;
    protected Color DATA_POINT_COLOR_DEFAULT;
    protected Color dataPointColor;
    protected NFLine MEAN_LINE_DEFAULT;
    protected NFLine meanLine;
    protected Vector meanActiveLabels;
    protected int MINIMUM_DATA_POINTS_DEFAULT;
    protected int minimumDataPoints;
    protected Vector boxActiveLabels;
    private static final boolean a = false;
    
    public NFBoxchart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.MEDIAN_COLOR_DEFAULT = Color.white;
        this.medianColor = this.MEDIAN_COLOR_DEFAULT;
        this.OUTLIER_COLOR_DEFAULT = null;
        this.outlierColor = this.OUTLIER_COLOR_DEFAULT;
        this.DATA_TYPE_DEFAULT = 0;
        this.dataType = this.DATA_TYPE_DEFAULT;
        this.BOX_HEIGHT_DEFAULT = 0;
        this.boxHeight = this.BOX_HEIGHT_DEFAULT;
        this.BOX_WIDTH_FACTOR_DEFAULT = 0.75;
        this.boxWidthFactor = this.BOX_WIDTH_FACTOR_DEFAULT;
        this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT = 0.95;
        this.boxSymbolWidthFactor = this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT;
        this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT = 0;
        this.boxSymbolWidthRelative = this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT;
        this.SHOW_DATA_POINTS_DEFAULT = false;
        this.showDataPoints = this.SHOW_DATA_POINTS_DEFAULT;
        this.DRAW_FENCES_DEFAULT = true;
        this.drawFences = this.DRAW_FENCES_DEFAULT;
        this.BOX_LAYOUT_DEFAULT = 2;
        this.boxLayout = this.BOX_LAYOUT_DEFAULT;
        this.PLOT_TYPE_DEFAULT = 0;
        this.plotType = this.PLOT_TYPE_DEFAULT;
        this.WHISKER_TYPE_DEFAULT = 0;
        this.whiskerType = this.WHISKER_TYPE_DEFAULT;
        this.FENCE_POSITION_DEFAULT = 0;
        this.fencePosition = this.FENCE_POSITION_DEFAULT;
        this.NATURAL_DISPLAY_ORDER_DEFAULT = false;
        this.naturalDisplayOrder = this.NATURAL_DISPLAY_ORDER_DEFAULT;
        this.BOX_FENCES_DEFAULT = true;
        this.boxFences = this.BOX_FENCES_DEFAULT;
        this.DATA_POINT_JITTER_DEFAULT = true;
        this.dataPointJitter = this.DATA_POINT_JITTER_DEFAULT;
        this.MEAN_COLOR_DEFAULT = null;
        this.meanColor = this.MEAN_COLOR_DEFAULT;
        this.DATA_POINT_COLOR_DEFAULT = null;
        this.dataPointColor = this.DATA_POINT_COLOR_DEFAULT;
        this.MEAN_LINE_DEFAULT = null;
        this.meanLine = this.MEAN_LINE_DEFAULT;
        this.meanActiveLabels = new Vector();
        this.MINIMUM_DATA_POINTS_DEFAULT = 0;
        this.minimumDataPoints = this.MINIMUM_DATA_POINTS_DEFAULT;
        this.boxActiveLabels = null;
        this.initGraph(applet);
        this.initBoxchart();
        this.reshape(n, n2, n3, n4);
    }
    
    public NFBoxchart(final Applet applet) {
        this.MEDIAN_COLOR_DEFAULT = Color.white;
        this.medianColor = this.MEDIAN_COLOR_DEFAULT;
        this.OUTLIER_COLOR_DEFAULT = null;
        this.outlierColor = this.OUTLIER_COLOR_DEFAULT;
        this.DATA_TYPE_DEFAULT = 0;
        this.dataType = this.DATA_TYPE_DEFAULT;
        this.BOX_HEIGHT_DEFAULT = 0;
        this.boxHeight = this.BOX_HEIGHT_DEFAULT;
        this.BOX_WIDTH_FACTOR_DEFAULT = 0.75;
        this.boxWidthFactor = this.BOX_WIDTH_FACTOR_DEFAULT;
        this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT = 0.95;
        this.boxSymbolWidthFactor = this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT;
        this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT = 0;
        this.boxSymbolWidthRelative = this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT;
        this.SHOW_DATA_POINTS_DEFAULT = false;
        this.showDataPoints = this.SHOW_DATA_POINTS_DEFAULT;
        this.DRAW_FENCES_DEFAULT = true;
        this.drawFences = this.DRAW_FENCES_DEFAULT;
        this.BOX_LAYOUT_DEFAULT = 2;
        this.boxLayout = this.BOX_LAYOUT_DEFAULT;
        this.PLOT_TYPE_DEFAULT = 0;
        this.plotType = this.PLOT_TYPE_DEFAULT;
        this.WHISKER_TYPE_DEFAULT = 0;
        this.whiskerType = this.WHISKER_TYPE_DEFAULT;
        this.FENCE_POSITION_DEFAULT = 0;
        this.fencePosition = this.FENCE_POSITION_DEFAULT;
        this.NATURAL_DISPLAY_ORDER_DEFAULT = false;
        this.naturalDisplayOrder = this.NATURAL_DISPLAY_ORDER_DEFAULT;
        this.BOX_FENCES_DEFAULT = true;
        this.boxFences = this.BOX_FENCES_DEFAULT;
        this.DATA_POINT_JITTER_DEFAULT = true;
        this.dataPointJitter = this.DATA_POINT_JITTER_DEFAULT;
        this.MEAN_COLOR_DEFAULT = null;
        this.meanColor = this.MEAN_COLOR_DEFAULT;
        this.DATA_POINT_COLOR_DEFAULT = null;
        this.dataPointColor = this.DATA_POINT_COLOR_DEFAULT;
        this.MEAN_LINE_DEFAULT = null;
        this.meanLine = this.MEAN_LINE_DEFAULT;
        this.meanActiveLabels = new Vector();
        this.MINIMUM_DATA_POINTS_DEFAULT = 0;
        this.minimumDataPoints = this.MINIMUM_DATA_POINTS_DEFAULT;
        this.boxActiveLabels = null;
        this.initGraph(applet);
        this.initBoxchart();
    }
    
    protected void initBoxchart() {
        this.initChart();
    }
    
    public void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
    }
    
    public void setDefaultAxis(final NFAxis nfAxis) {
        if (nfAxis != this.getYAxis(super.bottomAxis, super.leftAxis)) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        final int size = super.dataSeries.size();
        if (this.getYAxis(super.bottomAxis, super.leftAxis) == null || size == 0) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        nfAxis.reverseActiveLabels = !this.naturalDisplayOrder;
        final Vector ticLabels = new Vector<String>();
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 5) {
                if (!this.naturalDisplayOrder) {
                    ticLabels.insertElementAt(nfDataSeries.name, 0);
                }
                else {
                    ticLabels.addElement(nfDataSeries.name);
                }
            }
        }
        final Vector ticLabels2 = nfAxis.getTicLabels();
        if ((ticLabels2 == null || ticLabels2.size() == 0) && ticLabels.size() > 0) {
            nfAxis.setTicLabels(ticLabels);
            nfAxis.setSpacing(new NFSpacing(0.0, size - 1.0, 1.0));
        }
        if (nfAxis.autoscale) {
            nfAxis.setMinMax(-0.5, size - 0.5);
        }
        else {
            final double n = Math.ceil(nfAxis.getMin()) - 0.5;
            final double n2 = Math.floor(nfAxis.getMax()) + 0.5;
            if (n != nfAxis.getMin() || n2 != nfAxis.getMax()) {
                nfAxis.setMinMax(n, n2);
            }
        }
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfDataSeries.type != 5) {
            return super.getMinMax(nfDataSeries, nfAxis, array);
        }
        if (this.getYAxis(nfDataSeries.XAxis, nfDataSeries.YAxis) == nfAxis) {
            final int size = super.dataSeries.size();
            if (-0.5 < array[0]) {
                array[0] = -0.5;
            }
            if (size - 0.5 > array[1]) {
                array[1] = size - 0.5;
            }
            return array;
        }
        if (this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis) == nfAxis) {
            final double[] minMax = nfDataSeries.dataset.getMinMax(1);
            if (minMax != null) {
                if (minMax[0] < array[0]) {
                    array[0] = minMax[0];
                }
                if (minMax[1] > array[1]) {
                    array[1] = minMax[1];
                }
            }
            final NFBoxInfo info = getInfo(nfDataSeries);
            if (info.a != null) {
                final Object[][] plot = getPlot(this.plotType, nfDataSeries, 0);
                final double[] array2 = { ((Number)plot[0][0]).doubleValue(), ((Number)plot[plot.length - 1][0]).doubleValue() };
                if (array2[0] < array[0]) {
                    array[0] = array2[0];
                }
                if (array2[1] > array[1]) {
                    array[1] = array2[1];
                }
            }
            for (int n = 0; info.f != null && n < info.f.size(); ++n) {
                final double doubleValue = info.f.elementAt(n).doubleValue();
                if (doubleValue < array[0]) {
                    array[0] = doubleValue;
                }
                if (doubleValue > array[1]) {
                    array[1] = doubleValue;
                }
            }
            return array;
        }
        return super.getMinMax(nfDataSeries, nfAxis, array);
    }
    
    protected void drawData(final Graphics graphics) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        double max = 0.0;
        for (int n = 0; this.boxSymbolWidthRelative != 0 && n < size; ++n) {
            max = Math.max(max, getInfo((NFDataSeries)super.dataSeries.elementAt(n)).a.sampleSize);
        }
        for (int i = 0; i < 2; ++i) {
            final Polygon polygon = new Polygon();
            for (int j = 0; j < size; ++j) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                this.setActiveLabel(nfDataSeries, this.meanActiveLabels, "MeanActiveLabels", j, -1, -1, 0, 0, "", null, null);
                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                final Point drawBox = this.drawBox(clippedGraphics, nfDataSeries, j, size, i == 1, this.boxSymbolWidthFactor * ((this.dataType == 0) ? ((this.boxSymbolWidthRelative != 0) ? ((this.boxSymbolWidthRelative == 1) ? (getInfo(nfDataSeries).a.sampleSize / max) : (Math.sqrt(getInfo(nfDataSeries).a.sampleSize) / Math.sqrt(max))) : 1.0) : 1.0), (this.dataType == 0) ? this.minimumDataPoints : 0);
                if (drawBox != null) {
                    polygon.addPoint(drawBox.x, drawBox.y);
                }
                if (i == 0 && j > 0) {
                    final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(j - 1);
                    if ((drawBox == null || j == size - 1 || nfDataSeries.XAxis != nfDataSeries2.XAxis || nfDataSeries.YAxis != nfDataSeries2.YAxis) && polygon.npoints > 0) {
                        if (this.meanLine != null) {
                            Color color = this.meanLine.getColor();
                            if (color == null) {
                                color = this.meanColor;
                            }
                            if (color == null) {
                                color = this.medianColor;
                            }
                            clippedGraphics.setColor(color);
                            this.meanLine.drawPoly(clippedGraphics, polygon);
                        }
                        polygon.npoints = 0;
                    }
                }
                clippedGraphics.dispose();
            }
        }
    }
    
    public void drawDataLite(final Graphics graphics) {
    }
    
    protected Point drawBox(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final int n2, final boolean b, final double n3, final int n4) {
        final NFDataSet dataset = nfDataSeries.dataset;
        final NFBoxInfo info = getInfo(nfDataSeries);
        final NFPercentile a = info.a;
        final double min = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMin();
        final double max = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMax();
        final double min2 = this.getYAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMin();
        final double max2 = this.getYAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMax();
        final int n5 = this.naturalDisplayOrder ? n : (n2 - 1 - n);
        if (n5 < min2 || n5 > max2) {
            return null;
        }
        final double n6 = this.mapYValue(nfDataSeries.XAxis, nfDataSeries.YAxis, min2) - this.mapYValue(nfDataSeries.XAxis, nfDataSeries.YAxis, min2 + 1.0);
        double n7 = n6 * this.boxWidthFactor;
        if (this.boxHeight > 0) {
            n7 = this.boxHeight;
        }
        final int mapYValue = this.mapYValue(nfDataSeries.XAxis, nfDataSeries.YAxis, n5);
        final double n8 = mapYValue - n7 / 2.0;
        Point point = null;
        if (!Double.isNaN(a.mean)) {
            final int mapXValue = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, a.mean);
            point = this.getPoint(mapXValue, mapYValue);
            if (b) {
                if (info.d != null && info.d.type != 0) {
                    Color color = info.d.getColor();
                    if (color == null) {
                        color = this.meanColor;
                    }
                    if (color == null) {
                        color = this.medianColor;
                    }
                    graphics.setColor(color);
                    int subtract = mapXValue;
                    int subtract2 = mapYValue;
                    if (info.d.type == 7) {
                        subtract2 = subtract(subtract2, info.d.size / 2);
                    }
                    if (info.d.type == 8) {
                        subtract = subtract(subtract, info.d.size / 2);
                    }
                    final Point point2 = this.getPoint(subtract, subtract2);
                    if (info.d.type == 7 || info.d.type == 8) {
                        info.d.draw(graphics, point2.x, point2.y, info.d.size);
                    }
                    else {
                        info.d.draw(graphics, point2.x, point2.y);
                    }
                    final Rectangle rectangle = this.getRectangle(subtract(subtract, info.d.size / 2), subtract(subtract2, info.d.size / 2), info.d.size, info.d.size);
                    this.setActiveLabel(nfDataSeries, this.meanActiveLabels, "MeanActiveLabels", n, rectangle.x, rectangle.y, rectangle.width, rectangle.height, "Mean: " + this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(a.mean), null, info.d);
                }
                return point;
            }
        }
        for (int i = 0; i < dataset.size(); ++i) {
            this.setActiveLabel(nfDataSeries, i, -1, -1, 0, 0, "");
            this.setActiveLabel(nfDataSeries, info.h, "FenceActiveLabels" + (n + 1), i, -1, -1, 0, 0, "", null, null);
            this.setActiveLabel(nfDataSeries, info.i, "FenceActiveLabels" + (n + 1), i, -1, -1, 0, 0, "", null, null);
            this.setActiveLabel(nfDataSeries, info.g, "FenceActiveLabels" + (n + 1), i, -1, -1, 0, 0, "", null, null);
        }
        int n9 = (int)(n7 * n3);
        if (n9 % 2 == 0) {
            --n9;
        }
        final Object[][] plot = getPlot(this.plotType, nfDataSeries, n9);
        final boolean b2 = this.fencePosition == 1;
        for (int n10 = 0; a.sampleSize >= n4 && n10 < 2; ++n10) {
            int n11 = -1;
            for (int j = 0; j < plot.length; ++j) {
                final Object[] array = plot[j];
                final Number n12 = (Number)array[0];
                if (!Double.isNaN(n12.doubleValue())) {
                    final int mapXValue2 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, n12.doubleValue());
                    final int intValue = ((Number)array[3]).intValue();
                    if (n10 == (b2 ? 1 : 0)) {
                        switch (intValue) {
                            case 0:
                            case 1: {
                                if (n11 != -1 && !Double.isNaN(((Number)plot[n11][0]).doubleValue())) {
                                    final int mapXValue3 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, ((Number)plot[n11][0]).doubleValue());
                                    final int intValue2 = ((Number)plot[n11][4]).intValue();
                                    final Rectangle rectangle2 = this.getRectangle(mapXValue3, subtract(mapYValue, intValue2 / 2), subtract(mapXValue2 - mapXValue3, -1), intValue2);
                                    nfDataSeries.region.draw(graphics, rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, nfDataSeries.pattern);
                                }
                                if (intValue == 1) {
                                    n11 = -1;
                                    break;
                                }
                                n11 = j;
                                break;
                            }
                        }
                        if ((boolean)array[1] && j > 0 && this.whiskerType == 0 && !Double.isNaN(((Number)plot[j - 1][0]).doubleValue())) {
                            final int mapXValue4 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, ((Number)plot[j - 1][0]).doubleValue());
                            int n13 = (int)(2.0 * (n7 * n3 / 5.0));
                            if (n13 % 2 == 0) {
                                --n13;
                            }
                            final Rectangle rectangle3 = this.getRectangle(mapXValue4, subtract(mapYValue, n13 / 2), subtract(mapXValue2 - mapXValue4, -1), n13);
                            nfDataSeries.region.draw(graphics, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, nfDataSeries.pattern);
                        }
                    }
                    else {
                        if ((boolean)array[1] && j > 0 && this.whiskerType == 1 && !Double.isNaN(((Number)plot[j - 1][0]).doubleValue())) {
                            final Point point3 = this.getPoint(this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, ((Number)plot[j - 1][0]).doubleValue()), mapYValue);
                            final Point point4 = this.getPoint(mapXValue2, mapYValue);
                            NFLine.draw(graphics, point3.x, point3.y, point4.x, point4.y, 1, 1, (array[6] == null) ? this.medianColor : ((Color)array[6]), null, null);
                        }
                        int intValue3 = ((Number)array[2]).intValue();
                        if (this.boxFences && ((Number)array[3]).intValue() != 4) {
                            intValue3 = 1;
                        }
                        if (this.drawFences && intValue3 != 0) {
                            final Point point5 = this.getPoint(mapXValue2, (int)n8);
                            final Point point6 = this.getPoint(mapXValue2, (int)(n8 + n7));
                            NFLine.draw(graphics, point5.x, point5.y, point6.x, point6.y, 1, intValue3, (array[6] == null) ? this.medianColor : ((Color)array[6]), null, null);
                            final int n14 = 5;
                            final Rectangle rectangle4 = this.getRectangle(mapXValue2 - n14 / 2, (int)n8, n14, (int)n7);
                            if (this.plotType != 0 || this.beenLoaded("FenceActiveLabels" + (n + 1))) {
                                this.setActiveLabel(nfDataSeries, info.i, "FenceActiveLabels" + (n + 1), j, rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height, array[5] + this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(n12.doubleValue()), null, null);
                            }
                            else {
                                this.setActiveLabel(nfDataSeries, getOldStyleFenceActiveLabelIndex(j), rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height, getOldStyleFenceActiveLabelPrefix(j) + this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(n12.doubleValue()));
                            }
                        }
                    }
                }
            }
        }
        NFGraphSymbol b3 = info.b;
        if (this.dataType == 0 && (this.showDataPoints || a.sampleSize < n4)) {
            if (b3 == null || b3.type == 0) {
                b3 = new NFGraphSymbol();
                b3.style = 1;
                b3.size = (int)Math.abs(n7 / 10.0);
            }
            Color color2 = b3.getColor();
            if (color2 == null) {
                color2 = this.dataPointColor;
            }
            if (color2 == null) {
                color2 = this.medianColor;
            }
            graphics.setColor(color2);
            for (int k = 0; k < nfDataSeries.dataset.size(); ++k) {
                final double nth = nfDataSeries.dataset.getNth(k);
                if (!Double.isNaN(nth)) {
                    int n15 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, nth);
                    int subtract3 = this.dataPointJitter ? ((int)(n8 + Math.random() * n7)) : mapYValue;
                    if (b3.type == 7) {
                        subtract3 = subtract(subtract3, b3.size / 2);
                    }
                    if (b3.type == 8) {
                        n15 = subtract(n15, b3.size / 2);
                    }
                    final Point point7 = this.getPoint(n15, subtract3);
                    if (b3.type == 7 || b3.type == 8) {
                        b3.draw(graphics, point7.x, point7.y, b3.size);
                    }
                    else {
                        b3.draw(graphics, point7.x, point7.y);
                    }
                    final Rectangle rectangle5 = this.getRectangle(subtract(n15, b3.size / 2), subtract(subtract3, b3.size / 2), b3.size, b3.size);
                    this.setActiveLabel(nfDataSeries, info.h, "DataSet" + (n + 1), k, rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height, this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(nth), null, b3);
                }
            }
        }
        if (a.sampleSize >= n4) {
            NFGraphSymbol c = info.c;
            if (c == null) {
                c = new NFGraphSymbol();
                c.style = (0x2 | 0x1);
                c.size = (int)Math.abs(n7 / 3.0);
            }
            Color color3 = c.getColor();
            if (color3 == null) {
                color3 = this.outlierColor;
            }
            if (color3 == null) {
                color3 = this.medianColor;
            }
            graphics.setColor(color3);
            final NFDataSet outliers = getOutliers(this.plotType, a);
            NFDataSet copy = null;
            if (!this.beenLoaded("OutlierActiveLabels" + (n + 1)) && outliers != null) {
                copy = NFDataSet.createCopy(outliers);
                copy.sort();
            }
            for (int n16 = 0; outliers != null && n16 < outliers.size(); ++n16) {
                final double nth2 = outliers.getNth(n16);
                if (!Double.isNaN(nth2) && nth2 >= min) {
                    if (nth2 <= max) {
                        int n17 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, nth2);
                        int subtract4 = (int)(n8 + n7 / 2.0);
                        if (c.type == 7) {
                            subtract4 = subtract(subtract4, c.size / 2);
                        }
                        if (c.type == 8) {
                            n17 = subtract(n17, c.size / 2);
                        }
                        final Point point8 = this.getPoint(n17, subtract4);
                        if (c.type == 7 || c.type == 8) {
                            c.draw(graphics, point8.x, point8.y, c.size);
                        }
                        else {
                            c.draw(graphics, point8.x, point8.y);
                        }
                        final Rectangle rectangle6 = this.getRectangle(subtract(n17, c.size / 2), subtract(subtract4, c.size / 2), c.size, c.size);
                        if (this.beenLoaded("OutlierActiveLabels" + (n + 1))) {
                            this.setActiveLabel(nfDataSeries, info.g, "DataSet" + (n + 1), dataset.getIndex(outliers.getPoint(n16)), n16, rectangle6.x, rectangle6.y, rectangle6.width, rectangle6.height, "Outlier: " + this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(nth2), null, c);
                        }
                        else {
                            this.setActiveLabel(nfDataSeries, copy.getIndex(outliers.getPoint(n16)) + 5, rectangle6.x, rectangle6.y, rectangle6.width, rectangle6.height, "Outlier: " + this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getLabel(nth2), c);
                        }
                    }
                }
            }
        }
        if (info.e != null && info.f != null) {
            for (int l = 0; l < info.f.size(); ++l) {
                final Number n18 = info.f.elementAt(l);
                if (n18 != null) {
                    if (!Double.isNaN(n18.doubleValue())) {
                        final int mapXValue5 = this.mapXValue(nfDataSeries.XAxis, nfDataSeries.YAxis, n18.doubleValue());
                        final Point point9 = this.getPoint(mapXValue5, (int)(mapYValue - n6 / 2.0));
                        final Point point10 = this.getPoint(mapXValue5, (int)(mapYValue + n6 / 2.0));
                        Color color4 = info.e.getColor();
                        if (color4 == null) {
                            color4 = this.medianColor;
                        }
                        graphics.setColor(color4);
                        info.e.draw(graphics, point9.x, point9.y, point10.x, point10.y);
                    }
                }
            }
        }
        return point;
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        super.defineParams();
        hashtable.put("RAW", new Integer(0));
        hashtable.put("SUMMARY", new Integer(1));
        hashtable.put("SUMMARYWITHMEAN", new Integer(2));
        super.param.defineSymbol("DataType", hashtable, new Integer(0));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("DataSetName"));
        super.param.defineRegion("DataSet", vector);
        super.param.defineVector("DataSets", super.param.defineTuple("DataSet", vector));
        final NFParamDef defineNumber = super.param.defineNumber("DataValue");
        super.param.defineColor("MedianColor", Color.white);
        super.param.defineColor("OutlierColor", null);
        super.param.defineColor("MeanColor", null);
        super.param.defineColor("DataPointColor", null);
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("DataSet" + (i + 1), defineNumber);
            super.param.defineActiveLabel("OutlierActiveLabels" + (i + 1));
            super.param.defineActiveLabel("DataPointActiveLabels" + (i + 1));
            super.param.defineActiveLabel("FenceActiveLabels" + (i + 1));
        }
        super.param.defineString("OutlierActiveLabelsEnabled", "ON");
        super.param.defineString("DataPointActiveLabelsEnabled", "ON");
        super.param.defineString("FenceActiveLabelsEnabled", "ON");
        super.param.defineActiveLabel("MeanActiveLabels");
        this.defineDataAxisParams(super.param, "DataAxis");
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineNumber("BoxLimitLinePoint"));
        super.param.defineVector("BoxLimitLines", super.param.defineTuple("BoxLimitLineDataPoint", vector2, true));
        super.param.defineNumber("BoxHeight", null);
        super.param.defineNumber("BoxWidth", new Double(this.BOX_WIDTH_FACTOR_DEFAULT));
        super.param.defineNumber("BoxSymbolWidth", new Double(this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT));
        NFPatternFill.definePatternFillParam(super.param, "BoxFillPattern");
        this.defineLineSymbol(super.param, "OutlierSymbol");
        this.defineLineSymbol(super.param, "MeanSymbol");
        this.defineLineSymbol(super.param, "DataPointSymbol");
        this.defineLine(super.param, "MeanLine", false, false, false);
        this.defineLine(super.param, "BoxLimitLineStyle", true, false, false);
        super.param.defineString("DrawFences", this.DRAW_FENCES_DEFAULT ? "ON" : "OFF");
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("OFF", new Integer(0));
        hashtable2.put("LINEAR", new Integer(1));
        hashtable2.put("SQRT", new Integer(2));
        super.param.defineSymbol("RelativeBoxSymbolWidth", hashtable2, new Integer(this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT));
        super.param.defineString("ShowDataPoints", this.SHOW_DATA_POINTS_DEFAULT ? "ON" : "OFF");
        final Hashtable<String, Integer> hashtable3 = new Hashtable<String, Integer>();
        hashtable3.put("VERTICAL", new Integer(1));
        hashtable3.put("HORIZONTAL", new Integer(2));
        super.param.defineSymbol("GraphLayout", hashtable3, new Integer(this.BOX_LAYOUT_DEFAULT));
        final Hashtable<String, Integer> hashtable4 = new Hashtable<String, Integer>();
        hashtable4.put("STANDARD", new Integer(0));
        hashtable4.put("EDA", new Integer(1));
        hashtable4.put("TENNINETY", new Integer(2));
        hashtable4.put("GAUSSIAN", new Integer(3));
        super.param.defineSymbol("PlotType", hashtable4, new Integer(this.PLOT_TYPE_DEFAULT));
        final Hashtable<String, Integer> hashtable5 = new Hashtable<String, Integer>();
        hashtable5.put("BOX", new Integer(0));
        hashtable5.put("LINE", new Integer(1));
        super.param.defineSymbol("WhiskerType", hashtable5, new Integer(this.WHISKER_TYPE_DEFAULT));
        final Hashtable<String, Integer> hashtable6 = new Hashtable<String, Integer>();
        hashtable6.put("OVER", new Integer(0));
        hashtable6.put("UNDER", new Integer(1));
        super.param.defineSymbol("FencePosition", hashtable6, new Integer(this.FENCE_POSITION_DEFAULT));
        super.param.defineString("BoxFences", this.BOX_FENCES_DEFAULT ? "ON" : "OFF");
        super.param.defineString("NaturalDisplayOrder", this.NATURAL_DISPLAY_ORDER_DEFAULT ? "ON" : "OFF");
        super.param.defineString("DataPointJitter", this.DATA_POINT_JITTER_DEFAULT ? "ON" : "OFF");
        super.param.defineNumber("MinimumDataPoints", new Integer(this.MINIMUM_DATA_POINTS_DEFAULT));
        super.param.defineVector("BoxLabels", super.param.defineString("Label"));
        super.param.defineActiveLabel("BoxActiveLabels");
    }
    
    protected synchronized void loadParams() throws Exception {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        super.loadParams();
        if (super.param.changed("DrawFences")) {
            super.graphChanged = true;
            this.drawFences = NFUtil.getString(super.param.get("DrawFences"), this.DRAW_FENCES_DEFAULT ? "ON" : "OFF").equalsIgnoreCase("ON");
        }
        if (super.param.changed("MedianColor")) {
            super.graphChanged = true;
            this.medianColor = NFUtil.getColor(super.param.get("MedianColor"), this.MEDIAN_COLOR_DEFAULT);
        }
        if (super.param.changed("OutlierColor")) {
            super.graphChanged = true;
            this.outlierColor = NFUtil.getColor(super.param.get("OutlierColor"), this.OUTLIER_COLOR_DEFAULT);
        }
        if (super.param.changed("MeanColor")) {
            super.graphChanged = true;
            this.meanColor = NFUtil.getColor(super.param.get("MeanColor"), this.MEAN_COLOR_DEFAULT);
        }
        if (super.param.changed("DataPointColor")) {
            super.graphChanged = true;
            this.dataPointColor = NFUtil.getColor(super.param.get("DataPointColor"), this.DATA_POINT_COLOR_DEFAULT);
        }
        if (super.param.changed("DataType")) {
            super.graphChanged = true;
            b3 = true;
            this.dataType = NFUtil.getNumber(super.param.get("DataType"), this.DATA_TYPE_DEFAULT);
        }
        if (super.param.changed("BoxHeight")) {
            super.graphChanged = true;
            this.boxHeight = NFUtil.getNumber(super.param.get("BoxHeight"), this.BOX_HEIGHT_DEFAULT);
            if (this.boxHeight < 0) {
                this.boxHeight = 0;
            }
        }
        if (super.param.changed("BoxWidth")) {
            super.graphChanged = true;
            this.boxWidthFactor = loadFactor(super.param, "BoxWidth", this.BOX_WIDTH_FACTOR_DEFAULT, false);
        }
        if (super.param.changed("BoxSymbolWidth")) {
            super.graphChanged = true;
            this.boxSymbolWidthFactor = loadFactor(super.param, "BoxSymbolWidth", this.BOX_SYMBOL_WIDTH_FACTOR_DEFAULT, false);
        }
        if (super.param.changed("RelativeBoxSymbolWidth")) {
            super.graphChanged = true;
            this.boxSymbolWidthRelative = NFUtil.getNumber(super.param.get("RelativeBoxSymbolWidth"), this.BOX_SYMBOL_WIDTH_RELATIVE_DEFAULT);
        }
        if (super.param.changed("ShowDataPoints")) {
            super.graphChanged = true;
            this.showDataPoints = NFUtil.getString(super.param.get("ShowDataPoints"), this.SHOW_DATA_POINTS_DEFAULT ? "ON" : "OFF").equalsIgnoreCase("ON");
        }
        if (super.param.changed("PlotType")) {
            super.graphChanged = true;
            b = true;
            b2 = true;
            this.plotType = NFUtil.getNumber(super.param.get("PlotType"), this.PLOT_TYPE_DEFAULT);
        }
        if (super.param.changed("WhiskerType")) {
            super.graphChanged = true;
            this.whiskerType = NFUtil.getNumber(super.param.get("WhiskerType"), this.WHISKER_TYPE_DEFAULT);
        }
        if (super.param.changed("FencePosition")) {
            super.graphChanged = true;
            this.fencePosition = NFUtil.getNumber(super.param.get("FencePosition"), this.FENCE_POSITION_DEFAULT);
        }
        if (super.param.changed("BoxFences")) {
            super.graphChanged = true;
            this.boxFences = NFUtil.getString(super.param.get("BoxFences"), this.BOX_FENCES_DEFAULT ? "ON" : "OFF").equals("ON");
        }
        if (super.param.changed("NaturalDisplayOrder")) {
            super.graphChanged = true;
            b = true;
            this.naturalDisplayOrder = NFUtil.getString(super.param.get("NaturalDisplayOrder"), this.NATURAL_DISPLAY_ORDER_DEFAULT ? "ON" : "OFF").equals("ON");
        }
        if (super.param.changed("DataPointJitter")) {
            super.graphChanged = true;
            this.dataPointJitter = NFUtil.getString(super.param.get("DataPointJitter"), this.DATA_POINT_JITTER_DEFAULT ? "ON" : "OFF").equals("ON");
        }
        if (super.param.changed("MeanLine")) {
            super.graphChanged = true;
            final Vector vector = (Vector)super.param.get("MeanLine");
            if (vector == null || vector.size() == 0) {
                this.meanLine = this.MEAN_LINE_DEFAULT;
            }
            else {
                this.meanLine = this.loadLine(vector, this.meanLine, NFDataChart.defaultLineStyle);
            }
        }
        if (super.param.changed("GraphLayout")) {
            super.graphChanged = true;
            super.layoutChanged = true;
            this.boxLayout = NFUtil.getNumber(super.param.get("GraphLayout"), this.BOX_LAYOUT_DEFAULT);
            this.handleLayoutChange();
            this.setChangedIfLoaded("BoxLabels");
            this.setChangedIfLoaded("BoxActiveLabels");
            super.loadParams();
        }
        if (super.param.changed("BoxLabels")) {
            super.graphChanged = true;
            super.layoutChanged = true;
            final Vector vector2 = (Vector)super.param.get("BoxLabels");
            if (vector2 != null && vector2.size() > 0) {
                this.getXAxis(super.leftAxis, super.bottomAxis).setTicLabels(vector2, "BoxLabels");
                this.getXAxis(super.leftAxis, super.bottomAxis).setSpacing(new NFSpacing(0.0, vector2.size() - 1.0, 1.0));
            }
            else {
                this.getXAxis(super.leftAxis, super.bottomAxis).clearTicLabels();
                this.getXAxis(super.leftAxis, super.bottomAxis).setSpacing(null);
            }
        }
        if (super.param.changed("BoxActiveLabels") && super.dwell != null) {
            this.getXAxis(super.leftAxis, super.bottomAxis).setActiveLabels(null);
            if (this.boxActiveLabels == null) {
                this.boxActiveLabels = new Vector();
            }
            else {
                if (super.dwell != null) {
                    super.dwell.removeLabel(this.boxActiveLabels);
                }
                this.boxActiveLabels.removeAllElements();
            }
            final Vector loadAllParams = NFActiveLabel.loadAllParams(super.param, "BoxActiveLabels");
            for (int size = loadAllParams.size(), i = 0; i < size; ++i) {
                this.boxActiveLabels.addElement(super.dwell.addLabel(loadAllParams, i));
            }
            this.getXAxis(super.leftAxis, super.bottomAxis).setActiveLabels(this.boxActiveLabels);
        }
        if (super.param.changed("MinimumDataPoints")) {
            super.graphChanged = true;
            this.minimumDataPoints = NFUtil.getNumber(super.param.get("MinimumDataPoints"), this.MINIMUM_DATA_POINTS_DEFAULT);
        }
        final boolean loadDataSetParams = this.loadDataSetParams(super.param, 5, "DataSets", "DataAxis", super.bottomAxis, super.leftAxis);
        final boolean[] loadDataSets = this.loadDataSets(super.param, 5, "DataSet", loadDataSetParams);
        for (int j = 0; j < loadDataSets.length; ++j) {
            if (b3 || b2 || loadDataSets[j]) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                if (nfDataSeries.type == 5) {
                    final NFBoxInfo info = getInfo(nfDataSeries);
                    if (this.dataType == 0) {
                        if (b3 || loadDataSets[j]) {
                            info.a = new NFPercentile(nfDataSeries.dataset);
                        }
                    }
                    else {
                        info.a = loadSummary(this.plotType, nfDataSeries.dataset, this.dataType == 2);
                    }
                }
            }
        }
        this.loadActiveLabels(super.param, 5, "ActiveLabels", loadDataSets);
        this.loadActiveLabels(super.param, 5, "FenceActiveLabels", loadDataSets, false, b);
        this.loadActiveLabels(super.param, 5, "DataPointActiveLabels", loadDataSets, false, b);
        this.loadActiveLabels(super.param, 5, "OutlierActiveLabels", loadDataSets, false, b);
        this.loadPatternFill(super.param, 5, "BoxFillPattern");
        if (loadDataSetParams || super.param.changed("MeanActiveLabels")) {
            if (super.dwell != null) {
                super.dwell.removeLabel(this.meanActiveLabels);
            }
            this.meanActiveLabels.removeAllElements();
            final Vector loadAllParams2 = NFActiveLabel.loadAllParams(super.param, "MeanActiveLabels");
            for (int n = 0; super.dwell != null && n < super.dataSeries.size(); ++n) {
                this.meanActiveLabels.addElement(super.dwell.addLabel(loadAllParams2, n));
            }
        }
        if (super.param.changed("OutlierSymbol")) {
            super.graphChanged = true;
            this.loadSymbol(super.param, "OutlierSymbol", true);
        }
        if (super.param.changed("MeanSymbol")) {
            super.graphChanged = true;
            this.loadSymbol(super.param, "MeanSymbol", true);
        }
        if (super.param.changed("DataPointSymbol")) {
            super.graphChanged = true;
            this.loadSymbol(super.param, "DataPointSymbol", true);
        }
        if (super.param.changed("BoxLimitLines")) {
            super.graphChanged = true;
            this.loadLimitLines(super.param, "BoxLimitLines");
        }
        if (super.param.changed("BoxLimitLineStyle")) {
            super.graphChanged = true;
            this.loadLimitLineStyle(super.param, "BoxLimitLineStyle");
        }
        for (int k = 0; k < loadDataSets.length; ++k) {
            if (b || loadDataSetParams || loadDataSets[k]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
    }
    
    protected void loadLimitLines(final NFParam nfParam, final String s) throws Exception {
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 5);
            if (dataSeries == null) {
                break;
            }
            getInfo(dataSeries).f = (Vector)vector.elementAt(i).clone();
        }
    }
    
    protected void loadLimitLineStyle(final NFParam nfParam, final String s) throws Exception {
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 5);
            if (dataSeries == null) {
                break;
            }
            final Vector vector2 = vector.elementAt(i);
            final NFBoxInfo info = getInfo(dataSeries);
            info.e = ((vector2 == null) ? null : this.loadLine(vector2, info.e, NFDataChart.defaultLineStyle));
        }
    }
    
    protected void loadSymbol(final NFParam nfParam, final String s, final boolean b) throws Exception {
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return;
        }
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 5);
            if (dataSeries == null) {
                break;
            }
            if (!b && i >= vector.size()) {
                break;
            }
            final Vector vector2 = vector.elementAt((b && i >= vector.size()) ? (vector.size() - 1) : i);
            final NFBoxInfo info = getInfo(dataSeries);
            if (s.equals("OutlierSymbol")) {
                info.c = this.loadSymbol(vector2, info.c, NFDataChart.defaultLineSymbol);
            }
            else if (s.equals("DataPointSymbol")) {
                info.b = this.loadSymbol(vector2, info.b, NFDataChart.defaultLineSymbol);
            }
            else if (s.equals("MeanSymbol")) {
                info.d = this.loadSymbol(vector2, info.d, NFDataChart.defaultLineSymbol);
            }
        }
    }
    
    protected static NFBoxInfo getInfo(final NFDataSeries nfDataSeries) {
        if (nfDataSeries == null) {
            return null;
        }
        if (nfDataSeries.info == null || !(nfDataSeries.info instanceof NFBoxInfo)) {
            nfDataSeries.info = new NFBoxInfo();
        }
        return (NFBoxInfo)nfDataSeries.info;
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 5) {
            super.loadDataSetParams(nfDataSeries, n, o);
            return;
        }
        if (nfDataSeries.dataset == null) {
            nfDataSeries.dataset = new NFDataSet();
        }
        else {
            nfDataSeries.dataset.clear();
        }
        final Vector vector = (Vector)o;
        nfDataSeries.name = vector.elementAt(0);
        nfDataSeries.region = NFRegion.loadParams(super.param, vector, 1);
        nfDataSeries.c = nfDataSeries.region.getColor();
        if (nfDataSeries.c == null) {
            nfDataSeries.c = this.defaultColor(n);
            nfDataSeries.region.setColor(nfDataSeries.c);
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 5) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        nfDataSeries.dataset.addPoint(((Number)o).doubleValue());
    }
    
    protected Point getPoint(final int n, final int n2) {
        if (this.boxLayout == 2) {
            return new Point(n, n2);
        }
        return new Point(n2, n);
    }
    
    protected Rectangle getRectangle(int n, int n2, int n3, int n4) {
        if (this.boxLayout == 2) {
            return new Rectangle(n, n2, n3, n4);
        }
        if (n3 < 0) {
            n += n3 + 1;
            n3 = -n3;
        }
        if (n4 < 0) {
            n2 += n4 + 1;
            n4 = -n4;
        }
        return new Rectangle(n2, n, n4, n3);
    }
    
    protected int mapXValue(final NFAxis nfAxis, final NFAxis nfAxis2, final double n) {
        if (this.boxLayout == 2) {
            return nfAxis.mapValue(n).x;
        }
        return nfAxis2.mapValue(n).y;
    }
    
    protected int mapYValue(final NFAxis nfAxis, final NFAxis nfAxis2, final double n) {
        if (this.boxLayout == 2) {
            return nfAxis2.mapValue(n).y;
        }
        return nfAxis.mapValue(n).x;
    }
    
    protected NFAxis getXAxis(final NFAxis nfAxis, final NFAxis nfAxis2) {
        if (this.boxLayout == 2) {
            return nfAxis;
        }
        return nfAxis2;
    }
    
    protected NFAxis getYAxis(final NFAxis nfAxis, final NFAxis nfAxis2) {
        if (this.boxLayout == 2) {
            return nfAxis2;
        }
        return nfAxis;
    }
    
    protected static int subtract(final int n, final int n2) {
        if (n < 0) {
            return n + n2;
        }
        return n - n2;
    }
    
    public void clean() {
        super.clean();
        for (int i = 0; i < super.MaxDataSets; ++i) {
            if (this.beenLoaded("OutlierActiveLabels" + (i + 1))) {
                this.setLoaded("OutlierActiveLabels" + (i + 1), false);
            }
            if (this.beenLoaded("FenceActiveLabels" + (i + 1))) {
                this.setLoaded("FenceActiveLabels" + (i + 1), false);
            }
        }
        for (int j = 0; j < super.axes.size(); ++j) {
            final NFAxis nfAxis = super.axes.elementAt(j);
            if (nfAxis != null) {
                nfAxis.clearTicLabels();
            }
        }
        this.cleanLayoutParameters();
    }
    
    protected static NFDataSet getOutliers(final int n, final NFPercentile nfPercentile) {
        switch (n) {
            default: {
                return nfPercentile.outliers;
            }
            case 2:
            case 3: {
                return null;
            }
        }
    }
    
    protected static int getOldStyleFenceActiveLabelIndex(final int n) {
        switch (n) {
            case 0: {
                return 3;
            }
            case 1: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 4;
            }
            default: {
                return -1;
            }
        }
    }
    
    protected static String getOldStyleFenceActiveLabelPrefix(final int n) {
        switch (n) {
            case 0: {
                return "Min: ";
            }
            case 1: {
                return "25th Pctl: ";
            }
            case 2: {
                return "Median: ";
            }
            case 3: {
                return "75th Pctl: ";
            }
            case 4: {
                return "Max: ";
            }
            default: {
                return "";
            }
        }
    }
    
    protected static Object[][] getPlot(final int n, final NFDataSeries nfDataSeries, final int n2) {
        final NFPercentile a = getInfo(nfDataSeries).a;
        Object[][] array = null;
        switch (n) {
            default: {
                array = new Object[][] { { new Double(a.lowerAdjacentValue), new Boolean(false), new Integer(1), new Integer(4), null, "Lower Adjacent Value: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P25), new Boolean(true), new Integer(0), new Integer(0), new Integer(n2), "25th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P50), new Boolean(false), new Integer(1), new Integer(4), null, "Median: ", null }, { new Double(a.P75), new Boolean(false), new Integer(0), new Integer(1), null, "75th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.upperAdjacentValue), new Boolean(true), new Integer(1), new Integer(4), null, "Upper Adjacent Value: ", nfDataSeries.region.getBorderColor() } };
                break;
            }
            case 1: {
                array = new Object[][] { { new Double(a.lowerOuterFence), new Boolean(false), new Integer(1), new Integer(4), null, "Lower Outer Fence: ", nfDataSeries.region.getBorderColor() }, { new Double(a.lowerInnerFence), new Boolean(false), new Integer(1), new Integer(4), null, "Lower Inner Fence: ", nfDataSeries.region.getBorderColor() }, { new Double(a.lowerAdjacentValue), new Boolean(false), new Integer(1), new Integer(4), null, "Lower Adjacent Value: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P25), new Boolean(true), new Integer(0), new Integer(0), new Integer(n2), "25th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P50), new Boolean(false), new Integer(1), new Integer(4), null, "Median: ", null }, { new Double(a.P75), new Boolean(false), new Integer(0), new Integer(1), null, "75th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.upperAdjacentValue), new Boolean(true), new Integer(1), new Integer(4), null, "Upper Adjacent Value: ", nfDataSeries.region.getBorderColor() }, { new Double(a.upperInnerFence), new Boolean(false), new Integer(1), new Integer(4), null, "Upper Inner Fence: ", nfDataSeries.region.getBorderColor() }, { new Double(a.upperOuterFence), new Boolean(false), new Integer(1), new Integer(4), null, "Upper Outer Fence: ", nfDataSeries.region.getBorderColor() } };
                break;
            }
            case 2: {
                array = new Object[][] { { new Double(a.smallest), new Boolean(false), new Integer(1), new Integer(4), null, "Minimum: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P10), new Boolean(true), new Integer(0), new Integer(0), new Integer(n2), "10th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.P50), new Boolean(false), new Integer(1), new Integer(4), null, "Median: ", null }, { new Double(a.P90), new Boolean(false), new Integer(0), new Integer(1), null, "90th Percentile: ", nfDataSeries.region.getBorderColor() }, { new Double(a.biggest), new Boolean(true), new Integer(1), new Integer(4), null, "Maximum: ", nfDataSeries.region.getBorderColor() } };
                break;
            }
            case 3: {
                array = new Object[][] { { new Double(a.gaussianLowerFence), new Boolean(false), new Integer(1), new Integer(4), null, "Mean - Std. Dev. * 3: ", nfDataSeries.region.getBorderColor() }, { new Double(a.smallest), new Boolean(true), new Integer(0), new Integer(0), new Integer(n2), "Minimum: ", nfDataSeries.region.getBorderColor() }, { new Double(a.mean), new Boolean(false), new Integer(1), new Integer(4), null, "Mean: ", null }, { new Double(a.biggest), new Boolean(false), new Integer(0), new Integer(1), null, "Maximum: ", nfDataSeries.region.getBorderColor() }, { new Double(a.gaussianUpperFence), new Boolean(true), new Integer(1), new Integer(4), null, "Mean + Std. Dev. * 3: ", nfDataSeries.region.getBorderColor() } };
                break;
            }
        }
        return array;
    }
    
    protected static double getPoint(final NFDataSet set, final int n) {
        if (set == null || n < 0 || n >= set.size()) {
            return Double.NaN;
        }
        return set.getNth(n);
    }
    
    protected static NFPercentile loadSummary(final int n, final NFDataSet set, final boolean b) {
        final NFPercentile nfPercentile = new NFPercentile(null);
        int i = 0;
        switch (n) {
            default: {
                nfPercentile.P25 = getPoint(set, i++);
                nfPercentile.P50 = getPoint(set, i++);
                nfPercentile.P75 = getPoint(set, i++);
                nfPercentile.lowerAdjacentValue = getPoint(set, i++);
                nfPercentile.upperAdjacentValue = getPoint(set, i++);
                if (b) {
                    nfPercentile.mean = getPoint(set, i++);
                    break;
                }
                break;
            }
            case 1: {
                nfPercentile.P25 = getPoint(set, i++);
                nfPercentile.P50 = getPoint(set, i++);
                nfPercentile.P75 = getPoint(set, i++);
                nfPercentile.lowerOuterFence = getPoint(set, i++);
                nfPercentile.lowerInnerFence = getPoint(set, i++);
                nfPercentile.lowerAdjacentValue = getPoint(set, i++);
                nfPercentile.upperAdjacentValue = getPoint(set, i++);
                nfPercentile.upperInnerFence = getPoint(set, i++);
                nfPercentile.upperOuterFence = getPoint(set, i++);
                if (b) {
                    nfPercentile.mean = getPoint(set, i++);
                    break;
                }
                break;
            }
            case 2: {
                nfPercentile.P10 = getPoint(set, i++);
                nfPercentile.P50 = getPoint(set, i++);
                nfPercentile.P90 = getPoint(set, i++);
                nfPercentile.smallest = getPoint(set, i++);
                nfPercentile.biggest = getPoint(set, i++);
                if (b) {
                    nfPercentile.mean = getPoint(set, i++);
                    break;
                }
                break;
            }
            case 3: {
                nfPercentile.smallest = getPoint(set, i++);
                nfPercentile.mean = getPoint(set, i++);
                nfPercentile.biggest = getPoint(set, i++);
                nfPercentile.gaussianLowerFence = getPoint(set, i++);
                nfPercentile.gaussianUpperFence = getPoint(set, i++);
                break;
            }
        }
        (nfPercentile.outliers = new NFDataSet()).setType(1);
        while (i < set.size()) {
            nfPercentile.outliers.addDataPoint(set.getPoint(i));
            ++i;
        }
        return nfPercentile;
    }
    
    protected static double loadFactor(final NFParam nfParam, final String s, final double n, final boolean b) throws Exception {
        double number = NFUtil.getNumber(nfParam.get(s), n);
        if (!b && number < 0.0) {
            number = -number;
        }
        if (number >= 1.0) {
            number /= 100.0;
        }
        if (number > 1.0) {
            number = 1.0;
        }
        return number;
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final NFDataSeries nfDataSeries, final String s, final boolean b) throws Exception {
        if (s.startsWith("ActiveLabels")) {
            return super.loadActiveLabels(nfParam, nfDataSeries, s, b);
        }
        Vector<NFActiveLabel> vector = null;
        int n = 0;
        final NFBoxInfo info = getInfo(nfDataSeries);
        if (s.startsWith("OutlierActiveLabels")) {
            vector = (Vector<NFActiveLabel>)info.g;
            final NFDataSet outliers = getOutliers(this.plotType, info.a);
            n = ((outliers == null) ? 0 : outliers.size());
        }
        else if (s.startsWith("DataPointActiveLabels")) {
            vector = (Vector<NFActiveLabel>)info.h;
            n = this.getDataSetSize(nfDataSeries);
        }
        else if (s.startsWith("FenceActiveLabels")) {
            vector = (Vector<NFActiveLabel>)info.i;
            n = getPlot(this.plotType, nfDataSeries, 0).length;
        }
        if (super.dwell != null) {
            super.dwell.removeLabel(vector);
        }
        vector.removeAllElements();
        if (super.dwell == null) {
            return true;
        }
        if (!b) {
            return true;
        }
        final Vector loadAllParams = NFActiveLabel.loadAllParams(nfParam, s);
        for (int i = 0; i < n; ++i) {
            vector.addElement(super.dwell.addLabel(loadAllParams, i));
        }
        return true;
    }
}
