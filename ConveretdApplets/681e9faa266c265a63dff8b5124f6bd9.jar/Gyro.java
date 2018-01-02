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

public class Gyro extends JApplet implements Runnable, ActionListener, ChangeListener, ItemListener
{
    Thread Z0002;
    JButton[] Z0005;
    JSlider[] Z0097;
    JCheckBox[] Z0011;
    DrawingArea Z0020;
    Color Z0004;
    Z0010[] Z0112;
    Z0010 w;
    Z0010 Z0109;
    Z0010 Z0029;
    Z0009 q;
    Z0009 Z0058;
    Z0009 Z0049;
    Z0009 Z0050;
    Z0009 Z0051;
    Z0009 Z0052;
    Z0009 Z0055;
    Z0009 Z0059;
    double Z0040;
    double Z0106;
    double Z0042;
    double Z0111;
    double Z0018;
    double Z0108;
    double Z0035;
    double Z0039;
    double Z0038;
    double Z0037;
    double Z0036;
    double Z0024;
    double Z0061;
    double Z0003;
    int Z0099;
    int Z0100;
    int Z0027;
    int Z0031;
    int Z0014;
    int Z0101;
    boolean Z0063;
    boolean Z0096;
    boolean Z0062;
    
    public void init() {
        this.Z0091();
        this.Z0004 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0084(), "East");
        contentPane.add(this.Z0020 = new DrawingArea(), "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0084() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0004), panel.getBorder()));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0005 = new JButton[2];
        this.Z0067(0, panel2, "Reset");
        this.Z0069(panel2, 10, 0);
        this.Z0067(1, panel2, "Start");
        panel.add(panel2);
        this.Z0069(panel, 0, 20);
        this.Z0097 = new JSlider[5];
        this.Z0070(0, panel, (int)this.Z0035, 5, 170, "Elevation");
        this.Z0070(1, panel, (int)this.Z0039, 5, 100, "Spin");
        this.Z0070(2, panel, (int)this.Z0038, -20, 20, "Precession");
        this.Z0070(3, panel, (int)this.Z0037, 2, 200, "Shape (X 100)");
        this.Z0070(4, panel, (int)this.Z0036, 0, 200, "Gravity");
        this.Z0069(panel, 0, 5);
        this.Z0011 = new JCheckBox[1];
        this.Z0068(0, panel, "Trajectory");
        return panel;
    }
    
    void Z0067(final int n, final JPanel panel, final String s) {
        (this.Z0005[n] = new JButton(s)).setPreferredSize(this.Z0005[n].getPreferredSize());
        this.Z0005[n].addActionListener(this);
        panel.add(this.Z0005[n]);
    }
    
    void Z0068(final int n, final JPanel panel, final String s) {
        (this.Z0011[n] = new JCheckBox(s)).addItemListener(this);
        this.Z0011[n].setAlignmentX(0.5f);
        panel.add(this.Z0011[n]);
    }
    
