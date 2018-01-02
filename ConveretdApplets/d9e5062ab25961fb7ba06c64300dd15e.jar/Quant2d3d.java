import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Tuple3f;
import javax.vecmath.Point3f;
import javax.media.j3d.TriangleFanArray;
import javax.media.j3d.QuadArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.PolygonAttributes;
import javax.vecmath.Vector3d;
import javax.media.j3d.TransparencyAttributes;
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
import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
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

public class Quant2d3d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0004;
    Canvas3D Z0012;
    GraphicsContext3D Z0049;
    SimpleUniverse Z0129;
    Z0021 Z0075;
    JButton[] Z0007;
    JSlider[] Z0123;
    JRadioButton[] Z0085;
    Color Z0006;
    Random Z0086;
    Z0019 Z0054;
    double[] Z0079;
    double[] Z0039;
    double[] Z0038;
    double[] Z0044;
    double[] Z0043;
    double Z0080;
    double Z0067;
    double Z0068;
    double Z0037;
    double Z0071;
    double Z0082;
    double Z0147;
    double Z0025;
    double Z0148;
    double Z0024;
    double Z0081;
    double Z0089;
    double Z0126;
    double Z0125;
    float[] Z0045;
    int Z0055;
    int Z0128;
    int Z0127;
    boolean Z0092;
    boolean Z0142;
    
    public void init() {
        this.Z0109();
        this.Z0086 = new Random();
        this.Z0006 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0106(), "East");
        (this.Z0012 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0012, "Center");
        this.Z0129 = new SimpleUniverse(this.Z0012);
        this.Z0129.getViewingPlatform().setNominalViewingTransform();
        this.Z0049 = null;
        this.Z0075 = new Z0021();
        this.Z0012.addMouseListener((MouseListener)this.Z0075);
        this.Z0012.addMouseMotionListener((MouseMotionListener)this.Z0075);
        this.Z0004 = null;
        this.Z0054 = null;
    }
    
    JPanel Z0106() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0006), panel.getBorder()));
        this.Z0007 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0095(0, panel2, "Restart");
        this.Z0097(panel2, 10, 0);
        this.Z0095(1, panel2, "Start");
        panel.add(panel2);
        this.Z0097(panel, 0, 10);
        this.Z0123 = new JSlider[8];
        this.Z0098(0, panel, (int)(100.0 * this.Z0089), 10, 200, "Rel Height");
        this.Z0098(1, panel, (int)(100.0 * this.Z0037), 1, 200, "Energy");
        this.Z0098(2, panel, this.Z0128, 1, 20, "Update");
        this.Z0098(3, panel, this.Z0055, 40, 100, "Grid");
        this.Z0097(panel, 0, 20);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        panel3.setAlignmentX(0.5f);
        panel3.setBorder(BorderFactory.createEtchedBorder());
        this.Z0085 = new JRadioButton[2];
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.Z0096(0, panel3, buttonGroup, "Cylinder", true);
        this.Z0096(1, panel3, buttonGroup, "Slits", false);
        panel.add(panel3);
        this.Z0097(panel, 0, 10);
        this.Z0098(4, panel, (int)(100.0 * this.Z0081), -90, 90, "Potential");
        this.Z0098(5, panel, (int)(100.0 * this.Z0082), 2, 20, "Radius");
        this.Z0098(6, panel, (int)(100.0 * this.Z0126), 5, 20, "Width");
        this.Z0098(7, panel, (int)(100.0 * this.Z0125), 5, 30, "Space");
        return panel;
    }
    
    void Z0095(final int n, final JPanel panel, final String s) {
        (this.Z0007[n] = new JButton(s)).setPreferredSize(this.Z0007[n].getPreferredSize());
        this.Z0007[n].addActionListener(this);
        panel.add(this.Z0007[n]);
    }
    
    void Z0096(final int n, final JPanel panel, final ButtonGroup buttonGroup, final String s, final boolean selected) {
        (this.Z0085[n] = new JRadioButton(s)).setAlignmentX(0.0f);
        this.Z0085[n].addActionListener(this);
        this.Z0085[n].setSelected(selected);
        buttonGroup.add(this.Z0085[n]);
        panel.add(this.Z0085[n]);
    }
    
    void Z0098(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0097(panel, 0, 2);
        (this.Z0123[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0123[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0123[n].setPreferredSize(preferredSize);
        this.Z0123[n].setMajorTickSpacing(n4 - n3);
        this.Z0123[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0123[n].setPaintLabels(true);
        this.Z0123[n].setPaintTicks(true);
        this.Z0123[n].addChangeListener(this);
        panel.add(this.Z0123[n]);
    }
    
    void Z0097(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        boolean b = false;
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0123[0]) {
                this.Z0089 = 0.01 * value;
            }
            else if (slider == this.Z0123[1]) {
                this.Z0037 = 0.01 * value;
                b = true;
            }
            else if (slider == this.Z0123[2]) {
                this.Z0128 = value;
            }
            else if (slider == this.Z0123[3]) {
                this.Z0055 = (value + 5) / 10 * 10 - 1;
                this.Z0099();
            }
            else if (slider == this.Z0123[4]) {
                this.Z0081 = 0.01 * value;
                b = true;
            }
            else if (slider == this.Z0123[5]) {
                this.Z0082 = 0.01 * value;
                b = true;
            }
            else if (slider == this.Z0123[6]) {
                this.Z0126 = 0.01 * value;
                b = true;
            }
            else if (slider == this.Z0123[7]) {
                this.Z0125 = 0.01 * value;
                b = true;
            }
            if (b) {
                this.Z0102();
                this.Z0101();
                this.Z0104();
                this.Z0054.fill(this.Z0045, (float)this.Z0089);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        boolean b = false;
        final Object source = actionEvent.getSource();
        if (source == this.Z0007[0]) {
            this.Z0104();
            this.Z0054.fill(this.Z0045, (float)this.Z0089);
        }
        else if (source == this.Z0007[1]) {
            this.Z0092 = !this.Z0092;
            this.Z0007[1].setText(this.Z0092 ? "Stop" : "Start");
            this.Z0085[0].setEnabled(!this.Z0092);
            this.Z0085[1].setEnabled(!this.Z0092);
            this.Z0123[3].setEnabled(!this.Z0092);
        }
        else if (source == this.Z0085[0]) {
            if (this.Z0085[0].isSelected()) {
                this.Z0142 = false;
                b = true;
            }
        }
        else if (source == this.Z0085[1] && this.Z0085[1].isSelected()) {
            this.Z0142 = true;
            b = true;
        }
        if (b) {
            this.Z0099();
        }
    }
    
    public void start() {
        if (this.Z0004 == null) {
            (this.Z0004 = new Thread(this)).setPriority(1);
            this.Z0004.start();
        }
        if (this.Z0049 == null) {
            this.Z0049 = this.Z0012.getGraphicsContext3D();
            this.Z0103();
        }
        this.Z0075.Z0117(Math.min(this.Z0012.getSize().width, this.Z0012.getSize().height));
        this.Z0099();
    }
    
    public void stop() {
        this.Z0004 = null;
    }
    
    public void Z0026() {
        this.Z0129.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0004) {
            if (this.Z0092) {
                this.Z0110();
                if (this.Z0127 % this.Z0128 != 0) {
                    continue;
                }
                this.Z0054.fill(this.Z0045, (float)this.Z0089);
                this.Z0108();
                if (!this.Z0111(20L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0049 != null && this.Z0054 != null) {
                    this.Z0108();
                }
                if (!this.Z0111(200L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0111(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0103() {
        this.Z0049.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0049.addLight((Light)directionalLight);
        this.Z0075.Z0118(30.0f);
        this.Z0075.Z0116(-90.0f);
    }
    
    void Z0109() {
        this.Z0055 = 61;
        this.Z0142 = false;
        this.Z0089 = 0.8;
        this.Z0037 = 1.0;
        this.Z0071 = 40.0;
        this.Z0080 = 0.05;
        this.Z0082 = 0.1;
        this.Z0081 = 0.4;
        this.Z0126 = 0.1;
        this.Z0125 = 0.15;
        this.Z0128 = 4;
    }
    
    void Z0099() {
        this.Z0102();
        this.Z0105();
        this.Z0101();
        this.Z0104();
        this.Z0054.fill(this.Z0045, (float)this.Z0089);
        this.Z0092 = false;
        this.Z0123[4].setEnabled(!this.Z0142);
        this.Z0123[5].setEnabled(!this.Z0142);
        this.Z0123[6].setEnabled(this.Z0142);
        this.Z0123[7].setEnabled(this.Z0142);
    }
    
    void Z0105() {
        final int n = this.Z0055 * this.Z0055;
        this.Z0044 = new double[n];
        this.Z0043 = new double[n];
        this.Z0045 = new float[n];
        this.Z0039 = new double[n];
        this.Z0038 = new double[n];
        this.Z0079 = new double[n];
        this.Z0054 = new Z0019(this.Z0055);
    }
    
    void Z0102() {
        if (this.Z0142) {
            this.Z0147 = 0.2;
            this.Z0148 = 0.5;
        }
        else {
            this.Z0147 = 0.2;
            this.Z0148 = 0.2;
        }
        this.Z0025 = 1.0 / (this.Z0055 - 1);
        this.Z0024 = 2.0 * this.Z0025 * this.Z0025 / this.Z0071;
        this.Z0068 = 100.0;
    }
    
    void Z0101() {
        for (int i = this.Z0055 * this.Z0055 - 1; i >= 0; --i) {
            this.Z0079[i] = 0.0;
        }
        if (this.Z0142) {
            for (int j = 0; j < this.Z0055; ++j) {
                final double n = Math.abs((double)(j - this.Z0055 / 2)) / this.Z0055;
                for (int k = 0; k < this.Z0055; ++k) {
                    if (Math.abs(k - this.Z0055 / 2) <= 1 && (n < 0.5 * this.Z0125 || n > 0.5 * this.Z0125 + this.Z0126)) {
                        this.Z0079[j * this.Z0055 + k] = this.Z0024 * 3.0 * this.Z0068 * this.Z0068;
                    }
                }
            }
        }
        else {
            final double n2 = this.Z0082 * this.Z0055;
            for (int l = 0; l < this.Z0055; ++l) {
                for (int n3 = 0; n3 < this.Z0055; ++n3) {
                    final double n4 = n3 - this.Z0055 / 2;
                    final double n5 = l - this.Z0055 / 2;
                    if (n4 * n4 + n5 * n5 < n2 * n2) {
                        this.Z0079[l * this.Z0055 + n3] = this.Z0024 * this.Z0081 * this.Z0068 * this.Z0068;
                    }
                }
            }
        }
        for (int n6 = this.Z0055 * this.Z0055 - 1; n6 >= 0; --n6) {
            this.Z0039[n6] = Math.cos(this.Z0079[n6]);
            this.Z0038[n6] = Math.sin(this.Z0079[n6]);
        }
    }
    
    void Z0104() {
        this.Z0067 = Math.sqrt(this.Z0037) * this.Z0068;
        double z0067;
        double n;
        if (this.Z0142) {
            z0067 = this.Z0067;
            n = 0.0;
        }
        else {
            z0067 = this.Z0067 / Math.sqrt(2.0);
            n = this.Z0067 / Math.sqrt(2.0);
        }
        for (int i = this.Z0055 * this.Z0055 - 1; i >= 0; --i) {
            final float[] z68 = this.Z0045;
            final int n2 = i;
            final double[] z69 = this.Z0044;
            final int n3 = i;
            final double n4 = 1.0E-6 * this.Z0086.nextDouble();
            z69[n3] = n4;
            z68[n2] = (float)n4;
            this.Z0043[i] = 0.0;
        }
        double n5 = 0.0;
        for (int j = 1; j < this.Z0055 - 1; ++j) {
            n5 += this.Z0025;
            double n6 = 0.0;
            for (int k = 1; k < this.Z0055 - 1; ++k) {
                n6 += this.Z0025;
                final int n7 = j * this.Z0055 + k;
                final double n8 = 0.5 * ((n6 - this.Z0147) * (n6 - this.Z0147) + (n5 - this.Z0148) * (n5 - this.Z0148)) / (this.Z0080 * this.Z0080);
                if (n8 < 10.0) {
                    final double exp = Math.exp(-n8);
                    final double n9 = z0067 * n6 + n * n5;
                    this.Z0044[n7] = exp * Math.cos(n9);
                    this.Z0043[n7] = exp * Math.sin(n9);
                    this.Z0045[n7] = (float)(this.Z0044[n7] * this.Z0044[n7] + this.Z0043[n7] * this.Z0043[n7]);
                }
            }
        }
        this.Z0127 = 0;
    }
    
    void Z0110() {
        ++this.Z0127;
        final double cos = Math.cos(1.0 / this.Z0071);
        final double n = -Math.sin(1.0 / this.Z0071);
        final double cos2 = Math.cos(2.0 / this.Z0071);
        final double n2 = -Math.sin(2.0 / this.Z0071);
        this.Z0107(0, 0, 0, cos, n);
        this.Z0107(1, 0, 0, cos2, n2);
        this.Z0107(0, 0, 0, cos, n);
        this.Z0107(0, 0, 1, cos, n);
        this.Z0107(0, 1, 1, cos2, n2);
        this.Z0107(0, 0, 1, cos, n);
        for (int i = this.Z0055 * this.Z0055 - 1; i >= 0; --i) {
            final double n3 = this.Z0044[i];
            final double n4 = this.Z0043[i];
            this.Z0044[i] = this.Z0039[i] * n3 + this.Z0038[i] * n4;
            this.Z0043[i] = this.Z0039[i] * n4 - this.Z0038[i] * n3;
        }
        for (int j = this.Z0055 * this.Z0055 - 1; j >= 0; --j) {
            this.Z0045[j] = (float)(this.Z0044[j] * this.Z0044[j] + this.Z0043[j] * this.Z0043[j]);
        }
    }
    
    void Z0107(final int n, final int n2, final int n3, final double n4, final double n5) {
        int n6;
        int n7;
        if (n3 == 0) {
            n6 = 2;
            n7 = 1;
        }
        else {
            n6 = 1;
            n7 = 2;
        }
        for (int i = n2; i < this.Z0055; i += n7) {
            for (int j = n; j < this.Z0055; j += n6) {
                final int n8 = i * this.Z0055 + j;
                int n9;
                if (n3 == 0) {
                    n9 = n8 + 1;
                    if (j == this.Z0055 - 1) {
                        n9 -= this.Z0055;
                    }
                }
                else {
                    n9 = n8 + this.Z0055;
                    if (i == this.Z0055 - 1) {
                        n9 -= this.Z0055 * this.Z0055;
                    }
                }
                final double n10 = this.Z0044[n8];
                final double n11 = this.Z0043[n8];
                final double n12 = this.Z0044[n9];
                final double n13 = this.Z0043[n9];
                this.Z0044[n8] = n4 * n10 + n5 * n13;
                this.Z0043[n8] = n4 * n11 - n5 * n12;
                this.Z0044[n9] = n4 * n12 + n5 * n11;
                this.Z0043[n9] = n4 * n13 - n5 * n10;
            }
        }
    }
    
    void Z0108() {
        final Transform3D z0112 = this.Z0112();
        final Transform3D transform3D = new Transform3D();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        final Appearance appearance = new Appearance();
        final Color3f z113 = new Z0020(0.5f, 0.7f, 0.4f).Z0052();
        appearance.setMaterial(new Material(z113, color3f, z113, new Z0020(0.5f, 0.3f, 1.0f).Z0052(), 60.0f));
        this.Z0054.draw(this.Z0049, appearance);
        final Appearance appearance2 = new Appearance();
        Z0020 z114;
        if (this.Z0142 || this.Z0081 > 0.0) {
            z114 = new Z0020(0.01f, 0.8f, 0.8f);
        }
        else {
            z114 = new Z0020(0.1f, 0.8f, 0.8f);
        }
        final Color3f z115 = z114.Z0052();
        appearance2.setMaterial(new Material(z115, color3f, z115, color3f2, 20.0f));
        appearance2.setTransparencyAttributes(new TransparencyAttributes(2, 0.4f));
        if (!this.Z0142) {
            final float n = (float)Math.abs(0.2 * this.Z0081);
            final Transform3D modelTransform = new Transform3D();
            modelTransform.setTranslation(new Vector3d(0.0, 0.0, (double)(0.5f * n)));
            modelTransform.mul(z0112, modelTransform);
            this.Z0049.setModelTransform(modelTransform);
            new Z0018((float)this.Z0082, n, 1).draw(this.Z0049, appearance2);
        }
        else {
            appearance2.setPolygonAttributes(new PolygonAttributes(2, 0, 0.0f));
            final Transform3D transform3D2 = new Transform3D();
            transform3D2.rotY(1.5707963267948966);
            float n2 = 0.0f;
            float n3 = 0.0f;
            for (int i = 0; i < 3; ++i) {
                if (i == 0) {
                    n2 = -0.5f;
                    n3 = -(float)(0.5 * this.Z0125 + this.Z0126);
                }
                else if (i == 1) {
                    n2 = -(float)(0.5 * this.Z0125);
                    n3 = (float)(0.5 * this.Z0125);
                }
                else if (i == 2) {
                    n2 = (float)(0.5 * this.Z0125 + this.Z0126);
                    n3 = 0.5f;
                }
                final float n4 = 0.25f;
                final Z0022 z116 = new Z0022(n4, n3 - n2);
                final Transform3D modelTransform2 = new Transform3D();
                modelTransform2.setTranslation(new Vector3d(0.0, (double)(float)(0.5 * (n2 + n3)), (double)(0.5f * n4)));
                modelTransform2.mul(z0112, modelTransform2);
                modelTransform2.mul(modelTransform2, transform3D2);
                this.Z0049.setModelTransform(modelTransform2);
                z116.draw(this.Z0049, appearance2);
            }
        }
        this.Z0100();
    }
    
    Transform3D Z0112() {
        this.Z0049.setBackground(new Background(new Z0020(0.6f, 1.0f, 0.2f).Z0052()));
        this.Z0049.clear();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(this.Z0075.Z0050());
        final Transform3D transform3D2 = new Transform3D();
        transform3D2.rotX(this.Z0075.Z0051());
        final Transform3D modelTransform = new Transform3D();
        modelTransform.mul(transform3D2, transform3D);
        final Transform3D transform3D3 = new Transform3D();
        transform3D3.rotX(-1.5707963267948966);
        modelTransform.mul(transform3D3);
        final Transform3D transform3D4 = new Transform3D();
        transform3D4.setScale(1.4);
        modelTransform.mul(transform3D4, modelTransform);
        this.Z0049.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0100() {
        final J3DGraphics2D graphics2D = this.Z0012.getGraphics2D();
        graphics2D.setColor(this.Z0006);
        graphics2D.draw3DRect(0, 0, this.Z0012.getSize().width - 1, this.Z0012.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0012.swap();
    }
    
    public class Z0020
    {
        float[] Z0058;
        
        public Z0020(final Quant2d3d quant2d3d) {
            this(quant2d3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0020(final Quant2d3d quant2d3d, final float[] array) {
            this(quant2d3d, array[0], array[1], array[2]);
        }
        
        public Z0020(final float n, final float n2, final float n3) {
            (this.Z0058 = new float[3])[0] = n;
            this.Z0058[1] = n2;
            this.Z0058[2] = n3;
        }
        
        Color3f Z0052() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0058[0] * 5.9999f);
            final float n2 = this.Z0058[0] * 6.0f - n;
            array[0] = this.Z0058[2] * (1.0f - this.Z0058[1]);
            array[1] = this.Z0058[2] * (1.0f - n2 * this.Z0058[1]);
            array[2] = this.Z0058[2] * (1.0f - (1.0f - n2) * this.Z0058[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0058[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0058[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0058[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0058[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0058[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0058[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0018
    {
        private QuadArray Z0041;
        private TriangleFanArray Z0042;
        
        public Z0018(final Quant2d3d quant2d3d) {
            this(quant2d3d, 1.0f, 1.0f, 0);
        }
        
        public Z0018(final Quant2d3d quant2d3d, final float n, final float n2, final int n3) {
            this(quant2d3d, n, n2, n3, 32);
        }
        
        public Z0018(final float n, final float n2, final int n3, final int n4) {
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            for (int i = 0; i < 4; ++i) {
                array[i] = new Point3f();
                array2[i] = new Vector3f();
            }
            this.Z0041 = new QuadArray(4 * n4, 3);
            int n5 = 0;
            float n6 = 1.0f;
            float n7 = 0.0f;
            for (int j = 0; j < n4; ++j) {
                final float n8 = n6;
                final float n9 = n7;
                final float n10 = (float)(6.283185307179586 * (j + 1) / n4);
                n6 = (float)Math.cos(n10);
                n7 = (float)Math.sin(n10);
                array2[0].set(n8, n9, 0.0f);
                array2[1].set(n8, n9, 0.0f);
                array2[2].set(n6, n7, 0.0f);
                array2[3].set(n6, n7, 0.0f);
                for (int k = 0; k < 4; ++k) {
                    array[k].scale(n, (Tuple3f)array2[k]);
                }
                array[0].z = 0.5f * n2;
                array[1].z = -0.5f * n2;
                array[2].z = -0.5f * n2;
                array[3].z = 0.5f * n2;
                this.Z0041.setCoordinates(n5, array);
                this.Z0041.setNormals(n5, array2);
                n5 += 4;
            }
            if (n3 == 1) {
                this.Z0042 = new TriangleFanArray(2 * (n4 + 2), 3, new int[] { n4 + 2, n4 + 2 });
                int n11 = 0;
                for (int l = 0; l < 2; ++l) {
                    final float n12 = (l == 0) ? -1.0f : 1.0f;
                    array2[0].set(0.0f, 0.0f, n12);
                    final float n13 = 0.5f * n2 * n12;
                    array[0].set(0.0f, 0.0f, n13);
                    this.Z0042.setCoordinate(n11, array[0]);
                    this.Z0042.setNormal(n11, array2[0]);
                    ++n11;
                    for (int n14 = 0; n14 < n4 + 1; ++n14) {
                        final float n15 = (float)(6.283185307179586 * n14 * n12 / n4);
                        array[0].set(n * (float)Math.cos(n15), n * (float)Math.sin(n15), n13);
                        this.Z0042.setCoordinate(n11, array[0]);
                        this.Z0042.setNormal(n11, array2[0]);
                        ++n11;
                    }
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0041, appearance));
            if (this.Z0042 != null) {
                graphicsContext3D.draw(new Shape3D((Geometry)this.Z0042, appearance));
            }
        }
    }
    
    public class Z0022
    {
        private QuadArray Z0040;
        
        public Z0022(final Quant2d3d quant2d3d) {
            this(quant2d3d, 1.0f);
        }
        
        public Z0022(final Quant2d3d quant2d3d, final float n) {
            this(quant2d3d, n, n);
        }
        
        public Z0022(final float n, final float n2) {
            this.Z0040 = new QuadArray(4, 3);
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            array[0] = new Point3f(-0.5f * n, -0.5f * n2, 0.0f);
            array[1] = new Point3f(0.5f * n, -0.5f * n2, 0.0f);
            array[2] = new Point3f(0.5f * n, 0.5f * n2, 0.0f);
            array[3] = new Point3f(-0.5f * n, 0.5f * n2, 0.0f);
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Vector3f(0.0f, 0.0f, 1.0f);
            }
            this.Z0040.setCoordinates(0, array);
            this.Z0040.setNormals(0, array2);
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0040, appearance));
        }
    }
    
    public class Z0019
    {
        private TriangleStripArray Z0040;
        private Point3f[] Z0048;
        private Vector3f[] Z0047;
        private int[] Z0143;
        private int Z0056;
        
        Z0019(final int z0056) {
            this.Z0056 = z0056;
            this.Z0048 = new Point3f[z0056 * z0056];
            this.Z0047 = new Vector3f[z0056 * z0056];
            for (int i = 0; i < z0056 * z0056; ++i) {
                this.Z0048[i] = new Point3f();
                this.Z0047[i] = new Vector3f();
            }
            this.Z0143 = new int[z0056 - 1];
            for (int j = 0; j < z0056 - 1; ++j) {
                this.Z0143[j] = 2 * z0056;
            }
            this.Z0040 = new TriangleStripArray(2 * z0056 * (z0056 - 1), 3, this.Z0143);
        }
        
        public void fill(final float[] array, final float n) {
            final float n2 = 1.0f / (this.Z0056 - 1);
            for (int i = 0; i < this.Z0056; ++i) {
                for (int j = 0; j < this.Z0056; ++j) {
                    final int n3 = i * this.Z0056 + j;
                    this.Z0048[n3].set(j * n2 - 0.5f, i * n2 - 0.5f, array[n3] * n);
                }
            }
            final int[] array2 = new int[4];
            final Vector3f[] array3 = new Vector3f[3];
            for (int k = 0; k < 3; ++k) {
                array3[k] = new Vector3f();
            }
            for (int l = 0; l < this.Z0056 * this.Z0056; ++l) {
                this.Z0047[l].set(0.0f, 0.0f, 0.0f);
            }
            for (int n4 = 0; n4 < this.Z0056 - 1; ++n4) {
                for (int n5 = 0; n5 < this.Z0056 - 1; ++n5) {
                    array2[0] = n4 * this.Z0056 + n5;
                    array2[1] = array2[0] + 1;
                    array2[2] = array2[1] + this.Z0056;
                    array2[3] = array2[2] - 1;
                    for (int n6 = 0; n6 < 4; ++n6) {
                        array3[0].sub((Tuple3f)this.Z0048[array2[(n6 + 1) % 4]], (Tuple3f)this.Z0048[array2[n6]]);
                        array3[1].sub((Tuple3f)this.Z0048[array2[(n6 + 3) % 4]], (Tuple3f)this.Z0048[array2[n6]]);
                        array3[2].cross(array3[0], array3[1]);
                        this.Z0047[array2[n6]].add((Tuple3f)array3[2]);
                    }
                }
            }
            for (int n7 = 0; n7 < this.Z0056 * this.Z0056; ++n7) {
                this.Z0047[n7].normalize();
            }
            int n8 = 0;
            for (int n9 = 0; n9 < this.Z0056 - 1; ++n9) {
                for (int n10 = 0; n10 < this.Z0056; ++n10) {
                    final int n11 = n9 * this.Z0056 + n10;
                    this.Z0040.setCoordinate(n8, this.Z0048[n11 + this.Z0056]);
                    this.Z0040.setNormal(n8, this.Z0047[n11 + this.Z0056]);
                    ++n8;
                    this.Z0040.setCoordinate(n8, this.Z0048[n11]);
                    this.Z0040.setNormal(n8, this.Z0047[n11]);
                    ++n8;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0040, appearance));
        }
    }
    
    public class Z0021 extends MouseAdapter implements MouseMotionListener
    {
        private double Z0002;
        private double Z0001;
        private int Z0122;
        private int Z0029;
        private int Z0030;
        
        public Z0021() {
            this.Z0002 = 10.0;
            this.Z0001 = 10.0;
            this.Z0122 = 256;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0083(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0083(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0083(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0083(final int n, final int n2, final int n3) {
            switch (n3) {
                case 1: {
                    this.Z0029 = n;
                    this.Z0030 = n2;
                    break;
                }
                case 0: {
                    this.Z0001 += 80.0 * (n - this.Z0029) / this.Z0122;
                    if (this.Z0001 < -180.0) {
                        this.Z0001 += 360.0;
                    }
                    else if (this.Z0001 >= 180.0) {
                        this.Z0001 -= 360.0;
                    }
                    this.Z0002 += 60.0 * (n2 - this.Z0030) / this.Z0122;
                    this.Z0029 = n;
                    this.Z0030 = n2;
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
        
        public void Z0117(final int z0122) {
            this.Z0122 = z0122;
        }
        
        public double Z0051() {
            return this.Z0002 * 3.141592653589793 / 180.0;
        }
        
        public double Z0050() {
            return this.Z0001 * 3.141592653589793 / 180.0;
        }
        
        public void Z0118(final float n) {
            this.Z0002 = n;
        }
        
        public void Z0116(final float n) {
            this.Z0001 = n;
        }
    }
}
