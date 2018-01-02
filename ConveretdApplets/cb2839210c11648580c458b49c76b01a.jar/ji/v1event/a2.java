// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1event;

import ji.util.d;
import java.util.Hashtable;
import java.awt.event.KeyEvent;
import ji.io.h;
import ji.util.i;
import ji.awt.bb;
import ji.util.ba;
import java.util.Vector;
import ji.awt.c;

public class a2
{
    c a;
    Vector b;
    ba c;
    ba d;
    static ba e;
    String f;
    bb g;
    sv h;
    boolean i;
    boolean j;
    int k;
    c l;
    Vector m;
    static final Object n;
    String o;
    int p;
    boolean q;
    private static c r;
    
    public a2(final String s, final String s2) {
        this(s, false, 100, s2);
    }
    
    public a2(final String f, final boolean j, final int k, final String o) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.k = 25;
        this.l = null;
        this.m = null;
        this.o = "";
        this.p = 0;
        this.q = false;
        this.f = f;
        this.o = o;
        this.j = j;
        this.k = k;
        if (ji.util.i.c(255)) {
            synchronized (a2.n) {
                a2.r.c(this);
                // monitorexit(a2.n)
                return;
            }
        }
        synchronized (a2.r) {
            a2.r.c(this);
        }
        // monitorexit(a2.r)
        this.b = new Vector();
        if (this.g == null) {
            synchronized (this) {
                this.i = false;
            }
        }
    }
    
    public String a() {
        return this.f;
    }
    
    public String b() {
        return this.o;
    }
    
    public static void a(final String s) {
        for (int i = 0; i < a2.r.b(); ++i) {
            final a2 a2 = (a2)ji.v1event.a2.r.b(i);
            try {
                if (a2.b().equals(s)) {
                    a2.h();
                    ji.v1event.a2.r.d(i);
                    --i;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private void h() {
        if (ji.util.i.c(255)) {
            synchronized (a2.n) {
                this.i = true;
                if (this.c != null) {
                    this.c.a();
                }
                // monitorexit(a2.n)
                return;
            }
        }
        synchronized (this) {
            this.i = true;
        }
        if (this.b != null) {
            synchronized (this.b) {
                this.b.notifyAll();
            }
            // monitorexit(this.b)
        }
    }
    
    public final void a(final ak ak) {
        if (ji.util.i.c(255)) {
            if (this.l != null && this.l.a(ak)) {
                this.l.b(ak);
            }
        }
        else if (this.m != null && this.m.contains(ak)) {
            this.m.removeElement(ak);
        }
    }
    
    public final void b(final ak ak) {
        if (ji.util.i.c(255)) {
            if (this.l == null) {
                this.l = new c("jiProcessQueue12", 2);
            }
            if (!this.l.a(ak)) {
                this.l.c(ak);
            }
        }
        else {
            if (this.m == null) {
                this.m = new Vector();
            }
            if (!this.m.contains(ak)) {
                this.m.addElement(ak);
            }
        }
    }
    
    public final void a(final a9 a9) {
        if (ji.util.i.c(255)) {
            synchronized (this) {
                if (ji.util.i.c(36) || this.q) {
                    ji.io.h.d("Dispatcher", "Dispatcher: addQueue synchronizing");
                }
                final Object n = a2.n;
                // monitorenter(n)
                try {
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: addQueue synchronized");
                    }
                    this.b(a9);
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: addQueue end synchronized");
                    }
                }
                // monitorexit(n)
                finally {}
                return;
            }
        }
        if (ji.util.i.c(36) || this.q) {
            ji.io.h.d("Dispatcher", "Dispatcher: addQueue synchronizing");
        }
        if (ji.util.i.c(36) || this.q) {
            ji.io.h.d("Dispatcher", "Dispatcher: addQueue synchronized");
        }
        this.b(a9);
        if (ji.util.i.c(36) || this.q) {
            ji.io.h.d("Dispatcher", "Dispatcher: addQueue end synchronized");
        }
    }
    
    private final synchronized boolean i() {
        return this.i;
    }
    
    private final void b(final a9 a9) {
        if (ji.util.i.c(255)) {
            if (!this.i) {
                try {
                    if (this.c == null) {
                        this.c = new ba("jiDispatcherLock");
                    }
                    if (this.d == null) {
                        this.d = new ba("jiDispatcherEmptyLock");
                    }
                    if (this.a == null) {
                        this.a = new c("jiProcessQueue1-".concat(String.valueOf(String.valueOf(this.f))));
                    }
                    if (a9.d() instanceof KeyEvent) {
                        ++this.p;
                    }
                    if (this.a.b() == 0) {
                        this.d.a(31);
                    }
                    this.a.c(a9);
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: added ".concat(String.valueOf(String.valueOf(a9))));
                    }
                    if (this.g == null) {
                        this.c.a(101);
                        this.i = false;
                        this.h = new sv();
                        (this.g = new bb(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.o))).append(" - ").append(this.f))), this.h)).start();
                    }
                    this.c.a();
                }
                catch (Exception ex) {}
            }
        }
        else if (!this.i()) {
            synchronized (this) {
                if (this.h == null) {
                    this.h = new sv();
                    (this.g = new bb(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.o))).append(" - ").append(this.f))), this.h)).start();
                }
            }
            try {
                if (a9.d() instanceof KeyEvent) {
                    ++this.p;
                }
                synchronized (this.b) {
                    this.b.addElement(a9);
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: added ".concat(String.valueOf(String.valueOf(a9))));
                    }
                    this.b.notifyAll();
                }
                // monitorexit(this.b)
            }
            catch (Exception ex2) {}
        }
    }
    
    private final void c(final a9 a9) {
        if (a9 != null) {
            try {
                a9.a(this.j);
                if (!a9.b() && this.m != null) {
                    for (int i = 0; i < this.m.size(); ++i) {
                        try {
                            if (ji.util.i.c(36) || this.q) {
                                ji.io.h.d("Dispatcher", "Dispatcher: dispatching to ".concat(String.valueOf(String.valueOf(this.m.elementAt(i)))));
                            }
                            ((ak)this.m.elementAt(i)).a(a9);
                        }
                        catch (Exception ex) {}
                    }
                }
                if (a9.e()) {
                    synchronized (a9) {
                        a9.notifyAll();
                    }
                }
            }
            catch (Throwable t) {
                a9.a(t);
            }
            finally {
                a9.f();
            }
        }
    }
    
    public final c c() {
        if (ji.util.i.c(36) || this.q) {
            ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents");
        }
        final c c = new c("jiDispatcher.AllKeyEvents");
        if (this.p > 0) {
            Label_0356: {
                if (ji.util.i.c(255)) {
                    synchronized (a2.n) {
                        if (ji.util.i.c(36) || this.q) {
                            ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents synchronized ".concat(String.valueOf(String.valueOf(this.f))));
                        }
                        final int b = this.a.b();
                        if (b > 1) {
                            for (int i = 0; i < b; ++i) {
                                final a9 a9 = (a9)this.a.b(i);
                                final Object d = a9.d();
                                if (d instanceof KeyEvent) {
                                    c.c(d);
                                    a9.a();
                                }
                            }
                        }
                        if (ji.util.i.c(36) || this.q) {
                            ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents end synchronized ".concat(String.valueOf(String.valueOf(this.f))));
                        }
                        // monitorexit(a2.n)
                        break Label_0356;
                    }
                }
                synchronized (this.b) {
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents synchronized ".concat(String.valueOf(String.valueOf(this.f))));
                    }
                    final int size = this.b.size();
                    if (size > 1) {
                        for (int j = 0; j < size; ++j) {
                            final a9 a10 = this.b.elementAt(j);
                            final Object d2 = a10.d();
                            if (d2 instanceof KeyEvent) {
                                c.c(d2);
                                a10.a();
                            }
                        }
                    }
                    if (ji.util.i.c(36) || this.q) {
                        ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents end synchronized ".concat(String.valueOf(String.valueOf(this.f))));
                    }
                }
                // monitorexit(this.b)
            }
            if (ji.util.i.c(36) || this.q) {
                ji.io.h.d("Dispatcher", "Dispatcher: getAllKeyEvents done");
            }
        }
        return c;
    }
    
    public final int d() {
        if (ji.util.i.c(255)) {
            try {
                if (this.a != null) {
                    return this.a.b();
                }
                return 0;
            }
            catch (Exception ex) {
                return 0;
            }
        }
        try {
            synchronized (this.b) {
                if (this.b != null) {
                    // monitorexit(this.b)
                    return this.b.size();
                }
                final boolean b = false;
                // monitorexit(this.b)
                return b ? 1 : 0;
            }
        }
        catch (Exception ex2) {
            return 0;
        }
    }
    
    public final int e() {
        return this.p;
    }
    
    private final void j() {
        if (ji.util.i.c(255)) {
            if (this.a != null) {
                final int b = this.a.b();
                if (b > 1) {
                    final c c = new c("jiDispatchNormalisedEvents");
                    try {
                        for (int i = b - 1; i >= 0; --i) {
                            if (this.a.b() > i) {
                                final a9 a9 = (a9)this.a.b(i);
                                if (a9.c() && !a9.b()) {
                                    final Object d = a9.d();
                                    String s = null;
                                    if (d instanceof d7) {
                                        s = "jiDisplayEvent".concat(String.valueOf(String.valueOf(((d7)d).d())));
                                    }
                                    else if (d instanceof ob) {
                                        s = "jiClipEvent".concat(String.valueOf(String.valueOf(((ob)d).a())));
                                    }
                                    else if (d instanceof a3) {
                                        s = "jiJavaScriptEvent".concat(String.valueOf(String.valueOf(((a3)d).j())));
                                    }
                                    else if (d instanceof a6) {
                                        s = "jiStatusEvent".concat(String.valueOf(String.valueOf(((a6)d).d())));
                                    }
                                    if (s != null) {
                                        if (c.d(s) != null) {
                                            a9.a();
                                        }
                                        else {
                                            c.a(s, new Integer(i));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception ex) {}
                    c.c();
                }
            }
        }
        else if (this.b != null) {
            final int size = this.b.size();
            if (size > 1) {
                final Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();
                try {
                    for (int j = size - 1; j >= 0; --j) {
                        if (this.b.size() > j) {
                            final a9 a10 = this.b.elementAt(j);
                            if (a10.c() && !a10.b()) {
                                final Object d2 = a10.d();
                                String s2 = null;
                                if (d2 instanceof d7) {
                                    s2 = "jiDisplayEvent".concat(String.valueOf(String.valueOf(((d7)d2).d())));
                                }
                                else if (d2 instanceof ob) {
                                    s2 = "jiClipEvent".concat(String.valueOf(String.valueOf(((ob)d2).a())));
                                }
                                else if (d2 instanceof a3) {
                                    s2 = "jiJavaScriptEvent".concat(String.valueOf(String.valueOf(((a3)d2).j())));
                                }
                                else if (d2 instanceof a6) {
                                    s2 = "jiStatusEvent".concat(String.valueOf(String.valueOf(((a6)d2).d())));
                                }
                                if (s2 != null) {
                                    if (hashtable.get(s2) != null) {
                                        a10.a();
                                    }
                                    else {
                                        hashtable.put(s2, new Integer(j));
                                    }
                                }
                            }
                        }
                    }
                }
                catch (Exception ex2) {}
                hashtable.clear();
            }
        }
    }
    
    public void f() {
        try {
            if (ji.util.i.c(255)) {
                if (this.d != null) {
                    this.d.a(32);
                    this.d.a();
                }
            }
            else if (ji.util.d.cy()) {
                ji.io.h.d(this.o, "Waiting for empty queue");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void g() {
        if (ji.util.i.c(255)) {
            try {
                this.i = true;
                if (this.a != null) {
                    this.a.c();
                    this.a = null;
                }
                if (this.d != null) {
                    this.d.a(false);
                    this.d.a();
                    this.d = null;
                }
                if (this.c != null) {
                    this.c.a(false);
                    this.c.a();
                    this.c = null;
                }
                if (a2.e != null) {
                    a2.e.a(false);
                    a2.e.a();
                    a2.e = null;
                }
                if (this.l != null) {
                    this.l.c();
                    this.l = null;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            try {
                if (a2.r != null) {
                    for (int i = 0; i < a2.r.b(); ++i) {
                        final a2 a2 = (a2)ji.v1event.a2.r.b(i);
                        try {
                            if (a2 == this) {
                                ji.v1event.a2.r.d(i);
                                --i;
                            }
                        }
                        catch (Exception ex5) {}
                    }
                }
                return;
            }
            catch (Exception ex2) {
                ji.util.d.a(ex2);
                return;
            }
        }
        try {
            synchronized (this) {
                this.i = true;
            }
            if (this.b != null) {
                synchronized (this.b) {
                    this.b.notifyAll();
                    this.b.removeAllElements();
                }
                // monitorexit(this.b = null)
            }
            if (this.m != null) {
                this.m.removeAllElements();
                this.m = null;
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        try {
            if (a2.r != null) {
                synchronized (a2.r) {
                    for (int j = 0; j < a2.r.b(); ++j) {
                        final a2 a3 = (a2)a2.r.b(j);
                        try {
                            if (a3 == this) {
                                a2.r.d(j);
                                --j;
                            }
                        }
                        catch (Exception ex6) {}
                    }
                }
                // monitorexit(a2.r)
            }
        }
        catch (Exception ex4) {
            ji.util.d.a(ex4);
        }
    }
    
    static {
        a2.e = null;
        n = new Object();
        a2.r = new c("active dispatchers list");
    }
    
    class sv implements Runnable
    {
        public void run() {
            while (true) {
                if (ji.util.i.c(255)) {
                    try {
                        a9 a9 = null;
                        while (!a2.this.i) {
                            if (a2.this.c == null) {
                                break;
                            }
                            if (a2.this.a == null) {
                                break;
                            }
                            try {
                                a2.this.c.a(100);
                                if (ji.util.i.c(36) || a2.this.q) {
                                    ji.io.h.d("Dispatcher", "Dispatcher: run1 synchronizing ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                }
                                int n;
                                synchronized (a2.n) {
                                    if (ji.util.i.c(36) || a2.this.q) {
                                        ji.io.h.d("Dispatcher", "Dispatcher: run1 synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                    }
                                    n = a2.this.a.b();
                                    if (ji.util.i.c(36) || a2.this.q) {
                                        ji.io.h.d("Dispatcher", "Dispatcher: run1 end synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                    }
                                }
                                // monitorexit(a2.n)
                                if (n <= 0) {
                                    continue;
                                }
                                if (a2.this.j && a2.this.k > 0) {
                                    ji.util.d.b(a2.this.k, 4013, "Dispatcher");
                                }
                                if (!a2.this.i) {
                                    if (ji.util.i.c(36) || a2.this.q) {
                                        ji.io.h.d("Dispatcher", "Dispatcher: run2 synchronizing ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                    }
                                    synchronized (a2.n) {
                                        if (ji.util.i.c(36) || a2.this.q) {
                                            ji.io.h.d("Dispatcher", "Dispatcher: run2 synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                        }
                                        n = a2.this.a.b();
                                        if (ji.util.i.c(36) || a2.this.q) {
                                            ji.io.h.d("Dispatcher", "Dispatcher: run2 end synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                        }
                                    }
                                    // monitorexit(a2.n)
                                    while (!a2.this.i && n > 0) {
                                        if (ji.util.i.c(36) || a2.this.q) {
                                            ji.io.h.d("Dispatcher", "Dispatcher: run3 synchronizing ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                        }
                                        synchronized (a2.n) {
                                            if (ji.util.i.c(36) || a2.this.q) {
                                                ji.io.h.d("Dispatcher", "Dispatcher: run3 synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                            }
                                            a2.this.j();
                                            a9 = (a9)a2.this.a.b(0);
                                            if (a9.d() instanceof KeyEvent) {
                                                final a2 a10 = a2.this;
                                                --a10.p;
                                            }
                                            a2.this.a.d(0);
                                            if (a2.this.a.b() == 0 && a2.this.d != null) {
                                                a2.this.d.a();
                                            }
                                            if (ji.util.i.c(36) || a2.this.q) {
                                                ji.io.h.d("Dispatcher", "Dispatcher: got event ".concat(String.valueOf(String.valueOf(a9))));
                                            }
                                            if (ji.util.i.c(36) || a2.this.q) {
                                                ji.io.h.d("Dispatcher", "Dispatcher: run3 end synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                            }
                                        }
                                        // monitorexit(a2.n)
                                        if (a9 != null) {
                                            try {
                                                a9.a(a2.this.j);
                                                if (!a9.b() && a2.this.l != null) {
                                                    for (int i = 0; i < a2.this.l.b(); ++i) {
                                                        try {
                                                            if (ji.util.i.c(36) || a2.this.q) {
                                                                ji.io.h.d("Dispatcher", "Dispatcher: dispatching to ".concat(String.valueOf(String.valueOf(a2.this.l.b(i)))));
                                                            }
                                                            ((ak)a2.this.l.b(i)).a(a9);
                                                        }
                                                        catch (Exception ex3) {}
                                                    }
                                                }
                                                if (a9.e()) {
                                                    synchronized (a9) {
                                                        a9.notifyAll();
                                                    }
                                                    // monitorexit(a9)
                                                }
                                            }
                                            catch (Throwable t) {
                                                a9.a(t);
                                            }
                                            finally {
                                                a9.f();
                                            }
                                        }
                                        if (ji.util.i.c(36) || a2.this.q) {
                                            ji.io.h.d("Dispatcher", "Dispatcher: run4 synchronizing ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                        }
                                        synchronized (a2.n) {
                                            if (ji.util.i.c(36) || a2.this.q) {
                                                ji.io.h.d("Dispatcher", "Dispatcher: run4 synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                            }
                                            n = a2.this.a.b();
                                            if (ji.util.i.c(36) || a2.this.q) {
                                                ji.io.h.d("Dispatcher", "Dispatcher: run4 end synchronized ".concat(String.valueOf(String.valueOf(a2.this.f))));
                                            }
                                        }
                                        // monitorexit(a2.n)
                                    }
                                    continue;
                                }
                            }
                            catch (Exception ex4) {
                                continue;
                            }
                            break;
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    catch (ThreadDeath threadDeath) {
                        threadDeath.printStackTrace();
                        throw threadDeath;
                    }
                    finally {
                        if (ji.util.i.c(36) || a2.this.q) {
                            ji.io.h.d("Dispatcher", "Dispatcher: STOPPED ".concat(String.valueOf(String.valueOf(a2.this.f))));
                        }
                        a2.this.g = null;
                        a2.this.h = null;
                    }
                    try {
                        while (!a2.this.i()) {
                            try {
                                final a9 a11 = this.a();
                                if (a11 == null) {
                                    continue;
                                }
                                a2.this.c(a11);
                            }
                            catch (Exception ex5) {}
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    catch (ThreadDeath threadDeath2) {
                        threadDeath2.printStackTrace();
                        throw threadDeath2;
                    }
                    finally {
                        if (ji.util.i.c(36) || a2.this.q) {
                            ji.io.h.d("Dispatcher", "Dispatcher: STOPPED ".concat(String.valueOf(String.valueOf(a2.this.f))));
                        }
                        a2.this.g = null;
                        a2.this.h = null;
                    }
                    return;
                }
                continue;
            }
        }
        
        private final a9 a() {
            a9 a9 = null;
            synchronized (a2.this.b) {
                if (a2.this.b.size() == 0) {
                    try {
                        a2.this.b.wait(5000L);
                    }
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                if (a2.this.i()) {
                    // monitorexit(this.a.b)
                    return null;
                }
                if (a2.this.b.size() > 0) {
                    a2.this.j();
                    a9 = a2.this.b.elementAt(0);
                    if (a9.d() instanceof KeyEvent) {
                        final a2 a11 = a2.this;
                        --a11.p;
                    }
                    a2.this.b.removeElementAt(0);
                }
            }
            // monitorexit(this.a.b)
            return a9;
        }
    }
}
