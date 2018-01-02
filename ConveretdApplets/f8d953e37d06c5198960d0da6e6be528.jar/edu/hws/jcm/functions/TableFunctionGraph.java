// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import java.awt.event.MouseEvent;
import java.awt.Graphics;
import edu.hws.jcm.draw.CoordinateRect;
import edu.hws.jcm.draw.DisplayCanvas;
import java.awt.Color;
import edu.hws.jcm.awt.Computable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import edu.hws.jcm.draw.Drawable;

public class TableFunctionGraph extends Drawable implements MouseListener, MouseMotionListener
{
    private TableFunction function;
    private boolean showPoints;
    private boolean interactive;
    private Computable onDrag;
    private Computable onFinishDrag;
    private Color color;
    private int dragPoint;
    private int startX;
    private int startY;
    private int prevY;
    private boolean moved;
    
    public TableFunctionGraph() {
        this(null);
    }
    
    public TableFunctionGraph(final TableFunction function) {
        this.dragPoint = -1;
        this.function = function;
        this.color = Color.magenta;
        this.showPoints = true;
    }
    
    public void setFunction(final TableFunction function) {
        this.function = function;
        this.needsRedraw();
    }
    
    public TableFunction getFunction() {
        return this.function;
    }
    
    public void setOnDrag(final Computable onDrag) {
        this.onDrag = onDrag;
    }
    
    public Computable getOnDrag() {
        return this.onDrag;
    }
    
    public void setOnFinishDrag(final Computable onFinishDrag) {
        this.onFinishDrag = onFinishDrag;
    }
    
    public Computable getOnFinishDrag() {
        return this.onFinishDrag;
    }
    
    public void setInteractive(final boolean interactive) {
        if (this.interactive == interactive) {
            return;
        }
        if (this.interactive && super.canvas != null) {
            super.canvas.removeMouseListener(this);
            super.canvas.removeMouseMotionListener(this);
        }
        this.interactive = interactive;
        if (this.interactive && super.canvas != null) {
            super.canvas.addMouseListener(this);
            super.canvas.addMouseMotionListener(this);
        }
    }
    
    public boolean getInteractive() {
        return this.interactive;
    }
    
    public void setShowPoints(final boolean showPoints) {
        this.showPoints = showPoints;
        this.needsRedraw();
    }
    
    public boolean getShowPoints() {
        return this.showPoints;
    }
    
    public void setColor(final Color color) {
        if (color != null) {
            this.color = color;
            this.needsRedraw();
        }
    }
    
    public Color getColor() {
        return this.color;
    }
    
    protected void setOwnerData(final DisplayCanvas displayCanvas, final CoordinateRect coordinateRect) {
        if (this.interactive && super.canvas != null) {
            displayCanvas.removeMouseListener(this);
            displayCanvas.removeMouseMotionListener(this);
        }
        super.setOwnerData(displayCanvas, coordinateRect);
        if (this.interactive && super.canvas != null) {
            displayCanvas.addMouseListener(this);
            displayCanvas.addMouseMotionListener(this);
        }
    }
    
