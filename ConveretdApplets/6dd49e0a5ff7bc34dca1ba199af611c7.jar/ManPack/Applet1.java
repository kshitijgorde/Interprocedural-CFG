// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import jclass.bwt.JCAdjustmentEvent;
import java.awt.event.MouseEvent;
import jclass.bwt.JCActionEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import borland.jbcl.layout.XYConstraints;
import java.awt.LayoutManager;
import jclass.bwt.JCActionListener;
import java.awt.event.MouseListener;
import jclass.bwt.JCAdjustmentListener;
import java.awt.Toolkit;
import java.awt.SystemColor;
import java.awt.Label;
import jclass.bwt.JCButton;
import jclass.bwt.JCSlider;
import java.awt.Color;
import borland.jbcl.layout.XYLayout;
import java.applet.Applet;

public class Applet1 extends Applet implements Runnable
{
    XYLayout xYLayout1;
    boolean isStandalone;
    int initRadius;
    int initX;
    int initY;
    static int initPoints;
    static int maxPoints;
    static Color foreColor;
    Poly p;
    Thread manThread;
    boolean threadSuspended;
    JCSlider speedSlider;
    int sleepLength;
    JCButton quitButton;
    JCSlider numPointsSlider;
    Poly numPointPoly;
    Label label3;
    
    public Applet1() {
        this.xYLayout1 = new XYLayout();
        this.isStandalone = false;
        this.initRadius = 195;
        this.initX = 400;
        this.initY = 195;
        this.speedSlider = new JCSlider();
        this.sleepLength = 37;
        this.quitButton = new JCButton();
        this.numPointsSlider = new JCSlider();
        this.numPointPoly = null;
        this.label3 = new Label();
    }
    
    public String getParameter(final String key, final String def) {
        return this.isStandalone ? System.getProperty(key, def) : ((this.getParameter(key) != null) ? this.getParameter(key) : def);
    }
    
