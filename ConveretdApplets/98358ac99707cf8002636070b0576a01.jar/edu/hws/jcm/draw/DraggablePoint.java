// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import edu.hws.jcm.awt.Tie;
import edu.hws.jcm.awt.Tieable;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.ValueMath;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Constant;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.Controller;
import java.awt.Color;
import edu.hws.jcm.awt.InputObject;

public class DraggablePoint extends Drawable implements InputObject, Draggable
{
    public static final int DISK = 0;
    public static final int SQUARE = 1;
    public static final int CROSS = 2;
    private int radius;
    private Color color;
    private Color ghostColor;
    private int style;
    private double xLoc;
    private double yLoc;
    private int xPosition;
    private int yPosition;
    private boolean useGhost;
    private DPV xVar;
    private DPV yVar;
    private Controller onUserAction;
    private Value clampX;
    private Value clampY;
    private boolean dragging;
    
    public DraggablePoint() {
        this(0);
    }
    
    public DraggablePoint(final int style) {
        if (style >= 0 && style <= 2) {
            this.style = style;
        }
        this.setColor(Color.darkGray);
        this.setGhostColor(Color.lightGray);
        this.radius = 4;
        this.xPosition = -10000;
        this.xLoc = Double.NaN;
        this.yLoc = Double.NaN;
        this.xVar = new DPV(true);
        this.yVar = new DPV(false);
    }
    
    public void clampX(final Value clampX) {
        this.clampX = clampX;
        if (clampX != null) {
            this.clampY = null;
        }
        this.checkClamp();
        this.needsRedraw();
    }
    
    public void clampY(final Value clampY) {
        this.clampY = clampY;
        if (clampY != null) {
            this.clampX = null;
        }
        this.checkClamp();
        this.needsRedraw();
    }
    
    public void clampX(final double n) {
        this.clampX(new Constant(n));
    }
    
    public void clampY(final double n) {
        this.clampY(new Constant(n));
    }
    
    public void clampX(final Function function) {
        if (function != null) {
            this.clampX(new ValueMath(function, this.xVar));
        }
    }
    
    public void clampY(final Function function) {
        if (function != null) {
            this.clampY(new ValueMath(function, this.xVar));
        }
    }
    
    public int getRadius() {
        return this.radius;
    }
    
    public void setRadius(final int radius) {
        if (radius > 0) {
            this.radius = radius;
            this.needsRedraw();
        }
    }
    
    public void setStyle(final int style) {
        if (style >= 0 && style <= 2) {
            this.style = style;
            this.needsRedraw();
        }
    }
    
    public int getStyle() {
        return this.style;
    }
    
    public Variable getXVar() {
        return this.xVar;
    }
    
    public Variable getYVar() {
        return this.yVar;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final Color color) {
        if (color != null) {
            this.color = color;
            this.needsRedraw();
        }
    }
    
    public Color getGhostColor() {
        return this.ghostColor;
    }
    
    public void setGhostColor(final Color ghostColor) {
        if (ghostColor != null) {
            this.ghostColor = ghostColor;
            this.needsRedraw();
        }
    }
    
    public void setOnUserAction(final Controller onUserAction) {
        this.onUserAction = onUserAction;
    }
    
    public void notifyControllerOnChange(final Controller onUserAction) {
        this.setOnUserAction(onUserAction);
    }
    
    public Controller getOnUserAction(final Controller controller) {
        return this.onUserAction;
    }
    
    public void setLocation(final double n, final double n2) {
        this.xLoc = n;
        this.yLoc = n2;
        this.xVar.setVariableValue(n);
        this.yVar.setVariableValue(n2);
        final DPV xVar = this.xVar;
        ++xVar.serialNumber;
        final DPV yVar = this.yVar;
        ++yVar.serialNumber;
        this.checkClamp();
        this.needsRedraw();
    }
    
    private void checkClamp() {
        if (this.clampX != null) {
            this.xLoc = this.clampX.getVal();
            this.xVar.setVariableValue(this.xLoc);
        }
        else if (this.clampY != null) {
            this.yLoc = this.clampY.getVal();
            this.yVar.setVariableValue(this.yLoc);
        }
    }
    
    public void checkInput() {
        this.xVar.needsClamp = true;
        this.yVar.needsClamp = true;
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (super.coords == null) {
            return;
        }
        this.checkPosition();
        if (this.useGhost) {
            graphics.setColor(this.getGhostColor());
        }
        else {
            graphics.setColor(this.color);
        }
        switch (this.style) {
            case 0: {
                graphics.fillOval(this.xPosition - this.radius, this.yPosition - this.radius, 2 * this.radius + 1, 2 * this.radius + 1);
                break;
            }
            case 1: {
                graphics.fillRect(this.xPosition - this.radius, this.yPosition - this.radius, 2 * this.radius + 1, 2 * this.radius + 1);
                break;
            }
            case 2: {
                graphics.drawLine(this.xPosition - this.radius, this.yPosition, this.xPosition + this.radius, this.yPosition);
                graphics.drawLine(this.xPosition, this.yPosition - this.radius, this.xPosition, this.yPosition + this.radius);
                break;
            }
        }
    }
    
