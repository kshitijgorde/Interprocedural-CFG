// 
// Decompiled by Procyon v0.5.30
// 

package en.gui.components;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import en.javafx.Parameter;
import java.awt.Image;
import java.awt.Canvas;

public class xSwitch extends Canvas implements xGUI
{
    Image imageOn;
    Image imageOff;
    Parameter target;
    
    public xSwitch() {
        this(Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/buttonOff.jpg"), Toolkit.getDefaultToolkit().createImage("d:/HyperNet/classes/volumetriclights/buttonOn.jpg"));
    }
    
    public xSwitch(final Image imageOff, final Image imageOn) {
        this.target = new Parameter("default", new df(false));
        this.imageOff = imageOff;
        this.imageOn = imageOn;
        while (true) {
            if (imageOff.getWidth(null) != -1) {
                if (imageOff.getHeight(null) == -1) {
                    continue;
                }
                break;
            }
        }
        this.setSize(imageOff.getWidth(null), imageOff.getHeight(null));
        this.enableEvents(16L);
    }
    
    public final void setTarget(final Parameter target) throws Exception {
        if (target == null) {
            System.out.println("xSwitch: setTarget has null parameter");
            return;
        }
        this.target = target;
        if (!(target.target instanceof df)) {
            throw new Exception("xSwitch: target parameter must be of xBoolean type");
        }
        this.forceRepaint();
    }
    
    public final boolean isOn() {
        return ((df)this.target.target).p();
    }
    
    public final boolean isOff() {
        return !this.isOn();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.imageOff != null && this.imageOn != null) {
            if (this.isOn()) {
                graphics.drawImage(this.imageOn, 0, 0, this);
            }
            else {
                graphics.drawImage(this.imageOff, 0, 0, this);
            }
        }
        else {
            graphics.drawRect(0, 0, 30, 30);
        }
    }
    
    protected final void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 500: {
                if (this.target != null) {
                    ((df)this.target.target).p(!((df)this.target.target).p());
                }
                this.forceRepaint();
            }
        }
        super.processMouseEvent(mouseEvent);
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
    
    public final void forceRepaint() {
        this.update(this.getGraphics());
    }
}
