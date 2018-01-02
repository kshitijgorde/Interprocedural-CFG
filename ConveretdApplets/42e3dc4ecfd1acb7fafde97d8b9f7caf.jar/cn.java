import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class cn extends JComponent implements MouseListener
{
    private Image a;
    private Image b;
    private Image c;
    private int d;
    private Dimension e;
    private String f;
    
    public cn() {
        this.d = -1;
        this.e = new Dimension();
        this.f = "";
        this.c();
    }
    
    public cn(final Image image, final Image image2, final int n) {
        this.d = -1;
        this.e = new Dimension();
        this.f = "";
        this.c(image);
        this.a(image2);
        this.a(n);
        this.c();
    }
    
    public void a(final Image a) {
        this.a = a;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public String getToolTipText() {
        return this.f;
    }
    
    public void setToolTipText(final String f) {
        this.f = f;
    }
    
    public void b(final Image b) {
        this.b = b;
    }
    
    public void c(final Image c) {
        this.c = c;
        final Dimension size = new Dimension(this.c.getWidth(this), this.c.getHeight(this));
        if (size.height > 0) {
            this.setSize(size);
        }
    }
    
    public void a() {
        if (this.d != 0) {
            this.a(2);
            this.repaint();
        }
    }
    
    public void b() {
        if (this.d != 0) {
            this.a(1);
            this.repaint();
        }
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
        return this.e;
    }
    
    public void setPreferredSize(final Dimension e) {
        this.setSize(this.e = e);
    }
    
    public void setMaximumSize(final Dimension e) {
        this.setSize(this.e = e);
    }
    
    public Dimension getMaximumSize() {
        return this.e;
    }
    
    public Dimension getMinimumSize() {
        return this.e;
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.e = new Dimension(n, n2);
    }
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        this.e = dimension;
    }
    
    public void paint(final Graphics graphics) {
        switch (this.d) {
            case 0: {
                graphics.drawImage(this.b, 0, 0, this);
                break;
            }
            case 1: {
                graphics.drawImage(this.c, 0, 0, this);
                break;
            }
            case 2: {
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
    
    private void c() {
        this.setEnabled(true);
        this.setForeground(Color.black);
        this.addMouseListener(this);
        this.setBackground(Color.white);
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
        this.a();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.b();
    }
}
