import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.DatagramPacket;
import java.net.BindException;
import java.net.DatagramSocket;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a extends Thread
{
    private c a;
    
    public a(final c a) {
        super("UDPThread");
        this.a = a;
    }
    
    public final void run() {
        try {
            if (this.a.k == null || !this.a.k.isBound()) {
                this.a.k = new DatagramSocket(this.a.b());
            }
            this.a.k.setSoTimeout(1000);
        }
        catch (BindException ex2) {
            e.c("UDP-S", "Couldnt bind to port " + this.a.b());
            return;
        }
        catch (Exception ex3) {
            e.c("UDP-S", "other error");
            return;
        }
        if (this.a.k == null || !this.a.k.isBound()) {
            e.c("UDP-S", "UDP test failed - socket not bound");
            return;
        }
        e.a("UDP-S", "Listening on UDP port " + this.a.b());
        try {
            while (this.a.j) {
                try {
                    final DatagramPacket datagramPacket = new DatagramPacket(new byte[512], 512);
                    this.a.k.receive(datagramPacket);
                    final String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                    e.d("UDP-S", "received: " + s);
                    if (s.startsWith("UDP")) {
                        e.a("UDP-S", "UDP answer received");
                        this.a.b(s);
                    }
                    if (s.startsWith("OPTIONS")) {
                        e.a("SIP-S", "SIP OPTIONS received");
                        e.d("SIP-S", "received: " + s);
                        this.a.d(s);
                        this.a.a();
                    }
                    if (!s.startsWith("SIP")) {
                        continue;
                    }
                    e.a("SIP-S", "SIP REGISTER reply received");
                    e.d("SIP-S", "received: " + s);
                }
                catch (SocketTimeoutException ex4) {}
            }
        }
        catch (IOException ex) {
            e.c("UDP-S", "UDP test failed");
            e.c("UDP-S", ex.getMessage());
        }
        this.a.k.close();
        e.a("UDP-S", "UDP Thread destroyed");
    }
}
