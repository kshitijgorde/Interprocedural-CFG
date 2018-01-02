import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.text.NumberFormat;
import java.awt.Insets;
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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
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

public class Cscatt extends JApplet implements ActionListener, ChangeListener
{
    JButton[] Z0003;
    JSlider[] Z0059;
    JLabel[] Z0025;
    DrawingArea Z0016;
    MouseAction Z0029;
    Color[] Z0037;
    Color Z0002;
    double[] Z0007;
    double[] Z0008;
    double Z0023;
    double Z0024;
    double scale;
    double Z0014;
    double Z0035;
    int Z0043;
    int Z0031;
    int Z0032;
    int Z0028;
    int Z0027;
    int Z0030;
    int Z0015;
    int Z0067;
    int left;
    int top;
    
    public void init() {
        this.Z0056();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0053(), "East");
        (this.Z0016 = new DrawingArea()).setFont(new Font("Serif", 1, 12));
        contentPane.add(this.Z0016, "Center");
        this.Z0029 = new MouseAction();
        this.Z0016.addMouseListener(this.Z0029);
        this.Z0016.addMouseMotionListener(this.Z0029);
    }
    
    JPanel Z0053() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0059 = new JSlider[3];
        this.Z0047(0, panel, this.Z0031, 3, 8, "Disks");
        this.Z0047(1, panel, this.Z0015, 60, 98, "Radius");
        this.Z0046(panel, 0, 15);
        this.Z0025 = new JLabel[1];
        this.Z0045(0, panel, "Coarse Adjust");
        this.Z0046(panel, 0, 5);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0003 = new JButton[2];
        this.Z0044(0, panel2, "<<");
        this.Z0046(panel2, 5, 0);
        this.Z0044(1, panel2, ">>");
        panel.add(panel2);
        this.Z0046(panel, 0, 20);
        this.Z0047(2, panel, this.Z0067, -600, 600, "Fine Adjust");
        return panel;
    }
    
    void Z0044(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setPreferredSize(this.Z0003[n].getPreferredSize());
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0045(final int n, final JPanel panel, final String s) {
        (this.Z0025[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0025[n]);
    }
    
    void Z0047(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0046(panel, 0, 3);
        (this.Z0059[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0059[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0059[n].setPreferredSize(preferredSize);
        this.Z0059[n].setMajorTickSpacing(n4 - n3);
        this.Z0059[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0059[n].setPaintLabels(true);
        this.Z0059[n].setPaintTicks(true);
        this.Z0059[n].addChangeListener(this);
        panel.add(this.Z0059[n]);
        this.Z0046(panel, 0, 5);
    }
    
    void Z0046(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        final int value = slider.getValue();
        if (slider == this.Z0059[0]) {
            this.Z0031 = value;
        }
        else if (slider == this.Z0059[1]) {
            this.Z0015 = value;
        }
        else if (slider == this.Z0059[2]) {
            this.Z0067 = value;
            this.Z0023 = this.Z0024 + 0.001 * this.Z0067 / this.scale;
        }
        this.Z0016.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0003[0] || source == this.Z0003[1]) {
            if (source == this.Z0003[0]) {
                this.Z0024 -= 1.0 / this.scale;
            }
            else {
                this.Z0024 += 1.0 / this.scale;
            }
            this.Z0067 = 0;
            this.Z0059[2].setValue(this.Z0067);
            this.Z0023 = this.Z0024;
        }
        this.Z0016.repaint();
    }
    
    public void start() {
        final Dimension size = this.Z0016.getSize();
        final int n = size.width - size.height;
        this.Z0016.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0048();
    }
    
    public void stop() {
    }
    
    void Z0056() {
        this.Z0030 = 64;
        this.Z0027 = 9;
        this.Z0031 = 3;
        this.Z0015 = 85;
        this.Z0028 = 500;
        this.Z0024 = 0.0;
        this.Z0023 = this.Z0024;
        this.Z0067 = 0;
    }
    
    void Z0048() {
        this.Z0052();
        this.Z0057();
    }
    
    void Z0052() {
        this.Z0037 = new Color[this.Z0030];
        this.Z0007 = new double[this.Z0027];
        this.Z0008 = new double[this.Z0027];
    }
    
    void Z0054(int n, final int n2, final int n3) {
        n -= this.left;
        switch (n3) {
            case 0:
            case 1: {
                this.Z0024 = (n - this.Z0043 / 2) / this.scale;
                this.Z0023 = this.Z0024 + 0.001 * this.Z0067 / this.scale;
                this.Z0016.repaint();
                break;
            }
        }
    }
    
    void Z0057() {
        double n3;
        double n2;
        double n = n2 = (n3 = 0.0);
        for (int i = 0; i < this.Z0030; ++i) {
            final double n4 = i / this.Z0030;
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
            this.Z0037[i] = new Color((int)(255.0 * n2), (int)(255.0 * n), (int)(255.0 * n3));
        }
    }
    
    void Z0055(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0016.getSize();
        final Insets insets = this.Z0016.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        this.Z0043 = Math.min(n, n2);
        this.left = insets.left + (n - this.Z0043) / 2;
        this.top = insets.top + (n2 - this.Z0043) / 2;
        graphics.setColor(Color.black);
        graphics.fillRect(this.left, this.top, this.Z0043, this.Z0043);
        this.Z0058(graphics);
        graphics.setColor(Color.white);
        graphics.drawString("Pos: " + this.Z0051(this.Z0023, 6), 20 + this.left, this.Z0043 + this.top - 15);
    }
    
    void Z0058(final Graphics graphics) {
        final double n = 1.0 / Math.sin(3.141592653589793 / this.Z0031);
        this.scale = 0.4 * this.Z0043 / (n + 1.0);
        for (int i = 0; i < this.Z0031; ++i) {
            final double n2 = 6.283185307179586 * (i + 0.5) / this.Z0031;
            this.Z0007[i] = n * Math.sin(n2);
            this.Z0008[i] = -n * Math.cos(n2);
        }
        this.Z0035 = n + 2.0;
        this.Z0014 = 0.01 * this.Z0015;
        this.Z0049(graphics);
        double z0023 = this.Z0023;
        if (Math.abs(z0023) >= this.Z0035) {
            return;
        }
        double n3 = -Math.sqrt(this.Z0035 * this.Z0035 - z0023 * z0023);
        double n4 = 0.0;
        double n5 = 1.0;
        int n6 = 0;
        this.Z0032 = -1;
        while (this.Z0032 < this.Z0028 && n6 >= 0) {
            double n7 = 9.0;
            n6 = -1;
            for (int j = 0; j < this.Z0031; ++j) {
                final double n8 = z0023 - this.Z0007[j];
                final double n9 = n3 - this.Z0008[j];
                final double n10 = n4 * n8 + n5 * n9;
                final double n11 = n10 * n10 - n8 * n8 - n9 * n9 + this.Z0014 * this.Z0014;
                if (n11 >= 0.0) {
                    final double n12 = -n10 - Math.sqrt(n11);
                    if (n12 > 0.0 && n12 < n7) {
                        n7 = n12;
                        n6 = j;
                    }
                }
            }
            double n13;
            double n14;
            if (n6 >= 0) {
                n13 = z0023 + n7 * n4;
                n14 = n3 + n7 * n5;
                final double n15 = (n13 - this.Z0007[n6]) / this.Z0014;
                final double n16 = (n14 - this.Z0008[n6]) / this.Z0014;
                final double n17 = n4 * n15 + n5 * n16;
                final double n18 = n4 - 2.0 * n17 * n15;
                final double n19 = n5 - 2.0 * n17 * n16;
                final double sqrt = Math.sqrt(n18 * n18 + n19 * n19);
                n4 = n18 / sqrt;
                n5 = n19 / sqrt;
            }
            else {
                n13 = z0023;
                n14 = n3;
                final double n20 = n4 * z0023 + n5 * n3;
                final double n21 = n20 * n20 - z0023 * z0023 - n3 * n3 + this.Z0035 * this.Z0035;
                if (n21 >= 0.0) {
                    final double n22 = -n20 + Math.sqrt(n21);
                    if (n22 > 0.0) {
                        n13 += n22 * n4;
                        n14 += n22 * n5;
                    }
                }
            }
            final int n23 = this.Z0043 / 2 + (int)(this.scale * z0023);
            final int n24 = this.Z0043 / 2 - (int)(this.scale * n3);
            final int n25 = this.Z0043 / 2 + (int)(this.scale * n13);
            final int n26 = this.Z0043 / 2 - (int)(this.scale * n14);
            z0023 = n13;
            n3 = n14;
            graphics.setColor(this.Z0037[this.Z0032 + 11 & this.Z0030 - 1]);
            graphics.drawLine(n23 + this.left, n24 + this.top, n25 + this.left, n26 + this.top);
            ++this.Z0032;
        }
    }
    
    void Z0049(final Graphics graphics) {
        graphics.setColor(Color.white);
        final int n = (int)(this.Z0014 * this.scale);
        for (int i = 0; i < this.Z0031; ++i) {
            graphics.drawOval(this.Z0043 / 2 + (int)(this.scale * this.Z0007[i]) - n + this.left, this.Z0043 / 2 - (int)(this.scale * this.Z0008[i]) - n + this.top, 2 * n, 2 * n);
        }
    }
    
    String Z0051(final double n, final int n2) {
        final NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(n2);
        numberInstance.setMaximumFractionDigits(n2);
        return numberInstance.format(n);
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Cscatt.this.Z0054(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Cscatt.this.Z0054(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Cscatt.this.Z0054(mouseEvent.getX(), mouseEvent.getY(), -1);
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
            Cscatt.this.Z0055(graphics);
        }
    }
}
