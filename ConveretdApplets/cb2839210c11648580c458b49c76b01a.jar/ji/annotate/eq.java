// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.util.m;
import ji.io.h;
import ji.util.d;
import java.lang.reflect.Method;

public class eq
{
    private Method a;
    private Method b;
    private Object c;
    private String d;
    static /* synthetic */ Class e;
    
    public eq(final Class clazz, final String d) {
        this.d = null;
        try {
            this.d = d;
            if (ji.util.d.cs()) {
                h.d(d, "Obtaining (1) editSecurity...");
            }
            this.a = m.a(clazz, "editSecurity", new Class[] { (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, Boolean.TYPE });
            if (ji.util.d.cs()) {
                h.d(d, "Obtaining (2) editSecurity...");
            }
            this.b = m.a(clazz, "editSecurity", new Class[] { (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e, (eq.e == null) ? (eq.e = class$("java.lang.String")) : eq.e });
            if (ji.util.d.cs()) {
                h.d(d, "Obtaining instance...");
            }
            this.c = clazz.newInstance();
            if (ji.util.d.cs()) {
                h.d(d, "Got instance...".concat(String.valueOf(String.valueOf(this.c))));
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cs()) {
                h.d(d, "Failed to access Filenet Ce Security library");
            }
        }
    }
    
    public String a(final String s, final String s2, final String s3, final boolean b) throws Exception {
        if (this.a != null && this.c != null) {
            return (String)this.a.invoke(this.c, s, s2, s3, new Boolean(b));
        }
        throw new Exception("Filenet CE security invoke instance is null");
    }
    
    public String a(final String s, final String s2, final String s3, final String s4) throws Exception {
        if (this.b != null && this.c != null) {
            return (String)this.b.invoke(this.c, s, s2, s3, s4);
        }
        throw new Exception("Filenet CE security invoke instance is null");
    }
    
    public void a() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
