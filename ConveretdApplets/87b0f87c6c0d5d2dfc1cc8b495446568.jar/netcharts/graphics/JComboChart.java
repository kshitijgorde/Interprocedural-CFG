// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import netcharts.util.NFDataSet;
import netcharts.util.NFParamException;
import java.util.Hashtable;
import netcharts.util.NFParam;
import java.awt.Color;
import netcharts.util.NFParamDef;
import java.awt.Graphics;
import java.applet.Applet;
import java.util.Vector;

public class JComboChart extends JBarchart
{
    protected static final int BAR = 10;
    protected static final int LINE = 11;
    protected static final int LINEFILL = 12;
    protected Vector drawOrder;
    private static final boolean a = false;
    private int b;
    private int c;
    private int d;
    
    public JComboChart(final Applet applet) {
        super(applet);
        this.drawOrder = this.getDefaultDataDrawOrder();
    }
    
    public JComboChart(final Applet applet, final int n, final int n2, final int n3, final int n4) {
        super(applet, n, n2, n3, n4);
        this.drawOrder = this.getDefaultDataDrawOrder();
    }
    
    protected void drawData(final Graphics graphics) {
        this.drawData(graphics, (this.drawOrder != null) ? ((Vector)this.drawOrder.clone()) : null);
    }
    
    protected void drawData(final Graphics graphics, final Vector vector) {
        final int size = super.dataSeries.size();
        if (size < 1) {
            return;
        }
        while (vector != null && vector.size() > 0) {
            final Number n = vector.elementAt(0);
            if (n == null) {
                vector.removeElementAt(0);
            }
            else {
                switch (n.intValue()) {
                    case 10: {
                        super.drawData(graphics);
                        vector.removeElementAt(0);
                        continue;
                    }
                    case 12: {
                        this.a(graphics, size, true);
                        vector.removeElementAt(0);
                        continue;
                    }
                    case 11: {
                        this.a(graphics, size, false);
                        vector.removeElementAt(0);
                        continue;
                    }
                    default: {}
                }
            }
        }
    }
    
    protected int get3DDepth(final boolean b) {
        if (!b) {
            return super.defaultDepth;
        }
        int get3DDepth = super.get3DDepth(b);
        if (super.line3D > 0) {
            final int n = super.line3D * this.getDataSetCount(2);
            if (n > get3DDepth) {
                get3DDepth = n;
            }
        }
        return super.defaultDepth = get3DDepth;
    }
    
