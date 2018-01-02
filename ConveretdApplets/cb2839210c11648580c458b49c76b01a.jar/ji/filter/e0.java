// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.document.ad;
import ji.util.e;
import java.awt.Color;
import ji.v1event.a6;
import ji.image.ds;
import ji.io.ac;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.awt.Dimension;
import java.io.RandomAccessFile;
import netscape.security.PrivilegeManager;
import ji.image.dx;
import ji.v1event.af;
import java.awt.Component;
import ji.res.ay;
import ji.io.h;
import ji.util.d;
import ji.io.at;
import java.awt.Image;
import ji.util.m;

public class e0 extends cj
{
    public static m a;
    public static Object b;
    private boolean c;
    private Image d;
    private static at e;
    private static String f;
    private boolean g;
    private String h;
    private String i;
    
    public final String getFilterName() {
        return "jiFilterExternal";
    }
    
    public void a(final String i) {
        this.i = i;
    }
    
    public e0(final String s) {
        this.c = false;
        this.d = null;
        this.g = false;
        this.h = null;
        this.i = null;
        try {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal: ".concat(String.valueOf(String.valueOf(s))));
            }
            e0.b = ji.util.d.a2(s);
            if (e0.b != null) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, String.valueOf(String.valueOf(new StringBuffer("FilterExternal: ").append(s).append(" LOADED"))));
                }
                e0.a = new m(e0.b);
            }
            else if (ji.util.d.dr()) {
                ji.io.h.d(this.i, String.valueOf(String.valueOf(new StringBuffer("FilterExternal: ").append(s).append(" NOT LOADED"))));
            }
            if (e0.e == null) {
                e0.e = new at(this.i);
            }
        }
        catch (Exception ex) {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal: ".concat(String.valueOf(String.valueOf(ex))));
            }
            ji.util.d.a(ex, ay.a(), null, null, this.i);
        }
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (e0.e == null) {
            e0.e = new at(this.i);
        }
        dx dx = null;
        Object o = null;
        if (e0.b == null) {
            return null;
        }
        try {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: Start");
            }
            final Method b = e0.a.b("loadImageHeader");
            if (b == null) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal IH: Can't find loadImageHeader method");
                }
                System.out.println("**METHOD ERROR**   Method: loadImageHeader(String filename) does not exists in class ExternalDecoder");
                return null;
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: Found loadImageHeader method");
            }
            this.h = fh.f;
            if (this.c()) {
                PrivilegeManager.enablePrivilege("Netcaster");
            }
            o = new RandomAccessFile(this.h, "r");
            final Dimension dimension = (Dimension)e0.a.a(b, o);
            if (dimension == null) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal IH: Can't invoke loadImageHeader method or method returned null");
                }
                return null;
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: Invoked loadImageHeader method");
            }
            dx = new dx();
            dx.u = 1;
            dx.m = dimension.width;
            dx.n = dimension.height;
            dx.i = fh.b.v();
            dx.bk = new Hashtable(5);
            if (!fh.i) {
                dx.bk.put("Width", "".concat(String.valueOf(String.valueOf(dx.m))));
                dx.bk.put("Height", "".concat(String.valueOf(String.valueOf(dx.n))));
            }
            dx.z = 8;
            dx.aa = 3;
            dx.am = dx.z * dx.aa;
            dx.an = 0;
            dx.ar = false;
            dx.at = 0;
            dx.l = "External RGB";
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: numPages=".concat(String.valueOf(String.valueOf(dx.u))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: width=".concat(String.valueOf(String.valueOf(dx.m))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: height=".concat(String.valueOf(String.valueOf(dx.n))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: fileLength=".concat(String.valueOf(String.valueOf(dx.i))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: bitsPerSample=".concat(String.valueOf(String.valueOf(dx.z))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: samplesPerPixel=".concat(String.valueOf(String.valueOf(dx.aa))));
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH: pixelDepth=".concat(String.valueOf(String.valueOf(dx.am))));
            }
        }
        catch (Exception ex) {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal IH Error: ".concat(String.valueOf(String.valueOf(ex))));
            }
            ex.printStackTrace();
            return null;
        }
        finally {
            try {
                if (o != null) {
                    if (this.c()) {
                        PrivilegeManager.enablePrivilege("Netcaster");
                    }
                    ((RandomAccessFile)o).close();
                }
            }
            catch (Exception ex2) {}
        }
        return dx;
    }
    
    public final boolean e() {
        return false;
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        this.a(fh.b, fh.c, fh.d, fh.g, fh.o);
    }
    
    public final void a(final ac ac, final ds ds, final dx dx, final af af, final Component component) throws Exception {
        super.c = true;
        try {
            super.b = false;
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal Fill: Start");
            }
            final Method b = e0.a.b("getLine");
            RandomAccessFile randomAccessFile = null;
            if (b == null) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal Fill: Can't find getLine method");
                }
                System.out.println("**METHOD ERROR**   Method: getLine(int line) does not exists in class ExternalDecoder");
                return;
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.i, "FilterExternal Fill: Found getLine method");
            }
            ds.b(4, false, component);
            try {
                if (this.c()) {
                    PrivilegeManager.enablePrivilege("Netcaster");
                }
                randomAccessFile = new RandomAccessFile(this.h, "r");
                System.currentTimeMillis();
                final long currentTimeMillis = System.currentTimeMillis();
                int n = dx.n;
                int n2 = 0;
                int min = ji.util.d.ar / 8;
                if (ji.util.d.em()) {
                    min = Math.min(dx.m * dx.n, 3145728);
                }
                int min2 = Math.min(Math.min(min / dx.m, dx.n), dx.n / 5);
                if (min2 < 1) {
                    min2 = 1;
                }
                final a6 a6 = new a6(this, 4, "");
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.i, "External Loading image ".concat(String.valueOf(String.valueOf(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis)))));
                }
                int n3 = new Color(192, 192, 192).getRGB();
                try {
                    if (ji.util.e.ao() != null) {
                        n3 = ji.util.e.ao().getRGB();
                    }
                    else if (component.getBackground() != null) {
                        n3 = component.getBackground().getRGB();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal Fill: Block size =".concat(String.valueOf(String.valueOf(min))));
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal Fill: Num lines in block =".concat(String.valueOf(String.valueOf(min2))));
                }
                while (n > 0 && !super.b) {
                    final int[] array = (int[])e0.a.a(b, new Integer(n2), new Integer(min2), randomAccessFile);
                    for (int i = 0; i < array.length; ++i) {
                        if ((array[i] & 0xFF000000) != 0xFF000000) {
                            array[i] = n3;
                        }
                    }
                    ds.a(array, min2 * dx.m, component, n2, min2 - 1, true);
                    n2 += min2;
                    n -= min2;
                    if (n < min2) {
                        min2 = n;
                    }
                    if (af != null) {
                        a6.a("".concat(String.valueOf(String.valueOf(100 * n2 / dx.n))));
                        af.a(a6);
                    }
                }
                final long currentTimeMillis2 = System.currentTimeMillis();
                ds.e(component);
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.i, String.valueOf(String.valueOf(new StringBuffer("External ").append(dx.n * dx.m * 4).append(" image prepared in ").append(ji.util.d.e(System.currentTimeMillis() - currentTimeMillis2)))));
                }
            }
            catch (Exception ex2) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.i, "FilterExternal Fill Error:".concat(String.valueOf(String.valueOf(ex2))));
                }
                ex2.printStackTrace();
            }
            finally {
                try {
                    if (randomAccessFile != null) {
                        if (this.c()) {
                            PrivilegeManager.enablePrivilege("Netcaster");
                        }
                        randomAccessFile.close();
                    }
                }
                catch (Exception ex3) {}
            }
        }
        finally {
            super.c = false;
        }
        if (super.b) {
            ds.a(component);
        }
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public final void abort(final dx dx) {
        try {
            if (ji.util.e.ai()) {
                super.b = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void close(final dx dx, final ad ad) {
        e0.b = null;
        if (e0.a != null) {
            try {
                e0.a.c("close");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            e0.a.b();
            e0.a = null;
        }
        this.g = false;
        if (this.d != null) {
            this.d.flush();
            this.d = null;
        }
    }
    
    private final boolean c() {
        return ji.util.d.av(this.i) && !ji.util.d.do();
    }
    
    static {
        e0.a = null;
        e0.b = null;
        e0.e = null;
        e0.f = null;
    }
}
