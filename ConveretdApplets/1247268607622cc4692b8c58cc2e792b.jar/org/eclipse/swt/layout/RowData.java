// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.layout;

import org.eclipse.swt.graphics.Point;

public final class RowData
{
    public int width;
    public int height;
    public boolean exclude;
    
    public RowData() {
        this.width = -1;
        this.height = -1;
        this.exclude = false;
    }
    
    public RowData(final int width, final int height) {
        this.width = -1;
        this.height = -1;
        this.exclude = false;
        this.width = width;
        this.height = height;
    }
    
    public RowData(final Point point) {
        this(point.x, point.y);
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        String s = String.valueOf(this.getName()) + " {";
        if (this.width != -1) {
            s = String.valueOf(s) + "width=" + this.width + " ";
        }
        if (this.height != -1) {
            s = String.valueOf(s) + "height=" + this.height + " ";
        }
        if (this.exclude) {
            s = String.valueOf(s) + "exclude=" + this.exclude + " ";
        }
        return String.valueOf(s.trim()) + "}";
    }
}
