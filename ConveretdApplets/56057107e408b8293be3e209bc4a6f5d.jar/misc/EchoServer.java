// 
// Decompiled by Procyon v0.5.30
// 

package misc;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer implements Runnable
{
    private Socket m_Socket;
    
    public EchoServer(final Socket socket) {
        this.m_Socket = socket;
        new Thread(this).start();
    }
    
    public static void main(final String[] array) throws Exception {
        final ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            new EchoServer(serverSocket.accept());
        }
    }
    
    public void run() {
        try {
            final byte[] array = new byte[10000];
            final InputStream inputStream = this.m_Socket.getInputStream();
            final OutputStream outputStream = this.m_Socket.getOutputStream();
            inputStream.read(array, 0, 32);
            outputStream.write(array, 0, 40);
            int n = 0;
            int read;
            while ((read = inputStream.read(array)) > 0) {
                for (int i = 0; i < read; ++i) {
                    final int n2 = array[i] & 0xFF;
                    if (n2 != n % 256) {
                        System.out.println("EchoServer (" + this.m_Socket + ")read failure at position: " + n + " -- Read: " + n2 + " -- Expected: " + n % 256);
                    }
                    ++n;
                }
                outputStream.write(array, 0, read);
            }
            System.out.println("EchoServer (" + this.m_Socket + ")read/write " + n + " Bytes");
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        try {
            this.m_Socket.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
