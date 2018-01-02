// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.PrintGraphics;
import java.awt.Color;
import java.awt.Graphics;
import borland.jbcl.control.BevelPanel;
import java.awt.event.ActionListener;
import sTools.Format;
import java.awt.Panel;

public class SoundCanvas extends Panel
{
    private Format mouseFormat;
    private int numPts;
    private int iwidth;
    private int iheight;
    private int[] xPix;
    private int[] yPix;
    private int startDraw;
    private int drawPts;
    private double[] yVec;
    private int c1;
    private int c2;
    private int c3;
    private boolean noCursors;
    private boolean dragC1;
    private ActionListener actionListener;
    BevelPanel bevelPanel1;
    
    public void setYVec(final double[] yVec) {
        this.yVec = yVec;
        if (this.yVec != null) {
            this.numPts = this.yVec.length;
        }
        else {
            this.numPts = 0;
        }
        this.setYVec(yVec, 0, this.numPts - 1);
    }
    
    public void setYVec(final double[] yVec, final int startDraw, final int n) {
        this.yVec = yVec;
        if (this.yVec == null) {
            this.numPts = 0;
            this.c1 = 0;
            this.c2 = 0;
            this.yPix = null;
            this.xPix = null;
            return;
        }
        this.numPts = this.yVec.length;
        if (this.xPix == null || this.numPts != this.xPix.length) {
            this.xPix = new int[this.numPts];
            this.yPix = new int[this.numPts];
            this.c1 = this.getSize().width / 2 - 20;
            this.c2 = this.getSize().width / 2 + 20;
            if (this.c1 < 0) {
                this.c1 = 0;
            }
            if (this.c2 > this.numPts - 1) {
                this.c2 = this.numPts - 1;
            }
            this.notifyActionListeners();
        }
        this.startDraw = startDraw;
        this.drawPts = n - startDraw + 1;
        if (this.drawPts > this.numPts) {
            this.drawPts = this.numPts;
        }
        this.recalc();
    }
    
    public void setNoCursors(final boolean noCursors) {
        if (this.noCursors == noCursors) {
            return;
        }
        this.noCursors = noCursors;
        this.repaint();
    }
    
    public boolean isNoCursors() {
        return this.noCursors;
    }
    
    public double getTimeFromPix(final int n) {
        if (n < 0 || n > this.iwidth - 1) {
            return 0.0;
        }
        return (this.startDraw + n / (this.iwidth - 1.0) * (this.drawPts - 1.0)) / 8000.0;
    }
    
    public void setC1(final int c1) {
        this.c1 = c1;
        this.repaint();
    }
    
    public int getC1() {
        if (this.noCursors || this.yVec == null || this.iwidth < 2) {
            return 0;
        }
        return (int)(this.c1 / (this.iwidth - 1.0) * (this.numPts - 1));
    }
    
    public void setC2(final int c2) {
        this.c2 = c2;
        this.repaint();
    }
    
    public int getC2() {
        if (this.noCursors || this.yVec == null || this.iwidth < 2) {
            return this.numPts;
        }
        return (int)(this.c2 / (this.iwidth - 1.0) * (this.numPts - 1));
    }
    
