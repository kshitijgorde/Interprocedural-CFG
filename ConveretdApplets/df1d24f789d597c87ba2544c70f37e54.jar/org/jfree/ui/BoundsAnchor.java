// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.io.Serializable;

public final class BoundsAnchor implements Serializable
{
    public static final BoundsAnchor TOP_LEFT;
    public static final BoundsAnchor TOP_CENTER;
    public static final BoundsAnchor TOP_RIGHT;
    public static final BoundsAnchor MIDDLE_LEFT;
    public static final BoundsAnchor CENTER;
    public static final BoundsAnchor MIDDLE_RIGHT;
    public static final BoundsAnchor BOTTOM_LEFT;
    public static final BoundsAnchor BOTTOM_CENTER;
    public static final BoundsAnchor BOTTOM_RIGHT;
    private String name;
    
    private BoundsAnchor(final String name) {
        this.name = name;
    }
    
    public String toString() {
        return this.name;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o instanceof BoundsAnchor && this.name.equals(((BoundsAnchor)o).name));
    }
    
    public int hashCode() {
        return this.name.hashCode();
    }
    
    static {
        TOP_LEFT = new BoundsAnchor("BoundsAnchor.TOP_LEFT");
        TOP_CENTER = new BoundsAnchor("BoundsAnchor.TOP_CENTER");
        TOP_RIGHT = new BoundsAnchor("BoundsAnchor.TOP_RIGHT");
        MIDDLE_LEFT = new BoundsAnchor("BoundsAnchor.MIDDLE_LEFT");
        CENTER = new BoundsAnchor("BoundsAnchor.CENTER");
        MIDDLE_RIGHT = new BoundsAnchor("BoundsAnchor.MIDDLE_RIGHT");
        BOTTOM_LEFT = new BoundsAnchor("BoundsAnchor.BOTTOM_LEFT");
        BOTTOM_CENTER = new BoundsAnchor("BoundsAnchor.BOTTOM_CENTER");
        BOTTOM_RIGHT = new BoundsAnchor("BoundsAnchor.BOTTOM_RIGHT");
    }
}
