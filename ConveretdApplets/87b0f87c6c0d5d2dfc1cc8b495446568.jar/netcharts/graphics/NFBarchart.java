// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFParam;
import netcharts.util.NFColor;
import netcharts.util.NFUtil;
import netcharts.util.NFParamDef;
import java.util.Hashtable;
import netcharts.util.NFDataSet;
import java.awt.Point;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Polygon;
import java.util.Vector;

public class NFBarchart extends NFDataChart
{
    public static final int GROUP = 1;
    public static final int ROWS = 2;
    public static final int STACK = 3;
    public static final int STACKTOTAL = 1;
    public static final int STACKITEM = 2;
    public int BAR_FORMAT_DEFAULT;
    public int barFormat;
    public int BAR_LAYOUT_DEFAULT;
    public int barLayout;
    public int STACK_LABEL_DEFAULT;
    public int stackLabel;
    public int BAR_VALUE_LABEL_STYLE_DEFAULT;
    private NFLabel a;
    private static final boolean b = false;
    private static final boolean c = false;
    private Vector d;
    private double e;
    private double f;
    public double BAR_HEIGHT_FACTOR_DEFAULT;
    public double barHeightFactor;
    private Vector g;
    private Vector h;
    private NFLine i;
    private NFLine j;
    public boolean SHOW_ZAXIS_LABELS_DEFAULT;
    protected boolean showZAxisLabels;
    int k;
    int l;
    private Polygon m;
    private Polygon n;
    private Color[] o;
    private int[] p;
    
    public NFBarchart(final Applet applet) {
        this.BAR_FORMAT_DEFAULT = 1;
        this.barFormat = this.BAR_FORMAT_DEFAULT;
        this.BAR_LAYOUT_DEFAULT = 1;
        this.barLayout = this.BAR_LAYOUT_DEFAULT;
        this.STACK_LABEL_DEFAULT = 1;
        this.stackLabel = this.STACK_LABEL_DEFAULT;
        this.BAR_VALUE_LABEL_STYLE_DEFAULT = 0;
        this.a = null;
        this.d = null;
        this.e = 0.8;
        this.f = this.e;
        this.BAR_HEIGHT_FACTOR_DEFAULT = 0.75;
        this.barHeightFactor = this.BAR_HEIGHT_FACTOR_DEFAULT;
        this.g = new Vector();
        this.h = null;
        this.i = new NFLine(null);
        this.j = this.i;
        this.SHOW_ZAXIS_LABELS_DEFAULT = false;
        this.showZAxisLabels = this.SHOW_ZAXIS_LABELS_DEFAULT;
        this.k = 0;
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = new Color[2];
        this.p = new int[1];
        this.initGraph(applet);
        this.initBarchart();
    }
    
