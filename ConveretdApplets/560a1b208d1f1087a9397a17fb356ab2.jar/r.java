import java.awt.image.ImageProducer;
import sun.awt.image.URLImageSource;
import java.awt.Toolkit;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.awt.Image;
import java.io.InputStream;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

class r extends URLConnection
{
    protected static final String a = "application/jpeg";
    protected InputStream b;
    Image c;
    
    public InputStream getInputStream() {
        return this.b;
    }
    
    r(final URL url, final byte[] array) {
        super(url);
        this.b = null;
        this.c = null;
        this.b = new ByteArrayInputStream(array);
        this.c = Toolkit.getDefaultToolkit().createImage(new URLImageSource(this));
    }
    
    public String getContentType() {
        return "application/jpeg";
    }
    
    public void connect() {
    }
}
