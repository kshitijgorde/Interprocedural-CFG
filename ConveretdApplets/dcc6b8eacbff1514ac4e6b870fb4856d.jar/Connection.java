import java.io.IOException;
import netscape.security.PrivilegeManager;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class Connection
{
    public Socket cf;
    public InputStream ax;
    public OutputStream aw;
    
    public Connection(final boolean b, final String s, final int n) throws IOException {
        if (b) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex2) {
                System.out.println("No previlege to open connection");
            }
        }
        try {
            this.cf = new Socket(s, n);
            this.ax = this.cf.getInputStream();
            this.aw = this.cf.getOutputStream();
        }
        catch (IOException ex) {
            this.bd("Failed to connect " + s + " " + n);
            throw new IOException(ex.toString());
        }
    }
    
    public final void bd(final String s) {
        System.out.println(s);
        this.a0();
    }
    
    public final void a0() {
        try {
            if (this.aw != null) {
                this.aw.close();
            }
            if (this.ax != null) {
                this.ax.close();
            }
            if (this.cf != null) {
                this.cf.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public final InputStream bc() {
        return this.ax;
    }
    
    public final OutputStream bb() {
        return this.aw;
    }
}
