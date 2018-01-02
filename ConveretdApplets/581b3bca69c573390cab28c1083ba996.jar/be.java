import java.awt.image.ImageProducer;
import sun.awt.image.URLImageSource;
import java.io.ByteArrayInputStream;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

class be extends URLConnection
{
    private static final String a = "application/jpeg";
    private InputStream b;
    
    be(final URL url) {
        super(url);
        this.b = null;
    }
    
    Image a(final byte[] array, final int n, final int n2) {
        try {
            return this.b(array, n, n2);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.c(array, n, n2);
        }
    }
    
    private Image b(final byte[] array, final int n, final int n2) {
        return Toolkit.getDefaultToolkit().createImage(array, n, n2);
    }
    
    private Image c(final byte[] array, final int n, final int n2) {
        this.b = new ByteArrayInputStream(array, n, n2);
        return Toolkit.getDefaultToolkit().createImage(new URLImageSource(this));
    }
    
    public void connect() {
    }
    
    public String getContentType() {
        return "application/jpeg";
    }
    
    public InputStream getInputStream() {
        return this.b;
    }
}
