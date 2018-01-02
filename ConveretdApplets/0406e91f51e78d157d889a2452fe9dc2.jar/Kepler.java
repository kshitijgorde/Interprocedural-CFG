import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.ItemSelectable;
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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.Cursor;
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

public class Kepler extends JApplet implements Runnable, ActionListener, ChangeListener, ItemListener
{
    Thread Z0001;
    JButton[] Z0004;
    JSlider[] Z0117;
    JLabel[] Z0032;
    JCheckBox[] Z0005;
    DrawingArea Z0016;
    MouseAction Z0044;
    Cursor Z0009;
    Color Z0002;
    double[] Z0088;
    double[] Z0089;
    double[] Z0086;
    double[] Z0087;
    double[] Z0076;
    double[] Z0077;
    double[] Z0080;
    double[] Z0081;
    double[] Z0072;
    double[] Z0073;
    double[] Z0074;
    double[] Z0075;
    double[] Z0067;
    double[] Z0068;
    double[] Z0084;
    double[] Z0085;
    double[][] Z0070;
    double[][] Z0071;
    double[] Z0039;
    double[] Z0065;
    double[] Z0066;
    double Z0119;
    double Z0120;
    double Z0040;
    double Z0135;
    double Z0011;
    double Z0128;
    double Z0069;
    double Z0134;
    int[] Z0003;
    int Z0079;
    int Z0041;
    int Z0046;
    int Z0042;
    int Z0015;
    int Z0121;
    int Z0122;
    int Z0022;
    int Z0029;
    int Z0033;
    int Z0035;
    int left;
    int top;
    boolean Z0116;
    boolean Z0021;
    boolean Z0083;
    boolean Z0078;
    
