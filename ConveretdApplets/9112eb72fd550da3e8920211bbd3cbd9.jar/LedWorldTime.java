import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LedWorldTime extends Applet implements MouseListener, MouseMotionListener
{
    LedDisplay ledScreen;
    Color inactiveColor;
    Color activeColor;
    Color bgcolor;
    Image bgImage;
    boolean usebg;
    boolean mouseOver1;
    boolean mouseOver2;
    boolean mouseOver3;
    boolean mouseOver4;
    int next;
    int[] rect1x;
    int[] rect1y;
    int[] rect2x;
    int[] rect2y;
    Image appletImage;
    Graphics appletBuffer;
    Dimension dim;
    URL ra;
    int corner;
    boolean useborder;
    MediaTracker mt;
    boolean useSkin;
    String skin;
    
    public LedWorldTime() {
        this.usebg = true;
        this.next = 0;
        this.rect1x = new int[] { 10, 18, 18, 0 };
        this.rect1y = new int[] { 10, 6, 14, 0 };
        this.rect2x = new int[] { 40, 48, 40, 0 };
        this.rect2y = new int[] { 6, 10, 14, 0 };
    }
    
    public void destroy() {
        this.ledScreen.displayThread = null;
        System.out.println("Destroy called");
        System.out.println("Memory is Clean");
    }
    
    public void init() {
        if (!this.getParameter("skin").toUpperCase().equals("NONE") || this.getParameter("skin") != null) {
            this.useSkin = true;
            this.skin = this.getParameter("skin");
        }
        if (!this.useSkin) {
            this.bgcolor = new Color(Integer.parseInt(this.getParameter("bgcolor").substring(0, 3)), Integer.parseInt(this.getParameter("bgcolor").substring(4, 7)), Integer.parseInt(this.getParameter("bgcolor").substring(8, 11)));
            this.inactiveColor = new Color(Integer.parseInt(this.getParameter("forecolor").substring(0, 3)), Integer.parseInt(this.getParameter("forecolor").substring(4, 7)), Integer.parseInt(this.getParameter("forecolor").substring(8, 11)));
            this.activeColor = new Color(Integer.parseInt(this.getParameter("forecolor2").substring(0, 3)), Integer.parseInt(this.getParameter("forecolor2").substring(4, 7)), Integer.parseInt(this.getParameter("forecolor2").substring(8, 11)));
            if (!this.getParameter("bgimage").toUpperCase().equals("NONE")) {
                this.bgImage = this.getImage(this.getDocumentBase(), this.getParameter("bgimage"));
            }
            else {
                this.usebg = false;
            }
        }
        else {
            this.initSkin(this.skin);
        }
        System.out.println("LedWorldTime Applet Initialized");
        this.ledScreen = new LedDisplay(this);
        this.setLayout(null);
        this.ledScreen.setBackground(this.bgcolor);
        this.setBackground(Color.black);
        this.ledScreen.setBounds(5, 22, 126, 27);
        this.add(this.ledScreen);
        (this.mt = new MediaTracker(this)).addImage(this.bgImage, 0);
        try {
            this.mt.waitForAll(0L);
        }
        catch (Exception ex) {}
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.appletImage = this.createImage(150, 100);
        this.appletBuffer = this.appletImage.getGraphics();
        this.dim = this.getSize();
        if (this.getParameter("use_border").toUpperCase().equals("YES")) {
            this.useborder = true;
        }
        else {
            this.useborder = false;
        }
        this.corner = Integer.parseInt(this.getParameter("border_rounded"));
    }
    
    public void initSkin(final String s) {
        if (s.toUpperCase().equals("SKIN1")) {
            this.bgcolor = new Color(0, 0, 0);
            this.inactiveColor = new Color(0, 255, 0);
            this.activeColor = new Color(200, 255, 200);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg1.jpg");
        }
        else if (s.toUpperCase().equals("SKIN2")) {
            this.bgcolor = new Color(0, 0, 0);
            this.inactiveColor = new Color(0, 0, 255);
            this.activeColor = new Color(255, 255, 255);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg2.jpg");
        }
        else if (s.toUpperCase().equals("SKIN3")) {
            this.bgcolor = new Color(0, 255, 0);
            this.inactiveColor = new Color(0, 0, 0);
            this.activeColor = new Color(122, 122, 122);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg3.jpg");
        }
        else if (s.toUpperCase().equals("SKIN4")) {
            this.bgcolor = new Color(255, 255, 255);
            this.inactiveColor = new Color(0, 0, 0);
            this.activeColor = new Color(122, 122, 122);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg4.jpg");
        }
        else if (s.toUpperCase().equals("SKIN5")) {
            this.bgcolor = new Color(0, 0, 0);
            this.inactiveColor = new Color(255, 255, 0);
            this.activeColor = new Color(122, 122, 0);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg5.gif");
        }
        else if (s.toUpperCase().equals("SKIN6")) {
            this.bgcolor = new Color(0, 0, 0);
            this.inactiveColor = new Color(0, 255, 0);
            this.activeColor = new Color(200, 255, 200);
            this.bgImage = this.getImage(this.getDocumentBase(), "bg6.jpg");
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 10 && y > 5 && x < 18 && y < 13) {
            this.mouseOver1 = true;
            this.repaint();
        }
        else {
            if (this.mouseOver1) {
                this.repaint();
            }
            this.mouseOver1 = false;
        }
        if (x > 25 && y > 5 && x < 33 && y < 13) {
            this.mouseOver2 = true;
            this.repaint();
        }
        else {
            if (this.mouseOver2) {
                this.repaint();
            }
            this.mouseOver2 = false;
        }
        if (x > 40 && y > 5 && x < 48 && y < 13) {
            this.mouseOver3 = true;
            this.repaint();
        }
        else {
            if (this.mouseOver3) {
                this.repaint();
            }
            this.mouseOver3 = false;
        }
        if (x > 20 && y > 55 && x < 120 && y < 68) {
            this.mouseOver4 = true;
            this.repaint();
        }
        else {
            if (this.mouseOver4) {
                this.repaint();
            }
            this.mouseOver4 = false;
        }
        if (!this.mouseOver1 && !this.mouseOver2 && !this.mouseOver3 && !this.mouseOver4) {
            this.setCursor(new Cursor(0));
        }
        else {
            this.setCursor(new Cursor(12));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x > 10 && y > 5 && x < 18 && y < 13) {
            --this.next;
            if (this.next < -12) {
                this.next = 13;
            }
            this.ledScreen.time = new WorldTime(this.next);
        }
        if (x > 25 && y > 5 && x < 33 && y < 13) {
            this.next = 13;
            this.ledScreen.time = new WorldTime(this.next);
        }
        if (x > 40 && y > 5 && x < 48 && y < 13) {
            ++this.next;
            if (this.next > 13) {
                this.next = -12;
            }
            this.ledScreen.time = new WorldTime(this.next);
        }
        if (x > 20 && y > 55 && x < 120 && y < 80) {
            try {
                this.ra = new URL("Http://www.realapplets.com");
            }
            catch (Exception ex) {}
            this.getAppletContext().showDocument(this.ra, "_blank");
        }
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.appletBuffer.setFont(new Font("Verdana", 0, 12));
        this.appletBuffer.setColor(this.bgcolor);
        this.appletBuffer.fillRect(0, 0, 300, 200);
        if (this.usebg) {
            this.appletBuffer.drawImage(this.bgImage, 0, 0, this);
        }
        if (this.mouseOver1 && this.appletBuffer.getColor() != this.activeColor) {
            this.appletBuffer.setColor(this.activeColor);
        }
        else {
            this.appletBuffer.setColor(this.inactiveColor);
        }
        this.appletBuffer.fillPolygon(this.rect1x, this.rect1y, 3);
        if (this.mouseOver3) {
            this.appletBuffer.setColor(this.activeColor);
        }
        else {
            this.appletBuffer.setColor(this.inactiveColor);
        }
        this.appletBuffer.fillPolygon(this.rect2x, this.rect2y, 3);
        if (this.mouseOver2) {
            this.appletBuffer.setColor(this.activeColor);
        }
        else {
            this.appletBuffer.setColor(this.inactiveColor);
        }
        this.appletBuffer.fillArc(25, 7, 8, 8, 0, 360);
        if (this.mouseOver4) {
            this.appletBuffer.setColor(this.activeColor);
        }
        else {
            this.appletBuffer.setColor(this.inactiveColor);
        }
        if (this.dim.height > 69) {
            this.appletBuffer.drawString("RealApplets.com", 20, 62);
        }
        else {
            this.appletBuffer.drawString("RealApplets.com", 20, 5);
        }
        this.appletBuffer.setColor(this.inactiveColor);
        this.appletBuffer.drawString(this.ledScreen.time.getTimeZone(), 60, 15);
        if (this.useborder) {
            this.appletBuffer.drawRoundRect(0, 0, this.dim.width - 1, this.dim.height - 1, this.corner, this.corner);
        }
        graphics.drawImage(this.appletImage, 0, 0, this);
    }
    
    public void stop() {
        this.ledScreen.displayThread = null;
        System.out.println("Stop called");
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
