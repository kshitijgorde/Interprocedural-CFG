// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Enumeration;
import java.util.Hashtable;

final class cN
{
    private int[] a;
    private String[] b;
    private int c;
    private int d;
    private boolean e;
    private Hashtable f;
    private static final Object g;
    
    public cN() {
        this.e = false;
        this.f = new Hashtable();
        this.a = new int[10];
        this.b = new String[10];
        this.c = 0;
        this.d = 0;
    }
    
    public final void a(final q q) {
        if (this.c >= this.a.length) {
            this.a = bz.a(this.a, this.a.length + 10);
        }
        this.a[this.c++] = b(q.b());
        if (this.d >= this.b.length) {
            this.b = bz.a(this.b, this.b.length + 10);
        }
        this.b[this.d++] = q.c();
    }
    
    public final boolean b(final q q) {
        for (int i = 0; i < this.d; ++i) {
            final Object value = this.f.get(this.b[i]);
            if (this.a[i] == 0) {
                this.f.put(this.b[i], Boolean.FALSE);
            }
            else if (value == null) {
                if (a(this.a[i])) {
                    boolean b = false;
                    switch (this.a[i]) {
                        case 1:
                        case 2:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 11:
                        case 12:
                        case 13:
                        case 14: {
                            b = true;
                            break;
                        }
                        default: {
                            b = false;
                            break;
                        }
                    }
                    if (b) {
                        this.f.put(this.b[i], Boolean.TRUE);
                    }
                    else {
                        this.f.put(this.b[i], Boolean.FALSE);
                    }
                }
                else {
                    this.f.put(this.b[i], cN.g);
                }
            }
            else if (value == cN.g && a(this.a[i])) {
                this.f.put(this.b[i], Boolean.FALSE);
            }
        }
        final Enumeration<String> keys = this.f.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (this.f.get(s) == cN.g) {
                this.f.put(s, Boolean.TRUE);
            }
        }
        return this.f.get(q.c());
    }
    
    public static boolean a(final String s) {
        switch (b(s)) {
            case 1:
            case 2:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private static boolean a(final int n) {
        switch (n) {
            case 1:
            case 2:
            case 6:
            case 7:
            case 8:
            case 13:
            case 14: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    private static int b(final String s) {
        if (s.equals("GET")) {
            return 2;
        }
        if (s.equals("POST")) {
            return 3;
        }
        if (s.equals("HEAD")) {
            return 1;
        }
        if (s.equals("PUT")) {
            return 4;
        }
        if (s.equals("DELETE")) {
            return 5;
        }
        if (s.equals("OPTIONS")) {
            return 6;
        }
        if (s.equals("TRACE")) {
            return 7;
        }
        if (s.equals("PROPFIND")) {
            return 8;
        }
        if (s.equals("PROPPATCH")) {
            return 9;
        }
        if (s.equals("MKCOL")) {
            return 10;
        }
        if (s.equals("COPY")) {
            return 11;
        }
        if (s.equals("MOVE")) {
            return 12;
        }
        if (s.equals("LOCK")) {
            return 13;
        }
        if (s.equals("UNLOCK")) {
            return 14;
        }
        return 0;
    }
    
    static {
        g = new Object();
    }
}
