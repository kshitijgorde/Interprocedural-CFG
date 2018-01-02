import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Polygon;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class ImgButtonImage
{
    private int xSize;
    private int ySize;
    private int border;
    private String gifName;
    public Image up;
    public Image down;
    private static final Color upEdgeColor;
    private static final Color downEdgeColor;
    private static final Color lineColor;
    private final float shiftPercentage = 5.0f;
    
    static {
        upEdgeColor = Color.lightGray;
        downEdgeColor = Color.darkGray;
        lineColor = Color.black;
    }
    
    public ImgButtonImage(final String s, final int xSize, final int ySize, final int border, final Applet applet) {
        this.gifName = new String(s);
        this.xSize = xSize;
        this.ySize = ySize;
        this.border = border;
        this.up = applet.createImage(this.xSize, this.ySize);
        this.down = applet.createImage(this.xSize, this.ySize);
        final Graphics graphics = this.up.getGraphics();
        final Graphics graphics2 = this.down.getGraphics();
        System.out.println("getting image " + applet.getCodeBase() + this.gifName);
        final Image image = applet.getImage(applet.getCodeBase(), this.gifName);
        this.fillImage(graphics, image, (int)(this.border * 5.0f / 100.0f), true, applet);
        this.fillImage(graphics2, image, (int)(-1.0 * this.border * 5.0 / 100.0), false, applet);
    }
    
    boolean IsEqual(final String s, final int n, final int n2, final int n3) {
        return true && this.gifName.compareTo(s) == 0 && this.xSize == n && this.ySize == n2 && this.border == n3;
    }
    
    private void fillImage(final Graphics graphics, final Image image, final int n, final boolean b, final Applet applet) {
        Color color;
        Color color2;
        if (b) {
            color = ImgButtonImage.upEdgeColor;
            color2 = ImgButtonImage.downEdgeColor;
        }
        else {
            color = ImgButtonImage.downEdgeColor;
            color2 = ImgButtonImage.upEdgeColor;
        }
        final Polygon polygon = new Polygon(new int[] { 0, this.border + n, this.border + n, 0, 0 }, new int[] { 0, this.border + n, this.ySize - this.border + n, this.ySize, 0 }, 5);
        graphics.setColor(color);
        graphics.fillPolygon(polygon);
        final Polygon polygon2 = new Polygon(new int[] { 0, this.border + n, this.xSize - this.border + n, this.xSize, 0 }, new int[] { 0, this.border + n, this.border + n, 0, 0 }, 5);
        graphics.setColor(color);
        graphics.fillPolygon(polygon2);
        final Polygon polygon3 = new Polygon(new int[] { this.xSize, this.xSize - this.border + n, this.xSize - this.border + n, this.xSize, this.xSize }, new int[] { 0, this.border + n, this.ySize - this.border + n, this.ySize, 0 }, 5);
        graphics.setColor(color2);
        graphics.fillPolygon(polygon3);
        final Polygon polygon4 = new Polygon(new int[] { 0, this.border + n, this.xSize - this.border + n, this.xSize, 0 }, new int[] { this.ySize, this.ySize - this.border + n, this.ySize - this.border + n, this.ySize, this.ySize }, 5);
        graphics.setColor(color2);
        graphics.fillPolygon(polygon4);
        graphics.setColor(ImgButtonImage.lineColor);
        graphics.drawPolygon(polygon);
        graphics.drawPolygon(polygon2);
        graphics.drawPolygon(polygon3);
        graphics.drawPolygon(polygon4);
        final MediaTracker mediaTracker = new MediaTracker(applet);
        final int n2 = 1;
        mediaTracker.addImage(image, n2);
        try {
            mediaTracker.waitForID(n2);
        }
        catch (InterruptedException ex) {}
        graphics.drawImage(image, this.border + n, this.border + n, this.xSize - 2 * this.border, this.ySize - 2 * this.border, applet);
    }
    
    public int getBorder() {
        return this.border;
    }
    
    public Image getDown() {
        return this.down;
    }
    
    public Image getUp() {
        return this.up;
    }
    
    public int getXsize() {
        return this.xSize;
    }
    
    public int getYsize() {
        return this.ySize;
    }
}
