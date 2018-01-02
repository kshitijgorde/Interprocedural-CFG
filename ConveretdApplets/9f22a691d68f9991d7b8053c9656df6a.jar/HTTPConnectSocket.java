import java.io.IOException;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class HTTPConnectSocket extends Socket
{
    public HTTPConnectSocket(final String s, final int n, final String s2, final int n2) throws IOException {
        super(s2, n2);
        this.getOutputStream().write(("CONNECT " + s + ":" + n + " HTTP/1.0\r\n\r\n").getBytes());
        final DataInputStream dataInputStream = new DataInputStream(this.getInputStream());
        String s3 = dataInputStream.readLine();
        if (!s3.startsWith("HTTP/1.0 200 ")) {
            if (s3.startsWith("HTTP/1.0 ")) {
                s3 = s3.substring(9);
            }
            throw new IOException("Proxy reports \"" + s3 + "\"");
        }
        while (dataInputStream.readLine().length() != 0) {}
    }
}
