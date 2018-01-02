// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.layout;

public class XYConstraints implements Cloneable
{
    int x;
    int y;
    int width;
    int height;
    
    public XYConstraints() {
        this(0, 0, 0, 0);
    }
    
    public XYConstraints(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public int hashCode() {
        return this.x ^ this.y * 37 ^ this.width * 43 ^ this.height * 47;
    }
    
    public boolean equals(final Object o) {
        if (o instanceof XYConstraints) {
            final XYConstraints xyConstraints = (XYConstraints)o;
            return xyConstraints.x == this.x && xyConstraints.y == this.y && xyConstraints.width == this.width && xyConstraints.height == this.height;
        }
        return false;
    }
    
    public Object clone() {
        return new XYConstraints(this.x, this.y, this.width, this.height);
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf("XYConstraints[").concat(String.valueOf(this.x))).concat(String.valueOf(","))).concat(String.valueOf(this.y))).concat(String.valueOf(","))).concat(String.valueOf(this.width))).concat(String.valueOf(","))).concat(String.valueOf(this.height))).concat(String.valueOf("]"));
    }
}
