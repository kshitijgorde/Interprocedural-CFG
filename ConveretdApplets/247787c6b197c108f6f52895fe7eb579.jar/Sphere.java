// 
// Decompiled by Procyon v0.5.30
// 

public class Sphere extends Object3D
{
    private static int filterHeight;
    private static int filterWidth;
    private static final int dataNum = 11664;
    static double density;
    static double radius;
    private byte[] data;
    byte[] neededData;
    byte[] A0data;
    byte[] A45data;
    byte[] A90data;
    byte[] A135data;
    byte[] tmpData;
    int[] offsets;
    static int idensity;
    static int iradius;
    static int D;
    int faceWidth;
    int faceHeight;
    int dataLen;
    Matrix2D xRotationMatrix;
    Matrix2D yRotationMatrix;
    Matrix2D zRotationMatrix;
    double xTheta;
    double yTheta;
    double zTheta;
    private static final int[] ballDraws;
    
    public Sphere(final double radius, final double density) {
        this.xRotationMatrix = new Matrix2D(3, 3);
        this.yRotationMatrix = new Matrix2D(3, 3);
        this.zRotationMatrix = new Matrix2D(3, 3);
        Sphere.radius = radius;
        Sphere.density = density;
        Sphere.idensity = (int)density;
        Sphere.iradius = (int)radius;
        Sphere.D = 2 * Sphere.iradius;
        this.dataLen = (Sphere.idensity - 2 * Sphere.filterHeight) * 2 * (Sphere.idensity - 2 * Sphere.filterWidth);
        this.data = new byte[this.dataLen];
        this.faceWidth = 256 / (Sphere.idensity * 2);
        this.faceHeight = 128 / Sphere.idensity;
        this.init();
    }
    
    public void init() {
        this.tmpData = new byte[24000];
        this.A0data = new byte[11664];
        this.offsets = new int[38];
        this.A45data = new byte[11664];
        this.A90data = new byte[11664];
        this.A135data = new byte[11664];
        this.genVertex();
        this.xTheta = 6.283185307179586 / 36.0;
        this.zTheta = 0.0;
        this.yTheta = 0.0;
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
    }
    
    private void genVertex() {
        final double radius = Sphere.radius;
        super.numVertices = 0;
        for (double n = Sphere.filterHeight * 3.141592653589793 / Sphere.density; n <= 3.141592653589793 - Sphere.filterHeight * 3.141592653589793 / Sphere.density; n += 3.141592653589793 / Sphere.density) {
            for (double n2 = Sphere.filterWidth * 3.141592653589793 / Sphere.density; n2 < 3.141592653589793 - Sphere.filterWidth * 3.141592653589793 / Sphere.density; n2 += 3.141592653589793 / Sphere.density) {
                this.addVertex(radius * Math.sin(n) * Math.cos(n2), radius * Math.sin(n) * Math.sin(n2), radius * Math.cos(n));
            }
            final double n3 = -n;
            for (double n4 = Sphere.filterWidth * 3.141592653589793 / Sphere.density; n4 < 3.141592653589793 - Sphere.filterWidth * 3.141592653589793 / Sphere.density; n4 += 3.141592653589793 / Sphere.density) {
                this.addVertex(radius * Math.sin(n3) * Math.cos(n4), radius * Math.sin(n3) * Math.sin(n4), radius * Math.cos(n3));
            }
        }
    }
    
    private void genData(final byte[] array, final int n, final int n2) {
        switch (Sphere.ballDraws[n * 4 + n2 - 1]) {
            case 1: {
                this.makeData(array, n, 1);
                break;
            }
            case 2: {
                this.makeData(array, n, 2);
                break;
            }
            case 3: {
                this.makeData(array, n, 3);
                break;
            }
            case 4: {
                this.makeData(array, n, 4);
                break;
            }
        }
    }
    