    void Z0070(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0069(panel, 0, 3);
        (this.Z0097[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0097[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0097[n].setPreferredSize(preferredSize);
        this.Z0097[n].setMajorTickSpacing(n4 - n3);
        this.Z0097[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0097[n].setPaintLabels(true);
        this.Z0097[n].setPaintTicks(true);
        this.Z0097[n].addChangeListener(this);
        panel.add(this.Z0097[n]);
        this.Z0069(panel, 0, 5);
    }
    
    void Z0069(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final double z0036 = slider.getValue();
            if (slider == this.Z0097[0]) {
                this.Z0035 = z0036;
            }
            else if (slider == this.Z0097[1]) {
                this.Z0039 = z0036;
            }
            else if (slider == this.Z0097[2]) {
                this.Z0038 = z0036;
            }
            else if (slider == this.Z0097[3]) {
                this.Z0037 = z0036;
            }
            else if (slider == this.Z0097[4]) {
                this.Z0036 = z0036;
            }
            this.Z0080();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0005[0]) {
            this.Z0080();
        }
        else if (source == this.Z0005[1]) {
            this.Z0063 = !this.Z0063;
            this.Z0005[1].setText(this.Z0063 ? "Stop" : "Start");
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getItemSelectable() == this.Z0011[0]) {
            this.Z0096 = (itemEvent.getStateChange() == 1);
            if (this.Z0096) {
                this.Z0031 = 0;
                this.Z0014 = 0;
            }
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0020.getSize();
        final int n = size.width - size.height;
        this.Z0020.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0004), BorderFactory.createRaisedBevelBorder()));
        this.Z0073();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0063) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0081();
                if (this.Z0099 % this.Z0100 != 0) {
                    continue;
                }
                this.Z0020.repaint();
                this.Z0020.getToolkit().sync();
                if (!this.Z0092(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0062) {
                    this.Z0062 = false;
                    this.Z0020.repaint();
                }
                if (!this.Z0092(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0092(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0091() {
        this.Z0035 = 70.0;
        this.Z0039 = 80.0;
        this.Z0038 = 0.0;
        this.Z0037 = 20.0;
        this.Z0036 = 100.0;
        this.Z0018 = 2.0E-4;
        this.Z0100 = 8;
        this.Z0027 = 500;
        this.Z0101 = 4 * this.Z0100;
        this.Z0096 = false;
    }
    
    void Z0073() {
        this.Z0083();
        this.Z0080();
        this.Z0063 = false;
    }
    
    void Z0083() {
        this.q = new Z0009();
        this.Z0058 = new Z0009();
        this.Z0049 = new Z0009();
        this.Z0050 = new Z0009();
        this.Z0051 = new Z0009();
        this.Z0052 = new Z0009();
        this.Z0055 = new Z0009();
        this.Z0059 = new Z0009();
        this.Z0112 = new Z0010[this.Z0027];
        for (int i = 0; i < this.Z0027; ++i) {
            this.Z0112[i] = new Z0010();
        }
        this.w = new Z0010();
        this.Z0109 = new Z0010();
        this.Z0029 = new Z0010();
    }
    
    void Z0080() {
        final Z0009 z0009 = new Z0009();
        this.Z0094(this.Z0029, 1.0, 1.0, 0.01 * this.Z0037);
        this.Z0024 = this.Z0036;
        this.Z0040 = 0.5235987755982988;
        this.Z0106 = this.Z0035 * 3.141592653589793 / 180.0;
        this.Z0042 = 0.0;
        final double z10 = this.Z0038;
        final double n = 0.0;
        final double z11 = this.Z0039;
        this.Z0089(this.q, Math.sin(0.5 * this.Z0106) * Math.cos(0.5 * (this.Z0040 - this.Z0042)), Math.sin(0.5 * this.Z0106) * Math.sin(0.5 * (this.Z0040 - this.Z0042)), Math.cos(0.5 * this.Z0106) * Math.sin(0.5 * (this.Z0040 + this.Z0042)), Math.cos(0.5 * this.Z0106) * Math.cos(0.5 * (this.Z0040 + this.Z0042)));
        this.Z0089(z0009, Math.sin(this.Z0106) * Math.sin(this.Z0042) * z10 + Math.cos(this.Z0042) * n, Math.sin(this.Z0106) * Math.cos(this.Z0042) * z10 - Math.sin(this.Z0042) * n, Math.cos(this.Z0106) * z10 + z11, 0.0);
        this.Z0087(this.Z0058, this.q, z0009);
        this.Z0088(this.Z0058, 0.5);
        this.Z0076();
        this.Z0086(this.Z0050, this.Z0049);
        this.Z0086(this.Z0051, this.Z0049);
        this.Z0086(this.Z0052, this.Z0049);
        this.Z0108 = 0.0;
        this.Z0099 = 0;
        this.Z0031 = 0;
        this.Z0014 = 0;
        this.Z0062 = true;
    }
    
    void Z0081() {
        ++this.Z0099;
        this.Z0108 += this.Z0018;
        this.Z0085();
        this.Z0076();
        this.Z0077();
        if (this.Z0099 % 100 == 0) {
            this.Z0071();
        }
    }
    
    void Z0076() {
        final double[] array = new double[9];
        final Z0010 z0010 = new Z0010();
        final Z0009 z11 = new Z0009();
        this.Z0095();
        this.Z0094(z0010, this.Z0024 * Math.sin(this.Z0106) * Math.cos(this.Z0040), this.Z0024 * Math.sin(this.Z0106) * Math.sin(this.Z0040), 0.0);
        this.Z0075(array, this.q, 0);
        this.Z0082(this.Z0109, array, z0010);
        this.Z0074();
        this.Z0089(z11, (this.Z0109.x + (this.Z0029.y - this.Z0029.z) * this.w.y * this.w.z) / this.Z0029.x, (this.Z0109.y + (this.Z0029.z - this.Z0029.x) * this.w.z * this.w.x) / this.Z0029.y, (this.Z0109.z + (this.Z0029.x - this.Z0029.y) * this.w.x * this.w.y) / this.Z0029.z, -2.0 * (this.Z0058.Z0115 * this.Z0058.Z0115 + this.Z0058.Z0116 * this.Z0058.Z0116 + this.Z0058.Z0117 * this.Z0058.Z0117 + this.Z0058.Z0118 * this.Z0058.Z0118));
        this.Z0087(this.Z0049, this.q, z11);
        this.Z0088(this.Z0049, 0.5);
    }
    
    void Z0074() {
        final Z0009 z0009 = new Z0009();
        this.Z0058.Z0118 = -this.Z0058.Z0118;
        this.Z0087(z0009, this.Z0058, this.q);
        this.Z0058.Z0118 = -this.Z0058.Z0118;
        this.Z0094(this.w, z0009.Z0115, z0009.Z0116, z0009.Z0117);
        this.Z0093(this.w, 2.0);
    }
    
    void Z0075(final double[] array, final Z0009 z0009, final int n) {
        final double[] array2 = new double[10];
        final double[] array3 = { z0009.Z0115, z0009.Z0116, z0009.Z0117, z0009.Z0118 };
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            for (int j = i; j < 4; ++j, ++n2) {
                array2[n2] = 2.0 * array3[j] * array3[i];
            }
        }
        array[0] = array2[0] + array2[9] - 1.0;
        array[4] = array2[4] + array2[9] - 1.0;
        array[8] = array2[7] + array2[9] - 1.0;
        double n3 = -1.0;
        if (n == 1) {
            n3 = 1.0;
        }
        array[1] = array2[1] + n3 * array2[8];
        array[3] = array2[1] - n3 * array2[8];
        array[2] = array2[2] - n3 * array2[6];
        array[6] = array2[2] + n3 * array2[6];
        array[5] = array2[5] + n3 * array2[3];
        array[7] = array2[5] - n3 * array2[3];
    }
    
    void Z0085() {
        final double[] array = { 323.0, -264.0, 159.0, -38.0 };
        final double[] array2 = { 502.0, -621.0, 396.0, -97.0 };
        final double n = 360.0;
        final double n2 = this.Z0018 * this.Z0018 / n;
        final double n3 = this.Z0018 / n;
        this.Z0086(this.Z0055, this.q);
        this.Z0086(this.Z0059, this.Z0058);
        final Z0009 q = this.q;
        q.Z0115 += this.Z0018 * this.Z0058.Z0115 + n2 * (array[0] * this.Z0049.Z0115 + array[1] * this.Z0050.Z0115 + array[2] * this.Z0051.Z0115 + array[3] * this.Z0052.Z0115);
        this.Z0058.Z0115 = (this.q.Z0115 - this.Z0055.Z0115) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0115 + array2[1] * this.Z0050.Z0115 + array2[2] * this.Z0051.Z0115 + array2[3] * this.Z0052.Z0115);
        final Z0009 q2 = this.q;
        q2.Z0116 += this.Z0018 * this.Z0058.Z0116 + n2 * (array[0] * this.Z0049.Z0116 + array[1] * this.Z0050.Z0116 + array[2] * this.Z0051.Z0116 + array[3] * this.Z0052.Z0116);
        this.Z0058.Z0116 = (this.q.Z0116 - this.Z0055.Z0116) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0116 + array2[1] * this.Z0050.Z0116 + array2[2] * this.Z0051.Z0116 + array2[3] * this.Z0052.Z0116);
        final Z0009 q3 = this.q;
        q3.Z0117 += this.Z0018 * this.Z0058.Z0117 + n2 * (array[0] * this.Z0049.Z0117 + array[1] * this.Z0050.Z0117 + array[2] * this.Z0051.Z0117 + array[3] * this.Z0052.Z0117);
        this.Z0058.Z0117 = (this.q.Z0117 - this.Z0055.Z0117) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0117 + array2[1] * this.Z0050.Z0117 + array2[2] * this.Z0051.Z0117 + array2[3] * this.Z0052.Z0117);
        final Z0009 q4 = this.q;
        q4.Z0118 += this.Z0018 * this.Z0058.Z0118 + n2 * (array[0] * this.Z0049.Z0118 + array[1] * this.Z0050.Z0118 + array[2] * this.Z0051.Z0118 + array[3] * this.Z0052.Z0118);
        this.Z0058.Z0118 = (this.q.Z0118 - this.Z0055.Z0118) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0118 + array2[1] * this.Z0050.Z0118 + array2[2] * this.Z0051.Z0118 + array2[3] * this.Z0052.Z0118);
        this.Z0086(this.Z0052, this.Z0051);
        this.Z0086(this.Z0051, this.Z0050);
        this.Z0086(this.Z0050, this.Z0049);
    }
    