    public void setFunctionStyle(final int style) {
        if (this.function != null && this.function.getStyle() != style) {
            this.function.setStyle(style);
            this.needsRedraw();
        }
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (this.function == null || super.coords == null) {
            return;
        }
        final int pointCount = this.function.getPointCount();
        if (pointCount == 0) {
            return;
        }
        final double pixelToX = super.coords.pixelToX(super.coords.getLeft());
        final double pixelToX2 = super.coords.pixelToX(super.coords.getLeft() + super.coords.getWidth());
        if (this.function.getX(0) > pixelToX2 || this.function.getX(pointCount - 1) < pixelToX) {
            return;
        }
        int n;
        for (n = 0; n < pointCount - 1 && this.function.getX(n + 1) <= pixelToX; ++n) {}
        int n2;
        for (n2 = pointCount - 1; n2 > 1 && this.function.getX(n2 - 1) >= pixelToX2; --n2) {}
        graphics.setColor(this.color);
        switch (this.function.getStyle()) {
            case 0: {
                if (n2 > n) {
                    double x = this.function.getX(n);
                    final double val = this.function.getVal(x);
                    int xToPixel = super.coords.xToPixel(x);
                    int yToPixel = super.coords.yToPixel(val);
                    double x2 = pixelToX2;
                    if (this.function.getX(n2) < x2) {
                        x2 = this.function.getX(n2);
                    }
                    super.coords.xToPixel(this.function.getX(pointCount - 1));
                    double pixelToX3;
                    int yToPixel2;
                    for (int n3 = xToPixel; x < x2; x = pixelToX3, xToPixel = n3, yToPixel = yToPixel2) {
                        n3 += 3;
                        pixelToX3 = super.coords.pixelToX(n3);
                        if (pixelToX3 > x2) {
                            pixelToX3 = x2;
                        }
                        yToPixel2 = super.coords.yToPixel(this.function.getVal(pixelToX3));
                        graphics.drawLine(xToPixel, yToPixel, n3, yToPixel2);
                    }
                    break;
                }
                break;
            }
            case 1: {
                int xToPixel2 = super.coords.xToPixel(this.function.getX(n));
                int yToPixel3 = super.coords.yToPixel(this.function.getY(n));
                for (int i = n + 1; i <= n2; ++i) {
                    final int xToPixel3 = super.coords.xToPixel(this.function.getX(i));
                    final int yToPixel4 = super.coords.yToPixel(this.function.getY(i));
                    graphics.drawLine(xToPixel2, yToPixel3, xToPixel3, yToPixel4);
                    xToPixel2 = xToPixel3;
                    yToPixel3 = yToPixel4;
                }
                break;
            }
            case 2: {
                double x3 = this.function.getX(n);
                int xToPixel4 = super.coords.xToPixel(x3);
                for (int j = n; j <= n2; ++j) {
                    double n4;
                    if (j < n2) {
                        final double x4 = this.function.getX(j + 1);
                        n4 = (x3 + x4) / 2.0;
                        x3 = x4;
                    }
                    else {
                        n4 = x3;
                    }
                    final int xToPixel5 = super.coords.xToPixel(n4);
                    final int yToPixel5 = super.coords.yToPixel(this.function.getY(j));
                    graphics.drawLine(xToPixel4, yToPixel5, xToPixel5, yToPixel5);
                    xToPixel4 = xToPixel5;
                }
                break;
            }
            case 3: {
                int xToPixel6 = super.coords.xToPixel(this.function.getX(n));
                for (int k = n + 1; k <= n2; ++k) {
                    final int xToPixel7 = super.coords.xToPixel(this.function.getX(k));
                    final int yToPixel6 = super.coords.yToPixel(this.function.getY(k - 1));
                    graphics.drawLine(xToPixel6, yToPixel6, xToPixel7, yToPixel6);
                    xToPixel6 = xToPixel7;
                }
                break;
            }
            case 4: {
                int xToPixel8 = super.coords.xToPixel(this.function.getX(n));
                for (int l = n + 1; l <= n2; ++l) {
                    final int xToPixel9 = super.coords.xToPixel(this.function.getX(l));
                    final int yToPixel7 = super.coords.yToPixel(this.function.getY(l));
                    graphics.drawLine(xToPixel8, yToPixel7, xToPixel9, yToPixel7);
                    xToPixel8 = xToPixel9;
                }
                break;
            }
        }
        if (!this.showPoints) {
            return;
        }
        for (int n5 = n; n5 <= n2; ++n5) {
            graphics.fillOval(super.coords.xToPixel(this.function.getX(n5)) - 2, super.coords.yToPixel(this.function.getY(n5)) - 2, 5, 5);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.dragPoint = -1;
        if (this.function == null || !this.getVisible() || super.canvas == null || super.coords == null || mouseEvent.isConsumed()) {
            return;
        }
        if (mouseEvent.isShiftDown() || mouseEvent.isMetaDown() || mouseEvent.isControlDown() || mouseEvent.isAltDown()) {
            return;
        }
        this.moved = false;
        for (int pointCount = this.function.getPointCount(), i = 0; i < pointCount; ++i) {
            final int xToPixel = super.coords.xToPixel(this.function.getX(i));
            final int yToPixel = super.coords.yToPixel(this.function.getY(i));
            if (mouseEvent.getX() >= xToPixel - 3 && mouseEvent.getX() <= xToPixel + 3 && mouseEvent.getY() >= yToPixel - 3 && mouseEvent.getY() <= yToPixel + 3) {
                this.startX = mouseEvent.getX();
                final int y = mouseEvent.getY();
                this.startY = y;
                this.prevY = y;
                this.dragPoint = i;
                mouseEvent.consume();
                return;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragPoint == -1) {
            return;
        }
        mouseEvent.consume();
        if (!this.moved) {
            this.dragPoint = -1;
            return;
        }
        this.mouseDragged(mouseEvent);
        this.dragPoint = -1;
        if (this.onFinishDrag != null) {
            this.onFinishDrag.compute();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.dragPoint == -1 || this.prevY == mouseEvent.getY()) {
            return;
        }
        mouseEvent.consume();
        if (!this.moved && Math.abs(mouseEvent.getY() - this.startY) < 3) {
            return;
        }
        this.moved = true;
        int prevY = mouseEvent.getY();
        if (prevY < super.coords.getTop() + 4) {
            prevY = super.coords.getTop() + 4;
        }
        else if (prevY > super.coords.getTop() + super.coords.getHeight() - 4) {
            prevY = super.coords.getTop() + super.coords.getHeight() - 4;
        }
        if (Math.abs(mouseEvent.getX() - this.startX) > 72) {
            prevY = this.startY;
        }
        if (prevY == this.prevY) {
            return;
        }
        this.prevY = prevY;
        this.function.setY(this.dragPoint, super.coords.pixelToY(this.prevY));
        this.needsRedraw();
        if (this.onDrag != null) {
            this.onDrag.compute();
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
}
