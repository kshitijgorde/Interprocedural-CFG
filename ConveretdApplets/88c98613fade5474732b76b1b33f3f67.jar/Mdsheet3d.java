import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.vecmath.Point2d;
import javax.vecmath.Point3d;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Tuple3f;
import javax.vecmath.Point3f;
import javax.media.j3d.TriangleArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.media.j3d.Fog;
import javax.media.j3d.LinearFog;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.vecmath.Vector3d;
import java.util.Random;
import java.awt.Color;
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

public class Mdsheet3d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0005;
    Canvas3D Z0015;
    GraphicsContext3D Z0062;
    SimpleUniverse Z0187;
    Z0030 Z0092;
    JButton[] Z0011;
    JSlider[] Z0183;
    Color Z0007;
    Random Z0132;
    Z0029 Z0068;
    Vector3d Z0179;
    double[] Z0145;
    double[] Z0146;
    double[] Z0147;
    double[] Z0142;
    double[] Z0143;
    double[] Z0144;
    double[] Z0133;
    double[] Z0134;
    double[] Z0135;
    double[] Z0008;
    double Z0065;
    double Z0066;
    double Z0067;
    double Z0031;
    double Z0056;
    double Z0010;
    double Z0058;
    double Z0064;
    double Z0122;
    double Z0129;
    double Z0207;
    double Z0033;
    double Z0115;
    double Z0074;
    final double P1 = 0.5257311;
    final double P2 = 0.8506508;
    double[][] Z0112;
    int[] Z0020;
    int[] Z0100;
    int[] Z0009;
    int[] Z0047;
    int[] Z0051;
    int Z0019;
    int Z0093;
    int Z0096;
    int Z0094;
    int Z0185;
    int Z0103;
    int Z0101;
    int Z0102;
    int Z0099;
    int Z0186;
    int Z0073;
    int Z0095;
    int Z0069;
    int[] Z0050;
    boolean[] Z0006;
    boolean Z0141;
    
    public Mdsheet3d() {
        this.Z0112 = new double[][] { { -0.5257311, 0.0, 0.8506508 }, { 0.5257311, 0.0, 0.8506508 }, { -0.5257311, 0.0, -0.8506508 }, { 0.5257311, 0.0, -0.8506508 }, { 0.0, 0.8506508, 0.5257311 }, { 0.0, 0.8506508, -0.5257311 }, { 0.0, -0.8506508, 0.5257311 }, { 0.0, -0.8506508, -0.5257311 }, { 0.8506508, 0.5257311, 0.0 }, { -0.8506508, 0.5257311, 0.0 }, { 0.8506508, -0.5257311, 0.0 }, { -0.8506508, -0.5257311, 0.0 } };
        this.Z0050 = new int[] { 0, 1, 4, 0, 4, 9, 0, 9, 11, 0, 11, 6, 0, 6, 1, 4, 5, 9, 4, 8, 5, 1, 8, 4, 3, 5, 8, 2, 5, 3, 2, 3, 7, 6, 11, 7, 2, 11, 9, 2, 9, 5, 2, 7, 11, 1, 10, 8, 3, 8, 10, 3, 10, 7, 6, 7, 10, 1, 6, 10 };
    }
    
    public void init() {
        this.Z0176();
        this.Z0132 = new Random();
        this.Z0007 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0169(), "East");
        (this.Z0015 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0015, "Center");
        this.Z0187 = new SimpleUniverse(this.Z0015);
        this.Z0187.getViewingPlatform().setNominalViewingTransform();
        this.Z0062 = null;
        this.Z0092 = new Z0030();
        this.Z0015.addMouseListener((MouseListener)this.Z0092);
        this.Z0015.addMouseMotionListener((MouseMotionListener)this.Z0092);
        this.Z0005 = null;
        this.Z0068 = null;
    }
    
    JPanel Z0169() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0007), panel.getBorder()));
        this.Z0011 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0150(0, panel2, "Reset");
        this.Z0151(panel2, 10, 0);
        this.Z0150(1, panel2, "Start");
        panel.add(panel2);
        this.Z0151(panel, 0, 20);
        this.Z0183 = new JSlider[3];
        this.Z0152(0, panel, (int)(100.0 * this.Z0064), 0, 100, "Gravity");
        this.Z0152(1, panel, (int)this.Z0115, 0, 500, "Pressure");
        this.Z0152(2, panel, this.Z0186, 1, 50, "Update");
        return panel;
    }
    
    void Z0150(final int n, final JPanel panel, final String s) {
        (this.Z0011[n] = new JButton(s)).setPreferredSize(this.Z0011[n].getPreferredSize());
        this.Z0011[n].addActionListener(this);
        panel.add(this.Z0011[n]);
    }
    
    void Z0152(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0151(panel, 0, 3);
        (this.Z0183[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0183[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0183[n].setPreferredSize(preferredSize);
        this.Z0183[n].setMajorTickSpacing(n4 - n3);
        this.Z0183[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0183[n].setPaintLabels(true);
        this.Z0183[n].setPaintTicks(true);
        this.Z0183[n].addChangeListener(this);
        panel.add(this.Z0183[n]);
        this.Z0151(panel, 0, 5);
    }
    
    void Z0151(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0183[0]) {
                this.Z0064 = 0.01 * value;
            }
            else if (slider == this.Z0183[1]) {
                this.Z0115 = value;
            }
            else if (slider == this.Z0183[2]) {
                this.Z0186 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0011[0]) {
            this.Z0164();
            this.Z0068.fill(this.Z0145, this.Z0146, this.Z0147, this.Z0051);
        }
        else if (source == this.Z0011[1]) {
            this.Z0141 = !this.Z0141;
            this.Z0011[1].setText(this.Z0141 ? "Stop" : "Start");
        }
    }
    
    public void start() {
        if (this.Z0005 == null) {
            (this.Z0005 = new Thread(this)).setPriority(1);
            this.Z0005.start();
        }
        if (this.Z0062 == null) {
            this.Z0062 = this.Z0015.getGraphicsContext3D();
            this.Z0163();
        }
        this.Z0092.Z0180(Math.min(this.Z0015.getSize().width, this.Z0015.getSize().height));
        this.Z0154();
    }
    
    public void stop() {
        this.Z0005 = null;
    }
    
    public void Z0032() {
        this.Z0187.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0005) {
            if (this.Z0141) {
                this.Z0170();
                if (this.Z0185 % this.Z0186 != 0) {
                    continue;
                }
                this.Z0068.fill(this.Z0145, this.Z0146, this.Z0147, this.Z0051);
                this.Z0175();
                if (!this.Z0177(10L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0062 != null && this.Z0068 != null) {
                    this.Z0175();
                }
                if (!this.Z0177(100L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0177(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0163() {
        this.Z0062.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0062.addLight((Light)directionalLight);
        this.Z0092.set(0.0, 0.0, 0.0);
        this.Z0015.getView().setFrontClipDistance(1.0);
        this.Z0015.getView().setBackClipDistance(100.0);
    }
    
    void Z0176() {
        this.Z0019 = 10;
        this.Z0129 = 0.4;
        this.Z0122 = Math.pow(2.0, 0.16666666666666666);
        this.Z0031 = 0.005;
        this.Z0056 = 100.0;
        this.Z0010 = 16.0;
        this.Z0101 = 6;
        this.Z0073 = 4;
        this.Z0115 = 50.0;
        this.Z0058 = 0.5;
        this.Z0064 = 0.4;
        this.Z0186 = 20;
    }
    
    void Z0154() {
        this.Z0093 = 2 + 10 * this.Z0073 * this.Z0073;
        this.Z0095 = 20 * this.Z0073 * this.Z0073;
        this.Z0094 = 30 * this.Z0073 * this.Z0073;
        this.Z0103 = this.Z0101 * this.Z0093;
        this.Z0096 = this.Z0093 * (this.Z0093 - 1) / 2;
        this.Z0168();
        this.Z0165();
        this.Z0164();
        this.Z0179 = new Vector3d(0.0, 0.0, -1.7 * this.Z0010);
        this.Z0068.fill(this.Z0145, this.Z0146, this.Z0147, this.Z0051);
    }
    
    void Z0168() {
        this.Z0145 = new double[this.Z0093];
        this.Z0146 = new double[this.Z0093];
        this.Z0147 = new double[this.Z0093];
        this.Z0142 = new double[this.Z0093];
        this.Z0143 = new double[this.Z0093];
        this.Z0144 = new double[this.Z0093];
        this.Z0133 = new double[this.Z0093];
        this.Z0134 = new double[this.Z0093];
        this.Z0135 = new double[this.Z0093];
        this.Z0020 = new int[this.Z0093 + this.Z0019 * this.Z0019 * this.Z0019];
        this.Z0100 = new int[2 * this.Z0103];
        this.Z0006 = new boolean[this.Z0096];
        this.Z0009 = new int[2 * this.Z0094];
        this.Z0008 = new double[this.Z0094];
        this.Z0051 = new int[3 * this.Z0095];
        this.Z0047 = new int[60];
        this.Z0068 = new Z0029(this.Z0093);
    }
    
    void Z0170() {
        ++this.Z0185;
        this.Z0167(1);
        this.Z0153();
        if (this.Z0099 > 0) {
            this.Z0099 = 0;
            this.Z0033 = 0.0;
            this.Z0155();
        }
        final Matrix3d value = this.Z0092.get();
        this.Z0065 = -this.Z0064 * value.getElement(1, 0);
        this.Z0066 = -this.Z0064 * value.getElement(1, 1);
        this.Z0067 = -this.Z0064 * value.getElement(1, 2);
        this.Z0157();
        this.Z0156();
        this.Z0158();
        this.Z0167(2);
        this.Z0160();
        this.Z0033 += Math.sqrt(this.Z0207) * this.Z0031;
        if (this.Z0033 > this.Z0129 * 0.5) {
            this.Z0099 = 1;
        }
    }
    
    void Z0155() {
        final int[][] array = { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 1, 0 }, { 0, 1, 0 }, { -1, 1, 0 }, { 0, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 }, { 0, 1, 1 }, { -1, 1, 1 }, { -1, 0, 1 }, { -1, -1, 1 }, { 0, -1, 1 }, { 1, -1, 1 } };
        int z0019 = (int)(this.Z0010 / (this.Z0122 + this.Z0129));
        if (z0019 > this.Z0019) {
            z0019 = this.Z0019;
        }
        final double n = z0019 / this.Z0010;
        final double n2 = (this.Z0122 + this.Z0129) * (this.Z0122 + this.Z0129);
        for (int i = this.Z0093; i < this.Z0093 + z0019 * z0019 * z0019; ++i) {
            this.Z0020[i] = -1;
        }
        for (int j = 0; j < this.Z0093; ++j) {
            final int n3 = ((int)((this.Z0147[j] + 0.5 * this.Z0010) * n) * z0019 + (int)((this.Z0146[j] + 0.5 * this.Z0010) * n)) * z0019 + (int)((this.Z0145[j] + 0.5 * this.Z0010) * n) + this.Z0093;
            this.Z0020[j] = this.Z0020[n3];
            this.Z0020[n3] = j;
        }
        this.Z0102 = 0;
        for (int k = 0; k < z0019; ++k) {
            for (int l = 0; l < z0019; ++l) {
                for (int n4 = 0; n4 < z0019; ++n4) {
                    final int n5 = (k * z0019 + l) * z0019 + n4 + this.Z0093;
                    for (int n6 = 0; n6 < 14; ++n6) {
                        final int n7 = n4 + array[n6][0];
                        final int n8 = l + array[n6][1];
                        final int n9 = k + array[n6][2];
                        if (n7 >= 0 && n7 < z0019 && n8 >= 0 && n8 < z0019) {
                            if (n9 < z0019) {
                                final int n10 = (n9 * z0019 + n8) * z0019 + n7 + this.Z0093;
                                for (int n11 = this.Z0020[n5]; n11 >= 0; n11 = this.Z0020[n11]) {
                                    for (int n12 = this.Z0020[n10]; n12 >= 0; n12 = this.Z0020[n12]) {
                                        if (n5 != n10 || n12 < n11) {
                                            final int n13 = (n11 > n12) ? n11 : n12;
                                            if (!this.Z0006[n13 * (n13 - 1) / 2 + (n11 + n12 - n13)]) {
                                                final double n14 = this.Z0145[n11] - this.Z0145[n12];
                                                final double n15 = this.Z0146[n11] - this.Z0146[n12];
                                                final double n16 = this.Z0147[n11] - this.Z0147[n12];
                                                if (n14 * n14 + n15 * n15 + n16 * n16 < n2) {
                                                    this.Z0100[2 * this.Z0102] = n11;
                                                    this.Z0100[2 * this.Z0102 + 1] = n12;
                                                    ++this.Z0102;
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
    }
    
    void Z0157() {
        final double n = this.Z0122 * this.Z0122;
        for (int i = 0; i < this.Z0093; ++i) {
            this.Z0133[i] = this.Z0065;
            this.Z0134[i] = this.Z0066;
            this.Z0135[i] = this.Z0067;
        }
        for (int j = 0; j < this.Z0102; ++j) {
            final int n2 = this.Z0100[2 * j];
            final int n3 = this.Z0100[2 * j + 1];
            final double n4 = this.Z0145[n2] - this.Z0145[n3];
            final double n5 = this.Z0146[n2] - this.Z0146[n3];
            final double n6 = this.Z0147[n2] - this.Z0147[n3];
            final double n7 = n4 * n4 + n5 * n5 + n6 * n6;
            if (n7 < n) {
                final double n8 = 1.0 / n7;
                final double n9 = n8 * n8 * n8;
                final double n10 = 48.0 * n9 * (n9 - 0.5) * n8;
                final double[] z0133 = this.Z0133;
                final int n11 = n2;
                z0133[n11] += n10 * n4;
                final double[] z134 = this.Z0134;
                final int n12 = n2;
                z134[n12] += n10 * n5;
                final double[] z135 = this.Z0135;
                final int n13 = n2;
                z135[n13] += n10 * n6;
                final double[] z136 = this.Z0133;
                final int n14 = n3;
                z136[n14] -= n10 * n4;
                final double[] z137 = this.Z0134;
                final int n15 = n3;
                z137[n15] -= n10 * n5;
                final double[] z138 = this.Z0135;
                final int n16 = n3;
                z138[n16] -= n10 * n6;
            }
        }
    }
    
    void Z0156() {
        for (int i = 0; i < this.Z0094; ++i) {
            final int n = this.Z0009[2 * i];
            final int n2 = this.Z0009[2 * i + 1];
            final double n3 = this.Z0145[n] - this.Z0145[n2];
            final double n4 = this.Z0146[n] - this.Z0146[n2];
            final double n5 = this.Z0147[n] - this.Z0147[n2];
            final double n6 = n3 * n3 + n4 * n4 + n5 * n5;
            final double sqrt = Math.sqrt(n6);
            final double n7 = this.Z0056 * (this.Z0008[i] - sqrt) / sqrt - this.Z0058 * (n3 * (this.Z0142[n] - this.Z0142[n2]) + n4 * (this.Z0143[n] - this.Z0143[n2]) + n5 * (this.Z0144[n] - this.Z0144[n2])) / n6;
            final double[] z0133 = this.Z0133;
            final int n8 = n;
            z0133[n8] += n7 * n3;
            final double[] z134 = this.Z0134;
            final int n9 = n;
            z134[n9] += n7 * n4;
            final double[] z135 = this.Z0135;
            final int n10 = n;
            z135[n10] += n7 * n5;
            final double[] z136 = this.Z0133;
            final int n11 = n2;
            z136[n11] -= n7 * n3;
            final double[] z137 = this.Z0134;
            final int n12 = n2;
            z137[n12] -= n7 * n4;
            final double[] z138 = this.Z0135;
            final int n13 = n2;
            z138[n13] -= n7 * n5;
        }
    }
    
    void Z0153() {
        for (int i = 0; i < this.Z0093; ++i) {
            if (Math.abs(this.Z0145[i]) > 0.5 * this.Z0010 - 1.0E-4) {
                final double n = (this.Z0145[i] > 0.0) ? 1.0 : -1.0;
                this.Z0145[i] = n * (0.5 * this.Z0010 - 1.0E-4);
                if (this.Z0142[i] * n > 0.0) {
                    final double[] z0142 = this.Z0142;
                    final int n2 = i;
                    z0142[n2] *= -1.0;
                }
            }
            if (Math.abs(this.Z0146[i]) > 0.5 * this.Z0010 - 1.0E-4) {
                final double n3 = (this.Z0146[i] > 0.0) ? 1.0 : -1.0;
                this.Z0146[i] = n3 * (0.5 * this.Z0010 - 1.0E-4);
                if (this.Z0143[i] * n3 > 0.0) {
                    final double[] z143 = this.Z0143;
                    final int n4 = i;
                    z143[n4] *= -1.0;
                }
            }
            if (Math.abs(this.Z0147[i]) > 0.5 * this.Z0010 - 1.0E-4) {
                final double n5 = (this.Z0147[i] > 0.0) ? 1.0 : -1.0;
                this.Z0147[i] = n5 * (0.5 * this.Z0010 - 1.0E-4);
                if (this.Z0144[i] * n5 > 0.0) {
                    final double[] z144 = this.Z0144;
                    final int n6 = i;
                    z144[n6] *= -1.0;
                }
            }
        }
    }
    
    double Z0161() {
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        for (int i = 0; i < this.Z0093; ++i) {
            n += this.Z0145[i];
            n2 += this.Z0146[i];
            n3 += this.Z0147[i];
        }
        final double n4 = n / this.Z0093;
        final double n5 = n2 / this.Z0093;
        final double n6 = n3 / this.Z0093;
        double n7 = 0.0;
        for (int j = 0; j < this.Z0095; ++j) {
            final int n8 = this.Z0051[3 * j];
            final int n9 = this.Z0051[3 * j + 1];
            final int n10 = this.Z0051[3 * j + 2];
            n7 += (((this.Z0146[n9] - this.Z0146[n8]) * (this.Z0147[n10] - this.Z0147[n8]) - (this.Z0147[n9] - this.Z0147[n8]) * (this.Z0146[n10] - this.Z0146[n8])) * (this.Z0145[n8] - n4) + ((this.Z0147[n9] - this.Z0147[n8]) * (this.Z0145[n10] - this.Z0145[n8]) - (this.Z0145[n9] - this.Z0145[n8]) * (this.Z0147[n10] - this.Z0147[n8])) * (this.Z0146[n8] - n5) + ((this.Z0145[n9] - this.Z0145[n8]) * (this.Z0146[n10] - this.Z0146[n8]) - (this.Z0146[n9] - this.Z0146[n8]) * (this.Z0145[n10] - this.Z0145[n8])) * (this.Z0147[n8] - n6)) / 6.0;
        }
        return n7;
    }
    
    void Z0158() {
        final double n = 0.1 * this.Z0115 * (1.0 - this.Z0161() / this.Z0074);
        for (int i = 0; i < this.Z0095; ++i) {
            final int n2 = this.Z0051[3 * i];
            final int n3 = this.Z0051[3 * i + 1];
            final int n4 = this.Z0051[3 * i + 2];
            final double n5 = (this.Z0146[n3] - this.Z0146[n2]) * (this.Z0147[n4] - this.Z0147[n2]) - (this.Z0147[n3] - this.Z0147[n2]) * (this.Z0146[n4] - this.Z0146[n2]);
            final double n6 = (this.Z0147[n3] - this.Z0147[n2]) * (this.Z0145[n4] - this.Z0145[n2]) - (this.Z0145[n3] - this.Z0145[n2]) * (this.Z0147[n4] - this.Z0147[n2]);
            final double n7 = (this.Z0145[n3] - this.Z0145[n2]) * (this.Z0146[n4] - this.Z0146[n2]) - (this.Z0146[n3] - this.Z0146[n2]) * (this.Z0145[n4] - this.Z0145[n2]);
            final double n8 = n5 * n;
            final double n9 = n6 * n;
            final double n10 = n7 * n;
            final double[] z0133 = this.Z0133;
            final int n11 = n2;
            z0133[n11] += n8;
            final double[] z134 = this.Z0134;
            final int n12 = n2;
            z134[n12] += n9;
            final double[] z135 = this.Z0135;
            final int n13 = n2;
            z135[n13] += n10;
            final double[] z136 = this.Z0133;
            final int n14 = n3;
            z136[n14] += n8;
            final double[] z137 = this.Z0134;
            final int n15 = n3;
            z137[n15] += n9;
            final double[] z138 = this.Z0135;
            final int n16 = n3;
            z138[n16] += n10;
            final double[] z139 = this.Z0133;
            final int n17 = n4;
            z139[n17] += n8;
            final double[] z140 = this.Z0134;
            final int n18 = n4;
            z140[n18] += n9;
            final double[] z141 = this.Z0135;
            final int n19 = n4;
            z141[n19] += n10;
        }
    }
    
    void Z0167(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0093; ++i) {
                final double[] z0142 = this.Z0142;
                final int n2 = i;
                z0142[n2] += 0.5 * this.Z0031 * this.Z0133[i];
                final double[] z143 = this.Z0143;
                final int n3 = i;
                z143[n3] += 0.5 * this.Z0031 * this.Z0134[i];
                final double[] z144 = this.Z0144;
                final int n4 = i;
                z144[n4] += 0.5 * this.Z0031 * this.Z0135[i];
                final double[] z145 = this.Z0145;
                final int n5 = i;
                z145[n5] += this.Z0031 * this.Z0142[i];
                final double[] z146 = this.Z0146;
                final int n6 = i;
                z146[n6] += this.Z0031 * this.Z0143[i];
                final double[] z147 = this.Z0147;
                final int n7 = i;
                z147[n7] += this.Z0031 * this.Z0144[i];
            }
        }
        else {
            for (int j = 0; j < this.Z0093; ++j) {
                final double[] z148 = this.Z0142;
                final int n8 = j;
                z148[n8] += 0.5 * this.Z0031 * this.Z0133[j];
                final double[] z149 = this.Z0143;
                final int n9 = j;
                z149[n9] += 0.5 * this.Z0031 * this.Z0134[j];
                final double[] z150 = this.Z0144;
                final int n10 = j;
                z150[n10] += 0.5 * this.Z0031 * this.Z0135[j];
            }
        }
    }
    
    void Z0160() {
        this.Z0207 = 0.0;
        for (int i = 0; i < this.Z0093; ++i) {
            final double z0207 = this.Z0142[i] * this.Z0142[i] + this.Z0143[i] * this.Z0143[i] + this.Z0144[i] * this.Z0144[i];
            if (z0207 > this.Z0207) {
                this.Z0207 = z0207;
            }
        }
    }
    
    void Z0164() {
        final double[] array = new double[3];
        this.Z0166(Math.sqrt(1.5 * this.Z0093 / 12.566370614359172));
        this.Z0074 = this.Z0161();
        for (int i = 0; i < this.Z0093; ++i) {
            this.Z0174(array);
            this.Z0142[i] = array[0];
            this.Z0143[i] = array[1];
            this.Z0144[i] = array[2];
            this.Z0133[i] = 0.0;
            this.Z0134[i] = 0.0;
            this.Z0135[i] = 0.0;
        }
        this.Z0099 = 1;
        this.Z0185 = 0;
        this.Z0162();
    }
    
    void Z0174(final double[] array) {
        double n;
        double n2;
        double n3;
        for (n = 2.0, n2 = 0.0, n3 = 0.0; n > 1.0; n = n2 * n2 + n3 * n3) {
            n2 = 2.0 * this.Z0132.nextDouble() - 1.0;
            n3 = 2.0 * this.Z0132.nextDouble() - 1.0;
        }
        array[2] = 1.0 - 2.0 * n;
        final double n4 = 2.0 * Math.sqrt(1.0 - n);
        array[0] = n4 * n2;
        array[1] = n4 * n3;
    }
    
    void Z0165() {
        final int[] array = new int[60];
        final int[] array2 = new int[3];
        final int[] array3 = new int[20];
        final int[] array4 = new int[20];
        int n = 12;
        int n2 = 0;
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 3; ++j) {
                final int n3 = this.Z0050[3 * i + j];
                final int n4 = this.Z0050[3 * i + (j + 1) % 3];
                if (n3 < n4) {
                    array[2 * n2] = n3;
                    array[2 * n2 + 1] = n4;
                    ++n2;
                    n += this.Z0073 - 1;
                }
            }
        }
        for (int k = 0; k < 20; ++k) {
            for (int l = 0; l < 3; ++l) {
                int n5;
                int n6;
                int n7;
                for (n5 = this.Z0050[3 * k + l], n6 = this.Z0050[3 * k + (l + 1) % 3], n7 = 0; n7 < 30 && (array[2 * n7] != n5 || array[2 * n7 + 1] != n6) && (array[2 * n7] != n6 || array[2 * n7 + 1] != n5); ++n7) {}
                this.Z0047[3 * k + l] = n7;
            }
        }
        int n8 = 0;
        for (int n9 = 0; n9 < 20; ++n9) {
            for (int n10 = 0; n10 < 3; ++n10) {
                array2[n10] = this.Z0050[3 * n9 + n10];
            }
            array3[0] = array2[0];
            for (int n11 = 0; n11 < this.Z0073; ++n11) {
                for (int n12 = 0; n12 <= n11; ++n12) {
                    array4[n12] = array3[n12];
                }
                if (n11 < this.Z0073 - 1) {
                    final int n13 = 12 + this.Z0047[3 * n9] * (this.Z0073 - 1) + n11;
                    final int n14 = 12 + this.Z0047[3 * n9 + 2] * (this.Z0073 - 1) + n11;
                    array3[0] = n13;
                    for (int n15 = 1; n15 <= n11; ++n15) {
                        array3[n15] = n;
                        ++n;
                    }
                    array3[n11 + 1] = n14;
                }
                else {
                    array3[0] = array2[1];
                    for (int n16 = 0; n16 < n11; ++n16) {
                        array3[n16 + 1] = 12 + this.Z0047[3 * n9 + 1] * (this.Z0073 - 1) + ((array2[1] < array2[2]) ? n16 : (this.Z0073 - 2 - n16));
                    }
                    array3[n11 + 1] = array2[2];
                }
                for (int n17 = 0; n17 < n11; ++n17) {
                    this.Z0051[n8++] = array4[n17];
                    this.Z0051[n8++] = array3[n17];
                    this.Z0051[n8++] = array3[n17 + 1];
                    this.Z0051[n8++] = array4[n17];
                    this.Z0051[n8++] = array3[n17 + 1];
                    this.Z0051[n8++] = array4[n17 + 1];
                }
                final int n18 = n11;
                this.Z0051[n8++] = array4[n18];
                this.Z0051[n8++] = array3[n18];
                this.Z0051[n8++] = array3[n18 + 1];
            }
        }
    }
    
    void Z0166(final double n) {
        for (int i = 0; i < 12; ++i) {
            this.Z0145[i] = n * this.Z0112[i][0];
            this.Z0146[i] = n * this.Z0112[i][1];
            this.Z0147[i] = n * this.Z0112[i][2];
        }
        int n2 = 12;
        for (int j = 0; j < 20; ++j) {
            for (int k = 0; k < 3; ++k) {
                final int n3 = this.Z0050[3 * j + k];
                final int n4 = this.Z0050[3 * j + (k + 1) % 3];
                if (n3 < n4) {
                    for (int l = 0; l < this.Z0073 - 1; ++l) {
                        final double n5 = 1.0 - (l + 1.0) / this.Z0073;
                        this.Z0145[n2] = n5 * this.Z0145[n3] + (1.0 - n5) * this.Z0145[n4];
                        this.Z0146[n2] = n5 * this.Z0146[n3] + (1.0 - n5) * this.Z0146[n4];
                        this.Z0147[n2] = n5 * this.Z0147[n3] + (1.0 - n5) * this.Z0147[n4];
                        final double sqrt = Math.sqrt(this.Z0145[n2] * this.Z0145[n2] + this.Z0146[n2] * this.Z0146[n2] + this.Z0147[n2] * this.Z0147[n2]);
                        final double[] z0145 = this.Z0145;
                        final int n6 = n2;
                        z0145[n6] *= n / sqrt;
                        final double[] z146 = this.Z0146;
                        final int n7 = n2;
                        z146[n7] *= n / sqrt;
                        final double[] z147 = this.Z0147;
                        final int n8 = n2;
                        z147[n8] *= n / sqrt;
                        ++n2;
                    }
                }
            }
        }
        for (int n9 = 0; n9 < 20; ++n9) {
            for (int n10 = 0; n10 < this.Z0073; ++n10) {
                if (n10 < this.Z0073 - 1) {
                    final int n11 = 12 + this.Z0047[3 * n9] * (this.Z0073 - 1) + n10;
                    final int n12 = 12 + this.Z0047[3 * n9 + 2] * (this.Z0073 - 1) + n10;
                    for (int n13 = 1; n13 <= n10; ++n13) {
                        final double n14 = 1.0 - n13 / (n10 + 1.0);
                        this.Z0145[n2] = n14 * this.Z0145[n11] + (1.0 - n14) * this.Z0145[n12];
                        this.Z0146[n2] = n14 * this.Z0146[n11] + (1.0 - n14) * this.Z0146[n12];
                        this.Z0147[n2] = n14 * this.Z0147[n11] + (1.0 - n14) * this.Z0147[n12];
                        final double sqrt2 = Math.sqrt(this.Z0145[n2] * this.Z0145[n2] + this.Z0146[n2] * this.Z0146[n2] + this.Z0147[n2] * this.Z0147[n2]);
                        final double[] z148 = this.Z0145;
                        final int n15 = n2;
                        z148[n15] *= n / sqrt2;
                        final double[] z149 = this.Z0146;
                        final int n16 = n2;
                        z149[n16] *= n / sqrt2;
                        final double[] z150 = this.Z0147;
                        final int n17 = n2;
                        z150[n17] *= n / sqrt2;
                        ++n2;
                    }
                }
            }
        }
    }
    
    void Z0162() {
        for (int i = 0; i < this.Z0096; ++i) {
            this.Z0006[i] = false;
        }
        int n = 0;
        for (int j = 0; j < this.Z0095; ++j) {
            for (int k = 0; k < 3; ++k) {
                final int n2 = this.Z0051[3 * j + k];
                final int n3 = this.Z0051[3 * j + (k + 1) % 3];
                if (n2 > n3) {
                    this.Z0009[2 * n] = n2;
                    this.Z0009[2 * n + 1] = n3;
                    this.Z0006[n2 * (n2 - 1) / 2 + n3] = true;
                    final double n4 = this.Z0145[n2] - this.Z0145[n3];
                    final double n5 = this.Z0146[n2] - this.Z0146[n3];
                    final double n6 = this.Z0147[n2] - this.Z0147[n3];
                    this.Z0008[n] = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
                    ++n;
                }
            }
        }
    }
    
    void Z0175() {
        this.Z0178();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        this.Z0062.setFog((Fog)new LinearFog(color3f, -this.Z0179.z, -this.Z0179.z + 2.5 * this.Z0010));
        final Appearance appearance = new Appearance();
        final Color3f z0063 = new Z0028(0.6f, 0.9f, 0.5f).Z0063();
        appearance.setMaterial(new Material(z0063, color3f, z0063, new Z0028(0.6f, 0.4f, 1.0f).Z0063(), 60.0f));
        this.Z0068.draw(this.Z0062, appearance);
        final Appearance appearance2 = new Appearance();
        final Color3f z64 = new Z0028(0.2f, 0.3f, 0.5f).Z0063();
        appearance2.setMaterial(new Material(z64, color3f, z64, z64, 60.0f));
        new Z0027(this.Z0010).draw(this.Z0062, appearance2);
        this.Z0159();
    }
    
    Transform3D Z0178() {
        this.Z0062.setBackground(new Background(new Z0028(0.6f, 1.0f, 0.2f).Z0063()));
        this.Z0062.clear();
        final Transform3D modelTransform = new Transform3D();
        modelTransform.set(this.Z0092.get());
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(this.Z0179);
        modelTransform.mul(transform3D, modelTransform);
        this.Z0062.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0159() {
        final J3DGraphics2D graphics2D = this.Z0015.getGraphics2D();
        graphics2D.setColor(this.Z0007);
        graphics2D.draw3DRect(0, 0, this.Z0015.getSize().width - 1, this.Z0015.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0015.swap();
    }
    
    public class Z0029
    {
        private TriangleArray Z0053;
        private Point3f[] Z0061;
        private Vector3f[] Z0060;
        private int Z0097;
        private int Z0095;
        
        Z0029(final int z0097) {
            this.Z0097 = z0097;
            this.Z0095 = 2 * (z0097 - 2);
            this.Z0061 = new Point3f[z0097];
            this.Z0060 = new Vector3f[z0097];
            for (int i = 0; i < z0097; ++i) {
                this.Z0061[i] = new Point3f();
                this.Z0060[i] = new Vector3f();
            }
            this.Z0053 = new TriangleArray(3 * this.Z0095, 3);
        }
        
        public void fill(final double[] array, final double[] array2, final double[] array3, final int[] array4) {
            for (int i = 0; i < this.Z0097; ++i) {
                this.Z0061[i].set((float)array[i], (float)array2[i], (float)array3[i]);
            }
            for (int j = 0; j < this.Z0097; ++j) {
                this.Z0060[j].set(0.0f, 0.0f, 0.0f);
            }
            final Vector3f[] array5 = new Vector3f[3];
            for (int k = 0; k < 3; ++k) {
                array5[k] = new Vector3f();
            }
            for (int l = 0; l < this.Z0095; ++l) {
                final int n = array4[3 * l];
                final int n2 = array4[3 * l + 1];
                final int n3 = array4[3 * l + 2];
                array5[0].sub((Tuple3f)this.Z0061[n2], (Tuple3f)this.Z0061[n]);
                array5[1].sub((Tuple3f)this.Z0061[n3], (Tuple3f)this.Z0061[n]);
                array5[2].cross(array5[0], array5[1]);
                this.Z0060[n].add((Tuple3f)array5[2]);
                this.Z0060[n2].add((Tuple3f)array5[2]);
                this.Z0060[n3].add((Tuple3f)array5[2]);
            }
            for (int n4 = 0; n4 < this.Z0097; ++n4) {
                this.Z0060[n4].normalize();
            }
            for (int n5 = 0; n5 < 3 * this.Z0095; ++n5) {
                this.Z0053.setCoordinate(n5, this.Z0061[array4[n5]]);
                this.Z0053.setNormal(n5, this.Z0060[array4[n5]]);
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0053, appearance));
        }
    }
    
    public class Z0028
    {
        float[] Z0071;
        
        public Z0028(final Mdsheet3d mdsheet3d) {
            this(mdsheet3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0028(final Mdsheet3d mdsheet3d, final float[] array) {
            this(mdsheet3d, array[0], array[1], array[2]);
        }
        
        public Z0028(final float n, final float n2, final float n3) {
            (this.Z0071 = new float[3])[0] = n;
            this.Z0071[1] = n2;
            this.Z0071[2] = n3;
        }
        
        Color3f Z0063() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0071[0] * 5.9999f);
            final float n2 = this.Z0071[0] * 6.0f - n;
            array[0] = this.Z0071[2] * (1.0f - this.Z0071[1]);
            array[1] = this.Z0071[2] * (1.0f - n2 * this.Z0071[1]);
            array[2] = this.Z0071[2] * (1.0f - (1.0f - n2) * this.Z0071[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0071[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0071[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0071[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0071[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0071[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0071[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0027
    {
        private QuadArray Z0053;
        
        public Z0027(final Mdsheet3d mdsheet3d) {
            this(mdsheet3d, 1.0);
        }
        
        public Z0027(final Mdsheet3d mdsheet3d, final double n) {
            this(mdsheet3d, new Vector3f((float)n, (float)n, (float)n));
        }
        
        public Z0027(final Vector3f vector3f) {
            final int[] array = { 0, 2, 3, 1, 4, 5, 7, 6, 0, 1, 5, 4, 2, 6, 7, 3, 0, 4, 6, 2, 1, 3, 7, 5 };
            this.Z0053 = new QuadArray(24, 3);
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
                if (this.Z0057(array2)) {
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
                        this.Z0053.setCoordinate(n + l, array2[l]);
                        this.Z0053.setNormal(n + l, vector3f2);
                    }
                    n += 4;
                }
            }
        }
        
        private boolean Z0057(final Point3f[] array) {
            final Transform3D transform3D = new Transform3D();
            transform3D.set(Mdsheet3d.this.Z0092.get());
            final Transform3D transform3D2 = new Transform3D();
            transform3D2.setTranslation(Mdsheet3d.this.Z0179);
            transform3D.mul(transform3D2, transform3D);
            final Transform3D transform3D3 = new Transform3D();
            Mdsheet3d.this.Z0015.getVworldToImagePlate(transform3D3);
            final Transform3D transform3D4 = new Transform3D();
            transform3D4.mul(transform3D3, transform3D);
            final Point3d point3d = new Point3d();
            final Point3d point3d2 = new Point3d();
            final Point2d[] array2 = new Point2d[3];
            for (int i = 0; i < 3; ++i) {
                point3d.set((Tuple3f)array[i]);
                transform3D4.transform(point3d, point3d2);
                array2[i] = new Point2d();
                Mdsheet3d.this.Z0015.getPixelLocationFromImagePlate(point3d2, array2[i]);
            }
            return (array2[2].x - array2[1].x) * (array2[0].y - array2[1].y) - (array2[2].y - array2[1].y) * (array2[0].x - array2[1].x) < 0.0;
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0053, appearance));
        }
    }
    
    public class Z0030 extends MouseAdapter implements MouseMotionListener
    {
        private Z0014 Z0120;
        private int Z0182;
        private int Z0034;
        private int Z0035;
        
        public Z0030() {
            this.Z0120 = new Z0014();
            this.Z0182 = 256;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0116(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0116(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0116(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0116(final int n, final int n2, final int n3) {
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            switch (n3) {
                case 1: {
                    this.Z0034 = n;
                    this.Z0035 = n2;
                    break;
                }
                case 0: {
                    array[0] = this.Z0034;
                    array[1] = this.Z0035;
                    array2[0] = n;
                    array2[1] = n2;
                    if (this.Z0190(array, array2)) {
                        this.Z0034 = n;
                        this.Z0035 = n2;
                        break;
                    }
                    break;
                }
            }
        }
        
        private boolean Z0190(final int[] array, final int[] array2) {
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final Z0014 z0014 = new Z0014();
            final Z0014 z15 = new Z0014();
            final double n = 0.8 * this.Z0182;
            array3[0] = (array[0] - this.Z0182 / 2) / n;
            array3[1] = (this.Z0182 / 2 - array[1]) / n;
            final double n2 = array3[0] * array3[0] + array3[1] * array3[1];
            if (n2 > 1.0) {
                return false;
            }
            array3[2] = Math.sqrt(1.0 - n2);
            array4[0] = (array2[0] - this.Z0182 / 2) / n;
            array4[1] = (this.Z0182 / 2 - array2[1]) / n;
            final double n3 = array4[0] * array4[0] + array4[1] * array4[1];
            if (n3 > 1.0) {
                return false;
            }
            array4[2] = Math.sqrt(1.0 - n3);
            this.Z0173(z0014, array3[1] * array4[2] - array3[2] * array4[1], array3[2] * array4[0] - array3[0] * array4[2], array3[0] * array4[1] - array3[1] * array4[0], array3[0] * array4[0] + array3[1] * array4[1] + array3[2] * array4[2]);
            this.Z0172(z15, z0014, this.Z0120);
            this.Z0171(this.Z0120, z15);
            return true;
        }
        
        private void Z0173(final Z0014 z0014, final double z15, final double z16, final double z17, final double z18) {
            z0014.Z0193 = z15;
            z0014.Z0194 = z16;
            z0014.Z0195 = z17;
            z0014.Z0196 = z18;
        }
        
        private void Z0171(final Z0014 z0014, final Z0014 z15) {
            z0014.Z0193 = z15.Z0193;
            z0014.Z0194 = z15.Z0194;
            z0014.Z0195 = z15.Z0195;
            z0014.Z0196 = z15.Z0196;
        }
        
        private void Z0172(final Z0014 z0014, final Z0014 z15, final Z0014 z16) {
            z0014.Z0193 = z15.Z0196 * z16.Z0193 - z15.Z0195 * z16.Z0194 + z15.Z0194 * z16.Z0195 + z15.Z0193 * z16.Z0196;
            z0014.Z0194 = z15.Z0195 * z16.Z0193 + z15.Z0196 * z16.Z0194 - z15.Z0193 * z16.Z0195 + z15.Z0194 * z16.Z0196;
            z0014.Z0195 = -z15.Z0194 * z16.Z0193 + z15.Z0193 * z16.Z0194 + z15.Z0196 * z16.Z0195 + z15.Z0195 * z16.Z0196;
            z0014.Z0196 = -z15.Z0193 * z16.Z0193 - z15.Z0194 * z16.Z0194 - z15.Z0195 * z16.Z0195 + z15.Z0196 * z16.Z0196;
        }
        
        public void Z0180(final int z0182) {
            this.Z0182 = z0182;
        }
        
        public void set(final double n, final double n2, final double n3) {
            final double n4 = 0.008726646259971648 * n2;
            final double n5 = 0.008726646259971648 * (n - n3);
            final double n6 = 0.008726646259971648 * (n + n3);
            final double sin = Math.sin(n4);
            final double cos = Math.cos(n4);
            this.Z0173(this.Z0120, sin * Math.cos(n5), sin * Math.sin(n5), cos * Math.sin(n6), cos * Math.cos(n6));
        }
        
        public Matrix3d get() {
            final double[] array = new double[10];
            final double[] array2 = { this.Z0120.Z0193, this.Z0120.Z0194, this.Z0120.Z0195, this.Z0120.Z0196 };
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
            double Z0193;
            double Z0194;
            double Z0195;
            double Z0196;
        }
    }
}
