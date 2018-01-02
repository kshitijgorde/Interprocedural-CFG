import javax.vecmath.Matrix3d;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.vecmath.Tuple3f;
import javax.media.j3d.TriangleFanArray;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;
import javax.media.j3d.QuadArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
import javax.vecmath.Color3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Point2d;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Light;
import javax.media.j3d.AmbientLight;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.vecmath.Vector3d;
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

public class Hexapod3d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0007;
    Canvas3D Z0024;
    GraphicsContext3D Z0069;
    SimpleUniverse Z0170;
    Z0038 Z0095;
    JButton[] Z0013;
    JSlider[] Z0167;
    JLabel[] Z0166;
    JLabel[] Z0086;
    Color Z0009;
    Vector3d Z0160;
    Z0035[] Z0056;
    Z0035[] Z0057;
    Z0039[] Z0181;
    Z0039[] Z0180;
    Z0039[] Z0182;
    Z0036[] Z0079;
    int Z0075;
    int Z0076;
    int Z0108;
    int Z0100;
    int Z0093;
    boolean init;
    Z0017[] Z0011;
    
    public void init() {
        this.Z0151();
        this.Z0009 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0144(), "East");
        (this.Z0024 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0024, "Center");
        this.Z0170 = new SimpleUniverse(this.Z0024);
        this.Z0170.getViewingPlatform().setNominalViewingTransform();
        this.Z0069 = null;
        this.Z0095 = new Z0038();
        this.Z0024.addMouseListener((MouseListener)this.Z0095);
        this.Z0024.addMouseMotionListener((MouseMotionListener)this.Z0095);
        this.Z0007 = null;
        this.init = false;
    }
    
    JPanel Z0144() {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0009), panel.getBorder()));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        this.Z0167 = new JSlider[1];
        this.Z0166 = new JLabel[1];
        this.Z0136(0, panel, this.Z0100, 1, 5, "Bugs");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        layout.setConstraints(this.Z0166[0], gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipady = 40;
        layout.setConstraints(this.Z0167[0], gridBagConstraints);
        this.Z0086 = new JLabel[1];
        this.Z0135(0, panel, "Motion");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = 0;
        layout.setConstraints(this.Z0086[0], gridBagConstraints);
        this.Z0013 = new JButton[4];
        this.Z0134(0, panel, "^");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(this.Z0013[0], gridBagConstraints);
        this.Z0134(1, panel, "<");
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.Z0013[1], gridBagConstraints);
        this.Z0134(2, panel, ">");
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        layout.setConstraints(this.Z0013[2], gridBagConstraints);
        this.Z0134(3, panel, "-");
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        layout.setConstraints(this.Z0013[3], gridBagConstraints);
        return panel;
    }
    
    void Z0134(final int n, final JPanel panel, final String s) {
        (this.Z0013[n] = new JButton(s)).addActionListener(this);
        panel.add(this.Z0013[n]);
    }
    
    void Z0136(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        (this.Z0166[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0166[n]);
        (this.Z0167[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0167[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0167[n].setPreferredSize(preferredSize);
        this.Z0167[n].setMajorTickSpacing(n4 - n3);
        this.Z0167[n].setMinorTickSpacing(1);
        this.Z0167[n].setPaintLabels(true);
        this.Z0167[n].setPaintTicks(true);
        this.Z0167[n].addChangeListener(this);
        panel.add(this.Z0167[n]);
    }
    
    void Z0135(final int n, final JPanel panel, final String s) {
        (this.Z0086[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0086[n]);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0013[0]) {
            this.Z0138(0);
        }
        else if (source == this.Z0013[1]) {
            this.Z0138(2);
        }
        else if (source == this.Z0013[2]) {
            this.Z0138(3);
        }
        else if (source == this.Z0013[3]) {
            this.Z0138(1);
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0167[0]) {
                this.init = false;
                this.Z0100 = value;
                this.Z0137();
            }
        }
    }
    
    public void start() {
        if (this.Z0007 == null) {
            (this.Z0007 = new Thread(this)).setPriority(1);
            this.Z0007.start();
        }
        if (this.Z0069 == null) {
            this.Z0069 = this.Z0024.getGraphicsContext3D();
            this.Z0141();
        }
        this.Z0095.Z0161(Math.min(this.Z0024.getSize().width, this.Z0024.getSize().height));
        this.Z0137();
    }
    
    public void stop() {
        this.Z0007 = null;
    }
    
    public void Z0042() {
        this.Z0170.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0007) {
            if (this.init) {
                this.Z0138(4);
                this.Z0152();
                this.Z0153();
                this.Z0150();
            }
            if (!this.Z0154(10L)) {
                break;
            }
        }
    }
    
    boolean Z0154(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0141() {
        this.Z0069.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0069.addLight((Light)directionalLight);
        this.Z0095.set(0.0, -60.0, 45.0);
        this.Z0160 = new Vector3d(0.0, 0.0, -1.0 * this.Z0075);
    }
    
    void Z0152() {
        final double z0073 = this.Z0095.Z0073();
        if (z0073 != 0.0) {
            if (z0073 > 0.0) {
                final Vector3d z74 = this.Z0160;
                z74.z /= 1.0 + 1.0 * Math.abs(z0073);
            }
            else {
                final Vector3d z75 = this.Z0160;
                z75.z *= 1.0 + 1.0 * Math.abs(z0073);
            }
        }
    }
    
    void Z0151() {
        this.Z0093 = 5;
        this.Z0100 = 2;
        this.Z0075 = 15;
        this.Z0076 = 15;
    }
    
    void Z0137() {
        this.Z0142();
        this.Z0143();
        this.Z0011[0].Z0188 = 1;
        this.Z0011[0].Z0026 = true;
    }
    
    void Z0142() {
        this.Z0011 = new Z0017[this.Z0100];
        for (int i = 0; i < this.Z0100; ++i) {
            this.Z0011[i] = new Z0017();
            this.Z0011[i].Z0012 = new Vector3d();
            this.Z0011[i].Z0064 = new Vector3d[6];
            this.Z0011[i].Z0109 = new Point3d();
            for (int j = 0; j < 6; ++j) {
                this.Z0011[i].Z0064[j] = new Vector3d();
            }
        }
        this.Z0056 = new Z0035[this.Z0100];
        this.Z0057 = new Z0035[this.Z0100];
        this.Z0181 = new Z0039[this.Z0100];
        this.Z0180 = new Z0039[this.Z0100];
        this.Z0182 = new Z0039[this.Z0100];
        this.Z0079 = new Z0036[this.Z0100];
    }
    
    void Z0138(final int n) {
        boolean b = true;
        if (n == 4) {
            final int z0070 = this.Z0095.Z0070();
            if (z0070 >= 0) {
                final int z71 = this.Z0095.Z0071();
                final Transform3D transform3D = new Transform3D();
                transform3D.set(this.Z0095.get());
                final Transform3D transform3D2 = new Transform3D();
                transform3D2.setTranslation(this.Z0160);
                transform3D.mul(transform3D2, transform3D);
                final Transform3D transform3D3 = new Transform3D();
                this.Z0024.getVworldToImagePlate(transform3D3);
                final Transform3D transform3D4 = new Transform3D();
                transform3D4.mul(transform3D3, transform3D);
                final Point3d point3d = new Point3d();
                final Point2d point2d = new Point2d();
                int n2 = 999999;
                int z72 = -1;
                for (int i = 0; i < this.Z0100; ++i) {
                    transform3D4.transform(this.Z0011[i].Z0109, point3d);
                    this.Z0024.getPixelLocationFromImagePlate(point3d, point2d);
                    final int n3 = z0070 - (int)point2d.x;
                    final int n4 = z71 - (int)point2d.y;
                    final int n5 = n3 * n3 + n4 * n4;
                    if (n5 < n2) {
                        z72 = i;
                        n2 = n5;
                    }
                }
                if (z72 >= 0 && n2 < 1600 && z72 != this.Z0108) {
                    this.Z0108 = z72;
                }
                else {
                    b = false;
                }
            }
            else {
                b = false;
            }
        }
        if (b && this.Z0108 >= 0) {
            final int z73 = this.Z0108;
            switch (n) {
                case 0: {
                    this.Z0011[z73].Z0188 = 0;
                    if (!this.Z0011[z73].Z0096) {
                        this.Z0011[z73].Z0026 = true;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.Z0011[z73].Z0188 = 0;
                    if (this.Z0011[z73].Z0096) {
                        this.Z0011[z73].Z0026 = true;
                        break;
                    }
                    break;
                }
                case 2: {
                    this.Z0011[z73].Z0188 = 1;
                    if (this.Z0011[z73].Z0187 != this.Z0011[z73].Z0188 && !this.Z0011[z73].Z0096) {
                        this.Z0011[z73].Z0026 = true;
                        break;
                    }
                    break;
                }
                case 3: {
                    this.Z0011[z73].Z0188 = -1;
                    if (this.Z0011[z73].Z0187 != this.Z0011[z73].Z0188 && !this.Z0011[z73].Z0096) {
                        this.Z0011[z73].Z0026 = true;
                        break;
                    }
                    break;
                }
                case 4: {
                    this.Z0011[z73].Z0188 = 0;
                    this.Z0011[z73].Z0026 = true;
                    break;
                }
            }
        }
    }
    
    void Z0153() {
        for (int i = 0; i < this.Z0100; ++i) {
            this.Z0159(i);
        }
        this.Z0139();
    }
    
    void Z0143() {
        for (int i = 0; i < this.Z0100; ++i) {
            final double pow = Math.pow(0.85, i);
            this.Z0011[i].Z0129 = 0.8 * pow;
            this.Z0011[i].Z0130 = 0.3 * pow;
            this.Z0011[i].Z0089 = 0.08 * pow;
            this.Z0011[i].Z0088 = 0.85 * pow;
            this.Z0011[i].Z0063 = 0.9 * pow;
            this.Z0011[i].height = 0.6 * pow;
            final double n = 6.283185307179586 * i / this.Z0100;
            this.Z0011[i].Z0012.set(3.0 * Math.cos(n), 3.0 * Math.sin(n), this.Z0011[i].height);
            this.Z0011[i].Z0204 = 0.04 * pow;
            this.Z0011[i].Z0189 = 0.03 * pow;
            for (int j = 0; j < 6; ++j) {
                this.Z0011[i].Z0064[j].set(this.Z0011[i].Z0063 * Math.cos(3.141592653589793 * (2 * j + 1) / 6.0), this.Z0011[i].Z0063 * Math.sin(3.141592653589793 * (2 * j + 1) / 6.0), -this.Z0011[i].height);
            }
            this.Z0011[i].Z0168 = 0;
            this.Z0011[i].Z0106 = 0;
            this.Z0011[i].Z0026 = false;
            this.Z0011[i].Z0096 = false;
            this.Z0011[i].Z0097 = n - 1.5707963267948966;
            this.Z0011[i].Z0187 = 0;
            this.Z0011[i].Z0188 = 0;
            this.Z0056[i] = new Z0035(this.Z0011[i].Z0129 + this.Z0011[i].Z0089, this.Z0011[i].Z0130, 14);
            this.Z0057[i] = new Z0035(0.4 * this.Z0011[i].Z0129, 0.7 * this.Z0011[i].Z0130, 14);
            this.Z0181[i] = new Z0039(this.Z0011[i].Z0089, 0.8 * this.Z0011[i].Z0089, this.Z0011[i].Z0088, 0, 10);
            this.Z0180[i] = new Z0039(0.8 * this.Z0011[i].Z0089, 0.4 * this.Z0011[i].Z0089, this.Z0011[i].Z0088, 1, 10);
            this.Z0182[i] = new Z0039(0.05 * this.Z0011[i].Z0129, 0.0, 1.3 * this.Z0011[i].Z0129, 0, 10);
            this.Z0079[i] = new Z0036(0.8 * this.Z0011[i].Z0089, 1, 10);
        }
        this.Z0108 = 0;
        this.init = true;
    }
    
    void Z0159(final int n) {
        final Vector3d vector3d = new Vector3d();
        final Vector3d vector3d2 = new Vector3d();
        final Vector3d vector3d3 = new Vector3d();
        if (this.Z0011[n].Z0168 == 0) {
            if (this.Z0011[n].Z0026 && (this.Z0011[n].Z0106 == 0 || this.Z0011[n].Z0106 == 2)) {
                final Z0017 z0017 = this.Z0011[n];
                ++z0017.Z0106;
                this.Z0011[n].Z0026 = false;
            }
            if (this.Z0011[n].Z0106 == 2 && this.Z0011[n].Z0187 != this.Z0011[n].Z0188) {
                final Z0017 z18 = this.Z0011[n];
                ++z18.Z0106;
                this.Z0011[n].Z0026 = true;
            }
            if (this.Z0011[n].Z0106 == 1 || this.Z0011[n].Z0106 == 4) {
                this.Z0011[n].Z0096 = !this.Z0011[n].Z0096;
            }
            this.Z0011[n].Z0169 = 12;
            if (this.Z0011[n].Z0106 == 1 || this.Z0011[n].Z0106 == 3) {
                final Z0017 z19 = this.Z0011[n];
                z19.Z0169 /= 2;
            }
            else if (this.Z0011[n].Z0106 == 0 || this.Z0011[n].Z0106 == 4) {
                this.Z0011[n].Z0169 = 0;
            }
            if (this.Z0011[n].Z0169 > 0) {
                this.Z0011[n].Z0065 = 0.6 * this.Z0011[n].height / (this.Z0011[n].Z0169 / 2);
                if (this.Z0011[n].Z0106 == 1 || this.Z0011[n].Z0106 == 3) {
                    final Z0017 z20 = this.Z0011[n];
                    z20.Z0065 *= 0.5;
                }
            }
            if (this.Z0011[n].Z0106 == 1) {
                this.Z0011[n].Z0187 = this.Z0011[n].Z0188;
                this.Z0011[n].Z0062 = 1;
            }
            else {
                this.Z0011[n].Z0062 = -this.Z0011[n].Z0062;
            }
            if (this.Z0011[n].Z0106 == 4) {
                this.Z0011[n].Z0106 = 0;
                this.Z0011[n].Z0187 = 0;
            }
            if (this.Z0011[n].Z0106 == 1 || this.Z0011[n].Z0106 == 3) {
                final Z0017 z21 = this.Z0011[n];
                ++z21.Z0106;
            }
            this.Z0011[n].Z0168 = this.Z0011[n].Z0169;
        }
        if (this.Z0011[n].Z0169 > 0) {
            final Z0017 z22 = this.Z0011[n];
            --z22.Z0168;
            if (this.Z0011[n].Z0187 == 0) {
                vector3d.set(this.Z0011[n].Z0204 * Math.cos(this.Z0011[n].Z0097), this.Z0011[n].Z0204 * Math.sin(this.Z0011[n].Z0097), 0.0);
                this.Z0011[n].Z0012.add((Tuple3d)this.Z0011[n].Z0012, (Tuple3d)vector3d);
                for (int i = 0; i < 6; ++i) {
                    final double n2 = ((i & 0x1) == 0x1) ? this.Z0011[n].Z0062 : ((double)(-this.Z0011[n].Z0062));
                    vector3d.set(n2 * this.Z0011[n].Z0204, 0.0, 0.0);
                    if (n2 > 0.0) {
                        vector3d.z = ((this.Z0011[n].Z0168 <= this.Z0011[n].Z0169 / 2 - 1) ? this.Z0011[n].Z0065 : (-this.Z0011[n].Z0065));
                    }
                    this.Z0011[n].Z0064[i].add((Tuple3d)this.Z0011[n].Z0064[i], (Tuple3d)vector3d);
                }
            }
            else {
                final Z0017 z23 = this.Z0011[n];
                z23.Z0097 += this.Z0011[n].Z0189 * this.Z0011[n].Z0187;
                if (this.Z0011[n].Z0097 >= 3.141592653589793) {
                    final Z0017 z24 = this.Z0011[n];
                    z24.Z0097 -= 6.283185307179586;
                }
                else if (this.Z0011[n].Z0097 < -3.141592653589793) {
                    final Z0017 z25 = this.Z0011[n];
                    z25.Z0097 += 6.283185307179586;
                }
                final double cos = Math.cos(this.Z0011[n].Z0189 * this.Z0011[n].Z0187);
                final double sin = Math.sin(this.Z0011[n].Z0189 * this.Z0011[n].Z0187);
                for (int j = 0; j < 6; ++j) {
                    vector3d3.set(this.Z0011[n].Z0129 * Math.cos(3.141592653589793 * (2 * j + 1) / 6.0), this.Z0011[n].Z0129 * Math.sin(3.141592653589793 * (2 * j + 1) / 6.0), 0.0);
                    vector3d.add((Tuple3d)this.Z0011[n].Z0064[j], (Tuple3d)vector3d3);
                    final double n3 = ((j & 0x1) == 0x1) ? this.Z0011[n].Z0062 : ((double)(-this.Z0011[n].Z0062));
                    vector3d2.set(vector3d.x * cos - vector3d.y * n3 * sin, vector3d.x * n3 * sin + vector3d.y * cos, vector3d.z);
                    if (n3 > 0.0) {
                        final Vector3d vector3d4 = vector3d2;
                        vector3d4.z += ((this.Z0011[n].Z0168 <= this.Z0011[n].Z0169 / 2 - 1) ? this.Z0011[n].Z0065 : (-this.Z0011[n].Z0065));
                    }
                    this.Z0011[n].Z0064[j].sub((Tuple3d)vector3d2, (Tuple3d)vector3d3);
                }
            }
        }
    }
    
    void Z0139() {
        final Vector3d vector3d = new Vector3d();
        final Vector3d vector3d2 = new Vector3d();
        final Vector3d vector3d3 = new Vector3d();
        final Vector3d vector3d4 = new Vector3d();
        for (int i = 0; i < this.Z0100 - 1; ++i) {
            final Vector3d z0012 = this.Z0011[i].Z0012;
            if (this.Z0011[i].Z0096 && this.Z0011[i].Z0187 == 0) {
                vector3d.set(this.Z0011[i].Z0204 * Math.cos(this.Z0011[i].Z0097), this.Z0011[i].Z0204 * Math.sin(this.Z0011[i].Z0097), 0.0);
            }
            else {
                vector3d.set(0.0, 0.0, 0.0);
            }
            final double n = this.Z0011[i].Z0129 + this.Z0011[i].Z0063;
            for (int j = i + 1; j < this.Z0100; ++j) {
                final Vector3d z13 = this.Z0011[j].Z0012;
                if (this.Z0011[j].Z0096 && this.Z0011[j].Z0187 == 0) {
                    vector3d2.set(this.Z0011[j].Z0204 * Math.cos(this.Z0011[j].Z0097), this.Z0011[j].Z0204 * Math.sin(this.Z0011[j].Z0097), 0.0);
                }
                else {
                    vector3d2.set(0.0, 0.0, 0.0);
                }
                final double n2 = this.Z0011[j].Z0129 + this.Z0011[j].Z0063;
                vector3d3.sub((Tuple3d)z0012, (Tuple3d)z13);
                vector3d4.sub((Tuple3d)vector3d, (Tuple3d)vector3d2);
                if (vector3d3.dot(vector3d4) < 0.0 && vector3d3.lengthSquared() < 1.5 * this.Z0155(n + n2)) {
                    if (this.Z0011[i].Z0106 == 2) {
                        this.Z0011[i].Z0026 = true;
                    }
                    if (this.Z0011[j].Z0106 == 2) {
                        this.Z0011[j].Z0026 = true;
                    }
                }
            }
        }
    }
    
    double Z0155(final double n) {
        return n * n;
    }
    
    void Z0150() {
        final Transform3D z0156 = this.Z0156();
        final Transform3D transform3D = new Transform3D();
        final Transform3D modelTransform = new Transform3D();
        final Transform3D transform3D2 = new Transform3D();
        final Transform3D modelTransform2 = new Transform3D();
        final Transform3D transform3D3 = new Transform3D();
        final Transform3D modelTransform3 = new Transform3D();
        final Transform3D modelTransform4 = new Transform3D();
        final Vector3d translation = new Vector3d();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        for (int i = 0; i < this.Z0100; ++i) {
            modelTransform.setIdentity();
            transform3D.setTranslation(this.Z0011[i].Z0012);
            modelTransform.mul(z0156, transform3D);
            transform3D2.rotZ(this.Z0011[i].Z0097);
            modelTransform.mul(modelTransform, transform3D2);
            final Color3f z157 = new Z0037(0.1f, 0.8f, 0.5f).Z0074();
            final Color3f z158 = new Z0037(0.1f, 0.8f, 0.9f).Z0074();
            final Appearance appearance = new Appearance();
            appearance.setMaterial(new Material(z157, color3f, z157, z158, 60.0f));
            for (int j = 0; j < 6; ++j) {
                final double sqrt = Math.sqrt(this.Z0155(this.Z0011[i].Z0064[j].x) + this.Z0155(this.Z0011[i].Z0064[j].y));
                final double n = 0.5 * sqrt + this.Z0011[i].height * Math.sqrt(this.Z0155(this.Z0011[i].Z0088) / (this.Z0155(sqrt) + this.Z0155(this.Z0011[i].Z0064[j].z)) - 0.25);
                final double sqrt2 = Math.sqrt(this.Z0155(this.Z0011[i].Z0088) - this.Z0155(n));
                transform3D.setTranslation(new Vector3d(this.Z0011[i].Z0129 * Math.cos(3.141592653589793 * (2 * j + 1) / 6.0), this.Z0011[i].Z0129 * Math.sin(3.141592653589793 * (2 * j + 1) / 6.0), 0.0));
                modelTransform2.mul(modelTransform, transform3D);
                transform3D2.rotZ(Math.atan2(this.Z0011[i].Z0064[j].y, this.Z0011[i].Z0064[j].x));
                transform3D3.mul(modelTransform2, transform3D2);
                transform3D2.rotY(Math.atan2(n, sqrt2));
                modelTransform2.mul(transform3D3, transform3D2);
                transform3D.setTranslation(new Vector3d(0.0, 0.0, 0.5 * this.Z0011[i].Z0088));
                modelTransform2.mul(modelTransform2, transform3D);
                this.Z0069.setModelTransform(modelTransform2);
                this.Z0181[i].draw(this.Z0069, appearance);
                transform3D.setTranslation(new Vector3d(0.0, 0.0, 0.5 * this.Z0011[i].Z0088));
                modelTransform2.mul(modelTransform2, transform3D);
                this.Z0069.setModelTransform(modelTransform2);
                this.Z0079[i].draw(this.Z0069, appearance);
                transform3D.setTranslation(new Vector3d(n, 0.0, sqrt2));
                modelTransform2.mul(transform3D3, transform3D);
                final double n2 = sqrt - n;
                transform3D2.rotY(Math.atan2(n2, -Math.sqrt(this.Z0155(this.Z0011[i].Z0088) - this.Z0155(n2))));
                modelTransform2.mul(modelTransform2, transform3D2);
                transform3D.setTranslation(new Vector3d(0.0, 0.0, 0.5 * this.Z0011[i].Z0088));
                modelTransform2.mul(modelTransform2, transform3D);
                this.Z0069.setModelTransform(modelTransform2);
                this.Z0180[i].draw(this.Z0069, appearance);
            }
            this.Z0069.setModelTransform(modelTransform);
            this.Z0056[i].draw(this.Z0069, appearance);
            translation.set(0.7 * this.Z0011[i].Z0129, 0.0, 0.6 * this.Z0011[i].Z0130);
            transform3D.setTranslation(translation);
            modelTransform4.mul(modelTransform, transform3D);
            this.Z0069.setModelTransform(modelTransform4);
            this.Z0057[i].draw(this.Z0069, appearance);
            final Color3f z159 = new Z0037((i == this.Z0108) ? 0.01f : 0.7f, 0.8f, 0.7f).Z0074();
            final Appearance appearance2 = new Appearance();
            appearance2.setMaterial(new Material(z159, color3f, z159, color3f2, 60.0f));
            for (int k = 0; k < 2; ++k) {
                transform3D.setTranslation(new Vector3d(0.2 * this.Z0011[i].Z0129, (2 * k - 1) * 0.2 * this.Z0011[i].Z0130, 0.2 * this.Z0011[i].Z0130));
                modelTransform3.mul(modelTransform4, transform3D);
                transform3D2.rotZ((2 * k - 1) * 50.0 * 3.141592653589793 / 180.0);
                modelTransform3.mul(modelTransform3, transform3D2);
                transform3D2.rotY(0.8726646259971648);
                modelTransform3.mul(modelTransform3, transform3D2);
                transform3D.setTranslation(new Vector3d(0.0, 0.0, 0.65 * this.Z0011[i].Z0129));
                modelTransform3.mul(modelTransform3, transform3D);
                this.Z0069.setModelTransform(modelTransform3);
                this.Z0182[i].draw(this.Z0069, appearance2);
            }
            this.Z0011[i].Z0109.x = this.Z0011[i].Z0012.x + Math.cos(this.Z0011[i].Z0097) * (translation.x + 0.2 * this.Z0011[i].Z0129);
            this.Z0011[i].Z0109.y = this.Z0011[i].Z0012.y - Math.sin(this.Z0011[i].Z0097) * (0.2 * this.Z0011[i].Z0129);
            this.Z0011[i].Z0109.z = this.Z0011[i].Z0012.z + translation.z + 0.2 * this.Z0011[i].Z0130;
        }
        this.Z0069.setModelTransform(z0156);
        final Color3f[] array = { new Color3f(new Z0037(0.5f, 0.8f, 0.8f).Z0074()), new Color3f(new Z0037(0.6f, 0.8f, 0.8f).Z0074()) };
        final Material material = new Material(color3f, color3f, color3f, color3f, 0.0f);
        material.setColorTarget(4);
        final Appearance appearance3 = new Appearance();
        appearance3.setMaterial(material);
        appearance3.setPolygonAttributes(new PolygonAttributes(2, 0, 0.0f));
        new Z0034(2.0 * this.Z0075, this.Z0076, array).draw(this.Z0069, appearance3);
        this.Z0140();
    }
    
    Transform3D Z0156() {
        this.Z0069.setBackground(new Background(new Z0037(0.6f, 1.0f, 0.4f).Z0074()));
        this.Z0069.clear();
        final Transform3D modelTransform = new Transform3D();
        modelTransform.set(this.Z0095.get());
        final Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(this.Z0160);
        modelTransform.mul(transform3D, modelTransform);
        this.Z0069.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0140() {
        final J3DGraphics2D graphics2D = this.Z0024.getGraphics2D();
        graphics2D.setColor(this.Z0009);
        graphics2D.draw3DRect(0, 0, this.Z0024.getSize().width - 1, this.Z0024.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0024.swap();
    }
    
    class Z0017
    {
        Vector3d[] Z0064;
        Vector3d Z0012;
        Point3d Z0109;
        double Z0130;
        double Z0129;
        double Z0088;
        double Z0089;
        double height;
        double Z0063;
        double Z0204;
        double Z0189;
        double Z0065;
        double Z0097;
        int Z0062;
        int Z0106;
        int Z0168;
        int Z0169;
        int Z0187;
        int Z0188;
        boolean Z0096;
        boolean Z0026;
    }
    
    public class Z0035
    {
        private QuadArray Z0059;
        
        public Z0035(final Hexapod3d hexapod3d) {
            this(hexapod3d, 1.0);
        }
        
        public Z0035(final Hexapod3d hexapod3d, final double n) {
            this(hexapod3d, n, n, 32);
        }
        
        public Z0035(final Hexapod3d hexapod3d, final double n, final double n2, final int n3) {
            this(hexapod3d, (float)n, (float)n2, n3);
        }
        
        public Z0035(final float n, final float n2, final int n3) {
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3f();
                array2[i] = new Vector3f();
            }
            this.Z0059 = new QuadArray(4 * n3 * n3, 3);
            final Vector3f vector3f = new Vector3f(n * n, n * n, n2 * n2);
            final float n4 = 1.0f / n;
            final float n5 = 1.0f / n2;
            int n6 = 0;
            float n7 = 1.0f;
            float n8 = 0.0f;
            for (int j = 0; j < n3; ++j) {
                final float n9 = n7;
                final float n10 = n8;
                final float n11 = (float)(3.141592653589793 * (j + 1) / n3 - 1.5707963267948966);
                n7 = (float)Math.cos(n11);
                n8 = (float)Math.sin(n11);
                float n12 = 1.0f;
                float n13 = 0.0f;
                for (int k = 0; k < n3; ++k) {
                    final float n14 = n12;
                    final float n15 = n13;
                    final float n16 = (float)(6.283185307179586 * (k + 1) / n3);
                    n12 = (float)Math.cos(n16);
                    n13 = (float)Math.sin(n16);
                    array2[0].set(n14 * n7 * n4, n15 * n7 * n4, n8 * n5);
                    array2[1].set(n14 * n9 * n4, n15 * n9 * n4, n10 * n5);
                    array2[2].set(n12 * n9 * n4, n13 * n9 * n4, n10 * n5);
                    array2[3].set(n12 * n7 * n4, n13 * n7 * n4, n8 * n5);
                    for (int l = 0; l < 4; ++l) {
                        array[l].x = n * n * array2[l].x;
                        array[l].y = n * n * array2[l].y;
                        array[l].z = n2 * n2 * array2[l].z;
                        array2[l].normalize();
                    }
                    this.Z0059.setCoordinates(n6, array);
                    this.Z0059.setNormals(n6, array2);
                    n6 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0059, appearance));
        }
    }
    
    public class Z0039
    {
        private QuadArray Z0060;
        private TriangleFanArray Z0061;
        
        public Z0039(final Hexapod3d hexapod3d) {
            this(hexapod3d, 1.0, 1.0, 0);
        }
        
        public Z0039(final Hexapod3d hexapod3d, final double n, final double n2, final int n3) {
            this(hexapod3d, n, n, n2, n3, 32);
        }
        
        public Z0039(final Hexapod3d hexapod3d, final double n, final double n2, final double n3, final int n4, final int n5) {
            this(hexapod3d, (float)n, (float)n2, (float)n3, n4, n5);
        }
        
        public Z0039(final float n, final float n2, final float n3, final int n4, final int n5) {
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3f();
                array2[i] = new Vector3f();
            }
            this.Z0060 = new QuadArray(4 * n5, 3);
            final float n6 = (float)Math.sqrt((n - n2) * (n - n2) + n3 * n3);
            final float n7 = n3 / n6;
            final float n8 = (n - n2) / n6;
            int n9 = 0;
            float n10 = 1.0f;
            float n11 = 0.0f;
            for (int j = 0; j < n5; ++j) {
                final float n12 = n10;
                final float n13 = n11;
                final float n14 = (float)(6.283185307179586 * (j + 1) / n5);
                n10 = (float)Math.cos(n14);
                n11 = (float)Math.sin(n14);
                array2[0].set(n12 * n7, n13 * n7, n8);
                array2[1].set(n12 * n7, n13 * n7, n8);
                array2[2].set(n10 * n7, n11 * n7, n8);
                array2[3].set(n10 * n7, n11 * n7, n8);
                array[0].set(n2 * n12, n2 * n13, 0.5f * n3);
                array[1].set(n * n12, n * n13, -0.5f * n3);
                array[2].set(n * n10, n * n11, -0.5f * n3);
                array[3].set(n2 * n10, n2 * n11, 0.5f * n3);
                this.Z0060.setCoordinates(n9, array);
                this.Z0060.setNormals(n9, array2);
                n9 += 4;
            }
            if (n4 == 1) {
                this.Z0061 = new TriangleFanArray(2 * (n5 + 2), 3, new int[] { n5 + 2, n5 + 2 });
                int n15 = 0;
                for (int k = 0; k < 2; ++k) {
                    final float n16 = (k == 0) ? -1.0f : 1.0f;
                    final float n17 = (k == 0) ? n : n2;
                    array2[0].set(0.0f, 0.0f, n16);
                    final float n18 = 0.5f * n3 * n16;
                    array[0].set(0.0f, 0.0f, n18);
                    this.Z0061.setCoordinate(n15, array[0]);
                    this.Z0061.setNormal(n15, array2[0]);
                    ++n15;
                    for (int l = 0; l < n5 + 1; ++l) {
                        final float n19 = (float)(6.283185307179586 * l * n16 / n5);
                        array[0].set(n17 * (float)Math.cos(n19), n17 * (float)Math.sin(n19), n18);
                        this.Z0061.setCoordinate(n15, array[0]);
                        this.Z0061.setNormal(n15, array2[0]);
                        ++n15;
                    }
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0060, appearance));
            if (this.Z0061 != null) {
                graphicsContext3D.draw(new Shape3D((Geometry)this.Z0061, appearance));
            }
        }
    }
    
    public class Z0036
    {
        private QuadArray Z0059;
        
        public Z0036(final Hexapod3d hexapod3d) {
            this(hexapod3d, 1.0, 1);
        }
        
        public Z0036(final Hexapod3d hexapod3d, final double n, final int n2) {
            this(hexapod3d, n, n2, 32);
        }
        
        public Z0036(final Hexapod3d hexapod3d, final double n, final int n2, final int n3) {
            this(hexapod3d, (float)n, n2, n3);
        }
        
        public Z0036(final float n, final int n2, final int n3) {
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3f();
                array2[i] = new Vector3f();
            }
            this.Z0059 = new QuadArray(4 * (n3 / 2) * n3, 3);
            int n4 = 0;
            float n5 = 1.0f;
            float n6 = 0.0f;
            for (int j = 0; j < n3 / 2; ++j) {
                final float n7 = n5;
                final float n8 = n6;
                final float n9 = (float)(n2 * 3.141592653589793 * (j + 1) / n3);
                n5 = (float)Math.cos(n9);
                n6 = (float)Math.sin(n9);
                float n10 = 1.0f;
                float n11 = 0.0f;
                for (int k = 0; k < n3; ++k) {
                    final float n12 = n10;
                    final float n13 = n11;
                    final float n14 = (float)(n2 * 2.0 * 3.141592653589793 * (k + 1) / n3);
                    n10 = (float)Math.cos(n14);
                    n11 = (float)Math.sin(n14);
                    array2[0].set(n12 * n5, n13 * n5, n6);
                    array2[1].set(n12 * n7, n13 * n7, n8);
                    array2[2].set(n10 * n7, n11 * n7, n8);
                    array2[3].set(n10 * n5, n11 * n5, n6);
                    for (int l = 0; l < 4; ++l) {
                        array[l].scale(n, (Tuple3f)array2[l]);
                    }
                    this.Z0059.setCoordinates(n4, array);
                    this.Z0059.setNormals(n4, array2);
                    n4 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0059, appearance));
        }
    }
    
    public class Z0037
    {
        float[] Z0080;
        
        public Z0037(final Hexapod3d hexapod3d) {
            this(hexapod3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0037(final Hexapod3d hexapod3d, final float[] array) {
            this(hexapod3d, array[0], array[1], array[2]);
        }
        
        public Z0037(final float n, final float n2, final float n3) {
            (this.Z0080 = new float[3])[0] = n;
            this.Z0080[1] = n2;
            this.Z0080[2] = n3;
        }
        
        Color3f Z0074() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0080[0] * 5.9999f);
            final float n2 = this.Z0080[0] * 6.0f - n;
            array[0] = this.Z0080[2] * (1.0f - this.Z0080[1]);
            array[1] = this.Z0080[2] * (1.0f - n2 * this.Z0080[1]);
            array[2] = this.Z0080[2] * (1.0f - (1.0f - n2) * this.Z0080[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0080[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0080[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0080[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0080[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0080[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0080[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0038 extends MouseAdapter implements MouseMotionListener
    {
        private Z0021 Z0118;
        private int Z0165;
        private int Z0045;
        private int Z0046;
        private int Z0111;
        private int Z0112;
        private int Z0091;
        private int Z0027;
        private int Z0028;
        
        public Z0038() {
            this.Z0118 = new Z0021();
            this.Z0165 = 256;
            this.Z0111 = 0;
            this.Z0112 = 0;
            this.Z0027 = -999;
            this.Z0028 = -999;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0110(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0110(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0110(mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getButton(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0110(final int n, final int n2, final int z0091, final int n3) {
            final int[] array = new int[2];
            final int[] array2 = new int[2];
            switch (n3) {
                case 1: {
                    this.Z0091 = z0091;
                    this.Z0045 = n;
                    this.Z0046 = n2;
                    if (this.Z0091 == 1) {
                        this.Z0027 = n;
                        this.Z0028 = n2;
                        break;
                    }
                    break;
                }
                case 0: {
                    if (this.Z0091 != 1) {
                        this.Z0111 = n - this.Z0045;
                        this.Z0112 = n2 - this.Z0046;
                        this.Z0045 = n;
                        this.Z0046 = n2;
                        break;
                    }
                    array[0] = this.Z0045;
                    array[1] = this.Z0046;
                    array2[0] = n;
                    array2[1] = n2;
                    if (this.Z0185(array, array2)) {
                        this.Z0045 = n;
                        this.Z0046 = n2;
                        break;
                    }
                    break;
                }
            }
        }
        
        private boolean Z0185(final int[] array, final int[] array2) {
            final double[] array3 = new double[3];
            final double[] array4 = new double[3];
            final Z0021 z0021 = new Z0021();
            final Z0021 z22 = new Z0021();
            final double n = 0.8 * this.Z0165;
            array3[0] = (array[0] - this.Z0165 / 2) / n;
            array3[1] = (this.Z0165 / 2 - array[1]) / n;
            final double n2 = array3[0] * array3[0] + array3[1] * array3[1];
            if (n2 > 1.0) {
                return false;
            }
            array3[2] = Math.sqrt(1.0 - n2);
            array4[0] = (array2[0] - this.Z0165 / 2) / n;
            array4[1] = (this.Z0165 / 2 - array2[1]) / n;
            final double n3 = array4[0] * array4[0] + array4[1] * array4[1];
            if (n3 > 1.0) {
                return false;
            }
            array4[2] = Math.sqrt(1.0 - n3);
            this.Z0149(z0021, array3[1] * array4[2] - array3[2] * array4[1], array3[2] * array4[0] - array3[0] * array4[2], array3[0] * array4[1] - array3[1] * array4[0], array3[0] * array4[0] + array3[1] * array4[1] + array3[2] * array4[2]);
            this.Z0148(z22, z0021, this.Z0118);
            this.Z0147(this.Z0118, z22);
            return true;
        }
        
        private void Z0149(final Z0021 z0021, final double z22, final double z23, final double z24, final double z25) {
            z0021.Z0191 = z22;
            z0021.Z0192 = z23;
            z0021.Z0193 = z24;
            z0021.Z0194 = z25;
        }
        
        private void Z0147(final Z0021 z0021, final Z0021 z22) {
            z0021.Z0191 = z22.Z0191;
            z0021.Z0192 = z22.Z0192;
            z0021.Z0193 = z22.Z0193;
            z0021.Z0194 = z22.Z0194;
        }
        
        private void Z0148(final Z0021 z0021, final Z0021 z22, final Z0021 z23) {
            z0021.Z0191 = z22.Z0194 * z23.Z0191 - z22.Z0193 * z23.Z0192 + z22.Z0192 * z23.Z0193 + z22.Z0191 * z23.Z0194;
            z0021.Z0192 = z22.Z0193 * z23.Z0191 + z22.Z0194 * z23.Z0192 - z22.Z0191 * z23.Z0193 + z22.Z0192 * z23.Z0194;
            z0021.Z0193 = -z22.Z0192 * z23.Z0191 + z22.Z0191 * z23.Z0192 + z22.Z0194 * z23.Z0193 + z22.Z0193 * z23.Z0194;
            z0021.Z0194 = -z22.Z0191 * z23.Z0191 - z22.Z0192 * z23.Z0192 - z22.Z0193 * z23.Z0193 + z22.Z0194 * z23.Z0194;
        }
        
        public void Z0161(final int z0165) {
            this.Z0165 = z0165;
        }
        
        public void set(final double n, final double n2, final double n3) {
            final double n4 = 0.008726646259971648 * n2;
            final double n5 = 0.008726646259971648 * (n - n3);
            final double n6 = 0.008726646259971648 * (n + n3);
            final double sin = Math.sin(n4);
            final double cos = Math.cos(n4);
            this.Z0149(this.Z0118, sin * Math.cos(n5), sin * Math.sin(n5), cos * Math.sin(n6), cos * Math.cos(n6));
        }
        
        public Matrix3d get() {
            final double[] array = new double[10];
            final double[] array2 = { this.Z0118.Z0191, this.Z0118.Z0192, this.Z0118.Z0193, this.Z0118.Z0194 };
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
        
        public double Z0072() {
            final double n = this.Z0111 / this.Z0165;
            this.Z0111 = 0;
            return n;
        }
        
        public double Z0073() {
            final double n = this.Z0112 / this.Z0165;
            this.Z0112 = 0;
            return n;
        }
        
        public int Z0070() {
            final int z0027 = this.Z0027;
            this.Z0027 = -999;
            return z0027;
        }
        
        public int Z0071() {
            final int z0028 = this.Z0028;
            this.Z0028 = -999;
            return z0028;
        }
        
        class Z0021
        {
            double Z0191;
            double Z0192;
            double Z0193;
            double Z0194;
        }
    }
    
    public class Z0034
    {
        private QuadArray Z0059;
        
        public Z0034(final Hexapod3d hexapod3d, final double n, final int n2, final Color3f[] array) {
            this(hexapod3d, (float)n, n2, array);
        }
        
        public Z0034(final float n, final int n2, final Color3f[] array) {
            final Point3f[] array2 = new Point3f[4];
            final Vector3f[] array3 = new Vector3f[4];
            final Color3f[] array4 = new Color3f[4];
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Point3f();
                array3[i] = new Vector3f(0.0f, 0.0f, 1.0f);
                array4[i] = new Color3f();
            }
            this.Z0059 = new QuadArray(4 * n2 * n2, 7);
            final float n3 = 1.0f / (n2 - 1);
            int n4 = 0;
            for (int j = 0; j < n2 - 1; ++j) {
                for (int k = 0; k < n2 - 1; ++k) {
                    array2[0].set(k * n3 - 0.5f, (j + 1) * n3 - 0.5f, 0.0f);
                    array2[1].set(k * n3 - 0.5f, j * n3 - 0.5f, 0.0f);
                    array2[2].set((k + 1) * n3 - 0.5f, j * n3 - 0.5f, 0.0f);
                    array2[3].set((k + 1) * n3 - 0.5f, (j + 1) * n3 - 0.5f, 0.0f);
                    for (int l = 0; l < 4; ++l) {
                        array2[l].scale(n);
                    }
                    final int n5 = (k % 2 != j % 2) ? 1 : 0;
                    for (int n6 = 0; n6 < 4; ++n6) {
                        array4[n6] = array[n5];
                    }
                    this.Z0059.setCoordinates(n4, array2);
                    this.Z0059.setNormals(n4, array3);
                    this.Z0059.setColors(n4, array4);
                    n4 += 4;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0059, appearance));
        }
    }
}
