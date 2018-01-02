import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;

// 
// Decompiled by Procyon v0.5.30
// 

public class br extends y
{
    public boolean k_;
    public boolean kz;
    public String ky;
    public InetAddress kx;
    public byte[][] kw;
    public static int kv;
    public static Object ku;
    
    public br(final Socket socket, final int n, final int n2, final b7 b7) throws IOException {
        super(socket, n, n2, b7);
        this.k_ = b7.lr().lh;
        if (this.k_) {
            final int lg = b7.lr().lg;
            final byte[] address = b7.lr().ln().getAddress();
            final int n3 = address[0] & 0xFF;
            final int n4 = address[1] & 0xFF;
            final int n5 = address[2] & 0xFF;
            final int n6 = address[3] & 0xFF;
            this.kw = new byte[10][1];
            for (int i = 0; i < 10; ++i) {
                this.kw[i] = ("PORT " + n3 + "," + n4 + "," + n5 + "," + n6 + "," + (lg + i >>> 8 & 0xFF) + "," + (lg + i & 0xFF) + "\n").getBytes();
            }
        }
        this.kx = b7.lr().ll();
        byte[] array = b7.lr().ll().getAddress();
        if (array[0] == 0) {
            array = InetAddress.getLocalHost().getAddress();
        }
        this.ky = String.valueOf(array[0] & 0xFF) + "," + (array[1] & 0xFF) + "," + (array[2] & 0xFF) + "," + (array[3] & 0xFF);
    }
    
    public final boolean js(final String s, final int[] array) {
        boolean b = true;
        int n = 0;
        try {
            for (int i = 0; i < 6; ++i) {
                int n2;
                if (i == 5) {
                    n2 = s.indexOf(41, n);
                    if (n2 == -1) {
                        n2 = s.indexOf(13, n);
                    }
                    else if (n2 == -1) {
                        n2 = s.indexOf(10, n);
                    }
                }
                else {
                    n2 = s.indexOf(44, n);
                }
                final String substring = s.substring(n, n2);
                n = n2 + 1;
                array[i] = Integer.parseInt(substring);
            }
        }
        catch (Exception ex) {
            b = false;
        }
        return b;
    }
    
    public final void ef(final ay ay) {
        final String s = new String(ay.hy(), ay.hw(), ay.hv());
        if (s.startsWith("PASV") || s.startsWith("pasv")) {
            this.kz = true;
        }
        else if (s.startsWith("PORT ") || s.startsWith("port ")) {
            if (!this.k_) {
                super.fq.h9("Ftp-client is using PORT commands, either \nenable 'passive mode' in the ftp-client or \nenable 'ftp PORT' in 'SSH Settings' and reconnect.");
            }
            else {
                final String substring = s.substring(5);
                final int[] array = new int[6];
                if (this.js(substring, array)) {
                    final byte[] hy = ay.hy();
                    final byte[] array2;
                    final String string;
                    synchronized (br.ku) {
                        array2 = this.kw[br.kv];
                        string = "#FTP" + br.kv;
                        br.kv = (br.kv + 1) % 10;
                    }
                    // monitorexit(br.ku)
                    final int length = array2.length;
                    int n = ay.hw() - 4;
                    hy[n++] = (byte)(length >>> 24 & 0xFF);
                    hy[n++] = (byte)(length >>> 16 & 0xFF);
                    hy[n++] = (byte)(length >>> 8 & 0xFF);
                    hy[n++] = (byte)(length & 0xFF);
                    System.arraycopy(array2, 0, hy, n, length);
                    ay.hu(n + length);
                    super.fq.j1(string, String.valueOf(array[0]) + "." + array[1] + "." + array[2] + "." + array[3], array[4] << 8 | array[5]);
                }
                else {
                    super.fq.h9("Bug in SSHFtpTunnel (PORT), please report: " + substring);
                }
            }
        }
        super.ef(ay);
    }
    
    public final void ee(final ay ay) {
        bm lw = null;
        final String s = new String(ay.hy(), ay.hw(), ay.hv());
        if (this.kz && s.startsWith("227 ")) {
            this.kz = false;
            final String substring = s.substring(27);
            final int[] array = new int[6];
            if (this.js(substring, array)) {
                final String string = String.valueOf(array[0]) + "." + array[1] + "." + array[2] + "." + array[3];
                final int n = array[4] << 8 | array[5];
                try {
                    lw = super.fq.lw(this.kx.getHostAddress(), 0, string, n, "general");
                }
                catch (IOException ex) {
                    super.fq.h9("Error in FtpTunnel: " + ex.toString());
                }
                lw.i4(true);
                final int i7 = lw.i7();
                ay.hx(("227 Entering Passive Mode (" + this.ky + "," + (i7 >>> 8 & 0xFF) + "," + (i7 & 0xFF) + ")\n").getBytes());
            }
            else {
                super.fq.h9("Bug in SSHFtpTunnel (PASV), please report: " + substring);
            }
        }
        super.ee(ay);
    }
    
    public final String d5() {
        if (super.fp != null) {
            return String.valueOf(super.fp.getInetAddress().getHostAddress()) + ":" + super.fp.getPort() + " <-ftp-> " + super.el() + ":" + super.fp.getLocalPort() + " <-ssh-> " + super.fj;
        }
        return "< N/A >";
    }
    
    static {
        br.ku = new Object();
    }
}
