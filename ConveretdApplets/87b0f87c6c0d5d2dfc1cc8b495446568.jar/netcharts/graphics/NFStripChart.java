// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Enumeration;
import java.awt.Component;
import netcharts.util.NFParamDef;
import java.util.Hashtable;
import java.net.URL;
import netcharts.util.NFUtil;
import java.util.Vector;
import netcharts.util.NFParam;
import java.awt.Point;
import netcharts.util.NFDebug;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Polygon;
import netcharts.util.NFDataSet;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;

public class NFStripChart extends NFDataChart
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    protected Color labelBackgroundColor;
    protected static String UNDEF_STRING_DEFAULT;
    protected String undefString;
    protected static int INITIAL_FILL_DEFAULT;
    protected int initialFill;
    protected int nslots;
    protected static int DISPLAY_SLOTS_DEFAULT;
    protected int displayslots;
    protected int scrollSlots;
    protected static int MAX_LOOK_AHEAD_DEFAULT;
    protected int maxLookAhead;
    protected Rectangle workRect;
    private static final boolean a = false;
    private Image b;
    protected int BAR_STYLE_DEFAULT;
    protected int barStyle;
    private Rectangle c;
    private Rectangle d;
    private Rectangle e;
    private Rectangle f;
    private int g;
    private NFDataSet h;
    private int i;
    private boolean j;
    private int k;
    private double l;
    private int m;
    private int n;
    private NFActiveLabel[][] o;
    private int p;
    private static int q;
    private static int r;
    private static int s;
    private boolean t;
    private int[] u;
    private boolean v;
    private Polygon w;
    
    public NFStripChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.labelBackgroundColor = null;
        this.undefString = NFStripChart.UNDEF_STRING_DEFAULT;
        this.initialFill = NFStripChart.INITIAL_FILL_DEFAULT;
        this.nslots = 11;
        this.displayslots = NFStripChart.DISPLAY_SLOTS_DEFAULT;
        this.scrollSlots = -1;
        this.maxLookAhead = NFStripChart.MAX_LOOK_AHEAD_DEFAULT;
        this.workRect = new Rectangle(0, 0, 0, 0);
        this.b = null;
        this.BAR_STYLE_DEFAULT = 1;
        this.barStyle = this.BAR_STYLE_DEFAULT;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.i = 0;
        this.j = false;
        this.k = 0;
        this.l = 0.0;
        this.n = 10;
        this.o = null;
        this.p = 0;
        this.t = false;
        this.u = null;
        this.v = true;
        this.w = null;
        this.initGraph(applet);
        this.initStripChart();
        this.reshape(n, n2, n3, n4);
    }
    
    public NFStripChart(final Applet applet) {
        this.labelBackgroundColor = null;
        this.undefString = NFStripChart.UNDEF_STRING_DEFAULT;
        this.initialFill = NFStripChart.INITIAL_FILL_DEFAULT;
        this.nslots = 11;
        this.displayslots = NFStripChart.DISPLAY_SLOTS_DEFAULT;
        this.scrollSlots = -1;
        this.maxLookAhead = NFStripChart.MAX_LOOK_AHEAD_DEFAULT;
        this.workRect = new Rectangle(0, 0, 0, 0);
        this.b = null;
        this.BAR_STYLE_DEFAULT = 1;
        this.barStyle = this.BAR_STYLE_DEFAULT;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.i = 0;
        this.j = false;
        this.k = 0;
        this.l = 0.0;
        this.n = 10;
        this.o = null;
        this.p = 0;
        this.t = false;
        this.u = null;
        this.v = true;
        this.w = null;
        this.initGraph(applet);
        this.initStripChart();
    }
    
    protected void drawData(final Graphics graphics) {
    }
    
    private void a(final String s) {
        NFDebug.print(512L, "NFStripChart: " + s);
    }
    
    protected void drawDataLite(final Graphics graphics) {
    }
    
    protected void initStripChart() {
        this.initChart();
        if (super.topAxis != null) {
            super.axes.removeElement(super.topAxis);
        }
        if (super.bottomAxis != null) {
            super.axes.removeElement(super.bottomAxis);
        }
        super.topAxis = NFStripAxis.defaultAxis("Top");
        super.bottomAxis = NFStripAxis.defaultAxis("Bottom");
        super.XValuesAxis = super.bottomAxis;
        super.axes.addElement(super.topAxis);
        this.setNumberFormat(super.topAxis);
        super.axes.addElement(super.bottomAxis);
        this.setNumberFormat(super.bottomAxis);
        super.topAxis.autoscale = false;
        super.bottomAxis.autoscale = false;
    }
    
    protected void slotsChanged() {
        Point mapValue = new Point(0, 0);
        this.h = new NFDataSet(this.nslots);
        for (int i = 0; i < this.nslots; ++i) {
            mapValue = super.bottomAxis.mapValue(i, mapValue);
            this.h.addPoint(mapValue.x);
        }
        this.v = true;
    }
    
    protected boolean loadDataSetParams(final NFParam nfParam, final int n, final String s, final String s2, final NFAxis nfAxis, final NFAxis nfAxis2) throws Exception {
        final int k = this.k;
        this.k = 0;
        final boolean loadDataSetParams = super.loadDataSetParams(nfParam, n, s, s2, nfAxis, nfAxis2);
        if (!loadDataSetParams) {
            this.k = k;
            return loadDataSetParams;
        }
        this.i = 0;
        ((NFStripAxis)super.bottomAxis).changed = true;
        ((NFStripAxis)super.topAxis).changed = true;
        this.n = this.d();
        if (super.dwellOn && super.dwell != null) {
            this.o = new NFActiveLabel[super.dataSeries.size()][this.nslots];
            for (int i = 0; i < super.dataSeries.size(); ++i) {
                for (int j = 0; j < this.nslots; ++j) {
                    (this.o[i][j] = new NFActiveLabel()).setBounds(-1, -1, 0, 0);
                    super.dwell.addLabel(this.o[i][j]);
                }
            }
        }
        if (!nfParam.changed("BarStyle")) {
            this.c();
        }
        return loadDataSetParams;
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        final int n2 = 2;
        if (nfDataSeries.dataset == null) {
            nfDataSeries.dataset = new NFDataSet(this.nslots);
        }
        else {
            nfDataSeries.dataset.clear();
        }
        final NFStripChartInfo info = new NFStripChartInfo();
        nfDataSeries.info = info;
        info.rawdata = new NFDataSet(this.maxLookAhead);
        ((NFStripAxis)super.topAxis).setHistorySize(this.nslots);
        ((NFStripAxis)super.bottomAxis).setHistorySize(this.nslots);
        info.history = new NFDataSet(this.nslots);
        final Vector vector = (Vector)o;
        final String name = vector.elementAt(0);
        Color defaultColor = (Color)vector.elementAt(1);
        NFGraphSymbol loadParams = NFGraphSymbol.loadParams(super.param, vector, n2);
        if (loadParams == null) {
            loadParams = new NFGraphSymbol();
            loadParams.type = 0;
            loadParams.size = super.dwellOffset;
            loadParams.style = 1;
            loadParams.setScale(super.scale);
        }
        final NFLine loadParams2 = NFLine.loadParams(super.param, vector, n2 + NFStripChart.r);
        final Color fillColor = (Color)vector.elementAt(n2 + NFStripChart.r + NFStripChart.s);
        if (defaultColor == null) {
            defaultColor = this.defaultColor(n);
        }
        if (loadParams != null) {
            if (loadParams.type == 8) {
                loadParams.type = 7;
            }
            if (loadParams.type == 7) {
                ++this.k;
            }
        }
        if (loadParams2 != null && loadParams2.getColor() == null) {
            loadParams2.setColor(defaultColor);
        }
        nfDataSeries.c = defaultColor;
        nfDataSeries.name = name;
        nfDataSeries.sym = loadParams;
        nfDataSeries.line = loadParams2;
        nfDataSeries.fillColor = fillColor;
        nfDataSeries.activeLabels = new Vector();
    }
    
    private void a(final Graphics graphics, final boolean b) {
        if (this.k > 0) {
            if (this.barStyle == 3) {
                this.b(graphics, b);
            }
            else {
                this.c(graphics, b);
            }
        }
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.sym == null || nfDataSeries.sym.type != 7) {
                this.a(graphics, nfDataSeries, i, b);
            }
        }
    }
    
    private int[] a(final int n) {
        if (this.u == null || this.u.length < n) {
            this.u = new int[n];
            this.v = true;
        }
        if (this.initialFill == 1 && n < this.nslots) {
            for (int i = 0; i < n; ++i) {
                this.u[i] = (int)this.h.getNth(this.nslots - n + i);
            }
        }
        else if (this.v) {
            for (int j = 0; j < n; ++j) {
                this.u[j] = (int)this.h.getNth(j);
            }
        }
        this.v = false;
        return this.u;
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        if (n < 1) {
            return;
        }
        if (n == 1) {
            graphics.drawLine(this.w.xpoints[0], n2, this.w.xpoints[0], this.w.ypoints[0]);
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final NFPatternFill nfPatternFill) {
        if (this.w == null) {
            (this.w = new Polygon()).addPoint(0, 0);
            this.w.addPoint(0, 0);
            this.w.addPoint(0, 0);
            this.w.addPoint(0, 0);
        }
        this.w.xpoints[0] = n;
        this.w.ypoints[0] = n2;
        this.w.xpoints[1] = n3;
        this.w.ypoints[1] = n4;
        this.w.xpoints[2] = n3;
        this.w.ypoints[2] = n5;
        this.w.xpoints[3] = n;
        this.w.ypoints[3] = n5;
        this.w.npoints = 4;
        if (NFUtil.getJDKVersion() >= 1.2 && nfPatternFill != null && nfPatternFill.pattern != 0) {
            NF12GraphicsUtil.patternFillPolygon(graphics, this.w, color, nfPatternFill);
        }
        else {
            graphics.fillPolygon(this.w);
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final boolean b) {
        final int size = nfDataSeries.dataset.size();
        if (size < 1) {
            return;
        }
        final int x = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMin()).x;
        final int x2 = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMax()).x;
        final int y = nfDataSeries.YAxis.mapValue(nfDataSeries.YAxis.getMin()).y;
        final int[] a = this.a(size);
        if (size > 1 && (nfDataSeries.line != null || nfDataSeries.fillColor != null)) {
            double n2 = nfDataSeries.dataset.getNth(0);
            for (int i = 1; i < size; ++i) {
                final double n3 = n2;
                n2 = nfDataSeries.dataset.getNth(i);
                if (!Double.isNaN(n3)) {
                    if (!Double.isNaN(n2)) {
                        if (a[i - 1] >= x || a[i] >= x) {
                            if (a[i - 1] <= x2 || a[i] <= x2) {
                                if (nfDataSeries.fillColor != null) {
                                    graphics.setColor(nfDataSeries.fillColor);
                                    this.a(graphics, a[i - 1], (int)n3, a[i], (int)n2, y, (nfDataSeries.line == null) ? nfDataSeries.c : nfDataSeries.line.getColor(), nfDataSeries.pattern);
                                }
                                if (nfDataSeries.line != null) {
                                    nfDataSeries.line.draw(graphics, a[i - 1], (int)n3, a[i], (int)n2);
                                }
                            }
                        }
                    }
                }
            }
        }
        graphics.setColor(nfDataSeries.c);
        final int n4 = (nfDataSeries.sym == null) ? 0 : (nfDataSeries.sym.size / 2);
        final NFStripChartInfo nfStripChartInfo = (NFStripChartInfo)nfDataSeries.info;
        final double[] array = (double[])((nfStripChartInfo == null || nfStripChartInfo.history == null) ? null : nfStripChartInfo.history.getDoubleArray());
        for (int j = 0; j < size; ++j) {
            final double nth = nfDataSeries.dataset.getNth(j);
            if (Double.isNaN(nth) || a[j] < x || a[j] > x2) {
                if (super.dwellOn && (b || j == size - 1)) {
                    this.a(n, j, size, -1, -1, 0, 0);
                }
            }
            else {
                if (nfDataSeries.sym != null) {
                    nfDataSeries.sym.draw(graphics, a[j], (int)nth);
                }
                if (nfDataSeries.valueLabelStyle != 0 && array != null && array.length > j) {
                    this.drawLineValueLabel(graphics, nfDataSeries, a[j], (int)nth, nfDataSeries.YAxis.getLabel(array[j]));
                }
                if (super.dwellOn && (b || j == size - 1) && nfDataSeries.sym != null) {
                    this.a(n, j, size, a[j] - n4, (int)nth - n4, nfDataSeries.sym.size, nfDataSeries.sym.size);
                }
            }
        }
    }
    
    private void a(final int n, final int selectedItemIndex, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (this.o == null) {
            return;
        }
        NFActiveLabel nfActiveLabel;
        if (this.initialFill == 0 || n2 >= this.nslots) {
            nfActiveLabel = this.o[n][selectedItemIndex];
            nfActiveLabel.selectedItemIndex = selectedItemIndex;
        }
        else {
            nfActiveLabel = this.o[n][this.nslots - n2 + selectedItemIndex];
            nfActiveLabel.selectedItemIndex = this.nslots - n2 + selectedItemIndex;
        }
        nfActiveLabel.selectedItemParam = "DataSet" + (n + 1);
        nfActiveLabel.setBounds(n3, n4, n5, n6);
    }
    
    private void b(final Graphics graphics, final boolean b) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        final NFDataSeries nfDataSeries = super.dataSeries.elementAt(0);
        final int size2 = nfDataSeries.dataset.size();
        final int[] a = this.a(size2);
        final int x = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMin()).x;
        final int x2 = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMax()).x;
        for (int i = 0; i < size2; ++i) {
            double n2;
            double n = n2 = 0.0;
            double n4;
            double n3 = n4 = 0.0;
            for (int j = 0; j < size; ++j) {
                final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(j);
                final Point baseLine = this.getBaseLine(nfDataSeries2.YAxis);
                if (j == 0) {
                    n = (n2 = baseLine.y);
                    n3 = (n4 = 0.0);
                }
                if (nfDataSeries2.sym != null) {
                    if (nfDataSeries2.sym.type == 7) {
                        final int n5 = nfDataSeries2.sym.size / 2;
                        graphics.setColor(nfDataSeries2.c);
                        final double nth = nfDataSeries2.dataset.getNth(i);
                        if (Double.isNaN(nth) || a[i] < x || a[i] > x2) {
                            if (super.dwellOn && (b || i == size2 - 1)) {
                                this.a(j, i, size2, -1, -1, 0, 0);
                            }
                        }
                        else {
                            int n6 = (int)(baseLine.y - nth);
                            if (n6 < 0) {
                                n6 = -n6;
                            }
                            int n7;
                            if (nth > baseLine.y) {
                                n4 += n6;
                                n7 = (int)n2;
                                n2 += n4;
                            }
                            else {
                                n3 += n6;
                                n7 = (int)n - n6;
                                n = n7;
                            }
                            if (n6 == 0) {
                                n6 = 1;
                            }
                            nfDataSeries2.sym.draw(graphics, a[i], n7, n6, nfDataSeries2.pattern);
                            if (super.dwellOn && (b || i == size2 - 1)) {
                                this.a(j, i, size2, a[i] - n5, n7, nfDataSeries2.sym.size, n6);
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void c(final Graphics graphics, final boolean b) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        final NFDataSeries nfDataSeries = super.dataSeries.elementAt(0);
        final int size2 = nfDataSeries.dataset.size();
        final int[] a = this.a(size2);
        final int x = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMin()).x;
        final int x2 = nfDataSeries.XAxis.mapValue(nfDataSeries.XAxis.getMax()).x;
        for (int i = 0; i < size2; ++i) {
            int n = a[i] + (int)this.l;
            for (int j = 0; j < size; ++j) {
                final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(j);
                final Point baseLine = this.getBaseLine(nfDataSeries2.YAxis);
                if (nfDataSeries2.sym != null) {
                    if (nfDataSeries2.sym.type == 7) {
                        final int n2 = nfDataSeries2.sym.size / 2;
                        graphics.setColor(nfDataSeries2.c);
                        final double nth = nfDataSeries2.dataset.getNth(i);
                        if (Double.isNaN(nth) || a[i] < x || a[i] > x2) {
                            n += this.m;
                            if (super.dwellOn && (b || i == size2 - 1)) {
                                this.a(j, i, size2, -1, -1, 0, 0);
                            }
                        }
                        else {
                            int n3 = (int)(baseLine.y - nth);
                            if (n3 < 0) {
                                n3 = -n3;
                            }
                            int y;
                            if (nth > baseLine.y) {
                                y = baseLine.y;
                            }
                            else {
                                y = (int)nth;
                            }
                            if (n3 == 0) {
                                n3 = 1;
                            }
                            nfDataSeries2.sym.draw(graphics, n, y, n3, nfDataSeries2.pattern);
                            if (super.dwellOn && (b || i == size2 - 1)) {
                                this.a(j, i, size2, n - this.m / 2, y, this.m, n3);
                            }
                            n += this.m;
                        }
                    }
                }
            }
        }
    }
    
    private void a() {
        final int size = super.dataSeries.size();
        Point mapValue = new Point(0, 0);
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            nfDataSeries.dataset.clear();
            final double[] doubleArray = ((NFStripChartInfo)nfDataSeries.info).history.getDoubleArray();
            final int length = doubleArray.length;
            if (length != 0) {
                int n;
                if (length > this.nslots) {
                    n = length - this.nslots;
                }
                else {
                    n = 0;
                }
                for (int j = n; j < length; ++j) {
                    try {
                        final double n2 = doubleArray[j];
                        if (Double.isNaN(n2)) {
                            nfDataSeries.dataset.addNull();
                        }
                        else {
                            mapValue = nfDataSeries.YAxis.mapValue(n2, mapValue);
                            nfDataSeries.dataset.addPoint(mapValue.y);
                        }
                    }
                    catch (Exception ex) {
                        nfDataSeries.dataset.addNull();
                    }
                }
            }
        }
    }
    
    private Rectangle a(final Vector vector, final Graphics graphics, Rectangle rectangle) {
        try {
            Rectangle rectangle2 = null;
            for (int i = 0; i < vector.size(); ++i) {
                final NFAxis nfAxis = vector.elementAt(i);
                if (nfAxis.autoscale) {
                    final double min = nfAxis.getMin();
                    final double max = nfAxis.getMax();
                    this.autoscaleAxis(nfAxis);
                    if (min != nfAxis.getMin() || max != nfAxis.getMax()) {
                        this.a();
                        rectangle = null;
                    }
                }
                final Rectangle rect = nfAxis.getRect(graphics);
                if (rectangle2 == null) {
                    rectangle2 = rect;
                }
                else {
                    rectangle2.add(rect);
                }
            }
            if (rectangle == null || rectangle2.width != rectangle.width || rectangle2.height != rectangle.height) {
                return rectangle2;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    protected void drawGraph(final Graphics graphics, final Rectangle workRect) {
        if (super.background == null) {
            this.labelBackgroundColor = super.a.getBackground();
        }
        else {
            this.labelBackgroundColor = super.background.getColor();
        }
        boolean b = false;
        if (super.layoutChanged || this.workRect == null || this.workRect.x != workRect.x || this.workRect.y != workRect.y || this.workRect.width != workRect.width || this.workRect.height != workRect.height) {
            this.a(graphics, this.workRect = workRect);
            this.slotsChanged();
            this.a();
            b = true;
        }
        try {
            if (this.p > 0) {
                if (this.p > this.maxLookAhead) {
                    this.p = this.maxLookAhead;
                }
                for (int i = 0; i < this.p; ++i) {
                    this.processSlot();
                    ++this.i;
                }
            }
        }
        catch (Exception ex) {
            this.a("Preload failed: " + ex);
        }
        finally {
            this.p = 0;
        }
        final Rectangle e = (super.leftAxes.size() == 0) ? null : this.getRects(super.leftAxes, graphics);
        final Rectangle f = (super.rightAxes.size() == 0) ? null : this.getRects(super.rightAxes, graphics);
        final Rectangle a = this.a(super.leftAxes, graphics, this.e);
        final Rectangle a2 = this.a(super.rightAxes, graphics, this.f);
        if (a != null) {
            this.e = a;
            super.layoutChanged = true;
        }
        if (a2 != null) {
            this.f = a2;
            super.layoutChanged = true;
        }
        if (this.e != null && e != null && !e.equals(this.e)) {
            this.e = e;
            super.layoutChanged = true;
        }
        if (this.f != null && f != null && !f.equals(this.f)) {
            this.f = f;
            super.layoutChanged = true;
        }
        this.d(graphics, b);
    }
    
    private void d(final Graphics graphics, final boolean b) {
        this.checkScroll((NFStripAxis)super.bottomAxis);
        this.checkScroll((NFStripAxis)super.topAxis);
        this.drawAxes(graphics, b);
        this.a(graphics);
        this.a(super.clipRect, b);
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.drawAllNoteSets(super.notesets, graphics, super.clipRect);
        }
        if (super.legend != null && super.legend.enabled() && super.legend.isInside()) {
            super.legend.draw(super.a, super.clipRect, super.clipRect.getClipRect(), 10, super.colorTable);
        }
    }
    
    private void a(final Graphics graphics) {
        for (int i = 0; i < super.grids.size(); ++i) {
            ((NFGrid)super.grids.elementAt(i)).display(graphics);
        }
    }
    
    private void a(final Graphics graphics, final Rectangle rectangle) {
        ((NFStripAxis)super.bottomAxis).setupAxisTics();
        if (super.topAxis != null && this.t) {
            ((NFStripAxis)super.topAxis).setupAxisTics();
        }
        this.g = super.bottomAxis.getTicLength();
        super.bottomAxis.showAxisLine(true);
        super.bottomAxis.showTics(true);
        if (this.t) {
            super.topAxis.showAxisLine(true);
            super.topAxis.showTics(true);
        }
        this.setAxes(graphics, rectangle);
        this.c = null;
        this.d = null;
    }
    
    protected void setAxes(final Graphics graphics, final Rectangle rectangle) {
        super.depth3D = 0;
        super.grid3D = 0;
        super.line3D = 0;
        final Vector<NFAxis> vector = new Vector<NFAxis>();
        vector.addElement(super.bottomAxis);
        final Vector<NFAxis> vector2 = new Vector<NFAxis>();
        if (this.t) {
            vector2.addElement(super.topAxis);
        }
        super.setAxes(graphics, rectangle, vector, vector2, super.leftAxes, super.rightAxes);
    }
    
    protected Rectangle getRects(final Vector vector, final Graphics graphics) {
        Rectangle rectangle = null;
        for (int i = 0; i < vector.size(); ++i) {
            final Rectangle rect = vector.elementAt(i).getRect(graphics);
            if (rectangle == null) {
                rectangle = rect;
            }
            else {
                rectangle.add(rect);
            }
        }
        return rectangle;
    }
    
    protected void processSlot() {
        ((NFStripAxis)super.bottomAxis).appendNextAxisLabel();
        if (this.t) {
            ((NFStripAxis)super.topAxis).appendNextAxisLabel();
        }
        this.appendNextSlot();
    }
    
    protected void checkScroll(final NFStripAxis nfStripAxis) {
        if (!nfStripAxis.isSliderOn()) {
            return;
        }
        if (this.initialFill == 1) {
            return;
        }
        if (this.i <= this.displayslots || this.i > this.nslots) {
            return;
        }
        final double n = nfStripAxis.getMax() - (this.i - 2);
        if (n >= 0.0 && n < 1.0) {
            nfStripAxis.setMinMax(this.i - this.displayslots, this.i - 1);
            this.slotsChanged();
        }
    }
    
    protected void drawGraphLite(final Graphics graphics) {
        if (super.axisScrollUpdate) {
            this.displayAxes(graphics);
            super.axisScrollUpdate = false;
            return;
        }
        this.processSlot();
        ++this.i;
        final Rectangle e = (super.leftAxes.size() == 0) ? null : this.getRects(super.leftAxes, graphics);
        final Rectangle f = (super.rightAxes.size() == 0) ? null : this.getRects(super.rightAxes, graphics);
        final Rectangle a = this.a(super.leftAxes, graphics, this.e);
        final Rectangle a2 = this.a(super.rightAxes, graphics, this.f);
        if (a != null) {
            this.e = a;
            super.layoutChanged = true;
            this.update(graphics);
            return;
        }
        if (a2 != null) {
            this.f = a2;
            super.layoutChanged = true;
            this.update(graphics);
            return;
        }
        if (this.e != null && e != null && !e.equals(this.e)) {
            this.e = e;
            super.layoutChanged = true;
            this.update(graphics);
            return;
        }
        if (this.f != null && f != null && !f.equals(this.f)) {
            this.f = f;
            super.layoutChanged = true;
            this.update(graphics);
            return;
        }
        this.d(graphics, false);
    }
    
    public void drawAxes(final Graphics graphics, final boolean b) {
        if (this.c == null) {
            this.c = super.bottomAxis.getRect(graphics);
        }
        if (this.d == null && this.t) {
            this.d = super.topAxis.getRect(graphics);
        }
        graphics.setColor(this.labelBackgroundColor);
        if (super.bottomAxis != null && this.c != null) {
            graphics.fillRect(this.c.x - 5, this.c.y + 1 + this.g, this.c.width + 10, this.c.height - this.g);
        }
        if (this.t && super.topAxis != null && this.d != null) {
            graphics.fillRect(this.d.x - 5, this.d.y, this.d.width + 10, this.d.height - this.g);
        }
        if (super.leftAxes.size() > 0 && this.e != null) {
            graphics.fillRect(this.e.x, this.e.y, this.e.width, this.e.height);
        }
        if (super.rightAxes.size() > 0 && this.f != null) {
            graphics.fillRect(this.f.x, this.f.y, this.f.width, this.f.height);
        }
        final NFStripAxis nfStripAxis = (NFStripAxis)super.bottomAxis;
        if (b || nfStripAxis.changed || (nfStripAxis.axisMode == 1 && (this.initialFill == 1 || this.i >= this.displayslots))) {
            graphics.fillRect(this.c.x, this.c.y + 1, this.c.width + 1, this.g);
            nfStripAxis.setupAxisTics();
            nfStripAxis.changed = false;
        }
        final NFStripAxis nfStripAxis2 = (NFStripAxis)super.topAxis;
        if (this.t && (b || nfStripAxis2.changed || (nfStripAxis2.axisMode == 1 && (this.initialFill == 1 || this.i >= this.displayslots)))) {
            graphics.fillRect(this.d.x, this.d.y + this.d.height - this.g - 1, this.d.width + 1, this.g);
            nfStripAxis2.setupAxisTics();
            nfStripAxis2.changed = false;
        }
        this.displayAxes(graphics);
        if (super.bottomAxis != null) {
            this.c = super.bottomAxis.getRect(graphics, this.c);
        }
        if (this.t && super.topAxis != null) {
            this.d = super.topAxis.getRect(graphics, this.d);
        }
    }
    
    public void displayAxes(final Graphics graphics) {
        for (int i = 0; i < super.leftAxes.size(); ++i) {
            ((NFAxis)super.leftAxes.elementAt(i)).display(graphics);
        }
        for (int j = 0; j < super.rightAxes.size(); ++j) {
            ((NFAxis)super.rightAxes.elementAt(j)).display(graphics);
        }
        super.bottomAxis.display(graphics);
        if (this.t) {
            super.topAxis.display(graphics);
        }
    }
    
    protected void appendNewData(final int n, final double n2) throws Exception {
        try {
            ((NFStripChartInfo)super.dataSeries.elementAt(n).info).rawdata.addPoint(n2);
        }
        catch (Exception ex) {
            throw new Exception("NFStripChart: appendNewData failed - DataSet probably not created " + ex);
        }
    }
    
    protected void appendNextSlot() {
        final int size = super.dataSeries.size();
        Point mapValue = new Point(0, 0);
        if (super.dwellOn && (this.initialFill == 1 || this.i >= this.nslots)) {
            this.b();
        }
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            final NFStripChartInfo nfStripChartInfo = (NFStripChartInfo)nfDataSeries.info;
            double shift;
            try {
                shift = nfStripChartInfo.rawdata.shift();
                if (Double.isNaN(shift)) {
                    nfDataSeries.dataset.addNull();
                }
                else {
                    mapValue = nfDataSeries.YAxis.mapValue(shift, mapValue);
                    nfDataSeries.dataset.addPoint(mapValue.y);
                }
                nfStripChartInfo.history.addPoint(shift);
            }
            catch (Exception ex) {
                nfDataSeries.dataset.addNull();
                nfStripChartInfo.history.addPoint(Double.NaN);
                shift = Double.NaN;
            }
            if (super.dwellOn) {
                if (this.barStyle == 3 && nfDataSeries.sym != null && nfDataSeries.sym.type == 7) {
                    if (shift > 0.0) {
                        n2 = (shift += n2);
                    }
                    else {
                        n = (shift += n);
                    }
                }
                this.appendNextActiveSlot(nfDataSeries, i, this.i, shift);
            }
        }
    }
    
    protected void appendNextActiveSlot(final NFDataSeries nfDataSeries, final int n, final int n2, final double n3) {
        if (!super.dwellOn) {
            return;
        }
        NFActiveLabel nfActiveLabel;
        try {
            nfActiveLabel = nfDataSeries.activeLabels.elementAt(0);
            nfDataSeries.activeLabels.removeElementAt(0);
        }
        catch (Exception ex2) {
            String string;
            try {
                string = "(" + ((NFStripAxis)super.bottomAxis).genXLabel(n2) + "," + super.leftAxis.getLabel(n3) + ")";
            }
            catch (Exception ex) {
                this.a("failed appending new slot value = " + n3);
                ex.printStackTrace();
                string = "ERROR";
            }
            nfActiveLabel = new NFActiveLabel(string, null, null);
        }
        if (nfActiveLabel == null) {
            return;
        }
        NFActiveLabel nfActiveLabel2;
        if (this.initialFill == 1 || this.i >= this.nslots) {
            nfActiveLabel2 = this.o[n][this.nslots - 1];
        }
        else {
            nfActiveLabel2 = this.o[n][this.i];
        }
        nfActiveLabel2.setLabel(nfActiveLabel.label);
        nfActiveLabel2.setURL(nfActiveLabel.url);
        nfActiveLabel2.setTarget(nfActiveLabel.target);
    }
    
    private void b() {
        final int size = super.dataSeries.size();
        final int nslots = this.nslots;
        final int[] integerArray = this.h.getIntegerArray();
        if (!super.dwellOn) {
            return;
        }
        int n = 0;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.sym != null) {
                int n2;
                int n3;
                if (nfDataSeries.sym.type != 7 || this.barStyle == 3) {
                    n2 = -(nfDataSeries.sym.size / 2);
                    n3 = nfDataSeries.sym.size;
                }
                else {
                    n2 = (int)this.l + n * this.m;
                    n3 = this.m;
                    ++n;
                }
                for (int j = 1; j < nslots; ++j) {
                    final NFActiveLabel nfActiveLabel = this.o[i][j];
                    final NFActiveLabel nfActiveLabel2 = this.o[i][j - 1];
                    nfActiveLabel2.setLabel(nfActiveLabel.label);
                    nfActiveLabel2.setURL(nfActiveLabel.url);
                    nfActiveLabel2.setTarget(nfActiveLabel.target);
                    final int n4 = integerArray[j - 1] + this.workRect.x + n2;
                    final int ymin = nfActiveLabel.ymin;
                    final int n5 = nfActiveLabel.xmax - nfActiveLabel.xmin;
                    nfActiveLabel2.setBounds(n4, ymin, n3, nfActiveLabel.ymax - nfActiveLabel.ymin);
                }
            }
        }
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        NFStripAxis.defineAxis(super.param, "Top", "X");
        NFStripAxis.defineAxis(super.param, "Bottom", "X");
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("LEFT", new Integer(0));
        hashtable.put("RIGHT", new Integer(1));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineNumber("NumSlots", new Integer(10)));
        vector.addElement(super.param.defineSymbol("InitialFill", hashtable, new Integer(1)));
        vector.addElement(super.param.defineNumber("MaxLookAhead", new Integer(100)));
        vector.addElement(super.param.defineString("undef", new String("-")));
        super.param.defineTuple("StripLayout", vector);
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineString("DataSetName"));
        vector2.addElement(super.param.defineColor("DataSetColor", null));
        NFStripChart.r = super.param.defineGraphSymbol("DataSet", vector2);
        NFStripChart.s = super.param.defineLine("DataSet", vector2);
        vector2.addElement(super.param.defineColor("DataSetFillColor", null));
        super.param.defineVector("DataSets", super.param.defineTuple("DataSet", vector2));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("STACK", new Integer(3));
        hashtable2.put("GROUP", new Integer(1));
        super.param.defineSymbol("BarStyle", hashtable2, new Integer(1));
        for (int i = 1; i <= super.MaxDataSets; ++i) {
            super.param.defineVector("AppendDataSet" + i, super.param.defineDate("YValue"));
        }
        for (int j = 1; j <= super.MaxDataSets; ++j) {
            super.param.defineVector("DataSet" + j, super.param.defineDate("YValue"));
        }
        this.defineDataAxisParams(super.param, "DataAxis");
        this.defineLineSymbol(super.param);
        this.defineLineStyle(super.param);
        NFPatternFill.definePatternFillParam(super.param, "LineFillPattern");
        this.defineLineValueLabel(super.param);
    }
    
    protected synchronized void loadParams() throws Exception {
        int displayslots = this.displayslots;
        final int nslots = this.nslots;
        if (super.param.changed("StripLayout")) {
            final Vector vector = (Vector)super.param.get("StripLayout");
            displayslots = NFUtil.getNumber(vector, 0, NFStripChart.DISPLAY_SLOTS_DEFAULT);
            this.initialFill = NFUtil.getNumber(vector, 1, NFStripChart.INITIAL_FILL_DEFAULT);
            this.maxLookAhead = NFUtil.getNumber(vector, 2, NFStripChart.MAX_LOOK_AHEAD_DEFAULT);
            this.undefString = NFUtil.getString(vector, 3, NFStripChart.UNDEF_STRING_DEFAULT);
        }
        super.bottomAxis = this.loadStripAxisParams("Bottom", super.bottomAxis);
        super.topAxis = this.loadStripAxisParams("Top", super.topAxis);
        int nslots2;
        if (super.bottomAxis.isSliderOn()) {
            final double[] scrollLimits = super.bottomAxis.getScrollLimits();
            nslots2 = (int)(scrollLimits[1] - scrollLimits[0] + 1.0);
        }
        else if (super.topAxis.isSliderOn()) {
            final double[] scrollLimits2 = super.topAxis.getScrollLimits();
            nslots2 = (int)(scrollLimits2[1] - scrollLimits2[0] + 1.0);
        }
        else {
            nslots2 = displayslots;
        }
        if (nslots2 != this.nslots || displayslots != this.displayslots) {
            this.nslots = nslots2;
            this.displayslots = displayslots;
            super.layoutChanged = true;
            if (this.initialFill == 0) {
                super.bottomAxis.setMinMax(0.0, this.displayslots - 1);
                super.topAxis.setMinMax(0.0, this.displayslots - 1);
            }
            else {
                super.bottomAxis.setMinMax(this.nslots - this.displayslots, this.nslots - 1);
                super.topAxis.setMinMax(this.nslots - this.displayslots, this.nslots - 1);
            }
            this.slotsChanged();
        }
        ((NFStripAxis)super.bottomAxis).setStripInfo(this.initialFill, this.nslots, this.displayslots, this.undefString);
        ((NFStripAxis)super.topAxis).setStripInfo(this.initialFill, this.nslots, this.displayslots, this.undefString);
        if (super.bottomAxis.isSliderOn() && !super.topAxis.isSliderOn()) {
            ((NFStripAxis)super.bottomAxis).setPartner((NFStripAxis)super.topAxis);
        }
        if (super.topAxis.isSliderOn() && !super.bottomAxis.isSliderOn()) {
            ((NFStripAxis)super.topAxis).setPartner((NFStripAxis)super.bottomAxis);
        }
        super.loadParams();
        this.loadDataSetParams(super.param, 2, "DataSets", "DataAxis", super.XValuesAxis, super.YValuesAxis);
        this.loadPatternFill(super.param, 2, "LineFillPattern");
        this.loadLineSymbol(super.param);
        this.loadLineStyle(super.param);
        this.loadLineValueLabel(super.param);
        if (super.param.changed("BarStyle")) {
            this.c();
        }
        if (((NFStripAxis)super.bottomAxis).changed) {
            ((NFStripAxis)super.bottomAxis).initLabels();
        }
        if (((NFStripAxis)super.topAxis).changed) {
            ((NFStripAxis)super.topAxis).initLabels();
        }
        for (int size = super.dataSeries.size(), i = 1; i <= size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i - 1);
            final String string = "ActiveLabels" + i;
            if (super.param.changed(string)) {
                final Vector loadAllParams = NFActiveLabel.loadAllParams(super.param, string);
                for (int size2 = loadAllParams.size(), j = 0; j < size2; ++j) {
                    nfDataSeries.activeLabels.addElement(loadAllParams.elementAt(j));
                }
            }
            final String string2 = "DataSet" + i;
            if (super.param.changed(string2)) {
                final Vector vector2 = (Vector)super.param.get(string2);
                if (vector2 == null) {
                    continue;
                }
                if (vector2.size() == 0) {
                    continue;
                }
                final Enumeration<Object> elements = vector2.elements();
                while (elements.hasMoreElements()) {
                    this.appendNewData(i - 1, nfDataSeries.XAxis.getValue(elements.nextElement()));
                }
                if (vector2.size() > this.p) {
                    super.layoutChanged = true;
                    this.p = vector2.size();
                }
            }
            final String string3 = "AppendDataSet" + i;
            if (super.param.changed(string3)) {
                super.incrementalUpdate = true;
                final Vector vector3 = (Vector)super.param.get(string3);
                if (vector3 != null) {
                    if (vector3.size() != 0) {
                        final Enumeration<Object> elements2 = vector3.elements();
                        while (elements2.hasMoreElements()) {
                            this.appendNewData(i - 1, nfDataSeries.XAxis.getValue(elements2.nextElement()));
                        }
                    }
                }
            }
        }
        if (super.notesets != null && super.notesets.size() > 0) {
            NFNoteSet.setAllMapComponent(this, super.notesets);
            for (int size3 = super.notesets.size(), k = 0; k < size3; ++k) {
                ((NFNoteSet)super.notesets.elementAt(k)).setAxisMap(this);
            }
        }
        if (super.legend != null) {
            super.legend.setAxisMap(this);
        }
    }
    
    protected NFAxis loadStripAxisParams(final String s, final NFAxis nfAxis) throws Exception {
        final NFAxis loadAxis = NFStripAxis.loadAxis(super.a, super.param, s, nfAxis, super.dwell);
        if (loadAxis == null) {
            return nfAxis;
        }
        if (s.equals("Top") && loadAxis.getShowAxis()) {
            this.t = true;
        }
        super.layoutChanged = true;
        if (loadAxis != nfAxis) {
            super.axes.addElement(loadAxis);
        }
        return loadAxis;
    }
    
    private void c() {
        try {
            if (this.k == 0) {
                return;
            }
            this.barStyle = NFUtil.getNumber(super.param.get("BarStyle"), this.BAR_STYLE_DEFAULT);
            if (this.barStyle == 1) {
                this.m = this.n / this.k;
                this.l = -(this.m * this.k) / 2.0;
                this.l += this.m / 2.0;
            }
            else {
                if (this.barStyle != 3) {
                    return;
                }
                this.m = this.n;
            }
            final Enumeration<NFDataSeries> elements = super.dataSeries.elements();
            while (elements.hasMoreElements()) {
                final NFDataSeries nfDataSeries = elements.nextElement();
                if (nfDataSeries.sym != null && nfDataSeries.sym.type == 7) {
                    nfDataSeries.sym.size = this.m;
                }
            }
        }
        catch (Exception ex) {
            this.a("failed in loadBarStyleParams " + ex);
        }
    }
    
    private int d() {
        Enumeration elements;
        int size;
        NFDataSeries nfDataSeries;
        for (elements = super.dataSeries.elements(), size = 0; elements.hasMoreElements() && size <= 0; size = nfDataSeries.sym.size) {
            nfDataSeries = elements.nextElement();
            if (nfDataSeries.sym != null && nfDataSeries.sym.type == 7) {}
        }
        return size;
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfAxis == super.bottomAxis || nfAxis == super.topAxis) {
            return array;
        }
        final double[] minMax = ((NFStripChartInfo)nfDataSeries.info).history.getMinMax(1);
        if (minMax[0] < array[0]) {
            array[0] = minMax[0];
        }
        if (minMax[1] > array[1]) {
            array[1] = minMax[1];
        }
        return array;
    }
    
    protected void updateGraph() {
        this.processSlot();
        ++this.i;
        super.layoutChanged = true;
    }
    
    public NFDataSeries getDataSeries(final NFActiveLabel nfActiveLabel) {
        for (int i = 0; i < super.dataSeries.size(); ++i) {
            for (int j = 0; j < this.nslots; ++j) {
                if (this.o[i][j] == nfActiveLabel) {
                    return (NFDataSeries)super.dataSeries.elementAt(i);
                }
            }
        }
        return null;
    }
    
    public NFActiveLabel[] getDataSeriesActiveLabels(final NFDataSeries nfDataSeries) {
        return this.getDataSeriesActiveLabels(this.getDataSeriesIndex(nfDataSeries));
    }
    
    public NFActiveLabel[] getDataSeriesActiveLabels(int n) {
        if (--n < 0 || n >= this.o.length) {
            return null;
        }
        return this.o[n];
    }
    
    static {
        NFStripChart.UNDEF_STRING_DEFAULT = "-";
        NFStripChart.INITIAL_FILL_DEFAULT = 0;
        NFStripChart.DISPLAY_SLOTS_DEFAULT = 11;
        NFStripChart.MAX_LOOK_AHEAD_DEFAULT = 100;
    }
}
