// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.req.g;
import com.daysofwonder.util.s;
import com.daysofwonder.req.G;
import com.daysofwonder.req.f;
import com.daysofwonder.a.j;
import com.daysofwonder.req.o;
import com.daysofwonder.a.n;
import com.daysofwonder.req.A;
import com.daysofwonder.req.t;
import com.daysofwonder.req.q;
import com.daysofwonder.req.H;
import com.daysofwonder.req.x;
import com.daysofwonder.req.B;
import com.daysofwonder.req.b;
import com.daysofwonder.req.E;
import java.util.Enumeration;
import com.daysofwonder.req.l;
import java.util.ArrayList;
import java.util.List;
import com.daysofwonder.req.u;
import com.daysofwonder.req.d;
import com.daysofwonder.a.c;
import com.daysofwonder.req.k;
import com.daysofwonder.req.C;
import com.daysofwonder.req.D;
import com.daysofwonder.a.a;
import com.daysofwonder.a.e;
import com.daysofwonder.a.i;
import java.util.Vector;
import java.util.Hashtable;
import java.io.PrintWriter;

public abstract class aG
{
    private static final int K;
    private static final PrintWriter L;
    protected int a;
    protected Object b;
    protected int c;
    protected boolean d;
    protected boolean e;
    protected boolean f;
    protected boolean g;
    protected boolean h;
    protected boolean i;
    protected int j;
    protected v k;
    protected I l;
    protected aq m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    protected Hashtable q;
    protected Vector r;
    protected i s;
    protected int t;
    protected String u;
    protected String v;
    protected J w;
    protected boolean x;
    protected boolean y;
    protected Vector z;
    protected Vector A;
    protected Vector B;
    protected boolean C;
    protected long D;
    protected boolean E;
    protected boolean F;
    protected long G;
    protected boolean H;
    protected ag I;
    private e M;
    private e N;
    private i O;
    public static final a J;
    private long P;
    private int Q;
    private int R;
    private String S;
    private int T;
    
    private static void a(final String s) {
        aG.L.println("BaseGame:" + s);
    }
    
    public aG(final String s, final int n) {
        this.a = 0;
        this.q = new Hashtable();
        this.r = new Vector();
        this.z = new Vector();
        this.A = new Vector();
        this.B = new Vector();
        this.M = new e();
        this.N = new e();
        this.x = false;
        this.y = true;
    }
    
    public void a(final ag i) {
        this.I = i;
    }
    
    public void a(final aq m) {
        this.f = false;
        this.k = m.d();
        this.l = m.c();
        this.m = m;
        this.a = 2;
    }
    
    public void a(final J w) {
        this.w = w;
    }
    
    public J ag() {
        return this.w;
    }
    
    public abstract i a(final D p0);
    
    public int a(final String u, String s, final String s2, final String s3) {
        if (s == null) {
            s = "";
        }
        final C c = new C(u, s, s2, s3);
        this.l.a(c);
        if (aG.K > 1) {
            a(" sending auth req for " + u + " with id " + c.z());
        }
        final k n = this.n(c.z());
        if (aG.K > 1) {
            a(" got server answer " + n);
        }
        if (n instanceof com.daysofwonder.req.i) {
            if (((com.daysofwonder.req.i)n).e() != com.daysofwonder.a.c.a().b()) {
                return -3;
            }
            this.e = true;
            this.u = u;
            this.v = c(this.u);
            this.c(((com.daysofwonder.req.i)n).d());
            (this.s = this.a(((com.daysofwonder.req.i)n).g())).a(this.v);
            this.j = 0;
            this.T = ((com.daysofwonder.req.i)n).f();
            return 1;
        }
        else {
            if (n instanceof d && ((d)n).d() == 10) {
                return -2;
            }
            return -1;
        }
    }
    
    public void a(final String u, final String s) {
        this.e = true;
        this.u = u;
    }
    
    public void ah() {
        System.out.println("TABLE LIST");
        if (this.l != null) {
            this.l.a(new u());
        }
    }
    
