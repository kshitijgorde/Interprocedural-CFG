import java.awt.image.ImageProducer;
import sun.awt.image.URLImageSource;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;

// 
// Decompiled by Procyon v0.5.30
// 

public class hm35decimg extends URLConnection
{
    public String qn;
    public InputStream qm;
    
    public final boolean equals(final Object o) {
        if (!(o instanceof URLConnection)) {
            final Object[] array = (Object[])o;
            int intValue = (int)array[0];
            final byte[] array2 = (byte[])array[1];
            super.url = (URL)array[2];
            if (intValue != 1 && intValue != 0) {
                intValue = ((array2[0] != 71) ? 1 : 0);
            }
            this.qn = ((intValue == 1) ? "image/jpeg" : "image/gif");
            this.qm = new ByteArrayInputStream(array2);
            try {
                array[0] = null;
                array[0] = ((Component)array[3]).createImage(new URLImageSource(this));
            }
            catch (Exception ex) {}
            final Object[] array3 = array;
            final int n = 1;
            final Object[] array4 = array;
            final int n2 = 2;
            final Object[] array5 = array;
            final int n3 = 3;
            final Object o2 = null;
            array5[n3] = o2;
            array3[n] = (array4[n2] = o2);
            return false;
        }
        return super.equals(o);
    }
    
    public final InputStream getInputStream() {
        return this.qm;
    }
    
    public final String getContentType() {
        return this.qn;
    }
    
    public final void connect() {
    }
    
    public hm35decimg() {
        super(null);
    }
}
