// 
// Decompiled by Procyon v0.5.30
// 

class polygon
{
    point3d p1;
    point3d p2;
    point3d p3;
    point3d normal;
    int color;
    int facing;
    int shading;
    int count;
    int dot;
    int dot1;
    int dot2;
    int dot3;
    int red;
    int green;
    int blue;
    
    public polygon() {
    }
    
    public polygon(final point3d point3d, final point3d point3d2, final point3d point3d3, final int n, final int n2, final int n3) {
        this.setColor(n, n2, n3);
        this.normal = new point3d(0, 0, 0);
        this.setTo(point3d, point3d2, point3d3);
    }
    
    public polygon(final point3d p5, final point3d p6, final point3d p7, final point3d normal, final int n) {
        this.p1 = p5;
        this.p2 = p6;
        this.p3 = p7;
        this.normal = normal;
    }
    
    public void setTo(final point3d p3, final point3d p4, final point3d p5) {
        this.p1 = p3;
        this.p2 = p4;
        this.p3 = p5;
        final float n = (this.p2.localY - this.p1.localY) * (this.p3.localZ - this.p1.localZ) - (this.p2.localZ - this.p1.localZ) * (this.p3.localY - this.p1.localY);
        final float n2 = (this.p2.localZ - this.p1.localZ) * (this.p3.localX - this.p1.localX) - (this.p2.localX - this.p1.localX) * (this.p3.localZ - this.p1.localZ);
        final float n3 = (this.p2.localX - this.p1.localX) * (this.p3.localY - this.p1.localY) - (this.p2.localY - this.p1.localY) * (this.p3.localX - this.p1.localX);
        final float n4 = (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
        this.normal.setTo((int)(n / n4 * 256.0f), (int)(n2 / n4 * 256.0f), (int)(n3 / n4 * 256.0f));
        this.normal.copyOrigin(this.p1);
    }
    
    public void localRotate(final int n, final int n2, final int n3, final SinCos sinCos) {
        this.p1.localRotate(n, n2, n3, sinCos);
        this.p2.localRotate(n, n2, n3, sinCos);
        this.p3.localRotate(n, n2, n3, sinCos);
        this.p1.xform2d();
        this.p2.xform2d();
        this.p3.xform2d();
    }
    
    public int avgZ() {
        return (this.p1.z3d + this.p2.z3d + this.p3.z3d) / 3;
    }
    
    public void setColor(final int red, final int green, final int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public void setNewOrigin(final point3d point3d) {
        this.p1.setNewOrigin(point3d);
        this.p2.setNewOrigin(point3d);
        this.p3.setNewOrigin(point3d);
        this.normal.setNewOrigin(point3d);
    }
    
    public void setGNormals() {
        this.p1.addNormal(this.normal.rotoX, this.normal.rotoY, this.normal.rotoZ);
        this.p2.addNormal(this.normal.rotoX, this.normal.rotoY, this.normal.rotoZ);
        this.p3.addNormal(this.normal.rotoX, this.normal.rotoY, this.normal.rotoZ);
    }
}
