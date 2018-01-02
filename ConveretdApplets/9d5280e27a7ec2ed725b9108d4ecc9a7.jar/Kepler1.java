import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FontMetrics;
import java.awt.event.MouseMotionListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class Kepler1 extends PhApplet implements ActionListener, ItemListener, MouseMotionListener
{
    int width0;
    final int xM = 220;
    final int yM = 160;
    FontMetrics fmH;
    Canvas1 cv;
    GBLJPanel pan;
    JComboBox chPl;
    JTextField tfA;
    JTextField tfEps;
    JCheckBox cbE;
    JCheckBox cbA;
    JCheckBox cbL;
    JLabel lB;
    JLabel lMin;
    JLabel lMax;
    JLabel lD;
    final int aPix = 120;
    int bPix;
    int ePix;
    double a;
    double b;
    double eps;
    double phi;
    boolean unknown;
    final double[] aPl;
    final double[] epsPl;
    
    public Kepler1() {
        this.aPl = new double[] { 0.387, 0.723, 1.0, 1.52, 5.2, 9.55, 19.2, 30.1, 39.7, 17.9 };
        this.epsPl = new double[] { 0.206, 0.007, 0.017, 0.093, 0.048, 0.056, 0.046, 0.009, 0.252, 0.967 };
    }
    
    public void start() {
        super.start();
        this.fmH = this.getFontMetrics(Kepler1.HELV);
        this.a = this.aPl[0];
        this.eps = this.epsPl[0];
        this.phi = 0.5235987755982988;
        this.unknown = false;
        this.calculation();
        this.width0 = 440;
        (this.cv = new Canvas1()).setBackground(this.BG);
        this.cv.setBounds(0, 0, this.width0, this.height);
        this.cp.add(this.cv);
        (this.pan = new GBLJPanel(this.PAN)).setBounds(this.width0, 0, this.width - this.width0, this.height);
        this.chPl = new JComboBox();
        for (int i = 0; i < this.aPl.length; ++i) {
            this.chPl.addItem(this.text(19 + i));
        }
        this.chPl.addItem("");
        this.pan.add((JComponent)this.chPl, Color.white, 0, 0, 3, 15, 10, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(3)), this.PAN, 0, 1, 1, 15, 10, 0, 5);
        (this.tfA = new JTextField(5)).setText(this.toString2(this.a, 3));
        this.pan.add((JComponent)this.tfA, Color.white, 1, 1, 1, 15, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(2)), this.PAN, 2, 1, 1, 15, 5, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(4)), this.PAN, 0, 2, 1, 5, 10, 0, 5);
        (this.tfEps = new JTextField(5)).setText(this.toString2(this.eps, 3));
        this.pan.add((JComponent)this.tfEps, Color.white, 1, 2, 1, 5, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(5)), this.PAN, 0, 3, 1, 5, 10, 0, 5);
        (this.lB = new JLabel()).setText(this.toString2(this.b, 3));
        this.pan.add((JComponent)this.lB, this.PAN, 1, 3, 1, 5, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(2)), this.PAN, 2, 3, 1, 5, 5, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(6)), this.PAN, 0, 4, 3, 15, 10, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(7)), this.PAN, 0, 5, 1, 0, 10, 0, 0);
        this.lD = new JLabel();
        this.pan.add((JComponent)this.lD, this.PAN, 1, 5, 1, 0, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(2)), this.PAN, 2, 5, 1, 0, 5, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(8)), this.PAN, 0, 6, 1, 0, 10, 0, 0);
        (this.lMin = new JLabel()).setText(this.toString2(this.a * (1.0 - this.eps), 3));
        this.pan.add((JComponent)this.lMin, this.PAN, 1, 6, 1, 0, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(2)), this.PAN, 2, 6, 1, 0, 5, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(9)), this.PAN, 0, 7, 1, 0, 10, 0, 0);
        (this.lMax = new JLabel()).setText(this.toString2(this.a * (1.0 + this.eps), 3));
        this.pan.add((JComponent)this.lMax, this.PAN, 1, 7, 1, 0, 0, 0, 0);
        this.pan.add((JComponent)new JLabel(this.text(2)), this.PAN, 2, 7, 1, 0, 5, 0, 10);
        this.cbE = new JCheckBox(this.text(10));
        this.pan.add((JComponent)this.cbE, this.PAN, 0, 8, 3, 15, 10, 0, 10);
        this.cbA = new JCheckBox(this.text(11));
        this.pan.add((JComponent)this.cbA, this.PAN, 0, 9, 3, 0, 10, 0, 10);
        this.cbL = new JCheckBox(this.text(12));
        this.pan.add((JComponent)this.cbL, this.PAN, 0, 10, 3, 0, 10, 0, 10);
        this.pan.add((JComponent)new JLabel(this.text(18)), this.PAN, 0, 11, 3, 15, 10, 15, 10);
        this.cp.add((Component)this.pan);
        this.pan.repaint();
        this.cv.repaint();
        this.chPl.addItemListener(this);
        this.tfA.addActionListener(this);
        this.tfEps.addActionListener(this);
        this.cbE.addItemListener(this);
        this.cbA.addItemListener(this);
        this.cbL.addItemListener(this);
        this.addMouseMotionListener((MouseMotionListener)this);
    }
    
    public void stop() {
        this.cp.removeAll();
    }
    
    void calculation() {
        this.b = this.a * Math.sqrt(1.0 - this.eps * this.eps);
        this.bPix = (int)Math.round(120.0 * this.b / this.a);
        this.ePix = (int)Math.round(this.eps * 120.0);
    }
    
    void planet(final Graphics g) {
        final double phi1 = Math.atan2(this.a * Math.sin(this.phi), this.b * Math.cos(this.phi));
        final int x = (int)Math.round(220.0 + 120.0 * Math.cos(phi1));
        final int y = (int)Math.round(160.0 - this.bPix * Math.sin(phi1));
        g.setColor(Color.blue);
        g.fillOval(x - 2, y - 2, 5, 5);
        final int i = this.chPl.getSelectedIndex();
        if (i < 9) {
            this.write(g, this.text(14), x, (y < 160) ? (y - 6) : (y + 16), 0);
        }
        else if (i == 9) {
            this.write(g, this.text(15), x, (y < 160) ? (y - 6) : (y + 16), 0);
        }
        if (this.cbL.isSelected()) {
            g.setColor(Color.black);
            g.drawLine(220 - this.ePix, 160, x, y);
            g.drawLine(220 + this.ePix, 160, x, y);
        }
        final double dx = this.a * (Math.cos(phi1) - this.eps);
        final double dy = this.b * Math.sin(phi1);
        final double d = Math.sqrt(dx * dx + dy * dy);
        this.lD.setText(this.toString2(d, 3));
    }
    
    void sun(final Graphics g) {
        g.setColor(Color.red);
        g.fillOval(220 + this.ePix - 4, 156, 9, 9);
        this.write(g, this.text(13), 220 + this.ePix, 154, 0);
        g.setColor(Color.black);
        g.drawOval(220 + this.ePix - 4, 156, 8, 8);
        if (this.cbL.isSelected()) {
            g.fillOval(220 - this.ePix - 1, 159, 3, 3);
            g.fillOval(220 + this.ePix - 1, 159, 3, 3);
            if (this.eps == 0.0) {
                this.write(g, "F = F'", 220, 176, 0);
            }
            else if (this.ePix < 10) {
                this.write(g, "F", 220 + this.ePix, 176, -1);
                this.write(g, "F'", 220 - this.ePix, 176, 1);
            }
            else {
                this.write(g, "F", 220 + this.ePix, 176, 0);
                this.write(g, "F'", 220 - this.ePix, 176, 0);
            }
        }
    }
    
    double inputTF(final JTextField tf, final double min, final double max, final int length, final int type) {
        double value = this.toDouble(tf.getText());
        if (value < min) {
            value = min;
        }
        if (value > max) {
            value = max;
        }
        if (type == 1) {
            tf.setText(this.toString(value, length));
        }
        else {
            tf.setText(this.toString2(value, length));
        }
        return value;
    }
    
    void write(final Graphics g, final String s, final int x, final int y, final int pos) {
        final int w = this.fmH.stringWidth(s);
        final int beg = x - (pos + 1) * w / 2;
        g.drawString(s, beg, y);
    }
    
    void scale(final Graphics g) {
        String s = "1 " + this.text(2);
        g.setColor(Color.black);
        int length = (int)Math.round(120.0 / this.a);
        if (length > 400) {
            length /= 10;
            s = this.toString(0.1, 1) + " " + this.text(2);
        }
        else if (length < 40) {
            length *= 10;
            s = "10 " + this.text(2);
        }
        final int xL = 220 - length / 2;
        final int xR = 220 + length / 2;
        final int y = this.height - 40;
        g.drawLine(xL, y, xR, y);
        g.drawLine(xL, y - 5, xL, y + 5);
        g.drawLine(xR, y - 5, xR, y + 5);
        this.write(g, s, 220, y + 15, 0);
    }
    
    void actionEnd() {
        this.calculation();
        this.lB.setText(this.toString2(this.b, 3));
        this.lMin.setText(this.toString2(this.a * (1.0 - this.eps), 3));
        this.lMax.setText(this.toString2(this.a * (1.0 + this.eps), 3));
        this.cv.repaint();
        this.pan.repaint();
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.a = this.inputTF(this.tfA, 0.1, 100.0, 3, 2);
        this.eps = this.inputTF(this.tfEps, 0.0, 0.999, 3, 1);
        this.unknown = true;
        this.chPl.setSelectedIndex(this.aPl.length);
        this.actionEnd();
    }
    
    public void itemStateChanged(final ItemEvent e) {
        if (e.getSource() == this.chPl) {
            int i = this.chPl.getSelectedIndex();
            final int n = this.aPl.length;
            if (i == n && !this.unknown) {
                --i;
                this.chPl.setSelectedIndex(i);
            }
            if (i < n) {
                this.unknown = false;
                this.a = this.aPl[i];
                this.eps = this.epsPl[i];
            }
            this.tfA.setText(this.toString2(this.a, 3));
            this.tfEps.setText(this.toString(this.eps, 3));
        }
        this.actionEnd();
    }
    
    public void mouseDragged(final MouseEvent e) {
        this.phi = Math.atan2(160 - e.getY(), e.getX() - 220);
        this.cv.repaint();
    }
    
    public void mouseMoved(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    class Canvas1 extends JPanel
    {
        public void paint(final Graphics g) {
            super.paint(g);
            g.setFont(WFApplet.HELV);
            g.setColor(Color.black);
            if (Kepler1.this.cbE.isSelected()) {
                g.drawOval(100, 160 - Kepler1.this.bPix, 240, 2 * Kepler1.this.bPix);
                Kepler1.this.write(g, Kepler1.this.text(16), 347, 164, -1);
                Kepler1.this.write(g, Kepler1.this.text(17), 93, 164, 1);
            }
            if (Kepler1.this.cbA.isSelected()) {
                g.drawLine(100, 160, 340, 160);
                g.drawLine(220, 160 - Kepler1.this.bPix, 220, 160 + Kepler1.this.bPix);
            }
            Kepler1.this.sun(g);
            Kepler1.this.planet(g);
            Kepler1.this.scale(g);
        }
    }
}
