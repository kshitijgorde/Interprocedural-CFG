import java.awt.Insets;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
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

public class Grchute extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0007;
    JSlider[] Z0127;
    DrawingArea Z0024;
    Color Z0006;
    Random Z0086;
    double[] Z0101;
    double[] Z0102;
    double[] Z0099;
    double[] Z0100;
    double[] Z0087;
    double[] Z0088;
    double[] s;
    double[] Z0133;
    double[] Z0124;
    double[] Z0003;
    double[] Z0004;
    double Z0091;
    double Z0092;
    double Z0044;
    double Z0045;
    double Z0126;
    double Z0048;
    double Z0020;
    double Z0083;
    double Z0021;
    double Z0085;
    double Z0023;
    double Z0142;
    double Z0035;
    double Z0036;
    double Z0043;
    double Z0046;
    double Z0042;
    double Z0033;
    int[] Z0010;
    int[] Z0072;
    int[] Z0005;
    int Z0049;
    int Z0050;
    int Z0066;
    int Z0068;
    int Z0069;
    int Z0067;
    int Z0013;
    int Z0014;
    int Z0011;
    int Z0012;
    int Z0075;
    int Z0073;
    int Z0071;
    int Z0074;
    int Z0131;
    int Z0132;
    boolean Z0098;
    boolean Z0090;
    
    public void init() {
        this.Z0122();
        this.Z0086 = new Random();
        this.Z0006 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0119(), "East");
        contentPane.add(this.Z0024 = new DrawingArea(), "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0119() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0006), panel.getBorder()));
        this.Z0007 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0103(0, panel2, "Reset");
        this.Z0104(panel2, 10, 0);
        this.Z0103(1, panel2, "Start");
        panel.add(panel2);
        this.Z0104(panel, 0, 20);
        this.Z0127 = new JSlider[4];
        this.Z0105(0, panel, (int)(10.0 * this.Z0126), 10, 50, "Size (X 10)");
        this.Z0105(1, panel, (int)(10.0 * this.Z0046), 0, 50, "Gravity");
        this.Z0105(2, panel, (int)this.Z0042, 0, 45, "Slope");
        this.Z0105(3, panel, this.Z0132, 1, 100, "Update");
        return panel;
    }
    
    void Z0103(final int n, final JPanel panel, final String s) {
        (this.Z0007[n] = new JButton(s)).setPreferredSize(this.Z0007[n].getPreferredSize());
        this.Z0007[n].addActionListener(this);
        panel.add(this.Z0007[n]);
    }
    
    void Z0105(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0104(panel, 0, 3);
        (this.Z0127[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0127[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0127[n].setPreferredSize(preferredSize);
        this.Z0127[n].setMajorTickSpacing(n4 - n3);
        this.Z0127[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0127[n].setPaintLabels(true);
        this.Z0127[n].setPaintTicks(true);
        this.Z0127[n].addChangeListener(this);
        panel.add(this.Z0127[n]);
        this.Z0104(panel, 0, 5);
    }
    
    void Z0104(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        boolean b = false;
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0127[0]) {
                this.Z0126 = 0.1 * value;
                this.Z0106();
            }
            else if (slider == this.Z0127[1]) {
                this.Z0046 = 0.1 * value;
                b = true;
            }
            else if (slider == this.Z0127[2]) {
                this.Z0042 = value;
                b = true;
            }
            else if (slider == this.Z0127[3]) {
                this.Z0132 = value;
            }
            if (b) {
                this.Z0044 = this.Z0046 * Math.cos(3.141592653589793 * (90.0 - this.Z0042) / 180.0);
                this.Z0045 = -this.Z0046 * Math.sin(3.141592653589793 * (90.0 - this.Z0042) / 180.0);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0007[0]) {
            this.Z0115();
        }
        else if (source == this.Z0007[1]) {
            this.Z0098 = !this.Z0098;
            this.Z0007[1].setText(this.Z0098 ? "Stop" : "Start");
            this.Z0127[0].setEnabled(!this.Z0098);
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0024.getSize();
        final int n = size.width - size.height;
        this.Z0024.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0006), BorderFactory.createRaisedBevelBorder()));
        this.Z0106();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0098) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0120();
                if (this.Z0131 % this.Z0132 != 0) {
                    continue;
                }
                this.Z0024.repaint();
                this.Z0024.getToolkit().sync();
                if (!this.Z0123(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0090) {
                    this.Z0090 = false;
                    this.Z0024.repaint();
                }
                if (!this.Z0123(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0123(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0122() {
        this.Z0067 = 24;
        this.Z0033 = 1.2;
        this.Z0126 = 4.0;
        this.Z0069 = 1;
        this.Z0046 = 4.0;
        this.Z0042 = 20.0;
        this.Z0048 = 0.125;
        this.Z0036 = 0.5;
        this.Z0035 = 10.0;
        this.Z0085 = 0.4;
        this.Z0132 = 10;
    }
    
    void Z0106() {
        this.Z0021 = 0.9;
        this.Z0049 = this.Z0067;
        this.Z0050 = this.Z0067;
        this.Z0091 = (this.Z0049 + 1) / Math.sqrt(this.Z0021);
        this.Z0092 = this.Z0091;
        this.Z0020 = 0.005;
        this.Z0083 = Math.pow(2.0, 0.16666666666666666);
        this.Z0013 = (int)(this.Z0091 / (this.Z0083 + this.Z0085));
        this.Z0014 = (int)(this.Z0092 / (this.Z0083 + this.Z0085));
        this.Z0011 = (int)(this.Z0091 / (this.Z0083 * this.Z0126 + this.Z0085));
        this.Z0012 = (int)(this.Z0092 / (this.Z0083 * this.Z0126 + this.Z0085));
        this.Z0044 = this.Z0046 * Math.cos(3.141592653589793 * (90.0 - this.Z0042) / 180.0);
        this.Z0045 = -this.Z0046 * Math.sin(3.141592653589793 * (90.0 - this.Z0042) / 180.0);
        this.Z0111();
        this.Z0073 = 5;
        this.Z0075 = this.Z0073 * this.Z0066;
        this.Z0118();
        this.Z0115();
        this.Z0098 = false;
    }
    
    void Z0118() {
        this.Z0101 = new double[this.Z0066];
        this.Z0102 = new double[this.Z0066];
        this.Z0099 = new double[this.Z0066];
        this.Z0100 = new double[this.Z0066];
        this.Z0087 = new double[this.Z0066];
        this.Z0088 = new double[this.Z0066];
        this.s = new double[this.Z0066];
        this.Z0133 = new double[this.Z0066];
        this.Z0124 = new double[this.Z0066];
        this.Z0010 = new int[this.Z0066 + this.Z0013 * this.Z0014];
        this.Z0072 = new int[2 * this.Z0075];
        this.Z0003 = new double[this.Z0066];
        this.Z0004 = new double[this.Z0066];
        this.Z0005 = new int[this.Z0066];
    }
    
    void Z0120() {
        ++this.Z0131;
        this.Z0116(1);
        this.Z0109();
        if (this.Z0071 > 0) {
            this.Z0071 = 0;
            this.Z0023 = 0.0;
            this.Z0107();
        }
        this.Z0110();
        this.Z0116(2);
        this.Z0113();
        this.Z0023 += Math.sqrt(this.Z0142) * this.Z0020;
        if (this.Z0023 > 0.5 * this.Z0085) {
            this.Z0071 = 1;
        }
    }
    
    void Z0107() {
        this.Z0108(this.Z0074 = 0, this.Z0013, this.Z0014, this.Z0083 + this.Z0085);
        this.Z0108(1, this.Z0011, this.Z0012, this.Z0083 * this.Z0126 + this.Z0085);
    }
    
    void Z0108(final int n, final int n2, final int n3, final double n4) {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n5 = n2 / this.Z0091;
        final double n6 = n3 / this.Z0092;
        final double n7 = n4 * n4;
        for (int i = this.Z0066; i < this.Z0066 + n2 * n3; ++i) {
            this.Z0010[i] = -1;
        }
        for (int j = 0; j < this.Z0066; ++j) {
            final int n8 = (int)((this.Z0102[j] + 0.5 * this.Z0092) * n6) * n2 + (int)((this.Z0101[j] + 0.5 * this.Z0091) * n5) + this.Z0066;
            this.Z0010[j] = this.Z0010[n8];
            this.Z0010[n8] = j;
        }
        for (int k = 0; k < n3; ++k) {
            for (int l = 0; l < n2; ++l) {
                final int n9 = k * n2 + l + this.Z0066;
                for (int n10 = 0; n10 < 5; ++n10) {
                    int n11 = l + array[n10];
                    final int n12 = k + array2[n10];
                    if (n12 < n3) {
                        double z0091 = 0.0;
                        if (n11 >= n2) {
                            n11 = 0;
                            z0091 = this.Z0091;
                        }
                        else if (n11 < 0) {
                            n11 = n2 - 1;
                            z0091 = -this.Z0091;
                        }
                        final int n13 = n12 * n2 + n11 + this.Z0066;
                        for (int n14 = this.Z0010[n9]; n14 >= 0; n14 = this.Z0010[n14]) {
                            for (int n15 = this.Z0010[n13]; n15 >= 0; n15 = this.Z0010[n15]) {
                                if (((this.Z0005[n14] == n && this.Z0005[n15] == n) || (n == 1 && this.Z0005[n14] != this.Z0005[n15])) && (n9 != n13 || n15 < n14)) {
                                    final double n16 = this.Z0101[n14] - this.Z0101[n15] - z0091;
                                    final double n17 = this.Z0102[n14] - this.Z0102[n15];
                                    if (n16 * n16 + n17 * n17 < n7) {
                                        this.Z0072[2 * this.Z0074] = n14;
                                        this.Z0072[2 * this.Z0074 + 1] = n15;
                                        ++this.Z0074;
                                    }
                                }
                            }
                        }
                    }
                }
                int n18 = 0;
                if (k == n3 - 1) {
                    n18 = -4;
                }
                if (n18 != 0) {
                    for (int n19 = this.Z0010[n9]; n19 >= 0; n19 = this.Z0010[n19]) {
                        this.Z0072[2 * this.Z0074] = n19;
                        this.Z0072[2 * this.Z0074 + 1] = n18;
                        ++this.Z0074;
                    }
                }
            }
        }
    }
    
    void Z0110() {
        for (int i = 0; i < this.Z0066; ++i) {
            this.Z0087[i] = 0.0;
            this.Z0088[i] = 0.0;
            this.Z0124[i] = 0.0;
        }
        for (int j = 0; j < this.Z0074; ++j) {
            if (this.Z0072[j * 2 + 1] >= 0) {
                this.Z0112(this.Z0072[2 * j], this.Z0072[2 * j + 1]);
            }
            else {
                final int n = this.Z0072[j * 2];
                final int n2 = this.Z0072[2 * j + 1];
                final double n3 = this.Z0102[n] - 0.5 * this.Z0092;
                final double n4 = 0.5 * (1.0 + this.Z0003[n]);
                final double n5 = n3 * n3;
                if (n5 < this.Z0083 * this.Z0083 * n4 * n4) {
                    final double n6 = n4 * n4 / n5;
                    final double n7 = n6 * n6 * n6;
                    final double n8 = 48.0 * n7 * (n7 - 0.5) * n6 / n4;
                    final double[] z0088 = this.Z0088;
                    final int n9 = n;
                    z0088[n9] += n8 * n3;
                }
            }
        }
        for (int k = 0; k < this.Z0068; ++k) {
            final double[] z89 = this.Z0087;
            final int n10 = k;
            z89[n10] += this.Z0004[k] * this.Z0044;
            final double[] z90 = this.Z0088;
            final int n11 = k;
            z90[n11] += this.Z0004[k] * this.Z0045;
        }
        for (int l = 0; l < this.Z0068; ++l) {
            final double[] z91 = this.Z0087;
            final int n12 = l;
            z91[n12] /= this.Z0004[l];
            final double[] z92 = this.Z0088;
            final int n13 = l;
            z92[n13] /= this.Z0004[l];
            final double[] z93 = this.Z0124;
            final int n14 = l;
            z93[n14] *= 0.5 / (this.Z0048 * this.Z0004[l] * this.Z0003[l]);
        }
    }
    
    void Z0112(final int n, final int n2) {
        final double n3 = 0.5 * (this.Z0003[n] + this.Z0003[n2]);
        double n4 = this.Z0101[n] - this.Z0101[n2];
        final double n5 = this.Z0102[n] - this.Z0102[n2];
        if (n4 >= 0.5 * this.Z0091) {
            n4 -= this.Z0091;
        }
        else if (n4 < -0.5 * this.Z0091) {
            n4 += this.Z0091;
        }
        final double n6 = n4 * n4 + n5 * n5;
        if (n6 < this.Z0083 * this.Z0083 * n3 * n3) {
            final double n7 = n3 * n3 / n6;
            final double n8 = n7 * n7 * n7;
            final double n9 = 48.0 * n8 * (n8 - 0.5) * n7 / n3;
            final double n10 = this.Z0099[n] - this.Z0099[n2];
            final double n11 = this.Z0100[n] - this.Z0100[n2];
            final double n12 = n9 - this.Z0035 * (n4 * n10 + n5 * n11) / n6;
            final double[] z0087 = this.Z0087;
            z0087[n] += n12 * n4;
            final double[] z88 = this.Z0088;
            z88[n] += n12 * n5;
            final double[] z89 = this.Z0087;
            z89[n2] -= n12 * n4;
            final double[] z90 = this.Z0088;
            z90[n2] -= n12 * n5;
            final double n13 = (this.Z0003[n] * this.Z0133[n] + this.Z0003[n2] * this.Z0133[n2]) / (this.Z0003[n] + this.Z0003[n2]);
            final double n14 = n10 + n13 * n5;
            final double n15 = n11 - n13 * n4;
            final double sqrt = Math.sqrt(n14 * n14 + n15 * n15);
            final double n16 = this.Z0036 * Math.abs(n12) * Math.sqrt(n6);
            double n17;
            if (this.Z0035 * sqrt > n16) {
                n17 = -n16 / sqrt;
            }
            else {
                n17 = -this.Z0035;
            }
            final double[] z91 = this.Z0087;
            z91[n] += n17 * n14;
            final double[] z92 = this.Z0088;
            z92[n] += n17 * n15;
            final double[] z93 = this.Z0087;
            z93[n2] -= n17 * n14;
            final double[] z94 = this.Z0088;
            z94[n2] -= n17 * n15;
            if (n4 * n15 - n5 * n14 < 0.0) {
                n17 = -n17;
            }
            final double[] z95 = this.Z0124;
            z95[n] -= n17 * sqrt;
            final double[] z96 = this.Z0124;
            z96[n2] -= n17 * sqrt;
        }
    }
    
    void Z0116(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0068; ++i) {
                final double[] z0099 = this.Z0099;
                final int n2 = i;
                z0099[n2] += 0.5 * this.Z0020 * this.Z0087[i];
                final double[] z100 = this.Z0100;
                final int n3 = i;
                z100[n3] += 0.5 * this.Z0020 * this.Z0088[i];
                final double[] z101 = this.Z0101;
                final int n4 = i;
                z101[n4] += this.Z0020 * this.Z0099[i];
                final double[] z102 = this.Z0102;
                final int n5 = i;
                z102[n5] += this.Z0020 * this.Z0100[i];
                final double[] z103 = this.Z0133;
                final int n6 = i;
                z103[n6] += 0.5 * this.Z0020 * this.Z0124[i];
                final double[] s = this.s;
                final int n7 = i;
                s[n7] += this.Z0020 * this.Z0133[i];
                if (this.s[i] <= -3.141592653589793) {
                    final double[] s2 = this.s;
                    final int n8 = i;
                    s2[n8] += 6.283185307179586;
                }
                else if (this.s[i] > 3.141592653589793) {
                    final double[] s3 = this.s;
                    final int n9 = i;
                    s3[n9] -= 6.283185307179586;
                }
            }
        }
        else {
            for (int j = 0; j < this.Z0068; ++j) {
                final double[] z104 = this.Z0099;
                final int n10 = j;
                z104[n10] += 0.5 * this.Z0020 * this.Z0087[j];
                final double[] z105 = this.Z0100;
                final int n11 = j;
                z105[n11] += 0.5 * this.Z0020 * this.Z0088[j];
                final double[] z106 = this.Z0133;
                final int n12 = j;
                z106[n12] += 0.5 * this.Z0020 * this.Z0124[j];
            }
        }
    }
    
    void Z0109() {
        for (int i = 0; i < this.Z0068; ++i) {
            if (this.Z0101[i] >= 0.5 * this.Z0091) {
                final double[] z0101 = this.Z0101;
                final int n = i;
                z0101[n] -= this.Z0091;
            }
            else if (this.Z0101[i] < -0.5 * this.Z0091) {
                final double[] z102 = this.Z0101;
                final int n2 = i;
                z102[n2] += this.Z0091;
            }
        }
    }
    
    void Z0113() {
        this.Z0142 = 0.0;
        for (int i = 0; i < this.Z0068; ++i) {
            final double z0142 = this.Z0099[i] * this.Z0099[i] + this.Z0100[i] * this.Z0100[i];
            if (z0142 > this.Z0142) {
                this.Z0142 = z0142;
            }
        }
    }
    
    void Z0111() {
        this.Z0068 = this.Z0049 * this.Z0050;
        this.Z0066 = this.Z0068 + (int)(this.Z0091 / this.Z0033);
        this.Z0075 = this.Z0073 * this.Z0066;
    }
    
    void Z0115() {
        this.Z0131 = 0;
        this.Z0071 = 1;
        final double n = this.Z0091 / (this.Z0049 + 1);
        final double n2 = this.Z0092 / (this.Z0050 + 1);
        int i;
        for (i = 0; i < this.Z0069; ++i) {
            this.Z0101[i] = (i - 0.5 * (this.Z0069 - 1)) * this.Z0091 / this.Z0069;
            this.Z0102[i] = -0.5 * this.Z0092 + 0.5 * this.Z0083 * (2.0 + this.Z0126);
            this.Z0005[i] = 1;
            this.Z0003[i] = this.Z0126;
        }
        final double pow = Math.pow(this.Z0083 * (1.0 + this.Z0126) * 0.5, 2.0);
        for (int j = 0; j < this.Z0050; ++j) {
            final double n3 = (j + 1) * n2 - 0.5 * this.Z0092;
            for (int k = 0; k < this.Z0049; ++k) {
                final double n4 = (k + 1) * n - 0.5 * this.Z0091;
                int l;
                for (l = 0; l < this.Z0069; ++l) {
                    final double n5 = n4 - this.Z0101[l];
                    final double n6 = n3 - this.Z0102[l];
                    if (n5 * n5 + n6 * n6 < pow) {
                        break;
                    }
                }
                if (l == this.Z0069) {
                    this.Z0101[i] = n4;
                    this.Z0102[i] = n3;
                    this.Z0005[i] = 0;
                    this.Z0003[i] = 1.0 - 0.2 * this.Z0086.nextDouble();
                    ++i;
                }
            }
        }
        this.Z0068 = i;
        this.Z0066 = this.Z0068;
        final int n7 = (int)(this.Z0091 / this.Z0033);
        this.Z0066 += n7;
        final double n8 = this.Z0091 / n7;
        for (int n9 = 0; n9 < n7; ++n9) {
            this.Z0101[i] = -this.Z0091 * 0.5 + (n9 + 0.5) * n8;
            this.Z0102[i] = -this.Z0092 * 0.5;
            this.Z0005[i] = 0;
            this.Z0003[i] = 1.0;
            ++i;
        }
        for (int n10 = 0; n10 < this.Z0066; ++n10) {
            this.Z0004[n10] = this.Z0003[n10] * this.Z0003[n10];
        }
        for (int n11 = 0; n11 < this.Z0068; ++n11) {
            final double n12 = 6.283185307179586 * this.Z0086.nextDouble();
            this.Z0099[n11] = Math.cos(n12);
            this.Z0100[n11] = Math.sin(n12);
        }
        for (int z0068 = this.Z0068; z0068 < this.Z0066; ++z0068) {
            this.Z0099[z0068] = 0.0;
            this.Z0100[z0068] = 0.0;
        }
        for (int n13 = 0; n13 < this.Z0066; ++n13) {
            this.Z0087[n13] = 0.0;
            this.Z0088[n13] = 0.0;
            this.s[n13] = 0.0;
            this.Z0133[n13] = 0.0;
            this.Z0124[n13] = 0.0;
        }
        this.Z0090 = true;
    }
    
    void Z0121(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0024.getSize();
        final Insets insets = this.Z0024.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        final int min = Math.min(n, n2);
        final int n3 = insets.left + (n - min) / 2;
        final int n4 = insets.top + (n2 - min) / 2;
        final Rectangle2D.Double clip = new Rectangle2D.Double(n3, n4, min, min);
        final Shape clip2 = graphics2D.getClip();
        graphics2D.setClip(clip);
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(clip);
        graphics2D.setPaint(Color.green);
        final double n5 = min / this.Z0091;
        for (int i = 0; i < this.Z0066; ++i) {
            final int n6 = (int)(0.9 * n5 * this.Z0003[i]);
            if (this.Z0005[i] == 1) {
                graphics2D.setPaint(Color.red);
            }
            final int n7 = (int)(n5 * (0.5 * this.Z0091 + this.Z0101[i])) - n6 / 2 + n3;
            final int n8 = (int)(n5 * (0.5 * this.Z0092 - this.Z0102[i])) - n6 / 2 + n4;
            graphics2D.fill(new Ellipse2D.Double(n7, n8, n6, n6));
            final double n9 = 0.5 * (this.Z0091 - this.Z0003[i]);
            final boolean b = this.Z0101[i] > -n9;
            final boolean b2 = this.Z0101[i] < n9;
            if (!b || !b2) {
                graphics2D.fill(new Ellipse2D.Double(n7 + (b ? (-min) : min), n8, n6, n6));
            }
            if (this.Z0005[i] == 1) {
                graphics2D.setPaint(Color.yellow);
                final int n10 = n7 + n6 / 2;
                final int n11 = n8 + n6 / 2;
                graphics2D.draw(new Line2D.Double(n10, n11, (int)(n10 + 0.5 * Math.cos(this.s[i]) * n6), (int)(n11 - 0.5 * Math.sin(this.s[i]) * n6)));
                graphics2D.setPaint(Color.green);
            }
        }
        graphics2D.setClip(clip2);
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Grchute.this.Z0121(graphics);
        }
    }
}
