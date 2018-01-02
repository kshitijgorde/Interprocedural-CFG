// 
// Decompiled by Procyon v0.5.30
// 

public class Midpoint extends PointElement
{
    PointElement A;
    PointElement B;
    
    Midpoint(final PointElement a, final PointElement b) {
        super.dimension = 0;
        this.A = a;
        this.B = b;
        if (this.A.AP == this.B.AP) {
            super.AP = this.A.AP;
        }
    }
    
    protected void update() {
        super.x = (this.A.x + this.B.x) / 2.0;
        super.y = (this.A.y + this.B.y) / 2.0;
        super.z = (this.A.z + this.B.z) / 2.0;
    }
}