    void Z0077() {
        final double[] array = { 38.0, 171.0, -36.0, 7.0 };
        final double[] array2 = { 97.0, 114.0, -39.0, 8.0 };
        final double n = 360.0;
        final double n2 = this.Z0018 * this.Z0018 / n;
        final double n3 = this.Z0018 / n;
        this.q.Z0115 = this.Z0055.Z0115 + this.Z0018 * this.Z0059.Z0115 + n2 * (array[0] * this.Z0049.Z0115 + array[1] * this.Z0050.Z0115 + array[2] * this.Z0051.Z0115 + array[3] * this.Z0052.Z0115);
        this.Z0058.Z0115 = (this.q.Z0115 - this.Z0055.Z0115) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0115 + array2[1] * this.Z0050.Z0115 + array2[2] * this.Z0051.Z0115 + array2[3] * this.Z0052.Z0115);
        this.q.Z0116 = this.Z0055.Z0116 + this.Z0018 * this.Z0059.Z0116 + n2 * (array[0] * this.Z0049.Z0116 + array[1] * this.Z0050.Z0116 + array[2] * this.Z0051.Z0116 + array[3] * this.Z0052.Z0116);
        this.Z0058.Z0116 = (this.q.Z0116 - this.Z0055.Z0116) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0116 + array2[1] * this.Z0050.Z0116 + array2[2] * this.Z0051.Z0116 + array2[3] * this.Z0052.Z0116);
        this.q.Z0117 = this.Z0055.Z0117 + this.Z0018 * this.Z0059.Z0117 + n2 * (array[0] * this.Z0049.Z0117 + array[1] * this.Z0050.Z0117 + array[2] * this.Z0051.Z0117 + array[3] * this.Z0052.Z0117);
        this.Z0058.Z0117 = (this.q.Z0117 - this.Z0055.Z0117) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0117 + array2[1] * this.Z0050.Z0117 + array2[2] * this.Z0051.Z0117 + array2[3] * this.Z0052.Z0117);
        this.q.Z0118 = this.Z0055.Z0118 + this.Z0018 * this.Z0059.Z0118 + n2 * (array[0] * this.Z0049.Z0118 + array[1] * this.Z0050.Z0118 + array[2] * this.Z0051.Z0118 + array[3] * this.Z0052.Z0118);
        this.Z0058.Z0118 = (this.q.Z0118 - this.Z0055.Z0118) / this.Z0018 + n3 * (array2[0] * this.Z0049.Z0118 + array2[1] * this.Z0050.Z0118 + array2[2] * this.Z0051.Z0118 + array2[3] * this.Z0052.Z0118);
    }
    
    void Z0071() {
        this.Z0088(this.q, 1.0 / Math.sqrt(this.q.Z0115 * this.q.Z0115 + this.q.Z0116 * this.q.Z0116 + this.q.Z0117 * this.q.Z0117 + this.q.Z0118 * this.q.Z0118));
    }
    
    void Z0078() {
        this.Z0074();
        this.Z0111 = 0.5 * (this.Z0029.x * this.w.x * this.w.x + this.Z0029.y * this.w.y * this.w.y + this.Z0029.z * this.w.z * this.w.z) + this.Z0024 * Math.cos(this.Z0106);
    }
    
    void Z0095() {
        final double n = this.q.Z0115 * this.q.Z0115 + this.q.Z0116 * this.q.Z0116;
        final double n2 = 2.0 * Math.sqrt(n * (1.0 - n));
        this.Z0106 = Math.atan2(n2, 1.0 - 2.0 * n);
        if (n2 != 0.0) {
            final double n3 = 2.0 * (this.q.Z0115 * this.q.Z0117 + this.q.Z0116 * this.q.Z0118) / n2;
            final double n4 = 2.0 * (this.q.Z0115 * this.q.Z0118 - this.q.Z0116 * this.q.Z0117) / n2;
            final double n5 = 2.0 * (this.q.Z0115 * this.q.Z0117 - this.q.Z0116 * this.q.Z0118) / n2;
            final double n6 = 2.0 * (this.q.Z0115 * this.q.Z0118 + this.q.Z0116 * this.q.Z0117) / n2;
            this.Z0040 = Math.atan2(n3, n4);
            this.Z0042 = Math.atan2(n5, n6);
        }
        else {
            this.Z0040 = 2.0 * Math.atan2(this.q.Z0117, this.q.Z0118);
            this.Z0042 = 0.0;
        }
    }
    
    void Z0072() {
        if (this.Z0099 % this.Z0101 == 0) {
            this.Z0095();
            this.Z0094(this.Z0112[this.Z0014], Math.sin(this.Z0106) * Math.sin(this.Z0040), -Math.sin(this.Z0106) * Math.cos(this.Z0040), Math.cos(this.Z0106));
            this.Z0093(this.Z0112[this.Z0014], 1.1 * this.Z0003);
            if (++this.Z0014 == this.Z0027) {
                this.Z0014 = 0;
            }
            if (this.Z0031 < this.Z0027) {
                ++this.Z0031;
            }
        }
    }
    
    void Z0090(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0020.getSize();
        final Insets insets = this.Z0020.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        final int n3 = size.width / 2;
        final int n4 = size.height / 2;
        this.Z0061 = Math.min(n, n2) / 5.0;
        this.Z0003 = 1.6 * this.Z0061;
        graphics.setColor(Color.black);
        graphics.fillRect(insets.left, insets.top, n, n2);
        final double[] array = new double[9];
        final int[] array2 = new int[3];
        final int[] array3 = new int[3];
        final int[] array4 = new int[3];
        final int[] array5 = new int[3];
        final Z0010 z0010 = new Z0010();
        this.Z0075(array, this.q, 1);
        final int n5 = 5;
        array4[0] = n3;
        array5[0] = n4;
        array4[1] = array4[0] - 3;
        array5[1] = array5[0] + 7;
        array4[2] = array4[0] + 3;
        array5[2] = array5[1];
        this.Z0094(z0010, 0.0, 0.0, this.Z0003);
        final int n6 = (int)(array[6] * z0010.z);
        final int n7 = (int)(array[8] * z0010.z);
        final boolean b = array[7] < 0.0;
        if (b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n3, n4, n3 + n6, n4 - n7);
            graphics.fillPolygon(array4, array5, 3);
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Color.blue);
        }
        for (int i = 0; i < n5; ++i) {
            for (int j = 0; j < 3; ++j) {
                switch (j) {
                    case 0: {
                        z0010.x = 0.0;
                        z0010.y = 0.0;
                        break;
                    }
                    case 1: {
                        final double n8 = 6.283185307179586 * (i - 0.25) / n5;
                        z0010.x = this.Z0061 * Math.cos(n8);
                        z0010.y = this.Z0061 * Math.sin(n8);
                        break;
                    }
                    case 2: {
                        final double n9 = 6.283185307179586 * (i + 0.25) / n5;
                        z0010.x = this.Z0061 * Math.cos(n9);
                        z0010.y = this.Z0061 * Math.sin(n9);
                        break;
                    }
                }
                array2[j] = n3 + (int)(array[0] * z0010.x + array[3] * z0010.y + array[6] * z0010.z);
                array3[j] = n4 - (int)(array[2] * z0010.x + array[5] * z0010.y + array[8] * z0010.z);
            }
            graphics.fillPolygon(array2, array3, 3);
        }
        if (!b) {
            graphics.setColor(Color.red);
            graphics.drawLine(n3, n4, n3 + n6, n4 - n7);
            graphics.fillPolygon(array4, array5, 3);
        }
        if (this.Z0096 && this.Z0063) {
            graphics.setColor(Color.green);
            for (int k = 0; k < this.Z0031; ++k) {
                final int n10 = n3 + (int)this.Z0112[k].x;
                final int n11 = n4 - (int)this.Z0112[k].z;
                graphics.drawLine(n10, n11, n10, n11 - 1);
            }
            this.Z0072();
        }
    }
    
    void Z0094(final Z0010 z0010, final double x, final double y, final double z11) {
        z0010.x = x;
        z0010.y = y;
        z0010.z = z11;
    }
    
    void Z0093(final Z0010 z0010, final double n) {
        z0010.x *= n;
        z0010.y *= n;
        z0010.z *= n;
    }
    
    void Z0082(final Z0010 z0010, final double[] array, final Z0010 z11) {
        z0010.x = array[0] * z11.x + array[3] * z11.y + array[6] * z11.z;
        z0010.y = array[1] * z11.x + array[4] * z11.y + array[7] * z11.z;
        z0010.z = array[2] * z11.x + array[5] * z11.y + array[8] * z11.z;
    }
    
    void Z0089(final Z0009 z0009, final double z10, final double z11, final double z12, final double z13) {
        z0009.Z0115 = z10;
        z0009.Z0116 = z11;
        z0009.Z0117 = z12;
        z0009.Z0118 = z13;
    }
    
    void Z0086(final Z0009 z0009, final Z0009 z10) {
        z0009.Z0115 = z10.Z0115;
        z0009.Z0116 = z10.Z0116;
        z0009.Z0117 = z10.Z0117;
        z0009.Z0118 = z10.Z0118;
    }
    
    void Z0088(final Z0009 z0009, final double n) {
        z0009.Z0115 *= n;
        z0009.Z0116 *= n;
        z0009.Z0117 *= n;
        z0009.Z0118 *= n;
    }
    
    void Z0087(final Z0009 z0009, final Z0009 z10, final Z0009 z11) {
        z0009.Z0115 = z10.Z0118 * z11.Z0115 - z10.Z0117 * z11.Z0116 + z10.Z0116 * z11.Z0117 + z10.Z0115 * z11.Z0118;
        z0009.Z0116 = z10.Z0117 * z11.Z0115 + z10.Z0118 * z11.Z0116 - z10.Z0115 * z11.Z0117 + z10.Z0116 * z11.Z0118;
        z0009.Z0117 = -z10.Z0116 * z11.Z0115 + z10.Z0115 * z11.Z0116 + z10.Z0118 * z11.Z0117 + z10.Z0117 * z11.Z0118;
        z0009.Z0118 = -z10.Z0115 * z11.Z0115 - z10.Z0116 * z11.Z0116 - z10.Z0117 * z11.Z0117 + z10.Z0118 * z11.Z0118;
    }
    
    class Z0010
    {
        double x;
        double y;
        double z;
    }
    
    class Z0009
    {
        double Z0115;
        double Z0116;
        double Z0117;
        double Z0118;
    }
    
    class DrawingArea extends JPanel
    {
        public void repaint(final Graphics graphics) {
            this.paintComponent(graphics);
        }
        
        public void paintComponent(final Graphics graphics) {
            super.paintComponent(graphics);
            Gyro.this.Z0090(graphics);
        }
    }
}
