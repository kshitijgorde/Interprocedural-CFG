// 
// Decompiled by Procyon v0.5.30
// 

public class AnimFramePoint
{
    public int x;
    public int y;
    public int z;
    public int color;
    private int d;
    
    AnimFramePoint(final int x, final int y, final int z, final int color) {
        this.d = 300;
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }
    
    public int pointX() {
        if (this.z + this.d == 0) {
            return this.x * this.d;
        }
        return (int)Math.rint(this.x * this.d / (this.z + this.d));
    }
    
    public int pointY() {
        if (this.z + this.d == 0) {
            return this.y * this.d;
        }
        return (int)Math.rint(this.y * this.d / (this.z + this.d));
    }
}
