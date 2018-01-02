import javax.vecmath.Matrix3d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.TriangleFanArray;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.AxisAngle4d;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.media.j3d.LineArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import javax.vecmath.Tuple3d;
import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.media.j3d.Transform3D;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.vecmath.Vector3d;
import javax.vecmath.Color3f;
import java.awt.Color;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Canvas3D;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scene3d extends JApplet implements Runnable
{
    Thread Z0009;
    Canvas3D Z0026;
    GraphicsContext3D Z0059;
    SimpleUniverse Z0164;
    Z0036 Z0079;
    Color Z0013;
    Color3f[] Z0098;
    Color3f black;
    Color3f white;
    double[] Z0099;
    double[] Z0100;
    double[] Z0102;
    float[] Z0101;
    Vector3d Z0152;
    Vector3d Z0018;
    Z0033[] Z0014;
    Z0034[] Z0195;
    Z0038[] Z0201;
    Z0037[] Z0128;
    Z0032[] Z0016;
    Z0039[] Z0171;
    double Z0019;
    int Z0082;
    int Z0085;
    int Z0084;
    int Z0089;
    int Z0086;
    int Z0081;
    int Z0091;
    int Z0083;
    int Z0162;
    int Z0163;
    boolean init;
    boolean Z0131;
    
    public Scene3d() {
        this.Z0099 = new double[] { 0.08, 0.17, 0.9, 0.98, 0.6, 0.3, 0.05, 0.07, 0.0, 0.3 };
        this.Z0100 = new double[] { 0.8, 0.7, 0.4, 0.7, 0.5, 0.6, 0.9, 0.6, 0.8, 0.8 };
        this.Z0102 = new double[] { 0.6, 0.5, 0.6, 0.6, 0.6, 0.6, 0.3, 0.4, 0.7, 0.7 };
        this.Z0101 = new float[] { 60.0f, 0.0f, 60.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
    }
    
    public void init() {
        this.Z0145();
        this.Z0013 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        (this.Z0026 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0026, "Center");
        this.Z0164 = new SimpleUniverse(this.Z0026);
        this.Z0164.getViewingPlatform().setNominalViewingTransform();
        this.Z0059 = null;
        this.Z0079 = new Z0036();
        this.Z0026.addMouseListener((MouseListener)this.Z0079);
        this.Z0026.addMouseMotionListener((MouseMotionListener)this.Z0079);
        this.Z0009 = null;
        this.init = false;
    }
    
    public void start() {
        if (this.Z0009 == null) {
            (this.Z0009 = new Thread(this)).setPriority(1);
            this.Z0009.start();
        }
        if (this.Z0059 == null) {
            this.Z0059 = this.Z0026.getGraphicsContext3D();
            this.Z0138();
        }
        this.Z0079.Z0153(Math.min(this.Z0026.getSize().width, this.Z0026.getSize().height));
        this.Z0134();
    }
    
    public void stop() {
        this.Z0009 = null;
    }
    
    public void Z0042() {
        this.Z0164.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0009) {
            if (this.init) {
                this.Z0140();
                if (this.Z0131) {
                    this.Z0147();
                }
                this.Z0146();
                this.Z0144();
            }
            if (!this.Z0148(20L)) {
                break;
            }
        }
    }
    
    void Z0140() {
        if (this.Z0135() == 0) {
            this.Z0131 = !this.Z0131;
            this.Z0128[8].Z0030 = this.Z0098[this.Z0131 ? 8 : 9];
        }
    }
    
    int Z0135() {
        int n = -1;
        final int z0060 = this.Z0079.Z0060();
        if (z0060 >= 0) {
            final int z61 = this.Z0079.Z0061();
            final Transform3D transform3D = new Transform3D();
            transform3D.set(this.Z0079.get());
            final Transform3D transform3D2 = new Transform3D();
            transform3D2.setTranslation(this.Z0152);
            transform3D.mul(transform3D2, transform3D);
            final Transform3D transform3D3 = new Transform3D();
            transform3D3.setScale(0.08);
            transform3D.mul(transform3D, transform3D3);
            final Transform3D transform3D4 = new Transform3D();
            this.Z0026.getVworldToImagePlate(transform3D4);
            final Transform3D transform3D5 = new Transform3D();
            transform3D5.mul(transform3D4, transform3D);
            final Point3d point3d = new Point3d();
            final Point3d point3d2 = new Point3d();
            final Point2d point2d = new Point2d();
            point3d.set((Tuple3d)this.Z0018);
            transform3D5.transform(point3d, point3d2);
            this.Z0026.getPixelLocationFromImagePlate(point3d2, point2d);
            final int n2 = z0060 - (int)point2d.x;
            final int n3 = z61 - (int)point2d.y;
            if (n2 * n2 + n3 * n3 < 1600) {
                n = 0;
            }
        }
        return n;
    }
    
    boolean Z0148(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0138() {
        this.Z0059.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0059.addLight((Light)directionalLight);
        this.Z0079.set(0.0, -60.0, 0.0);
        this.Z0152 = new Vector3d(0.0, 0.0, -3.5);
    }
    
    void Z0146() {
        final double z0062 = this.Z0079.Z0062();
        if (z0062 != 0.0) {
            if (z0062 > 0.0) {
                final Vector3d z63 = this.Z0152;
                z63.z /= 1.0 + 1.0 * Math.abs(z0062);
            }
            else {
                final Vector3d z64 = this.Z0152;
                z64.z *= 1.0 + 1.0 * Math.abs(z0062);
            }
        }
    }
    
    void Z0145() {
        this.Z0085 = 7;
        this.Z0163 = 5;
        this.Z0019 = 1.0;
    }
    
    void Z0134() {
        this.Z0082 = 12;
        this.Z0084 = 4;
        this.Z0089 = 1;
        this.Z0086 = 9;
        this.Z0081 = 1;
        this.Z0091 = 2;
        this.Z0083 = 10;
        this.Z0139();
        this.black = new Color3f(0.0f, 0.0f, 0.0f);
        this.white = new Color3f(0.4f, 0.4f, 0.4f);
        for (int i = 0; i < this.Z0083; ++i) {
            this.Z0098[i] = new Z0035((float)this.Z0099[i], (float)this.Z0100[i], (float)this.Z0102[i]).Z0063();
        }
        this.Z0018 = new Vector3d(12.0, -2.5, -9.5 + 0.15 * this.Z0019);
        final Vector3d vector3d = new Vector3d(1.0, 0.0, 0.0);
        final Vector3d vector3d2 = new Vector3d(0.0, 1.0, 0.0);
        final Vector3d vector3d3 = new Vector3d(0.0, 0.0, 1.0);
        this.Z0014[0] = new Z0033(new Vector3d(0.0, 0.0, 4.0), 0.0, vector3d, new Vector3d(2.0, 2.0, 2.0), 3);
        this.Z0014[1] = new Z0033(new Vector3d(-0.5, 11.0, 10.7), 0.0, vector3d3, new Vector3d(21.0, 2.0, 0.6), 4);
        this.Z0014[2] = new Z0033(new Vector3d(-12.0, 6.5, 10.7), 0.0, vector3d3, new Vector3d(2.0, 11.0, 0.6), 4);
        this.Z0014[3] = new Z0033(new Vector3d(2.0, 6.0, -10.0), 0.0, vector3d3, new Vector3d(31.0, 22.0, 1.0), 6);
        this.Z0014[4] = new Z0033(new Vector3d(-12.0, 6.0, 0.1), 0.0, vector3d3, new Vector3d(1.5, 0.5, 21.0), 7);
        this.Z0014[5] = new Z0033(new Vector3d(-4.5, 14.0, -4.0), 0.0, vector3d3, new Vector3d(0.5, 1.5, 11.0), 7);
        this.Z0014[6] = new Z0033(new Vector3d(6.0, 10.0, -3.0), 0.0, vector3d3, new Vector3d(0.5, 1.5, 13.0), 7);
        this.Z0014[7] = new Z0033(new Vector3d(-12.0, -3.0, -4.5), 0.0, vector3d3, new Vector3d(1.5, 0.5, 11.0), 7);
        this.Z0014[8] = new Z0033(new Vector3d(8.0, -3.0, -4.5), 0.0, vector3d3, new Vector3d(1.5, 0.5, 11.0), 7);
        this.Z0014[9] = new Z0033(new Vector3d(13.7, 10.0, 13.0), 0.0, vector3d, new Vector3d(2.0, 3.0, 0.5), 4);
        this.Z0014[10] = new Z0033(new Vector3d(15.2, 10.0, 13.0), 0.0, vector3d, new Vector3d(1.0, 1.0, 10.0), 4);
        this.Z0014[11] = new Z0033(new Vector3d(16.5, 10.0, 2.0), 0.0, vector3d3, new Vector3d(0.5, 1.5, 24.0), 7);
        this.Z0195[0] = new Z0034(new Vector3d(-12.0, 11.5, 0.0), 4.71238898038469, vector3d, 2.5, 1.0, 20, 1, 0, 1, 0.005235987755982988);
        this.Z0195[1] = new Z0034(new Vector3d(-9.5, 14.0, 0.0), 4.71238898038469, vector3d2, 2.5, 1.0, 20, 1, 1, 1, -0.005235987755982988);
        this.Z0195[2] = new Z0034(new Vector3d(0.0, 14.0, 0.0), 1.5707963267948966, vector3d2, 1.789, 1.0, 16, 0, 0, 1, 0.005235987755982988);
        this.Z0195[3] = new Z0034(new Vector3d(0.0, 10.0, 2.0), 1.5707963267948966, vector3d2, 2.683, 1.0, 24, 0, 1, 1, -0.003490658503988659);
        this.Z0201[0] = new Z0038(new Vector3d(11.2, 10.0, 2.0), 1.5707963267948966, vector3d2, 11.0, 2.0, 1.5, 1.0, 6, 0, -0.003490658503988659, 24, 2.0, 0.3, 0.5);
        this.Z0128[0] = new Z0037(new Vector3d(-12.0, 3.5, 0.0), 1.5707963267948966, vector3d, 0.5, 16.0, 2);
        this.Z0128[1] = new Z0037(new Vector3d(-4.75, 14.0, 0.0), 1.5707963267948966, vector3d2, 0.5, 9.5, 2);
        this.Z0128[2] = new Z0037(new Vector3d(5.75, 10.0, 2.0), 1.5707963267948966, vector3d2, 0.5, 11.5, 2);
        this.Z0128[3] = new Z0037(new Vector3d(8.0, -1.75, 0.0), 1.5707963267948966, vector3d, 0.5, 3.5, 2);
        this.Z0128[4] = new Z0037(new Vector3d(16.2, 10.0, 13.0), 1.5707963267948966, vector3d2, 0.3, 2.0, 2);
        this.Z0128[5] = new Z0037(new Vector3d(-12.0, 0.0, 0.0), 1.5707963267948966, vector3d, 1.5, 3.0, 2);
        this.Z0128[6] = new Z0037(new Vector3d(8.0, 0.0, 0.0), 1.5707963267948966, vector3d, 1.5, 3.0, 2);
        this.Z0128[7] = new Z0037(new Vector3d(0.0, 0.0, 0.0), 1.5707963267948966, vector3d2, 1.5, 3.0, 0);
        this.Z0128[8] = new Z0037(this.Z0018, 0.0, vector3d3, this.Z0019, 0.3 * this.Z0019, 8);
        this.Z0016[0] = new Z0032(new Vector3d(-12.0, 0.0, 0.0), 1.5707963267948966, vector3d, 1.5, 20.0, 3.0, 0.5, 60, 5, 0.005235987755982988);
        this.Z0171[0] = new Z0039(new Vector3d(-12.0, -4.7, 0.0), 0.0, vector3d2, 0.8, 0.2, 4.0, 4);
        this.Z0171[1] = new Z0039(new Vector3d(-12.0, -4.7, 0.0), 0.0, vector3d2, 0.8, 0.2, 4.0, 4);
        this.Z0162 = 0;
        this.init = true;
        this.Z0131 = true;
    }
    
    void Z0139() {
        this.Z0098 = new Color3f[this.Z0083];
        this.Z0014 = new Z0033[this.Z0082];
        this.Z0195 = new Z0034[this.Z0084];
        this.Z0201 = new Z0038[this.Z0089];
        this.Z0128 = new Z0037[this.Z0086];
        this.Z0016 = new Z0032[this.Z0081];
        this.Z0171 = new Z0039[this.Z0091];
    }
    
    void Z0147() {
        this.Z0162 += this.Z0163;
        this.Z0014[9].Z0004 = 0.3490658503988659 * Math.sin(this.Z0162 * this.Z0201[0].Z0130 * this.Z0201[0].Z0080 + 3.141592653589793);
        this.Z0014[10].Z0004 = this.Z0014[9].Z0004;
        this.Z0171[0].Z0004 = this.Z0162 * this.Z0195[0].Z0130;
        this.Z0171[1].Z0004 = this.Z0162 * this.Z0195[0].Z0130 + 3.141592653589793;
    }
    
    void Z0144() {
        final Transform3D z0149 = this.Z0149();
        for (int i = 0; i < this.Z0082; ++i) {
            if (i == 0) {
                for (int j = 0; j < this.Z0085; ++j) {
                    this.Z0137(j);
                    this.Z0014[i].Z0129();
                    this.Z0014[i].draw(this.Z0059, z0149);
                }
            }
            else {
                this.Z0014[i].Z0129();
                this.Z0014[i].draw(this.Z0059, z0149);
            }
        }
        for (int k = 0; k < this.Z0084; ++k) {
            this.Z0195[k].Z0129(this.Z0162);
            this.Z0195[k].draw(this.Z0059, z0149);
        }
        for (int l = 0; l < this.Z0089; ++l) {
            this.Z0201[l].Z0129(this.Z0162);
            this.Z0201[l].draw(this.Z0059, z0149);
        }
        for (int n = 0; n < this.Z0086; ++n) {
            if (n != 7) {
                this.Z0128[n].Z0129();
                this.Z0128[n].draw(this.Z0059, z0149);
            }
        }
        for (int n2 = 0; n2 < this.Z0081; ++n2) {
            this.Z0016[n2].Z0129(this.Z0162);
            this.Z0016[n2].draw(this.Z0059, z0149);
        }
        for (int n3 = 0; n3 < this.Z0091; ++n3) {
            this.Z0171[n3].Z0129();
            this.Z0171[n3].draw(this.Z0059, z0149);
        }
        this.Z0136();
    }
    
    void Z0137(final int n) {
        final double[] array = new double[5];
        final double n2 = (this.Z0016[0].Z0127 + this.Z0016[0].Z0173) * this.Z0016[0].Z0130;
        final double n3 = 0.05;
        array[0] = (this.Z0201[0].Z0110.x - this.Z0016[0].Z0110.x - 0.5 * (this.Z0201[0].Z0200 + this.Z0014[0].Z0160.x)) / n2;
        array[1] = array[0] - 1.5707963267948966 / this.Z0201[0].Z0130;
        array[2] = array[1] + (this.Z0201[0].Z0110.x - 0.5 * (this.Z0201[0].Z0200 + this.Z0014[0].Z0160.x) - this.Z0014[2].Z0110.x) / n3;
        array[3] = array[2] + this.Z0014[2].Z0160.y / n3;
        array[4] = array[3] + (this.Z0014[2].Z0110.z + 0.5 * this.Z0014[2].Z0160.z - (this.Z0016[0].Z0110.y + this.Z0016[0].Z0127 + this.Z0016[0].Z0173)) / n3;
        double n4;
        for (n4 = this.Z0162 + n * array[4] / this.Z0085; n4 >= array[4]; n4 -= array[4]) {}
        if (n4 < array[0]) {
            this.Z0014[0].Z0110.set(this.Z0016[0].Z0110.x + n2 * n4, 0.0, this.Z0016[0].Z0110.z + this.Z0016[0].Z0127 + this.Z0016[0].Z0173 + 0.5 * this.Z0014[0].Z0160.z);
            this.Z0014[0].Z0004 = 0.0;
        }
        else if (n4 < array[1]) {
            this.Z0014[0].Z0010.set(1.0, 0.0, 0.0);
            this.Z0014[0].Z0004 = (n4 - array[0]) * this.Z0201[0].Z0130;
            final double n5 = 0.5 * this.Z0014[0].Z0160.z / this.Z0201[0].Z0127 - this.Z0014[0].Z0004;
            final double y = this.Z0201[0].Z0110.y;
            this.Z0014[0].Z0110.set(this.Z0201[0].Z0110.x - 0.5 * (this.Z0201[0].Z0200 + this.Z0014[0].Z0160.x), y * (1.0 - Math.cos(n5)), this.Z0201[0].Z0110.z + y * Math.sin(n5));
        }
        else if (n4 < array[2]) {
            final double n6 = n4 - array[1];
            this.Z0014[0].Z0010.set(1.0, 0.0, 0.0);
            this.Z0014[0].Z0004 = 1.5707963267948966;
            final double y2 = this.Z0201[0].Z0110.y;
            this.Z0014[0].Z0110.set(this.Z0201[0].Z0110.x - 0.5 * (this.Z0201[0].Z0200 + this.Z0014[0].Z0160.x) - n6 * n3, this.Z0014[1].Z0110.y, this.Z0014[1].Z0110.z + 0.5 * (this.Z0014[1].Z0160.z + this.Z0014[0].Z0160.z));
        }
        else if (n4 < array[3]) {
            final double n7 = n4 - array[2];
            this.Z0014[0].Z0010.set(1.0, 0.0, 0.0);
            this.Z0014[0].Z0004 = 1.5707963267948966;
            this.Z0014[0].Z0110.set(0.0, 0.5 * (this.Z0014[2].Z0160.y - this.Z0014[0].Z0160.y) - n7 * n3, 0.5 * (this.Z0014[2].Z0160.z + this.Z0014[0].Z0160.z));
            this.Z0014[0].Z0110.add((Tuple3d)this.Z0014[0].Z0110, (Tuple3d)this.Z0014[2].Z0110);
        }
        else {
            final double n8 = n4 - array[3];
            this.Z0014[0].Z0010.set(1.0, 0.0, 0.0);
            this.Z0014[0].Z0004 = 1.5707963267948966;
            this.Z0014[0].Z0110.set(0.0, -0.5 * (this.Z0014[2].Z0160.y + this.Z0014[0].Z0160.y), 0.5 * (this.Z0014[2].Z0160.z + this.Z0014[0].Z0160.z) - n8 * n3);
            this.Z0014[0].Z0110.add((Tuple3d)this.Z0014[0].Z0110, (Tuple3d)this.Z0014[2].Z0110);
        }
    }
    
    Transform3D Z0149() {
        this.Z0059.setBackground(new Background(new Z0035(0.6f, 1.0f, 0.4f).Z0063()));
        this.Z0059.clear();
        final Transform3D modelTransform = new Transform3D();
        modelTransform.set(this.Z0079.get());
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(this.Z0152);
        modelTransform.mul(transform3D, modelTransform);
        final Transform3D transform3D2 = new Transform3D();
        transform3D2.setScale(0.08);
        modelTransform.mul(modelTransform, transform3D2);
        this.Z0059.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0136() {
        final J3DGraphics2D graphics2D = this.Z0026.getGraphics2D();
        graphics2D.setColor(this.Z0013);
        graphics2D.draw3DRect(0, 0, this.Z0026.getSize().width - 1, this.Z0026.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0026.swap();
    }
    
    public class Z0032
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private double Z0127;
        private double Z0045;
        private double Z0196;
        private double Z0173;
        private int Z0090;
        private Color3f Z0030;
        private float Z0159;
        private double Z0130;
        private int Z0087;
        private QuadArray Z0056;
        private LineArray Z0076;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        private Appearance Z0008;
        
        public Z0032(final Vector3d z0110, final double z111, final Vector3d z112, final double z113, final double z114, final double z115, final double z116, final int z117, final int n, final double z118) {
            this.Z0087 = 36;
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0127 = z113;
            this.Z0045 = z114;
            this.Z0196 = z115;
            this.Z0173 = z116;
            this.Z0090 = z117;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0130 = z118;
            this.Z0007 = new Appearance();
            this.Z0008 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0056 = new QuadArray(24 * (this.Z0087 / 2) + 32, 3);
            this.Z0076 = new LineArray(2 * z117, 1);
        }
        
        public void Z0129(final int n) {
            double n2;
            for (n2 = n * this.Z0130; n2 >= 6.283185307179586; n2 -= 6.283185307179586) {}
            while (n2 < 0.0) {
                n2 += 6.283185307179586;
            }
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
            }
            final double n3 = 0.5 * this.Z0196;
            final double n4 = this.Z0127 + this.Z0173;
            int n5 = 0;
            for (int j = 0; j < 2; ++j) {
                final double n6 = j * this.Z0045;
                for (int k = -1; k <= 1; k += 2) {
                    array2[0].set(0.0, 0.0, (double)k);
                    for (int l = 0; l < this.Z0087 / 2; ++l) {
                        if (l == 0) {
                            final double n7 = k * (6.283185307179586 * l / this.Z0087 + (j + 0.5) * 3.141592653589793);
                            final double cos = Math.cos(n7);
                            final double sin = Math.sin(n7);
                            array[0].set(n6 + this.Z0127 * cos, this.Z0127 * sin, n3 * k);
                            array[1].set(n6 + n4 * cos, n4 * sin, n3 * k);
                        }
                        else {
                            array[0].set((Tuple3d)array[3]);
                            array[1].set((Tuple3d)array[2]);
                        }
                        final double n8 = k * (6.283185307179586 * (l + 1) / this.Z0087 + (j + 0.5) * 3.141592653589793);
                        final double cos2 = Math.cos(n8);
                        final double sin2 = Math.sin(n8);
                        array[2].set(n6 + n4 * cos2, n4 * sin2, n3 * k);
                        array[3].set(n6 + this.Z0127 * cos2, this.Z0127 * sin2, n3 * k);
                        for (int n9 = 0; n9 < 4; ++n9) {
                            this.Z0056.setCoordinate(n5 + n9, array[n9]);
                            vector3f.set((Tuple3d)array2[0]);
                            this.Z0056.setNormal(n5 + n9, vector3f);
                        }
                        n5 += 4;
                    }
                }
            }
            for (int n10 = 0; n10 < 2; ++n10) {
                for (int n11 = -1; n11 <= 1; n11 += 2) {
                    array2[0].set(0.0, 0.0, (double)n11);
                    array[0].set(this.Z0045, (n10 == 0) ? (-n4 * n11) : (this.Z0127 * n11), n3 * n11);
                    array[1].set(this.Z0045, (n10 == 0) ? (-this.Z0127 * n11) : (n4 * n11), array[0].z);
                    array[2].set(0.0, array[1].y, array[0].z);
                    array[3].set(0.0, array[0].y, array[0].z);
                    for (int n12 = 0; n12 < 4; ++n12) {
                        this.Z0056.setCoordinate(n5 + n12, array[n12]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0056.setNormal(n5 + n12, vector3f);
                    }
                    n5 += 4;
                }
            }
            for (int n13 = 0; n13 < 2; ++n13) {
                final double n14 = n13 * this.Z0045;
                for (int n15 = 0; n15 < this.Z0087 / 2; ++n15) {
                    if (n15 == 0) {
                        final double n16 = 6.283185307179586 * n15 / this.Z0087 + (n13 + 0.5) * 3.141592653589793;
                        final double cos3 = Math.cos(n16);
                        final double sin3 = Math.sin(n16);
                        array2[0].set(cos3, sin3, 0.0);
                        array[0].set(n14 + n4 * cos3, n4 * sin3, n3);
                        array[1].set(n14 + n4 * cos3, n4 * sin3, -n3);
                    }
                    else {
                        array2[0].set((Tuple3d)array2[1]);
                        array[0].set((Tuple3d)array[3]);
                        array[1].set((Tuple3d)array[2]);
                    }
                    final double n17 = 6.283185307179586 * (n15 + 1) / this.Z0087 + (n13 + 0.5) * 3.141592653589793;
                    final double cos4 = Math.cos(n17);
                    final double sin4 = Math.sin(n17);
                    array2[1].set(cos4, sin4, 0.0);
                    array[2].set(n14 + n4 * cos4, n4 * sin4, -n3);
                    array[3].set(n14 + n4 * cos4, n4 * sin4, n3);
                    for (int n18 = 0; n18 < 4; ++n18) {
                        this.Z0056.setCoordinate(n5 + n18, array[n18]);
                        vector3f.set((Tuple3d)array2[n18 / 2]);
                        this.Z0056.setNormal(n5 + n18, vector3f);
                    }
                    n5 += 4;
                }
            }
            for (int n19 = 0; n19 < 2; ++n19) {
                for (int n20 = -1; n20 <= 1; n20 += 2) {
                    array2[0].set(0.0, (double)(-n20), 0.0);
                    array[0].set((n20 > 0.0) ? this.Z0045 : 0.0, (n19 == 0) ? (-n20 * n4) : (n20 * this.Z0127), -n3);
                    array[1].set((n20 > 0.0) ? this.Z0045 : 0.0, array[0].y, n3);
                    array[2].set((n20 > 0.0) ? 0.0 : this.Z0045, array[0].y, n3);
                    array[3].set((n20 > 0.0) ? 0.0 : this.Z0045, array[0].y, -n3);
                    for (int n21 = 0; n21 < 4; ++n21) {
                        this.Z0056.setCoordinate(n5 + n21, array[n21]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0056.setNormal(n5 + n21, vector3f);
                    }
                    n5 += 4;
                }
            }
            final double[] array3 = new double[3];
            final double n22 = 0.5 * this.Z0196;
            final double n23 = this.Z0127 + this.Z0173;
            double n24;
            double n25;
            for (n24 = 2.0 * this.Z0045 + 6.283185307179586 * n23, n25 = n23 * n2; n25 >= n24; n25 -= n24) {}
            while (n25 < 0.0) {
                n25 += n24;
            }
            final double n26 = 0.05;
            array3[0] = this.Z0045;
            array3[1] = this.Z0045 + 3.141592653589793 * n23;
            array3[2] = 2.0 * this.Z0045 + 3.141592653589793 * n23;
            int n27 = 0;
            for (int n28 = 0; n28 < this.Z0090; ++n28) {
                n25 += n24 / this.Z0090;
                if (n25 >= n24) {
                    n25 -= n24;
                }
                if (n25 < array3[0]) {
                    array[0].set(n25, n23 + n26, n22);
                }
                else if (n25 < array3[1]) {
                    final double n29 = (n25 - array3[0]) / n23;
                    array[0].set(this.Z0045 + (n23 + n26) * Math.sin(n29), (n23 + n26) * Math.cos(n29), n22);
                }
                else if (n25 < array3[2]) {
                    array[0].set(this.Z0045 - (n25 - array3[1]), -(n23 + n26), n22);
                }
                else {
                    final double n30 = (n25 - array3[2]) / n23;
                    array[0].set(-(n23 + n26) * Math.sin(n30), -(n23 + n26) * Math.cos(n30), n22);
                }
                array[1].set(array[0].x, array[0].y, -array[0].z);
                for (int n31 = 0; n31 < 2; ++n31) {
                    this.Z0076.setCoordinate(n27 + n31, array[n31]);
                }
                n27 += 2;
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0056, this.Z0007));
            this.Z0008.setMaterial(new Material(Scene3d.this.black, Scene3d.this.black, Scene3d.this.black, Scene3d.this.black, 0.0f));
            final LineAttributes lineAttributes = new LineAttributes();
            lineAttributes.setLineWidth(2.0f);
            this.Z0008.setLineAttributes(lineAttributes);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0076, this.Z0008));
        }
    }
    
    public class Z0038
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private double Z0127;
        private double Z0198;
        private double Z0125;
        private double Z0200;
        private int Z0088;
        private Color3f Z0030;
        private float Z0159;
        private double Z0130;
        private int Z0080;
        private double Z0074;
        private double Z0124;
        private double Z0068;
        private double Z0006;
        private int Z0087;
        private QuadArray Z0056;
        private TriangleFanArray Z0055;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        
        public Z0038(final Vector3d z0110, final double z111, final Vector3d z112, final double z113, final double z114, final double z115, final double z116, final int z117, final int n, final double z118, final int z119, final double z120, final double z121, final double z122) {
            this.Z0087 = 36;
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0127 = z113;
            this.Z0198 = z114;
            this.Z0125 = z115;
            this.Z0200 = z116;
            this.Z0088 = z117;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0130 = z118;
            this.Z0080 = z119;
            this.Z0074 = z120;
            this.Z0124 = z121;
            this.Z0068 = z122;
            this.Z0007 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0056 = new QuadArray(20 * this.Z0087 + 24 * z117, 3);
            this.Z0055 = new TriangleFanArray(2 * (this.Z0087 + 2), 3, new int[] { this.Z0087 + 2, this.Z0087 + 2 });
        }
        
        public void Z0129(final int n) {
            this.Z0006 = n * this.Z0130;
            while (this.Z0006 >= 6.283185307179586) {
                this.Z0006 -= 6.283185307179586;
            }
            while (this.Z0006 < 0.0) {
                this.Z0006 += 6.283185307179586;
            }
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Point3d[] array3 = new Point3d[2];
            final Vector3d vector3d = new Vector3d();
            final Vector3d vector3d2 = new Vector3d();
            final Vector3d vector3d3 = new Vector3d();
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
                if (i < 2) {
                    array3[i] = new Point3d();
                }
            }
            final double n2 = 0.5 * this.Z0200;
            final double n3 = this.Z0127 - this.Z0198;
            int n4 = 0;
            for (int j = -1; j <= 1; j += 2) {
                array2[0].set(0.0, 0.0, (double)j);
                for (int k = 0; k < this.Z0087; ++k) {
                    if (k == 0) {
                        final double n5 = j * 2.0 * 3.141592653589793 * k / this.Z0087;
                        final double cos = Math.cos(n5);
                        final double sin = Math.sin(n5);
                        array[0].set(n3 * cos, n3 * sin, n2 * j);
                        array[1].set(this.Z0127 * cos, this.Z0127 * sin, n2 * j);
                        array3[0].set((Tuple3d)array[0]);
                        array3[1].set((Tuple3d)array[1]);
                    }
                    else {
                        array[0].set((Tuple3d)array[3]);
                        array[1].set((Tuple3d)array[2]);
                    }
                    if (k < this.Z0087 - 1) {
                        final double n6 = j * 2.0 * 3.141592653589793 * (k + 1) / this.Z0087;
                        final double cos2 = Math.cos(n6);
                        final double sin2 = Math.sin(n6);
                        array[2].set(this.Z0127 * cos2, this.Z0127 * sin2, n2 * j);
                        array[3].set(n3 * cos2, n3 * sin2, n2 * j);
                    }
                    else {
                        array[2].set((Tuple3d)array3[1]);
                        array[3].set((Tuple3d)array3[0]);
                    }
                    for (int l = 0; l < 4; ++l) {
                        this.Z0056.setCoordinate(n4 + l, array[l]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0056.setNormal(n4 + l, vector3f);
                    }
                    n4 += 4;
                }
            }
            for (int n7 = -1; n7 <= 1; n7 += 2) {
                final double n8 = this.Z0127 - this.Z0198 * 0.5 * (1 - n7);
                for (int n9 = 0; n9 < this.Z0087; ++n9) {
                    if (n9 == 0) {
                        final double n10 = n7 * 2.0 * 3.141592653589793 * n9 / this.Z0087;
                        final double cos3 = Math.cos(n10);
                        final double sin3 = Math.sin(n10);
                        array2[0].set(n7 * cos3, n7 * sin3, 0.0);
                        array[0].set(n8 * cos3, n8 * sin3, n2);
                        array[1].set(n8 * cos3, n8 * sin3, -n2);
                        vector3d.set((Tuple3d)array2[0]);
                        array3[0].set((Tuple3d)array[0]);
                        array3[1].set((Tuple3d)array[1]);
                    }
                    else {
                        array2[0].set((Tuple3d)array2[1]);
                        array[0].set((Tuple3d)array[3]);
                        array[1].set((Tuple3d)array[2]);
                    }
                    if (n9 < this.Z0087 - 1) {
                        final double n11 = n7 * 2.0 * 3.141592653589793 * (n9 + 1) / this.Z0087;
                        final double cos4 = Math.cos(n11);
                        final double sin4 = Math.sin(n11);
                        array2[1].set(n7 * cos4, n7 * sin4, 0.0);
                        array[2].set(n8 * cos4, n8 * sin4, -n2);
                        array[3].set(n8 * cos4, n8 * sin4, n2);
                    }
                    else {
                        array2[1].set((Tuple3d)vector3d);
                        array[2].set((Tuple3d)array3[1]);
                        array[3].set((Tuple3d)array3[0]);
                    }
                    for (int n12 = 0; n12 < 4; ++n12) {
                        this.Z0056.setCoordinate(n4 + n12, array[n12]);
                        vector3f.set((Tuple3d)array2[n12 / 2]);
                        this.Z0056.setNormal(n4 + n12, vector3f);
                    }
                    n4 += 4;
                }
            }
            for (int n13 = 0; n13 < this.Z0087; ++n13) {
                if (n13 == 0) {
                    final double n14 = 6.283185307179586 * n13 / this.Z0087;
                    final double cos5 = Math.cos(n14);
                    final double sin5 = Math.sin(n14);
                    array2[0].set(cos5, sin5, 0.0);
                    array[0].set(this.Z0125 * cos5, this.Z0125 * sin5, n2);
                    array[1].set(this.Z0125 * cos5, this.Z0125 * sin5, -n2);
                    vector3d.set((Tuple3d)array2[0]);
                    array3[0].set((Tuple3d)array[0]);
                    array3[1].set((Tuple3d)array[1]);
                }
                else {
                    array2[0].set((Tuple3d)array2[1]);
                    array[0].set((Tuple3d)array[3]);
                    array[1].set((Tuple3d)array[2]);
                }
                if (n13 < this.Z0087 - 1) {
                    final double n15 = 6.283185307179586 * (n13 + 1) / this.Z0087;
                    final double cos6 = Math.cos(n15);
                    final double sin6 = Math.sin(n15);
                    array2[1].set(cos6, sin6, 0.0);
                    array[2].set(this.Z0125 * cos6, this.Z0125 * sin6, -n2);
                    array[3].set(this.Z0125 * cos6, this.Z0125 * sin6, n2);
                }
                else {
                    array2[1].set((Tuple3d)vector3d);
                    array[2].set((Tuple3d)array3[1]);
                    array[3].set((Tuple3d)array3[0]);
                }
                for (int n16 = 0; n16 < 4; ++n16) {
                    this.Z0056.setCoordinate(n4 + n16, array[n16]);
                    vector3f.set((Tuple3d)array2[n16 / 2]);
                    this.Z0056.setNormal(n4 + n16, vector3f);
                }
                n4 += 4;
            }
            int n17 = 0;
            for (int n18 = -1; n18 <= 1; n18 += 2) {
                array2[0].set(0.0, 0.0, (double)n18);
                array[0].set(0.0, 0.0, n2 * n18);
                this.Z0055.setCoordinate(n17, array[0]);
                vector3f.set((Tuple3d)array2[0]);
                this.Z0055.setNormal(n17, vector3f);
                ++n17;
                for (int n19 = 0; n19 <= this.Z0087; ++n19) {
                    final double n20 = n18 * 2.0 * 3.141592653589793 * n19 / this.Z0087;
                    array[0].set(this.Z0125 * Math.cos(n20), this.Z0125 * Math.sin(n20), n2 * n18);
                    this.Z0055.setCoordinate(n17, array[0]);
                    vector3f.set((Tuple3d)array2[0]);
                    this.Z0055.setNormal(n17, vector3f);
                    ++n17;
                }
            }
            final double n21 = 0.3 * this.Z0200;
            final double n22 = this.Z0127 - this.Z0198 * 0.5;
            for (int n23 = -1; n23 <= 1; n23 += 2) {
                array2[0].set(0.0, 0.0, (double)n23);
                for (int n24 = 0; n24 < this.Z0088; ++n24) {
                    final double n25 = this.Z0006 + n23 * 2.0 * 3.141592653589793 * (n24 - 0.25) / this.Z0088;
                    final double cos7 = Math.cos(n25);
                    final double sin7 = Math.sin(n25);
                    array[0].set(this.Z0125 * cos7, this.Z0125 * sin7, n21 * n23);
                    array[1].set(n22 * cos7, n22 * sin7, n21 * n23);
                    final double n26 = this.Z0006 + n23 * 2.0 * 3.141592653589793 * (n24 + 0.25) / this.Z0088;
                    final double cos8 = Math.cos(n26);
                    final double sin8 = Math.sin(n26);
                    array[2].set(n22 * cos8, n22 * sin8, n21 * n23);
                    array[3].set(this.Z0125 * cos8, this.Z0125 * sin8, n21 * n23);
                    for (int n27 = 0; n27 < 4; ++n27) {
                        this.Z0056.setCoordinate(n4 + n27, array[n27]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0056.setNormal(n4 + n27, vector3f);
                    }
                    n4 += 4;
                }
            }
            final double n28 = this.Z0127 - this.Z0198;
            for (int n29 = 0; n29 < this.Z0088; ++n29) {
                for (int n30 = 0; n30 < 2; ++n30) {
                    if (n30 == 0) {
                        final double n31 = this.Z0006 + 6.283185307179586 * (n29 - 0.25) / this.Z0088;
                        final double cos9 = Math.cos(n31);
                        final double sin9 = Math.sin(n31);
                        array[0].set(n28 * cos9, n28 * sin9, -n21);
                        array[1].set(n28 * cos9, n28 * sin9, n21);
                        array[2].set(this.Z0125 * cos9, this.Z0125 * sin9, n21);
                        array[3].set(this.Z0125 * cos9, this.Z0125 * sin9, -n21);
                    }
                    else {
                        final double n32 = this.Z0006 + 6.283185307179586 * (n29 + 0.25) / this.Z0088;
                        final double cos10 = Math.cos(n32);
                        final double sin10 = Math.sin(n32);
                        array[0].set(this.Z0125 * cos10, this.Z0125 * sin10, -n21);
                        array[1].set(this.Z0125 * cos10, this.Z0125 * sin10, n21);
                        array[2].set(n28 * cos10, n28 * sin10, n21);
                        array[3].set(n28 * cos10, n28 * sin10, -n21);
                    }
                    vector3d2.sub((Tuple3d)array[3], (Tuple3d)array[2]);
                    vector3d3.sub((Tuple3d)array[1], (Tuple3d)array[2]);
                    array2[0].cross(vector3d2, vector3d3);
                    array2[0].scale(1.0 / array2[0].length());
                    for (int n33 = 0; n33 < 4; ++n33) {
                        this.Z0056.setCoordinate(n4 + n33, array[n33]);
                        vector3f.set((Tuple3d)array2[0]);
                        this.Z0056.setNormal(n4 + n33, vector3f);
                    }
                    n4 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0056, this.Z0007));
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0055, this.Z0007));
            final double n = this.Z0127 - this.Z0068 - this.Z0124;
            Scene3d.this.Z0128[7].Z0126 = this.Z0124;
            Scene3d.this.Z0128[7].Z0075 = this.Z0074;
            for (int i = 0; i < this.Z0080; ++i) {
                final double n2 = this.Z0006 + 6.283185307179586 * i / this.Z0080 * ((this.Z0130 > 0.0) ? 1.0 : -1.0);
                Scene3d.this.Z0128[7].Z0110.set(0.5 * (this.Z0200 + this.Z0074), n * Math.cos(n2), n * Math.sin(n2));
                Scene3d.this.Z0128[7].Z0110.add((Tuple3d)this.Z0110);
                Scene3d.this.Z0128[7].Z0129();
                Scene3d.this.Z0128[7].draw(graphicsContext3D, transform3D);
            }
        }
    }
    
    public class Z0037
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private double Z0126;
        private double Z0075;
        private Color3f Z0030;
        private float Z0159;
        private QuadArray Z0056;
        private TriangleFanArray Z0055;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        private int Z0087;
        
        public Z0037(final Vector3d z0110, final double z111, final Vector3d z112, final double z113, final double z114, final int n) {
            this.Z0087 = 24;
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0126 = z113;
            this.Z0075 = z114;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0007 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0056 = new QuadArray(4 * this.Z0087, 3);
            this.Z0055 = new TriangleFanArray(2 * (this.Z0087 + 2), 3, new int[] { this.Z0087 + 2, this.Z0087 + 2 });
        }
        
        public void Z0129() {
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
            }
            int n = 0;
            double cos = 1.0;
            double sin = 0.0;
            for (int j = 0; j < this.Z0087; ++j) {
                final double n2 = cos;
                final double n3 = sin;
                final double n4 = 6.283185307179586 * (j + 1) / this.Z0087;
                cos = Math.cos(n4);
                sin = Math.sin(n4);
                array2[0].set(n2, n3, 0.0);
                array2[1].set(n2, n3, 0.0);
                array2[2].set(cos, sin, 0.0);
                array2[3].set(cos, sin, 0.0);
                for (int k = 0; k < 4; ++k) {
                    array[k].scale(this.Z0126, (Tuple3d)array2[k]);
                }
                array[0].z = 0.5 * this.Z0075;
                array[1].z = -0.5 * this.Z0075;
                array[2].z = -0.5 * this.Z0075;
                array[3].z = 0.5 * this.Z0075;
                for (int l = 0; l < 4; ++l) {
                    this.Z0056.setCoordinate(n + l, array[l]);
                    vector3f.set((Tuple3d)array2[l]);
                    this.Z0056.setNormal(n + l, vector3f);
                }
                n += 4;
            }
            int n5 = 0;
            for (int n6 = 0; n6 < 2; ++n6) {
                final double n7 = (n6 == 0) ? -1.0 : 1.0;
                array2[0].set(0.0, 0.0, n7);
                final double n8 = 0.5 * this.Z0075 * n7;
                array[0].set(0.0, 0.0, n8);
                this.Z0055.setCoordinate(n5, array[0]);
                vector3f.set((Tuple3d)array2[0]);
                this.Z0055.setNormal(n5, vector3f);
                ++n5;
                for (int n9 = 0; n9 < this.Z0087 + 1; ++n9) {
                    final double n10 = 6.283185307179586 * n9 * n7 / this.Z0087;
                    array[0].set(this.Z0126 * Math.cos(n10), this.Z0126 * Math.sin(n10), n8);
                    this.Z0055.setCoordinate(n5, array[0]);
                    vector3f.set((Tuple3d)array2[0]);
                    this.Z0055.setNormal(n5, vector3f);
                    ++n5;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0056, this.Z0007));
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0055, this.Z0007));
        }
    }
    
    public class Z0039
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private double Z0015;
        private double Z0175;
        private double Z0073;
        Color3f Z0030;
        private float Z0159;
        private QuadArray Z0056;
        private TriangleFanArray Z0055;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        private int Z0087;
        
        public Z0039(final Vector3d z0110, final double z111, final Vector3d z112, final double z113, final double z114, final double z115, final int n) {
            this.Z0087 = 24;
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0015 = z113;
            this.Z0175 = z114;
            this.Z0073 = z115;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0007 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0056 = new QuadArray(4 * this.Z0087, 3);
            this.Z0055 = new TriangleFanArray(this.Z0087 + 2, 3, new int[] { this.Z0087 + 2 });
        }
        
        public void Z0129() {
            final Point3d[] array = new Point3d[4];
            final Vector3d[] array2 = new Vector3d[4];
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
                array2[i] = new Vector3d();
            }
            final double sqrt = Math.sqrt((this.Z0015 - this.Z0175) * (this.Z0015 - this.Z0175) + this.Z0073 * this.Z0073);
            final double n = this.Z0073 / sqrt;
            final double n2 = (this.Z0015 - this.Z0175) / sqrt;
            int n3 = 0;
            double cos = 1.0;
            double sin = 0.0;
            for (int j = 0; j < this.Z0087; ++j) {
                final double n4 = cos;
                final double n5 = sin;
                final double n6 = 6.283185307179586 * (j + 1) / this.Z0087;
                cos = Math.cos(n6);
                sin = Math.sin(n6);
                array2[0].set(n4 * n, n5 * n, n2);
                array2[1].set(n4 * n, n5 * n, n2);
                array2[2].set(cos * n, sin * n, n2);
                array2[3].set(cos * n, sin * n, n2);
                array[0].set(this.Z0175 * n4, this.Z0175 * n5, this.Z0073);
                array[1].set(this.Z0015 * n4, this.Z0015 * n5, 0.0);
                array[2].set(this.Z0015 * cos, this.Z0015 * sin, 0.0);
                array[3].set(this.Z0175 * cos, this.Z0175 * sin, this.Z0073);
                for (int k = 0; k < 4; ++k) {
                    this.Z0056.setCoordinate(n3 + k, array[k]);
                    vector3f.set((Tuple3d)array2[k]);
                    this.Z0056.setNormal(n3 + k, vector3f);
                }
                n3 += 4;
            }
            int n7 = 0;
            array2[0].set(0.0, 0.0, 1.0);
            array[0].set(0.0, 0.0, this.Z0073);
            this.Z0055.setCoordinate(n7, array[0]);
            vector3f.set((Tuple3d)array2[0]);
            this.Z0055.setNormal(n7, vector3f);
            ++n7;
            for (int l = 0; l < this.Z0087 + 1; ++l) {
                final double n8 = 6.283185307179586 * l / this.Z0087;
                array[0].set(this.Z0175 * Math.cos(n8), this.Z0175 * Math.sin(n8), this.Z0073);
                this.Z0055.setCoordinate(n7, array[0]);
                vector3f.set((Tuple3d)array2[0]);
                this.Z0055.setNormal(n7, vector3f);
                ++n7;
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0056, this.Z0007));
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0055, this.Z0007));
        }
    }
    
    public class Z0034
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private double Z0127;
        private double Z0200;
        private int Z0092;
        private int Z0161;
        private int Z0103;
        private Color3f Z0030;
        private float Z0159;
        private double Z0130;
        private TriangleFanArray Z0055;
        private TriangleArray Z0057;
        private QuadArray Z0056;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        
        public Z0034(final Vector3d z0110, final double z111, final Vector3d z112, final double z113, final double z114, final int z115, final int z116, final int z117, final int n, final double z118) {
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0127 = z113;
            this.Z0200 = z114;
            this.Z0092 = z115;
            this.Z0161 = z116;
            this.Z0103 = z117;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0130 = z118;
            this.Z0007 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0055 = new TriangleFanArray(2 * (z115 + 2), 3, new int[] { z115 + 2, z115 + 2 });
            this.Z0057 = new TriangleArray(6 * z115, 3);
            this.Z0056 = new QuadArray(8 * z115, 3);
        }
        
        public void Z0129(final int n) {
            double n2;
            for (n2 = n * this.Z0130 + this.Z0103 * 3.141592653589793 / this.Z0092; n2 >= 6.283185307179586; n2 -= 6.283185307179586) {}
            while (n2 < 0.0) {
                n2 += 6.283185307179586;
            }
            final Point3d[] array = new Point3d[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3d();
            }
            final Vector3f vector3f = new Vector3f();
            final Vector3d vector3d = new Vector3d();
            final Vector3d vector3d2 = new Vector3d();
            final Vector3d vector3d3 = new Vector3d();
            final double n3 = this.Z0127 - 0.05;
            final double n4 = 0.5 * (1.5 * this.Z0127 * Math.sin(3.141592653589793 / this.Z0092));
            int n5 = 0;
            int n6 = 0;
            for (int j = -1; j <= 1; j += 2) {
                final double n7 = this.Z0200 * j * 0.5;
                final double n8 = n3 - 0.5 * this.Z0161 * this.Z0200 * j;
                vector3f.set(0.0f, 0.0f, (float)j);
                array[0].set(0.0, 0.0, n7);
                this.Z0055.setCoordinate(n5, array[0]);
                this.Z0055.setNormal(n5, vector3f);
                ++n5;
                for (int k = 0; k <= this.Z0092; ++k) {
                    final double n9 = n2 + j * 2.0 * 3.141592653589793 * k / this.Z0092;
                    array[0].set((n8 - n4) * Math.cos(n9), (n8 - n4) * Math.sin(n9), n7);
                    this.Z0055.setCoordinate(n5, array[0]);
                    this.Z0055.setNormal(n5, vector3f);
                    ++n5;
                }
                for (int l = 0; l < this.Z0092; ++l) {
                    final double n10 = n2 + j * 2.0 * 3.141592653589793 * l / this.Z0092;
                    array[0].set((n8 - n4) * Math.cos(n10), (n8 - n4) * Math.sin(n10), n7);
                    final double n11 = n2 + j * 2.0 * 3.141592653589793 * (l + 0.5) / this.Z0092;
                    array[1].set((n8 + n4) * Math.cos(n11), (n8 + n4) * Math.sin(n11), n7);
                    final double n12 = n2 + j * 2.0 * 3.141592653589793 * (l + 1) / this.Z0092;
                    array[2].set((n8 - n4) * Math.cos(n12), (n8 - n4) * Math.sin(n12), n7);
                    for (int n13 = 0; n13 < 3; ++n13) {
                        this.Z0057.setCoordinate(n6 + n13, array[n13]);
                        this.Z0057.setNormal(n6 + n13, vector3f);
                    }
                    n6 += 3;
                }
            }
            int n14 = 0;
            for (int n15 = 0; n15 < this.Z0092; ++n15) {
                for (int n16 = 0; n16 < 2; ++n16) {
                    if (n16 == 0) {
                        final double n17 = n2 + 6.283185307179586 * (n15 + 0.5) / this.Z0092;
                        final double cos = Math.cos(n17);
                        final double sin = Math.sin(n17);
                        final double n18 = n3 + n4 + 0.5 * this.Z0161 * this.Z0200;
                        array[0].set(n18 * cos, n18 * sin, -0.5 * this.Z0200);
                        final double n19 = n3 + n4 - 0.5 * this.Z0161 * this.Z0200;
                        array[1].set(n19 * cos, n19 * sin, 0.5 * this.Z0200);
                        final double n20 = n2 + 6.283185307179586 * n15 / this.Z0092;
                        final double cos2 = Math.cos(n20);
                        final double sin2 = Math.sin(n20);
                        final double n21 = n3 - n4 - 0.5 * this.Z0161 * this.Z0200;
                        array[2].set(n21 * cos2, n21 * sin2, 0.5 * this.Z0200);
                        final double n22 = n3 - n4 + 0.5 * this.Z0161 * this.Z0200;
                        array[3].set(n22 * cos2, n22 * sin2, -0.5 * this.Z0200);
                    }
                    else {
                        array[2].set((Tuple3d)array[1]);
                        array[3].set((Tuple3d)array[0]);
                        final double n23 = n2 + 6.283185307179586 * (n15 + 1) / this.Z0092;
                        final double cos3 = Math.cos(n23);
                        final double sin3 = Math.sin(n23);
                        final double n24 = n3 - n4 + 0.5 * this.Z0161 * this.Z0200;
                        array[0].set(n24 * cos3, n24 * sin3, -0.5 * this.Z0200);
                        final double n25 = n3 - n4 - 0.5 * this.Z0161 * this.Z0200;
                        array[1].set(n25 * cos3, n25 * sin3, 0.5 * this.Z0200);
                    }
                    vector3d2.sub((Tuple3d)array[3], (Tuple3d)array[2]);
                    vector3d3.sub((Tuple3d)array[1], (Tuple3d)array[2]);
                    vector3d.cross(vector3d2, vector3d3);
                    vector3d.scale(1.0 / vector3f.length());
                    vector3f.set((Tuple3d)vector3d);
                    for (int n26 = 0; n26 < 4; ++n26) {
                        this.Z0056.setCoordinate(n14 + n26, array[n26]);
                        this.Z0056.setNormal(n14 + n26, vector3f);
                    }
                    n14 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0056, this.Z0007));
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0057, this.Z0007));
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0055, this.Z0007));
        }
    }
    
    public class Z0033
    {
        private Vector3d Z0110;
        private double Z0004;
        private Vector3d Z0010;
        private Vector3d Z0160;
        private Color3f Z0030;
        private float Z0159;
        private QuadArray Z0054;
        private Transform3D Z0167;
        private Transform3D Z0169;
        private Transform3D Z0165;
        private Appearance Z0007;
        
        public Z0033(final Vector3d z0110, final double z111, final Vector3d z112, final Vector3d z113, final int n) {
            this.Z0110 = z0110;
            this.Z0004 = z111;
            this.Z0010 = z112;
            this.Z0160 = z113;
            this.Z0030 = Scene3d.this.Z0098[n];
            this.Z0159 = Scene3d.this.Z0101[n];
            this.Z0007 = new Appearance();
            this.Z0169 = new Transform3D();
            this.Z0167 = new Transform3D();
            this.Z0165 = new Transform3D();
            this.Z0054 = new QuadArray(24, 3);
        }
        
        public void Z0129() {
            final int[] array = { 0, 1, 3, 2, 4, 6, 7, 5, 0, 4, 5, 1, 2, 3, 7, 6, 0, 2, 6, 4, 1, 5, 7, 3 };
            final Point3d point3d = new Point3d();
            final Vector3f vector3f = new Vector3f();
            for (int i = 0; i < 6; ++i) {
                vector3f.set(0.0f, 0.0f, 0.0f);
                switch (i >> 1) {
                    case 0: {
                        vector3f.x = 2 * (i & 0x1) - 1;
                        break;
                    }
                    case 1: {
                        vector3f.y = 2 * (i & 0x1) - 1;
                        break;
                    }
                    case 2: {
                        vector3f.z = 2 * (i & 0x1) - 1;
                        break;
                    }
                }
                for (int j = 0; j < 4; ++j) {
                    this.Z0054.setNormal(4 * i + j, vector3f);
                    point3d.set(this.Z0160.x * ((array[4 * i + j] >> 2 & 0x1) - 0.5), this.Z0160.y * ((array[4 * i + j] >> 1 & 0x1) - 0.5), this.Z0160.z * ((array[4 * i + j] & 0x1) - 0.5));
                    this.Z0054.setCoordinate(4 * i + j, point3d);
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Transform3D transform3D) {
            this.Z0007.setMaterial(new Material(this.Z0030, Scene3d.this.black, this.Z0030, (this.Z0159 == 0.0f) ? Scene3d.this.black : Scene3d.this.white, this.Z0159));
            this.Z0169.set(this.Z0110);
            this.Z0167.set(new AxisAngle4d(this.Z0010, this.Z0004));
            this.Z0165.mul(this.Z0169, this.Z0167);
            this.Z0165.mul(transform3D, this.Z0165);
            graphicsContext3D.setModelTransform(this.Z0165);
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0054, this.Z0007));
        }
    }
    
    public class Z0035
    {
        float[] Z0066;
        
        public Z0035(final Scene3d scene3d) {
            this(scene3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0035(final Scene3d scene3d, final float[] array) {
            this(scene3d, array[0], array[1], array[2]);
        }
        
        public Z0035(final float n, final float n2, final float n3) {
            (this.Z0066 = new float[3])[0] = n;
            this.Z0066[1] = n2;
            this.Z0066[2] = n3;
        }
        
        Color3f Z0063() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0066[0] * 5.9999f);
            final float n2 = this.Z0066[0] * 6.0f - n;
            array[0] = this.Z0066[2] * (1.0f - this.Z0066[1]);
            array[1] = this.Z0066[2] * (1.0f - n2 * this.Z0066[1]);
            array[2] = this.Z0066[2] * (1.0f - (1.0f - n2) * this.Z0066[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0066[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0066[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0066[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0066[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0066[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0066[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0036 extends MouseAdapter implements MouseMotionListener
    {
        private Z0023 Z0119;
        private int Z0160;
        private int Z0046;
        private int Z0047;
        private int Z0112;
        private int Z0113;
        private int Z0077;
        private int Z0027;
        private int Z0028;
        
        public Z0036() {
            this.Z0119 = new Z0023();
            this.Z0160 = 256;
            this.Z0112 = 0;
            this.Z0113 = 0;
            this.Z0027 = -999;
            this.Z0028 = -999;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0111(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0111(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0111(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0111(final int n, final int n2, final int z0077, final int n3) {
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            switch (n3) {
                case 1: {
                    this.Z0077 = z0077;
                    this.Z0046 = n;
                    this.Z0047 = n2;
                    if (this.Z0077 == 1) {
                        this.Z0027 = n;
                        this.Z0028 = n2;
                        break;
                    }
                    break;
                }
                case 0: {
                    if (this.Z0077 != 1) {
                        this.Z0112 = n - this.Z0046;
                        this.Z0113 = n2 - this.Z0047;
                        this.Z0046 = n;
                        this.Z0047 = n2;
                        break;
                    }
                    array[0] = this.Z0046;
                    array[1] = this.Z0047;
                    array2[0] = n;
                    array2[1] = n2;
                    if (this.Z0176(array, array2)) {
                        this.Z0046 = n;
                        this.Z0047 = n2;
                        break;
                    }
                    break;
                }
            }
        }
        
        private boolean Z0176(final int[] array, final int[] array2) {
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final Z0023 z0023 = new Z0023();
            final Z0023 z24 = new Z0023();
            final double n = 0.8 * this.Z0160;
            array3[0] = (array[0] - this.Z0160 / 2) / n;
            array3[1] = (this.Z0160 / 2 - array[1]) / n;
            final double n2 = array3[0] * array3[0] + array3[1] * array3[1];
            if (n2 > 1.0) {
                return false;
            }
            array3[2] = Math.sqrt(1.0 - n2);
            array4[0] = (array2[0] - this.Z0160 / 2) / n;
            array4[1] = (this.Z0160 / 2 - array2[1]) / n;
            final double n3 = array4[0] * array4[0] + array4[1] * array4[1];
            if (n3 > 1.0) {
                return false;
            }
            array4[2] = Math.sqrt(1.0 - n3);
            this.Z0143(z0023, array3[1] * array4[2] - array3[2] * array4[1], array3[2] * array4[0] - array3[0] * array4[2], array3[0] * array4[1] - array3[1] * array4[0], array3[0] * array4[0] + array3[1] * array4[1] + array3[2] * array4[2]);
            this.Z0142(z24, z0023, this.Z0119);
            this.Z0141(this.Z0119, z24);
            return true;
        }
        
        private void Z0143(final Z0023 z0023, final double z24, final double z25, final double z26, final double z27) {
            z0023.Z0179 = z24;
            z0023.Z0180 = z25;
            z0023.Z0181 = z26;
            z0023.Z0182 = z27;
        }
        
        private void Z0141(final Z0023 z0023, final Z0023 z24) {
            z0023.Z0179 = z24.Z0179;
            z0023.Z0180 = z24.Z0180;
            z0023.Z0181 = z24.Z0181;
            z0023.Z0182 = z24.Z0182;
        }
        
        private void Z0142(final Z0023 z0023, final Z0023 z24, final Z0023 z25) {
            z0023.Z0179 = z24.Z0182 * z25.Z0179 - z24.Z0181 * z25.Z0180 + z24.Z0180 * z25.Z0181 + z24.Z0179 * z25.Z0182;
            z0023.Z0180 = z24.Z0181 * z25.Z0179 + z24.Z0182 * z25.Z0180 - z24.Z0179 * z25.Z0181 + z24.Z0180 * z25.Z0182;
            z0023.Z0181 = -z24.Z0180 * z25.Z0179 + z24.Z0179 * z25.Z0180 + z24.Z0182 * z25.Z0181 + z24.Z0181 * z25.Z0182;
            z0023.Z0182 = -z24.Z0179 * z25.Z0179 - z24.Z0180 * z25.Z0180 - z24.Z0181 * z25.Z0181 + z24.Z0182 * z25.Z0182;
        }
        
        public void Z0153(final int z0160) {
            this.Z0160 = z0160;
        }
        
        public void set(final double n, final double n2, final double n3) {
            final double n4 = 0.008726646259971648 * n2;
            final double n5 = 0.008726646259971648 * (n - n3);
            final double n6 = 0.008726646259971648 * (n + n3);
            final double sin = Math.sin(n4);
            final double cos = Math.cos(n4);
            this.Z0143(this.Z0119, sin * Math.cos(n5), sin * Math.sin(n5), cos * Math.sin(n6), cos * Math.cos(n6));
        }
        
        public Matrix3d get() {
            final double[] array = new double[10];
            final double[] array2 = { this.Z0119.Z0179, this.Z0119.Z0180, this.Z0119.Z0181, this.Z0119.Z0182 };
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
        
        public double Z0062() {
            final double n = this.Z0113 / this.Z0160;
            this.Z0113 = 0;
            return n;
        }
        
        public int Z0060() {
            final int z0027 = this.Z0027;
            this.Z0027 = -999;
            return z0027;
        }
        
        public int Z0061() {
            final int z0028 = this.Z0028;
            this.Z0028 = -999;
            return z0028;
        }
        
        class Z0023
        {
            double Z0179;
            double Z0180;
            double Z0181;
            double Z0182;
        }
    }
}
