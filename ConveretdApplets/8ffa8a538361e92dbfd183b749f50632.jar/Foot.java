// 
// Decompiled by Procyon v0.5.30
// 

public class Foot extends PointElement
{
    PointElement A;
    PointElement B;
    PointElement C;
    
    Foot(final PointElement a, final PointElement b, final PointElement c) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        this.C = c;
        if (this.B.AP == this.C.AP) {
            super.AP = this.B.AP;
        }
    }
    
    protected void update() {
        this.to(this.A).toLine(this.B, this.C, false);
    }
}
