// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import soht.client.java.configuration.ConfigurationManager;
import java.io.OutputStream;

public class ProxyReader extends BaseProxy
{
    private OutputStream out;
    
    public ProxyReader(final String name, final ConfigurationManager configurationManager, final long connectionId, final Socket socket) throws IOException {
        super(name, configurationManager, connectionId, socket);
        this.out = socket.getOutputStream();
    }
    
    public void run() {
        try {
            final HttpURLConnection urlConnection = this.configurationManager.getURLConnection();
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
            out.write("action=read");
            out.write("&");
            out.write("id=" + this.connectionId);
            out.flush();
            out.close();
            urlConnection.connect();
            InputStream in = null;
            try {
                in = urlConnection.getInputStream();
                final byte[] bytes = new byte[1024];
                int count = 0;
                boolean isFirst = true;
                int startIndex = 1;
                while (true) {
                    count = in.read(bytes);
                    if (count == -1 || (isFirst && count > 0 && bytes[0] == 0)) {
                        break;
                    }
                    startIndex = (isFirst ? 1 : 0);
                    try {
                        this.out.write(bytes, startIndex, count - startIndex);
                    }
                    catch (IOException e) {
                        this.closeServer();
                    }
                    isFirst = false;
                }
                out.close();
                this.socket.close();
            }
            finally {
                if (in != null) {
                    in.close();
                }
                urlConnection.disconnect();
            }
        }
        catch (IOException ioe) {
            if (this.out != null) {
                try {
                    this.out.close();
                    this.socket.close();
                }
                catch (IOException e2) {
                    System.out.println("Error closing output stream to client.");
                }
            }
            System.out.println("IOException in ProxyReader.");
            ioe.printStackTrace();
        }
    }
}
