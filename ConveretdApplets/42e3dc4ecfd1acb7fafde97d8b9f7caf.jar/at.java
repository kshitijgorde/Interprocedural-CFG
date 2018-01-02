import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Image;
import java.awt.PopupMenu;

// 
// Decompiled by Procyon v0.5.30
// 

public class at extends au
{
    private PopupMenu a;
    private int b;
    private Image c;
    private Image d;
    
    public at(final z z, final String s, final boolean b, final int n) {
        super(z, s, b);
        this.b = 0;
        this.c = ImageRes.bi;
        this.d = ImageRes.bm;
        this.a(n);
    }
    
    public int a() {
        return 23;
    }
    
    public PopupMenu b() {
        if (this.a == null) {
            this.a = new PopupMenu();
        }
        return this.a;
    }
    
    public void a(final int b) {
        switch (this.b) {
            case 0: {
                this.c = ImageRes.bi;
                break;
            }
            case 1: {
                this.c = ImageRes.bj;
                break;
            }
            case 2: {
                this.d = ImageRes.bm;
                break;
            }
            case 3: {
                this.d = ImageRes.bn;
                break;
            }
        }
        switch (this.b = b) {
            case 0: {
                this.a(new aw());
                this.c = ImageRes.bg;
                break;
            }
            case 1: {
                this.a(new ax());
                this.c = ImageRes.bh;
                break;
            }
            case 2: {
                this.a(new as());
                this.d = ImageRes.bk;
                break;
            }
            case 3: {
                this.a(new ay());
                this.d = ImageRes.bl;
                break;
            }
        }
    }
    
    public void a(final int n, final int n2) {
        if (n > this.k() & n <= this.k() + 132 & (n2 > this.l() + 15 & n2 <= this.l() + this.a())) {
            if (this.b == 1) {
                this.a(0);
            }
            else {
                this.a(1);
            }
        }
        if (n > this.k() + 132 & n <= this.k() + this.g().width & (n2 > this.l() + 15 & n2 <= this.l() + this.a())) {
            if (this.b == 3) {
                this.a(2);
            }
            else {
                this.a(3);
            }
        }
        this.c();
    }
    
    public void a(final Graphics graphics, final Component component, final int n, final int n2) {
        if (this.e()) {
            graphics.drawImage(this.c, n + 1, n2 + 15, component);
            graphics.drawImage(this.d, n + 132, n2 + 15, component);
        }
    }
}
