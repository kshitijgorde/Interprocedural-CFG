// 
// Decompiled by Procyon v0.5.30
// 

class point3d
{
    int x3d;
    int y3d;
    int z3d;
    int localX;
    int localY;
    int localZ;
    int srcX;
    int srcY;
    int srcZ;
    int rotoX;
    int rotoY;
    int rotoZ;
    int oX;
    int oY;
    int oZ;
    int x2d;
    int y2d;
    int nX;
    int nY;
    int nZ;
    int nCount;
    int i;
    int j;
    int k;
    int xDeg;
    int xDeg2;
    int yDeg;
    int yDeg2;
    int zDeg;
    int zDeg2;
    int tx;
    int ty;
    double length;
    point3d normal;
    
    public point3d(final int n, final int n2, final int n3) {
        this.setTo(n, n2, n3);
    }
    
    public void setTexCords(final int tx, final int ty) {
        this.tx = tx;
        this.ty = ty;
    }
    
    public void calcLength() {
        this.length = Math.sqrt(this.rotoX * this.rotoX + this.rotoY * this.rotoY + this.rotoZ * this.rotoZ);
    }
    
    public void setTo(final int n, final int n2, final int n3) {
        this.x3d = n;
        this.y3d = n2;
        this.z3d = n3;
        final boolean b = false;
        this.ty = (b ? 1 : 0);
        this.tx = (b ? 1 : 0);
        this.localX = n;
        this.localY = n2;
        this.localZ = n3;
        this.srcX = n;
        this.srcY = n2;
        this.srcZ = n3;
        this.rotoX = n;
        this.rotoY = n2;
        this.rotoZ = n3;
        this.oX = 0;
        this.oY = 0;
        this.oZ = 0;
        final boolean b2 = false;
        this.xDeg2 = (b2 ? 1 : 0);
        this.xDeg = (b2 ? 1 : 0);
        final boolean b3 = false;
        this.yDeg2 = (b3 ? 1 : 0);
        this.yDeg = (b3 ? 1 : 0);
        final boolean b4 = false;
        this.zDeg2 = (b4 ? 1 : 0);
        this.zDeg = (b4 ? 1 : 0);
        this.zeroNormal();
        this.nCount = 0;
        this.xform2d();
    }
    
    public void xform2d() {
        if (this.z3d > 0) {
            this.x2d = (this.x3d << 10) / this.z3d + 120;
            this.y2d = 99 - (this.y3d << 10) / this.z3d;
            return;
        }
        if (this.z3d == 0) {
            this.x2d = 120 + (this.x3d << 10);
            this.y2d = 99 - (this.y3d << 10);
            return;
        }
        this.x2d = 120 - (this.x3d << 10) / this.z3d;
        this.y2d = 99 + (this.y3d << 10) / this.z3d;
    }
    
    public void setNewOrigin(final point3d point3d) {
        this.oX = point3d.x3d;
        this.oY = point3d.y3d;
        this.oZ = point3d.z3d;
        this.localX = this.x3d - this.oX;
        this.localY = this.y3d - this.oY;
        this.localZ = this.z3d - this.oZ;
        this.rotoX = this.localX;
        this.rotoY = this.localY;
        this.rotoZ = this.localZ;
    }
    
    public void globalXform2origin(final point3d point3d) {
        this.oX = point3d.x3d;
        this.oY = point3d.y3d;
        this.oZ = point3d.z3d;
        this.globalXform();
        this.xform2d();
    }
    
    public void copyOrigin(final point3d point3d) {
        this.oX = point3d.oX;
        this.oY = point3d.oY;
        this.oZ = point3d.oZ;
        this.globalXform();
        this.xform2d();
    }
    
    public void localRotate(final int n, final int n2) {
    }
    
    public void localRotate(final int n, final int n2, final int n3, final SinCos sinCos) {
        this.xDeg += n;
        this.yDeg += n2;
        this.zDeg += n3;
        this.xDeg %= 360;
        this.yDeg %= 360;
        this.zDeg %= 360;
        final int zdCos = sinCos.zDCos(this.xDeg);
        final int zdSin = sinCos.zDSin(this.xDeg);
        final int zdCos2 = sinCos.zDCos(this.yDeg);
        final int zdSin2 = sinCos.zDSin(this.yDeg);
        final int zdCos3 = sinCos.zDCos(this.zDeg);
        final int zdSin3 = sinCos.zDSin(this.zDeg);
        this.i = this.localX * zdCos3 - this.localY * zdSin3 >> 8;
        this.j = this.localX * zdSin3 + this.localY * zdCos3 >> 8;
        this.k = this.localZ;
        this.rotoY = this.j * zdCos - this.k * zdSin >> 8;
        this.rotoZ = this.j * zdSin + this.k * zdCos >> 8;
        this.k = this.rotoZ;
        this.rotoX = this.k * zdSin2 + this.i * zdCos2 >> 8;
        this.rotoZ = this.k * zdCos2 - this.i * zdSin2 >> 8;
        this.globalXform();
    }
    