    private void a(final Graphics graphics, final int n, final boolean b) {
        if (this.getLineDepth() > 0) {
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                final NFDataSeries nfDataSeries = super.dataSeries.elementAt(i);
                if (nfDataSeries.type == 2) {
                    if (!b || nfDataSeries.fillColor != null) {
                        if (b || nfDataSeries.fillColor == null) {
                            ++n2;
                        }
                    }
                }
            }
            int n3 = 0;
            for (int j = n - 1; j >= 0; --j) {
                final NFDataSeries nfDataSeries2 = super.dataSeries.elementAt(j);
                if (nfDataSeries2.type == 2) {
                    if (!b || nfDataSeries2.fillColor != null) {
                        if (b || nfDataSeries2.fillColor == null) {
                            ++n3;
                            final Graphics clippedGraphics = this.createClippedGraphics(graphics, this.getXAxis(nfDataSeries2.XAxis, nfDataSeries2.YAxis), this.getYAxis(nfDataSeries2.YAxis, nfDataSeries2.XAxis));
                            this.drawPoints(clippedGraphics, nfDataSeries2, 0, nfDataSeries2.XAxis, nfDataSeries2.YAxis, n2 - n3);
                            clippedGraphics.dispose();
                        }
                    }
                }
            }
        }
        else {
            int n4 = 0;
            for (int k = 0; k < n; ++k) {
                final NFDataSeries nfDataSeries3 = super.dataSeries.elementAt(k);
                if (nfDataSeries3.type == 2) {
                    if (!b || nfDataSeries3.fillColor != null) {
                        if (b || nfDataSeries3.fillColor == null) {
                            final Graphics clippedGraphics2 = this.createClippedGraphics(graphics, this.getXAxis(nfDataSeries3.XAxis, nfDataSeries3.YAxis), this.getYAxis(nfDataSeries3.YAxis, nfDataSeries3.XAxis));
                            this.drawPoints(clippedGraphics2, nfDataSeries3, 0, nfDataSeries3.XAxis, nfDataSeries3.YAxis, n4);
                            clippedGraphics2.dispose();
                            ++n4;
                        }
                    }
                }
            }
        }
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("LineSetName"));
        vector.addElement(super.param.defineColor("LineSetColor", null));
        this.b = 2;
        this.c = super.param.defineGraphSymbol("LineSet", vector);
        this.d = super.param.defineLine("LineSet", vector);
        vector.addElement(super.param.defineColor("LineSetFillColor", null));
        super.param.defineVector("LineSets", super.param.defineTuple("LineSet", vector));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineDate("X"));
        vector2.addElement(super.param.defineDate("Y"));
        final NFParamDef defineTuple = super.param.defineTuple("LinePoint", vector2);
        for (int i = 0; i < super.MaxDataSets; ++i) {
            super.param.defineVector("LineSet" + (i + 1), defineTuple);
            super.param.defineActiveLabel("LineLabels" + (i + 1));
        }
        super.param.defineString("LineLabelsEnabled", "ON");
        this.defineDataAxisParams(super.param, "LineAxis");
        this.defineLineSymbol(super.param);
        this.defineLineStyle(super.param);
        NFPatternFill.definePatternFillParam(super.param, "LineFillPattern");
        this.defineDataDrawOrder(super.param);
        this.defineLineValueLabel(super.param);
    }
    
    protected void defineDataDrawOrder(final NFParam nfParam) {
        nfParam.defineVector("DrawOrder", nfParam.defineSymbol("DrawOrderElement", this.getDataDrawOrderSymbols(), null));
    }
    
    protected Hashtable getDataDrawOrderSymbols() {
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("BAR", new Integer(10));
        hashtable.put("LINEFILL", new Integer(12));
        hashtable.put("LINE", new Integer(11));
        return hashtable;
    }
    
    protected Vector getDefaultDataDrawOrder() {
        final Vector<Integer> vector = new Vector<Integer>();
        vector.addElement(new Integer(12));
        vector.addElement(new Integer(10));
        vector.addElement(new Integer(11));
        return vector;
    }
    
    protected void loadDataDrawOrder(final NFParam nfParam) throws NFParamException {
        if (nfParam.changed("DrawOrder")) {
            this.drawOrder = (Vector)nfParam.get("DrawOrder");
            if (this.drawOrder == null || this.drawOrder.size() == 0) {
                this.drawOrder = this.getDefaultDataDrawOrder();
            }
        }
    }
    
    protected synchronized void loadParams() throws Exception {
        super.loadParams();
        boolean loadDataSetParams = this.loadDataSetParams(super.param, 2, "LineSets", "LineAxis", super.bottomAxis, (super.rightAxis != null && super.rightAxis.getShowTics()) ? super.rightAxis : super.leftAxis);
        final boolean[] loadDataSets = this.loadDataSets(super.param, 2, "LineSet", loadDataSetParams);
        this.loadActiveLabels(super.param, 2, "LineLabels", loadDataSets);
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
        this.loadLineValueLabel(super.param);
        for (int i = 0; i < loadDataSets.length; ++i) {
            if (loadDataSetParams || loadDataSets[i]) {
                this.reset();
                super.layoutChanged = true;
                break;
            }
        }
        this.loadDataDrawOrder(super.param);
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
            nfDataSeries.c = this.defaultColor(super.dataSeries.indexOf(nfDataSeries));
        }
        nfDataSeries.sym = NFGraphSymbol.loadParams(super.param, vector, this.b);
        nfDataSeries.line = NFLine.loadParams(super.param, vector, this.b + this.c);
        if (nfDataSeries.line != null && nfDataSeries.line.getColor() == null) {
            nfDataSeries.line.setColor(nfDataSeries.c);
        }
        nfDataSeries.fillColor = (Color)vector.elementAt(this.b + this.c + this.d);
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
