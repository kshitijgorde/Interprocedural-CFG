import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PieChart extends Applet
{
    String title;
    Font font;
    FontMetrics fontMetrics;
    int titleHeight;
    int columns;
    int[] values;
    Color[] colors;
    String[] labels;
    float[] percent;
    float[] angle;
    int maxLabelWidth;
    int maxValueWidth;
    int max;
    int strWidth;
    boolean showLabel;
    boolean showPercent;
    int lx;
    int ly;
    int cx;
    int cy;
    
    public PieChart() {
        this.titleHeight = 15;
        this.maxLabelWidth = 0;
        this.maxValueWidth = 0;
        this.max = 0;
        this.strWidth = 0;
        this.showLabel = true;
        this.showPercent = true;
        this.lx = 0;
        this.ly = 0;
        this.cx = 0;
        this.cy = 0;
    }
    
    private void adjustLabel(final int n) {
        if (this.lx > this.cx && this.ly < this.cy) {
            this.lx += 5;
            this.ly -= 5;
        }
        if (this.lx > this.cx && this.ly > this.cy) {
            this.lx += 5;
            this.ly += 10;
        }
        if (this.lx < this.cx && this.ly > this.cy) {
            this.strWidth = this.fontMetrics.stringWidth(this.labels[n]);
            this.lx -= this.strWidth + 5;
            if (this.lx < 0) {
                this.lx = 0;
            }
        }
        if (this.lx < this.cx && this.ly < this.cy) {
            this.strWidth = this.fontMetrics.stringWidth(this.labels[n]);
            this.lx -= this.strWidth + 5;
            if (this.lx < 0) {
                this.lx = 0;
            }
        }
    }
    
    public synchronized void init() {
        this.font = new Font("Sanserif", 1, 12);
        this.fontMetrics = this.getFontMetrics(this.font);
        final String parameter = this.getParameter("bgcolor");
        if (parameter == null) {
            this.setBackground(Color.white);
        }
        else if (parameter.equals("red")) {
            this.setBackground(Color.red);
        }
        else if (parameter.equals("green")) {
            this.setBackground(Color.green);
        }
        else if (parameter.equals("blue")) {
            this.setBackground(Color.blue);
        }
        else if (parameter.equals("pink")) {
            this.setBackground(Color.pink);
        }
        else if (parameter.equals("orange")) {
            this.setBackground(Color.orange);
        }
        else if (parameter.equals("magenta")) {
            this.setBackground(Color.magenta);
        }
        else if (parameter.equals("cyan")) {
            this.setBackground(Color.cyan);
        }
        else if (parameter.equals("white")) {
            this.setBackground(Color.white);
        }
        else if (parameter.equals("yellow")) {
            this.setBackground(Color.yellow);
        }
        else if (parameter.equals("gray")) {
            this.setBackground(Color.gray);
        }
        else if (parameter.equals("darkGray")) {
            this.setBackground(Color.darkGray);
        }
        else {
            this.setBackground(Color.white);
        }
        this.title = this.getParameter("title");
        if (this.title == null) {
            this.title = "Pie Chart";
        }
        final String parameter2 = this.getParameter("columns");
        if (parameter2 == null) {
            this.columns = 5;
        }
        else {
            this.columns = Integer.parseInt(parameter2);
        }
        final String parameter3 = this.getParameter("showlabel");
        if (parameter3 == null) {
            this.showLabel = true;
        }
        else {
            if (parameter3.equalsIgnoreCase("YES")) {
                this.showLabel = true;
            }
            if (parameter3.equalsIgnoreCase("NO")) {
                this.showLabel = false;
            }
            else {
                this.showLabel = true;
            }
        }
        final String parameter4 = this.getParameter("showpercent");
        if (parameter4 == null) {
            this.showPercent = true;
        }
        else {
            if (parameter4.equalsIgnoreCase("YES")) {
                this.showPercent = true;
            }
            if (parameter4.equalsIgnoreCase("NO")) {
                this.showPercent = false;
            }
            else {
                this.showPercent = true;
            }
        }
        this.values = new int[this.columns];
        this.colors = new Color[this.columns];
        this.labels = new String[this.columns];
        this.percent = new float[this.columns];
        this.angle = new float[this.columns];
        float n = 0.0f;
        for (int i = 0; i < this.columns; ++i) {
            final String parameter5 = this.getParameter("Pvalue" + (i + 1));
            if (parameter5 != null) {
                try {
                    this.values[i] = Integer.parseInt(parameter5);
                }
                catch (NumberFormatException ex) {
                    this.values[i] = 0;
                }
            }
            n += this.values[i];
            if (this.values[i] > this.max) {
                this.max = this.values[i];
            }
            final String parameter6 = this.getParameter("Plabel" + (i + 1));
            this.labels[i] = ((parameter6 == null) ? "" : parameter6);
            this.maxLabelWidth = Math.max(this.fontMetrics.stringWidth(this.labels[i]), this.maxLabelWidth);
            final String parameter7 = this.getParameter("Pcolor" + (i + 1));
            if (parameter7 != null) {
                if (parameter7.equals("red")) {
                    this.colors[i] = Color.red;
                }
                else if (parameter7.equals("green")) {
                    this.colors[i] = Color.green;
                }
                else if (parameter7.equals("blue")) {
                    this.colors[i] = Color.blue;
                }
                else if (parameter7.equals("pink")) {
                    this.colors[i] = Color.pink;
                }
                else if (parameter7.equals("orange")) {
                    this.colors[i] = Color.orange;
                }
                else if (parameter7.equals("magenta")) {
                    this.colors[i] = Color.magenta;
                }
                else if (parameter7.equals("cyan")) {
                    this.colors[i] = Color.cyan;
                }
                else if (parameter7.equals("white")) {
                    this.colors[i] = Color.white;
                }
                else if (parameter7.equals("yellow")) {
                    this.colors[i] = Color.yellow;
                }
                else if (parameter7.equals("gray")) {
                    this.colors[i] = Color.gray;
                }
                else if (parameter7.equals("darkGray")) {
                    this.colors[i] = Color.darkGray;
                }
                else {
                    this.colors[i] = Color.gray;
                }
            }
            else {
                this.colors[i] = Color.gray;
            }
        }
        final float n2 = 100.0f / n;
        for (int j = 0; j < this.columns; ++j) {
            this.percent[j] = this.values[j] * n2;
            this.angle[j] = (float)(this.percent[j] * 3.6);
        }
    }
    
    public synchronized void paint(final Graphics graphics) {
        final int min;
        final int n = min = Math.min(this.getSize().width - 100, this.getSize().height - 100);
        int n3;
        final int n2 = n3 = 50;
        if (this.getSize().width > min) {
            n3 = (this.getSize().width - min) / 2;
        }
        this.cx = n3 + min / 2;
        this.cy = n2 + n / 2;
        final int n4 = min / 2;
        this.strWidth = this.fontMetrics.stringWidth(this.title);
        graphics.setFont(new Font("Sanserif", 1, 16));
        graphics.setColor(Color.red);
        graphics.drawString(this.title, (this.getSize().width - this.strWidth) / 2, 15);
        graphics.setFont(this.font);
        int n5 = 90;
        int n6 = 0;
        int n7 = (int)(this.angle[0] / 2.0f);
        int i = 0;
        while (i < this.columns) {
            final int round = Math.round(this.angle[i]);
            graphics.setColor(this.colors[i]);
            if (i == this.columns - 1) {
                final int n8 = 360 - n6;
                graphics.fillArc(n3, n2, min, n, n5, -n8);
                graphics.setColor(Color.black);
                graphics.drawArc(n3, n2, min, n, n5, -n8);
                if (this.showLabel) {
                    this.lx = (int)(this.cx + n4 * Math.cos(n7 * 3.14f / 180.0f - 1.57f));
                    this.ly = (int)(this.cy + n4 * Math.sin(n7 * 3.14f / 180.0f - 1.57f));
                    this.adjustLabel(i);
                    graphics.drawString(this.labels[i], this.lx, this.ly);
                }
                if (this.showPercent) {
                    graphics.drawString(String.valueOf(String.valueOf(Math.round(this.percent[i]))) + "%", (int)(this.cx + n4 * 2 / 3 * Math.cos(n7 * 3.14f / 180.0f - 1.57f)), (int)(this.cy + n4 * 2 / 3 * Math.sin(n7 * 3.14f / 180.0f - 1.57f)));
                    break;
                }
                break;
            }
            else {
                graphics.fillArc(n3, n2, min, n, n5, -round);
                graphics.setColor(Color.black);
                graphics.drawArc(n3, n2, min, n, n5, -round);
                n6 += round;
                graphics.drawLine(this.cx, this.cy, (int)(this.cx + n4 * Math.cos(n6 * 3.14f / 180.0f - 1.57f)), (int)(this.cy + n4 * Math.sin(n6 * 3.14f / 180.0f - 1.57f)));
                if (this.showLabel) {
                    this.lx = (int)(this.cx + n4 * Math.cos(n7 * 3.14f / 180.0f - 1.57f));
                    this.ly = (int)(this.cy + n4 * Math.sin(n7 * 3.14f / 180.0f - 1.57f));
                    this.adjustLabel(i);
                    graphics.drawString(this.labels[i], this.lx, this.ly);
                }
                if (this.showPercent) {
                    final int n9 = (int)(this.cx + n4 * 2 / 3 * Math.cos(n7 * 3.14f / 180.0f - 1.57f));
                    final int n10 = (int)(this.cy + n4 * 2 / 3 * Math.sin(n7 * 3.14f / 180.0f - 1.57f));
                    this.strWidth = this.fontMetrics.stringWidth(String.valueOf(Math.round(this.percent[i])) + "%");
                    graphics.drawString(String.valueOf(String.valueOf(Math.round(this.percent[i]))) + "%", n9 - this.strWidth / 2, n10);
                }
                n7 += (int)(this.angle[i] / 2.0f + this.angle[i + 1] / 2.0f);
                n5 += -round;
                ++i;
            }
        }
        graphics.setColor(Color.black);
        graphics.drawLine(this.cx, this.cy, this.cx, this.cy - n4);
    }
}
