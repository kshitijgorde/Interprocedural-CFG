// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.graphs;

import java.awt.Graphics;
import java.util.Vector;

public class CoordinateRect
{
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private int gap;
    private int left;
    private int top;
    private int width;
    private int height;
    protected Vector drawItems;
    protected boolean needsReset;
    private double restore_xmin;
    private double restore_xmax;
    private double restore_ymin;
    private double restore_ymax;
    
    public double getXmin() {
        if (this.needsReset) {
            this.checkLimits();
        }
        return this.xmin;
    }
    
    public void setXmin(final double xmin) {
        if (this.xmin != xmin) {
            this.xmin = xmin;
            this.needsReset = true;
        }
    }
    
    public double getXmax() {
        if (this.needsReset) {
            this.checkLimits();
        }
        return this.xmax;
    }
    
    public void setXmax(final double xmax) {
        if (this.xmax != xmax) {
            this.xmax = xmax;
            this.needsReset = true;
        }
    }
    
    public double getYmin() {
        if (this.needsReset) {
            this.checkLimits();
        }
        return this.ymin;
    }
    
    public void setYmin(final double ymin) {
        if (this.ymin != ymin) {
            this.ymin = ymin;
            this.needsReset = true;
        }
    }
    
    public double getYmax() {
        if (this.needsReset) {
            this.checkLimits();
        }
        return this.ymax;
    }
    
