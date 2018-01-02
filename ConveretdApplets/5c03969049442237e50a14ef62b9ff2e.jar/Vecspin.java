import java.awt.Insets;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
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
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Vecspin extends JApplet implements Runnable, ActionListener, ChangeListener, ItemListener
{
    Thread Z0002;
    JButton[] Z0008;
    JSlider[] Z0071;
    JCheckBox[] Z0013;
    DrawingArea Z0019;
    Color Z0007;
    Random Z0039;
    double[] Z0086;
    double[] Z0087;
    double[] Z0084;
    double[] Z0085;
    double[] Z0080;
    double[] Z0081;
    double[] Z0082;
    double[] Z0083;
    double[] Z0073;
    double[] Z0074;
    double[] Z0078;
    double[] Z0068;
    double[] Z0069;
    double[] Z0070;
    double[] Z0079;
    double Z0017;
    double Z0090;
    double Z0096;
    int Z0011;
    int Z0024;
    int Z0023;
    int Z0030;
    int Z0076;
    int Z0077;
    boolean Z0041;
    boolean Z0001;
    boolean Z0040;
    
    public void init() {
        this.Z0063();
        this.Z0039 = new Random();
        this.Z0007 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0059(), "East");
        contentPane.add(this.Z0019 = new DrawingArea(), "Center");
        this.Z0039 = new Random();
        this.Z0002 = null;
    }
    
    JPanel Z0059() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0007), panel.getBorder()));
        this.Z0008 = new JButton[1];
        this.Z0044(0, panel, "Start");
        this.Z0046(panel, 0, 20);
        this.Z0071 = new JSlider[4];
        this.Z0047(0, panel, (int)(100.0 * this.Z0090), 1, 400, "Temperature (X 100)");
        this.Z0047(1, panel, this.Z0023, -5, 5, "Field");
        this.Z0047(2, panel, this.Z0077, 1, 20, "Update");
        this.Z0047(3, panel, this.Z0024, 8, 32, "Grid");
        this.Z0046(panel, 0, 20);
        this.Z0013 = new JCheckBox[1];
        this.Z0045(0, panel, "Antiferro");
        return panel;
    }
    
    void Z0044(final int n, final JPanel panel, final String s) {
        (this.Z0008[n] = new JButton(s)).setAlignmentX(0.5f);
        this.Z0008[n].setPreferredSize(this.Z0008[n].getPreferredSize());
        this.Z0008[n].addActionListener(this);
        panel.add(this.Z0008[n]);
    }
    
    void Z0045(final int n, final JPanel panel, final String s) {
        (this.Z0013[n] = new JCheckBox(s)).addItemListener(this);
        this.Z0013[n].setAlignmentX(0.5f);
        panel.add(this.Z0013[n]);
    }
    
    void Z0047(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0046(panel, 0, 3);
        (this.Z0071[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0071[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0071[n].setPreferredSize(preferredSize);
        this.Z0071[n].setMajorTickSpacing(n4 - n3);
        this.Z0071[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0071[n].setPaintLabels(true);
        this.Z0071[n].setPaintTicks(true);
        this.Z0071[n].addChangeListener(this);
        panel.add(this.Z0071[n]);
        this.Z0046(panel, 0, 5);
    }
    
    void Z0046(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0071[0]) {
                this.Z0090 = 0.01 * value;
                this.Z0052();
            }
            else if (slider == this.Z0071[1]) {
                this.Z0023 = value;
            }
            else if (slider == this.Z0071[2]) {
                this.Z0077 = value;
            }
            else if (slider == this.Z0071[3]) {
                this.Z0024 = (value + 4) / 8 * 8;
                this.Z0051();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.Z0008[0]) {
            this.Z0041 = !this.Z0041;
            this.Z0008[0].setText(this.Z0041 ? "Stop" : "Start");
            this.Z0071[3].setEnabled(!this.Z0041);
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.Z0013[0]) {
            this.Z0001 = (itemEvent.getStateChange() == 1);
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0019.getSize();
        final int n = size.width - size.height;
        this.Z0019.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0007), BorderFactory.createRaisedBevelBorder()));
        this.Z0051();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0041) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0064();
                if (this.Z0076 % this.Z0077 != 0) {
                    continue;
                }
                this.Z0019.repaint();
                this.Z0019.getToolkit().sync();
                if (!this.Z0065(Math.max(5L, 40L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0040) {
                    this.Z0040 = false;
                    this.Z0019.repaint();
                }
                if (!this.Z0065(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0065(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0063() {
        this.Z0024 = 16;
        this.Z0090 = 2.0;
        this.Z0023 = 0;
        this.Z0017 = 0.01;
        this.Z0001 = false;
        this.Z0077 = 5;
    }
    
    void Z0051() {
        this.Z0030 = this.Z0024 * this.Z0024;
        this.Z0058();
        this.Z0060();
    }
    
    void Z0058() {
        this.Z0086 = new double[this.Z0030];
        this.Z0087 = new double[this.Z0030];
        this.Z0078 = new double[this.Z0030];
        this.Z0068 = new double[this.Z0030];
        this.Z0069 = new double[this.Z0030];
        this.Z0070 = new double[this.Z0030];
        this.Z0084 = new double[this.Z0030];
        this.Z0085 = new double[this.Z0030];
        this.Z0080 = new double[this.Z0030];
        this.Z0081 = new double[this.Z0030];
        this.Z0082 = new double[this.Z0030];
        this.Z0083 = new double[this.Z0030];
        this.Z0073 = new double[this.Z0030];
        this.Z0074 = new double[this.Z0030];
        this.Z0079 = new double[this.Z0030];
    }
    
    void Z0064() {
        ++this.Z0076;
        this.Z0061();
        this.Z0053();
        this.Z0050();
        this.Z0054();
        this.Z0055();
        if (this.Z0076 % 10 == 0) {
            this.Z0048();
        }
        if (this.Z0076 % 100 == 0) {
            this.Z0049();
        }
    }
    
    void Z0053() {
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0068[i] = 0.0;
        }
        final double n = this.Z0023;
        int n2 = 0;
        for (int j = 0; j < this.Z0024; ++j) {
            int z0024 = this.Z0024;
            if (j == 0) {
                z0024 -= this.Z0024 * this.Z0024;
            }
            for (int k = 0; k < this.Z0024; ++k) {
                int n3 = 1;
                if (k == 0) {
                    n3 -= this.Z0024;
                }
                final int n4 = n2 - n3;
                double n5 = this.Z0086[n2] * this.Z0087[n4] - this.Z0087[n2] * this.Z0086[n4];
                if (this.Z0001) {
                    n5 = -n5;
                }
                final double[] z25 = this.Z0068;
                final int n6 = n2;
                z25[n6] += n5;
                final double[] z26 = this.Z0068;
                final int n7 = n4;
                z26[n7] -= n5;
                final int n8 = n2 - z0024;
                double n9 = this.Z0086[n2] * this.Z0087[n8] - this.Z0087[n2] * this.Z0086[n8];
                if (this.Z0001) {
                    n9 = -n9;
                }
                final double[] z27 = this.Z0068;
                final int n10 = n2;
                z27[n10] += n9;
                final double[] z28 = this.Z0068;
                final int n11 = n8;
                z28[n11] -= n9;
                final double[] z29 = this.Z0068;
                final int n12 = n2;
                z29[n12] -= n * this.Z0086[n2];
                ++n2;
            }
        }
    }
    
    void Z0061() {
        final double[] array = { 23.0, -16.0, 5.0 };
        final double n = this.Z0017 / 12.0;
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0073[i] = this.Z0086[i];
            this.Z0074[i] = this.Z0087[i];
            this.Z0079[i] = this.Z0078[i];
            final double[] z0086 = this.Z0086;
            final int n2 = i;
            z0086[n2] += n * (array[0] * this.Z0084[i] + array[1] * this.Z0080[i] + array[2] * this.Z0082[i]);
            final double[] z87 = this.Z0087;
            final int n3 = i;
            z87[n3] += n * (array[0] * this.Z0085[i] + array[1] * this.Z0081[i] + array[2] * this.Z0083[i]);
            this.Z0082[i] = this.Z0080[i];
            this.Z0083[i] = this.Z0081[i];
            this.Z0080[i] = this.Z0084[i];
            this.Z0081[i] = this.Z0085[i];
            this.Z0078[i] += n * (array[0] * this.Z0068[i] + array[1] * this.Z0069[i] + array[2] * this.Z0070[i]);
            this.Z0070[i] = this.Z0069[i];
            this.Z0069[i] = this.Z0068[i];
            this.Z0084[i] = -this.Z0078[i] * this.Z0087[i];
            this.Z0085[i] = this.Z0078[i] * this.Z0086[i];
        }
    }
    
    void Z0054() {
        final double[] array = { 5.0, 8.0, -1.0 };
        final double n = this.Z0017 / 12.0;
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0086[i] = this.Z0073[i] + n * (array[0] * this.Z0084[i] + array[1] * this.Z0080[i] + array[2] * this.Z0082[i]);
            this.Z0087[i] = this.Z0074[i] + n * (array[0] * this.Z0085[i] + array[1] * this.Z0081[i] + array[2] * this.Z0083[i]);
            this.Z0078[i] = this.Z0079[i] + n * (array[0] * this.Z0068[i] + array[1] * this.Z0069[i] + array[2] * this.Z0070[i]);
            this.Z0084[i] = -this.Z0078[i] * this.Z0087[i];
            this.Z0085[i] = this.Z0078[i] * this.Z0086[i];
        }
    }
    
    void Z0050() {
        double n = 0.0;
        double n2 = 0.0;
        for (int i = 0; i < this.Z0030; ++i) {
            n += this.Z0078[i] * this.Z0068[i];
            n2 += this.Z0078[i] * this.Z0078[i];
        }
        final double n3 = -n / n2;
        for (int j = 0; j < this.Z0030; ++j) {
            final double[] z0068 = this.Z0068;
            final int n4 = j;
            z0068[n4] += n3 * this.Z0078[j];
        }
    }
    
    void Z0048() {
        for (int i = 0; i < this.Z0030; ++i) {
            final double sqrt = Math.sqrt(this.Z0086[i] * this.Z0086[i] + this.Z0087[i] * this.Z0087[i]);
            final double[] z0086 = this.Z0086;
            final int n = i;
            z0086[n] /= sqrt;
            final double[] z87 = this.Z0087;
            final int n2 = i;
            z87[n2] /= sqrt;
        }
    }
    
    void Z0055() {
        this.Z0096 = 0.0;
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0096 += this.Z0078[i] * this.Z0078[i];
        }
    }
    
    void Z0060() {
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0086[i] = 0.0;
            this.Z0087[i] = 1.0;
        }
        final double sqrt = Math.sqrt(this.Z0090);
        double n = 0.0;
        for (int j = 0; j < this.Z0030; ++j) {
            this.Z0078[j] = sqrt * 2.0 * this.Z0039.nextDouble();
            n += this.Z0078[j];
        }
        for (int k = 0; k < this.Z0030; ++k) {
            final double[] z0078 = this.Z0078;
            final int n2 = k;
            z0078[n2] -= n / this.Z0030;
        }
        for (int l = 0; l < this.Z0030; ++l) {
            this.Z0084[l] = -this.Z0078[l] * this.Z0086[l];
            this.Z0085[l] = this.Z0078[l] * this.Z0087[l];
            final double[] z79 = this.Z0070;
            final int n3 = l;
            final double[] z80 = this.Z0069;
            final int n4 = l;
            final double[] z81 = this.Z0068;
            final int n5 = l;
            final double n6 = 0.0;
            z81[n5] = n6;
            z79[n3] = (z80[n4] = n6);
            this.Z0080[l] = this.Z0084[l];
            this.Z0081[l] = this.Z0085[l];
            this.Z0082[l] = this.Z0084[l];
            this.Z0083[l] = this.Z0085[l];
        }
        this.Z0076 = 0;
        this.Z0040 = true;
    }
    
    void Z0049() {
        final double n = Math.sqrt(this.Z0090) / Math.sqrt(this.Z0096 / this.Z0030);
        for (int i = 0; i < this.Z0030; ++i) {
            final double[] z0078 = this.Z0078;
            final int n2 = i;
            z0078[n2] *= n;
        }
    }
    
    void Z0052() {
        final double sqrt = Math.sqrt(this.Z0090);
        final double n = sqrt / Math.sqrt(this.Z0096 / this.Z0030);
        if (n < 1.0 || sqrt > 2.0) {
            for (int i = 0; i < this.Z0030; ++i) {
                final double[] z0078 = this.Z0078;
                final int n2 = i;
                z0078[n2] *= n;
            }
        }
        else {
            double n3 = 0.0;
            for (int j = 0; j < this.Z0030; ++j) {
                if (this.Z0078[j] < 0.0) {
                    this.Z0078[j] = -1.0;
                }
                else {
                    this.Z0078[j] = 1.0;
                }
                n3 += this.Z0078[j];
            }
            for (int k = 0; k < this.Z0030; ++k) {
                final double[] z79 = this.Z0078;
                final int n4 = k;
                z79[n4] -= n3 / this.Z0030;
            }
            this.Z0055();
            this.Z0049();
        }
    }
    
    void Z0062(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0019.getSize();
        final Insets insets = this.Z0019.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        int n3 = Math.min(n, n2) / this.Z0024;
        if (n3 < 1) {
            n3 = 1;
        }
        final int n4 = this.Z0024 * n3;
        final int n5 = insets.left + (n - n4) / 2;
        final int n6 = insets.top + (n2 - n4) / 2;
        graphics.setColor(Color.pink);
        graphics.fillRect(n5, n6, n4, n4);
        graphics.setColor(Color.blue);
        final double n7 = 0.3 * n3;
        final double n8 = 0.6 * n7;
        final double n9 = -n8 * 0.801;
        final double n10 = n8 * 0.598;
        int n11 = 0;
        for (int i = 0; i < this.Z0024; ++i) {
            final double n12 = n3 * (i + 0.5);
            for (int j = 0; j < this.Z0024; ++j) {
                final double n13 = n3 * (j + 0.5);
                final double n14 = this.Z0086[n11];
                final double n15 = this.Z0087[n11];
                final int n16 = n5 + (int)(n13 - n14 * n7);
                final int n17 = n6 + (int)(n12 - n15 * n7);
                final int n18 = n5 + (int)(n13 + n14 * n7);
                final int n19 = n6 + (int)(n12 + n15 * n7);
                final int n20 = n18 + (int)(n14 * n9 + n15 * n10);
                final int n21 = n19 + (int)(n15 * n9 - n14 * n10);
                final int n22 = n18 + (int)(n14 * n9 - n15 * n10);
                final int n23 = n19 + (int)(n15 * n9 + n14 * n10);
                graphics.drawLine(n16, n17, n18, n19);
                graphics.drawLine(n20, n21, n18, n19);
                graphics.drawLine(n18, n19, n22, n23);
                ++n11;
            }
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Vecspin.this.Z0062(graphics);
        }
    }
}
