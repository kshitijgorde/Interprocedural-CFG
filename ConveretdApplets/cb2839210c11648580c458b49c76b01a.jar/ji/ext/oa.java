// 
// Decompiled by Procyon v0.5.30
// 

package ji.ext;

import ji.util.i;
import ji.io.h;
import ji.util.e;
import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import java.lang.reflect.Method;
import ji.util.m;

public class oa implements n
{
    private Object a;
    private m b;
    private static boolean c;
    private static boolean d;
    private Method e;
    private Method f;
    private Method g;
    private Method h;
    private Method i;
    
    public oa() {
        this.a = null;
        this.b = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }
    
    private final boolean a(final Component component, final String s, final af af) {
        try {
            if (!ji.util.d.ly && !oa.d) {
                try {
                    oa.d = true;
                    return w.a(component, s, af, this, "jiex2", 767, 1, false) && (ji.util.d.ly = true);
                }
                finally {
                    oa.d = false;
                }
            }
            if (ji.util.d.ly && !oa.d) {
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean a(final Object[] array) throws Exception {
        this.a();
        this.b.c("jiCheck");
        return true;
    }
    
    public final void a() throws Exception {
        try {
            if (this.a == null) {
                this.a = ji.util.d.a2("daeja.ji.jiex2");
            }
            if (this.a != null && this.b == null) {
                this.b = new m(this.a);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            throw ex;
        }
    }
    
    public boolean h(final String s) {
        try {
            if (this.b != null) {
                this.b.b();
                this.b = null;
            }
            this.a = null;
        }
        catch (Throwable t) {
            ji.util.d.a(t);
        }
        return true;
    }
    
    private boolean a(final String s, final String s2) {
        if (!ji.util.e.z(s)) {
            ji.io.h.d(s, "Attempt to call a Vista Specific Method: ".concat(String.valueOf(String.valueOf(s2))));
            return false;
        }
        return true;
    }
    
    private boolean b(final String s, final String s2) {
        if (!ji.util.e.x(s)) {
            ji.io.h.d(s, "Attempy tp call a Windows 200 or later method: ".concat(String.valueOf(String.valueOf(s2))));
        }
        return true;
    }
    
    public void a(final boolean b, final String s, final Component component) {
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!oa.c) {
                    oa.c = this.a(component, s, null);
                }
                this.a();
                if (this.b != null) {
                    if (this.e == null) {
                        this.e = this.b.b("setDebugging");
                    }
                    this.b.a(this.e, new Boolean(b));
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final boolean a(final String s, final Component component) {
        if (!this.a(s, "isInternetExplorerProtectedMode")) {
            return false;
        }
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!oa.c) {
                    oa.c = this.a(component, s, null);
                }
                this.a();
                if (this.f == null) {
                    this.f = this.b.b("isIEProtectedModeProcess");
                }
                return (boolean)this.b.a(this.f);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public final String b(final String s, final Component component) {
        if (!this.a(s, "getIEProtectedModeWriteablePath")) {
            return null;
        }
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!oa.c) {
                    oa.c = this.a(component, s, null);
                }
                this.a();
                if (this.g == null) {
                    this.g = this.b.b("getIEProtectedModeWriteablePath");
                }
                final Object a = this.b.a(this.g);
                if (a instanceof String) {
                    return (String)a;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return null;
    }
    
    public final boolean a(final String s, final Component component, final String s2, final boolean b) {
        if (!this.b(s, "addFontResourceEx")) {
            return false;
        }
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!oa.c) {
                    oa.c = this.a(component, s, null);
                }
                this.a();
                if (this.h == null) {
                    this.h = this.b.b("addFontResourceEx");
                }
                return (boolean)this.b.a(this.h, s2, new Boolean(b));
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    public final boolean b(final String s, final Component component, final String s2, final boolean b) {
        if (!this.b(s, "removeFontResourceEx")) {
            return false;
        }
        try {
            if (ji.util.i.c(7) && ji.util.e.t()) {
                if (!oa.c) {
                    oa.c = this.a(component, s, null);
                }
                this.a();
                if (this.i == null) {
                    this.i = this.b.b("removeFontResourceEx");
                }
                return (boolean)this.b.a(this.i, s2, new Boolean(b));
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return false;
    }
    
    static {
        oa.c = false;
        oa.d = false;
    }
}
