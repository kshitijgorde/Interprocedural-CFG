// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Vector;

public abstract class m extends bp
{
    public int a;
    private bl a;
    private bl b;
    public int b;
    public int c;
    public bl[] a;
    public en[] a;
    public boolean[] a;
    public boolean[] b;
    public boolean[] c;
    public int[] a;
    public int d;
    public int e;
    long a;
    int f;
    public byte[][] a;
    public dv a;
    protected ae a;
    
    public m() {
        this.b = new bl((byte)0);
        this.a = System.currentTimeMillis();
    }
    
    public final boolean a() {
        if (super.c) {
            this.e = 0;
            this.a((cw)null);
            return true;
        }
        return false;
    }
    
    public final boolean a(final int n) {
        if (super.c) {
            this.e = 0;
            for (int i = 0; i < this.a; ++i) {
                this.a.a(i, (n == i) ? 2 : 1);
            }
            this.a(this.a);
            return true;
        }
        return false;
    }
    
    public final void a(int i, final dv a) {
        this.a = a;
        this.a = 4;
        this.a = new bl[4];
        this.a = new en[4];
        this.a = new boolean[4];
        this.b = new boolean[4];
        this.c = new boolean[4];
        this.a = new int[4];
        this.a = new ae();
        for (i = 0; i < 4; ++i) {
            this.a[i] = new bl((byte)0);
        }
        this.a = new bl(2, false);
        this.a = new byte[4][13];
    }
    
    public final boolean b() {
        return true;
    }
    
    public boolean b(final int n) {
        return this.e == 2 && this.c == n;
    }
    
    public abstract void a();
    
    abstract void b();
    
    public void c() {
    }
    
    public final void d() {
        this.a += 101L;
        this.b();
        this.e();
    }
    
    public void e() {
        this.e = 1;
        this.a.a_();
        for (int i = 0; i < this.a; ++i) {
            this.a[i].a.removeAllElements();
            this.a[i] = 0;
        }
        for (int j = 0; j < this.a; ++j) {
            this.a[j] = false;
            this.b[j] = false;
            this.c[j] = false;
            this.a[j] = null;
            for (int k = 0; k < 13; ++k) {
                this.a[j][k] = 52;
            }
        }
        this.b.a.removeAllElements();
        for (int l = 0; l < this.a.a.size(); ++l) {
            this.b.a(this.a.a(l));
        }
        ++this.a;
        final bl b = this.b;
        final long a = this.a;
        final r a2 = b.a;
        final dw dw = new dw(a);
        final r r = a2;
        for (int n = 0; n < r.size() - 1; ++n) {
            final int a3 = dw.a(r.size() - n);
            final r r2 = r;
            final int n2 = n;
            final int n3 = a3 + n;
            final int n4 = n2;
            final Vector<Object> vector = (Vector<Object>)r2;
            final Object element = r2.elementAt(n4);
            vector.setElementAt(vector.elementAt(n3), n4);
            vector.setElementAt(element, n3);
        }
        for (int n5 = 0; n5 < this.a; ++n5) {
            for (int n6 = 0; n6 < 13; ++n6) {
                final bl bl = this.a[n5];
                final r a4;
                final Object lastElement = (a4 = this.b.a).lastElement();
                a4.removeElementAt(a4.size() - 1);
                bl.a((en)lastElement);
            }
            this.a.b(n5, this.a[n5]);
        }
        this.d = 0;
    }
    
    public boolean a(final int n, final en en) {
        if (this.e != 2 || this.c != n || !this.a[this.c].b(en)) {
            return false;
        }
        if (this.c != this.b && en.b != this.a[this.b].b && this.a[this.c].a(this.a[this.b].b)) {
            this.a.f_(1716519111);
            return false;
        }
        return true;
    }
    
    public static int a(final en en) {
        return (en.a - 2 << 2) + en.b;
    }
    
    public static en a(final int n) {
        return new en(n / 4 + 2, n % 4);
    }
    
    public final boolean c(final int n) {
        if (this.e != 2) {
            this.a.f_(1716519112);
            return false;
        }
        if (this.c[n]) {
            return false;
        }
        if (this.c == this.b && this.d == 0) {
            this.a.f_(1716519112);
            return false;
        }
        return true;
    }
    
    public abstract void f();
    
    public final int a(final int n) {
        return (n + 1) % this.a;
    }
    
    public final int a(final en en, final en en2) {
        if (en2.b == en.b) {
            return en2.a;
        }
        if (en2.b == this.f) {
            return en2.a + 13;
        }
        return -1;
    }
    
    public abstract void b(final DataOutput p0);
    
    public final void a(final DataOutput dataOutput) {
        dataOutput.write(this.e);
        dataOutput.writeLong(this.a);
        if (this.e != 0) {
            dataOutput.write(this.b);
            dataOutput.write(this.c);
            for (int i = 0; i < this.a; ++i) {
                this.a[i].a(dataOutput);
                final en en = this.a[i];
                dataOutput.writeBoolean(en != null);
                if (en != null) {
                    en.a(dataOutput);
                }
                dataOutput.writeBoolean(this.a[i]);
                dataOutput.write(this.a[i]);
            }
            dataOutput.write(this.d);
            dataOutput.write(this.f);
            for (int j = 0; j < this.a; ++j) {
                for (int k = 0; k < 13; ++k) {
                    dataOutput.write(this.a[j][k]);
                }
            }
            this.b(dataOutput);
        }
    }
    
    public abstract void b(final DataInput p0);
    
    public final void a(final DataInput dataInput) {
        this.e = dataInput.readByte();
        this.a = dataInput.readLong();
        super.c = (this.e != 0);
        if (super.c) {
            this.b = dataInput.readByte();
            this.c = dataInput.readByte();
            for (int i = 0; i < this.a; ++i) {
                this.a[i].a(dataInput);
                this.a[i] = (dataInput.readBoolean() ? en.a(dataInput) : null);
                this.a[i] = dataInput.readBoolean();
                this.a[i] = dataInput.readByte();
            }
            this.d = dataInput.readByte();
            this.f = dataInput.readByte();
            for (int j = 0; j < this.a; ++j) {
                for (int k = 0; k < 13; ++k) {
                    this.a[j][k] = dataInput.readByte();
                }
            }
            this.b(dataInput);
        }
    }
    
    public void g() {
        super.g();
        if (this.e != 0) {
            this.a.a_();
            for (int i = 0; i < this.a; ++i) {
                this.a.b(i, this.a[i]);
            }
            if (this.e == 2) {
                for (int j = 0; j < this.a; ++j) {
                    this.a.d(j, this.a[j]);
                }
                this.a.c(this.c);
                this.a.k(180000);
                for (int n = this.b; this.a[n] != null; n = this.a(n)) {
                    this.a.a(n, this.a[n]);
                }
            }
            for (int k = 0; k < this.a; ++k) {
                if (this.a[k]) {
                    this.a.d_(k);
                }
            }
        }
    }
}
