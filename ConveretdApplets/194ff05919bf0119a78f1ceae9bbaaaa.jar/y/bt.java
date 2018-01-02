// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInputStream;
import java.awt.Container;

public final class bt implements cn, cu, db
{
    private ew a;
    private aj a;
    private ac a;
    private ci a;
    private ek a;
    private String a;
    private l a;
    private boolean a;
    
    public bt(final aj a) {
        (this.a = ew.a()).b("In RemoteClientTableModule ctor");
        this.a = null;
        this.a = a;
        this.a = a.a();
        this.a = new ci("tableframe", this.a.f(), this, this.a.c(), this.a.d());
        this.a = new ac(null, this.a);
        final int c = this.a.c();
        final String e = this.a.e();
        final String d = this.a.d();
        final String b = this.a.b();
        ((dm)(this.a = true)).e();
        this.a.a(c, e, d, b);
    }
    
    private void b(String string) {
        this.a = string;
        final StringBuffer sb;
        (sb = new StringBuffer()).append(this.a.a(1716519088));
        sb.append(this.a.c());
        sb.append(this.a.a(1716519053));
        sb.append(this.a.b());
        sb.append(this.a.a(1716519086));
        sb.append(this.a.c());
        if (null == string) {
            sb.append("");
        }
        else {
            sb.append(this.a.a(1716519078));
            sb.append(string);
        }
        final ci a = this.a;
        string = sb.toString();
        a.b(4, string);
    }
    
    public final void a(final String s) {
        this.b(this.a.a(s).b);
    }
    
    public final void d() {
        if (null != this.a) {
            this.a.b(this.a);
            this.a = null;
        }
        final ci a;
        (a = this.a).b(3, null);
        synchronized (a.a) {
            (a.a = null).notifyAll();
        }
    }
    
    public final boolean a(final boolean b) {
        return true;
    }
    
    public final boolean a() {
        return true;
    }
    
    public final void a() {
    }
    
    public final void f() {
    }
    
    public final void b(final int n) {
    }
    
    public final void g() {
    }
    
    public final void b() {
        this.a.a(this.a.b(), 1, 1, 0, 0, true);
    }
    
    public final boolean a(final byte b, final DataInputStream dataInputStream) {
        return false;
    }
    
    public final void c() {
        this.d();
    }
    
    public final void a(final int n) {
    }
    
    public final void a(final eu eu) {
        if (eu.a.equals(this.a.a())) {
            this.b(null);
        }
    }
    
    public final void e() {
    }
    
    public final void h() {
        this.a.b("RemoteClientTableModule.onClosedWindow() 12345");
        this.a.a(this.a.toString());
        this.a.a(true);
    }
    
    public final void i() {
        this.a.f();
        this.a.b(5, this.a.a());
        this.b(this.a);
        final o a = this.a.a();
        this.a.a = a;
        this.a.a = a;
        ((aj)(this.a = new l(120000L, this))).a(this.a);
    }
    
    public final void a(final String s, final String s2) {
        final StringBuffer sb = new StringBuffer("openTableWindow('");
        this.a.b("onOpenUrlRequest()");
        this.a.b("url=" + s);
        this.a.b("url length=" + s.length());
        this.a.b("windowName=" + s2);
        sb.append(s);
        sb.append("','");
        sb.append(s2);
        sb.append("')");
        this.a.a(sb.toString());
    }
    
    public final void a(final Container container) {
        this.a.a(container);
    }
    
    public final boolean a(final bg bg) {
        if (bg.a == this.a) {
            if (!this.a) {
                this.a.b(2, null);
            }
            this.a = false;
        }
        return false;
    }
    
    public final void j() {
        this.a.f();
        this.h();
        this.a.e(this.a.a(1716525262));
    }
    
    public final void k() {
        this.a.g();
    }
}
