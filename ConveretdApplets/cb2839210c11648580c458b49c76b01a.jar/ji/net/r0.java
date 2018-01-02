// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.document.ad;
import java.net.URLConnection;
import ji.util.i;
import java.net.URL;

public class r0 extends jiFeedbackServletCommunication
{
    public r0(final URL url, final String s) {
        super(url, s);
    }
    
    public final String getLastError() {
        String s = super.getLastError();
        if (s == null) {
            s = this.a(1069);
        }
        return s;
    }
    
    protected final String b() {
        return "Annotation burn";
    }
    
    protected final boolean d() {
        return i.c(79) || i.c(126);
    }
    
    protected final boolean c() {
        return i.c(203);
    }
    
    protected void a(final int n, int n2, String s) {
        int n3 = -1;
        switch (n) {
            case 1: {
                n3 = 3;
                n2 = 0;
                s = this.a(1070);
                break;
            }
            case 2: {
                n3 = 3;
                n2 = 0;
                s = this.a(1071);
                break;
            }
            case 3: {
                n3 = 3;
                s = this.a(1072);
                break;
            }
            case 4: {
                n3 = 3;
                break;
            }
            case 5: {
                n3 = 3;
                n2 = 100;
                s = this.a(1061);
                break;
            }
            case 6: {
                n3 = 0;
                n2 = 100;
                s = this.a(1061);
                break;
            }
            case 7: {
                n3 = 1;
                n2 = 100;
                break;
            }
        }
        this.b(n3, n2, s);
    }
    
    protected String a() {
        return this.a(1073);
    }
    
    protected final void a(final URLConnection urlConnection, final ad ad, final boolean b, final String s) throws Exception {
        super.a(urlConnection, ad, b, s);
        urlConnection.setRequestProperty("X-Client-BurnPDFToPDF", ad.bi(3) ? "true" : "false");
    }
}
