// 
// Decompiled by Procyon v0.5.30
// 

package xpl;

import java.awt.Cursor;
import java.awt.Event;
import java.awt.event.AdjustmentEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class slider extends Panel implements AdjustmentListener
{
    private int min;
    private int max;
    private int pos1;
    private int resizeDelta;
    private int oldSwidth;
    private int mouseState;
    private int mx;
    private int action;
    private Image img;
    private Image pimg1;
    private Image pimg2;
    private Image dbuf;
    private int[] pixels;
    private int[] pixels1;
    private int[] pixels2;
    private int width;
    private int height;
    private int minSliderWidth;
    private int swidth;
    private transient Vector adjustmentListeners;
    private float sliderPos;
    private float sliderWidth;
    private boolean resizable;
    
    public slider() {
        this.resizeDelta = 3;
        this.mouseState = 0;
        this.action = 0;
        this.img = null;
        this.pimg1 = null;
        this.pimg2 = null;
        this.dbuf = null;
        this.width = 100;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        this.width = 100;
        this.swidth = 20;
        this.oldSwidth = -1;
        this.resizeDelta = 2;
        this.resizable = true;
        this.minSliderWidth = 4;
        this.pos1 = 1;
        this.setSliderPos(0.0f);
        this.setSliderWidth(0.3f);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this.width != bounds.width || this.height != bounds.height) {
            this.width = bounds.width;
            this.height = bounds.height;
            this.pixels = new int[this.width * this.height];
            final float n = this.width / 255.0f;
            final int width = this.width;
            for (int height = this.height, i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    if (j == 0 || i == 0) {
                        this.pixels[i * width + j] = -10461025;
                    }
                    else if (j == width - 1 || i == height - 1) {
                        this.pixels[i * width + j] = -2105345;
                    }
                    else if (j % 5 == i % 5 && j > 2 && i > 2 && j < width - 2 && i < height - 2) {
                        if (i % 2 == 1) {
                            this.pixels[i * width + j] = -5263393;
                        }
                        else {
                            this.pixels[i * width + j] = -8355681;
                        }
                    }
                    else {
                        this.pixels[i * width + j] = -6316081;
                    }
                }
            }
            this.img = this.createImage(new MemoryImageSource(this.width, this.height, this.pixels, 0, this.width));
            this.dbuf = this.createImage(this.width, this.height);
            System.gc();
        }
        if (this.oldSwidth != this.swidth) {
            final int swidth = this.swidth;
            final int n2 = this.height - 2;
            this.oldSwidth = this.swidth;
            this.pixels1 = new int[swidth * n2];
            for (int k = 0; k < n2; ++k) {
                for (int l = 0; l < swidth; ++l) {
                    if (l == 0 || k == 0) {
                        this.pixels1[k * swidth + l] = -2105345;
                    }
                    else if (l == swidth - 1 || k == n2 - 1) {
                        this.pixels1[k * swidth + l] = -10461025;
                    }
                    else if (k > 3 && k < n2 - 3 && (l == 3 || l == swidth - 4)) {
                        this.pixels1[k * swidth + l] = -9408305;
                    }
                    else if (l % 6 == k % 6 && l > 5 && k > 4 && l < swidth - 5 && k < n2 - 4) {
                        if (k % 2 == 1) {
                            this.pixels1[k * swidth + l] = -2105345;
                        }
                        else {
                            this.pixels1[k * swidth + l] = -10461025;
                        }
                    }
                    else {
                        this.pixels1[k * swidth + l] = -6316049;
                    }
                }
            }
            this.pimg1 = this.createImage(new MemoryImageSource(swidth, n2, this.pixels1, 0, swidth));
            System.gc();
        }
        if (this.img != null) {
            this.dbuf.getGraphics().drawImage(this.img, 0, 0, this);
        }
        if (this.pimg1 != null) {
            this.dbuf.getGraphics().drawImage(this.pimg1, this.pos1, 1, this);
        }
        graphics.drawImage(this.dbuf, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void setMin(final int min) {
        this.min = min;
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMax(final int max) {
        this.max = max;
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setPos1(final int pos1) {
        this.pos1 = pos1;
        this.pos1 = Math.max(1, Math.min(this.pos1, this.width - this.swidth - 2));
        this.sliderPos = this.pos1 / this.width;
        this.repaint();
        this.fireAdjustmentValueChanged(null);
    }
    
    public int getPos1() {
        return this.pos1;
    }
    
    public synchronized boolean mouseDown(final Event event, final int mx, final int n) {
        if (this.resizable) {
            if (mx >= this.pos1 && mx <= this.pos1 + this.resizeDelta) {
                this.action = -1;
            }
            if (mx >= this.pos1 + this.swidth - this.resizeDelta && mx <= this.pos1 + this.swidth) {
                this.action = 1;
            }
        }
        this.mx = mx;
        this.mouseState = 1;
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, final int n, final int n2) {
        if (n > this.pos1 + this.resizeDelta && n < this.pos1 + this.swidth - this.resizeDelta) {
            this.setCursor(new Cursor(12));
        }
        else if (this.resizable && n >= this.pos1 && n <= this.pos1 + this.swidth) {
            this.setCursor(new Cursor(10));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public synchronized boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.mouseState == 1 && this.action == 0) {
            this.setPos1(this.pos1 - this.mx + n);
            this.mx = n;
        }
        if (this.mouseState == 1 && (this.action == -1 || this.action == 1)) {
            this.setSwidth(this.swidth - this.action * (this.mx - n));
            if (this.action == -1) {
                this.setPos1(this.pos1 - (this.mx - n));
            }
            this.mx = n;
            this.repaint();
        }
        this.fireAdjustmentValueChanged(null);
        return true;
    }
    
    public synchronized boolean mouseUp(final Event event, final int n, final int n2) {
        this.mouseState = 0;
        this.action = 0;
        return true;
    }
    
    public void setSwidth(final int swidth) {
        this.swidth = swidth;
        this.swidth = Math.max(this.minSliderWidth, Math.min(this.swidth, this.width - 2 - this.pos1));
        this.sliderWidth = this.swidth / this.width;
        this.repaint();
        this.fireAdjustmentValueChanged(null);
    }
    
    public int getSwidth() {
        return this.swidth;
    }
    
    public synchronized void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        if (this.adjustmentListeners != null && this.adjustmentListeners.contains(adjustmentListener)) {
            final Vector adjustmentListeners = (Vector)this.adjustmentListeners.clone();
            adjustmentListeners.removeElement(adjustmentListener);
            this.adjustmentListeners = adjustmentListeners;
        }
    }
    
    public synchronized void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        final Vector adjustmentListeners = (Vector)((this.adjustmentListeners == null) ? new Vector<AdjustmentListener>(2) : this.adjustmentListeners.clone());
        if (!adjustmentListeners.contains(adjustmentListener)) {
            adjustmentListeners.addElement(adjustmentListener);
            this.adjustmentListeners = adjustmentListeners;
        }
    }
    
    protected void fireAdjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (this.adjustmentListeners != null) {
            final Vector adjustmentListeners = this.adjustmentListeners;
            for (int size = adjustmentListeners.size(), i = 0; i < size; ++i) {
                adjustmentListeners.elementAt(i).adjustmentValueChanged(adjustmentEvent);
            }
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
    }
    
    public int getSliderIntPos() {
        return this.min + (int)((this.max - this.min) * this.sliderPos);
    }
    
    public int getSliderIntWidth() {
        return (int)((this.max - this.min) * this.sliderWidth);
    }
    
    public void setSliderPosWidth(final int n, final int n2) {
        this.sliderPos = 0.0f;
        this.sliderWidth = 0.0f;
        this.setSliderPos(n / (this.max - this.min));
        this.setSliderWidth(n2 / (this.max - this.min));
    }
    
    public void setBounds(final Rectangle bounds) {
        this.repaint();
        super.setBounds(bounds);
    }
    
    public void setSliderPos(final float n) {
        this.sliderPos = Math.min(1.0f, Math.max(0.0f, n));
        this.pos1 = (int)((this.width - 2) * this.sliderPos);
        if (this.pos1 + this.swidth > this.width - 2) {
            this.pos1 = this.width - this.swidth;
        }
        this.pos1 = Math.max(this.pos1, 1);
        this.repaint();
        this.fireAdjustmentValueChanged(null);
    }
    
    public float getSliderPos() {
        return this.sliderPos;
    }
    
    public void setSliderWidth(final float n) {
        this.sliderWidth = Math.min(1.0f, Math.max(0.0f, n));
        this.swidth = (int)((this.width - 2) * this.sliderWidth);
        this.swidth = Math.min(this.width - 2, Math.max(1, this.swidth));
        if (this.pos1 + this.swidth > this.width - 2) {
            this.pos1 = this.width - this.swidth;
        }
        this.pos1 = Math.max(this.pos1, 1);
        this.repaint();
        this.fireAdjustmentValueChanged(null);
    }
    
    public float getSliderWidth() {
        return this.sliderWidth;
    }
    
    public void setResizable(final boolean resizable) {
        this.resizable = resizable;
        if (this.resizable) {
            this.resizeDelta = 2;
        }
        else {
            this.resizeDelta = 0;
        }
    }
    
    public boolean isResizable() {
        return this.resizable;
    }
}
