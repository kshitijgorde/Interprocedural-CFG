import java.io.DataOutputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class tunnel
{
    private static long seq;
    groupboard gb;
    int id;
    ByteArrayOutputStream outbuf;
    ByteArrayInputStream inbuf;
    int port;
    int tunnel_port;
    String server_address;
    Object mutex;
    String protocol;
    String cgi;
    
    tunnel(final groupboard gb) {
        this.gb = gb;
        this.id = -1;
        this.mutex = new Object();
        this.protocol = "http://";
        this.cgi = gb.get_arg("TUNNEL_CGI_URL");
        if (null == this.cgi) {
            this.cgi = "/mp/gbproxy.cgi";
        }
    }
    
    OutputStream getOutputStream() {
        return this.outbuf;
    }
    
    InputStream getInputStream() {
        return this.inbuf;
    }
    
    boolean open(final String server_address, final int tunnel_port, final int port) {
        if (tunnel_port == 443) {
            this.protocol = "https://";
        }
        synchronized (this.mutex) {
            this.outbuf = new ByteArrayOutputStream();
            this.tunnel_port = tunnel_port;
            this.port = port;
            this.server_address = server_address;
            try {
                final URL url = new URL(this.protocol + server_address + ":" + tunnel_port + this.cgi + "?cmd=open?id=0?port=" + port + "?seq=" + tunnel.seq);
                ++tunnel.seq;
                final URLConnection openConnection = url.openConnection();
                openConnection.setDoInput(true);
                openConnection.setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                this.id = dataInputStream.readInt();
                dataInputStream.close();
            }
            catch (IOException ex) {
                System.out.println("Error opening tunnel connection: " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
    
    void get(final int n) {
        if (this.id == -1) {
            return;
        }
        final int n2 = 50000;
        final byte[] array = new byte[n2];
        int i = 0;
        if (null != this.inbuf) {
            i += this.inbuf.read(array, 0, n2);
        }
        if (i < 0) {
            i = 0;
        }
        while (i < n) {
            synchronized (this.mutex) {
                try {
                    final URL url = new URL(this.protocol + this.server_address + ":" + this.tunnel_port + this.cgi + "?cmd=get?id=" + this.id + "?port=" + this.port + "?seq=" + tunnel.seq);
                    ++tunnel.seq;
                    final URLConnection openConnection = url.openConnection();
                    openConnection.setDoInput(true);
                    openConnection.setUseCaches(false);
                    final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                    int j = 1;
                    while (j > 0) {
                        j = dataInputStream.read(array, i, n2 - i);
                        if (j > 0) {
                            i += j;
                        }
                    }
                    dataInputStream.close();
                }
                catch (IOException ex) {}
            }
            try {
                Thread.currentThread();
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
        this.inbuf = new ByteArrayInputStream(array, 0, i);
    }
    
    void post() {
        if (this.id == -1) {
            return;
        }
        if (this.outbuf.size() <= 0) {
            return;
        }
        synchronized (this.mutex) {
            if (this.id == -1) {
                return;
            }
            if (this.outbuf.size() <= 0) {
                return;
            }
            try {
                final URL url = new URL(this.protocol + this.server_address + ":" + this.tunnel_port + this.cgi + "?cmd=post?id=" + this.id + "?port=" + this.port + "?seq=" + tunnel.seq);
                ++tunnel.seq;
                final URLConnection openConnection = url.openConnection();
                openConnection.setDoOutput(true);
                openConnection.setAllowUserInteraction(true);
                openConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                final DataOutputStream dataOutputStream = new DataOutputStream(openConnection.getOutputStream());
                this.outbuf.writeTo(dataOutputStream);
                this.outbuf.reset();
                new DataInputStream(openConnection.getInputStream()).close();
                dataOutputStream.close();
            }
            catch (IOException ex) {}
        }
    }
    
    void close() {
        synchronized (this.mutex) {
            if (this.id == -1) {
                return;
            }
            try {
                final URL url = new URL(this.protocol + this.server_address + ":" + this.tunnel_port + this.cgi + "?cmd=close?id=" + this.id + "?port=0?seq=" + tunnel.seq);
                ++tunnel.seq;
                final URLConnection openConnection = url.openConnection();
                openConnection.setDoInput(true);
                new DataInputStream(openConnection.getInputStream()).close();
            }
            catch (IOException ex) {}
            this.id = -1;
        }
    }
    
    public void force_post() {
        this.gb.tunnel_thread.interrupt();
    }
    
    static {
        tunnel.seq = System.currentTimeMillis();
    }
}
