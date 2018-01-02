import java.text.NumberFormat;
import java.awt.Insets;
import java.util.StringTokenizer;
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
import java.awt.Graphics;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionListener;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lsys extends JApplet implements ActionListener, ChangeListener
{
    Graphics Z0022;
    JButton[] Z0009;
    JSlider[] Z0061;
    JLabel[] Z0023;
    DrawingArea Z0019;
    Color Z0008;
    String[] Z0038;
    String[] Z0081;
    String Z0007;
    double[] Z0068;
    double[] Z0069;
    double[] Z0064;
    double[] Z0067;
    double Z0076;
    double Z0077;
    double Z0074;
    double Z0075;
    double Z0033;
    double Z0031;
    double Z0034;
    double Z0032;
    double scale;
    double Z0028;
    double Z0029;
    double Z0006;
    double Z0005;
    double Z0004;
    double Z0003;
    double Z0002;
    double Z0001;
    double Z0035;
    int Z0065;
    int Z0025;
    int Z0015;
    int Z0016;
    int Z0036;
    int Z0066;
    int Z0073;
    int Z0013;
    int left;
    int top;
    boolean Z0011;
    boolean Z0018;
    boolean Z0010;
    String[][] Z0072;
    
    public Lsys() {
        this.Z0072 = new String[][] { { "Dragon Curve", "1", "1->-1++2 2->1--2+", "1->--0++0 2->0--0++", "45", null, null, null, "3", "11" }, { "Weed", "0", "0->0[+0]0[-0]0", null, "26", null, "90", "0.95", "2", "6" }, { "S Curve", "1", "1->10201+0+20102-0-10201 2->20102-0-10201+0+20102", "1->00+0+00-0-00 2->00-0-00+0+00", "90", null, null, null, "1", "4" }, { "Peano Curve", "0", "0->00+0+0+00+0+0-0", null, "90", null, null, null, "1", "4" }, { "Arrowhead", "1", "1->+2-1-2+ 2->-1+2+1-", "1->+0-0-0+ 2->-0+0+0-", "60", "60", null, null, "1", "7" }, { "Bush-1", "12000", "1->[+++3][---3]41 3->+5[-3]2 5->-3[+5]2 4->42 2->[-000][+000]0", null, "18", null, "90", "0.9", "3", "10" }, { "Bush-2", "1", "1->102[+1][-1] 2->2[-000][+000]02", null, "26", null, "90", "0.9", "2", "7" }, { "Bush-3", "0", "0->00+[+0-0-0]-[-0+0+0]", null, "25", null, "90", "0.9", "2", "5" }, { "Koch Curve", "0", "0->0+0--0+0", null, "60", null, null, null, "2", "6" }, { "Grass", "1", "0->00 1->0[+1]0[-1]+1", null, "20", null, "90", null, "2", "8" }, { "Tree", "0", "0->0[+0][-0]", null, "45", "30", "90", "0.6", "2", "9" }, { "Sierpinski Gasket", "010++00++00", "1->++010--010--010++ 0->00", null, "60", null, null, null, "1", "7" }, { "Koch Island", "0+0+0+0", "0->0+0-0-00+0+0-0", null, "90", null, null, null, "1", "4" }, { "Hilbert Curve", "1", "1->+20-101-02+ 2->-10+202+01-", "1->+0-0-0+ 2->-0+0+0-", "90", null, null, null, "1", "6" } };
    }
    
    public void init() {
        this.Z0052();
        this.Z0008 = new Color(204, 255, 255);
        final Container contentPane = this.getContentPane();
        contentPane.add(this.Z0049(), "East");
        contentPane.add(this.Z0019 = new DrawingArea(), "Center");
    }
    
    JPanel Z0049() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 0, 5, 5, this.Z0008), panel.getBorder()));
        this.Z0023 = new JLabel[2];
        this.Z0009 = new JButton[4];
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, 0));
        this.Z0039(0, panel2, "<<");
        this.Z0041(panel2, 5, 0);
        this.Z0039(1, panel2, ">>");
        panel.add(panel2);
        this.Z0041(panel, 0, 10);
        this.Z0040(0, panel, " ");
        this.Z0041(panel, 0, 20);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, 0));
        this.Z0039(2, panel3, "<<");
        this.Z0041(panel3, 5, 0);
        this.Z0039(3, panel3, ">>");
        panel.add(panel3);
        this.Z0041(panel, 0, 10);
        this.Z0040(1, panel, " ");
        this.Z0041(panel, 0, 20);
        this.Z0061 = new JSlider[3];
        this.Z0042(0, panel, (int)this.Z0003, 0, 360, "AngleP");
        this.Z0042(1, panel, (int)this.Z0002, 0, 360, "AngleM");
        this.Z0042(2, panel, (int)(100.0 * this.Z0035), 0, 100, "Reduce");
        return panel;
    }
    
    void Z0039(final int n, final JPanel panel, final String s) {
        (this.Z0009[n] = new JButton(s)).setPreferredSize(this.Z0009[n].getPreferredSize());
        this.Z0009[n].addActionListener(this);
        panel.add(this.Z0009[n]);
    }
    
    void Z0040(final int n, final JPanel panel, final String s) {
        (this.Z0023[n] = new JLabel(s, 0)).setAlignmentX(0.5f);
        panel.add(this.Z0023[n]);
    }
    
    void Z0042(final int n, final JPanel panel, final int n2, final int n3, final int n4, final String s) {
        final JLabel label = new JLabel(s, 0);
        label.setAlignmentX(0.5f);
        panel.add(label);
        this.Z0041(panel, 0, 3);
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
        this.Z0041(panel, 0, 5);
    }
    
    void Z0041(final JPanel panel, final int n, final int n2) {
        panel.add(Box.createRigidArea(new Dimension(n, n2)));
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        final JSlider slider = (JSlider)changeEvent.getSource();
        if (!slider.getValueIsAdjusting()) {
            final int value = slider.getValue();
            if (slider == this.Z0061[0]) {
                this.Z0003 = value;
            }
            else if (slider == this.Z0061[1]) {
                this.Z0002 = value;
            }
            else if (slider == this.Z0061[2]) {
                this.Z0035 = 0.01 * value;
            }
        }
        this.Z0019.repaint();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final Object source = actionEvent.getSource();
        if (source == this.Z0009[0]) {
            if (--this.Z0013 < 0) {
                this.Z0013 = this.Z0073 - 1;
            }
            this.Z0010 = true;
        }
        else if (source == this.Z0009[1]) {
            if (++this.Z0013 >= this.Z0073) {
                this.Z0013 = 0;
            }
            this.Z0010 = true;
        }
        else if (source == this.Z0009[2]) {
            if (--this.Z0015 < 0) {
                this.Z0015 = 0;
            }
        }
        else if (source == this.Z0009[3] && ++this.Z0015 > this.Z0016) {
            this.Z0015 = this.Z0016;
        }
        this.Z0019.repaint();
    }
    
    public void start() {
        final Dimension size = this.Z0019.getSize();
        final int n = size.width - size.height;
        this.Z0019.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(5, 5 + Math.max(n, 0), 5 + Math.max(-n, 0), 5, this.Z0008), BorderFactory.createRaisedBevelBorder()));
        this.Z0043();
    }
    
    public void stop() {
    }
    
    void Z0052() {
        this.Z0013 = 0;
        this.Z0073 = this.Z0072.length;
        this.Z0025 = 10;
        this.Z0065 = 100;
        this.Z0003 = 0.0;
        this.Z0002 = 0.0;
        this.Z0035 = 0.0;
    }
    
    void Z0043() {
        this.Z0048();
        this.Z0010 = true;
    }
    
    void Z0048() {
        this.Z0068 = new double[this.Z0065];
        this.Z0069 = new double[this.Z0065];
        this.Z0064 = new double[this.Z0065];
        this.Z0067 = new double[this.Z0065];
    }
    
    void Z0044() {
        if (this.Z0010) {
            this.Z0010 = false;
            this.Z0007 = this.Z0072[this.Z0013][1];
            final StringTokenizer stringTokenizer = new StringTokenizer(this.Z0072[this.Z0013][2]);
            this.Z0038 = new String[10];
            this.Z0081 = new String[10];
            for (int i = stringTokenizer.countTokens() - 1; i >= 0; --i) {
                final String nextToken = stringTokenizer.nextToken();
                this.Z0038[Character.digit(nextToken.charAt(0), 10)] = nextToken.substring(3);
            }
            if (this.Z0072[this.Z0013][3] != null) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(this.Z0072[this.Z0013][3]);
                for (int j = stringTokenizer2.countTokens() - 1; j >= 0; --j) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    this.Z0081[Character.digit(nextToken2.charAt(0), 10)] = nextToken2.substring(3);
                }
            }
            this.Z0003 = Double.valueOf(this.Z0072[this.Z0013][4]);
            final int n = (this.Z0072[this.Z0013][5] == null) ? 1 : 2;
            this.Z0002 = ((n == 1) ? this.Z0003 : Double.valueOf(this.Z0072[this.Z0013][5]));
            this.Z0001 = ((this.Z0072[this.Z0013][6] == null) ? 0.0 : Double.valueOf(this.Z0072[this.Z0013][6]));
            this.Z0035 = ((this.Z0072[this.Z0013][7] == null) ? 1.0 : Double.valueOf(this.Z0072[this.Z0013][7]));
            this.Z0015 = Integer.valueOf(this.Z0072[this.Z0013][8]);
            this.Z0016 = Integer.valueOf(this.Z0072[this.Z0013][9]);
            this.Z0023[0].setText(this.Z0072[this.Z0013][0]);
            this.Z0061[0].setValue((int)this.Z0003);
            this.Z0061[1].setValue((int)this.Z0002);
            this.Z0061[2].setValue((int)(100.0 * this.Z0035));
            this.Z0061[1].setEnabled(n == 2);
            this.Z0061[2].setEnabled(this.Z0035 < 1.0);
        }
        this.Z0023[1].setText("Order: " + String.valueOf(this.Z0015));
        this.Z0006 = this.Z0003 * 3.141592653589793 / 180.0;
        this.Z0005 = this.Z0002 * 3.141592653589793 / 180.0;
        this.Z0004 = this.Z0001 * 3.141592653589793 / 180.0;
        this.Z0033 = 0.0;
        this.Z0031 = 0.0;
        this.Z0034 = 0.0;
        this.Z0032 = 0.0;
        this.Z0047(false);
        this.Z0050(this.Z0007, 0);
        this.scale = Math.min((this.Z0036 - 2 * this.Z0025) / (this.Z0031 - this.Z0033), (this.Z0036 - 2 * this.Z0025) / (this.Z0032 - this.Z0034));
        this.Z0028 = 0.5 * (this.Z0036 - (this.Z0031 + this.Z0033) * this.scale);
        this.Z0029 = 0.5 * (this.Z0036 - (this.Z0032 + this.Z0034) * this.scale);
        this.Z0047(true);
        this.Z0050(this.Z0007, 0);
    }
    
    void Z0050(final String s, final int n) {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (n < this.Z0015) {
                if (Character.isDigit(char1) && this.Z0038[Character.digit(char1, 10)] != null) {
                    this.Z0050(this.Z0038[Character.digit(char1, 10)], n + 1);
                }
                else {
                    this.Z0050(s.substring(i, i + 1), n + 1);
                }
            }
            else if (Character.isDigit(char1) && this.Z0081[Character.digit(char1, 10)] != null) {
                this.Z0050(this.Z0081[Character.digit(char1, 10)], n);
            }
            else {
                this.Z0058(char1);
            }
        }
    }
    
    void Z0047(final boolean z0018) {
        this.Z0018 = z0018;
        this.Z0076 = 0.0;
        this.Z0077 = 0.0;
        this.Z0074 = this.Z0004;
        this.Z0075 = 1.0;
        this.Z0066 = 0;
    }
    
    void Z0058(final char c) {
        switch (c) {
            case '0': {
                this.Z0053();
                break;
            }
            case '9': {
                this.Z0054();
                break;
            }
            case '+': {
                this.Z0057(1);
                break;
            }
            case '-': {
                this.Z0057(-1);
                break;
            }
            case '|': {
                this.Z0057(0);
                break;
            }
            case '[': {
                this.Z0056();
                break;
            }
            case ']': {
                this.Z0055();
                break;
            }
        }
    }
    
    void Z0053() {
        int n;
        int n2;
        if (this.Z0018) {
            n = (int)(this.Z0028 + this.scale * this.Z0076);
            n2 = (int)(this.Z0029 + this.scale * this.Z0077);
        }
        else {
            n = 0;
            n2 = 0;
        }
        this.Z0054();
        if (this.Z0018) {
            this.Z0022.drawLine(n + this.left, this.Z0036 - n2 + this.top, (int)(this.Z0028 + this.scale * this.Z0076) + this.left, this.Z0036 - (int)(this.Z0029 + this.scale * this.Z0077) + this.top);
        }
    }
    
    void Z0054() {
        this.Z0076 += this.Z0075 * Math.cos(this.Z0074);
        this.Z0077 += this.Z0075 * Math.sin(this.Z0074);
        if (!this.Z0018) {
            if (this.Z0076 < this.Z0033) {
                this.Z0033 = this.Z0076;
            }
            else if (this.Z0076 > this.Z0031) {
                this.Z0031 = this.Z0076;
            }
            if (this.Z0077 < this.Z0034) {
                this.Z0034 = this.Z0077;
            }
            else if (this.Z0077 > this.Z0032) {
                this.Z0032 = this.Z0077;
            }
        }
    }
    
    void Z0057(final int n) {
        if (n > 0) {
            final double z0074 = this.Z0074 + this.Z0006;
            this.Z0074 = z0074;
            if (z0074 >= 3.141592653589793) {
                this.Z0074 -= 6.283185307179586;
            }
        }
        else if (n < 0) {
            final double z75 = this.Z0074 - this.Z0005;
            this.Z0074 = z75;
            if (z75 < -3.141592653589793) {
                this.Z0074 += 6.283185307179586;
            }
        }
        else {
            final double z76 = this.Z0074 - 3.141592653589793;
            this.Z0074 = z76;
            if (z76 < -3.141592653589793) {
                this.Z0074 += 6.283185307179586;
            }
        }
    }
    
    void Z0056() {
        this.Z0068[this.Z0066] = this.Z0076;
        this.Z0069[this.Z0066] = this.Z0077;
        this.Z0064[this.Z0066] = this.Z0074;
        this.Z0067[this.Z0066] = this.Z0075;
        ++this.Z0066;
        this.Z0075 *= this.Z0035;
    }
    
    void Z0055() {
        --this.Z0066;
        this.Z0076 = this.Z0068[this.Z0066];
        this.Z0077 = this.Z0069[this.Z0066];
        this.Z0074 = this.Z0064[this.Z0066];
        this.Z0075 = this.Z0067[this.Z0066];
    }
    
    void Z0051(final Graphics z0022) {
        if (z0022 == null) {
            return;
        }
        this.Z0022 = z0022;
        final Dimension size = this.Z0019.getSize();
        final Insets insets = this.Z0019.getInsets();
        final int n = size.width - insets.left - insets.right;
        final int n2 = size.height - insets.top - insets.bottom;
        this.Z0036 = Math.min(n, n2);
        this.left = insets.left + (n - this.Z0036) / 2;
        this.top = insets.top + (n2 - this.Z0036) / 2;
        z0022.setColor(Color.black);
        z0022.fillRect(this.left, this.top, this.Z0036, this.Z0036);
        z0022.setColor(Color.green);
        this.Z0044();
    }
    
    String Z0046(final double n, final int n2) {
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
            Lsys.this.Z0051(graphics);
        }
    }
}
