// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

import java.net.HttpURLConnection;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.SocketException;
import java.io.IOException;
import java.net.Socket;
import soht.client.java.configuration.ConfigurationManager;
import java.io.InputStream;

public class ProxyWriter extends BaseProxy
{
    private InputStream in;
    
    public ProxyWriter(final String name, final ConfigurationManager configurationManager, final long connectionId, final Socket socket) throws IOException {
        super(name, configurationManager, connectionId, socket);
        this.in = socket.getInputStream();
    }
    
    public void run() {
        try {
            final byte[] bytes = new byte[1024];
            int count = 0;
            while (true) {
                try {
                    count = this.in.read(bytes);
                }
                catch (SocketException socketException) {
                    if (!"Socket closed".equals(socketException.getMessage())) {
                        System.out.println("Error reading data from server: " + socketException);
                    }
                    break;
                }
                if (count == -1) {
                    this.closeServer();
                    break;
                }
                final HttpURLConnection urlConnection = this.configurationManager.getURLConnection();
                final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
                out.write("action=write");
                out.write("&");
                out.write("id=" + this.connectionId);
                out.write("&");
                out.write("datalength=" + count);
                out.write("&");
                out.write("data=");
                out.write(this.encode(bytes, count));
                out.flush();
                out.close();
                urlConnection.connect();
                urlConnection.getInputStream();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
