// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions;

import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import xfunctions.graphs.DisplayCanvas;

class ICPDisplayCanvas extends DisplayCanvas
{
    private IntegralCurvesPanel owner;
    private int left;
    private int top;
    private int width;
    private int height;
    private int gap;
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private Graphics g;
    
    ICPDisplayCanvas(final IntegralCurvesPanel owner) {
        this.owner = owner;
    }
    
    public void invalidateCanvas() {
        this.owner.stopAllCurves();
        this.doneDrawing();
        super.invalidateCanvas();
    }
    
    public void setErrorMessage(final String errorMessage) {
        this.owner.stopAllCurves();
        this.doneDrawing();
        super.setErrorMessage(errorMessage);
    }
    
    void startDrawing() {
        if (super.coords == null) {
            return;
        }
        (this.g = this.getGraphics()).setColor(Color.black);
        if (this.getOSG() != null) {
            this.getOSG().setColor(Color.black);
        }
        this.left = super.coords.getLeft();
        this.top = super.coords.getTop();
        this.width = super.coords.getWidth();
        this.height = super.coords.getHeight();
        this.gap = super.coords.getGap();
        this.xmin = super.coords.getXmin();
        this.xmax = super.coords.getXmax();
        this.ymin = super.coords.getYmin();
        this.ymax = super.coords.getYmax();
    }
    
    boolean putLine(final double n, final double n2, final double n3, final double n4) {
        if (this.g == null) {
            return false;
        }
        final double n5 = this.left + this.gap + (n - this.xmin) / (this.xmax - this.xmin) * (this.width - 2 * this.gap - 1);
        final double n6 = this.top + this.gap + (this.ymax - n2) / (this.ymax - this.ymin) * (this.height - 2 * this.gap - 1);
        if (Double.isNaN(n5) || Double.isNaN(n6) || Math.abs(n5) > 32000.0 || Math.abs(n6) > 32000.0) {
            return false;
        }
        final double n7 = this.left + this.gap + (n3 - this.xmin) / (this.xmax - this.xmin) * (this.width - 2 * this.gap - 1);
        final double n8 = this.top + this.gap + (this.ymax - n4) / (this.ymax - this.ymin) * (this.height - 2 * this.gap - 1);
        if (Double.isNaN(n7) || Double.isNaN(n8) || Math.abs(n7) > 32000.0 || Math.abs(n8) > 32000.0) {
            return false;
        }
        this.g.drawLine((int)n5, (int)n6, (int)n7, (int)n8);
        final Graphics osg = this.getOSG();
        if (this.g != osg && osg != null) {
            osg.drawLine((int)n5, (int)n6, (int)n7, (int)n8);
        }
        return true;
    }
    
    void doneDrawing() {
        if (this.g != null) {
            this.g.dispose();
        }
        this.g = null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (super.mouseDown(event, n, n2)) {
            return true;
        }
        if (event.metaDown()) {
            this.invalidateCanvas();
            final double[] zoomInOnPixel = super.coords.zoomInOnPixel(n, n2);
            if (zoomInOnPixel != null) {
                this.owner.numberInput.setValues(zoomInOnPixel);
            }
        }
        else if ((event.modifiers & 0x8) != 0x0) {
            this.invalidateCanvas();
            final double[] zoomOutFromPixel = super.coords.zoomOutFromPixel(n, n2);
            if (zoomOutFromPixel != null) {
                this.owner.numberInput.setValues(zoomOutFromPixel);
            }
        }
        else {
            this.owner.mouseDownOnCanvas(n, n2);
        }
        return true;
    }
}
