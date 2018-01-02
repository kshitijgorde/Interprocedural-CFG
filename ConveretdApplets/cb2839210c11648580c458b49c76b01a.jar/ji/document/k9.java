// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import java.util.Hashtable;
import ji.awt.ax;
import ji.annotate.df;
import ji.io.h;
import ji.annotate.dg;
import ji.image.dx;
import ji.util.d;
import ji.filter.output.jiImageSaveFailedException;
import java.util.Properties;
import ji.burn.jiBurnerListener;
import java.io.File;
import ji.applet.jiApplet;
import java.applet.Applet;
import java.io.PrintWriter;

public class k9 extends ad
{
    static boolean a;
    private String b;
    private String c;
    
    public k9(final String s, final String s2, final PrintWriter printWriter, final String b) {
        super(false, null, null, s, null, s2, b, true);
        this.b = "";
        this.c = "";
        this.b = b;
    }
    
    public final void a(final boolean[] array) {
        super.a(array);
    }
    
    public String a(final int n, final File file, final jiBurnerListener jiBurnerListener, final String s, final int n2, final int n3, final int n4, final Properties properties, final long n5, final String s2, final boolean b, final boolean b2, final boolean b3) throws jiImageSaveFailedException {
        final dx w = super.g.w();
        if (w == null) {
            throw new jiImageSaveFailedException("Empty header");
        }
        String s3 = null;
        if (w.h() && this.bi(3)) {
            s3 = "pdf";
        }
        else {
            switch (n4) {
                case 3: {
                    s3 = "tif";
                    break;
                }
                case 1: {
                    s3 = "png";
                    break;
                }
                case 2: {
                    s3 = "jpg";
                    break;
                }
                default: {
                    s3 = "tif";
                    break;
                }
            }
        }
        Properties properties2 = null;
        if (properties != null) {
            properties2 = new Properties(properties);
            ((Hashtable<String, Integer>)properties2).put("imageWriteFormat", new Integer(n4));
        }
        int n6 = n;
        boolean b4 = false;
        if (n == -1) {
            n6 = 0;
            b4 = true;
        }
        String a;
        if (s2 != null) {
            a = s2;
        }
        else {
            a = ji.util.d.a(n6, s3, file);
        }
        if (this.a(a, jiBurnerListener, s, n2, n3, b4, properties2, n5, b, b2, b3)) {
            return a;
        }
        final String ko = this.ko();
        if (ko != null) {
            throw new jiImageSaveFailedException(ko);
        }
        return null;
    }
    
    private boolean a(final String s, final jiBurnerListener jiBurnerListener, final String s2, final int n, final int n2, final boolean b, final Properties properties, final long n3, final boolean b2, final boolean b3, final boolean b4) throws jiImageSaveFailedException {
        if (!k9.a) {
            this.bc("\n".concat(String.valueOf(String.valueOf(ji.util.d.bt("".concat(String.valueOf(String.valueOf(this.b))))))));
            this.bc("\n".concat(String.valueOf(String.valueOf(ji.util.d.c(this)))));
            k9.a = true;
        }
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long n4 = totalMemory - freeMemory;
        this.bc(String.valueOf(String.valueOf(new StringBuffer("heap size ").append(totalMemory).append(" bytes"))));
        this.bc(String.valueOf(String.valueOf(new StringBuffer("     used ").append(n4).append(" bytes"))));
        this.bc(String.valueOf(String.valueOf(new StringBuffer("available ").append(freeMemory).append(" bytes"))));
        this.bc("Page = ".concat(String.valueOf(String.valueOf(this.j7()))));
        final df ah = this.ah();
        if (ah != null) {
            this.bc("Annotations: ".concat(String.valueOf(String.valueOf(ah.d()))));
            final boolean b5 = this.dm().ce() && this.bi(3);
            final ax g = ah.g();
            while (g.a()) {
                final dg b6 = ah.b(g.b());
                this.bc("".concat(String.valueOf(String.valueOf(b6))));
                if (!ji.annotate.dg.a(b6, b5, this)) {
                    final String concat = String.valueOf(String.valueOf(ji.util.d.b(1227, this.iu()))).concat(String.valueOf(String.valueOf(ji.annotate.dg.a(b6.d5(), this.iu()))));
                    this.bc(concat);
                    if (!b4) {
                        throw new jiImageSaveFailedException(concat);
                    }
                    continue;
                }
            }
        }
        else {
            this.bc("No annotations found on this document");
        }
        ji.document.ad.a(n3);
        this.j(false);
        this.c(false, 0);
        this.a(jiBurnerListener, s2, n, n2);
        boolean b7 = false;
        try {
            if (b3) {
                b7 = this.a(s, properties);
            }
            else if (b) {
                b7 = this.b(s, properties);
            }
            else {
                b7 = this.c(s, properties);
            }
        }
        catch (jiImageSaveFailedException ex) {
            ji.io.h.a(this.b, ex);
            throw ex;
        }
        catch (Exception ex2) {
            ji.io.h.a(this.b, ex2);
            throw new jiImageSaveFailedException(ex2.getMessage());
        }
        finally {
            if (b2) {
                this.dz();
                this.c4();
                System.gc();
            }
            if (b7) {
                this.bc("image saved in ".concat(String.valueOf(String.valueOf(s))));
            }
            else {
                this.bc("Failed to save image");
            }
        }
        return b7;
    }
    
    public void releaseResources() {
        super.releaseResources();
    }
    
    public final void bb(final String c) {
        this.c = c;
    }
    
    private void bc(final String s) {
        ji.io.h.d(this.b, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c))).append(": ").append(s))));
    }
    
    static {
        k9.a = false;
    }
}
