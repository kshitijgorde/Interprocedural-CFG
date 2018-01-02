import java.awt.image.ImageObserver;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class x extends JPanel
{
    private String a;
    private int b;
    
    public x(final String s) {
        this.a = "  " + s + " ";
        this.b = Toolkit.getDefaultToolkit().getFontMetrics(dj.aj).stringWidth(this.a);
        this.setBackground(dj.w);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(dj.m);
        graphics.drawLine(0, 0, this.getWidth(), 0);
        graphics.drawImage(ImageRes.bo, this.getWidth() - 26 - this.b, 0, this);
        graphics.fillRect(this.getWidth() - 13 - this.b, 0, this.b, 14);
        graphics.setColor(dj.e);
        graphics.drawString(this.a, this.getWidth() - 13 - this.b, 12);
        graphics.drawImage(ImageRes.bp, this.getWidth() - 13, 0, this);
    }
}
