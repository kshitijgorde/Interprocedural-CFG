import java.awt.Insets;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
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
import java.awt.Font;
import java.awt.Component;
import java.util.Random;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gratchet extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0014;
    JSlider[] Z0144;
    DrawingArea Z0031;
    Color Z0013;
    Random Z0103;
    double[] Z0115;
    double[] Z0116;
    double[] Z0113;
    double[] Z0114;
    double[] Z0104;
    double[] Z0105;
    double[] Z0006;
    double[] Z0007;
    double[] Z0140;
    double[] Z0044;
    double[] Z0045;
    double[] Z0064;
    double Z0107;
    double Z0108;
    double Z0027;
    double Z0101;
    double Z0028;
    double Z0102;
    double Z0030;
    double Z0161;
    double Z0042;
    double Z0048;
    double Z0011;
    double Z0012;
    double Z0158;
    double Z0159;
    double Z0157;
    double Z0151;
    double Z0142;
    double Z0010;
    double Z0139;
    double Z0141;
    double Z0138;
    double Z0008;
    double Z0040;
    double Z0066;
    int[] Z0019;
    int[] Z0086;
    int[] Z0053;
    int[] Z0065;
    int Z0015;
    int Z0016;
    int Z0054;
    int Z0055;
    int Z0077;
    int Z0079;
    int Z0020;
    int Z0021;
    int Z0089;
    int Z0087;
    int Z0085;
    int Z0088;
    int Z0146;
    int Z0147;
    int Z0080;
    int Z0081;
    int Z0083;
    int Z0078;
    int Z0082;
    boolean Z0112;
    boolean Z0106;
    
    public void init() {
        this.Z0135();
        this.Z0103 = new Random();
        this.Z0013 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0131(), "East");
        (this.Z0031 = new DrawingArea()).setFont(new Font("Serif", 1, 12));
        contentPane.add(this.Z0031, "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0131() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0013), panel.getBorder()));
        this.Z0014 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0117(0, panel2, "Reset");
        this.Z0118(panel2, 10, 0);
        this.Z0117(1, panel2, "Start");
        panel.add(panel2);
        this.Z0118(panel, 0, 20);
        this.Z0144 = new JSlider[4];
        this.Z0119(0, panel, (int)(100.0 * this.Z0159), 35, 55, "Frequency");
        this.Z0119(1, panel, this.Z0147, 1, 100, "Update");
        this.Z0119(2, panel, this.Z0055, 4, 12, "Layers");
        this.Z0119(3, panel, this.Z0083, 8, 30, "Teeth");
        return panel;
    }
    
    void Z0117(final int n, final JPanel panel, final String s) {
        (this.Z0014[n] = new JButton(s)).setPreferredSize(this.Z0014[n].getPreferredSize());
        this.Z0014[n].addActionListener(this);
        panel.add(this.Z0014[n]);
    }
    
    void Z0119(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0118(panel, 0, 3);
        (this.Z0144[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0144[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0144[n].setPreferredSize(preferredSize);
        this.Z0144[n].setMajorTickSpacing(n4 - n3);
        this.Z0144[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0144[n].setPaintLabels(true);
        this.Z0144[n].setPaintTicks(true);
        this.Z0144[n].addChangeListener(this);
        panel.add(this.Z0144[n]);
        this.Z0118(panel, 0, 5);
    }
    
    void Z0118(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0144[0]) {
                this.Z0159 = 0.01 * value;
            }
            else if (slider == this.Z0144[1]) {
                this.Z0147 = value;
            }
            else if (slider == this.Z0144[2]) {
                this.Z0055 = (value + 1) / 2 * 2;
                this.Z0120();
            }
            else if (slider == this.Z0144[3]) {
                this.Z0083 = (value + 2) / 4 * 4;
                this.Z0120();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0014[0]) {
            this.Z0128();
        }
        else if (source == this.Z0014[1]) {
            this.Z0112 = !this.Z0112;
            this.Z0014[1].setText(this.Z0112 ? "Stop" : "Start");
            this.Z0144[2].setEnabled(!this.Z0112);
            this.Z0144[3].setEnabled(!this.Z0112);
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        this.Z0031.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, this.Z0013), BorderFactory.createRaisedBevelBorder()));
        this.Z0120();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0112) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0132();
                if (this.Z0146 % this.Z0147 != 0) {
                    continue;
                }
                this.Z0031.repaint();
                this.Z0031.getToolkit().sync();
                if (!this.Z0136(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0106) {
                    this.Z0106 = false;
                    this.Z0031.repaint();
                }
                if (!this.Z0136(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0136(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0135() {
        this.Z0054 = 70;
        this.Z0055 = 6;
        this.Z0042 = 5.0;
        this.Z0048 = 5.0;
        this.Z0159 = 0.4;
        this.Z0157 = 1.0;
        this.Z0147 = 30;
        this.Z0142 = 0.67;
        this.Z0139 = 2.0;
        this.Z0083 = 12;
        this.Z0138 = 0.99;
        this.Z0008 = 0.333;
        this.Z0040 = 0.5;
        this.Z0066 = 1.2;
        this.Z0140 = new double[2];
        this.Z0044 = new double[2];
        this.Z0045 = new double[2];
        this.Z0053 = new int[2];
    }
    
    void Z0120() {
        this.Z0028 = 0.95;
        if (this.Z0055 > this.Z0054) {
            this.Z0055 = this.Z0054;
        }
        this.Z0107 = this.Z0054 / Math.sqrt(this.Z0028);
        this.Z0108 = this.Z0142 * this.Z0107;
        this.Z0027 = 0.005;
        this.Z0101 = Math.pow(2.0, 0.16666666666666666);
        this.Z0020 = (int)(this.Z0107 / (this.Z0101 + this.Z0102));
        this.Z0021 = (int)(this.Z0108 / (this.Z0101 + this.Z0102));
        this.Z0010 = this.Z0107;
        this.Z0141 = this.Z0010 / this.Z0083;
        this.Z0140[0] = this.Z0141 * this.Z0138;
        this.Z0140[1] = this.Z0141 - this.Z0140[0];
        this.Z0125();
        this.Z0082 = (int)(this.Z0055 / this.Z0066);
        if (this.Z0082 < 1) {
            this.Z0082 = 1;
        }
        this.Z0102 = 0.4;
        this.Z0087 = 5;
        this.Z0089 = this.Z0087 * this.Z0077;
        this.Z0130();
        this.Z0128();
        this.Z0112 = false;
    }
    
    void Z0125() {
        this.Z0079 = this.Z0054 * this.Z0055;
        this.Z0078 = 0;
        for (int i = 0; i < 2; ++i) {
            this.Z0053[i] = (int)(1.0 + Math.sqrt(this.Z0140[i] * this.Z0140[i] + this.Z0139 * this.Z0139) / (this.Z0040 * this.Z0008));
            this.Z0078 += this.Z0053[i] * this.Z0083;
            this.Z0044[i] = this.Z0140[i] / this.Z0053[i];
            this.Z0045[i] = this.Z0139 / this.Z0053[i];
        }
        final double[] z0045 = this.Z0045;
        final int n = 1;
        z0045[n] *= -1.0;
        this.Z0077 = this.Z0079 + this.Z0078;
    }
    
    void Z0130() {
        this.Z0115 = new double[this.Z0077];
        this.Z0116 = new double[this.Z0077];
        this.Z0113 = new double[this.Z0077];
        this.Z0114 = new double[this.Z0077];
        this.Z0104 = new double[this.Z0077];
        this.Z0105 = new double[this.Z0077];
        this.Z0019 = new int[this.Z0077 + this.Z0020 * this.Z0021];
        this.Z0086 = new int[2 * this.Z0089];
        this.Z0006 = new double[this.Z0077];
        this.Z0007 = new double[this.Z0077];
        this.Z0065 = new int[this.Z0082];
        this.Z0064 = new double[this.Z0082];
    }
    
    void Z0134() {
        final double z0151 = this.Z0146 * this.Z0027;
        double n = z0151 - this.Z0151;
        this.Z0080 = (int)(this.Z0158 * n);
        if (this.Z0146 > 0 && this.Z0159 != this.Z0158 && this.Z0080 != this.Z0081) {
            this.Z0158 = this.Z0159;
            this.Z0151 = z0151;
            n = 0.0;
            this.Z0080 = 0;
            this.Z0137();
            this.Z0126(0);
        }
        final double n2 = this.Z0158 * n - this.Z0080;
        this.Z0011 = -0.5 * this.Z0108 + this.Z0157 - this.Z0157 * Math.cos(6.283185307179586 * n2);
        this.Z0012 = 6.283185307179586 * this.Z0158 * this.Z0157 * Math.cos(6.283185307179586 * n2);
        double n3 = -0.5 * this.Z0010;
        double z152 = this.Z0011;
        int z153 = this.Z0079;
        for (int i = 0; i < this.Z0083; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < this.Z0053[j]; ++k) {
                    this.Z0115[z153] = n3;
                    this.Z0116[z153] = z152;
                    this.Z0113[z153] = 0.0;
                    this.Z0114[z153] = this.Z0012;
                    n3 += this.Z0044[j];
                    z152 += this.Z0045[j];
                    ++z153;
                }
            }
        }
    }
    
    void Z0132() {
        ++this.Z0146;
        this.Z0134();
        this.Z0129(1);
        this.Z0122();
        if (this.Z0085 > 0) {
            this.Z0085 = 0;
            this.Z0030 = 0.0;
            this.Z0121();
        }
        if (this.Z0112) {
            this.Z0124();
            this.Z0129(2);
            this.Z0127();
            this.Z0030 += Math.sqrt(this.Z0161) * this.Z0027;
            if (this.Z0030 > 0.5 * this.Z0102) {
                this.Z0085 = 1;
            }
        }
    }
    
    void Z0121() {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n = this.Z0020 / this.Z0107;
        final double n2 = this.Z0021 / this.Z0108;
        for (int i = this.Z0077; i < this.Z0077 + this.Z0020 * this.Z0021; ++i) {
            this.Z0019[i] = -1;
        }
        for (int j = 0; j < this.Z0077; ++j) {
            final int n3 = (int)((this.Z0116[j] + 0.5 * this.Z0108) * n2) * this.Z0020 + (int)((this.Z0115[j] + 0.5 * this.Z0107) * n) + this.Z0077;
            this.Z0019[j] = this.Z0019[n3];
            this.Z0019[n3] = j;
        }
        this.Z0088 = 0;
        for (int k = 0; k < this.Z0021; ++k) {
            for (int l = 0; l < this.Z0020; ++l) {
                final int n4 = k * this.Z0020 + l + this.Z0077;
                for (int n5 = 0; n5 < 5; ++n5) {
                    int n6 = l + array[n5];
                    final int n7 = k + array2[n5];
                    if (n7 < this.Z0021) {
                        double z0107 = 0.0;
                        if (n6 >= this.Z0020) {
                            n6 = 0;
                            z0107 = this.Z0107;
                        }
                        else if (n6 < 0) {
                            n6 = this.Z0020 - 1;
                            z0107 = -this.Z0107;
                        }
                        final int n8 = n7 * this.Z0020 + n6 + this.Z0077;
                        for (int n9 = this.Z0019[n4]; n9 >= 0; n9 = this.Z0019[n9]) {
                            for (int n10 = this.Z0019[n8]; n10 >= 0; n10 = this.Z0019[n10]) {
                                if ((n4 != n8 || n10 < n9) && (n9 < this.Z0079 || n10 < this.Z0079)) {
                                    final double n11 = this.Z0115[n9] - this.Z0115[n10] - z0107;
                                    final double n12 = this.Z0116[n9] - this.Z0116[n10];
                                    final double n13 = 0.5 * (this.Z0006[n9] + this.Z0006[n10]);
                                    if (n11 * n11 + n12 * n12 < (this.Z0101 * n13 + this.Z0102) * (this.Z0101 * n13 + this.Z0102)) {
                                        this.Z0086[2 * this.Z0088] = n9;
                                        this.Z0086[2 * this.Z0088 + 1] = n10;
                                        ++this.Z0088;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void Z0124() {
        for (int i = 0; i < this.Z0077; ++i) {
            this.Z0104[i] = 0.0;
            this.Z0105[i] = 0.0;
        }
        for (int j = 0; j < this.Z0088; ++j) {
            final int n = this.Z0086[2 * j];
            final int n2 = this.Z0086[2 * j + 1];
            final double n3 = 0.5 * (this.Z0006[n] + this.Z0006[n2]);
            double n4 = this.Z0115[n] - this.Z0115[n2];
            final double n5 = this.Z0116[n] - this.Z0116[n2];
            if (n4 >= 0.5 * this.Z0107) {
                n4 -= this.Z0107;
            }
            else if (n4 < -0.5 * this.Z0107) {
                n4 += this.Z0107;
            }
            final double n6 = n4 * n4 + n5 * n5;
            if (n6 < this.Z0101 * n3 * (this.Z0101 * n3)) {
                final double n7 = n3 * n3 / n6;
                final double n8 = n7 * n7 * n7;
                final double n9 = 48.0 * n8 * (n8 - 0.5) * n7 / n3 - this.Z0042 * (n4 * (this.Z0113[n] - this.Z0113[n2]) + n5 * (this.Z0114[n] - this.Z0114[n2])) / n6;
                final double[] z0104 = this.Z0104;
                final int n10 = n;
                z0104[n10] += n9 * n4;
                final double[] z105 = this.Z0105;
                final int n11 = n;
                z105[n11] += n9 * n5;
                final double[] z106 = this.Z0104;
                final int n12 = n2;
                z106[n12] -= n9 * n4;
                final double[] z107 = this.Z0105;
                final int n13 = n2;
                z107[n13] -= n9 * n5;
            }
        }
        this.Z0123();
        for (int k = 0; k < this.Z0077; ++k) {
            final double[] z108 = this.Z0105;
            final int n14 = k;
            z108[n14] -= this.Z0048;
        }
    }
    
    void Z0123() {
        final int n = this.Z0021 - 1;
        for (int i = 0; i < this.Z0020; ++i) {
            for (int j = this.Z0019[n * this.Z0020 + i + this.Z0077]; j >= 0; j = this.Z0019[j]) {
                final double n2 = this.Z0116[j] - 0.5 * this.Z0108;
                final double n3 = n2 * n2;
                final double n4 = 0.5 * (this.Z0006[j] + 1.0);
                if (n3 < this.Z0101 * n4 * (this.Z0101 * n4)) {
                    final double n5 = n4 * n4 / n3;
                    final double n6 = n5 * n5 * n5;
                    final double n7 = 48.0 * n6 * (n6 - 0.5) * n5 / n4;
                    final double[] z0105 = this.Z0105;
                    final int n8 = j;
                    z0105[n8] += n7 * n2;
                }
            }
        }
    }
    
    void Z0129(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0079; ++i) {
                final double[] z0113 = this.Z0113;
                final int n2 = i;
                z0113[n2] += 0.5 * this.Z0027 * this.Z0104[i];
                final double[] z114 = this.Z0114;
                final int n3 = i;
                z114[n3] += 0.5 * this.Z0027 * this.Z0105[i];
                final double[] z115 = this.Z0115;
                final int n4 = i;
                z115[n4] += this.Z0027 * this.Z0113[i];
                final double[] z116 = this.Z0116;
                final int n5 = i;
                z116[n5] += this.Z0027 * this.Z0114[i];
            }
        }
        else {
            for (int j = 0; j < this.Z0079; ++j) {
                final double[] z117 = this.Z0113;
                final int n6 = j;
                z117[n6] += 0.5 * this.Z0027 * this.Z0104[j];
                final double[] z118 = this.Z0114;
                final int n7 = j;
                z118[n7] += 0.5 * this.Z0027 * this.Z0105[j];
            }
        }
    }
    
    void Z0122() {
        for (int i = 0; i < this.Z0079; ++i) {
            if (this.Z0115[i] >= 0.5 * this.Z0107) {
                final double[] z0115 = this.Z0115;
                final int n = i;
                z0115[n] -= this.Z0107;
            }
            else if (this.Z0115[i] < -0.5 * this.Z0107) {
                final double[] z116 = this.Z0115;
                final int n2 = i;
                z116[n2] += this.Z0107;
            }
        }
    }
    
    void Z0127() {
        this.Z0161 = this.Z0012 * this.Z0012;
        for (int i = 0; i < this.Z0079; ++i) {
            final double z0161 = this.Z0113[i] * this.Z0113[i] + this.Z0114[i] * this.Z0114[i];
            if (z0161 > this.Z0161) {
                this.Z0161 = z0161;
            }
            final double[] z162 = this.Z0007;
            final int n = i;
            z162[n] += this.Z0113[i] * this.Z0027;
        }
        if (this.Z0080 > 0 && this.Z0080 != this.Z0081) {
            this.Z0081 = this.Z0080;
            this.Z0126(1);
        }
    }
    
    void Z0128() {
        this.Z0146 = 0;
        this.Z0158 = this.Z0159;
        this.Z0151 = 0.0;
        this.Z0134();
        this.Z0081 = 0;
        this.Z0085 = 1;
        for (int i = 0; i < this.Z0079; ++i) {
            this.Z0006[i] = 1.0 - 0.1 * this.Z0103.nextDouble();
        }
        for (int j = 0; j < this.Z0078; ++j) {
            this.Z0006[this.Z0079 + j] = this.Z0008;
        }
        final double n = this.Z0107 / this.Z0054;
        int n2 = 0;
        for (int k = 0; k < this.Z0055; ++k) {
            final double n3 = k * n + this.Z0011 + this.Z0139 + 1.0;
            for (int l = 0; l < this.Z0054; ++l) {
                this.Z0115[n2] = (l + 0.5) * n - 0.5 * this.Z0107;
                this.Z0116[n2] = n3;
                ++n2;
            }
        }
        final double n4 = 1.0;
        for (int n5 = 0; n5 < this.Z0079; ++n5) {
            final double n6 = 6.283185307179586 * this.Z0103.nextDouble();
            this.Z0113[n5] = n4 * Math.cos(n6);
            this.Z0114[n5] = n4 * Math.sin(n6);
        }
        for (int n7 = 0; n7 < this.Z0077; ++n7) {
            this.Z0104[n7] = 0.0;
            this.Z0105[n7] = 0.0;
        }
        this.Z0137();
        this.Z0121();
        this.Z0124();
        this.Z0127();
        this.Z0126(0);
        this.Z0106 = true;
    }
    
    void Z0137() {
        double n = 0.0;
        for (int i = 0; i < this.Z0079; ++i) {
            n += this.Z0113[i];
        }
        for (int j = 0; j < this.Z0079; ++j) {
            final double[] z0113 = this.Z0113;
            final int n2 = j;
            z0113[n2] -= n / this.Z0079;
        }
    }
    
    void Z0126(final int n) {
        switch (n) {
            case 0: {
                for (int i = 0; i < this.Z0079; ++i) {
                    this.Z0007[i] = 0.0;
                }
                for (int j = 0; j < this.Z0082; ++j) {
                    this.Z0064[j] = 0.0;
                    this.Z0065[j] = 0;
                }
                break;
            }
            case 1: {
                final double n2 = 0.5 * (1.0 + this.Z0008) + this.Z0011;
                for (int k = 0; k < this.Z0082; ++k) {
                    final double[] z0064 = this.Z0064;
                    final int n3 = k;
                    z0064[n3] *= this.Z0065[k];
                }
                for (int l = 0; l < this.Z0079; ++l) {
                    int n4 = (int)((this.Z0116[l] - n2) / this.Z0066);
                    if (n4 > this.Z0082 - 1) {
                        n4 = this.Z0082 - 1;
                    }
                    final double[] z65 = this.Z0064;
                    final int n5 = n4;
                    z65[n5] += this.Z0007[l];
                    final int[] z66 = this.Z0065;
                    final int n6 = n4;
                    ++z66[n6];
                    this.Z0007[l] = 0.0;
                }
                for (int n7 = 0; n7 < this.Z0082; ++n7) {
                    if (this.Z0065[n7] > 0) {
                        final double[] z67 = this.Z0064;
                        final int n8 = n7;
                        z67[n8] /= this.Z0065[n7];
                    }
                }
                break;
            }
        }
    }
    
    void Z0133(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0031.getSize();
        final Insets insets = this.Z0031.getInsets();
        this.Z0015 = size.width - insets.left - insets.right;
        this.Z0016 = size.height - insets.top - insets.bottom;
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(new Rectangle2D.Double(insets.left, insets.top, this.Z0015, this.Z0016));
        final double n = this.Z0015 / this.Z0107;
        graphics2D.setPaint(Color.red);
        final double n2 = 0.5 * this.Z0107 - 0.45;
        final double n3 = 0.5 * this.Z0108 - 0.45;
        for (int i = 0; i < this.Z0077; ++i) {
            if (i == this.Z0079) {
                graphics2D.setPaint(Color.green);
            }
            int n4 = (int)(0.95 * this.Z0006[i] * n);
            if (n4 < 2) {
                n4 = 2;
            }
            final int n5 = (int)(n * (0.5 * this.Z0107 + this.Z0115[i])) - n4 / 2 + insets.left;
            final int n6 = (int)(n * (0.5 * this.Z0108 - this.Z0116[i])) - n4 / 2 + insets.top - 6;
            graphics2D.fill(new Ellipse2D.Double(n5, n6, n4, n4));
            final boolean b = this.Z0115[i] > -n2;
            final boolean b2 = this.Z0115[i] < n2;
            if (!b || !b2) {
                graphics2D.fill(new Ellipse2D.Double(n5 + (b ? (-this.Z0015) : this.Z0015), n6, n4, n4));
            }
        }
        final double n7 = 0.5 * (1.0 + this.Z0008) + this.Z0011;
        final double n8 = 0.8 * n * this.Z0066;
        final double n9 = n8 * Math.cos(2.5);
        final double n10 = n8 * Math.sin(2.5);
        graphics2D.setPaint(Color.yellow);
        for (int j = 0; j < this.Z0082; ++j) {
            final double n11 = 30.0 * this.Z0064[j] * this.Z0158;
            final int n12 = (int)(n * 0.5 * (this.Z0107 - n11));
            final int n13 = (int)(n * 0.5 * (this.Z0107 + n11));
            final int n14 = (int)(n * (0.5 * this.Z0108 - (this.Z0066 * (j + 0.5) + n7)));
            final double n15 = (this.Z0064[j] > 0.0) ? 1.0 : -1.0;
            final int n16 = n13 + (int)(n15 * n9);
            final int n17 = n14 - (int)(n15 * n10);
            final int n18 = n14 + (int)(n15 * n10);
            graphics2D.draw(new Line2D.Double(n12 + insets.left, n14 + insets.top - 6, n13 + insets.left, n14 + insets.top - 6));
            graphics2D.draw(new Line2D.Double(n16 + insets.left, n17 + insets.top - 6, n13 + insets.left, n14 + insets.top - 6));
            graphics2D.draw(new Line2D.Double(n13 + insets.left, n14 + insets.top - 6, n16 + insets.left, n18 + insets.top - 6));
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Gratchet.this.Z0133(graphics);
        }
    }
}
