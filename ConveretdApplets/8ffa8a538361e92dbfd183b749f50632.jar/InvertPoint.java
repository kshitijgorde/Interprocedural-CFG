// 
// Decompiled by Procyon v0.5.30
// 

public class InvertPoint extends PointElement
{
    PointElement A;
    CircleElement C;
    
    InvertPoint(final PointElement a, final CircleElement c) {
        super.dimension = 0;
        this.A = a;
        this.C = c;
        super.AP = this.C.AP;
    }
    
    protected void update() {
        this.toInvertPoint(this.A, this.C);
    }
}
