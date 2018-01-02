import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.vecmath.Tuple3f;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.media.j3d.QuadArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.vecmath.Vector3d;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.vecmath.Color3f;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.util.Random;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Canvas3D;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Waves3d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0004;
    Canvas3D Z0008;
    GraphicsContext3D Z0032;
    SimpleUniverse Z0087;
    Z0015 Z0051;
    JButton[] Z0007;
    JSlider[] Z0081;
    JLabel[] Z0047;
    Color Z0006;
    Random Z0056;
    Z0013 Z0036;
    float[] z;
    float[] Z0102;
    float[] Z0101;
    float Z0058;
    float Z0095;
    int[] Z0084;
    int Z0052;
    int Z0037;
    int Z0083;
    int Z0086;
    int Z0085;
    int Z0049;
    boolean Z0060;
    
    public void init() {
        this.Z0072();
        this.Z0056 = new Random();
        this.Z0006 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0070(), "East");
        (this.Z0008 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0008, "Center");
        this.Z0087 = new SimpleUniverse(this.Z0008);
        this.Z0087.getViewingPlatform().setNominalViewingTransform();
        this.Z0032 = null;
        this.Z0051 = new Z0015();
        this.Z0008.addMouseListener((MouseListener)this.Z0051);
        this.Z0008.addMouseMotionListener((MouseMotionListener)this.Z0051);
        this.Z0004 = null;
        this.Z0036 = null;
    }
    
    JPanel Z0070() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0006), panel.getBorder()));
        this.Z0007 = new JButton[6];
        this.Z0047 = new JLabel[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0061(0, panel2, "Reset");
        this.Z0063(panel2, 10, 0);
        this.Z0061(1, panel2, "Start");
        panel.add(panel2);
        this.Z0063(panel, 0, 20);
        this.Z0062(0, panel, "Slits");
        this.Z0063(panel, 0, 5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0061(2, panel3, "<<");
        this.Z0063(panel3, 10, 0);
        this.Z0061(3, panel3, ">>");
        panel.add(panel3);
        this.Z0063(panel, 0, 20);
        this.Z0062(1, panel, "Width");
        this.Z0063(panel, 0, 5);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        this.Z0061(4, panel4, "<<");
        this.Z0063(panel4, 10, 0);
        this.Z0061(5, panel4, ">>");
        panel.add(panel4);
        this.Z0063(panel, 0, 30);
        this.Z0081 = new JSlider[2];
        this.Z0064(0, panel, this.Z0086, 1, 20, "Update");
        this.Z0064(1, panel, this.Z0037, 40, 100, "Grid");
        return panel;
    }
    
    void Z0061(final int n, final JPanel panel, final String s) {
        (this.Z0007[n] = new JButton(s)).setPreferredSize(this.Z0007[n].getPreferredSize());
        this.Z0007[n].addActionListener(this);
        panel.add(this.Z0007[n]);
    }
    
    void Z0062(final int n, final JPanel panel, final String s) {
        (this.Z0047[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0047[n]);
    }
    
    void Z0064(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0063(panel, 0, 2);
        (this.Z0081[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0081[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0081[n].setPreferredSize(preferredSize);
        this.Z0081[n].setMajorTickSpacing(n4 - n3);
        this.Z0081[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0081[n].setPaintLabels(true);
        this.Z0081[n].setPaintTicks(true);
        this.Z0081[n].addChangeListener(this);
        panel.add(this.Z0081[n]);
    }
    
    void Z0063(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0081[0]) {
                this.Z0086 = value;
            }
            else if (slider == this.Z0081[1]) {
                this.Z0037 = (value + 5) / 10 * 10 - 1;
                this.Z0065();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        boolean b = false;
        final Object source = actionEvent.getSource();
        if (source == this.Z0007[0]) {
            b = true;
        }
        else if (source == this.Z0007[1]) {
            this.Z0060 = !this.Z0060;
            this.Z0007[1].setText(this.Z0060 ? "Stop" : "Start");
            this.Z0081[1].setEnabled(!this.Z0060);
        }
        else if (source == this.Z0007[2] || source == this.Z0007[3]) {
            if (source == this.Z0007[2]) {
                if (--this.Z0052 < 1) {
                    this.Z0052 = 1;
                }
            }
            else {
                if (++this.Z0052 > this.Z0049) {
                    this.Z0052 = this.Z0049;
                }
                if ((this.Z0083 * 2 + 2) * this.Z0052 > this.Z0037 - 4) {
                    this.Z0052 = (this.Z0037 - 4) / (this.Z0083 * 2 + 2);
                }
            }
            b = true;
        }
        else if (source == this.Z0007[4] || source == this.Z0007[5]) {
            if (source == this.Z0007[4]) {
                if (--this.Z0083 < 1) {
                    this.Z0083 = 1;
                }
            }
            else {
                if (++this.Z0083 > 6) {
                    this.Z0083 = 6;
                }
                if ((this.Z0083 * 2 + 2) * this.Z0052 > this.Z0037 - 4) {
                    this.Z0083 = ((this.Z0037 - 4) / this.Z0052 - 2) / 2;
                }
            }
            b = true;
        }
        if (b) {
            this.Z0068();
            this.Z0036.fill(this.z, this.Z0058);
        }
    }
    
    public void start() {
        if (this.Z0004 == null) {
            (this.Z0004 = new Thread(this)).setPriority(1);
            this.Z0004.start();
        }
        if (this.Z0032 == null) {
            this.Z0032 = this.Z0008.getGraphicsContext3D();
            this.Z0067();
        }
        this.Z0051.Z0077(Math.min(this.Z0008.getSize().width, this.Z0008.getSize().height));
        this.Z0065();
    }
    
    public void stop() {
        this.Z0004 = null;
    }
    
    public void Z0018() {
        this.Z0087.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0004) {
            if (this.Z0060) {
                this.Z0073();
                if (this.Z0085 % this.Z0086 != 0) {
                    continue;
                }
                this.Z0036.fill(this.z, this.Z0058);
                this.Z0071();
                if (!this.Z0074(20L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0032 != null && this.Z0036 != null) {
                    this.Z0071();
                }
                if (!this.Z0074(100L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0074(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0067() {
        this.Z0032.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0032.addLight((Light)directionalLight);
        this.Z0051.Z0078(50.0f);
        this.Z0051.Z0076(-75.0f);
    }
    
    void Z0072() {
        this.Z0037 = 61;
        this.Z0052 = 2;
        this.Z0058 = 0.6f;
        this.Z0083 = 2;
        this.Z0086 = 4;
        this.Z0049 = 5;
    }
    
    void Z0065() {
        this.Z0069();
        this.Z0068();
        this.Z0036.fill(this.z, this.Z0058);
        this.Z0060 = false;
    }
    
    void Z0069() {
        this.z = new float[this.Z0037 * this.Z0037];
        this.Z0102 = new float[this.Z0037 * this.Z0037];
        this.Z0101 = new float[this.Z0037 * this.Z0037];
        this.Z0084 = new int[this.Z0049];
        this.Z0036 = new Z0013(this.Z0037);
    }
    
    void Z0073() {
        ++this.Z0085;
        final float n = 0.1f;
        final float n2 = 0.2f;
        for (int i = 0; i < this.Z0037 * this.Z0037; ++i) {
            this.Z0101[i] = 0.0f;
        }
        for (int n3 = 0, j = 0; j < this.Z0037; ++j, ++n3) {
            for (int k = 0; k < this.Z0037 - 1; ++k, ++n3) {
                final float n4 = this.z[n3 + 1] - this.z[n3];
                final float[] z0101 = this.Z0101;
                final int n5 = n3;
                z0101[n5] += n4;
                final float[] z102 = this.Z0101;
                final int n6 = n3 + 1;
                z102[n6] -= n4;
            }
        }
        int n7 = 0;
        for (int l = 0; l < this.Z0037 - 1; ++l) {
            for (int n8 = 0; n8 < this.Z0037; ++n8, ++n7) {
                final float n9 = this.z[n7 + this.Z0037] - this.z[n7];
                final float[] z103 = this.Z0101;
                final int n10 = n7;
                z103[n10] += n9;
                final float[] z104 = this.Z0101;
                final int n11 = n7 + this.Z0037;
                z104[n11] -= n9;
            }
        }
        final float n12 = (float)(this.Z0095 * Math.cos(this.Z0085 * n * 2.0 * 3.141592653589793 * Math.sqrt(n2 / this.Z0037)));
        int n13 = 0;
        for (int n14 = 0; n14 < this.Z0037; ++n14) {
            for (int n15 = 0; n15 < this.Z0037; ++n15, ++n13) {
                if (n15 == 0) {
                    this.Z0102[n13] = n12;
                }
                else {
                    final float[] z105 = this.Z0101;
                    final int n16 = n13;
                    z105[n16] *= n2;
                    final float[] z106 = this.Z0102;
                    final int n17 = n13;
                    z106[n17] += this.Z0101[n13] * n;
                }
                final float[] z107 = this.z;
                final int n18 = n13;
                z107[n18] += this.Z0102[n13] * n;
                if (n15 == 0) {
                    int n19;
                    for (n19 = 0; n19 < this.Z0052 && Math.abs(n14 - this.Z0084[n19]) > this.Z0083; ++n19) {}
                    if (n19 == this.Z0052) {
                        this.z[n13] = 0.0f;
                    }
                }
            }
        }
    }
    
    void Z0068() {
        this.Z0095 = 0.05f;
        int n = 0;
        for (int i = 0; i < this.Z0037; ++i) {
            for (int j = 0; j < this.Z0037; ++j, ++n) {
                this.z[n] = (float)(1.0E-6 * this.Z0056.nextDouble());
                this.Z0102[n] = ((j == 0) ? this.Z0095 : 0.0f);
            }
        }
        for (int k = 0; k < this.Z0052; ++k) {
            this.Z0084[k] = (2 * k + 2) * this.Z0037 / (2 * this.Z0052 + 2);
        }
        this.Z0085 = 0;
    }
    
    void Z0071() {
        final Transform3D z0075 = this.Z0075();
        final Transform3D transform3D = new Transform3D();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        final Appearance appearance = new Appearance();
        final Color3f z76 = new Z0014(0.5f, 0.7f, 0.4f).Z0035();
        appearance.setMaterial(new Material(z76, color3f, z76, new Z0014(0.5f, 0.3f, 1.0f).Z0035(), 60.0f));
        appearance.setPolygonAttributes(new PolygonAttributes(2, 0, 0.0f));
        this.Z0036.draw(this.Z0032, appearance);
        final Appearance appearance2 = new Appearance();
        final Color3f z77 = new Z0014(0.1f, 0.8f, 0.6f).Z0035();
        appearance2.setMaterial(new Material(z77, color3f, z77, color3f2, 20.0f));
        appearance2.setTransparencyAttributes(new TransparencyAttributes(2, 0.3f));
        appearance2.setPolygonAttributes(new PolygonAttributes(2, 0, 0.0f));
        final Transform3D transform3D2 = new Transform3D();
        transform3D2.rotY(1.5707963267948966);
        for (int i = 0; i <= this.Z0052; ++i) {
            int n;
            if (i > 0) {
                n = this.Z0084[i - 1] + this.Z0083;
            }
            else {
                n = 0;
            }
            int n2;
            if (i < this.Z0052) {
                n2 = this.Z0084[i] - this.Z0083;
            }
            else {
                n2 = this.Z0037 - 1;
            }
            final Z0016 z78 = new Z0016(0.4f * this.Z0058, (n2 - n) / (this.Z0037 - 1));
            final Transform3D modelTransform = new Transform3D();
            modelTransform.setTranslation(new Vector3d(-0.5, (double)(float)(0.5 * (n + n2) / (this.Z0037 - 1) - 0.5), 0.0));
            modelTransform.mul(z0075, modelTransform);
            modelTransform.mul(modelTransform, transform3D2);
            this.Z0032.setModelTransform(modelTransform);
            z78.draw(this.Z0032, appearance2);
        }
        this.Z0066();
    }
    
    Transform3D Z0075() {
        this.Z0032.setBackground(new Background(new Z0014(0.6f, 1.0f, 0.2f).Z0035()));
        this.Z0032.clear();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(this.Z0051.Z0033());
        final Transform3D transform3D2 = new Transform3D();
        transform3D2.rotX(this.Z0051.Z0034());
        final Transform3D modelTransform = new Transform3D();
        modelTransform.mul(transform3D2, transform3D);
        final Transform3D transform3D3 = new Transform3D();
        transform3D3.rotX(-1.5707963267948966);
        modelTransform.mul(transform3D3);
        final Transform3D transform3D4 = new Transform3D();
        transform3D4.setScale(1.4);
        modelTransform.mul(transform3D4, modelTransform);
        this.Z0032.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0066() {
        final J3DGraphics2D graphics2D = this.Z0008.getGraphics2D();
        graphics2D.setColor(this.Z0006);
        graphics2D.draw3DRect(0, 0, this.Z0008.getSize().width - 1, this.Z0008.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0008.swap();
    }
    
    public class Z0014
    {
        float[] Z0040;
        
        public Z0014(final Waves3d waves3d) {
            this(waves3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0014(final Waves3d waves3d, final float[] array) {
            this(waves3d, array[0], array[1], array[2]);
        }
        
        public Z0014(final float n, final float n2, final float n3) {
            (this.Z0040 = new float[3])[0] = n;
            this.Z0040[1] = n2;
            this.Z0040[2] = n3;
        }
        
        Color3f Z0035() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0040[0] * 5.9999f);
            final float n2 = this.Z0040[0] * 6.0f - n;
            array[0] = this.Z0040[2] * (1.0f - this.Z0040[1]);
            array[1] = this.Z0040[2] * (1.0f - n2 * this.Z0040[1]);
            array[2] = this.Z0040[2] * (1.0f - (1.0f - n2) * this.Z0040[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0040[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0040[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0040[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0040[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0040[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0040[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0016
    {
        private QuadArray Z0028;
        
        public Z0016(final Waves3d waves3d) {
            this(waves3d, 1.0f);
        }
        
        public Z0016(final Waves3d waves3d, final float n) {
            this(waves3d, n, n);
        }
        
        public Z0016(final float n, final float n2) {
            this.Z0028 = new QuadArray(4, 3);
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            array[0] = new Point3f(-0.5f * n, -0.5f * n2, 0.0f);
            array[1] = new Point3f(0.5f * n, -0.5f * n2, 0.0f);
            array[2] = new Point3f(0.5f * n, 0.5f * n2, 0.0f);
            array[3] = new Point3f(-0.5f * n, 0.5f * n2, 0.0f);
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Vector3f(0.0f, 0.0f, 1.0f);
            }
            this.Z0028.setCoordinates(0, array);
            this.Z0028.setNormals(0, array2);
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0028, appearance));
        }
    }
    
    public class Z0013
    {
        private TriangleStripArray Z0028;
        private Point3f[] Z0031;
        private Vector3f[] Z0030;
        private int[] Z0096;
        private int Z0038;
        
        Z0013(final int z0038) {
            this.Z0038 = z0038;
            this.Z0031 = new Point3f[z0038 * z0038];
            this.Z0030 = new Vector3f[z0038 * z0038];
            for (int i = 0; i < z0038 * z0038; ++i) {
                this.Z0031[i] = new Point3f();
                this.Z0030[i] = new Vector3f();
            }
            this.Z0096 = new int[z0038 - 1];
            for (int j = 0; j < z0038 - 1; ++j) {
                this.Z0096[j] = 2 * z0038;
            }
            this.Z0028 = new TriangleStripArray(2 * z0038 * (z0038 - 1), 3, this.Z0096);
        }
        
        public void fill(final float[] array, final float n) {
            final float n2 = 1.0f / (this.Z0038 - 1);
            for (int i = 0; i < this.Z0038; ++i) {
                for (int j = 0; j < this.Z0038; ++j) {
                    final int n3 = i * this.Z0038 + j;
                    this.Z0031[n3].set(j * n2 - 0.5f, i * n2 - 0.5f, array[n3] * n);
                }
            }
            final int[] array2 = new int[4];
            final Vector3f[] array3 = new Vector3f[3];
            for (int k = 0; k < 3; ++k) {
                array3[k] = new Vector3f();
            }
            for (int l = 0; l < this.Z0038 * this.Z0038; ++l) {
                this.Z0030[l].set(0.0f, 0.0f, 0.0f);
            }
            for (int n4 = 0; n4 < this.Z0038 - 1; ++n4) {
                for (int n5 = 0; n5 < this.Z0038 - 1; ++n5) {
                    array2[0] = n4 * this.Z0038 + n5;
                    array2[1] = array2[0] + 1;
                    array2[2] = array2[1] + this.Z0038;
                    array2[3] = array2[2] - 1;
                    for (int n6 = 0; n6 < 4; ++n6) {
                        array3[0].sub((Tuple3f)this.Z0031[array2[(n6 + 1) % 4]], (Tuple3f)this.Z0031[array2[n6]]);
                        array3[1].sub((Tuple3f)this.Z0031[array2[(n6 + 3) % 4]], (Tuple3f)this.Z0031[array2[n6]]);
                        array3[2].cross(array3[0], array3[1]);
                        this.Z0030[array2[n6]].add((Tuple3f)array3[2]);
                    }
                }
            }
            for (int n7 = 0; n7 < this.Z0038 * this.Z0038; ++n7) {
                this.Z0030[n7].normalize();
            }
            int n8 = 0;
            for (int n9 = 0; n9 < this.Z0038 - 1; ++n9) {
                for (int n10 = 0; n10 < this.Z0038; ++n10) {
                    final int n11 = n9 * this.Z0038 + n10;
                    this.Z0028.setCoordinate(n8, this.Z0031[n11 + this.Z0038]);
                    this.Z0028.setNormal(n8, this.Z0030[n11 + this.Z0038]);
                    ++n8;
                    this.Z0028.setCoordinate(n8, this.Z0031[n11]);
                    this.Z0028.setNormal(n8, this.Z0030[n11]);
                    ++n8;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0028, appearance));
        }
    }
    
    public class Z0015 extends MouseAdapter implements MouseMotionListener
    {
        private double Z0002;
        private double Z0001;
        private int Z0080;
        private int Z0019;
        private int Z0020;
        
        public Z0015() {
            this.Z0002 = 10.0;
            this.Z0001 = 10.0;
            this.Z0080 = 256;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0055(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0055(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0055(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0055(final int n, final int n2, final int n3) {
            switch (n3) {
                case 1: {
                    this.Z0019 = n;
                    this.Z0020 = n2;
                    break;
                }
                case 0: {
                    this.Z0001 += 80.0 * (n - this.Z0019) / this.Z0080;
                    if (this.Z0001 < -180.0) {
                        this.Z0001 += 360.0;
                    }
                    else if (this.Z0001 >= 180.0) {
                        this.Z0001 -= 360.0;
                    }
                    this.Z0002 += 60.0 * (n2 - this.Z0020) / this.Z0080;
                    this.Z0019 = n;
                    this.Z0020 = n2;
                    if (this.Z0002 > 80.0) {
                        this.Z0002 = 80.0;
                        break;
                    }
                    if (this.Z0002 < 5.0) {
                        this.Z0002 = 5.0;
                        break;
                    }
                    break;
                }
            }
        }
        
        public void Z0077(final int z0080) {
            this.Z0080 = z0080;
        }
        
        public double Z0034() {
            return this.Z0002 * 3.141592653589793 / 180.0;
        }
        
        public double Z0033() {
            return this.Z0001 * 3.141592653589793 / 180.0;
        }
        
        public void Z0078(final float n) {
            this.Z0002 = n;
        }
        
        public void Z0076(final float n) {
            this.Z0001 = n;
        }
    }
}
