// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.util.Hashtable;
import java.io.DataInputStream;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import java.util.Vector;
import java.awt.Color;

public abstract class ap implements di
{
    public static Color c;
    fm a;
    dm a;
    private ec a;
    bp a;
    private int b;
    private boolean a;
    private long a;
    private long b;
    private boolean c;
    protected fc[] a;
    private int[] a;
    eu[] a;
    int a;
    private int c;
    t a;
    boolean b;
    Vector c;
    private int d;
    
    public ap() {
        this.b = 0;
        this.a = -1;
        this.c = 0;
        this.b = false;
        this.c = new Vector(1);
    }
    
    public final void a(final db db) {
        this.c.addElement(db);
    }
    
    final void a(final fm a, final dm a2, final ec a3) {
        this.a = a3;
        this.a = a2.d();
        this.a = a;
        this.a = a2;
        this.a = a2.a.a(a.a, this);
        final int g = this.a.g;
        this.a = new fc[g];
        this.a = new eu[g];
        this.a = new int[g];
        this.c();
        this.a = this.a();
        for (int i = 0; i < this.c.size(); ++i) {
            ((db)this.c.elementAt(i)).b();
        }
    }
    
    public void c() {
        for (int i = 0; i < this.c.size(); ++i) {
            ((db)this.c.elementAt(i)).a();
        }
    }
    
    public int a(final int n) {
        return n;
    }
    
    public int b(final int n) {
        return n;
    }
    
    private void a(final int n, final int n2) {
        this.a[n] = n2;
        this.a[this.a(n)].a.a(this.a.a.a[n2]);
    }
    
    public void b(final int n) {
    }
    
    public void g(final int n) {
        this.g_(n);
    }
    
    public void a(final int n) {
    }
    
    public void b(final int n, final String s) {
        this.g_(n);
    }
    
    void g_(final int n) {
        final int a = this.a(n);
        if (this.a[n] == null) {
            this.a[a].a.b(Color.white);
            this.a[a].a("");
            this.a[a].a.a((Image)null);
            return;
        }
        this.a[a].a.b((Color)null);
        this.a[a].a(this.a[n].b);
        this.a(n, this.a[n]);
    }
    
    void a(final String s, final boolean b) {
    }
    
    protected void b(final String s) {
    }
    
    protected static Font a() {
        return new Font(Toolkit.getDefaultToolkit().getFontList()[0], 1, 14);
    }
    
    protected static Color c() {
        return Color.white;
    }
    
    protected Color a(final int n) {
        return Color.black;
    }
    
    public final fc a(final int n) {
        final Color a;
        if (!(a = this.a(n)).equals(Color.black)) {
            a.brighter().brighter();
        }
        return this.a[n] = new fc(a.darker(), this, n);
    }
    
    public abstract t a();
    
    public void d() {
    }
    
    public final int b() {
        return this.b;
    }
    
    public final dm a() {
        return this.a;
    }
    
    public final boolean c() {
        return this.a;
    }
    
    public final String a() {
        return this.a.c;
    }
    
    public final String b() {
        return this.a.g;
    }
    
    final boolean a(final String s) {
        return this.a.a(s);
    }
    
    public final eu a(final String s) {
        return this.a.a.get(s);
    }
    
    public final long a() {
        return this.a.a;
    }
    
    public final eu a(final DataInputStream dataInputStream) {
        return this.a(dataInputStream.readUTF());
    }
    
    public final int c() {
        return this.a.e;
    }
    
    public final Hashtable a() {
        return this.a.a;
    }
    
    public final int d() {
        return this.a.g;
    }
    
    public final bp a() {
        return this.a;
    }
    
    public final boolean d() {
        return this.c;
    }
    
    public final void o() {
        final long b = this.b;
        final int b2 = this.b;
        this.a.g();
        this.b = b;
        this.b = b2;
    }
    
    final void a(final String s, final String s2) {
        this.a.a(s, s2);
    }
    
    private void a() {
        this.a.a.write(43);
        this.a.a.write(this.a.e);
    }
    
