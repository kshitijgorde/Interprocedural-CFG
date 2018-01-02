// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

import org.eclipse.swt.internal.SerializableCompatibility;

public final class Point implements SerializableCompatibility
{
    public int x;
    public int y;
    static final long serialVersionUID = 3257002163938146354L;
    
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Point)) {
            return false;
        }
        final Point point = (Point)o;
        return point.x == this.x && point.y == this.y;
    }
    
    public int hashCode() {
        return this.x ^ this.y;
    }
    
    public String toString() {
        return "Point {" + this.x + ", " + this.y + "}";
    }
}
