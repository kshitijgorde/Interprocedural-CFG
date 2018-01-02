import java.awt.Window;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_bg implements rp_fq
{
    private Vector a;
    
    public rp_bg(final InputStream inputStream) {
        this.a = new Vector();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                rp_C.a(10, "HttpResponse: " + line);
                this.a.add(line);
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            rp_C.a(4, "Error parsing response");
        }
    }
    
    public rp_bg(final String s) {
        this(rp_C.a(s));
    }
    
    public final boolean a() {
        return this.a.size() > 0 && this.a.get(0).equals("OK");
    }
    
    public final String a(final int n) {
        if (this.a.size() > n) {
            return this.a.get(n);
        }
        return null;
    }
    
    public final String a(final String s) {
        for (int i = 1; i < this.a.size(); ++i) {
            if (((String)this.a.get(i)).startsWith(s)) {
                return ((String)this.a.get(i)).substring(s.length());
            }
        }
        return null;
    }
    
    public final String a(final int n, final String s) {
        if (this.a.size() > 1) {
            String string = this.a.get(1);
            for (int i = 2; i < this.a.size(); ++i) {
                string = string + s + (String)this.a.get(i);
            }
            return string;
        }
        return null;
    }
    
    public final void a(final rp_fK rp_fK) {
        final String a = this.a(1, " \n");
        final rp_fb a2 = rp_au.a.a();
        rp_bd.a(rp_fK.a(), a2.a(0, "err"), a, a2.a(0, "cl"));
    }
}
