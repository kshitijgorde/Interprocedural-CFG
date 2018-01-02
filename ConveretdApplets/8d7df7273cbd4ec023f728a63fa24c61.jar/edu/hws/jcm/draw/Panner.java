// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import edu.hws.jcm.awt.Controller;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Panner extends Drawable implements MouseListener, MouseMotionListener
{
    private int modifiers;
    private Controller onUserAction;
    private Controller onFinishDrag;
    private boolean dragging;
    private int prevX;
    private int prevY;
    
    public Panner() {
        this(4);
    }
    
    public Panner(final int n) {
        this.modifiers = (n & 0xF);
    }
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
    }
    
    public Controller getOnUserAction() {
        return this.onUserAction;
    }
    
    public void setOnFinishDrag(final Controller onFinishDrag) {
        this.onFinishDrag = onFinishDrag;
    }
    
    public Controller getOnFinishDrag() {
        return this.onFinishDrag;
    }
    
    protected void setOwnerData(final DisplayCanvas displayCanvas, final CoordinateRect coordinateRect) {
        if (displayCanvas != null) {
            displayCanvas.removeMouseListener(this);
            displayCanvas.removeMouseMotionListener(this);
        }
        super.setOwnerData(displayCanvas, coordinateRect);
        if (displayCanvas != null) {
            displayCanvas.addMouseListener(this);
            displayCanvas.addMouseMotionListener(this);
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dragging = false;
        if (mouseEvent.isConsumed()) {
            return;
        }
        if (!this.getVisible() || super.canvas == null || super.coords == null) {
            return;
        }
        if ((mouseEvent.getModifiers() & this.modifiers) != this.modifiers) {
            return;
        }
        this.prevX = mouseEvent.getX();
        this.prevY = mouseEvent.getY();
        if (this.prevX < super.coords.getLeft() || this.prevX >= super.coords.getLeft() + super.coords.getWidth() || this.prevY < super.coords.getTop() || this.prevY >= super.coords.getTop() + super.coords.getHeight()) {
            return;
        }
        mouseEvent.consume();
        this.dragging = true;
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            return;
        }
        mouseEvent.consume();
        if (mouseEvent.getX() == this.prevX && mouseEvent.getY() == this.prevY) {
            return;
        }
        final double[] limits = super.coords.getLimits();
        if (limits == null) {
            return;
        }
        final double n = (mouseEvent.getX() - this.prevX) * super.coords.getPixelWidth();
        final double n2 = (mouseEvent.getY() - this.prevY) * super.coords.getPixelHeight();
        super.coords.setLimits(limits[0] - n, limits[1] - n, limits[2] + n2, limits[3] + n2);
        this.needsRedraw();
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
        this.prevX = mouseEvent.getX();
        this.prevY = mouseEvent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            return;
        }
        mouseEvent.consume();
        this.mouseDragged(mouseEvent);
        this.dragging = false;
        if (this.onFinishDrag != null) {
            this.onFinishDrag.compute();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
}
