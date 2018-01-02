import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class an extends JComponent implements MouseListener
{
    private Image a;
    private Image b;
    private Image c;
    private Image d;
    private Image e;
    private int f;
    private Dimension g;
    private boolean h;
    
    public void a(final Image b) {
        this.b = b;
    }
    
    public void b(final Image d) {
        this.d = d;
    }
    
    public void c(final Image c) {
        this.c = c;
    }
    
    public void d(final Image a) {
        this.a = a;
    }
    
    public an(final Image image, final Image image2, final Image image3, final Image image4, final Image image5, final int n) {
        this.f = 1;
        this.g = new Dimension();
        this.c(image);
        this.b(image2);
        this.a(image3);
        this.d(image4);
        this.e(image5);
        this.setSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        this.a(n);
        this.addMouseListener(this);
    }
    
    public void a(final int f) {
        this.f = f;
    }
    
    public void e(final Image e) {
        this.e = e;
    }
    
    public void a() {
        if (this.f != 0) {
            this.a(4);
            this.repaint();
        }
    }
    
    public void b() {
        if (this.f != 0) {
            if (this.e()) {
                this.a(2);
            }
            else {
                this.a(1);
            }
            this.repaint();
        }
    }
    
    public void c() {
        if (this.f != 0) {
            this.a(3);
            this.repaint();
        }
    }
    
    public void d() {
        if (this.f != 0) {
            this.a(2);
            this.h = true;
            this.repaint();
        }
    }
    
    public boolean e() {
        return this.h;
    }
    
    public void disable() {
        this.a(0);
        this.repaint();
    }
    
    public void enable() {
        this.a(1);
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        if (this.c != null) {
            this.g.setSize(this.c.getWidth(this), this.c.getHeight(this));
        }
        return this.g;
    }
    
    public void setPreferredSize(final Dimension g) {
        this.setSize(this.g = g);
    }
    
    public void setMaximumSize(final Dimension g) {
        this.setSize(this.g = g);
    }
    
    public Dimension getMaximumSize() {
        return this.g;
    }
    
    public Dimension getMinimumSize() {
        return this.g;
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.g = new Dimension(n, n2);
    }
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        this.g = dimension;
    }
    
    public void paint(final Graphics graphics) {
        switch (this.f) {
            case 0: {
                graphics.drawImage(this.e, 0, 0, this);
                break;
            }
            case 1: {
                graphics.drawImage(this.c, 0, 0, this);
                break;
            }
            case 2: {
                graphics.drawImage(this.d, 0, 0, this);
                break;
            }
            case 3: {
                graphics.drawImage(this.b, 0, 0, this);
                break;
            }
            case 4: {
                graphics.drawImage(this.a, 0, 0, this);
                break;
            }
            default: {
                final Dimension size = this.getSize();
                graphics.drawRect(0, 0, size.width - 1, size.height - 1);
                break;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.b();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.c();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.b();
    }
}
