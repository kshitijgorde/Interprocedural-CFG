// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.v1event.af;
import ji.util.d;
import ji.font.d1;
import java.awt.Component;
import ji.io.h;
import ji.awt.c;

public class d3
{
    String a;
    char[] b;
    String c;
    private static String d;
    private int e;
    private int f;
    private int g;
    private String h;
    private int i;
    private c j;
    private boolean k;
    private int[] l;
    private int[] m;
    private int[] n;
    private int[] o;
    private boolean p;
    private boolean q;
    private int r;
    
    public d3(final String c) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.h = " []<>{}()\";.:=-+_&%#~|\\\n\r?!,@'*/";
        this.i = -1;
        this.j = new c("jiTextEntry1");
        this.k = true;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.c = c;
    }
    
    public final void a(final String a) {
        this.a = a;
        if (a != null) {
            this.b = a.toCharArray();
        }
        else {
            this.b = null;
        }
    }
    
    public final String a() {
        return this.a;
    }
    
    protected final int b() {
        if (this.e > this.c()) {
            this.e = Math.max(this.c(), 0);
        }
        return this.e;
    }
    
    public final int c() {
        if (this.a != null) {
            return this.a.length();
        }
        return 0;
    }
    
    public final int d() {
        int n = 0;
        try {
            if (this.e >= 0 && this.b != null && this.m != null) {
                int n2 = this.m[0];
                for (int i = 0; i < this.m.length; ++i) {
                    if (n2 != this.m[i]) {
                        n2 = this.m[i];
                        ++n;
                    }
                }
                ++n;
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    public final boolean a(final boolean b) {
        boolean b2 = false;
        this.q = false;
        if (this.a != null) {
            this.e = this.a.length();
        }
        if (!b) {
            b2 |= this.j();
        }
        else {
            this.b(0);
            this.g = this.b();
        }
        return b2;
    }
    
    private final int q() {
        int n = 0;
        this.p = false;
        if (this.m != null) {
            if (this.e < 0) {
                if (this.a != null) {
                    n = this.a.length();
                }
            }
            else {
                if (this.e >= this.m.length) {
                    return this.e;
                }
                final int min = Math.min(this.e, this.m.length - 1);
                final int n2 = this.m[min];
                int i = min;
                while (i < this.m.length) {
                    if (n2 != this.m[i]) {
                        n = i - 1;
                        if (this.b != null && this.b[n] != '\n' && this.b[n] != '\r') {
                            ++n;
                            this.p = true;
                            break;
                        }
                        break;
                    }
                    else {
                        ++i;
                    }
                }
                if (n == 0 && this.a != null) {
                    n = this.a.length();
                }
            }
        }
        return n;
    }
    
    private final int r() {
        int n = 0;
        this.q = false;
        if (this.m != null) {
            if (this.e < 0) {
                n = 0;
            }
            else {
                if (this.e >= this.m.length) {
                    return this.e;
                }
                final int min = Math.min(this.e, this.m.length - 1);
                final int n2 = this.m[min];
                for (int i = min; i > 0; --i) {
                    if (n2 != this.m[i]) {
                        n = i + 1;
                        break;
                    }
                }
            }
        }
        return n;
    }
    
    public final boolean b(final boolean b) {
        boolean b2 = false;
        final int e = this.e;
        this.e = this.q();
        if (this.p) {
            this.q = true;
        }
        if (!b) {
            b2 |= this.j();
        }
        else {
            this.b(0);
            this.f = e;
            this.g = this.e;
            if (this.b[this.g] != '\n' && this.b[this.g] != '\r') {
                --this.g;
            }
        }
        return b2;
    }
    
    public final boolean e() {
        return this.q;
    }
    
    public final boolean c(final boolean b) {
        final boolean b2 = false;
        final int e = this.e;
        this.e = this.r();
        boolean b3;
        if (!b) {
            b3 = (b2 | this.j());
        }
        else {
            this.b(-1);
            this.f = this.e;
            this.g = e - 1;
            b3 = true;
        }
        return b3;
    }
    
    public final boolean f() {
        return this.e == this.c();
    }
    
    public final boolean d(final boolean b) {
        boolean b2 = false;
        this.q = false;
        final int f = this.f;
        final int g = this.g;
        if (b && this.f < 0) {
            this.f = this.e;
        }
        if (this.a != null) {
            this.e = Math.min(this.e + 1, this.a.length());
        }
        if (b) {
            if (this.g >= this.e) {
                this.f = this.e;
            }
            if (this.g < this.e || f <= 0) {
                this.g = this.e - 1;
            }
            if (f != this.f || g != this.g) {
                b2 = true;
            }
        }
        else {
            b2 |= this.j();
        }
        return b2;
    }
    
    public final boolean e(final boolean b) {
        boolean b2 = false;
        this.q = false;
        final int f = this.f;
        final int g = this.g;
        if (this.e >= 0) {
            if (b && this.f < 0) {
                this.f = this.e;
            }
            int q = this.q();
            final int n = this.l[Math.min(this.e, this.l.length - 1)];
            if (q > 0 && this.b != null) {
                final int n2 = this.l[this.e];
                if (q < this.l.length) {
                    while (this.m[Math.min(this.e, this.m.length - 1)] == this.m[q] && q < this.l.length - 1) {
                        ++q;
                    }
                }
                final int min = Math.min(q, this.b.length - 1);
                this.e = min;
                final int n3 = min;
                final int q2 = this.q();
                if (this.m != null) {
                    int e = n3;
                    for (int i = n3; i < q2; ++i) {
                        if (this.l[i] <= n) {
                            e = i;
                        }
                    }
                    if (e != n3) {
                        this.e = e;
                    }
                }
            }
            if (b) {
                if (this.g >= this.e) {
                    this.f = this.e;
                }
                if (this.g < this.e || f <= 0) {
                    this.g = this.e - 1;
                }
                if (f != this.f || g != this.g) {
                    b2 = true;
                }
            }
            else {
                b2 |= this.j();
            }
        }
        return b2;
    }
    
    public final boolean f(final boolean b) {
        boolean b2 = false;
        this.q = false;
        final int f = this.f;
        final int g = this.g;
        try {
            if (this.b != null) {
                if (b && this.f < 0) {
                    this.f = this.e;
                }
                final int e = this.e;
                int length;
                int i;
                for (length = this.b.length, i = e; i < length; ++i) {
                    if (this.h.indexOf("".concat(String.valueOf(String.valueOf(this.b[i])))) >= 0) {
                        this.e = Math.min(i + 1, this.b.length - 1);
                        break;
                    }
                }
                if (i == length) {
                    this.e = Math.min(length, this.b.length);
                }
                if (b) {
                    if (this.g >= this.e) {
                        this.f = this.e;
                    }
                    if (this.g < this.e || f <= 0) {
                        this.g = this.e - 1;
                    }
                    if (f != this.f || g != this.g) {
                        b2 = true;
                    }
                }
                else {
                    b2 |= this.j();
                }
            }
        }
        catch (Exception ex) {}
        return b2;
    }
    
    public final boolean g(final boolean b) {
        boolean b2 = false;
        this.q = false;
        final int f = this.f;
        final int g = this.g;
        if (b && f <= 0) {
            if (this.g > 0) {
                this.g = Math.min(this.g, this.e - 1);
            }
            else {
                this.g = this.e - 1;
            }
        }
        if (this.e > 0) {
            --this.e;
        }
        if (b) {
            if (f < this.e && f >= 0) {
                if (this.g > 0) {
                    this.g = Math.min(this.g, this.e - 1);
                }
                else {
                    this.g = this.e - 1;
                }
            }
            if (this.f > this.e) {
                this.f = this.e;
            }
            else if (this.f <= 0) {
                this.f = this.e;
            }
            if (f != this.f || g != this.g) {
                b2 = true;
            }
        }
        else {
            b2 |= this.j();
        }
        return b2;
    }
    
    public final boolean h(final boolean b) {
        boolean b2 = false;
        this.q = false;
        final int f = this.f;
        final int g = this.g;
        if (b && f <= 0) {
            if (this.g > 0) {
                this.g = Math.min(this.g, this.e - 1);
            }
            else {
                this.g = this.e - 1;
            }
        }
        if (this.e > 0) {
            final int r = this.r();
            final int n = this.l[Math.min(this.e, this.l.length - 1)];
            if (r > 0) {
                final int n2 = this.l[this.e];
                final int e = r - 1;
                this.e = e;
                final int r2 = this.r();
                if (this.m != null) {
                    int e2 = e;
                    for (int i = e; i >= r2; --i) {
                        if (this.l[i] >= n) {
                            e2 = i;
                        }
                    }
                    if (e2 != e) {
                        this.e = e2;
                    }
                }
            }
        }
        if (b) {
            if (f < this.e && f >= 0) {
                if (this.g > 0) {
                    this.g = Math.min(this.g, this.e - 1);
                }
                else {
                    this.g = this.e - 1;
                }
            }
            if (this.f > this.e) {
                this.f = this.e;
            }
            else if (this.f <= 0) {
                this.f = this.e;
            }
            if (f != this.f || g != this.g) {
                b2 = true;
            }
        }
        else {
            b2 |= this.j();
        }
        return b2;
    }
    
    public final boolean i(final boolean b) {
        boolean b2 = false;
        if (this.k) {
            ji.io.h.d(this.c, "previousWord: ".concat(String.valueOf(String.valueOf(b))));
        }
        if (this.e > 0) {
            final int f = this.f;
            final int g = this.g;
            final int e = this.e;
            if (b && f <= 0) {
                if (this.g > 0) {
                    this.g = Math.max(Math.min(this.g, this.e - 1), 0);
                }
                else {
                    this.g = Math.max(this.e - 1, 0);
                }
            }
            if (this.e <= 1) {
                this.e = 0;
            }
            else if (this.b != null) {
                final int max = Math.max(this.e - 2, 0);
                if (max <= 1) {
                    this.e = 0;
                }
                else {
                    int n = max;
                    for (int i = max; i > 0; --i) {
                        if (this.h.indexOf("".concat(String.valueOf(String.valueOf(this.b[i])))) >= 0) {
                            n = i;
                            break;
                        }
                    }
                    this.e = Math.max(n + 1, 0);
                    if (this.h.indexOf("".concat(String.valueOf(String.valueOf(this.b[this.e])))) >= 0) {
                        this.e = 0;
                    }
                }
            }
            if (b) {
                if (f < this.e && f > 0) {
                    if (this.g > 0) {
                        this.g = Math.min(this.g, this.e - 1);
                    }
                    else {
                        this.g = this.e - 1;
                    }
                }
                if (this.f > this.e) {
                    this.f = this.e;
                }
                else if (this.f <= 0) {
                    this.f = this.e;
                }
                if (f != this.f || g != this.g) {
                    b2 = true;
                }
            }
            else {
                b2 |= this.j();
            }
        }
        return b2;
    }
    
    public final void a(final int e) {
        this.e = e;
    }
    
    public final boolean b(final int n) {
        if (this.f < 0 || this.g < 0) {
            this.f = this.b() + n;
            this.g = this.f;
            return true;
        }
        return false;
    }
    
    public final void b(final String d) {
        d3.d = d;
    }
    
    public final String g() {
        return d3.d;
    }
    
    public final int h() {
        return this.f;
    }
    
    public final int i() {
        return this.g;
    }
    
    public final boolean j(final boolean b) {
        final boolean b2 = false;
        this.q = false;
        this.e = 0;
        boolean b3;
        if (!b) {
            b3 = (b2 | this.j());
        }
        else {
            this.b(-1);
            this.f = 0;
            b3 = true;
        }
        return b3;
    }
    
    public final boolean j() {
        final boolean b = this.f > 0 || this.g > 0;
        this.f = -1;
        this.g = -1;
        return b;
    }
    
    public final void a(final Component component, final int[] array, final int[] array2, final int[] array3, final int[] array4, final d1 d1) {
        if (array != null) {
            this.l = new int[array.length];
            this.m = new int[array2.length];
            this.n = new int[array3.length];
            this.o = new int[array4.length];
            for (int i = 0; i < array.length; ++i) {
                this.l[i] = array[i];
            }
            for (int j = 0; j < array2.length; ++j) {
                this.m[j] = array2[j];
            }
            for (int k = 0; k < array3.length; ++k) {
                this.n[k] = array3[k];
            }
            for (int l = 0; l < array4.length; ++l) {
                this.o[l] = array4[l];
            }
            if (this.b == null && this.a != null) {
                this.b = this.a.toCharArray();
            }
            this.a(component, d1);
        }
    }
    
    public final void a(final Component component, final d1 d1) {
        try {
            if (!ji.util.d.by(this.a) && d1 != null && this.m != null) {
                if (this.b == null) {
                    this.b = this.a.toCharArray();
                }
                this.r = 0;
                int n = 0;
                int n2 = -1;
                for (int i = 0; i < this.b.length; ++i) {
                    if (n2 < 0) {
                        n2 = this.m[i];
                    }
                    if (this.b[i] == '\n' || this.b[i] == '\r') {
                        this.r = Math.max(this.r, n);
                        n = 0;
                        n2 = -1;
                    }
                    else if (n2 == this.m[i]) {
                        n += d1.a(component, "".concat(String.valueOf(String.valueOf(this.b[i]))), false, null);
                    }
                    else {
                        this.r = Math.max(this.r, n);
                        n = 0;
                        n2 = -1;
                    }
                }
                this.r = Math.max(this.r, n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int k() {
        return this.r;
    }
    
    public final void l() {
        try {
            if (ji.util.d.by(this.a)) {
                this.j.c(new u3("", this.e));
            }
            else {
                this.j.c(new u3(this.a, this.e));
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final Component component, final d1 d1) {
        try {
            if (this.j != null && this.j.b() > 0) {
                final u3 u3 = (u3)this.j.b(this.j.b() - 1);
                this.j.d(this.j.b() - 1);
                this.a = u3.a;
                this.e = u3.b;
                u3.a();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void m() {
        try {
            if (this.j != null) {
                while (this.j.b() > 0) {
                    ((u3)this.j.b(0)).a();
                    this.j.d(0);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final String n() {
        final StringBuffer sb = new StringBuffer("");
        try {
            if (this.j != null) {
                for (int i = 0; i < this.j.b(); ++i) {
                    sb.append("".concat(String.valueOf(String.valueOf(this.j.b(i)))));
                }
            }
        }
        catch (Exception ex) {}
        return sb.toString();
    }
    
    public final c o() {
        return this.j;
    }
    
    public final void a(final d3 d3) {
        try {
            this.m();
            final c o = d3.o();
            if (o != null) {
                for (int i = 0; i < o.b(); ++i) {
                    this.j.c(o.b(i));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void s() {
        try {
            if (this.j != null) {
                this.m();
                this.j = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void p() {
        this.s();
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.a = null;
        this.b = null;
    }
    
    static {
        d3.d = null;
    }
    
    class u3
    {
        String a;
        int b;
        
        public u3(final d3 d3, final String a, final int b) {
            this.a = null;
            this.b = 0;
            this.a = a;
            this.b = b;
        }
        
        public void a() {
            this.a = null;
        }
    }
}
