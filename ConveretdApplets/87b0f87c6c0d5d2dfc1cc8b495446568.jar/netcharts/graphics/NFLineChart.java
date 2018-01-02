// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFDataSet;
import java.util.Hashtable;
import java.awt.Color;
import netcharts.util.NFParamDef;
import java.util.Vector;
import java.awt.Point;
import netcharts.util.NFUtil;
import netcharts.util.NFColor;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Polygon;

public class NFLineChart extends NFDataChart
{
    public static final int STACK = 1;
    public static final int PERCENT = 2;
    public static final int ROWS = 3;
    public static final int STACKTOTAL = 1;
    public static final int STACKITEM = 2;
    public static final int STACKPERCENT = 3;
    private static final boolean a = false;
    private int b;
    private int c;
    private int d;
    private int e;
    private Polygon f;
    
    public NFLineChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.b = 1;
        this.c = this.b;
        this.d = 1;
        this.e = this.d;
        this.f = null;
        this.initGraph(applet);
        this.initLineChart();
        this.reshape(n, n2, n3, n4);
    }
    
    public NFLineChart(final Applet applet) {
        this.b = 1;
        this.c = this.b;
        this.d = 1;
        this.e = this.d;
        this.f = null;
        this.initGraph(applet);
        this.initLineChart();
    }
    
    protected void initLineChart() {
        super.initChart();
        super.line3D = -1;
        (this.f = new Polygon()).addPoint(0, 0);
        this.f.addPoint(0, 0);
        this.f.addPoint(0, 0);
        this.f.addPoint(0, 0);
        this.f.addPoint(0, 0);
    }
    
    private boolean a(final NFAxis nfAxis) {
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.YAxis != null && nfDataSeries.YAxis == nfAxis) {
                return true;
            }
        }
        return false;
    }
    
    protected double[] getMinMax(final NFAxis nfAxis) {
        final double[] minMax = super.getMinMax(nfAxis);
        if (this.c == 2 && this.a(nfAxis)) {
            if (minMax[0] > 0.0) {
                minMax[0] = 0.0;
            }
            if (minMax[1] < 100.0) {
                minMax[1] = 100.0;
            }
            return minMax;
        }
        if (this.c != 1) {
            return minMax;
        }
        final int a = this.a();
        if (a < 1) {
            return minMax;
        }
        final double[] array = new double[a];
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 2) {
                if (nfDataSeries.YAxis == nfAxis) {
                    for (int n = 0; n < nfDataSeries.dataset.size() && n < a; ++n) {
                        final double nth = nfDataSeries.dataset.getNth(n, 2);
                        if (!Double.isNaN(nth)) {
                            if (nth > 0.0) {
                                final double[] array2 = array;
                                final int n2 = n;
                                array2[n2] += nth;
                            }
                        }
                    }
                }
            }
        }
        for (int j = 0; j < a; ++j) {
            if (minMax[0] < 0.0) {
                minMax[0] = 0.0;
            }
            if (array[j] > minMax[1]) {
                minMax[1] = array[j];
            }
        }
        return minMax;
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfDataSeries.type != 2) {
            return super.getMinMax(nfDataSeries, nfAxis, array);
        }
        if (nfDataSeries.XAxis == nfAxis) {
            final int size = nfDataSeries.dataset.size();
            if (0.0 < array[0]) {
                array[0] = 0.0;
            }
            if (size - 1 > array[1]) {
                array[1] = size - 1;
            }
            return array;
        }
        if (nfDataSeries.YAxis == nfAxis) {
            switch (this.c) {
                case 2: {
                    if (array[0] > 0.0) {
                        array[0] = 0.0;
                    }
                    if (array[1] < 100.0) {
                        array[1] = 100.0;
                        break;
                    }
                    break;
                }
                case 1: {
                    final double[] minMax = nfDataSeries.dataset.getMinMax(2);
                    if (minMax[0] < array[0]) {
                        array[0] = minMax[0];
                    }
                    if (minMax[1] > array[1]) {
                        array[1] = minMax[1];
                        break;
                    }
                    break;
                }
                case 3: {
                    return super.getMinMax(nfDataSeries, nfAxis, array);
                }
            }
            return array;
        }
        return array;
    }
    
    public void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
        this.b();
    }
    
    private int a() {
        return this.a(null, null);
    }
    
    private int a(final NFAxis nfAxis, final NFAxis nfAxis2) {
        int size = 0;
        for (int size2 = super.dataSeries.size(), i = 0; i < size2; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 2) {
                if (nfAxis == null || nfDataSeries.XAxis == nfAxis) {
                    if (nfAxis2 == null || nfDataSeries.YAxis == nfAxis2) {
                        if (nfDataSeries.dataset.size() > size) {
                            size = nfDataSeries.dataset.size();
                        }
                    }
                }
            }
        }
        return size;
    }
    
    private void b() {
        final int size = super.dataSeries.size();
        final int a = this.a();
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 2) {
                this.a(nfDataSeries, a);
            }
        }
    }
    
    private void a(final NFDataSeries nfDataSeries, final int n) {
        if (n < 1) {
            return;
        }
        double min;
        if (nfDataSeries.XAxis.isSliderOn()) {
            final double[] scrollLimits = nfDataSeries.XAxis.getScrollLimits();
            min = scrollLimits[0];
            final double n2 = scrollLimits[1];
        }
        else {
            min = nfDataSeries.XAxis.getMin();
            nfDataSeries.XAxis.getMax();
        }
        if (n == 1 && nfDataSeries.dataset.size() > 0) {
            nfDataSeries.dataset.getPoint(0).x = min;
            return;
        }
        for (int size = nfDataSeries.dataset.size(), i = 0; i < size; ++i) {
            nfDataSeries.dataset.getPoint(i).x = i;
        }
    }
    
    protected void drawData(final Graphics graphics) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        switch (this.c) {
            case 3: {
                this.drawRowData(graphics, size);
                break;
            }
            case 1:
            case 2: {
                this.drawStackData(graphics, size);
                break;
            }
        }
    }
    
    protected int getLineDepth() {
        if (super.line3D >= 0) {
            return super.line3D;
        }
        if (this.c == 1 || this.c == 2) {
            if (super.grid3D >= 0) {
                return super.grid3D;
            }
            return this.get3DDepth();
        }
        else {
            final int dataSetCount = this.getDataSetCount(2);
            if (dataSetCount < 1) {
                return 0;
            }
            if (super.grid3D >= 0) {
                return super.grid3D / dataSetCount;
            }
            return this.get3DDepth() / dataSetCount;
        }
    }
    
    protected int get3DDepth(final boolean b) {
        if (this.c == 1 || this.c == 2) {
            if (super.line3D > 0) {
                return super.line3D;
            }
            if (super.grid3D > 0) {
                return super.grid3D;
            }
            return 0;
        }
        else {
            final int dataSetCount = this.getDataSetCount(2);
            if (super.line3D > 0) {
                return dataSetCount * super.line3D;
            }
            if (super.grid3D > 0) {
                return super.grid3D;
            }
            return 0;
        }
    }
    
    protected void drawRowData(final Graphics graphics, final int n) {
        final int dataSetCount = this.getDataSetCount(2);
        int n2 = 0;
        for (int i = n - 1; i >= 0; --i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 2) {
                ++n2;
                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                this.a(clippedGraphics, nfDataSeries, dataSetCount - n2);
                clippedGraphics.dispose();
            }
        }
    }
    
    protected void drawStackData(final Graphics graphics, final int n) {
        final int a = this.a();
        if (a < 1) {
            return;
        }
        final double[] c = this.c();
        double[] array = null;
        final boolean[] array2 = new boolean[a];
        for (int i = 0; i < a; ++i) {
            array2[i] = true;
        }
        if (this.c == 2) {
            array = new double[a];
            System.arraycopy(c, 0, array, 0, a);
            for (int j = 0; j < a; ++j) {
                c[j] = 100.0;
            }
        }
        final int lineWidth = this.getLineWidth();
        for (int k = n - 1; k >= 0; --k) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(k);
            if (nfDataSeries.type == 2) {
                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                final double min = nfDataSeries.XAxis.getMin();
                final double min2 = nfDataSeries.YAxis.getMin();
                final double max = nfDataSeries.XAxis.getMax();
                final double max2 = nfDataSeries.YAxis.getMax();
                double nth = nfDataSeries.dataset.getNth(0, 1);
                double nth2 = nfDataSeries.dataset.getNth(0, 2);
                int l;
                for (l = 1; l < nfDataSeries.dataset.size(); ++l) {
                    final double nth3 = nfDataSeries.dataset.getNth(l, 1);
                    final double nth4 = nfDataSeries.dataset.getNth(l, 2);
                    if (!Double.isNaN(nth) && !Double.isNaN(nth2) && !Double.isNaN(nth3) && !Double.isNaN(nth4)) {
                        this.a(clippedGraphics, nfDataSeries, c, l, nth, nth2, nth3, nth4, min, min2, max, max2, lineWidth, array2, l == nfDataSeries.dataset.size() - 1);
                    }
                    if (!Double.isNaN(nth) && !Double.isNaN(nth2)) {
                        this.a(clippedGraphics, nfDataSeries, nth, nth2, c, array, l - 1, min, min2, max, max2);
                    }
                    nth = nth3;
                    nth2 = nth4;
                }
                if (!Double.isNaN(nth) && !Double.isNaN(nth2)) {
                    this.a(clippedGraphics, nfDataSeries, nth, nth2, c, array, l - 1, min, min2, max, max2);
                }
                clippedGraphics.dispose();
            }
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final double[] array, final int n, double n2, double n3, double n4, double n5, final double n6, final double n7, final double n8, final double n9, final int n10, final boolean[] array2, boolean b) {
        if (Double.isNaN(n3) || n3 < 0.0) {
            n3 = 0.0;
        }
        if (Double.isNaN(n5) || n5 < 0.0) {
            n5 = 0.0;
        }
        if (n4 <= n6 || n2 >= n8) {
            return;
        }
        if (array[n - 1] < n7 && array[n] < n7) {
            return;
        }
        n3 = array[n - 1];
        n5 = array[n];
        if (n2 < n6) {
            n3 += (n5 - n3) * (n6 - n2) / (n4 - n2);
            n2 = n6;
        }
        if (n4 > n8) {
            n5 -= (n5 - n3) * (n4 - n8) / (n4 - n2);
            n4 = n8;
            b = true;
        }
        final Point mapValue = nfDataSeries.XAxis.mapValue(n2);
        final Point mapValue2 = nfDataSeries.XAxis.mapValue(n4);
        final Point mapValue3 = nfDataSeries.YAxis.mapValue(n3);
        final Point mapValue4 = nfDataSeries.YAxis.mapValue(n5);
        if (array2[n] && n10 > 0) {
            this.f.xpoints[0] = mapValue.x;
            this.f.ypoints[0] = mapValue3.y;
            this.f.xpoints[1] = mapValue.x + n10;
            this.f.ypoints[1] = mapValue3.y - n10;
            this.f.xpoints[2] = mapValue2.x + n10;
            this.f.ypoints[2] = mapValue4.y - n10;
            this.f.xpoints[3] = mapValue2.x;
            this.f.ypoints[3] = mapValue4.y;
            this.f.xpoints[4] = mapValue.x;
            this.f.ypoints[4] = mapValue3.y;
            graphics.setColor(NFColor.brighter(nfDataSeries.c));
            if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.f, (nfDataSeries.line == null) ? nfDataSeries.c : nfDataSeries.line.getColor(), nfDataSeries.pattern);
            }
            else {
                graphics.fillPolygon(this.f);
            }
            array2[n] = false;
        }
        if (nfDataSeries.fillColor != null) {
            this.f.xpoints[0] = mapValue.x;
            this.f.ypoints[0] = nfDataSeries.YAxis.mapValue(n7).y;
            this.f.xpoints[1] = mapValue.x;
            this.f.ypoints[1] = mapValue3.y;
            this.f.xpoints[2] = mapValue2.x;
            this.f.ypoints[2] = mapValue4.y;
            this.f.xpoints[3] = mapValue2.x;
            this.f.ypoints[3] = nfDataSeries.YAxis.mapValue(n7).y;
            this.f.xpoints[4] = mapValue.x;
            this.f.ypoints[4] = nfDataSeries.YAxis.mapValue(n7).y;
            graphics.setColor(nfDataSeries.fillColor);
            if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                NF12GraphicsUtil.patternFillPolygon(graphics, this.f, (nfDataSeries.line == null) ? nfDataSeries.c : nfDataSeries.line.getColor(), nfDataSeries.pattern);
            }
            else {
                graphics.fillPolygon(this.f);
            }
            if (b && n10 > 0) {
                this.f.xpoints[0] = mapValue2.x;
                this.f.ypoints[0] = mapValue4.y;
                this.f.xpoints[1] = mapValue2.x + n10;
                this.f.ypoints[1] = mapValue4.y - n10;
                this.f.xpoints[2] = mapValue2.x + n10;
                this.f.ypoints[2] = nfDataSeries.YAxis.mapValue(n7).y - n10;
                this.f.xpoints[3] = mapValue2.x;
                this.f.ypoints[3] = nfDataSeries.YAxis.mapValue(n7).y;
                this.f.xpoints[4] = mapValue2.x;
                this.f.ypoints[4] = mapValue4.y;
                graphics.setColor(NFColor.darker(nfDataSeries.fillColor));
                if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                    NF12GraphicsUtil.patternFillPolygon(graphics, this.f, (nfDataSeries.line == null) ? nfDataSeries.c : nfDataSeries.line.getColor(), nfDataSeries.pattern);
                }
                else {
                    graphics.fillPolygon(this.f);
                }
                graphics.setColor(nfDataSeries.c);
                graphics.drawPolygon(this.f);
            }
        }
        if (nfDataSeries.line != null) {
            nfDataSeries.line.draw(graphics, mapValue.x, mapValue3.y, mapValue2.x, mapValue4.y);
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final double n, double n2, final double[] array, final double[] array2, final int n3, final double n4, final double n5, final double n6, final double n7) {
        if (Double.isNaN(n2) || n2 < 0.0) {
            n2 = 0.0;
        }
        if (n < n4 || n > n6 || array[n3] < n5 || array[n3] > n7) {
            this.setActiveLabel(nfDataSeries, n3, -1, -1, 0, 0, "");
            if (this.c == 2) {
                array[n3] -= 100.0 * n2 / array2[n3];
            }
            else {
                array[n3] -= n2;
            }
            if (Double.isNaN(array[n3])) {
                array[n3] = 0.0;
            }
            return;
        }
        String s = null;
        switch (this.e) {
            default: {
                s = nfDataSeries.YAxis.getLabel(array[n3]);
                break;
            }
            case 2: {
                s = nfDataSeries.YAxis.getLabel(n2);
                break;
            }
            case 3: {
                if (this.c == 2) {
                    s = nfDataSeries.YAxis.getLabel(100.0 * n2 / array2[n3]);
                    break;
                }
                s = nfDataSeries.YAxis.getLabel(n2);
                break;
            }
        }
        final int x = nfDataSeries.XAxis.mapValue(n).x;
        final int y = nfDataSeries.YAxis.mapValue(array[n3]).y;
        final int n8 = x;
        final int n9 = y;
        if (nfDataSeries.sym != null) {
            graphics.setColor((nfDataSeries.sym.getColor() == null) ? nfDataSeries.c : nfDataSeries.sym.getColor());
            nfDataSeries.sym.draw(graphics, x, y);
            this.setActiveLabel(nfDataSeries, n3, x - (super.dwellOffset + nfDataSeries.sym.size) / 2, y - (super.dwellOffset + nfDataSeries.sym.size) / 2, super.dwellOffset + nfDataSeries.sym.size, super.dwellOffset + nfDataSeries.sym.size, s, nfDataSeries.sym);
        }
        else {
            this.setActiveLabel(nfDataSeries, n3, x, y, super.dwellOffset, s);
        }
        if (nfDataSeries.valueLabelStyle != 0) {
            this.drawLineValueLabel(graphics, nfDataSeries, n8, n9, s);
        }
        if (this.c == 2) {
            array[n3] -= 100.0 * n2 / array2[n3];
        }
        else {
            array[n3] -= n2;
        }
        if (Double.isNaN(array[n3])) {
            array[n3] = 0.0;
        }
    }
    
    private double[] c() {
        final int a = this.a();
        if (a < 1) {
            return null;
        }
        final double[] array = new double[a];
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 2) {
                for (int n = 0; n < nfDataSeries.dataset.size() && n < a; ++n) {
                    final double nth = nfDataSeries.dataset.getNth(n, 2);
                    if (!Double.isNaN(nth)) {
                        if (nth >= 0.0) {
                            final double[] array2 = array;
                            final int n2 = n;
                            array2[n2] += nth;
                        }
                    }
                }
            }
        }
        return array;
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n) {
        this.drawPoints(graphics, nfDataSeries, 0, nfDataSeries.XAxis, nfDataSeries.YAxis, n);
    }
    
    protected void drawDataLite(final Graphics graphics) {
        this.drawData(graphics);
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("LineSetName"));
        vector.addElement(super.param.defineColor("LineSetSymColor", null));
        super.param.defineVector("LineSets", super.param.defineTuple("LineSetTuple", vector));
        final NFParamDef defineDate = super.param.defineDate("LineSetValue");
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("LineSet" + (i + 1), defineDate);
        }
        this.defineDataAxisParams(super.param, "LineAxis");
        this.defineLineSymbol(super.param);
        this.defineLineStyle(super.param);
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("STACK", new Integer(1));
        hashtable.put("PERCENT", new Integer(2));
        hashtable.put("ROWS", new Integer(3));
        super.param.defineSymbol("GraphType", hashtable, new Integer(1));
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("TOTAL", new Integer(1));
        hashtable2.put("ITEM", new Integer(2));
        hashtable2.put("PERCENT", new Integer(3));
        super.param.defineSymbol("StackLabel", hashtable2, new Integer(1));
        NFPatternFill.definePatternFillParam(super.param, "LineFillPattern");
        this.defineLineValueLabel(super.param);
    }
    
    protected synchronized void loadParams() throws Exception {
        super.loadParams();
        boolean loadDataSetParams = this.loadDataSetParams(super.param, 2, "LineSets", "LineAxis", super.bottomAxis, super.leftAxis);
        final boolean[] loadDataSets = this.loadDataSets(super.param, 2, "LineSet", loadDataSetParams);
        for (int i = 0; i < super.axes.size(); ++i) {
            final NFAxis nfAxis = super.axes.elementAt(i);
            final int a = this.a(nfAxis, null);
            final Vector ticLabels = nfAxis.getTicLabels();
            if (NFDataChart.isXAxis(nfAxis) && !nfAxis.isScaleSpecified() && (nfAxis.autoscale || (ticLabels != null && ticLabels.size() > 0)) && a != 0) {
                nfAxis.setMinMax(0.0, a - 1);
                nfAxis.setSpacing(new NFSpacing(0.0, a - 1.0, 1.0));
            }
        }
        this.loadActiveLabels(super.param, 2, "ActiveLabels", loadDataSets);
        this.loadPatternFill(super.param, 2, "LineFillPattern");
        if (loadDataSetParams || super.param.changed("LineSymbol")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadLineSymbol(super.param);
        }
        if (loadDataSetParams || super.param.changed("LineStyle")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadLineStyle(super.param);
        }
        if (super.param.changed("GraphType")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.c = NFUtil.getNumber(super.param.get("GraphType"), this.b);
        }
        if (super.param.changed("StackLabel")) {
            this.e = NFUtil.getNumber(super.param.get("StackLabel"), this.d);
        }
        this.loadLineValueLabel(super.param);
        for (int j = 0; j < loadDataSets.length; ++j) {
            if (loadDataSetParams || loadDataSets[j]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 2) {
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
        nfDataSeries.c = (Color)vector.elementAt(1);
        if (nfDataSeries.c == null) {
            nfDataSeries.c = this.defaultColor(n);
        }
        if (nfDataSeries.sym == null) {
            nfDataSeries.sym = new NFGraphSymbol();
        }
        if (nfDataSeries.line == null) {
            (nfDataSeries.line = new NFLine(null)).setColor(nfDataSeries.c);
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 2) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        nfDataSeries.dataset.addPoint(0.0, nfDataSeries.YAxis.getValue(o));
    }
}
