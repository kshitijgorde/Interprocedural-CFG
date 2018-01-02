// 
// Decompiled by Procyon v0.5.30
// 

public class CircleSlider extends PointElement
{
    CircleElement C;
    double initx;
    double inity;
    double initz;
    PointElement newP;
    
    CircleSlider(final CircleElement c, final double n, final double n2, final double n3) {
        this.newP = new PointElement();
        super.dimension = 0;
        super.dragable = true;
        this.C = c;
        this.initx = n;
        super.x = n;
        this.inity = n2;
        super.y = n2;
        this.initz = n3;
        super.z = n3;
    }
    
    protected void reset() {
        super.x = this.initx;
        super.y = this.inity;
        super.z = this.initz;
        this.toCircle(this.C);
    }
    
    protected void update() {
        this.toCircle(this.C);
    }
    
    protected boolean drag(final double x, final double y) {
        super.x = x;
        super.y = y;
        if (!this.defined()) {
            super.z = this.initz;
        }
        this.newP.to(this).uptoPlane(this.C.AP);
        if (!this.newP.defined()) {
            this.newP.to(this).toPlane(this.C.AP);
        }
        this.newP.toSphere(this.C.Center, this.C.radius());
        if ((this.newP.x - super.x) * (this.newP.x - super.x) + (this.newP.y - super.y) * (this.newP.y - super.y) < 0.5) {
            return false;
        }
        this.to(this.newP);
        return true;
    }
}
