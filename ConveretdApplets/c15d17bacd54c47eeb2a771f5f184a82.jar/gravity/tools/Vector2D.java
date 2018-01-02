// 
// Decompiled by Procyon v0.5.30
// 

package gravity.tools;

public class Vector2D
{
    public double x;
    public double y;
    
    public Vector2D() {
        final double n = 0.0;
        this.y = n;
        this.x = n;
    }
    
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(final Vector2D vector2D) {
        return vector2D != null && this.x == vector2D.x && this.y == vector2D.y;
    }
    
    public Object clone() {
        return new Vector2D(this.x, this.y);
    }
}
