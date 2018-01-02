import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class AppletWrapper extends Applet
{
    private static final long serialVersionUID = 1L;
    boolean m_bHtmlContext;
    boolean m_bAppActive;
    static /* synthetic */ Class class$0;
    
    AppletWrapper() {
        this.m_bHtmlContext = false;
        this.m_bAppActive = false;
    }
    
    public void start() {
        if (this.m_bHtmlContext) {
            super.start();
        }
        else {
            this.m_bAppActive = true;
        }
    }
    
    public void stop() {
        if (this.m_bHtmlContext) {
            super.stop();
        }
        else {
            this.m_bAppActive = false;
        }
    }
    
    public boolean isActive() {
        if (this.m_bHtmlContext) {
            return super.isActive();
        }
        return this.m_bAppActive;
    }
    
    public URL getDocumentBase() {
        if (this.m_bHtmlContext) {
            return super.getDocumentBase();
        }
        return null;
    }
    
    public URL getCodeBase() {
        if (this.m_bHtmlContext) {
            return super.getCodeBase();
        }
        Class class$0;
        if ((class$0 = AppletWrapper.class$0) == null) {
            try {
                class$0 = (AppletWrapper.class$0 = Class.forName("AppletWrapper"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        return class$0.getProtectionDomain().getCodeSource().getLocation();
    }
    
    public void showStatus(final String s) {
        if (this.m_bHtmlContext) {
            super.showStatus(s);
        }
    }
    
    public Image getImage(final URL url) {
        if (this.m_bHtmlContext) {
            return super.getImage(url);
        }
        return this.getImage(url.getPath());
    }
    
    public Image getImage(final URL url, final String s) {
        if (url != null) {
            return super.getImage(url, s);
        }
        return this.getImage(s);
    }
    
    public Image getImage(final String s) {
        final File file = new File(s);
        if (!file.exists()) {
            return null;
        }
        BufferedImage read;
        try {
            read = ImageIO.read(file);
        }
        catch (Exception ex) {
            read = null;
        }
        return read;
    }
    
    public String getParameter(final String s) {
        if (this.m_bHtmlContext) {
            return super.getParameter(s);
        }
        return null;
    }
}
