import java.io.OutputStream;
import java.io.InputStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class ck extends Socket
{
    public String nw;
    public int nv;
    public cn nu;
    
    private ck(final String s, final int n, final String s2, final int n2) throws IOException, UnknownHostException {
        super(s2, n2);
        this.nw = this.nw;
        this.nv = this.nv;
    }
    
    public static ck nn(final String s, final int n, final String s2, final int n2, String s3, final as as, final String s4) throws IOException, UnknownHostException {
        ck ck = new ck(s, n, s2, n2);
        int nu;
        try {
            final InputStream inputStream = ck.getInputStream();
            final OutputStream outputStream = ck.getOutputStream();
            final cn cn = new cn();
            if (s3 == null) {
                s3 = "";
            }
            cn.nx("CONNECT " + s3 + s + ":" + n + " HTTP/1.0");
            cn.nv("User-Agent", s4);
            cn.nv("Pragma", "No-Cache");
            cn.nv("Proxy-Connection", "Keep-Alive");
            cn.hz(outputStream);
            (ck.nu = new cn(inputStream)).nw("server");
            if (ck.nu.nu() == 407 && as != null) {
                final String ns = ck.nu.ns();
                String nr = ck.nu.nr();
                if (nr == null) {
                    nr = "";
                }
                if (!"basic".equalsIgnoreCase(ns)) {
                    if ("digest".equalsIgnoreCase(ns)) {
                        throw new IOException("We don't support 'Digest' HTTP Authentication");
                    }
                    throw new IOException("Unknown HTTP Authentication method '" + ns + "'");
                }
                else {
                    ck.close();
                    ck = new ck(s, n, s2, n2);
                    final InputStream inputStream2 = ck.getInputStream();
                    final OutputStream outputStream2 = ck.getOutputStream();
                    cn.nt(as.ge("HTTP Proxy", nr), as.gd("HTTP Proxy", nr));
                    cn.hz(outputStream2);
                    ck.nu = new cn(inputStream2);
                }
            }
            nu = ck.nu.nu();
        }
        catch (SocketException ex) {
            throw new SocketException("Error communicating with proxy server " + s2 + ":" + n2 + " (" + ex.getMessage() + ")");
        }
        if (nu < 200 || nu > 299) {
            throw new cl("Proxy tunnel setup failed: " + ck.nu.ny());
        }
        return ck;
    }
    
    public final String toString() {
        return "WebProxyTunnelSocket[addr=" + this.getInetAddress() + ",port=" + this.getPort() + ",localport=" + this.getLocalPort() + "]";
    }
}
