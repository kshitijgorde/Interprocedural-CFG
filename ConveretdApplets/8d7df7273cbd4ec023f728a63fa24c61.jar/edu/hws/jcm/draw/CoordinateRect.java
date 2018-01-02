// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import edu.hws.jcm.data.Value;
import edu.hws.jcm.awt.Controller;
import edu.hws.jcm.awt.Tie;
import java.util.Vector;
import edu.hws.jcm.awt.InputObject;
import edu.hws.jcm.awt.Computable;
import edu.hws.jcm.awt.Limits;
import edu.hws.jcm.awt.Tieable;

public class CoordinateRect implements Tieable, Limits, Computable, InputObject
{
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    private int gap;
    protected Vector drawItems;
    protected boolean changed;
    private long serialNumber;
    private Tie syncWith;
    public static final int XMIN = 0;
    public static final int XMAX = 1;
    public static final int YMIN = 2;
    public static final int YMAX = 3;
    protected Controller onChange;
    private int left;
    private int top;
    private int width;
    private int height;
    private double restore_xmin;
    private double restore_xmax;
    private double restore_ymin;
    private double restore_ymax;
    private DisplayCanvas canvas;
    
    public CoordinateRect() {
        this(-5.0, 5.0, -5.0, 5.0);
    }
    
    public CoordinateRect(final double n, final double n2, final double n3, final double n4) {
        this.gap = 5;
        this.drawItems = new Vector();
        this.width = -1;
        this.height = -1;
        this.restore_xmin = Double.NaN;
        this.setLimits(n, n2, n3, n4);
        this.serialNumber = 0L;
        this.setRestoreBuffer();
    }
    
    public double getXmin() {
        return this.xmin;
    }
    
    public double getXmax() {
        return this.xmax;
    }
    
    public double getYmin() {
        return this.ymin;
    }
    
    public double getYmax() {
        return this.ymax;
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setGap(final int gap) {
        if (gap >= 0 && this.gap != gap) {
            final int gap2 = this.gap;
            this.gap = gap;
            this.changed = true;
            ++this.serialNumber;
            this.needsRedraw();
        }
    }
    
    public double[] getLimits() {
        return new double[] { this.xmin, this.xmax, this.ymin, this.ymax };
    }
    
    public void setLimits(final double xmin, final double xmax, final double ymin, final double ymax) {
        final double[] limits = this.getLimits();
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.checkLimits();
        final double[] limits2 = this.getLimits();
        if (limits[0] == limits2[0] && limits[1] == limits2[1] && limits[2] == limits2[2] && limits[3] == limits2[3]) {
            return;
        }
        this.changed = true;
        ++this.serialNumber;
        if (this.syncWith != null) {
            this.syncWith.check();
        }
        if (this.onChange != null) {
            this.onChange.compute();
        }
        this.needsRedraw();
    }
    
    public void setLimits(final double[] array) {
        if (array != null && array.length >= 4) {
            this.setLimits(array[0], array[1], array[2], array[3]);
        }
    }
    
    public void setOnChange(final Controller onChange) {
        this.onChange = onChange;
    }
    
    public Controller getOnChange() {
        return this.onChange;
    }
    
    public Value getValueObject(final int n) {
        return new Value() {
            public double getVal() {
                switch (n) {
                    case 0: {
                        return CoordinateRect.this.getXmin();
                    }
                    case 1: {
                        return CoordinateRect.this.getXmax();
                    }
                    case 2: {
                        return CoordinateRect.this.getYmin();
                    }
                    default: {
                        return CoordinateRect.this.getYmax();
                    }
                }
            }
        };
    }
    
    public long getSerialNumber() {
        return this.serialNumber;
    }
    
    public void setSyncWith(final Tie syncWith) {
        this.syncWith = syncWith;
    }
    
    public void sync(final Tie tie, final Tieable tieable) {
        if (tieable != this) {
            if (!(tieable instanceof Limits)) {
                throw new IllegalArgumentException("Internal programming error:  A CoordinateRect can only be tied to a Limits object.");
            }
            final double[] limits = ((Limits)tieable).getLimits();
            if (limits != null && limits.length >= 4) {
                final double[] limits2 = this.getLimits();
                if (limits[0] == limits2[0] && limits[1] == limits2[1] && limits[2] == limits2[2] && limits[3] == limits2[3]) {
                    return;
                }
                this.xmin = limits[0];
                this.xmax = limits[1];
                this.ymin = limits[2];
                this.ymax = limits[3];
                this.checkLimits();
                this.serialNumber = tieable.getSerialNumber();
                this.changed = true;
                if (this.onChange != null) {
                    this.onChange.compute();
                }
                this.needsRedraw();
            }
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
    
    public double getPixelWidth() {
        return (this.xmax - this.xmin) / (this.width - 2 * this.gap - 1);
    }
    
    public double getPixelHeight() {
        return (this.ymax - this.ymin) / (this.height - 2 * this.gap - 1);
    }
    
    public int xToPixel(final double n) {
        final int n2 = this.left + this.gap + (int)((n - this.xmin) / (this.xmax - this.xmin) * (this.width - 2 * this.gap - 1));
        if (n2 < -32000) {
            return -32000;
        }
        if (n2 > 32000) {
            return 32000;
        }
        return n2;
    }
    
    public int yToPixel(final double n) {
        final int n2 = this.top + this.gap + (int)((this.ymax - n) / (this.ymax - this.ymin) * (this.height - 2 * this.gap - 1));
        if (n2 < -32000) {
            return -32000;
        }
        if (n2 > 32000) {
            return 32000;
        }
        return n2;
    }
    
    public double pixelToX(final int n) {
        return this.xmin + (n - this.left - this.gap) * (this.xmax - this.xmin) / (this.width - 2 * this.gap - 1);
    }
    
    public double pixelToY(final int n) {
        return this.ymax - (n - this.top - this.gap) * (this.ymax - this.ymin) / (this.height - 2 * this.gap - 1);
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
        if (this.badData()) {
            return;
        }
        this.checkLimits();
        this.restore_xmin = this.xmin;
        this.restore_xmax = this.xmax;
        this.restore_ymin = this.ymin;
        this.restore_ymax = this.ymax;
    }
    
    private boolean badData() {
        return Double.isNaN(this.xmin) || Double.isInfinite(this.xmin) || Double.isNaN(this.ymin) || Double.isInfinite(this.ymin) || Double.isNaN(this.xmax) || Double.isInfinite(this.xmax) || Double.isNaN(this.ymax) || Double.isInfinite(this.ymax);
    }
    
    public double[] zoomIn() {
        if (this.badData()) {
            return this.getLimits();
        }
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
        if (this.badData()) {
            return this.getLimits();
        }
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
        if (this.badData()) {
            return this.getLimits();
        }
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
        if (this.badData()) {
            return this.getLimits();
        }
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
        if (this.badData()) {
            return this.getLimits();
        }
        final double n = this.xmax - this.xmin;
        final double n2 = this.ymax - this.ymin;
        final double n3 = n / (this.width - 2 * this.gap - 1);
        final double n4 = n2 / (this.height - 2 * this.gap - 1);
        double xmax;
        double xmin;
        double ymin;
        double ymax;
        if (n3 < n4) {
            final double n5 = (this.xmax + this.xmin) / 2.0;
            final double n6 = n / 2.0 * n4 / n3;
            xmax = n5 + n6;
            xmin = n5 - n6;
            ymin = this.ymin;
            ymax = this.ymax;
        }
        else {
            if (n3 <= n4) {
                return null;
            }
            final double n7 = (this.ymax + this.ymin) / 2.0;
            final double n8 = n2 / 2.0 * n3 / n4;
            ymax = n7 + n8;
            ymin = n7 - n8;
            xmin = this.xmin;
            xmax = this.xmax;
        }
        this.setLimits(xmin, xmax, ymin, ymax);
        return this.getLimits();
    }
    
    void setOwner(final DisplayCanvas canvas) {
        this.canvas = canvas;
    }
    
    private void needsRedraw() {
        if (this.canvas != null) {
            this.canvas.doRedraw(this);
        }
    }
    
    public void checkInput() {
        for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
            if (this.drawItems.elementAt(i) instanceof InputObject) {
                ((InputObject)this.drawItems.elementAt(i)).checkInput();
            }
        }
    }
    
    public void compute() {
        for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
            if (this.drawItems.elementAt(i) instanceof Computable) {
                ((Computable)this.drawItems.elementAt(i)).compute();
            }
        }
    }
    
    public void notifyControllerOnChange(final Controller controller) {
        for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
            if (this.drawItems.elementAt(i) instanceof InputObject) {
                ((InputObject)this.drawItems.elementAt(i)).notifyControllerOnChange(controller);
            }
        }
    }
    
