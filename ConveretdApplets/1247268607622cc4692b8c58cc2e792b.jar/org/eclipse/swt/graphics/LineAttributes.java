// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.graphics;

public class LineAttributes
{
    public float width;
    public int style;
    public int cap;
    public int join;
    public float[] dash;
    public float dashOffset;
    public float miterLimit;
    
    public LineAttributes(final float n) {
        this(n, 1, 1, 1, null, 0.0f, 10.0f);
    }
    
    public LineAttributes(final float n, final int n2, final int n3) {
        this(n, n2, n3, 1, null, 0.0f, 10.0f);
    }
    
    public LineAttributes(final float width, final int cap, final int join, final int style, final float[] dash, final float dashOffset, final float miterLimit) {
        this.width = width;
        this.cap = cap;
        this.join = join;
        this.style = style;
        this.dash = dash;
        this.dashOffset = dashOffset;
        this.miterLimit = miterLimit;
    }
}
