// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

public class StyledRectangle
{
    public int x;
    public int y;
    public int width;
    public int height;
    
    public StyledRectangle(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof StyledRectangle)) {
            return false;
        }
        final StyledRectangle styledRectangle = (StyledRectangle)o;
        return styledRectangle.x == this.x && styledRectangle.y == this.y && styledRectangle.width == this.width && styledRectangle.height == this.height;
    }
    
    public int hashCode() {
        return this.x + this.y + this.width + this.height;
    }
    
    public boolean contains(final int n, final int n2) {
        return n >= this.x && n2 >= this.y && n < this.x + this.width && n2 < this.y + this.height;
    }
    
    private boolean noEmpty(final int n, final int n2, final int n3, final int n4) {
        if (n < n3) {
            return n + n2 > n3;
        }
        return n3 + n4 > n;
    }
    
    public boolean hit(final StyledRectangle styledRectangle) {
        return this.noEmpty(styledRectangle.x, styledRectangle.width, this.x, this.width) && this.noEmpty(styledRectangle.y, styledRectangle.height, this.y, this.height);
    }
    
    public void add(final StyledRectangle styledRectangle) {
        if (styledRectangle.x < this.x) {
            this.width += this.x - styledRectangle.x;
            this.x = styledRectangle.x;
        }
        if (styledRectangle.y < this.y) {
            this.height += this.y - styledRectangle.y;
            this.y = styledRectangle.y;
        }
        if (styledRectangle.width > this.width) {
            this.width = styledRectangle.width;
        }
        if (styledRectangle.height > this.height) {
            this.height = styledRectangle.height;
        }
    }
    
    public String toString() {
        return "StyledRectangle : " + this.x + "," + this.y + "," + this.width + "," + this.height;
    }
}
