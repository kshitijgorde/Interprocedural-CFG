import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_bO extends JPanel
{
    public rp_ew a;
    private Dimension a;
    private Point a;
    rp_z a;
    protected rp_aJ a;
    private Image a;
    
    public rp_bO(final rp_aJ a) {
        this.a = null;
        this.a = new Dimension(0, 0);
        this.a = new Point(0, 0);
        this.a = null;
        this.a = null;
        this.a = a;
        final String c;
        if ((c = a.c("wmark_file")) != null) {
            this.a = a.a().a(c, true);
        }
    }
    
    public final rp_aJ a() {
        return this.a;
    }
    
    void a() {
    }
    
    public final Point a() {
        return new Point(this.a);
    }
    
    public final boolean a(final Point a) {
        ((rp_eV)(this.a = a)).a(this.a);
        this.a();
        return true;
    }
    
    public final Dimension a() {
        return new Dimension(this.a);
    }
    
    public final boolean a(final Dimension a) {
        if (this.a.equals(a)) {
            return false;
        }
        this.a = a;
        if (this.a != null) {
            this.a.a();
        }
        return true;
    }
    
    public final Dimension b() {
        final rp_ew a = this.a;
        return new Dimension(a.a * a.a.width, a.a * a.a.height);
    }
    
    public final void b() {
        if (this.a == null) {
            this.invalidate();
            this.a = new rp_ew(this.a.a(), new Rectangle(0, 0, this.getSize().width, this.getSize().height), this.a, 1000);
            this.setBackground(rp_aJ.b);
        }
    }
    
    public void paintComponent(final Graphics a) {
        super.paintComponent(a);
        this.a();
        final Dimension size = this.getSize();
        (this.a.a = a).setColor(rp_aJ.b);
        a.fillRect(0, 0, size.width, size.height);
        if (this.a != null) {
            final int width = this.a.getWidth(this);
            final int height = this.a.getHeight(this);
            final String a2 = this.a.a("wmark_pos", "CC");
            int n = 0;
            if (a2.charAt(0) == 'C') {
                n = Math.max(0, (size.width - width) / 2);
            }
            if (a2.charAt(0) == 'R') {
                n = Math.max(0, size.width - width);
            }
            int n2 = 0;
            if (a2.charAt(1) == 'C') {
                n2 = Math.max(0, (size.height - height) / 2);
            }
            if (a2.charAt(1) == 'B') {
                n2 = Math.max(0, size.height - height);
            }
            a.drawImage(this.a, n, n2, this);
        }
    }
    
    public final rp_ew a() {
        if (this.a == null) {
            this.b();
        }
        return this.a;
    }
    
    final boolean a(int max) {
        final Point a = this.a();
        final Dimension size = this.a.a.getSize();
        final int n = a.x + this.a.a * size.width / 2;
        final int n2 = a.y + this.a.a * size.height / 2;
        if (this.a.a(max)) {
            max = Math.max(0, n - this.a.a * size.width / 2);
            this.a(new Point(max, Math.max(0, n2 - this.a.a * size.height / 2)));
            this.a();
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean a() {
        return true;
    }
}