    public final void c(final String s) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(67);
        this.a.a.writeUTF(s);
        this.a.c();
    }
    
    public final void b(final cw cw) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(89);
        this.a.a.a(cw);
        this.a.c();
    }
    
    public final void a(final cw cw, final cw cw2) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(83);
        this.a.a.a(cw);
        this.a.a.a(cw2);
        this.a.c();
    }
    
    public final void a(final char c, final byte b) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(c);
        this.a.a.write(b);
        this.a.c();
    }
    
    public final void a(final byte b, final byte b2) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(88);
        this.a.a.write(b);
        this.a.a.write(b2);
        this.a.c();
    }
    
    public final void a(final char c) {
        if (this.a == null) {
            return;
        }
        this.a();
        this.a.a.write(c);
        this.a.c();
    }
    
    public void a(final byte b, final DataInputStream dataInputStream) {
        for (int i = 0; i < this.c.size(); ++i) {
            if (((db)this.c.elementAt(i)).a(b, dataInputStream)) {
                return;
            }
        }
        switch (b) {
            case 97: {
                this.a.a(dataInputStream);
                this.b = dataInputStream.readInt();
                this.a.g();
                this.b -= this.a;
            }
            case 98: {
                final bp a;
                if (!(a = this.a).c || a.d()) {
                    a.c = true;
                    a.a.m();
                    a.d();
                    a.i();
                }
            }
            case 100: {
                final byte byte1 = dataInputStream.readByte();
                this.a[byte1] = null;
                this.a.a(byte1, false);
                if (byte1 == this.a) {
                    this.a = -1;
                    this.b((int)byte1);
                }
                this.g(byte1);
            }
            case 116: {
                final byte byte2 = dataInputStream.readByte();
                final byte byte3 = dataInputStream.readByte();
                this.a[byte2] = this.a(dataInputStream);
                this.a(byte2, (int)byte3);
                this.a.a(byte2, true);
                if (this.a[byte2].a.equals(this.a.c)) {
                    this.a = byte2;
                    this.d = byte3;
                    this.a((int)byte2);
                }
                this.b(byte2, this.a[byte2].a);
            }
            case 48: {
                this.a = dataInputStream.readLong();
            }
            case 49: {
                this.a("*** " + dataInputStream.readUTF(), Color.blue);
            }
            case 52: {
                this.a(dataInputStream.readUTF(), dataInputStream.read() == 1);
            }
            case 53: {
                this.b(dataInputStream.readUTF());
            }
            case 56: {
                this.a(dataInputStream.readByte(), (int)dataInputStream.readByte());
            }
            default: {
                throw new IllegalArgumentException("YOG protocol error: " + b + " " + (char)b);
            }
        }
    }
    
    protected final void d(final String s) {
        this.a.c(s);
    }
    
    protected final void h(int b) {
        b = this.b(b);
        if (this.a[b] != null) {
            this.d(this.a[b].a);
        }
    }
    
    public final void i(final int n) {
        this.a('%', (byte)n);
    }
    
    public final void p() {
        this.a('B');
    }
    
    final void j(final int n) {
        if (this.a >= 0 && this.a(this.a) == n) {
            this.d = (this.d + 1) % (45 - (this.a.a(4L) ? 0 : 11));
            final int d = this.d;
            this.c = 533;
            this.a[n].a.a(this.a.a.a[d]);
        }
    }
    
    abstract void a(final eu p0);
    
    abstract void b(final eu p0);
    
    public abstract void i();
    
    public void l() {
        if (this.c > 0) {
            --this.c;
            if (this.c == 0) {
                this.a.b(this.d);
            }
        }
        if (this.c && this.a.c) {
            final int n = (int)(this.b - System.currentTimeMillis());
            final int max = Math.max(n, 0);
            for (int i = 0; i < this.c.size(); ++i) {
                ((db)this.c.elementAt(i)).a(max);
            }
            if (n <= 0) {
                this.c = false;
                this.a('(');
            }
        }
        if (this.a.a > 0) {
            this.b = true;
            final t a = this.a;
            a.a -= 15;
            final t a2 = this.a;
            int a3 = this.a.a;
            final t t = a2;
            if (a3 < 0) {
                a3 = 0;
            }
            t.a.a(t.a + y.d.a((long)(a3 + 999)));
            if (this.a.a <= 0) {
                this.a.c(0);
                this.d();
            }
        }
        for (int j = 0; j < this.c.size(); ++j) {
            ((db)this.c.elementAt(j)).e();
        }
    }
    
    public final void a(final Hashtable hashtable) {
        for (int i = 0; i < this.c.size(); ++i) {
            this.c.elementAt(i);
        }
    }
    
    public final void e(final String s) {
        this.a(s, (Color)null);
    }
    
    public abstract void a(final String p0, final Color p1);
    
    public final int e() {
        return this.a;
    }
    
    public void m() {
        for (int i = 0; i < this.c.size(); ++i) {
            ((db)this.c.elementAt(i)).g();
        }
    }
    
    public void a(final cw cw) {
        ++this.b;
        for (int i = 0; i < this.c.size(); ++i) {
            ((db)this.c.elementAt(i)).f();
        }
    }
    
    public final void k(final int n) {
        this.b = System.currentTimeMillis() + n;
        this.c = true;
    }
    
    public void n() {
    }
    
    static {
        ap.c = new Color(26265);
    }
}
