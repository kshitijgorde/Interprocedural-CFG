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
    int cmX;
    int cmY;
    int cmZ;
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
    int xDeg3;
    int yDeg;
    int yDeg2;
    int yDeg3;
    int zDeg;
    int zDeg2;
    int zDeg3;
    int tx;
    int ty;
    double length;
    point3d normal;
    
    public point3d(final int n, final int n2, final int n3) {
        this.setTo(n, n2, n3);
    }
    
    public void calcLength() {
        this.length = Math.sqrt(this.rotoX * this.rotoX + this.rotoY * this.rotoY + this.rotoZ * this.rotoZ);
    }
    
    public void setTexCord(final int tx, final int ty) {
        this.tx = tx;
        this.ty = ty;
    }
    
    public void setTo(final int rotoX, final int rotoY, final int rotoZ) {
        this.x3d = rotoX;
        this.y3d = rotoY;
        this.z3d = rotoZ;
        this.localX = rotoX;
        this.localY = rotoY;
        this.localZ = rotoZ;
        this.srcX = rotoX;
        this.srcY = rotoY;
        this.srcZ = rotoZ;
        this.cmX = rotoX;
        this.cmY = rotoY;
        this.cmZ = rotoZ;
        this.rotoX = rotoX;
        this.rotoY = rotoY;
        this.rotoZ = rotoZ;
        this.oX = 0;
        this.oY = 0;
        this.oZ = 0;
        final boolean b = false;
        this.xDeg2 = (b ? 1 : 0);
        this.xDeg = (b ? 1 : 0);
        final boolean b2 = false;
        this.yDeg2 = (b2 ? 1 : 0);
        this.yDeg = (b2 ? 1 : 0);
        final boolean b3 = false;
        this.zDeg2 = (b3 ? 1 : 0);
        this.zDeg = (b3 ? 1 : 0);
        this.zeroNormal();
        this.nCount = 0;
        this.xform2d();
    }
    
    public void xform2d() {
        if (this.z3d > 0) {
            this.x2d = 119 + (this.x3d << 10) / this.z3d;
            this.y2d = 99 - (this.y3d << 10) / this.z3d;
            return;
        }
        if (this.z3d == 0) {
            this.x2d = 119 + (this.x3d << 10);
            this.y2d = 99 - (this.y3d << 10);
            return;
        }
        this.x2d = 119 - (this.x3d << 10) / this.z3d;
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
        this.xDeg3 += n;
        this.yDeg3 += n2;
        this.zDeg3 += n3;
        this.xDeg3 %= 360;
        this.yDeg3 %= 360;
        this.zDeg3 %= 360;
        final int zdCos = sinCos.zDCos(this.xDeg3);
        final int zdSin = sinCos.zDSin(this.xDeg3);
        final int zdCos2 = sinCos.zDCos(this.yDeg3);
        final int zdSin2 = sinCos.zDSin(this.yDeg3);
        final int zdCos3 = sinCos.zDCos(this.zDeg3);
        final int zdSin3 = sinCos.zDSin(this.zDeg3);
        this.i = (this.srcX - point3d.x3d) * zdCos3 - (this.srcY - point3d.y3d) * zdSin3 >> 8;
        this.j = (this.srcX - point3d.x3d) * zdSin3 + (this.srcY - point3d.y3d) * zdCos3 >> 8;
        this.k = this.srcZ - point3d.z3d;
        this.cmY = this.j * zdCos - this.k * zdSin >> 8;
        this.cmZ = this.j * zdSin + this.k * zdCos >> 8;
        this.k = this.cmZ;
        this.cmX = this.k * zdSin2 + this.i * zdCos2 >> 8;
        this.cmZ = this.k * zdCos2 - this.i * zdSin2 >> 8;
        this.cmX += point3d.x3d;
        this.cmY += point3d.y3d;
        this.cmZ += point3d.z3d;
    }
    
    public void CamRotate(final int n, final int n2, final int n3, final point3d point3d, final SinCos sinCos) {
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
        this.i = (this.cmX - point3d.x3d) * zdCos3 - (this.cmY - point3d.y3d) * zdSin3 >> 8;
        this.j = (this.cmX - point3d.x3d) * zdSin3 + (this.cmY - point3d.y3d) * zdCos3 >> 8;
        this.k = this.cmZ - point3d.z3d;
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
    
    public void translate(final int n, final int n2, final int n3) {
        this.oX += n;
        this.oY += n2;
        this.oZ += n3;
        this.globalXform();
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
    
    public void ResetTurn2() {
        this.xDeg3 = 0;
        this.yDeg3 = 0;
        this.zDeg3 = 0;
    }
}