    public void produceData(final byte[] neededData, final int n) {
        this.neededData = neededData;
        if (n < 8) {
            Sphere.filterWidth = 8;
            Sphere.filterHeight = 8;
        }
        else {
            Sphere.filterWidth = 0;
            Sphere.filterHeight = 6;
        }
        this.genVertex();
        this.xTheta = 0.17453292519943295;
        this.yTheta = 0.0;
        this.zTheta = 0.0;
        for (int i = 0; i < 11664; ++i) {
            if (n < 8) {
                this.A0data[i] = 2;
            }
            else {
                this.A0data[i] = 1;
            }
        }
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
        this.genData(this.A0data, n, 1);
        this.genVertex();
        this.xTheta = 1.5707963267948966;
        this.zTheta = 0.0;
        this.yTheta = 0.0;
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
        this.rotate();
        this.xTheta = 0.0;
        this.zTheta = 0.0;
        this.yTheta = 0.17453292519943295;
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
        for (int j = 0; j < 11664; ++j) {
            if (n < 8) {
                this.A90data[j] = 2;
            }
            else {
                this.A90data[j] = 1;
            }
        }
        this.genData(this.A90data, n, 2);
        this.genVertex();
        this.xTheta = 0.17453292519943295;
        this.yTheta = 0.17453292519943295;
        this.zTheta = 0.0;
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
        for (int k = 0; k < 11664; ++k) {
            if (n < 8) {
                this.A45data[k] = 2;
            }
            else {
                this.A45data[k] = 1;
            }
        }
        this.genData(this.A45data, n, 3);
        this.genVertex();
        this.xTheta = -0.17453292519943295;
        this.yTheta = 0.17453292519943295;
        this.zTheta = 0.0;
        this.setXRotation(this.xTheta);
        this.setYRotation(this.yTheta);
        this.setZRotation(this.zTheta);
        for (int l = 0; l < 11664; ++l) {
            if (n < 8) {
                this.A135data[l] = 2;
            }
            else {
                this.A135data[l] = 1;
            }
        }
        this.genData(this.A135data, n, 4);
    }
    
