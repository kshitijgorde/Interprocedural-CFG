// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.v1event.a6;
import java.awt.Dimension;
import ji.image.cy;
import java.util.Vector;
import ji.font.ct;
import java.awt.Font;
import ji.util.d;
import ji.v1event.af;
import java.awt.Component;
import ji.document.ad;
import ji.awt.ax;
import ji.util.i;
import ji.awt.c;

public class df
{
    int a;
    c b;
    c c;
    c d;
    boolean e;
    String f;
    boolean g;
    boolean[] h;
    int i;
    boolean j;
    static long k;
    boolean l;
    
    public df(final String f) {
        this.a = 0;
        this.b = new c("jiAnnotations1");
        this.c = new c("jiAnnotations2");
        this.d = new c("jiAnnotations3");
        this.e = false;
        this.f = null;
        this.g = false;
        this.h = null;
        this.i = 0;
        this.j = false;
        this.l = true;
        ++df.k;
        this.f = f;
    }
    
    public final boolean a(final boolean j) {
        final boolean i = this.j;
        this.j = j;
        return i;
    }
    
    public final boolean a() {
        return this.j;
    }
    
    public boolean b() {
        return this.o();
    }
    
    public final void a(final boolean g, final int i) {
        this.g = g;
        this.i = i;
        if (g) {
            if (this.h == null) {
                this.h = new boolean[i + 1];
            }
        }
        else {
            this.h = null;
        }
    }
    
    public final void a(final int n, final boolean b) {
        if (this.g) {
            this.h[n] = b;
        }
    }
    
    public final boolean a(final int n) {
        return !this.g || (n < this.h.length && this.h[n]);
    }
    