    public void init() {
        this.Z0109();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0103(), "East");
        (this.Z0016 = new DrawingArea()).setFont(new Font("Serif", 1, 12));
        contentPane.add(this.Z0016, "Center");
        this.Z0044 = new MouseAction();
        this.Z0016.addMouseListener(this.Z0044);
        this.Z0016.addMouseMotionListener(this.Z0044);
        this.Z0009 = new Cursor(1);
        this.Z0016.setCursor(this.Z0009);
        this.Z0001 = null;
    }
    
    JPanel Z0103() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0004 = new JButton[5];
        this.Z0090(0, panel, "Start");
        this.Z0093(panel, 0, 5);
        this.Z0090(1, panel, "Repeat");
        this.Z0093(panel, 0, 15);
        this.Z0032 = new JLabel[1];
        this.Z0092(0, panel, "View");
        this.Z0093(panel, 0, 5);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        this.Z0090(2, panel2, "Reset");
        this.Z0093(panel2, 0, 5);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0090(3, panel3, "<+>");
        this.Z0093(panel3, 5, 0);
        this.Z0090(4, panel3, ">-<");
        panel2.add(panel3);
        panel.add(panel2);
        this.Z0093(panel, 0, 20);
        this.Z0005 = new JCheckBox[2];
        this.Z0091(0, panel, "Trajectory");
        this.Z0093(panel, 0, 5);
        this.Z0091(1, panel, "Fix CM");
        this.Z0093(panel, 0, 20);
        this.Z0117 = new JSlider[3];
        this.Z0094(0, panel, this.Z0022, 1, 100, "Gravity");
        this.Z0094(1, panel, this.Z0041, 0, 60, "Mass(Log)");
        this.Z0094(2, panel, this.Z0122, 1, 50, "Update");
        return panel;
    }
    
    void Z0090(final int n, final JPanel panel, final String s) {
        (this.Z0004[n] = new JButton(s)).setPreferredSize(this.Z0004[n].getPreferredSize());
        this.Z0004[n].addActionListener(this);
        this.Z0004[n].setAlignmentX(0.5f);
        panel.add(this.Z0004[n]);
    }
    
    void Z0091(final int n, final JPanel panel, final String s) {
        (this.Z0005[n] = new JCheckBox(s)).addItemListener(this);
        this.Z0005[n].setAlignmentX(0.5f);
        panel.add(this.Z0005[n]);
    }
    
    void Z0092(final int n, final JPanel panel, final String s) {
        (this.Z0032[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0032[n]);
    }
    
    void Z0094(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0093(panel, 0, 3);
        (this.Z0117[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0117[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0117[n].setPreferredSize(preferredSize);
        this.Z0117[n].setMajorTickSpacing(n4 - n3);
        this.Z0117[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0117[n].setPaintLabels(true);
        this.Z0117[n].setPaintTicks(true);
        this.Z0117[n].addChangeListener(this);
        panel.add(this.Z0117[n]);
        this.Z0093(panel, 0, 5);
    }
    
    void Z0093(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0117[0]) {
                this.Z0022 = value;
            }
            else if (slider == this.Z0117[1]) {
                this.Z0041 = value;
                if (this.Z0015 >= 0) {
                    final int n = this.Z0041 / 10;
                    int n2 = this.Z0041 % 10;
                    if (n2 == 0) {
                        n2 = 1;
                    }
                    this.Z0039[this.Z0015] = n2 * Math.pow(10.0, n);
                    this.Z0003[this.Z0015] = 6 + n;
                    this.Z0078 = true;
                }
            }
            else if (slider == this.Z0117[2]) {
                this.Z0122 = value;
            }
        }
    }
    
    void Z0110(final int n) {
        if (n >= 0) {
            final int n2 = (int)(Math.log(this.Z0039[n]) / Math.log(10.0));
            this.Z0041 = 10 * n2 + (int)(this.Z0039[n] / Math.pow(10.0, n2));
        }
        else {
            this.Z0041 = 0;
        }
        this.Z0117[1].setValue(this.Z0041);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.Z0078 = false;
        final Object source = actionEvent.getSource();
        if (source == this.Z0004[0]) {
            this.Z0083 = !this.Z0083;
            this.Z0004[0].setText(this.Z0083 ? "Stop" : "Start");
            this.Z0101();
            this.Z0078 = true;
        }
        else if (source == this.Z0004[1]) {
            if (this.Z0083) {
                this.Z0083 = false;
                this.Z0101();
                this.Z0083 = true;
                this.Z0101();
                this.Z0078 = true;
            }
        }
        else if (source == this.Z0004[2]) {
            this.Z0069 = this.Z0079;
            this.Z0119 = 0.0;
            this.Z0120 = 0.0;
            this.Z0078 = true;
        }
        else if (source == this.Z0004[3]) {
            this.Z0069 *= Math.sqrt(2.0);
            this.Z0078 = true;
        }
        else if (source == this.Z0004[4]) {
            this.Z0069 /= Math.sqrt(2.0);
            this.Z0078 = true;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final ItemSelectable itemSelectable = itemEvent.getItemSelectable();
        if (itemSelectable == this.Z0005[0]) {
            this.Z0116 = (itemEvent.getStateChange() == 1);
            this.Z0029 = 0;
            this.Z0033 = 0;
        }
        else if (itemSelectable == this.Z0005[1]) {
            this.Z0021 = (itemEvent.getStateChange() == 1);
        }
        this.Z0078 = true;
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0016.getSize();
        final int n = size.width - size.height;
        this.Z0016.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0095();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0083) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0114();
                if (this.Z0121 % this.Z0122 != 0) {
                    continue;
                }
                this.Z0016.repaint();
                this.Z0016.getToolkit().sync();
                if (!this.Z0115(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0078) {
                    this.Z0078 = false;
                    this.Z0016.repaint();
                }
                if (!this.Z0115(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0115(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0109() {
        this.Z0022 = 50;
        this.Z0042 = 10;
        this.Z0040 = 20.0;
        this.Z0135 = 2.0;
        this.Z0134 = 20.0;
        this.Z0011 = 5.0E-4;
        this.Z0122 = 5;
        this.Z0035 = 500;
    }
    
    void Z0095() {
        this.Z0102();
        this.Z0079 = Math.min(this.Z0016.getSize().width, this.Z0016.getSize().height);
        this.Z0015 = -1;
        this.Z0083 = false;
        this.Z0116 = false;
        this.Z0021 = false;
        this.Z0069 = this.Z0079;
        this.Z0119 = 0.0;
        this.Z0120 = 0.0;
        this.Z0046 = 2;
        this.Z0088[0] = -this.Z0079 / 10 / this.Z0069 + this.Z0119;
        this.Z0089[0] = this.Z0079 / 10 / this.Z0069 + this.Z0120;
        this.Z0086[0] = this.Z0135 / 2.0;
        this.Z0087[0] = this.Z0135 / 2.0;
        this.Z0039[0] = this.Z0040;
        this.Z0003[0] = 6;
        this.Z0088[1] = this.Z0079 / 10 / this.Z0069 + this.Z0119;
        this.Z0089[1] = -this.Z0079 / 10 / this.Z0069 + this.Z0120;
        this.Z0086[1] = -this.Z0135 / 2.0;
        this.Z0087[1] = -this.Z0135 / 2.0;
        this.Z0039[1] = this.Z0040;
        this.Z0003[1] = 6;
    }
    
    void Z0102() {
        this.Z0088 = new double[this.Z0042];
        this.Z0089 = new double[this.Z0042];
        this.Z0080 = new double[this.Z0042];
        this.Z0081 = new double[this.Z0042];
        this.Z0086 = new double[this.Z0042];
        this.Z0087 = new double[this.Z0042];
        this.Z0076 = new double[this.Z0042];
        this.Z0077 = new double[this.Z0042];
        this.Z0072 = new double[this.Z0042];
        this.Z0073 = new double[this.Z0042];
        this.Z0074 = new double[this.Z0042];
        this.Z0075 = new double[this.Z0042];
        this.Z0067 = new double[this.Z0042];
        this.Z0068 = new double[this.Z0042];
        this.Z0084 = new double[this.Z0042];
        this.Z0085 = new double[this.Z0042];
        this.Z0039 = new double[this.Z0042];
        this.Z0003 = new int[this.Z0042];
        this.Z0070 = new double[this.Z0042][this.Z0035];
        this.Z0071 = new double[this.Z0042][this.Z0035];
        this.Z0065 = new double[this.Z0035];
        this.Z0066 = new double[this.Z0035];
    }
    
    void Z0114() {
        ++this.Z0121;
        this.Z0105();
        this.Z0096();
        this.Z0097();
        if (this.Z0121 % this.Z0122 == 0) {
            if (++this.Z0033 == this.Z0035) {
                this.Z0033 = 0;
            }
            if (this.Z0029 < this.Z0035) {
                ++this.Z0029;
            }
            for (int i = 0; i < this.Z0046; ++i) {
                this.Z0070[i][this.Z0033] = this.Z0088[i];
                this.Z0071[i][this.Z0033] = this.Z0089[i];
            }
            this.Z0098();
        }
    }
    
    void Z0107(int n, int n2, final int n3) {
        this.Z0078 = false;
        n -= this.left;
        n2 -= this.top;
        if (!this.Z0083) {
            final int n4 = 20;
            final int n5 = 10;
            switch (n3) {
                case 1: {
                    this.Z0015 = -1;
                    for (int i = 0; i < this.Z0046; ++i) {
                        final int n6 = this.Z0079 / 2 + (int)((this.Z0088[i] - this.Z0119) * this.Z0069);
                        final int n7 = this.Z0079 / 2 - (int)((this.Z0089[i] - this.Z0120) * this.Z0069);
                        final int n8 = n6 + (int)(this.Z0086[i] * this.Z0134);
                        final int n9 = n7 - (int)(this.Z0087[i] * this.Z0134);
                        if ((Math.abs(n - n6) < n4 && Math.abs(n2 - n7) < n4) || (Math.abs(n - n8) < n4 && Math.abs(n2 - n9) < n4)) {
                            this.Z0015 = i;
                            break;
                        }
                    }
                    if (this.Z0015 < 0 && this.Z0046 < this.Z0042 && n >= n5 && n <= this.Z0079 - n5 && n2 >= n5 && n2 <= this.Z0079 - n5) {
                        this.Z0088[this.Z0046] = (n - this.Z0079 / 2) / this.Z0069 + this.Z0119;
                        this.Z0089[this.Z0046] = (this.Z0079 / 2 - n2) / this.Z0069 + this.Z0120;
                        this.Z0086[this.Z0046] = this.Z0135;
                        this.Z0087[this.Z0046] = this.Z0135;
                        this.Z0039[this.Z0046] = this.Z0040;
                        this.Z0003[this.Z0046] = 6;
                        this.Z0015 = this.Z0046;
                        ++this.Z0046;
                    }
                    this.Z0078 = true;
                    this.Z0110(this.Z0015);
                    break;
                }
                case 0: {
                    if (this.Z0015 < 0) {
                        break;
                    }
                    final int n10 = this.Z0079 / 2 + (int)((this.Z0088[this.Z0015] - this.Z0119) * this.Z0069);
                    final int n11 = this.Z0079 / 2 - (int)((this.Z0089[this.Z0015] - this.Z0120) * this.Z0069);
                    final int n12 = n10 + (int)(this.Z0086[this.Z0015] * this.Z0134);
                    final int n13 = n11 - (int)(this.Z0087[this.Z0015] * this.Z0134);
                    if (Math.abs(n - n12) < n4 && Math.abs(n2 - n13) < n4) {
                        if (n != n12 || n2 != n13) {
                            this.Z0086[this.Z0015] = (n - n10) / this.Z0134;
                            this.Z0087[this.Z0015] = (n11 - n2) / this.Z0134;
                            this.Z0078 = true;
                            break;
                        }
                        break;
                    }
                    else {
                        if (Math.abs(n - n10) < n4 && Math.abs(n2 - n11) < n4 && (n != n10 || n2 != n11)) {
                            if (n >= n5 && n <= this.Z0079 - n5 && n2 >= n5 && n2 <= this.Z0079 - n5) {
                                this.Z0088[this.Z0015] = (n - this.Z0079 / 2) / this.Z0069 + this.Z0119;
                                this.Z0089[this.Z0015] = (this.Z0079 / 2 - n2) / this.Z0069 + this.Z0120;
                            }
                            else {
                                int n14 = 0;
                                for (int j = 0; j < this.Z0046; ++j) {
                                    if (j != this.Z0015) {
                                        this.Z0088[n14] = this.Z0088[j];
                                        this.Z0089[n14] = this.Z0089[j];
                                        this.Z0086[n14] = this.Z0086[j];
                                        this.Z0087[n14] = this.Z0087[j];
                                        this.Z0039[n14] = this.Z0039[j];
                                        ++n14;
                                    }
                                }
                                --this.Z0046;
                                this.Z0110(this.Z0015 = -1);
                            }
                            this.Z0078 = true;
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        else {
            switch (n3) {
                case 1: {
                    this.Z0119 += (n - this.Z0079 / 2) / this.Z0069;
                    this.Z0120 += (this.Z0079 / 2 - n2) / this.Z0069;
                    this.Z0078 = true;
                    break;
                }
            }
        }
        if (this.Z0078) {
            this.Z0078 = false;
            this.Z0016.repaint();
        }
    }
    
    void Z0101() {
        if (this.Z0083) {
            for (int i = 0; i < this.Z0046; ++i) {
                this.Z0067[i] = this.Z0088[i];
                this.Z0068[i] = this.Z0089[i];
                this.Z0084[i] = this.Z0086[i];
                this.Z0085[i] = this.Z0087[i];
                this.Z0076[i] = 0.0;
                this.Z0077[i] = 0.0;
                this.Z0072[i] = 0.0;
                this.Z0073[i] = 0.0;
                this.Z0074[i] = 0.0;
                this.Z0075[i] = 0.0;
            }
        }
        else {
            for (int j = 0; j < this.Z0046; ++j) {
                this.Z0088[j] = this.Z0067[j];
                this.Z0089[j] = this.Z0068[j];
                this.Z0086[j] = this.Z0084[j];
                this.Z0087[j] = this.Z0085[j];
            }
            this.Z0069 = this.Z0079;
            this.Z0119 = 0.0;
            this.Z0120 = 0.0;
        }
        this.Z0033 = 0;
        for (int k = 0; k < this.Z0046; ++k) {
            this.Z0070[k][this.Z0033] = this.Z0088[k];
            this.Z0071[k][this.Z0033] = this.Z0089[k];
        }
        this.Z0029 = 1;
        this.Z0098();
        this.Z0110(this.Z0015 = -1);
        this.Z0029 = 0;
        this.Z0033 = 0;
    }
    
    void Z0096() {
        final double n = 0.001 * this.Z0022;
        for (int i = 0; i < this.Z0046; ++i) {
            this.Z0076[i] = 0.0;
            this.Z0077[i] = 0.0;
        }
        this.Z0128 = 0.0;
        for (int j = 0; j < this.Z0046 - 1; ++j) {
            for (int k = j + 1; k < this.Z0046; ++k) {
                final double n2 = this.Z0088[j] - this.Z0088[k];
                final double n3 = this.Z0089[j] - this.Z0089[k];
                double n4 = n2 * n2 + n3 * n3;
                if (n4 < 1.0E-4) {
                    n4 = 1.0E-4;
                }
                final double n5 = -n / Math.sqrt(n4);
                this.Z0128 += this.Z0039[j] * this.Z0039[k] * n5;
                final double n6 = n5 / n4;
                final double n7 = n2 * n6;
                final double n8 = n3 * n6;
                final double[] z0076 = this.Z0076;
                final int n9 = j;
                z0076[n9] += this.Z0039[k] * n7;
                final double[] z77 = this.Z0077;
                final int n10 = j;
                z77[n10] += this.Z0039[k] * n8;
                final double[] z78 = this.Z0076;
                final int n11 = k;
                z78[n11] -= this.Z0039[j] * n7;
                final double[] z79 = this.Z0077;
                final int n12 = k;
                z79[n12] -= this.Z0039[j] * n8;
            }
        }
    }
    
    void Z0105() {
        final double[] array = { 19.0, -10.0, 3.0 };
        final double[] array2 = { 27.0, -22.0, 7.0 };
        final double n = 24.0;
        final double n2 = this.Z0011 * this.Z0011 / n;
        final double n3 = this.Z0011 / n;
        for (int i = 0; i < this.Z0046; ++i) {
            this.Z0080[i] = this.Z0088[i];
            this.Z0081[i] = this.Z0089[i];
            final double[] z0088 = this.Z0088;
            final int n4 = i;
            z0088[n4] += this.Z0011 * this.Z0086[i] + n2 * (array[0] * this.Z0076[i] + array[1] * this.Z0072[i] + array[2] * this.Z0074[i]);
            final double[] z89 = this.Z0089;
            final int n5 = i;
            z89[n5] += this.Z0011 * this.Z0087[i] + n2 * (array[0] * this.Z0077[i] + array[1] * this.Z0073[i] + array[2] * this.Z0075[i]);
            this.Z0074[i] = this.Z0072[i];
            this.Z0075[i] = this.Z0073[i];
            this.Z0072[i] = this.Z0076[i];
            this.Z0073[i] = this.Z0077[i];
        }
    }
    
    void Z0097() {
        final double[] array = { 3.0, 10.0, -1.0 };
        final double[] array2 = { 7.0, 6.0, -1.0 };
        final double n = 24.0;
        final double n2 = this.Z0011 * this.Z0011 / n;
        final double n3 = this.Z0011 / n;
        for (int i = 0; i < this.Z0046; ++i) {
            this.Z0088[i] = this.Z0080[i] + this.Z0011 * this.Z0086[i] + n2 * (array[0] * this.Z0076[i] + array[1] * this.Z0072[i] + array[2] * this.Z0074[i]);
            this.Z0089[i] = this.Z0081[i] + this.Z0011 * this.Z0087[i] + n2 * (array[0] * this.Z0077[i] + array[1] * this.Z0073[i] + array[2] * this.Z0075[i]);
            this.Z0086[i] = (this.Z0088[i] - this.Z0080[i]) / this.Z0011 + n3 * (array2[0] * this.Z0076[i] + array2[1] * this.Z0072[i] + array2[2] * this.Z0074[i]);
            this.Z0087[i] = (this.Z0089[i] - this.Z0081[i]) / this.Z0011 + n3 * (array2[0] * this.Z0077[i] + array2[1] * this.Z0073[i] + array2[2] * this.Z0075[i]);
        }
    }
    
    void Z0098() {
        double n = 0.0;
        this.Z0065[this.Z0033] = 0.0;
        this.Z0066[this.Z0033] = 0.0;
        for (int i = 0; i < this.Z0046; ++i) {
            final double[] z0065 = this.Z0065;
            final int z66 = this.Z0033;
            z0065[z66] += this.Z0039[i] * this.Z0088[i];
            final double[] z67 = this.Z0066;
            final int z68 = this.Z0033;
            z67[z68] += this.Z0039[i] * this.Z0089[i];
            n += this.Z0039[i];
        }
        final double[] z69 = this.Z0065;
        final int z70 = this.Z0033;
        z69[z70] /= n;
        final double[] z71 = this.Z0066;
        final int z72 = this.Z0033;
        z71[z72] /= n;
    }
    
    void Z0108(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0016.getSize();
        final Insets insets = this.Z0016.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        this.Z0079 = Math.min(n, n2);
        this.left = insets.left + (n - this.Z0079) / 2;
        this.top = insets.top + (n2 - this.Z0079) / 2;
        graphics.setColor(new Color(63, 63, 255));
        graphics.fillRect(this.left, this.top, this.Z0079, this.Z0079);
        graphics.setColor(Color.yellow);
        if (this.Z0083 && this.Z0116 && this.Z0029 > 1) {
            for (int i = 0; i < this.Z0046; ++i) {
                this.Z0113(graphics, i);
            }
        }
        for (int j = 0; j < this.Z0046; ++j) {
            this.Z0111(graphics, j);
        }
        if (!this.Z0083) {
            for (int k = 0; k < this.Z0046; ++k) {
                this.Z0112(graphics, k);
            }
        }
        if (!this.Z0083 && this.Z0015 >= 0) {
            graphics.setColor(Color.black);
            this.Z0106(graphics, this.Z0015);
        }
    }
    
    void Z0111(final Graphics graphics, final int n) {
        double n2 = this.Z0088[n];
        double n3 = this.Z0089[n];
        if (this.Z0021 && this.Z0083) {
            n2 -= this.Z0065[this.Z0033];
            n3 -= this.Z0066[this.Z0033];
        }
        final int n4 = (int)((n2 - this.Z0119) * this.Z0069);
        final int n5 = (int)((n3 - this.Z0120) * this.Z0069);
        if (Math.abs(n4) < this.Z0079 / 2 && Math.abs(n5) < this.Z0079 / 2) {
            graphics.fillOval(this.Z0079 / 2 - this.Z0003[n] / 2 + n4 + this.left, this.Z0079 / 2 - this.Z0003[n] / 2 - n5 + this.top, this.Z0003[n], this.Z0003[n]);
        }
    }
    
    void Z0113(final Graphics graphics, final int n) {
        int z0033 = this.Z0033;
        for (int i = 1; i < this.Z0029; ++i) {
            int n2 = z0033 - 1;
            if (n2 < 0) {
                n2 += this.Z0035;
            }
            double n3 = this.Z0070[n][z0033];
            double n4 = this.Z0071[n][z0033];
            double n5 = this.Z0070[n][n2];
            double n6 = this.Z0071[n][n2];
            if (this.Z0021) {
                n3 -= this.Z0065[z0033];
                n4 -= this.Z0066[z0033];
                n5 -= this.Z0065[n2];
                n6 -= this.Z0066[n2];
            }
            final int n7 = (int)((n3 - this.Z0119) * this.Z0069);
            final int n8 = (int)((n4 - this.Z0120) * this.Z0069);
            final int n9 = (int)((n5 - this.Z0119) * this.Z0069);
            final int n10 = (int)((n6 - this.Z0120) * this.Z0069);
            if (Math.abs(n7) < this.Z0079 / 2 && Math.abs(n8) < this.Z0079 / 2 && Math.abs(n9) < this.Z0079 / 2 && Math.abs(n10) < this.Z0079 / 2) {
                graphics.drawLine(this.Z0079 / 2 + n7 + this.left, this.Z0079 / 2 - n8 + this.top, this.Z0079 / 2 + n9 + this.left, this.Z0079 / 2 - n10 + this.top);
            }
            if (--z0033 < 0) {
                z0033 += this.Z0035;
            }
        }
    }
    
    void Z0112(final Graphics graphics, final int n) {
        final double n2 = -8.01;
        final double n3 = 5.98;
        final double n4 = this.Z0079 / 2 + (int)((this.Z0088[n] - this.Z0119) * this.Z0069);
        final double n5 = this.Z0079 / 2 - (int)((this.Z0089[n] - this.Z0119) * this.Z0069);
        double n6 = this.Z0086[n];
        double n7 = this.Z0087[n];
        final double sqrt = Math.sqrt(n6 * n6 + n7 * n7);
        final double n8 = sqrt * this.Z0134;
        if (sqrt > 0.0) {
            n6 /= sqrt;
            n7 /= sqrt;
        }
        final int n9 = (int)(n4 + n6 * n8);
        final int n10 = (int)(n5 - n7 * n8);
        final int n11 = (int)(n4 - n6 * n8);
        final int n12 = (int)(n5 + n7 * n8);
        final int n13 = n9 + (int)(n6 * n2 + n7 * n3);
        final int n14 = n10 - (int)(n7 * n2 - n6 * n3);
        final int n15 = n9 + (int)(n6 * n2 - n7 * n3);
        final int n16 = n10 - (int)(n7 * n2 + n6 * n3);
        graphics.drawLine(n9 + this.left, n10 + this.top, n11 + this.left, n12 + this.top);
        graphics.drawLine(n9 + this.left, n10 + this.top, n13 + this.left, n14 + this.top);
        graphics.drawLine(n9 + this.left, n10 + this.top, n15 + this.left, n16 + this.top);
    }
    
    void Z0106(final Graphics graphics, final int n) {
        final int n2 = this.Z0079 / 2 + (int)((this.Z0088[n] - this.Z0119) * this.Z0069) + this.left;
        final int n3 = this.Z0079 / 2 - (int)((this.Z0089[n] - this.Z0120) * this.Z0069) + this.top;
        graphics.drawString("X: " + this.Z0100(this.Z0088[n], 2) + "   Y: " + this.Z0100(this.Z0089[n], 2), n2, n3 + 20);
        graphics.drawString("Vx: " + this.Z0100(this.Z0086[n], 2) + "   Vy: " + this.Z0100(this.Z0087[n], 2), n2, n3 + 40);
    }
    
    String Z0100(final double n, final int n2) {
        final NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(n2);
        numberInstance.setMaximumFractionDigits(n2);
        return numberInstance.format(n);
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Kepler.this.Z0107(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Kepler.this.Z0107(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Kepler.this.Z0107(mouseEvent.getX(), mouseEvent.getY(), -1);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Kepler.this.Z0108(graphics);
        }
    }
}
