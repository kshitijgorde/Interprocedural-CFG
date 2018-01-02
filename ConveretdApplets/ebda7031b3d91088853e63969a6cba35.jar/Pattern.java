import java.text.NumberFormat;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.Box;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pattern extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0003;
    JButton[] Z0007;
    JSlider[] Z0071;
    JLabel[] Z0039;
    DrawingArea Z0021;
    Color Z0006;
    Color[] Z0046;
    Random Z0049;
    double[] Z0083;
    double[] Z0085;
    double[] Z0084;
    double[] Z0086;
    double[] Z0047;
    double[] Z0048;
    double[] Z0088;
    double[] Z0089;
    double[] b;
    double[] u;
    double[] z;
    double[] Z0026;
    double Z0013;
    double Z0014;
    double Z0019;
    double Z0020;
    double Z0016;
    double Z0018;
    int Z0011;
    int Z0028;
    int Z0042;
    int Z0074;
    int Z0075;
    boolean Z0052;
    boolean Z0051;
    
    public void init() {
        this.Z0064();
        this.Z0006 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0061(), "East");
        contentPane.add(this.Z0021 = new DrawingArea(), "Center");
        this.Z0049 = new Random();
        this.Z0003 = null;
    }
    
    JPanel Z0061() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0006), panel.getBorder()));
        this.Z0007 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0053(0, panel2, "Reset");
        this.Z0055(panel2, 10, 0);
        this.Z0053(1, panel2, "Start");
        panel.add(panel2);
        this.Z0055(panel, 0, 20);
        this.Z0071 = new JSlider[4];
        this.Z0056(0, panel, (int)(10000.0 * this.Z0014), 100, 800, "Const-K (X 10000)");
        this.Z0056(1, panel, (int)(10000.0 * this.Z0013), 100, 800, "Const-F (X 10000)");
        this.Z0056(2, panel, this.Z0028, 64, 128, "Grid");
        this.Z0056(3, panel, this.Z0075, 1, 50, "Update");
        this.Z0055(panel, 0, 20);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        panel3.setAlignmentX(0.5f);
        this.Z0039 = new JLabel[2];
        this.Z0054(0, panel3, " ");
        this.Z0055(panel3, 0, 5);
        this.Z0054(1, panel3, " ");
        panel.add(panel3);
        return panel;
    }
    
    void Z0053(final int n, final JPanel panel, final String s) {
        (this.Z0007[n] = new JButton(s)).setPreferredSize(this.Z0007[n].getPreferredSize());
        this.Z0007[n].addActionListener(this);
        panel.add(this.Z0007[n]);
    }
    
    void Z0056(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0055(panel, 0, 3);
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
        this.Z0055(panel, 0, 5);
    }
    
    void Z0054(final int n, final JPanel panel, final String s) {
        (this.Z0039[n] = new JLabel(s, 2)).setAlignmentX(0.0f);
        this.Z0039[n].setFont(new Font("Sanserif", 1, 14));
        panel.add(this.Z0039[n]);
    }
    
    void Z0055(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0071[0]) {
                this.Z0014 = 1.0E-4 * value;
                if (this.Z0074 > 0) {
                    this.Z0062();
                }
                this.Z0065();
            }
            else if (slider == this.Z0071[1]) {
                this.Z0013 = 1.0E-4 * value;
                if (this.Z0074 > 0) {
                    this.Z0062();
                }
                this.Z0065();
            }
            else if (slider == this.Z0071[2]) {
                this.Z0028 = (value + 4) / 8 * 8;
                this.Z0057();
            }
            else if (slider == this.Z0071[3]) {
                this.Z0075 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0007[0]) {
            this.Z0062();
        }
        else if (source == this.Z0007[1]) {
            this.Z0052 = !this.Z0052;
            this.Z0007[1].setText(this.Z0052 ? "Stop" : "Start");
            this.Z0071[0].setEnabled(!this.Z0052);
            this.Z0071[1].setEnabled(!this.Z0052);
            this.Z0071[2].setEnabled(!this.Z0052);
        }
    }
    
    public void start() {
        if (this.Z0003 == null) {
            (this.Z0003 = new Thread(this)).setPriority(1);
            this.Z0003.start();
        }
        final Dimension size = this.Z0021.getSize();
        final int n = size.width - size.height;
        this.Z0021.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0006), BorderFactory.createRaisedBevelBorder()));
        this.Z0057();
    }
    
    public void stop() {
        this.Z0003 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0003) {
            if (this.Z0052) {
                this.Z0067();
                if (this.Z0074 % this.Z0075 != 0) {
                    continue;
                }
                this.Z0021.repaint();
                this.Z0021.getToolkit().sync();
                if (!this.Z0068(500L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0051) {
                    this.Z0051 = false;
                    this.Z0021.repaint();
                }
                if (!this.Z0068(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0068(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0064() {
        this.Z0028 = 64;
        this.Z0075 = 5;
        this.Z0019 = 2.0E-5;
        this.Z0020 = 1.0E-5;
        this.Z0013 = 0.055;
        this.Z0014 = 0.065;
        this.Z0018 = 0.01;
        this.Z0016 = 10.0;
        this.Z0042 = 100;
    }
    
    void Z0057() {
        this.Z0060();
        this.Z0066(0.7);
        this.Z0052 = false;
        this.Z0062();
        this.Z0065();
    }
    
    void Z0060() {
        final int n = this.Z0028 * this.Z0028;
        this.Z0083 = new double[n];
        this.Z0085 = new double[n];
        this.Z0084 = new double[n];
        this.Z0086 = new double[n];
        this.Z0047 = new double[n];
        this.Z0048 = new double[n];
        this.Z0088 = new double[n];
        this.Z0089 = new double[n];
        this.b = new double[n];
        this.u = new double[n];
        this.z = new double[n];
        this.Z0026 = new double[n];
        this.Z0046 = new Color[this.Z0042];
    }
    
    void Z0062() {
        this.Z0074 = 0;
        for (int i = 0; i < this.Z0028 * this.Z0028; ++i) {
            this.Z0083[i] = 1.0;
            this.Z0085[i] = 0.0;
        }
        for (int j = this.Z0028 / 2 - 10; j <= this.Z0028 / 2 + 10; ++j) {
            for (int k = this.Z0028 / 2 - 10; k <= this.Z0028 / 2 + 10; ++k) {
                this.Z0083[j * this.Z0028 + k] = 0.5 + 0.02 * (this.Z0049.nextDouble() - 0.5);
                this.Z0085[j * this.Z0028 + k] = 0.25 + 0.02 * (this.Z0049.nextDouble() - 0.5);
            }
        }
        this.Z0051 = true;
    }
    
    void Z0067() {
        ++this.Z0074;
        double z0016 = this.Z0016;
        if (this.Z0074 < 10 && z0016 > 5.0) {
            z0016 = 5.0;
        }
        final double n = 0.5 * z0016 * this.Z0019 / (this.Z0018 * this.Z0018);
        final double n2 = 0.5 * z0016 * this.Z0020 / (this.Z0018 * this.Z0018);
        final double n3 = 0.5 * z0016;
        final double n4 = 1.0 - 2.0 * n;
        final double n5 = 1.0 - 2.0 * n2;
        for (int i = 0; i < this.Z0028; ++i) {
            int n6 = -this.Z0028;
            int z17 = this.Z0028;
            if (i == 0) {
                n6 += this.Z0028 * this.Z0028;
            }
            else if (i == this.Z0028 - 1) {
                z17 -= this.Z0028 * this.Z0028;
            }
            for (int j = 0; j < this.Z0028; ++j) {
                final int n7 = i * this.Z0028 + j;
                this.Z0047[j] = n * (this.Z0083[n7 + n6] + this.Z0083[n7 + z17]) + n4 * this.Z0083[n7] + n3 * (-this.Z0083[n7] * this.Z0085[n7] * this.Z0085[n7] + this.Z0013 * (1.0 - this.Z0083[n7]));
                this.Z0048[j] = n2 * (this.Z0085[n7 + n6] + this.Z0085[n7 + z17]) + n5 * this.Z0085[n7] + n3 * (this.Z0083[n7] * this.Z0085[n7] * this.Z0085[n7] - (this.Z0013 + this.Z0014) * this.Z0085[n7]);
            }
            this.Z0069(n, this.Z0047, this.Z0088, this.b, this.u, this.z, this.Z0026, this.Z0028);
            this.Z0069(n2, this.Z0048, this.Z0089, this.b, this.u, this.z, this.Z0026, this.Z0028);
            for (int k = 0; k < this.Z0028; ++k) {
                this.Z0084[i * this.Z0028 + k] = this.Z0088[k];
                this.Z0086[i * this.Z0028 + k] = this.Z0089[k];
            }
        }
        for (int l = 0; l < this.Z0028; ++l) {
            int n8 = -1;
            int n9 = 1;
            if (l == 0) {
                n8 += this.Z0028;
            }
            else if (l == this.Z0028 - 1) {
                n9 -= this.Z0028;
            }
            for (int n10 = 0; n10 < this.Z0028; ++n10) {
                final int n11 = n10 * this.Z0028 + l;
                this.Z0047[n10] = n * (this.Z0084[n11 + n8] + this.Z0084[n11 + n9]) + n4 * this.Z0084[n11] + n3 * (-this.Z0084[n11] * this.Z0086[n11] * this.Z0086[n11] + this.Z0013 * (1.0 - this.Z0084[n11]));
                this.Z0048[n10] = n2 * (this.Z0086[n11 + n8] + this.Z0086[n11 + n9]) + n5 * this.Z0086[n11] + n3 * (this.Z0084[n11] * this.Z0086[n11] * this.Z0086[n11] - (this.Z0013 + this.Z0014) * this.Z0086[n11]);
            }
            this.Z0069(n, this.Z0047, this.Z0088, this.b, this.u, this.z, this.Z0026, this.Z0028);
            this.Z0069(n2, this.Z0048, this.Z0089, this.b, this.u, this.z, this.Z0026, this.Z0028);
            for (int n12 = 0; n12 < this.Z0028; ++n12) {
                this.Z0083[n12 * this.Z0028 + l] = this.Z0088[n12];
                this.Z0085[n12 * this.Z0028 + l] = this.Z0089[n12];
            }
        }
    }
    
    void Z0069(final double n, final double[] array, final double[] array2, final double[] array3, final double[] array4, final double[] array5, final double[] array6, final int n2) {
        final double n3 = -n;
        final double n4 = 1.0 + 2.0 * n;
        array3[0] = 2.0 * n4;
        array3[n2 - 1] = n4 + n3 * n3 / n4;
        array4[0] = -n4;
        array4[n2 - 1] = n3;
        for (int i = 1; i < n2 - 1; ++i) {
            array3[i] = n4;
            array4[i] = 0.0;
        }
        this.Z0070(n3, array3, array, array2, array6, n2);
        this.Z0070(n3, array3, array4, array5, array6, n2);
        final double n5 = (n4 * array2[0] - n3 * array2[n2 - 1]) / (n4 + n4 * array5[0] - n3 * array5[n2 - 1]);
        for (int j = 0; j < n2; ++j) {
            final int n6 = j;
            array2[n6] -= n5 * array5[j];
        }
    }
    
    void Z0070(final double n, final double[] array, final double[] array2, final double[] array3, final double[] array4, final int n2) {
        double n3 = array[0];
        array3[0] = array2[0] / n3;
        for (int i = 1; i < n2; ++i) {
            array4[i] = n / n3;
            n3 = array[i] - n * array4[i];
            array3[i] = (array2[i] - n * array3[i - 1]) / n3;
        }
        for (int j = n2 - 2; j >= 0; --j) {
            final int n4 = j;
            array3[n4] -= array4[j + 1] * array3[j + 1];
        }
    }
    
    void Z0066(final double n) {
        double n4;
        double n3;
        double n2 = n3 = (n4 = 0.0);
        for (int i = 0; i < this.Z0042; ++i) {
            final double n5 = n * i / this.Z0042;
            final double n7;
            final double n6 = n7 = 1.0;
            int n8 = (int)(6.0 * n5);
            final double n9 = n5 * 6.0 - n8;
            final double n10 = n6 * (1.0 - n7);
            final double n11 = n6 * (1.0 - n9 * n7);
            final double n12 = n6 * (1.0 - (1.0 - n9) * n7);
            if (n8 == 6) {
                n8 = 0;
            }
            switch (n8) {
                case 0: {
                    n3 = n6;
                    n2 = n12;
                    n4 = n10;
                    break;
                }
                case 1: {
                    n3 = n11;
                    n2 = n6;
                    n4 = n10;
                    break;
                }
                case 2: {
                    n3 = n10;
                    n2 = n6;
                    n4 = n12;
                    break;
                }
                case 3: {
                    n3 = n10;
                    n2 = n11;
                    n4 = n6;
                    break;
                }
                case 4: {
                    n3 = n12;
                    n2 = n10;
                    n4 = n6;
                    break;
                }
                case 5: {
                    n3 = n6;
                    n2 = n10;
                    n4 = n11;
                    break;
                }
            }
            this.Z0046[i] = new Color((int)(255.0 * n3), (int)(255.0 * n2), (int)(255.0 * n4));
        }
    }
    
    void Z0063(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0021.getSize();
        final Insets insets = this.Z0021.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        int n3 = Math.min(n, n2) / this.Z0028;
        if (n3 < 1) {
            n3 = 1;
        }
        final int n4 = this.Z0028 * n3;
        final int n5 = insets.left + (n - n4) / 2;
        final int n6 = insets.top + (n2 - n4) / 2;
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(new Rectangle2D.Double(n5, n6, n4, n4));
        int n7 = -1;
        int n8 = 0;
        for (int i = 0; i < this.Z0028; ++i) {
            for (int j = 0; j < this.Z0028; ++j, ++n8) {
                int n9 = (int)((1.2 * this.Z0083[n8] - 0.1) * this.Z0042);
                if (n9 < 0) {
                    n9 = 0;
                }
                else if (n9 > this.Z0042 - 1) {
                    n9 = this.Z0042 - 1;
                }
                if (n9 != n7) {
                    graphics2D.setPaint(this.Z0046[n9]);
                    n7 = n9;
                }
                graphics2D.fill(new Rectangle2D.Double(j * n3 + n5, i * n3 + n6, n3, n3));
            }
        }
        graphics2D.setFont(new Font("Sanserif", 1, 12));
        graphics2D.setPaint(Color.white);
        graphics2D.drawString("Cycle: " + String.valueOf(this.Z0074), 15 + n5, n4 - 15 + n6);
    }
    
    void Z0065() {
        this.Z0039[0].setText("Const-K: " + this.Z0059(this.Z0014, 4));
        this.Z0039[1].setText("Const-F: " + this.Z0059(this.Z0013, 4));
    }
    
    String Z0059(final double n, final int n2) {
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
            Pattern.this.Z0063(graphics);
        }
    }
}
