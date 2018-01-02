import java.util.Hashtable;
import java.util.Properties;
import java.io.IOException;
import netscape.security.PrivilegeManager;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class n
{
    public Socket ck;
    public InputStream ay;
    public OutputStream ax;
    
    public n(final boolean b, final String s, final int n, final String s2) throws IOException {
        String substring = null;
        String substring2 = null;
        if (s2 != null && !s2.equals("")) {
            final int index = s2.indexOf(":");
            if (index > 0) {
                substring = s2.substring(0, index);
                substring2 = s2.substring(index + 1);
            }
        }
        if (b) {
            try {
                PrivilegeManager.enablePrivilege("30Capabilities");
            }
            catch (Exception ex2) {
                System.out.println("No previlege to open connection");
            }
        }
        try {
            if (substring != null && substring2 != null) {
                final Properties properties = System.getProperties();
                ((Hashtable<String, String>)properties).put("socksProxyHost", substring);
                ((Hashtable<String, String>)properties).put("socksProxyPort", substring2);
                System.setProperties(properties);
                System.out.println("setting socks server :" + substring + ", port at " + substring2);
            }
            this.ck = new Socket(s, n);
            this.ay = this.ck.getInputStream();
            this.ax = this.ck.getOutputStream();
            if (substring != null && substring2 != null) {
                final Properties properties2 = System.getProperties();
                properties2.remove("socksProxyHost");
                properties2.remove("socksProxyPort");
                System.setProperties(properties2);
            }
        }
        catch (IOException ex) {
            this.bg("Failed to connect " + s + " " + n);
            throw new IOException(ex.toString());
        }
    }
    
    public final void bg(final String s) {
        System.out.println(s);
        this.a1();
    }
    
    public final void a1() {
        try {
            if (this.ax != null) {
                this.ax.close();
            }
            if (this.ay != null) {
                this.ay.close();
            }
            if (this.ck != null) {
                this.ck.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public final InputStream bf() {
        return this.ay;
    }
    
    public final OutputStream be() {
        return this.ax;
    }
}
