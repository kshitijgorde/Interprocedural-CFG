import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Efield extends JApplet implements Runnable, ActionListener
{
    Thread Z0002;
    JButton[] Z0005;
    JRadioButton[] Z0054;
    JLabel[] Z0033;
    DrawingArea Z0015;
    MouseAction Z0038;
    Color Z0004;
    double[] Z0018;
    double[] Z0019;
    double[] Z0043;
    double[] Z0044;
    double[] Z0088;
    double[] Z0086;
    double[] Z0087;
    double[] Z0084;
    double[] Z0085;
    int Z0024;
    int Z0001;
    int Z0035;
    int Z0036;
    int Z0006;
    int Z0040;
    int Z0041;
    int Z0013;
    int Z0047;
    int Z0048;
    int Z0089;
    int Z0090;
    int Z0056;
    int left;
    int top;
    boolean[] Z0082;
    boolean Z0007;
    boolean Z0049;
    boolean Z0014;
    boolean Z0083;
    boolean Z0055;
    
    public void init() {
        this.Z0073();
        this.Z0004 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0070(), "East");
        (this.Z0015 = new DrawingArea()).setFont(new Font("Serif", 1, 14));
        contentPane.add(this.Z0015, "Center");
        this.Z0038 = new MouseAction();
        this.Z0015.addMouseListener(this.Z0038);
        this.Z0015.addMouseMotionListener(this.Z0038);
        this.Z0002 = null;
    }
    
    JPanel Z0070() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0004), panel.getBorder()));
        this.Z0054 = new JRadioButton[5];
        this.Z0033 = new JLabel[2];
        this.Z0060(0, panel, "Mode:");
        this.Z0062(panel, 0, 10);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.setAlignmentX(0.0f);
        panel2.setBorder(BorderFactory.createEtchedBorder());
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.Z0061(0, panel2, buttonGroup, "Edit", true);
        this.Z0061(1, panel2, buttonGroup, "Probe", false);
        this.Z0061(2, panel2, buttonGroup, "Shoot", false);
        panel.add(panel2);
        this.Z0062(panel, 0, 20);
        this.Z0060(0, panel, "Add Charge:");
        this.Z0062(panel, 0, 10);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        panel3.setAlignmentX(0.0f);
        panel3.setBorder(BorderFactory.createEtchedBorder());
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        this.Z0061(3, panel3, buttonGroup2, "+Q", true);
        this.Z0061(4, panel3, buttonGroup2, "-Q", false);
        panel.add(panel3);
        return panel;
    }
    
    void Z0061(final int n, final JPanel panel, final ButtonGroup buttonGroup, final String s, final boolean selected) {
        (this.Z0054[n] = new JRadioButton(s)).setAlignmentX(0.0f);
        this.Z0054[n].addActionListener(this);
        this.Z0054[n].setSelected(selected);
        buttonGroup.add(this.Z0054[n]);
        panel.add(this.Z0054[n]);
    }
    
    void Z0060(final int n, final JPanel panel, final String s) {
        (this.Z0033[n] = new JLabel(s, 0)).setAlignmentX(0.0f);
        panel.add(this.Z0033[n]);
    }
    
    void Z0062(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.Z0055 = false;
        final Object source = actionEvent.getSource();
        if (source == this.Z0054[0]) {
            if (this.Z0054[0].isSelected()) {
                this.Z0007 = true;
                this.Z0049 = false;
                this.Z0083 = false;
                this.Z0065();
                this.Z0055 = true;
            }
        }
        else if (source == this.Z0054[1]) {
            if (this.Z0054[1].isSelected()) {
                this.Z0007 = false;
                this.Z0049 = true;
                this.Z0083 = false;
                this.Z0065();
                this.Z0055 = true;
            }
        }
        else if (source == this.Z0054[2]) {
            if (this.Z0054[2].isSelected()) {
                this.Z0007 = false;
                this.Z0049 = false;
                this.Z0083 = true;
                this.Z0068();
                this.Z0055 = true;
            }
        }
        else if (source == this.Z0054[3]) {
            if (this.Z0054[3].isSelected()) {
                this.Z0006 = 1;
            }
        }
        else if (source == this.Z0054[4] && this.Z0054[4].isSelected()) {
            this.Z0006 = -1;
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0015.getSize();
        final int n = size.width - size.height;
        this.Z0015.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0004), BorderFactory.createRaisedBevelBorder()));
        this.Z0063();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0083 && this.Z0041 > 0) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0080();
                if (this.Z0089 % this.Z0090 != 0) {
                    continue;
                }
                this.Z0015.repaint();
                this.Z0015.getToolkit().sync();
                if (!this.Z0079(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0055) {
                    this.Z0055 = false;
                    this.Z0015.repaint();
                }
                if (!this.Z0079(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0079(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0073() {
        this.Z0024 = 28;
        this.Z0035 = 30;
        this.Z0036 = 40;
        this.Z0006 = 1;
        this.Z0090 = 5;
    }
    
    void Z0063() {
        this.Z0069();
        this.Z0040 = 2;
        this.Z0043[0] = 0.2;
        this.Z0044[0] = 0.2;
        this.Z0088[0] = 1.0;
        this.Z0043[1] = 0.8;
        this.Z0044[1] = 0.8;
        this.Z0088[1] = -1.0;
        this.Z0007 = true;
        this.Z0049 = false;
        this.Z0083 = false;
        this.Z0041 = 0;
        this.Z0013 = -1;
    }
    
    void Z0069() {
        this.Z0018 = new double[this.Z0024 * this.Z0024];
        this.Z0019 = new double[this.Z0024 * this.Z0024];
        this.Z0043 = new double[this.Z0035];
        this.Z0044 = new double[this.Z0035];
        this.Z0088 = new double[this.Z0035];
        this.Z0086 = new double[this.Z0036];
        this.Z0087 = new double[this.Z0036];
        this.Z0084 = new double[this.Z0036];
        this.Z0085 = new double[this.Z0036];
        this.Z0082 = new boolean[this.Z0036];
    }
    
    void Z0071(int n, int n2, final int n3) {
        this.Z0055 = false;
        final int n4 = 20;
        n -= this.left;
        n2 = this.Z0056 + this.top - n2;
        switch (n3) {
            case 1: {
                final double n5 = n / this.Z0056;
                final double n6 = n2 / this.Z0056;
                if (this.Z0007) {
                    this.Z0013 = -1;
                    for (int i = 0; i < this.Z0040; ++i) {
                        if (Math.abs(n - (int)(this.Z0043[i] * this.Z0056)) < n4 && Math.abs(n2 - (int)(this.Z0044[i] * this.Z0056)) < n4) {
                            this.Z0013 = i;
                            break;
                        }
                    }
                    if (this.Z0013 < 0 && this.Z0040 < this.Z0035 && n >= 5 && n <= this.Z0056 - 5 && n2 >= 5 && n2 <= this.Z0056 - 5) {
                        this.Z0043[this.Z0040] = n5;
                        this.Z0044[this.Z0040] = n6;
                        this.Z0088[this.Z0040] = this.Z0006;
                        this.Z0013 = this.Z0040;
                        ++this.Z0040;
                    }
                    this.Z0055 = true;
                    break;
                }
                if (this.Z0049) {
                    this.Z0014 = true;
                    this.Z0047 = n;
                    this.Z0048 = n2;
                    this.Z0055 = true;
                    break;
                }
                if (this.Z0083) {
                    this.Z0074(n, n2);
                    this.Z0055 = true;
                    break;
                }
                break;
            }
            case 0: {
                if (this.Z0007) {
                    if (this.Z0013 >= 0) {
                        final double n7 = n / this.Z0056;
                        final double n8 = n2 / this.Z0056;
                        if (n < 5 || n > this.Z0056 - 10 || n2 < 5 || n2 > this.Z0056 - 10) {
                            int n9 = 0;
                            for (int j = 0; j < this.Z0040; ++j) {
                                if (j != this.Z0013) {
                                    this.Z0043[n9] = this.Z0043[j];
                                    this.Z0044[n9] = this.Z0044[j];
                                    this.Z0088[n9] = this.Z0088[j];
                                    ++n9;
                                }
                            }
                            --this.Z0040;
                            this.Z0013 = -1;
                        }
                        else {
                            this.Z0043[this.Z0013] = n7;
                            this.Z0044[this.Z0013] = n8;
                        }
                        this.Z0055 = true;
                        break;
                    }
                    break;
                }
                else {
                    if (this.Z0049 && this.Z0014) {
                        this.Z0047 = n;
                        this.Z0048 = n2;
                        this.Z0055 = true;
                        break;
                    }
                    break;
                }
                break;
            }
            case -1: {
                this.Z0014 = false;
                this.Z0013 = -1;
                this.Z0055 = true;
                break;
            }
        }
    }
    
    void Z0080() {
        ++this.Z0089;
        final double n = 1.0E-4;
        final double n2 = 1.0E-4;
        for (int i = 0; i < this.Z0036; ++i) {
            if (this.Z0082[i]) {
                double n3 = 0.0;
                double n4 = 0.0;
                for (int j = 0; j < this.Z0040; ++j) {
                    final double n5 = this.Z0086[i] - this.Z0043[j];
                    final double n6 = this.Z0087[i] - this.Z0044[j];
                    double n7 = n5 * n5 + n6 * n6;
                    if (n7 < n2) {
                        n7 = n2;
                    }
                    final double n8 = this.Z0088[j] / (n7 * Math.sqrt(n7));
                    n3 += n8 * n5;
                    n4 += n8 * n6;
                }
                final double[] z0084 = this.Z0084;
                final int n9 = i;
                z0084[n9] += 5.0 * n3 * n;
                final double[] z85 = this.Z0085;
                final int n10 = i;
                z85[n10] += 5.0 * n4 * n;
                final double[] z86 = this.Z0086;
                final int n11 = i;
                z86[n11] += this.Z0084[i] * n;
                final double[] z87 = this.Z0087;
                final int n12 = i;
                z87[n12] += this.Z0085[i] * n;
                if (this.Z0086[i] < 0.0 || this.Z0086[i] > 1.0 || this.Z0087[i] < 0.0 || this.Z0087[i] > 1.0) {
                    this.Z0082[i] = false;
                    --this.Z0041;
                }
            }
        }
    }
    
    void Z0066() {
        double n = 0.0;
        final double n2 = 1.0E-4;
        int n3 = 0;
        for (int i = 0; i < this.Z0024; ++i) {
            for (int j = 0; j < this.Z0024; ++j) {
                final double n4 = (j + 0.5) / this.Z0024;
                final double n5 = (i + 0.5) / this.Z0024;
                this.Z0018[n3] = 0.0;
                this.Z0019[n3] = 0.0;
                for (int k = 0; k < this.Z0040; ++k) {
                    final double n6 = n4 - this.Z0043[k];
                    final double n7 = n5 - this.Z0044[k];
                    double n8 = n6 * n6 + n7 * n7;
                    if (n8 < n2) {
                        n8 = n2;
                    }
                    final double n9 = this.Z0088[k] / (n8 * Math.sqrt(n8));
                    final double[] z0018 = this.Z0018;
                    final int n10 = n3;
                    z0018[n10] += n9 * n6;
                    final double[] z19 = this.Z0019;
                    final int n11 = n3;
                    z19[n11] += n9 * n7;
                }
                final double n12 = this.Z0018[n3] * this.Z0018[n3] + this.Z0019[n3] * this.Z0019[n3];
                if (n12 > n) {
                    n = n12;
                }
                ++n3;
            }
        }
        final double n13 = 1.0 / Math.sqrt(n);
        int n14 = 0;
        for (int l = 0; l < this.Z0024; ++l) {
            for (int n15 = 0; n15 < this.Z0024; ++n15, ++n14) {
                final double[] z20 = this.Z0018;
                final int n16 = n14;
                z20[n16] *= n13;
                final double[] z21 = this.Z0019;
                final int n17 = n14;
                z21[n17] *= n13;
            }
        }
    }
    
    void Z0068() {
        if (this.Z0041 > 0) {
            this.Z0065();
        }
        else {
            for (int i = 0; i < this.Z0036; ++i) {
                this.Z0082[i] = false;
            }
        }
        this.Z0089 = 0;
    }
    
    void Z0065() {
        if (this.Z0041 > 0) {
            for (int i = 0; i < this.Z0036; ++i) {
                this.Z0082[i] = false;
            }
            this.Z0041 = 0;
        }
    }
    
    void Z0074(final int n, final int n2) {
        if (this.Z0041 < this.Z0036) {
            int n3;
            for (n3 = 0; n3 < this.Z0036 && this.Z0082[n3]; ++n3) {}
            ++this.Z0041;
            this.Z0086[n3] = n / this.Z0056;
            this.Z0087[n3] = n2 / this.Z0056;
            this.Z0084[n3] = 0.0;
            this.Z0085[n3] = 0.0;
            this.Z0082[n3] = true;
        }
    }
    
    void Z0072(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0015.getSize();
        final Insets insets = this.Z0015.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        this.Z0056 = Math.min(n, n2);
        this.left = insets.left + (n - this.Z0056) / 2;
        this.top = insets.top + (n2 - this.Z0056) / 2;
        graphics.setColor(Color.black);
        graphics.fillRect(this.left, this.top, this.Z0056, this.Z0056);
        graphics.setColor(Color.green);
        this.Z0066();
        this.Z0075(graphics);
        graphics.setColor(Color.red);
        this.Z0076(graphics);
        if (this.Z0049 && this.Z0014) {
            graphics.setColor(Color.yellow);
            this.Z0078(graphics);
        }
        if (this.Z0083 && this.Z0041 > 0) {
            graphics.setColor(Color.yellow);
            this.Z0077(graphics);
        }
    }
    
    void Z0077(final Graphics graphics) {
        for (int i = 0; i < this.Z0036; ++i) {
            if (this.Z0082[i]) {
                graphics.fillOval((int)(this.Z0086[i] * this.Z0056) + this.left - 3, this.Z0056 + this.top - (int)(this.Z0087[i] * this.Z0056) - 3, 6, 6);
            }
        }
    }
    
    void Z0076(final Graphics graphics) {
        for (int i = 0; i < this.Z0040; ++i) {
            final int n = (int)(this.Z0043[i] * this.Z0056) + this.left - 3;
            final int n2 = this.Z0056 + this.top - (int)(this.Z0044[i] * this.Z0056) - 3;
            graphics.fillOval(n, n2, 6, 6);
            graphics.drawString((this.Z0088[i] > 0.0) ? "+" : "-", n + 5, n2 - 5);
        }
    }
    
    void Z0075(final Graphics graphics) {
        final double n = 0.4 * this.Z0056 / this.Z0024;
        int n2 = 0;
        for (int i = 0; i < this.Z0024; ++i) {
            final double n3 = this.Z0056 + this.top - this.Z0056 * (i + 0.5) / this.Z0024;
            for (int j = 0; j < this.Z0024; ++j) {
                this.Z0064(graphics, this.Z0056 * (j + 0.5) / this.Z0024 + this.left, n3, this.Z0018[n2], this.Z0019[n2], n);
                ++n2;
            }
        }
    }
    
    void Z0078(final Graphics graphics) {
        final double n = this.Z0047 / this.Z0056;
        final double n2 = this.Z0048 / this.Z0056;
        final double n3 = 1.0E-4;
        double n4 = 0.0;
        double n5 = 0.0;
        for (int i = 0; i < this.Z0040; ++i) {
            final double n6 = n - this.Z0043[i];
            final double n7 = n2 - this.Z0044[i];
            double n8 = n6 * n6 + n7 * n7;
            if (n8 < n3) {
                n8 = n3;
            }
            final double n9 = this.Z0088[i] / (n8 * Math.sqrt(n8));
            n4 += n9 * n6;
            n5 += n9 * n7;
        }
        this.Z0064(graphics, this.Z0047 + this.left, this.Z0056 + this.top - this.Z0048, n4, n5, 1.5 * this.Z0056 / this.Z0024);
    }
    
    void Z0064(final Graphics graphics, final double n, final double n2, double n3, double n4, final double n5) {
        final double n6 = -0.4 * n5;
        final double n7 = 0.3 * n5;
        final double sqrt = Math.sqrt(n3 * n3 + n4 * n4);
        if (sqrt > 0.0) {
            n3 /= sqrt;
            n4 /= sqrt;
        }
        final int n8 = (int)(n + n3 * n5);
        final int n9 = (int)(n2 - n4 * n5);
        final int n10 = (int)(n - n3 * n5);
        final int n11 = (int)(n2 + n4 * n5);
        final int n12 = n8 + (int)(n3 * n6 + n4 * n7);
        final int n13 = n9 - (int)(n4 * n6 - n3 * n7);
        final int n14 = n8 + (int)(n3 * n6 - n4 * n7);
        final int n15 = n9 - (int)(n4 * n6 + n3 * n7);
        graphics.drawLine(n8, n9, n10, n11);
        graphics.drawLine(n8, n9, n12, n13);
        graphics.drawLine(n8, n9, n14, n15);
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Efield.this.Z0071(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Efield.this.Z0071(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Efield.this.Z0071(mouseEvent.getX(), mouseEvent.getY(), -1);
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
            Efield.this.Z0072(graphics);
        }
    }
}
