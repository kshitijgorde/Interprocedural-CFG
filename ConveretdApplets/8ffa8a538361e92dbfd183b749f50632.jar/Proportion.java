// 
// Decompiled by Procyon v0.5.30
// 

public class Proportion extends PointElement
{
    PointElement S0;
    PointElement S1;
    PointElement T0;
    PointElement T1;
    PointElement U0;
    PointElement U1;
    PointElement V0;
    PointElement V1;
    
    Proportion(final PointElement s0, final PointElement s2, final PointElement t0, final PointElement t2, final PointElement u0, final PointElement u2, final PointElement v0, final PointElement v2) {
        super.dimension = 0;
        this.S0 = s0;
        this.S1 = s2;
        this.T0 = t0;
        this.T1 = t2;
        this.U0 = u0;
        this.U1 = u2;
        this.V0 = v0;
        this.V1 = v2;
        if (this.V0.AP == this.V1.AP) {
            super.AP = this.V0.AP;
        }
    }
    
    protected void update() {
        final double sqrt = Math.sqrt(this.T0.distance2(this.T1) * this.U0.distance2(this.U1) / (this.S0.distance2(this.S1) * this.V0.distance2(this.V1)));
        this.to(this.V1).minus(this.V0);
        this.times(sqrt).plus(this.V0);
    }
}
