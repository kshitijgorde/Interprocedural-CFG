// 
// Decompiled by Procyon v0.5.30
// 

class obj3d
{
    point3d[] point;
    point3d[] normal;
    polygon[] poly;
    point3d origin;
    int numPoints;
    int numNormals;
    int numPolys;
    int[] trig;
    int xDeg;
    int yDeg;
    int zDeg;
    
    public obj3d(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.origin = new point3d(0, 0, 0);
        this.trig = new int[6];
        this.point = new point3d[n4];
        this.normal = new point3d[n5 * 3];
        this.poly = new polygon[n5];
        this.origin = new point3d(n, n2, n3);
        this.numPoints = 0;
        this.numNormals = 0;
        this.numPolys = 0;
        this.xDeg = 0;
        this.yDeg = 0;
        this.zDeg = 0;
    }
    
    public void addLocalPoint(final int n, final int n2, final int n3) {
        ++this.numPoints;
        (this.point[this.numPoints - 1] = new point3d(n, n2, n3)).globalXform2origin(this.origin);
    }
    
    public void addLocalPoint(final point3d point3d) {
        point3d.globalXform2origin(this.origin);
        ++this.numPoints;
        this.point[this.numPoints - 1] = point3d;
    }
    
    public void addLocalPoly(final polygon polygon) {
        ++this.numPolys;
        this.poly[this.numPolys - 1] = polygon;
        this.addNormal(polygon.normal);
    }
    
    public void addLocalPoly(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.addLocalPoly(new polygon(this.point[n], this.point[n2], this.point[n3], n4, n5, n6));
    }
    
    public void AroundpRotate(final int n, final int n2, final int n3, final point3d point3d, final SinCos sinCos) {
        for (int i = 0; i < this.numPoints; ++i) {
            this.point[i].AroundpRotate(n, n2, n3, point3d, sinCos);
            this.point[i].xform2d();
        }
        for (int j = 0; j < this.numNormals; ++j) {
            this.normal[j].AroundpRotate(n, n2, n3, point3d, sinCos);
        }
    }
    
    public void ResetTurn2() {
        for (int i = 0; i < this.numPoints; ++i) {
            this.point[i].ResetTurn2();
        }
    }
    
    public void localRotate(final int n, final int n2, final int n3, final SinCos sinCos) {
        this.xDeg += n;
        this.yDeg += n2;
        this.zDeg += n3;
        this.xDeg %= 360;
        this.yDeg %= 360;
        this.zDeg %= 360;
        this.trig[0] = sinCos.zDSin(this.xDeg);
        this.trig[1] = sinCos.zDCos(this.xDeg);
        this.trig[2] = sinCos.zDSin(this.yDeg);
        this.trig[3] = sinCos.zDCos(this.yDeg);
        this.trig[4] = sinCos.zDSin(this.zDeg);
        this.trig[5] = sinCos.zDCos(this.zDeg);
        for (int i = 0; i < this.numPoints; ++i) {
            this.point[i].localRotate(this.trig, sinCos);
            this.point[i].xform2d();
        }
        for (int j = 0; j < this.numNormals; ++j) {
            this.normal[j].localRotate(this.trig, sinCos);
        }
    }
    
    public void translate(final int n, final int n2, final int n3) {
        this.origin.translate(n, n2, n3);
        for (int i = 0; i < this.numPoints; ++i) {
            this.point[i].translate(n, n2, n3);
            this.point[i].xform2d();
        }
        for (int j = 0; j < this.numNormals; ++j) {
            this.normal[j].translate(n, n2, n3);
        }
    }
    
    public void setGNormals() {
        for (int i = 0; i < this.numPolys; ++i) {
            this.poly[i].setGNormals();
        }
        for (int j = 0; j < this.numPoints; ++j) {
            this.point[j].avgNormal();
        }
        for (int numPoints = this.numPoints, k = 0; k < numPoints; ++k) {
            if (this.point[k].nCount > 0) {
                this.addNormal(this.point[k].nX, this.point[k].nY, this.point[k].nZ);
                this.point[k].normal = this.normal[this.numNormals - 1];
            }
        }
    }
    
    public void addNormal(final int n, final int n2, final int n3) {
        ++this.numNormals;
        (this.normal[this.numNormals - 1] = new point3d(n, n2, n3)).globalXform2origin(this.origin);
    }
    
    public void addNormal(final point3d point3d) {
        point3d.globalXform2origin(this.origin);
        ++this.numNormals;
        this.normal[this.numNormals - 1] = point3d;
    }
}
