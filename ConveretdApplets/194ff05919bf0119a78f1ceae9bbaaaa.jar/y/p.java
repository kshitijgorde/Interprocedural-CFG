// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Hashtable;

public final class p extends m
{
    public int g;
    public int[] b;
    public boolean[] d;
    private boolean d;
    public int[] c;
    private int[] d;
    public int h;
    public boolean a;
    public boolean b;
    public ad a;
    
    public p() {
        this.b = new int[4];
        this.d = new boolean[4];
        this.c = new int[2];
        this.d = new int[2];
    }
    
    public final void a(final Hashtable hashtable, final di di) {
        super.a(4, (dv)di);
        this.a = (ad)di;
        this.a = a(hashtable);
        this.b = b(hashtable);
    }
    
    public final boolean b(final int n) {
        if (super.e != 1) {
            return super.b(n);
        }
        if (this.b[this.h] == -2) {
            return n == this.h;
        }
        if (super.a[this.h].a.size() == 13) {
            return n == this.h;
        }
        return n == (this.h + 2) % 4;
    }
    
    public final boolean c() {
        if (super.c) {
            if (this.c[0] == this.c[1]) {
                final p p = this;
                final ae a = super.a;
                this = p;
                boolean b = false;
                for (int i = 0; i < super.e.length; ++i) {
                    a.a(i, super.e[i] ? 1 : 2);
                    if (super.e[i]) {
                        b = true;
                    }
                }
                this.a(b ? a : null);
            }
            else {
                this.j();
            }
            return true;
        }
        return false;
    }
    
    public final void b() {
        this.g = new dw(super.a - 7L).a(4);
        for (int i = 0; i < 2; ++i) {
            this.c[i] = 0;
            this.d[i] = 0;
        }
    }
    
    public final void e() {
        super.e();
        this.h = this.g;
        this.d = false;
        for (int i = 0; i < 4; ++i) {
            super.a[i] = 0;
            this.d[i] = false;
            this.b[i] = -2;
            if (this.b && this.c[(i + 1) % 2] - this.c[i % 2] < 200) {
                this.d[i] = true;
                this.a.a(i, super.a[i]);
            }
        }
        this.a.b_(this.h);
        this.a.k(180000);
    }
    
    public final void h() {
        this.h = (this.h + 1) % 4;
        if (this.h == this.g) {
            this.a.b_(-1);
            final p p = this;
            final int g = this.g;
            this = p;
            final int n = g;
            p.b = n;
            p.c = n;
            super.e = 2;
            super.a.c(super.c);
            super.a.k(180000);
            super.f = 2;
            return;
        }
        this.a.b_(this.h);
        this.a.k(180000);
    }
    
    public final boolean a(final int n, final en en) {
        if (!super.a(n, en)) {
            return false;
        }
        if (n == super.b && !this.d && en.b == 2 && (super.a[n].a(3) || super.a[n].a(1) || super.a[n].a(0))) {
            this.a.f_(1716521431);
            return false;
        }
        return true;
    }
    
    public final void f() {
        this.c[0] = (this.c[1] = 0);
        this.d[0] = (this.d[1] = 0);
    }
    
    public final void c() {
        for (int i = 0; i < 4; ++i) {
            if (super.a[i].b == 2) {
                this.d = true;
            }
        }
    }
    
    private void j() {
        if (this.c[0] > this.c[1]) {
            super.a.a(0, 1);
            super.a.a(2, 1);
            super.a.a(1, 2);
            super.a.a(3, 2);
        }
        else {
            super.a.a(0, 2);
            super.a.a(2, 2);
            super.a.a(1, 1);
            super.a.a(3, 1);
        }
        final p p = this;
        final ae a = super.a;
        this = p;
        p.e = 0;
        this.a(a);
    }
    