    private List a(final Vector vector) {
        int n = 0;
        final ArrayList list = new ArrayList<com.daysofwonder.applet.k>(vector.size());
        list.add(new com.daysofwonder.applet.k(this.s));
        final Enumeration<l> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final l l = elements.nextElement();
            if (n == 0 && l.a == this.s.w()) {
                n = 1;
            }
            else {
                l.b = c(l.b);
                list.add(new com.daysofwonder.applet.k(l));
            }
        }
        return list;
    }
    
    private List a(final E e) {
        final ArrayList<ar> list = new ArrayList<ar>();
        if (e != null) {
            final Enumeration<b> elements = e.d().elements();
            while (elements.hasMoreElements()) {
                final StringBuffer sb = new StringBuffer();
                final b b = elements.nextElement();
                if (b.n && this.s.u() > 20) {
                    continue;
                }
                final ar ar = new ar();
                ar.a = b.c;
                ar.h = b.m;
                ar.j = b.b;
                ar.b = b.d;
                ar.i = b.n;
                for (int i = 0; i < ar.b.length; ++i) {
                    ar.b[i] = c(ar.b[i]);
                    if (b.e[i] == this.aD() && b.h[i]) {
                        this.c(true);
                    }
                }
                ar.c = b.e;
                ar.g = b.h;
                ar.d = b.f;
                ar.e = b.g;
                ar.f = b.i;
                ar.k = b.j;
                ar.l = b.k;
                ar.m = b.l;
                ar.n = b.a;
                ar.o = b.o;
                ar.p = b.p;
                ar.q = b.q;
                list.add(ar);
            }
        }
        return list;
    }
    
    public synchronized void a(final an an) {
        if (aG.K > 1) {
            a("adding ignorelist listener");
        }
        this.B.addElement(an);
    }
    
    public synchronized void b(final an an) {
        if (aG.K > 1) {
            a("removing ignorelist listener " + this.B.size());
        }
        this.B.removeElement(an);
        if (aG.K > 1) {
            a("removing ignorelist listener " + this.B.size());
        }
    }
    
    public synchronized void a(final com.daysofwonder.applet.e e) {
        if (aG.K > 1) {
            a("adding buddylist listener");
        }
        this.A.addElement(e);
    }
    
    public synchronized void b(final com.daysofwonder.applet.e e) {
        if (aG.K > 1) {
            a("removing buddylist listener " + this.A.size());
        }
        this.A.removeElement(e);
        if (aG.K > 1) {
            a("removing buddylist listener " + this.A.size());
        }
    }
    
    public synchronized void a(final p p) {
        if (aG.K > 1) {
            a("adding changetable listener");
        }
        this.z.addElement(p);
    }
    
    public synchronized void b(final p p) {
        if (aG.K > 1) {
            a("removing changetable listener " + this.z.size());
        }
        this.z.removeElement(p);
        if (aG.K > 1) {
            a("removing changetable listener " + this.z.size());
        }
    }
    
    public synchronized void ai() {
        this.l.a(new B(true, 1));
    }
    
    public synchronized void aj() {
        this.l.a(new B(true, 2));
    }
    
    public synchronized boolean ak() {
        boolean b = false;
        final Enumeration<com.daysofwonder.applet.e> elements = (Enumeration<com.daysofwonder.applet.e>)this.A.elements();
        while (elements.hasMoreElements()) {
            final com.daysofwonder.applet.e nextElement = elements.nextElement();
            if (nextElement instanceof com.daysofwonder.applet.e) {
                final com.daysofwonder.applet.e e = nextElement;
                if (aG.K > 2) {
                    a("firing buddylist changed");
                }
                e.a(this.M);
                b = true;
            }
        }
        return b;
    }
    
    public synchronized boolean al() {
        boolean b = false;
        final Enumeration<an> elements = (Enumeration<an>)this.B.elements();
        while (elements.hasMoreElements()) {
            final an nextElement = elements.nextElement();
            if (nextElement instanceof an) {
                final an an = nextElement;
                if (aG.K > 2) {
                    a("firing buddylist changed");
                }
                an.b(this.N);
                b = true;
            }
        }
        return b;
    }
    
    public synchronized boolean b(final k k) {
        boolean b = false;
        if (k instanceof x) {
            this.w.a((x)k);
            b = true;
        }
        else if (k instanceof H) {
            this.w.a((H)k);
            b = true;
        }
        else if (k instanceof B) {
            this.M = ((B)k).d();
            if (this.ak()) {
                b = true;
            }
        }
        else if (k instanceof q) {
            this.N = ((q)k).d();
            if (this.al()) {
                b = true;
            }
        }
        else if (k instanceof E) {
            final E e = (E)k;
            if (this.z.size() > 0) {
                final Enumeration<p> elements = (Enumeration<p>)this.z.elements();
                while (elements.hasMoreElements()) {
                    final p nextElement = elements.nextElement();
                    if (nextElement instanceof p && k instanceof E) {
                        nextElement.a(e.f(), e.g(), this.a(e), this.a(e.e()));
                        b = true;
                    }
                }
            }
        }
        return b;
    }
    
    public boolean am() {
        this.h = false;
        this.l.a(new t("-1", "Resumed Game", "", true));
        t t;
        try {
            t = (t)this.a(t.class);
            this.c = t.e();
        }
        catch (ClassCastException ex) {
            return false;
        }
        catch (Exception ex2) {
            return false;
        }
        this.g = true;
        this.a(t);
        return true;
    }
    
    public void b(final boolean g) {
        com.daysofwonder.util.t.a("SetResurrected: " + g);
        this.g = g;
    }
    
    public boolean a(final String s, final String s2, final String s3) {
        this.h = false;
        this.l.a(new t(s, s3, s2, true));
        t t;
        try {
            t = (t)this.a(t.class);
            this.c = t.e();
        }
        catch (ClassCastException ex) {
            return false;
        }
        this.g = true;
        this.a(t);
        return true;
    }
    
    public abstract void a(final t p0);
    
    public boolean an() {
        return this.g;
    }
    
    public void k(final int n) {
        this.l.a(new H(n, true));
    }
    
    public void l(final int n) {
        this.l.a(new H(n));
    }
    
    public int b(final String s, String s2, final String s3) {
        this.ao();
        this.h = false;
        com.daysofwonder.applet.y.a();
        if (s2 == null) {
            s2 = "";
        }
        this.l.a(new t(s, s3, s2, false));
        final k a = this.a(t.class);
        if (!(a instanceof t)) {
            if (a instanceof d) {
                this.ap();
                return -((d)a).d();
            }
            this.ap();
            return -1;
        }
        else {
            final t t = (t)a;
            if (t == null) {
                this.ap();
                return -1;
            }
            this.c = t.e();
            final int f = t.f();
            if (t.d() != null) {
                final Enumeration elements = t.d().elements();
                while (elements.hasMoreElements()) {
                    final D d = elements.nextElement();
                    final int c = d.c();
                    final i a2 = this.a(d);
                    if (f == c) {
                        if (c == 0) {
                            this.d = true;
                        }
                        this.t = this.r.size();
                        this.s = a2;
                    }
                    this.r.addElement(a2);
                    if (aG.K > 1) {
                        System.out.println("Name;: " + a2.z() + " id: " + a2.v());
                    }
                    this.q.put(c, a2);
                }
            }
            this.b(t);
            return 1;
        }
    }
    
    public int a(final Integer n, String s) {
        this.ao();
        com.daysofwonder.applet.y.a();
        com.daysofwonder.util.t.a("selectTable: " + n + " pass: " + ((s == null) ? "null" : s));
        this.h = false;
        if (s == null) {
            s = "";
        }
        final t t = new t("table", s, n);
        this.l.a(t);
        com.daysofwonder.util.t.a("selectTable: sent " + t);
        final k a = this.a(t.class);
        com.daysofwonder.util.t.a("selectTable: received " + a);
        if (!(a instanceof t)) {
            com.daysofwonder.util.t.a("selectTable: req is not ClientTableRequest");
            if (a instanceof d) {
                com.daysofwonder.util.t.a("selectTable: req is ErrorRequest ID: " + ((d)a).d());
                this.ap();
                return -((d)a).d();
            }
            com.daysofwonder.util.t.a("selectTable: return -1");
            this.ap();
            return -1;
        }
        else {
            final t t2 = (t)a;
            if (t2 == null) {
                com.daysofwonder.util.t.a("selectTable: res == null");
                this.ap();
                return -1;
            }
            this.c = t2.e();
            com.daysofwonder.util.t.a("selectTable: joining tid: " + this.c);
            final int f = t2.f();
            if (t2.d() != null) {
                final Enumeration elements = t2.d().elements();
                while (elements.hasMoreElements()) {
                    final D d = elements.nextElement();
                    final int c = d.c();
                    final i a2 = this.a(d);
                    if (f == c) {
                        if (c == 0) {
                            this.d = true;
                        }
                        this.t = this.r.size();
                        this.s = a2;
                    }
                    this.r.addElement(a2);
                    this.q.put(c, a2);
                }
            }
            com.daysofwonder.util.t.a("selectTable: returning 1");
            this.b(t2);
            return 1;
        }
    }
    
    protected synchronized void ao() {
        com.daysofwonder.util.t.a("queuePlayerRequest()");
        this.p = true;
    }
    
    protected synchronized void ap() {
        com.daysofwonder.util.t.a("stopQueueingPlayerRequest()");
        this.p = false;
    }
    
    public boolean b(final Integer n) {
        com.daysofwonder.applet.y.a();
        this.ao();
        com.daysofwonder.util.t.a("observeTable: observe " + n);
        final A a = new A(n);
        com.daysofwonder.util.t.a("observeTable: sending " + a);
        this.l.a(a);
        A a2;
        try {
            a2 = (A)this.a(A.class);
        }
        catch (ClassCastException ex) {
            com.daysofwonder.util.t.a("observeTable: error ");
            com.daysofwonder.util.t.a(ex);
            this.ap();
            return false;
        }
        if (a2 == null) {
            com.daysofwonder.util.t.a("observeTable: res=null");
            this.ap();
            return false;
        }
        this.c = a2.h();
        this.h = true;
        this.O = this.s;
        this.a(a2);
        return true;
    }
    
    public abstract void a(final A p0);
    
    public abstract void b(final t p0);
    
    public int a(final String s, final String s2, final boolean b, final boolean b2, final int n, final int n2, final n n3, final String s3) {
        this.h = false;
        com.daysofwonder.util.t.a("createTable: begin");
        this.ao();
        com.daysofwonder.applet.y.a();
        try {
            if (this.aK().a(s3)) {
                final t t = new t(s, s2, true, b, b2, n, n2, n3);
                if (aG.K > 1) {
                    a(" send table creation req ");
                }
                com.daysofwonder.util.t.a("createTable: send ClientTableRequest");
                this.l.a(t);
                t t2;
                try {
                    com.daysofwonder.util.t.a("createTable: waiting for ClientTableRequest");
                    t2 = (t)this.a(t.class);
                    com.daysofwonder.util.t.a("createTable: got ClientTableRequest");
                }
                catch (ClassCastException ex) {
                    com.daysofwonder.util.t.a("got: " + ex.getMessage());
                    this.ap();
                    return -1;
                }
                if (t2 == null) {
                    com.daysofwonder.util.t.a("createTable: res==null");
                    this.ap();
                    return -1;
                }
                a(" send table creation answer id " + t.z() + " tableid: " + t2.e());
                this.c = t2.e();
                this.t = 0;
                this.r.addElement(this.s);
                this.s.h(t2.f());
                if (aG.K > 1) {
                    System.out.println("Name;: " + this.s.z() + " id: " + this.s.v() + " index " + this.t);
                }
                this.q.put(t2.f(), this.s);
                this.d = true;
                com.daysofwonder.util.t.a("createTable: return " + this.c);
                return this.c;
            }
        }
        catch (Exception ex2) {
            com.daysofwonder.util.t.a("createTable: got Exception " + ex2.getMessage());
            com.daysofwonder.applet.y.a("createTable", ex2);
        }
        this.ap();
        com.daysofwonder.util.t.a("createTable: return -1");
        return -1;
    }
    
    public int aq() {
        return this.a;
    }
    
    public void m(final int n) {
        this.a(n, -1);
    }
    
    public void a(final String s, final int n) {
        if (!this.h) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.P > 750L) {
                this.P = currentTimeMillis;
                if (this.S != null && this.S.equals(s) && this.Q > 3) {
                    com.daysofwonder.util.t.a("chat identical, blacklisted: " + this.S + " on " + this.Q);
                    ++this.Q;
                }
                else {
                    if (this.S != null && !s.equals(this.S)) {
                        com.daysofwonder.util.t.a("chat not identical whitelisting: " + this.Q);
                        this.Q = 0;
                    }
                    else {
                        com.daysofwonder.util.t.a("chat identical: " + this.Q);
                        ++this.Q;
                    }
                    this.l.a(new com.daysofwonder.req.a(this.u, s, n));
                    this.S = s;
                }
            }
            this.w.a(this.v, s);
        }
    }
    
    public void a(final int r, final int n) {
        if (!this.h) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.P > 750L) {
                this.P = currentTimeMillis;
                if (this.R == r && this.Q > 3) {
                    com.daysofwonder.util.t.a("chat identical, blacklisted: " + this.R + " on " + this.Q);
                    ++this.Q;
                }
                else {
                    if (this.S != null && this.R != r) {
                        com.daysofwonder.util.t.a("chat not identical whitelisting: " + this.Q);
                        this.Q = 0;
                    }
                    else {
                        com.daysofwonder.util.t.a("chat identical: " + this.Q);
                        ++this.Q;
                    }
                    this.R = r;
                    this.l.a(new com.daysofwonder.req.a(this.u, r, n));
                }
            }
            this.w.a(this.v, r);
        }
    }
    
    public void b(final String s) {
        this.a(s, -1);
    }
    
    protected void c(final k k) {
        com.daysofwonder.util.t.a("playerLeaving: " + k);
        this.C = true;
        final com.daysofwonder.req.y y = (com.daysofwonder.req.y)k;
        final int d = y.d();
        if (d != -1) {
            com.daysofwonder.util.t.a("playerLeaving: id " + d);
            final i i = this.q.get(y.d());
            if (i != null) {
                if (i == this.s) {
                    this.x = false;
                    if (i != null && this.w != null) {
                        final J w = this.w;
                        int n;
                        if (y.f()) {
                            final J w2 = this.w;
                            n = 7;
                        }
                        else {
                            final J w3 = this.w;
                            n = 5;
                        }
                        w.b(n, null);
                    }
                    this.au();
                    return;
                }
                com.daysofwonder.util.t.a("Removing: " + i.v() + " name: " + i.z());
                if (y.f()) {
                    final J w4 = this.w;
                    final J w5 = this.w;
                    w4.a(700, new Object[] { i.z() });
                }
                else {
                    final J w6 = this.w;
                    final J w7 = this.w;
                    w6.a(5, new Object[] { i.z() });
                }
                this.q.remove(y.d());
                this.r.removeElement(i);
                com.daysofwonder.util.t.a("Player hash:");
                final Enumeration<Integer> keys = (Enumeration<Integer>)this.q.keys();
                while (keys.hasMoreElements()) {
                    final Integer n2 = keys.nextElement();
                    com.daysofwonder.util.t.a("hash: " + n2 + " val " + ((i)this.q.get(n2)).v() + " name " + ((i)this.q.get(n2)).z());
                }
                this.t = this.r.indexOf(this.s);
                if (this.w != null) {
                    this.w.b();
                    this.w.a();
                }
            }
            if (y.e()) {
                this.x = false;
                if (i != null && this.w != null) {
                    final J w8 = this.w;
                    final J w9 = this.w;
                    w8.b(0, new Object[] { i.z() });
                }
                this.au();
            }
        }
    }
    
    public void ar() {
        this.G = System.currentTimeMillis();
        if (this.l != null && this.m != null && !this.m.e()) {
            com.daysofwonder.util.t.a("--> sending ping: " + this.G);
            this.l.a(new o(this.G));
        }
    }
    
    public abstract j Z();
    
    public synchronized void d(k k) {
        try {
            k = (k)com.daysofwonder.req.f.a(k);
            if (k instanceof o) {
                com.daysofwonder.util.t.a("got ping: " + ((o)k).d());
                return;
            }
            com.daysofwonder.util.t.a("got req: " + k);
            if (this.Z() != null) {
                k.a(this.Z());
                k.c();
            }
            else {
                k.o();
            }
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.c("got an InstantiationException from request factory - will postpone request");
            com.daysofwonder.util.t.a(ex);
            k.o();
            try {
                if (this.m != null) {
                    this.m.a(k);
                }
            }
            catch (Exception ex2) {
                com.daysofwonder.util.t.a(ex2);
            }
            return;
        }
        if (k instanceof H) {
            final H h = (H)k;
            if (this.w != null && h.q()) {
                this.w.a(h.d());
                return;
            }
        }
        if (k instanceof com.daysofwonder.req.a) {
            final com.daysofwonder.req.a a = (com.daysofwonder.req.a)k;
            if (this.w != null) {
                String s = null;
                if (a.j() && (a.g() == this.s.v() || this.s.z().equals(a.e()))) {
                    this.w.a((String)null);
                    return;
                }
                if (a.g() != -1) {
                    final i i = this.q.get(a.g());
                    if (i != null) {
                        s = i.z();
                    }
                    if (this.aA()) {
                        s = "[table] " + s;
                    }
                    else if (a.f() != null && !this.F) {
                        final StringBuffer sb = new StringBuffer();
                        sb.append('[').append(a.f().substring(0, Math.min(a.f().length(), 6))).append("] ").append(s);
                        s = sb.toString();
                    }
                }
                else if (a.e() != null) {
                    s = c(a.e());
                    if (this.aA() || this.F) {
                        s = "[lobby] " + s;
                    }
                    else if (a.f() != null && !this.F) {
                        final StringBuffer sb2 = new StringBuffer();
                        sb2.append('[').append(a.f().substring(0, Math.min(a.f().length(), 6))).append("] ").append(s);
                        s = sb2.toString();
                    }
                }
                if (s != null) {
                    if (a.a_() != -1) {
                        this.w.a(s, a.a_(), a.h());
                    }
                    else {
                        this.w.a(s, a.d(), a.h());
                    }
                    if (a.j()) {
                        this.w.a(s);
                    }
                }
            }
            return;
        }
        if (k instanceof com.daysofwonder.req.y && this.p) {
            com.daysofwonder.util.t.a("Queuing incoming LeaveGameRequest: " + k);
        }
        try {
            if (!this.b(k)) {
                com.daysofwonder.util.t.a("enq " + k);
                if (this.m != null) {
                    this.m.a(k);
                }
                com.daysofwonder.util.t.a("enq done");
            }
        }
        catch (s s2) {}
        catch (com.daysofwonder.util.p p) {}
        catch (Exception ex3) {
            com.daysofwonder.util.t.a(ex3);
            com.daysofwonder.applet.y.a("incoming", ex3);
        }
    }
    
    public k n(final int n) {
        final k as = this.as();
        if (as == null) {
            return null;
        }
        if (as.z() == n) {
            return as;
        }
        System.out.println("got id " + as.z() + " waited " + n + " for req: " + as);
        return null;
    }
    
    public k a(final Class clazz) {
        com.daysofwonder.util.t.a("waiting for: " + clazz.getName());
        while (true) {
            final k as = this.as();
            if (as == null) {
                com.daysofwonder.util.t.a("waitFor: req == null");
                return null;
            }
            com.daysofwonder.util.t.a("waitFor: got req: " + as.getClass());
            if (as.getClass() == clazz) {
                com.daysofwonder.util.t.a("waitFor: matching class");
                return as;
            }
            if (as.getClass() == d.class) {
                com.daysofwonder.util.t.a("waitFor: matching Error");
                return as;
            }
            com.daysofwonder.util.t.a("waitFor: going another round");
        }
    }
    
    protected k as() {
        try {
            com.daysofwonder.util.t.a("dequeue ");
            final k a = this.m.a();
            com.daysofwonder.util.t.a("dequeue: " + a);
            return a;
        }
        catch (com.daysofwonder.util.p p) {}
        catch (InterruptedException ex) {}
        return null;
    }
    
    public k at() {
        Object a = null;
        try {
            System.out.println("Waiting request");
            if (this.m == null) {
                return null;
            }
            a = this.m.a();
        }
        catch (com.daysofwonder.util.p p) {}
        if (a == null) {
            return null;
        }
        System.out.println("Dequeued " + a);
        return (k)a;
    }
    
    public synchronized void au() {
        final boolean aa = this.aA();
        if (!this.f) {
            this.f = true;
            if (aG.K > 1) {
                a("aborting game");
            }
            this.y = false;
            this.x = false;
            if (this.s != null && this.l != null) {
                this.l.a(new com.daysofwonder.req.y(this.s.v()));
            }
            if (this.aA()) {
                this.m.b();
            }
            try {
                this.m.a(null);
            }
            catch (Exception ex) {
                com.daysofwonder.util.t.a(ex);
            }
            this.aw();
        }
        if (!aa) {
            this.c(true);
        }
    }
    
    public synchronized void av() {
        this.notify();
    }
    
    public synchronized void aw() {
        this.E = true;
        this.notify();
    }
    
    public synchronized void ax() {
        com.daysofwonder.util.t.a("Wait user action state=" + this.a);
        try {
            if (this.aA()) {
                this.wait(5000L);
            }
            else {
                this.wait(180000L);
            }
        }
        catch (InterruptedException ex) {
            com.daysofwonder.util.t.a("waitUserAction InterruptedException...");
            throw new az();
        }
        if (this.E) {
            com.daysofwonder.util.t.a("waitUserAction fAbortUserAction=true");
            this.E = false;
            throw new az();
        }
        this.w.a();
    }
    
    public synchronized void e() {
        System.out.println("reset...");
        if (this.h) {
            this.s = this.O;
            this.O = null;
        }
        if (this.s != null) {
            this.s.b();
        }
        this.a = 0;
        this.b = null;
        this.c = 0;
        this.e = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.q.clear();
        this.q = new Hashtable();
        this.r.removeAllElements();
        this.r = new Vector();
        this.t = 0;
        this.u = null;
        this.w = new com.daysofwonder.applet.n();
        this.x = false;
        this.y = true;
        this.z = new Vector();
        this.A = new Vector();
        this.B = new Vector();
        this.g = false;
        this.h = false;
        this.i = false;
        this.E = false;
        this.d = false;
        this.o = false;
        this.p = false;
    }
    
    public String ay() {
        return this.v;
    }
    
    public Vector az() {
        return this.r;
    }
    
    public boolean aA() {
        return this.h;
    }
    
    public synchronized boolean aB() {
        return this.y;
    }
    
    public boolean aC() {
        return this.C && System.currentTimeMillis() - this.D < 300000L && this.C;
    }
    
    public void c(final boolean c) {
        this.C = c;
        if (this.C) {
            this.D = System.currentTimeMillis();
        }
    }
    
    protected static String c(String substring) {
        if (substring.lastIndexOf(126) > 1) {
            substring = substring.substring(0, substring.lastIndexOf(126) - 1);
        }
        return substring;
    }
    
    public int aD() {
        return this.s.w();
    }
    
    public int aE() {
        return this.r.size();
    }
    
    public boolean aF() {
        return this.d;
    }
    
    public boolean a(final com.daysofwonder.a.d d) {
        if (d != null) {
            this.M.a(d.a);
            this.l.a(new B(4, d.a));
            this.ak();
            return true;
        }
        return false;
    }
    
    public void a(final i i) {
        this.l.a(new g(i));
    }
    
    public boolean a(final int n, final String s) {
        if (!this.M.c(n)) {
            this.M.a(-1, n, 0L, null, s);
            this.l.a(new B(3, n));
            this.ak();
            return true;
        }
        return false;
    }
    
    public boolean b(final com.daysofwonder.a.d d) {
        if (d != null) {
            this.N.a(d.a);
            this.l.a(new q(4, d.a));
            this.al();
            return true;
        }
        return false;
    }
    
    public boolean o(final int n) {
        final com.daysofwonder.a.d b = this.M.b(n);
        if (b != null) {
            this.M.a(b.a);
            this.l.a(new B(4, b.a));
            this.ak();
            return true;
        }
        return false;
    }
    
    public boolean p(final int n) {
        final com.daysofwonder.a.d b = this.N.b(n);
        if (b != null) {
            this.N.a(b.a);
            this.l.a(new q(4, b.a));
            this.al();
            return true;
        }
        return false;
    }
    
    public boolean q(final int n) {
        return this.N.c(n);
    }
    
    public boolean r(final int n) {
        return this.M.c(n);
    }
    
    public boolean b(final int n, final String s) {
        if (!this.N.c(n)) {
            this.N.a(-1, n, 0L, null, s);
            this.l.a(new q(3, n));
            this.al();
            return true;
        }
        return false;
    }
    
    public int aG() {
        return this.t;
    }
    
    public void aH() {
        this.H = true;
        try {
            if (this.m != null) {
                this.m.a(null);
            }
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
        }
        if (this.I != null) {
            this.I.b();
        }
    }
    
    public boolean aI() {
        return this.H;
    }
    
    public int aJ() {
        return this.T;
    }
    
    public a aK() {
        return this.s.g(this.aJ());
    }
    
    public boolean aL() {
        return this.s.H();
    }
    
    public boolean aM() {
        return this.n;
    }
    
    static {
        K = com.daysofwonder.util.j.a("BaseGame:");
        L = com.daysofwonder.util.j.a();
        J = new com.daysofwonder.a.f();
    }
}