    public final void b(final boolean b) {
        try {
            if (ji.util.i.c(99)) {
                final ax d = this.b.d();
                boolean b2 = false;
                while (d.a()) {
                    final dg dg = (dg)this.b.d(d.b());
                    if ((!dg.aw() || b) && dg.gc()) {
                        b2 = true;
                    }
                }
                if (b2) {
                    System.gc();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean b(final int n) {
        boolean b = false;
        try {
            final ax d = this.b.d();
            Block_5: {
                while (d.a()) {
                    final dg dg = (dg)this.b.d(d.b());
                    if (dg.cs() && dg.i(n) == n) {
                        break Block_5;
                    }
                }
                return b;
            }
            b = true;
        }
        catch (Exception ex) {}
        return b;
    }
    
    private boolean o() {
        boolean b = false;
        try {
            final ax d = this.b.d();
            while (d.a()) {
                if (((dg)this.b.d(d.b())).a2()) {
                    b = true;
                    break;
                }
            }
            if (!b && this.c != null && this.c.b() > 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public final boolean c() {
        boolean b = false;
        final ax d = this.b.d();
        while (d.a()) {
            if (((dg)this.b.d(d.b())).a1()) {
                b = true;
            }
        }
        return b;
    }
    
    public final boolean a(final ad ad) {
        boolean b = false;
        final int b2 = this.b.b();
        final int[] array = new int[b2];
        for (int i = 0; i < b2; ++i) {
            final dg dg = (dg)this.b.b(i);
            if (dg.gh()) {
                array[i] = ji.annotate.dg.a(dg, ad.bg(dg.i(0)), ad.iu(), ad);
            }
            else {
                array[i] = 1;
            }
        }
        for (int j = 4; j > 1; j /= 2) {
            for (int k = 0; k < b2; ++k) {
                if (array[k] == j) {
                    final dg dg2 = (dg)this.b.b(k);
                    if (dg2.gh()) {
                        for (int l = 0; l < b2; ++l) {
                            if (l != k && array[l] < j) {
                                final dg dg3 = (dg)this.b.b(l);
                                if (dg3.gh() && dg2.dr().equals(dg3.dr())) {
                                    array[l] = j;
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int n = 0; n < b2; ++n) {
            if (array[n] > 1) {
                final dg dg4 = (dg)this.b.b(n);
                dg.a(dg4, array[n], ad.bg(dg4.i(0)), ad, ad, this.f);
                dg4.d(1);
                b = true;
            }
        }
        return b;
    }
    
    public String toString() {
        if (this.b != null) {
            String s = String.valueOf(String.valueOf(new StringBuffer("jiAnnotations(").append(df.k).append(") - \n")));
            final ax d = this.b.d();
            while (d.a()) {
                s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.b.d(d.b()))).concat("\n"))));
            }
            return s;
        }
        return "jiAnnotations[EMPTY]";
    }
    
    public final void a(final int a, final af af, final boolean b) {
        if (this.a != a) {
            this.a = a;
            if (!ji.util.d.bf() && b) {
                this.a(a, af);
            }
            if (this.h != null && a + 1 > this.h.length) {
                final boolean[] h = new boolean[a + 1];
                for (int i = 0; i < this.h.length; ++i) {
                    h[i] = this.h[i];
                }
                this.h = h;
            }
        }
    }
    
    public final int d() {
        return this.b.b();
    }
    
    public final int c(final int n) {
        final int b = this.b.b();
        if (b > 0) {
            int n2 = 0;
            for (int i = 0; i < b; ++i) {
                if (((dg)this.b.b(i)).i(n) == n) {
                    ++n2;
                }
            }
            return n2;
        }
        return 0;
    }
    
    public final String d(final int n) {
        final int b = this.b.b();
        if (b > 0) {
            String value = "";
            int n2 = 0;
            for (int i = 0; i < b; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("").append(dg.d0())));
                    ++n2;
                }
            }
            return value;
        }
        return "0";
    }
    
    public final boolean a(final int n, final boolean b, final boolean b2, final String s, final ad ad, final Object o) {
        final int b3 = this.b.b();
        if (b3 > 0) {
            for (int i = 0; i < b3; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n) {
                    if (b) {
                        if (dg.a(s, b2)) {
                            return true;
                        }
                    }
                    else if (dg.b(s, b2)) {
                        return true;
                    }
                }
            }
            return b8.a(n, this.f, ji.util.d.c1(this.f), ad, o, ad);
        }
        return b8.a(n, this.f, ji.util.d.c1(this.f), ad, o, ad);
    }
    
    public final boolean e(final int n) {
        boolean b = true;
        final int b2 = this.b.b();
        if (b2 > 0) {
            for (int i = 0; i < b2; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n && dg.gh()) {
                    b = !dg.gk();
                    if (!b) {
                        break;
                    }
                }
            }
        }
        return b;
    }
    
    public final dg[] a(final int n, final int n2, final boolean b, final boolean b2, final String s, final boolean b3, final boolean b4) {
        final int b5 = this.b.b();
        int n3 = 0;
        if (b5 > 0) {
            for (int i = 0; i < b5; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n && dg.d5() == n2) {
                    if (b) {
                        if (b3) {
                            if (dg.a(s, b2) && !dg.c(s, b2) && !b4) {
                                ++n3;
                            }
                        }
                        else if (dg.a(s, b2)) {
                            ++n3;
                        }
                    }
                    else {
                        ++n3;
                    }
                }
            }
        }
        final dg[] array = new dg[n3];
        if (n3 > 0) {
            int n4 = 0;
            for (int j = 0; j < b5; ++j) {
                final dg dg2 = (dg)this.b.b(j);
                if (dg2.i(n) == n && dg2.d5() == n2) {
                    if (b) {
                        if (b3) {
                            if (dg2.a(s, b2) && !dg2.c(s, b2) && !b4) {
                                array[n4] = dg2;
                                ++n4;
                            }
                        }
                        else if (dg2.a(s, b2)) {
                            array[n4] = dg2;
                            ++n4;
                        }
                    }
                    else {
                        array[n4] = dg2;
                        ++n4;
                    }
                }
            }
        }
        return array;
    }
    
    public final dg f(final int n) {
        return (dg)this.b.b(n);
    }
    
    public final df a(final int n, final String s) {
        final int b = this.b.b();
        final df df = new df(s);
        for (int i = 0; i < b; ++i) {
            final dg dg = (dg)this.b.b(i);
            if (dg.i(n) == n) {
                try {
                    df.a(dg, false);
                }
                catch (Exception ex) {}
            }
        }
        return df;
    }
    
    public final c b(final int n, final String s) {
        final int b = this.b.b();
        final c c = new c("ZOrderAnnotationsOnPage", true);
        for (int i = 0; i < b; ++i) {
            final dg dg = (dg)this.b.b(i);
            if (dg.i(n) == n) {
                try {
                    c.a("".concat(String.valueOf(String.valueOf(dg.cg()))), dg);
                }
                catch (Exception ex) {}
            }
        }
        return c;
    }
    
    public final boolean b(final int n, final boolean b) {
        final int b2 = this.b.b();
        if (b2 > 0) {
            for (int i = 0; i < b2; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (b) {
                    if (dg.i(n) == n) {
                        return true;
                    }
                }
                else if (dg.i(n) == n && !dg.aw()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public final boolean a(final int n, final boolean b, final boolean b2, final String s, final boolean b3, final boolean b4) {
        final int b5 = this.b.b();
        if (b5 > 0) {
            if (b3) {
                if (b) {
                    for (int i = 0; i < b5; ++i) {
                        final dg dg = (dg)this.b.b(i);
                        if (dg.i(n) == n && dg.a(s, b2) && !dg.c(s, b2)) {
                            if (b4) {
                                return true;
                            }
                            if (dg.gh()) {
                                return true;
                            }
                        }
                    }
                }
                else {
                    for (int j = 0; j < b5; ++j) {
                        final dg dg2 = (dg)this.b.b(j);
                        if (dg2.i(n) == n && dg2.b(s, b2) && !dg2.c(s, b2)) {
                            if (b4) {
                                return true;
                            }
                            if (dg2.gh()) {
                                return true;
                            }
                        }
                    }
                }
            }
            else if (b) {
                for (int k = 0; k < b5; ++k) {
                    final dg dg3 = (dg)this.b.b(k);
                    if (dg3.i(n) == n && dg3.a(s, b2)) {
                        if (b4) {
                            return true;
                        }
                        if (dg3.gh()) {
                            return true;
                        }
                    }
                }
            }
            else {
                for (int l = 0; l < b5; ++l) {
                    final dg dg4 = (dg)this.b.b(l);
                    if (dg4.i(n) == n && dg4.b(s, b2)) {
                        if (b4) {
                            return true;
                        }
                        if (dg4.gh()) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public final boolean g(final int n) {
        final int b = this.b.b();
        if (b > 0) {
            for (int i = 0; i < b; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n && dg.en()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    
    public final void a(final dg dg) throws Exception {
        this.a(dg, true);
    }
    
    public final String a(final String s) {
        String s2 = s.toLowerCase();
        try {
            final boolean b = false;
            int n = 1;
            Block_7: {
                while (!b) {
                    boolean b2 = false;
                    s2 = String.valueOf(String.valueOf(s.toLowerCase())).concat(String.valueOf(String.valueOf(n)));
                    for (int b3 = this.b.b(), i = 0; i < b3; ++i) {
                        final String du = ((dg)this.b.b(i)).du();
                        if (!ji.util.d.by(du) && du.toLowerCase().equals(s2)) {
                            b2 = true;
                            break;
                        }
                    }
                    if (!b2) {
                        break Block_7;
                    }
                    ++n;
                }
                return s2;
            }
            s2 = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(n)));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return s2;
    }
    
    public final void a(final dg dg, final boolean b) throws Exception {
        if (dg != null) {
            if (dg.d5() < 0) {
                throw new Exception("Please set the annotation type before adding it to Annotations: ".concat(String.valueOf(String.valueOf(dg))));
            }
            final long d0 = dg.d0();
            boolean b2 = false;
            for (int b3 = this.b.b(), i = 0; i < b3; ++i) {
                if (((dg)this.b.b(i)).d0() == d0) {
                    b2 = true;
                    break;
                }
            }
            if (!b2) {
                if (b) {
                    this.i();
                }
                this.b.a("".concat(String.valueOf(String.valueOf(dg.d0()))), dg);
                if (b) {
                    this.i();
                }
                this.p();
            }
        }
        this.l = true;
    }
    
    public final void h(final int n) {
        try {
            this.b.a(n);
            this.i();
            this.p();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b(final dg dg) throws Exception {
        if (dg != null) {
            if (dg.d5() < 0) {
                throw new Exception("Please set the annotation type before adding it to Annotations: ".concat(String.valueOf(String.valueOf(dg))));
            }
            this.b.a("".concat(String.valueOf(String.valueOf(dg.d0()))), dg);
            this.l = true;
        }
    }
    
    public final void e() {
    }
    
    protected final void c(final dg dg) {
        this.b(dg, false);
    }
    
    protected final void b(final dg dg, final boolean b) {
        try {
            if (dg != null && !dg.bh()) {
                final int az = dg.az();
                if (az == 1 || az == 0 || b) {
                    if (dg.d5() < 0) {
                        throw new Exception("Please set the deleted annotation type before adding it to Annotations: ".concat(String.valueOf(String.valueOf(dg))));
                    }
                    final long d0 = dg.d0();
                    boolean b2 = false;
                    for (int b3 = this.c.b(), i = 0; i < b3; ++i) {
                        if (((dg)this.c.b(i)).d0() == d0) {
                            b2 = true;
                            break;
                        }
                    }
                    if (!b2) {
                        dg.d(3);
                        this.c.a("".concat(String.valueOf(String.valueOf(dg.d0()))), dg);
                        this.l = true;
                        this.p();
                    }
                }
                else if (az != 3) {
                    dg.d(3);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final dg dg, final dg dg2) throws Exception {
        if (dg != null && dg2 != null && dg2.d5() >= 0) {
            final int b = this.b.b();
            final long d0 = dg.d0();
            for (int i = 0; i < b; ++i) {
                final dg dg3 = (dg)this.b.b(i);
                if (dg3.d0() == d0) {
                    dg2.o(dg3.ce());
                    this.b.a("".concat(String.valueOf(String.valueOf(d0))));
                    this.b.a("".concat(String.valueOf(String.valueOf(dg2.d0()))), dg2);
                    this.i();
                    this.p();
                    break;
                }
            }
        }
    }
    
    public final void f() {
        if (this.b != null) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                ((dg)this.b.b(i)).ek();
            }
        }
    }
    
    public final void a(final double n) {
        if (this.b != null) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                final dg dg = (dg)this.b.b(i);
                final ct cu = dg.cu();
                if (cu != null) {
                    final ct c = cu.c();
                    c.a *= (int)n;
                    c.b *= (int)n;
                    if (c.i != null) {
                        c.i = new Font(c.i.getFontName(), c.i.getStyle(), c.b);
                    }
                    dg.a(c, true);
                }
            }
        }
    }
    
    public final Vector a(final int n, final String s, final int n2) {
        Vector<Integer> vector = null;
        if (!s.equals(this.f)) {
            return vector;
        }
        int n3 = 0;
        if (this.b != null) {
            vector = new Vector<Integer>(10);
            for (int i = 1; i != 0; i = 1) {
                i = 0;
                for (int b = this.b.b(), j = 0; j < b; ++j) {
                    final dg dg = (dg)this.b.b(j);
                    if (dg.e(n)) {
                        final int k = dg.i(0);
                        if (n2 == -1 || k == n2) {
                            if (k > n3) {
                                vector.addElement(new Integer(k));
                                n3 = k;
                            }
                            this.b.d(j);
                            dg.gd();
                            break;
                        }
                    }
                }
            }
        }
        return vector;
    }
    
    public final boolean d(final dg dg) {
        return this.c(dg, true);
    }
    
    public final boolean c(final dg dg, final boolean b) {
        if (dg != null && this.b.a(dg)) {
            for (int b2 = this.b.b(), i = 0; i < b2; ++i) {
                final dg dg2 = (dg)this.b.b(i);
                if (dg2.d0() == dg.d0()) {
                    this.c(dg2);
                    this.b.d(i);
                    if (b) {
                        this.i();
                    }
                    this.p();
                    return true;
                }
            }
        }
        return false;
    }
    
    public final ax g() {
        return this.b.d();
    }
    
    public final ax h() {
        return this.c.d();
    }
    
    public final ax a(final Component component) {
        this.b(component);
        return this.d.d();
    }
    
    public final dg b(final String s) {
        return (dg)this.b.d(s);
    }
    
    public final dg c(final String s) {
        return (dg)this.c.d(s);
    }
    
    public final dg d(final String s) {
        return (dg)this.d.d(s);
    }
    
    public final dg e(final dg dg) {
        return (dg)this.b.d("".concat(String.valueOf(String.valueOf(dg.d0()))));
    }
    
    private final void p() {
        try {
            if (this.d != null) {
                this.d.c();
            }
            this.l = true;
        }
        catch (Exception ex) {}
    }
    
    private final void b(final Component component) {
        if (this.d != null && this.d.b() == 0) {
            final int b = this.b.b();
            if (b > 0) {
                for (int i = 0; i < b; ++i) {
                    final dg dg = (dg)this.b.b(i);
                    if (dg != null && dg.k() && !dg.bh()) {
                        final dg dg2 = dg;
                        this.d.a("".concat(String.valueOf(String.valueOf(dg2.d0()))), dg2);
                    }
                }
            }
            final int b2 = this.c.b();
            if (b2 > 0) {
                for (int j = 0; j < b2; ++j) {
                    final dg dg3 = (dg)this.c.b(j);
                    if (dg3 != null) {
                        final dg dg4 = dg3;
                        this.d.a("".concat(String.valueOf(String.valueOf(dg4.d0()))), dg4);
                    }
                }
            }
        }
    }
    
    public final dg e(final String s) {
        dg dg = null;
        try {
            if (s != null && this.b != null) {
                final String lowerCase = s.toLowerCase();
                dg dg2 = null;
                Block_7: {
                    for (int b = this.b.b(), i = 0; i < b; ++i) {
                        dg2 = (dg)this.b.b(i);
                        final String du = dg2.du();
                        if (!ji.util.d.by(du) && du.toLowerCase().equals(lowerCase)) {
                            break Block_7;
                        }
                    }
                    return dg;
                }
                dg = dg2;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return dg;
    }
    
    public final void a(final df df) throws Exception {
        final boolean j = this.j;
        try {
            this.j = true;
            if (df != null) {
                for (int b = df.b.b(), i = 0; i < b; ++i) {
                    this.a((dg)df.b.b(i), false);
                }
                this.i();
                this.p();
            }
        }
        finally {
            this.j = j;
        }
    }
    
    public final void c(final boolean b) throws Exception {
        if (this.b != null) {
            for (int b2 = this.b.b(), i = 0; i < b2; ++i) {
                ((dg)this.b.b(i)).y(b);
            }
            this.p();
        }
    }
    
    public final void d(final boolean b) throws Exception {
        if (this.b != null) {
            for (int b2 = this.b.b(), i = 0; i < b2; ++i) {
                ((dg)this.b.b(i)).bg(b);
            }
        }
    }
    
    public final boolean i(final int n) {
        boolean b = false;
        final int b2 = this.b.b();
        if (b2 > 0) {
            for (int i = 0; i < b2; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.dl()) {
                    dg.as(false);
                    b = true;
                }
            }
        }
        return b;
    }
    
    public final void j(final int n) {
        final int b = this.b.b();
        if (b > 0) {
            for (int i = 0; i < b; ++i) {
                ((dg)this.b.b(i)).as(false);
            }
        }
    }
    
    public final boolean a(final int n, final cy cy, final ad ad, final boolean b) {
        boolean b2 = false;
        final int b3 = this.b.b();
        if (b3 > 0) {
            for (int i = 0; i < b3; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.i(n) == n) {
                    boolean b4 = false;
                    final Dimension dk = dg.dk();
                    if (dk == null) {
                        b4 = true;
                    }
                    else {
                        Dimension dimension;
                        if (b) {
                            dimension = ad.a(n);
                        }
                        else {
                            dimension = cy.c5();
                        }
                        if (!dimension.equals(dk)) {
                            b4 = true;
                        }
                    }
                    if (b4) {
                        b2 = true;
                        final double ef = cy.ef();
                        final double eg = cy.eg();
                        final int a = cy.r().a;
                        Dimension dimension2;
                        if (b) {
                            dimension2 = ad.a(n);
                        }
                        else {
                            dimension2 = cy.c5();
                        }
                        dg.a(dimension2, ef, eg, a, b, ad.b());
                    }
                }
            }
        }
        return b2;
    }
    
    public final void a(final Component component, final int n) {
        if (this.a > 0) {
            try {
                boolean b = false;
                final c b2 = new c("newList3");
                final int b3 = this.b.b();
                if (b3 > 0) {
                    for (int i = 0; i < b3; ++i) {
                        final dg dg = (dg)this.b.b(i);
                        if (dg.i(-1) < 0) {
                            for (int j = 0; j < this.a; ++j) {
                                final dg c = dg.c(component);
                                c.bt();
                                c.a(null, -1.0, -1.0, n);
                                c.j(j + 1);
                                final String du = c.du();
                                String s;
                                if (ji.util.d.by(du)) {
                                    s = String.valueOf(String.valueOf(new StringBuffer("").append(j + 1)));
                                }
                                else {
                                    s = String.valueOf(String.valueOf(du)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("").append(j + 1))))));
                                }
                                c.q(s);
                                b2.a("".concat(String.valueOf(String.valueOf(c.d0()))), c);
                            }
                            b = true;
                        }
                        else {
                            b2.a("".concat(String.valueOf(String.valueOf(dg.d0()))), dg);
                        }
                    }
                }
                if (b) {
                    this.j();
                    this.b = b2;
                    this.i();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final int i() {
        synchronized (this) {
            return this.a((af)null);
        }
    }
    
    public final void a(final int n, final af af) {
        try {
            if (n > 0) {
                final int b = this.b.b();
                int max = 0;
                for (int i = 0; i < b; ++i) {
                    max = Math.max(max, ((dg)this.b.b(i)).i(0));
                }
                if (max > n) {
                    final c b2 = new c("jiAnnotations1B");
                    final int n2 = n + 1;
                    for (int j = 0; j < b; ++j) {
                        final dg dg = (dg)this.b.b(j);
                        if (dg.i(0) < n2) {
                            b2.a("".concat(String.valueOf(String.valueOf(dg.d0()))), dg);
                        }
                    }
                    this.j();
                    this.b = b2;
                    this.p();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final int a(final af af) {
        if (!ji.util.i.c(257)) {
            return this.b(af);
        }
        return this.c(af);
    }
    
    public final int b(final af af) {
        int ce = 0;
        int n = 0;
        if (!this.l) {
            for (int b = this.b.b(), i = 0; i < b; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg.gh()) {
                    final int ce2 = dg.ce();
                    if (ce2 > ce) {
                        ce = ce2;
                    }
                }
            }
            return ce;
        }
        try {
            final int b2 = this.b.b();
            boolean b3 = false;
            if (b2 > 0) {
                int n2 = 0;
                int min = -1;
                int max = 0;
                for (int j = 0; j < b2; ++j) {
                    final dg dg2 = (dg)this.b.b(j);
                    final int ce3 = dg2.ce();
                    if (dg2.gh()) {
                        ++n;
                        if (ce3 > -1) {
                            ++n2;
                            if (min == -1) {
                                min = ce3;
                            }
                            else {
                                min = Math.min(min, ce3);
                            }
                            max = Math.max(max, ce3);
                        }
                        dg2.a9(false);
                    }
                }
                final int n3 = b2;
                int n4 = 0;
                int max2;
                final int n5 = max2 = Math.max(n3 / 50, 1);
                final long currentTimeMillis = System.currentTimeMillis();
                int k = 0;
                int l = n2;
                while (k < n2) {
                    for (int n6 = b2 - 1; n6 >= 0; --n6) {
                        final dg dg3 = (dg)this.b.b(n6);
                        if (!dg3.ex() && dg3.gh()) {
                            final int ce4 = dg3.ce();
                            if (ce4 == min) {
                                if (ce4 != k) {
                                    dg3.o(k);
                                    if (dg3.az() == 0 && this.q() && !this.j) {
                                        dg3.d(1);
                                    }
                                }
                                ++k;
                                if (dg3.ce() > ce) {
                                    ce = dg3.ce();
                                }
                                dg3.a9(true);
                                if (af != null) {
                                    try {
                                        ++n4;
                                        if (--max2 <= 0) {
                                            max2 = n5;
                                            if (System.currentTimeMillis() - currentTimeMillis > 250) {
                                                b3 = true;
                                                this.a(af, 100 * n4 / n3);
                                            }
                                        }
                                    }
                                    catch (Exception ex) {}
                                }
                                if (k >= n2) {
                                    break;
                                }
                            }
                        }
                    }
                    ++min;
                }
                while (l < n) {
                    int n7 = b2 - 1;
                    while (n7 >= 0) {
                        final dg dg4 = (dg)this.b.b(n7);
                        if (!dg4.ex() && dg4.gh() && dg4.ce() == -1) {
                            dg4.o(l);
                            if (l > ce) {
                                ce = l;
                            }
                            ++l;
                            dg4.a9(true);
                            if (af != null) {
                                try {
                                    ++n4;
                                    if (--max2 <= 0) {
                                        max2 = n5;
                                        if (System.currentTimeMillis() - currentTimeMillis > 250) {
                                            b3 = true;
                                            this.a(af, 100 * n4 / n3);
                                        }
                                    }
                                }
                                catch (Exception ex2) {}
                                break;
                            }
                            break;
                        }
                        else {
                            --n7;
                        }
                    }
                }
            }
            if (b3) {
                this.a(af, 1);
            }
        }
        catch (Exception ex3) {}
        this.l = false;
        return ce;
    }
    
    public final int c(final af af) {
        int max = -2;
        if (!this.l) {
            return 0;
        }
        try {
            int n2;
            final int n = n2 = this.b.b();
            boolean b = false;
            if (n > 0) {
                for (int i = 0; i < n; ++i) {
                    final dg dg = (dg)this.b.b(i);
                    final int ce = dg.ce();
                    if (dg.gh()) {
                        dg.a9(false);
                        max = Math.max(max, ce);
                        n2 = Math.min(n2, ce);
                    }
                }
                int n3 = 0;
                int j = n2;
                final int n4 = n;
                int n5 = 0;
                int max2 = Math.max(n4 / 50, 1);
                final long currentTimeMillis = System.currentTimeMillis();
                while (j <= max) {
                    boolean b2 = false;
                    for (int k = 0; k < n; ++k) {
                        final dg dg2 = (dg)this.b.b(k);
                        if (!dg2.ex() && dg2.gh()) {
                            final int ce2 = dg2.ce();
                            if (ce2 == j) {
                                if (n3 != ce2) {
                                    dg2.o(n3);
                                    if (dg2.az() == 0 && ce2 != -1 && this.q() && !this.j) {
                                        dg2.d(1);
                                    }
                                }
                                ++n3;
                                dg2.a9(true);
                                b2 = true;
                                break;
                            }
                        }
                    }
                    if (!b2) {
                        ++j;
                    }
                    if (af != null) {
                        try {
                            ++n5;
                            if (--max2 > 0) {
                                continue;
                            }
                            max2 = max2;
                            if (System.currentTimeMillis() - currentTimeMillis <= 250) {
                                continue;
                            }
                            b = true;
                            this.a(af, 100 * n5 / n4);
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            if (b) {
                this.a(af, 1);
            }
        }
        catch (Exception ex2) {}
        this.l = false;
        return max;
    }
    
    public final c k(final int n) {
        final ax g = this.g();
        final c c = new c("zorderChanged");
        this.c(n);
        while (g.a()) {
            final dg b = this.b(g.b());
            if (b.i(n) == n) {
                final int ch = b.ch();
                if (ch != -1 && ch != b.ce()) {
                    b.o(ch);
                    this.a(b, c);
                }
                b.ci();
            }
        }
        return c;
    }
    
    private final void a(final dg dg, final c c) {
        final int b = c.b();
        if (b > 0) {
            int n;
            for (n = b - 1; n >= 0 && dg.ce() >= ((dg)c.b(n)).ce(); --n) {}
            c.b(dg, n + 1);
        }
        else {
            c.c(dg);
        }
    }
    
    public final boolean d(final dg dg, final boolean b) {
        boolean b2 = false;
        try {
            this.i();
            if (dg != null) {
                if (!ji.util.i.c(257)) {
                    b2 = this.f(dg, b);
                }
                else {
                    b2 = this.e(dg, b);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return b2;
    }
    
    private boolean e(final dg dg, final boolean b) {
        boolean b2 = false;
        final long d0 = dg.d0();
        final int ce = dg.ce();
        final int b3 = this.b.b();
        final int n = b3 - 1;
        if (b3 > 0) {
            for (int i = 0; i < b3; ++i) {
                final dg dg2 = (dg)this.b.b(i);
                if (dg2.gh()) {
                    final int ce2 = dg2.ce();
                    if (!b) {
                        dg2.q(ce2);
                    }
                    if (dg2.d0() != d0) {
                        if (ce2 < ce) {
                            dg2.o(ce2 + 1);
                        }
                        dg2.o(Math.min(dg2.ce(), n));
                        this.l = true;
                    }
                    else {
                        if (dg2.ce() != 0) {
                            b2 = true;
                        }
                        dg2.o(0);
                        this.l = true;
                    }
                    if (dg2.ce() != ce2 && dg2.az() == 0 && this.q() && !this.j) {
                        dg2.d(1);
                    }
                }
            }
        }
        return b2;
    }
    
    private boolean f(final dg dg, final boolean b) {
        boolean b2 = false;
        if (dg.gh()) {
            final int b3 = this.b.b();
            int n = 0;
            for (int i = 0; i < b3; ++i) {
                final dg dg2 = (dg)this.b.b(i);
                if (dg2.gh()) {
                    ++n;
                    if (!b && ji.util.d.bi()) {
                        dg2.q(dg2.ce());
                    }
                }
            }
            if (dg.ce() != n - 1) {
                dg.cf();
                this.l = true;
                this.i();
                b2 = true;
            }
        }
        return b2;
    }
    
    private boolean q() {
        boolean b = true;
        if (ji.util.d.bf()) {
            b = false;
            if (ji.util.d.bh() && ji.util.i.c(212)) {
                b = true;
            }
        }
        return b;
    }
    
    public final boolean f(final dg dg) {
        boolean b = false;
        try {
            this.i();
            if (dg != null) {
                if (!ji.util.i.c(257)) {
                    b = this.h(dg);
                }
                else {
                    b = this.i(dg);
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private boolean h(final dg dg) {
        boolean b = false;
        final long d0 = dg.d0();
        final int b2 = this.b.b();
        int ce = dg.ce();
        if (!dg.gh()) {
            return false;
        }
        if (ce == -1) {
            ce = b2;
        }
        if (b2 > 0) {
            for (int i = 0; i < b2; ++i) {
                final dg dg2 = (dg)this.b.b(i);
                if (dg2.gh()) {
                    boolean b3 = false;
                    final int ce2 = dg2.ce();
                    if (dg2.d0() == d0) {
                        if (ce2 != 0) {
                            dg2.o(0);
                            b3 = true;
                            this.l = true;
                            b = true;
                        }
                    }
                    else if (ce2 < ce) {
                        dg2.o(ce2 + 1);
                        b3 = true;
                        this.l = true;
                        b = true;
                    }
                    if (b3 && dg2.az() == 0 && this.q() && !this.j) {
                        dg2.d(1);
                    }
                }
            }
        }
        return b;
    }
    
    private boolean i(final dg dg) {
        boolean b = false;
        final long d0 = dg.d0();
        final int ce = dg.ce();
        final int b2 = this.b.b();
        final int n = b2 - 1;
        if (b2 > 0) {
            for (int i = 0; i < b2; ++i) {
                final dg dg2 = (dg)this.b.b(i);
                if (dg2.gh()) {
                    if (dg2.d0() != d0) {
                        final int ce2 = dg2.ce();
                        if (ce2 > ce) {
                            dg2.o(ce2 - 1);
                        }
                        dg2.o(Math.min(dg2.ce(), n));
                        this.l = true;
                    }
                    else {
                        if (dg2.ce() != b2 - 1) {
                            b = true;
                        }
                        dg2.o(b2 - 1);
                        this.l = true;
                    }
                }
            }
        }
        return b;
    }
    
    public final df b(final ad ad) {
        return this.a(ad, null);
    }
    
    public final df a(final ad ad, final af af) {
        final df df = new df(this.f);
        final int b = this.b.b();
        df.a = this.a;
        if (b > 0) {
            final int n = b;
            int n2 = 0;
            int max = Math.max(n / 50, 1);
            final long currentTimeMillis = System.currentTimeMillis();
            for (int i = 0; i < b; ++i) {
                final dg dg = (dg)this.b.b(i);
                if (dg != null) {
                    boolean equals = !dg.bh();
                    if (!equals) {
                        equals = ad.iu().equals(this.f);
                    }
                    if (equals) {
                        final dg c = dg.c((Component)ad);
                        c.at(false);
                        try {
                            df.a(c, false);
                        }
                        catch (Exception ex) {}
                    }
                }
                if (af != null) {
                    try {
                        ++n2;
                        if (--max <= 0) {
                            max = max;
                            if (System.currentTimeMillis() - currentTimeMillis > 250) {
                                this.a(af, 100 * n2 / n);
                            }
                        }
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        final int b2 = this.c.b();
        if (b2 > 0) {
            for (int j = 0; j < b2; ++j) {
                final dg dg2 = (dg)this.c.b(j);
                if (dg2 != null) {
                    final dg c2 = dg2.c((Component)ad);
                    try {
                        df.b(c2, true);
                    }
                    catch (Exception ex3) {}
                }
            }
        }
        df.a(this.g, this.i);
        if (this.g) {
            for (int n3 = 0; n3 < this.h.length && n3 <= this.i; ++n3) {
                df.a(n3, this.h[n3]);
            }
        }
        df.j = this.j;
        return df;
    }
    
    private final void a(final af af, final int n) {
        try {
            if (af != null) {
                af.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n)))));
            }
        }
        catch (Exception ex) {}
    }
    
    public final void g(final dg dg) {
        try {
            final dg dg2 = (dg)this.b.d("".concat(String.valueOf(String.valueOf(dg.d0()))));
            if (dg2 != null) {
                this.d(dg2);
                this.p();
            }
        }
        catch (Exception ex) {}
        this.b.a("".concat(String.valueOf(String.valueOf(dg.d0()))));
    }
    
    public final void f(final String s) {
        try {
            this.b.a(s);
        }
        catch (Exception ex) {}
    }
    
    public final void j() {
        this.e(false);
    }
    
    public final void e(final boolean b) {
        try {
            if (this.b != null) {
                try {
                    if (b) {
                        final int b2 = this.b.b();
                        if (b2 > 0) {
                            for (int i = 0; i < b2; ++i) {
                                final dg dg = (dg)this.b.b(i);
                                if (dg != null) {
                                    dg.gd();
                                }
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                this.b.c();
            }
        }
        catch (Exception ex2) {}
        this.b.c();
    }
    
    public final void a(final boolean b, final boolean b2, final String s) {
        final int b3 = this.b.b();
        if (b3 > 0) {
            for (int i = 0; i < b3; ++i) {
                ((dg)this.b.b(i)).b(b, s, b2);
            }
        }
    }
    
    public final void k() {
        if (this.c != null) {
            for (int i = 0; i < this.c.b(); ++i) {
                final dg dg = (dg)this.c.b(i);
                if (dg.af() == 1) {
                    this.c.b(dg);
                }
            }
        }
    }
    
    public final void l() {
        this.p();
        try {
            if (this.c != null) {
                this.c.c();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void m() {
        this.h = null;
        this.p();
        this.l();
    }
    
    public final void n() {
        final ax g = this.g();
        while (g.a()) {
            final dg b = this.b(g.b());
            if (b.gh()) {
                b.ge();
            }
        }
    }
    
    static {
        df.k = 0L;
    }
}
