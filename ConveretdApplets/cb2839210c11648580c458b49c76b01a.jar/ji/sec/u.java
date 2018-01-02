// 
// Decompiled by Procyon v0.5.30
// 

package ji.sec;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import netscape.security.PrivilegeManager;
import ji.util.d;
import ji.util.e;
import ji.util.i;
import java.io.File;

public class u
{
    File a;
    String b;
    String c;
    boolean d;
    boolean e;
    boolean f;
    
    private final void a(final String s) throws Exception {
        try {
            if (!this.f && i.c(185) && i.c(198) && !this.e && ji.util.e.u(s) && ji.util.d.a0(s)) {
                if (g.a(s)) {
                    this.e = true;
                }
                else {
                    this.e = false;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.e = false;
            this.f = true;
        }
    }
    
    private final void b(final String s) throws Exception {
        this.a(s);
        if (!this.e) {
            this.d = (g.a() && !g.b());
        }
    }
    
    public u(final String s, final String s2) throws Exception {
        this(s, s2, true);
    }
    
    public u(final String b, final String c, final boolean b2) throws Exception {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = false;
        this.b = b;
        this.c = c;
        if (b2) {
            this.b(c);
        }
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (b != null && b.length() > 0) {
            this.a = new File(b);
        }
    }
    
    public final String a() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.getAbsolutePath();
    }
    
    public final String b() throws IOException {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
            return this.b;
        }
        return this.a.getCanonicalPath();
    }
    
    public final boolean c() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            boolean b = av.a(this.b, this.c);
            if (!b) {
                b = this.a.exists();
            }
            return b;
        }
        return this.a.exists();
    }
    
    public final boolean d() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            return !this.a.exists() || this.a.isFile();
        }
        return this.a.isFile();
    }
    
    public final boolean e() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            return !this.a.exists() || this.a.isDirectory();
        }
        return this.a.isDirectory();
    }
    
    public final long f() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            long n = av.d(this.b, this.c);
            if (n <= 0) {
                n = this.a.lastModified();
            }
            return n;
        }
        return this.a.lastModified();
    }
    
    public final long g() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            long n = av.c(this.b, this.c);
            if (n < 0) {
                n = this.a.length();
            }
            return n;
        }
        return this.a.length();
    }
    
    public final boolean h() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.mkdir();
    }
    
    public final boolean i() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.mkdirs();
    }
    
    public final String[] a(final FilenameFilter filenameFilter) {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.list(filenameFilter);
    }
    
    public final String[] j() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.list();
    }
    
    public final boolean k() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        if (this.e) {
            boolean b = av.b(this.b, this.c);
            if (!b) {
                b = this.a.delete();
            }
            return b;
        }
        return this.a.delete();
    }
    
    public final boolean equals(final Object o) {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.equals(o);
    }
    
    public final String toString() {
        try {
            if (this.d && !g.r) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
        }
        catch (Exception ex) {
            g.r = true;
        }
        return this.a.toString();
    }
    
    public final String l() {
        String s = "Unknown";
        try {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Test String".getBytes());
            final InputStreamReader inputStreamReader = new InputStreamReader(byteArrayInputStream);
            s = inputStreamReader.getEncoding();
            inputStreamReader.close();
            byteArrayInputStream.close();
        }
        catch (Exception ex2) {
            if (this.d && !g.r) {
                try {
                    PrivilegeManager.enablePrivilege("Netcaster");
                    PrivilegeManager.enablePrivilege("UniversalPropertyWrite");
                    final ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream("Test String".getBytes());
                    final InputStreamReader inputStreamReader2 = new InputStreamReader(byteArrayInputStream2);
                    s = inputStreamReader2.getEncoding();
                    inputStreamReader2.close();
                    byteArrayInputStream2.close();
                }
                catch (Exception ex) {
                    g.r = true;
                    ex.printStackTrace();
                }
            }
            else {
                ex2.printStackTrace();
            }
        }
        return s;
    }
}
