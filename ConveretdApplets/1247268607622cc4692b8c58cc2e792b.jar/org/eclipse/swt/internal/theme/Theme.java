// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.theme;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Device;

public class Theme
{
    Device device;
    
    public Theme(final Device device) {
        this.device = device;
    }
    
    public Rectangle computeTrim(final GC gc, final DrawData drawData) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        return drawData.computeTrim(this, gc);
    }
    
    void checkTheme() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
    }
    
    public void dispose() {
        this.device = null;
    }
    
    public void drawBackground(final GC gc, final Rectangle rectangle, final DrawData drawData) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        drawData.draw(this, gc, rectangle);
    }
    
    public void drawFocus(final GC gc, final Rectangle rectangle, final DrawData drawData) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        gc.drawFocus(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void drawImage(final GC gc, final Rectangle rectangle, final DrawData drawData, final Image image, final int n) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (image == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        drawData.drawImage(this, image, gc, rectangle);
    }
    
    public void drawText(final GC gc, final Rectangle rectangle, final DrawData drawData, final String s, final int n) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (s == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        drawData.drawText(this, s, n, gc, rectangle);
    }
    
    public Rectangle getBounds(final int n, final Rectangle rectangle, final DrawData drawData) {
        this.checkTheme();
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        return drawData.getBounds(n, rectangle);
    }
    
    public int getSelection(final Point point, final Rectangle rectangle, final RangeDrawData rangeDrawData) {
        this.checkTheme();
        if (point == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (rangeDrawData == null) {
            SWT.error(4);
        }
        return rangeDrawData.getSelection(point, rectangle);
    }
    
    public int hitBackground(final Point point, final Rectangle rectangle, final DrawData drawData) {
        this.checkTheme();
        if (point == null) {
            SWT.error(4);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        return drawData.hit(this, point, rectangle);
    }
    
    public boolean isDisposed() {
        return this.device == null;
    }
    
    public Rectangle measureText(final GC gc, final Rectangle rectangle, final DrawData drawData, final String s, final int n) {
        this.checkTheme();
        if (gc == null) {
            SWT.error(4);
        }
        if (drawData == null) {
            SWT.error(4);
        }
        if (s == null) {
            SWT.error(4);
        }
        if (gc.isDisposed()) {
            SWT.error(5);
        }
        return drawData.measureText(this, s, n, gc, rectangle);
    }
}
