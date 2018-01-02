import java.util.MissingResourceException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fb
{
    private ResourceBundle a;
    private Properties a;
    
    public rp_fb() {
        this.a = null;
        this.a = null;
        this.a((Locale)null);
    }
    
    public rp_fb(final String s, final String s2) {
        this.a = null;
        this.a = null;
        this.a(new Locale(s, s2));
    }
    
    public rp_fb(final rp_fK rp_fK, final String s) {
        this.a = null;
        this.a = null;
        rp_C.a(4, "BasicMessages loaded using Properties.");
        this.a = new Properties();
        try {
            final InputStream b;
            if ((b = rp_fK.b(s)) != null) {
                this.a.load(b);
            }
        }
        catch (Exception ex) {
            rp_C.a(1, "Error loading properties: " + s);
        }
    }
    
    private void a(final Locale locale) {
        if (locale == null) {
            this.a = ResourceBundle.getBundle("BasicMessages");
            return;
        }
        this.a = ResourceBundle.getBundle("BasicMessages", locale);
    }
    
    public final String a(final String s) {
        return "<html>" + this.a(0, s) + "</html>";
    }
    
    public final String a(final int n, final String s) {
        if (this.a == null && this.a != null) {
            final String property;
            if ((property = this.a.getProperty(s)) == null) {
                rp_C.a(1, "String property not found: " + s);
                return "";
            }
            return property;
        }
        else {
            ResourceBundle a = null;
            switch (n) {
                case 0: {
                    a = this.a;
                    break;
                }
            }
            if (a == null) {
                System.out.println("Error: Unknown resource bundle (" + n + ")");
                return "";
            }
            try {
                return a.getString(s);
            }
            catch (MissingResourceException ex) {
                System.out.println("Error: missing resource (" + n + ") " + s);
                return "";
            }
        }
    }
}
