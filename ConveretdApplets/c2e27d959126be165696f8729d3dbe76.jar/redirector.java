import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class redirector implements Runnable
{
    private redirector companion;
    private Socket localSocket;
    private Socket remoteSocket;
    private InputStream from;
    private OutputStream to;
    private byte[] buffer;
    
    public redirector(final Socket local, final Socket remote) {
        this.companion = null;
        this.buffer = new byte[4096];
        try {
            this.localSocket = local;
            this.remoteSocket = remote;
            this.from = this.localSocket.getInputStream();
            this.to = this.remoteSocket.getOutputStream();
        }
        catch (Exception ex) {
            System.err.println("redirector: cannot get streams");
        }
    }
    
    public void couple(final redirector c) {
        this.companion = c;
        final Thread listen = new Thread(this);
        listen.start();
    }
    
    public void decouple() {
        this.companion = null;
    }
    
    public void run() {
        try {
            int count;
            while (this.companion != null && (count = this.from.read(this.buffer)) >= 0) {
                this.to.write(this.buffer, 0, count);
            }
        }
        catch (Exception ex) {
            System.err.println("redirector: connection lost");
        }
        try {
            this.from.close();
            this.to.close();
            this.localSocket.close();
            this.remoteSocket.close();
            if (this.companion != null) {
                this.companion.decouple();
            }
        }
        catch (Exception io) {
            System.err.println("redirector: error closing streams and sockets");
            io.printStackTrace();
        }
    }
}
