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

class ByteMappedImage extends URLConnection
{
    private static final String mContent = "application/jpeg";
    private InputStream mConnectionStream;
    
    ByteMappedImage(final URL url) {
        super(url);
        this.mConnectionStream = null;
    }
    
    Image createImage(final byte[] array, final int offset, final int length) {
        try {
            return this.createImage11(array, offset, length);
        }
        catch (NoSuchMethodError e) {
            return this.createImage102(array, offset, length);
        }
    }
    
    private Image createImage11(final byte[] array, final int offset, final int length) {
        return Toolkit.getDefaultToolkit().createImage(array, offset, length);
    }
    
    private Image createImage102(final byte[] array, final int offset, final int length) {
        this.mConnectionStream = new ByteArrayInputStream(array, offset, length);
        return Toolkit.getDefaultToolkit().createImage(new URLImageSource(this));
    }
    
    public void connect() {
    }
    
    public String getContentType() {
        return "application/jpeg";
    }
    
    public InputStream getInputStream() {
        return this.mConnectionStream;
    }
}
