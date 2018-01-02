import java.util.Date;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Dimension;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnalogClock
{
    private ParamParser param;
    private Dimension size;
    private FontMetrics fm;
    private Point start;
    private Point cen;
    private int rad;
    private int diam;
    private Point[] cn;
    private String[] ns;
    private int sr;
    private int mr;
    private int hr;
    private double sa;
    private double sda;
    private double mda;
    private double hda;
    private Color ccolor;
    private Color ncolor;
    private Color shcolor;
    private Color mhcolor;
    private Color hhcolor;
    private Color bgcolor;
    
    public AnalogClock(final Rectangle rectangle, final ParamParser param, final FontMetrics fm) {
        this.sa = 1.5707963267948966;
        this.sda = 0.10471975511965977;
        this.mda = this.sda / 60.0;
        this.hda = this.mda / 12.0;
        this.param = param;
        this.fm = fm;
        this.ccolor = param.parseColor("ccolor", Color.lightGray);
        this.ncolor = param.parseColor("ncolor", Color.black);
        this.shcolor = param.parseColor("shcolor", Color.red);
        this.mhcolor = param.parseColor("mhcolor", Color.blue);
        this.hhcolor = param.parseColor("hhcolor", Color.green);
        this.bgcolor = param.parseColor("bgcolor", Color.white);
        this.size = new Dimension(rectangle.width, rectangle.height);
        this.diam = Math.min(rectangle.width, rectangle.height);
        this.rad = this.diam / 2;
        this.start = new Point(rectangle.x, rectangle.y);
        this.cen = new Point(this.start.x + this.rad, this.start.y + this.rad);
        this.sr = this.rad;
        this.mr = (int)(0.9 * this.rad);
        this.hr = (int)(0.7 * this.rad);
        this.setClockNumbers(param.parseInt("nradius", 80) * this.rad / 100);
    }
    
    public void draw(final Graphics graphics) {
        final Date date = new Date();
        final int seconds = date.getSeconds();
        final int minutes = date.getMinutes();
        final int n = date.getHours() % 12;
        final int n2 = minutes * 60;
        final int n3 = n * 60 * 60;
        graphics.setColor(this.bgcolor);
        graphics.fillRect(this.start.x, this.start.y, this.size.width, this.size.height);
        graphics.setColor(this.ccolor);
        graphics.fillOval(this.start.x, this.start.y, this.diam, this.diam);
        graphics.setColor(this.ncolor);
        graphics.drawOval(this.start.x, this.start.y, this.diam, this.diam);
        for (int i = 0; i < this.cn.length; ++i) {
            graphics.drawString(this.ns[i], this.cn[i].x, this.cn[i].y);
        }
        graphics.setColor(this.shcolor);
        graphics.drawLine(this.cen.x, this.cen.y, (int)(Math.cos(seconds * this.sda - this.sa) * this.sr + this.cen.x), (int)(Math.sin(seconds * this.sda - this.sa) * this.sr + this.cen.y));
        graphics.setColor(this.mhcolor);
        final int n4 = (int)(Math.cos((n2 + seconds) * this.mda - this.sa) * this.mr + this.cen.x);
        final int n5 = (int)(Math.sin((n2 + seconds) * this.mda - this.sa) * this.mr + this.cen.y);
        graphics.drawLine(this.cen.x, this.cen.y - 1, n4, n5);
        graphics.drawLine(this.cen.x - 1, this.cen.y, n4, n5);
        graphics.setColor(this.hhcolor);
        final int n6 = (int)(Math.cos((n3 + n2 + seconds) * this.hda - this.sa) * this.hr + this.cen.x);
        final int n7 = (int)(Math.sin((n3 + n2 + seconds) * this.hda - this.sa) * this.hr + this.cen.y);
        graphics.drawLine(this.cen.x, this.cen.y - 1, n6, n7);
        graphics.drawLine(this.cen.x - 1, this.cen.y, n6, n7);
    }
    
    private void setClockNumbers(final int n) {
        this.cn = new Point[12];
        (this.ns = new String[12])[0] = "12";
        for (int i = 1; i < this.cn.length; ++i) {
            this.ns[i] = Integer.toString(i);
        }
        final int maxAscent = this.fm.getMaxAscent();
        final int n2 = (this.fm.getMaxAscent() + this.fm.getMaxDescent()) / 2;
        for (int j = 0; j < this.cn.length; ++j) {
            this.cn[j] = new Point((int)(Math.cos(j * 0.5235987755982988 - this.sa) * n + this.cen.x) - this.fm.stringWidth(this.ns[j]) / 2, (int)(Math.sin(j * 0.5235987755982988 - this.sa) * n + this.cen.y) + maxAscent - n2);
        }
    }
}
