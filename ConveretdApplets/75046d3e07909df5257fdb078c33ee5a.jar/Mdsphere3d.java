import javax.vecmath.Matrix3d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Tuple3f;
import javax.vecmath.Point3f;
import javax.media.j3d.QuadArray;
import java.text.NumberFormat;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.media.j3d.Fog;
import javax.media.j3d.LinearFog;
import javax.vecmath.Color3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import java.awt.event.ItemEvent;
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
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;
import java.util.Random;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import com.sun.j3d.utils.universe.SimpleUniverse;
import javax.media.j3d.GraphicsContext3D;
import javax.media.j3d.Canvas3D;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mdsphere3d extends JApplet implements Runnable, ActionListener, ChangeListener, ItemListener
{
    Thread Z0005;
    Canvas3D Z0017;
    GraphicsContext3D Z0054;
    SimpleUniverse Z0165;
    Z0031 Z0079;
    JButton[] Z0009;
    JSlider[] Z0161;
    JLabel[] Z0067;
    JCheckBox[] Z0020;
    Color Z0008;
    Random Z0113;
    Vector3d[] r;
    Vector3d[] Z0121;
    Vector3d[] Z0111;
    Vector3d Z0114;
    Vector3d Z0157;
    Point3i Z0019;
    Point3i Z0059;
    double Z0032;
    double Z0106;
    double Z0107;
    double Z0110;
    double Z0189;
    double Z0036;
    double Z0169;
    double Z0033;
    double Z0179;
    double Z0066;
    double Z0099;
    double Z0171;
    double Z0190;
    double Z0034;
    int[] Z0018;
    int[] Z0084;
    int Z0081;
    int Z0080;
    int Z0163;
    int Z0087;
    int Z0085;
    int Z0086;
    int Z0083;
    int Z0164;
    boolean Z0006;
    boolean Z0024;
    boolean Z0120;
    boolean init;
    
    public void init() {
        this.Z0152();
        this.Z0113 = new Random();
        this.Z0008 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0143(), "East");
        (this.Z0017 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0017, "Center");
        this.Z0165 = new SimpleUniverse(this.Z0017);
        this.Z0165.getViewingPlatform().setNominalViewingTransform();
        this.Z0054 = null;
        this.Z0079 = new Z0031();
        this.Z0017.addMouseListener((MouseListener)this.Z0079);
        this.Z0017.addMouseMotionListener((MouseMotionListener)this.Z0079);
        this.Z0005 = null;
    }
    
    JPanel Z0143() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0008), panel.getBorder()));
        this.Z0009 = new JButton[6];
        this.Z0067 = new JLabel[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0124(0, panel2, "Reset");
        this.Z0127(panel2, 10, 0);
        this.Z0124(1, panel2, "Start");
        panel.add(panel2);
        this.Z0127(panel, 0, 20);
        this.Z0126(0, panel, " ");
        this.Z0127(panel, 0, 5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0124(2, panel3, "<< T");
        this.Z0127(panel3, 10, 0);
        this.Z0124(3, panel3, "T >>");
        panel.add(panel3);
        this.Z0127(panel, 0, 20);
        this.Z0126(1, panel, " ");
        this.Z0127(panel, 0, 5);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        this.Z0124(4, panel4, "<< D");
        this.Z0127(panel4, 10, 0);
        this.Z0124(5, panel4, "D >>");
        panel.add(panel4);
        this.Z0127(panel, 0, 20);
        this.Z0020 = new JCheckBox[1];
        this.Z0125(0, panel, "Attract");
        this.Z0127(panel, 0, 20);
        this.Z0161 = new JSlider[2];
        this.Z0128(0, panel, this.Z0164, 1, 50, "Update");
        this.Z0128(1, panel, this.Z0081, 4, 8, "Size");
        return panel;
    }
    
    void Z0124(final int n, final JPanel panel, final String s) {
        (this.Z0009[n] = new JButton(s)).setPreferredSize(this.Z0009[n].getPreferredSize());
        this.Z0009[n].addActionListener(this);
        panel.add(this.Z0009[n]);
    }
    
    void Z0125(final int n, final JPanel panel, final String s) {
        (this.Z0020[n] = new JCheckBox(s)).addItemListener(this);
        this.Z0020[n].setAlignmentX(0.5f);
        panel.add(this.Z0020[n]);
    }
    
    void Z0126(final int n, final JPanel panel, final String s) {
        (this.Z0067[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0067[n]);
    }
    
    void Z0128(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0127(panel, 0, 3);
        (this.Z0161[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0161[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0161[n].setPreferredSize(preferredSize);
        this.Z0161[n].setMajorTickSpacing(n4 - n3);
        if (n4 - n3 > 10) {
            this.Z0161[n].setMinorTickSpacing((n4 - n3) / 5);
        }
        else {
            this.Z0161[n].setMinorTickSpacing(1);
        }
        this.Z0161[n].setPaintLabels(true);
        this.Z0161[n].setPaintTicks(true);
        this.Z0161[n].addChangeListener(this);
        panel.add(this.Z0161[n]);
        this.Z0127(panel, 0, 5);
    }
    
    void Z0127(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0161[0]) {
                this.Z0164 = value;
            }
            else if (slider == this.Z0161[1]) {
                this.Z0081 = value;
                this.Z0131();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0009[0]) {
            this.Z0140();
        }
        else if (source == this.Z0009[1]) {
            this.Z0120 = !this.Z0120;
            this.Z0009[1].setText(this.Z0120 ? "Stop" : "Start");
            this.Z0161[1].setEnabled(!this.Z0120);
            this.Z0020[0].setEnabled(!this.Z0120);
        }
        else if (source == this.Z0009[2] || source == this.Z0009[3]) {
            this.Z0134((source == this.Z0009[2]) ? 2 : 1);
            this.Z0067[0].setText("Temperature: " + this.Z0138(this.Z0169, 2));
        }
        else if (source == this.Z0009[4] || source == this.Z0009[5]) {
            this.Z0133((source == this.Z0009[4]) ? 2 : 1);
            this.Z0067[1].setText("Density: " + this.Z0138(this.Z0033, 2));
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.Z0020[0]) {
            this.Z0006 = (itemEvent.getStateChange() == 1);
            this.Z0131();
        }
    }
    
    public void start() {
        if (this.Z0005 == null) {
            (this.Z0005 = new Thread(this)).setPriority(1);
            this.Z0005.start();
        }
        if (this.Z0054 == null) {
            this.Z0054 = this.Z0017.getGraphicsContext3D();
            this.Z0139();
        }
        this.Z0079.Z0158(Math.min(this.Z0017.getSize().width, this.Z0017.getSize().height));
        this.Z0131();
    }
    
    public void stop() {
        this.Z0005 = null;
    }
    
    public void Z0035() {
        this.Z0165.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0005) {
            if (this.Z0120) {
                this.Z0144();
                if (this.Z0163 % this.Z0164 != 0) {
                    continue;
                }
                this.Z0151();
                if (!this.Z0153(10L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0054 != null && this.init) {
                    this.Z0151();
                }
                if (!this.Z0153(100L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0153(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0139() {
        this.Z0054.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0054.addLight((Light)directionalLight);
        this.Z0079.set(0.0, 0.0, 0.0);
        this.Z0017.getView().setFrontClipDistance(1.0);
        this.Z0017.getView().setBackClipDistance(100.0);
    }
    
    void Z0152() {
        this.Z0081 = 4;
        this.Z0164 = 20;
        this.Z0034 = 1.2;
        this.Z0032 = 0.005;
        this.Z0024 = true;
        this.Z0006 = false;
    }
    
    void Z0131() {
        this.Z0169 = 1.0;
        this.Z0033 = 0.8;
        this.Z0067[0].setText("Temperature: " + this.Z0138(this.Z0169, 2));
        this.Z0067[1].setText("Density: " + this.Z0138(this.Z0033, 2));
        this.Z0059 = new Point3i(this.Z0081, this.Z0081, this.Z0081);
        this.Z0080 = this.Z0059.x * this.Z0059.y * this.Z0059.z;
        this.Z0114 = new Vector3d(this.Z0059.x / Math.pow(this.Z0033, 0.3333333333333333), this.Z0059.y / Math.pow(this.Z0033, 0.3333333333333333), this.Z0059.z / Math.pow(this.Z0033, 0.3333333333333333));
        this.Z0110 = 0.4;
        this.Z0106 = Math.pow(2.0, 0.16666666666666666);
        if (this.Z0006) {
            this.Z0107 = 1.8;
            this.Z0085 = 30;
        }
        else {
            this.Z0107 = this.Z0106;
            this.Z0085 = 8;
        }
        this.Z0087 = this.Z0085 * this.Z0080;
        this.Z0019 = new Point3i((int)(this.Z0114.x / (this.Z0107 + this.Z0110)), (int)(this.Z0114.y / (this.Z0107 + this.Z0110)), (int)(this.Z0114.z / (this.Z0107 + this.Z0110)));
        this.Z0142();
        this.Z0140();
        this.Z0157 = new Vector3d(0.0, 0.0, -1.7 * this.Z0114.z);
        this.Z0120 = false;
        this.init = true;
    }
    
    void Z0142() {
        this.r = new Vector3d[this.Z0080];
        this.Z0121 = new Vector3d[this.Z0080];
        this.Z0111 = new Vector3d[this.Z0080];
        for (int i = 0; i < this.Z0080; ++i) {
            this.r[i] = new Vector3d();
            this.Z0121[i] = new Vector3d();
            this.Z0111[i] = new Vector3d();
        }
        this.Z0018 = new int[this.Z0080 + this.Z0019.x * this.Z0019.y * this.Z0019.z];
        this.Z0084 = new int[2 * this.Z0087];
    }
    
    void Z0144() {
        ++this.Z0163;
        this.Z0141(1);
        this.Z0130();
        if (this.Z0083 > 0) {
            this.Z0083 = 0;
            this.Z0036 = 0.0;
            this.Z0132();
        }
        this.Z0135();
        if (this.Z0024) {
            this.Z0129();
        }
        this.Z0141(2);
        this.Z0137();
        this.Z0036 += Math.sqrt(this.Z0189) * this.Z0032;
        if (this.Z0036 > this.Z0110 * 0.5) {
            this.Z0083 = 1;
        }
    }
    
    void Z0132() {
        final int[][] array = { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 }, { -1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 }, { 0, 1, 1 }, { -1, 1, 1 }, { -1, 0, 1 }, { -1, -1, 1 }, { 0, -1, 1 }, { 1, -1, 1 } };
        final Vector3d vector3d = new Vector3d(this.Z0019.x / this.Z0114.x, this.Z0019.y / this.Z0114.y, this.Z0019.z / this.Z0114.y);
        for (int i = this.Z0080; i < this.Z0080 + this.Z0019.x * this.Z0019.y * this.Z0019.z; ++i) {
            this.Z0018[i] = -1;
        }
        final Vector3d vector3d2 = new Vector3d();
        for (int j = 0; j < this.Z0080; ++j) {
            vector3d2.scaleAdd(0.5, (Tuple3d)this.Z0114, (Tuple3d)this.r[j]);
            final int n = ((int)(vector3d2.z * vector3d.z) * this.Z0019.y + (int)(vector3d2.y * vector3d.y)) * this.Z0019.x + (int)(vector3d2.x * vector3d.x) + this.Z0080;
            this.Z0018[j] = this.Z0018[n];
            this.Z0018[n] = j;
        }
        final Vector3d vector3d3 = new Vector3d();
        final double n2 = (this.Z0107 + this.Z0110) * (this.Z0107 + this.Z0110);
        this.Z0086 = 0;
        for (int k = 0; k < this.Z0019.z; ++k) {
            for (int l = 0; l < this.Z0019.y; ++l) {
                for (int n3 = 0; n3 < this.Z0019.x; ++n3) {
                    final int n4 = (k * this.Z0019.y + l) * this.Z0019.x + n3 + this.Z0080;
                    for (int n5 = 0; n5 < 14; ++n5) {
                        final int n6 = n3 + array[n5][0];
                        final int n7 = l + array[n5][1];
                        final int n8 = k + array[n5][2];
                        if (n6 >= 0 && n6 < this.Z0019.x && n7 >= 0 && n7 < this.Z0019.y) {
                            if (n8 < this.Z0019.z) {
                                final int n9 = (n8 * this.Z0019.y + n7) * this.Z0019.x + n6 + this.Z0080;
                                for (int n10 = this.Z0018[n4]; n10 >= 0; n10 = this.Z0018[n10]) {
                                    for (int n11 = this.Z0018[n9]; n11 >= 0; n11 = this.Z0018[n11]) {
                                        if (n4 != n9 || n11 < n10) {
                                            vector3d3.sub((Tuple3d)this.r[n10], (Tuple3d)this.r[n11]);
                                            if (vector3d3.lengthSquared() < n2) {
                                                this.Z0084[2 * this.Z0086] = n10;
                                                this.Z0084[2 * this.Z0086 + 1] = n11;
                                                ++this.Z0086;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void Z0135() {
        final double n = this.Z0107 * this.Z0107;
        for (int i = 0; i < this.Z0080; ++i) {
            this.Z0111[i].set(0.0, 0.0, 0.0);
        }
        this.Z0179 = 0.0;
        final Vector3d vector3d = new Vector3d();
        for (int j = 0; j < this.Z0086; ++j) {
            final int n2 = this.Z0084[2 * j];
            final int n3 = this.Z0084[2 * j + 1];
            vector3d.sub((Tuple3d)this.r[n2], (Tuple3d)this.r[n3]);
            final double lengthSquared = vector3d.lengthSquared();
            if (lengthSquared < n) {
                final double n4 = 1.0 / lengthSquared;
                final double n5 = n4 * n4 * n4;
                final double n6 = 48.0 * n5 * (n5 - 0.5) * n4;
                double n7 = 4.0 * n5 * (n5 - 1.0);
                if (!this.Z0006) {
                    ++n7;
                }
                this.Z0111[n2].scaleAdd(n6, (Tuple3d)vector3d, (Tuple3d)this.Z0111[n2]);
                this.Z0111[n3].scaleAdd(-n6, (Tuple3d)vector3d, (Tuple3d)this.Z0111[n3]);
                this.Z0179 += n7;
            }
        }
    }
    
    void Z0130() {
        for (int i = 0; i < this.Z0080; ++i) {
            if (Math.abs(this.r[i].x) > 0.5 * this.Z0114.x - 1.0E-4) {
                final double n = (this.r[i].x > 0.0) ? 1.0 : -1.0;
                this.r[i].x = n * (0.5 * this.Z0114.x - 1.0E-4);
                if (this.Z0121[i].x * n > 0.0) {
                    final Vector3d vector3d = this.Z0121[i];
                    vector3d.x *= -1.0;
                }
            }
            if (Math.abs(this.r[i].y) > 0.5 * this.Z0114.y - 1.0E-4) {
                final double n2 = (this.r[i].y > 0.0) ? 1.0 : -1.0;
                this.r[i].y = n2 * (0.5 * this.Z0114.y - 1.0E-4);
                if (this.Z0121[i].y * n2 > 0.0) {
                    final Vector3d vector3d2 = this.Z0121[i];
                    vector3d2.y *= -1.0;
                }
            }
            if (Math.abs(this.r[i].z) > 0.5 * this.Z0114.z - 1.0E-4) {
                final double n3 = (this.r[i].z > 0.0) ? 1.0 : -1.0;
                this.r[i].z = n3 * (0.5 * this.Z0114.z - 1.0E-4);
                if (this.Z0121[i].z * n3 > 0.0) {
                    final Vector3d vector3d3 = this.Z0121[i];
                    vector3d3.z *= -1.0;
                }
            }
        }
    }
    
    void Z0129() {
        double n = 0.0;
        double n2 = 0.0;
        final Vector3d vector3d = new Vector3d();
        for (int i = 0; i < this.Z0080; ++i) {
            vector3d.scaleAdd(0.5 * this.Z0032, (Tuple3d)this.Z0111[i], (Tuple3d)this.Z0121[i]);
            n += vector3d.dot(this.Z0111[i]);
            n2 += vector3d.lengthSquared();
        }
        final double n3 = -n / n2;
        for (int j = 0; j < this.Z0080; ++j) {
            vector3d.scaleAdd(0.5 * this.Z0032, (Tuple3d)this.Z0111[j], (Tuple3d)this.Z0121[j]);
            this.Z0111[j].scaleAdd(n3, (Tuple3d)vector3d, (Tuple3d)this.Z0111[j]);
        }
    }
    
    void Z0141(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0080; ++i) {
                this.Z0121[i].scaleAdd(0.5 * this.Z0032, (Tuple3d)this.Z0111[i], (Tuple3d)this.Z0121[i]);
                this.r[i].scaleAdd(this.Z0032, (Tuple3d)this.Z0121[i], (Tuple3d)this.r[i]);
            }
        }
        else {
            for (int j = 0; j < this.Z0080; ++j) {
                this.Z0121[j].scaleAdd(0.5 * this.Z0032, (Tuple3d)this.Z0111[j], (Tuple3d)this.Z0121[j]);
            }
        }
    }
    
    void Z0137() {
        this.Z0189 = 0.0;
        for (int i = 0; i < this.Z0080; ++i) {
            final double lengthSquared = this.Z0121[i].lengthSquared();
            if (lengthSquared > this.Z0189) {
                this.Z0189 = lengthSquared;
            }
        }
        this.Z0066 = 0.5 * this.Z0190 / this.Z0080;
        this.Z0099 = this.Z0179 / this.Z0080;
        this.Z0171 = this.Z0066 + this.Z0099;
    }
    
    void Z0140() {
        final Vector3d vector3d = new Vector3d(this.Z0114.x / this.Z0059.x, this.Z0114.y / this.Z0059.y, this.Z0114.z / this.Z0059.z);
        int n = 0;
        for (int i = 0; i < this.Z0059.z; ++i) {
            for (int j = 0; j < this.Z0059.y; ++j) {
                for (int k = 0; k < this.Z0059.x; ++k) {
                    this.r[n].set((k + 0.5) * vector3d.x, (j + 0.5) * vector3d.y, (i + 0.5) * vector3d.z);
                    this.r[n].scaleAdd(-0.5, (Tuple3d)this.Z0114, (Tuple3d)this.r[n]);
                    ++n;
                }
            }
        }
        final Vector3d vector3d2 = new Vector3d(0.0, 0.0, 0.0);
        final double sqrt = Math.sqrt(3.0 * this.Z0169);
        for (int l = 0; l < this.Z0080; ++l) {
            (this.Z0121[l] = this.Z0150()).scale(sqrt);
            vector3d2.add((Tuple3d)this.Z0121[l]);
        }
        for (int n2 = 0; n2 < this.Z0080; ++n2) {
            this.Z0121[n2].scaleAdd(-1.0 / this.Z0080, (Tuple3d)vector3d2, (Tuple3d)this.Z0121[n2]);
        }
        for (int n3 = 0; n3 < this.Z0080; ++n3) {
            this.Z0111[n3].set(0.0, 0.0, 0.0);
        }
        this.Z0083 = 1;
        this.Z0163 = 0;
    }
    
    void Z0134(final int n) {
        double n2;
        if (n == 1) {
            n2 = 1.02;
        }
        else {
            n2 = 0.98;
        }
        if ((n2 > 1.0 && this.Z0169 < 10.0) || (n2 < 1.0 && this.Z0169 > 0.02)) {
            this.Z0190 = 0.0;
            for (int i = 0; i < this.Z0080; ++i) {
                this.Z0121[i].scale(n2);
                this.Z0190 += this.Z0121[i].lengthSquared();
            }
            this.Z0169 = this.Z0190 / (3.0 * this.Z0080);
        }
    }
    
    void Z0133(final int n) {
        double n2;
        if (n == 1) {
            n2 = 0.98;
        }
        else {
            n2 = 1.02;
        }
        if ((n2 > 1.0 && this.Z0033 > 0.1) || (n2 < 1.0 && this.Z0033 < this.Z0034)) {
            if (n2 < 1.0) {
                for (int i = 0; i < this.Z0080; ++i) {
                    this.r[i].scale(n2);
                }
            }
            this.Z0033 /= n2 * n2;
            this.Z0114.scale(n2);
            this.Z0019.set((int)(this.Z0114.x / (this.Z0107 + this.Z0110)), (int)(this.Z0114.y / (this.Z0107 + this.Z0110)), (int)(this.Z0114.z / (this.Z0107 + this.Z0110)));
            this.Z0018 = new int[this.Z0080 + this.Z0019.x * this.Z0019.y * this.Z0019.z];
            this.Z0083 = 1;
        }
    }
    
    Vector3d Z0150() {
        double n;
        double n2;
        double n3;
        for (n = 2.0, n2 = 0.0, n3 = 0.0; n > 1.0; n = n2 * n2 + n3 * n3) {
            n2 = 2.0 * this.Z0113.nextDouble() - 1.0;
            n3 = 2.0 * this.Z0113.nextDouble() - 1.0;
        }
        final double n4 = 2.0 * Math.sqrt(1.0 - n);
        return new Vector3d(n4 * n2, n4 * n3, 1.0 - 2.0 * n);
    }
    
    void Z0151() {
        final Transform3D z0154 = this.Z0154();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        this.Z0054.setFog((Fog)new LinearFog(color3f, -this.Z0157.z, -this.Z0157.z + 2.5 * this.Z0114.z));
        final Appearance appearance = new Appearance();
        final Color3f z155 = new Z0030(0.1f, 0.2f, 0.5f).Z0055();
        appearance.setMaterial(new Material(z155, color3f, z155, new Z0030(0.1f, 0.2f, 1.0f).Z0055(), 60.0f));
        final Transform3D transform3D = new Transform3D();
        final Transform3D modelTransform = new Transform3D();
        final Z0028 z156 = new Z0028(0.48f, 16);
        for (int i = 0; i < this.Z0080; ++i) {
            transform3D.setTranslation(this.r[i]);
            modelTransform.mul(z0154, transform3D);
            this.Z0054.setModelTransform(modelTransform);
            z156.draw(this.Z0054, appearance);
        }
        final Appearance appearance2 = new Appearance();
        final Color3f z157 = new Z0030(0.5f, 0.3f, 0.5f).Z0055();
        appearance2.setMaterial(new Material(z157, color3f, z157, z157, 40.0f));
        this.Z0054.setModelTransform(z0154);
        new Z0029(this.Z0114.x + this.Z0106).draw(this.Z0054, appearance2);
        this.Z0136();
    }
    
    Transform3D Z0154() {
        this.Z0054.setBackground(new Background(new Z0030(0.6f, 1.0f, 0.2f).Z0055()));
        this.Z0054.clear();
        final Transform3D modelTransform = new Transform3D();
        modelTransform.set(this.Z0079.get());
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(this.Z0157);
        modelTransform.mul(transform3D, modelTransform);
        this.Z0054.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0136() {
        final J3DGraphics2D graphics2D = this.Z0017.getGraphics2D();
        graphics2D.setColor(this.Z0008);
        graphics2D.draw3DRect(0, 0, this.Z0017.getSize().width - 1, this.Z0017.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0017.swap();
    }
    
    String Z0138(final double n, final int n2) {
        final NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(n2);
        numberInstance.setMaximumFractionDigits(n2);
        return numberInstance.format(n);
    }
    
    public class Z0028
    {
        private QuadArray Z0048;
        
        public Z0028(final Mdsphere3d mdsphere3d) {
            this(mdsphere3d, 1.0f);
        }
        
        public Z0028(final Mdsphere3d mdsphere3d, final float n) {
            this(mdsphere3d, n, 32);
        }
        
        public Z0028(final float n, final int n2) {
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3f();
                array2[i] = new Vector3f();
            }
            this.Z0048 = new QuadArray(4 * n2 * n2, 3);
            int n3 = 0;
            float n4 = 0.0f;
            float n5 = -1.0f;
            for (int j = 0; j < n2; ++j) {
                final float n6 = n4;
                final float n7 = n5;
                final float n8 = (float)(3.141592653589793 * (j + 1) / n2 - 1.5707963267948966);
                n4 = (float)Math.cos(n8);
                n5 = (float)Math.sin(n8);
                float n9 = 1.0f;
                float n10 = 0.0f;
                for (int k = 0; k < n2; ++k) {
                    final float n11 = n9;
                    final float n12 = n10;
                    final float n13 = (float)(6.283185307179586 * (k + 1) / n2);
                    n9 = (float)Math.cos(n13);
                    n10 = (float)Math.sin(n13);
                    array2[0].set(n11 * n4, n12 * n4, n5);
                    array2[1].set(n11 * n6, n12 * n6, n7);
                    array2[2].set(n9 * n6, n10 * n6, n7);
                    array2[3].set(n9 * n4, n10 * n4, n5);
                    for (int l = 0; l < 4; ++l) {
                        array[l].scale(n, (Tuple3f)array2[l]);
                    }
                    this.Z0048.setCoordinates(n3, array);
                    this.Z0048.setNormals(n3, array2);
                    n3 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0048, appearance));
        }
    }
    
    public class Z0030
    {
        float[] Z0057;
        
        public Z0030(final Mdsphere3d mdsphere3d) {
            this(mdsphere3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0030(final Mdsphere3d mdsphere3d, final float[] array) {
            this(mdsphere3d, array[0], array[1], array[2]);
        }
        
        public Z0030(final float n, final float n2, final float n3) {
            (this.Z0057 = new float[3])[0] = n;
            this.Z0057[1] = n2;
            this.Z0057[2] = n3;
        }
        
        Color3f Z0055() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0057[0] * 5.9999f);
            final float n2 = this.Z0057[0] * 6.0f - n;
            array[0] = this.Z0057[2] * (1.0f - this.Z0057[1]);
            array[1] = this.Z0057[2] * (1.0f - n2 * this.Z0057[1]);
            array[2] = this.Z0057[2] * (1.0f - (1.0f - n2) * this.Z0057[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0057[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0057[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0057[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0057[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0057[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0057[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0029
    {
        private QuadArray Z0048;
        
        public Z0029(final Mdsphere3d mdsphere3d) {
            this(mdsphere3d, 1.0);
        }
        
        public Z0029(final Mdsphere3d mdsphere3d, final double n) {
            this(mdsphere3d, new Vector3f((float)n, (float)n, (float)n));
        }
        
        public Z0029(final Vector3f vector3f) {
            final int[] array = { 0, 2, 3, 1, 4, 5, 7, 6, 0, 1, 5, 4, 2, 6, 7, 3, 0, 4, 6, 2, 1, 3, 7, 5 };
            this.Z0048 = new QuadArray(24, 3);
            final Point3f[] array2 = new Point3f[4];
            final Vector3f vector3f2 = new Vector3f();
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Point3f();
            }
            int n = 0;
            for (int j = 0; j < 6; ++j) {
                for (int k = 0; k < 4; ++k) {
                    array2[k].set(vector3f.x * (float)((array[4 * j + k] >> 2 & 0x1) - 0.5), vector3f.y * (float)((array[4 * j + k] >> 1 & 0x1) - 0.5), vector3f.z * (float)((array[4 * j + k] & 0x1) - 0.5));
                }
                if (this.Z0051(array2)) {
                    vector3f2.set(0.0f, 0.0f, 0.0f);
                    switch (j >> 1) {
                        case 0: {
                            vector3f2.x = 1 - 2 * (j & 0x1);
                            break;
                        }
                        case 1: {
                            vector3f2.y = 1 - 2 * (j & 0x1);
                            break;
                        }
                        case 2: {
                            vector3f2.z = 1 - 2 * (j & 0x1);
                            break;
                        }
                    }
                    for (int l = 0; l < 4; ++l) {
                        this.Z0048.setCoordinate(n + l, array2[l]);
                        this.Z0048.setNormal(n + l, vector3f2);
                    }
                    n += 4;
                }
            }
        }
        
        private boolean Z0051(final Point3f[] array) {
            final Transform3D transform3D = new Transform3D();
            transform3D.set(Mdsphere3d.this.Z0079.get());
            final Transform3D transform3D2 = new Transform3D();
            transform3D2.setTranslation(Mdsphere3d.this.Z0157);
            transform3D.mul(transform3D2, transform3D);
            final Transform3D transform3D3 = new Transform3D();
            Mdsphere3d.this.Z0017.getVworldToImagePlate(transform3D3);
            final Transform3D transform3D4 = new Transform3D();
            transform3D4.mul(transform3D3, transform3D);
            final Point3d point3d = new Point3d();
            final Point3d point3d2 = new Point3d();
            final Point2d[] array2 = new Point2d[3];
            for (int i = 0; i < 3; ++i) {
                point3d.set((Tuple3f)array[i]);
                transform3D4.transform(point3d, point3d2);
                array2[i] = new Point2d();
                Mdsphere3d.this.Z0017.getPixelLocationFromImagePlate(point3d2, array2[i]);
            }
            return (array2[2].x - array2[1].x) * (array2[0].y - array2[1].y) - (array2[2].y - array2[1].y) * (array2[0].x - array2[1].x) < 0.0;
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0048, appearance));
        }
    }
    
    public class Z0031 extends MouseAdapter implements MouseMotionListener
    {
        private Z0014 Z0104;
        private int Z0160;
        private int Z0038;
        private int Z0039;
        
        public Z0031() {
            this.Z0104 = new Z0014();
            this.Z0160 = 256;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0100(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0100(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0100(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0100(final int n, final int n2, final int n3) {
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            switch (n3) {
                case 1: {
                    this.Z0038 = n;
                    this.Z0039 = n2;
                    break;
                }
                case 0: {
                    array[0] = this.Z0038;
                    array[1] = this.Z0039;
                    array2[0] = n;
                    array2[1] = n2;
                    if (this.Z0172(array, array2)) {
                        this.Z0038 = n;
                        this.Z0039 = n2;
                        break;
                    }
                    break;
                }
            }
        }
        
        private boolean Z0172(final int[] array, final int[] array2) {
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final Z0014 z0014 = new Z0014();
            final Z0014 z15 = new Z0014();
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
            this.Z0149(z0014, array3[1] * array4[2] - array3[2] * array4[1], array3[2] * array4[0] - array3[0] * array4[2], array3[0] * array4[1] - array3[1] * array4[0], array3[0] * array4[0] + array3[1] * array4[1] + array3[2] * array4[2]);
            this.Z0148(z15, z0014, this.Z0104);
            this.Z0147(this.Z0104, z15);
            return true;
        }
        
        private void Z0149(final Z0014 z0014, final double z15, final double z16, final double z17, final double z18) {
            z0014.Z0175 = z15;
            z0014.Z0176 = z16;
            z0014.Z0177 = z17;
            z0014.Z0178 = z18;
        }
        
        private void Z0147(final Z0014 z0014, final Z0014 z15) {
            z0014.Z0175 = z15.Z0175;
            z0014.Z0176 = z15.Z0176;
            z0014.Z0177 = z15.Z0177;
            z0014.Z0178 = z15.Z0178;
        }
        
        private void Z0148(final Z0014 z0014, final Z0014 z15, final Z0014 z16) {
            z0014.Z0175 = z15.Z0178 * z16.Z0175 - z15.Z0177 * z16.Z0176 + z15.Z0176 * z16.Z0177 + z15.Z0175 * z16.Z0178;
            z0014.Z0176 = z15.Z0177 * z16.Z0175 + z15.Z0178 * z16.Z0176 - z15.Z0175 * z16.Z0177 + z15.Z0176 * z16.Z0178;
            z0014.Z0177 = -z15.Z0176 * z16.Z0175 + z15.Z0175 * z16.Z0176 + z15.Z0178 * z16.Z0177 + z15.Z0177 * z16.Z0178;
            z0014.Z0178 = -z15.Z0175 * z16.Z0175 - z15.Z0176 * z16.Z0176 - z15.Z0177 * z16.Z0177 + z15.Z0178 * z16.Z0178;
        }
        
        public void Z0158(final int z0160) {
            this.Z0160 = z0160;
        }
        
        public void set(final double n, final double n2, final double n3) {
            final double n4 = 0.008726646259971648 * n2;
            final double n5 = 0.008726646259971648 * (n - n3);
            final double n6 = 0.008726646259971648 * (n + n3);
            final double sin = Math.sin(n4);
            final double cos = Math.cos(n4);
            this.Z0149(this.Z0104, sin * Math.cos(n5), sin * Math.sin(n5), cos * Math.sin(n6), cos * Math.cos(n6));
        }
        
        public Matrix3d get() {
            final double[] array = new double[10];
            final double[] array2 = { this.Z0104.Z0175, this.Z0104.Z0176, this.Z0104.Z0177, this.Z0104.Z0178 };
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
        
        class Z0014
        {
            double Z0175;
            double Z0176;
            double Z0177;
            double Z0178;
        }
    }
}
