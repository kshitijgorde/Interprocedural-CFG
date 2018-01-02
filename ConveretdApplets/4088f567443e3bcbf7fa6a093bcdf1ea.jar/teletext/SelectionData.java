// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

public class SelectionData
{
    boolean active;
    boolean changed;
    int x1;
    int y1;
    int x2;
    int y2;
    
    public void activate() {
        this.active = true;
    }
    
    public void deactivate() {
        this.active = false;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public boolean isChanged() {
        final boolean changed = this.changed;
        this.changed = false;
        return changed;
    }
    
    public void setX1(final int x1) {
        if (this.x1 != x1) {
            this.changed = true;
            this.x1 = x1;
        }
    }
    
    public int getX1() {
        return this.x1;
    }
    
    public void setY1(final int y1) {
        if (this.y1 != y1) {
            this.changed = true;
            this.y1 = y1;
        }
    }
    
    public int getY1() {
        return this.y1;
    }
    
    public void setX2(final int x2) {
        if (this.x2 != x2) {
            this.changed = true;
            this.x2 = x2;
        }
    }
    
    public int getX2() {
        return this.x2;
    }
    
    public void setY2(final int y2) {
        if (this.y2 != y2) {
            this.changed = true;
            this.y2 = y2;
        }
    }
    
    public int getY2() {
        return this.y2;
    }
    
    public int getWidth() {
        return this.x2 - this.x1 + 1;
    }
    
    public int getHeight() {
        return this.y2 - this.y1 + 1;
    }
    
    public void setBounds(final int x1, final int y1, final int x2, final int y2) {
        this.setX1(x1);
        this.setY1(y1);
        this.setX2(x2);
        this.setY2(y2);
    }
    
    public void moveLeft() {
        if (this.x1 > 0) {
            this.changed = true;
            --this.x1;
            --this.x2;
        }
    }
    
    public void moveRight() {
        this.changed = true;
        ++this.x1;
        ++this.x2;
    }
    
    public void moveUp() {
        if (this.y1 > 0) {
            this.changed = true;
            --this.y1;
            --this.y2;
        }
    }
    
    public void moveDown() {
        this.changed = true;
        ++this.y1;
        ++this.y2;
    }
    
    public void inflate(final int n) {
        this.setBounds(this.x1 - n, this.y1 - n, this.x2 + n, this.y2 + n);
    }
    
    public void inflate() {
        this.inflate(1);
    }
    
    public void deflate(final int n) {
        this.setBounds(this.x1 + n, this.y1 + n, this.x2 - n, this.y2 - n);
    }
    
    public void deflate() {
        this.deflate(1);
    }
    
    public String toString() {
        return "<SelectionData: " + this.x1 + " " + this.y1 + " " + this.x2 + " " + this.y2 + ">";
    }
}
