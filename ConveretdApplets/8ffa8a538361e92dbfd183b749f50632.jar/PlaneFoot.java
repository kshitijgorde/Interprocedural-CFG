// 
// Decompiled by Procyon v0.5.30
// 

public class PlaneFoot extends PointElement
{
    PointElement A;
    PlaneElement P;
    
    PlaneFoot(final PointElement a, final PlaneElement ap) {
        super.dimension = 0;
        this.A = a;
        super.AP = ap;
    }
    
    protected void update() {
        this.to(this.A).toPlane(super.AP);
    }
}
