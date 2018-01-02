// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.SerializableCompatibility;

public final class Rectangle implements SerializableCompatibility
{
    public int x;
    public int y;
    public int width;
    public int height;
    static final long serialVersionUID = 3256439218279428914L;
    
    public Rectangle(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void add(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        final int x = (this.x < rectangle.x) ? this.x : rectangle.x;
        final int y = (this.y < rectangle.y) ? this.y : rectangle.y;
        final int n = this.x + this.width;
        final int n2 = rectangle.x + rectangle.width;
        final int n3 = (n > n2) ? n : n2;
        final int n4 = this.y + this.height;
        final int n5 = rectangle.y + rectangle.height;
        final int n6 = (n4 > n5) ? n4 : n5;
        this.x = x;
        this.y = y;
        this.width = n3 - x;
        this.height = n6 - y;
    }
    
    public boolean contains(final int n, final int n2) {
        return n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height;
    }
    
    public boolean contains(final Point point) {
        if (point == null) {
            SWT.error(4);
        }
        return this.contains(point.x, point.y);
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Rectangle)) {
            return false;
        }
        final Rectangle rectangle = (Rectangle)o;
        return rectangle.x == this.x && rectangle.y == this.y && rectangle.width == this.width && rectangle.height == this.height;
    }
    
    public int hashCode() {
        return this.x ^ this.y ^ this.width ^ this.height;
    }
    
    public void intersect(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        if (this == rectangle) {
            return;
        }
        final int n = (this.x > rectangle.x) ? this.x : rectangle.x;
        final int n2 = (this.y > rectangle.y) ? this.y : rectangle.y;
        final int n3 = this.x + this.width;
        final int n4 = rectangle.x + rectangle.width;
        final int n5 = (n3 < n4) ? n3 : n4;
        final int n6 = this.y + this.height;
        final int n7 = rectangle.y + rectangle.height;
        final int n8 = (n6 < n7) ? n6 : n7;
        this.x = ((n5 < n) ? 0 : n);
        this.y = ((n8 < n2) ? 0 : n2);
        this.width = ((n5 < n) ? 0 : (n5 - n));
        this.height = ((n8 < n2) ? 0 : (n8 - n2));
    }
    
    public Rectangle intersection(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        if (this == rectangle) {
            return new Rectangle(this.x, this.y, this.width, this.height);
        }
        final int n = (this.x > rectangle.x) ? this.x : rectangle.x;
        final int n2 = (this.y > rectangle.y) ? this.y : rectangle.y;
        final int n3 = this.x + this.width;
        final int n4 = rectangle.x + rectangle.width;
        final int n5 = (n3 < n4) ? n3 : n4;
        final int n6 = this.y + this.height;
        final int n7 = rectangle.y + rectangle.height;
        final int n8 = (n6 < n7) ? n6 : n7;
        return new Rectangle((n5 < n) ? 0 : n, (n8 < n2) ? 0 : n2, (n5 < n) ? 0 : (n5 - n), (n8 < n2) ? 0 : (n8 - n2));
    }
    
    public boolean intersects(final int n, final int n2, final int n3, final int n4) {
        return n < this.x + this.width && n2 < this.y + this.height && n + n3 > this.x && n2 + n4 > this.y;
    }
    
    public boolean intersects(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        return rectangle == this || this.intersects(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public boolean isEmpty() {
        return this.width <= 0 || this.height <= 0;
    }
    
    public String toString() {
        return "Rectangle {" + this.x + ", " + this.y + ", " + this.width + ", " + this.height + "}";
    }
    
    public Rectangle union(final Rectangle rectangle) {
        if (rectangle == null) {
            SWT.error(4);
        }
        final int n = (this.x < rectangle.x) ? this.x : rectangle.x;
        final int n2 = (this.y < rectangle.y) ? this.y : rectangle.y;
        final int n3 = this.x + this.width;
        final int n4 = rectangle.x + rectangle.width;
        final int n5 = (n3 > n4) ? n3 : n4;
        final int n6 = this.y + this.height;
        final int n7 = rectangle.y + rectangle.height;
        return new Rectangle(n, n2, n5 - n, ((n6 > n7) ? n6 : n7) - n2);
    }
}
