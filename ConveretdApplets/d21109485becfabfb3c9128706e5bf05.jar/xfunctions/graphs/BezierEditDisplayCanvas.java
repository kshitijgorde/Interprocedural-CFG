// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Event;
import java.awt.Graphics;
import xfunctions.functions.BezierFunction;

public class BezierEditDisplayCanvas extends DisplayCanvas
{
    private BezierFunction func;
    private boolean startingDrag;
    private boolean dragging;
    private int startx;
    private int starty;
    private int prevx;
    private int prevy;
    
    protected void drawExtraStuff(final Graphics graphics) {
        if (this.func != null) {
            final int gap = super.coords.getGap();
            this.func.drawDirect(graphics, gap, this.size().width - gap, gap, this.size().height - gap, super.coords.getXmin(), super.coords.getXmax(), super.coords.getYmin(), super.coords.getYmax(), true);
        }
    }
    
    public void setFunction(final BezierFunction func) {
        this.func = func;
        this.invalidateCanvas();
    }
    
    private void startDrag(final int n, final int n2) {
        if (this.func.findHit(n, n2, super.coords, true, 5)) {
            this.startingDrag = true;
            this.startx = n;
            this.starty = n2;
            this.prevx = n;
            this.prevy = n2;
        }
    }
    
    private void continueDrag(int max, int max2) {
        max = Math.max(0, Math.min(max, this.size().width));
        max2 = Math.max(0, Math.min(max2, this.size().height));
        if (max == this.prevx && max2 == this.prevy) {
            return;
        }
        this.prevx = max;
        this.prevy = max2;
        this.func.continueDrag(max, max2, super.coords);
        this.invalidateCanvas();
    }
    
    private void endDrag(final int n, final int n2) {
        if (n != this.prevx || n2 != this.prevy) {
            this.continueDrag(n, n2);
        }
        this.dragging = false;
    }
    
    private void doSplitJoin(final int n, final int n2) {
        if (this.func.findHit(n, n2, super.coords, true, 5)) {
            this.func.doBreakAfterFindHit(super.coords);
            this.invalidateCanvas();
        }
    }
    
    private void doNewPoint(final int n, final int n2) {
        if (this.func.findHit(n, n2, super.coords, false, 3)) {
            this.func.doJoinAfterFindHit();
            this.invalidateCanvas();
        }
        else if (this.func.doNewPoint(super.coords.pixelToX(n), super.coords.pixelToY(n2), super.coords.getPixelWidth(), super.coords.getPixelHeight())) {
            this.invalidateCanvas();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.getErrorMessage() != null) {
            this.clearErrorMessage();
            return true;
        }
        if (this.func == null) {
            return true;
        }
        if (this.dragging) {
            this.endDrag(this.prevx, this.prevy);
        }
        if (event.controlDown() || event.clickCount == 2) {
            this.doNewPoint(n, n2);
        }
        else if (event.shiftDown() || event.metaDown()) {
            this.doSplitJoin(n, n2);
        }
        else {
            this.startDrag(n, n2);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.startingDrag && (Math.abs(n - this.startx) > 1 || Math.abs(n2 - this.starty) > 1)) {
            this.dragging = true;
            this.startingDrag = false;
        }
        if (this.dragging) {
            this.continueDrag(n, n2);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.dragging) {
            this.endDrag(n, n2);
        }
        this.startingDrag = false;
        return true;
    }
}
