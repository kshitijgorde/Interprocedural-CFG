import java.text.NumberFormat;
import java.awt.Insets;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
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
import java.awt.Component;
import java.util.Random;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mddisk extends JApplet implements Runnable, ActionListener, ChangeListener, ItemListener
{
    Thread Z0002;
    JButton[] Z0004;
    JSlider[] Z0127;
    JLabel[] Z0048;
    JCheckBox[] Z0010;
    DrawingArea Z0021;
    Color Z0003;
    Random Z0080;
    double[] Z0095;
    double[] Z0097;
    double[] Z0093;
    double[] Z0094;
    double[] Z0081;
    double[] Z0082;
    double[] Z0096;
    double[] Z0098;
    double Z0085;
    double Z0086;
    double Z0016;
    double Z0077;
    double Z0136;
    double Z0017;
    double Z0138;
    double Z0047;
    double Z0076;
    double Z0137;
    double Z0148;
    double Z0079;
    double Z0020;
    double Z0147;
    double Z0018;
    int[] Z0007;
    int[] Z0062;
    int Z0008;
    int Z0009;
    int Z0033;
    int Z0034;
    int Z0059;
    int Z0065;
    int Z0063;
    int Z0061;
    int Z0064;
    int Z0130;
    int Z0060;
    int Z0131;
    int Z0032;
    int Z0051;
    int Z0049;
    boolean Z0012;
    boolean Z0092;
    boolean Z0126;
    boolean Z0084;
    
    public void init() {
        this.Z0121();
        this.Z0080 = new Random();
        this.Z0003 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0118(), "East");
        contentPane.add(this.Z0021 = new DrawingArea(), "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0118() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0003), panel.getBorder()));
        this.Z0004 = new JButton[6];
        this.Z0048 = new JLabel[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0101(0, panel2, "Reset");
        this.Z0104(panel2, 10, 0);
        this.Z0101(1, panel2, "Start");
        panel.add(panel2);
        this.Z0104(panel, 0, 20);
        this.Z0103(0, panel, " ");
        this.Z0104(panel, 0, 5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0101(2, panel3, "<< T");
        this.Z0104(panel3, 10, 0);
        this.Z0101(3, panel3, "T >>");
        panel.add(panel3);
        this.Z0104(panel, 0, 20);
        this.Z0103(1, panel, " ");
        this.Z0104(panel, 0, 5);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        this.Z0101(4, panel4, "<< D");
        this.Z0104(panel4, 10, 0);
        this.Z0101(5, panel4, "D >>");
        panel.add(panel4);
        this.Z0104(panel, 0, 20);
        this.Z0010 = new JCheckBox[1];
        this.Z0102(0, panel, "Trajectories");
        this.Z0104(panel, 0, 20);
        this.Z0127 = new JSlider[2];
        this.Z0105(0, panel, this.Z0131, 1, 50, "Update");
        this.Z0105(1, panel, this.Z0060, 10, 30, "Size");
        return panel;
    }
    
    void Z0101(final int n, final JPanel panel, final String s) {
        (this.Z0004[n] = new JButton(s)).setPreferredSize(this.Z0004[n].getPreferredSize());
        this.Z0004[n].addActionListener(this);
        panel.add(this.Z0004[n]);
    }
    
    void Z0102(final int n, final JPanel panel, final String s) {
        (this.Z0010[n] = new JCheckBox(s)).addItemListener(this);
        this.Z0010[n].setAlignmentX(0.5f);
        panel.add(this.Z0010[n]);
    }
    
    void Z0103(final int n, final JPanel panel, final String s) {
        (this.Z0048[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0048[n]);
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
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0127[0]) {
                this.Z0131 = value;
            }
            else if (slider == this.Z0127[1]) {
                this.Z0060 = value;
                this.Z0106();
                this.Z0126 = false;
                this.Z0010[0].setSelected(this.Z0126);
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0004[0]) {
            this.Z0115();
            this.Z0126 = false;
            this.Z0010[0].setSelected(this.Z0126);
        }
        else if (source == this.Z0004[1]) {
            this.Z0092 = !this.Z0092;
            this.Z0004[1].setText(this.Z0092 ? "Stop" : "Start");
            this.Z0127[1].setEnabled(!this.Z0092);
        }
        else if (source == this.Z0004[2] || source == this.Z0004[3]) {
            this.Z0109((source == this.Z0004[2]) ? 2 : 1);
            this.Z0048[0].setText("Temperature: " + this.Z0114(this.Z0136, 2));
        }
        else if (source == this.Z0004[4] || source == this.Z0004[5]) {
            this.Z0108((source == this.Z0004[4]) ? 2 : 1);
            this.Z0048[1].setText("Density: " + this.Z0114(this.Z0017, 2));
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.Z0010[0]) {
            this.Z0126 = (itemEvent.getStateChange() == 1);
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0021.getSize();
        final int n = size.width - size.height;
        this.Z0021.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0003), BorderFactory.createRaisedBevelBorder()));
        this.Z0106();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0092) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0119();
                if (this.Z0130 % this.Z0131 != 0) {
                    continue;
                }
                this.Z0123();
                this.Z0021.repaint();
                this.Z0021.getToolkit().sync();
                if (!this.Z0122(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0084) {
                    this.Z0084 = false;
                    this.Z0021.repaint();
                }
                if (!this.Z0122(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0122(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0121() {
        this.Z0060 = 20;
        this.Z0012 = true;
        this.Z0131 = 5;
        this.Z0018 = 1.3;
        this.Z0079 = 0.4;
        this.Z0016 = 0.005;
        this.Z0051 = 20;
    }
    
    void Z0106() {
        this.Z0136 = 1.0;
        this.Z0017 = 0.8;
        this.Z0048[0].setText("Temperature: " + this.Z0114(this.Z0136, 2));
        this.Z0048[1].setText("Density: " + this.Z0114(this.Z0017, 2));
        this.Z0033 = this.Z0060;
        this.Z0034 = (int)(this.Z0060 / Math.sqrt(3.0));
        this.Z0085 = this.Z0033 / Math.sqrt(this.Z0017 * Math.sqrt(3.0) / 2.0);
        this.Z0086 = this.Z0034 / Math.sqrt(this.Z0017 / (2.0 * Math.sqrt(3.0)));
        this.Z0085 = Math.max(this.Z0085, this.Z0086);
        this.Z0059 = 2 * this.Z0033 * this.Z0034;
        this.Z0085 = Math.sqrt(this.Z0059 / this.Z0017);
        this.Z0086 = this.Z0085;
        this.Z0063 = 5;
        this.Z0077 = Math.pow(2.0, 0.16666666666666666);
        this.Z0065 = this.Z0063 * this.Z0059;
        this.Z0008 = (int)(this.Z0085 / (this.Z0077 + this.Z0079));
        this.Z0009 = (int)(this.Z0086 / (this.Z0077 + this.Z0079));
        this.Z0117();
        this.Z0115();
        this.Z0092 = false;
        this.Z0126 = false;
    }
    
    void Z0117() {
        this.Z0095 = new double[this.Z0059];
        this.Z0097 = new double[this.Z0059];
        this.Z0093 = new double[this.Z0059];
        this.Z0094 = new double[this.Z0059];
        this.Z0081 = new double[this.Z0059];
        this.Z0082 = new double[this.Z0059];
        this.Z0007 = new int[this.Z0059 + this.Z0008 * this.Z0009];
        this.Z0062 = new int[2 * this.Z0065];
        this.Z0096 = new double[this.Z0059 * this.Z0051];
        this.Z0098 = new double[this.Z0059 * this.Z0051];
    }
    
    void Z0119() {
        ++this.Z0130;
        this.Z0116(1);
        this.Z0110();
        if (this.Z0061 > 0) {
            this.Z0061 = 0;
            this.Z0020 = 0.0;
            this.Z0107();
        }
        if (this.Z0092) {
            this.Z0111();
            this.Z0116(2);
            this.Z0112();
            this.Z0020 += Math.sqrt(this.Z0147) * this.Z0016;
            if (this.Z0020 > 0.5 * this.Z0079) {
                this.Z0061 = 1;
            }
        }
    }
    
    void Z0107() {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n = this.Z0008 / this.Z0085;
        final double n2 = this.Z0009 / this.Z0086;
        final double n3 = (this.Z0077 + this.Z0079) * (this.Z0077 + this.Z0079);
        for (int i = this.Z0059; i < this.Z0059 + this.Z0008 * this.Z0009; ++i) {
            this.Z0007[i] = -1;
        }
        for (int j = 0; j < this.Z0059; ++j) {
            final int n4 = (int)((this.Z0097[j] + 0.5 * this.Z0086) * n2) * this.Z0008 + (int)((this.Z0095[j] + 0.5 * this.Z0085) * n) + this.Z0059;
            this.Z0007[j] = this.Z0007[n4];
            this.Z0007[n4] = j;
        }
        this.Z0064 = 0;
        for (int k = 0; k < this.Z0009; ++k) {
            for (int l = 0; l < this.Z0008; ++l) {
                final int n5 = k * this.Z0008 + l + this.Z0059;
                for (int n6 = 0; n6 < 5; ++n6) {
                    int n7 = l + array[n6];
                    int n8 = k + array2[n6];
                    double z0085 = 0.0;
                    double z86 = 0.0;
                    if (n7 >= this.Z0008) {
                        n7 = 0;
                        z0085 = this.Z0085;
                    }
                    else if (n7 < 0) {
                        n7 = this.Z0008 - 1;
                        z0085 = -this.Z0085;
                    }
                    if (n8 >= this.Z0009) {
                        n8 = 0;
                        z86 = this.Z0086;
                    }
                    else if (n8 < 0) {
                        n8 = this.Z0009 - 1;
                        z86 = -this.Z0086;
                    }
                    final int n9 = n8 * this.Z0008 + n7 + this.Z0059;
                    for (int n10 = this.Z0007[n5]; n10 >= 0; n10 = this.Z0007[n10]) {
                        for (int n11 = this.Z0007[n9]; n11 >= 0; n11 = this.Z0007[n11]) {
                            if (n5 != n9 || n11 < n10) {
                                final double n12 = this.Z0095[n10] - this.Z0095[n11] - z0085;
                                final double n13 = this.Z0097[n10] - this.Z0097[n11] - z86;
                                if (n12 * n12 + n13 * n13 < n3) {
                                    this.Z0062[2 * this.Z0064] = n10;
                                    this.Z0062[2 * this.Z0064 + 1] = n11;
                                    ++this.Z0064;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void Z0111() {
        final double n = this.Z0077 * this.Z0077;
        for (int i = 0; i < this.Z0059; ++i) {
            this.Z0081[i] = 0.0;
            this.Z0082[i] = 0.0;
        }
        this.Z0138 = 0.0;
        for (int j = 0; j < this.Z0064; ++j) {
            final int n2 = this.Z0062[2 * j];
            final int n3 = this.Z0062[2 * j + 1];
            double n4 = this.Z0095[n2] - this.Z0095[n3];
            double n5 = this.Z0097[n2] - this.Z0097[n3];
            if (n4 >= 0.5 * this.Z0085) {
                n4 -= this.Z0085;
            }
            else if (n4 < -0.5 * this.Z0085) {
                n4 += this.Z0085;
            }
            if (n5 >= 0.5 * this.Z0086) {
                n5 -= this.Z0086;
            }
            else if (n5 < -0.5 * this.Z0086) {
                n5 += this.Z0086;
            }
            final double n6 = n4 * n4 + n5 * n5;
            if (n6 < n) {
                final double n7 = 1.0 / n6;
                final double n8 = n7 * n7 * n7;
                final double n9 = 48.0 * n8 * (n8 - 0.5) * n7;
                final double n10 = 4.0 * n8 * (n8 - 1.0) + 1.0;
                final double[] z0081 = this.Z0081;
                final int n11 = n2;
                z0081[n11] += n9 * n4;
                final double[] z82 = this.Z0082;
                final int n12 = n2;
                z82[n12] += n9 * n5;
                final double[] z83 = this.Z0081;
                final int n13 = n3;
                z83[n13] -= n9 * n4;
                final double[] z84 = this.Z0082;
                final int n14 = n3;
                z84[n14] -= n9 * n5;
                this.Z0138 += n10;
            }
        }
    }
    
    void Z0116(final int n) {
        if (n == 1) {
            if (this.Z0012) {
                double n2 = 0.0;
                double n3 = 0.0;
                for (int i = 0; i < this.Z0059; ++i) {
                    n2 += this.Z0093[i] * this.Z0081[i] + this.Z0094[i] * this.Z0082[i];
                    n3 += this.Z0093[i] * this.Z0093[i] + this.Z0094[i] * this.Z0094[i];
                }
                final double n4 = -n2 / n3;
                for (int j = 0; j < this.Z0059; ++j) {
                    this.Z0093[j] = (1.0 + n4 * this.Z0016) * this.Z0093[j] + 0.5 * this.Z0016 * this.Z0081[j];
                    this.Z0094[j] = (1.0 + n4 * this.Z0016) * this.Z0094[j] + 0.5 * this.Z0016 * this.Z0082[j];
                }
            }
            else {
                for (int k = 0; k < this.Z0059; ++k) {
                    final double[] z0093 = this.Z0093;
                    final int n5 = k;
                    z0093[n5] += 0.5 * this.Z0016 * this.Z0081[k];
                    final double[] z94 = this.Z0094;
                    final int n6 = k;
                    z94[n6] += 0.5 * this.Z0016 * this.Z0082[k];
                }
            }
            for (int l = 0; l < this.Z0059; ++l) {
                final double[] z95 = this.Z0095;
                final int n7 = l;
                z95[n7] += this.Z0016 * this.Z0093[l];
                final double[] z96 = this.Z0097;
                final int n8 = l;
                z96[n8] += this.Z0016 * this.Z0094[l];
            }
        }
        else {
            for (int n9 = 0; n9 < this.Z0059; ++n9) {
                final double[] z97 = this.Z0093;
                final int n10 = n9;
                z97[n10] += 0.5 * this.Z0016 * this.Z0081[n9];
                final double[] z98 = this.Z0094;
                final int n11 = n9;
                z98[n11] += 0.5 * this.Z0016 * this.Z0082[n9];
            }
        }
    }
    
    void Z0110() {
        for (int i = 0; i < this.Z0059; ++i) {
            if (this.Z0095[i] >= 0.5 * this.Z0085) {
                final double[] z0095 = this.Z0095;
                final int n = i;
                z0095[n] -= this.Z0085;
            }
            else if (this.Z0095[i] < -0.5 * this.Z0085) {
                final double[] z96 = this.Z0095;
                final int n2 = i;
                z96[n2] += this.Z0085;
            }
            if (this.Z0097[i] >= 0.5 * this.Z0086) {
                final double[] z97 = this.Z0097;
                final int n3 = i;
                z97[n3] -= this.Z0086;
            }
            else if (this.Z0097[i] < -0.5 * this.Z0086) {
                final double[] z98 = this.Z0097;
                final int n4 = i;
                z98[n4] += this.Z0086;
            }
        }
    }
    
    void Z0112() {
        this.Z0148 = 0.0;
        this.Z0147 = 0.0;
        for (int i = 0; i < this.Z0059; ++i) {
            final double z0147 = this.Z0093[i] * this.Z0093[i] + this.Z0094[i] * this.Z0094[i];
            this.Z0148 += z0147;
            if (z0147 > this.Z0147) {
                this.Z0147 = z0147;
            }
        }
        this.Z0047 = 0.5 * this.Z0148 / this.Z0059;
        this.Z0076 = this.Z0138 / this.Z0059;
        this.Z0137 = this.Z0047 + this.Z0076;
    }
    
    void Z0115() {
        this.Z0130 = 0;
        this.Z0061 = 1;
        final double n = this.Z0085 / this.Z0033;
        final double n2 = this.Z0086 / this.Z0034;
        int n3 = 0;
        for (int i = 0; i < this.Z0034; ++i) {
            final double n4 = (i + 0.25) * n2 - 0.5 * this.Z0086;
            for (int j = 0; j < this.Z0033; ++j) {
                final double n5 = (j + 0.25) * n - 0.5 * this.Z0085;
                this.Z0095[n3] = n5;
                this.Z0097[n3] = n4;
                ++n3;
                this.Z0095[n3] = n5 + 0.5 * n;
                this.Z0097[n3] = n4 + 0.5 * n2;
                ++n3;
            }
        }
        double n6 = 0.0;
        double n7 = 0.0;
        final double sqrt = Math.sqrt(2.0 * this.Z0136);
        for (int k = 0; k < this.Z0059; ++k) {
            final double n8 = 6.283185307179586 * this.Z0080.nextDouble();
            this.Z0093[k] = sqrt * Math.cos(n8);
            this.Z0094[k] = sqrt * Math.sin(n8);
            n6 += this.Z0093[k];
            n7 += this.Z0094[k];
        }
        for (int l = 0; l < this.Z0059; ++l) {
            final double[] z0093 = this.Z0093;
            final int n9 = l;
            z0093[n9] -= n6 / this.Z0059;
            final double[] z94 = this.Z0094;
            final int n10 = l;
            z94[n10] -= n7 / this.Z0059;
        }
        for (int n11 = 0; n11 < this.Z0059; ++n11) {
            this.Z0081[n11] = 0.0;
            this.Z0082[n11] = 0.0;
        }
        this.Z0107();
        this.Z0111();
        this.Z0112();
        this.Z0032 = 0;
        this.Z0049 = 0;
        this.Z0084 = true;
    }
    
    void Z0109(final int n) {
        double n2;
        if (n == 1) {
            n2 = 1.02;
        }
        else {
            n2 = 0.98;
        }
        if ((n2 > 1.0 && this.Z0136 < 10.0) || (n2 < 1.0 && this.Z0136 > 0.02)) {
            this.Z0148 = 0.0;
            for (int i = 0; i < this.Z0059; ++i) {
                final double[] z0093 = this.Z0093;
                final int n3 = i;
                z0093[n3] *= n2;
                final double[] z94 = this.Z0094;
                final int n4 = i;
                z94[n4] *= n2;
                this.Z0148 += this.Z0093[i] * this.Z0093[i] + this.Z0094[i] * this.Z0094[i];
            }
            this.Z0136 = this.Z0148 / (2.0 * this.Z0059);
        }
    }
    
    void Z0108(final int n) {
        double n2;
        if (n == 1) {
            n2 = 0.98;
        }
        else {
            n2 = 1.02;
        }
        if ((n2 > 1.0 && this.Z0017 > 0.1) || (n2 < 1.0 && this.Z0017 < this.Z0018)) {
            if (n2 < 1.0) {
                for (int i = 0; i < this.Z0059; ++i) {
                    final double[] z0095 = this.Z0095;
                    final int n3 = i;
                    z0095[n3] *= n2;
                    final double[] z96 = this.Z0097;
                    final int n4 = i;
                    z96[n4] *= n2;
                }
            }
            this.Z0017 /= n2 * n2;
            this.Z0085 *= n2;
            this.Z0086 *= n2;
            this.Z0008 = (int)(this.Z0085 / (this.Z0077 + this.Z0079));
            this.Z0009 = (int)(this.Z0086 / (this.Z0077 + this.Z0079));
            this.Z0007 = new int[this.Z0059 + this.Z0008 * this.Z0009];
            this.Z0061 = 1;
        }
    }
    
    void Z0123() {
        for (int i = 0; i < this.Z0059; ++i) {
            this.Z0096[i * this.Z0051 + this.Z0049] = this.Z0095[i];
            this.Z0098[i * this.Z0051 + this.Z0049] = this.Z0097[i];
        }
        if (++this.Z0049 >= this.Z0051) {
            this.Z0049 = 0;
        }
        if (this.Z0032 < this.Z0051) {
            ++this.Z0032;
        }
    }
    
    void Z0120(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0021.getSize();
        final Insets insets = this.Z0021.getInsets();
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
        final double n5 = min / this.Z0085;
        if (this.Z0126) {
            for (int i = 0; i < this.Z0059; ++i) {
                int z0049 = this.Z0049;
                int n6 = z0049 - 1;
                for (int j = 1; j < this.Z0032; ++j) {
                    if (--z0049 < 0) {
                        z0049 += this.Z0051;
                    }
                    if (--n6 < 0) {
                        n6 += this.Z0051;
                    }
                    final int n7 = i * this.Z0051 + z0049;
                    final int n8 = i * this.Z0051 + n6;
                    if (Math.abs(this.Z0096[n7] - this.Z0096[n8]) < 0.5 * this.Z0085 && Math.abs(this.Z0098[n7] - this.Z0098[n8]) < 0.5 * this.Z0086) {
                        graphics2D.draw(new Line2D.Double((int)(n5 * (0.5 * this.Z0085 + this.Z0096[n7])) + n3, (int)(n5 * (0.5 * this.Z0086 - this.Z0098[n7])) + n4, (int)(n5 * (0.5 * this.Z0085 + this.Z0096[n8])) + n3, (int)(n5 * (0.5 * this.Z0086 - this.Z0098[n8])) + n4));
                    }
                }
            }
        }
        else {
            final int n9 = (int)(0.9 * n5);
            final double n10 = 0.5 * this.Z0085 - 0.45;
            final double n11 = 0.5 * this.Z0086 - 0.45;
            for (int k = 0; k < this.Z0059; ++k) {
                final int n12 = (int)(n5 * (0.5 * this.Z0085 + this.Z0095[k])) - n9 / 2 + n3;
                final int n13 = (int)(n5 * (0.5 * this.Z0086 - this.Z0097[k])) - n9 / 2 + n4;
                graphics2D.fill(new Ellipse2D.Double(n12, n13, n9, n9));
                final boolean b = this.Z0095[k] > -n10;
                final boolean b2 = this.Z0095[k] < n10;
                final boolean b3 = this.Z0097[k] > -n11;
                final boolean b4 = this.Z0097[k] < n11;
                if (!b || !b2 || !b3 || !b4) {
                    final int n14 = b ? (-min) : min;
                    final int n15 = b4 ? (-min) : min;
                    if (b3 && b4) {
                        graphics2D.fill(new Ellipse2D.Double(n12 + n14, n13, n9, n9));
                    }
                    else if (b && b2) {
                        graphics2D.fill(new Ellipse2D.Double(n12, n13 + n15, n9, n9));
                    }
                    else {
                        graphics2D.fill(new Ellipse2D.Double(n12 + n14, n13, n9, n9));
                        graphics2D.fill(new Ellipse2D.Double(n12, n13 + n15, n9, n9));
                        graphics2D.fill(new Ellipse2D.Double(n12 + n14, n13 + n15, n9, n9));
                    }
                }
            }
        }
        graphics2D.setClip(clip2);
    }
    
    String Z0114(final double n, final int n2) {
        final NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(n2);
        numberInstance.setMaximumFractionDigits(n2);
        return numberInstance.format(n);
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Mddisk.this.Z0120(graphics);
        }
    }
}
