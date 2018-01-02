import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class graphFrame extends Frame
{
    int[] D;
    double[] L;
    double[] P;
    int y0;
    int x0;
    int N;
    compute comp;
    String[] monthArray;
    Checkbox box;
    Checkbox box1;
    boolean fullmoon;
    boolean newmoon;
    
    public graphFrame(final String titleStr, final int n, final int[] DD, final double[] LL, final double[] PP) {
        this.D = new int[400];
        this.L = new double[400];
        this.P = new double[400];
        this.y0 = 490;
        this.x0 = 30;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.fullmoon = true;
        this.newmoon = true;
        this.setTitle(titleStr);
        this.comp = new compute();
        for (int i = 0; i < n; ++i) {
            this.L[i] = LL[i];
            this.D[i] = DD[i];
            this.P[i] = PP[i];
        }
        this.N = n;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.insets = new Insets(10, 20, 0, 0);
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        (this.box = new Checkbox("Show Full Moon")).setState(true);
        gbl.setConstraints(this.box, gbc);
        this.add(this.box);
        (this.box1 = new Checkbox("Show New Moon")).setState(true);
        gbl.setConstraints(this.box1, gbc);
        this.add(this.box1);
        gbc.gridy = 1;
        gbc.insets = new Insets(500, 0, 0, 0);
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target == this.box) {
            this.fullmoon ^= true;
            this.repaint();
        }
        if (evt.target == this.box1) {
            this.newmoon ^= true;
            this.repaint();
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        final int F = 18;
        for (int i = 1; i <= 24; ++i) {
            if (i % 5 == 0) {
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.lightGray);
            }
            g.drawLine(this.x0, this.y0 - i * F, this.x0 + 2 * this.D[this.N - 1], this.y0 - i * F);
            if (i % 5 == 0) {
                g.setColor(Color.black);
                g.drawString(String.valueOf(i), this.x0 - 15, this.y0 - i * F + 5);
            }
        }
        g.setColor(Color.black);
        g.drawString("Hours", this.x0 - 25, this.y0 - 415);
        final double jd0 = this.comp.Jul_Date(31, 12, 2010, 0.0);
        int date = 0;
        int date2 = 0;
        g.setColor(Color.black);
        if (this.fullmoon) {
            for (int j = 1; j < this.N - 1; ++j) {
                if (this.P[j] > 0.99) {
                    if (this.P[j] < this.P[j + 1]) {
                        g.drawOval(this.x0 + 2 * this.D[j + 1] - 4, this.y0 - (int)Math.round(F * this.L[j + 1]) - 4, 8, 8);
                    }
                    else if (this.P[j] < this.P[j - 1]) {
                        g.drawOval(this.x0 + 2 * this.D[j - 1] - 4, this.y0 - (int)Math.round(F * this.L[j - 1]) - 4, 8, 8);
                    }
                    else {
                        g.drawOval(this.x0 + 2 * this.D[j] - 4, this.y0 - (int)Math.round(F * this.L[j]) - 4, 8, 8);
                    }
                }
            }
        }
        if (this.newmoon) {
            for (int j = 1; j < this.N - 1; ++j) {
                if (this.P[j] < 0.01) {
                    g.setColor(Color.black);
                    if (this.P[j] > this.P[j + 1]) {
                        g.fillOval(this.x0 + 2 * this.D[j + 1] - 4, this.y0 - (int)Math.round(F * this.L[j + 1]) - 4, 8, 8);
                    }
                    else if (this.P[j] > this.P[j - 1]) {
                        g.fillOval(this.x0 + 2 * this.D[j - 1] - 4, this.y0 - (int)Math.round(F * this.L[j - 1]) - 4, 8, 8);
                    }
                    else {
                        g.fillOval(this.x0 + 2 * this.D[j] - 4, this.y0 - (int)Math.round(F * this.L[j]) - 4, 8, 8);
                    }
                }
            }
        }
        g.setColor(Color.black);
        for (int j = 0; j < 12; ++j) {
            g.drawString(this.monthArray[j], this.x0 + 23 + j * 60, this.y0 + 15);
        }
        for (int k = 0; k < this.N - 1; ++k) {
            final double jd2 = jd0 + this.D[k];
            date = (int)this.comp.caldat(1, jd2);
            final double jd3 = jd0 + this.D[k + 1];
            date2 = (int)this.comp.caldat(1, jd3);
            if (date2 < date) {
                g.setColor(Color.gray);
                g.drawLine(this.x0 + 2 * this.D[k], this.y0, this.x0 + 2 * this.D[k], this.y0 - 24 * F);
                g.setColor(Color.black);
            }
            if (date == 10 || date == 20) {
                g.setColor(Color.lightGray);
                g.drawLine(this.x0 + 2 * this.D[k], this.y0, this.x0 + 2 * this.D[k], this.y0 - 24 * F);
            }
            g.setColor(Color.blue);
            g.drawLine(this.x0 + 2 * this.D[k], this.y0 - (int)Math.round(F * this.L[k]), this.x0 + 2 * this.D[k + 1], this.y0 - (int)Math.round(F * this.L[k + 1]));
        }
        g.setColor(Color.red);
        g.drawLine(this.x0, this.y0, this.x0 + 2 * this.D[this.N - 1], this.y0);
        g.drawLine(this.x0, this.y0, this.x0, this.y0 - 24 * F);
        g.drawLine(this.x0 + 2 * this.D[this.N - 1], this.y0, this.x0 + 2 * this.D[this.N - 1], this.y0 - 24 * F);
    }
}