    private void makeData(final byte[] array, final int n, final int n2) {
        this.offsets[0] = 0;
        for (int i = 0; i <= 36; ++i) {
            final int n3 = this.offsets[i] * 3;
            int n4 = 0;
            for (int j = 0; j < super.numVertices; ++j) {
                if (super.vertices[j].z >= 0.0) {
                    final int n5 = (int)(super.vertices[j].x + 0.5);
                    final int n6 = (int)super.vertices[j].y;
                    final int n7 = n5 + (Sphere.iradius - 1);
                    final int n8 = n6 + Sphere.iradius;
                    int k;
                    for (k = 0; k < n4; ++k) {
                        if (this.tmpData[n3 + k * 3] == n7 && this.tmpData[n3 + k * 3 + 1] == n8) {
                            switch (n2) {
                                case 1: {
                                    if (this.neededData[j] == 0) {
                                        if (this.tmpData[n3 + k * 3 + 2] != 0) {
                                            this.tmpData[n3 + k * 3 + 2] = 0;
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        if (this.neededData[j] == 1 && this.tmpData[n3 + k * 3 + 2] != 0) {
                                            this.tmpData[n3 + k * 3 + 2] = 1;
                                            break;
                                        }
                                        break;
                                    }
                                    break;
                                }
                                case 2: {
                                    if (this.neededData[j] != 0) {
                                        this.tmpData[n3 + k * 3 + 2] = this.neededData[j];
                                        break;
                                    }
                                    break;
                                }
                                case 4: {
                                    this.tmpData[n3 + k * 3 + 2] = this.neededData[j];
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    if (k == n4) {
                        this.tmpData[n3 + k * 3] = (byte)n7;
                        this.tmpData[n3 + k * 3 + 1] = (byte)n8;
                        this.tmpData[n3 + k * 3 + 2] = this.neededData[j];
                        ++n4;
                    }
                }
            }
            this.offsets[i + 1] = this.offsets[i] + n4;
            this.rotate();
        }
        for (int l = 0; l < 36; ++l) {
            final int n9 = l * 18 * 18;
            for (int n10 = this.offsets[l]; n10 < this.offsets[l + 1]; ++n10) {
                final byte b = (byte)(this.tmpData[n10 * 3 + 1] * 18 + this.tmpData[n10 * 3]);
                if (b < 324) {
                    array[b + n9] = this.tmpData[n10 * 3 + 2];
                }
            }
            final int[] array2 = { 3, 2, 14, 2, 2, 3, 15, 3, 2, 15, 15, 15, 6, 12, 7, 6, 9, 12, 9, 10, 7, 12, 6, 6 };
            if (n >= 8) {
                for (int n11 = 0; n11 < 12; ++n11) {
                    final int n12 = array2[n11 * 2 + 1] * 18 + array2[n11 * 2] + n9;
                    if (array[n12] == 1) {
                        if (array[n12 + 1] != 1) {
                            array[n12] = array[n12 + 1];
                        }
                        else if (array[n12 - 1] != 1) {
                            array[n12] = array[n12 - 1];
                        }
                    }
                }
            }
        }
    }
    
    public void setXRotation(final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        this.xRotationMatrix.setElement(0, 0, 1.0);
        this.xRotationMatrix.setElement(0, 1, 0.0);
        this.xRotationMatrix.setElement(0, 2, 0.0);
        this.xRotationMatrix.setElement(1, 0, 0.0);
        this.xRotationMatrix.setElement(1, 1, cos);
        this.xRotationMatrix.setElement(1, 2, sin);
        this.xRotationMatrix.setElement(2, 0, 0.0);
        this.xRotationMatrix.setElement(2, 1, -sin);
        this.xRotationMatrix.setElement(2, 2, cos);
    }
    
    public void setYRotation(final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        this.yRotationMatrix.setElement(0, 0, cos);
        this.yRotationMatrix.setElement(0, 1, 0.0);
        this.yRotationMatrix.setElement(0, 2, -sin);
        this.yRotationMatrix.setElement(1, 0, 0.0);
        this.yRotationMatrix.setElement(1, 1, 1.0);
        this.yRotationMatrix.setElement(1, 2, 0.0);
        this.yRotationMatrix.setElement(2, 0, sin);
        this.yRotationMatrix.setElement(2, 1, 0.0);
        this.yRotationMatrix.setElement(2, 2, cos);
    }
    
    public void setZRotation(final double n) {
        final double cos = Math.cos(n);
        final double sin = Math.sin(n);
        this.zRotationMatrix.setElement(0, 0, cos);
        this.zRotationMatrix.setElement(0, 1, sin);
        this.zRotationMatrix.setElement(0, 2, 0.0);
        this.zRotationMatrix.setElement(1, 0, -sin);
        this.zRotationMatrix.setElement(1, 1, cos);
        this.zRotationMatrix.setElement(1, 2, 0.0);
        this.zRotationMatrix.setElement(2, 0, 0.0);
        this.zRotationMatrix.setElement(2, 1, 0.0);
        this.zRotationMatrix.setElement(2, 2, 1.0);
    }
    
    public void rotateX() {
        for (int i = 0; i < super.numVertices; ++i) {
            super.vertices[i].matrixMultiply(this.xRotationMatrix);
        }
    }
    
    public void rotateY() {
        for (int i = 0; i < super.numVertices; ++i) {
            super.vertices[i].matrixMultiply(this.yRotationMatrix);
        }
    }
    
    public void rotateZ() {
        for (int i = 0; i < super.numVertices; ++i) {
            super.vertices[i].matrixMultiply(this.zRotationMatrix);
        }
    }
    
    public void rotate() {
        for (int i = 0; i < super.numVertices; ++i) {
            super.vertices[i].matrixMultiply(this.xRotationMatrix);
            super.vertices[i].matrixMultiply(this.yRotationMatrix);
            super.vertices[i].matrixMultiply(this.zRotationMatrix);
        }
    }
    
    static {
        Sphere.filterHeight = 8;
        Sphere.filterWidth = 8;
        ballDraws = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
    }
}
