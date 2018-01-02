// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Event;
import java.awt.Image;
import netcharts.util.NFParamImage;
import java.util.Enumeration;
import java.awt.Component;
import java.net.URL;
import netcharts.util.NFParamDef;
import java.util.Hashtable;
import netcharts.util.NFColor;
import netcharts.util.NFParamException;
import netcharts.util.NFParam;
import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import netcharts.util.NFDebug;
import java.awt.Rectangle;
import netcharts.util.NFUtil;
import java.applet.Applet;
import java.awt.Polygon;
import java.awt.Graphics;
import java.util.Vector;

public abstract class JDataChart extends JGraph implements NFAxisMap
{
    public static final int DEFAULT = -1;
    public static final int BARSET = 1;
    public static final int LINESET = 2;
    public static final int TASKSET = 3;
    public static final int STOCKSET = 4;
    public static final int BOXSET = 5;
    public static final int BUBBLESET = 6;
    public static final int BOTTOM = 0;
    public static final int TOP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    protected final String AXIS_ALIAS_MODIFIER = "1";
    protected String[] axisNames;
    protected String[] axisTypes;
    protected Vector leftAxes;
    protected Vector rightAxes;
    protected Vector bottomAxes;
    protected Vector topAxes;
    protected Vector[] axesVectors;
    protected Vector[] axesGapsVectors;
    protected Vector[] axesLayoutVectors;
    protected int[] axesLayoutDirection;
    protected double[] axesLayoutTotals;
    public static final boolean DISABLE_ZOOM_DEFAULT = false;
    protected boolean disableZoom;
    public static final int VERTICAL = 1;
    public static final int HORIZONTAL = 2;
    public static final int BOTH = 3;
    public static final int NONE = 4;
    public static final int GRID3D = 1;
    public static final int GRID2D = 2;
    public static final int NORMAL = 0;
    public static final int OUTWARD = 1;
    public static final int LAYOUT_DIRECTION_DEFAULT = 0;
    public int DEPTH_3D_DEFAULT;
    public int depth3D;
    public int GRID_3D_DEFAULT;
    public int grid3D;
    public int LINE_3D_DEFAULT;
    public int line3D;
    public double LINE_WIDTH_FACTOR_DEFAULT;
    public double lineWidthFactor;
    public int defaultDepth;
    public static final int LINE_VALUE_LABEL_STYLE_DEFAULT = 0;
    protected NFLabel lineValueLabel;
    protected double[] chartAxesSizes;
    public NFAxis bottomAxis;
    public NFAxis leftAxis;
    public NFAxis rightAxis;
    public NFAxis topAxis;
    public NFAxis rightSlantAxis;
    public Vector dataSeries;
    protected int depth;
    protected Vector axes;
    protected Vector grids;
    protected NFAxis XValuesAxis;
    protected NFAxis YValuesAxis;
    protected int MaxDataSets;
    protected int MaxAxes;
    protected boolean UseOldAutoScaling;
    protected Graphics clipRect;
    protected boolean axisScrollUpdate;
    private static final boolean a = false;
    private Polygon b;
    protected static int AXIS_THICKNESS_DEFAULT;
    protected int axisThickness;
    protected static final String[] axisParameters;
    protected static NFGraphSymbol defaultLineSymbol;
    protected static NFLine defaultLineStyle;
    private int c;
    private int d;
    private int e;
    
