// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Hashtable;
import netcharts.util.NFParamDef;
import java.awt.Point;
import java.awt.Color;
import netcharts.util.NFUtil;
import java.awt.Graphics;
import java.util.Vector;
import java.applet.Applet;

public class NFStockChart extends NFComboChart
{
    protected static final int STOCK = 13;
    private static final boolean a = false;
    private static double b;
    private static double c;
    private static double d;
    private static double e;
    private StringBuffer f;
    
    public NFStockChart(final Applet applet) {
        super(applet);
        this.f = new StringBuffer();
        this.initStockChart();
    }
    
    public NFStockChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        super(applet, n, n2, n3, n4);
        this.f = new StringBuffer();
        this.initStockChart();
    }
    
    public void initStockChart() {
    }
    
    public void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
    }
    
    protected double[] getMinMax(final NFDataSeries nfDataSeries, final NFAxis nfAxis, final double[] array) {
        if (nfDataSeries.type != 4) {
            return super.getMinMax(nfDataSeries, nfAxis, array);
        }
        final NFStockInfo nfStockInfo = (NFStockInfo)nfDataSeries.info;
        final int size = nfStockInfo.c.size();
        final double[] array2 = new double[2];
        if (nfDataSeries.XAxis == nfAxis) {
            if (0.0 < array[0]) {
                array[0] = 0.0;
            }
            if (size - 1 > array[1]) {
                array[1] = size - 1;
            }
            return array;
        }
        for (int i = 0; i < size; ++i) {
            final NFStockData nfStockData = nfStockInfo.c.elementAt(i);
            if (nfStockData.a < array[0]) {
                array[0] = nfStockData.a;
            }
            if (nfStockData.a > array[1]) {
                array[1] = nfStockData.a;
            }
            if (nfStockData.b < array[0]) {
                array[0] = nfStockData.b;
            }
            if (nfStockData.b > array[1]) {
                array[1] = nfStockData.b;
            }
        }
        return array;
    }
    
    protected Vector getDefaultDataDrawOrder() {
        final Vector defaultDataDrawOrder = super.getDefaultDataDrawOrder();
        defaultDataDrawOrder.addElement(new Integer(13));
        return defaultDataDrawOrder;
    }
    
    protected void drawData(final Graphics graphics) {
        this.drawData(graphics, (super.drawOrder != null) ? ((Vector)super.drawOrder.clone()) : null);
    }
    
    protected void drawData(final Graphics graphics, final Vector vector) {
        while (vector != null && vector.size() > 0) {
            final Number n = vector.elementAt(0);
            if (n == null) {
                vector.removeElementAt(0);
            }
            else {
                switch (n.intValue()) {
                    default: {
                        super.drawData(graphics, vector);
                        continue;
                    }
                    case 13: {
                        for (int size = super.dataSeries.size(), i = 0; i < size; ++i) {
                            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
                            if (nfDataSeries.type == 4) {
                                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                                this.drawStock(clippedGraphics, nfDataSeries);
                                clippedGraphics.dispose();
                            }
                        }
                        vector.removeElementAt(0);
                        continue;
                    }
                }
            }
        }
    }
    
    protected void drawStock(final Graphics graphics, final NFDataSeries nfDataSeries) {
        final NFStockInfo nfStockInfo = (NFStockInfo)nfDataSeries.info;
        final int size = nfStockInfo.c.size();
        if (nfDataSeries.c != null) {
            graphics.setColor(nfDataSeries.c);
        }
        final double min = nfDataSeries.XAxis.getMin();
        final double max = nfDataSeries.XAxis.getMax();
        final double min2 = nfDataSeries.YAxis.getMin();
        final double max2 = nfDataSeries.YAxis.getMax();
        int n = 0;
        if (nfStockInfo.a <= 0 || nfStockInfo.b <= 0) {
            n = nfDataSeries.XAxis.mapValue(min + 1.0).x - nfDataSeries.XAxis.mapValue(min).x;
        }
        int a;
        if (nfStockInfo.a > 0) {
            a = nfStockInfo.a;
        }
        else {
            a = (int)NFUtil.rint(NFStockChart.c * n);
        }
        if (a < 1) {
            a = 1;
        }
        int b;
        if (nfStockInfo.b > 0) {
            b = nfStockInfo.b;
        }
        else if (NFStockChart.e <= 0.0) {
            b = (n - a) / 2;
            if (b < 0) {
                b = 0;
            }
        }
        else {
            b = (int)NFUtil.rint(NFStockChart.e * n);
        }
        for (int i = 0; i < size; ++i) {
            final NFStockData nfStockData = nfStockInfo.c.elementAt(i);
            if (Double.isNaN(nfStockData.a) || Double.isNaN(nfStockData.b) || this.clipLine(i, nfStockData.a, i, nfStockData.b, min, max, min2, max2)) {
                this.setActiveLabel(nfDataSeries, i, -1, -1, 0, 0, "");
            }
            else {
                final Point mapValue = nfDataSeries.XAxis.mapValue(i);
                final Point mapValue2 = nfDataSeries.YAxis.mapValue(nfStockData.a);
                final Point mapValue3 = nfDataSeries.YAxis.mapValue(nfStockData.b);
                if (mapValue2.y > mapValue3.y) {
                    final int y = mapValue2.y;
                    mapValue2.y = mapValue3.y;
                    mapValue3.y = y;
                }
                int y2 = mapValue2.y;
                int n2 = mapValue3.y - mapValue2.y + 1;
                int x;
                int n3;
                if (a <= 1) {
                    x = mapValue.x;
                    n3 = 1;
                    graphics.drawLine(mapValue.x, mapValue2.y, mapValue.x, mapValue3.y);
                }
                else {
                    x = mapValue.x - a / 2;
                    n3 = a;
                    if (NFUtil.getJDKVersion() >= 1.2 && nfDataSeries.pattern != null && nfDataSeries.pattern.pattern != 0) {
                        NF12GraphicsUtil.patternFillRect(graphics, x, y2, n3, n2, null, nfDataSeries.pattern);
                    }
                    else {
                        graphics.fillRect(x, y2, n3, n2);
                    }
                }
                this.f.setLength(0);
                this.f.append(nfDataSeries.XAxis.getLabel(i));
                this.f.append("\nHi: ");
                this.f.append(nfDataSeries.YAxis.getLabel(nfStockData.a));
                this.f.append("\nLo: ");
                this.f.append(nfDataSeries.YAxis.getLabel(nfStockData.b));
                if (!Double.isNaN(nfStockData.c) && b > 0) {
                    final Point mapValue4 = nfDataSeries.YAxis.mapValue(nfStockData.c);
                    graphics.drawLine(x - b, mapValue4.y, x, mapValue4.y);
                    this.f.append("\nOpen: ");
                    this.f.append(nfDataSeries.YAxis.getLabel(nfStockData.c));
                }
                if (!Double.isNaN(nfStockData.d) && b > 0) {
                    final Point mapValue5 = nfDataSeries.YAxis.mapValue(nfStockData.d);
                    graphics.drawLine(x + n3 - 1, mapValue5.y, x + n3 + b - 1, mapValue5.y);
                    this.f.append("\nClose: ");
                    this.f.append(nfDataSeries.YAxis.getLabel(nfStockData.d));
                }
                if (n3 < 8) {
                    x = x + n3 / 2 - 4;
                    n3 = 8;
                }
                if (n2 < 8) {
                    y2 = y2 + n2 / 2 - 4;
                    n2 = 8;
                }
                this.setActiveLabel(nfDataSeries, i, x, y2, n3, n2, this.f.toString());
            }
        }
    }
    
    protected int getDataSetSize(final NFDataSeries nfDataSeries) {
        if (nfDataSeries.info != null && nfDataSeries.info instanceof NFStockInfo && ((NFStockInfo)nfDataSeries.info).c != null) {
            return ((NFStockInfo)nfDataSeries.info).c.size();
        }
        return super.getDataSetSize(nfDataSeries);
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        super.param.remove("GraphLayout");
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("StockSetName"));
        vector.addElement(super.param.defineColor("StockSetColor", null));
        vector.addElement(super.param.defineNumber("StockSetWidth", null));
        vector.addElement(super.param.defineNumber("StockSetTicWidth", null));
        super.param.defineVector("StockSets", super.param.defineTuple("StockSetItem", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineNumber("StockHi", new Double(0.0)));
        vector2.addElement(super.param.defineNumber("StockLo", null));
        vector2.addElement(super.param.defineNumber("StockOpen", null));
        vector2.addElement(super.param.defineNumber("StockClose", null));
        final NFParamDef defineTuple = super.param.defineTuple("StockTuple", vector2);
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("StockData" + (i + 1), defineTuple);
            super.param.defineActiveLabel("StockLabels" + (i + 1));
        }
        super.param.defineString("StockLabelsEnabled", "ON");
        this.defineDataAxisParams(super.param, "StockAxis");
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineNumber("StockBarWidth", new Double(30.0)));
        vector3.addElement(super.param.defineNumber("StockTicWidth", new Double(0.0)));
        super.param.defineTuple("StockWidth", vector3);
        NFPatternFill.definePatternFillParam(super.param, "StockFillPattern");
    }
    
    protected Hashtable getDataDrawOrderSymbols() {
        final Hashtable dataDrawOrderSymbols = super.getDataDrawOrderSymbols();
        dataDrawOrderSymbols.put("STOCK", new Integer(13));
        return dataDrawOrderSymbols;
    }
    
    protected synchronized void loadParams() throws Exception {
        super.loadParams();
        super.barLayout = 1;
        if (super.param.changed("StockWidth")) {
            super.graphChanged = true;
            final Vector vector = (Vector)super.param.get("StockWidth");
            NFStockChart.c = NFUtil.getNumber(vector, 0, NFStockChart.b);
            if (NFStockChart.c < 0.0) {
                NFStockChart.c = 0.0;
            }
            else if (NFStockChart.c > 1.0) {
                NFStockChart.c /= 100.0;
                if (NFStockChart.c > 1.0) {
                    NFStockChart.c = 1.0;
                }
            }
            NFStockChart.e = NFUtil.getNumber(vector, 1, NFStockChart.d);
            if (NFStockChart.e < 0.0) {
                NFStockChart.e = 0.0;
            }
            else if (NFStockChart.e > 1.0) {
                NFStockChart.e /= 100.0;
                if (NFStockChart.e > 1.0) {
                    NFStockChart.e = 1.0;
                }
            }
        }
        final boolean loadDataSetParams = this.loadDataSetParams(super.param, 4, "StockSets", "StockAxis", super.bottomAxis, super.leftAxis);
        final boolean[] loadDataSets = this.loadDataSets(super.param, 4, "StockData", loadDataSetParams);
        this.loadActiveLabels(super.param, 4, "StockLabels", loadDataSets);
        this.loadPatternFill(super.param, 4, "StockFillPattern");
        for (int i = 0; i < loadDataSets.length; ++i) {
            if (loadDataSetParams || loadDataSets[i]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 4) {
            super.loadDataSetParams(nfDataSeries, n, o);
            return;
        }
        final NFStockInfo info = new NFStockInfo();
        nfDataSeries.info = info;
        info.c = new Vector();
        final Vector vector = (Vector)o;
        nfDataSeries.name = vector.elementAt(0);
        nfDataSeries.c = (Color)vector.elementAt(1);
        if (nfDataSeries.c == null) {
            nfDataSeries.c = this.defaultColor(super.dataSeries.indexOf(nfDataSeries));
        }
        final Number n2 = (Number)vector.elementAt(2);
        if (n2 != null) {
            if (Double.isNaN(n2.doubleValue())) {
                info.a = 0;
            }
            else {
                info.a = n2.intValue();
            }
        }
        final Number n3 = (Number)vector.elementAt(3);
        if (n3 != null) {
            if (Double.isNaN(n3.doubleValue())) {
                info.b = 0;
            }
            else {
                info.b = n3.intValue();
            }
        }
    }
    
    protected void clearDataItems(final NFDataSeries nfDataSeries, final int n) {
        if (nfDataSeries.type != 4) {
            super.clearDataItems(nfDataSeries, n);
            return;
        }
        ((NFStockInfo)nfDataSeries.info).c.removeAllElements();
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 4) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        final NFStockData nfStockData = new NFStockData();
        ((NFStockInfo)nfDataSeries.info).c.addElement(nfStockData);
        final Vector vector = (Vector)o;
        nfStockData.a = vector.elementAt(0).doubleValue();
        final Number n2 = vector.elementAt(1);
        if (n2 == null) {
            nfStockData.b = nfStockData.a;
        }
        else {
            nfStockData.b = n2.doubleValue();
        }
        final Number n3 = vector.elementAt(2);
        if (n3 == null) {
            nfStockData.c = Double.NaN;
        }
        else {
            nfStockData.c = n3.doubleValue();
        }
        final Number n4 = vector.elementAt(3);
        if (n4 == null) {
            nfStockData.d = Double.NaN;
        }
        else {
            nfStockData.d = n4.doubleValue();
        }
    }
    
    static {
        NFStockChart.b = 0.33;
        NFStockChart.c = NFStockChart.b;
        NFStockChart.d = 0.0;
        NFStockChart.e = NFStockChart.d;
    }
}
