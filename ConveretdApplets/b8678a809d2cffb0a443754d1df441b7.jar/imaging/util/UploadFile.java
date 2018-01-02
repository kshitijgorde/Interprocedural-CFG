// 
// Decompiled by Procyon v0.5.30
// 

package imaging.util;

import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.net.URL;

public class UploadFile
{
    public static byte[] upload(final URL url, final Hashtable<String, String> params, final byte[] fileBytes, final byte[] data2) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        HttpURLConnection hc = null;
        InputStream is = null;
        byte[] res = null;
        try {
            System.out.println("URL in Upload File is =" + url.toString());
            hc = (HttpURLConnection)url.openConnection();
            hc.setRequestProperty("Content-Length", String.valueOf(fileBytes.length));
            hc.setRequestProperty("Content-Type", "image/jpg;");
            hc.setRequestMethod("POST");
            hc.setDoInput(true);
            hc.setDoOutput(true);
            final OutputStream dout = hc.getOutputStream();
            dout.write(fileBytes);
            dout.write(data2);
            dout.flush();
            dout.close();
            hc.connect();
            is = hc.getInputStream();
            int ch;
            while ((ch = is.read()) != -1) {
                bos.write(ch);
            }
            res = bos.toByteArray();
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (is != null) {
                    is.close();
                }
                if (hc != null) {
                    hc.disconnect();
                }
            }
            catch (Exception ex) {}
        }
        return null;
    }
}