    public void init() {
        try {
            this.jbInit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.p = new Poly(Applet1.initPoints, this.initRadius, this.initX, this.initY);
    }
    
    private void jbInit() throws Exception {
        this.setBackground(SystemColor.controlHighlight);
        final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.xYLayout1.setWidth(400);
        this.xYLayout1.setHeight(660);
        this.speedSlider.setOrientation(1);
        this.speedSlider.setMaximum(45);
        this.speedSlider.setNumTicks(30);
        this.speedSlider.setValue(this.sleepLength);
        this.speedSlider.setMinimumLabelString("Faster");
        this.speedSlider.setMaximumLabelString("Slower");
        this.speedSlider.addAdjustmentListener(new Applet1_speedSlider_adjustmentAdapter(this));
        this.speedSlider.addMouseListener(new Applet1_speedSlider_mouseAdapter(this));
        this.quitButton.setLabel("Stop");
        this.numPointsSlider.setOrientation(1);
        this.numPointsSlider.setMinimum(1);
        this.numPointsSlider.setNumTicks(10);
        this.numPointsSlider.setValue(Applet1.initPoints);
        this.numPointsSlider.setMaximumLabelString("1");
        this.label3.setText(String.valueOf(Applet1.initPoints).concat(String.valueOf(" Points")));
        this.numPointsSlider.setMinimumLabelString("100");
        this.numPointsSlider.addMouseListener(new Applet1_numPointsSlider_mouseAdapter(this));
        this.numPointsSlider.addAdjustmentListener(new Applet1_numPointsSlider_adjustmentAdapter(this));
        this.quitButton.addActionListener(new Applet1_quitButton_actionAdapter(this));
        this.setLayout(this.xYLayout1);
        this.add(this.speedSlider, new XYConstraints(22, 30, 50, 222));
        this.add(this.quitButton, new XYConstraints(34, 327, 63, 35));
        this.add(this.numPointsSlider, new XYConstraints(86, 30, 39, 222));
        this.add(this.label3, new XYConstraints(131, 129, 62, 21));
    }
    
    public void run() {
        final Thread myThread = Thread.currentThread();
        final Graphics g = this.getGraphics();
        final Line curLine = new Line();
        int curPoint = 0;
        while (this.manThread == myThread) {
            if (this.p.erasing) {
                g.setColor(this.getBackground());
            }
            else {
                g.setColor(Applet1.foreColor);
            }
            this.p.zero();
            while (this.p.thereAreAnyLinesToDraw(curPoint, curLine) && this.manThread == myThread) {
                g.drawLine(this.p.x[curLine.startFrom], this.p.y[curLine.startFrom], this.p.x[curLine.goTo], this.p.y[curLine.goTo]);
                this.p.connected[curLine.startFrom][curLine.goTo] = true;
                this.p.connected[curLine.goTo][curLine.startFrom] = true;
                curPoint = curLine.goTo;
                try {
                    Thread.sleep(this.sleepLength);
                    synchronized (this) {
                        while (this.threadSuspended) {
                            this.wait();
                        }
                    }
                }
                catch (InterruptedException ex) {}
            }
            this.p.erasing = !this.p.erasing;
        }
    }
    
    public void start() {
        if (this.manThread == null) {
            (this.manThread = new Thread(this, "ManThread")).start();
        }
    }
    
    public void stop() {
        this.manThread = null;
    }
    
    public String getAppletInfo() {
        return "Sand Dollar - Version 1.0";
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    void quitButton_actionPerformed(final JCActionEvent e) {
        this.manThread = null;
    }
    
    synchronized void speedSlider_mouseReleased(final MouseEvent e) {
        e.consume();
        this.threadSuspended = false;
        this.notify();
    }
    
    synchronized void speedSlider_mousePressed(final MouseEvent e) {
        e.consume();
        this.threadSuspended = true;
    }
    
    synchronized void speedSlider_adjustmentValueChanged(final JCAdjustmentEvent e) {
        this.sleepLength = e.getValue();
    }
    
    synchronized void numPointsSlider_adjustmentValueChanged(final JCAdjustmentEvent e) {
        int newPoints = e.getValue();
        newPoints = Applet1.maxPoints - newPoints;
        if (newPoints == 0) {
            this.label3.setText(String.valueOf(newPoints + 1).concat(String.valueOf(" Point")));
        }
        else {
            this.label3.setText(String.valueOf(newPoints + 1).concat(String.valueOf(" Points")));
        }
        if (this.numPointPoly != null) {
            final Graphics g = this.getGraphics();
            this.numPointPoly.drawPoly(this.getBackground(), g);
            (this.numPointPoly = new Poly(newPoints, this.numPointPoly.radius, this.numPointPoly.centerX, this.numPointPoly.centerY)).drawPoly(Applet1.foreColor, g);
        }
    }
    
    synchronized void numPointsSlider_mousePressed(final MouseEvent e) {
        e.consume();
        this.manThread = null;
        final Graphics g = this.getGraphics();
        g.setColor(this.getBackground());
        g.fillRect(this.initX - this.initRadius, this.initY - this.initRadius, 2 * this.initRadius, 2 * this.initRadius);
        (this.numPointPoly = new Poly(this.p.lastPoint, this.p.radius, this.p.centerX, this.p.centerY)).drawPoly(Applet1.foreColor, g);
    }
    
    synchronized void numPointsSlider_mouseReleased(final MouseEvent e) {
        e.consume();
        if (this.numPointPoly != null) {
            final Graphics g = this.getGraphics();
            this.numPointPoly.drawPoly(this.getBackground(), g);
            this.p = this.numPointPoly;
            this.numPointPoly = null;
            (this.manThread = new Thread(this, "ManThread")).start();
        }
    }
    
    static {
        Applet1.initPoints = 32;
        Applet1.maxPoints = 100;
        Applet1.foreColor = Color.blue;
    }
}