    public double getMin(final double[] array) {
        if (array == null || array.length == 0) {
            return 0.0;
        }
        double n = array[1];
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n) {
                n = array[i];
            }
        }
        return n;
    }
    
    public double getMax(final double[] array) {
        if (array == null || array.length == 0) {
            return 0.0;
        }
        double n = array[1];
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > n) {
                n = array[i];
            }
        }
        return n;
    }
    
    private void recalc() {
        this.iwidth = this.getSize().width;
        this.iheight = this.getSize().height;
        double min = this.getMin(this.yVec);
        double max = this.getMax(this.yVec);
        final int n = 5;
        final int n2 = this.iheight - 2 * n;
        if (max == min) {
            max += 0.5;
            min -= 0.5;
        }
        final double n3 = 1.0 * this.numPts / this.drawPts;
        for (int i = 0; i < this.drawPts; ++i) {
            this.xPix[i] = (int)(n3 * i * (this.iwidth - 1.0) / (this.numPts - 1.0));
            this.yPix[i] = n + (int)(n2 * (max - this.yVec[this.startDraw + i]) / (max - min));
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.getSize().width == 0 || this.getSize().height == 0) {
            return;
        }
        if (this.iwidth != this.getSize().width || this.iheight != this.getSize().height) {
            this.c1 = this.getSize().width / 2 - 20;
            this.c2 = this.getSize().width / 2 + 20;
            if (this.c1 < 0) {
                this.c1 = 0;
            }
            if (this.c2 > this.numPts - 1) {
                this.c2 = this.numPts - 1;
            }
            this.recalc();
            this.notifyActionListeners();
        }
        if (this.noCursors) {
            graphics.setColor(new Color(255, 255, 200));
            graphics.fillRect(0, 0, this.iwidth, this.iheight);
            if (graphics instanceof PrintGraphics) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.red);
            }
            graphics.drawPolyline(this.xPix, this.yPix, this.drawPts);
        }
        else {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.iwidth, this.iheight);
            graphics.setColor(new Color(255, 255, 200));
            graphics.fillRect(this.c1, 0, this.c2 - this.c1, this.iheight - 1);
            if (graphics instanceof PrintGraphics) {
                graphics.setColor(Color.black);
            }
            else {
                graphics.setColor(Color.blue);
            }
            graphics.drawPolyline(this.xPix, this.yPix, this.drawPts);
            graphics.setColor(Color.red);
            graphics.drawLine(this.c1, 0, this.c1, this.iheight - 1);
            graphics.drawLine(this.c2, 0, this.c2, this.iheight - 1);
        }
        graphics.setColor(Color.black);
    }
    
    public SoundCanvas() {
        this.mouseFormat = new Format("%-+8.5g");
        this.numPts = 0;
        this.iwidth = 0;
        this.iheight = 0;
        this.yVec = null;
        this.c1 = 0;
        this.c2 = 0;
        this.noCursors = false;
        this.dragC1 = true;
        this.actionListener = null;
        this.bevelPanel1 = new BevelPanel();
        try {
            this.numPts = 0;
            this.yVec = null;
            this.xPix = null;
            this.yPix = null;
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void jbInit() throws Exception {
        this.addMouseListener(new SoundCanvas_this_mouseAdapter(this));
        this.addMouseMotionListener(new SoundCanvas_this_mouseMotionAdapter(this));
    }
    
    void this_mouseMoved(final MouseEvent mouseEvent) {
        if (!this.noCursors) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
    }
    
    void this_mouseDragged(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final double timeFromPix = this.getTimeFromPix(x);
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            final FontMetrics fontMetrics = graphics.getFontMetrics(graphics.getFont());
            final String concat = String.valueOf(String.valueOf("t= ").concat(String.valueOf(this.mouseFormat.form(timeFromPix)))).concat(String.valueOf("s"));
            graphics.setColor(Color.yellow);
            graphics.fillRect(0, this.iheight - 15, 15 + fontMetrics.stringWidth("t= +000.00000 s"), 15);
            graphics.setColor(Color.black);
            graphics.drawString(concat, 10, this.iheight - 2);
            if (!this.noCursors) {
                graphics.setColor(Color.red);
                graphics.setXORMode(this.getBackground());
                graphics.drawLine(this.c3, 0, this.c3, this.iheight - 1);
                graphics.drawLine(this.c3 = x, 0, this.c3, this.iheight - 1);
            }
            graphics.dispose();
        }
    }
    
    void this_mousePressed(final MouseEvent mouseEvent) {
        if (this.noCursors) {
            return;
        }
        final int x = mouseEvent.getX();
        if (Math.abs(x - this.c1) <= Math.abs(x - this.c2)) {
            this.c3 = this.c1;
            this.dragC1 = true;
        }
        else {
            this.c3 = this.c2;
            this.dragC1 = false;
        }
    }
    
    void this_mouseReleased(final MouseEvent mouseEvent) {
        if (this.noCursors) {
            this.repaint();
            return;
        }
        final int x = mouseEvent.getX();
        if (this.dragC1) {
            this.c1 = x;
        }
        else {
            this.c2 = x;
        }
        if (this.c1 > this.c2) {
            final int c2 = this.c2;
            this.c2 = this.c1;
            this.c1 = c2;
        }
        if (this.c1 == this.c2) {
            this.c2 = this.c1 + 1;
        }
        if (this.c1 < 0) {
            this.c1 = 0;
        }
        if (this.c1 >= this.iwidth) {
            this.c1 = this.iwidth - 1;
        }
        if (this.c2 < 0) {
            this.c2 = 0;
        }
        if (this.c2 >= this.iwidth) {
            this.c2 = this.iwidth - 1;
        }
        this.repaint();
        this.notifyActionListeners();
    }
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void notifyActionListeners() {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, "CMove"));
        }
    }
}
