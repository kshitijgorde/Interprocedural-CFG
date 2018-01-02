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
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vibseg extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0013;
    JSlider[] Z0125;
    JLabel[] Z0057;
    DrawingArea Z0032;
    Color Z0010;
    Random Z0085;
    double[] Z0097;
    double[] Z0098;
    double[] Z0095;
    double[] Z0096;
    double[] Z0086;
    double[] Z0087;
    double[] Z0003;
    double[] Z0004;
    double[] Z0121;
    double[] Z0045;
    double[] Z0046;
    double Z0089;
    double Z0090;
    double Z0027;
    double Z0083;
    double Z0028;
    double Z0084;
    double Z0030;
    double Z0140;
    double Z0043;
    double Z0047;
    double Z0007;
    double Z0009;
    double Z0138;
    double Z0137;
    double Z0123;
    double Z0012;
    double Z0011;
    double Z0006;
    double Z0120;
    double Z0122;
    double Z0119;
    double Z0136;
    double Z0005;
    double Z0040;
    double Z0041;
    double Z0008;
    int[] Z0018;
    int[] Z0075;
    int[] Z0048;
    int Z0014;
    int Z0015;
    int Z0049;
    int Z0050;
    int Z0067;
    int Z0070;
    int Z0019;
    int Z0020;
    int Z0078;
    int Z0076;
    int Z0074;
    int Z0077;
    int Z0127;
    int Z0128;
    int Z0071;
    int Z0072;
    int Z0068;
    int Z0069;
    boolean Z0094;
    boolean Z0088;
    
    public void init() {
        this.Z0117();
        this.Z0085 = new Random();
        this.Z0010 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0113(), "East");
        (this.Z0032 = new DrawingArea()).setFont(new Font("Serif", 1, 12));
        contentPane.add(this.Z0032, "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0113() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0010), panel.getBorder()));
        this.Z0013 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0099(0, panel2, "Reset");
        this.Z0101(panel2, 10, 0);
        this.Z0099(1, panel2, "Start");
        panel.add(panel2);
        this.Z0101(panel, 0, 20);
        this.Z0125 = new JSlider[1];
        this.Z0102(0, panel, this.Z0128, 1, 100, "Update");
        this.Z0101(panel, 0, 40);
        this.Z0057 = new JLabel[1];
        this.Z0100(0, panel, "     ");
        return panel;
    }
    
    void Z0099(final int n, final JPanel panel, final String s) {
        (this.Z0013[n] = new JButton(s)).setPreferredSize(this.Z0013[n].getPreferredSize());
        this.Z0013[n].addActionListener(this);
        panel.add(this.Z0013[n]);
    }
    
    void Z0100(final int n, final JPanel panel, final String s) {
        (this.Z0057[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0057[n]);
    }
    
    void Z0102(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0101(panel, 0, 3);
        (this.Z0125[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0125[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0125[n].setPreferredSize(preferredSize);
        this.Z0125[n].setMajorTickSpacing(n4 - n3);
        this.Z0125[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0125[n].setPaintLabels(true);
        this.Z0125[n].setPaintTicks(true);
        this.Z0125[n].addChangeListener(this);
        panel.add(this.Z0125[n]);
        this.Z0101(panel, 0, 5);
    }
    
    void Z0101(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0125[0]) {
                this.Z0128 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0013[0]) {
            this.Z0110();
        }
        else if (source == this.Z0013[1]) {
            this.Z0094 = !this.Z0094;
            this.Z0013[1].setText(this.Z0094 ? "Stop" : "Start");
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        this.Z0032.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, this.Z0010), BorderFactory.createRaisedBevelBorder()));
        this.Z0103();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0094) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0114();
                if (this.Z0127 % this.Z0128 != 0) {
                    continue;
                }
                this.Z0032.repaint();
                this.Z0032.getToolkit().sync();
                if (!this.Z0118(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0088) {
                    this.Z0088 = false;
                    this.Z0032.repaint();
                }
                if (!this.Z0118(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0118(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0117() {
        this.Z0049 = 70;
        this.Z0050 = 6;
        this.Z0043 = 5.0;
        this.Z0047 = 5.0;
        this.Z0138 = 0.4;
        this.Z0137 = 1.0;
        this.Z0128 = 30;
        this.Z0123 = 0.67;
        this.Z0012 = 1.3;
        this.Z0011 = 0.2;
        this.Z0120 = 2.0;
        this.Z0072 = 8;
        this.Z0119 = 0.99;
        this.Z0005 = 0.333;
        this.Z0040 = 0.5;
        this.Z0041 = 2.5;
        this.Z0008 = 5.0;
        this.Z0136 = 0.5;
        this.Z0121 = new double[2];
        this.Z0045 = new double[2];
        this.Z0046 = new double[2];
        this.Z0048 = new int[2];
    }
    
    void Z0103() {
        this.Z0028 = 0.9;
        this.Z0089 = this.Z0049 / Math.sqrt(this.Z0028);
        this.Z0090 = 2.0 * this.Z0008 + 3.0 * this.Z0137 + 3.0 * this.Z0050;
        this.Z0027 = 0.005;
        this.Z0083 = Math.pow(2.0, 0.16666666666666666);
        this.Z0019 = (int)(this.Z0089 / (this.Z0083 * this.Z0012 + this.Z0084));
        this.Z0020 = (int)(this.Z0090 / (this.Z0083 * this.Z0012 + this.Z0084));
        this.Z0006 = this.Z0089 - 5.0 * this.Z0083;
        this.Z0122 = this.Z0006 / this.Z0072;
        this.Z0121[0] = this.Z0122 * this.Z0119;
        this.Z0121[1] = this.Z0122 - this.Z0121[0];
        this.Z0069 = (int)(this.Z0050 / (this.Z0005 * this.Z0040));
        this.Z0108();
        this.Z0084 = 0.4;
        this.Z0076 = 5;
        this.Z0078 = this.Z0076 * this.Z0067;
        this.Z0112();
        this.Z0110();
        this.Z0094 = false;
    }
    
    void Z0108() {
        this.Z0070 = this.Z0049 * this.Z0050;
        this.Z0068 = 0;
        for (int i = 0; i < 2; ++i) {
            this.Z0048[i] = (int)(1.0 + Math.sqrt(this.Z0121[i] * this.Z0121[i] + this.Z0120 * this.Z0120) / (this.Z0040 * this.Z0005));
            this.Z0068 += this.Z0048[i] * this.Z0072;
            this.Z0045[i] = this.Z0121[i] / this.Z0048[i];
            this.Z0046[i] = this.Z0120 / this.Z0048[i];
        }
        final double[] z0046 = this.Z0046;
        final int n = 1;
        z0046[n] *= -1.0;
        this.Z0068 += 2 * this.Z0069;
        this.Z0067 = this.Z0070 + this.Z0068;
    }
    
    void Z0112() {
        this.Z0097 = new double[this.Z0067];
        this.Z0098 = new double[this.Z0067];
        this.Z0095 = new double[this.Z0067];
        this.Z0096 = new double[this.Z0067];
        this.Z0086 = new double[this.Z0067];
        this.Z0087 = new double[this.Z0067];
        this.Z0018 = new int[this.Z0067 + this.Z0019 * this.Z0020];
        this.Z0075 = new int[2 * this.Z0078];
        this.Z0003 = new double[this.Z0067];
        this.Z0004 = new double[this.Z0067];
    }
    
    void Z0114() {
        ++this.Z0127;
        this.Z0116();
        this.Z0111(1);
        this.Z0105();
        if (this.Z0074 > 0) {
            this.Z0074 = 0;
            this.Z0030 = 0.0;
            this.Z0104();
        }
        if (this.Z0094) {
            this.Z0107();
            this.Z0111(2);
            this.Z0109();
            this.Z0030 += Math.sqrt(this.Z0140) * this.Z0027;
            if (this.Z0030 > 0.5 * this.Z0084) {
                this.Z0074 = 1;
            }
        }
    }
    
    void Z0116() {
        final double n = this.Z0127 * this.Z0027;
        this.Z0071 = (int)(this.Z0138 * n);
        final double n2 = this.Z0138 * n - this.Z0071;
        this.Z0007 = -0.5 * this.Z0090 + this.Z0008 + this.Z0137 * (1.0 - Math.sin(6.283185307179586 * n2));
        this.Z0009 = -6.283185307179586 * this.Z0138 * this.Z0137 * Math.cos(6.283185307179586 * n2);
        double n3 = -0.5 * this.Z0006;
        double z0007 = this.Z0007;
        int z8 = this.Z0070;
        for (int i = 0; i < this.Z0072; ++i) {
            for (int j = 0; j < 2; ++j) {
                for (int k = 0; k < this.Z0048[j]; ++k) {
                    this.Z0097[z8] = n3;
                    this.Z0098[z8] = z0007;
                    this.Z0095[z8] = 0.0;
                    this.Z0096[z8] = this.Z0009;
                    n3 += this.Z0045[j];
                    z0007 += this.Z0046[j];
                    ++z8;
                }
            }
        }
        for (int l = 0; l < 2; ++l) {
            double n4;
            double z9;
            if (l == 0) {
                n4 = -0.5 * this.Z0006;
                z9 = this.Z0007;
            }
            else {
                n4 = 0.5 * this.Z0006;
                z9 = this.Z0007 + this.Z0120 + this.Z0041;
            }
            for (int n5 = 0; n5 < this.Z0069; ++n5) {
                this.Z0097[z8] = n4;
                this.Z0098[z8] = z9;
                this.Z0095[z8] = 0.0;
                this.Z0096[z8] = this.Z0009;
                z9 += this.Z0040 * this.Z0005;
                ++z8;
            }
        }
    }
    
    void Z0104() {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n = this.Z0019 / this.Z0089;
        final double n2 = this.Z0020 / this.Z0090;
        for (int i = this.Z0067; i < this.Z0067 + this.Z0019 * this.Z0020; ++i) {
            this.Z0018[i] = -1;
        }
        for (int j = 0; j < this.Z0067; ++j) {
            final int n3 = (int)((this.Z0098[j] + 0.5 * this.Z0090) * n2) * this.Z0019 + (int)((this.Z0097[j] + 0.5 * this.Z0089) * n) + this.Z0067;
            this.Z0018[j] = this.Z0018[n3];
            this.Z0018[n3] = j;
        }
        this.Z0077 = 0;
        for (int k = 0; k < this.Z0020; ++k) {
            for (int l = 0; l < this.Z0019; ++l) {
                final int n4 = k * this.Z0019 + l + this.Z0067;
                for (int n5 = 0; n5 < 5; ++n5) {
                    final int n6 = l + array[n5];
                    int n7 = k + array2[n5];
                    if (n6 >= 0) {
                        if (n6 < this.Z0019) {
                            double z0090 = 0.0;
                            if (n7 >= this.Z0020) {
                                n7 = 0;
                                z0090 = this.Z0090;
                            }
                            else if (n7 < 0) {
                                n7 = this.Z0020 - 1;
                                z0090 = -this.Z0090;
                            }
                            final int n8 = n7 * this.Z0019 + n6 + this.Z0067;
                            for (int n9 = this.Z0018[n4]; n9 >= 0; n9 = this.Z0018[n9]) {
                                for (int n10 = this.Z0018[n8]; n10 >= 0; n10 = this.Z0018[n10]) {
                                    if ((n4 != n8 || n10 < n9) && (n9 < this.Z0070 || n10 < this.Z0070)) {
                                        final double n11 = this.Z0097[n9] - this.Z0097[n10];
                                        final double n12 = this.Z0098[n9] - this.Z0098[n10] - z0090;
                                        final double n13 = 0.5 * (this.Z0003[n9] + this.Z0003[n10]);
                                        if (n11 * n11 + n12 * n12 < (this.Z0083 * n13 + this.Z0084) * (this.Z0083 * n13 + this.Z0084)) {
                                            this.Z0075[2 * this.Z0077] = n9;
                                            this.Z0075[2 * this.Z0077 + 1] = n10;
                                            ++this.Z0077;
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
    
    void Z0107() {
        for (int i = 0; i < this.Z0067; ++i) {
            this.Z0086[i] = 0.0;
            this.Z0087[i] = 0.0;
        }
        for (int j = 0; j < this.Z0077; ++j) {
            final int n = this.Z0075[2 * j];
            final int n2 = this.Z0075[2 * j + 1];
            final double n3 = 0.5 * (this.Z0003[n] + this.Z0003[n2]);
            final double n4 = this.Z0097[n] - this.Z0097[n2];
            double n5 = this.Z0098[n] - this.Z0098[n2];
            if (n5 >= 0.5 * this.Z0090) {
                n5 -= this.Z0090;
            }
            else if (n5 < -0.5 * this.Z0090) {
                n5 += this.Z0090;
            }
            final double n6 = n4 * n4 + n5 * n5;
            if (n6 < this.Z0083 * n3 * (this.Z0083 * n3)) {
                final double n7 = n3 * n3 / n6;
                final double n8 = n7 * n7 * n7;
                final double n9 = 48.0 * n8 * (n8 - 0.5) * n7 / n3 - this.Z0043 * (n4 * (this.Z0095[n] - this.Z0095[n2]) + n5 * (this.Z0096[n] - this.Z0096[n2])) / n6;
                final double[] z0086 = this.Z0086;
                final int n10 = n;
                z0086[n10] += n9 * n4;
                final double[] z87 = this.Z0087;
                final int n11 = n;
                z87[n11] += n9 * n5;
                final double[] z88 = this.Z0086;
                final int n12 = n2;
                z88[n12] -= n9 * n4;
                final double[] z89 = this.Z0087;
                final int n13 = n2;
                z89[n13] -= n9 * n5;
            }
        }
        this.Z0106();
        for (int k = 0; k < this.Z0070; ++k) {
            final double[] z90 = this.Z0086;
            final int n14 = k;
            z90[n14] /= this.Z0004[k];
            final double[] z91 = this.Z0087;
            final int n15 = k;
            z91[n15] /= this.Z0004[k];
        }
        for (int l = 0; l < this.Z0070; ++l) {
            final double[] z92 = this.Z0086;
            final int n16 = l;
            z92[n16] -= this.Z0136 * this.Z0095[l];
            final double[] z93 = this.Z0087;
            final int n17 = l;
            z93[n17] -= this.Z0136 * this.Z0096[l] + this.Z0047;
        }
    }
    
    void Z0106() {
        for (int i = 0; i < this.Z0019; i += this.Z0019 - 1) {
            for (int j = 0; j < this.Z0020; ++j) {
                for (int k = this.Z0018[j * this.Z0019 + i + this.Z0067]; k >= 0; k = this.Z0018[k]) {
                    double n;
                    if (i == 0) {
                        n = this.Z0097[k] + (0.5 * this.Z0089 + 0.2 * this.Z0083);
                    }
                    else {
                        n = this.Z0097[k] - (0.5 * this.Z0089 + 0.2 * this.Z0083);
                    }
                    final double n2 = n * n;
                    final double n3 = 0.5 * (this.Z0003[k] + 1.0);
                    if (n2 < this.Z0083 * n3 * (this.Z0083 * n3)) {
                        final double n4 = n3 * n3 / n2;
                        final double n5 = n4 * n4 * n4;
                        final double n6 = 48.0 * n5 * (n5 - 0.5) * n4 / n3;
                        final double[] z0086 = this.Z0086;
                        final int n7 = k;
                        z0086[n7] += n6 * n;
                    }
                }
            }
        }
    }
    
    void Z0105() {
        for (int i = 0; i < this.Z0070; ++i) {
            if (this.Z0098[i] >= 0.5 * this.Z0090) {
                this.Z0098[i] = 0.5 * this.Z0090 - 1.0E-4;
                if (this.Z0096[i] > 0.0) {
                    final double[] z0096 = this.Z0096;
                    final int n = i;
                    z0096[n] *= -1.0;
                }
            }
            else if (this.Z0098[i] < -0.5 * this.Z0090) {
                final double[] z97 = this.Z0098;
                final int n2 = i;
                z97[n2] += this.Z0090;
                if (this.Z0095[i] > 0.0) {
                    this.Z0095[i] = -5.0;
                }
                else {
                    this.Z0095[i] = 5.0;
                }
                this.Z0096[i] = -0.1;
            }
        }
    }
    
    void Z0111(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0070; ++i) {
                final double[] z0095 = this.Z0095;
                final int n2 = i;
                z0095[n2] += 0.5 * this.Z0027 * this.Z0086[i];
                final double[] z96 = this.Z0096;
                final int n3 = i;
                z96[n3] += 0.5 * this.Z0027 * this.Z0087[i];
                final double[] z97 = this.Z0097;
                final int n4 = i;
                z97[n4] += this.Z0027 * this.Z0095[i];
                final double[] z98 = this.Z0098;
                final int n5 = i;
                z98[n5] += this.Z0027 * this.Z0096[i];
            }
        }
        else {
            for (int j = 0; j < this.Z0070; ++j) {
                final double[] z99 = this.Z0095;
                final int n6 = j;
                z99[n6] += 0.5 * this.Z0027 * this.Z0086[j];
                final double[] z100 = this.Z0096;
                final int n7 = j;
                z100[n7] += 0.5 * this.Z0027 * this.Z0087[j];
            }
        }
    }
    
    void Z0109() {
        this.Z0140 = 0.0;
        for (int i = 0; i < this.Z0067; ++i) {
            final double z0140 = this.Z0095[i] * this.Z0095[i] + this.Z0096[i] * this.Z0096[i];
            if (z0140 > this.Z0140) {
                this.Z0140 = z0140;
            }
        }
    }
    
    void Z0110() {
        this.Z0127 = 0;
        this.Z0116();
        this.Z0074 = 1;
        for (int i = 0; i < this.Z0067; ++i) {
            double z0012;
            if (this.Z0085.nextDouble() < this.Z0011) {
                z0012 = this.Z0012;
            }
            else {
                z0012 = 1.0;
            }
            this.Z0003[i] = z0012 - 0.1 * this.Z0085.nextDouble();
        }
        for (int j = 0; j < this.Z0068; ++j) {
            this.Z0003[this.Z0070 + j] = this.Z0005;
        }
        for (int k = 0; k < this.Z0070; ++k) {
            this.Z0004[k] = this.Z0003[k] * this.Z0003[k];
        }
        double n2;
        final double n = n2 = 0.5 * this.Z0006 - this.Z0012 * this.Z0083;
        double n3 = this.Z0007 + this.Z0120;
        for (int l = 0; l < this.Z0070; ++l) {
            n2 += this.Z0003[l] * this.Z0083;
            if (n2 > n) {
                n2 = -n;
                n3 += this.Z0012 * this.Z0083;
            }
            this.Z0097[l] = n2;
            this.Z0098[l] = n3;
        }
        final double n4 = 1.0;
        for (int n5 = 0; n5 < this.Z0070; ++n5) {
            final double n6 = 6.283185307179586 * this.Z0085.nextDouble();
            this.Z0095[n5] = n4 * Math.cos(n6);
            this.Z0096[n5] = n4 * Math.sin(n6);
        }
        for (int n7 = 0; n7 < this.Z0067; ++n7) {
            this.Z0086[n7] = 0.0;
            this.Z0087[n7] = 0.0;
        }
        this.Z0104();
        this.Z0107();
        this.Z0109();
        this.Z0088 = true;
    }
    
    void Z0115(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0032.getSize();
        final Insets insets = this.Z0032.getInsets();
        this.Z0014 = size.width - insets.left - insets.right;
        this.Z0015 = size.height - insets.top - insets.bottom;
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(new Rectangle2D.Double(insets.left, insets.top, this.Z0014, this.Z0015));
        final double n = this.Z0014 / this.Z0089;
        final double n2 = 0.5 * (this.Z0015 - n * this.Z0090);
        for (int i = 0; i < this.Z0067; ++i) {
            if (this.Z0003[i] > 1.0) {
                graphics2D.setPaint(Color.red);
            }
            else if (this.Z0003[i] < 0.5) {
                graphics2D.setPaint(Color.green);
            }
            else {
                graphics2D.setPaint(Color.yellow);
            }
            final int n3 = (int)(0.99 * this.Z0003[i] * n);
            graphics2D.fill(new Ellipse2D.Double((int)(n * (0.5 * this.Z0089 + this.Z0097[i])) - n3 / 2 + insets.left, (int)(n2 + n * (0.5 * this.Z0090 - this.Z0098[i])) - n3 / 2 + insets.top - 2, n3, n3));
        }
        graphics2D.setPaint(Color.green);
        final int n4 = (int)(n2 - 5.0) + insets.top;
        graphics2D.draw(new Line2D.Double(insets.left, n4, this.Z0014 + insets.left, n4));
        final int n5 = (int)(n2 + n * this.Z0090 - 0.0) + insets.top;
        graphics2D.draw(new Line2D.Double(insets.left, n5, this.Z0014 + insets.left, n5));
        this.Z0057[0].setText("Cycle: " + String.valueOf(this.Z0071));
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Vibseg.this.Z0115(graphics);
        }
    }
}
