import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Insets;
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

public class Grshear extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0004;
    JSlider[] Z0109;
    DrawingArea Z0020;
    Color Z0003;
    Random Z0069;
    double[] Z0083;
    double[] Z0084;
    double[] Z0081;
    double[] Z0082;
    double[] Z0070;
    double[] Z0071;
    double Z0074;
    double Z0075;
    double Z0016;
    double Z0068;
    double Z0017;
    double Z0032;
    double Z0107;
    double Z0123;
    double Z0124;
    double Z0125;
    double Z0126;
    double Z0028;
    double Z0119;
    double Z0029;
    double Z0030;
    double Z0122;
    double Z0019;
    int[] Z0009;
    int[] Z0036;
    int Z0039;
    int Z0040;
    int Z0058;
    int Z0059;
    int Z0010;
    int Z0011;
    int Z0111;
    int Z0112;
    int Z0005;
    int Z0006;
    int Z0037;
    int Z0113;
    int left;
    int top;
    boolean Z0080;
    boolean Z0073;
    
    public void init() {
        this.Z0103();
        this.Z0069 = new Random();
        this.Z0003 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0099(), "East");
        (this.Z0020 = new DrawingArea()).setFont(new Font("Serif", 1, 12));
        contentPane.add(this.Z0020, "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0099() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0003), panel.getBorder()));
        this.Z0004 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0085(0, panel2, "Reset");
        this.Z0086(panel2, 10, 0);
        this.Z0085(1, panel2, "Start");
        panel.add(panel2);
        this.Z0086(panel, 0, 20);
        this.Z0109 = new JSlider[4];
        this.Z0087(0, panel, (int)(10.0 * this.Z0119), 0, 30, "Shear");
        this.Z0087(1, panel, (int)(10.0 * this.Z0029), 5, 50, "Load");
        this.Z0087(2, panel, (int)(10.0 * this.Z0030), 1, 50, "Spring");
        this.Z0087(3, panel, this.Z0112, 1, 50, "Update");
        return panel;
    }
    
    void Z0085(final int n, final JPanel panel, final String s) {
        (this.Z0004[n] = new JButton(s)).setPreferredSize(this.Z0004[n].getPreferredSize());
        this.Z0004[n].addActionListener(this);
        panel.add(this.Z0004[n]);
    }
    
    void Z0087(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0086(panel, 0, 3);
        (this.Z0109[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0109[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0109[n].setPreferredSize(preferredSize);
        this.Z0109[n].setMajorTickSpacing(n4 - n3);
        this.Z0109[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0109[n].setPaintLabels(true);
        this.Z0109[n].setPaintTicks(true);
        this.Z0109[n].addChangeListener(this);
        panel.add(this.Z0109[n]);
        this.Z0086(panel, 0, 5);
    }
    
    void Z0086(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0109[0]) {
                this.Z0119 = 0.1 * value;
            }
            else if (slider == this.Z0109[1]) {
                this.Z0029 = 0.1 * value;
            }
            else if (slider == this.Z0109[2]) {
                this.Z0030 = 0.1 * value;
            }
            else if (slider == this.Z0109[3]) {
                this.Z0112 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0004[0]) {
            this.Z0095();
        }
        else if (source == this.Z0004[1]) {
            this.Z0080 = !this.Z0080;
            this.Z0004[1].setText(this.Z0080 ? "Stop" : "Start");
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        this.Z0020.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, this.Z0003), BorderFactory.createRaisedBevelBorder()));
        this.Z0088();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0080) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0100();
                if (this.Z0111 % this.Z0112 != 0) {
                    continue;
                }
                this.Z0020.repaint();
                this.Z0020.getToolkit().sync();
                if (!this.Z0105(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0073) {
                    this.Z0073 = false;
                    this.Z0020.repaint();
                }
                if (!this.Z0105(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0105(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0103() {
        this.Z0039 = 50;
        this.Z0040 = 6;
        this.Z0032 = 5.0;
        this.Z0112 = 20;
        this.Z0016 = 0.005;
        this.Z0107 = 0.6;
        this.Z0028 = 1.3;
        this.Z0119 = 1.0;
        this.Z0029 = 2.5;
        this.Z0030 = 2.0;
        this.Z0113 = 20;
    }
    
    void Z0088() {
        final Dimension size = this.Z0020.getSize();
        final Insets insets = this.Z0020.getInsets();
        this.Z0005 = size.width - insets.left - insets.right;
        this.Z0006 = size.height - insets.top - insets.bottom;
        this.left = insets.left;
        this.top = insets.top;
        this.Z0017 = 0.9;
        if (this.Z0040 > this.Z0039) {
            this.Z0040 = this.Z0039;
        }
        this.Z0074 = this.Z0039 / Math.sqrt(this.Z0017);
        this.Z0075 = this.Z0107 * this.Z0074 + 2.0;
        this.Z0068 = Math.pow(2.0, 0.16666666666666666);
        this.Z0010 = (int)(this.Z0074 / this.Z0068);
        this.Z0011 = (int)(this.Z0075 / this.Z0068);
        this.Z0092();
        this.Z0098();
        this.Z0095();
        this.Z0080 = false;
    }
    
    void Z0098() {
        this.Z0083 = new double[this.Z0058];
        this.Z0084 = new double[this.Z0058];
        this.Z0081 = new double[this.Z0058];
        this.Z0082 = new double[this.Z0058];
        this.Z0070 = new double[this.Z0058];
        this.Z0071 = new double[this.Z0058];
        this.Z0009 = new int[this.Z0058 + this.Z0010 * this.Z0011];
        this.Z0036 = new int[this.Z0005 - 8];
    }
    
    void Z0100() {
        ++this.Z0111;
        this.Z0101();
        this.Z0096(1);
        this.Z0090();
        this.Z0096(2);
        this.Z0093();
        this.Z0089();
        if (this.Z0111 % this.Z0113 == 0) {
            this.Z0106();
        }
    }
    
    void Z0101() {
        final int n = (this.Z0058 - this.Z0059) / 2;
        final double n2 = this.Z0074 / n;
        for (int i = this.Z0059; i < this.Z0059 + n; ++i) {
            this.Z0083[i] = -0.5 * this.Z0074 + (i - this.Z0059) * n2;
            this.Z0084[i] = -0.5 * this.Z0075 + 0.5;
            this.Z0081[i] = 0.0;
            this.Z0082[i] = 0.0;
        }
        for (int j = this.Z0059 + n; j < this.Z0059 + 2 * n; ++j) {
            this.Z0083[j] = this.Z0123 - 0.5 * this.Z0074 + (j - this.Z0059 - n) * n2;
            this.Z0084[j] = this.Z0124;
            this.Z0081[j] = this.Z0125;
            this.Z0082[j] = this.Z0126;
        }
    }
    
    void Z0090() {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n = this.Z0010 / this.Z0074;
        final double n2 = this.Z0011 / this.Z0075;
        for (int i = this.Z0058; i < this.Z0058 + this.Z0010 * this.Z0011; ++i) {
            this.Z0009[i] = -1;
        }
        for (int j = 0; j < this.Z0058; ++j) {
            final int n3 = (int)((this.Z0084[j] + 0.5 * this.Z0075) * n2) * this.Z0010 + (int)((this.Z0083[j] + 0.5 * this.Z0074) * n) + this.Z0058;
            this.Z0009[j] = this.Z0009[n3];
            this.Z0009[n3] = j;
        }
        final double n4 = this.Z0068 * this.Z0068;
        for (int k = 0; k < this.Z0058; ++k) {
            this.Z0070[k] = 0.0;
            this.Z0071[k] = 0.0;
        }
        for (int l = 0; l < this.Z0011; ++l) {
            for (int n5 = 0; n5 < this.Z0010; ++n5) {
                final int n6 = l * this.Z0010 + n5 + this.Z0058;
                for (int n7 = 0; n7 < 5; ++n7) {
                    int n8 = n5 + array[n7];
                    final int n9 = l + array2[n7];
                    if (n9 < this.Z0011) {
                        double z0074 = 0.0;
                        if (n8 >= this.Z0010) {
                            n8 = 0;
                            z0074 = this.Z0074;
                        }
                        else if (n8 < 0) {
                            n8 = this.Z0010 - 1;
                            z0074 = -this.Z0074;
                        }
                        final int n10 = n9 * this.Z0010 + n8 + this.Z0058;
                        for (int n11 = this.Z0009[n6]; n11 >= 0; n11 = this.Z0009[n11]) {
                            for (int n12 = this.Z0009[n10]; n12 >= 0; n12 = this.Z0009[n12]) {
                                if ((n6 != n10 || n12 < n11) && (n11 < this.Z0059 || n12 < this.Z0059)) {
                                    final double n13 = this.Z0083[n11] - this.Z0083[n12] - z0074;
                                    final double n14 = this.Z0084[n11] - this.Z0084[n12];
                                    final double n15 = n13 * n13 + n14 * n14;
                                    if (n15 < n4) {
                                        final double n16 = 1.0 / n15;
                                        final double n17 = n16 * n16 * n16;
                                        final double n18 = 48.0 * n17 * (n17 - 0.5) * n16 - this.Z0032 * (n13 * (this.Z0081[n11] - this.Z0081[n12]) + n14 * (this.Z0082[n11] - this.Z0082[n12])) / n15;
                                        final double[] z75 = this.Z0070;
                                        final int n19 = n11;
                                        z75[n19] += n18 * n13;
                                        final double[] z76 = this.Z0071;
                                        final int n20 = n11;
                                        z76[n20] += n18 * n14;
                                        final double[] z77 = this.Z0070;
                                        final int n21 = n12;
                                        z77[n21] -= n18 * n13;
                                        final double[] z78 = this.Z0071;
                                        final int n22 = n12;
                                        z78[n22] -= n18 * n14;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void Z0096(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0059; ++i) {
                final double[] z0081 = this.Z0081;
                final int n2 = i;
                z0081[n2] += 0.5 * this.Z0016 * this.Z0070[i];
                final double[] z82 = this.Z0082;
                final int n3 = i;
                z82[n3] += 0.5 * this.Z0016 * this.Z0071[i];
                final double[] z83 = this.Z0083;
                final int n4 = i;
                z83[n4] += this.Z0016 * this.Z0081[i];
                final double[] z84 = this.Z0084;
                final int n5 = i;
                z84[n5] += this.Z0016 * this.Z0082[i];
            }
        }
        else {
            for (int j = 0; j < this.Z0059; ++j) {
                final double[] z85 = this.Z0081;
                final int n6 = j;
                z85[n6] += 0.5 * this.Z0016 * this.Z0070[j];
                final double[] z86 = this.Z0082;
                final int n7 = j;
                z86[n7] += 0.5 * this.Z0016 * this.Z0071[j];
            }
        }
    }
    
    void Z0089() {
        for (int i = 0; i < this.Z0058; ++i) {
            if (this.Z0083[i] >= 0.5 * this.Z0074) {
                final double[] z0083 = this.Z0083;
                final int n = i;
                z0083[n] -= this.Z0074;
            }
            else if (this.Z0083[i] < -0.5 * this.Z0074) {
                final double[] z84 = this.Z0083;
                final int n2 = i;
                z84[n2] += this.Z0074;
            }
        }
    }
    
    void Z0093() {
        final int n = (this.Z0058 - this.Z0059) / 2;
        final double n2 = this.Z0074 / n;
        double n3 = 0.0;
        double n4 = 0.0;
        for (int i = this.Z0059 + n; i < this.Z0059 + 2 * n; ++i) {
            n3 += this.Z0070[i];
            n4 += this.Z0071[i];
        }
        final double n5 = n3 / n;
        final double n6 = n4 / n;
        this.Z0019 += this.Z0119 * this.Z0016;
        if (this.Z0019 >= 0.5 * this.Z0074) {
            this.Z0019 -= this.Z0074;
        }
        double n7 = this.Z0019 - this.Z0122;
        if (Math.abs(n7) > 0.5 * this.Z0074) {
            n7 += this.Z0074 * ((n7 > 0.0) ? -1.0 : 1.0);
        }
        final double n8 = n5 + this.Z0030 * n7;
        final double n9 = n6 - this.Z0029 * this.Z0074;
        this.Z0125 += this.Z0016 * n8;
        this.Z0126 += this.Z0016 * n9;
        this.Z0123 += this.Z0016 * this.Z0125;
        this.Z0124 += this.Z0016 * this.Z0126;
        this.Z0122 += this.Z0016 * this.Z0125;
        if (this.Z0122 >= 0.5 * this.Z0074) {
            this.Z0122 -= this.Z0074;
        }
        else if (this.Z0122 < -0.5 * this.Z0074) {
            this.Z0122 += this.Z0074;
        }
        if (this.Z0123 < 0.0) {
            this.Z0123 += n2;
        }
        else if (this.Z0123 >= n2) {
            this.Z0123 -= n2;
        }
        if (this.Z0124 >= 0.5 * this.Z0075) {
            this.Z0124 = 0.5 * this.Z0075 - 0.01;
            if (this.Z0126 > 0.0) {
                this.Z0126 = -this.Z0126;
            }
        }
    }
    
    void Z0095() {
        this.Z0111 = 0;
        final double n2;
        final double n = n2 = this.Z0074 / this.Z0039;
        int n3 = 0;
        for (int i = 0; i < this.Z0040; ++i) {
            final double n4 = (i + 0.5) * n2 - 0.5 * this.Z0075 + 1.0;
            for (int j = 0; j < this.Z0039; ++j) {
                double n5 = (j + 0.25) * n - 0.5 * this.Z0074;
                if ((i & 0x1) != 0x0) {
                    n5 += 0.5 * n;
                }
                this.Z0083[n3] = n5;
                this.Z0084[n3] = n4;
                ++n3;
            }
        }
        final double n6 = 1.0;
        for (int k = 0; k < this.Z0058; ++k) {
            final double n7 = 6.283185307179586 * this.Z0069.nextDouble();
            this.Z0081[k] = n6 * Math.cos(n7);
            this.Z0082[k] = n6 * Math.sin(n7);
        }
        for (int l = 0; l < this.Z0058; ++l) {
            this.Z0070[l] = 0.0;
            this.Z0071[l] = 0.0;
        }
        this.Z0123 = 0.0;
        this.Z0124 = this.Z0084[this.Z0059 - 1] + 1.5;
        this.Z0125 = 0.0;
        this.Z0126 = 0.0;
        this.Z0122 = 0.0;
        this.Z0019 = 0.0;
        this.Z0101();
        this.Z0094();
        this.Z0073 = true;
    }
    
    void Z0092() {
        this.Z0059 = this.Z0039 * this.Z0040;
        this.Z0058 = this.Z0059 + 2 * (int)(this.Z0074 / this.Z0028);
    }
    
    void Z0102(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle2D.Double clip = new Rectangle2D.Double(this.left, this.top, this.Z0005, this.Z0006);
        final Shape clip2 = graphics2D.getClip();
        graphics2D.setClip(clip);
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(clip);
        final double n = this.Z0005 / this.Z0074;
        graphics2D.setPaint(Color.red);
        final int n2 = (int)(0.8 * n);
        final int n3 = (int)(0.9 * this.Z0006 - n * this.Z0075);
        final double n4 = 0.5 * this.Z0074 - 0.45;
        for (int i = 0; i < this.Z0058; ++i) {
            final int n5 = (int)(n * (0.5 * this.Z0074 + this.Z0083[i])) - n2 / 2 + this.left;
            final int n6 = (int)(n * (0.5 * this.Z0075 - this.Z0084[i])) + n3 - n2 / 2 + this.top;
            graphics2D.fill(new Ellipse2D.Double(n5, n6, n2, n2));
            final boolean b = this.Z0083[i] > -n4;
            final boolean b2 = this.Z0083[i] < n4;
            if (!b || !b2) {
                graphics2D.fill(new Ellipse2D.Double(n5 + (b ? (-this.Z0005) : this.Z0005), n6, n2, n2));
            }
        }
        graphics2D.setPaint(Color.yellow);
        int n7 = (int)(n * (0.5 * this.Z0075 - this.Z0124)) + n3;
        graphics2D.draw(new Line2D.Double(this.left, n7 + this.top, this.Z0005 + this.left, n7 + this.top));
        final int n8 = (int)(n * (0.5 * this.Z0074 + this.Z0122));
        graphics2D.draw(new Line2D.Double(n8 + this.left, n7 + this.top, n8 + this.left, n7 - 30 + this.top));
        final int n9 = (int)(n * (0.5 * this.Z0074 + this.Z0019));
        graphics2D.draw(new Line2D.Double(n9 + this.left, n7 + this.top - 4, n9 + this.left, n7 - 30 + 4 + this.top));
        n7 -= 15;
        final int abs = Math.abs(n8 - n9);
        if (abs < this.Z0005 / 2) {
            if (n9 > n8) {
                this.Z0091(graphics2D, n8, n9, n7, abs);
            }
            else {
                this.Z0091(graphics2D, n9, n8, n7, abs);
            }
        }
        else {
            final int abs2 = Math.abs(abs - this.Z0005);
            final int n10 = (n8 > n9) ? n8 : n9;
            this.Z0091(graphics2D, n10, this.Z0005, n7, abs2);
            this.Z0091(graphics2D, n8 + n9 - n10, 0, n7, -abs2);
        }
        graphics2D.setPaint(Color.green);
        this.Z0104(graphics2D);
        graphics2D.setClip(clip2);
    }
    
    void Z0091(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        final int n5 = 8;
        final double n6 = n4 / (n5 - 1);
        int n7 = -6;
        double n8 = n;
        int n9 = n3;
        for (int i = 0; i < n5; ++i) {
            final double n10 = n8;
            final int n11 = n9;
            if (i == 0) {
                n8 = n10 + 0.5 * n6;
                n9 = n11 + n7;
            }
            else if (i == n5 - 1) {
                n8 = n2;
                n9 = n3;
            }
            else {
                n8 = n10 + n6;
                n9 = n11 + 2 * n7;
            }
            graphics2D.draw(new Line2D.Double((int)(n10 + 0.5) + this.left, n11 + this.top, (int)(n8 + 0.5) + this.left, n9 + this.top));
            n7 = -n7;
        }
    }
    
    void Z0094() {
        this.Z0037 = 0;
        for (int i = 0; i < this.Z0005 - 8; ++i) {
            this.Z0036[i] = -1;
        }
    }
    
    void Z0106() {
        double n = this.Z0019 - this.Z0122;
        if (Math.abs(n) > 0.5 * this.Z0074) {
            n += this.Z0074 * ((n > 0.0) ? -1.0 : 1.0);
        }
        this.Z0036[this.Z0037] = (int)(0.5 * (this.Z0006 - 8) + 6.0 * n);
        if (++this.Z0037 == this.Z0005 - 8) {
            this.Z0037 = 0;
        }
    }
    
    void Z0104(final Graphics2D graphics2D) {
        for (int i = 0; i < this.Z0005 - 8; ++i) {
            int n = this.Z0037 + i;
            if (n >= this.Z0005 - 8) {
                n -= this.Z0005 - 8;
            }
            graphics2D.draw(new Line2D.Double(this.left + i + 4, this.Z0006 + this.top - 6 - this.Z0036[n], this.left + i + 4, this.Z0006 + this.top - 5 - this.Z0036[n]));
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Grshear.this.Z0102(graphics);
        }
    }
}
