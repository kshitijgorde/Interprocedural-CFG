// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.OutputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import imaging.util.UploadFile;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.Serializable;

public class SendToAspx implements Serializable
{
    public static void sendToServer(final ImageApplet option, final String queryString, final URL url1, final BufferedImage br, final BufferedImage af) throws MalformedURLException, IOException {
        final byte[] data = bufferedImageToByteArray(br);
        final byte[] data2 = bufferedImageToByteArray(af);
        System.out.println("Original URL=" + url1.toString());
        final URL url2 = new URL(url1, String.valueOf(queryString) + "&length=" + data.length);
        System.out.println("After URL=" + url2.toString());
        UploadFile.upload(url2, null, data, data2);
    }
    
    public static byte[] bufferedImageToByteArray(final BufferedImage img) throws ImageFormatException, IOException {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder((OutputStream)os);
        encoder.encode(img);
        return os.toByteArray();
    }
}
