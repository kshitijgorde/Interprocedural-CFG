// 
// Decompiled by Procyon v0.5.30
// 

package ji.net;

import java.awt.Component;
import ji.zip.a4;
import ji.v1event.ao;
import ji.annotate.b8;
import java.net.URL;
import ji.v1event.af;
import ji.io.h;
import ji.util.d;
import ji.awt.c;
import java.util.Enumeration;
import ji.document.ad;
import ji.awt.bb;
import java.util.Hashtable;

public class en
{
    String a;
    Hashtable b;
    Hashtable c;
    vv d;
    v6 e;
    int f;
    int g;
    boolean h;
    boolean i;
    bb j;
    ad k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    boolean s;
    static int t;
    static long u;
    static int v;
    Hashtable w;
    boolean x;
    
    public en() {
        this.a = null;
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = false;
        this.i = false;
        this.j = null;
        this.k = null;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.w = new Hashtable();
        this.x = true;
    }
    
    public final void a(final int l) {
        this.l = l;
    }
    
    public final void a(final int n, final boolean b, final ad k, final String a, final int f, final int g, final boolean s) {
        try {
            this.k = k;
            this.a = a;
            this.f = f;
            this.g = g;
            this.s = s;
            if (k != null) {
                k.j0();
            }
            if ((f != 0 || g != 0) && k.c7() && n > 0 && k.j8() > 1 && this.e == null) {
                this.e = new v6(n, b);
                new bb(a, this.e).start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a() {
        try {
            if (this.w.size() > 0 && this.w.size() >= en.v) {
                while (this.w.size() >= 0) {
                    final Enumeration<String> keys = this.w.keys();
                    vv vv = null;
                    Object o = null;
                    while (keys.hasMoreElements()) {
                        o = keys.nextElement();
                        vv = (vv)this.w.get(o);
                    }
                    if (vv != null) {
                        try {
                            vv.c();
                            vv.a().join();
                        }
                        catch (Exception ex2) {}
                        try {
                            vv.d();
                        }
                        catch (Exception ex3) {}
                        try {
                            this.w.remove(o);
                        }
                        catch (Exception ex4) {}
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b() {
        try {
            if (this.w.size() > 0 && this.w.size() >= en.v) {
                while (this.w.size() >= en.v) {
                    final Enumeration<String> keys = this.w.keys();
                    vv vv = null;
                    Object o = null;
                    while (keys.hasMoreElements()) {
                        o = keys.nextElement();
                        vv = (vv)this.w.get(o);
                    }
                    if (vv != null) {
                        try {
                            vv.a().join();
                        }
                        catch (Exception ex2) {}
                        try {
                            vv.d();
                        }
                        catch (Exception ex3) {}
                        try {
                            this.w.remove(o);
                        }
                        catch (Exception ex4) {}
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void a(final int n, final boolean b) {
        try {
            if ((this.f != 0 || this.g != 0) && this.k.c7() && n > 0 && !this.k.dw() && this.k.j8() > 1) {
                this.h = false;
                if (this.d == null) {
                    this.d = new vv(this.k, b, this.k.gg());
                    (this.j = new bb(this.a, this.d)).setPriority(1);
                    this.d.a(this.j);
                    this.j.start();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final ad ad, final int n) {
    }
    
    public final void c() {
        try {
            this.l = 0;
            this.m = 0;
            this.n = 0;
            this.b.clear();
            this.c.clear();
        }
        catch (Exception ex) {}
    }
    
    public final void d() {
        this.k = null;
        this.c();
    }
    
    static {
        en.t = 0;
        en.u = 0L;
        en.v = 2;
    }
    
    class vv implements Runnable
    {
        ad a;
        a0 b;
        boolean c;
        c d;
        c e;
        boolean f;
        int g;
        boolean h;
        long i;
        bb j;
        Object k;
        
        public void a(final bb j) {
            this.j = j;
            en.this.w.put("".concat(String.valueOf(String.valueOf(this.i))), this);
        }
        
        public final bb a() {
            return this.j;
        }
        
        public vv(final ad a, final boolean h, final Object k) {
            this.a = null;
            this.b = null;
            this.c = false;
            this.d = null;
            this.e = null;
            this.f = false;
            this.g = 0;
            this.h = false;
            this.i = 0L;
            this.j = null;
            this.k = null;
            this.i = en.u;
            ++en.u;
            this.a = a;
            this.h = h;
            this.k = k;
        }
        
        public final boolean b() {
            return this.c;
        }
        
        public final void c() {
            try {
                this.c = true;
                if (en.this.k != null) {
                    en.this.k.j0();
                }
                if (this.b != null) {
                    if (ji.util.d.dr() || ji.util.d.ds()) {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: Abort1."));
                    }
                    this.b.d();
                    if (ji.util.d.dr() || ji.util.d.ds()) {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: Abort2."));
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        public void run() {
            try {
                try {
                    if (ji.util.d.dr() || ji.util.d.ds()) {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: wait1..."));
                    }
                    while (!en.this.k.j3() && !this.c && !en.this.h) {
                        en.this.i = true;
                        ji.util.d.b(10, 2005, en.this.a);
                    }
                    if (ji.util.d.dr() || ji.util.d.ds()) {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: waited..."));
                    }
                }
                catch (Exception ex) {}
                finally {
                    en.this.i = false;
                }
                boolean b = true;
                if (!en.this.k.j1()) {
                    b = false;
                }
                if (this.c) {
                    b = false;
                }
                if (en.this.h) {
                    b = false;
                }
                if (this.f) {
                    b = false;
                }
                if (b) {
                    this.e();
                    en.this.l = en.this.k.j7();
                    Object[] js = null;
                    Object[] jt = null;
                    if (this.d != null && this.d.b() > 0) {
                        js = en.this.k.js();
                    }
                    if (this.e != null && this.e.b() > 0) {
                        jt = en.this.k.jt();
                    }
                    (this.b = new a0(this.a, en.this.a, en.this.x)).c(true);
                    this.b.b((af)null);
                    if (this.e != null && (jt != null || en.this.k.ju()) && this.e.b() > 0) {
                        if (en.this.k.ju()) {
                            this.a(this.a, this.b, this.e, jt, true, true, this.k);
                        }
                        else {
                            this.a(this.a, this.b, this.e, jt, true, false, this.k);
                        }
                    }
                    if (this.d != null && !this.h && (js != null || en.this.k.ju()) && this.d.b() > 0) {
                        if (en.this.k.ju()) {
                            this.a(this.a, this.b, this.d, js, false, true, this.k);
                        }
                        else {
                            this.a(this.a, this.b, this.d, js, false, false, this.k);
                        }
                    }
                }
            }
            catch (Exception ex2) {}
            finally {
                this.d();
            }
        }
        
        public final void d() {
            en.this.w.remove("".concat(String.valueOf(String.valueOf(this.i))));
            this.j = null;
            try {
                this.f();
            }
            catch (Exception ex) {}
            this.k = null;
            this.a = null;
            en.this.d = null;
            en.this.j = null;
            if (this.b != null) {
                this.b.a();
            }
            this.b = null;
        }
        
        void e() {
            try {
                if (en.this.f != 0) {
                    if (this.d == null) {
                        this.d = new c("jiDocumentPrefetchPages");
                    }
                    else {
                        this.d.c();
                    }
                }
                if (en.this.g == 0 && en.this.f != 0) {
                    en.this.g = en.this.f;
                }
                if (en.this.g != 0) {
                    if (this.e == null) {
                        this.e = new c("jiDocumentPrefetchThumbs");
                    }
                    else {
                        this.e.c();
                    }
                }
                if (en.this.f < 0) {
                    en.this.f = en.this.k.j8();
                }
                if (en.this.g < 0) {
                    en.this.g = en.this.k.j8();
                }
                final int j7 = en.this.k.j7();
                final int j8 = en.this.k.j8();
                if (en.this.f > 0) {
                    this.a(j7);
                    if ((en.this.l <= j7 && j7 != en.this.k.j8()) || j7 < 2) {
                        for (int i = j7 + 1; i <= j7 + en.this.f; ++i) {
                            if (i <= j8 && !this.c(i)) {
                                this.d.c("".concat(String.valueOf(String.valueOf(i))));
                            }
                        }
                    }
                    else {
                        for (int k = j7 - 1; k >= j7 - en.this.f; --k) {
                            if (k <= j8 && k > 0 && !this.c(k)) {
                                this.d.c("".concat(String.valueOf(String.valueOf(k))));
                            }
                        }
                    }
                    en.this.q = this.d.b();
                }
                if (en.this.g > 0) {
                    final int g5 = en.this.k.g5();
                    final int g6 = en.this.k.g6();
                    if (g5 > 0 && g6 > 0) {
                        if (en.this.n <= g6 && g6 != en.this.k.j8()) {
                            for (int l = g6 + 1; l <= g6 + en.this.g; ++l) {
                                if (l <= j8 && !this.f(l)) {
                                    this.e.c("".concat(String.valueOf(String.valueOf(l))));
                                }
                            }
                        }
                        else {
                            for (int n = g5 - 1; n >= g5 - en.this.g; --n) {
                                if (n <= j8 && n > 0 && !this.f(n)) {
                                    this.e.c("".concat(String.valueOf(String.valueOf(n))));
                                }
                            }
                        }
                        en.this.m = g5;
                        en.this.n = g6;
                    }
                    en.this.r = this.e.b();
                }
            }
            catch (Exception ex) {}
        }
        
        private final void a(final int n) {
            synchronized (en.this.b) {
                final en l = en.this;
                ++l.o;
                en.this.b.put("".concat(String.valueOf(String.valueOf(n))), "".concat(String.valueOf(String.valueOf(n))));
            }
            // monitorexit(this.l.b)
        }
        
        private final void b(final int n) {
            synchronized (en.this.b) {
                en.this.b.remove("".concat(String.valueOf(String.valueOf(n))));
            }
            // monitorexit(this.l.b)
        }
        
        private final boolean c(final int n) {
            synchronized (en.this.b) {
                // monitorexit(this.l.b)
                return en.this.b.get("".concat(String.valueOf(String.valueOf(n)))) != null;
            }
        }
        
        private final void d(final int n) {
            synchronized (en.this.c) {
                final en l = en.this;
                ++l.p;
                en.this.c.put("".concat(String.valueOf(String.valueOf(n))), "".concat(String.valueOf(String.valueOf(n))));
            }
            // monitorexit(this.l.c)
        }
        
        private final void e(final int n) {
            synchronized (en.this.c) {
                en.this.c.remove("".concat(String.valueOf(String.valueOf(n))));
            }
            // monitorexit(this.l.c)
        }
        
        private final boolean f(final int n) {
            synchronized (en.this.c) {
                // monitorexit(this.l.c)
                return en.this.c.get("".concat(String.valueOf(String.valueOf(n)))) != null;
            }
        }
        
        void a(final ad ad, final a0 a0, final c c, final Object[] array, final boolean b, final boolean b2, final Object o) {
            try {
                if (ji.util.d.dr() || ji.util.d.ds()) {
                    ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch entry."));
                }
                if (c.b() > 0 && (array != null || b2)) {
                    int n = 0;
                    while (c.b() > 0) {
                        if (!en.this.k.j1()) {
                            break;
                        }
                        if (en.this.h) {
                            break;
                        }
                        if (this.f) {
                            break;
                        }
                        this.g = ji.util.d.c((String)c.b(0), 0);
                        c.d(0);
                        if (this.g <= 0) {
                            continue;
                        }
                        if (n != 0) {
                            if (ji.util.d.dr() || ji.util.d.ds()) {
                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch was aborted for page ").append(this.g))));
                            }
                            if (b) {
                                this.e(this.g);
                            }
                            else {
                                this.b(this.g);
                            }
                        }
                        else {
                            boolean b3 = true;
                            if (b) {
                                if (this.f(this.g)) {
                                    b3 = false;
                                }
                            }
                            else if (this.c(this.g)) {
                                b3 = false;
                            }
                            if (!b3) {
                                continue;
                            }
                            System.currentTimeMillis();
                            try {
                                if (array == null && !b2) {
                                    continue;
                                }
                                Object d;
                                if (b2) {
                                    d = en.this.k.d(this.g, b);
                                }
                                else {
                                    d = array[this.g - 1];
                                }
                                if (!(d instanceof URL)) {
                                    continue;
                                }
                                String s;
                                if (b) {
                                    s = "Thumbnail";
                                }
                                else {
                                    s = "Fullpage";
                                }
                                Object a2 = null;
                                try {
                                    if (ji.util.d.a(ad) && o != null) {
                                        a2 = b8.a(o, this.g, true);
                                        if (ji.util.d.dr() || ji.util.d.ds()) {
                                            ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch1 Annotations for page ").append(this.g).append(": ").append(a2).append("..."))));
                                        }
                                        String s2 = a0.b((URL)a2, ad, en.this.k);
                                        if (s2 != null) {
                                            if (s2.startsWith("V!RF")) {
                                                s2 = s2.substring("V!RF".length());
                                            }
                                            byte[] array2;
                                            if (ad.n() != null) {
                                                array2 = a4.a(s2.getBytes(ad.n()));
                                            }
                                            else {
                                                array2 = a4.a(s2.getBytes());
                                            }
                                            b8.a(this.g, ad.jf(), en.this.a, ad.g8(), ad, ad, array2);
                                            b8.a(this.g, en.this.a, en.this.k, false);
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch failed to retrieve annotations: ").append(a2))));
                                    ex.printStackTrace();
                                }
                                try {
                                    if (en.this.k.jx() != null) {
                                        if (en.this.k.jx() instanceof URL) {
                                            if (ji.util.d.dr() || ji.util.d.ds()) {
                                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch background image1: ").append(en.this.k.jx()).append("..."))));
                                            }
                                            a0.a((URL)en.this.k.jx(), ad, en.this.k);
                                            if (ji.util.d.dr() || ji.util.d.ds()) {
                                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch background image1 done."));
                                            }
                                        }
                                    }
                                    else if (en.this.k.jy() != null) {
                                        final Object[] jy = en.this.k.jy();
                                        if (jy[this.g - 1] instanceof URL) {
                                            if (ji.util.d.dr() || ji.util.d.ds()) {
                                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch background image2: ").append(jy[this.g - 1]).append("..."))));
                                            }
                                            a0.a((URL)jy[this.g - 1], ad, en.this.k);
                                            if (ji.util.d.dr() || ji.util.d.ds()) {
                                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch background image2 done."));
                                            }
                                        }
                                    }
                                }
                                catch (Exception ex2) {}
                                if (ji.util.d.dr() || ji.util.d.ds()) {
                                    ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch1 ").append(s).append(" ").append(this.g).append(": ").append(d).append("..."))));
                                }
                                final String a3 = a0.a((URL)d, ad, en.this.k);
                                if (a3 != null) {
                                    if (b) {
                                        this.d(this.g);
                                    }
                                    else {
                                        this.a(this.g);
                                    }
                                    if (ji.util.d.dr() || ji.util.d.ds()) {
                                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch1 done."));
                                    }
                                    if (en.this.s && en.this.k != null) {
                                        if (ji.util.d.dr() || ji.util.d.ds()) {
                                            ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch rendering..."));
                                        }
                                        en.this.k.jz();
                                        en.this.k.bd(this.g);
                                        if (ji.util.d.dr() || ji.util.d.ds()) {
                                            ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch rendered."));
                                        }
                                    }
                                }
                                else {
                                    if (ji.util.d.dr() || ji.util.d.ds()) {
                                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch was aborted for page ").append(this.g))));
                                    }
                                    if (b) {
                                        this.e(this.g);
                                    }
                                    else {
                                        this.b(this.g);
                                    }
                                    n = 1;
                                }
                                if ((!ji.util.d.dr() && !ji.util.d.ds()) || a3 == null) {
                                    continue;
                                }
                                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Prefetch ").append(s).append(" ").append(this.g).append(": Complete"))));
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
            }
            catch (Exception ex4) {}
            en.this.a(ad, 0);
            if (ji.util.d.dr() || ji.util.d.ds()) {
                ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch exit."));
            }
        }
        
        void f() {
            try {
                if (this.d != null) {
                    this.d.c();
                    this.d = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.e != null) {
                    this.e.c();
                    this.e = null;
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    class v6 implements Runnable
    {
        int a;
        boolean b;
        boolean c;
        
        public v6(final int a, final boolean c) {
            this.a = 0;
            this.b = false;
            this.c = false;
            ++en.t;
            this.a = a;
            this.c = c;
        }
        
        public void run() {
            try {
                try {
                    en.this.b();
                    if (en.this.d != null && en.this.d.b()) {
                        if (ji.util.d.dr() || ji.util.d.ds()) {
                            ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: Waiting for previous abort..."));
                        }
                        en.this.j.join();
                        if (ji.util.d.dr() || ji.util.d.ds()) {
                            ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: Previous abort complete..."));
                        }
                    }
                }
                catch (Exception ex2) {}
                if (ji.util.d.dr() || ji.util.d.ds()) {
                    ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: Waiting for user to settle..."));
                }
                for (int c = ji.util.d.c(), n = 100; !this.b && !en.this.h && c > 0; c -= n) {
                    en.this.i = true;
                    ji.util.d.b(n, 2005, en.this.a);
                }
                if (ji.util.d.dr() || ji.util.d.ds()) {
                    if (this.b) {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: aborted..."));
                    }
                    else {
                        ji.io.h.d(en.this.a, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Prefetch: User settled..."));
                    }
                }
                if (!this.b) {
                    en.this.a(this.a, this.c);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                --en.t;
                en.this.e = null;
            }
        }
    }
}