    private void checkPosition() {
        this.useGhost = false;
        this.xVar.getVal();
        this.yVar.getVal();
        if (Double.isNaN(this.xLoc) || Double.isNaN(this.yLoc)) {
            if (this.xPosition == -10000) {
                this.xPosition = super.coords.getLeft() + super.coords.getWidth() / 2;
                this.yPosition = super.coords.getTop() + super.coords.getHeight() / 2;
            }
            this.useGhost = true;
        }
        else {
            this.xPosition = super.coords.xToPixel(this.xLoc);
            this.yPosition = super.coords.yToPixel(this.yLoc);
        }
        if (this.xPosition <= super.coords.getLeft()) {
            this.useGhost = true;
            this.xPosition = super.coords.getLeft() + 1;
        }
        else if (this.xPosition >= super.coords.getLeft() + super.coords.getWidth()) {
            this.useGhost = true;
            this.xPosition = super.coords.getLeft() + super.coords.getWidth() - 1;
        }
        if (this.yPosition <= super.coords.getTop()) {
            this.useGhost = true;
            this.yPosition = super.coords.getTop() + 1;
        }
        else if (this.yPosition >= super.coords.getTop() + super.coords.getHeight()) {
            this.useGhost = true;
            this.yPosition = super.coords.getTop() + super.coords.getHeight() - 1;
        }
    }
    
    public boolean startDrag(final MouseEvent mouseEvent) {
        this.dragging = false;
        if (mouseEvent.isConsumed() || !this.getVisible() || super.coords == null) {
            return false;
        }
        this.checkPosition();
        if (mouseEvent.getX() < this.xPosition - this.radius || mouseEvent.getX() >= this.xPosition + this.radius || mouseEvent.getY() < this.yPosition - this.radius || mouseEvent.getY() >= this.yPosition + this.radius) {
            return false;
        }
        this.dragging = true;
        mouseEvent.consume();
        return true;
    }
    
    public void continueDrag(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        double n = super.coords.pixelToX(mouseEvent.getX());
        double n2 = super.coords.pixelToY(mouseEvent.getY());
        if (n < super.coords.getXmin()) {
            n = super.coords.getXmin();
        }
        else if (n > super.coords.getXmax()) {
            n = super.coords.getXmax();
        }
        if (n2 < super.coords.getYmin()) {
            n2 = super.coords.getYmin();
        }
        else if (n2 > super.coords.getYmax()) {
            n2 = super.coords.getYmax();
        }
        this.setLocation(n, n2);
        if (Double.isNaN(this.xLoc) || Double.isNaN(this.yLoc)) {
            this.xPosition = x;
            this.yPosition = y;
        }
        if (this.onUserAction != null) {
            this.onUserAction.compute();
        }
    }
    
    public void finishDrag(final MouseEvent mouseEvent) {
        this.dragging = false;
    }
    
    private class DPV extends Variable implements Tieable
    {
        private boolean isXVar;
        long serialNumber;
        boolean needsClamp;
        
        DPV(final boolean isXVar) {
            super(isXVar ? "xDrag" : "yDrag");
            this.isXVar = isXVar;
            super.setVal(Double.NaN);
        }
        
        public double getVal() {
            if (this.needsClamp) {
                if (this.isXVar) {
                    if (DraggablePoint.this.clampX != null) {
                        DraggablePoint.this.xLoc = DraggablePoint.this.clampX.getVal();
                        this.setVariableValue(DraggablePoint.this.xLoc);
                    }
                }
                else if (DraggablePoint.this.clampY != null) {
                    DraggablePoint.this.yLoc = DraggablePoint.this.clampY.getVal();
                    this.setVariableValue(DraggablePoint.this.yLoc);
                }
                this.needsClamp = false;
            }
            return super.getVal();
        }
        
        public void setVal(final double n) {
            if (this.isXVar) {
                DraggablePoint.this.setLocation(n, DraggablePoint.this.yVar.getVal());
            }
            else {
                DraggablePoint.this.setLocation(DraggablePoint.this.xVar.getVal(), n);
            }
        }
        
        void setVariableValue(final double val) {
            super.setVal(val);
            this.needsClamp = false;
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
