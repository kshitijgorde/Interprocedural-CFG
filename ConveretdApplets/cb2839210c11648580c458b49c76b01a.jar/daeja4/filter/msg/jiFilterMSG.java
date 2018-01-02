// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import java.io.OutputStream;
import ji.document.gm;
import ji.io.h;
import ji.image.dx;
import ji.filter.fh;
import ji.util.d;
import ji.v1event.af;
import ji.io.ac;
import ji.document.ad;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import ji.filter.cj;

public class jiFilterMSG extends cj
{
    private nd a;
    private nm b;
    private double c;
    private double d;
    private double e;
    private int f;
    private boolean g;
    static /* synthetic */ Class h;
    
    public jiFilterMSG() {
        this.c = 100.0;
        this.d = 200.0;
        this.e = this.d;
        this.f = 830;
        this.g = false;
        try {
            this.c = Toolkit.getDefaultToolkit().getScreenResolution();
        }
        catch (HeadlessException ex) {
            ex.printStackTrace();
            this.c = 100.0;
        }
    }
    
    public boolean isDefinitelySupportedMimeType(final String s, final ad ad) {
        return "application/vnd.ms-outlook".equals(s);
    }
    
    public int isFileType(final ac ac, final String s, final String s2, final ad ad, final boolean b, final String s3, final String s4, final af af, final boolean b2) {
        int n = 0;
        try {
            this.a = new nd(ac);
            if ("0B0D0200-0000-0000-C000-000000000046".equals(this.a.j())) {
                n = 1;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return n;
    }
    
    public dx loadImageHeaderInternal(final fh fh) throws Exception {
        if (this.a == null) {
            this.a = new nd(fh.b);
        }
        this.a.a();
        this.a.a(fh.o, fh.u);
        final dx d = new dx();
        d.f = fh.e;
        if (fh.r) {
            if (fh.j == 6) {
                this.e = 200.0;
            }
            else {
                this.e = this.c;
            }
        }
        else if (this.g && !fh.q) {
            this.e = 200.0;
        }
        else {
            this.e = this.d;
        }
        fh.d = d;
        d.ay = false;
        fh.m = "application/vnd.ms-outlook";
        this.a(d, fh.o, this.f);
        d.ac = this.d;
        d.ad = this.d;
        d.ae = this.d;
        d.af = this.d;
        final double n = this.e / this.c;
        this.b.a(n);
        try {
            this.a(d, fh.o, fh.g);
            d.l = "MS Outlook";
            d.i = fh.b.w();
            d.o = this.b.a();
            d.m = (int)(d.o * n);
            d.p = this.b.b();
            d.n = (int)(d.p * n);
            d.am = 24;
            d.u = 1;
            d.v = 1;
        }
        catch (Exception ex) {
            ji.io.h.a(fh.u, ex);
            super.b = true;
            throw ex;
        }
        return d;
    }
    
    private void a(final dx dx, final Object o, final af af) throws Exception {
        ac h = null;
        try {
            final String b = this.a.b();
            h = this.a.h();
            this.b.a(h, dx, b, this.a.i(), af);
        }
        finally {
            if (h != null) {
                h.a(o);
            }
        }
    }
    
    public void clearAbort(final dx dx, final String s) {
        super.b = false;
    }
    
    public void close(final dx dx, final ad ad) {
        if (this.b != null) {
            this.b.a(dx);
            this.b = null;
        }
    }
    
    public void fillDibInternal(final fh fh) throws Exception {
        super.b = false;
        final ac ac = null;
        try {
            this.b.a(this.a.b(), fh.c, fh.d, fh.g, fh.o);
        }
        catch (Exception ex) {
            ji.io.h.a(fh.u, ex);
            super.b = true;
        }
        finally {
            if (ac != null) {
                ac.a(fh.o);
            }
        }
        if (super.b) {
            fh.c.a(fh.o);
        }
    }
    
    public String getFilterName() {
        return "jiFilterMSG";
    }
    
    public int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        return null;
    }
    
    public void abort(final dx dx) {
        super.abort(dx);
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return super.b;
    }
    
    public gm[] getAttachmentData() throws Exception {
        return this.a.g();
    }
    
    public boolean isAttachmentAvailable() {
        return this.a.e();
    }
    
    public void streamAttachment(final gm gm, final OutputStream outputStream) throws Exception, IllegalArgumentException {
        this.a.a(gm, outputStream);
    }
    
    public boolean supportsResolutionChange() {
        return true;
    }
    
    public int getResolution() {
        return (int)this.d;
    }
    
    public void setResolution(final int n) {
        this.d = n;
    }
    
    public boolean getAutoLimitResolution() {
        return this.g;
    }
    
    public void setAutoLimitResolution(final boolean g) {
        this.g = g;
    }
    
    public String toString() {
        return ((jiFilterMSG.h == null) ? (jiFilterMSG.h = class$("daeja4.filter.msg.jiFilterMSG")) : jiFilterMSG.h).getName();
    }
    
    private final void a(final dx dx, final ad ad, final int n) throws Exception {
        if (this.b == null) {
            this.b = new nm(dx, ad, this.a, n);
        }
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