    public final void a() {
        this.g = (this.g + 1) % 4;
        this.a.b(-1, -1);
        for (int i = 0; i < 2; ++i) {
            final int n = ((this.b[i] == -1) ? 0 : this.b[i]) + ((this.b[i + 2] == -1) ? 0 : this.b[i + 2]);
            final int n2;
            if ((n2 = super.a[i] + super.a[i + 2]) >= n) {
                final int[] c = this.c;
                final int n3 = i;
                c[n3] += n * 10;
                final int n4 = n2 - n;
                final int[] d = this.d;
                final int n5 = i;
                d[n5] += n4;
                final int[] c2 = this.c;
                final int n6 = i;
                c2[n6] += n4;
                this.a.a(i, n, n4);
                while (this.d[i] >= 10) {
                    final int[] c3 = this.c;
                    final int n7 = i;
                    c3[n7] -= 100;
                    final int[] d2 = this.d;
                    final int n8 = i;
                    d2[n8] -= 10;
                }
            }
            else {
                final int[] c4 = this.c;
                final int n9 = i;
                c4[n9] -= n * 10;
                this.a.c(i, n * 10);
            }
            for (int j = 0; j < 2; ++j) {
                if (this.b[i + (j << 1)] == 0) {
                    if (super.a[i + (j << 1)] == 0) {
                        final int[] c5 = this.c;
                        final int n10 = i;
                        c5[n10] += 100;
                        this.a.b(i, 1716521435);
                    }
                    else {
                        final int[] c6 = this.c;
                        final int n11 = i;
                        c6[n11] -= 100;
                        this.a.b(i, 1716521426);
                    }
                }
                else if (this.b[i + (j << 1)] == -1) {
                    if (super.a[i + (j << 1)] == 0) {
                        final int[] c7 = this.c;
                        final int n12 = i;
                        c7[n12] += 200;
                        this.a.b(i, 1716521438);
                    }
                    else {
                        final int[] c8 = this.c;
                        final int n13 = i;
                        c8[n13] -= 200;
                        this.a.b(i, 1716521432);
                    }
                }
            }
            if (!this.a) {
                this.c[i] = Math.max(-200, this.c[i]);
            }
        }
        this.a.a(this.c, this.d);
        for (int k = 0; k < 2; ++k) {
            if ((this.c[k] >= 500 || (this.a && Math.abs(this.c[0] - this.c[1]) >= 500)) && this.c[0] != this.c[1]) {
                this.a.a(this.c);
                this.j();
                return;
            }
        }
        this.e();
    }
    
    public final void b(final DataOutput dataOutput) {
        dataOutput.write(this.g);
        for (int i = 0; i < 4; ++i) {
            dataOutput.write(this.b[i]);
            dataOutput.writeBoolean(this.d[i]);
        }
        dataOutput.writeBoolean(this.d);
        for (int j = 0; j < 2; ++j) {
            dataOutput.writeInt(this.c[j]);
            dataOutput.write(this.d[j]);
        }
        dataOutput.write(this.h);
    }
    
    public final void b(final DataInput dataInput) {
        this.g = dataInput.readByte();
        for (int i = 0; i < 4; ++i) {
            this.b[i] = dataInput.readByte();
            this.d[i] = dataInput.readBoolean();
        }
        this.d = dataInput.readBoolean();
        for (int j = 0; j < 2; ++j) {
            this.c[j] = dataInput.readInt();
            this.d[j] = dataInput.readByte();
        }
        this.h = dataInput.readByte();
    }
    
    public final void g() {
        super.g();
        if (super.e != 0) {
            if (super.e == 1) {
                if (this.b && this.b[this.h] == -1) {
                    this.a.c_((super.a[this.h].a.size() == 13) ? this.h : ((this.h + 2) % 4));
                    this.a.k(180000);
                }
                else {
                    this.a.b_(this.h);
                    this.a.k(180000);
                }
                for (int i = this.g; i != this.h; i = (i + 1) % 4) {
                    this.a.a(i, this.b[i]);
                }
                if (this.b[this.h] == -1) {
                    this.a.a(this.h, -1);
                }
            }
            else {
                for (int j = 0; j < 4; ++j) {
                    this.a.a(j, this.b[j]);
                }
            }
            for (int k = 0; k < 4; ++k) {
                if (this.d[k]) {
                    this.a.a(k, super.a[k]);
                }
            }
            this.a.a(this.c, this.d);
        }
    }
    
    public static boolean a(final Hashtable hashtable) {
        return hashtable.get("su") != null;
    }
    
    public static boolean b(final Hashtable hashtable) {
        return hashtable.get("ps") != null;
    }
}
