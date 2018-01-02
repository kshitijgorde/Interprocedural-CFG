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
import java.awt.Font;
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

public class Travel extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0002;
    JButton[] Z0005;
    JSlider[] Z0061;
    JLabel[] Z0020;
    DrawingArea Z0012;
    Color Z0004;
    Random Z0036;
    String Z0060;
    double[] Z0040;
    double[] Z0041;
    double Z0033;
    double Z0008;
    double Z0059;
    int[] Z0034;
    int Z0028;
    int Z0063;
    int Z0064;
    int Z0026;
    int Z0027;
    int Z0010;
    boolean Z0039;
    boolean Z0037;
    
    public void init() {
        this.Z0056();
        this.Z0036 = new Random();
        this.Z0004 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0051(), "East");
        (this.Z0012 = new DrawingArea()).setFont(new Font("Serif", 1, 14));
        contentPane.add(this.Z0012, "Center");
        this.Z0002 = null;
    }
    
    JPanel Z0051() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0004), panel.getBorder()));
        this.Z0005 = new JButton[5];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 1));
        panel2.setAlignmentX(0.5f);
        this.Z0042(0, panel2, "Start");
        this.Z0044(panel2, 0, 5);
        this.Z0042(1, panel2, "Restart");
        this.Z0044(panel2, 0, 5);
        this.Z0042(2, panel2, "New");
        panel.add(panel2);
        this.Z0044(panel, 0, 20);
        this.Z0020 = new JLabel[1];
        this.Z0043(0, panel, "Temperature");
        this.Z0044(panel, 0, 10);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0042(3, panel3, "<<");
        this.Z0044(panel3, 5, 0);
        this.Z0042(4, panel3, ">>");
        panel.add(panel3);
        this.Z0044(panel, 0, 20);
        this.Z0061 = new JSlider[3];
        this.Z0045(0, panel, this.Z0028, 50, 900, "Cities");
        this.Z0045(1, panel, this.Z0064, 1, 10, "Update");
        this.Z0045(2, panel, this.Z0010, 1, 10, "Delay");
        return panel;
    }
    
    void Z0042(final int n, final JPanel panel, final String s) {
        (this.Z0005[n] = new JButton(s)).setPreferredSize(this.Z0005[n].getPreferredSize());
        this.Z0005[n].setAlignmentX(0.5f);
        this.Z0005[n].addActionListener(this);
        panel.add(this.Z0005[n]);
    }
    
    void Z0043(final int n, final JPanel panel, final String s) {
        (this.Z0020[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0020[n]);
    }
    
    void Z0045(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0044(panel, 0, 3);
        (this.Z0061[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0061[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0061[n].setPreferredSize(preferredSize);
        this.Z0061[n].setMajorTickSpacing(n4 - n3);
        this.Z0061[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0061[n].setPaintLabels(true);
        this.Z0061[n].setPaintTicks(true);
        this.Z0061[n].addChangeListener(this);
        panel.add(this.Z0061[n]);
        this.Z0044(panel, 0, 5);
    }
    
    void Z0044(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0061[0]) {
                this.Z0028 = value;
                this.Z0046();
            }
            else if (slider == this.Z0061[1]) {
                this.Z0064 = value;
            }
            else if (slider == this.Z0061[2]) {
                this.Z0010 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        this.Z0037 = false;
        if (source == this.Z0005[0]) {
            this.Z0039 = !this.Z0039;
            this.Z0005[0].setText(this.Z0039 ? "Stop" : "Start");
            this.Z0061[0].setEnabled(!this.Z0039);
        }
        else if (source == this.Z0005[1]) {
            this.Z0055();
            this.Z0037 = true;
        }
        else if (source == this.Z0005[2]) {
            this.Z0053();
            this.Z0037 = true;
        }
        else if (source == this.Z0005[3]) {
            this.Z0008 /= 1.1;
        }
        else if (source == this.Z0005[4]) {
            if (this.Z0008 < 0.001) {
                this.Z0008 = 0.001;
            }
            else {
                this.Z0008 *= 1.1;
            }
        }
    }
    
    public void start() {
        if (this.Z0002 == null) {
            (this.Z0002 = new Thread(this)).setPriority(1);
            this.Z0002.start();
        }
        final Dimension size = this.Z0012.getSize();
        final int n = size.width - size.height;
        this.Z0012.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0004), BorderFactory.createRaisedBevelBorder()));
        this.Z0046();
    }
    
    public void stop() {
        this.Z0002 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0002) {
            if (this.Z0039) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0052();
                if (this.Z0063 % this.Z0064 != 0) {
                    continue;
                }
                this.Z0057();
                this.Z0012.repaint();
                this.Z0012.getToolkit().sync();
                if (!this.Z0058(Math.max(5L, 100 * this.Z0010 - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0037) {
                    this.Z0037 = false;
                    this.Z0012.repaint();
                }
                if (!this.Z0058(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0058(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0056() {
        this.Z0028 = 200;
        this.Z0059 = 0.95;
        this.Z0064 = 5;
        this.Z0010 = 2;
    }
    
    void Z0046() {
        this.Z0050();
        this.Z0053();
    }
    
    void Z0050() {
        this.Z0040 = new double[this.Z0028];
        this.Z0041 = new double[this.Z0028];
        this.Z0034 = new int[this.Z0028];
    }
    
    void Z0053() {
        final double n = 0.4;
        for (int i = 0; i < this.Z0028; ++i) {
            final double n2 = (1.0 - n) / Math.sqrt(2.0);
            final double n3 = (0.25 + 0.5 * ((int)(4.0 * this.Z0036.nextDouble()) % 4)) * 3.141592653589793;
            this.Z0040[i] = 0.5 * (1.0 - n) + n2 * Math.cos(n3) + n * this.Z0036.nextDouble();
            this.Z0041[i] = 0.5 * (1.0 - n) + n2 * Math.sin(n3) + n * this.Z0036.nextDouble();
            this.Z0034[i] = i;
        }
        this.Z0049();
    }
    
    void Z0055() {
        for (int i = 0; i < this.Z0028; ++i) {
            this.Z0034[i] = i;
        }
        this.Z0049();
    }
    
    void Z0049() {
        this.Z0033 = 0.0;
        this.Z0008 = 0.5;
        for (int i = 0; i < this.Z0028; ++i) {
            final int n = i;
            final int n2 = (i + 1) % this.Z0028;
            final double n3 = this.Z0040[n] - this.Z0040[n2];
            final double n4 = this.Z0041[n] - this.Z0041[n2];
            this.Z0033 += Math.sqrt(n3 * n3 + n4 * n4);
        }
        this.Z0057();
        this.Z0063 = 0;
        this.Z0026 = 0;
        this.Z0027 = 0;
    }
    
    void Z0052() {
        ++this.Z0063;
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        boolean b = false;
        for (int i = 0; i < this.Z0028; ++i) {
            int n;
            do {
                array[0] = (int)(this.Z0028 * this.Z0036.nextDouble());
                array[1] = (int)(this.Z0028 * this.Z0036.nextDouble());
                n = (array[1] - array[0] + this.Z0028) % this.Z0028;
            } while (n < 3 || n > this.Z0028 - 3);
            array[2] = (array[0] + this.Z0028 - 1) % this.Z0028;
            array[3] = (array[1] + 1) % this.Z0028;
            for (int j = 0; j < 4; ++j) {
                array2[j] = this.Z0034[array[j]];
            }
            final double n2 = this.Z0040[array2[0]] - this.Z0040[array2[3]];
            final double n3 = this.Z0041[array2[0]] - this.Z0041[array2[3]];
            final double sqrt = Math.sqrt(n2 * n2 + n3 * n3);
            final double n4 = this.Z0040[array2[1]] - this.Z0040[array2[2]];
            final double n5 = this.Z0041[array2[1]] - this.Z0041[array2[2]];
            final double n6 = sqrt + Math.sqrt(n4 * n4 + n5 * n5);
            final double n7 = this.Z0040[array2[0]] - this.Z0040[array2[2]];
            final double n8 = this.Z0041[array2[0]] - this.Z0041[array2[2]];
            final double n9 = n6 - Math.sqrt(n7 * n7 + n8 * n8);
            final double n10 = this.Z0040[array2[1]] - this.Z0040[array2[3]];
            final double n11 = this.Z0041[array2[1]] - this.Z0041[array2[3]];
            final double n12 = n9 - Math.sqrt(n10 * n10 + n11 * n11);
            if (this.Z0036.nextDouble() * (1.0 + Math.exp(n12 / this.Z0008)) < 1.0) {
                ++this.Z0026;
                this.Z0033 += n12;
                for (int n13 = (1 + n) / 2, k = 0; k < n13; ++k) {
                    final int n14 = (array[0] + k) % this.Z0028;
                    final int n15 = (array[1] - k + this.Z0028) % this.Z0028;
                    final int n16 = this.Z0034[n14];
                    this.Z0034[n14] = this.Z0034[n15];
                    this.Z0034[n15] = n16;
                }
            }
            if (this.Z0026 % (10 * this.Z0028) == 0) {
                this.Z0008 *= this.Z0059;
                b = true;
            }
        }
        if (!b && this.Z0063 % 100 == 0) {
            this.Z0008 *= this.Z0059;
        }
    }
    
    void Z0054(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0012.getSize();
        final Insets insets = this.Z0012.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        final int min = Math.min(n, n2);
        final int n3 = insets.left + (n - min) / 2;
        final int n4 = insets.top + (n2 - min) / 2;
        graphics.setColor(Color.blue);
        graphics.fillRect(n3, n4, min, min);
        graphics.setColor(Color.red);
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < this.Z0028; ++i) {
            int n8;
            int n9;
            if (i == 0) {
                final int n7 = this.Z0034[this.Z0028 - 1];
                n8 = 4 + (int)(this.Z0040[n7] * (min - 10)) + n3;
                n9 = 4 + (int)(this.Z0041[n7] * (min - 10)) + n4;
            }
            else {
                n8 = n5;
                n9 = n6;
            }
            final int n10 = this.Z0034[i];
            n5 = 4 + (int)(this.Z0040[n10] * (min - 10)) + n3;
            n6 = 4 + (int)(this.Z0041[n10] * (min - 10)) + n4;
            graphics.drawLine(n8, n9, n5, n6);
        }
        graphics.setColor(Color.yellow);
        for (int j = 0; j < this.Z0028; ++j) {
            graphics.fillRect(4 + (int)(this.Z0040[j] * (min - 10)) + n3 - 1, 4 + (int)(this.Z0041[j] * (min - 10)) + n4 - 1, 3, 3);
        }
        graphics.setColor(Color.black);
        graphics.drawString(this.Z0060, 10 + n3, min - 10 + n4);
    }
    
    void Z0057() {
        final double n = 1000.0 * this.Z0008;
        this.Z0060 = "Temp: " + this.Z0048(n, (n < 10.0) ? 2 : 1) + "   Cycle: " + String.valueOf(this.Z0063) + "   Length: " + String.valueOf((int)(this.Z0033 * 100.0)) + "   Changes: " + String.valueOf(this.Z0026 - this.Z0027);
        this.Z0027 = this.Z0026;
    }
    
    String Z0048(final double n, final int n2) {
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
            Travel.this.Z0054(graphics);
        }
    }
}
