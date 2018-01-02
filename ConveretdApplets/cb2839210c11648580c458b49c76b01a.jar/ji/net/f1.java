// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import ji.document.ad;
import java.net.URLConnection;
import ji.util.i;
import ji.res.s;
import java.net.URL;

public class f1 extends jiFeedbackServletCommunication
{
    private boolean a;
    
    public f1(final URL url, final String s) {
        super(url, s);
        this.a = false;
    }
    
    protected void a(final int n, int n2, String s) {
        int n3 = -1;
        switch (n) {
            case 1: {
                n3 = 3;
                n2 = 0;
                s = s.a(1326, super.f);
                break;
            }
            case 2: {
                n3 = 3;
                n2 = 0;
                s = s.a(1327, super.f);
                break;
            }
            case 8: {
                n3 = 4;
                break;
            }
            case 3: {
                n3 = 3;
                s = s.a(1328, super.f);
                break;
            }
            case 4: {
                n3 = 2;
                break;
            }
            case 5: {
                n3 = 3;
                n2 = 100;
                s = s.a(1329, super.f);
                break;
            }
            case 6: {
                n3 = 0;
                n2 = 100;
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
        return s.a(1331, super.f);
    }
    
    protected String b() {
        return "Streamer Text Search";
    }
    
    protected boolean c() {
        return true;
    }
    
    protected boolean d() {
        return i.c(79) || i.c(5);
    }
    
    protected void a(final URLConnection urlConnection, final ad ad, final boolean b, final String s) throws Exception {
        super.a(urlConnection, ad, b, s);
    }
}
