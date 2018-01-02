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
    public Socket cl;
    public InputStream az;
    public OutputStream ay;
    
    public n(final boolean b, final String s, final int n) throws IOException {
        if (b) {
            try {
                PrivilegeManager.enablePrivilege("TerminalEmulator");
            }
            catch (Exception ex2) {
                System.out.println("No previlege to open connection");
            }
        }
        try {
            this.cl = new Socket(s, n);
            this.az = this.cl.getInputStream();
            this.ay = this.cl.getOutputStream();
        }
        catch (IOException ex) {
            this.bd("Failed to connect " + s + " " + n);
            throw new IOException(ex.toString());
        }
    }
    
    public final void bd(final String s) {
        System.out.println(s);
        this.az();
    }
    
    public final void az() {
        try {
            if (this.ay != null) {
                this.ay.close();
            }
            if (this.az != null) {
                this.az.close();
            }
            if (this.cl != null) {
                this.cl.close();
            }
        }
        catch (IOException ex) {}
    }
    
    public final InputStream bc() {
        return this.az;
    }
    
    public final OutputStream bb() {
        return this.ay;
    }
}
