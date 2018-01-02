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

public class Ising extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0001;
    JButton[] Z0003;
    JSlider[] Z0054;
    JLabel[] Z0020;
    DrawingArea Z0007;
    Color Z0002;
    boolean Z0033;
    boolean Z0029;
    double Z0063;
    double Z0015;
    double Z0067;
    double Z0066;
    int[] Z0027;
    int Z0004;
    int Z0017;
    int Z0059;
    int Z0060;
    int Z0056;
    int Z0026;
    short[] Z0057;
    int[] Z0028;
    int Z0030;
    int Z0031;
    int Z0032;
    
    public Ising() {
        this.Z0030 = 250;
        this.Z0031 = 103;
        this.Z0032 = 0;
    }
    
    public void init() {
        this.Z0050();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0045(), "East");
        contentPane.add(this.Z0007 = new DrawingArea(), "Center");
        this.Z0043();
        this.Z0001 = null;
    }
    
    JPanel Z0045() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0003 = new JButton[1];
        this.Z0034(0, panel, "Start");
        this.Z0036(panel, 0, 20);
        this.Z0054 = new JSlider[4];
        this.Z0037(0, panel, (int)(100.0 * this.Z0063), 10, 600, "Temperature (X 100)");
        this.Z0037(1, panel, (int)(10.0 * this.Z0015), -20, 20, "Field (X 10)");
        this.Z0037(2, panel, this.Z0060, 1, 50, "Update");
        this.Z0037(3, panel, this.Z0017, 32, 256, "Grid");
        this.Z0036(panel, 0, 40);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.setAlignmentX(0.5f);
        this.Z0020 = new JLabel[2];
        this.Z0035(0, panel2, "       ");
        this.Z0036(panel2, 0, 5);
        this.Z0035(1, panel2, "       ");
        panel.add(panel2);
        return panel;
    }
    
    void Z0034(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setAlignmentX(0.5f);
        this.Z0003[n].setPreferredSize(this.Z0003[n].getPreferredSize());
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0037(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0036(panel, 0, 3);
        (this.Z0054[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0054[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0054[n].setPreferredSize(preferredSize);
        this.Z0054[n].setMajorTickSpacing(n4 - n3);
        this.Z0054[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0054[n].setPaintLabels(true);
        this.Z0054[n].setPaintTicks(true);
        this.Z0054[n].addChangeListener(this);
        panel.add(this.Z0054[n]);
        this.Z0036(panel, 0, 5);
    }
    
    void Z0035(final int n, final JPanel panel, final String s) {
        (this.Z0020[n] = new JLabel(s, 2)).setAlignmentX(0.0f);
        this.Z0020[n].setFont(new Font("Sanserif", 1, 12));
        panel.add(this.Z0020[n]);
    }
    
    void Z0036(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0054[0]) {
                this.Z0063 = 0.01 * value;
                this.Z0039();
                this.Z0051();
            }
            else if (slider == this.Z0054[1]) {
                this.Z0015 = 0.1 * value;
                this.Z0039();
                this.Z0051();
            }
            else if (slider == this.Z0054[2]) {
                this.Z0060 = value;
            }
            else if (slider == this.Z0054[3]) {
                this.Z0017 = (value + 4) / 8 * 8;
                this.Z0038();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.Z0003[0]) {
            this.Z0033 = !this.Z0033;
            this.Z0003[0].setText(this.Z0033 ? "Stop" : "Start");
            this.Z0054[3].setEnabled(!this.Z0033);
        }
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0007.getSize();
        final int n = size.width - size.height;
        this.Z0007.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0038();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0033) {
                this.Z0052();
                if (this.Z0059 % this.Z0060 != 0) {
                    continue;
                }
                this.Z0040();
                this.Z0007.repaint();
                this.Z0007.getToolkit().sync();
                if (!this.Z0053(500L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0029) {
                    this.Z0029 = false;
                    this.Z0007.repaint();
                }
                if (!this.Z0053(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0053(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0050() {
        this.Z0017 = 64;
        this.Z0063 = 2.5;
        this.Z0015 = 0.0;
        this.Z0060 = 1;
    }
    
    void Z0038() {
        this.Z0044();
        this.Z0039();
        this.Z0047();
        this.Z0051();
    }
    
    void Z0044() {
        this.Z0057 = new short[this.Z0017 * this.Z0017];
        this.Z0027 = new int[10];
    }
    
    void Z0039() {
        int n = 1;
        for (int i = 0; i < 10; ++i) {
            n = 1 - n;
            final double n2 = (i - n - 4 + this.Z0015) * (4 * n - 2);
            double n3;
            if (this.Z0063 > 0.0) {
                n3 = n2 / this.Z0063;
            }
            else if (n2 > 0.0) {
                n3 = 30.0;
            }
            else {
                n3 = -30.0;
            }
            if (n3 > 25.0) {
                this.Z0027[i] = 0;
            }
            else if (n3 < -25.0) {
                this.Z0027[i] = Integer.MAX_VALUE;
            }
            else {
                this.Z0027[i] = (int)(2.147483647E9 / (Math.exp(n3) + 1.0));
            }
        }
        this.Z0059 = 0;
    }
    
    void Z0052() {
        ++this.Z0059;
        int z0017 = this.Z0017;
        int n = this.Z0017 * (this.Z0017 - 1);
        int n2 = 0;
        for (int i = 0; i < this.Z0017; ++i) {
            if (i == this.Z0017 - 1) {
                z0017 = this.Z0017 * (1 - this.Z0017);
            }
            int n3 = 1;
            int n4 = this.Z0017 - 1;
            for (int j = 0; j < this.Z0017; ++j, ++n2) {
                if (j == this.Z0017 - 1) {
                    n3 = 1 - this.Z0017;
                }
                final short n5 = (short)(this.Z0057[n2 + z0017] + this.Z0057[n2 + n] + this.Z0057[n2 + n3] + this.Z0057[n2 + n4]);
                if (this.Z0048() < this.Z0027[this.Z0057[n2] + (n5 << 1)]) {
                    this.Z0057[n2] = (short)(1 - this.Z0057[n2]);
                    final int n6 = (this.Z0057[n2] << 2) - 2;
                    this.Z0026 += n6 * ((n5 << 1) - 4);
                    this.Z0056 += n6;
                }
                n4 = -1;
            }
            n = -this.Z0017;
        }
    }
    
    void Z0047() {
        for (int i = 0; i < this.Z0017 * this.Z0017; ++i) {
            this.Z0057[i] = 1;
        }
        this.Z0056 = this.Z0017 * this.Z0017;
        this.Z0026 = 2 * this.Z0056;
        this.Z0040();
        this.Z0059 = 0;
        this.Z0029 = true;
    }
    
    void Z0040() {
        this.Z0067 = 0.5 * this.Z0026 / (this.Z0017 * this.Z0017);
        this.Z0066 = this.Z0056 / (this.Z0017 * this.Z0017);
    }
    
    void Z0043() {
        (this.Z0028 = new int[256])[0] = 17273747;
        for (int i = 1; i < 256; ++i) {
            this.Z0028[i] = 314159269 * this.Z0028[i - 1] + 453806245;
        }
        for (int j = 0; j < 256; ++j) {
            final int[] z0028 = this.Z0028;
            final int n = j;
            z0028[n] &= Integer.MAX_VALUE;
        }
    }
    
    int Z0048() {
        final int[] z0028 = this.Z0028;
        final int z29 = this.Z0030;
        final int n = this.Z0028[this.Z0031] ^ this.Z0028[this.Z0032];
        z0028[z29] = n;
        final int n2 = n;
        ++this.Z0030;
        this.Z0030 &= 0xFF;
        ++this.Z0031;
        this.Z0031 &= 0xFF;
        ++this.Z0032;
        this.Z0032 &= 0xFF;
        return n2;
    }
    
    void Z0049(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0007.getSize();
        final Insets insets = this.Z0007.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        int n3 = Math.min(n, n2) / this.Z0017;
        if (n3 < 1) {
            n3 = 1;
        }
        final int n4 = this.Z0017 * n3;
        final int n5 = insets.left + (n - n4) / 2;
        final int n6 = insets.top + (n2 - n4) / 2;
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(new Rectangle2D.Double(n5, n6, n4, n4));
        graphics2D.setPaint(Color.red);
        int n7 = 0;
        for (int i = 0; i < this.Z0017; ++i) {
            for (int j = 0; j < this.Z0017; ++j) {
                if (this.Z0057[n7] > 0) {
                    graphics2D.fill(new Rectangle2D.Double(j * n3 + n5, i * n3 + n6, n3, n3));
                }
                ++n7;
            }
        }
        graphics2D.setFont(new Font("Sanserif", 1, 12));
        graphics2D.setPaint(Color.white);
        graphics2D.drawString("Step: " + String.valueOf(this.Z0059) + "   Sro: " + this.Z0042(this.Z0067, 2) + "   Lro: " + this.Z0042(this.Z0066, 2), 15 + n5, n4 - 15 + n6);
    }
    
    void Z0051() {
        this.Z0020[0].setText("T: " + this.Z0042(this.Z0063, 2));
        this.Z0020[1].setText("H: " + this.Z0042(this.Z0015, 2));
    }
    
    String Z0042(final double n, final int n2) {
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
            Ising.this.Z0049(graphics);
        }
    }
}
