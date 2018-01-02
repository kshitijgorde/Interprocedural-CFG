import java.io.BufferedInputStream;
import java.net.URL;
import javax.swing.ImageIcon;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageLoader
{
    public ImageIcon getImageIcon(final String sImagePath) {
        URL url = ClassLoader.getSystemResource(sImagePath);
        if (url == null) {
            url = this.getClass().getResource(sImagePath);
        }
        ImageIcon image;
        if (url == null) {
            final byte[] bytes = this.pLoadBytes(sImagePath);
            if (bytes == null || bytes.length == 0) {
                image = null;
            }
            else {
                image = new ImageIcon(bytes);
            }
        }
        else {
            image = new ImageIcon(url);
        }
        return image;
    }
    
    private byte[] pLoadBytes(final String sPathFile) {
        try {
            BufferedInputStream file = new BufferedInputStream(this.getClass().getResourceAsStream(sPathFile));
            final byte[] bytes = new byte[file.available()];
            file.read(bytes, 0, bytes.length);
            file.close();
            file = null;
            return bytes;
        }
        catch (Exception e) {
            return null;
        }
    }
}
