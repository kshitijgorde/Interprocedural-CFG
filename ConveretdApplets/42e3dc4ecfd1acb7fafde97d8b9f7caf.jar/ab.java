import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ab extends JPanel implements ac, MouseListener, MouseMotionListener
{
    private int a;
    private int b;
    private z c;
    private final ad d;
    private int e;
    private Image f;
    private Graphics g;
    private aa h;
    private int i;
    
    public void a(final int e) {
        this.e = e;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final av a = this.a(mouseEvent.getX(), mouseEvent.getY());
        if (a != null) {
            this.setToolTipText(a.b(mouseEvent.getX(), mouseEvent.getY(), this));
            if (this.getToolTipText() == null) {
                this.setToolTipText("Loading " + a.r() + "...");
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public av[] a() {
        return this.d.a(this.c);
    }
    
    public ab(final z c, final aa h) {
        this.d = new ad();
        this.i = 1;
        this.c = c;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(h);
        this.h = h;
    }
    
    public int b() {
        final av[] b = this.d.b();
        int n = 0;
        for (int i = 0; i < b.length; ++i) {
            if (!b[i].n()) {
                n = b[i].g().height + n + this.i;
            }
        }
        return n;
    }
    
    public void a(final Dimension dimension) {
        this.a = dimension.width;
        this.b = dimension.height;
        if (this.g != null) {
            this.g.dispose();
            this.g = null;
        }
        if (this.f != null) {
            this.f.flush();
            this.f = null;
        }
        this.f = this.createImage(this.a, this.b);
        this.g = this.f.getGraphics();
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Dimension size = this.getSize();
        if (size.height > 0) {
            try {
                if (this.a != size.width || this.b != size.height || this.f == null || this.g == null) {
                    this.a(size);
                }
                this.a(this.g, size);
                graphics.drawImage(this.f, 0, 0, this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.h.e = false;
    }
    
    public void a(final Graphics graphics, final Dimension dimension) {
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, dimension.width, dimension.height);
        final av[] a = this.a();
        for (int n = 0, n2 = 0; n2 < a.length && n + this.e <= dimension.height; ++n2) {
            if (!a[n2].n()) {
                if (n + this.e + a[n2].g().height > 0) {
                    a[n2].b(graphics, this, 1, n + this.e);
                }
                n = a[n2].g().height + n + this.i;
            }
        }
    }
    
    public void c() {
        this.h.a();
    }
    
    public synchronized boolean a(final av av) {
        this.d.a(av);
        av.a(this);
        this.c();
        return true;
    }
    
    public boolean a(final String s) {
        return this.d.b(s) != null;
    }
    
    public void b(final String s) {
        this.d.a(s);
        this.c();
    }
    
    public void d() {
        this.d.c();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private synchronized av a(final int n, final int n2) {
        final av[] a = this.a();
        av av = null;
        int n3 = 0;
        for (int i = 0; i < a.length; ++i) {
            if (!a[i].n()) {
                a[i].b(0, n3 + this.e);
                if (a[i].d(n, n2)) {
                    av = a[i];
                    break;
                }
                n3 = a[i].g().height + n3 + this.i;
            }
        }
        return av;
    }
    
    public av c(final String s) {
        return this.d.b(s);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final av a = this.a(mouseEvent.getX(), mouseEvent.getY());
        if (a != null) {
            if (0x4 == (mouseEvent.getModifiers() & 0x4)) {
                this.h.a(a.a(mouseEvent.getX(), mouseEvent.getY(), this.h), mouseEvent.getX(), mouseEvent.getY());
            }
            else {
                a.c(mouseEvent.getX(), mouseEvent.getY());
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
}
