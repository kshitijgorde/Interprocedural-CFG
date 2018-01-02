// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.Point;
import javax.swing.JLabel;

class NavigationMapLabel extends JLabel
{
    static final long serialVersionUID = 0L;
    Point point;
    Color dotColor;
    URL c_imageNavigationsKort;
    public int HotSpotSizeFactor;
    private int HotSpotSizeX;
    private int HotSpotSizeY;
    
    public NavigationMapLabel() {
        this.point = null;
        this.dotColor = Color.RED;
        this.HotSpotSizeX = 5;
        this.HotSpotSizeY = 4;
        try {
            final String urlBase = Constant.imageURL;
            System.out.println(urlBase);
            this.c_imageNavigationsKort = new URL(String.valueOf(urlBase) + "sat_dk.jpg");
        }
        catch (MalformedURLException e) {
            System.out.println("Couldn't set navigation map for Denmark");
        }
        this.HotSpotSizeFactor = 1;
        this.setIcon(new ImageIcon(this.c_imageNavigationsKort));
        this.setOpaque(false);
        this.repaint();
    }
    
    public void setHotSpotSizeFactor(final int HotSpotSizeFactor) {
        this.HotSpotSizeFactor = HotSpotSizeFactor;
        this.repaint();
    }
    
    public void setHotSpotSize(final String HotSpotSizeX, final String HotSpotSizeY) {
        this.HotSpotSizeX = Integer.parseInt(HotSpotSizeX);
        this.HotSpotSizeY = Integer.parseInt(HotSpotSizeY);
        this.repaint();
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.point != null) {
            g.setColor(this.dotColor);
            g.drawRect(this.point.x - this.HotSpotSizeX / 2, this.point.y - this.HotSpotSizeY / 2, this.HotSpotSizeX, this.HotSpotSizeY);
        }
    }
    
    public void drawBox(final int cursorX, final int cursorY) {
        if (this.point == null) {
            this.point = new Point(cursorX, cursorY);
        }
        else {
            this.point.x = cursorX;
            this.point.y = cursorY;
        }
        this.repaint();
    }
}
