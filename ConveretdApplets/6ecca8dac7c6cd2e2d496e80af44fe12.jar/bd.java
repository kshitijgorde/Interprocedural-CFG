import java.net.MalformedURLException;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class bd
{
    String a;
    Image b;
    int c;
    URL d;
    AppletContext e;
    int f;
    int g;
    
    bd(String substring, final AppletContext e) {
        this.c = 500;
        this.d = null;
        this.e = e;
        try {
            if (substring == null) {
                return;
            }
            final int index = substring.indexOf(59);
            if (index < 0) {
                this.a = substring;
                return;
            }
            this.a = substring.substring(0, index);
            substring = substring.substring(index + 1);
            final int index2 = substring.indexOf(59);
            if (index2 < 0) {
                this.c = Integer.parseInt(substring);
                return;
            }
            this.c = Integer.parseInt(substring.substring(0, index2));
            this.d = new URL(substring.substring(index2 + 1));
        }
        catch (NumberFormatException ex) {
            this.c = 500;
        }
        catch (MalformedURLException ex2) {
            this.d = null;
        }
    }
    
    String a() {
        return this.a;
    }
    
    void a(final int f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    void a(final Image b) {
        this.b = b;
    }
    
    Image b() {
        return this.b;
    }
    
    int c() {
        return this.g;
    }
    
    int d() {
        return this.f;
    }
    
    AppletContext e() {
        return this.e;
    }
}
