// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.io.IOException;
import netscape.security.PrivilegeManager;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import java.io.FileInputStream;
import java.io.InputStream;

public class az extends InputStream
{
    FileInputStream a;
    boolean b;
    boolean c;
    av d;
    String e;
    String f;
    
    private final void a() {
        this.b = (g.a() && !g.b());
    }
    
    private final void a(final String s, final String s2) throws Exception {
        if (i.c(185) && i.c(198) && this.d == null && ji.util.e.u(s2) && ji.util.d.a0(s2)) {
            this.d = new av(s, "r", s2);
            if (!this.d.a()) {
                this.d.h();
                this.d = null;
            }
            else {
                this.c = true;
            }
        }
    }
    
    public az(final String e, final String f) throws Exception {
        this.a = null;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = null;
        this.f = null;
        this.a(this.e = e, this.f = f);
        if (!this.c) {
            this.a();
            try {
                if (this.b && !g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                g.r = true;
            }
            this.a = new FileInputStream(e);
        }
    }
    
    public final int read() throws IOException {
        if (this.c) {
            return this.d.b();
        }
        try {
            if (this.b && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.read();
    }
    
    public final int read(final byte[] array) throws IOException {
        if (this.c) {
            return this.d.a(array, 0, array.length);
        }
        try {
            if (this.b && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.read(array);
    }
    
    public final int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this.c) {
            return this.d.a(array, n, n2);
        }
        try {
            if (this.b && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.read(array, n, n2);
    }
    
    public final long skip(final long n) throws IOException {
        if (this.c) {
            final long g = this.d.g();
            this.d.b(g + n);
            return (int)(this.d.g() - g);
        }
        try {
            if (this.b && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.skip(n);
    }
    
    public final int available() throws IOException {
        if (this.c) {
            return (int)(this.d.f() - this.d.g());
        }
        try {
            if (this.b && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.available();
    }
    
    public final void close() throws IOException {
        if (this.c && this.d != null) {
            this.d.h();
        }
        else {
            try {
                if (this.b && !g.r) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                g.r = true;
            }
            this.a.close();
        }
        this.f = null;
        this.e = null;
    }
    
    protected void finalize() throws IOException {
        this.close();
    }
}
