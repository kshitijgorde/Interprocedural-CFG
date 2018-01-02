// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Hashtable;
import netcharts.util.NFParamDef;
import java.util.Vector;
import java.applet.Applet;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import netcharts.util.NFParamObserver;

public class JMultiChart extends JGraph
{
    public static final int COLS = 1;
    public static final int ROWS = 2;
    public static final int STATIC = 1;
    public static final int DYNAMIC = 2;
    public static final int MaxCharts = 20;
    private NFParamObserver a;
    
    public JMultiChart() {
        this.a = null;
        this.setLayout(new BorderLayout());
    }
    
    public JMultiChart(final Applet applet, final NFParamObserver a) {
        this.a = null;
        this.a = a;
        this.initGraph(applet);
        this.setLayout(new BorderLayout());
    }
    
    protected void addBannerButton() {
    }
    
    protected void createDwell(final NFLabel nfLabel) {
    }
    
    public void start(final Applet applet) {
        super.start(applet);
        if (this.a != null) {
            try {
                this.a.paramChanged(this, "START", null);
            }
            catch (Exception ex) {}
        }
    }
    
    public void stop(final Applet applet) {
        super.stop(applet);
        if (this.a != null) {
            try {
                this.a.paramChanged(this, "STOP", null);
            }
            catch (Exception ex) {}
        }
    }
    
    protected void defineParams() {
        if (super.param != null) {
            return;
        }
        super.defineParams();
        final Vector<NFParamDef> vector = new Vector<NFParamDef>();
        vector.addElement(super.param.defineString("Multi-ChartName", null));
        vector.addElement(super.param.defineString("Multi-ChartType", "PIECHART"));
        vector.addElement(super.param.defineNumber("Multi-ChartWidth", new Integer(0)));
        vector.addElement(super.param.defineNumber("Multi-ChartHeight", new Integer(0)));
        super.param.defineVector("Charts", super.param.defineTuple("ChartsItem", vector));
        for (int i = 0; i < 20; ++i) {
            super.param.defineString("ChartScript" + (i + 1), null);
            super.param.defineString("ChartURL" + (i + 1), null);
        }
        final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
        hashtable.put("COL", new Integer(1));
        hashtable.put("COLS", new Integer(1));
        hashtable.put("COLUMN", new Integer(1));
        hashtable.put("COLUMNS", new Integer(1));
        hashtable.put("ROW", new Integer(2));
        hashtable.put("ROWS", new Integer(2));
        final Vector<NFParamDef> vector2 = new Vector<NFParamDef>();
        vector2.addElement(super.param.defineSymbol("LayoutType", hashtable, new Integer(1)));
        super.param.defineTuple("Layout", vector2);
        final Hashtable<String, Integer> hashtable2 = new Hashtable<String, Integer>();
        hashtable2.put("STATIC", new Integer(1));
        hashtable2.put("DYNAMIC", new Integer(2));
        final Vector<NFParamDef> vector3 = new Vector<NFParamDef>();
        vector3.addElement(super.param.defineColor("SashColor", Color.lightGray));
        vector3.addElement(super.param.defineNumber("SashWidth", new Integer(0)));
        vector3.addElement(super.param.defineSymbol("SashMode", hashtable2, new Integer(1)));
        super.param.defineTuple("Sash", vector3);
    }
    
    protected void loadParams() throws Exception {
        super.loadParams();
        if (this.a != null) {
            this.a.paramChanged(this, "LOADPARAMS", super.param);
        }
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            this.getComponent(i).setBackground(this.getBackground());
        }
        super.paint(graphics);
        this.paintChildren(graphics);
    }
}
