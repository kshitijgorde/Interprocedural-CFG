import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
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
import java.awt.Component;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Spring extends JApplet implements Runnable, ActionListener, ChangeListener
{
    Thread Z0001;
    JButton[] Z0003;
    JSlider[] Z0082;
    JLabel[] Z0020;
    JRadioButton[] Z0045;
    DrawingArea Z0011;
    MouseAction Z0028;
    Color Z0002;
    double[] Z0054;
    double[] Z0055;
    double[] Z0052;
    double[] Z0053;
    double[] Z0046;
    double[] Z0047;
    double[] Z0043;
    double[] Z0044;
    double[] Z0022;
    double Z0009;
    double Z0088;
    double Z0089;
    int[] Z0087;
    int[] Z0038;
    int[] Z0039;
    int Z0030;
    int Z0024;
    int Z0032;
    int Z0026;
    int Z0010;
    int Z0025;
    int Z0086;
    int Z0029;
    int Z0066;
    int Z0084;
    int Z0090;
    int Z0091;
    int Z0049;
    int left;
    int top;
    boolean Z0051;
    boolean Z0040;
    boolean Z0048;
    
    public void init() {
        this.Z0077();
        this.Z0002 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0074(), "East");
        contentPane.add(this.Z0011 = new DrawingArea(), "Center");
        this.Z0028 = new MouseAction();
        this.Z0011.addMouseListener(this.Z0028);
        this.Z0011.addMouseMotionListener(this.Z0028);
        this.Z0001 = null;
    }
    
    JPanel Z0074() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0002), panel.getBorder()));
        this.Z0003 = new JButton[2];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0056(0, panel2, "Clear");
        this.Z0058(panel2, 10, 0);
        this.Z0056(1, panel2, "Start");
        panel.add(panel2);
        this.Z0058(panel, 0, 20);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 1));
        panel3.setAlignmentX(0.5f);
        panel3.setBorder(BorderFactory.createEtchedBorder());
        this.Z0045 = new JRadioButton[3];
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.Z0057(0, panel3, buttonGroup, "Add Mass", true);
        this.Z0057(1, panel3, buttonGroup, "Add Spring", false);
        this.Z0057(2, panel3, buttonGroup, "Pull", false);
        panel.add(panel3);
        this.Z0058(panel, 0, 20);
        this.Z0082 = new JSlider[2];
        this.Z0059(0, panel, this.Z0086, 1, 100, "Spring K");
        this.Z0059(1, panel, this.Z0091, 1, 20, "Update");
        return panel;
    }
    
    void Z0056(final int n, final JPanel panel, final String s) {
        (this.Z0003[n] = new JButton(s)).setPreferredSize(this.Z0003[n].getPreferredSize());
        this.Z0003[n].addActionListener(this);
        panel.add(this.Z0003[n]);
    }
    
    void Z0057(final int n, final JPanel panel, final ButtonGroup buttonGroup, final String s, final boolean selected) {
        (this.Z0045[n] = new JRadioButton(s)).setAlignmentX(0.0f);
        this.Z0045[n].addActionListener(this);
        this.Z0045[n].setSelected(selected);
        buttonGroup.add(this.Z0045[n]);
        panel.add(this.Z0045[n]);
    }
    
    void Z0059(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0058(panel, 0, 3);
        (this.Z0082[n] = new JSlider(n3, n4, n2)).setAlignmentX(0.5f);
        final Dimension preferredSize = this.Z0082[n].getPreferredSize();
        preferredSize.width = 100;
        this.Z0082[n].setPreferredSize(preferredSize);
        this.Z0082[n].setMajorTickSpacing(n4 - n3);
        this.Z0082[n].setMinorTickSpacing((n4 - n3) / 5);
        this.Z0082[n].setPaintLabels(true);
        this.Z0082[n].setPaintTicks(true);
        this.Z0082[n].addChangeListener(this);
        panel.add(this.Z0082[n]);
        this.Z0058(panel, 0, 5);
    }
    
    void Z0058(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0082[0]) {
                this.Z0086 = value;
            }
            else if (slider == this.Z0082[1]) {
                this.Z0091 = value;
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0003[0]) {
            this.Z0030 = 0;
            this.Z0032 = 0;
            this.Z0066 = -1;
            this.Z0048 = true;
        }
        else if (source == this.Z0003[1]) {
            this.Z0051 = !this.Z0051;
            this.Z0003[0].setEnabled(!this.Z0051);
            this.Z0003[1].setText(this.Z0051 ? "Stop" : "Start");
            this.Z0045[0].setEnabled(!this.Z0051);
            this.Z0045[1].setEnabled(!this.Z0051);
            this.Z0045[2].setEnabled(!this.Z0051);
            this.Z0071();
            this.Z0048 = true;
        }
        else if (source == this.Z0045[0]) {
            if (this.Z0045[0].isSelected()) {
                this.Z0029 = 0;
            }
        }
        else if (source == this.Z0045[1]) {
            if (this.Z0045[1].isSelected()) {
                this.Z0029 = 1;
            }
        }
        else if (source == this.Z0045[2] && this.Z0045[2].isSelected()) {
            this.Z0029 = 2;
        }
    }
    
    public void start() {
        if (this.Z0001 == null) {
            (this.Z0001 = new Thread(this)).setPriority(1);
            this.Z0001.start();
        }
        final Dimension size = this.Z0011.getSize();
        final int n = size.width - size.height;
        this.Z0011.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0002), BorderFactory.createRaisedBevelBorder()));
        this.Z0060();
    }
    
    public void stop() {
        this.Z0001 = null;
    }
    
    public void run() {
        while (Thread.currentThread() == this.Z0001) {
            if (this.Z0051) {
                final long currentTimeMillis = System.currentTimeMillis();
                this.Z0079();
                if (this.Z0090 % this.Z0091 != 0) {
                    continue;
                }
                this.Z0011.repaint();
                this.Z0011.getToolkit().sync();
                if (!this.Z0080(Math.max(5L, 20L - (System.currentTimeMillis() - currentTimeMillis)))) {
                    break;
                }
                continue;
            }
            else {
                if (this.Z0048) {
                    this.Z0048 = false;
                    this.Z0011.repaint();
                }
                if (!this.Z0080(5L)) {
                    break;
                }
                continue;
            }
        }
    }
    
    boolean Z0080(final long n) {
        try {
            Thread.sleep(n);
            return true;
        }
        catch (InterruptedException ex) {
            return false;
        }
    }
    
    void Z0077() {
        this.Z0091 = 5;
        this.Z0010 = 8;
        this.Z0025 = 32;
        this.Z0024 = 10;
        this.Z0026 = this.Z0024 * (this.Z0024 - 1) / 2;
        this.Z0086 = 20;
        this.Z0009 = 0.005;
    }
    
    void Z0060() {
        this.Z0073();
        this.Z0030 = 2;
        this.Z0049 = Math.min(this.Z0011.getSize().width, this.Z0011.getSize().height);
        this.Z0043[0] = this.Z0049 / 3;
        this.Z0044[0] = this.Z0049 / 3;
        this.Z0043[1] = 2 * this.Z0049 / 3;
        this.Z0044[1] = 2 * this.Z0049 / 3;
        this.Z0032 = 1;
        this.Z0087[0] = 0;
        this.Z0087[1] = 1;
        this.Z0069();
        this.Z0071();
        this.Z0090 = 0;
        this.Z0029 = 0;
        this.Z0051 = false;
        this.Z0040 = false;
    }
    
    void Z0073() {
        this.Z0054 = new double[this.Z0024];
        this.Z0055 = new double[this.Z0024];
        this.Z0052 = new double[this.Z0024];
        this.Z0053 = new double[this.Z0024];
        this.Z0046 = new double[this.Z0024];
        this.Z0047 = new double[this.Z0024];
        this.Z0043 = new double[this.Z0024];
        this.Z0044 = new double[this.Z0024];
        this.Z0022 = new double[this.Z0026];
        this.Z0087 = new int[2 * this.Z0026];
        this.Z0038 = new int[this.Z0025];
        this.Z0039 = new int[this.Z0025];
    }
    
    void Z0079() {
        ++this.Z0090;
        this.Z0064();
        this.Z0072();
        this.Z0063();
    }
    
    boolean Z0062(final int n) {
        int n2 = 0;
        for (int i = 0; i < this.Z0032; ++i) {
            if (n == this.Z0087[2 * i] || n == this.Z0087[2 * i + 1]) {
                ++n2;
            }
        }
        return n2 < this.Z0030 - 1;
    }
    
    boolean Z0061(final int n, final int n2) {
        if (n == n2) {
            return false;
        }
        for (int i = 0; i < this.Z0032; ++i) {
            if ((n == this.Z0087[2 * i] && n2 == this.Z0087[2 * i + 1]) || (n == this.Z0087[2 * i + 1] && n2 == this.Z0087[2 * i])) {
                return false;
            }
        }
        return true;
    }
    
    void Z0075(final int n, final int n2, final int n3) {
        if (this.Z0051) {
            return;
        }
        switch (this.Z0029) {
            case 0: {
                this.Z0065(n, n2, n3);
                break;
            }
            case 1: {
                this.Z0068(n, n2, n3);
                break;
            }
            case 2: {
                this.Z0067(n, n2, n3);
                break;
            }
        }
    }
    
    void Z0065(final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                this.Z0066 = -1;
                for (int i = 0; i < this.Z0030; ++i) {
                    final int n4 = (int)this.Z0054[i];
                    final int n5 = (int)this.Z0055[i];
                    if (Math.abs(n - n4) < this.Z0010 && Math.abs(n2 - n5) < this.Z0010) {
                        this.Z0066 = i;
                        break;
                    }
                }
                if (this.Z0066 < 0 && this.Z0030 < this.Z0024 && n >= this.Z0010 && n <= this.Z0049 - this.Z0010 && n2 >= this.Z0010 && n2 <= this.Z0049 - this.Z0010) {
                    this.Z0066 = this.Z0030;
                    this.Z0054[this.Z0030] = n;
                    this.Z0055[this.Z0030] = n2;
                    ++this.Z0030;
                    this.Z0048 = true;
                    break;
                }
                break;
            }
            case 0: {
                if (this.Z0066 < 0) {
                    break;
                }
                final int n6 = (int)this.Z0054[this.Z0066];
                final int n7 = (int)this.Z0055[this.Z0066];
                if (Math.abs(n - n6) < 4 * this.Z0010 && Math.abs(n2 - n7) < 4 * this.Z0010 && (n != n6 || n2 != n7) && n >= this.Z0010 && n <= this.Z0049 - this.Z0010 && n2 >= this.Z0010 && n2 <= this.Z0049 - this.Z0010) {
                    this.Z0054[this.Z0066] = n;
                    this.Z0055[this.Z0066] = n2;
                    this.Z0048 = true;
                    break;
                }
                break;
            }
            case -1: {
                if (this.Z0066 >= 0) {
                    this.Z0043[this.Z0066] = this.Z0054[this.Z0066];
                    this.Z0044[this.Z0066] = this.Z0055[this.Z0066];
                    this.Z0069();
                    this.Z0048 = true;
                    this.Z0066 = -1;
                    break;
                }
                break;
            }
        }
    }
    
    void Z0068(final int n, final int n2, final int n3) {
        if (this.Z0030 < 2) {
            return;
        }
        switch (n3) {
            case 1: {
                this.Z0084 = -1;
                for (int i = 0; i < this.Z0030; ++i) {
                    final int n4 = (int)this.Z0054[i];
                    final int n5 = (int)this.Z0055[i];
                    if (Math.abs(n - n4) < this.Z0010 && Math.abs(n2 - n5) < this.Z0010 && this.Z0062(i)) {
                        this.Z0084 = i;
                        this.Z0088 = n;
                        this.Z0089 = n2;
                        this.Z0048 = true;
                        break;
                    }
                }
                break;
            }
            case 0: {
                if (this.Z0084 < 0) {
                    break;
                }
                final int n6 = (int)this.Z0088;
                final int n7 = (int)this.Z0089;
                if ((n != n6 || n2 != n7) && n >= this.Z0010 && n <= this.Z0049 - this.Z0010 && n2 >= this.Z0010 && n2 <= this.Z0049 - this.Z0010) {
                    this.Z0088 = n;
                    this.Z0089 = n2;
                    this.Z0048 = true;
                    break;
                }
                break;
            }
            case -1: {
                if (this.Z0084 >= 0) {
                    int n8 = -1;
                    int n9 = 1073741824;
                    for (int j = 0; j < this.Z0030; ++j) {
                        if (this.Z0061(this.Z0084, j)) {
                            final double n10 = this.Z0088 - this.Z0054[j];
                            final double n11 = this.Z0089 - this.Z0055[j];
                            final int n12 = (int)(n10 * n10 + n11 * n11);
                            if (n12 < n9) {
                                n9 = n12;
                                n8 = j;
                            }
                        }
                    }
                    this.Z0087[2 * this.Z0032] = this.Z0084;
                    this.Z0087[2 * this.Z0032 + 1] = n8;
                    ++this.Z0032;
                    this.Z0084 = -1;
                    this.Z0069();
                    this.Z0048 = true;
                    break;
                }
                break;
            }
        }
    }
    
    void Z0067(final int n, final int n2, final int n3) {
        switch (n3) {
            case 1: {
                this.Z0066 = -1;
                for (int i = 0; i < this.Z0030; ++i) {
                    final int n4 = (int)this.Z0054[i];
                    final int n5 = (int)this.Z0055[i];
                    if (Math.abs(n - n4) < this.Z0010 && Math.abs(n2 - n5) < this.Z0010) {
                        this.Z0066 = i;
                        this.Z0040 = true;
                        break;
                    }
                }
                break;
            }
            case 0: {
                if (this.Z0066 < 0) {
                    break;
                }
                final int n6 = (int)this.Z0054[this.Z0066];
                final int n7 = (int)this.Z0055[this.Z0066];
                if (Math.abs(n - n6) < 4 * this.Z0010 && Math.abs(n2 - n7) < 4 * this.Z0010 && (n != n6 || n2 != n7) && n >= this.Z0010 && n <= this.Z0049 - this.Z0010 && n2 >= this.Z0010 && n2 <= this.Z0049 - this.Z0010) {
                    this.Z0054[this.Z0066] = n;
                    this.Z0055[this.Z0066] = n2;
                    this.Z0048 = true;
                    break;
                }
                break;
            }
            case -1: {
                if (this.Z0066 >= 0) {
                    this.Z0066 = -1;
                    this.Z0040 = false;
                    break;
                }
                break;
            }
        }
    }
    
    void Z0071() {
        if (this.Z0051) {
            for (int i = 0; i < this.Z0030; ++i) {
                this.Z0052[i] = 0.0;
                this.Z0053[i] = 0.0;
            }
            this.Z0090 = 0;
        }
        else {
            for (int j = 0; j < this.Z0030; ++j) {
                this.Z0054[j] = this.Z0043[j];
                this.Z0055[j] = this.Z0044[j];
            }
        }
        this.Z0066 = -1;
        this.Z0084 = -1;
        this.Z0048 = true;
    }
    
    void Z0069() {
        for (int i = 0; i < this.Z0032; ++i) {
            final double n = this.Z0043[this.Z0087[2 * i]] - this.Z0043[this.Z0087[2 * i + 1]];
            final double n2 = this.Z0044[this.Z0087[2 * i]] - this.Z0044[this.Z0087[2 * i + 1]];
            this.Z0022[i] = Math.sqrt(n * n + n2 * n2);
        }
    }
    
    void Z0064() {
        for (int i = 0; i < this.Z0030; ++i) {
            this.Z0046[i] = 0.0;
            this.Z0047[i] = 0.0;
        }
        final double n = this.Z0086;
        for (int j = 0; j < this.Z0032; ++j) {
            final int n2 = this.Z0087[2 * j];
            final int n3 = this.Z0087[2 * j + 1];
            final double n4 = this.Z0054[n2] - this.Z0054[n3];
            final double n5 = this.Z0055[n2] - this.Z0055[n3];
            double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
            if (sqrt < 0.01) {
                sqrt = 0.01;
            }
            final double n6 = n * (this.Z0022[j] / sqrt - 1.0);
            final double[] z0046 = this.Z0046;
            final int n7 = n2;
            z0046[n7] += n6 * n4;
            final double[] z47 = this.Z0047;
            final int n8 = n2;
            z47[n8] += n6 * n5;
            final double[] z48 = this.Z0046;
            final int n9 = n3;
            z48[n9] -= n6 * n4;
            final double[] z49 = this.Z0047;
            final int n10 = n3;
            z49[n10] -= n6 * n5;
        }
    }
    
    void Z0072() {
        for (int i = 0; i < this.Z0030; ++i) {
            final double[] z0052 = this.Z0052;
            final int n = i;
            z0052[n] += this.Z0009 * this.Z0046[i];
            final double[] z53 = this.Z0053;
            final int n2 = i;
            z53[n2] += this.Z0009 * this.Z0047[i];
            final double[] z54 = this.Z0054;
            final int n3 = i;
            z54[n3] += this.Z0009 * this.Z0052[i];
            final double[] z55 = this.Z0055;
            final int n4 = i;
            z55[n4] += this.Z0009 * this.Z0053[i];
        }
    }
    
    void Z0063() {
        for (int i = 0; i < this.Z0030; ++i) {
            if (this.Z0054[i] < this.left + this.Z0010) {
                this.Z0054[i] = this.left + this.Z0010;
                if (this.Z0052[i] < 0.0) {
                    final double[] z0052 = this.Z0052;
                    final int n = i;
                    z0052[n] *= -1.0;
                }
            }
            else if (this.Z0054[i] > this.left + this.Z0049 - this.Z0010) {
                this.Z0054[i] = this.left + this.Z0049 - this.Z0010;
                if (this.Z0052[i] > 0.0) {
                    final double[] z53 = this.Z0052;
                    final int n2 = i;
                    z53[n2] *= -1.0;
                }
            }
            if (this.Z0055[i] < this.top + this.Z0010) {
                this.Z0055[i] = this.top + this.Z0010;
                if (this.Z0053[i] < 0.0) {
                    final double[] z54 = this.Z0053;
                    final int n3 = i;
                    z54[n3] *= -1.0;
                }
            }
            else if (this.Z0055[i] > this.top + this.Z0049 - this.Z0010) {
                this.Z0055[i] = this.top + this.Z0049 - this.Z0010;
                if (this.Z0053[i] > 0.0) {
                    final double[] z55 = this.Z0053;
                    final int n4 = i;
                    z55[n4] *= -1.0;
                }
            }
        }
    }
    
    void Z0076(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.Z0011.getSize();
        final Insets insets = this.Z0011.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        this.Z0049 = Math.min(n, n2);
        this.left = insets.left + (n - this.Z0049) / 2;
        this.top = insets.top + (n2 - this.Z0049) / 2;
        graphics.setColor(Color.pink);
        graphics.fillRect(this.left, this.top, this.Z0049, this.Z0049);
        graphics.setColor(Color.blue);
        for (int i = 0; i < this.Z0030; ++i) {
            graphics.fillOval((int)this.Z0054[i] - this.Z0010, (int)this.Z0055[i] - this.Z0010, 2 * this.Z0010, 2 * this.Z0010);
        }
        for (int j = 0; j < this.Z0032; ++j) {
            this.Z0078(graphics, j);
        }
        if (this.Z0084 >= 0) {
            this.Z0078(graphics, -1);
        }
    }
    
    void Z0078(final Graphics graphics, final int n) {
        double n2;
        double z0088;
        double n3;
        double z89;
        if (n < 0) {
            n2 = this.Z0054[this.Z0084];
            z0088 = this.Z0088;
            n3 = this.Z0055[this.Z0084];
            z89 = this.Z0089;
        }
        else {
            n2 = this.Z0054[this.Z0087[2 * n]];
            z0088 = this.Z0054[this.Z0087[2 * n + 1]];
            n3 = this.Z0055[this.Z0087[2 * n]];
            z89 = this.Z0055[this.Z0087[2 * n + 1]];
        }
        double n4 = z0088 - n2;
        double n5 = z89 - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5);
        if (sqrt > 0.0) {
            n4 /= sqrt;
            n5 /= sqrt;
        }
        final double n6 = 1.2 * this.Z0010;
        int z90;
        if (this.Z0051 || this.Z0040) {
            z90 = 2 * (int)(this.Z0022[n] / n6 + 1.0) + 2;
        }
        else {
            z90 = 2 * (int)(sqrt / n6 + 1.0) + 2;
        }
        if (z90 > this.Z0025) {
            z90 = this.Z0025;
        }
        this.Z0038[0] = (int)n2;
        this.Z0039[0] = (int)n3;
        double n7;
        if (z90 > 2) {
            n7 = sqrt / (z90 - 2);
        }
        else {
            n7 = 0.0;
        }
        double sqrt2 = Math.sqrt(Math.max(4.0, n6 * n6 - n7 * n7));
        for (int i = 1; i < z90 - 1; ++i) {
            final double n8 = (i - 0.5) * n7;
            sqrt2 = -sqrt2;
            this.Z0038[i] = (int)(n2 + n8 * n4 - sqrt2 * n5);
            this.Z0039[i] = (int)(n3 + n8 * n5 + sqrt2 * n4);
        }
        this.Z0038[z90 - 1] = (int)z0088;
        this.Z0039[z90 - 1] = (int)z89;
        for (int j = 0; j < z90 - 1; ++j) {
            graphics.drawLine(this.Z0038[j], this.Z0039[j], this.Z0038[j + 1], this.Z0039[j + 1]);
        }
    }
    
    class MouseAction extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            Spring.this.Z0075(mouseEvent.getX(), mouseEvent.getY(), 1);
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Spring.this.Z0075(mouseEvent.getX(), mouseEvent.getY(), 0);
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Spring.this.Z0075(mouseEvent.getX(), mouseEvent.getY(), -1);
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
            Spring.this.Z0076(graphics);
        }
    }
}
