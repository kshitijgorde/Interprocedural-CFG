// 
// Decompiled by Procyon v0.5.30
// 

class Position
{
    private int positionX;
    private int positionY;
    
    public Position(final int x, final int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    public int x() {
        return this.positionX;
    }
    
    public int y() {
        return this.positionY;
    }
}
