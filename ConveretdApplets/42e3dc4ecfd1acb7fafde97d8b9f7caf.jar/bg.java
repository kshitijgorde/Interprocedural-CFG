import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class bg extends Panel
{
    private static final Toolkit a;
    private Image b;
    private Dimension c;
    private String d;
    private int e;
    private int f;
    
    static {
        a = Toolkit.getDefaultToolkit();
    }
    
    public void a(final int e, final int f, final Color foreground, final Font font, final String d) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.setFont(font);
        this.setForeground(foreground);
    }
    
    public String a() {
        return this.d;
    }
    
    public Dimension getPreferredSize() {
        return this.c;
    }
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        this.c = dimension;
        this.b();
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.c = new Dimension(n, n2);
        this.b();
    }
    
    public bg() {
        this.b = null;
        this.c = new Dimension(320, 240);
        this.e = 0;
        this.f = 0;
        this.setSize(240, 180);
        this.setBackground(Color.white);
    }
    
    public bg(final Image b, final int n, final int n2) {
        this.b = null;
        this.c = new Dimension(320, 240);
        this.e = 0;
        this.f = 0;
        this.setSize(n, n2);
        this.setBackground(Color.white);
        this.b = b;
        this.b();
    }
    
    public void a(final Image b) {
        this.b = b;
        this.b();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        this.b = bg.a.createImage(array, n, n2);
        this.b();
    }
    
    public void b() {
        try {
            if (this.b != null && this.b.getWidth(null) != this.c.width) {
                this.b = this.b.getScaledInstance(this.c.width, this.c.height, 2);
            }
            this.repaint(0L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void c() {
        this.d = null;
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.b != null) {
                graphics.drawImage(this.b, 0, 0, this);
            }
            if (this.d != null) {
                graphics.drawString(this.d, this.e, this.f);
            }
            super.paint(graphics);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
