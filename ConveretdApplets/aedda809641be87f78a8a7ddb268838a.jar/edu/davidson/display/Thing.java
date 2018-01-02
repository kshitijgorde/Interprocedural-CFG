// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.Polygon;
import java.util.Vector;
import java.awt.Font;
import edu.davidson.tools.SApplet;
import java.awt.Color;
import edu.davidson.tools.SDataListener;
import edu.davidson.tools.SDataSource;

public class Thing implements SDataSource, SDataListener
{
    static Color darkGreen;
    static Color lightGreen;
    protected boolean noDrag;
    protected boolean visible;
    protected boolean resizable;
    protected boolean highlight;
    protected int hotSpot;
    protected SApplet applet;
    protected int w;
    protected int h;
    protected int s;
    protected Color color;
    protected double x;
    protected double y;
    protected SScalable canvas;
    protected Constraint constraint;
    protected Font font;
    protected Format format;
    protected int xDisplayOff;
    protected int yDisplayOff;
    protected String[] varStrings;
    protected double[][] ds;
    protected String label;
    Thing myMaster;
    Vector mySlaves;
    Color highlightColor;
    Polygon trail;
    int trailSize;
    
    public Thing(final SScalable canvas) {
        this.noDrag = true;
        this.visible = true;
        this.resizable = true;
        this.highlight = false;
        this.hotSpot = 0;
        this.applet = null;
        this.w = 5;
        this.h = 5;
        this.s = 5;
        this.color = Color.black;
        this.constraint = null;
        this.font = new Font("Helvetica", 1, 14);
        this.format = new Format("%-+6.3g");
        this.xDisplayOff = 0;
        this.yDisplayOff = 0;
        this.varStrings = new String[] { "x", "y" };
        this.ds = new double[1][2];
        this.label = null;
        this.myMaster = null;
        this.mySlaves = new Vector();
        this.highlightColor = Color.red;
        this.trail = new Polygon();
        this.trailSize = 0;
        this.canvas = canvas;
        try {
            SApplet.addDataSource(this);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            SApplet.addDataListener(this);
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public Thing(final SScalable sScalable, final double x, final double y) {
        this(sScalable);
        this.x = x;
        this.y = y;
    }
    
    public int getID() {
        return this.hashCode();
    }
    
    public final boolean isNoDrag() {
        return this.noDrag;
    }
    
    public final void setNoDrag(final boolean noDrag) {
        this.noDrag = noDrag;
    }
    
    public void setOwner(final SApplet applet) {
        this.applet = applet;
    }
    
    public SApplet getOwner() {
        return this.applet;
    }
    
    public String[] getVarStrings() {
        return this.varStrings;
    }
    
    public boolean isInsideThing(final int n, final int n2) {
        return false;
    }
    
    public final int getSize() {
        return this.s;
    }
    
    public void setSize(final int s) {
        this.s = s;
    }
    
    public final Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public final void setDragable(final boolean b) {
        this.noDrag = !b;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setHighlight(final boolean highlight) {
        this.highlight = highlight;
    }
    
    public final boolean isHighlight() {
        return this.highlight;
    }
    
    public final boolean isVisible() {
        return this.visible;
    }
    
    public void setResizable(final boolean resizable) {
        this.resizable = resizable;
    }
    
    public final boolean isResizable() {
        return this.resizable;
    }
    
    public final Thing getMaster() {
        return this.myMaster;
    }
    
    public final Vector getSlaves() {
        return this.mySlaves;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public final boolean setFormat(final String s) {
        try {
            this.format = new Format(s);
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
    
    public final boolean setFont(final Font font) {
        this.font = font;
        if (this.font == null) {
            this.font = new Font("Helvetica", 1, 14);
        }
        return true;
    }
    
    public void setDisplayOff(final int xDisplayOff, final int yDisplayOff) {
        this.xDisplayOff = xDisplayOff;
        this.yDisplayOff = yDisplayOff;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        if (this.myMaster != null) {
            this.x = this.myMaster.x;
            return;
        }
        this.x = x;
        if (this.constraint != null) {
            this.constraint.enforceConstraint(this);
        }
    }
    
    public void setXY(final double x, final double y) {
        if (this.myMaster != null) {
            this.x = this.myMaster.x;
            this.y = this.myMaster.y;
            return;
        }
        this.x = x;
        this.y = y;
        if (this.constraint != null) {
            this.constraint.enforceConstraint(this);
        }
        this.updateMySlaves();
    }
    
    public void dragMe(final double n, final double n2) {
        this.setXY(n, n2);
    }
    
    public final int getXPix() {
        return this.canvas.pixFromX(this.x);
    }
    
    public final double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        if (this.myMaster != null) {
            this.y = this.myMaster.y;
            return;
        }
        this.y = y;
        if (this.constraint != null) {
            this.constraint.enforceConstraint(this);
        }
    }
    
    public final int getYPix() {
        return this.canvas.pixFromY(this.y);
    }
    
    public final int getHeight() {
        return this.h;
    }
    
    public void setHeight(final int h) {
        this.h = h;
    }
    
    public final int getWidth() {
        return this.w;
    }
    
    public void setWidth(final int w) {
        this.w = w;
    }
    
    public void setConstraint(final Constraint constraint) {
        if (constraint == null) {
            return;
        }
        (this.constraint = constraint).enforceConstraint(this);
    }
    
    public void addSlave(final Thing thing) {
        if (this.myMaster == thing) {
            this.myMaster = null;
        }
        this.mySlaves.addElement(thing);
        thing.myMaster = this;
        thing.setVarsFromMaster();
    }
    
    public void setVarsFromMaster() {
        if (this.myMaster == null) {
            return;
        }
        this.x = this.myMaster.x;
        this.y = this.myMaster.y;
        final Enumeration<Thing> elements = this.mySlaves.elements();
        while (elements.hasMoreElements()) {
            final Thing thing = elements.nextElement();
            if (thing != this && thing.myMaster == this) {
                thing.setVarsFromMaster();
            }
        }
    }
    
    public void updateMySlaves() {
        final Enumeration<Thing> elements = this.mySlaves.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().setVarsFromMaster();
        }
    }
    
    public void paintMySlaves(final Graphics graphics) {
        final Enumeration<Thing> elements = this.mySlaves.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(graphics);
        }
    }
    
    public double[][] getVariables() {
        this.ds[0][0] = this.x;
        this.ds[0][1] = this.y;
        return this.ds;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void paintHighlight(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void clearSeries(final int n) {
    }
    
    public void deleteSeries(final int n) {
    }
    
    public void addDatum(final SDataSource sDataSource, final int n, final double n2, final double n3) {
        this.setXY(n2, n3);
        if (this.canvas instanceof Canvas) {
            ((Canvas)this.canvas).repaint();
        }
    }
    
    public void addData(final SDataSource sDataSource, final int n, final double[] array, final double[] array2) {
        final int n2 = array.length - 1;
        this.setXY(array[n2], array2[n2]);
    }
    
    static {
        Thing.darkGreen = new Color(0, 128, 0);
        Thing.lightGreen = new Color(128, 255, 128);
    }
}