    public void AroundpRotate(final int n, final int n2, final int n3, final point3d point3d, final SinCos sinCos) {
        this.xDeg2 += n;
        this.yDeg2 += n2;
        this.zDeg2 += n3;
        this.xDeg2 %= 360;
        this.yDeg2 %= 360;
        this.zDeg2 %= 360;
        final int zdCos = sinCos.zDCos(this.xDeg2);
        final int zdSin = sinCos.zDSin(this.xDeg2);
        final int zdCos2 = sinCos.zDCos(this.yDeg2);
        final int zdSin2 = sinCos.zDSin(this.yDeg2);
        final int zdCos3 = sinCos.zDCos(this.zDeg2);
        final int zdSin3 = sinCos.zDSin(this.zDeg2);
        this.i = (this.srcX - point3d.x3d) * zdCos3 - (this.srcY - point3d.y3d) * zdSin3 >> 8;
        this.j = (this.srcX - point3d.x3d) * zdSin3 + (this.srcY - point3d.y3d) * zdCos3 >> 8;
        this.k = this.srcZ - point3d.z3d;
        this.localY = this.j * zdCos - this.k * zdSin >> 8;
        this.localZ = this.j * zdSin + this.k * zdCos >> 8;
        this.k = this.localZ;
        this.localX = this.k * zdSin2 + this.i * zdCos2 >> 8;
        this.localZ = this.k * zdCos2 - this.i * zdSin2 >> 8;
        this.localX += point3d.x3d;
        this.localY += point3d.y3d;
        this.localZ += point3d.z3d;
    }
    
    public void localRotate(final int[] array, final SinCos sinCos) {
        this.i = this.localX * array[5] - this.localY * array[4] >> 8;
        this.j = this.localX * array[4] + this.localY * array[5] >> 8;
        this.k = this.localZ;
        this.rotoY = this.j * array[1] - this.k * array[0] >> 8;
        this.rotoZ = this.j * array[0] + this.k * array[1] >> 8;
        this.k = this.rotoZ;
        this.rotoX = this.k * array[2] + this.i * array[3] >> 8;
        this.rotoZ = this.k * array[3] - this.i * array[2] >> 8;
        this.globalXform();
    }
    
    public void globalRotate(final int[] array, final SinCos sinCos) {
        this.i = this.x3d * array[5] - this.y3d * array[4] >> 8;
        this.j = this.x3d * array[4] + this.y3d * array[5] >> 8;
        this.k = this.z3d;
        this.y3d = this.j * array[1] - this.k * array[0] >> 8;
        this.z3d = this.j * array[0] + this.k * array[1] >> 8;
        this.k = this.z3d;
        this.x3d = this.k * array[2] + this.i * array[3] >> 8;
        this.z3d = this.k * array[3] - this.i * array[2] >> 8;
    }
    
    public void translate(final int n, final int n2, final int n3) {
        this.oX += n;
        this.oY += n2;
        this.oZ += n3;
        this.globalXform();
    }
    
    public void localtranslate(final int n, final int n2, final int n3) {
        this.srcX += n;
        this.srcY += n2;
        this.srcZ += n3;
    }
    
    public void globalXform() {
        this.x3d = this.rotoX + this.oX;
        this.y3d = this.rotoY + this.oY;
        this.z3d = this.rotoZ + this.oZ;
    }
    
    public void zeroNormal() {
        this.nX = 0;
        this.nY = 0;
        this.nZ = 0;
        this.nCount = 0;
    }
    
    public void addNormal(final int n, final int n2, final int n3) {
        this.nX += n;
        this.nY += n2;
        this.nZ += n3;
        ++this.nCount;
    }
    
    public void avgNormal() {
        if (this.nCount <= 0) {
            final boolean nx = false;
            this.nZ = (nx ? 1 : 0);
            this.nY = (nx ? 1 : 0);
            this.nX = (nx ? 1 : 0);
            return;
        }
        this.nX /= this.nCount;
        this.nY /= this.nCount;
        this.nZ /= this.nCount;
        final int n = (int)Math.sqrt(this.nX * this.nX + this.nY * this.nY + this.nZ * this.nZ);
        if (n > 0) {
            this.nX = (this.nX << 8) / n;
            this.nY = (this.nY << 8) / n;
            this.nZ = (this.nZ << 8) / n;
            return;
        }
        final boolean nx2 = false;
        this.nZ = (nx2 ? 1 : 0);
        this.nY = (nx2 ? 1 : 0);
        this.nX = (nx2 ? 1 : 0);
    }
    
    public void matRotate(final int[] array, final SinCos sinCos) {
        this.rotoX = this.localX * array[0] + this.localY * array[1] + this.localZ * array[2] >> 8;
        this.rotoY = this.localX * array[3] + this.localY * array[4] + this.localZ * array[5] >> 8;
        this.rotoZ = this.localX * array[6] + this.localY * array[7] + this.localZ * array[8] >> 8;
        this.globalXform();
    }
    
    public void ResetTurn2() {
        this.xDeg2 = 0;
        this.yDeg2 = 0;
        this.zDeg2 = 0;
    }
}
