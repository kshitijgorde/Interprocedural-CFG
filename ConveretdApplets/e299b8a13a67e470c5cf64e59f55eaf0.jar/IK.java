import java.net.Socket;
import java.net.InetAddress;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class IK implements Runnable
{
    private boolean AS;
    private HK BS;
    private URL GS;
    private long ZS;
    private int ZM;
    
    private int CS(final InetAddress inetAddress, final int n) {
        Socket socket = null;
        try {
            this.ZS = JK.NN();
            final long fr = JK.FR();
            socket = new Socket(inetAddress, n);
            final long mp = JK.MP();
            this.ZS = JK.NN();
            return Math.max(1, (int)(mp - fr));
        }
        catch (Throwable t) {}
        finally {
            try {
                socket.close();
            }
            catch (Throwable t2) {}
        }
        try {
            socket.close();
        }
        catch (Throwable t3) {}
        return -1;
    }
    
    public int JO(final HK bs, final URL gs) {
        this.AS = true;
        this.BS = bs;
        this.GS = gs;
        final Thread thread = new Thread(this, VK("wKqT__`8rpp"));
        thread.start();
        this.ZS = JK.NN();
        while (thread.isAlive() && JK.NN() - this.ZS < 8000L) {
            try {
                thread.join(1000L);
            }
            catch (Throwable t) {}
        }
        this.AS = false;
        return this.ZM;
    }
    
    private void CM(final String s) {
        if (this.AS) {
            this.BS.CM(s);
        }
    }
    
    public void run() {
        this.ZS = JK.NN();
        InetAddress byName = null;
        try {
            byName = InetAddress.getByName(InetAddress.getByName(this.GS.getHost()).getHostAddress());
        }
        catch (Throwable t) {}
        if (byName != null) {
            this.ZS = JK.NN();
            final int port = this.GS.getPort();
            final int n = (port > 0) ? port : 80;
            this.CS(byName, n);
            int zm = -1;
            for (int n2 = 0; this.AS && n2 < 3; ++n2) {
                final int cs = this.CS(byName, n);
                if (cs > 0) {
                    this.CM(String.valueOf(VK("E")) + cs + VK("EWQ"));
                }
                zm = ((zm < 0) ? cs : Math.min(zm, cs));
            }
            if (zm > 0) {
                this.ZM = zm;
                this.CM(String.valueOf(VK("rpp(")) + zm + VK("E=") + byName + VK("+") + n + VK("<"));
            }
        }
    }
    
    private static String VK(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            final char c = charArray[i];
            if (c >= ' ' && c <= '~') {
                charArray[i] = (char)(32 + (10328849 - c) % 95);
            }
        }
        return new String(charArray);
    }
    
    public IK() {
        this.ZM = 0;
    }
}
