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
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Banner extends Panel implements MouseListener, MouseMotionListener
{
    private Color achterkleur;
    private Color fontkleur1;
    private Color fontkleur2;
    private Font font1;
    private int laading;
    private int lengte;
    private int xx;
    private static int HOOGTE;
    private Applet tmp;
    private Image gbuffer;
    private Graphics gbuf;
    
    public Banner(final int lengte, final Applet tmp) {
        this.lengte = lengte;
        this.tmp = tmp;
        this.setLayout(null);
        this.setBackground(this.achterkleur = new Color(0, 51, 102));
        this.fontkleur1 = new Color(255, 255, 255);
        this.fontkleur2 = new Color(255, 204, 102);
        this.font1 = new Font("Ariel", 0, 20);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.xx = this.lengte - 85;
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
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.tmp.showStatus("");
        this.setCursor(new Cursor(0));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.xx = mouseEvent.getX();
        if (this.xx > this.lengte - Banner.HOOGTE / 2) {
            this.xx = this.lengte - Banner.HOOGTE / 2;
        }
        if (this.xx < this.lengte - 211 + Banner.HOOGTE / 2) {
            this.xx = this.lengte - 211 + Banner.HOOGTE / 2;
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.gbuffer == null) {
            this.gbuffer = this.createImage(this.lengte, Banner.HOOGTE);
            this.gbuf = this.gbuffer.getGraphics();
        }
        this.gbuf.setColor(this.achterkleur);
        this.gbuf.fillRect(0, 0, this.lengte, Banner.HOOGTE);
        this.gbuf.setColor(this.fontkleur2);
        this.gbuf.fillOval(this.xx - Banner.HOOGTE / 2, 0, Banner.HOOGTE, Banner.HOOGTE);
        this.gbuf.setFont(this.font1);
        this.gbuf.setColor(this.fontkleur1);
        this.gbuf.drawString("This is a real applet from:", 5, 20);
        this.gbuf.setColor(this.achterkleur);
        this.gbuf.drawString("www.realapplets.com", this.lengte - 201, 37);
        graphics.drawImage(this.gbuffer, 0, 0, this.lengte, Banner.HOOGTE, this);
    }
    
    static {
        Banner.HOOGTE = 60;
    }
}
