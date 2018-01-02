import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar extends JComponent implements g
{
    public final int a = 0;
    public final int b = 1;
    public final int c = 2;
    private long d;
    private long e;
    private int f;
    private int g;
    private int h;
    private Color i;
    private Color j;
    private Color k;
    private String l;
    
    public ar(final long n, final int n2, final int n3) {
        this.a(n, n2, n3, 0);
    }
    
    private void a(final long d, final int f, final int g, final int h) {
        this.d = d;
        this.e = 0L;
        this.g = g;
        this.f = f;
        this.setSize(this.getPreferredSize());
        this.h = h;
        this.i = new Color(50, 200, 255);
        this.j = Color.gray;
        this.k = Color.darkGray;
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.f, this.g);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getSize() {
        return this.getPreferredSize();
    }
    
    public void b(final long e) {
        this.e = e;
        this.setToolTipText(this.a());
        this.repaint();
    }
    
    public void a(final Color i, final Color j, final Color k) {
        if (i != null) {
            this.i = i;
        }
        if (j != null) {
            this.j = j;
        }
        if (k != null) {
            this.k = k;
        }
    }
    
    public String a() {
        if (this.l == null) {
            return String.valueOf((int)(this.e * 100L / this.d)) + "%";
        }
        return this.l;
    }
    
    public String b() {
        return String.valueOf((int)(this.e * 100L / this.d)) + "%";
    }
    
    public String c() {
        return "(" + this.e + " of " + this.d + ")";
    }
    
    public void a(final String l) {
        this.l = l;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.i);
        graphics.fillRect(0, 0, (int)(this.e * this.f / this.d), this.g);
        graphics.setColor(this.j);
        graphics.drawRect(0, 0, this.f - 1, this.g - 1);
        graphics.setColor(this.k);
        if (this.h == 1) {
            this.a(this.b(), graphics, this.f, this.g);
        }
        else if (this.h == 2) {
            this.a(this.c(), graphics, this.f, this.g);
        }
    }
    
    private void a(final String s, final Graphics graphics, final int n, final int n2) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (fontMetrics == null) {
            return;
        }
        graphics.drawString(s, (n - fontMetrics.stringWidth(s)) / 2, (n2 - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
    }
}
