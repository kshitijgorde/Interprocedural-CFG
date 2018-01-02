// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.awt.c;
import java.util.EventObject;

public class a6 extends EventObject
{
    private int a;
    private int b;
    private String c;
    private c d;
    private boolean e;
    private boolean f;
    private boolean g;
    private int h;
    
    public a6(final Object o, final int a, final String c) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.d = new c("statusEvent:consumed");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.a = a;
        if (a == 4) {
            try {
                this.b = Integer.parseInt(c);
            }
            catch (NumberFormatException ex) {}
        }
        this.c = c;
    }
    
    public a6(final Object o, final int a, final String c, final boolean f) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.d = new c("statusEvent:consumed");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.a = a;
        if (a == 4) {
            try {
                this.b = Integer.parseInt(c);
            }
            catch (NumberFormatException ex) {}
        }
        this.c = c;
        this.f = f;
    }
    
    public a6(final Object o, final int a) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.d = new c("statusEvent:consumed");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.a = a;
        this.c = "";
        this.b = 0;
    }
    
    public a6(final Object o, final int a, final String c, final int b) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.d = new c("statusEvent:consumed");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.a = a;
        this.c = c;
        this.b = b;
    }
    
    public a6(final Object o, final int a, final int n) {
        super(o);
        this.a = 0;
        this.b = 0;
        this.d = new c("statusEvent:consumed");
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = 0;
        this.a = a;
        this.a(n);
    }
    
    public void a(final boolean g) {
        this.g = g;
    }
    
    public boolean a() {
        return this.g;
    }
    
    public void a(final int b) {
        this.g();
        if (this.a == 4 || this.a == 17) {
            this.c = Integer.toString(b);
            this.b = b;
            return;
        }
        throw new IllegalArgumentException("Int values can only be accepted with PERCENT events.");
    }
    
    public void b(final int h) {
        this.h = h;
    }
    
    public boolean b() {
        return this.f;
    }
    
    public int c() {
        return this.h;
    }
    
    public int d() {
        return this.a;
    }
    
    public String toString() {
        String s = "Unknown";
        switch (this.a) {
            case 1: {
                s = "UPDATE";
                break;
            }
            case 2: {
                s = "START";
                break;
            }
            case 3: {
                s = "END";
                break;
            }
            case 4: {
                s = "PERCENT";
                break;
            }
            case 5: {
                s = "PRINT";
                break;
            }
            case 6: {
                s = "PRINTEND";
                break;
            }
            case 7: {
                s = "SPOOLING";
                break;
            }
            case 8: {
                s = "UPDATE_PRIORITY";
                break;
            }
            case 9: {
                s = "RETRIEVAL_TEXT";
                break;
            }
            case 10: {
                s = "RETRIEVAL_TEXT_CLEAR";
                break;
            }
            case 11: {
                s = "BUSY";
                break;
            }
            case 12: {
                s = "NOTBUSY";
                break;
            }
            case 13: {
                s = "RETRIEVAL_TEXT_CLEAR_PRIORITY";
                break;
            }
            case 14: {
                s = "RETRIEVAL_PERCENT";
                break;
            }
            case 15: {
                s = "BUSY2";
                break;
            }
            case 16: {
                s = "NOTBUSY2";
                break;
            }
            case 17: {
                s = "RETRIEVAL_TEXT_AND_PERCENT";
                break;
            }
            case 19: {
                s = "REWORK_ANNOTATIONS_FROM_THUMBS";
                break;
            }
            case 20: {
                s = "REWORK_ANNOTATIONS_FROM_FULLPAGE";
                break;
            }
            case 21: {
                s = "RETRIEVAL_TEXT_PREVENT_HIDE";
                break;
            }
            case 22: {
                s = "RETRIEVAL_TEXT_ALLOW_HIDE";
                break;
            }
            case 23: {
                s = "PROGRESSIVE_DISPLAY";
                break;
            }
            case 24: {
                s = "NETCACHE_START";
                break;
            }
            case 25: {
                s = "NETCACHE_COMPLETE";
                break;
            }
            case 18: {
                s = "PAINT_TOOLBARS";
                break;
            }
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiStatusEvent(").append(s).append(", '").append(this.c).append("', ").append(this.getSource()).append(", ").append(this.f).append(")")));
    }
    
    public String e() {
        return this.c;
    }
    
    public int f() {
        return this.b;
    }
    
    public void a(final String c) {
        this.g();
        this.c = c;
    }
    
    public void a(final af af) {
        if (!this.d.a(af)) {
            this.d.c(af);
        }
    }
    
    public boolean b(final af af) {
        return this.d.a(af);
    }
    
    private void g() {
        this.d.c();
    }
}
