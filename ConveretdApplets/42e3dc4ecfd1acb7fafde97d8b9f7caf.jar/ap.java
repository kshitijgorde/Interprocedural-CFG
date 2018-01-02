import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ap extends JPanel implements MouseListener
{
    private String a;
    private int b;
    private Image c;
    private Color d;
    public Dimension e;
    
    public ap(final String s) {
        this.c = ImageRes.a9;
        this.d = dj.y;
        this.a = s + " ";
        this.b = Toolkit.getDefaultToolkit().getFontMetrics(dj.ak).stringWidth(this.a);
        this.setBackground(dj.w);
        super.setSize(this.e = new Dimension(this.b + 12, 12));
        this.addMouseListener(this);
    }
    
    public Dimension getSize() {
        return this.e;
    }
    
    public Dimension getPreferredSize() {
        return this.e;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.c = ImageRes.ba;
        this.d = dj.z;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.c = ImageRes.a9;
        this.d = dj.y;
        this.repaint();
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(this.d);
        graphics.setFont(dj.ak);
        graphics.drawString(this.a, 0, 9);
        graphics.drawImage(this.c, this.b, 0, this);
    }
}
