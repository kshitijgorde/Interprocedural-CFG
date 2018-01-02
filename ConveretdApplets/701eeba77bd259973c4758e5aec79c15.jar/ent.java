// 
// Decompiled by Procyon v0.5.30
// 

class ent
{
    public int x;
    public int y;
    public int z;
    public int i;
    public boolean moves;
    public boolean dirL;
    public boolean dirR;
    public boolean dirU;
    public boolean dirD;
    public int freeze;
    public int moveSpeed;
    public boolean flip;
    
    public ent(final int x, final int y, final int n) {
        this.x = x;
        this.y = y;
        this.z = n;
        this.i = n;
        this.moves = false;
        this.dirL = false;
        this.dirR = false;
        this.dirU = false;
        this.dirD = false;
        this.freeze = 0;
        this.moveSpeed = 0;
        this.flip = true;
    }
}
