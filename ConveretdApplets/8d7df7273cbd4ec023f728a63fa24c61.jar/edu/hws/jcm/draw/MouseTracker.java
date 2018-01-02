// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Tieable;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.InputObject;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MouseTracker extends Drawable implements MouseListener, MouseMotionListener, InputObject
{
    protected boolean listenForDrags;
    protected boolean undefinedWhenNotDragging;
    protected Controller onUserAction;
    protected boolean clampX;
    protected boolean clampY;
    private MTVariable xVar;
    private MTVariable yVar;
    private int xClick;
    private int yClick;
    private boolean inRect;
    
    public MouseTracker() {
        this(true, false);
    }
    
    public MouseTracker(final boolean listenForDrags, final boolean undefinedWhenNotDragging) {
        this.clampX = true;
        this.clampY = true;
        this.listenForDrags = listenForDrags;
        this.undefinedWhenNotDragging = undefinedWhenNotDragging;
        this.xVar = new MTVariable(true);
        this.yVar = new MTVariable(false);
    }
    
    public Variable getXVar() {
        return this.xVar;
    }
    
    public Variable getYVar() {
        return this.yVar;
    }
    
    public void setListenForDrags(final boolean listenForDrags) {
        if (listenForDrags != this.listenForDrags) {
            this.listenForDrags = listenForDrags;
            if (super.canvas != null) {
                if (listenForDrags) {
                    super.canvas.addMouseMotionListener(this);
                }
                else {
                    super.canvas.removeMouseMotionListener(this);
                }
            }
        }
    }
    
    public boolean getListenForDrags() {
        return this.listenForDrags;
    }
    
    public void setUndefinedWhenNotDragging(final boolean undefinedWhenNotDragging) {
        this.undefinedWhenNotDragging = undefinedWhenNotDragging;
    }
    
    public boolean getUndefinedWhenNotDragging() {
        return this.undefinedWhenNotDragging;
    }
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
    }
    
    public void notifyControllerOnChange(final Controller onUserAction) {
        this.setOnUserAction(onUserAction);
    }
    
    public Controller getOnUserAction() {
        return this.onUserAction;
    }
    
    public void setClampX(final boolean clampX) {
        this.clampX = clampX;
    }
    
    public boolean getClampX() {
        return this.clampX;
    }
    
    public void setClampY(final boolean clampY) {
        this.clampY = clampY;
    }
    
    public boolean getClampY() {
        return this.clampX;
    }
    
    public void checkInput() {
        if (super.coords == null || (this.undefinedWhenNotDragging && !this.inRect)) {
            this.xVar.setVal(Double.NaN);
            this.yVar.setVal(Double.NaN);
        }
        else {
            double val = super.coords.pixelToX(this.xClick);
            if (this.clampX) {
                if (val < super.coords.getXmin()) {
                    val = super.coords.getXmin();
                }
                else if (val > super.coords.getXmax()) {
                    val = super.coords.getXmax();
                }
            }
            this.xVar.setVal(val);
            double val2 = super.coords.pixelToY(this.yClick);
            if (this.clampY) {
                if (val2 < super.coords.getYmin()) {
                    val2 = super.coords.getYmin();
                }
                else if (val2 > super.coords.getYmax()) {
                    val2 = super.coords.getYmax();
                }
            }
            this.yVar.setVal(val2);
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
    }
    
    protected void setOwnerData(final DisplayCanvas canvas, final CoordinateRect coords) {
        if (super.canvas != null) {
            canvas.removeMouseListener(this);
            canvas.removeMouseMotionListener(this);
        }
        super.canvas = canvas;
        super.coords = coords;
        canvas.addMouseListener(this);
        if (this.listenForDrags) {
            canvas.addMouseMotionListener(this);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isConsumed() || super.coords == null) {
            return;
        }
        if (!(this.inRect = (mouseEvent.getX() >= super.coords.getLeft() && mouseEvent.getX() <= super.coords.getLeft() + super.coords.getWidth() && mouseEvent.getY() >= super.coords.getTop() && mouseEvent.getY() <= super.coords.getTop() + super.coords.getHeight()))) {
            return;
        }
        mouseEvent.consume();
        this.xClick = mouseEvent.getX();
        this.yClick = mouseEvent.getY();
        final MTVariable xVar = this.xVar;
        ++xVar.serialNumber;
        final MTVariable yVar = this.yVar;
        ++yVar.serialNumber;
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.inRect) {
            return;
        }
        this.inRect = false;
        if (this.listenForDrags && this.undefinedWhenNotDragging) {
            final MTVariable xVar = this.xVar;
            ++xVar.serialNumber;
            final MTVariable yVar = this.yVar;
            ++yVar.serialNumber;
            if (this.onUserAction != null) {
                this.onUserAction.compute();
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.listenForDrags && this.inRect) {
            this.xClick = mouseEvent.getX();
            this.yClick = mouseEvent.getY();
            final MTVariable xVar = this.xVar;
            ++xVar.serialNumber;
            final MTVariable yVar = this.yVar;
            ++yVar.serialNumber;
            if (this.onUserAction != null) {
                this.onUserAction.compute();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    static /* synthetic */ void access$1(final MouseTracker mouseTracker, final int xClick) {
        mouseTracker.xClick = xClick;
    }
    
    static /* synthetic */ void access$3(final MouseTracker mouseTracker, final int yClick) {
        mouseTracker.yClick = yClick;
    }
    
    private class MTVariable extends Variable implements Tieable
    {
        private boolean isXVar;
        long serialNumber;
        
        MTVariable(final boolean isXVar) {
            super(isXVar ? "xMouse" : "yMouse");
            this.isXVar = isXVar;
            super.setVal(Double.NaN);
        }
        
        public void setVal(final double val) {
            if (this.isXVar) {
                if (MouseTracker.this.coords != null) {
                    MouseTracker.access$1(MouseTracker.this, MouseTracker.this.coords.xToPixel(val));
                }
            }
            else if (MouseTracker.this.coords != null) {
                MouseTracker.access$3(MouseTracker.this, MouseTracker.this.coords.yToPixel(val));
            }
            super.setVal(val);
        }
        
        public long getSerialNumber() {
            return this.serialNumber;
        }
        
        public void sync(final Tie tie, final Tieable tieable) {
            if (!(tieable instanceof Value)) {
                throw new IllegalArgumentException("Internal Error:  A MouseTracker variable can only be tied to a Value object.");
            }
            if (tieable != this) {
                this.setVal(((Value)tieable).getVal());
                this.serialNumber = tieable.getSerialNumber();
            }
        }
    }
}
