import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.geom.Line2D;
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
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Quant1d extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0001;
    JButton[] Z0003;
    JSlider[] Z0067;
    DrawingArea Z0013;
    Color Z0002;
    MouseAction Z0034;
    double[] Z0037;
    double[] Z0019;
    double[] Z0018;
    double[] Z0021;
    double[] Z0020;
    double[] Z0022;
    double Z0038;
    double Z0030;
    double Z0031;
    double Z0078;
    double Z0011;
    double Z0010;
    double Z0042;
    double Z0077;
    int[] Z0040;
    int[] Z0043;
    int[] Z0044;
    int Z0006;
    int Z0007;
    int Z0035;
    int Z0032;
    int Z0025;
    int Z0039;
    int Z0041;
    int Z0016;
    int Z0017;
    int Z0070;
    int Z0069;
    int left;
    int top;
    boolean Z0046;
    boolean Z0012;
    boolean Z0045;
    
    public void init() {
        this.Z0063();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0059(), "East");
        contentPane.add(this.Z0013 = new DrawingArea(), "Center");
        this.Z0034 = new MouseAction();
        this.Z0013.addMouseListener(this.Z0034);
        this.Z0013.addMouseMotionListener(this.Z0034);
        this.Z0001 = null;
    }
    
    JPanel Z0059() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0003 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0049(0, panel2, "Reset");
        this.Z0050(panel2, 10, 0);
        this.Z0049(1, panel2, "Start");
        panel.add(panel2);
        this.Z0050(panel, 0, 20);
        this.Z0067 = new JSlider[2];
        this.Z0051(0, panel, this.Z0017, 1, 100, "Energy");
        this.Z0051(1, panel, this.Z0070, 1, 20, "Update");
        return panel;
    }
    
    void Z0049(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setPreferredSize(this.Z0003[n].getPreferredSize());
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0051(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0050(panel, 0, 3);
        (this.Z0067[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0067[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0067[n].setPreferredSize(preferredSize);
        this.Z0067[n].setMajorTickSpacing(n4 - n3);
        this.Z0067[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0067[n].setPaintLabels(true);
        this.Z0067[n].setPaintTicks(true);
        this.Z0067[n].addChangeListener(this);
        panel.add(this.Z0067[n]);
        this.Z0050(panel, 0, 5);
    }
    
    void Z0050(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0067[0]) {
                this.Z0017 = value;
                this.Z0057();
            }
            else if (slider == this.Z0067[1]) {
                this.Z0070 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0003[0]) {
            this.Z0057();
        }
        else if (source == this.Z0003[1]) {
            this.Z0046 = !this.Z0046;
            this.Z0003[1].setText(this.Z0046 ? "Stop" : "Start");
            if (this.Z0046) {
                this.Z0054();
                this.Z0057();
            }
        }
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        this.Z0013.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0052();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0046) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0064();
                if (this.Z0069 % this.Z0070 != 0) {
                    continue;
                }
                this.Z0013.repaint();
                this.Z0013.getToolkit().sync();
                if (!this.Z0065(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0045) {
                    this.Z0045 = false;
                    this.Z0013.repaint();
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
        this.Z0017 = 50;
        this.Z0032 = 40;
        this.Z0070 = 5;
    }
    
    void Z0052() {
        final Dimension size = this.Z0013.getSize();
        final Insets insets = this.Z0013.getInsets();
        this.Z0006 = size.width - insets.left - insets.right;
        this.Z0007 = size.height - insets.top - insets.bottom;
        this.left = insets.left;
        this.top = insets.top;
        this.Z0035 = this.Z0006 / this.Z0032;
        if (this.Z0035 * this.Z0032 != this.Z0006) {
            ++this.Z0035;
        }
        this.Z0025 = this.Z0006 / 2 * 2;
        this.Z0058();
        this.Z0056();
        this.Z0055();
        this.Z0054();
        this.Z0057();
        this.Z0013.repaint();
        this.Z0046 = false;
        this.Z0012 = false;
    }
    
    void Z0058() {
        this.Z0021 = new double[this.Z0025];
        this.Z0020 = new double[this.Z0025];
        this.Z0022 = new double[this.Z0025];
        this.Z0019 = new double[this.Z0025];
        this.Z0018 = new double[this.Z0025];
        this.Z0037 = new double[this.Z0025];
        this.Z0040 = new int[this.Z0025];
        this.Z0043 = new int[2 * this.Z0035];
        this.Z0044 = new int[2 * this.Z0035];
    }
    
    void Z0064() {
        ++this.Z0069;
        final double cos = Math.cos(1.0 / this.Z0031);
        final double n = -Math.sin(1.0 / this.Z0031);
        final double cos2 = Math.cos(2.0 / this.Z0031);
        final double n2 = -Math.sin(2.0 / this.Z0031);
        this.Z0060(0, cos, n);
        this.Z0060(1, cos2, n2);
        this.Z0060(0, cos, n);
        for (int i = 0; i < this.Z0025; ++i) {
            final double n3 = this.Z0021[i];
            final double n4 = this.Z0020[i];
            this.Z0021[i] = this.Z0019[i] * n3 + this.Z0018[i] * n4;
            this.Z0020[i] = this.Z0019[i] * n4 - this.Z0018[i] * n3;
        }
        for (int j = 0; j < this.Z0025; ++j) {
            this.Z0022[j] = this.Z0021[j] * this.Z0021[j] + this.Z0020[j] * this.Z0020[j];
        }
    }
    
    void Z0060(final int n, final double n2, final double n3) {
        for (int i = n; i < this.Z0025; i += 2) {
            int n4 = i + 1;
            if (i == this.Z0025 - 1) {
                n4 -= this.Z0025;
            }
            final double n5 = this.Z0021[i];
            final double n6 = this.Z0020[i];
            final double n7 = this.Z0021[n4];
            final double n8 = this.Z0020[n4];
            this.Z0021[i] = n2 * n5 + n3 * n8;
            this.Z0020[i] = n2 * n6 - n3 * n7;
            this.Z0021[n4] = n2 * n7 + n3 * n6;
            this.Z0020[n4] = n2 * n8 - n3 * n5;
        }
    }
    
    void Z0056() {
        this.Z0039 = 2 * this.Z0007 / 3 / 5 * 5;
        this.Z0041 = this.Z0007 / 3 - 5;
        this.Z0077 = 0.7;
        this.Z0078 = 0.25;
        this.Z0011 = 1.0 / (this.Z0025 - 1);
        this.Z0031 = 10.0;
        this.Z0030 = 200.0;
        this.Z0042 = 0.5;
        this.Z0038 = 0.05;
        this.Z0010 = 2.0 * this.Z0011 * this.Z0011 / this.Z0031;
        this.Z0016 = this.Z0039 + 1;
    }
    
    void Z0057() {
        final double n = Math.sqrt(0.01 * this.Z0017) * this.Z0030;
        final double sqrt = Math.sqrt((this.Z0007 - this.Z0039) * this.Z0077);
        double n2 = 0.0;
        for (int i = 0; i < this.Z0025; ++i) {
            if (i == 0 || i == this.Z0025 - 1) {
                this.Z0021[i] = 0.0;
                this.Z0020[i] = 0.0;
                this.Z0022[i] = 0.0;
            }
            else {
                final double n3 = (n2 - this.Z0078) / this.Z0038;
                final double n4 = 0.5 * n3 * n3;
                final double n5 = (n4 < 10.0) ? (sqrt * Math.exp(-n4)) : 0.0;
                final double n6 = n * n2;
                this.Z0021[i] = n5 * Math.cos(n6);
                this.Z0020[i] = n5 * Math.sin(n6);
                this.Z0022[i] = this.Z0021[i] * this.Z0021[i] + this.Z0020[i] * this.Z0020[i];
            }
            n2 += this.Z0011;
        }
        this.Z0069 = 0;
        this.Z0045 = true;
    }
    
    void Z0054() {
        for (int i = 0; i < this.Z0025; ++i) {
            this.Z0037[i] = this.Z0010 * this.Z0042 * this.Z0030 * this.Z0030 * (this.Z0039 - this.Z0040[i / this.Z0032]) / this.Z0041;
        }
        for (int j = 0; j < this.Z0025; ++j) {
            this.Z0019[j] = Math.cos(this.Z0037[j]);
            this.Z0018[j] = Math.sin(this.Z0037[j]);
        }
    }
    
    void Z0055() {
        for (int i = 0; i < this.Z0035; ++i) {
            this.Z0040[i] = this.Z0039;
        }
        this.Z0040[2 * this.Z0035 / 5] = (this.Z0039 - this.Z0041 / 4 * 2) / 5 * 5;
        this.Z0040[2 * this.Z0035 / 5 + 2] = (this.Z0039 + this.Z0041 / 8 * 2) / 5 * 5;
    }
    
    void Z0053(final int n, final int n2) {
        final int n3 = n / this.Z0032;
        if (n3 > 0 && n3 < this.Z0035) {
            final int n4 = n2 - this.Z0040[n3];
            if (Math.abs(n4) < 30 && Math.abs(n4) > 5 && Math.abs(n2 - this.Z0039) < this.Z0041) {
                final int n5 = n4 / 5 * 5;
                final int[] z0040 = this.Z0040;
                final int n6 = n3;
                z0040[n6] += n5;
            }
        }
    }
    
    void Z0061(int n, int n2, final int n3) {
        n -= this.left;
        n2 -= this.top;
        switch (n3) {
            case 1: {
                if (!this.Z0046) {
                    this.Z0012 = true;
                    break;
                }
                break;
            }
            case 0: {
                if (this.Z0012) {
                    this.Z0053(n, n2);
                    this.Z0013.repaint();
                    break;
                }
                break;
            }
            case -1: {
                this.Z0012 = false;
                break;
            }
        }
    }
    
    void Z0062(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setPaint(Color.green);
        graphics2D.fill(new Rectangle2D.Double(this.left, this.top, this.Z0006, this.Z0007));
        graphics2D.setPaint(Color.red);
        for (int i = 0; i < this.Z0025; ++i) {
            int n = this.Z0039 - 2 - (int)this.Z0022[i] + this.top;
            if (n < 1) {
                n = 1;
            }
            graphics2D.draw(new Line2D.Double(i + this.left, n, i + this.left, n - 1));
        }
        graphics2D.setPaint(Color.blue);
        this.Z0043[0] = this.left;
        this.Z0044[0] = this.Z0040[0] + this.top;
        for (int j = 1; j < this.Z0035; ++j) {
            this.Z0043[2 * j - 1] = j * this.Z0032 + this.left;
            this.Z0044[2 * j - 1] = this.Z0040[j - 1] + this.top;
            this.Z0043[2 * j] = j * this.Z0032 + this.left;
            this.Z0044[2 * j] = this.Z0040[j] + this.top;
        }
        this.Z0043[2 * this.Z0035 - 1] = this.Z0006 - 1 + this.left;
        this.Z0044[2 * this.Z0035 - 1] = this.Z0040[this.Z0035 - 1] + this.top;
        for (int k = 0; k < 2 * this.Z0035 - 1; ++k) {
            graphics2D.draw(new Line2D.Double(this.Z0043[k], this.Z0044[k], this.Z0043[k + 1], this.Z0044[k + 1]));
        }
        graphics2D.setPaint(Color.black);
        this.Z0016 = (int)(this.Z0039 - 0.01 * this.Z0017 * this.Z0041 / this.Z0042) + this.top;
        graphics2D.draw(new Line2D.Double(this.Z0006 - 40 + this.left, this.Z0016, this.Z0006 - 1 + this.left, this.Z0016));
        graphics2D.draw(new Line2D.Double(this.Z0006 - 40 + this.left, this.Z0016 - 1, this.Z0006 - 1 + this.left, this.Z0016 - 1));
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Quant1d.this.Z0061(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Quant1d.this.Z0061(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Quant1d.this.Z0061(mouseEvent.getX(), mouseEvent.getY(), -1);
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
            Quant1d.this.Z0062(graphics);
        }
    }
}
