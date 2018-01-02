import java.io.IOException;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class HTTPConnectSocket extends Socket
{
    public HTTPConnectSocket(final String host, final int port, final String proxyHost, final int proxyPort) throws IOException {
        super(proxyHost, proxyPort);
        this.getOutputStream().write(("CONNECT " + host + ":" + port + " HTTP/1.0\r\n\r\n").getBytes());
        final DataInputStream is = new DataInputStream(this.getInputStream());
        String str = is.readLine();
        if (!str.startsWith("HTTP/1.0 200 ")) {
            if (str.startsWith("HTTP/1.0 ")) {
                str = str.substring(9);
            }
            throw new IOException("Proxy reports \"" + str + "\"");
        }
        do {
            str = is.readLine();
        } while (str.length() != 0);
    }
}
