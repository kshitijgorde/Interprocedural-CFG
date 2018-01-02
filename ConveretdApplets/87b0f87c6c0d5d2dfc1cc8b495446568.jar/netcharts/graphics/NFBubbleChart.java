// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFDataSet;
import netcharts.util.NFParamException;
import netcharts.util.NFUtil;
import java.util.Enumeration;
import netcharts.util.NFDebug;
import java.util.Hashtable;
import netcharts.util.NFParam;
import netcharts.util.NFParamDef;
import java.util.Vector;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

public class NFBubbleChart extends NFDataChart
{
    private static final boolean a = false;
    public static final int AREA = 0;
    public static final int DIAMETER = 1;
    protected int FORMAT_TYPE_DEFAULT;
    protected int formatType;
    protected String FORMAT_STR_DEFAULT;
    protected String formatStr;
    private boolean b;
    
    public NFBubbleChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.FORMAT_TYPE_DEFAULT = -1;
        this.formatType = this.FORMAT_TYPE_DEFAULT;
        this.FORMAT_STR_DEFAULT = null;
        this.formatStr = this.FORMAT_STR_DEFAULT;
        this.b = false;
        this.initGraph(applet);
        this.initBubbleChart();
        this.reshape(n, n2, n3, n4);
    }
    
    public NFBubbleChart(final Applet applet) {
        this.FORMAT_TYPE_DEFAULT = -1;
        this.formatType = this.FORMAT_TYPE_DEFAULT;
        this.FORMAT_STR_DEFAULT = null;
        this.formatStr = this.FORMAT_STR_DEFAULT;
        this.b = false;
        this.initGraph(applet);
        this.initBubbleChart();
    }
    
    protected void initBubbleChart() {
        super.initChart();
    }
    
    public void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
    }
    
    private double[] a(double n, double n2) {
        final double[] array = new double[3];
        if (n == n2) {
            n -= n * 0.1;
            n2 += n2 * 0.1;
        }
        final double reasonableStep = NFAxis.defaultAxis("Left").reasonableStep(n, n2);
        double n3 = 0.0;
        double n4 = 0.0;
        if (n3 > n) {
            while (n3 > n) {
                n3 -= reasonableStep;
            }
        }
        else {
            while (n3 < n) {
                n3 += reasonableStep;
            }
            if (n3 > n) {
                n3 -= reasonableStep;
            }
        }
        if (n4 < n2) {
            while (n4 < n2) {
                n4 += reasonableStep;
            }
        }
        else {
            while (n4 > n2) {
                n4 -= reasonableStep;
            }
            if (n4 < n2) {
                n4 += reasonableStep;
            }
        }
        array[0] = n3;
        array[1] = n4;
        array[2] = reasonableStep;
        return array;
    }
    
    protected double[] getZMinMax(final NFDataSeries nfDataSeries, final double[] array) {
        final double[] minMax = nfDataSeries.dataset.getMinMax(3);
        if (minMax != null) {
            if (minMax[0] < array[0]) {
                array[0] = minMax[0];
            }
            if (minMax[1] > array[1]) {
                array[1] = minMax[1];
            }
        }
        return array;
    }
    
    protected void doZAutoscale() {
        double[] zMinMax = { Double.MAX_VALUE, -1.7976931348623157E308 };
        for (int i = 0; i < super.dataSeries.size(); ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 6) {
                zMinMax = this.getZMinMax(nfDataSeries, zMinMax);
            }
        }
        final double[] a = this.a(zMinMax[0], zMinMax[1]);
        for (int j = 0; j < super.dataSeries.size(); ++j) {
            final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(j);
            final NFBubbleInfo nfBubbleInfo = (NFBubbleInfo)nfDataSeries2.info;
            if (nfDataSeries2.type == 6) {
                if (Double.isNaN(nfBubbleInfo.c)) {
                    nfBubbleInfo.c = a[0];
                }
                if (Double.isNaN(nfBubbleInfo.d)) {
                    nfBubbleInfo.d = a[1];
                }
            }
        }
    }
    
    protected void drawData(final Graphics graphics) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        if (this.b) {
            this.doZAutoscale();
        }
        int n = 0;
        for (int i = 0; i < size; ++i) {
            final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
            if (nfDataSeries.type == 6) {
                final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
                this.a(clippedGraphics, nfDataSeries, n);
                clippedGraphics.dispose();
                ++n;
            }
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n) {
        final NFBubbleInfo nfBubbleInfo = (NFBubbleInfo)nfDataSeries.info;
        int n2 = 0;
        if (nfBubbleInfo.a) {
            final int n3 = nfDataSeries.dataset.size() - nfBubbleInfo.b;
            nfBubbleInfo.a = false;
            nfBubbleInfo.b = 0;
            n2 = ((n3 < 0) ? 0 : n3);
        }
        this.drawPoints(graphics, nfDataSeries, n2, nfDataSeries.XAxis, nfDataSeries.YAxis, n);
    }
    
    protected void drawDataLite(final Graphics graphics) {
        this.drawData(graphics);
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
        final NFGraphSymbol nfGraphSymbol = new NFGraphSymbol();
        final NFBubbleInfo nfBubbleInfo = (NFBubbleInfo)nfDataSeries.info;
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
            int i = n;
            if (i > 0) {
                --i;
            }
            double nth = nfDataSeries.dataset.getNth(i, 1);
            double nth2 = nfDataSeries.dataset.getNth(i, 2);
            Point mapValue2 = nfAxis.mapValue(nth);
            Point mapValue3 = nfAxis2.mapValue(nth2);
            int n4 = 0;
            ++i;
            while (i < size) {
                final double nth3 = nfDataSeries.dataset.getNth(i, 1);
                final double nth4 = nfDataSeries.dataset.getNth(i, 2);
                final Point mapValue4 = nfAxis.mapValue(nth3);
                final Point mapValue5 = nfAxis2.mapValue(nth4);
                if (mapValue4 != null && mapValue5 != null && mapValue2 != null && mapValue3 != null && !this.clipLine(nth3, nth4, nth, nth2, min, max, min2, max2)) {
                    this.drawSlab(graphics, mapValue2.x + n3, mapValue3.y - n3, mapValue4.x + n3, mapValue5.y - n3, mapValue.y - n3, lineWidth, nfDataSeries.line, fillColor, nfDataSeries.fillColor, color, n4 == 0, true);
                    ++n4;
                }
                else {
                    n4 = 0;
                }
                nth = nth3;
                nth2 = nth4;
                mapValue2 = mapValue4;
                mapValue3 = mapValue5;
                ++i;
            }
        }
        for (int j = n; j < size; ++j) {
            final Color color3 = (nfDataSeries.sym != null) ? nfDataSeries.sym.getColor() : nfDataSeries.c;
            graphics.setColor((color3 == null) ? nfDataSeries.c : color3);
            final double nth5 = nfDataSeries.dataset.getNth(j, 1);
            final double nth6 = nfDataSeries.dataset.getNth(j, 2);
            final double nth7 = nfDataSeries.dataset.getNth(j, 3);
            final Point mapValue6 = nfAxis.mapValue(nth5);
            final Point mapValue7 = nfAxis2.mapValue(nth6);
            if (mapValue6 == null || mapValue7 == null || this.clipPoint(nth5, nth6, min, max, min2, max2)) {
                this.setActiveLabel(nfDataSeries, j, -1, -1, 0, 0, "");
            }
            else {
                final String label = nfAxis.getLabel(nth5);
                String s = nfAxis2.getLabel(nth6);
                final String a = this.a(nth7, nfAxis.getLabel(nth7));
                if (nfDataSeries.sym != null) {
                    final int size2 = nfDataSeries.sym.size;
                    if (nfBubbleInfo.e == 1) {
                        nfDataSeries.sym.size = (int)(Object)new Double(nfDataSeries.sym.size * ((nth7 - nfBubbleInfo.c) / (nfBubbleInfo.d - nfBubbleInfo.c)));
                    }
                    else {
                        nfDataSeries.sym.size = (int)(Object)new Double(Math.sqrt(new Double(new Double(3.141592653589793 * (nfDataSeries.sym.size / 2) * (nfDataSeries.sym.size / 2)) * ((nth7 - nfBubbleInfo.c) / (nfBubbleInfo.d - nfBubbleInfo.c))) / 3.141592653589793) * 2.0);
                    }
                    if (nfDataSeries.sym.size > 5000) {
                        nfDataSeries.sym.size = 5000;
                    }
                    nfDataSeries.sym.draw(graphics, mapValue6.x + n3, mapValue7.y - n3, nfDataSeries.pattern);
                    this.setActiveLabel(nfDataSeries, j, mapValue6.x + n3 - super.dwellOffset / 2, mapValue7.y - n3 - super.dwellOffset / 2, super.dwellOffset, super.dwellOffset, "(" + label + "," + s + "," + a + ")", nfDataSeries.sym);
                    nfDataSeries.sym.size = size2;
                }
                else {
                    s = s + "," + a;
                    this.setActiveLabel(nfDataSeries, j, mapValue6.x + n3, mapValue7.y - n3, super.dwellOffset, label, s);
                }
                if (nfBubbleInfo.f != null) {
                    final NFGraphSymbol nfGraphSymbol2 = new NFGraphSymbol();
                    nfGraphSymbol2.setOutlineColor(nfBubbleInfo.f);
                    graphics.setColor(nfBubbleInfo.f);
                    nfGraphSymbol2.draw(graphics, mapValue6.x + n3, mapValue7.y - n3, nfDataSeries.pattern);
                }
                if (nfDataSeries.valueLabelStyle != 0) {
                    this.drawLineValueLabel(graphics, nfDataSeries, mapValue6.x + n3, mapValue7.y - n3, s);
                }
            }
        }
        graphics.setColor(color2);
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineNumber("Set"));
        vector.addElement(super.param.defineDate("pX"));
        vector.addElement(super.param.defineDate("pY"));
        vector.addElement(super.param.defineDate("pZ"));
        super.param.defineActiveLabel("Active", vector);
        super.param.defineVector("AddDataPoint", super.param.defineTuple("singlePoint", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineString("BubbleSetName"));
        vector2.addElement(super.param.defineColor("BubbleSetColor", null));
        super.param.defineVector("BubbleSets", super.param.defineTuple("BubbleSetTuple", vector2));
        for (int i = 0; i < super.MaxDataSets; ++i) {
            final String string = "BubbleSet" + (i + 1);
            final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
            vector3.addElement(super.param.defineDate(string + "X"));
            vector3.addElement(super.param.defineDate(string + "Y"));
            vector3.addElement(super.param.defineDate(string + "Z"));
            super.param.defineVector("BubbleSet" + (i + 1), super.param.defineTuple(string + "Tuple", vector3));
        }
        this.defineDataAxisParams(super.param, "BubbleAxis");
        this.defineBubbleScale(super.param);
        this.defineBubbleSymbol(super.param);
        this.defineBubbleFormat(super.param);
        this.defineLineStyle(super.param);
        NFPatternFill.definePatternFillParam(super.param, "BubbleFillPattern");
        this.defineLineValueLabel(super.param);
    }
    
    protected void defineBubbleFormat(final NFParam nfParam) {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("FLOAT", new Integer(2));
        hashtable.put("INTEGER", new Integer(1));
        hashtable.put("DECIMAL", new Integer(5));
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineSymbol("BubbleFormatType", hashtable, new Integer(2)));
        vector.addElement(nfParam.defineString("BubbleFormatStr", null));
        nfParam.defineTuple("BubbleFormat", vector);
    }
    
    protected void defineBubbleScale(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(nfParam.defineNumber("BubbleScaleMinValue"));
        vector.addElement(nfParam.defineNumber("BubbleScaleMaxValue"));
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("AREA", new Integer(0));
        hashtable.put("DIAMETER", new Integer(1));
        vector.addElement(nfParam.defineSymbol("BubbleScaleScale", hashtable, new Integer(1)));
        vector.addElement(nfParam.defineColor("BubbleScalePointColor", null));
        nfParam.defineVector("BubbleScale", nfParam.defineTuple("BubbleScaleTuple", vector));
    }
    
    protected void defineBubbleSymbol(final NFParam nfParam) {
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        final Hashtable symbolTypeTable = nfParam.getSymbolTypeTable();
        final Hashtable symbolStyleTable = nfParam.getSymbolStyleTable();
        vector.addElement(nfParam.defineSymbol("BubbleSymbolType", symbolTypeTable, null));
        vector.addElement(nfParam.defineNumber("BubbleSymbolSize", null));
        vector.addElement(nfParam.defineSymbol("BubbleSymbolStyle", symbolStyleTable, null));
        vector.addElement(nfParam.defineColor("BubbleSymbolBorderColor", null));
        vector.addElement(nfParam.defineNumber("BubbleSymbolBorderWidth", null));
        vector.addElement(nfParam.defineColor("BubbleSymbolColor", null));
        nfParam.defineVector("BubbleSymbol", nfParam.defineTuple("BubbleSymbolTuple", vector));
    }
    
    protected synchronized void loadParams() throws Exception {
        super.loadParams();
        if (super.param.changed("AddDataPoint")) {
            final Vector vector = (Vector)super.param.get("AddDataPoint");
            if (vector == null || vector.size() == 0) {
                return;
            }
            final Enumeration<Vector<Number>> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final Vector<Number> vector2 = elements.nextElement();
                int n;
                Number element;
                Number element2;
                Number element3;
                try {
                    n = vector2.elementAt(0).intValue() - 1;
                    element = vector2.elementAt(1);
                    element2 = vector2.elementAt(2);
                    element3 = vector2.elementAt(3);
                }
                catch (Exception ex2) {
                    NFDebug.print("NFBubbleChart: AddDataPoint: first four attributes are needed");
                    continue;
                }
                if (n < 0 || n >= super.dataSeries.size()) {
                    NFDebug.print("NFBubbleChart: AddDataPoint: Illegal data set ID");
                }
                else {
                    try {
                        final NFDataSeries nfDataSeries = super.dataSeries.elementAt(n);
                        final NFBubbleInfo nfBubbleInfo = (NFBubbleInfo)nfDataSeries.info;
                        nfBubbleInfo.a = true;
                        final NFBubbleInfo nfBubbleInfo2 = nfBubbleInfo;
                        ++nfBubbleInfo2.b;
                        nfDataSeries.dataset.addPoint(nfDataSeries.XAxis.getValue(element), nfDataSeries.YAxis.getValue(element2), nfDataSeries.XAxis.getValue(element3));
                        super.incrementalUpdate = true;
                        if (super.dwell == null) {
                            continue;
                        }
                        nfDataSeries.activeLabels.addElement(super.dwell.addLabel(NFActiveLabel.loadParams(super.param, vector2, 3)));
                    }
                    catch (Exception ex) {
                        NFDebug.print("NFBubbleChart: AddDataPoint: " + ex.getMessage());
                    }
                }
            }
        }
        boolean loadDataSetParams = this.loadDataSetParams(super.param, 6, "BubbleSets", "BubbleAxis", super.bottomAxis, super.leftAxis);
        final boolean[] loadDataSets = this.loadDataSets(super.param, 6, "BubbleSet", loadDataSetParams);
        this.loadActiveLabels(super.param, 6, "ActiveLabels", loadDataSets);
        this.loadPatternFill(super.param, 6, "BubbleFillPattern");
        if (loadDataSetParams || super.param.changed("BubbleScale")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadBubbleScale(super.param);
        }
        if (loadDataSetParams || super.param.changed("BubbleSymbol")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadBubbleSymbol(super.param);
        }
        if (loadDataSetParams || super.param.changed("LineStyle")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadLineStyle(super.param);
        }
        if (loadDataSetParams || super.param.changed("BubbleFormat")) {
            loadDataSetParams = true;
            super.graphChanged = true;
            this.loadBubbleFormat(super.param);
        }
        this.loadLineValueLabel(super.param, 6);
        for (int i = 0; i < loadDataSets.length; ++i) {
            if (loadDataSetParams || loadDataSets[i]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
    }
    
    protected void loadBubbleFormat(final NFParam nfParam) throws NFParamException {
        final Vector vector = (Vector)nfParam.get("BubbleFormat");
        this.formatType = NFUtil.getNumber(vector, 0, this.FORMAT_TYPE_DEFAULT);
        this.formatStr = NFUtil.getString(vector, 1, this.FORMAT_STR_DEFAULT);
    }
    
    protected void loadDataSetParams(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 6) {
            super.loadDataSetParams(nfDataSeries, n, o);
            return;
        }
        if (nfDataSeries.dataset == null) {
            nfDataSeries.dataset = new NFDataSet();
        }
        else {
            nfDataSeries.dataset.clear();
        }
        if (nfDataSeries.info == null) {
            nfDataSeries.info = new NFBubbleInfo();
        }
        final Vector vector = (Vector)o;
        nfDataSeries.name = vector.elementAt(0);
        nfDataSeries.c = (Color)vector.elementAt(1);
        if (nfDataSeries.c == null) {
            nfDataSeries.c = this.defaultColor(n);
        }
        nfDataSeries.sym = new NFGraphSymbol();
        nfDataSeries.sym.type = 1;
        nfDataSeries.sym.size = 10;
        nfDataSeries.sym.style = 2;
        (nfDataSeries.line = new NFLine(null)).setStyle(0);
        nfDataSeries.line.setThickness(1);
        nfDataSeries.line.setColor(nfDataSeries.c);
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 6) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        final Vector vector = (Vector)o;
        nfDataSeries.dataset.addPoint(nfDataSeries.XAxis.getValue(vector.elementAt(0)), nfDataSeries.YAxis.getValue(vector.elementAt(1)), nfDataSeries.XAxis.getValue(vector.elementAt(2)));
    }
    
    protected void loadBubbleScale(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("BubbleScale");
        if (vector == null || vector.size() == 0 || super.dataSeries == null) {
            return;
        }
        Vector<Number> vector2 = null;
        final int dataSetCount = this.getDataSetCount(6);
        this.b = false;
        for (int i = 0; i < dataSetCount; ++i) {
            if (i < vector.size()) {
                vector2 = vector.elementAt(i);
            }
            if (vector2 == null) {
                break;
            }
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 6);
            if (dataSeries == null) {
                break;
            }
            if (dataSeries.info == null) {
                dataSeries.info = new NFBubbleInfo();
            }
            final NFBubbleInfo nfBubbleInfo = (NFBubbleInfo)dataSeries.info;
            final Number n = vector2.elementAt(0);
            if (n != null && !Double.isNaN(n.doubleValue())) {
                nfBubbleInfo.c = n.intValue();
            }
            else {
                nfBubbleInfo.c = Double.NaN;
                this.b = true;
            }
            final Number n2 = vector2.elementAt(1);
            if (n2 != null && !Double.isNaN(n2.doubleValue())) {
                nfBubbleInfo.d = n2.intValue();
            }
            else {
                nfBubbleInfo.d = Double.NaN;
                this.b = true;
            }
            final Number n3 = vector2.elementAt(2);
            if (n3 != null && !Double.isNaN(n3.doubleValue())) {
                nfBubbleInfo.e = n3.intValue();
            }
            nfBubbleInfo.f = (Color)vector2.elementAt(3);
        }
    }
    
    protected void loadBubbleSymbol(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("BubbleSymbol");
        if (vector == null || vector.size() == 0 || super.dataSeries == null) {
            return;
        }
        Vector<Number> vector2 = null;
        for (int dataSetCount = this.getDataSetCount(6), i = 0; i < dataSetCount; ++i) {
            if (i < vector.size()) {
                vector2 = vector.elementAt(i);
            }
            if (vector2 == null) {
                break;
            }
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 6);
            if (dataSeries == null) {
                break;
            }
            if (dataSeries.sym == null) {
                dataSeries.sym = new NFGraphSymbol();
                dataSeries.sym.type = 1;
                dataSeries.sym.size = 10;
                dataSeries.sym.style = 2;
                dataSeries.sym.setScale(super.scale);
            }
            final Number n = vector2.elementAt(0);
            if (n != null && !Double.isNaN(n.doubleValue())) {
                dataSeries.sym.type = n.intValue();
            }
            final Number n2 = vector2.elementAt(1);
            if (n2 != null && !Double.isNaN(n2.doubleValue())) {
                dataSeries.sym.size = n2.intValue();
            }
            final Number n3 = vector2.elementAt(2);
            if (n3 != null && !Double.isNaN(n3.doubleValue())) {
                dataSeries.sym.style = n3.intValue();
            }
            final Color outlineColor = (Color)vector2.elementAt(3);
            if (outlineColor != null) {
                dataSeries.sym.setOutlineColor(outlineColor);
            }
            final Number n4 = vector2.elementAt(4);
            if (n4 != null && !Double.isNaN(n4.doubleValue())) {
                dataSeries.sym.setOutlineWidth(n4.intValue());
            }
            dataSeries.sym.setColor(NFUtil.getColor(vector2, 5, null));
        }
    }
    
    protected void loadLineStyle(final NFParam nfParam) throws Exception {
        final Vector vector = (Vector)nfParam.get("LineStyle");
        if (vector == null || vector.size() == 0 || super.dataSeries == null) {
            return;
        }
        final int dataSetCount = this.getDataSetCount(6);
        Vector<Number> vector2 = null;
        for (int i = 0; i < dataSetCount; ++i) {
            if (i < vector.size()) {
                vector2 = vector.elementAt(i);
            }
            if (vector2 == null) {
                break;
            }
            final NFDataSeries dataSeries = this.getDataSeries(i + 1, 6);
            if (dataSeries == null) {
                break;
            }
            if (dataSeries.line == null) {
                (dataSeries.line = new NFLine(null)).setStyle(0);
                dataSeries.line.setThickness(1);
                dataSeries.line.setColor(Color.black);
                dataSeries.line.setScale(super.scale);
            }
            final Number n = vector2.elementAt(0);
            if (n != null && !Double.isNaN(n.doubleValue())) {
                dataSeries.line.setStyle(n.intValue());
            }
            final Number n2 = vector2.elementAt(1);
            if (n2 != null && !Double.isNaN(n2.doubleValue())) {
                dataSeries.line.setThickness(n2.intValue());
            }
            dataSeries.line.setColor((Color)vector2.elementAt(2));
            if (dataSeries.line.getColor() == null) {
                dataSeries.line.setColor(dataSeries.c);
            }
            dataSeries.fillColor = (Color)vector2.elementAt(3);
        }
    }
    
    private String a(final double n, final String s) {
        if (Double.isNaN(n)) {
            return "NaN";
        }
        switch (this.formatType) {
            case 1:
            case 2:
            case 5: {
                return NFUtil.formatNumericValue(n, this.formatType, this.formatStr, this.getGroupSize(), this.getGroupSymbol(), this.getDecimalSymbol());
            }
            default: {
                return s;
            }
        }
    }
}
