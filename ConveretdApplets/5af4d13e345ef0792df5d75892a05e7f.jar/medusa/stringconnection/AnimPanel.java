// 
// Decompiled by Procyon v0.5.30
// 

package medusa.stringconnection;

import java.awt.Color;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import medusa.MedusaFrame;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class AnimPanel extends JPanel implements Runnable
{
    BufferedImage image;
    int anchorX;
    int anchorY;
    String text;
    Font nodeFont;
    double rotation;
    Thread spinThread;
    
    public AnimPanel() {
        this("/medusa/images/logo_still_p.gif");
    }
    
    public AnimPanel(final String image, final String text) {
        this.image = null;
        this.nodeFont = new Font("TimesNewRoman", 2, 28);
        this.rotation = 0.0;
        this.setImage(image);
        this.text = text;
        this.init();
    }
    
    public AnimPanel(final String image) {
        this.image = null;
        this.nodeFont = new Font("TimesNewRoman", 2, 28);
        this.rotation = 0.0;
        System.out.println("sending " + image);
        this.setImage(image);
        this.init();
    }
    
    private void init() {
        this.setBackground(MedusaFrame.STRINGCOLOR);
        this.setOpaque(true);
        this.anchorX = this.image.getWidth(this) / 2;
        this.anchorY = this.image.getHeight(this) / 2;
        this.setPreferredSize(new Dimension(this.image.getWidth(this), this.image.getHeight(this)));
    }
    
    private void setImage(final String s) {
        try {
            System.out.println(s);
            this.image = ImageIO.read(this.getClass().getResource(s));
        }
        catch (Exception ex) {
            this.image = null;
            ex.printStackTrace();
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        this.clear(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final AffineTransform rotateInstance = AffineTransform.getRotateInstance(3.141592653589793 * Math.sin(this.rotation), this.anchorX, this.anchorY);
        if (this.image != null) {
            graphics2D.drawImage(this.image, rotateInstance, this);
        }
        graphics2D.setColor(Color.yellow);
        graphics2D.setFont(this.nodeFont);
        if (this.text != null) {
            graphics2D.drawString(this.text, 5, this.image.getHeight(this));
        }
    }
    
    protected void clear(final Graphics graphics) {
        super.paintComponent(graphics);
    }
    
    public void start() {
        if (this.spinThread == null) {
            this.spinThread = new Thread(this);
        }
        this.spinThread.start();
    }
    
    public void run() {
        while (this.spinThread == Thread.currentThread()) {
            this.rotation += 0.02;
            this.repaint();
            try {
                Thread.sleep(30L);
                continue;
            }
            catch (InterruptedException ex) {}
            break;
        }
    }
    
    public void stop() {
        this.rotation = 0.0;
        if (this.spinThread != null) {
            this.spinThread = null;
        }
    }
}
