// 
// Decompiled by Procyon v0.5.30
// 

package speedometer.A;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.BufferedInputStream;
import javax.swing.ImageIcon;

public class D
{
    static /* synthetic */ Class class$0;
    
    public static ImageIcon A(final String s) {
        ImageIcon imageIcon = null;
        InputStream resourceAsStream = null;
        FilterInputStream filterInputStream = null;
        try {
            Class class$0;
            if ((class$0 = D.class$0) == null) {
                try {
                    class$0 = (D.class$0 = Class.forName("speedometer.A.D"));
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            filterInputStream = new BufferedInputStream(resourceAsStream = class$0.getResourceAsStream(s));
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = filterInputStream.read(array)) != -1) {
                byteArrayOutputStream.write(array, 0, read);
            }
            imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(byteArrayOutputStream.toByteArray()));
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            throw new RuntimeException("Image load failed, path=" + s);
        }
        finally {
            try {
                ((BufferedInputStream)filterInputStream).close();
            }
            catch (Exception ex3) {}
            try {
                resourceAsStream.close();
            }
            catch (Exception ex4) {}
        }
        try {
            ((BufferedInputStream)filterInputStream).close();
        }
        catch (Exception ex5) {}
        try {
            resourceAsStream.close();
        }
        catch (Exception ex6) {}
        return imageIcon;
    }
}