    public JDataChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.axisNames = new String[] { "Bottom", "Top", "Left", "Right" };
        this.axisTypes = new String[] { "X", "X", "Y", "Y" };
        this.leftAxes = new Vector();
        this.rightAxes = new Vector();
        this.bottomAxes = new Vector();
        this.topAxes = new Vector();
        this.axesVectors = new Vector[] { this.bottomAxes, this.topAxes, this.leftAxes, this.rightAxes };
        this.axesGapsVectors = new Vector[] { new Vector(), new Vector(), new Vector(), new Vector() };
        this.axesLayoutVectors = new Vector[] { new Vector(), new Vector(), new Vector(), new Vector() };
        this.axesLayoutDirection = new int[] { 0, 0, 0, 0 };
        this.axesLayoutTotals = new double[] { 0.0, 0.0, 0.0, 0.0 };
        this.disableZoom = false;
        this.DEPTH_3D_DEFAULT = -1;
        this.depth3D = this.DEPTH_3D_DEFAULT;
        this.GRID_3D_DEFAULT = -1;
        this.grid3D = this.GRID_3D_DEFAULT;
        this.LINE_3D_DEFAULT = 0;
        this.line3D = this.LINE_3D_DEFAULT;
        this.LINE_WIDTH_FACTOR_DEFAULT = 1.0;
        this.lineWidthFactor = this.LINE_WIDTH_FACTOR_DEFAULT;
        this.defaultDepth = 0;
        this.lineValueLabel = null;
        this.chartAxesSizes = new double[] { -1.0, -1.0, -1.0, -1.0 };
        this.dataSeries = new Vector();
        this.depth = 0;
        this.axes = new Vector();
        this.grids = new Vector();
        this.MaxDataSets = 50;
        this.MaxAxes = 15;
        this.UseOldAutoScaling = false;
        this.clipRect = null;
        this.axisScrollUpdate = false;
        this.b = null;
        this.axisThickness = JDataChart.AXIS_THICKNESS_DEFAULT;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.initGraph(applet);
        this.initChart();
        this.reshape(n, n2, n3, n4);
    }
    
    public JDataChart() {
        this.axisNames = new String[] { "Bottom", "Top", "Left", "Right" };
        this.axisTypes = new String[] { "X", "X", "Y", "Y" };
        this.leftAxes = new Vector();
        this.rightAxes = new Vector();
        this.bottomAxes = new Vector();
        this.topAxes = new Vector();
        this.axesVectors = new Vector[] { this.bottomAxes, this.topAxes, this.leftAxes, this.rightAxes };
        this.axesGapsVectors = new Vector[] { new Vector(), new Vector(), new Vector(), new Vector() };
        this.axesLayoutVectors = new Vector[] { new Vector(), new Vector(), new Vector(), new Vector() };
        this.axesLayoutDirection = new int[] { 0, 0, 0, 0 };
        this.axesLayoutTotals = new double[] { 0.0, 0.0, 0.0, 0.0 };
        this.disableZoom = false;
        this.DEPTH_3D_DEFAULT = -1;
        this.depth3D = this.DEPTH_3D_DEFAULT;
        this.GRID_3D_DEFAULT = -1;
        this.grid3D = this.GRID_3D_DEFAULT;
        this.LINE_3D_DEFAULT = 0;
        this.line3D = this.LINE_3D_DEFAULT;
        this.LINE_WIDTH_FACTOR_DEFAULT = 1.0;
        this.lineWidthFactor = this.LINE_WIDTH_FACTOR_DEFAULT;
        this.defaultDepth = 0;
        this.lineValueLabel = null;
        this.chartAxesSizes = new double[] { -1.0, -1.0, -1.0, -1.0 };
        this.dataSeries = new Vector();
        this.depth = 0;
        this.axes = new Vector();
        this.grids = new Vector();
        this.MaxDataSets = 50;
        this.MaxAxes = 15;
        this.UseOldAutoScaling = false;
        this.clipRect = null;
        this.axisScrollUpdate = false;
        this.b = null;
        this.axisThickness = JDataChart.AXIS_THICKNESS_DEFAULT;
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }
    
    protected void initChart() {
        this.leftAxis = NFAxis.defaultAxis(this.axisNames[2]);
        this.bottomAxis = NFAxis.defaultAxis(this.axisNames[0]);
        this.YValuesAxis = this.leftAxis;
        this.XValuesAxis = this.bottomAxis;
        this.axes.addElement(this.leftAxis);
        this.setNumberFormat(this.leftAxis);
        this.axes.addElement(this.bottomAxis);
        this.setNumberFormat(this.bottomAxis);
        (this.rightSlantAxis = new NFAxis(0, 0, 0, this.get3DDepth())).setTicPosition(3);
        this.rightSlantAxis.showAxisAndLine(false, false);
        this.rightSlantAxis.setType("RightSlant");
        this.axes.addElement(this.rightSlantAxis);
        this.setNumberFormat(this.rightSlantAxis);
    }
    
    public void setScale(final double n) {
        super.setScale(n);
        for (int i = 0; i < this.axes.size(); ++i) {
            ((NFAxis)this.axes.elementAt(i)).setScale(n);
        }
        for (int size = this.dataSeries.size(), j = 0; j < size; ++j) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(j);
            if (nfDataSeries.line != null) {
                nfDataSeries.line.setScale(n);
            }
            if (nfDataSeries.sym != null) {
                nfDataSeries.sym.setScale(n);
            }
        }
    }
    
    protected void deleteAllDataSets(final int n) {
        for (int i = this.dataSeries.size() - 1; i >= 0; --i) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (nfDataSeries.type == n) {
                if (nfDataSeries.activeLabels != null) {
                    if (super.dwell != null) {
                        super.dwell.removeLabel(nfDataSeries.activeLabels);
                    }
                    nfDataSeries.activeLabels.removeAllElements();
                }
                if (super.legend != null) {
                    super.legend.removeDataSet(nfDataSeries);
                }
                this.dataSeries.removeElementAt(i);
            }
        }
    }
    
    public NFDataSeries getDataSeries(final NFActiveLabel nfActiveLabel) {
        for (int i = 0; i < this.dataSeries.size(); ++i) {
            try {
                final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
                final Vector activeLabels = nfDataSeries.activeLabels;
                for (int j = 0; j < activeLabels.size(); ++j) {
                    if (nfActiveLabel == activeLabels.elementAt(j)) {
                        return nfDataSeries;
                    }
                }
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public NFDataSeries getDataSeries(final int n) {
        if (n < 1 || n > this.dataSeries.size()) {
            return null;
        }
        return this.dataSeries.elementAt(n - 1);
    }
    
    public NFDataSeries getDataSeries(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.dataSeries.size(); ++i) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (nfDataSeries.type == n2) {
                ++n3;
            }
            if (n3 == n) {
                return nfDataSeries;
            }
        }
        return null;
    }
    
    public int getDataSeriesIndex(final NFDataSeries nfDataSeries) {
        int n = 0;
        for (int i = 0; i < this.dataSeries.size(); ++i) {
            final NFDataSeries nfDataSeries2 = this.dataSeries.elementAt(i);
            if (nfDataSeries2.type == nfDataSeries.type) {
                ++n;
            }
            if (nfDataSeries2 == nfDataSeries) {
                return n;
            }
        }
        return -1;
    }
    
    public void displayAxes(final Graphics graphics) {
        for (int i = 0; i < this.axes.size(); ++i) {
            ((NFAxis)this.axes.elementAt(i)).display(graphics);
        }
    }
    
    public void displayGrids(final Graphics graphics) {
        int depth;
        if (this.grid3D < 0) {
            depth = this.get3DDepth();
        }
        else {
            depth = this.grid3D;
        }
        for (int i = 0; i < this.grids.size(); ++i) {
            final NFGrid nfGrid = this.grids.elementAt(i);
            nfGrid.setDepth(depth);
            nfGrid.setAxisThickness(this.axisThickness);
            nfGrid.display(graphics);
        }
    }
    
    protected void setDefaultAxes() {
        if (this.dataSeries.size() == 0) {
            return;
        }
        this.setRightSlantAxis();
        for (int i = 0; i < this.axes.size(); ++i) {
            this.setDefaultAxis((NFAxis)this.axes.elementAt(i));
        }
    }
    
    public void addObserver(final Object o) {
        super.addObserver(o);
        if (o instanceof NFScrollObserver) {
            if (this.leftAxis != null) {
                this.leftAxis.addObserver(o);
            }
            if (this.rightAxis != null) {
                this.rightAxis.addObserver(o);
            }
            if (this.bottomAxis != null) {
                this.bottomAxis.addObserver(o);
            }
            if (this.topAxis != null) {
                this.topAxis.addObserver(o);
            }
        }
    }
    
    public void removeObserver(final Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof NFScrollObserver) {
            if (this.leftAxis != null) {
                this.leftAxis.removeObserver(o);
            }
            if (this.rightAxis != null) {
                this.rightAxis.removeObserver(o);
            }
            if (this.bottomAxis != null) {
                this.bottomAxis.removeObserver(o);
            }
            if (this.topAxis != null) {
                this.topAxis.removeObserver(o);
            }
        }
        super.removeObserver(o);
    }
    
    public void setDefaultAxis(final NFAxis nfAxis) {
        if (nfAxis.autoscale) {
            this.autoscaleAxis(nfAxis);
        }
    }
    
    protected void setRightSlantAxis() {
        this.rightSlantAxis.setAxis(0, 0, 0, this.get3DDepth());
        this.rightSlantAxis.setTicPosition(3);
        this.rightSlantAxis.showAxisAndLine(false, false);
        this.rightSlantAxis.setSpacing(new NFSpacing(0.0, 1.0, 1));
        this.rightSlantAxis.setMinMax(0.0, 1.0);
    }
    
    public int getMaxBars(final NFAxis nfAxis) {
        final int size = this.dataSeries.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (nfDataSeries.type == 1) {
                if (nfAxis == null || nfDataSeries.XAxis == nfAxis) {
                    final int dataSetSize = this.getDataSetSize(nfDataSeries);
                    if (dataSetSize > n) {
                        n = dataSetSize;
                    }
                }
            }
        }
        return n;
    }
    
    protected int getDataSetCount(final int n) {
        final int size = this.dataSeries.size();
        int n2 = 0;
        for (int i = 0; i < size; ++i) {
            if (((NFDataSeries)this.dataSeries.elementAt(i)).type == n) {
                ++n2;
            }
        }
        return n2;
    }
    
    public void setDefaultGrid() {
    }
    
    public Graphics getClipRect() {
        return this.clipRect;
    }
    
    public int getAxisOffset(final Vector vector, final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            n3 += this.getAxisSpan(vector, i, n2) + this.getAxisGap(vector, i, n2);
        }
        return n3;
    }
    
    public int getAxesSpanWithGaps(final int n, final int n2) {
        int n3 = 0;
        if (n >= 0 && n < this.axesGapsVectors.length && this.axesGapsVectors[n].size() == this.axesVectors[n].size() - 1) {
            double n4 = 0.0;
            for (int i = 0; i < this.axesGapsVectors[n].size(); ++i) {
                n4 += ((Number)this.axesGapsVectors[n].elementAt(i)).doubleValue();
            }
            n3 = (int)(n2 / ((100.0 - n4) / 100.0));
        }
        if (n3 >= n2) {
            return n3;
        }
        return n2;
    }
    
    public int getAxisGap(final Vector vector, final int n, final int n2) {
        return this.getAxisGap(this.getAxesIndex(vector), n, n2);
    }
    
    public int getAxisGap(final int n, final int n2, final int n3) {
        if (n >= 0 && n < this.axesGapsVectors.length && n2 >= 0 && n2 < this.axesGapsVectors[n].size() && this.axesGapsVectors[n].size() == this.axesVectors[n].size() - 1 && this.axesLayoutTotals[n] > 0.0) {
            return (int)NFUtil.rint(n3 * (this.axesGapsVectors[n].elementAt(n2).doubleValue() / this.axesLayoutTotals[n]));
        }
        return 0;
    }
    
    public int getAxisSpan(final Vector vector, final int n, final int n2, final int n3) {
        int axisSpan = this.getAxisSpan(vector, n, n2);
        if (n == vector.size() - 1 && n3 + axisSpan != n2) {
            axisSpan = n2 - n3;
        }
        return axisSpan;
    }
    
    public int getAxisSpan(final Vector vector, final int n, final int n2) {
        final int axesIndex = this.getAxesIndex(vector);
        if (axesIndex >= 0 && axesIndex < this.axesLayoutVectors.length && this.axesLayoutVectors[axesIndex].size() == vector.size() && this.axesLayoutTotals[axesIndex] > 0.0) {
            return (int)NFUtil.rint(n2 * (((Number)this.axesLayoutVectors[axesIndex].elementAt(n)).doubleValue() / this.axesLayoutTotals[axesIndex]));
        }
        return n2 / vector.size();
    }
    
    public int getAxesIndex(final Vector vector) {
        for (int i = 0; i < this.axesVectors.length; ++i) {
            if (this.axesVectors[i] == vector) {
                return i;
            }
        }
        return -1;
    }
    
    protected synchronized void setAxes(final Graphics graphics, final Rectangle rectangle) {
        this.setAxes(graphics, rectangle, this.bottomAxes, this.topAxes, this.leftAxes, this.rightAxes);
    }
    
    protected int[] getAxesSizes(final Graphics graphics, final Rectangle rectangle, final Vector vector, final Vector vector2, final Vector vector3, final Vector vector4) {
        if (this.chartAxesSizes[0] != -1.0 && this.chartAxesSizes[1] != -1.0 && this.chartAxesSizes[2] != -1.0 && this.chartAxesSizes[3] != -1.0) {
            final int[] array = { this.convertValue(this.chartAxesSizes[0], rectangle.height), this.convertValue(this.chartAxesSizes[1], rectangle.height), this.convertValue(this.chartAxesSizes[2], rectangle.width), this.convertValue(this.chartAxesSizes[3], rectangle.width) };
            for (int i = 0; i < vector.size(); ++i) {
                final NFAxis nfAxis = vector.elementAt(i);
                if (nfAxis != null) {
                    nfAxis.getRect(graphics);
                }
            }
            for (int j = 0; j < vector2.size(); ++j) {
                final NFAxis nfAxis2 = vector2.elementAt(j);
                if (nfAxis2 != null) {
                    nfAxis2.getRect(graphics);
                }
            }
            for (int k = 0; k < vector3.size(); ++k) {
                final NFAxis nfAxis3 = vector3.elementAt(k);
                if (nfAxis3 != null) {
                    nfAxis3.getRect(graphics);
                }
            }
            for (int l = 0; l < vector4.size(); ++l) {
                final NFAxis nfAxis4 = vector4.elementAt(l);
                if (nfAxis4 != null) {
                    nfAxis4.getRect(graphics);
                }
            }
            return array;
        }
        int n = 0;
        Rectangle rectangle2 = new Rectangle(rectangle.x, rectangle.y + rectangle.height, rectangle.width, 0);
        Rectangle rectangle3 = new Rectangle(rectangle.x, rectangle.y, rectangle.width, 0);
        Rectangle rectangle4 = new Rectangle(rectangle.x, rectangle.y + rectangle.height, 0, rectangle.height);
        Rectangle rectangle5 = new Rectangle(rectangle.x + rectangle.width, rectangle.y + rectangle.height, 0, rectangle.height);
        final boolean b = n > 0 && this.axisThickness > 0;
        boolean b2 = true;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        for (int n6 = 0; b2 && n6 < 5; ++n6) {
            b2 = false;
            if (vector.size() > 0) {
                rectangle2.x = rectangle.x + (rectangle4.width + n4);
                rectangle2.y = rectangle.y + rectangle.height - (rectangle2.height + n3);
                rectangle2.width = rectangle.width - (rectangle4.width + n4) - (rectangle5.width + n2);
                final int n7 = rectangle2.height + n3;
                Rectangle rectangle6 = null;
                for (int n8 = 0; n8 < vector.size(); ++n8) {
                    final NFAxis defaultAxis = vector.elementAt((this.axesLayoutDirection[0] == 1) ? (vector.size() - n8 - 1) : n8);
                    Rectangle rectangle7;
                    if (this.axesLayoutDirection[0] == 0) {
                        final int axisOffset = this.getAxisOffset(vector, n8, rectangle2.width);
                        rectangle7 = new Rectangle(rectangle2.x + axisOffset, rectangle2.y, this.getAxisSpan(vector, n8, rectangle2.width, axisOffset), rectangle2.height);
                    }
                    else if (rectangle6 == null) {
                        rectangle7 = new Rectangle(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                    }
                    else {
                        rectangle7 = new Rectangle(rectangle6.x, rectangle6.y - rectangle2.height, rectangle6.width, rectangle2.height);
                    }
                    if (this.grids.size() > 0) {
                        defaultAxis.setAxis(rectangle7.x + (b ? this.axisThickness : 0), rectangle7.y, rectangle7.width - n - (b ? (this.axisThickness * 2) : 0), 0);
                    }
                    else {
                        defaultAxis.setAxis(rectangle7.x, rectangle7.y, rectangle7.width - n, 0);
                    }
                    this.setDefaultAxis(defaultAxis);
                    final Rectangle rect = defaultAxis.getRect(graphics, rectangle7);
                    if (this.axesLayoutDirection[0] == 1 && rectangle6 != null) {
                        rect.y = rectangle6.y - rect.height;
                    }
                    if (rectangle6 == null) {
                        rectangle6 = rect;
                    }
                    else {
                        rectangle6.add(rect);
                    }
                }
                rectangle2 = rectangle6;
                if (this.axesLayoutDirection[0] == 1) {
                    n3 = this.getAxesSpanWithGaps(0, rectangle2.height) - rectangle2.height;
                }
                if (rectangle2.height + n3 > n7) {
                    b2 = true;
                }
            }
            if (vector2.size() > 0) {
                rectangle3.x = rectangle.x + (rectangle4.width + n4);
                rectangle3.y = rectangle.y + (rectangle3.height + n5);
                rectangle3.width = rectangle.width - (rectangle4.width + n4) - (rectangle5.width + n2);
                final int n9 = rectangle3.height + n5;
                Rectangle rectangle8 = null;
                for (int n10 = 0; n10 < vector2.size(); ++n10) {
                    final NFAxis defaultAxis2 = vector2.elementAt((this.axesLayoutDirection[1] == 1) ? (vector2.size() - n10 - 1) : n10);
                    Rectangle rectangle9;
                    if (this.axesLayoutDirection[1] == 0) {
                        final int axisOffset2 = this.getAxisOffset(vector2, n10, rectangle3.width);
                        rectangle9 = new Rectangle(rectangle3.x + axisOffset2, rectangle3.y, this.getAxisSpan(vector2, n10, rectangle3.width, axisOffset2), rectangle3.height);
                    }
                    else if (rectangle8 == null) {
                        rectangle9 = new Rectangle(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                    }
                    else {
                        rectangle9 = new Rectangle(rectangle8.x, rectangle8.y + rectangle8.height, rectangle8.width, rectangle3.height);
                    }
                    if (this.grids.size() > 0) {
                        defaultAxis2.setAxis(rectangle9.x + n + (b ? this.axisThickness : 0), rectangle9.y, rectangle9.width - n - (b ? (this.axisThickness * 2) : 0), 0);
                    }
                    else {
                        defaultAxis2.setAxis(rectangle9.x, rectangle9.y, rectangle9.width - n, 0);
                    }
                    this.setDefaultAxis(defaultAxis2);
                    final Rectangle rect2 = defaultAxis2.getRect(graphics, rectangle9);
                    if (this.axesLayoutDirection[1] == 1 && rectangle8 != null) {
                        rect2.y += rect2.height;
                    }
                    if (rectangle8 == null) {
                        rectangle8 = rect2;
                    }
                    else {
                        rectangle8.add(rect2);
                    }
                }
                rectangle3 = rectangle8;
                if (this.axesLayoutDirection[1] == 1) {
                    n5 = this.getAxesSpanWithGaps(1, rectangle3.height) - rectangle3.height;
                }
                if (rectangle3.height + n5 > n9) {
                    b2 = true;
                }
            }
            if (vector3.size() > 0) {
                rectangle4.height = rectangle.height - (rectangle2.height + n3) - (rectangle3.height + n5);
                rectangle4.x = rectangle.x + (rectangle4.width + n4);
                rectangle4.y = rectangle.y + rectangle.height - (rectangle2.height + n3);
                final int n11 = rectangle4.width + n4;
                Rectangle rectangle10 = null;
                for (int n12 = 0; n12 < vector3.size(); ++n12) {
                    final NFAxis defaultAxis3 = vector3.elementAt((this.axesLayoutDirection[2] == 1) ? (vector3.size() - n12 - 1) : n12);
                    Rectangle rectangle11;
                    if (this.axesLayoutDirection[2] == 0) {
                        final int axisOffset3 = this.getAxisOffset(vector3, n12, rectangle4.height);
                        rectangle11 = new Rectangle(rectangle4.x, rectangle4.y - axisOffset3, rectangle4.width, this.getAxisSpan(vector3, n12, rectangle4.height, axisOffset3));
                    }
                    else if (rectangle10 == null) {
                        rectangle11 = new Rectangle(rectangle4.x, rectangle4.y, rectangle4.width, rectangle4.height);
                    }
                    else {
                        rectangle11 = new Rectangle(rectangle10.x + rectangle10.width, rectangle10.y + rectangle10.height, rectangle4.width, rectangle10.height);
                    }
                    if (this.grids.size() > 0) {
                        defaultAxis3.setAxis(rectangle11.x, rectangle11.y - (b ? this.axisThickness : 0), 0, -(rectangle11.height - n - (b ? (this.axisThickness * 2) : 0)));
                    }
                    else {
                        defaultAxis3.setAxis(rectangle11.x, rectangle11.y, 0, -(rectangle11.height - n));
                    }
                    this.setDefaultAxis(defaultAxis3);
                    final Rectangle rect3 = defaultAxis3.getRect(graphics, rectangle11);
                    if (this.axesLayoutDirection[2] == 1 && rectangle10 != null) {
                        rect3.x = rectangle10.x + rectangle10.width;
                    }
                    if (rectangle10 == null) {
                        rectangle10 = rect3;
                    }
                    else {
                        rectangle10.add(rect3);
                    }
                }
                rectangle4 = rectangle10;
                if (this.axesLayoutDirection[2] == 1) {
                    n4 = this.getAxesSpanWithGaps(2, rectangle4.width) - rectangle4.width;
                }
                if (rectangle4.width + n4 > n11) {
                    b2 = true;
                }
            }
            if (vector4.size() > 0) {
                rectangle5.height = rectangle.height - (rectangle2.height + n3) - (rectangle3.height + n5);
                rectangle5.x = rectangle.x + rectangle.width - (rectangle5.width + n2);
                rectangle5.y = rectangle.y + rectangle.height - (rectangle2.height + n3);
                final int n13 = rectangle5.width + n2;
                Rectangle rectangle12 = null;
                for (int n14 = 0; n14 < vector4.size(); ++n14) {
                    final NFAxis defaultAxis4 = vector4.elementAt((this.axesLayoutDirection[3] == 1) ? (vector4.size() - n14 - 1) : n14);
                    Rectangle rectangle13;
                    if (this.axesLayoutDirection[3] == 0) {
                        final int axisOffset4 = this.getAxisOffset(vector4, n14, rectangle5.height);
                        rectangle13 = new Rectangle(rectangle5.x, rectangle5.y - axisOffset4, rectangle5.width, this.getAxisSpan(vector4, n14, rectangle5.height, axisOffset4));
                    }
                    else if (rectangle12 == null) {
                        rectangle13 = new Rectangle(rectangle5.x, rectangle5.y, rectangle5.width, rectangle5.height);
                    }
                    else {
                        rectangle13 = new Rectangle(rectangle12.x + rectangle12.width, rectangle12.y + rectangle12.height, rectangle5.width, rectangle12.height);
                    }
                    if (this.grids.size() > 0) {
                        defaultAxis4.setAxis(rectangle13.x, rectangle13.y - n - (b ? this.axisThickness : 0), 0, -(rectangle13.height - n - (b ? (this.axisThickness * 2) : 0)));
                    }
                    else {
                        defaultAxis4.setAxis(rectangle13.x - n, rectangle13.y, 0, -(rectangle13.height - n));
                    }
                    this.setDefaultAxis(defaultAxis4);
                    final Rectangle rect4 = defaultAxis4.getRect(graphics, rectangle13);
                    if (rectangle12 == null) {
                        rectangle12 = rect4;
                    }
                    else {
                        rectangle12.add(rect4);
                    }
                }
                rectangle5 = rectangle12;
                if (this.axesLayoutDirection[3] == 1) {
                    n2 = this.getAxesSpanWithGaps(3, rectangle5.width) - rectangle5.width;
                }
                if (rectangle5.width + n2 > n13) {
                    b2 = true;
                }
            }
            final int n15 = n;
            n = this.get3DDepth(true);
            if (this.grid3D >= 0) {
                n = this.grid3D;
            }
            if (n > n15) {
                b2 = true;
            }
        }
        return new int[] { rectangle2.height + n3, rectangle3.height + n5, rectangle4.width + n4, rectangle5.width + n2 };
    }
    
    protected synchronized void setAxes(final Graphics graphics, final Rectangle rectangle, final Vector vector, final Vector vector2, final Vector vector3, final Vector vector4) {
        final int[] axesSizes = this.getAxesSizes(graphics, rectangle, vector, vector2, vector3, vector4);
        final int n = rectangle.width - axesSizes[2] - axesSizes[3];
        final int n2 = rectangle.height - axesSizes[1] - axesSizes[0];
        this.depth = this.get3DDepth(true);
        if (this.grid3D >= 0) {
            this.depth = this.grid3D;
        }
        final boolean b = this.depth > 0 && this.axisThickness > 0;
        final int n3 = rectangle.y + rectangle.height - axesSizes[0];
        final int n4 = n3 - n2;
        final int n5 = rectangle.x + axesSizes[2];
        final int n6 = n5 + n;
        if (vector.size() > 0 && rectangle.height > axesSizes[0]) {
            int n7 = n3;
            for (int i = 0; i < vector.size(); ++i) {
                final NFAxis nfAxis = vector.elementAt(i);
                if (this.axesLayoutDirection[0] == 0) {
                    final int axisOffset = this.getAxisOffset(vector, i, n);
                    nfAxis.setAxis(n5 + (b ? this.axisThickness : 0) + axisOffset, n3, this.getAxisSpan(vector, i, n, axisOffset) - this.depth - (b ? (this.axisThickness * 2) : 0), 0);
                }
                else {
                    nfAxis.setAxis(n5 + (b ? this.axisThickness : 0), n7, n - this.depth - (b ? (this.axisThickness * 2) : 0), 0);
                    n7 += nfAxis.getRect(graphics).height + this.getAxisGap(0, i, axesSizes[0]);
                }
            }
        }
        if (vector2.size() > 0 && rectangle.height > axesSizes[0] + axesSizes[1]) {
            int n8 = n4;
            for (int j = 0; j < vector2.size(); ++j) {
                final NFAxis nfAxis2 = vector2.elementAt(j);
                if (this.axesLayoutDirection[1] == 0) {
                    final int axisOffset2 = this.getAxisOffset(vector2, j, n);
                    final int axisSpan = this.getAxisSpan(vector2, j, n, axisOffset2);
                    if (this.grids.size() > 0) {
                        nfAxis2.setAxis(n5 + this.depth + (b ? (this.axisThickness * 2) : 0) + axisOffset2, n4, axisSpan - this.depth - (b ? (this.axisThickness * 2) : 0), 0);
                    }
                    else {
                        nfAxis2.setAxis(n5 + axisOffset2, n4, axisSpan - this.depth, 0);
                    }
                }
                else {
                    if (this.grids.size() > 0) {
                        nfAxis2.setAxis(n5 + this.depth + (b ? (this.axisThickness * 2) : 0), n8, n - this.depth - (b ? (this.axisThickness * 2) : 0), 0);
                    }
                    else {
                        nfAxis2.setAxis(n5, n8, n - this.depth, 0);
                    }
                    n8 -= nfAxis2.getRect(graphics).height + this.getAxisGap(1, j, axesSizes[1]);
                }
            }
        }
        if (vector3.size() > 0 && rectangle.width > axesSizes[2]) {
            int n9 = n5;
            for (int k = 0; k < vector3.size(); ++k) {
                final NFAxis nfAxis3 = vector3.elementAt(k);
                if (this.axesLayoutDirection[2] == 0) {
                    final int axisOffset3 = this.getAxisOffset(vector3, k, n2);
                    nfAxis3.setAxis(n5, n3 - (b ? this.axisThickness : 0) - axisOffset3, 0, -(this.getAxisSpan(vector3, k, n2, axisOffset3) - this.depth - (b ? (this.axisThickness * 2) : 0)));
                }
                else {
                    nfAxis3.setAxis(n9, n3 - (b ? this.axisThickness : 0), 0, -(n2 - this.depth - (b ? (this.axisThickness * 2) : 0)));
                    n9 -= nfAxis3.getRect(graphics).width + this.getAxisGap(2, k, axesSizes[2]);
                }
            }
        }
        if (vector4.size() > 0 && rectangle.width > axesSizes[2] + axesSizes[3]) {
            int n10 = n6;
            for (int l = 0; l < vector4.size(); ++l) {
                final NFAxis nfAxis4 = vector4.elementAt(l);
                if (this.axesLayoutDirection[3] == 0) {
                    final int axisOffset4 = this.getAxisOffset(vector4, l, n2);
                    final int axisSpan2 = this.getAxisSpan(vector4, l, n2, axisOffset4);
                    if (this.grids.size() > 0) {
                        nfAxis4.setAxis(n6, n3 - this.depth - (b ? (this.axisThickness * 2) : 0) - axisOffset4, 0, -(axisSpan2 - this.depth - (b ? (this.axisThickness * 2) : 0)));
                    }
                    else {
                        nfAxis4.setAxis(n6 - this.depth, n3 - axisOffset4, 0, -(axisSpan2 - this.depth));
                    }
                }
                else {
                    if (this.grids.size() > 0) {
                        nfAxis4.setAxis(n10, n3 - this.depth - (b ? (this.axisThickness * 2) : 0), 0, -(n2 - this.depth - (b ? (this.axisThickness * 2) : 0)));
                    }
                    else {
                        nfAxis4.setAxis(n10 - this.depth, n3, 0, -(n2 - this.depth));
                    }
                    n10 += nfAxis4.getRect(graphics).width + this.getAxisGap(3, l, axesSizes[3]);
                }
            }
        }
        this.rightSlantAxis.getRect(graphics);
        this.reset();
        if (this.clipRect != null) {
            this.clipRect.dispose();
            this.clipRect = null;
        }
        if (n < 20 || n2 < 20) {
            NFDebug.print("*** Insufficient room to display Graph ***");
            this.fireGraphTooSmall(new Dimension(rectangle.width, rectangle.height));
            return;
        }
        (this.clipRect = graphics.create(rectangle.x + axesSizes[2], rectangle.y + axesSizes[1], n, n2)).translate(-(rectangle.x + axesSizes[2]), -(rectangle.y + axesSizes[1]));
        this.clipRect.clipRect(rectangle.x + axesSizes[2] + (b ? this.axisThickness : 0), rectangle.y + axesSizes[1] + (b ? this.axisThickness : 0), n - (b ? (this.axisThickness * 2) : 0), n2 - (b ? (this.axisThickness * 2) : 0));
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle rectangle) {
        if (this.dataSeries.size() == 0) {
            return;
        }
        if (this.clipRect == null || super.layoutChanged) {
            this.setAxes(graphics, rectangle);
        }
        if (super.legend != null) {
            super.legend.setDepth(this.get3DDepth());
        }
        if (this.clipRect == null) {
            return;
        }
        this.displayGrids(graphics);
        this.drawData(this.clipRect);
        this.displayAxes(graphics);
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, this.clipRect);
        }
        if (super.legend != null && super.legend.enabled() && super.legend.isInside()) {
            super.legend.draw(super.a, this.clipRect, this.clipRect.getClipRect(), 10, super.colorTable);
        }
    }
    
    protected Rectangle getZoomRect() {
        return (this.clipRect == null) ? null : this.clipRect.getClipRect();
    }
    
    protected void drawGraphLite(final Graphics graphics) {
        if (this.dataSeries.size() == 0) {
            return;
        }
        if (this.clipRect == null) {
            return;
        }
        this.drawDataLite(this.clipRect);
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            final NFAxis nfAxis = this.axes.elementAt(i);
            if (nfAxis.getChanged()) {
                nfAxis.display(graphics);
            }
        }
    }
    
    protected void autoscaleAxis(final NFAxis nfAxis) {
        this.autoscaleAxis(nfAxis, this.getMinMax(nfAxis));
    }
    
    protected void autoscaleAxis(final NFAxis nfAxis, final double[] array) {
        double n = array[0];
        double n2 = array[1];
        if (!this.UseOldAutoScaling) {
            if (n == n2 && n != 0.0) {
                if (n > 0.0) {
                    n = 0.0;
                    n2 *= 2.0;
                }
                else {
                    n *= 2.0;
                    n2 = 0.0;
                }
            }
            n = ((nfAxis.getDefinedMin() == null) ? n : nfAxis.getValue(nfAxis.getDefinedMin()));
            n2 = ((nfAxis.getDefinedMax() == null) ? n2 : nfAxis.getValue(nfAxis.getDefinedMax()));
        }
        if (n == Double.MAX_VALUE) {
            return;
        }
        final double n3 = (this.UseOldAutoScaling && n > 0.0) ? 0.0 : n;
        final double n4 = (n2 > n3) ? n2 : (n3 + 100.0);
        double n5 = 0.0;
        double n6 = 0.0;
        double n7 = 0.0;
        if (!this.UseOldAutoScaling && nfAxis.getDefinedStep() != null) {
            n7 = nfAxis.getValue(nfAxis.getDefinedStep());
        }
        if (n7 == 0.0) {
            n7 = nfAxis.reasonableStep(n3, n4);
        }
        if (!this.UseOldAutoScaling) {
            if (nfAxis.getDefinedMin() == null && nfAxis.getDefinedMax() != null) {
                n6 = (n5 = n4);
            }
            else if (nfAxis.getDefinedMin() != null && nfAxis.getDefinedMax() == null) {
                n5 = (n6 = n3);
            }
        }
        double n8;
        if (n5 > n3) {
            n8 = n5 - n7 * Math.ceil((n5 - n3) / n7);
        }
        else {
            n8 = n5 + n7 * Math.floor((n3 - n5) / n7);
        }
        double n9;
        if (n6 < n4) {
            n9 = n6 + n7 * Math.ceil((n4 - n6) / n7);
        }
        else {
            n9 = n6 - n7 * Math.floor((n6 - n4) / n7);
        }
        nfAxis.setTicDivisions(n8, n9, n7);
        if (nfAxis.autoscroll) {
            nfAxis.setScrollLimits(n8, n9);
        }
    }
    
    protected double[] getMinMax(final NFAxis nfAxis) {
        double[] minMax = { Double.MAX_VALUE, -1.7976931348623157E308 };
        for (int size = this.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (nfDataSeries.XAxis == null || nfDataSeries.XAxis == nfAxis || nfDataSeries.YAxis == null || nfDataSeries.YAxis == nfAxis) {
                minMax = this.getMinMax(nfDataSeries, nfAxis, minMax);
            }
        }
        return minMax;
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        double[] array2 = null;
        if (nfDataSeries.XAxis == nfAxis) {
            array2 = nfDataSeries.dataset.getMinMax(1);
        }
        else if (nfDataSeries.YAxis == nfAxis) {
            array2 = nfDataSeries.dataset.getMinMax(2);
        }
        if (array2 != null) {
            if (array2[0] < array[0]) {
                array[0] = array2[0];
            }
            if (array2[1] > array[1]) {
                array[1] = array2[1];
            }
        }
        return array;
    }
    
    protected Point getBaseLine(final NFAxis nfAxis) {
        Point point;
        if (nfAxis.getMin() <= 0.0 && nfAxis.getMax() >= 0.0) {
            point = nfAxis.mapValue(0.0);
        }
        else if (nfAxis.getMin() > 0.0) {
            point = nfAxis.mapValue(nfAxis.getMin());
        }
        else {
            point = nfAxis.mapValue(nfAxis.getMax());
        }
        return point;
    }
    
    protected abstract void drawData(final Graphics p0);
    
    protected void drawDataLite(final Graphics graphics) {
    }
    
    protected Graphics createClippedGraphics(final Graphics graphics, final NFAxis nfAxis, final NFAxis nfAxis2) {
        final Graphics create = graphics.create();
        int n;
        if (this.grid3D < 0) {
            n = this.get3DDepth();
        }
        else {
            n = this.grid3D;
        }
        create.clipRect(nfAxis.getMinCoord(), nfAxis2.getMinCoord() - n, nfAxis.getMaxCoord() - nfAxis.getMinCoord() + n, nfAxis2.getMaxCoord() - nfAxis2.getMinCoord() + n);
        return create;
    }
    
    protected void drawPoints(final Graphics graphics, final NFDataSeries nfDataSeries) {
        this.drawPoints(graphics, nfDataSeries, 0, this.bottomAxis, this.leftAxis);
    }
    
    protected void drawPoints(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final NFAxis nfAxis, final NFAxis nfAxis2) {
        this.drawPoints(graphics, nfDataSeries, n, nfAxis, nfAxis2, 0);
    }
    
    protected void drawPoints(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final NFAxis nfAxis, final NFAxis nfAxis2, final int n2) {
        final int size = nfDataSeries.dataset.size();
        if (size < 1) {
            return;
        }
        final int lineWidth = this.getLineWidth();
        final int n3 = this.getLineDepth() * n2;
        Color fillColor = null;
        Color color = null;
        final Color color2 = graphics.getColor();
        final double min = nfAxis.getMin();
        final double max = nfAxis.getMax();
        final double min2 = nfAxis2.getMin();
        final double max2 = nfAxis2.getMax();
        final Point mapValue = nfAxis2.mapValue(min2);
        if (nfDataSeries.line != null) {
            fillColor = (color = nfDataSeries.line.getColor());
        }
        else if (nfDataSeries.fillColor != null && lineWidth > 1) {
            fillColor = nfDataSeries.fillColor;
            color = nfDataSeries.c;
        }
        if (fillColor != null || nfDataSeries.fillColor != null) {
            final Polygon polygon = new Polygon();
            double[] array = null;
            for (int i = (n > 0) ? (n - 1) : n; i < size; ++i) {
                final double nth = nfDataSeries.dataset.getNth(i, 1);
                final double nth2 = nfDataSeries.dataset.getNth(i, 2);
                final Point mapValue2 = nfAxis.mapValue(nth);
                final Point mapValue3 = nfAxis2.mapValue(nth2);
                final boolean b = nfDataSeries.fillColor != null;
                if (mapValue2 == null || mapValue3 == null || (array != null && this.clipLine(array[0], array[1], nth, nth2, min, max, min2, max2, b))) {
                    if (polygon.npoints > 1) {
                        if (nfDataSeries.lineCurveType != 1) {
                            this.drawSlab(graphics, polygon, mapValue.y - n3, lineWidth, nfDataSeries.line, fillColor, nfDataSeries.fillColor, color, true, true, nfDataSeries.pattern);
                        }
                        if (nfDataSeries.lineCurveType != 0) {
                            this.drawSlab(graphics, getCurveForPoints(polygon), mapValue.y - n3, lineWidth, nfDataSeries.line, fillColor, (nfDataSeries.lineCurveType == 2) ? null : nfDataSeries.fillColor, color, true, true, (nfDataSeries.lineCurveType == 2) ? null : nfDataSeries.pattern);
                        }
                    }
                    polygon.npoints = 0;
                    if (mapValue2 == null || mapValue3 == null) {
                        array = null;
                        continue;
                    }
                }
                array = new double[] { nth, nth2 };
                polygon.addPoint(mapValue2.x + n3, mapValue3.y - n3);
            }
            if (polygon.npoints > 1) {
                if (nfDataSeries.lineCurveType != 1) {
                    this.drawSlab(graphics, polygon, mapValue.y - n3, lineWidth, nfDataSeries.line, fillColor, nfDataSeries.fillColor, color, true, true, nfDataSeries.pattern);
                }
                if (nfDataSeries.lineCurveType != 0) {
                    this.drawSlab(graphics, getCurveForPoints(polygon), mapValue.y - n3, lineWidth, nfDataSeries.line, fillColor, (nfDataSeries.lineCurveType == 2) ? null : nfDataSeries.fillColor, color, true, true, (nfDataSeries.lineCurveType == 2) ? null : nfDataSeries.pattern);
                }
            }
        }
        final Color color3 = (nfDataSeries.sym != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
        graphics.setColor((color3 == null) ? nfDataSeries.c : color3);
        for (int j = n; j < size; ++j) {
            final double nth3 = nfDataSeries.dataset.getNth(j, 1);
            final double nth4 = nfDataSeries.dataset.getNth(j, 2);
            final Point mapValue4 = nfAxis.mapValue(nth3);
            final Point mapValue5 = nfAxis2.mapValue(nth4);
            if (mapValue4 == null || mapValue5 == null || this.clipPoint(nth3, nth4, min, max, min2, max2)) {
                this.setActiveLabel(nfDataSeries, j, -1, -1, 0, 0, "");
            }
            else {
                if (nfDataSeries.sym != null) {
                    nfDataSeries.sym.draw(graphics, mapValue4.x + n3, mapValue5.y - n3);
                }
                final String label = nfAxis.getLabel(nth3);
                final String label2 = nfAxis2.getLabel(nth4);
                if (this.getClass().getName().endsWith("XYChart")) {
                    this.setActiveLabel(nfDataSeries, j, mapValue4.x + n3, mapValue5.y - n3, super.dwellOffset, label, label2);
                }
                else {
                    this.setActiveLabel(nfDataSeries, j, mapValue4.x + n3, mapValue5.y - n3, super.dwellOffset, label2);
                }
                if (nfDataSeries.valueLabelStyle != 0) {
                    this.drawLineValueLabel(graphics, nfDataSeries, mapValue4.x + n3, mapValue5.y - n3, label2);
                }
            }
        }
        graphics.setColor(color2);
    }
    
    protected void drawLineValueLabel(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final int n2, final String s) {
        JValueLabel.drawPointValueLabel((this.lineValueLabel == null) ? super.dwell.getLabel() : this.lineValueLabel, graphics, nfDataSeries, n, n2, s);
    }
    
    protected void defineLineValueLabel(final NFParam nfParam) {
        JValueLabel.definePointValueLabel(nfParam, "Line", 0);
    }
    
    protected void loadLineValueLabel(final NFParam nfParam) throws NFParamException {
        this.loadLineValueLabel(nfParam, 2);
    }
    
    protected void loadLineValueLabel(final NFParam nfParam, final int n) throws NFParamException {
        this.lineValueLabel = JValueLabel.loadValueLabel(this, "Line", n, this.dataSeries, this.lineValueLabel, 0);
    }
    
    public static Polygon getCurveForPoints(Polygon polygon) {
        final Polygon polygon2 = new Polygon();
        if (polygon.npoints >= 2) {
            final int[] array = new int[polygon.npoints + 4];
            final int[] array2 = new int[polygon.npoints + 4];
            System.arraycopy(polygon.xpoints, 0, array, 2, polygon.npoints);
            System.arraycopy(polygon.ypoints, 0, array2, 2, polygon.npoints);
            array[0] = (array[1] = polygon.xpoints[0]);
            array2[0] = (array2[1] = polygon.ypoints[0]);
            array[polygon.npoints + 2] = (array[polygon.npoints + 3] = polygon.xpoints[polygon.npoints - 1]);
            array2[polygon.npoints + 2] = (array2[polygon.npoints + 3] = polygon.ypoints[polygon.npoints - 1]);
            polygon = new Polygon(array, array2, polygon.npoints + 4);
            for (int i = 1; i < polygon.npoints - 2; ++i) {
                final float n = (-polygon.xpoints[i - 1] + 3 * (polygon.xpoints[i] - polygon.xpoints[i + 1]) + polygon.xpoints[i + 2]) / 6;
                final float n2 = (-polygon.ypoints[i - 1] + 3 * (polygon.ypoints[i] - polygon.ypoints[i + 1]) + polygon.ypoints[i + 2]) / 6;
                final float n3 = (polygon.xpoints[i - 1] - 2 * polygon.xpoints[i] + polygon.xpoints[i + 1]) / 2;
                final float n4 = (polygon.ypoints[i - 1] - 2 * polygon.ypoints[i] + polygon.ypoints[i + 1]) / 2;
                final float n5 = (polygon.xpoints[i + 1] - polygon.xpoints[i - 1]) / 2;
                final float n6 = (polygon.ypoints[i + 1] - polygon.ypoints[i - 1]) / 2;
                final float n7 = (polygon.xpoints[i - 1] + 4 * polygon.xpoints[i] + polygon.xpoints[i + 1]) / 6;
                final float n8 = (polygon.ypoints[i - 1] + 4 * polygon.ypoints[i] + polygon.ypoints[i + 1]) / 6;
                for (int n9 = 50, j = 0; j <= n9; ++j) {
                    final float n10 = j / n9;
                    polygon2.addPoint(Math.round(((n * n10 + n3) * n10 + n5) * n10 + n7), Math.round(((n2 * n10 + n4) * n10 + n6) * n10 + n8));
                }
            }
        }
        return polygon2;
    }
    
    protected boolean clipPoint(final double n, final double n2, final double n3, final double n4, final double n5, final double n6) {
        return n < n3 || n > n4 || n2 < n5 || n2 > n6;
    }
    
    protected boolean clipLine(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8) {
        return this.clipLine(n, n2, n3, n4, n5, n6, n7, n8, false);
    }
    
    protected boolean clipLine(final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final boolean b) {
        return (!b || (n < n5 && n3 < n5) || (n > n6 && n3 > n6) || (n2 < n7 && n4 < n7)) && ((n < n5 && n3 < n5) || (n > n6 && n3 > n6) || (n2 < n7 && n4 < n7) || (n2 > n8 && n4 > n8));
    }
    
    protected int getLineDepth() {
        if (this.line3D >= 0) {
            return this.line3D;
        }
        final int dataSetCount = this.getDataSetCount(2);
        if (dataSetCount == 0) {
            return 0;
        }
        if (this.grid3D >= 0) {
            return this.grid3D / dataSetCount;
        }
        return this.get3DDepth() / dataSetCount;
    }
    
    protected int getLineWidth() {
        return (int)Math.round(this.lineWidthFactor * this.getLineDepth());
    }
    
    protected void drawSlab(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final NFLine nfLine, final Color color, final Color color2, final Color color3, final boolean b, final boolean b2) {
        this.drawSlab(graphics, n, n2, n3, n4, n5, n6, nfLine, color, color2, color3, b, b2, null);
    }
    
    protected void drawSlab(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final NFLine nfLine, final Color color, final Color color2, final Color color3, final boolean b, final boolean b2, final NFPatternFill nfPatternFill) {
        final Polygon polygon = new Polygon();
        polygon.addPoint(n, n2);
        polygon.addPoint(n3, n4);
        this.drawSlab(graphics, polygon, n5, n6, nfLine, color, color2, color3, b, b2, nfPatternFill);
    }
    
    protected void drawSlab(final Graphics graphics, final Polygon polygon, final int n, final int n2, final NFLine nfLine, final Color color, final Color color2, final Color color3, final boolean b, final boolean b2) {
        this.drawSlab(graphics, polygon, n, n2, nfLine, color, color2, color3, b, b2, null);
    }
    
    protected void drawSlab(final Graphics graphics, final Polygon polygon, final int n, final int n2, final NFLine nfLine, final Color color, final Color color2, final Color color3, final boolean b, final boolean b2, final NFPatternFill nfPatternFill) {
        if (n2 < 2 || color == null) {
            if (color2 != null) {
                final Polygon polygon2 = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
                polygon2.addPoint(polygon.xpoints[polygon.npoints - 1], n);
                polygon2.addPoint(polygon.xpoints[0], n);
                polygon2.addPoint(polygon.xpoints[0], polygon.ypoints[0]);
                graphics.setColor(color2);
                if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, polygon2, color, nfPatternFill);
                }
                else {
                    graphics.fillPolygon(polygon2);
                }
                if (color != null && color3 != null) {
                    graphics.setColor(color3);
                    if (b) {
                        graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[0], n);
                    }
                    if (b2) {
                        graphics.drawLine(polygon.xpoints[polygon.npoints - 1], polygon.ypoints[polygon.npoints - 1], polygon.xpoints[polygon.npoints - 1], n);
                    }
                }
            }
            if (nfLine != null && color != null) {
                nfLine.drawPoly(graphics, polygon);
            }
            return;
        }
        if (color != null) {
            graphics.setColor(NFColor.brighter(color));
            final int[] array = new int[4];
            final int[] array2 = new int[4];
            for (int i = 1; i < polygon.npoints; ++i) {
                array[0] = polygon.xpoints[i - 1];
                array2[0] = polygon.ypoints[i - 1];
                array[1] = polygon.xpoints[i];
                array2[1] = polygon.ypoints[i];
                array[2] = polygon.xpoints[i] + n2;
                array2[2] = polygon.ypoints[i] - n2;
                array[3] = polygon.xpoints[i - 1] + n2;
                array2[3] = polygon.ypoints[i - 1] - n2;
                final Polygon polygon3 = new Polygon(array, array2, 4);
                if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, polygon3, color, nfPatternFill);
                }
                else {
                    graphics.fillPolygon(polygon3);
                }
            }
            graphics.setColor(color);
            if (color2 != null && color3 != null) {
                graphics.setColor(color3);
            }
            for (int j = 1; j < polygon.npoints; ++j) {
                graphics.drawLine(polygon.xpoints[j - 1], polygon.ypoints[j - 1], polygon.xpoints[j], polygon.ypoints[j]);
                graphics.drawLine(polygon.xpoints[j - 1] + n2, polygon.ypoints[j - 1] - n2, polygon.xpoints[j] + n2, polygon.ypoints[j] - n2);
            }
            if (b) {
                graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[0] + n2, polygon.ypoints[0] - n2);
            }
            if (b2) {
                graphics.drawLine(polygon.xpoints[polygon.npoints - 1], polygon.ypoints[polygon.npoints - 1], polygon.xpoints[polygon.npoints - 1] + n2, polygon.ypoints[polygon.npoints - 1] - n2);
            }
        }
        if (color2 != null) {
            final Polygon polygon4 = new Polygon(polygon.xpoints, polygon.ypoints, polygon.npoints);
            polygon4.addPoint(polygon.xpoints[polygon.npoints - 1], n);
            polygon4.addPoint(polygon.xpoints[0], n);
            polygon4.addPoint(polygon.xpoints[0], polygon.ypoints[0]);
            graphics.setColor(color2);
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, polygon4, color, nfPatternFill);
            }
            else {
                graphics.fillPolygon(polygon4);
            }
        }
        if (color != null) {
            if (color2 == null || color3 == null) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(color3);
            }
            for (int k = 1; k < polygon.npoints; ++k) {
                graphics.drawLine(polygon.xpoints[k - 1], polygon.ypoints[k - 1], polygon.xpoints[k], polygon.ypoints[k]);
            }
            if (color2 != null) {
                if (b) {
                    graphics.drawLine(polygon.xpoints[0], polygon.ypoints[0], polygon.xpoints[0], n);
                }
                if (b2) {
                    graphics.drawLine(polygon.xpoints[polygon.npoints - 1], polygon.ypoints[polygon.npoints - 1], polygon.xpoints[polygon.npoints - 1], n);
                }
                graphics.drawLine(polygon.xpoints[0], n, polygon.xpoints[polygon.npoints - 1], n);
            }
        }
        if (b2 && color2 != null) {
            final int[] array3 = new int[4];
            final int[] array4 = new int[4];
            array3[0] = polygon.xpoints[polygon.npoints - 1];
            array4[0] = polygon.ypoints[polygon.npoints - 1];
            array3[1] = polygon.xpoints[polygon.npoints - 1] + n2;
            array4[1] = polygon.ypoints[polygon.npoints - 1] - n2;
            array3[2] = polygon.xpoints[polygon.npoints - 1] + n2;
            array4[2] = n - n2;
            array3[3] = polygon.xpoints[polygon.npoints - 1];
            array4[3] = n;
            final Polygon polygon5 = new Polygon(array3, array4, 4);
            graphics.setColor(NFColor.darker(color2));
            if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, polygon5, color, nfPatternFill);
            }
            else {
                graphics.fillPolygon(polygon5);
            }
            if (color3 != null) {
                graphics.setColor(color3);
                graphics.drawPolygon(polygon5);
            }
        }
    }
    
    protected int get3DDepth() {
        return this.get3DDepth(false);
    }
    
    protected int get3DDepth(final boolean b) {
        if (this.line3D > 0) {
            return this.line3D * this.getDataSetCount(2);
        }
        return 0;
    }
    
    protected void defineAxes(final NFParam nfParam) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("NORMAL", new Integer(0));
        hashtable.put("OUTWARD", new Integer(1));
        for (int i = 0; i < this.axisNames.length; ++i) {
            nfParam.defineSymbol(this.axisNames[i] + "AxesLayoutDirection", hashtable, new Integer(0));
            nfParam.defineVector(this.axisNames[i] + "AxesLayout", nfParam.defineNumber(this.axisNames[i] + "AxesLayoutElement", new Double(0.0)));
            nfParam.defineVector(this.axisNames[i] + "AxesGaps", nfParam.defineNumber(this.axisNames[i] + "AxesGap", new Double(0.0)));
            for (int j = 0; j < this.MaxAxes; ++j) {
                String string = this.axisNames[i];
                if (j > 0) {
                    string += j;
                }
                NFAxis.defineAxis(nfParam, string, this.axisTypes[i], String.valueOf(j).equals("1"));
            }
        }
    }
    
    protected void loadAxes() throws Exception {
        for (int i = 0; i < this.axisNames.length; ++i) {
            this.axesVectors[i].removeAllElements();
            for (int j = 0; j < this.MaxAxes; ++j) {
                String string = this.axisNames[i];
                if (j > 0) {
                    string += j;
                }
                final NFAxis loadAxisParams = this.loadAxisParams(string, getAxis(this.axes, string));
                if (j == 0) {
                    if (i == 0) {
                        this.bottomAxis = loadAxisParams;
                    }
                    else if (i == 1) {
                        this.topAxis = loadAxisParams;
                    }
                    else if (i == 2) {
                        this.leftAxis = loadAxisParams;
                    }
                    else if (i == 3) {
                        this.rightAxis = loadAxisParams;
                    }
                }
                if (loadAxisParams != null) {
                    this.axesVectors[i].addElement(loadAxisParams);
                }
            }
            this.loadAxesLayoutDirection(i);
            this.loadAxesLayout(i);
            this.loadAxesGaps(i);
            this.calculateLayoutTotals(i);
        }
    }
    
    protected void calculateLayoutTotals(final int n) {
        if (this.axesLayoutDirection[n] == 1) {
            this.axesLayoutTotals[n] = 100.0;
            return;
        }
        final boolean b = this.axesGapsVectors[n].size() == this.axesVectors[n].size() - 1;
        double n2 = 0.0;
        for (int i = 0; i < this.axesLayoutVectors[n].size(); ++i) {
            n2 += ((Number)this.axesLayoutVectors[n].elementAt(i)).doubleValue();
            if (b && i > 0) {
                n2 += ((Number)this.axesGapsVectors[n].elementAt(i - 1)).doubleValue();
            }
        }
        this.axesLayoutTotals[n] = n2;
    }
    
    protected void loadAxesLayoutDirection(final int n) throws Exception {
        if (super.param.changed(this.axisNames[n] + "AxesLayoutDirection")) {
            super.layoutChanged = true;
            this.axesLayoutDirection[n] = NFUtil.getNumber(super.param.get(this.axisNames[n] + "AxesLayoutDirection"), 0);
        }
    }
    
    protected void loadAxesLayout(final int n) throws Exception {
        if (super.param.changed(this.axisNames[n] + "AxesLayout")) {
            super.layoutChanged = true;
            this.axesLayoutVectors[n].removeAllElements();
            loadAxesPercentages((Vector)super.param.get(this.axisNames[n] + "AxesLayout"), this.axesLayoutVectors[n]);
        }
    }
    
    protected void loadAxesGaps(final int n) throws Exception {
        if (super.param.changed(this.axisNames[n] + "AxesGaps")) {
            super.layoutChanged = true;
            this.axesGapsVectors[n].removeAllElements();
            loadAxesPercentages((Vector)super.param.get(this.axisNames[n] + "AxesGaps"), this.axesGapsVectors[n]);
        }
    }
    
    protected static void loadAxesPercentages(final Vector vector, final Vector vector2) {
        for (int n = 0; vector != null && n < vector.size(); ++n) {
            final Number n2 = vector.elementAt(n);
            if (n2 != null && !Double.isNaN(n2.doubleValue())) {
                double doubleValue = n2.doubleValue();
                if (doubleValue > 100.0) {
                    doubleValue = 100.0;
                }
                if (doubleValue < 0.0) {
                    doubleValue = 0.0;
                }
                vector2.addElement(new Double(doubleValue));
            }
        }
    }
    
    protected static boolean insideAxis(final NFAxis nfAxis, final int n, final int n2) {
        int n3;
        if (isXAxis(nfAxis)) {
            n3 = n;
        }
        else {
            n3 = n2;
        }
        return n3 >= nfAxis.getMinCoord() && n3 <= nfAxis.getMaxCoord();
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        this.MaxDataSets = NFUtil.getNumber(this.getRuntimeProperties().getProperty("MaxDataSets"), this.MaxDataSets);
        this.MaxAxes = NFUtil.getNumber(this.getRuntimeProperties().getProperty("MaxAxes"), this.MaxAxes);
        this.UseOldAutoScaling = NFUtil.getBoolean(this.getRuntimeProperties().getProperty("UseOldAutoScaling"), this.UseOldAutoScaling);
        this.defineAxes(super.param);
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineNumber("BottomAxesHeight", null));
        vector.addElement(super.param.defineNumber("TopAxesHeight", null));
        vector.addElement(super.param.defineNumber("LeftAxesWidth", null));
        vector.addElement(super.param.defineNumber("RightAxesWidth", null));
        super.param.defineTuple("AxesSizes", vector);
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineColor("GridLine", null));
        vector2.addElement(super.param.defineColor("GridBG", null));
        vector2.addElement(super.param.defineColor("GridBorder", null));
        vector2.addElement(super.param.defineImage("GridImage", null));
        vector2.addElement(super.param.defineImageType("GridImageType", 0));
        super.param.defineVector("Grid", super.param.defineTuple("GridTuple", vector2));
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineString("GridXAxis", "BOTTOM"));
        vector3.addElement(super.param.defineString("GridYAxis", "LEFT"));
        super.param.defineVector("GridAxis", super.param.defineTuple("GridAxisTuple", vector3));
        super.param.defineNumber("Grid3DDepth", new Integer(-1));
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("SOLID", new Integer(1));
        hashtable.put("DOTTED", new Integer(2));
        hashtable.put("DASHED", new Integer(3));
        hashtable.put("DOTDASH", new Integer(4));
        hashtable.put("BAR", new Integer(9));
        hashtable.put("NONE", new Integer(0));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("BOTH", new Integer(3));
        hashtable2.put("HORIZONTAL", new Integer(2));
        hashtable2.put("VERTICAL", new Integer(1));
        hashtable2.put("NONE", new Integer(4));
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(super.param.defineSymbol("GridLineType", hashtable2, new Integer(3)));
        vector4.addElement(super.param.defineSymbol("GridLineStyle", hashtable, new Integer(1)));
        vector4.addElement(super.param.defineNumber("GridLineWidth", new Integer(1)));
        super.param.defineVector("GridLine", super.param.defineTuple("GridLineTuple", vector4));
        super.param.defineActiveLabel("GridActiveLabel");
        for (int i = 0; i < this.MaxDataSets; ++i) {
            super.param.defineActiveLabel("ActiveLabels" + (i + 1));
        }
        super.param.defineString("ActiveLabelsEnabled", "ON");
        super.param.defineNumber("Line3DDepth", new Integer(0));
        super.param.defineNumber("LineWidth", new Double(100.0));
        super.param.defineNumber("AxisThickness", new Integer(0));
        super.param.defineString("DisableZoom", null);
    }
    
    protected void defineDataAxisParams(final NFParam nfParam, final String s) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineString(s + "XAxis", "BOTTOM"));
        vector.addElement(nfParam.defineString(s + "YAxis", "LEFT"));
        nfParam.defineVector(s, nfParam.defineTuple(s + "Tuple", vector));
    }
    
    protected void defineLineSymbol(final NFParam nfParam) {
        this.defineLineSymbol(nfParam, "LineSymbol");
    }
    
    protected void defineLineSymbol(final NFParam nfParam, final String s) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        final Hashtable symbolTypeTable = nfParam.getSymbolTypeTable();
        final Hashtable symbolStyleTable = nfParam.getSymbolStyleTable();
        vector.addElement(nfParam.defineSymbol(s + "Type", symbolTypeTable, null));
        vector.addElement(nfParam.defineNumber(s + "Size", null));
        vector.addElement(nfParam.defineSymbol(s + "Style", symbolStyleTable, null));
        vector.addElement(nfParam.defineColor(s + "BorderColor", null));
        vector.addElement(nfParam.defineNumber(s + "BorderWidth", null));
        vector.addElement(nfParam.defineImage(s + "Image", null));
        vector.addElement(nfParam.defineColor(s + "Color", null));
        nfParam.defineVector(s, nfParam.defineTuple(s + "Tuple", vector));
    }
    
    protected void defineLineStyle(final NFParam nfParam) {
        this.defineLine(nfParam, "LineStyle", true, true, true);
    }
    
    protected void defineLine(final NFParam nfParam, final String s, final boolean b, final boolean b2, final boolean b3) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineSymbol(s + "Type", nfParam.getLineStyleTable(), null));
        vector.addElement(nfParam.defineNumber(s + "Width", null));
        vector.addElement(nfParam.defineColor(s + "Color", null));
        if (b2) {
            vector.addElement(nfParam.defineColor(s + "Fill", null));
        }
        if (b3) {
            final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
            hashtable.put("NORMAL", new Integer(0));
            hashtable.put("FIT", new Integer(1));
            hashtable.put("BOTH", new Integer(2));
            vector.addElement(nfParam.defineSymbol(s + "Curve", hashtable, new Integer(0)));
        }
        if (b) {
            nfParam.defineVector(s, nfParam.defineTuple(s + "Tuple", vector));
        }
        else {
            nfParam.defineTuple(s, vector);
        }
    }
    
    protected void loadAxesSizesParams() throws Exception {
        final Vector vector = (Vector)super.param.get("AxesSizes");
        this.chartAxesSizes[0] = NFUtil.getNumber(vector, 0, -1.0);
        this.chartAxesSizes[1] = NFUtil.getNumber(vector, 1, -1.0);
        this.chartAxesSizes[2] = NFUtil.getNumber(vector, 2, -1.0);
        this.chartAxesSizes[3] = NFUtil.getNumber(vector, 3, -1.0);
    }
    
    protected synchronized void loadParams() throws Exception {
        final boolean b = super.legend != null;
        super.loadParams();
        this.loadAxes();
        if (super.legend != null && !b) {
            final Enumeration<NFDataSeries> elements = (Enumeration<NFDataSeries>)this.dataSeries.elements();
            while (elements.hasMoreElements()) {
                super.legend.addDataSet(elements.nextElement());
            }
        }
        if (super.param.changed("AxesSizes")) {
            super.layoutChanged = true;
            this.loadAxesSizesParams();
        }
        if (super.param.changed("Grid") || super.param.changed("GridAxis") || super.param.changed("GridLine") || super.param.changed("Grid3DDepth") || super.param.changed("GridFormat")) {
            super.layoutChanged = true;
            this.loadGridParams();
        }
        if (super.param.changed("GridActiveLabel")) {
            this.loadGridActiveLabels();
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.setAllMapComponent(this, super.notesets);
            for (int size = super.notesets.size(), i = 0; i < size; ++i) {
                ((NFNoteSet)super.notesets.elementAt(i)).setAxisMap(this);
            }
        }
        if (super.legend != null) {
            super.legend.setAxisMap(this);
        }
        if (super.param.changed("Line3DDepth")) {
            super.graphChanged = true;
            super.layoutChanged = true;
            final Number n = (Number)super.param.get("Line3DDepth");
            if (n != null) {
                if (Double.isNaN(n.doubleValue())) {
                    this.line3D = -1;
                }
                else {
                    this.line3D = n.intValue();
                }
            }
            else {
                this.line3D = this.LINE_3D_DEFAULT;
            }
        }
        if (super.param.changed("LineWidth")) {
            super.graphChanged = true;
            final Number n2 = (Number)super.param.get("LineWidth");
            if (n2 != null) {
                this.lineWidthFactor = n2.doubleValue();
                if (this.lineWidthFactor < 0.0) {
                    this.lineWidthFactor = -this.lineWidthFactor;
                }
                if (this.lineWidthFactor > 1.0) {
                    this.lineWidthFactor /= 100.0;
                }
                if (this.lineWidthFactor > 1.0) {
                    this.lineWidthFactor = 1.0;
                }
            }
            else {
                this.lineWidthFactor = this.LINE_WIDTH_FACTOR_DEFAULT;
            }
        }
        if (super.param.changed("AxisThickness")) {
            super.graphChanged = true;
            super.layoutChanged = true;
            this.axisThickness = NFUtil.getNumber(super.param.get("AxisThickness"), JDataChart.AXIS_THICKNESS_DEFAULT);
        }
        if (super.param.changed("DisableZoom")) {
            this.disableZoom = NFUtil.getString(super.param.get("DisableZoom"), "OFF").equalsIgnoreCase("ON");
        }
        super.zoomEnabled = false;
        for (int n3 = 0; !this.disableZoom && n3 < this.axes.size(); ++n3) {
            if (((NFAxis)this.axes.elementAt(n3)).isSliderOn()) {
                super.zoomEnabled = true;
                break;
            }
        }
    }
    
    protected NFAxis loadAxisParams(final String s, final NFAxis nfAxis) throws Exception {
        final NFAxis loadAxis = NFAxis.loadAxis(this, super.param, s, nfAxis, super.dwell);
        if (loadAxis == null) {
            return nfAxis;
        }
        super.layoutChanged = true;
        if (loadAxis != nfAxis) {
            this.axes.addElement(loadAxis);
            this.setNumberFormat(loadAxis);
            for (int i = 0; i < super.observers.size(); ++i) {
                final Object element = super.observers.elementAt(i);
                if (element instanceof NFScrollObserver) {
                    loadAxis.addObserver(element);
                }
            }
        }
        return loadAxis;
    }
    
    protected void loadGridParams() throws Exception {
        this.loadGridParams(this.bottomAxis, this.leftAxis);
    }
    
    protected void loadGridParams(final NFAxis xAxis, final NFAxis yAxis) throws Exception {
        if (super.dwell != null) {
            for (int i = 0; i < this.grids.size(); ++i) {
                final NFActiveLabel activeLabel = this.grids.elementAt(i).getActiveLabel();
                if (activeLabel != null) {
                    super.dwell.removeLabel(activeLabel);
                }
            }
        }
        this.grids.removeAllElements();
        final Vector vector = (Vector)super.param.get("Grid");
        if (vector != null && vector.size() > 0) {
            for (int size = vector.size(), j = 0; j < size; ++j) {
                final Vector<Color> vector2 = vector.elementAt(j);
                final NFGrid nfGrid = new NFGrid();
                nfGrid.showGrid = true;
                nfGrid.showBorder = true;
                nfGrid.showBackground = true;
                nfGrid.XAxis = xAxis;
                nfGrid.YAxis = yAxis;
                nfGrid.ZAxis = this.rightSlantAxis;
                nfGrid.lineColor = vector2.elementAt(0);
                nfGrid.background = vector2.elementAt(1);
                nfGrid.borderColor = vector2.elementAt(2);
                final NFParamImage nfParamImage = (NFParamImage)vector2.elementAt(3);
                if (nfParamImage != null) {
                    nfGrid.im = nfParamImage.im;
                }
                else {
                    nfGrid.im = null;
                }
                nfGrid.imageType = ((Number)vector2.elementAt(4)).intValue();
                nfGrid.setActiveLabel(this.loadGridActiveLabel(null, null, j));
                this.grids.addElement(nfGrid);
            }
        }
        final int size2 = this.grids.size();
        final Vector vector3 = (Vector)super.param.get("GridAxis");
        if (vector3 != null && vector3.size() > 0) {
            for (int size3 = vector3.size(), n = 0; n < size3 && n < size2; ++n) {
                final NFGrid nfGrid2 = this.grids.elementAt(n);
                final Vector<String> vector4 = vector3.elementAt(n);
                final String s = vector4.elementAt(0);
                final String s2 = vector4.elementAt(1);
                if (s != null) {
                    nfGrid2.XAxis = this.getAxis(s);
                }
                if (s2 != null) {
                    nfGrid2.YAxis = this.getAxis(s2);
                }
            }
        }
        this.grid3D = NFUtil.getNumber(super.param.get("Grid3DDepth"), this.GRID_3D_DEFAULT);
        final Vector vector5 = (Vector)super.param.get("GridLine");
        if (vector5 != null && vector5.size() > 0) {
            for (int size4 = vector5.size(), n2 = 0; n2 < size4 && n2 < size2; ++n2) {
                final NFGrid nfGrid3 = this.grids.elementAt(n2);
                final Vector<Number> vector6 = vector5.elementAt(n2);
                final int intValue = vector6.elementAt(0).intValue();
                nfGrid3.showHorizontal = (intValue == 2 || intValue == 3);
                nfGrid3.showVertical = (intValue == 1 || intValue == 3);
                nfGrid3.lineStyle = vector6.elementAt(1).intValue();
                nfGrid3.lineWidth = vector6.elementAt(2).intValue();
            }
        }
    }
    
    protected void loadGridActiveLabels() {
        final Vector loadAllParams = NFActiveLabel.loadAllParams(super.param, "GridActiveLabel");
        for (int i = 0; i < this.grids.size(); ++i) {
            final NFGrid nfGrid = this.grids.elementAt(i);
            nfGrid.setActiveLabel(this.loadGridActiveLabel((NFActiveLabel)NFUtil.getObject(loadAllParams, i, null), nfGrid.getActiveLabel(), i));
        }
    }
    
    protected NFActiveLabel loadGridActiveLabel(NFActiveLabel nfActiveLabel, final NFActiveLabel nfActiveLabel2, final int selectedItemIndex) {
        if (nfActiveLabel == null) {
            nfActiveLabel = new NFActiveLabel();
        }
        final Polygon bounds = (nfActiveLabel2 != null) ? nfActiveLabel2.areaPoly : null;
        if (bounds != null) {
            nfActiveLabel.setBounds(bounds);
        }
        nfActiveLabel.selectedItemParam = "Grid";
        nfActiveLabel.selectedItemIndex = selectedItemIndex;
        if (super.dwell != null) {
            final Vector activeLabels = super.dwell.getActiveLabels();
            int n = 1;
            if (nfActiveLabel2 != null) {
                final int index = activeLabels.indexOf(nfActiveLabel2);
                if (index != -1) {
                    n = index;
                }
                super.dwell.removeLabel(nfActiveLabel2);
            }
            else {
                for (int i = 1; i < activeLabels.size(); ++i) {
                    final NFActiveLabel nfActiveLabel3 = activeLabels.elementAt(i);
                    if (nfActiveLabel3 != null) {
                        if (nfActiveLabel3.selectedItemParam == null || !nfActiveLabel3.selectedItemParam.equals("Grid")) {
                            n = i;
                            break;
                        }
                        if (i + 1 >= activeLabels.size()) {
                            n = i + 1;
                            break;
                        }
                    }
                }
            }
            super.dwell.addLabel(nfActiveLabel, n);
        }
        return nfActiveLabel;
    }
    
    public NFAxis getAxis(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.length() <= 0) {
            return null;
        }
        s = s.substring(0, 1).toUpperCase() + s.substring(1, s.length()).toLowerCase();
        if (NFAxis.getModifierOfName(s).equals("1")) {
            s = NFAxis.removeModifierFromName(s);
        }
        NFAxis nfAxis = getAxis(this.axes, s);
        if (nfAxis == null) {
            if (s.startsWith(this.axisNames[1])) {
                nfAxis = this.bottomAxis;
            }
            else if (s.startsWith(this.axisNames[3])) {
                nfAxis = this.leftAxis;
            }
            else if (s.startsWith(this.axisNames[2])) {
                nfAxis = this.rightAxis;
            }
            else if (s.startsWith(this.axisNames[0])) {
                nfAxis = this.topAxis;
            }
        }
        return nfAxis;
    }
    
    public static NFAxis getAxis(final Vector vector, final String s) {
        for (int i = 0; i < vector.size(); ++i) {
            final NFAxis nfAxis = vector.elementAt(i);
            if (nfAxis.getType().equals(s)) {
                return nfAxis;
            }
        }
        return null;
    }
    
    protected void loadDataAxisParams(final NFDataSeries nfDataSeries, final Vector vector, final int n) {
        if (vector == null || n >= vector.size()) {
            return;
        }
        final Vector<String> vector2 = vector.elementAt(n);
        final NFAxis axis = this.getAxis(vector2.elementAt(0));
        if (axis != null && isXAxis(axis)) {
            nfDataSeries.XAxis = axis;
        }
        final NFAxis axis2 = this.getAxis(vector2.elementAt(1));
        if (axis2 != null && isYAxis(axis2)) {
            nfDataSeries.YAxis = axis2;
        }
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final int n, final String s, final boolean[] array) throws Exception {
        return this.loadActiveLabels(nfParam, n, s, array, false);
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final int n, final String s, final boolean[] array, final boolean b) throws Exception {
        return this.loadActiveLabels(nfParam, n, s, array, b, false);
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final int n, final String s, final boolean[] array, final boolean b, final boolean b2) throws Exception {
        boolean b3 = false;
        final int size = this.dataSeries.size();
        int n2 = 0;
        for (int i = 0; i < size; ++i) {
            if (((NFDataSeries)this.dataSeries.elementAt(i)).type == n) {
                ++n2;
            }
        }
        int n3;
        int n4;
        int n5;
        int n6;
        if (b) {
            n3 = size - 1;
            n4 = -1;
            n5 = -1;
            ++n2;
            n6 = -1;
        }
        else {
            n3 = 0;
            n4 = size;
            n5 = 1;
            n2 = 0;
            n6 = 1;
        }
        final boolean changed = nfParam.changed(s + "Enabled");
        final boolean equals = NFUtil.getString(nfParam.get(s + "Enabled"), "ON").equals("ON");
        for (int j = n3; j != n4; j += n5) {
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(j);
            if (nfDataSeries.type == n) {
                n2 += n6;
                final String string = s + n2;
                if (b2 || array[j] || nfParam.changed(string) || changed) {
                    super.graphChanged = true;
                    b3 = true;
                    this.loadActiveLabels(nfParam, nfDataSeries, string, equals);
                }
            }
        }
        return b3;
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final NFDataSeries nfDataSeries, final String s) throws Exception {
        return this.loadActiveLabels(nfParam, nfDataSeries, s, true);
    }
    
    protected boolean loadActiveLabels(final NFParam nfParam, final NFDataSeries nfDataSeries, final String s, final boolean b) throws Exception {
        if (super.dwell != null) {
            super.dwell.removeLabel(nfDataSeries.activeLabels);
        }
        nfDataSeries.activeLabels.removeAllElements();
        if (super.dwell == null) {
            return true;
        }
        final int dataSetSize = this.getDataSetSize(nfDataSeries);
        if (!b) {
            return true;
        }
        final Vector loadAllParams = NFActiveLabel.loadAllParams(nfParam, s);
        for (int i = 0; i < dataSetSize; ++i) {
            nfDataSeries.activeLabels.addElement(super.dwell.addLabel(loadAllParams, i));
        }
        return true;
    }
    
    protected boolean loadDataSetParams(final NFParam nfParam, final int type, final String s, final String s2, final NFAxis xAxis, final NFAxis yAxis) throws Exception {
        if (!nfParam.changed(s) && !nfParam.changed(s2)) {
            return false;
        }
        super.graphChanged = true;
        super.legendChanged = true;
        this.deleteAllDataSets(type);
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return super.layoutChanged = true;
        }
        Vector vector2 = null;
        if (s2 != null) {
            vector2 = (Vector)nfParam.get(s2);
        }
        for (int size = vector.size(), n = 0; n < size && n < this.MaxDataSets; ++n) {
            final NFDataSeries nfDataSeries = new NFDataSeries();
            nfDataSeries.type = type;
            this.dataSeries.addElement(nfDataSeries);
            if (super.legend != null) {
                super.legend.addDataSet(nfDataSeries);
            }
            nfDataSeries.XAxis = xAxis;
            nfDataSeries.YAxis = yAxis;
            this.loadDataAxisParams(nfDataSeries, vector2, n);
            this.loadDataSetParams(nfDataSeries, n, vector.elementAt(n));
        }
        return true;
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
    }
    
    protected boolean[] loadDataSets(final NFParam nfParam, final int n, final String s, final boolean b) throws Exception {
        final int size = this.dataSeries.size();
        final boolean[] array = new boolean[size];
        int n2 = 0;
        for (int i = 0; i < size; ++i) {
            array[i] = false;
            final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
            if (nfDataSeries.type == n) {
                ++n2;
                final String string = s + n2;
                if (b || nfParam.changed(string)) {
                    array[i] = (super.graphChanged = true);
                    this.clearDataItems(nfDataSeries, i);
                    final Vector vector = (Vector)nfParam.get(string);
                    if (vector != null) {
                        if (vector.size() > 0) {
                            for (int size2 = vector.size(), j = 0; j < size2; ++j) {
                                this.loadDataItem(nfDataSeries, i, vector.elementAt(j));
                            }
                        }
                    }
                }
            }
        }
        return array;
    }
    
    protected void clearDataItems(final NFDataSeries nfDataSeries, final int n) {
        if (nfDataSeries.dataset != null) {
            nfDataSeries.dataset.clear();
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
    }
    
    protected void loadLineSymbol(final NFParam nfParam) throws Exception {
        this.loadDataSeriesSymbol(nfParam, "LineSymbol", 2, JDataChart.defaultLineSymbol);
    }
    
    protected void loadDataSeriesSymbol(final NFParam nfParam, final String s, final int n, final NFGraphSymbol nfGraphSymbol) throws Exception {
        final Vector vector = (Vector)nfParam.get(s);
        if (vector == null || vector.size() == 0) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, n);
            if (dataSeries == null) {
                break;
            }
            dataSeries.sym = this.loadSymbol(vector.elementAt(i), dataSeries.sym, nfGraphSymbol);
        }
    }
    
    protected NFGraphSymbol loadSymbol(final Vector vector, NFGraphSymbol copy, final NFGraphSymbol nfGraphSymbol) {
        if (copy == null) {
            copy = NFGraphSymbol.createCopy(nfGraphSymbol);
            copy.setScale(super.scale);
        }
        final Number n = vector.elementAt(0);
        if (n != null) {
            copy.type = n.intValue();
        }
        final Number n2 = vector.elementAt(1);
        if (n2 != null) {
            copy.size = n2.intValue();
        }
        final Number n3 = vector.elementAt(2);
        if (n3 != null) {
            copy.style = n3.intValue();
        }
        copy.setOutlineColor((Color)vector.elementAt(3));
        final Number n4 = vector.elementAt(4);
        if (n4 != null) {
            copy.setOutlineWidth(n4.intValue());
        }
        final NFParamImage nfParamImage = (NFParamImage)vector.elementAt(5);
        if (nfParamImage != null) {
            copy.setImage(nfParamImage.im);
        }
        else {
            copy.setImage(null);
        }
        copy.setColor(NFUtil.getColor(vector, 6, null));
        return copy;
    }
    
    protected void loadLineStyle(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("LineStyle");
        if (vector == null || vector.size() == 0) {
            return;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 2);
            if (dataSeries == null) {
                break;
            }
            final Vector<Color> vector2 = vector.elementAt(i);
            dataSeries.line = this.loadLine(vector2, dataSeries.line, JDataChart.defaultLineStyle);
            if (dataSeries.line.getColor() == null) {
                dataSeries.line.setColor(dataSeries.c);
            }
            dataSeries.fillColor = vector2.elementAt(3);
            dataSeries.lineCurveType = NFUtil.getNumber(vector2.elementAt(4), 0);
        }
    }
    
    protected NFLine loadLine(final Vector vector, NFLine copy, final NFLine nfLine) {
        if (copy == null) {
            copy = NFLine.createCopy(nfLine);
            copy.setScale(super.scale);
        }
        final Number n = vector.elementAt(0);
        if (n != null) {
            copy.setStyle(n.intValue());
        }
        final Number n2 = vector.elementAt(1);
        if (n2 != null) {
            copy.setThickness(n2.intValue());
        }
        copy.setColor((Color)vector.elementAt(2));
        return copy;
    }
    
    protected int getDataSetSize(final NFDataSeries nfDataSeries) {
        if (nfDataSeries.dataset != null) {
            return nfDataSeries.dataset.size();
        }
        return 0;
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final int n5, final double n6) {
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n5, Double.toString(n6));
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final double n5, final double n6) {
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, Double.toString(n5), Double.toString(n6));
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, int n2, int n3, final int n4, final String s, final String s2) {
        n2 -= n4 / 2;
        n3 -= n4 / 2;
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n4, s, s2);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, int n2, int n3, final int n4, final String s) {
        n2 -= n4 / 2;
        n3 -= n4 / 2;
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n4, s);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final int n5, final String s, final String s2) {
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n5, "(" + s + "," + s2 + ")", s, null);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final int n5, final String s) {
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n5, s, (NFGraphSymbol)null);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final int n5, final String s, final NFGraphSymbol nfGraphSymbol) {
        this.setActiveLabel(nfDataSeries, n, n2, n3, n4, n5, s, null, nfGraphSymbol);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final int n, final int n2, final int n3, final int n4, final int n5, final String s, final String s2, final NFGraphSymbol nfGraphSymbol) {
        String s3 = "DataSet";
        switch (nfDataSeries.type) {
            case 1: {
                s3 = "DataSet";
                break;
            }
            case 2: {
                s3 = "LineSet";
                break;
            }
            case 4: {
                s3 = "StockData";
                break;
            }
            case 3: {
                s3 = "DataSet";
                break;
            }
            case 5: {
                s3 = "DataSet";
                break;
            }
            case 6: {
                s3 = "BubbleSet";
                break;
            }
            default: {
                NFDebug.print("Unknown data series type, " + nfDataSeries.type);
                break;
            }
        }
        this.setActiveLabel(nfDataSeries, nfDataSeries.activeLabels, s3 + String.valueOf(this.getDataSeriesIndex(nfDataSeries)), n, n2, n3, n4, n5, s, s2, nfGraphSymbol);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final Vector vector, final String s, final int n, final int n2, final int n3, final int n4, final int n5, final String s2, final String s3, final NFGraphSymbol nfGraphSymbol) {
        this.setActiveLabel(nfDataSeries, vector, s, n, n, n2, n3, n4, n5, s2, s3, nfGraphSymbol);
    }
    
    protected void setActiveLabel(final NFDataSeries nfDataSeries, final Vector vector, final String selectedItemParam, final int selectedItemIndex, final int n, int n2, int n3, int n4, int n5, final String label, final String s, final NFGraphSymbol nfGraphSymbol) {
        if (!super.dwellOn || vector == null) {
            return;
        }
        final int size = vector.size();
        if (n < 0 || n >= size) {
            return;
        }
        final NFActiveLabel nfActiveLabel = (NFActiveLabel)NFUtil.getObject(vector, n, null);
        if (nfActiveLabel == null) {
            return;
        }
        nfActiveLabel.selectedItemParam = selectedItemParam;
        nfActiveLabel.selectedItemIndex = selectedItemIndex;
        if (nfActiveLabel.selectedItemLabels == null) {
            nfActiveLabel.selectedItemLabels = new Vector();
        }
        else {
            nfActiveLabel.selectedItemLabels.removeAllElements();
        }
        if (super.legend != null) {
            nfActiveLabel.selectedItemLabels.addElement(new String[] { "LegendLabel", super.legend.getLegendLabel(nfDataSeries, this.getDataSeriesIndex(nfDataSeries)) });
        }
        if (s != null) {
            nfActiveLabel.selectedItemLabels.addElement(new String[] { "XAxisLabel", s });
        }
        else if (nfDataSeries.XAxis != null) {
            nfActiveLabel.selectedItemLabels.addElement(new String[] { "XAxisLabel", nfDataSeries.XAxis.getNthLabel(n) });
        }
        if (nfActiveLabel.label == null || nfActiveLabel.label.length() < 1) {
            nfActiveLabel.autoLabel = true;
        }
        if (nfActiveLabel.autoLabel) {
            nfActiveLabel.setLabel(label);
        }
        if (n4 < 0) {
            n4 = -n4;
            n2 -= n4;
        }
        if (n5 < 0) {
            n5 = -n5;
            n3 -= n5;
        }
        if (nfGraphSymbol == null) {
            nfActiveLabel.setBounds(n2, n3, n4, n5);
            return;
        }
        final Polygon outline = nfGraphSymbol.getOutline();
        if (outline == null) {
            nfActiveLabel.setBounds(n2, n3, n4, n5);
            return;
        }
        nfActiveLabel.setBounds(outline);
    }
    
    public void zoom(final int n, final int n2, final int n3, final int n4) {
        boolean b = false;
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            final NFAxis nfAxis = this.axes.elementAt(i);
            if (insideAxis(nfAxis, n, n2)) {
                b |= nfAxis.setMinMaxRect(n, n2, n3, n4);
            }
        }
        if (b) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    public void zoomIn() {
        boolean b = false;
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            b |= ((NFAxis)this.axes.elementAt(i)).zoomIn();
        }
        if (b) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    public void zoomOut() {
        boolean b = false;
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            b |= ((NFAxis)this.axes.elementAt(i)).zoomOut();
        }
        if (b) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    public void zoomHome() {
        boolean b = false;
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            b |= ((NFAxis)this.axes.elementAt(i)).zoomHome();
        }
        if (b) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    public void scroll(final int n) {
        boolean b = false;
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            b |= ((NFAxis)this.axes.elementAt(i)).scroll(n);
        }
        if (b) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            if (((NFAxis)this.axes.elementAt(i)).mouseDown(event, n, n2)) {
                return false;
            }
        }
        return super.mouseDown(event, n, n2);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            if (((NFAxis)this.axes.elementAt(i)).mouseDrag(event, n, n2)) {
                super.layoutChanged = false;
                super.graphChanged = false;
                super.incrementalUpdate = true;
                this.axisScrollUpdate = true;
                this.repaint();
                return false;
            }
        }
        return super.mouseDrag(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int size = this.axes.size(), i = 0; i < size; ++i) {
            if (((NFAxis)this.axes.elementAt(i)).mouseUp(event, n, n2)) {
                super.layoutChanged = true;
                super.graphChanged = false;
                super.incrementalUpdate = false;
                this.axisScrollUpdate = false;
                this.repaint();
                return false;
            }
        }
        return super.mouseUp(event, n, n2);
    }
    
    protected void notifyGraphObservers(final Graphics graphics) {
        if (this.axisScrollUpdate) {
            this.axisScrollUpdate = false;
            return;
        }
        super.notifyGraphObservers(graphics);
    }
    
    public void clean() {
        super.clean();
        for (int n = 0; this.axes != null && n < this.axes.size(); ++n) {
            final NFAxis nfAxis = this.axes.elementAt(n);
            if (nfAxis != null) {
                boolean b = false;
                for (int i = 0; i < this.axisNames.length; ++i) {
                    if (nfAxis.getType().equals(this.axisNames[i])) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    nfAxis.setDefaultTicDivisions();
                    nfAxis.autoscale = true;
                }
                else if (nfAxis != this.rightSlantAxis) {
                    this.axes.removeElementAt(n);
                    --n;
                }
            }
        }
        for (int j = 0; j < this.axesVectors.length; ++j) {
            this.axesVectors[j].removeAllElements();
            if (j < this.axesLayoutVectors.length) {
                this.axesLayoutVectors[j].removeAllElements();
            }
            if (j < this.axesGapsVectors.length) {
                this.axesGapsVectors[j].removeAllElements();
            }
        }
        if (this.bottomAxis != null) {
            this.bottomAxis.showTicsAndLabels(true, true);
            this.bottomAxis.showAxisLine(true);
        }
        if (this.leftAxis != null) {
            this.leftAxis.showTicsAndLabels(true, true);
            this.leftAxis.showAxisLine(true);
        }
    }
    
    protected void loadPatternFill(final NFParam nfParam, final int n, final String s) throws NFParamException {
        if (nfParam.changed(s)) {
            super.graphChanged = true;
            final NFPatternFill[] loadPatternFillParam = NFPatternFill.loadPatternFillParam(nfParam, s);
            final int size = this.dataSeries.size();
            int n2 = 0;
            for (int i = 0; i < size; ++i) {
                final NFDataSeries nfDataSeries = this.dataSeries.elementAt(i);
                if (nfDataSeries.type == n) {
                    nfDataSeries.pattern = ((loadPatternFillParam == null || n2 >= loadPatternFillParam.length) ? null : loadPatternFillParam[n2]);
                    ++n2;
                }
            }
        }
    }
    
    public void scrollTo(final NFAxis nfAxis, final double n) {
        if (nfAxis == null) {
            return;
        }
        if (nfAxis.scrollTo(n)) {
            super.layoutChanged = true;
            super.graphChanged = false;
            super.incrementalUpdate = false;
            this.axisScrollUpdate = false;
            this.repaint();
        }
    }
    
    protected void loadNumberFormat(final Vector vector) {
        super.loadNumberFormat(vector);
        for (int i = 0; i < this.axes.size(); ++i) {
            this.setNumberFormat((NFAxis)this.axes.elementAt(i));
        }
    }
    
    protected void setNumberFormat(final NFAxis nfAxis) {
        nfAxis.setNumberFormat(this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
    }
    
    protected static boolean isYAxis(final NFAxis nfAxis) {
        return nfAxis != null && !isXAxis(nfAxis);
    }
    
    protected static boolean isXAxis(final NFAxis nfAxis) {
        if (nfAxis == null) {
            return false;
        }
        switch (nfAxis.getTicPosition()) {
            case 2:
            case 3: {
                return false;
            }
            case 1:
            case 4: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected void handleLayoutChange() {
        this.bottomAxis.clearTicLabels();
        this.bottomAxis.autoscale = true;
        this.bottomAxis.setSpacing(null);
        this.bottomAxis.setActiveLabels(null);
        this.bottomAxis.reverseActiveLabels = false;
        super.param.setChanged("BottomLabels");
        super.param.setChanged("BottomActiveLabels");
        this.leftAxis.clearTicLabels();
        this.leftAxis.autoscale = true;
        this.leftAxis.setSpacing(null);
        this.leftAxis.setActiveLabels(null);
        this.leftAxis.reverseActiveLabels = false;
        super.param.setChanged("LeftLabels");
        super.param.setChanged("LeftActiveLabels");
        for (int i = 0; i < JDataChart.axisParameters.length; ++i) {
            this.setChangedIfLoaded(JDataChart.axisParameters[i]);
        }
    }
    
    protected void setChangedIfLoaded(final String changed) {
        if (this.beenLoaded(changed)) {
            super.param.setChanged(changed);
        }
    }
    
    protected void setLoaded(final String s, final boolean loaded) {
        try {
            super.param.getParamDef(s).loaded = loaded;
        }
        catch (Exception ex) {}
    }
    
    protected boolean beenLoaded(final String s) {
        try {
            return super.param.getParamDef(s).loaded;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected void cleanLayoutParameters() {
        for (int i = 0; i < JDataChart.axisParameters.length; ++i) {
            if (this.beenLoaded(JDataChart.axisParameters[i])) {
                this.setLoaded(JDataChart.axisParameters[i], false);
            }
        }
    }
    
    static {
        JDataChart.AXIS_THICKNESS_DEFAULT = 0;
        axisParameters = new String[] { "BottomTics", "BottomAxis", "BottomScale", "BottomScaleSet", "BottomScaleMode", "BottomScroll", "LeftTics", "LeftAxis", "LeftScale", "LeftScaleSet", "LeftScaleMode", "LeftScroll" };
        JDataChart.defaultLineSymbol = null;
        JDataChart.defaultLineStyle = null;
        JDataChart.defaultLineSymbol = new NFGraphSymbol();
        JDataChart.defaultLineSymbol.type = 0;
        JDataChart.defaultLineSymbol.size = 1;
        JDataChart.defaultLineSymbol.style = 1;
        (JDataChart.defaultLineStyle = new NFLine(null)).setStyle(0);
        JDataChart.defaultLineStyle.setThickness(1);
        JDataChart.defaultLineStyle.setColor(Color.black);
    }
}
