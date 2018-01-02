import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Insets;
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

public class Dla extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0005;
    JSlider[] Z0068;
    JLabel[] Z0034;
    DrawingArea Z0018;
    Color[] Z0043;
    Color Z0004;
    int[] Z0070;
    int Z0027;
    int Z0003;
    int Z0011;
    int Z0014;
    int Z0077;
    int Z0022;
    int Z0012;
    int Z0021;
    int Z0023;
    int Z0024;
    int Z0038;
    int Z0046;
    int Z0009;
    short[] Z0026;
    short[] Z0042;
    boolean Z0050;
    int[] Z0044;
    int Z0047;
    int Z0048;
    int Z0049;
    
    public Dla() {
        this.Z0047 = 250;
        this.Z0048 = 103;
        this.Z0049 = 0;
    }
    
    public void init() {
        this.Z0065();
        this.Z0004 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0061(), "East");
        contentPane.add(this.Z0018 = new DrawingArea(), "Center");
        this.Z0059();
        this.Z0002 = null;
    }
    
    JPanel Z0061() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0004), panel.getBorder()));
        this.Z0005 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0051(0, panel2, "Clear");
        this.Z0053(panel2, 10, 0);
        this.Z0051(1, panel2, "Start");
        panel.add(panel2);
        this.Z0053(panel, 0, 20);
        this.Z0068 = new JSlider[1];
        this.Z0054(0, panel, this.Z0077, 1, 20, "Visits");
        this.Z0053(panel, 0, 80);
        this.Z0034 = new JLabel[1];
        this.Z0052(0, panel, "       ");
        return panel;
    }
    
    void Z0051(final int n, final JPanel panel, final String s) {
        (this.Z0005[n] = new JButton(s)).setPreferredSize(this.Z0005[n].getPreferredSize());
        this.Z0005[n].addActionListener(this);
        panel.add(this.Z0005[n]);
    }
    
    void Z0054(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0053(panel, 0, 3);
        (this.Z0068[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0068[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0068[n].setPreferredSize(preferredSize);
        this.Z0068[n].setMajorTickSpacing(n4 - n3);
        this.Z0068[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0068[n].setPaintLabels(true);
        this.Z0068[n].setPaintTicks(true);
        this.Z0068[n].addChangeListener(this);
        panel.add(this.Z0068[n]);
        this.Z0053(panel, 0, 5);
    }
    
    void Z0052(final int n, final JPanel panel, final String s) {
        (this.Z0034[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0034[n]);
    }
    
    void Z0053(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0068[0]) {
                this.Z0077 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0005[0]) {
            this.Z0062();
            this.Z0018.repaint();
        }
        else if (source == this.Z0005[1]) {
            this.Z0050 = !this.Z0050;
            this.Z0005[1].setText(this.Z0050 ? "Stop" : "Start");
            this.Z0005[0].setEnabled(!this.Z0050);
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0018.getSize();
        final int n = size.width - size.height;
        this.Z0018.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0004), BorderFactory.createRaisedBevelBorder()));
        this.Z0055();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0050) {
                final long currentTimeMillis = System.currentTimeMillis();
                if (!this.Z0056()) {
                    this.Z0050 = false;
                    this.Z0005[0].setEnabled(!this.Z0050);
                    this.Z0005[1].setText(this.Z0050 ? "Stop" : "Start");
                }
                this.Z0018.repaint();
                this.Z0018.getToolkit().sync();
                if (!this.Z0067(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (!this.Z0067(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0067(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0065() {
        this.Z0038 = 50;
        this.Z0077 = 1;
    }
    
    void Z0055() {
        final Dimension size = this.Z0018.getSize();
        final Insets insets = this.Z0018.getInsets();
        this.Z0027 = Math.min(size.width - insets.left - insets.right, size.height - insets.top - insets.bottom) / 2;
        this.Z0009 = 2 * this.Z0027;
        this.Z0060();
        this.Z0066();
        this.Z0046 = 100;
        this.Z0022 = 30000;
        this.Z0011 = 2000;
        this.Z0021 = 30;
        this.Z0023 = 50;
        this.Z0050 = false;
        this.Z0062();
    }
    
    void Z0060() {
        this.Z0043 = new Color[this.Z0038];
        this.Z0026 = new short[this.Z0027 * this.Z0027];
        this.Z0042 = new short[this.Z0027 * this.Z0027];
        this.Z0070 = new int[4];
    }
    
    boolean Z0056() {
        int z0014 = -1;
        int n = 0;
        int i = 0;
        while (i < this.Z0022) {
            int j;
            for (j = this.Z0011; j > 0; --j) {
                z0014 = this.Z0014;
                this.Z0014 += this.Z0070[(this.Z0063() & 0x3000) >> 12];
                if (this.Z0026[this.Z0014] != 0) {
                    break;
                }
            }
            i += this.Z0011 - j;
            if (this.Z0026[this.Z0014] == 0) {
                final int n2 = this.Z0014 / this.Z0027;
                final int n3 = this.Z0014 - n2 * this.Z0027 - this.Z0027 / 2;
                final int n4 = n2 - this.Z0027 / 2;
                if (n3 * n3 + n4 * n4 <= this.Z0024) {
                    continue;
                }
                this.Z0057();
            }
            else if (this.Z0026[this.Z0014] > 0) {
                final short[] z15 = this.Z0026;
                final int z16 = this.Z0014;
                if (++z15[z16] < this.Z0077) {
                    continue;
                }
                this.Z0026[this.Z0014] = -1;
                int k;
                for (k = this.Z0003 / this.Z0046; k >= this.Z0038; k -= this.Z0038) {}
                this.Z0042[this.Z0014] = (short)k;
                for (int l = 0; l < 4; ++l) {
                    if (this.Z0026[this.Z0014 + this.Z0070[l]] == 0) {
                        this.Z0026[this.Z0014 + this.Z0070[l]] = 1;
                    }
                }
                final int n5 = this.Z0014 / this.Z0027;
                final int abs = Math.abs(this.Z0014 - n5 * this.Z0027 - this.Z0027 / 2);
                final int abs2 = Math.abs(n5 - this.Z0027 / 2);
                if (abs > this.Z0012) {
                    this.Z0012 = abs;
                }
                if (abs2 > this.Z0012) {
                    this.Z0012 = abs2;
                }
                if (this.Z0012 >= this.Z0027 / 2 - 4) {
                    return false;
                }
                this.Z0024 = (this.Z0012 + this.Z0023) * (this.Z0012 + this.Z0023);
                ++this.Z0003;
                this.Z0057();
                if (++n == 2) {
                    break;
                }
                continue;
            }
            else {
                this.Z0014 = z0014;
            }
        }
        return true;
    }
    
    void Z0057() {
        final double n = 2.925836158485692E-9 * this.Z0063();
        int n2 = this.Z0021 + this.Z0012;
        if (n2 > this.Z0027 / 2 - 4) {
            n2 = this.Z0027 / 2 - 4;
        }
        this.Z0014 = (this.Z0027 / 2 + (int)(n2 * Math.sin(n) + 0.5)) * this.Z0027 + (this.Z0027 / 2 + (int)(n2 * Math.cos(n) + 0.5));
    }
    
    void Z0062() {
        this.Z0070[0] = -1;
        this.Z0070[1] = 1;
        this.Z0070[2] = -this.Z0027;
        this.Z0070[3] = this.Z0027;
        for (int i = 0; i < this.Z0027 * this.Z0027; ++i) {
            this.Z0026[i] = -2;
        }
        for (int j = 0; j < this.Z0027 * this.Z0027; ++j) {
            this.Z0042[j] = -1;
        }
        int n = this.Z0027 + 1;
        for (int k = 1; k < this.Z0027 - 1; ++k) {
            for (int l = 1; l < this.Z0027 - 1; ++l, ++n) {
                this.Z0026[n] = 0;
            }
            n += 2;
        }
        final int n2 = this.Z0027 * (this.Z0027 / 2) + this.Z0027 / 2;
        this.Z0026[n2] = -1;
        this.Z0042[n2] = 0;
        for (int n3 = 0; n3 < 4; ++n3) {
            this.Z0026[n2 + this.Z0070[n3]] = 1;
        }
        this.Z0012 = 1;
        this.Z0003 = 1;
        this.Z0057();
    }
    
    void Z0066() {
        double n3;
        double n2;
        double n = n2 = (n3 = 0.0);
        for (int i = 0; i < this.Z0038; ++i) {
            final double n4 = i / this.Z0038;
            final double n6;
            final double n5 = n6 = 1.0;
            int n7 = (int)(6.0 * n4);
            final double n8 = n4 * 6.0 - n7;
            final double n9 = n5 * (1.0 - n6);
            final double n10 = n5 * (1.0 - n8 * n6);
            final double n11 = n5 * (1.0 - (1.0 - n8) * n6);
            if (n7 == 6) {
                n7 = 0;
            }
            switch (n7) {
                case 0: {
                    n2 = n5;
                    n = n11;
                    n3 = n9;
                    break;
                }
                case 1: {
                    n2 = n10;
                    n = n5;
                    n3 = n9;
                    break;
                }
                case 2: {
                    n2 = n9;
                    n = n5;
                    n3 = n11;
                    break;
                }
                case 3: {
                    n2 = n9;
                    n = n10;
                    n3 = n5;
                    break;
                }
                case 4: {
                    n2 = n11;
                    n = n9;
                    n3 = n5;
                    break;
                }
                case 5: {
                    n2 = n5;
                    n = n9;
                    n3 = n10;
                    break;
                }
            }
            this.Z0043[i] = new Color((int)(255.0 * n2), (int)(255.0 * n), (int)(255.0 * n3));
        }
    }
    
    void Z0059() {
        (this.Z0044 = new int[256])[0] = 17273747;
        for (int i = 1; i < 256; ++i) {
            this.Z0044[i] = 314159269 * this.Z0044[i - 1] + 453806245;
        }
        for (int j = 0; j < 256; ++j) {
            final int[] z0044 = this.Z0044;
            final int n = j;
            z0044[n] &= Integer.MAX_VALUE;
        }
    }
    
    int Z0063() {
        final int[] z0044 = this.Z0044;
        final int z45 = this.Z0047;
        final int n = this.Z0044[this.Z0048] ^ this.Z0044[this.Z0049];
        z0044[z45] = n;
        final int n2 = n;
        ++this.Z0047;
        this.Z0047 &= 0xFF;
        ++this.Z0048;
        this.Z0048 &= 0xFF;
        ++this.Z0049;
        this.Z0049 &= 0xFF;
        return n2;
    }
    
    void Z0064(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0018.getSize();
        final Insets insets = this.Z0018.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        final int n3 = insets.left + (n - this.Z0009) / 2;
        final int n4 = insets.top + (n2 - this.Z0009) / 2;
        graphics2D.setPaint(Color.black);
        graphics2D.fill(new Rectangle2D.Double(n3, n4, this.Z0009, this.Z0009));
        int n5 = this.Z0027 + 1;
        for (int i = 1; i < this.Z0027 - 1; ++i) {
            for (int j = 1; j < this.Z0027 - 1; ++j) {
                if (this.Z0042[n5] >= 0) {
                    graphics2D.setPaint(this.Z0043[this.Z0042[n5]]);
                    graphics2D.fill(new Rectangle2D.Double(2 * (j - 1) + n3, 2 * (i - 1) + n4, 2.0, 2.0));
                }
                ++n5;
            }
            n5 += 2;
        }
        this.Z0034[0].setText("Size: " + String.valueOf(this.Z0003));
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Dla.this.Z0064(graphics);
        }
    }
}
