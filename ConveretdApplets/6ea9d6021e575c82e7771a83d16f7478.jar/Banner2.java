import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Banner2 extends Panel implements MouseListener
{
    private Color achterkleur;
    private Color fontkleur1;
    private Color fontkleur2;
    private Font font1;
    private boolean binnen;
    private static int HOOGTE;
    private static int LENGTE;
    private Applet tmp;
    private Image gbuffer;
    private Graphics gbuf;
    
    public Banner2(final Applet tmp) {
        this.binnen = false;
        this.tmp = tmp;
        this.setLayout(null);
        this.setBackground(this.achterkleur = new Color(0, 51, 102));
        this.fontkleur1 = new Color(255, 255, 255);
        this.fontkleur2 = new Color(255, 204, 102);
        this.font1 = new Font("Ariel", 1, 15);
        this.addMouseListener(this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        try {
            this.tmp.getAppletContext().showDocument(new URL("http://www.realapplets.com"), "_blank");
        }
        catch (MalformedURLException ex) {}
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.tmp.showStatus("Real applets for Real good sites");
        this.setCursor(new Cursor(12));
        this.binnen = true;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.tmp.showStatus("");
        this.setCursor(new Cursor(0));
        this.binnen = false;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.gbuffer == null) {
            this.gbuffer = this.createImage(Banner2.LENGTE, Banner2.HOOGTE);
            this.gbuf = this.gbuffer.getGraphics();
        }
        this.gbuf.setColor(this.achterkleur);
        this.gbuf.fillRect(0, 0, Banner2.LENGTE, Banner2.HOOGTE);
        this.gbuf.setFont(this.font1);
        if (this.binnen) {
            this.gbuf.setColor(this.fontkleur2);
        }
        else {
            this.gbuf.setColor(this.fontkleur1);
        }
        this.gbuf.drawString("R", 1, 12);
        this.gbuf.drawString("A", 10, 18);
        graphics.drawImage(this.gbuffer, 0, 0, Banner2.LENGTE, Banner2.HOOGTE, this);
    }
    
    static {
        Banner2.HOOGTE = 20;
        Banner2.LENGTE = 20;
    }
}
