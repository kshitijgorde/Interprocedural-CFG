import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class MSButton extends Canvas implements MouseListener
{
    private static final int WIDTH = 110;
    private static final int HEIGHT = 20;
    private static Image backgroundOut;
    private static Image backgroundOver;
    private static Color colorOut;
    private static Color colorOver;
    private Image gbuffer;
    private Graphics gbuf;
    private FontMetrics gfm;
    private MenuListener menuListener;
    private int number;
    private String text;
    private boolean inside;
    
    public static void init(final MoonStar moonStar, final Image image, final Color colorOut, final Color colorOver) {
        MSButton.backgroundOut = moonStar.createImage(110, 20);
        MSButton.backgroundOver = moonStar.createImage(110, 20);
        MSButton.backgroundOut.getGraphics().drawImage(image, 0, 0, 110, 20, 0, 0, 110, 20, moonStar);
        MSButton.backgroundOver.getGraphics().drawImage(image, 0, 0, 110, 20, 0, 20, 110, 40, moonStar);
        MSButton.colorOut = colorOut;
        MSButton.colorOver = colorOver;
    }
    
    public MSButton(final MenuListener menuListener, final int number, final String text) {
        this.inside = false;
        this.menuListener = menuListener;
        this.number = number;
        this.text = text;
        this.addMouseListener(this);
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics gbuf) {
        if (this.gbuffer == null) {
            try {
                this.gbuffer = this.createImage(110, 20);
                this.gbuf = this.gbuffer.getGraphics();
            }
            catch (Exception ex) {
                this.gbuf = gbuf;
                this.gbuffer = null;
            }
            this.gbuf.setFont(new Font("Ariel", 1, 15));
            this.gfm = this.gbuf.getFontMetrics();
        }
        if (this.inside) {
            this.gbuf.drawImage(MSButton.backgroundOver, 0, 0, 110, 20, this);
            this.gbuf.setColor(MSButton.colorOver);
        }
        else {
            this.gbuf.drawImage(MSButton.backgroundOut, 0, 0, 110, 20, this);
            this.gbuf.setColor(MSButton.colorOut);
        }
        this.gbuf.drawString(this.text, (110 - this.gfm.stringWidth(this.text)) / 2, 15);
        if (this.gbuffer != null) {
            gbuf.drawImage(this.gbuffer, 0, 0, 110, 20, this);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.inside = !this.menuListener.actionPerformed(this.number);
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.inside = true;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.inside = false;
        this.repaint();
    }
}
