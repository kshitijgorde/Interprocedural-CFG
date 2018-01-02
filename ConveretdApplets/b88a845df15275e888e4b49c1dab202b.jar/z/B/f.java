// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.io.FileInputStream;
import java.util.ResourceBundle;

public class f
{
    private static ResourceBundle A;
    static /* synthetic */ Class class$z$B$f;
    
    private static ResourceBundle A() {
        InputStream resourceAsStream = null;
        try {
            try {
                resourceAsStream = ((f.class$z$B$f == null) ? (f.class$z$B$f = class$("z.B.f")) : f.class$z$B$f).getResourceAsStream("properties");
            }
            catch (Exception ex2) {}
            if (resourceAsStream == null) {
                resourceAsStream = new FileInputStream("properties");
            }
            if (resourceAsStream != null) {
                return new PropertyResourceBundle(resourceAsStream);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String A(final String s) {
        if (f.A == null) {
            f.A = A();
        }
        return f.A.getString(s);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
