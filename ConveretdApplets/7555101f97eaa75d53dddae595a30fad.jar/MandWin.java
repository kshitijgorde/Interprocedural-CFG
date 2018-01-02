import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MandWin extends Panel
{
    Label xposlbl;
    Label yposlbl;
    double range;
    double xc;
    double yc;
    int xres;
    int yres;
    
    int PointMand(final double n, final double n2, final int n3) {
        double n5;
        double n4 = n5 = 0.0;
        int i;
        for (i = 0; i < n3; ++i) {
            final double n6 = n5 * n5;
            final double n7 = n4 * n4;
            if (n6 + n7 >= 4.0) {
                break;
            }
            final double n8 = 2.0 * n5 * n4;
            n5 = n6 - n7 + n;
            n4 = n8 + n2;
        }
        return i;
    }
    
    public void update(final Graphics graphics) {
        final float n = (float)this.xc;
        final float n2 = (float)this.yc;
        final float n3 = (float)this.range;
        new StringBuffer().append("X:").append(n).append("  Y:").append(n2).append("   Range:").append(n3).toString();
        this.resize(420, 420);
        graphics.clearRect(0, 0, 420, 420);
        graphics.drawString("X:" + n + "  Y:" + n2, 155, 350);
        graphics.drawString("Range:" + n3, 155, 365);
        int n4;
        if (this.xres <= 64) {
            n4 = 118;
        }
        else {
            n4 = 1;
        }
        int n5;
        if (this.yres <= 64) {
            n5 = 158;
        }
        else {
            n5 = 0;
        }
        final double n6 = this.xc - this.range / 2.0;
        final double n7 = this.yc + this.range / 2.0;
        final double n8 = this.range / this.yres;
        double n9 = n7;
        int pointMand = 0;
        int n10 = -1;
        final Color[] array = { Color.black, Color.red, Color.blue, Color.green, Color.yellow, Color.magenta, Color.cyan, Color.orange };
        graphics.setColor(Color.red);
        graphics.drawRect(n4 - 1, n5 - 1, this.xres + 2, this.yres + 2);
        graphics.setColor(array[0]);
        graphics.fillRect(n4, n5, this.xres, this.yres);
        for (int i = 0; i < this.yres; ++i) {
            double n11 = n6;
            int n12 = 0;
            int j;
            for (j = 0; j < this.xres; ++j) {
                pointMand = this.PointMand(n11, n9, 63);
                if (pointMand != n10) {
                    n10 = pointMand;
                    graphics.drawLine(n12 + n4, i + n5, j + n4, i + n5);
                    graphics.setColor(array[pointMand & 0x7]);
                    n12 = j;
                }
                n11 += n8;
            }
            graphics.setColor(array[pointMand & 0x7]);
            graphics.drawLine(n12 + n4, i + n5, j + n4, i + n5);
            n9 -= n8;
        }
    }
    
    public boolean action(final Event event, final Object o) {
        final double range = this.range / 2.0;
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals("NW")) {
                this.xc -= range;
                this.yc += range;
                this.repaint();
            }
            else if (s.equals("N")) {
                this.yc += range;
                this.repaint();
            }
            else if (s.equals("NE")) {
                this.xc += range;
                this.yc += range;
                this.repaint();
            }
            else if (s.equals("W")) {
                this.xc -= range;
                this.repaint();
            }
            else if (s.equals("E")) {
                this.xc += range;
                this.repaint();
            }
            else if (s.equals("SW")) {
                this.xc -= range;
                this.yc -= range;
                this.repaint();
            }
            else if (s.equals("S")) {
                this.yc -= range;
                this.repaint();
            }
            else if (s.equals("SE")) {
                this.xc += range;
                this.yc -= range;
                this.repaint();
            }
            else if (s.equals("In")) {
                this.range = range;
                this.repaint();
            }
            else if (s.equals("Out")) {
                this.range *= 2.0;
                this.repaint();
            }
            else if (s.equals("Big")) {
                this.xres = 400;
                this.yres = 300;
                this.repaint();
            }
            else if (s.equals("Small")) {
                this.xres = 64;
                this.yres = 64;
                this.repaint();
            }
        }
        else if (event.id == 201) {
            this.hide();
        }
        return true;
    }
    
    MandWin() {
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 3));
        panel.add(new Button("NW"));
        panel.add(new Button("N"));
        panel.add(new Button("NE"));
        panel.add(new Button("W"));
        panel.add(new Button("In"));
        panel.add(new Button("E"));
        panel.add(new Button("SW"));
        panel.add(new Button("S"));
        panel.add(new Button("SE"));
        panel.add(new Button("Small"));
        panel.add(new Button("Out"));
        panel.add(new Button("Big"));
        this.resize(420, 420);
        this.setLayout(null);
        this.add(panel);
        panel.resize(150, 110);
        panel.move(0, 310);
        this.range = 0.25;
        this.xc = 0.0;
        this.yc = 0.85;
        this.xres = 64;
        this.yres = 64;
    }
}
