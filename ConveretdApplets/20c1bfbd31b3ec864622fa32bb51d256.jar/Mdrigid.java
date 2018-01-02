import java.text.NumberFormat;
import java.awt.Insets;
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
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Component;
import java.util.Random;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mdrigid extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0003;
    JButton[] Z0005;
    JSlider[] Z0127;
    JLabel[] Z0046;
    JRadioButton[] Z0075;
    DrawingArea Z0019;
    Color Z0004;
    Random Z0076;
    double[] Z0091;
    double[] Z0092;
    double[] Z0089;
    double[] Z0090;
    double[] Z0077;
    double[] Z0078;
    double[] Z0134;
    double[] Z0135;
    double[] Z0132;
    double[] Z0133;
    double[] Z0120;
    double[] Z0121;
    double[] Z0024;
    double[] Z0025;
    double[] Z0055;
    double[] Z0056;
    double[] Z0073;
    double[] Z0074;
    double Z0081;
    double Z0082;
    double Z0015;
    double Z0071;
    double Z0138;
    double Z0016;
    double Z0141;
    double Z0140;
    double Z0126;
    double Z0058;
    double Z0017;
    int[] Z0008;
    int[] Z0033;
    int Z0009;
    int Z0010;
    int Z0034;
    int Z0035;
    int Z0059;
    int Z0061;
    int Z0130;
    int Z0062;
    int Z0060;
    int Z0131;
    boolean Z0088;
    boolean Z0048;
    boolean Z0080;
    
    public void init() {
        this.Z0118();
        this.Z0076 = new Random();
        this.Z0004 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0115(), "East");
        contentPane.add(this.Z0019 = new DrawingArea(), "Center");
        this.Z0003 = null;
    }
    
    JPanel Z0115() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0004), panel.getBorder()));
        this.Z0005 = new JButton[5];
        this.Z0046 = new JLabel[2];
        this.Z0093(0, panel, "Start");
        this.Z0096(panel, 0, 20);
        this.Z0094(0, panel, " ");
        this.Z0096(panel, 0, 5);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0093(1, panel2, "<< T");
        this.Z0096(panel2, 10, 0);
        this.Z0093(2, panel2, "T >>");
        panel.add(panel2);
        this.Z0096(panel, 0, 20);
        this.Z0094(1, panel, " ");
        this.Z0096(panel, 0, 5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0093(3, panel3, "<< D");
        this.Z0096(panel3, 10, 0);
        this.Z0093(4, panel3, "D >>");
        panel.add(panel3);
        this.Z0096(panel, 0, 20);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, 0));
        this.Z0075 = new JRadioButton[2];
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.Z0095(0, panel4, buttonGroup, "Triangle", true);
        this.Z0095(1, panel4, buttonGroup, "Linear", false);
        panel.add(panel4);
        this.Z0096(panel, 0, 15);
        this.Z0127 = new JSlider[2];
        this.Z0097(0, panel, this.Z0131, 1, 50, "Update");
        this.Z0097(1, panel, this.Z0060, 6, 12, "Size");
        return panel;
    }
    
    void Z0093(final int n, final JPanel panel, final String s) {
        (this.Z0005[n] = new JButton(s)).setAlignmentX(0.5f);
        this.Z0005[n].setPreferredSize(this.Z0005[n].getPreferredSize());
        this.Z0005[n].addActionListener(this);
        panel.add(this.Z0005[n]);
    }
    
    void Z0095(final int n, final JPanel panel, final ButtonGroup buttonGroup, final String s, final boolean selected) {
        (this.Z0075[n] = new JRadioButton(s)).addActionListener(this);
        this.Z0075[n].setSelected(selected);
        buttonGroup.add(this.Z0075[n]);
        panel.add(this.Z0075[n]);
    }
    
    void Z0094(final int n, final JPanel panel, final String s) {
        (this.Z0046[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0046[n]);
    }
    
    void Z0097(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0096(panel, 0, 3);
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
        this.Z0096(panel, 0, 5);
    }
    
    void Z0096(final JPanel panel, final int n, final int n2) {
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
                this.Z0100();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0005[0]) {
            this.Z0088 = !this.Z0088;
            this.Z0005[0].setText(this.Z0088 ? "Stop" : "Start");
            this.Z0127[1].setEnabled(!this.Z0088);
            this.Z0075[0].setEnabled(!this.Z0088);
            this.Z0075[1].setEnabled(!this.Z0088);
        }
        else if (source == this.Z0005[1] || source == this.Z0005[2]) {
            this.Z0102((source == this.Z0005[1]) ? 2 : 1);
            this.Z0046[0].setText("Temperature: " + this.Z0109(this.Z0138, 2));
        }
        else if (source == this.Z0005[3] || source == this.Z0005[4]) {
            this.Z0101((source == this.Z0005[3]) ? 2 : 1);
            this.Z0046[1].setText("Density: " + this.Z0109(this.Z0016, 2));
        }
        else if (source == this.Z0075[0] || source == this.Z0075[1]) {
            if (source == this.Z0075[0]) {
                this.Z0048 = !this.Z0075[0].isSelected();
            }
            else {
                this.Z0048 = this.Z0075[1].isSelected();
            }
            this.Z0100();
        }
    }
    
    public void start() {
        if (this.Z0003 == null) {
            (this.Z0003 = new Thread(this)).setPriority(1);
            this.Z0003.start();
        }
        final Dimension size = this.Z0019.getSize();
        final int n = size.width - size.height;
        this.Z0019.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0004), BorderFactory.createRaisedBevelBorder()));
        this.Z0100();
    }
    
    public void stop() {
        this.Z0003 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0003) {
            if (this.Z0088) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0116();
                if (this.Z0130 % this.Z0131 != 0) {
                    continue;
                }
                this.Z0019.repaint();
                this.Z0019.getToolkit().sync();
                if (!this.Z0119(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0080) {
                    this.Z0080 = false;
                    this.Z0019.repaint();
                }
                if (!this.Z0119(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0119(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0118() {
        this.Z0060 = 8;
        this.Z0126 = 0.7;
        this.Z0131 = 5;
        this.Z0015 = 0.0025;
        this.Z0048 = false;
        this.Z0138 = 1.0;
        this.Z0016 = 0.8;
    }
    
    void Z0100() {
        this.Z0034 = this.Z0060;
        if (this.Z0048) {
            this.Z0016 = 0.8;
            this.Z0017 = 0.9;
            this.Z0062 = 4;
            this.Z0081 = this.Z0060 * ((this.Z0062 - 1) * this.Z0126 + 1.0) / Math.sqrt(this.Z0016);
        }
        else {
            this.Z0016 = 0.4;
            this.Z0017 = 0.7;
            this.Z0062 = 3;
            this.Z0081 = this.Z0060 * (this.Z0126 + 1.0) / Math.sqrt(this.Z0016);
        }
        this.Z0046[0].setText("Temperature: " + this.Z0109(this.Z0138, 2));
        this.Z0046[1].setText("Density: " + this.Z0109(this.Z0016, 2));
        this.Z0082 = this.Z0081;
        this.Z0035 = (int)(this.Z0082 * Math.sqrt(this.Z0016));
        this.Z0059 = this.Z0034 * this.Z0035;
        this.Z0061 = this.Z0059 * this.Z0062;
        this.Z0071 = Math.pow(2.0, 0.16666666666666666);
        this.Z0009 = (int)(this.Z0081 / this.Z0071);
        this.Z0010 = (int)(this.Z0082 / this.Z0071);
        this.Z0114();
        this.Z0106();
        this.Z0111();
        this.Z0110();
        this.Z0105();
        this.Z0107();
        this.Z0088 = false;
    }
    
    void Z0114() {
        this.Z0091 = new double[this.Z0059];
        this.Z0092 = new double[this.Z0059];
        this.Z0089 = new double[this.Z0059];
        this.Z0090 = new double[this.Z0059];
        this.Z0077 = new double[this.Z0059];
        this.Z0078 = new double[this.Z0059];
        this.Z0134 = new double[this.Z0059];
        this.Z0135 = new double[this.Z0059];
        this.Z0132 = new double[this.Z0059];
        this.Z0133 = new double[this.Z0059];
        this.Z0120 = new double[this.Z0059];
        this.Z0121 = new double[this.Z0059];
        this.Z0024 = new double[this.Z0061];
        this.Z0025 = new double[this.Z0061];
        this.Z0073 = new double[this.Z0061];
        this.Z0074 = new double[this.Z0061];
        this.Z0055 = new double[this.Z0062];
        this.Z0056 = new double[this.Z0062];
        this.Z0008 = new int[this.Z0061 + this.Z0009 * this.Z0010];
        this.Z0033 = new int[this.Z0061];
    }
    
    void Z0116() {
        ++this.Z0130;
        this.Z0112(1);
        this.Z0103();
        this.Z0110();
        this.Z0105();
        this.Z0104();
        this.Z0112(2);
        this.Z0098();
        this.Z0099();
        this.Z0107();
    }
    
    void Z0110() {
        int n = 0;
        for (int i = 0; i < this.Z0059; ++i) {
            for (int j = 0; j < this.Z0062; ++j) {
                this.Z0073[n] = this.Z0091[i] + this.Z0134[i] * this.Z0055[j] - this.Z0135[i] * this.Z0056[j];
                this.Z0074[n] = this.Z0092[i] + this.Z0135[i] * this.Z0055[j] + this.Z0134[i] * this.Z0056[j];
                if (this.Z0073[n] >= 0.5 * this.Z0081) {
                    final double[] z0073 = this.Z0073;
                    final int n2 = n;
                    z0073[n2] -= this.Z0081;
                }
                else if (this.Z0073[n] < -0.5 * this.Z0081) {
                    final double[] z74 = this.Z0073;
                    final int n3 = n;
                    z74[n3] += this.Z0081;
                }
                if (this.Z0074[n] >= 0.5 * this.Z0082) {
                    final double[] z75 = this.Z0074;
                    final int n4 = n;
                    z75[n4] -= this.Z0082;
                }
                else if (this.Z0074[n] < -0.5 * this.Z0082) {
                    final double[] z76 = this.Z0074;
                    final int n5 = n;
                    z76[n5] += this.Z0082;
                }
                ++n;
            }
        }
    }
    
    void Z0105() {
        final int[] array = { 0, 1, 1, 0, -1 };
        final int[] array2 = { 0, 0, 1, 1, 1 };
        final double n = this.Z0009 / this.Z0081;
        final double n2 = this.Z0010 / this.Z0082;
        for (int i = this.Z0061; i < this.Z0061 + this.Z0009 * this.Z0010; ++i) {
            this.Z0008[i] = -1;
        }
        for (int j = 0; j < this.Z0061; ++j) {
            final int n3 = (int)((this.Z0074[j] + 0.5 * this.Z0082) * n2) * this.Z0009 + (int)((this.Z0073[j] + 0.5 * this.Z0081) * n) + this.Z0061;
            this.Z0008[j] = this.Z0008[n3];
            this.Z0008[n3] = j;
        }
        final double n4 = this.Z0071 * this.Z0071;
        for (int k = 0; k < this.Z0061; ++k) {
            this.Z0024[k] = 0.0;
            this.Z0025[k] = 0.0;
        }
        this.Z0141 = 0.0;
        for (int l = 0; l < this.Z0010; ++l) {
            for (int n5 = 0; n5 < this.Z0009; ++n5) {
                final int n6 = l * this.Z0009 + n5 + this.Z0061;
                for (int n7 = 0; n7 < 5; ++n7) {
                    int n8 = n5 + array[n7];
                    int n9 = l + array2[n7];
                    double z0081 = 0.0;
                    double z82 = 0.0;
                    if (n8 >= this.Z0009) {
                        n8 = 0;
                        z0081 = this.Z0081;
                    }
                    else if (n8 < 0) {
                        n8 = this.Z0009 - 1;
                        z0081 = -this.Z0081;
                    }
                    if (n9 >= this.Z0010) {
                        n9 = 0;
                        z82 = this.Z0082;
                    }
                    else if (n9 < 0) {
                        n9 = this.Z0010 - 1;
                        z82 = -this.Z0082;
                    }
                    final int n10 = n9 * this.Z0009 + n8 + this.Z0061;
                    for (int n11 = this.Z0008[n6]; n11 >= 0; n11 = this.Z0008[n11]) {
                        for (int n12 = this.Z0008[n10]; n12 >= 0; n12 = this.Z0008[n12]) {
                            if ((n6 != n10 || n12 < n11) && this.Z0033[n11] != this.Z0033[n12]) {
                                final double n13 = this.Z0073[n11] - this.Z0073[n12] - z0081;
                                final double n14 = this.Z0074[n11] - this.Z0074[n12] - z82;
                                final double n15 = n13 * n13 + n14 * n14;
                                if (n15 < n4) {
                                    final double n16 = 1.0 / n15;
                                    final double n17 = n16 * n16 * n16;
                                    final double n18 = 48.0 * n17 * (n17 - 0.5) * n16;
                                    final double[] z83 = this.Z0024;
                                    final int n19 = n11;
                                    z83[n19] += n18 * n13;
                                    final double[] z84 = this.Z0025;
                                    final int n20 = n11;
                                    z84[n20] += n18 * n14;
                                    final double[] z85 = this.Z0024;
                                    final int n21 = n12;
                                    z85[n21] -= n18 * n13;
                                    final double[] z86 = this.Z0025;
                                    final int n22 = n12;
                                    z86[n22] -= n18 * n14;
                                    this.Z0141 += 4.0 * n17 * (n17 - 1.0) + 1.0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    void Z0104() {
        for (int i = 0; i < this.Z0059; ++i) {
            this.Z0077[i] = 0.0;
            this.Z0078[i] = 0.0;
            double n = 0.0;
            for (int j = 0; j < this.Z0062; ++j) {
                final double[] z0077 = this.Z0077;
                final int n2 = i;
                z0077[n2] += this.Z0024[i * this.Z0062 + j];
                final double[] z78 = this.Z0078;
                final int n3 = i;
                z78[n3] += this.Z0025[i * this.Z0062 + j];
                double n4 = this.Z0073[i * this.Z0062 + j] - this.Z0091[i];
                double n5 = this.Z0074[i * this.Z0062 + j] - this.Z0092[i];
                if (n4 >= 0.5 * this.Z0081) {
                    n4 -= this.Z0081;
                }
                else if (n4 < -0.5 * this.Z0081) {
                    n4 += this.Z0081;
                }
                if (n5 >= 0.5 * this.Z0082) {
                    n5 -= this.Z0082;
                }
                else if (n5 < -0.5 * this.Z0082) {
                    n5 += this.Z0082;
                }
                n += n4 * this.Z0025[i * this.Z0062 + j] - n5 * this.Z0024[i * this.Z0062 + j];
            }
            this.Z0120[i] = -this.Z0135[i] * n / this.Z0058;
            this.Z0121[i] = this.Z0134[i] * n / this.Z0058;
            final double n6 = this.Z0132[i] * this.Z0132[i] + this.Z0133[i] * this.Z0133[i];
            final double[] z79 = this.Z0120;
            final int n7 = i;
            z79[n7] -= n6 * this.Z0134[i];
            final double[] z80 = this.Z0121;
            final int n8 = i;
            z80[n8] -= n6 * this.Z0135[i];
        }
    }
    
    void Z0112(final int n) {
        if (n == 1) {
            for (int i = 0; i < this.Z0059; ++i) {
                final double[] z0089 = this.Z0089;
                final int n2 = i;
                z0089[n2] += 0.5 * this.Z0015 * this.Z0077[i];
                final double[] z90 = this.Z0090;
                final int n3 = i;
                z90[n3] += 0.5 * this.Z0015 * this.Z0078[i];
                final double[] z91 = this.Z0091;
                final int n4 = i;
                z91[n4] += this.Z0015 * this.Z0089[i];
                final double[] z92 = this.Z0092;
                final int n5 = i;
                z92[n5] += this.Z0015 * this.Z0090[i];
                final double[] z93 = this.Z0132;
                final int n6 = i;
                z93[n6] += 0.5 * this.Z0015 * this.Z0120[i];
                final double[] z94 = this.Z0133;
                final int n7 = i;
                z94[n7] += 0.5 * this.Z0015 * this.Z0121[i];
                final double[] z95 = this.Z0134;
                final int n8 = i;
                z95[n8] += this.Z0015 * this.Z0132[i];
                final double[] z96 = this.Z0135;
                final int n9 = i;
                z96[n9] += this.Z0015 * this.Z0133[i];
            }
        }
        else {
            for (int j = 0; j < this.Z0059; ++j) {
                final double[] z97 = this.Z0089;
                final int n10 = j;
                z97[n10] += 0.5 * this.Z0015 * this.Z0077[j];
                final double[] z98 = this.Z0090;
                final int n11 = j;
                z98[n11] += 0.5 * this.Z0015 * this.Z0078[j];
                final double[] z99 = this.Z0132;
                final int n12 = j;
                z99[n12] += 0.5 * this.Z0015 * this.Z0120[j];
                final double[] z100 = this.Z0133;
                final int n13 = j;
                z100[n13] += 0.5 * this.Z0015 * this.Z0121[j];
            }
        }
    }
    
    void Z0103() {
        for (int i = 0; i < this.Z0059; ++i) {
            if (this.Z0091[i] >= 0.5 * this.Z0081) {
                final double[] z0091 = this.Z0091;
                final int n = i;
                z0091[n] -= this.Z0081;
            }
            else if (this.Z0091[i] < -0.5 * this.Z0081) {
                final double[] z92 = this.Z0091;
                final int n2 = i;
                z92[n2] += this.Z0081;
            }
            if (this.Z0092[i] >= 0.5 * this.Z0082) {
                final double[] z93 = this.Z0092;
                final int n3 = i;
                z93[n3] -= this.Z0082;
            }
            else if (this.Z0092[i] < -0.5 * this.Z0082) {
                final double[] z94 = this.Z0092;
                final int n4 = i;
                z94[n4] += this.Z0082;
            }
        }
    }
    
    void Z0107() {
        double n = 0.0;
        for (int i = 0; i < this.Z0059; ++i) {
            n += this.Z0089[i] * this.Z0089[i] + this.Z0090[i] * this.Z0090[i] + this.Z0058 * (this.Z0132[i] * this.Z0132[i] + this.Z0133[i] * this.Z0133[i]);
        }
        this.Z0140 = (0.5 * n + this.Z0141) / this.Z0059;
    }
    
    void Z0098() {
        for (int i = 0; i < this.Z0059; ++i) {
            final double sqrt = Math.sqrt(this.Z0134[i] * this.Z0134[i] + this.Z0135[i] * this.Z0135[i]);
            final double[] z0134 = this.Z0134;
            final int n = i;
            z0134[n] /= sqrt;
            final double[] z135 = this.Z0135;
            final int n2 = i;
            z135[n2] /= sqrt;
        }
    }
    
    void Z0106() {
        if (this.Z0048) {
            for (int i = 0; i < this.Z0062; ++i) {
                this.Z0055[i] = i - (this.Z0062 - 1) * 0.5;
                this.Z0056[i] = 0.0;
            }
        }
        else {
            this.Z0055[0] = 1.0 / Math.sqrt(3.0);
            this.Z0056[0] = 0.0;
            this.Z0055[1] = -0.5 / Math.sqrt(3.0);
            this.Z0056[1] = -0.5;
            this.Z0055[2] = -0.5 / Math.sqrt(3.0);
            this.Z0056[2] = 0.5;
        }
        for (int j = 0; j < this.Z0062; ++j) {
            final double[] z0055 = this.Z0055;
            final int n = j;
            z0055[n] *= this.Z0126;
            final double[] z56 = this.Z0056;
            final int n2 = j;
            z56[n2] *= this.Z0126;
        }
        this.Z0058 = 0.0;
        for (int k = 0; k < this.Z0062; ++k) {
            this.Z0058 += this.Z0055[k] * this.Z0055[k] + this.Z0056[k] * this.Z0056[k];
        }
        int n3 = 0;
        for (int l = 0; l < this.Z0059; ++l) {
            for (int n4 = 0; n4 < this.Z0062; ++n4, ++n3) {
                this.Z0033[n3] = l;
            }
        }
    }
    
    void Z0111() {
        this.Z0130 = 0;
        final double n = this.Z0081 / this.Z0034;
        final double n2 = this.Z0082 / this.Z0035;
        int n3 = 0;
        for (int i = 0; i < this.Z0035; ++i) {
            final double n4 = (i + 0.5) * n2 - 0.5 * this.Z0082;
            for (int j = 0; j < this.Z0034; ++j) {
                this.Z0091[n3] = (j + 0.5) * n - 0.5 * this.Z0081;
                this.Z0092[n3] = n4;
                ++n3;
            }
        }
        double n5 = 0.0;
        double n6 = 0.0;
        final double sqrt = Math.sqrt(2.0 * this.Z0138);
        for (int k = 0; k < this.Z0059; ++k) {
            final double n7 = 6.283185307179586 * this.Z0076.nextDouble();
            this.Z0089[k] = sqrt * Math.cos(n7);
            this.Z0090[k] = sqrt * Math.sin(n7);
            n5 += this.Z0089[k];
            n6 += this.Z0090[k];
        }
        for (int l = 0; l < this.Z0059; ++l) {
            final double[] z0089 = this.Z0089;
            final int n8 = l;
            z0089[n8] -= n5 / this.Z0059;
            final double[] z90 = this.Z0090;
            final int n9 = l;
            z90[n9] -= n6 / this.Z0059;
        }
        final double n10 = Math.sqrt(this.Z0138) / Math.sqrt(this.Z0058);
        for (int n11 = 0; n11 < this.Z0059; ++n11) {
            this.Z0134[n11] = 1.0;
            this.Z0135[n11] = 0.0;
            double n12 = 1.0;
            if (this.Z0076.nextDouble() > 0.5) {
                n12 *= -1.0;
            }
            this.Z0132[n11] = -n10 * n12 * this.Z0135[n11];
            this.Z0133[n11] = n10 * n12 * this.Z0134[n11];
        }
        for (int n13 = 0; n13 < this.Z0059; ++n13) {
            this.Z0077[n13] = 0.0;
            this.Z0078[n13] = 0.0;
            this.Z0120[n13] = 0.0;
            this.Z0121[n13] = 0.0;
        }
        this.Z0080 = true;
    }
    
    void Z0099() {
        double n = 0.0;
        for (int i = 0; i < this.Z0059; ++i) {
            n += this.Z0089[i] * this.Z0089[i] + this.Z0090[i] * this.Z0090[i] + this.Z0058 * (this.Z0132[i] * this.Z0132[i] + this.Z0133[i] * this.Z0133[i]);
        }
        final double sqrt = Math.sqrt(this.Z0138 / (n / (3.0 * this.Z0059)));
        for (int j = 0; j < this.Z0059; ++j) {
            final double[] z0089 = this.Z0089;
            final int n2 = j;
            z0089[n2] *= sqrt;
            final double[] z90 = this.Z0090;
            final int n3 = j;
            z90[n3] *= sqrt;
            final double[] z91 = this.Z0132;
            final int n4 = j;
            z91[n4] *= sqrt;
            final double[] z92 = this.Z0133;
            final int n5 = j;
            z92[n5] *= sqrt;
        }
    }
    
    void Z0102(final int n) {
        double n2;
        if (n == 1) {
            n2 = 1.02;
        }
        else {
            n2 = 0.98;
        }
        if ((n2 > 1.0 && this.Z0138 < 10.0) || (n2 < 1.0 && this.Z0138 > 0.02)) {
            for (int i = 0; i < this.Z0059; ++i) {
                final double[] z0089 = this.Z0089;
                final int n3 = i;
                z0089[n3] *= n2;
                final double[] z90 = this.Z0090;
                final int n4 = i;
                z90[n4] *= n2;
                final double[] z91 = this.Z0132;
                final int n5 = i;
                z91[n5] *= n2;
                final double[] z92 = this.Z0133;
                final int n6 = i;
                z92[n6] *= n2;
            }
            this.Z0138 *= n2 * n2;
        }
    }
    
    void Z0101(final int n) {
        double n2;
        if (n == 1) {
            n2 = 0.99;
        }
        else {
            n2 = 1.0101010101010102;
        }
        if ((n2 > 1.0 && this.Z0016 > 0.1) || (n2 < 1.0 && this.Z0016 < this.Z0017)) {
            if (n2 < 1.0) {
                for (int i = 0; i < this.Z0059; ++i) {
                    final double[] z0091 = this.Z0091;
                    final int n3 = i;
                    z0091[n3] *= n2;
                    final double[] z92 = this.Z0092;
                    final int n4 = i;
                    z92[n4] *= n2;
                }
            }
            this.Z0016 /= n2 * n2;
            this.Z0081 *= n2;
            this.Z0082 *= n2;
            this.Z0009 = (int)(this.Z0081 / this.Z0071);
            this.Z0010 = (int)(this.Z0082 / this.Z0071);
            this.Z0008 = new int[this.Z0061 + this.Z0009 * this.Z0010];
        }
    }
    
    void Z0117(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0019.getSize();
        final Insets insets = this.Z0019.getInsets();
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
        final double n5 = min / this.Z0081;
        final int n6 = (int)(0.9 * n5);
        final double n7 = 0.5 * this.Z0081 - 0.45;
        final double n8 = 0.5 * this.Z0082 - 0.45;
        for (int i = 0; i < this.Z0061; ++i) {
            final int n9 = (int)(n5 * (0.5 * this.Z0081 + this.Z0073[i])) - n6 / 2 + n3;
            final int n10 = (int)(n5 * (0.5 * this.Z0082 - this.Z0074[i])) - n6 / 2 + n4;
            graphics2D.fill(new Ellipse2D.Double(n9, n10, n6, n6));
            final boolean b = this.Z0073[i] > -n7;
            final boolean b2 = this.Z0073[i] < n7;
            final boolean b3 = this.Z0074[i] > -n8;
            final boolean b4 = this.Z0074[i] < n8;
            if (!b || !b2 || !b3 || !b4) {
                final int n11 = b ? (-min) : min;
                final int n12 = b4 ? (-min) : min;
                if (b3 && b4) {
                    graphics2D.fill(new Ellipse2D.Double(n9 + n11, n10, n6, n6));
                }
                else if (b && b2) {
                    graphics2D.fill(new Ellipse2D.Double(n9, n10 + n12, n6, n6));
                }
                else {
                    graphics2D.fill(new Ellipse2D.Double(n9 + n11, n10, n6, n6));
                    graphics2D.fill(new Ellipse2D.Double(n9, n10 + n12, n6, n6));
                    graphics2D.fill(new Ellipse2D.Double(n9 + n11, n10 + n12, n6, n6));
                }
            }
        }
        graphics2D.setClip(clip2);
    }
    
    String Z0109(final double n, final int n2) {
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
            Mdrigid.this.Z0117(graphics);
        }
    }
}