    public NFBarchart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.BAR_FORMAT_DEFAULT = 1;
        this.barFormat = this.BAR_FORMAT_DEFAULT;
        this.BAR_LAYOUT_DEFAULT = 1;
        this.barLayout = this.BAR_LAYOUT_DEFAULT;
        this.STACK_LABEL_DEFAULT = 1;
        this.stackLabel = this.STACK_LABEL_DEFAULT;
        this.BAR_VALUE_LABEL_STYLE_DEFAULT = 0;
        this.a = null;
        this.d = null;
        this.e = 0.8;
        this.f = this.e;
        this.BAR_HEIGHT_FACTOR_DEFAULT = 0.75;
        this.barHeightFactor = this.BAR_HEIGHT_FACTOR_DEFAULT;
        this.g = new Vector();
        this.h = null;
        this.i = new NFLine(null);
        this.j = this.i;
        this.SHOW_ZAXIS_LABELS_DEFAULT = false;
        this.showZAxisLabels = this.SHOW_ZAXIS_LABELS_DEFAULT;
        this.k = 0;
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = new Color[2];
        this.p = new int[1];
        this.initGraph(applet);
        this.initBarchart();
        this.reshape(n, n2, n3, n4);
    }
    
    protected void initBarchart() {
        super.initChart();
    }
    
    protected void setRightSlantAxis() {
        final int get3DDepth = this.get3DDepth();
        final int a = this.a();
        if (this.barFormat != 2 || a < 2) {
            super.setRightSlantAxis();
            return;
        }
        super.rightSlantAxis.setAxis(super.bottomAxis.getMaxCoord(), super.bottomAxis.mapValue(super.bottomAxis.getMax()).y, get3DDepth, -get3DDepth);
        final Vector<String> ticLabels = new Vector<String>();
        for (int i = 0; i < super.dataSeries.size(); ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 1) {
                ticLabels.addElement((nfDataSeries.name == null) ? "" : nfDataSeries.name);
            }
        }
        super.rightSlantAxis.setTicLabels(ticLabels);
        super.rightSlantAxis.setSpacing(new NFSpacing(0.0, a - 1.0, 1.0));
        super.rightSlantAxis.setMinMax(-0.5, a - 0.5);
        super.rightSlantAxis.showAxisAndLine(this.showZAxisLabels, this.showZAxisLabels);
    }
    
    protected void drawDataLite(final Graphics graphics) {
        this.drawData(graphics);
    }
    
    protected int get3DDepth(final boolean b) {
        if (super.depth3D < -1 || super.depth3D == 0) {
            super.depth3D = 0;
            return super.defaultDepth = 0;
        }
        if (!b) {
            return super.defaultDepth;
        }
        super.dataSeries.size();
        final int a = this.a();
        if (a == 0) {
            return super.defaultDepth = 0;
        }
        int depth3D;
        if (super.depth3D != -1) {
            depth3D = super.depth3D;
        }
        else {
            depth3D = this.getBarSize() / 3;
        }
        if (this.barFormat == 2) {
            depth3D *= a;
        }
        if (this.barFormat == 1) {
            depth3D /= a;
        }
        final Point mapValue = super.YValuesAxis.mapValue(super.YValuesAxis.getMax());
        final Point mapValue2 = super.YValuesAxis.mapValue(super.YValuesAxis.getMin());
        int n;
        if (this.barLayout == 1) {
            n = mapValue.y - mapValue2.y;
        }
        else {
            n = mapValue.x - mapValue2.x;
        }
        if (n < 0) {
            n = -n;
        }
        if (depth3D > n / 2) {
            depth3D = n / 2;
        }
        final Point mapValue3 = super.XValuesAxis.mapValue(super.XValuesAxis.getMax());
        final Point mapValue4 = super.XValuesAxis.mapValue(super.XValuesAxis.getMin());
        int n2;
        if (this.barLayout == 1) {
            n2 = mapValue3.x - mapValue4.x;
        }
        else {
            n2 = mapValue3.y - mapValue4.y;
        }
        if (n2 < 0) {
            n2 = -n2;
        }
        if (depth3D > n2 / 3) {
            depth3D = n2 / 3;
        }
        if (super.depth3D == -1 && b) {
            depth3D *= (int)0.75;
        }
        return super.defaultDepth = depth3D;
    }
    
    public void reset() {
        this.setDefaultAxes();
    }
    
    protected void setDefault3DGrid() {
    }
    
    private int a() {
        final int size = super.dataSeries.size();
        int n = 0;
        for (int i = 0; i < size; ++i) {
            if (((NFDataSeries)super.dataSeries.elementAt(i)).type == 1) {
                ++n;
            }
        }
        return n;
    }
    
    protected void drawBars(final Graphics graphics) {
        final int size = super.dataSeries.size();
        int n = 0;
        int n2 = -1;
        for (int i = 0; i < size; ++i) {
            if (((NFDataSeries)super.dataSeries.elementAt(i)).type == 1) {
                if (n2 < 0) {
                    n2 = i;
                }
                ++n;
            }
        }
        final int maxBars = this.getMaxBars(null);
        if (n == 0 || maxBars == 0) {
            return;
        }
        final int barSize = this.getBarSize();
        final int get3DDepth = this.get3DDepth();
        for (int j = 0; j < size; ++j) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
            if (nfDataSeries.type == 1) {
                for (int size2 = nfDataSeries.dataset.size(), k = 0; k < size2; ++k) {
                    this.setActiveLabel(nfDataSeries, k, -1, -1, 0, 0, "");
                }
            }
        }
        if (n == 1) {
            this.a(graphics, super.dataSeries.elementAt(n2), maxBars, barSize, get3DDepth);
            return;
        }
        switch (this.barFormat) {
            case 1: {
                this.a(graphics, n, maxBars, barSize, get3DDepth);
                break;
            }
            case 3: {
                this.b(graphics, n, maxBars, barSize, get3DDepth);
                break;
            }
            default: {
                this.c(graphics, n, maxBars, barSize, get3DDepth);
                break;
            }
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, final double n2, final double n3, final Color color, int n4, int n5, int n6, int n7, final int n8, final int n9, final int n10, final int n11, final int n12) {
        if (n8 > 0 && nfDataSeries.sym.type == 9) {
            if (n6 > 0) {
                if (n4 > n11 || n4 + n6 < n9) {
                    return;
                }
                if (n4 < n9) {
                    n6 -= n9 - n4;
                    n4 = n9;
                }
                if (n4 + n6 > n11) {
                    n6 = n11 - n4;
                }
            }
            else {
                if (n4 < n9 || n4 + n6 > n11) {
                    return;
                }
                if (n4 > n11) {
                    n6 += n4 - n11;
                    n4 = n11;
                }
                if (n4 + n6 < n9) {
                    n6 += n9 - n4 + n6;
                }
            }
            if (n5 < n10) {
                n7 -= n10 - n5;
                n5 = n10;
            }
            if (n5 + n7 > n12) {
                n7 = n12 - n5;
            }
            if (n7 <= 0 && this.barLayout == 2) {
                return;
            }
        }
        if (this.barLayout == 1 && n6 < 1) {
            n6 = 1;
        }
        if (this.barLayout == 2 && n7 < 1) {
            n7 = 1;
        }
        if (nfDataSeries.sym.type == 13 || nfDataSeries.sym.type == 14) {
            this.a(graphics, nfDataSeries, n2, n4, n5, n6, n7, n8, n, n3);
            return;
        }
        graphics.setColor(color);
        nfDataSeries.sym.draw(graphics, n4, n5, n6, n7, n8, this.barLayout, true, true, nfDataSeries.pattern);
        final String label = nfDataSeries.YAxis.getLabel(n3);
        this.setActiveLabel(nfDataSeries, n, n4, n5, n6, n7, label, nfDataSeries.sym);
        if (nfDataSeries.valueLabelStyle != 0) {
            NFValueLabel.drawRectValueLabel((this.a == null) ? super.dwell.getLabel() : this.a, graphics, nfDataSeries, n4, n5, n6, n7, label, n8, n3, this.barLayout == 1);
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n, int barSize, final int n2) {
        final NFDataSet dataset = nfDataSeries.dataset;
        int minCoord = 0;
        int maxCoord = 0;
        int minCoord2 = 0;
        int maxCoord2 = 0;
        barSize = this.getBarSize(nfDataSeries.XAxis);
        final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
        final double min = nfDataSeries.XAxis.getMin();
        final double max = nfDataSeries.XAxis.getMax();
        if (n2 > 0 || nfDataSeries.valueLabelStyle != 0) {
            minCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMinCoord();
            maxCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMaxCoord();
            minCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMinCoord();
            maxCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMaxCoord();
        }
        final Point baseLine = this.getBaseLine(nfDataSeries.YAxis);
        int n3 = -1;
        int n4 = -1;
        for (int i = 0; i < n; ++i) {
            if (i < min - 0.5 || i > max + 0.5) {
                n4 = -1;
                n3 = -1;
            }
            else {
                final double nth = dataset.getNth(i);
                if (Double.isNaN(nth)) {
                    n4 = -1;
                    n3 = -1;
                }
                else {
                    final Point mapValue = nfDataSeries.YAxis.mapValue(nth);
                    final Point mapValue2 = nfDataSeries.XAxis.mapValue(i);
                    Color defaultColor = (nfDataSeries.sym != null && nfDataSeries.sym.getColor() != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
                    if (defaultColor == null) {
                        defaultColor = this.defaultColor(i);
                    }
                    int x;
                    int y;
                    int n5;
                    int n6;
                    if (this.barLayout == 1) {
                        x = mapValue2.x - barSize / 2;
                        y = mapValue.y;
                        n5 = barSize;
                        n6 = baseLine.y - mapValue.y;
                    }
                    else {
                        x = baseLine.x;
                        y = mapValue2.y - barSize / 2;
                        n5 = mapValue.x - baseLine.x;
                        n6 = barSize;
                    }
                    if (this.f == 1.0) {
                        if (this.barLayout == 1 && n4 >= 0 && n4 != x) {
                            final int n7 = x - n4;
                            x -= n7;
                            n5 += n7;
                        }
                        else if (this.barLayout == 2 && n3 >= 0 && n3 != y + n6) {
                            n6 -= y + n6 - n3;
                        }
                    }
                    if (this.barLayout == 1) {
                        n4 = x + n5;
                    }
                    else {
                        n3 = y;
                    }
                    this.a(clippedGraphics, nfDataSeries, i, nth, nth, defaultColor, x, y, n5, n6, n2, minCoord, minCoord2, maxCoord, maxCoord2);
                }
            }
        }
        clippedGraphics.dispose();
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final int size = super.dataSeries.size();
        final int n5 = n3 / n;
        int n6 = -1;
        int n7 = -1;
        final boolean b = true;
        final Graphics[] array = new Graphics[size];
        for (int i = 0; i < n2; ++i) {
            int n8 = -1;
            int n9 = -1;
            int n10 = -1;
            if (b) {
                n9 = n6;
                n10 = n7;
            }
            for (int j = 0; j < size; ++j) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                if (nfDataSeries.type != 1) {
                    n9 = -1;
                    n10 = -1;
                }
                else {
                    if (array[j] == null) {
                        array[j] = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                    }
                    final Graphics graphics2 = array[j];
                    final double min = nfDataSeries.XAxis.getMin();
                    final double max = nfDataSeries.XAxis.getMax();
                    final Color color = (nfDataSeries.sym != null && nfDataSeries.sym.getColor() != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
                    if (i >= min - 0.5) {
                        if (i <= max + 0.5) {
                            final Point mapValue = nfDataSeries.XAxis.mapValue(i);
                            int minCoord = 0;
                            int maxCoord = 0;
                            int minCoord2 = 0;
                            int maxCoord2 = 0;
                            if (n4 > 0 || nfDataSeries.valueLabelStyle != 0) {
                                minCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMinCoord();
                                maxCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMaxCoord();
                                minCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMinCoord();
                                maxCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMaxCoord();
                            }
                            ++n8;
                            final Point baseLine = this.getBaseLine(nfDataSeries.YAxis);
                            if (i >= nfDataSeries.dataset.size()) {
                                n9 = -1;
                                n10 = -1;
                            }
                            else {
                                final double nth;
                                double n11 = nth = nfDataSeries.dataset.getNth(i);
                                if (Double.isNaN(n11)) {
                                    n9 = -1;
                                    n10 = -1;
                                }
                                else {
                                    if (n11 < nfDataSeries.YAxis.getMin()) {
                                        n11 = nfDataSeries.YAxis.getMin();
                                    }
                                    if (n11 > nfDataSeries.YAxis.getMax()) {
                                        n11 = nfDataSeries.YAxis.getMax();
                                    }
                                    final Point mapValue2 = nfDataSeries.YAxis.mapValue(n11);
                                    int x;
                                    int y;
                                    int n12;
                                    int n13;
                                    if (this.barLayout == 1) {
                                        x = mapValue.x - n3 / 2 + n8 * n5;
                                        y = mapValue2.y;
                                        n12 = n5;
                                        n13 = baseLine.y - mapValue2.y;
                                    }
                                    else {
                                        x = mapValue2.x;
                                        y = mapValue.y + n3 / 2 - (n8 + 1) * n5;
                                        n12 = baseLine.x - mapValue2.x;
                                        n13 = n5;
                                    }
                                    if (this.f == 1.0) {
                                        if (this.barLayout == 1 && n9 >= 0 && n9 != x) {
                                            final int n14 = x - n9;
                                            x -= n14;
                                            n12 += n14;
                                        }
                                        else if (this.barLayout == 2 && n10 >= 0 && n10 != y + n13) {
                                            n13 -= y + n13 - n10;
                                        }
                                    }
                                    if (this.barLayout == 1) {
                                        n9 = x + n12;
                                    }
                                    else {
                                        n10 = y;
                                    }
                                    this.a(graphics2, nfDataSeries, i, n11, nth, color, x, y, n12, n13, n4, minCoord, minCoord2, maxCoord, maxCoord2);
                                }
                            }
                        }
                    }
                }
            }
            if (this.barLayout == 1) {
                n6 = n9;
            }
            else {
                n7 = n10;
            }
        }
        for (int k = 0; k < array.length; ++k) {
            if (array[k] != null) {
                array[k].dispose();
                array[k] = null;
            }
        }
    }
    
    private void b(final Graphics graphics, final int n, final int n2, int barSize, final int n3) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        final int size = super.dataSeries.size();
        int n7 = -1;
        int n8 = -1;
        final Graphics[] array = new Graphics[size];
        for (int i = 0; i < n2; ++i) {
            boolean b = false;
            this.g.removeAllElements();
            double n9 = 0.0;
            for (int j = 0; j < size; ++j) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                if (nfDataSeries.type == 1) {
                    if (i < nfDataSeries.dataset.size()) {
                        final double min = nfDataSeries.XAxis.getMin();
                        final double max = nfDataSeries.XAxis.getMax();
                        if (i >= min - 0.5) {
                            if (i <= max + 0.5) {
                                final double nth = nfDataSeries.dataset.getNth(i);
                                if (!Double.isNaN(nth)) {
                                    if (nth < 0.0) {
                                        n9 += nth;
                                        this.g.insertElementAt(nfDataSeries, 0);
                                    }
                                    else {
                                        this.g.addElement(nfDataSeries);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            double n10 = n9;
            for (int size2 = this.g.size(), k = 0; k < size2; ++k) {
                b = true;
                final NFDataSeries nfDataSeries2 = this.g.elementAt(k);
                final Color color = (nfDataSeries2.sym != null && nfDataSeries2.sym.getColor() != null) ? nfDataSeries2.sym.getColor() : nfDataSeries2.c;
                final int index = super.dataSeries.indexOf(nfDataSeries2);
                if (array[index] == null) {
                    array[index] = this.createClippedGraphics(graphics, nfDataSeries2.XAxis, nfDataSeries2.YAxis);
                }
                final Graphics graphics2 = array[index];
                int minCoord = 0;
                int maxCoord = 0;
                int minCoord2 = 0;
                int maxCoord2 = 0;
                if (n3 > 0 || nfDataSeries2.valueLabelStyle != 0) {
                    minCoord = this.getXAxis(nfDataSeries2.XAxis, nfDataSeries2.YAxis).getMinCoord();
                    maxCoord = this.getXAxis(nfDataSeries2.XAxis, nfDataSeries2.YAxis).getMaxCoord();
                    minCoord2 = this.getYAxis(nfDataSeries2.YAxis, nfDataSeries2.XAxis).getMinCoord();
                    maxCoord2 = this.getYAxis(nfDataSeries2.YAxis, nfDataSeries2.XAxis).getMaxCoord();
                }
                final Point mapValue = nfDataSeries2.XAxis.mapValue(i);
                barSize = this.getBarSize(nfDataSeries2.XAxis);
                final Point point = mapValue;
                point.x -= barSize / 2;
                final Point point2 = mapValue;
                point2.y -= barSize / 2;
                final double nth2 = nfDataSeries2.dataset.getNth(i);
                double n11;
                if (nth2 < 0.0) {
                    n11 = ((this.stackLabel == 2) ? nth2 : n9);
                    n9 -= nth2;
                }
                else {
                    n9 += nth2;
                    n11 = ((this.stackLabel == 2) ? nth2 : n9);
                }
                double n12 = n9;
                if (n12 < nfDataSeries2.YAxis.getMin()) {
                    n12 = nfDataSeries2.YAxis.getMin();
                }
                if (n12 > nfDataSeries2.YAxis.getMax()) {
                    n12 = nfDataSeries2.YAxis.getMax();
                }
                final Point mapValue2 = nfDataSeries2.YAxis.mapValue(n12);
                int n13;
                if (this.barLayout == 1) {
                    n4 = mapValue.x;
                    n5 = mapValue2.y;
                    n6 = barSize;
                    n13 = nfDataSeries2.YAxis.mapValue(n10).y - mapValue2.y;
                }
                else {
                    n4 = nfDataSeries2.YAxis.mapValue(n10).x;
                    n5 = mapValue.y;
                    n6 = mapValue2.x - nfDataSeries2.YAxis.mapValue(n10).x;
                    n13 = barSize;
                }
                n10 = n12;
                if (this.f == 1.0) {
                    if (this.barLayout == 1 && n8 >= 0 && n8 != n4) {
                        final int n14 = n4 - n8;
                        n4 -= n14;
                        n6 += n14;
                    }
                    else if (this.barLayout == 2 && n7 >= 0 && n7 != n5 + n13) {
                        n13 -= n5 + n13 - n7;
                    }
                }
                this.a(graphics2, nfDataSeries2, i, n12, n11, color, n4, n5, n6, n13, n3, minCoord, minCoord2, maxCoord, maxCoord2);
            }
            if (b) {
                if (this.barLayout == 1) {
                    n8 = n4 + n6;
                }
                else {
                    n7 = n5;
                }
            }
            else {
                n8 = -1;
                n7 = -1;
            }
        }
        for (int l = 0; l < array.length; ++l) {
            if (array[l] != null) {
                array[l].dispose();
                array[l] = null;
            }
        }
    }
    
    private void c(final Graphics graphics, final int n, final int n2, int barSize, final int n3) {
        final int n4 = n3 / n;
        int n5 = n * n4;
        final int n6 = (int)Math.round(this.f * n4);
        for (int i = super.dataSeries.size() - 1; i >= 0; --i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 1) {
                final Color color = (nfDataSeries.sym != null && nfDataSeries.sym.getColor() != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                final double min = nfDataSeries.XAxis.getMin();
                final double max = nfDataSeries.XAxis.getMax();
                int minCoord = 0;
                int maxCoord = 0;
                int minCoord2 = 0;
                int maxCoord2 = 0;
                if (n4 > 0 || nfDataSeries.valueLabelStyle != 0) {
                    minCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMinCoord();
                    maxCoord = this.getXAxis(nfDataSeries.XAxis, nfDataSeries.YAxis).getMaxCoord();
                    minCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMinCoord();
                    maxCoord2 = this.getYAxis(nfDataSeries.YAxis, nfDataSeries.XAxis).getMaxCoord();
                }
                final Point baseLine = this.getBaseLine(nfDataSeries.YAxis);
                n5 -= n4;
                int n7 = -1;
                int n8 = -1;
                for (int n9 = 0; n9 < n2 && n9 < nfDataSeries.dataset.size(); ++n9) {
                    if (n9 < min - 0.5 || n9 > max + 0.5) {
                        n7 = -1;
                        n8 = -1;
                    }
                    else {
                        final Point mapValue = nfDataSeries.XAxis.mapValue(n9);
                        final double nth;
                        double n10 = nth = nfDataSeries.dataset.getNth(n9);
                        if (Double.isNaN(n10)) {
                            n7 = -1;
                            n8 = -1;
                        }
                        else {
                            if (n10 < nfDataSeries.YAxis.getMin()) {
                                n10 = nfDataSeries.YAxis.getMin();
                            }
                            if (n10 > nfDataSeries.YAxis.getMax()) {
                                n10 = nfDataSeries.YAxis.getMax();
                            }
                            final Point mapValue2 = nfDataSeries.YAxis.mapValue(n10);
                            barSize = this.getBarSize(nfDataSeries.XAxis);
                            int n11;
                            int n12;
                            int n13;
                            int n14;
                            if (this.barLayout == 1) {
                                n11 = mapValue.x - barSize / 2 + n5;
                                n12 = mapValue2.y - n5;
                                n13 = barSize;
                                n14 = baseLine.y - mapValue2.y;
                            }
                            else {
                                n11 = mapValue2.x + n5;
                                n12 = mapValue.y - barSize / 2 - n5;
                                n13 = baseLine.x - mapValue2.x;
                                n14 = barSize;
                            }
                            if (this.f == 1.0) {
                                if (this.barLayout == 1 && n7 >= 0 && n7 != n11) {
                                    final int n15 = n11 - n7;
                                    n11 -= n15;
                                    n13 += n15;
                                }
                                else if (this.barLayout == 2 && n8 >= 0 && n8 != n12 + n14) {
                                    n14 -= n12 + n14 - n8;
                                }
                            }
                            if (this.barLayout == 1) {
                                n7 = n11 + n13;
                            }
                            else {
                                n8 = n12;
                            }
                            this.a(clippedGraphics, nfDataSeries, n9, n10, nth, color, n11, n12, n13, n14, n6, minCoord + n5, minCoord2 - n5, maxCoord + n5, maxCoord2 - n5);
                        }
                    }
                }
                clippedGraphics.dispose();
            }
        }
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        super.defineParams();
        hashtable.put("GROUP", new Integer(1));
        hashtable.put("STACK", new Integer(3));
        hashtable.put("ROWS", new Integer(2));
        hashtable2.put("VERTICAL", new Integer(1));
        hashtable2.put("HORIZONTAL", new Integer(2));
        super.param.defineSymbol("GraphType", hashtable, new Integer(1));
        super.param.defineSymbol("GraphLayout", hashtable2, new Integer(1));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("DataSetName"));
        vector.addElement(super.param.defineColor("DataSetColor", null));
        this.k = 2;
        this.l = super.param.defineGraphSymbol("DataSetSymbol", vector);
        super.param.defineVector("DataSets", super.param.defineTuple("DataSet", vector));
        super.param.defineNumber("Bar3DDepth", new Integer(-1));
        super.param.defineNumber("BarWidth", new Double(this.f * 100.0));
        super.param.defineNumber("BarHeight");
        super.param.defineLine("BarBorder");
        this.defineBarSymbol(super.param);
        final Hashtable<String, Integer> hashtable3 = new Hashtable<String, Integer>();
        hashtable3.put("TOTAL", new Integer(1));
        hashtable3.put("ITEM", new Integer(2));
        super.param.defineSymbol("StackLabel", hashtable3, new Integer(1));
        final NFParamDef defineNumber = super.param.defineNumber("DataValue");
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("DataSet" + (i + 1), defineNumber);
        }
        this.defineDataAxisParams(super.param, "DataAxis");
        super.param.defineVector("BarLabels", super.param.defineString("Label"));
        NFValueLabel.defineRectValueLabel(super.param, "Bar", this.BAR_VALUE_LABEL_STYLE_DEFAULT);
        super.param.defineActiveLabel("BarActiveLabels");
        NFPatternFill.definePatternFillParam(super.param, "BarFillPattern");
        super.param.defineLabel("ZAxisLabels");
    }
    
    protected synchronized void loadParams() throws Exception {
        boolean b = false;
        super.loadParams();
        if (super.param.changed("StackLabel")) {
            this.stackLabel = NFUtil.getNumber(super.param.get("StackLabel"), this.STACK_LABEL_DEFAULT);
        }
        if (super.param.changed("GraphType")) {
            this.barFormat = NFUtil.getNumber(super.param.get("GraphType"), this.BAR_FORMAT_DEFAULT);
            super.graphChanged = true;
            super.layoutChanged = true;
        }
        if (super.param.changed("Bar3DDepth")) {
            super.depth3D = NFUtil.getNumber(super.param.get("Bar3DDepth"), super.DEPTH_3D_DEFAULT);
            super.layoutChanged = true;
        }
        if (super.param.changed("BarWidth")) {
            this.f = NFUtil.getNumber(super.param.get("BarWidth"), this.e);
            if (this.f < 0.0) {
                this.f = -this.f;
            }
            if (this.f >= 1.0) {
                this.f /= 100.0;
            }
            if (this.f > 1.0) {
                this.f = 1.0;
            }
            super.layoutChanged = true;
        }
        if (super.param.changed("BarBorder")) {
            super.graphChanged = true;
            b = true;
            final Vector vector = (Vector)super.param.get("BarBorder");
            if (vector == null || vector.size() == 0) {
                this.j = this.i;
            }
            else {
                this.j = NFLine.loadParams(super.param, vector);
            }
        }
        if (super.param.changed("BarHeight")) {
            this.barHeightFactor = NFUtil.getNumber(super.param.get("BarHeight"), this.BAR_HEIGHT_FACTOR_DEFAULT);
            if (this.barHeightFactor < 0.0) {
                this.barHeightFactor = -this.barHeightFactor;
            }
            if (this.barHeightFactor >= 1.0) {
                this.barHeightFactor /= 100.0;
            }
            super.layoutChanged = true;
        }
        if (super.param.changed("GraphLayout")) {
            this.barLayout = NFUtil.getNumber(super.param.get("GraphLayout"), this.BAR_LAYOUT_DEFAULT);
            if (this.barLayout == 2) {
                super.XValuesAxis = super.leftAxis;
                super.YValuesAxis = super.bottomAxis;
            }
            else {
                super.XValuesAxis = super.bottomAxis;
                super.YValuesAxis = super.leftAxis;
            }
            this.handleLayoutChange();
            this.setChangedIfLoaded("BarLabels");
            this.setChangedIfLoaded("BarActiveLabels");
            this.setChangedIfLoaded("BarSymbol");
            this.setChangedIfLoaded("BarFillPattern");
            super.param.setChanged("DataSets");
            super.loadParams();
            super.graphChanged = true;
            super.layoutChanged = true;
        }
        if (super.param.changed("BarLabels")) {
            super.graphChanged = true;
            super.layoutChanged = true;
            final Vector vector2 = (Vector)super.param.get("BarLabels");
            if (vector2 != null && vector2.size() > 0) {
                super.XValuesAxis.setTicLabels(vector2, "BarLabels");
                super.XValuesAxis.setSpacing(new NFSpacing(0.0, vector2.size() - 1.0, 1.0));
            }
            else {
                super.XValuesAxis.clearTicLabels();
                super.XValuesAxis.setSpacing(null);
            }
        }
        if (super.param.changed("BarActiveLabels") && super.dwell != null) {
            super.XValuesAxis.setActiveLabels(null);
            if (this.h == null) {
                this.h = new Vector();
            }
            else {
                if (super.dwell != null) {
                    super.dwell.removeLabel(this.h);
                }
                this.h.removeAllElements();
            }
            final Vector loadAllParams = NFActiveLabel.loadAllParams(super.param, "BarActiveLabels");
            for (int size = loadAllParams.size(), i = 0; i < size; ++i) {
                this.h.addElement(super.dwell.addLabel(loadAllParams, i));
            }
            super.XValuesAxis.setActiveLabels(this.h);
        }
        boolean loadDataSetParams = this.loadDataSetParams(super.param, 1, "DataSets", "DataAxis", super.XValuesAxis, super.YValuesAxis);
        this.loadBarSymbol(super.param);
        if (this.a() > 1) {
            for (int size2 = super.dataSeries.size(), j = 0; j < size2; ++j) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                if (nfDataSeries.type == 1 && nfDataSeries.c == null) {
                    nfDataSeries.c = this.defaultColor(j);
                }
            }
        }
        if (b || loadDataSetParams) {
            for (int size3 = super.dataSeries.size(), k = 0; k < size3; ++k) {
                final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(k);
                if (nfDataSeries2.type == 1) {
                    nfDataSeries2.sym.setBorderLine(this.j);
                }
            }
        }
        final boolean[] loadDataSets = this.loadDataSets(super.param, 1, "DataSet", loadDataSetParams);
        this.loadActiveLabels(super.param, 1, "ActiveLabels", loadDataSets, this.barFormat == 2);
        this.loadPatternFill(super.param, 1, "BarFillPattern");
        this.a = NFValueLabel.loadValueLabel(this, "Bar", 1, super.dataSeries, this.a, this.BAR_VALUE_LABEL_STYLE_DEFAULT);
        if (super.param.changed("ZAxisLabels")) {
            super.layoutChanged = true;
            super.graphChanged = true;
            loadDataSetParams = true;
            super.rightSlantAxis.loadAxisTics((Vector)super.param.get("ZAxisLabels"));
            super.rightSlantAxis.showAxisAndLine(false, false);
            this.showZAxisLabels = NFUtil.getString((Vector)super.param.get("ZAxisLabels"), 0, this.SHOW_ZAXIS_LABELS_DEFAULT ? "ON" : "OFF").equalsIgnoreCase("ON");
        }
        if (loadDataSetParams) {
            this.reset();
        }
        else {
            for (int l = 0; l < loadDataSets.length; ++l) {
                if (loadDataSets[l]) {
                    this.reset();
                    break;
                }
            }
        }
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 1) {
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
        nfDataSeries.sym = NFGraphSymbol.loadParams(super.param, vector, this.k);
        if (nfDataSeries.sym.type < 9) {
            nfDataSeries.sym.type = 9;
        }
        if (this.barLayout == 2) {
            final NFAxis xAxis = nfDataSeries.XAxis;
            final NFAxis yAxis = nfDataSeries.YAxis;
            if ((xAxis == super.bottomAxis || xAxis == super.topAxis) && (yAxis == super.leftAxis || yAxis == super.rightAxis)) {
                nfDataSeries.XAxis = yAxis;
                nfDataSeries.YAxis = xAxis;
            }
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 1) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        nfDataSeries.dataset.addPoint(((Number)o).doubleValue());
    }
    
    protected void loadGridParams() throws Exception {
        super.loadGridParams();
    }
    
    protected double[] getMinMax(final NFAxis nfAxis) {
        final double[] minMax = super.getMinMax(nfAxis);
        if (this.barFormat != 3) {
            return minMax;
        }
        final int maxBars = this.getMaxBars(null);
        if (maxBars < 1) {
            return minMax;
        }
        final double[] array = new double[maxBars];
        final double[] array2 = new double[maxBars];
        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 1) {
                if (nfDataSeries.YAxis == nfAxis) {
                    for (int n = 0; n < nfDataSeries.dataset.size() && n < maxBars; ++n) {
                        final double nth = nfDataSeries.dataset.getNth(n, 1);
                        if (!Double.isNaN(nth)) {
                            if (nth > 0.0) {
                                final double[] array3 = array;
                                final int n2 = n;
                                array3[n2] += nth;
                            }
                            else {
                                final double[] array4 = array2;
                                final int n3 = n;
                                array4[n3] += nth;
                            }
                        }
                    }
                }
            }
        }
        for (int j = 0; j < maxBars; ++j) {
            if (array2[j] < minMax[0]) {
                minMax[0] = array2[j];
            }
            if (array[j] > minMax[1]) {
                minMax[1] = array[j];
            }
        }
        return minMax;
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfDataSeries.type != 1) {
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
        if (this.barFormat != 3) {
            final double[] minMax = nfDataSeries.dataset.getMinMax(1);
            if (minMax[0] < array[0]) {
                array[0] = minMax[0];
            }
            if (minMax[1] > array[1]) {
                array[1] = minMax[1];
            }
            return array;
        }
        return array;
    }
    
    public int getBarSize() {
        return this.getBarSize(null);
    }
    
    public int getBarSize(NFAxis xValuesAxis) {
        if (xValuesAxis == null) {
            xValuesAxis = super.XValuesAxis;
        }
        final Point mapValue = xValuesAxis.mapValue(xValuesAxis.getMin());
        final Point mapValue2 = xValuesAxis.mapValue(xValuesAxis.getMin() + 1.0);
        double n;
        if (this.barLayout == 1) {
            n = mapValue2.x - mapValue.x;
        }
        else {
            n = mapValue2.y - mapValue.y;
        }
        if (n < 0.0) {
            n = -n;
        }
        double n2 = Math.round(this.f * n);
        if (n2 > n) {
            n2 = n;
        }
        if (n2 < 1.0) {
            n2 = 1.0;
        }
        return (int)n2;
    }
    
    public void setDefaultAxis(final NFAxis nfAxis) {
        if (nfAxis != super.XValuesAxis) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        final int maxBars = this.getMaxBars(nfAxis);
        if (maxBars == 0) {
            super.setDefaultAxis(nfAxis);
            return;
        }
        if (nfAxis.autoscale) {
            nfAxis.setMinMax(-0.5, maxBars - 0.5);
        }
    }
    
    protected void drawData(final Graphics graphics) {
        if (super.dataSeries.size() < 1) {
            return;
        }
        this.drawBars(graphics);
    }
    
    public void drawBar(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final int n5) {
        this.drawBar(graphics, n, n2, n3, n4, color, n5, true, true);
    }
    
    public void drawBar(final Graphics graphics, int n, int n2, int n3, int n4, final Color color, final int n5, final boolean b, final boolean b2) {
        if (n4 < 0) {
            n2 += n4;
            n4 = -n4;
        }
        if (n3 < 0) {
            n += n3;
            n3 = -n3;
        }
        graphics.setColor(color);
        graphics.fillRect(n, n2, n3, n4);
        if (n5 < 1) {
            return;
        }
        graphics.setColor(Color.black);
        graphics.drawRect(n, n2, n3, n4);
        if (this.m == null || this.n == null) {
            this.m = new Polygon();
            this.m.xpoints = new int[5];
            this.m.ypoints = new int[5];
            this.m.npoints = 5;
            this.n = new Polygon();
            this.n.xpoints = new int[5];
            this.n.ypoints = new int[5];
            this.n.npoints = 5;
        }
        if (b) {
            this.n.xpoints[0] = n;
            this.n.ypoints[0] = n2;
            this.n.xpoints[1] = n + n5;
            this.n.ypoints[1] = n2 - n5;
            this.n.xpoints[2] = n + n3 + n5;
            this.n.ypoints[2] = n2 - n5;
            this.n.xpoints[3] = n + n3;
            this.n.ypoints[3] = n2;
            this.n.xpoints[4] = n;
            this.n.ypoints[4] = n2;
        }
        if (b2) {
            this.m.xpoints[0] = n + n3;
            this.m.ypoints[0] = n2;
            this.m.xpoints[1] = n + n3 + n5;
            this.m.ypoints[1] = n2 - n5;
            this.m.xpoints[2] = n + n3 + n5;
            this.m.ypoints[2] = n2 + (n4 - n5);
            this.m.xpoints[3] = n + n3;
            this.m.ypoints[3] = n2 + n4;
            this.m.xpoints[4] = n + n3;
            this.m.ypoints[4] = n2;
        }
        if (b) {
            graphics.setColor(NFColor.brighter(color));
            graphics.fillPolygon(this.n);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.n);
        }
        if (b2) {
            graphics.setColor(NFColor.darker(color));
            graphics.fillPolygon(this.m);
            graphics.setColor(Color.black);
            graphics.drawPolygon(this.m);
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final double n, int n2, int n3, int n4, int n5, final int n6, final int n7, final double n8) {
        if (this.barLayout == 2) {
            final Point mapValue = nfDataSeries.YAxis.mapValue(nfDataSeries.YAxis.getMin());
            final Point mapValue2 = nfDataSeries.YAxis.mapValue(nfDataSeries.YAxis.getMax());
            n4 = (int)((mapValue2.x - mapValue.x) * this.barHeightFactor);
            n3 -= n6;
            n2 = mapValue.x + (int)((mapValue2.x - mapValue.x) * 0.05);
        }
        else {
            final Point mapValue3 = nfDataSeries.YAxis.mapValue(nfDataSeries.YAxis.getMin());
            n5 = (int)((mapValue3.y - nfDataSeries.YAxis.mapValue(nfDataSeries.YAxis.getMax()).y) * this.barHeightFactor);
            n3 = mapValue3.y - n5;
            n3 -= n6;
        }
        final Color color = (nfDataSeries.sym != null && nfDataSeries.sym.getColor() != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
        if (color != null && color.equals(this.defaultColor(0))) {
            this.o[0] = this.defaultColor(1);
            this.o[1] = color;
        }
        else if (color != null) {
            this.o[0] = this.defaultColor(0);
            this.o[1] = color;
        }
        else {
            this.o[0] = this.defaultColor(0);
            this.o[1] = this.defaultColor(1);
        }
        this.p[0] = (int)n;
        nfDataSeries.sym.draw(graphics, n2, n3, n4, n5, this.o, this.p, 1, n6, nfDataSeries.pattern);
        this.setActiveLabel(nfDataSeries, n7, n2, n3, n4, n5, nfDataSeries.YAxis.getLabel(n8));
    }
    
    public void clean() {
        super.clean();
        this.cleanLayoutParameters();
        if (this.beenLoaded("BarLabels")) {
            this.setLoaded("BarLabels", false);
        }
        if (this.beenLoaded("BarActiveLabels")) {
            this.setLoaded("BarActiveLabels", false);
        }
        this.g.removeAllElements();
    }
    
    protected NFAxis getXAxis(final NFAxis nfAxis, final NFAxis nfAxis2) {
        if (this.barLayout == 2) {
            return nfAxis2;
        }
        return nfAxis;
    }
    
    protected NFAxis getYAxis(final NFAxis nfAxis, final NFAxis nfAxis2) {
        if (this.barLayout == 2) {
            return nfAxis2;
        }
        return nfAxis;
    }
    
    protected Graphics createClippedGraphics(final Graphics graphics, final NFAxis nfAxis, final NFAxis nfAxis2) {
        return super.createClippedGraphics(graphics, this.getXAxis(nfAxis, nfAxis2), this.getYAxis(nfAxis2, nfAxis));
    }
    
    protected void defineBarSymbol(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineSymbol("BarSymbolType", nfParam.getSymbolTypeTable(), new Integer(9)));
        vector.addElement(nfParam.defineColor("BarSymbolColor", null));
        nfParam.defineVector("BarSymbol", nfParam.defineTuple("BarSymbolTuple", vector));
    }
    
    protected void loadBarSymbol(final NFParam nfParam) throws Exception {
        if (nfParam.changed("BarSymbol")) {
            super.graphChanged = true;
            final Vector vector = (Vector)nfParam.get("BarSymbol");
            for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
                if (nfDataSeries.type == 1) {
                    final Vector vector2 = (Vector)NFUtil.getObject(vector, i, null);
                    nfDataSeries.sym.type = NFUtil.getNumber(vector2, 0, 9);
                    nfDataSeries.sym.setColor(NFUtil.getColor(vector2, 1, null));
                    if (nfDataSeries.sym.type < 9) {
                        nfDataSeries.sym.type = 9;
                    }
                }
            }
        }
    }
}
