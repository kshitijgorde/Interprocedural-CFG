// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.OS;

public final class Region extends Resource
{
    public int handle;
    
    public Region() {
        this(null);
    }
    
    public Region(final Device device) {
        super(device);
        this.handle = OS.CreateRectRgn(0, 0, 0, 0);
        if (this.handle == 0) {
            SWT.error(2);
        }
        this.init();
    }
    
    Region(final Device device, final int handle) {
        super(device);
        this.handle = handle;
    }
    
    public void add(final int[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        final int createPolygonRgn = OS.CreatePolygonRgn(array, array.length / 2, 1);
        OS.CombineRgn(this.handle, this.handle, createPolygonRgn, 2);
        OS.DeleteObject(createPolygonRgn);
    }
    
    public void add(final Rectangle rectangle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        this.add(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void add(final int n, final int n2, final int n3, final int n4) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (n3 < 0 || n4 < 0) {
            SWT.error(5);
        }
        final int createRectRgn = OS.CreateRectRgn(n, n2, n + n3, n2 + n4);
        OS.CombineRgn(this.handle, this.handle, createRectRgn, 2);
        OS.DeleteObject(createRectRgn);
    }
    
    public void add(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 2);
    }
    
    public boolean contains(final int n, final int n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        return OS.PtInRegion(this.handle, n, n2);
    }
    
    public boolean contains(final Point point) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (point == null) {
            SWT.error(4);
        }
        return this.contains(point.x, point.y);
    }
    
    void destroy() {
        OS.DeleteObject(this.handle);
        this.handle = 0;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof Region && this.handle == ((Region)o).handle);
    }
    
    public Rectangle getBounds() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RECT rect = new RECT();
        OS.GetRgnBox(this.handle, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public int hashCode() {
        return this.handle;
    }
    
    public void intersect(final Rectangle rectangle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        this.intersect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void intersect(final int n, final int n2, final int n3, final int n4) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (n3 < 0 || n4 < 0) {
            SWT.error(5);
        }
        final int createRectRgn = OS.CreateRectRgn(n, n2, n + n3, n2 + n4);
        OS.CombineRgn(this.handle, this.handle, createRectRgn, 1);
        OS.DeleteObject(createRectRgn);
    }
    
    public void intersect(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 1);
    }
    
    public boolean intersects(final int n, final int n2, final int n3, final int n4) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        return OS.RectInRegion(this.handle, rect);
    }
    
    public boolean intersects(final Rectangle rectangle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        return this.intersects(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public boolean isDisposed() {
        return this.handle == 0;
    }
    
    public boolean isEmpty() {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        final RECT rect = new RECT();
        return OS.GetRgnBox(this.handle, rect) == 1 || rect.right - rect.left <= 0 || rect.bottom - rect.top <= 0;
    }
    
    public void subtract(final int[] array) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (array == null) {
            SWT.error(4);
        }
        if (OS.IsWinCE) {
            SWT.error(20);
        }
        final int createPolygonRgn = OS.CreatePolygonRgn(array, array.length / 2, 1);
        OS.CombineRgn(this.handle, this.handle, createPolygonRgn, 4);
        OS.DeleteObject(createPolygonRgn);
    }
    
    public void subtract(final Rectangle rectangle) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (rectangle == null) {
            SWT.error(4);
        }
        this.subtract(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void subtract(final int n, final int n2, final int n3, final int n4) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (n3 < 0 || n4 < 0) {
            SWT.error(5);
        }
        final int createRectRgn = OS.CreateRectRgn(n, n2, n + n3, n2 + n4);
        OS.CombineRgn(this.handle, this.handle, createRectRgn, 4);
        OS.DeleteObject(createRectRgn);
    }
    
    public void subtract(final Region region) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (region == null) {
            SWT.error(4);
        }
        if (region.isDisposed()) {
            SWT.error(5);
        }
        OS.CombineRgn(this.handle, this.handle, region.handle, 4);
    }
    
    public void translate(final int n, final int n2) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        OS.OffsetRgn(this.handle, n, n2);
    }
    
    public void translate(final Point point) {
        if (this.isDisposed()) {
            SWT.error(44);
        }
        if (point == null) {
            SWT.error(4);
        }
        this.translate(point.x, point.y);
    }
    
    public String toString() {
        if (this.isDisposed()) {
            return "Region {*DISPOSED*}";
        }
        return "Region {" + this.handle + "}";
    }
    
    public static Region win32_new(final Device device, final int n) {
        return new Region(device, n);
    }
}
