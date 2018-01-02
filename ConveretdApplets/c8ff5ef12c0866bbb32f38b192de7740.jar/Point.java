// 
// Decompiled by Procyon v0.5.30
// 

class Point
{
    private int x;
    private int y;
    private int maxX;
    private int maxY;
    
    public void randomize() {
        this.x = (int)(this.maxX * Math.random());
        this.y = (int)(this.maxY * Math.random());
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void randomize(final int maxX, final int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.x = (int)(this.maxX * Math.random());
        this.y = (int)(this.maxY * Math.random());
    }
    
    public Point() {
        this.x = (int)(this.maxX * Math.random());
        this.y = (int)(this.maxY * Math.random());
    }
    
    public Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setMaxY(final int maxY) {
        this.maxY = maxY;
    }
    
    public void moveUp(final int n) {
        if (this.y < n) {
            this.y = this.maxY - (n - this.y);
            return;
        }
        this.y -= n;
    }
    
    public void setMax(final int maxX, final int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void moveLeft(final int n) {
        if (this.x < n) {
            this.x = this.maxX - (n - this.x);
            return;
        }
        this.x -= n;
    }
    
    public void moveRight(final int n) {
        this.x += n;
        this.x %= this.maxX;
    }
    
    public void setMaxX(final int maxX) {
        this.maxX = maxX;
    }
    
    public void moveDown(final int n) {
        this.y += n;
        this.y %= this.maxY;
    }
}
