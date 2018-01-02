// 
// Decompiled by Procyon v0.5.30
// 

public class MeanProportional extends PointElement
{
    PointElement S0;
    PointElement S1;
    PointElement T0;
    PointElement T1;
    PointElement U0;
    PointElement U1;
    
    MeanProportional(final PointElement s0, final PointElement s2, final PointElement t0, final PointElement t2, final PointElement u0, final PointElement u2) {
        super.dimension = 0;
        this.S0 = s0;
        this.S1 = s2;
        this.T0 = t0;
        this.T1 = t2;
        this.U0 = u0;
        this.U1 = u2;
        if (this.U0.AP == this.U1.AP) {
            super.AP = this.U0.AP;
        }
    }
    
    protected void update() {
        final double sqrt = Math.sqrt(Math.sqrt(this.S0.distance2(this.S1) * this.T0.distance2(this.T1)) / this.U0.distance2(this.U1));
        this.to(this.U1).minus(this.U0);
        this.times(sqrt).plus(this.U0);
    }
}
