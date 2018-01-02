// 
// Decompiled by Procyon v0.5.30
// 

public class PlaneSlider extends PointElement
{
    double initx;
    double inity;
    double initz;
    PointElement newP;
    
    PlaneSlider(final PlaneElement ap, final double n, final double n2, final double n3) {
        this.newP = new PointElement();
        super.dimension = 0;
        super.dragable = true;
        super.AP = ap;
        this.initx = n;
        super.x = n;
        this.inity = n2;
        super.y = n2;
        this.initz = n3;
        super.z = n3;
    }
    
    protected void update() {
        this.toPlane(super.AP);
    }
    
    protected void reset() {
        super.x = this.initx;
        super.y = this.inity;
        super.z = this.initz;
        this.toPlane(super.AP);
    }
    
    protected boolean drag(final double x, final double y) {
        super.x = x;
        super.y = y;
        if (!this.defined()) {
            super.z = this.initz;
        }
        this.newP.to(this).uptoPlane(super.AP);
        if (this.newP.defined()) {
            this.to(this.newP);
        }
        else {
            this.toPlane(super.AP);
        }
        return true;
    }
}
