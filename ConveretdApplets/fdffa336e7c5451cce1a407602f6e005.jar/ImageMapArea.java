import java.net.URL;
import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class ImageMapArea implements ImageObserver
{
    ImageMap parent;
    int X;
    int Y;
    int W;
    int H;
    boolean entered;
    boolean active;
    boolean terminal;
    Image hlImage;
    
    public void init(final ImageMap parent, final String s) {
        this.parent = parent;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        this.X = Integer.parseInt(stringTokenizer.nextToken());
        this.Y = Integer.parseInt(stringTokenizer.nextToken());
        this.W = Integer.parseInt(stringTokenizer.nextToken());
        this.H = Integer.parseInt(stringTokenizer.nextToken());
        if (stringTokenizer.hasMoreTokens()) {
            this.handleArg(stringTokenizer.nextToken(""));
        }
        else {
            this.handleArg(null);
        }
        this.makeImages();
    }
    
    public void handleArg(final String s) {
    }
    
    public void setHighlight(final Image hlImage) {
        this.hlImage = hlImage;
    }
    
    public void makeImages() {
        this.setHighlight(this.parent.getHighlight(this.X, this.Y, this.W, this.H));
    }
    
    public boolean inside(final int n, final int n2) {
        return n >= this.X && n < this.X + this.W && n2 >= this.Y && n2 < this.Y + this.H;
    }
    
    public void drawImage(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final Graphics create = graphics.create();
        create.clipRect(n3, n4, n5, n6);
        create.drawImage(image, n, n2, this);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return image == this.hlImage && this.parent.imageUpdate(image, n, n2 + this.X, n3 + this.Y, n4, n5);
    }
    
    public void showStatus(final String s) {
        this.parent.getAppletContext().showStatus(s);
    }
    
    public void showDocument(final URL url) {
        this.parent.getAppletContext().showDocument(url);
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
        if (b) {
            graphics.drawImage(this.hlImage, this.X, this.Y, this);
            return;
        }
        this.drawImage(graphics, this.parent.baseImage, 0, 0, this.X, this.Y, this.W, this.H);
    }
    
    public void setState(final Graphics graphics, final boolean active) {
        this.highlight(graphics, active);
        this.active = active;
    }
    
    public void press(final int n, final int n2) {
        this.press();
    }
    
    public void press() {
    }
    
    public void lift(final int n, final int n2) {
        this.lift();
    }
    
    public void lift() {
    }
    
    public void drag(final int n, final int n2) {
    }
    
    ImageMapArea() {
        this.entered = false;
        this.active = false;
        this.terminal = true;
    }
}
