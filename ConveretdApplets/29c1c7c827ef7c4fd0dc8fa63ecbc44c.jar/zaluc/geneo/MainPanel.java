// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

class MainPanel extends Panel implements AdjustmentListener, Runnable
{
    private Globals globals;
    private TreeCanvas canvas;
    private Scrollbar hbar;
    private Scrollbar vbar;
    
    public MainPanel(final Geneo geneo, final Globals globals) {
        this.globals = globals;
        this.setForeground(this.globals.foregroundColor);
        this.setBackground(this.globals.backgroundColor);
        this.canvas = new TreeCanvas(geneo, globals, this);
        this.hbar = new Scrollbar(0);
        this.vbar = new Scrollbar(1);
        this.setLayout(new BorderLayout(0, 0));
        this.add("Center", this.canvas);
        this.add("South", this.hbar);
        this.add("East", this.vbar);
        this.hbar.addAdjustmentListener(this);
        this.vbar.addAdjustmentListener(this);
    }
    
    public void find() {
        new Thread(this, "MainPanel Find Thread").start();
    }
    
    public void run() {
        this.canvas.find();
    }
    
    public void zoomIn() {
        this.canvas.zoomIn();
    }
    
    public void zoomOut() {
        this.canvas.zoomOut();
    }
    
    public void home() {
        this.canvas.home();
    }
    
    public void setPrimary(final int primary) {
        this.canvas.setPrimary(primary);
    }
    
    public void setPrimary() {
        this.canvas.setPrimary();
    }
    
    public void back() {
        this.canvas.back();
    }
    
    public void forward() {
        this.canvas.forward();
    }
    
    public void notifyDone() {
        this.canvas.notifyDone();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        final Scrollbar scrollbar = (Scrollbar)adjustmentEvent.getAdjustable();
        if (scrollbar == this.hbar) {
            switch (adjustmentEvent.getAdjustmentType()) {
                case 2: {
                    this.canvas.incLeft();
                }
                case 1: {
                    this.canvas.incRight();
                }
                case 3: {
                    this.canvas.pageLeft();
                }
                case 4: {
                    this.canvas.pageRight();
                }
                case 5: {
                    this.canvas.setHorz(adjustmentEvent.getValue());
                }
            }
        }
        else if (scrollbar == this.vbar) {
            switch (adjustmentEvent.getAdjustmentType()) {
                case 2: {
                    this.canvas.incUp();
                }
                case 1: {
                    this.canvas.incDown();
                }
                case 3: {
                    this.canvas.pageUp();
                }
                case 4: {
                    this.canvas.pageDown();
                }
                case 5: {
                    this.canvas.setVert(adjustmentEvent.getValue());
                }
            }
        }
    }
    
    public void adjustScrollBars(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.hbar.setValues(n, n2, n3, n4);
        this.vbar.setValues(n5, n6, n7, n8);
    }
}