    public void setYmax(final double ymax) {
        if (this.ymax != ymax) {
            this.ymax = ymax;
            this.needsReset = true;
        }
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setGap(final int gap) {
        if (this.gap >= 0) {
            this.gap = gap;
            this.needsReset = true;
        }
    }
    
    public double[] getLimits() {
        if (this.needsReset) {
            this.checkLimits();
        }
        return new double[] { this.xmin, this.xmax, this.ymin, this.ymax };
    }
    
    public void setLimits(final double xmin, final double xmax, final double ymin, final double ymax) {
        this.setXmin(xmin);
        this.setXmax(xmax);
        this.setYmin(ymin);
        this.setYmax(ymax);
    }
    
    public void setLimits(final double[] array) {
        if (array != null && array.length >= 4) {
            this.setLimits(array[0], array[1], array[2], array[3]);
        }
    }
    
    public void reset() {
        this.needsReset = true;
    }
    
    public void releaseResources() {
        for (int i = 0; i < this.drawItems.size(); ++i) {
            ((Drawable)this.drawItems.elementAt(i)).releaseResources();
        }
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getTop() {
        return this.top;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public CoordinateRect() {
        this.gap = 5;
        this.width = -1;
        this.height = -1;
        this.drawItems = new Vector();
        this.restore_xmin = Double.NaN;
        this.setLimits(-5.0, 5.0, -5.0, 5.0);
    }
    
    public CoordinateRect(final double n, final double n2, final double n3, final double n4) {
        this.gap = 5;
        this.width = -1;
        this.height = -1;
        this.drawItems = new Vector();
        this.restore_xmin = Double.NaN;
        this.setLimits(n, n2, n3, n4);
    }
    
    public double[] restore() {
        if (Double.isNaN(this.restore_xmin)) {
            return null;
        }
        this.setLimits(this.restore_xmin, this.restore_xmax, this.restore_ymin, this.restore_ymax);
        return this.getLimits();
    }
    
    public void clearRestoreBuffer() {
        this.restore_xmin = Double.NaN;
    }
    
    public void setRestoreBuffer() {
        this.restore_xmin = this.getXmin();
        this.restore_xmax = this.xmax;
        this.restore_ymin = this.ymin;
        this.restore_ymax = this.ymax;
    }
    
    public double[] zoomIn() {
        final double n = (this.xmax - this.xmin) / 4.0;
        final double n2 = (this.ymax - this.ymin) / 4.0;
        final double n3 = (this.xmin + this.xmax) / 2.0;
        final double n4 = (this.ymin + this.ymax) / 2.0;
        if (Math.abs(n2) < 1.0E-100 || Math.abs(n) < 1.0E-100) {
            return null;
        }
        this.setLimits(n3 - n, n3 + n, n4 - n2, n4 + n2);
        return this.getLimits();
    }
    
    public double[] zoomOut() {
        final double n = this.xmax - this.xmin;
        final double n2 = this.ymax - this.ymin;
        final double n3 = (this.xmin + this.xmax) / 2.0;
        final double n4 = (this.ymin + this.ymax) / 2.0;
        if (Math.abs(n) > 1.0E100 || Math.abs(n2) > 1.0E100) {
            return null;
        }
        this.setLimits(n3 - n, n3 + n, n4 - n2, n4 + n2);
        return this.getLimits();
    }
    
    public double[] zoomInOnPixel(final int n, final int n2) {
        final double n3 = (this.xmax - this.xmin) / 4.0;
        final double n4 = (this.ymax - this.ymin) / 4.0;
        if (Math.abs(n4) < 1.0E-100 || Math.abs(n3) < 1.0E-100) {
            return null;
        }
        final double pixelToX = this.pixelToX(n);
        final double pixelToY = this.pixelToY(n2);
        final double n5 = (this.xmin + this.xmax) / 2.0;
        final double n6 = (this.ymin + this.ymax) / 2.0;
        final double n7 = (n5 + pixelToX) / 2.0;
        final double n8 = (n6 + pixelToY) / 2.0;
        this.setLimits(n7 - n3, n7 + n3, n8 - n4, n8 + n4);
        return this.getLimits();
    }
    
    public double[] zoomOutFromPixel(final int n, final int n2) {
        final double n3 = this.xmax - this.xmin;
        final double n4 = this.ymax - this.ymin;
        if (Math.abs(n3) > 1.0E100 || Math.abs(n4) > 1.0E100) {
            return null;
        }
        final double pixelToX = this.pixelToX(n);
        final double pixelToY = this.pixelToY(n2);
        final double n5 = (this.xmin + this.xmax) / 2.0;
        final double n6 = (this.ymin + this.ymax) / 2.0;
        final double n7 = 2.0 * n5 - pixelToX;
        final double n8 = 2.0 * n6 - pixelToY;
        this.setLimits(n7 - n3, n7 + n3, n8 - n4, n8 + n4);
        return this.getLimits();
    }
    
    public double[] equalizeAxes() {
        final double n = this.xmax - this.xmin;
        final double n2 = this.ymax - this.ymin;
        final double n3 = n / (this.width - 2 * this.gap - 1);
        final double n4 = n2 / (this.height - 2 * this.gap - 1);
        if (n3 < n4) {
            final double n5 = (this.xmax + this.xmin) / 2.0;
            final double n6 = n / 2.0 * n4 / n3;
            this.xmax = n5 + n6;
            this.xmin = n5 - n6;
        }
        else {
            if (n3 <= n4) {
                return null;
            }
            final double n7 = (this.ymax + this.ymin) / 2.0;
            final double n8 = n2 / 2.0 * n3 / n4;
            this.ymax = n7 + n8;
            this.ymin = n7 - n8;
        }
        this.needsReset = true;
        return this.getLimits();
    }
    
    public int xToPixel(final double n) {
        return this.left + this.gap + (int)((n - this.xmin) / (this.xmax - this.xmin) * (this.width - 2 * this.gap - 1));
    }
    
    public int yToPixel(final double n) {
        return this.top + this.gap + (int)((this.ymax - n) / (this.ymax - this.ymin) * (this.height - 2 * this.gap - 1));
    }
    
    public double pixelToX(final int n) {
        return this.xmin + (n - this.left - this.gap) * (this.xmax - this.xmin) / (this.width - 2 * this.gap - 1);
    }
    
    public double pixelToY(final int n) {
        return this.ymax - (n - this.top - this.gap) * (this.ymax - this.ymin) / (this.height - 2 * this.gap - 1);
    }
    
    public double getPixelWidth() {
        return (this.xmax - this.xmin) / (this.width - 2 * this.gap - 1);
    }
    
    public double getPixelHeight() {
        return (this.ymax - this.ymin) / (this.height - 2 * this.gap - 1);
    }
    
    public synchronized void add(final Drawable drawable) {
        if (drawable != null && !this.drawItems.contains(drawable)) {
            this.drawItems.addElement(drawable);
        }
    }
    
    public synchronized void clear() {
        this.drawItems.removeAllElements();
    }
    
    public synchronized void remove(final Drawable drawable) {
        this.drawItems.removeElement(drawable);
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, 0, 0, n, n2);
    }
    
    public synchronized void draw(final Graphics graphics, final int left, final int top, final int width, final int height) {
        if (this.needsReset || this.left != left || this.top != top || this.width != width || this.height != height) {
            this.width = width;
            this.height = height;
            this.left = left;
            this.top = top;
            this.checkLimits();
            this.needsReset = true;
        }
        this.prepare();
        this.doDraw(graphics);
    }
    
    public synchronized void prepare() {
        if (this.needsReset) {
            for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
                ((Drawable)this.drawItems.elementAt(i)).reset();
            }
            this.needsReset = false;
        }
    }
    
    protected void doDraw(final Graphics graphics) {
        for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
            ((Drawable)this.drawItems.elementAt(i)).draw(graphics, this);
        }
    }
    
    private void checkLimits() {
        if (this.xmin == this.xmax) {
            --this.xmin;
            ++this.xmax;
        }
        else if (this.xmin > this.xmax) {
            final double xmin = this.xmin;
            this.xmin = this.xmax;
            this.xmax = xmin;
        }
        if (this.ymin == this.ymax) {
            --this.ymin;
            ++this.ymax;
        }
        if (this.ymin > this.ymax) {
            final double ymin = this.ymin;
            this.ymin = this.ymax;
            this.ymax = ymin;
        }
    }
}
