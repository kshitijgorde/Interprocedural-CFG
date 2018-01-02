import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.Paint;
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

public class Cella extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0001;
    JButton[] Z0003;
    JSlider[] Z0039;
    DrawingArea Z0012;
    Color Z0002;
    Random Z0023;
    boolean Z0025;
    boolean Z0024;
    int Z0006;
    int Z0018;
    int Z0004;
    int Z0016;
    int Z0042;
    int Z0011;
    short[] Z0008;
    short[] Z0009;
    short[] Z0005;
    
    public void init() {
        this.Z0036();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0033(), "East");
        (this.Z0012 = new DrawingArea()).setFont(new Font("Serif", 1, 14));
        contentPane.add(this.Z0012, "Center");
        this.Z0023 = new Random();
        this.Z0001 = null;
    }
    
    JPanel Z0033() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0003 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0026(0, panel2, "Reset");
        this.Z0027(panel2, 10, 0);
        this.Z0026(1, panel2, "Start");
        panel.add(panel2);
        this.Z0027(panel, 0, 20);
        this.Z0039 = new JSlider[2];
        this.Z0028(0, panel, this.Z0011, 1, 10, "Delay");
        this.Z0028(1, panel, this.Z0018, 32, 256, "Grid");
        return panel;
    }
    
    void Z0026(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setPreferredSize(this.Z0003[n].getPreferredSize());
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0028(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0027(panel, 0, 3);
        (this.Z0039[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        this.Z0039[n].setMajorTickSpacing(n4 - n3);
        this.Z0039[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0039[n].setPaintLabels(true);
        this.Z0039[n].setPaintTicks(true);
        this.Z0039[n].addChangeListener(this);
        final Dimension preferredSize = this.Z0039[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0039[n].setPreferredSize(preferredSize);
        panel.add(this.Z0039[n]);
        this.Z0027(panel, 0, 5);
    }
    
    void Z0027(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0039[0]) {
                this.Z0011 = value;
            }
            else if (slider == this.Z0039[1]) {
                this.Z0018 = (value + 4) / 8 * 8;
                this.Z0029();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0003[0]) {
            this.Z0034();
        }
        else if (source == this.Z0003[1]) {
            this.Z0025 = !this.Z0025;
            this.Z0003[1].setText(this.Z0025 ? "Stop" : "Start");
            this.Z0039[1].setEnabled(!this.Z0025);
        }
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0012.getSize();
        final int n = size.width - size.height;
        this.Z0012.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0029();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.Z0025) {
                this.Z0037();
                this.Z0012.repaint();
                this.Z0012.getToolkit().sync();
                if (!this.Z0038(Math.max(5L, 100 * this.Z0011 - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0024) {
                    this.Z0024 = false;
                    this.Z0012.repaint();
                }
                if (!this.Z0038(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0038(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0036() {
        this.Z0018 = 64;
        this.Z0004 = 224;
        this.Z0016 = 40;
        this.Z0011 = 1;
    }
    
    void Z0029() {
        this.Z0032();
        this.Z0031();
        this.Z0034();
        this.Z0025 = false;
    }
    
    void Z0032() {
        this.Z0008 = new short[(this.Z0018 + 2) * (this.Z0018 + 2)];
        this.Z0009 = new short[(this.Z0018 + 2) * (this.Z0018 + 2)];
    }
    
    void Z0031() {
        this.Z0005 = new short[18];
        for (int i = 0; i < 18; ++i) {
            this.Z0005[i] = (short)(((this.Z0004 & 1 << i) != 0x0) ? 1 : 0);
        }
    }
    
    void Z0037() {
        ++this.Z0042;
        int n = 0;
        for (int i = 0; i < this.Z0018 + 2; ++i) {
            for (int j = 0; j < this.Z0018 + 2; ++j) {
                this.Z0009[n] = this.Z0008[n];
                ++n;
            }
        }
        int n2 = this.Z0018 + 3;
        for (int k = 0; k < this.Z0018; ++k) {
            for (int l = 0; l < this.Z0018; ++l) {
                this.Z0008[n2] = this.Z0005[(this.Z0009[n2 + 1] + this.Z0009[n2 - 1] + this.Z0009[n2 + this.Z0018 + 2] + this.Z0009[n2 - this.Z0018 - 2] + this.Z0009[n2 + this.Z0018 + 3] + this.Z0009[n2 + this.Z0018 + 1] + this.Z0009[n2 - this.Z0018 - 3] + this.Z0009[n2 - this.Z0018 - 1] << 1) + this.Z0009[n2]];
                ++n2;
            }
            n2 += 2;
        }
    }
    
    void Z0034() {
        for (int i = 0; i < (this.Z0018 + 2) * (this.Z0018 + 2); ++i) {
            this.Z0008[i] = 0;
        }
        final double n = this.Z0016 / 100.0;
        int n2 = this.Z0018 + 3;
        for (int j = 0; j < this.Z0018; ++j) {
            for (int k = 0; k < this.Z0018; ++k) {
                if (this.Z0023.nextDouble() < n) {
                    this.Z0008[n2] = 1;
                }
                ++n2;
            }
            n2 += 2;
        }
        this.Z0042 = 0;
        this.Z0024 = true;
    }
    
    void Z0035(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Dimension size = this.Z0012.getSize();
        final Insets insets = this.Z0012.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        int n3 = Math.min(n, n2) / this.Z0018;
        if (n3 < 1) {
            n3 = 1;
        }
        final int n4 = this.Z0018 * n3;
        final int n5 = insets.left + (n - n4) / 2;
        final int n6 = insets.top + (n2 - n4) / 2;
        graphics2D.setPaint(Color.blue);
        graphics2D.fill(new Rectangle2D.Double(n5, n6, n4, n4));
        graphics2D.setPaint(Color.red);
        int n7 = this.Z0018 + 3;
        for (int i = 0; i < this.Z0018; ++i) {
            for (int j = 0; j < this.Z0018; ++j) {
                if (this.Z0008[n7] > 0) {
                    graphics2D.fill(new Rectangle2D.Double(j * n3 + n5, i * n3 + n6, n3, n3));
                }
                ++n7;
            }
            n7 += 2;
        }
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Cella.this.Z0035(graphics);
        }
    }
}
