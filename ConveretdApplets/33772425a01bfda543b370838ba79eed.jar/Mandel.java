import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.text.NumberFormat;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.Font;
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
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mandel extends JApplet implements Runnable, ActionListener
{
    Thread Z0001;
    JButton[] Z0003;
    JLabel[] Z0029;
    DrawingArea Z0014;
    MouseAction Z0037;
    Cursor Z0012;
    Cursor Z0013;
    Color[] Z0045;
    Color Z0002;
    double[][] Z0020;
    double[] Z0075;
    double[] Z0078;
    double Z0033;
    double Z0034;
    double Z0035;
    int[] Z0031;
    int Z0019;
    int Z0007;
    int Z0041;
    int Z0042;
    int Z0040;
    int Z0047;
    int Z0048;
    int Z0076;
    int Z0079;
    int Z0018;
    int Z0038;
    int Z0072;
    int Z0011;
    int Z0021;
    int Z0009;
    int left;
    int top;
    boolean Z0049;
    boolean Z0081;
    boolean Z0046;
    
    public void init() {
        this.Z0065();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0060(), "East");
        contentPane.add(this.Z0014 = new DrawingArea(), "Center");
        this.Z0037 = new MouseAction();
        this.Z0014.addMouseListener(this.Z0037);
        this.Z0014.addMouseMotionListener(this.Z0037);
        this.Z0012 = new Cursor(1);
        this.Z0013 = new Cursor(3);
        this.Z0001 = null;
    }
    
    JPanel Z0060() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0003 = new JButton[4];
        this.Z0050(0, panel, "Start");
        this.Z0052(panel, 0, 10);
        this.Z0050(1, panel, "Zoom in");
        this.Z0052(panel, 0, 10);
        this.Z0050(2, panel, "Previous");
        this.Z0052(panel, 0, 10);
        this.Z0050(3, panel, "Full");
        this.Z0052(panel, 0, 60);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.setAlignmentX(0.5f);
        this.Z0029 = new JLabel[4];
        this.Z0051(0, panel2, "     ");
        this.Z0052(panel2, 0, 5);
        this.Z0051(1, panel2, "     ");
        this.Z0052(panel2, 0, 5);
        this.Z0051(2, panel2, "     ");
        this.Z0052(panel2, 0, 5);
        this.Z0051(3, panel2, "     ");
        panel.add(panel2);
        return panel;
    }
    
    void Z0050(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setAlignmentX(0.5f);
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0051(final int n, final JPanel panel, final String s) {
        (this.Z0029[n] = new JLabel(s, 2)).setAlignmentX(0.0f);
        this.Z0029[n].setFont(new Font("Sanserif", 1, 12));
        panel.add(this.Z0029[n]);
    }
    
    void Z0052(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0003[0]) {
            this.Z0069();
        }
        else if (source == this.Z0003[1]) {
            this.Z0081 = true;
            this.Z0014.setCursor(this.Z0012);
        }
        else if (source == this.Z0003[2]) {
            this.Z0062();
            this.Z0061();
            this.Z0069();
        }
        else if (source == this.Z0003[3]) {
            this.Z0055();
            this.Z0061();
            this.Z0069();
        }
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0014.getSize();
        final int n = size.width - size.height;
        this.Z0014.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0054();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0049) {
                this.Z0070();
                if (!this.Z0046) {
                    continue;
                }
                this.Z0014.repaint();
                this.Z0014.getToolkit().sync();
                if (!this.Z0068(300L)) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0046) {
                    this.Z0046 = false;
                    this.Z0014.repaint();
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
    
    void Z0065() {
        this.Z0038 = 200;
        this.Z0040 = 20000;
        this.Z0042 = 5;
        this.Z0048 = 10;
        this.Z0021 = 200;
        this.Z0009 = 0;
    }
    
    void Z0054() {
        final Dimension size = this.Z0014.getSize();
        final Insets insets = this.Z0014.getInsets();
        this.Z0007 = Math.min(size.width - insets.left - insets.right, size.height - insets.top - insets.bottom);
        this.left = insets.left;
        this.top = insets.top;
        this.Z0019 = this.Z0007;
        this.Z0059();
        this.Z0066();
        this.Z0058();
        this.Z0055();
        this.Z0067();
        this.Z0049 = false;
        this.Z0081 = false;
        this.Z0061();
    }
    
    void Z0059() {
        this.Z0045 = new Color[this.Z0038];
        this.Z0075 = new double[this.Z0019 * this.Z0019];
        this.Z0078 = new double[this.Z0019 * this.Z0019];
        this.Z0031 = new int[this.Z0019 * this.Z0019];
        this.Z0020 = new double[3][this.Z0021];
    }
    
    void Z0069() {
        this.Z0049 = !this.Z0049;
        this.Z0003[0].setText(this.Z0049 ? "Stop" : "Start");
        this.Z0003[1].setEnabled(!this.Z0049);
        this.Z0003[2].setEnabled(!this.Z0049);
        this.Z0003[3].setEnabled(!this.Z0049);
        if (this.Z0049) {
            this.Z0014.setCursor(this.Z0013);
        }
        else {
            this.Z0014.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    void Z0063(int n, int n2, final int n3) {
        n -= this.left;
        n2 -= this.top;
        switch (n3) {
            case 1: {
                if (this.Z0081 && n > 3 && n2 > 3 && n < this.Z0007 - 7 && n2 < this.Z0007 - 7) {
                    this.Z0076 = n - 3;
                    this.Z0079 = n2 - 3;
                    this.Z0018 = (int)(this.Z0019 / Math.sqrt(10.0));
                    this.Z0081 = false;
                    this.Z0014.setCursor(Cursor.getDefaultCursor());
                    this.Z0071();
                    this.Z0061();
                    this.Z0069();
                    break;
                }
                break;
            }
        }
    }
    
    void Z0067() {
        this.Z0029[0].setText("Midx: " + this.Z0057(this.Z0033, 5));
        this.Z0029[1].setText("Midy: " + this.Z0057(this.Z0034, 5));
        this.Z0029[2].setText("Range: " + this.Z0057(this.Z0035, 5));
        this.Z0029[3].setText("Cycle: " + String.valueOf(this.Z0041));
    }
    
    String Z0057(final double n, final int n2) {
        final NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(n2);
        numberInstance.setMaximumFractionDigits(n2);
        return numberInstance.format(n);
    }
    
    void Z0061() {
        for (int i = this.Z0019 * this.Z0019 - 1; i >= 0; --i) {
            this.Z0075[i] = 0.0;
            this.Z0078[i] = 0.0;
            this.Z0031[i] = 0;
        }
        this.Z0041 = 0;
        this.Z0047 = 0;
        this.Z0046 = true;
        this.Z0067();
    }
    
    void Z0055() {
        this.Z0033 = -0.75;
        this.Z0034 = 0.0;
        this.Z0035 = 2.6;
        this.Z0009 = 0;
        this.Z0053();
    }
    
    void Z0071() {
        final double n = 1.0 / this.Z0019;
        this.Z0033 += this.Z0035 * (this.Z0076 * n - 0.5);
        this.Z0034 -= this.Z0035 * (this.Z0079 * n - 0.5);
        this.Z0035 *= this.Z0018 * n;
        if (this.Z0035 < 0.1) {
            this.Z0009 = 1;
        }
        else if (this.Z0035 < 0.01) {
            this.Z0009 = 2;
        }
        else if (this.Z0035 < 0.001) {
            this.Z0009 = 3;
        }
        this.Z0053();
        this.Z0067();
    }
    
    void Z0070() {
        if (this.Z0049 && this.Z0041 < this.Z0040) {
            final int z0047 = this.Z0047;
            int z48 = z0047 + this.Z0048;
            if (z48 > this.Z0019) {
                z48 = this.Z0019;
            }
            int n = z0047 * this.Z0019;
            final double n2 = this.Z0035 / (this.Z0019 - 1);
            final double n3 = this.Z0035 / (this.Z0019 - 1);
            double n4 = this.Z0034 + this.Z0035 * 0.5 - z0047 * n3;
            for (int i = z0047; i < z48; ++i) {
                double n5 = this.Z0033 - this.Z0035 * 0.5;
                for (int j = 0; j < this.Z0019; ++j, ++n) {
                    if (this.Z0031[n] == 0) {
                        double n6 = this.Z0075[n];
                        double n7 = this.Z0078[n];
                        for (int k = this.Z0041; k < this.Z0041 + this.Z0042; ++k) {
                            final double n8 = n6 * n6;
                            final double n9 = n7 * n7;
                            if (n8 + n9 >= 4.0) {
                                this.Z0031[n] = k + 1;
                                break;
                            }
                            final double n10 = n8 - n9 + n5;
                            n7 = 2.0 * n6 * n7 + n4;
                            n6 = n10;
                        }
                        this.Z0075[n] = n6;
                        this.Z0078[n] = n7;
                    }
                    n5 += n2;
                }
                n4 -= n3;
            }
            this.Z0047 = z48;
            if (this.Z0047 == this.Z0019) {
                this.Z0047 = 0;
                this.Z0041 += this.Z0042;
                this.Z0046 = true;
            }
        }
    }
    
    void Z0058() {
        final boolean b = false;
        this.Z0011 = (b ? 1 : 0);
        this.Z0072 = (b ? 1 : 0);
    }
    
    void Z0053() {
        this.Z0020[0][this.Z0072] = this.Z0033;
        this.Z0020[1][this.Z0072] = this.Z0034;
        this.Z0020[2][this.Z0072] = this.Z0035;
        this.Z0011 = this.Z0072;
        if (++this.Z0072 > this.Z0021 - 1) {
            this.Z0072 = this.Z0021 - 1;
        }
    }
    
    void Z0062() {
        final int z0011 = this.Z0011 - 1;
        this.Z0011 = z0011;
        if (z0011 < 0) {
            this.Z0011 = 0;
        }
        this.Z0033 = this.Z0020[0][this.Z0011];
        this.Z0034 = this.Z0020[1][this.Z0011];
        this.Z0035 = this.Z0020[2][this.Z0011];
    }
    
    void Z0064(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        this.Z0046 = false;
        graphics.setColor(Color.black);
        graphics.fillRect(this.left, this.top, this.Z0007, this.Z0007);
        int n = -1;
        int n2 = 0;
        for (int i = 0; i < this.Z0019; ++i) {
            for (int j = 0; j < this.Z0019; ++j, ++n2) {
                if (this.Z0031[n2] > 0) {
                    int k;
                    for (k = this.Z0031[n2] >> this.Z0009; k >= this.Z0038; k -= this.Z0038) {}
                    if (k != n) {
                        graphics.setColor(this.Z0045[k]);
                        n = k;
                    }
                    graphics.drawLine(j + this.left, i + this.top, j + this.left, i + this.top);
                }
            }
        }
        this.Z0029[3].setText("Cycle: " + String.valueOf(this.Z0041));
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
            this.Z0045[i] = new Color((int)(255.0 * n2), (int)(255.0 * n), (int)(255.0 * n3));
        }
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Mandel.this.Z0063(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Mandel.this.Z0063(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Mandel.this.Z0063(mouseEvent.getX(), mouseEvent.getY(), -1);
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
            Mandel.this.Z0064(graphics);
        }
    }
}
