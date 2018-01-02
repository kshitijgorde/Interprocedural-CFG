import java.awt.Graphics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Viewer extends Panel
{
    private View3d v;
    private boolean repaint;
    private int noColors;
    private AnimationWidget animationWidget;
    private int noPanelsDisplayed;
    private Painter painter;
    private Thread engine;
    private Object3d model;
    private ColorControl[] colcontrols;
    protected Applet3d parent;
    Button moreButton;
    
    public Viewer(final Applet3d parent) {
        this.v = new View3d(Point3d.ijk);
        this.repaint = false;
        this.noColors = 0;
        this.noPanelsDisplayed = 0;
        this.colcontrols = new ColorControl[10];
        this.parent = parent;
        this.setLayout(new BorderLayout());
        this.painter = new Paint3d(this);
        this.add("Center", (Component)this.painter);
        (this.animationWidget = new AnimationWidget(this)).setAutoPlay(true);
    }
    
    public void moreControls(final Button button) {
        this.validate();
        ++this.noPanelsDisplayed;
    }
    
    public int addColor(final String s, final String s2, final boolean b, final Color color, final boolean b2) {
        this.colcontrols[this.noColors] = new ColorControl(s, s2, b, color, this.noColors, this);
        return this.noColors++;
    }
    
    public int addColor(final Color color) {
        this.v.setColor(this.noColors, color);
        return this.noColors++;
    }
    
    public void setColorVisibility(final int n, final boolean state) {
        this.colcontrols[n].setState(state);
        this.colcontrols[n].action(null, null);
    }
    
    public void setModel(final Object3d object3d) {
        this.model = object3d;
        this.v.setLastFrame(object3d.getLastFrame());
        this.painter.setModel(object3d);
        this.animationWidget.setLastFrame(object3d.getLastFrame());
        this.animationWidget.first();
    }
    
    public void start() {
        (this.engine = new Thread(this.painter)).setPriority(1);
        this.engine.start();
    }
    
    public void paint(final Graphics graphics) {
        this.painter.paint(graphics);
    }
    
    public boolean setView() {
        this.v.set(new View3dInfo(Point3d.k));
        this.put();
        return true;
    }
    
    public boolean setView(final View3dInfo view3dInfo) {
        this.v.set(view3dInfo);
        return true;
    }
    
    public synchronized void put() {
        this.repaint = (this.model != null);
        this.notify();
    }
    
    public synchronized void put(final int n, final Color color) {
        this.v.setColor(n, color);
        this.put();
    }
    
    public void setGraphics(final Graphics graphics, final int n, final int n2) {
        this.v.setGraphics(graphics, n, n2);
        this.put();
    }
    
    public void setWindow(final double n, final double n2, final double n3, final double n4) {
        this.v.setWindow(n, n2, n3, n4);
    }
    
    public synchronized void putFrameNo(final int frameNo) {
        this.v.setFrameNo(frameNo);
        this.put();
    }
    
    public void setFrameNo(final int frameNo) {
        this.animationWidget.setFrameNo(frameNo);
    }
    
    public synchronized View3d get() {
        while (!this.repaint) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
        this.repaint = false;
        return (View3d)this.v.clone();
    }
    
    public View3d getView() {
        return this.v;
    }
}
