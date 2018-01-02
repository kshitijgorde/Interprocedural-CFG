// 
// Decompiled by Procyon v0.5.30
// 

package soht.client.java.core;

import java.net.HttpURLConnection;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.io.IOException;
import java.net.Socket;
import soht.client.java.configuration.ConfigurationManager;
import java.io.OutputStream;
import java.io.InputStream;

public class ProxyReadWrite extends BaseProxy
{
    private static final long DEFAULT_SLEEP_TIME = 250L;
    private InputStream in;
    private OutputStream out;
    
    public ProxyReadWrite(final String name, final ConfigurationManager configurationManager, final long connectionId, final Socket socket) throws IOException {
        super(name, configurationManager, connectionId, socket);
        socket.setReceiveBufferSize(64);
        socket.setSendBufferSize(64);
        System.out.println("<<ProxyReadWrite>>:   ReceiveBufferSize=" + socket.getReceiveBufferSize());
        System.out.println("<<ProxyReadWrite>>:   SendBufferSize=" + socket.getSendBufferSize());
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();
    }
    
    public void run() {
        boolean isRunning = true;
        long sleepTime = 250L;
        try {
            System.out.println("ProxyReadWrite.run():  SoTimeout=" + this.socket.getSoTimeout());
            this.socket.setSoTimeout(50);
            System.out.println("ProxyReadWrite.run():  SoTimeout=" + this.socket.getSoTimeout());
            byte[] bytes = new byte[1024];
            int count = 0;
            while (isRunning) {
                try {
                    count = 0;
                    final long wakeTime = System.currentTimeMillis() + sleepTime;
                    while (count == 0) {
                        try {
                            count = this.in.read(bytes);
                            sleepTime = 250L;
                        }
                        catch (SocketTimeoutException timeoutException) {
                            if (System.currentTimeMillis() > wakeTime) {
                                sleepTime *= 2L;
                                break;
                            }
                            continue;
                        }
                    }
                }
                catch (SocketException socketException) {
                    if (!"Socket closed".equals(socketException.getMessage())) {
                        System.out.println("Error reading data from server: " + socketException);
                    }
                    this.closeServer();
                    break;
                }
                if (count == -1) {
                    this.closeServer();
                    break;
                }
                final HttpURLConnection urlConnection = this.configurationManager.getURLConnection();
                final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
                out.write("action=readwrite");
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
                final InputStream serverInputStream = urlConnection.getInputStream();
                bytes = new byte[1024];
                count = 0;
                boolean isFirst = true;
                int startIndex = 1;
                while (true) {
                    count = serverInputStream.read(bytes);
                    if (count == -1) {
                        break;
                    }
                    if (isFirst && count > 0 && bytes[0] == 0) {
                        isRunning = false;
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
            }
            this.socket.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
