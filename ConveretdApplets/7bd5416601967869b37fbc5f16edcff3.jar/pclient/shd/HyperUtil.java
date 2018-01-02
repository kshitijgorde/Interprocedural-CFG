// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Properties;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import com.pchat.sc.StringUtil;
import java.net.URL;

public class HyperUtil
{
    public static String initCandy(URL appendParams) throws IOException {
        appendParams = appendParams(appendParams, "new", null);
        final URLConnection openConnection = appendParams.openConnection();
        openConnection.setUseCaches(false);
        openConnection.setRequestProperty("chatcom", "new");
        String s = openConnection.getHeaderField("chatcandy");
        boolean b = true;
        if (StringUtil.isTrimmedEmpty(s)) {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new DataInputStream(openConnection.getInputStream()));
            final Properties properties = new Properties();
            properties.load(bufferedInputStream);
            s = properties.getProperty("chatcandy");
            if (StringUtil.isTrimmedEmpty(s)) {
                b = false;
            }
            System.out.println("ck=" + s);
        }
        openConnection.getInputStream().close();
        if (b) {
            return s;
        }
        return null;
    }
    
    public static byte[] post(URL appendParams, final String s, final byte[] array) throws MalformedURLException, IOException {
        appendParams = appendParams(appendParams, "upload", s);
        final URLConnection openConnection = appendParams.openConnection();
        openConnection.setUseCaches(false);
        openConnection.setDoOutput(true);
        openConnection.setRequestProperty("Content-type", "applicatin-octet-stream");
        openConnection.setRequestProperty("chatcandy", s);
        openConnection.setRequestProperty("chatcom", "upload");
        final OutputStream outputStream = openConnection.getOutputStream();
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(array, 0, array.length);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        final InputStream inputStream = openConnection.getInputStream();
        final byte[] input = readInput(openConnection, inputStream);
        inputStream.close();
        outputStream.close();
        return input;
    }
    
    public static byte[] download(URL appendParams, final String s) throws MalformedURLException, IOException {
        appendParams = appendParams(appendParams, "download", s);
        final URLConnection openConnection = appendParams.openConnection();
        openConnection.setUseCaches(false);
        openConnection.setDoOutput(false);
        openConnection.setRequestProperty("chatcandy", s);
        openConnection.setRequestProperty("chatcom", "download");
        final InputStream inputStream = openConnection.getInputStream();
        final byte[] input = readInput(openConnection, inputStream);
        inputStream.close();
        return input;
    }
    
    private static byte[] readInput(final URLConnection urlConnection, final InputStream inputStream) throws IOException {
        String contentType = urlConnection.getContentType();
        final int contentLength = urlConnection.getContentLength();
        if (contentType == null) {
            contentType = "text/plain";
        }
        if (contentType.startsWith("text/") || contentLength < 0) {
            System.out.println("contentT=" + contentType);
            System.out.println("contentL=" + contentLength);
            if (contentLength > 0) {
                System.out.print("in=");
                int read;
                while ((read = new InputStreamReader(new BufferedInputStream(inputStream)).read()) != -1) {
                    System.out.print((char)read);
                }
            }
            throw new IOException("http error #8922.");
        }
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        final byte[] array = new byte[contentLength];
        int i;
        int read2;
        for (i = 0; i < contentLength; i += read2) {
            read2 = bufferedInputStream.read(array, i, array.length - i);
            if (read2 == -1) {
                break;
            }
        }
        bufferedInputStream.close();
        if (i != contentLength) {
            throw new IOException("err8723, http " + i + "." + contentLength);
        }
        return array;
    }
    
    private static URL appendParams(final URL url, String s, String s2) throws MalformedURLException, IOException {
        final String string = url.toExternalForm() + "?";
        if (s == null) {
            s = "";
        }
        if (s2 == null) {
            s2 = "";
        }
        return new URL(string + "chatcom" + "=" + s + "&" + "chatcandy" + "=" + s2);
    }
}
