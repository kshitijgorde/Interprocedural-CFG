import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageStats
{
    int xImagePos;
    int yImagePos;
    Image Img;
    URL UrlLink;
    boolean bUrlLink;
    
    ImageStats(final Image Img, final String sUrl) {
        this.xImagePos = 0;
        this.yImagePos = 0;
        this.Img = Img;
        try {
            this.UrlLink = new URL(sUrl);
            this.bUrlLink = true;
        }
        catch (MalformedURLException e) {
            this.bUrlLink = false;
        }
    }
    
    boolean IsUrlAvailable() {
        return this.bUrlLink;
    }
    
    URL GetUrl() {
        return this.UrlLink;
    }
}
