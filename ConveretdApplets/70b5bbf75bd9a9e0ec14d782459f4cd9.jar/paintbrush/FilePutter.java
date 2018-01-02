// 
// Decompiled by Procyon v0.5.30
// 

package paintbrush;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class FilePutter
{
    String host;
    int port;
    String url;
    
    public FilePutter(final String url, final String host) {
        this.port = 80;
        this.host = host;
        this.url = url;
    }
    
    public FilePutter(final String url, final String host, final int port) {
        this.port = 80;
        this.host = host;
        this.port = port;
        this.url = url;
    }
    
    public boolean put(final byte[] source, final String fileName) throws IOException {
        final Socket sock = new Socket(this.host, this.port);
        final InputStream is = sock.getInputStream();
        final PrintStream os = new PrintStream(sock.getOutputStream(), false);
        final String hash = Long.toHexString(System.currentTimeMillis());
        final String header = "-----------------------------" + hash + "\r\n" + "Content-Disposition: form-data; name=\"ImageFile\"; filename=\"" + fileName + "\"\r\n" + "Content-Type: application/octet-stream\r\n" + "\r\n";
        final String footer = "-----------------------------" + hash + "--\r\n";
        os.print("POST " + this.url + " HTTP/1.0\r\n");
        os.print("Content-Type: multipart/form-data; boundary=---------------------------" + hash + "\r\n");
        os.print("Content-Length: " + (source.length + header.length() + footer.length()) + "\r\n");
        os.print("Host: " + this.host + "\r\n");
        os.print("\r\n");
        os.print(header);
        os.write(source);
        os.print("\r\n");
        os.print(footer);
        os.flush();
        String tmpStr = "";
        int b = 0;
        while ((b = is.read()) != -1) {
            tmpStr = String.valueOf(tmpStr) + (char)b;
        }
        os.close();
        is.close();
        return tmpStr.indexOf("Successful") != -1;
    }
}
