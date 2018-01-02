import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class TagArea extends ImageMapArea
{
    Image sourceImage;
    int tagx;
    int tagy;
    
    public void handleArg(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ", ");
        final String nextToken = stringTokenizer.nextToken();
        try {
            this.sourceImage = super.parent.getImage(new URL(super.parent.getDocumentBase(), nextToken));
        }
        catch (MalformedURLException ex) {}
        this.tagx = Integer.parseInt(stringTokenizer.nextToken());
        this.tagy = Integer.parseInt(stringTokenizer.nextToken());
    }
    
    public void makeImages() {
        this.setHighlight(this.sourceImage);
    }
    
    public void highlight(final Graphics graphics) {
        if (super.entered) {
            graphics.drawImage(super.hlImage, this.tagx, this.tagy, this);
        }
    }
    
    public void repaint() {
        super.parent.repaint(0L, this.tagx, this.tagy, 100, 25);
    }
    
    public boolean enter() {
        this.repaint();
        return true;
    }
    
    public void exit() {
        this.repaint();
    }
}
