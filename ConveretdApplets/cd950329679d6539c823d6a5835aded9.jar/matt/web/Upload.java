// 
// Decompiled by Procyon v0.5.30
// 

package matt.web;

import java.util.Properties;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioSystem;
import java.util.zip.ZipEntry;
import java.io.OutputStream;
import java.util.zip.ZipOutputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;

public class Upload
{
    public int submit(final AudioInputStream is, final AudioFileFormat.Type fileType) throws Exception {
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        DataInputStream inStream = null;
        final String exsistingFileName = "test.wav";
        final String lineEnd = "\r\n";
        final String twoHyphens = "--";
        final String boundary = "*****";
        final int maxBufferSize = 1048576;
        final String urlString = "http://localhost/tunometer/submit.php";
        try {
            final Properties systemProperties = System.getProperties();
            systemProperties.setProperty("http.proxyHost", "localhost");
            systemProperties.setProperty("http.proxyPort", "51966");
            final URL url = new URL(urlString);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"wav\"; filename=\"" + exsistingFileName + "\"" + lineEnd);
            dos.writeBytes(lineEnd);
            final ZipOutputStream out = new ZipOutputStream(dos);
            out.setLevel(9);
            out.putNextEntry(new ZipEntry(exsistingFileName));
            final int bytesAvailable = is.available();
            AudioSystem.write(is, fileType, out);
            out.flush();
            out.finish();
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
            is.close();
            dos.flush();
            dos.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("From ServletCom CLIENT REQUEST:" + ex);
        }
        catch (IOException ioe) {
            System.out.println("From ServletCom CLIENT REQUEST:" + ioe);
        }
        final StringBuffer response = new StringBuffer();
        try {
            inStream = new DataInputStream(conn.getInputStream());
            String str;
            while ((str = inStream.readLine()) != null) {
                response.append(str);
            }
            inStream.close();
            System.out.println("Response: " + (Object)response);
        }
        catch (IOException ioex) {
            System.out.println("From (ServerResponse): " + ioex);
        }
        return Integer.parseInt("" + response.substring(response.indexOf(":") + 1));
    }
}
