import java.util.Enumeration;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_E
{
    Vector a;
    String a;
    
    public rp_E() {
        this.a = new Vector();
    }
    
    public final boolean a(final rp_bZ rp_bZ) {
        final rp_eA rp_eA = new rp_eA();
        try {
            rp_eA.a(new BufferedReader(new InputStreamReader(rp_bZ.a("action=get-user-info"))));
            if (!rp_eA.a.equals("user-info")) {
                return false;
            }
        }
        catch (Exception ex) {
            System.out.println("User not logged in");
            return false;
        }
        final Enumeration elements = rp_eA.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2;
            if ((rp_eA2 = elements.nextElement()).a.equals("user")) {
                rp_bZ.b(rp_eA2.a("access-level", (String)null));
                final Enumeration elements2 = rp_eA2.a.elements();
                while (elements2.hasMoreElements()) {
                    final rp_eA rp_eA3;
                    if ((rp_eA3 = elements2.nextElement()).a.equals("email")) {
                        final String b = rp_eA3.b;
                        rp_C.a(4, "UserInfo.user.email=" + b);
                        if (b == null || b.length() <= 5) {
                            continue;
                        }
                        this.a.addElement(b);
                    }
                }
            }
            if (rp_eA2.a.equals("client")) {
                final Enumeration elements3 = rp_eA2.a.elements();
                while (elements3.hasMoreElements()) {
                    final rp_eA rp_eA4;
                    if ((rp_eA4 = elements3.nextElement()).a.equals("name")) {
                        this.a = rp_eA4.b;
                        rp_C.a(4, "UserInfo.client.name=" + this.a);
                    }
                }
            }
        }
        return true;
    }
}
