import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;
import java.awt.image.ImageObserver;

// 
// Decompiled by Procyon v0.5.30
// 

class CImageLoader implements ImageObserver
{
    protected int m_count;
    protected Applet m_applet;
    Vector m_vecName;
    Vector m_vecImage;
    
    CImageLoader() {
        this.m_vecName = new Vector();
        this.m_vecImage = new Vector();
    }
    
    CImageLoader(final Applet applet) {
        this.m_vecName = new Vector();
        this.m_vecImage = new Vector();
        this.m_applet = applet;
    }
    
    Image loadImage(final String s) {
        final Image image = this.findImage(s);
        if (image != null) {
            return image;
        }
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            this.m_applet.getAppletContext().showStatus("Bad Icon URL: " + s);
            return null;
        }
        this.m_applet.getAppletContext().showStatus("Loading Images...");
        final Image image2 = this.m_applet.getImage(url);
        this.m_vecName.addElement(s);
        this.m_vecImage.addElement(image2);
        if (!this.m_applet.prepareImage(image2, this)) {
            ++this.m_count;
        }
        return image2;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int checkImage = this.m_applet.checkImage(image, null);
        if ((checkImage & 0x20) == 0x20 || (checkImage & 0x80) == 0x80 || (checkImage & 0x40) == 0x40) {
            --this.m_count;
            if (this.m_count == 0) {
                this.m_applet.getAppletContext().showStatus("Done");
                this.m_applet.repaint();
            }
        }
        return true;
    }
    
    Image findImage(final String s) {
        for (int i = 0; i < this.m_vecName.size(); ++i) {
            if (s.compareTo((String)this.m_vecName.elementAt(i)) == 0) {
                return (Image)this.m_vecImage.elementAt(i);
            }
        }
        return null;
    }
    
    int getCount() {
        return this.m_count;
    }
}
