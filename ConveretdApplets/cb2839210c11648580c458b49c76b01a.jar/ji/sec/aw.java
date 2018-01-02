// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.io.IOException;
import netscape.security.PrivilegeManager;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class aw extends OutputStream
{
    FileOutputStream a;
    boolean b;
    static boolean c;
    boolean d;
    av e;
    String f;
    String g;
    
    private final void a() {
        this.b = (ji.sec.g.a() && !ji.sec.g.b());
    }
    
    private final void a(final String s, final String s2) throws Exception {
        if (i.c(185) && i.c(198) && this.e == null && ji.util.e.u(s2) && ji.util.d.a0(s2)) {
            this.e = new av(s, "rw", s2);
            if (!this.e.a()) {
                this.e.h();
                this.e = null;
            }
            else {
                this.d = true;
            }
        }
    }
    
    public aw(final String s, final String s2) throws Exception {
        this(s, false, s2);
    }
    
    public aw(final String f, final boolean b, final String g) throws Exception {
        this.a = null;
        this.b = false;
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a(this.f = f, this.g = g);
        if (!this.d) {
            this.a();
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            if (!b) {
                this.a = new FileOutputStream(f);
            }
            else {
                this.a = new FileOutputStream(f, b);
            }
        }
    }
    
    public final void write(final int n) throws IOException {
        if (this.d) {
            this.e.a(n);
        }
        else {
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            this.a.write(n);
        }
    }
    
    public final void write(final byte[] array) throws IOException {
        if (this.d) {
            this.e.a(array, 0, array.length, false);
        }
        else {
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            this.a.write(array);
        }
    }
    
    public final void write(final byte[] array, final int n, final int n2) throws IOException {
        if (this.d) {
            this.e.a(array, n, n2, false);
        }
        else {
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            this.a.write(array, n, n2);
        }
    }
    
    public final void flush() throws IOException {
        if (!this.d) {
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            this.a.flush();
        }
    }
    
    public final void close() throws IOException {
        if (this.d && this.e != null) {
            this.e.h();
        }
        else {
            try {
                if (this.b && !aw.c) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
            }
            catch (Exception ex) {
                aw.c = true;
            }
            this.a.close();
        }
        this.g = null;
        this.f = null;
    }
    
    protected void finalize() throws IOException {
        this.flush();
        this.close();
    }
    
    static {
        aw.c = false;
    }
}
