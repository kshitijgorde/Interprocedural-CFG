import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.media.j3d.TriangleFanArray;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;
import javax.media.j3d.QuadArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;
import javax.media.j3d.Transform3D;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.vecmath.Vector3d;
import javax.media.j3d.Appearance;
import javax.vecmath.Matrix3d;
import java.awt.Color;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Canvas3D;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Spiral3d extends JApplet implements Runnable
{
    Thread Z0007;
    Canvas3D Z0028;
    GraphicsContext3D Z0066;
    SimpleUniverse Z0159;
    Z0044 Z0091;
    Color Z0015;
    Matrix3d[] Z0013;
    Z0039[] Z0037;
    Z0043 Z0084;
    Z0041[] Z0073;
    Z0040[] Z0046;
    Z0045 Z0010;
    Z0038 Z0016;
    Appearance[] Z0006;
    Vector3d[] Z0182;
    Vector3d[] Z0183;
    Vector3d Z0125;
    Vector3d Z0151;
    double[] Z0105;
    double[] Z0152;
    double[] Z0083;
    double Z0100;
    double Z0101;
    double Z0099;
    double Z0104;
    double Z0174;
    double Z0078;
    double Z0123;
    double Z0085;
    double Z0072;
    double Z0171;
    double Z0012;
    double Z0014;
    double Z0121;
    double Z0122;
    double Z0119;
    double Z0120;
    double Z0124;
    double Z0169;
    int[] Z0036;
    int Z0093;
    int Z0096;
    int Z0158;
    boolean Z0011;
    boolean init;
    
    public void init() {
        this.Z0142();
        this.Z0015 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        (this.Z0028 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0028, "Center");
        this.Z0159 = new SimpleUniverse(this.Z0028);
        this.Z0159.getViewingPlatform().setNominalViewingTransform();
        this.Z0066 = null;
        this.Z0091 = new Z0044();
        this.Z0028.addMouseListener((MouseListener)this.Z0091);
        this.Z0028.addMouseMotionListener((MouseMotionListener)this.Z0091);
        this.Z0007 = null;
        this.init = false;
    }
    
    public void start() {
        if (this.Z0007 == null) {
            (this.Z0007 = new Thread(this)).setPriority(1);
            this.Z0007.start();
        }
        if (this.Z0066 == null) {
            this.Z0066 = this.Z0028.getGraphicsContext3D();
            this.Z0131();
        }
        this.Z0091.Z0153(Math.min(this.Z0028.getSize().width, this.Z0028.getSize().height));
        this.Z0127();
    }
    
    public void stop() {
        this.Z0007 = null;
    }
    
    public void Z0049() {
        this.Z0159.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0007) {
            if (this.init) {
                this.Z0144();
                this.Z0143();
                this.Z0141();
            }
            if (!this.Z0145(20L)) {
                break;
            }
        }
    }
    
    boolean Z0145(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0131() {
        this.Z0066.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0066.addLight((Light)directionalLight);
        this.Z0091.set(0.0, -75.0, 0.0);
        this.Z0151 = new Vector3d(0.0, 0.5, -20.0);
        this.Z0028.getView().setFrontClipDistance(1.0);
        this.Z0028.getView().setBackClipDistance(100.0);
    }
    
    void Z0143() {
        final double z0069 = this.Z0091.Z0069();
        if (z0069 != 0.0) {
            if (z0069 > 0.0) {
                final Vector3d z70 = this.Z0151;
                z70.z /= 1.0 + 3.0 * Math.abs(z0069);
            }
            else {
                final Vector3d z71 = this.Z0151;
                z71.z *= 1.0 + 3.0 * Math.abs(z0069);
            }
        }
    }
    
    void Z0142() {
        this.Z0125 = new Vector3d(10.0, 10.0, 10.0);
        this.Z0096 = 8;
        this.Z0093 = 4;
    }
    
    void Z0127() {
        this.Z0123 = 0.16 * this.Z0125.x;
        this.Z0072 = 0.16 * this.Z0123;
        this.Z0171 = 0.12 * this.Z0123;
        this.Z0012 = 0.25 * this.Z0123;
        this.Z0014 = 0.15 * this.Z0123;
        this.Z0174 = 3.25;
        this.Z0104 = 0.25 * this.Z0125.x;
        this.Z0078 = this.Z0174 * this.Z0104;
        this.Z0085 = 0.4 * this.Z0125.x;
        final double n = 6.283185307179586 * this.Z0123 * this.Z0174;
        final double n2 = this.Z0174 * this.Z0104;
        this.Z0100 = Math.sqrt(n * n + n2 * n2);
        this.Z0101 = 1.5707963267948966 * this.Z0123;
        this.Z0099 = this.Z0085;
        this.Z0134();
        this.Z0152[0] = this.Z0100;
        this.Z0152[1] = this.Z0099;
        this.Z0152[2] = this.Z0101;
        this.Z0152[3] = this.Z0099;
        this.Z0152[4] = this.Z0100;
        this.Z0152[5] = this.Z0099;
        this.Z0152[6] = this.Z0101;
        this.Z0152[7] = this.Z0099;
        this.Z0158 = 40;
        this.Z0084 = new Z0043(this.Z0085, this.Z0072, this.Z0171);
        this.Z0037[0] = new Z0039(this.Z0078, 3.141592653589793, this.Z0123, -this.Z0104, (int)(this.Z0174 * this.Z0158));
        this.Z0037[1] = new Z0039(this.Z0078, 1.5707963267948966, this.Z0123, 0.0, (int)(0.25 * this.Z0158));
        this.Z0037[2] = new Z0039(0.0, 0.0, this.Z0123, this.Z0104, (int)(this.Z0174 * this.Z0158));
        this.Z0037[3] = new Z0039(0.0, 4.71238898038469, this.Z0123, 0.0, (int)(0.25 * this.Z0158));
        this.Z0083[0] = 0.4 * this.Z0085;
        this.Z0083[1] = 0.1 * this.Z0085;
        this.Z0169 = 0.7 * this.Z0171;
        this.Z0124 = 1.2 * this.Z0012 + this.Z0169;
        this.Z0073[0] = new Z0041(this.Z0124, this.Z0083[0], this.Z0169);
        this.Z0073[1] = new Z0041(this.Z0124, this.Z0083[1], this.Z0169);
        this.Z0121 = 0.1 * this.Z0123;
        this.Z0122 = this.Z0123 - this.Z0072;
        this.Z0119 = 0.06 * this.Z0123;
        this.Z0120 = 0.3 * this.Z0119;
        this.Z0132();
        this.init = true;
    }
    
    void Z0134() {
        this.Z0152 = new double[this.Z0096];
        this.Z0105 = new double[this.Z0093];
        this.Z0036 = new int[this.Z0093];
        this.Z0182 = new Vector3d[this.Z0093];
        this.Z0183 = new Vector3d[this.Z0093];
        this.Z0013 = new Matrix3d[this.Z0093];
        for (int i = 0; i < this.Z0093; ++i) {
            this.Z0182[i] = new Vector3d();
            this.Z0183[i] = new Vector3d();
            this.Z0013[i] = new Matrix3d();
        }
        this.Z0037 = new Z0039[4];
        this.Z0073 = new Z0041[2];
        this.Z0046 = new Z0040[3];
        this.Z0083 = new double[2];
        this.Z0006 = new Appearance[4];
        for (int j = 0; j < 4; ++j) {
            this.Z0006[j] = new Appearance();
        }
    }
    
    void Z0144() {
        final Vector3d vector3d = new Vector3d();
        final Vector3d vector3d2 = new Vector3d();
        this.Z0011 = false;
        for (int i = 0; i < this.Z0093; ++i) {
            final double n = -0.05 * (1.0 - 0.5 * (this.Z0182[i].z / this.Z0078 + 0.5)) * this.Z0099;
            final double[] z0105 = this.Z0105;
            final int n2 = i;
            z0105[n2] += n;
            if (this.Z0105[i] >= this.Z0152[this.Z0036[i]]) {
                final double[] z106 = this.Z0105;
                final int n3 = i;
                z106[n3] -= this.Z0152[this.Z0036[i]];
                this.Z0036[i] = (this.Z0036[i] + 1) % this.Z0096;
            }
            else if (this.Z0105[i] < 0.0) {
                this.Z0036[i] = (this.Z0036[i] - 1 + this.Z0096) % this.Z0096;
                final double[] z107 = this.Z0105;
                final int n4 = i;
                z107[n4] += this.Z0152[this.Z0036[i]];
            }
            this.Z0183[i].set((Tuple3d)this.Z0182[i]);
            this.Z0133(i);
            vector3d.sub((Tuple3d)this.Z0182[i], (Tuple3d)this.Z0183[i]);
            vector3d2.set(-0.5 * vector3d.y / this.Z0012, 0.5 * vector3d.x / this.Z0012, 0.0);
            this.Z0013[i].mul(this.Z0128(vector3d2), this.Z0013[i]);
            if (this.Z0036[i] == 6) {
                this.Z0011 = true;
            }
        }
    }
    
    void Z0132() {
        this.Z0011 = false;
        for (int i = 0; i < this.Z0093; ++i) {
            this.Z0036[i] = 1 + 2 * i;
            this.Z0105[i] = 0.5 * this.Z0099;
            this.Z0013[i].setIdentity();
            this.Z0133(i);
            if (this.Z0036[i] == 6) {
                this.Z0011 = true;
            }
        }
    }
    
    void Z0133(final int n) {
        final double n2 = this.Z0105[n] / this.Z0152[this.Z0036[n]];
        final double n3 = 0.5 * this.Z0085;
        final double n4 = -0.5 * this.Z0078;
        final Vector3d vector3d = new Vector3d();
        final Vector3d vector3d2 = new Vector3d();
        switch (this.Z0036[n]) {
            case 0: {
                final double n5 = (1.5 - 2.0 * this.Z0174 * n2) * 3.141592653589793;
                vector3d.set(this.Z0123 * Math.cos(n5), this.Z0123 * Math.sin(n5), n2 * this.Z0078);
                vector3d2.set(-n3, -n3, n4);
                break;
            }
            case 1: {
                vector3d.set(0.0, this.Z0085 * n2, this.Z0078);
                vector3d2.set(-n3 - this.Z0123, -n3, n4);
                break;
            }
            case 2: {
                final double n6 = (1.0 - 0.5 * n2) * 3.141592653589793;
                vector3d.set(this.Z0123 * Math.cos(n6), this.Z0123 * Math.sin(n6), this.Z0078);
                vector3d2.set(-n3, n3, n4);
                break;
            }
            case 3: {
                vector3d.set(this.Z0085 * n2, 0.0, this.Z0078);
                vector3d2.set(-n3, n3 + this.Z0123, n4);
                break;
            }
            case 4: {
                final double n7 = (0.5 - 2.0 * this.Z0174 * n2) * 3.141592653589793;
                vector3d.set(this.Z0123 * Math.cos(n7), this.Z0123 * Math.sin(n7), (1.0 - n2) * this.Z0078);
                vector3d2.set(n3, n3, n4);
                break;
            }
            case 5: {
                vector3d.set(0.0, -this.Z0085 * n2, 0.0);
                vector3d2.set(n3 + this.Z0123, n3, n4);
                break;
            }
            case 6: {
                final double n8 = (0.0 - 0.5 * n2) * 3.141592653589793;
                vector3d.set(this.Z0123 * Math.cos(n8), this.Z0123 * Math.sin(n8), 0.0);
                vector3d2.set(n3, -n3, n4);
                break;
            }
            case 7: {
                vector3d.set(-this.Z0085 * n2, 0.0, 0.0);
                vector3d2.set(n3, -n3 - this.Z0123, n4);
                break;
            }
        }
        this.Z0182[n].add((Tuple3d)vector3d2, (Tuple3d)vector3d);
    }
    
    Matrix3d Z0128(final Vector3d vector3d) {
        final double[] array = new double[3];
        final double[] array2 = new double[3];
        final double n = 0.25 * vector3d.x * vector3d.x;
        array[0] = (1.0 - n) / (1.0 + n);
        array2[0] = vector3d.x / (1.0 + n);
        final double n2 = 0.25 * vector3d.y * vector3d.y;
        array[1] = (1.0 - n2) / (1.0 + n2);
        array2[1] = vector3d.y / (1.0 + n2);
        final double n3 = 0.25 * vector3d.z * vector3d.z;
        array[2] = (1.0 - n3) / (1.0 + n3);
        array2[2] = vector3d.z / (1.0 + n3);
        final Matrix3d matrix3d = new Matrix3d();
        final Matrix3d matrix3d2 = new Matrix3d();
        matrix3d.setElement(0, 0, array[1] * array[2]);
        matrix3d.setElement(0, 1, -array[1] * array2[2]);
        matrix3d.setElement(0, 2, array2[1]);
        matrix3d.setElement(1, 0, array2[0] * array2[1] * array[2] + array[0] * array2[2]);
        matrix3d.setElement(1, 1, -array2[0] * array2[1] * array2[2] + array[0] * array[2]);
        matrix3d.setElement(1, 2, -array2[0] * array[1]);
        matrix3d.setElement(2, 0, -array[0] * array2[1] * array[2] + array2[0] * array2[2]);
        matrix3d.setElement(2, 1, array[0] * array2[1] * array2[2] + array2[0] * array[2]);
        matrix3d.setElement(2, 2, array[0] * array[1]);
        matrix3d2.setElement(0, 0, array[1] * array[2]);
        matrix3d2.setElement(1, 0, array[1] * array2[2]);
        matrix3d2.setElement(2, 0, -array2[1]);
        matrix3d2.setElement(0, 1, array2[0] * array2[1] * array[2] - array[0] * array2[2]);
        matrix3d2.setElement(1, 1, array2[0] * array2[1] * array2[2] + array[0] * array[2]);
        matrix3d2.setElement(2, 1, array2[0] * array[1]);
        matrix3d2.setElement(0, 2, array[0] * array2[1] * array[2] + array2[0] * array2[2]);
        matrix3d2.setElement(1, 2, array[0] * array2[1] * array2[2] - array2[0] * array[2]);
        matrix3d2.setElement(2, 2, array[0] * array[1]);
        final Matrix3d matrix3d3 = new Matrix3d();
        matrix3d3.mul(matrix3d, matrix3d2);
        return matrix3d3;
    }
    
    void Z0141() {
        final Transform3D z0146 = this.Z0146();
        final Transform3D transform3D = new Transform3D();
        final Transform3D transform3D2 = new Transform3D();
        final Transform3D transform3D3 = new Transform3D();
        final Vector3d vector3d = new Vector3d();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        final Color3f[] array = new Color3f[3];
        final Color3f[] array2 = new Color3f[2];
        array[0] = new Z0042(0.1f, 0.8f, 0.5f).Z0070();
        this.Z0006[0].setMaterial(new Material(array[0], color3f, array[0], color3f, 0.0f));
        array[1] = new Z0042(0.05f, 0.8f, 0.5f).Z0070();
        this.Z0006[1].setMaterial(new Material(array[1], color3f, array[1], color3f, 0.0f));
        array[2] = new Z0042(0.25f, 0.8f, 0.4f).Z0070();
        this.Z0006[2].setMaterial(new Material(array[2], color3f, array[2], color3f, 0.0f));
        vector3d.set(-0.5 * this.Z0085, -0.5 * this.Z0085, -0.5 * this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0037[0].draw(this.Z0066, this.Z0006[0]);
        this.Z0129(transform3D3, this.Z0078, this.Z0078, 3.141592653589793, -this.Z0104, this.Z0174);
        vector3d.set(-this.Z0123, 0.0, this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(transform3D3, transform3D);
        transform3D2.rotZ(1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0084.draw(this.Z0066, this.Z0006[0]);
        vector3d.set(-0.5 * this.Z0085, 0.5 * this.Z0085, -0.5 * this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0037[1].draw(this.Z0066, this.Z0006[0]);
        this.Z0129(transform3D3, this.Z0078, this.Z0078, 1.5707963267948966, 0.0, 0.25);
        vector3d.set(0.0, this.Z0123, this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(transform3D3, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0084.draw(this.Z0066, this.Z0006[0]);
        vector3d.set(0.5 * this.Z0085, 0.5 * this.Z0085, -0.5 * this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0037[2].draw(this.Z0066, this.Z0006[0]);
        this.Z0129(transform3D3, 0.0, this.Z0078, 0.0, this.Z0104, this.Z0174);
        vector3d.set(this.Z0123, 0.0, 0.0);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(transform3D3, transform3D);
        transform3D2.rotZ(-1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0084.draw(this.Z0066, this.Z0006[0]);
        vector3d.set(0.5 * this.Z0085, -0.5 * this.Z0085, -0.5 * this.Z0078);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0037[3].draw(this.Z0066, this.Z0006[0]);
        this.Z0129(transform3D3, 0.0, 0.0, 4.71238898038469, 0.0, 0.25);
        vector3d.set(0.0, -this.Z0123, 0.0);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(transform3D3, transform3D);
        transform3D2.rotZ(3.141592653589793);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0084.draw(this.Z0066, this.Z0006[0]);
        vector3d.set(0.0, -0.5 * this.Z0085 - this.Z0123, -0.5 * this.Z0078 + this.Z0012);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        transform3D2.rotY(1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0046[0] = new Z0040(this.Z0124 - 1.2 * this.Z0169, 0.2 * this.Z0083[1], 1);
        this.Z0073[0].draw(this.Z0066, this.Z0006[0]);
        vector3d.set(0.5 * this.Z0085 + this.Z0123, 0.0, -0.5 * this.Z0078 + this.Z0012);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        transform3D2.rotZ(1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        transform3D2.rotY(1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0073[0].draw(this.Z0066, this.Z0006[0]);
        vector3d.set(0.5 * this.Z0085 + this.Z0123 / Math.sqrt(2.0), -0.5 * this.Z0085 - this.Z0123 / Math.sqrt(2.0), -0.5 * this.Z0078 + this.Z0012);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        transform3D2.rotZ(0.7853981633974483);
        transform3D3.mul(transform3D3, transform3D2);
        transform3D2.rotY(1.5707963267948966);
        transform3D3.mul(transform3D3, transform3D2);
        this.Z0066.setModelTransform(transform3D3);
        this.Z0073[1].draw(this.Z0066, this.Z0006[0]);
        if (!this.Z0011) {
            transform3D.setTranslation(vector3d);
            transform3D3.mul(z0146, transform3D);
            transform3D2.rotZ(0.7853981633974483);
            transform3D3.mul(transform3D3, transform3D2);
            transform3D2.rotY(1.5707963267948966);
            transform3D3.mul(transform3D3, transform3D2);
            this.Z0066.setModelTransform(transform3D3);
            this.Z0046[0].draw(this.Z0066, this.Z0006[0]);
        }
        vector3d.set(0.0, 0.0, -0.5 * this.Z0078 - 0.5 * this.Z0014 - this.Z0171);
        transform3D.setTranslation(vector3d);
        transform3D3.mul(z0146, transform3D);
        this.Z0066.setModelTransform(transform3D3);
        (this.Z0016 = new Z0038(this.Z0125.x, this.Z0125.y, this.Z0014)).draw(this.Z0066, this.Z0006[2]);
        array2[0] = new Color3f(new Z0042(0.5f, 0.8f, 0.7f).Z0070());
        array2[1] = new Color3f(new Z0042(0.8f, 0.8f, 0.7f).Z0070());
        this.Z0010 = new Z0045(this.Z0012, array2);
        final Material material = new Material(array[0], color3f, array[0], color3f2, 60.0f);
        material.setColorTarget(4);
        this.Z0006[3].setMaterial(material);
        for (int i = 0; i < this.Z0093; ++i) {
            vector3d.set(this.Z0182[i].x, this.Z0182[i].y, this.Z0182[i].z + this.Z0012);
            transform3D.setTranslation(vector3d);
            transform3D3.mul(z0146, transform3D);
            transform3D2.setIdentity();
            transform3D2.set(this.Z0013[i]);
            transform3D3.mul(transform3D3, transform3D2);
            this.Z0066.setModelTransform(transform3D3);
            this.Z0010.draw(this.Z0066, this.Z0006[3]);
        }
        this.Z0130();
    }
    
    void Z0129(final Transform3D transform3D, final double n, final double n2, final double n3, final double n4, final double n5) {
        final Transform3D transform3D2 = new Transform3D();
        final Transform3D transform3D3 = new Transform3D();
        final Transform3D transform3D4 = new Transform3D();
        final Vector3d translation = new Vector3d();
        translation.set(0.0, 0.0, 0.5 * n2 - this.Z0119);
        transform3D3.setTranslation(translation);
        transform3D2.mul(transform3D, transform3D3);
        this.Z0066.setModelTransform(transform3D2);
        (this.Z0046[1] = new Z0040(this.Z0121, n2 + 2.0 * this.Z0119, 1)).draw(this.Z0066, this.Z0006[1]);
        final int n6 = 4;
        final double n7 = 6.283185307179586 / n6;
        final double n8 = n4 / n6;
        double n9 = n3;
        double n10 = n;
        final int n11 = (int)(n6 * n5) + 1;
        this.Z0046[2] = new Z0040(this.Z0119, this.Z0122 - this.Z0121 + 2.0 * this.Z0120, 0);
        for (int i = 0; i < n11; ++i) {
            transform3D4.rotZ(n9);
            transform3D2.mul(transform3D, transform3D4);
            translation.set(this.Z0121 - this.Z0120, 0.0, n10 - this.Z0119);
            transform3D3.setTranslation(translation);
            transform3D2.mul(transform3D2, transform3D3);
            transform3D4.rotY(1.5707963267948966);
            transform3D2.mul(transform3D2, transform3D4);
            translation.set(0.0, 0.0, 0.5 * (this.Z0122 - this.Z0121 + 2.0 * this.Z0120));
            transform3D3.setTranslation(translation);
            transform3D2.mul(transform3D2, transform3D3);
            this.Z0066.setModelTransform(transform3D2);
            this.Z0046[2].draw(this.Z0066, this.Z0006[1]);
            n10 += n8;
            n9 += n7;
        }
    }
    
    Transform3D Z0146() {
        this.Z0066.setBackground(new Background(new Z0042(0.6f, 1.0f, 0.4f).Z0070()));
        this.Z0066.clear();
        final Transform3D modelTransform = new Transform3D();
        modelTransform.set(this.Z0091.get());
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(this.Z0151);
        modelTransform.mul(transform3D, modelTransform);
        this.Z0066.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0130() {
        final J3DGraphics2D graphics2D = this.Z0028.getGraphics2D();
        graphics2D.setColor(this.Z0015);
        graphics2D.draw3DRect(0, 0, this.Z0028.getSize().width - 1, this.Z0028.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0028.swap();
    }
    
    public class Z0039
    {
        private QuadArray Z0063;
        
        public Z0039(final double n, final double n2, final double n3, final double n4, final int n5) {
            final Point3d[] array = new Point3d[4];
            final Point3d[] array2 = new Point3d[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Point3d();
            }
            final Vector3d vector3d = new Vector3d();
            final Vector3d vector3d2 = new Vector3d();
            final Vector3f vector3f = new Vector3f();
            this.Z0063 = new QuadArray(16 * n5, 3);
            final double n6 = 6.283185307179586 / Spiral3d.this.Z0158;
            final double n7 = n4 / Spiral3d.this.Z0158;
            double n8 = n2;
            double n9 = n;
            double n10 = Math.cos(n8);
            double n11 = Math.sin(n8);
            array2[0].set((n3 + Spiral3d.this.Z0072) * n10, (n3 + Spiral3d.this.Z0072) * n11, n9);
            array2[1].set((n3 - Spiral3d.this.Z0072) * n10, (n3 - Spiral3d.this.Z0072) * n11, n9);
            array2[2].set((n3 - Spiral3d.this.Z0072) * n10, (n3 - Spiral3d.this.Z0072) * n11, n9 - Spiral3d.this.Z0171);
            array2[3].set((n3 + Spiral3d.this.Z0072) * n10, (n3 + Spiral3d.this.Z0072) * n11, n9 - Spiral3d.this.Z0171);
            int n12 = 0;
            for (int j = 0; j < n5; ++j) {
                for (int k = 0; k < 4; ++k) {
                    array[k].set((Tuple3d)array2[k]);
                }
                final double n13 = n10;
                final double n14 = n11;
                n8 += n6;
                n9 += n7;
                n10 = Math.cos(n8);
                n11 = Math.sin(n8);
                array2[0].set((n3 + Spiral3d.this.Z0072) * n10, (n3 + Spiral3d.this.Z0072) * n11, n9);
                array2[1].set((n3 - Spiral3d.this.Z0072) * n10, (n3 - Spiral3d.this.Z0072) * n11, n9);
                array2[2].set((n3 - Spiral3d.this.Z0072) * n10, (n3 - Spiral3d.this.Z0072) * n11, n9 - Spiral3d.this.Z0171);
                array2[3].set((n3 + Spiral3d.this.Z0072) * n10, (n3 + Spiral3d.this.Z0072) * n11, n9 - Spiral3d.this.Z0171);
                for (int l = 0; l < 4; ++l) {
                    if (l == 0 || l == 2) {
                        vector3d2.set(0.0, 0.0, (l == 2) ? -1.0 : 1.0);
                    }
                    else {
                        vector3d.set(n13, n14, 0.0);
                        vector3d2.set(n10, n11, 0.0);
                        if (l == 1) {
                            vector3d.scale(-1.0);
                            vector3d2.scale(-1.0);
                        }
                    }
                    if (l % 2 == 0) {
                        vector3f.set((Tuple3d)vector3d2);
                    }
                    if (l % 2 != 0) {
                        vector3f.set((Tuple3d)vector3d);
                    }
                    this.Z0063.setCoordinate(n12, array[l]);
                    this.Z0063.setNormal(n12, vector3f);
                    if (l % 2 != 0) {
                        vector3f.set((Tuple3d)vector3d2);
                    }
                    this.Z0063.setCoordinate(n12 + 1, array2[l]);
                    this.Z0063.setNormal(n12 + 1, vector3f);
                    this.Z0063.setCoordinate(n12 + 2, array2[(l + 1) % 4]);
                    this.Z0063.setNormal(n12 + 2, vector3f);
                    if (l % 2 != 0) {
                        vector3f.set((Tuple3d)vector3d);
                    }
                    this.Z0063.setCoordinate(n12 + 3, array[(l + 1) % 4]);
                    this.Z0063.setNormal(n12 + 3, vector3f);
                    n12 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0063, appearance));
        }
    }
    
    public class Z0043
    {
        private QuadArray Z0063;
        
        public Z0043(final double n, final double n2, final double n3) {
            final Point3d[] array = new Point3d[4];
            final Point3d[] array2 = new Point3d[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Point3d();
            }
            final Vector3d vector3d = new Vector3d();
            final Vector3f vector3f = new Vector3f();
            this.Z0063 = new QuadArray(16, 3);
            array[0].set(n, n2, 0.0);
            array[1].set(n, -n2, 0.0);
            array[2].set(n, -n2, -n3);
            array[3].set(n, n2, -n3);
            for (int j = 0; j < 4; ++j) {
                array2[j].set((Tuple3d)array[j]);
                final Point3d point3d = array2[j];
                point3d.x -= n;
            }
            int n4 = 0;
            for (int k = 0; k < 4; ++k) {
                if (k == 0 || k == 2) {
                    vector3d.set(0.0, 0.0, 1.0);
                }
                else {
                    vector3d.set(0.0, 1.0, 0.0);
                }
                if (k == 1 || k == 2) {
                    vector3d.scale(-1.0);
                }
                vector3f.set((Tuple3d)vector3d);
                this.Z0063.setCoordinate(n4, array[k]);
                this.Z0063.setNormal(n4, vector3f);
                this.Z0063.setCoordinate(n4 + 1, array2[k]);
                this.Z0063.setNormal(n4 + 1, vector3f);
                this.Z0063.setCoordinate(n4 + 2, array2[(k + 1) % 4]);
                this.Z0063.setNormal(n4 + 2, vector3f);
                this.Z0063.setCoordinate(n4 + 3, array[(k + 1) % 4]);
                this.Z0063.setNormal(n4 + 3, vector3f);
                n4 += 4;
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0063, appearance));
        }
    }
    
    public class Z0038
    {
        private QuadArray Z0062;
        
        public Z0038(final Spiral3d spiral3d) {
            this(spiral3d, 1.0);
        }
        
        public Z0038(final Spiral3d spiral3d, final double n) {
            this(spiral3d, new Vector3d(n, n, n));
        }
        
        public Z0038(final Spiral3d spiral3d, final double n, final double n2, final double n3) {
            this(spiral3d, new Vector3d(n, n2, n3));
        }
        
        public Z0038(final Vector3d vector3d) {
            final int[] array = { 0, 1, 3, 2, 4, 6, 7, 5, 0, 4, 5, 1, 2, 3, 7, 6, 0, 2, 6, 4, 1, 5, 7, 3 };
            this.Z0062 = new QuadArray(24, 3);
            final Point3d point3d = new Point3d(0.0, 0.0, 0.0);
            final Vector3d vector3d2 = new Vector3d(0.0, 0.0, 0.0);
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 6; ++i) {
                vector3d2.set(0.0, 0.0, 0.0);
                switch (i >> 1) {
                    case 0: {
                        vector3d2.x = 2 * (i & 0x1) - 1;
                        break;
                    }
                    case 1: {
                        vector3d2.y = 2 * (i & 0x1) - 1;
                        break;
                    }
                    case 2: {
                        vector3d2.z = 2 * (i & 0x1) - 1;
                        break;
                    }
                }
                vector3f.set((Tuple3d)vector3d2);
                for (int j = 0; j < 4; ++j) {
                    point3d.set(vector3d.x * ((array[4 * i + j] >> 2 & 0x1) - 0.5), vector3d.y * ((array[4 * i + j] >> 1 & 0x1) - 0.5), vector3d.z * ((array[4 * i + j] & 0x1) - 0.5));
                    this.Z0062.setCoordinate(4 * i + j, point3d);
                    this.Z0062.setNormal(4 * i + j, vector3f);
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0062, appearance));
        }
    }
    
    public class Z0045
    {
        private QuadArray Z0062;
        
        public Z0045(final Spiral3d spiral3d, final double n, final Color3f[] array) {
            this(spiral3d, n, 32, array);
        }
        
        public Z0045(final double n, int n2, final Color3f[] array) {
            n2 = (n2 + 3) / 4 * 4;
            if (n2 < 8) {
                n2 = 8;
            }
            final Point3d[] array2 = new Point3d[4];
            final Vector3d[] array3 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Point3d();
                array3[i] = new Vector3d();
            }
            this.Z0062 = new QuadArray(4 * n2 * n2, 7);
            int n3 = 0;
            double cos = 0.0;
            double sin = -1.0;
            for (int j = 0; j < n2; ++j) {
                final double n4 = cos;
                final double n5 = sin;
                final double n6 = 3.141592653589793 * (j + 1) / n2 - 1.5707963267948966;
                cos = Math.cos(n6);
                sin = Math.sin(n6);
                double cos2 = 1.0;
                double sin2 = 0.0;
                for (int k = 0; k < n2; ++k) {
                    final double n7 = cos2;
                    final double n8 = sin2;
                    final double n9 = 6.283185307179586 * (k + 1) / n2;
                    cos2 = Math.cos(n9);
                    sin2 = Math.sin(n9);
                    array3[0].set(n7 * cos, n8 * cos, sin);
                    array3[1].set(n7 * n4, n8 * n4, n5);
                    array3[2].set(cos2 * n4, sin2 * n4, n5);
                    array3[3].set(cos2 * cos, sin2 * cos, sin);
                    for (int l = 0; l < 4; ++l) {
                        array2[l].scale(n, (Tuple3d)array3[l]);
                    }
                    final int n10 = ((2 * (2 * j / n2) - 1) * (2 * (4 * k / n2 % 2) - 1) + 1) / 2;
                    for (int n11 = 0; n11 < 4; ++n11) {
                        this.Z0062.setCoordinate(n3 + n11, array2[n11]);
                        vector3f.set((Tuple3d)array3[n11]);
                        this.Z0062.setNormal(n3 + n11, vector3f);
                        this.Z0062.setColor(n3 + n11, array[n10]);
                    }
                    n3 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0062, appearance));
        }
    }
    
    public class Z0041
    {
        private QuadArray Z0063;
        
        public Z0041(final Spiral3d spiral3d) {
            this(spiral3d, 1.0, 1.0, 0.1);
        }
        
        public Z0041(final Spiral3d spiral3d, final double n, final double n2, final double n3) {
            this(spiral3d, n, n2, n3, 32);
        }
        
        public Z0041(final double n, final double n2, final double n3, final int n4) {
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
            }
            this.Z0063 = new QuadArray(16 * n4, 3);
            int n5 = 0;
            double cos = 1.0;
            double sin = 0.0;
            for (int j = 0; j < n4; ++j) {
                final double n6 = cos;
                final double n7 = sin;
                final double n8 = 6.283185307179586 * (j + 1) / n4;
                cos = Math.cos(n8);
                sin = Math.sin(n8);
                array2[0].set(n6, n7, 0.0);
                array2[1].set(n6, n7, 0.0);
                array2[2].set(cos, sin, 0.0);
                array2[3].set(cos, sin, 0.0);
                for (int k = 0; k < 4; ++k) {
                    array[k].scale(n, (Tuple3d)array2[k]);
                }
                array[0].z = 0.5 * n2;
                array[1].z = -0.5 * n2;
                array[2].z = -0.5 * n2;
                array[3].z = 0.5 * n2;
                for (int l = 0; l < 4; ++l) {
                    this.Z0063.setCoordinate(n5 + l, array[l]);
                    vector3f.set((Tuple3d)array2[l]);
                    this.Z0063.setNormal(n5 + l, vector3f);
                }
                n5 += 4;
            }
            double cos2 = 1.0;
            double sin2 = 0.0;
            for (int n9 = 0; n9 < n4; ++n9) {
                final double n10 = cos2;
                final double n11 = sin2;
                final double n12 = -6.283185307179586 * (n9 + 1) / n4;
                cos2 = Math.cos(n12);
                sin2 = Math.sin(n12);
                array2[0].set(n10, n11, 0.0);
                array2[1].set(n10, n11, 0.0);
                array2[2].set(cos2, sin2, 0.0);
                array2[3].set(cos2, sin2, 0.0);
                for (int n13 = 0; n13 < 4; ++n13) {
                    array[n13].scale(n - n3, (Tuple3d)array2[n13]);
                }
                array[0].z = 0.5 * n2;
                array[1].z = -0.5 * n2;
                array[2].z = -0.5 * n2;
                array[3].z = 0.5 * n2;
                for (int n14 = 0; n14 < 4; ++n14) {
                    this.Z0063.setCoordinate(n5 + n14, array[n14]);
                    vector3f.set((Tuple3d)array2[n14]);
                    vector3f.scale(-1.0f);
                    this.Z0063.setNormal(n5 + n14, vector3f);
                }
                n5 += 4;
            }
            for (int n15 = 0; n15 < 2; ++n15) {
                final double n16 = (n15 == 0) ? -1.0 : 1.0;
                array2[0].set(0.0, 0.0, n16);
                final double n17 = 0.5 * n2 * n16;
                double cos3 = 1.0;
                double sin3 = 0.0;
                for (int n18 = 0; n18 < n4; ++n18) {
                    final double n19 = cos3;
                    final double n20 = sin3;
                    final double n21 = -6.283185307179586 * (n18 + 1) * n16 / n4;
                    cos3 = Math.cos(n21);
                    sin3 = Math.sin(n21);
                    array[0].set((n - n3) * n19, (n - n3) * n20, n17);
                    array[1].set((n - n3) * cos3, (n - n3) * sin3, n17);
                    array[2].set(n * cos3, n * sin3, n17);
                    array[3].set(n * n19, n * n20, n17);
                    for (int n22 = 0; n22 < 4; ++n22) {
                        this.Z0063.setCoordinate(n5 + n22, array[n22]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0063.setNormal(n5 + n22, vector3f);
                    }
                    n5 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0063, appearance));
        }
    }
    
    public class Z0040
    {
        private QuadArray Z0063;
        private TriangleFanArray Z0064;
        
        public Z0040(final Spiral3d spiral3d) {
            this(spiral3d, 1.0, 1.0, 0);
        }
        
        public Z0040(final Spiral3d spiral3d, final double n, final double n2, final int n3) {
            this(spiral3d, n, n2, n3, 32);
        }
        
        public Z0040(final double n, final double n2, final int n3, final int n4) {
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
            }
            this.Z0063 = new QuadArray(4 * n4, 3);
            int n5 = 0;
            double cos = 1.0;
            double sin = 0.0;
            for (int j = 0; j < n4; ++j) {
                final double n6 = cos;
                final double n7 = sin;
                final double n8 = 6.283185307179586 * (j + 1) / n4;
                cos = Math.cos(n8);
                sin = Math.sin(n8);
                array2[0].set(n6, n7, 0.0);
                array2[1].set(n6, n7, 0.0);
                array2[2].set(cos, sin, 0.0);
                array2[3].set(cos, sin, 0.0);
                for (int k = 0; k < 4; ++k) {
                    array[k].scale(n, (Tuple3d)array2[k]);
                }
                array[0].z = 0.5 * n2;
                array[1].z = -0.5 * n2;
                array[2].z = -0.5 * n2;
                array[3].z = 0.5 * n2;
                for (int l = 0; l < 4; ++l) {
                    this.Z0063.setCoordinate(n5 + l, array[l]);
                    vector3f.set((Tuple3d)array2[l]);
                    this.Z0063.setNormal(n5 + l, vector3f);
                }
                n5 += 4;
            }
            if (n3 == 1) {
                this.Z0064 = new TriangleFanArray(2 * (n4 + 2), 3, new int[] { n4 + 2, n4 + 2 });
                int n9 = 0;
                for (int n10 = 0; n10 < 2; ++n10) {
                    final double n11 = (n10 == 0) ? -1.0 : 1.0;
                    array2[0].set(0.0, 0.0, n11);
                    final double n12 = 0.5 * n2 * n11;
                    array[0].set(0.0, 0.0, n12);
                    this.Z0064.setCoordinate(n9, array[0]);
                    vector3f.set((Tuple3d)array2[0]);
                    this.Z0064.setNormal(n9, vector3f);
                    ++n9;
                    for (int n13 = 0; n13 < n4 + 1; ++n13) {
                        final double n14 = 6.283185307179586 * n13 * n11 / n4;
                        array[0].set(n * Math.cos(n14), n * Math.sin(n14), n12);
                        this.Z0064.setCoordinate(n9, array[0]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0064.setNormal(n9, vector3f);
                        ++n9;
                    }
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0063, appearance));
            if (this.Z0064 != null) {
                graphicsContext3D.draw(new Shape3D((Geometry)this.Z0064, appearance));
            }
        }
    }
    
    public class Z0042
    {
        float[] Z0074;
        
        public Z0042(final Spiral3d spiral3d) {
            this(spiral3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0042(final Spiral3d spiral3d, final float[] array) {
            this(spiral3d, array[0], array[1], array[2]);
        }
        
        public Z0042(final float n, final float n2, final float n3) {
            (this.Z0074 = new float[3])[0] = n;
            this.Z0074[1] = n2;
            this.Z0074[2] = n3;
        }
        
        Color3f Z0070() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0074[0] * 5.9999f);
            final float n2 = this.Z0074[0] * 6.0f - n;
            array[0] = this.Z0074[2] * (1.0f - this.Z0074[1]);
            array[1] = this.Z0074[2] * (1.0f - n2 * this.Z0074[1]);
            array[2] = this.Z0074[2] * (1.0f - (1.0f - n2) * this.Z0074[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0074[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0074[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0074[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0074[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0074[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0074[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0044 extends MouseAdapter implements MouseMotionListener
    {
        private Z0023 Z0114;
        private int Z0157;
        private int Z0051;
        private int Z0052;
        private int Z0107;
        private int Z0108;
        private int Z0088;
        private int Z0031;
        private int Z0032;
        
        public Z0044() {
            this.Z0114 = new Z0023();
            this.Z0157 = 256;
            this.Z0107 = 0;
            this.Z0108 = 0;
            this.Z0031 = -999;
            this.Z0032 = -999;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0106(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0106(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0106(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0106(final int n, final int n2, final int z0088, final int n3) {
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            switch (n3) {
                case 1: {
                    this.Z0088 = z0088;
                    this.Z0051 = n;
                    this.Z0052 = n2;
                    if (this.Z0088 == 1) {
                        this.Z0031 = n;
                        this.Z0032 = n2;
                        break;
                    }
                    break;
                }
                case 0: {
                    if (this.Z0088 != 1) {
                        this.Z0107 = n - this.Z0051;
                        this.Z0108 = n2 - this.Z0052;
                        this.Z0051 = n;
                        this.Z0052 = n2;
                        break;
                    }
                    array[0] = this.Z0051;
                    array[1] = this.Z0052;
                    array2[0] = n;
                    array2[1] = n2;
                    if (this.Z0172(array, array2)) {
                        this.Z0051 = n;
                        this.Z0052 = n2;
                        break;
                    }
                    break;
                }
            }
        }
        
        private boolean Z0172(final int[] array, final int[] array2) {
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final Z0023 z0023 = new Z0023();
            final Z0023 z24 = new Z0023();
            final double n = 0.8 * this.Z0157;
            array3[0] = (array[0] - this.Z0157 / 2) / n;
            array3[1] = (this.Z0157 / 2 - array[1]) / n;
            final double n2 = array3[0] * array3[0] + array3[1] * array3[1];
            if (n2 > 1.0) {
                return false;
            }
            array3[2] = Math.sqrt(1.0 - n2);
            array4[0] = (array2[0] - this.Z0157 / 2) / n;
            array4[1] = (this.Z0157 / 2 - array2[1]) / n;
            final double n3 = array4[0] * array4[0] + array4[1] * array4[1];
            if (n3 > 1.0) {
                return false;
            }
            array4[2] = Math.sqrt(1.0 - n3);
            this.Z0140(z0023, array3[1] * array4[2] - array3[2] * array4[1], array3[2] * array4[0] - array3[0] * array4[2], array3[0] * array4[1] - array3[1] * array4[0], array3[0] * array4[0] + array3[1] * array4[1] + array3[2] * array4[2]);
            this.Z0139(z24, z0023, this.Z0114);
            this.Z0138(this.Z0114, z24);
            return true;
        }
        
        private void Z0140(final Z0023 z0023, final double z24, final double z25, final double z26, final double z27) {
            z0023.Z0175 = z24;
            z0023.Z0176 = z25;
            z0023.Z0177 = z26;
            z0023.Z0178 = z27;
        }
        
        private void Z0138(final Z0023 z0023, final Z0023 z24) {
            z0023.Z0175 = z24.Z0175;
            z0023.Z0176 = z24.Z0176;
            z0023.Z0177 = z24.Z0177;
            z0023.Z0178 = z24.Z0178;
        }
        
        private void Z0139(final Z0023 z0023, final Z0023 z24, final Z0023 z25) {
            z0023.Z0175 = z24.Z0178 * z25.Z0175 - z24.Z0177 * z25.Z0176 + z24.Z0176 * z25.Z0177 + z24.Z0175 * z25.Z0178;
            z0023.Z0176 = z24.Z0177 * z25.Z0175 + z24.Z0178 * z25.Z0176 - z24.Z0175 * z25.Z0177 + z24.Z0176 * z25.Z0178;
            z0023.Z0177 = -z24.Z0176 * z25.Z0175 + z24.Z0175 * z25.Z0176 + z24.Z0178 * z25.Z0177 + z24.Z0177 * z25.Z0178;
            z0023.Z0178 = -z24.Z0175 * z25.Z0175 - z24.Z0176 * z25.Z0176 - z24.Z0177 * z25.Z0177 + z24.Z0178 * z25.Z0178;
        }
        
        public void Z0153(final int z0157) {
            this.Z0157 = z0157;
        }
        
        public void set(final double n, final double n2, final double n3) {
            final double n4 = 0.008726646259971648 * n2;
            final double n5 = 0.008726646259971648 * (n - n3);
            final double n6 = 0.008726646259971648 * (n + n3);
            final double sin = Math.sin(n4);
            final double cos = Math.cos(n4);
            this.Z0140(this.Z0114, sin * Math.cos(n5), sin * Math.sin(n5), cos * Math.sin(n6), cos * Math.cos(n6));
        }
        
        public Matrix3d get() {
            final double[] array = new double[10];
            final double[] array2 = { this.Z0114.Z0175, this.Z0114.Z0176, this.Z0114.Z0177, this.Z0114.Z0178 };
            int n = 0;
            for (int i = 0; i < 4; ++i) {
                for (int j = i; j < 4; ++j, ++n) {
                    array[n] = 2.0 * array2[j] * array2[i];
                }
            }
            final Matrix3d matrix3d = new Matrix3d();
            matrix3d.setElement(0, 0, array[0] + array[9] - 1.0);
            matrix3d.setElement(1, 0, array[1] + array[8]);
            matrix3d.setElement(2, 0, array[2] - array[6]);
            matrix3d.setElement(0, 1, array[1] - array[8]);
            matrix3d.setElement(1, 1, array[4] + array[9] - 1.0);
            matrix3d.setElement(2, 1, array[5] + array[3]);
            matrix3d.setElement(0, 2, array[2] + array[6]);
            matrix3d.setElement(1, 2, array[5] - array[3]);
            matrix3d.setElement(2, 2, array[7] + array[9] - 1.0);
            return matrix3d;
        }
        
        public double Z0069() {
            final double n = this.Z0108 / this.Z0157;
            this.Z0108 = 0;
            return n;
        }
        
        public int Z0067() {
            final int z0031 = this.Z0031;
            this.Z0031 = -999;
            return z0031;
        }
        
        public int Z0068() {
            final int z0032 = this.Z0032;
            this.Z0032 = -999;
            return z0032;
        }
        
        class Z0023
        {
            double Z0175;
            double Z0176;
            double Z0177;
            double Z0178;
        }
    }
}
