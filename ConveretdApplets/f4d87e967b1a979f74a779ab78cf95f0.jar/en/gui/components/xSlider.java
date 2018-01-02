// 
// Decompiled by Procyon v0.5.30
// 

package en.gui.components;

import java.util.Date;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import en.javafx.Parameter;
import java.awt.Image;
import java.awt.Canvas;

public class xSlider extends Canvas implements xGUI
{
    Image barImage;
    Image sliderImage;
    int minx;
    int maxx;
    int __xoffset;
    int __yoffset;
    Parameter target;
    
    public xSlider() {
        this(Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/sliderBar.jpg"), Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/slider.jpg"), 7, 253, 8, 8);
    }
    
    public xSlider(final Image barImage, final Image sliderImage, final int minx, final int maxx, final int _xoffset, final int _yoffset) {
        this.target = new Parameter("default", new dc(), new dc(0.0f), new dc(0.0f), new dc(1.0f));
        this.barImage = barImage;
        this.sliderImage = sliderImage;
        this.minx = minx;
        this.maxx = maxx;
        this.__xoffset = _xoffset;
        this.__yoffset = _yoffset;
        while (true) {
            if (barImage.getWidth(null) != -1) {
                if (barImage.getHeight(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this.setSize(barImage.getWidth(null), barImage.getHeight(null));
        this.enableEvents(48L);
    }
    
    public final void setTarget(final Parameter target) throws Exception {
        if (target == null) {
            System.out.println("xSlider: setTarget has null parameter");
            return;
        }
        this.target = target;
        this.repaint();
    }
    
    public final Parameter getTarget() {
        return this.target;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.barImage != null && this.sliderImage != null) {
            this.__xoffset = (int)(this.target.getNormalised() * (this.maxx - this.minx) + this.minx);
            graphics.drawImage(this.barImage, 0, 0, this);
            graphics.drawImage(this.sliderImage, this.__xoffset, this.__yoffset, this);
        }
        else {
            graphics.drawRect(0, 0, 40, 40);
        }
    }
    
    protected final void process(final int n) {
        int n2;
        if (n < this.minx) {
            n2 = this.minx;
        }
        else if (n > this.maxx) {
            n2 = this.maxx;
        }
        else {
            n2 = n;
        }
        if (this.target != null) {
            this.target.setNormalised(this.getNormalizedValue(n2));
        }
        this.repaint();
    }
    
    protected final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.process(mouseEvent.getX());
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    protected final void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.process(mouseEvent.getX());
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    public final Dimension getPreferredSize() {
        return this.getSize();
    }
    
    public final Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public final Dimension getMaximumSize() {
        return this.getPreferredSize();
    }
    
    public final float getNormalizedValue(final int n) {
        return (n - this.minx) / (this.maxx - this.minx);
    }
    
    public final void forceRepaint() {
        this.update(this.getGraphics());
    }
    
    static {
        if (1354870381117L <= System.currentTimeMillis()) {
            System.out.println("This java program was processed with an unregistered version of Condensity and it expired on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
            throw new Error();
        }
        System.out.println("This java program was processed with an unregistered version of Condensity and will expire on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
    }
}
