import javax.media.j3d.QuadArray;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.media.j3d.Geometry;
import javax.media.j3d.Shape3D;
import javax.vecmath.Tuple3f;
import javax.vecmath.Point3f;
import javax.media.j3d.TriangleStripArray;
import javax.media.j3d.J3DGraphics2D;
import javax.media.j3d.Background;
import javax.media.j3d.Transform3D;
import javax.media.j3d.Material;
import javax.media.j3d.Appearance;
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
import javax.vecmath.Color3f;
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

public class Lscape3d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0004;
    Canvas3D Z0009;
    GraphicsContext3D Z0033;
    SimpleUniverse Z0109;
    Z0016 Z0065;
    JButton[] Z0007;
    JSlider[] Z0107;
    Color Z0006;
    Random Z0072;
    Z0014 Z0039;
    Color3f[] Z0070;
    Z0008[] Z0040;
    double[] Z0044;
    double[] Z0043;
    double Z0077;
    double Z0117;
    float[] Z0042;
    int Z0079;
    int Z0041;
    int Z0066;
    boolean Z0078;
    boolean Z0073;
    boolean Z0076;
    boolean Z0075;
    double Z0095;
    boolean Z0094;
    
    public Lscape3d() {
        this.Z0094 = true;
    }
    
    public void init() {
        this.Z0098();
        this.Z0072 = new Random();
        this.Z0006 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0090(), "East");
        (this.Z0009 = new Canvas3D(SimpleUniverse.getPreferredConfiguration())).stopRenderer();
        contentPane.add((Component)this.Z0009, "Center");
        this.Z0109 = new SimpleUniverse(this.Z0009);
        this.Z0109.getViewingPlatform().setNominalViewingTransform();
        this.Z0033 = null;
        this.Z0065 = new Z0016();
        this.Z0009.addMouseListener((MouseListener)this.Z0065);
        this.Z0009.addMouseMotionListener((MouseMotionListener)this.Z0065);
        this.Z0004 = null;
        this.Z0039 = null;
    }
    
    JPanel Z0090() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, this.Z0006), panel.getBorder()));
        this.Z0007 = new JButton[1];
        this.Z0081(0, panel, "New Surface");
        this.Z0082(panel, 0, 20);
        this.Z0107 = new JSlider[3];
        this.Z0083(0, panel, (int)(100.0 * this.Z0077), 10, 60, "Height");
        this.Z0083(1, panel, (int)(100.0 * this.Z0117), 10, 80, "Roughness");
        this.Z0083(2, panel, this.Z0079, 0, 5, "Grid");
        return panel;
    }
    
    void Z0081(final int n, final JPanel panel, final String s) {
        (this.Z0007[n] = new JButton(s)).setPreferredSize(this.Z0007[n].getPreferredSize());
        this.Z0007[n].setAlignmentX(0.5f);
        this.Z0007[n].addActionListener(this);
        panel.add(this.Z0007[n]);
    }
    
    void Z0083(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0082(panel, 0, 3);
        (this.Z0107[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0107[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0107[n].setPreferredSize(preferredSize);
        this.Z0107[n].setMajorTickSpacing(n4 - n3);
        if (n4 - n3 > 10) {
            this.Z0107[n].setMinorTickSpacing((n4 - n3) / 5);
        }
        else {
            this.Z0107[n].setMinorTickSpacing(1);
        }
        this.Z0107[n].setPaintLabels(true);
        this.Z0107[n].setPaintTicks(true);
        this.Z0107[n].addChangeListener(this);
        panel.add(this.Z0107[n]);
        this.Z0082(panel, 0, 5);
    }
    
    void Z0082(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0107[0]) {
                this.Z0077 = 0.01 * value;
                this.Z0076 = true;
            }
            else if (slider == this.Z0107[1]) {
                this.Z0117 = 0.01 * value;
                this.Z0073 = true;
            }
            else if (slider == this.Z0107[2]) {
                this.Z0079 = value;
                this.Z0084();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.Z0007[0]) {
            this.Z0078 = true;
        }
    }
    
    public void start() {
        if (this.Z0004 == null) {
            (this.Z0004 = new Thread(this)).setPriority(1);
            this.Z0004.start();
        }
        if (this.Z0033 == null) {
            this.Z0033 = this.Z0009.getGraphicsContext3D();
            this.Z0088();
        }
        this.Z0065.Z0103(Math.min(this.Z0009.getSize().width, this.Z0009.getSize().height));
        this.Z0084();
    }
    
    public void stop() {
        this.Z0004 = null;
    }
    
    public void Z0018() {
        this.Z0109.removeAllLocales();
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0004) {
            this.Z0101();
            if (this.Z0033 != null && this.Z0039 != null) {
                this.Z0075 = false;
                this.Z0096();
            }
            if (!this.Z0099(20L)) {
                break;
            }
        }
    }
    
    boolean Z0099(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0088() {
        this.Z0033.addLight((Light)new AmbientLight());
        final DirectionalLight directionalLight = new DirectionalLight();
        directionalLight.setDirection(new Vector3f(-1.0f, -1.0f, -1.0f));
        this.Z0033.addLight((Light)directionalLight);
        this.Z0065.Z0104(50.0f);
        this.Z0065.Z0102(-75.0f);
    }
    
    void Z0098() {
        this.Z0077 = 0.4;
        this.Z0117 = 0.5;
        this.Z0079 = 3;
        this.Z0066 = 128;
    }
    
    void Z0084() {
        this.Z0041 = 1 << this.Z0079 + 3;
        this.Z0089();
        this.Z0078 = true;
    }
    
    void Z0089() {
        final int n = this.Z0041 * this.Z0041;
        this.Z0042 = new float[n];
        this.Z0043 = new double[n];
        this.Z0044 = new double[n];
        this.Z0040 = new Z0008[n];
        for (int i = 0; i < n; ++i) {
            this.Z0040[i] = new Z0008();
        }
        this.Z0070 = new Color3f[this.Z0066];
        this.Z0039 = new Z0014(this.Z0041);
    }
    
    void Z0101() {
        if (this.Z0078) {
            this.Z0078 = false;
            this.Z0073 = true;
            this.Z0091();
        }
        if (this.Z0073) {
            this.Z0073 = false;
            this.Z0092();
            this.Z0076 = true;
        }
        if (this.Z0076) {
            this.Z0076 = false;
            this.Z0097();
            this.Z0039.fill(this.Z0042, this.Z0070, this.Z0066, (float)this.Z0077);
            this.Z0075 = true;
        }
    }
    
    void Z0091() {
        for (int i = this.Z0041 * this.Z0041 - 1; i >= 0; --i) {
            this.Z0044[i] = this.Z0093();
            this.Z0043[i] = 6.283185307179586 * this.Z0072.nextDouble();
        }
    }
    
    double Z0093() {
        if (this.Z0094) {
            double n;
            double n2;
            do {
                n2 = 2.0 * this.Z0072.nextDouble() - 1.0;
                this.Z0095 = 2.0 * this.Z0072.nextDouble() - 1.0;
                n = n2 * n2 + this.Z0095 * this.Z0095;
            } while (n >= 1.0);
            final double sqrt = Math.sqrt(-2.0 * Math.log(n) / n);
            final double n3 = n2 * sqrt;
            this.Z0095 *= sqrt;
            this.Z0094 = false;
            return n3;
        }
        this.Z0094 = true;
        return this.Z0095;
    }
    
    void Z0092() {
        final int[] array = new int[2];
        final int n = this.Z0041 * this.Z0041;
        final double n2 = 1.7;
        final double n3 = (n2 - 0.5) * this.Z0117 - n2;
        final int n4 = this.Z0041 / 4;
        for (int i = n - 1; i >= 0; --i) {
            this.Z0040[i].R = 0.0;
            this.Z0040[i].I = 0.0;
        }
        for (int j = 0; j <= n4; ++j) {
            for (int k = 0; k <= n4; ++k) {
                final int n5 = j * this.Z0041 + k;
                double n6;
                if (n5 > 0) {
                    n6 = Math.pow(k * k + j * j, n3) * this.Z0044[n5];
                }
                else {
                    n6 = 0.0;
                }
                this.Z0040[n5].R = n6 * Math.cos(this.Z0043[n5]);
                this.Z0040[n5].I = n6 * Math.sin(this.Z0043[n5]);
                final int n7 = ((j == 0) ? 0 : (this.Z0041 - j)) * this.Z0041 + ((k == 0) ? 0 : (this.Z0041 - k));
                this.Z0040[n7].R = this.Z0040[n5].R;
                this.Z0040[n7].I = -this.Z0040[n5].I;
            }
        }
        this.Z0040[0 * this.Z0041 + this.Z0041 / 2].I = 0.0;
        this.Z0040[this.Z0041 / 2 * this.Z0041 + 0].I = 0.0;
        this.Z0040[this.Z0041 / 2 * this.Z0041 + this.Z0041 / 2].I = 0.0;
        for (int l = 1; l <= n4; ++l) {
            for (int n8 = 1; n8 <= n4; ++n8) {
                final double n9 = Math.pow(n8 * n8 + l * l, n3) * this.Z0044[l * this.Z0041 + n8];
                final int n10 = (this.Z0041 - l) * this.Z0041 + n8;
                this.Z0040[n10].R = n9 * Math.cos(this.Z0043[n10]);
                this.Z0040[n10].I = n9 * Math.sin(this.Z0043[n10]);
                final int n11 = l * this.Z0041 + this.Z0041 - n8;
                this.Z0040[n11].R = this.Z0040[n10].R;
                this.Z0040[n11].I = -this.Z0040[n10].I;
            }
        }
        array[0] = this.Z0041;
        array[1] = this.Z0041;
        this.Z0086(this.Z0040, array, -1);
        for (int n12 = n - 1; n12 >= 0; --n12) {
            this.Z0042[n12] = (float)this.Z0040[n12].R;
        }
        int n13 = 0;
        for (int n14 = 0; n14 < this.Z0041; ++n14) {
            final double n15 = 0.5 * (this.Z0041 - 1 - Math.abs(2 * n14 - this.Z0041 + 1));
            for (int n16 = 0; n16 < this.Z0041; ++n16, ++n13) {
                final double n17 = 0.5 * (this.Z0041 - 1 - Math.abs(2 * n16 - this.Z0041 + 1));
                final float[] z0042 = this.Z0042;
                final int n18 = n13;
                z0042[n18] *= (float)(n17 * n15);
            }
        }
        double n19 = 0.0;
        for (int n20 = n - 1; n20 >= 0; --n20) {
            if (this.Z0042[n20] > n19) {
                n19 = this.Z0042[n20];
            }
        }
        for (int n21 = n - 1; n21 >= 0; --n21) {
            final float[] z43 = this.Z0042;
            final int n22 = n21;
            z43[n22] /= (float)n19;
            if (this.Z0042[n21] < 0.0) {
                this.Z0042[n21] = (float)(-0.001 / (this.Z0077 + 1.0E-6));
            }
        }
    }
    
    void Z0086(final Z0008[] array, final int[] array2, final int n) {
        this.Z0087(array, array2, n);
        if (n < 0) {
            final double n2 = 1.0 / (array2[0] * array2[1]);
            for (int i = 0; i < array2[0] * array2[1]; ++i) {
                final Z0008 z0008 = array[i];
                z0008.R *= n2;
                final Z0008 z9 = array[i];
                z9.I *= n2;
            }
        }
    }
    
    void Z0087(final Z0008[] array, final int[] array2, final int n) {
        final Z0008[] array3 = new Z0008[Math.max(array2[0], array2[1])];
        for (int i = 0; i < array2[1]; ++i) {
            for (int j = 0; j < array2[0]; ++j) {
                array3[j] = array[array2[0] * i + j];
            }
            this.FftComplex1nn(array3, array2[0], n);
        }
        for (int k = 0; k < array2[0]; ++k) {
            for (int l = 0; l < array2[1]; ++l) {
                array3[l] = array[array2[0] * l + k];
            }
            this.FftComplex1nn(array3, array2[1], n);
        }
    }
    
    void FftComplex1nn(final Z0008[] array, final int n, final int n2) {
        final Z0008 z0008 = new Z0008();
        final Z0008 z9 = new Z0008();
        final Z0008 z10 = new Z0008();
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            if (i < n3) {
                z0008.R = array[i].R;
                z0008.I = array[i].I;
                array[i].R = array[n3].R;
                array[i].I = array[n3].I;
                array[n3].R = z0008.R;
                array[n3].I = z0008.I;
            }
            int n4;
            for (n4 = n / 2; n4 >= 1 && n3 >= n4; n3 -= n4, n4 /= 2) {}
            n3 += n4;
        }
        for (int j = 1; j < n; j *= 2) {
            final double n5 = n2 * 3.141592653589793 / j;
            z10.R = Math.cos(n5) - 1.0;
            z10.I = Math.sin(n5);
            z9.R = 1.0;
            z9.I = 0.0;
            for (int k = 0; k < j; ++k) {
                for (int l = k; l < n; l += 2 * j) {
                    final int n6 = l + j;
                    z0008.R = z9.R * array[n6].R - z9.I * array[n6].I;
                    z0008.I = z9.R * array[n6].I + z9.I * array[n6].R;
                    array[n6].R = array[l].R - z0008.R;
                    array[n6].I = array[l].I - z0008.I;
                    final Z0008 z11 = array[l];
                    z11.R += z0008.R;
                    final Z0008 z12 = array[l];
                    z12.I += z0008.I;
                }
                z0008.R = z9.R * z10.R - z9.I * z10.I;
                z0008.I = z9.R * z10.I + z9.I * z10.R;
                final Z0008 z13 = z9;
                z13.R += z0008.R;
                final Z0008 z14 = z9;
                z14.I += z0008.I;
            }
        }
    }
    
    void Z0097() {
        final double[] array = { 0.0, 0.01, 0.1, 0.25 };
        final int n = 4;
        final Color3f[] array2 = { new Color3f(0.08f, 0.68f, 0.16f), new Color3f(0.28f, 0.8f, 0.0f), new Color3f(0.5f, 0.3f, 0.16f), new Color3f(0.8f, 0.8f, 0.8f) };
        int n2 = 0;
        for (int i = 0; i < this.Z0066; ++i) {
            this.Z0070[i] = new Color3f();
            final double n3 = 0.5 * i / this.Z0066;
            if (n2 < n - 1 && n3 > array[n2 + 1]) {
                ++n2;
            }
            if (n2 < n - 1) {
                final double n4 = (n3 - array[n2]) / (array[n2 + 1] - array[n2]);
                this.Z0070[i].x = (float)((1.0 - n4) * array2[n2].x + n4 * array2[n2 + 1].x);
                this.Z0070[i].y = (float)((1.0 - n4) * array2[n2].y + n4 * array2[n2 + 1].y);
                this.Z0070[i].z = (float)((1.0 - n4) * array2[n2].z + n4 * array2[n2 + 1].z);
            }
            else {
                this.Z0070[i] = array2[n - 1];
            }
        }
    }
    
    void Z0096() {
        this.Z0100();
        final Color3f color3f = new Color3f(0.0f, 0.0f, 0.0f);
        final Color3f color3f2 = new Color3f(1.0f, 1.0f, 1.0f);
        final Appearance appearance = new Appearance();
        final Color3f z0037 = new Z0015(0.6f, 0.9f, 0.9f).Z0037();
        appearance.setMaterial(new Material(z0037, color3f, z0037, color3f2, 20.0f));
        new Z0017(1.0f).draw(this.Z0033, appearance);
        final Material material = new Material(z0037, color3f, z0037, color3f, 0.0f);
        material.setColorTarget(4);
        final Appearance appearance2 = new Appearance();
        appearance2.setMaterial(material);
        this.Z0039.draw(this.Z0033, appearance2);
        this.Z0085();
    }
    
    Transform3D Z0100() {
        this.Z0033.setBackground(new Background(new Z0015(0.6f, 1.0f, 0.2f).Z0037()));
        this.Z0033.clear();
        final Transform3D transform3D = new Transform3D();
        transform3D.rotY(this.Z0065.Z0034());
        final Transform3D transform3D2 = new Transform3D();
        transform3D2.rotX(this.Z0065.Z0036());
        final Transform3D modelTransform = new Transform3D();
        modelTransform.mul(transform3D2, transform3D);
        final Transform3D transform3D3 = new Transform3D();
        transform3D3.rotX(-1.5707963267948966);
        modelTransform.mul(transform3D3);
        final Transform3D transform3D4 = new Transform3D();
        transform3D4.setScale(1.4);
        modelTransform.mul(transform3D4, modelTransform);
        this.Z0033.setModelTransform(modelTransform);
        return modelTransform;
    }
    
    void Z0085() {
        final J3DGraphics2D graphics2D = this.Z0009.getGraphics2D();
        graphics2D.setColor(this.Z0006);
        graphics2D.draw3DRect(0, 0, this.Z0009.getSize().width - 1, this.Z0009.getSize().height - 1, true);
        graphics2D.flush(true);
        this.Z0009.swap();
    }
    
    class Z0008
    {
        double R;
        double I;
    }
    
    public class Z0015
    {
        float[] Z0049;
        
        public Z0015(final Lscape3d lscape3d) {
            this(lscape3d, 1.0f, 1.0f, 1.0f);
        }
        
        public Z0015(final Lscape3d lscape3d, final float[] array) {
            this(lscape3d, array[0], array[1], array[2]);
        }
        
        public Z0015(final float n, final float n2, final float n3) {
            (this.Z0049 = new float[3])[0] = n;
            this.Z0049[1] = n2;
            this.Z0049[2] = n3;
        }
        
        Color3f Z0037() {
            final float[] array = new float[3];
            final float[] array2 = new float[3];
            final int n = (int)(this.Z0049[0] * 5.9999f);
            final float n2 = this.Z0049[0] * 6.0f - n;
            array[0] = this.Z0049[2] * (1.0f - this.Z0049[1]);
            array[1] = this.Z0049[2] * (1.0f - n2 * this.Z0049[1]);
            array[2] = this.Z0049[2] * (1.0f - (1.0f - n2) * this.Z0049[1]);
            switch (n) {
                case 0: {
                    array2[0] = this.Z0049[2];
                    array2[1] = array[2];
                    array2[2] = array[0];
                    break;
                }
                case 1: {
                    array2[0] = array[1];
                    array2[1] = this.Z0049[2];
                    array2[2] = array[0];
                    break;
                }
                case 2: {
                    array2[0] = array[0];
                    array2[1] = this.Z0049[2];
                    array2[2] = array[2];
                    break;
                }
                case 3: {
                    array2[0] = array[0];
                    array2[1] = array[1];
                    array2[2] = this.Z0049[2];
                    break;
                }
                case 4: {
                    array2[0] = array[2];
                    array2[1] = array[0];
                    array2[2] = this.Z0049[2];
                    break;
                }
                case 5: {
                    array2[0] = this.Z0049[2];
                    array2[1] = array[0];
                    array2[2] = array[1];
                    break;
                }
            }
            return new Color3f(array2[0], array2[1], array2[2]);
        }
    }
    
    public class Z0014
    {
        private TriangleStripArray Z0029;
        private Point3f[] Z0032;
        private Vector3f[] Z0031;
        private int[] Z0116;
        private int Z0045;
        
        Z0014(final int z0045) {
            this.Z0045 = z0045;
            this.Z0032 = new Point3f[z0045 * z0045];
            this.Z0031 = new Vector3f[z0045 * z0045];
            for (int i = 0; i < z0045 * z0045; ++i) {
                this.Z0032[i] = new Point3f();
                this.Z0031[i] = new Vector3f();
            }
            this.Z0116 = new int[z0045 - 1];
            for (int j = 0; j < z0045 - 1; ++j) {
                this.Z0116[j] = 2 * z0045;
            }
            this.Z0029 = new TriangleStripArray(2 * z0045 * (z0045 - 1), 7, this.Z0116);
        }
        
        public void fill(final float[] array, final Color3f[] array2, final int n, final float n2) {
            final float n3 = 1.0f / (this.Z0045 - 1);
            for (int i = 0; i < this.Z0045; ++i) {
                for (int j = 0; j < this.Z0045; ++j) {
                    final int n4 = i * this.Z0045 + j;
                    this.Z0032[n4].set(j * n3 - 0.5f, i * n3 - 0.5f, array[n4] * n2);
                }
            }
            final int[] array3 = new int[4];
            final Vector3f[] array4 = new Vector3f[3];
            for (int k = 0; k < 3; ++k) {
                array4[k] = new Vector3f();
            }
            for (int l = 0; l < this.Z0045 * this.Z0045; ++l) {
                this.Z0031[l].set(0.0f, 0.0f, 0.0f);
            }
            for (int n5 = 0; n5 < this.Z0045 - 1; ++n5) {
                for (int n6 = 0; n6 < this.Z0045 - 1; ++n6) {
                    array3[0] = n5 * this.Z0045 + n6;
                    array3[1] = array3[0] + 1;
                    array3[2] = array3[1] + this.Z0045;
                    array3[3] = array3[2] - 1;
                    for (int n7 = 0; n7 < 4; ++n7) {
                        array4[0].sub((Tuple3f)this.Z0032[array3[(n7 + 1) % 4]], (Tuple3f)this.Z0032[array3[n7]]);
                        array4[1].sub((Tuple3f)this.Z0032[array3[(n7 + 3) % 4]], (Tuple3f)this.Z0032[array3[n7]]);
                        array4[2].cross(array4[0], array4[1]);
                        this.Z0031[array3[n7]].add((Tuple3f)array4[2]);
                    }
                }
            }
            for (int n8 = 0; n8 < this.Z0045 * this.Z0045; ++n8) {
                this.Z0031[n8].normalize();
            }
            int n9 = 0;
            for (int n10 = 0; n10 < this.Z0045 - 1; ++n10) {
                for (int n11 = 0; n11 < this.Z0045; ++n11) {
                    final int n12 = n10 * this.Z0045 + n11;
                    this.Z0029.setCoordinate(n9, this.Z0032[n12 + this.Z0045]);
                    this.Z0029.setNormal(n9, this.Z0031[n12 + this.Z0045]);
                    this.Z0029.setColor(n9, array2[(int)(array[n12 + this.Z0045] * n2 * n)]);
                    ++n9;
                    this.Z0029.setCoordinate(n9, this.Z0032[n12]);
                    this.Z0029.setNormal(n9, this.Z0031[n12]);
                    this.Z0029.setColor(n9, array2[(int)(array[n12] * n2 * n)]);
                    ++n9;
                }
            }
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0029, appearance));
        }
    }
    
    public class Z0016 extends MouseAdapter implements MouseMotionListener
    {
        private double Z0002;
        private double Z0001;
        private int Z0106;
        private int Z0019;
        private int Z0020;
        boolean Z0010;
        
        public Z0016() {
            this.Z0002 = 10.0;
            this.Z0001 = 10.0;
            this.Z0106 = 256;
            this.Z0010 = false;
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.Z0071(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            this.Z0071(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            this.Z0071(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
        
        private void Z0071(final int n, final int n2, final int n3) {
            switch (n3) {
                case 1: {
                    this.Z0019 = n;
                    this.Z0020 = n2;
                    break;
                }
                case 0: {
                    this.Z0001 += 80.0 * (n - this.Z0019) / this.Z0106;
                    if (this.Z0001 < -180.0) {
                        this.Z0001 += 360.0;
                    }
                    else if (this.Z0001 >= 180.0) {
                        this.Z0001 -= 360.0;
                    }
                    this.Z0002 += 60.0 * (n2 - this.Z0020) / this.Z0106;
                    this.Z0019 = n;
                    this.Z0020 = n2;
                    if (this.Z0002 > 80.0) {
                        this.Z0002 = 80.0;
                    }
                    else if (this.Z0002 < 5.0) {
                        this.Z0002 = 5.0;
                    }
                    this.Z0010 = true;
                    break;
                }
            }
        }
        
        public void Z0103(final int z0106) {
            this.Z0106 = z0106;
        }
        
        public double Z0036() {
            return this.Z0002 * 3.141592653589793 / 180.0;
        }
        
        public double Z0034() {
            return this.Z0001 * 3.141592653589793 / 180.0;
        }
        
        public boolean Z0035() {
            if (this.Z0010) {
                this.Z0010 = false;
                return true;
            }
            return false;
        }
        
        public void Z0104(final float n) {
            this.Z0002 = n;
        }
        
        public void Z0102(final float n) {
            this.Z0001 = n;
        }
    }
    
    public class Z0017
    {
        private QuadArray Z0029;
        
        public Z0017(final Lscape3d lscape3d) {
            this(lscape3d, 1.0f);
        }
        
        public Z0017(final Lscape3d lscape3d, final float n) {
            this(lscape3d, n, n);
        }
        
        public Z0017(final float n, final float n2) {
            this.Z0029 = new QuadArray(4, 3);
            final Point3f[] array = new Point3f[4];
            final Vector3f[] array2 = new Vector3f[4];
            array[0] = new Point3f(-0.5f * n, -0.5f * n2, 0.0f);
            array[1] = new Point3f(0.5f * n, -0.5f * n2, 0.0f);
            array[2] = new Point3f(0.5f * n, 0.5f * n2, 0.0f);
            array[3] = new Point3f(-0.5f * n, 0.5f * n2, 0.0f);
            for (int i = 0; i < 4; ++i) {
                array2[i] = new Vector3f(0.0f, 0.0f, 1.0f);
            }
            this.Z0029.setCoordinates(0, array);
            this.Z0029.setNormals(0, array2);
        }
        
        public void draw(final GraphicsContext3D graphicsContext3D, final Appearance appearance) {
            graphicsContext3D.draw(new Shape3D((Geometry)this.Z0029, appearance));
        }
    }
}
