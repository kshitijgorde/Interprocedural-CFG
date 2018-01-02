// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFDataSet;
import java.util.Enumeration;
import netcharts.util.NFDebug;
import java.awt.Color;
import netcharts.util.NFParamDef;
import java.util.Vector;
import java.awt.Graphics;
import java.applet.Applet;

public class NFXYChart extends NFDataChart
{
    private static final boolean a = false;
    private int b;
    private int c;
    private int d;
    private boolean e;
    
    public NFXYChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = true;
        this.initGraph(applet);
        this.initXYChart();
        this.reshape(n, n2, n3, n4);
    }
    
    public NFXYChart(final Applet applet) {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = true;
        this.initGraph(applet);
        this.initXYChart();
    }
    
    protected void initXYChart() {
        super.initChart();
    }
    
    public void reset() {
        this.setDefaultAxes();
        this.setDefaultGrid();
    }
    
    protected void drawData(final Graphics graphics) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        if (this.getLineDepth() > 0) {
            int n = 0;
            for (int i = 0; i < size; ++i) {
                if (((NFDataSeries)super.dataSeries.elementAt(i)).type == 2) {
                    ++n;
                }
            }
            int n2 = 0;
            for (int j = size - 1; j >= 0; --j) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(j);
                if (nfDataSeries.type == 2) {
                    ++n2;
                    this.a(graphics, nfDataSeries, n - n2);
                }
            }
        }
        else {
            int n3 = 0;
            for (int k = 0; k < size; ++k) {
                final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(k);
                if (nfDataSeries2.type == 2) {
                    this.a(graphics, nfDataSeries2, n3);
                    ++n3;
                }
            }
        }
    }
    
    private void a(final Graphics graphics, final NFDataSeries nfDataSeries, final int n) {
        final NFXYInfo nfxyInfo = (NFXYInfo)nfDataSeries.info;
        int n2 = 0;
        if (nfxyInfo.a) {
            final int n3 = nfDataSeries.dataset.size() - nfxyInfo.b;
            nfxyInfo.a = false;
            nfxyInfo.b = 0;
            n2 = ((n3 < 0) ? 0 : n3);
        }
        final Graphics clippedGraphics = this.createClippedGraphics(graphics, nfDataSeries.XAxis, nfDataSeries.YAxis);
        this.drawPoints(clippedGraphics, nfDataSeries, n2, nfDataSeries.XAxis, nfDataSeries.YAxis, n);
        clippedGraphics.dispose();
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
        vector.addElement(super.param.defineString("DataSetName"));
        vector.addElement(super.param.defineColor("DataSetColor", null));
        this.b = 2;
        this.c = super.param.defineGraphSymbol("DataSet", vector);
        this.d = super.param.defineLine("DataSet", vector);
        vector.addElement(super.param.defineColor("DataSetFillColor", null));
        super.param.defineVector("DataSets", super.param.defineTuple("DataSet", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineDate("X"));
        vector2.addElement(super.param.defineDate("Y"));
        final NFParamDef defineTuple = super.param.defineTuple("DataPoint", vector2);
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("DataSet" + (i + 1), defineTuple);
        }
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineNumber("Set"));
        vector3.addElement(super.param.defineDate("pX"));
        vector3.addElement(super.param.defineDate("pY"));
        super.param.defineActiveLabel("Active", vector3);
        super.param.defineVector("AddDataPoint", super.param.defineTuple("singlePoint", vector3));
        this.defineDataAxisParams(super.param, "DataAxis");
        final Vector<NFParamDef> vector4 = new Vector<NFParamDef>();
        vector4.addElement(super.param.defineString("LineSetName"));
        vector4.addElement(super.param.defineColor("LineSetColor", null));
        super.param.defineVector("LineSets", super.param.defineTuple("LineSetTuple", vector4));
        for (int j = 0; j < super.MaxDataSets; ++j) {
            final String string = "LineSet" + (j + 1);
            final Vector<NFParamDef> vector5 = new Vector<NFParamDef>();
            vector5.addElement(super.param.defineDate(string + "X"));
            vector5.addElement(super.param.defineDate(string + "Y"));
            super.param.defineVector("LineSet" + (j + 1), super.param.defineTuple(string + "Tuple", vector5));
        }
        this.defineDataAxisParams(super.param, "LineAxis");
        this.defineLineSymbol(super.param);
        this.defineLineStyle(super.param);
        NFPatternFill.definePatternFillParam(super.param, "LineFillPattern");
        this.defineLineValueLabel(super.param);
    }
    
    public void clean() {
        super.clean();
        try {
            final NFParamDef paramDef = super.param.getParamDef("DataSets");
            if (paramDef != null) {
                paramDef.loaded = false;
            }
        }
        catch (Exception ex) {}
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
                try {
                    n = vector2.elementAt(0).intValue() - 1;
                    element = vector2.elementAt(1);
                    element2 = vector2.elementAt(2);
                }
                catch (Exception ex2) {
                    NFDebug.print("NFXYChart: AddDataPoint: first three attributes are needed");
                    continue;
                }
                if (n < 0 || n >= super.dataSeries.size()) {
                    NFDebug.print("NFXYChart: AddDataPoint: Illegal data set ID");
                }
                else {
                    try {
                        final NFDataSeries nfDataSeries = super.dataSeries.elementAt(n);
                        final NFXYInfo nfxyInfo = (NFXYInfo)nfDataSeries.info;
                        nfxyInfo.a = true;
                        final NFXYInfo nfxyInfo2 = nfxyInfo;
                        ++nfxyInfo2.b;
                        nfDataSeries.dataset.addPoint(nfDataSeries.XAxis.getValue(element), nfDataSeries.YAxis.getValue(element2));
                        super.incrementalUpdate = true;
                        if (super.dwell == null) {
                            continue;
                        }
                        nfDataSeries.activeLabels.addElement(super.dwell.addLabel(NFActiveLabel.loadParams(super.param, vector2, 3)));
                    }
                    catch (Exception ex) {
                        NFDebug.print("NFXYChart: AddDataPoint: " + ex.getMessage());
                    }
                }
            }
        }
        this.e = true;
        boolean b = this.loadDataSetParams(super.param, 2, "DataSets", "DataAxis", super.bottomAxis, super.leftAxis);
        if (!super.param.getParamDef("DataSets").loaded) {
            this.e = false;
            b = this.loadDataSetParams(super.param, 2, "LineSets", "LineAxis", super.bottomAxis, super.leftAxis);
        }
        boolean[] array;
        if (this.e) {
            array = this.loadDataSets(super.param, 2, "DataSet", b);
        }
        else {
            array = this.loadDataSets(super.param, 2, "LineSet", b);
        }
        this.loadActiveLabels(super.param, 2, "ActiveLabels", array);
        this.loadPatternFill(super.param, 2, "LineFillPattern");
        if (b || super.param.changed("LineSymbol")) {
            b = true;
            super.graphChanged = true;
            this.loadLineSymbol(super.param);
        }
        if (b || super.param.changed("LineStyle")) {
            b = true;
            super.graphChanged = true;
            this.loadLineStyle(super.param);
        }
        this.loadLineValueLabel(super.param);
        for (int i = 0; i < array.length; ++i) {
            if (b || array[i]) {
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
        if (nfDataSeries.info == null) {
            nfDataSeries.info = new NFXYInfo();
        }
        final Vector vector = (Vector)o;
        nfDataSeries.name = vector.elementAt(0);
        nfDataSeries.c = (Color)vector.elementAt(1);
        if (nfDataSeries.c == null) {
            nfDataSeries.c = this.defaultColor(n);
        }
        if (this.e) {
            nfDataSeries.sym = NFGraphSymbol.loadParams(super.param, vector, this.b);
            nfDataSeries.line = NFLine.loadParams(super.param, vector, this.b + this.c);
            if (nfDataSeries.line != null && nfDataSeries.line.getColor() == null) {
                nfDataSeries.line.setColor(nfDataSeries.c);
            }
            nfDataSeries.fillColor = (Color)vector.elementAt(this.b + this.c + this.d);
        }
        else {
            nfDataSeries.sym = new NFGraphSymbol();
            nfDataSeries.sym.type = 1;
            nfDataSeries.sym.size = 4;
            nfDataSeries.sym.style = 1;
            (nfDataSeries.line = new NFLine(null)).setStyle(0);
            nfDataSeries.line.setThickness(1);
            nfDataSeries.line.setColor(nfDataSeries.c);
        }
    }
    
    protected void loadDataItem(final NFDataSeries nfDataSeries, final int n, final Object o) {
        if (nfDataSeries.type != 2) {
            super.loadDataItem(nfDataSeries, n, o);
            return;
        }
        final Vector vector = (Vector)o;
        nfDataSeries.dataset.addPoint(nfDataSeries.XAxis.getValue(vector.elementAt(0)), nfDataSeries.YAxis.getValue(vector.elementAt(1)));
    }
}
