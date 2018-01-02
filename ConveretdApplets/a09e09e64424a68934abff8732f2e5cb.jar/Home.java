import java.io.IOException;
import java.security.Permission;
import java.security.SecurityPermission;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Home extends Applet
{
    public void init() {
    }
    
    public void start() {
        boolean accept;
        try {
            final SecurityManager security = System.getSecurityManager();
            final SecurityPermission perm = new SecurityPermission("java.lang.RuntimePermission exitVM.0");
            security.checkPermission(perm);
            accept = true;
        }
        catch (Exception ex) {
            accept = false;
        }
        if (accept) {
            try {
                new NewRaco();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void stop() {
        System.out.println("fermetrure applet");
    }
}