    public synchronized void add(final Drawable drawable) {
        if (drawable != null && !this.drawItems.contains(drawable)) {
            drawable.setOwnerData(this.canvas, this);
            this.drawItems.addElement(drawable);
        }
    }
    
    public synchronized void remove(final Drawable drawable) {
        if (drawable != null && this.drawItems.removeElement(drawable)) {
            drawable.setOwnerData(null, null);
        }
    }
    
    public int getDrawableCount() {
        return (this.drawItems == null) ? 0 : this.drawItems.size();
    }
    
    public Drawable getDrawable(final int n) {
        if (this.drawItems != null && n >= 0 && n < this.drawItems.size()) {
            return this.drawItems.elementAt(n);
        }
        return null;
    }
    
    Draggable checkDraggables(final MouseEvent mouseEvent) {
        for (int i = this.drawItems.size() - 1; i >= 0; --i) {
            if (this.drawItems.elementAt(i) instanceof Draggable && ((Draggable)this.drawItems.elementAt(i)).startDrag(mouseEvent)) {
                return (Draggable)this.drawItems.elementAt(i);
            }
        }
        return null;
    }
    
    public void draw(final Graphics graphics, final int n, final int n2) {
        this.draw(graphics, 0, 0, n, n2);
    }
    
    public synchronized void draw(final Graphics graphics, final int left, final int top, final int width, final int height) {
        if (this.badData()) {
            graphics.setColor(Color.red);
            graphics.drawRect(left, top, width - 1, height - 1);
            graphics.drawString("(undefined limits)", left + 6, top + 15);
        }
        if (this.changed || this.left != left || this.top != top || this.width != width || this.height != height) {
            this.width = width;
            this.height = height;
            this.left = left;
            this.top = top;
            this.checkLimits();
            this.changed = true;
        }
        this.doDraw(graphics);
        this.changed = false;
    }
    
    protected void doDraw(final Graphics graphics) {
        for (int size = this.drawItems.size(), i = 0; i < size; ++i) {
            final Drawable drawable = this.drawItems.elementAt(i);
            if (drawable.getVisible()) {
                drawable.draw(graphics, this.changed);
            }
        }
    }
}
