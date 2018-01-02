import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageStats
{
    final int EXPAND = 1;
    final int SHRINK = 2;
    int xImagePos;
    int yImagePos;
    int xImageSize;
    int yImageSize;
    int xImageSizeOrg;
    int yImageSizeOrg;
    int xImagePosOrg;
    int yImagePosOrg;
    int State;
    Image Img;
    URL UrlLink;
    boolean bUrlLink;
    int xImg1;
    int yImg1;
    int xImg2;
    int yImg2;
    
    ImageStats(final Image img, final int xImg1, final int yImg1, final int n, final int n2, final String s) {
        this.Img = img;
        this.xImagePosOrg = xImg1;
        this.xImagePos = xImg1;
        this.yImagePosOrg = yImg1;
        this.yImagePos = yImg1;
        this.xImageSizeOrg = n;
        this.xImageSize = n;
        this.yImageSizeOrg = n2;
        this.yImageSize = n2;
        this.State = 2;
        this.xImg1 = xImg1;
        this.yImg1 = yImg1;
        this.xImg2 = xImg1 + n;
        this.yImg2 = yImg1 + n2;
        try {
            this.UrlLink = new URL(s);
            this.bUrlLink = true;
        }
        catch (MalformedURLException ex) {
            this.bUrlLink = false;
        }
    }
    
    boolean IsUrlAvailable() {
        return this.bUrlLink;
    }
    
    URL GetUrl() {
        return this.UrlLink;
    }
    
    boolean CheckBrowse(final int n, final int n2) {
        return n >= this.xImg1 && n <= this.xImg2 && n2 >= this.yImg1 && n2 <= this.yImg2;
    }
}
