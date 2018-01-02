import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class w extends JPanel implements MouseListener
{
    private boolean a;
    private String b;
    private Image c;
    
    public boolean d() {
        return this.a;
    }
    
    public void a(final boolean a) {
        this.a = a;
        h.f().a();
        h.f().e().validate();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getX() > 183 & mouseEvent.getY() <= 16) {
            if (!this.d()) {
                this.a(true);
            }
            else {
                this.a(false);
            }
            this.repaint(0L);
        }
    }
    
    public w(final String b, final Image c) {
        this.a = false;
        this.b = b;
        this.c = c;
        this.setOpaque(true);
        this.setLayout(null);
        this.addMouseListener(this);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(dj.b);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.setColor(dj.g);
        graphics.fillRect(0, 0, 153, 15);
        graphics.drawImage(this.c, 2, 1, this);
        graphics.setColor(dj.h);
        graphics.fillRect(153, 0, 15, 15);
        graphics.setColor(dj.i);
        graphics.fillRect(168, 0, 15, 15);
        graphics.setColor(dj.j);
        graphics.fillRect(183, 0, 15, 15);
        graphics.setColor(dj.e);
        graphics.setFont(dj.ak);
        graphics.drawString(this.b, 16, 12);
        graphics.setColor(dj.f);
        graphics.drawRect(0, 0, this.getWidth() - 1, 14);
        if (this.d()) {
            graphics.drawImage(ImageRes.e, 184, 2, this);
        }
        else {
            graphics.drawImage(ImageRes.d, 184, 2, this);
        }
    }
}
