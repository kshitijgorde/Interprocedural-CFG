// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.dll;

import java.awt.Rectangle;
import java.util.Enumeration;
import ji.util.i;
import ji.v1event.n4;
import ji.image.dx;
import ji.v1event.am;
import ji.util.d;
import java.io.File;
import ji.io.h;
import java.util.Vector;
import java.util.Hashtable;

public abstract class n3
{
    protected String a;
    boolean b;
    int c;
    boolean d;
    protected Hashtable e;
    protected long f;
    private boolean g;
    private zk h;
    private zk i;
    boolean j;
    private zl k;
    zm l;
    private static Object m;
    protected Vector n;
    protected Vector o;
    protected Vector p;
    boolean q;
    boolean r;
    static int s;
    
    public n3(final String a) {
        this.b = false;
        this.c = -1;
        this.d = false;
        this.g = false;
        this.h = new zk();
        this.i = new zk();
        this.j = false;
        this.n = new Vector();
        this.o = new Vector();
        this.p = new Vector();
        this.q = false;
        this.r = false;
        this.a = a;
        this.e = new Hashtable();
    }
    
    public boolean a() {
        return this.d;
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public boolean a(final String s, final boolean r, final boolean b) {
        final Vector<String> vector = new Vector<String>();
        vector.addElement(s);
        this.r = r;
        return this.a(vector, b);
    }
    
    protected final boolean a(final Vector vector, final boolean b) {
        this.g = false;
        if (this.k == null) {
            this.k = new zl();
        }
        synchronized (this.k) {
            this.k.start();
            try {
                this.k.wait(5000L);
                if (!this.k.a()) {
                    this.k.interrupt();
                }
            }
            catch (InterruptedException ex) {
                if (this.m()) {
                    ji.io.h.d(this.a, "messageLoopThread interrupted");
                }
            }
            this.f = this.k.b();
        }
        // monitorexit(this.k)
        if (this.k.a()) {
            this.q = this.a(this.f, vector, b);
        }
        return this.q;
    }
    
    public boolean a(final long n, final Vector vector, final boolean b) {
        return this._openDoc(n, new File(vector.elementAt(0)).getAbsolutePath(), b);
    }
    
    public void b(final boolean b) {
        if (this.q && this.r) {
            synchronized (this.i) {
                this.i.a = true;
            }
            // monitorexit(this.i)
            this.d(b);
        }
    }
    
    public int a(final int n) {
        try {
            if (this.l != null) {
                synchronized (this.l) {
                    while (!this.l.a() && !this.l.a()) {
                        if (this.h() >= n) {
                            break;
                        }
                        try {
                            this.l.wait(1000L);
                        }
                        catch (InterruptedException ex) {
                            if (this.m()) {
                                ji.io.h.d(this.a, "waitForPage interrupted");
                            }
                        }
                        if (this.l.a() || this.h() >= n) {
                            break;
                        }
                    }
                }
                // monitorexit(this.l)
            }
            if (n > this.h()) {
                return this.h();
            }
            return n;
        }
        catch (Throwable t) {
            ji.util.d.a(t);
            return -1;
        }
    }
    
    public boolean b() {
        return this.l != null && !this.l.a();
    }
    
    private void d(final boolean b) {
        if (ji.util.d.b()) {
            new zn(b).run();
        }
        else {
            synchronized (this) {
                if (this.l == null) {
                    (this.l = new zm(b)).start();
                }
            }
        }
    }
    
    public int c() {
        return this.p.size();
    }
    
    public boolean a(final n0 n0) {
        return this.p.contains(n0);
    }
    
    public void b(final n0 n0) {
        if (!this.p.contains(n0)) {
            if (this.m()) {
                ji.io.h.d(this.a, "jiDLLDoc adding filter: ".concat(String.valueOf(String.valueOf(n0))));
            }
            this.p.addElement(n0);
        }
    }
    
    public void c(final n0 n0) {
        if (this.m()) {
            ji.io.h.d(this.a, "jiDLLDoc removing filter: ".concat(String.valueOf(String.valueOf(n0))));
        }
        this.p.removeElement(n0);
    }
    
    public void a(final am am) {
        synchronized (this.n) {
            if (!this.n.contains(am)) {
                this.n.addElement(am);
            }
        }
        // monitorexit(this.n)
    }
    
    public boolean d() {
        synchronized (this.i) {
            // monitorexit(this.i)
            return this.i.a;
        }
    }
    
    public void b(final am am) {
        synchronized (this.n) {
            this.n.removeElement(am);
        }
        // monitorexit(this.n)
    }
    
    public void a(final dx dx) {
        synchronized (this.o) {
            boolean a = false;
            final zk h = this.h;
            // monitorenter(h)
            try {
                a = this.h.a;
            }
            // monitorexit(h)
            finally {}
            Label_0169: {
                if (a) {
                    if (this.m()) {
                        ji.io.h.d(this.a, String.valueOf(String.valueOf(new StringBuffer("Not registering ").append(dx.hashCode()).append(" because page counting has already finished"))));
                    }
                    if (dx == null) {
                        break Label_0169;
                    }
                    // monitorenter(dx)
                    try {
                        dx.u = this.h();
                        dx.c2 = true;
                        dx.notifyAll();
                        // monitorexit(dx)
                        break Label_0169;
                    }
                    finally {}
                }
                if (this.m()) {
                    ji.io.h.d(this.a, "Registering ".concat(String.valueOf(String.valueOf(dx.hashCode()))));
                }
                if (!this.o.contains(dx)) {
                    this.o.addElement(dx);
                }
            }
        }
        // monitorexit(this.o)
    }
    
    public void b(final dx dx) {
        synchronized (this.o) {
            if (this.m()) {
                ji.io.h.d(this.a, "Unregistering ".concat(String.valueOf(String.valueOf(dx.hashCode()))));
            }
            this.o.removeElement(dx);
        }
        // monitorexit(this.o)
    }
    
    public void e() {
        synchronized (this.o) {
            for (int i = 0; i < this.o.size(); ++i) {
                final dx dx = this.o.elementAt(i);
                if (dx != null) {
                    final dx dx2 = dx;
                    // monitorenter(dx2)
                    try {
                        dx.u = this.h();
                    }
                    // monitorexit(dx2)
                    finally {}
                }
            }
        }
        // monitorexit(this.o)
        synchronized (this.n) {
            for (int j = 0; j < this.n.size(); ++j) {
                final am am = this.n.elementAt(j);
                if (am != null) {
                    am.a(new n4(this, 10));
                }
            }
        }
        // monitorexit(this.n)
    }
    
    public void f() {
        synchronized (this.o) {
            for (int i = 0; i < this.o.size(); ++i) {
                final dx dx = this.o.elementAt(i);
                if (dx != null) {
                    final dx dx2 = dx;
                    // monitorenter(dx2)
                    try {
                        dx.u = this.h();
                        dx.c2 = true;
                        dx.notifyAll();
                    }
                    // monitorexit(dx2)
                    finally {}
                }
            }
        }
        // monitorexit(this.o)
        synchronized (this.n) {
            for (int j = 0; j < this.n.size(); ++j) {
                ((am)this.n.elementAt(j)).a(new n4(this, 11));
            }
        }
        // monitorexit(this.n)
    }
    
    public boolean c(final dx dx) {
        this.d(dx);
        if (this.l != null) {
            this.l.b();
        }
        this.b = false;
        this.o();
        synchronized (this.n) {
            if (this.n.size() > 0 && this.m()) {
                ji.io.h.d(this.a, String.valueOf(String.valueOf(new StringBuffer("jiDLLDoc when closing doc, there were ").append(this.n.size()).append(" doc listeners left"))));
            }
            this.n.removeAllElements();
        }
        // monitorexit(this.n)
        synchronized (this.o) {
            if (this.o.size() > 0 && this.m()) {
                ji.io.h.d(this.a, String.valueOf(String.valueOf(new StringBuffer("jiDLLDoc when closing doc, there were ").append(this.o.size()).append(" header listeners left"))));
            }
            this.o.removeAllElements();
        }
        // monitorexit(this.o)
        boolean closeDoc = false;
        if (this.k.a()) {
            closeDoc = this._closeDoc(this.f);
        }
        if (this.m()) {
            ji.io.h.d(this.a, "jiDLLDoc closing success: ".concat(String.valueOf(String.valueOf(closeDoc))));
        }
        this.q = false;
        this.r = false;
        if (this.k != null) {
            this.k.c();
        }
        return closeDoc;
    }
    
    public void d(final dx dx) {
        final String concat = "DLL: aborting ".concat(String.valueOf(String.valueOf(this.f)));
        if (ji.util.i.c(5) || this.m()) {
            ji.io.h.d(this.a, concat);
        }
        this.g = true;
    }
    
    public void c(final boolean g) {
        this.g = g;
    }
    
    private void o() {
        final Enumeration<Object> keys = this.e.keys();
        while (keys.hasMoreElements()) {
            final zo zo = this.e.get(keys.nextElement());
            if (zo != null) {
                zo.c();
            }
        }
        this.e.clear();
    }
    
    public void a(final zo zo) {
    }
    
    public boolean g() {
        return !this._lastPageFound(this.f);
    }
    
    public int h() {
        int n = this._highestPageNum(this.f) + 1;
        if (n <= 0) {
            n = 1;
        }
        return n;
    }
    
    public zo b(final int n) {
        zo zo = this.e.get("".concat(String.valueOf(String.valueOf(n))));
        if (zo == null) {
            zo = new zo(n, this);
            this.e.put("".concat(String.valueOf(String.valueOf(n))), zo);
        }
        return zo;
    }
    
    public String i() {
        return this._getDocTypeString(this.f);
    }
    
    public int j() {
        return this._getDocTypeId(this.f);
    }
    
    public int k() {
        return this._getDisplayEngineType(this.f);
    }
    
    protected abstract boolean l();
    
    protected abstract boolean m();
    
    protected abstract boolean _openDoc(final long p0, final String p1, final boolean p2);
    
    protected abstract void _messageLoop();
    
    protected abstract long _createWindow();
    
    protected abstract long _getThreadId();
    
    protected abstract void _endMessageLoop(final long p0);
    
    protected abstract boolean _closeDoc(final long p0);
    
    protected abstract Rectangle _getMediaBox(final long p0, final int p1, final double p2);
    
    protected abstract void _beginDrawPage(final long p0);
    
    protected abstract void _endDrawPage(final long p0);
    
    protected abstract int _drawPageToMemory(final long p0, final int p1, final byte[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10, final int p11, final double p12);
    
    protected abstract int _drawPageToCompressedMemory(final long p0, final int p1, final byte[] p2, final int p3, final int p4, final int p5, final int p6, final int p7, final double p8, final int p9);
    
    protected abstract int _getDrawTop(final long p0);
    
    protected abstract int _getDrawBottom(final long p0);
    
    protected abstract int _getCurrentPercent(final long p0);
    
    protected abstract boolean _lastPageFound(final long p0);
    
    protected abstract int _highestPageNum(final long p0);
    
    protected abstract boolean _processPage(final long p0, final int p1);
    
    protected abstract String _getDocTypeString(final long p0);
    
    protected abstract int _getDocTypeId(final long p0);
    
    protected abstract int _getDisplayEngineType(final long p0);
    
    static {
        n3.m = new Object();
        n3.s = 0;
    }
    
    private class zl extends Thread
    {
        long a;
        long b;
        private adr c;
        
        public zl() {
            super("OI msg loop ".concat(String.valueOf(String.valueOf(++n3.s))));
            this.c = new adr((aeg)null);
            this.setDaemon(true);
        }
        
        public boolean a() {
            synchronized (this.c) {
                // monitorexit(this.c)
                return this.c.a;
            }
        }
        
        public long b() {
            return this.a;
        }
        
        public void run() {
            boolean b = false;
            try {
                this.a = n3.this._createWindow();
                this.b = n3.this._getThreadId();
                synchronized (this.c) {
                    this.c.a = true;
                }
                // monitorexit(this.c)
            }
            catch (Exception ex) {
                b = true;
                if (n3.this.m()) {
                    ji.io.h.d(n3.this.a, "jiDLLDoc exception calling native functions: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                }
            }
            catch (Error error) {
                b = true;
                if (n3.this.m()) {
                    ji.io.h.d(n3.this.a, String.valueOf(String.valueOf(new StringBuffer("jiDLLDoc ").append(error.getClass()).append(" calling native functions: ").append(error.getMessage()))));
                }
            }
            synchronized (this) {
                this.notify();
            }
            if (!b) {
                n3.this._messageLoop();
                synchronized (this.c) {
                    this.c.a = false;
                }
                // monitorexit(this.c)
            }
            if (n3.this.m()) {
                ji.io.h.d(n3.this.a, "jiDLLDoc finished messageloop");
            }
        }
        
        public void c() {
            if (n3.this.m()) {
                ji.io.h.d(n3.this.a, "jiDLLDoc message loop finish() called ".concat(String.valueOf(String.valueOf(this.getName()))));
            }
            synchronized (this.c) {
                Label_0126: {
                    if (!this.c.a) {
                        // monitorenter(this)
                        try {
                            if (n3.this.m()) {
                                ji.io.h.d(n3.this.a, "jiDLLDoc interrupting message loop");
                            }
                            this.interrupt();
                            // monitorexit(this)
                            break Label_0126;
                        }
                        finally {}
                    }
                    if (n3.this.m()) {
                        ji.io.h.d(n3.this.a, "jiDLLDoc ending message loop");
                    }
                    n3.this._endMessageLoop(this.b);
                }
            }
            // monitorexit(this.c)
        }
        
        private class adr
        {
            public boolean a;
            
            private adr(final zl zl) {
                this.a = false;
            }
        }
    }
    
    public class zo
    {
        n3 a;
        protected int b;
        private ads c;
        
        protected zo(final int n, final n3 a) {
            if (n < 1) {
                throw new IllegalArgumentException("pageNo must be 1 or greater");
            }
            this.a = a;
            this.b = n - 1;
            if (!a.b() && n > a.h()) {
                boolean processPage = true;
                int n2 = n3.this._highestPageNum(n3.this.f) + 1;
                if (ji.util.i.c(5)) {
                    ji.io.h.d(n3.this.a, "Waiting to find indexed page ".concat(String.valueOf(String.valueOf(n))));
                }
                while (!n3.this.g && processPage && a.g() && n2 <= this.b) {
                    synchronized (a) {
                        try {
                            while (n3.this.j && !n3.this.g) {
                                a.wait();
                            }
                            n3.this.j = true;
                            if (ji.util.i.c(5)) {
                                ji.io.h.d(n3.this.a, String.valueOf(String.valueOf(new StringBuffer("Processing ").append(n2 + 1))));
                            }
                            processPage = n3.this._processPage(n3.this.f, n2++);
                            n3.this.j = false;
                        }
                        catch (InterruptedException ex) {
                            if (n3.this.m()) {
                                ji.io.h.d(n3.this.a, "DLLPage interrupted");
                            }
                            ex.printStackTrace();
                        }
                        a.notify();
                        continue;
                    }
                    break;
                }
                if (processPage && n > n3.this.h() && ji.util.i.c(5)) {
                    ji.io.h.d(n3.this.a, String.valueOf(String.valueOf(new StringBuffer("Invalid document page index specified ").append(n).append(" is greater then ").append(n3.this.h()))));
                }
            }
        }
        
        public void a() {
            synchronized (n3.this) {
                try {
                    while (n3.this.j && !n3.this.g) {
                        n3.this.wait();
                    }
                    n3.this.j = true;
                    n3.this._beginDrawPage(n3.this.f);
                }
                catch (InterruptedException ex) {
                    if (n3.this.m()) {
                        ji.io.h.d(n3.this.a, "beginDrawPage interrupted");
                    }
                    ex.printStackTrace();
                }
            }
            // monitorexit(this.d)
        }
        
        public void b() {
            synchronized (n3.this) {
                n3.this._endDrawPage(n3.this.f);
                n3.this.j = false;
                n3.this.notify();
            }
            // monitorexit(this.d)
        }
        
        public int a(final int n, final Rectangle rectangle, final Rectangle rectangle2, final int n2, final Rectangle rectangle3, final byte[] array, final int n3, final double n4) {
            if (n3.this.g) {
                return -1;
            }
            synchronized (n3.m) {
                if (array != null && !n3.this.j) {
                    // monitorexit(n3.n())
                    return -1;
                }
                if (rectangle != null) {
                    // monitorexit(n3.n())
                    return n3.this._drawPageToMemory(n3.this.f, this.b, array, n3, rectangle.x, rectangle.y, rectangle.width, rectangle.height, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, n4);
                }
                // monitorexit(n3.n())
                return n3.this._drawPageToMemory(n3.this.f, this.b, array, n3, 0, 0, 0, 0, rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height, n4);
            }
        }
        
        public int a(final int n, final Rectangle rectangle, final int n2, final byte[] array, final int n3, final double n4, final int n5) {
            synchronized (n3.m) {
                if (array != null && !n3.this.j) {
                    // monitorexit(n3.n())
                    return -1;
                }
                if (rectangle != null) {
                    // monitorexit(n3.n())
                    return n3.this._drawPageToCompressedMemory(n3.this.f, this.b, array, n3, rectangle.x, rectangle.y, rectangle.width, rectangle.height, n4, n5);
                }
                // monitorexit(n3.n())
                return -1;
            }
        }
        
        public void c() {
            if (this.a != null) {
                this.a.a(this);
                this.a = null;
            }
        }
        
        public Rectangle a(final double n) {
            if (n3.this.l() || n3.this.k() == 7) {
                this.c = null;
            }
            if (this.c == null || this.c.width == 0 || this.c.height == 0) {
                int n2 = 3;
                do {
                    this.a();
                    try {
                        this.c = new ads(n3.this._getMediaBox(n3.this.f, this.b, n), n);
                    }
                    catch (Exception ex) {
                        throw (RuntimeException)ex;
                    }
                    catch (Error error) {
                        throw error;
                    }
                    finally {
                        this.b();
                    }
                } while ((this.c == null || this.c.width == 0 || this.c.height == 0) && n2-- >= 0);
            }
            return this.c;
        }
        
        public Rectangle b(final double n) {
            final Rectangle a = this.a(n);
            if (a != null) {
                final Rectangle rectangle = new Rectangle(a);
                rectangle.width /= (int)(1440 / n);
                rectangle.height /= (int)(1440 / n);
                return rectangle;
            }
            return null;
        }
        
        public int d() {
            return n3.this._getDrawTop(n3.this.f);
        }
        
        public int e() {
            return n3.this._getDrawBottom(n3.this.f);
        }
        
        public int f() {
            return n3.this._getCurrentPercent(n3.this.f);
        }
        
        private class ads extends Rectangle
        {
            private double a;
            
            public ads(final zo zo, final Rectangle rectangle, final double a) {
                super(rectangle);
                this.a = 0.0;
                this.a = a;
            }
        }
    }
    
    private class zn implements Runnable
    {
        boolean a;
        boolean b;
        
        public zn(final boolean b) {
            this.a = false;
            this.b = true;
            this.b = b;
        }
        
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: invokevirtual   ji/filter/dll/n3$zn.a:()Z
            //     4: ifeq            8
            //     7: return         
            //     8: iconst_1       
            //     9: istore_1       
            //    10: invokestatic    java/lang/System.currentTimeMillis:()J
            //    13: lstore_2       
            //    14: iconst_0       
            //    15: istore          4
            //    17: iconst_0       
            //    18: istore          5
            //    20: iconst_0       
            //    21: istore          6
            //    23: aload_0        
            //    24: invokevirtual   ji/filter/dll/n3$zn.a:()Z
            //    27: ifne            465
            //    30: iload_1        
            //    31: ifeq            465
            //    34: aload_0        
            //    35: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    38: invokevirtual   ji/filter/dll/n3.g:()Z
            //    41: ifeq            465
            //    44: aload_0        
            //    45: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    48: astore          7
            //    50: aload           7
            //    52: monitorenter   
            //    53: aload_0        
            //    54: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    57: getfield        ji/filter/dll/n3.j:Z
            //    60: ifeq            80
            //    63: aload_0        
            //    64: invokevirtual   ji/filter/dll/n3$zn.a:()Z
            //    67: ifne            80
            //    70: aload_0        
            //    71: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    74: invokevirtual   java/lang/Object.wait:()V
            //    77: goto            53
            //    80: aload_0        
            //    81: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    84: iconst_1       
            //    85: putfield        ji/filter/dll/n3.j:Z
            //    88: aload_0        
            //    89: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    92: aload_0        
            //    93: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //    96: getfield        ji/filter/dll/n3.f:J
            //    99: invokevirtual   ji/filter/dll/n3._highestPageNum:(J)I
            //   102: istore          8
            //   104: invokestatic    ji/util/d.b:()Z
            //   107: ifne            312
            //   110: iload           8
            //   112: iconst_1       
            //   113: if_icmple       312
            //   116: sipush          241
            //   119: invokestatic    ji/util/i.c:(I)Z
            //   122: ifeq            312
            //   125: iload           5
            //   127: ifne            312
            //   130: iconst_1       
            //   131: istore          5
            //   133: aload_0        
            //   134: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   137: iconst_0       
            //   138: putfield        ji/filter/dll/n3.j:Z
            //   141: aload_0        
            //   142: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   145: ldc2_w          1500
            //   148: invokevirtual   java/lang/Object.wait:(J)V
            //   151: iconst_1       
            //   152: istore          6
            //   154: jsr             198
            //   157: goto            312
            //   160: astore          9
            //   162: aload_0        
            //   163: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   166: invokevirtual   ji/filter/dll/n3.m:()Z
            //   169: ifeq            184
            //   172: aload_0        
            //   173: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   176: getfield        ji/filter/dll/n3.a:Ljava/lang/String;
            //   179: ldc             "CountPages sleep interrupted"
            //   181: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   184: jsr             198
            //   187: goto            312
            //   190: astore          10
            //   192: jsr             198
            //   195: aload           10
            //   197: athrow         
            //   198: astore          11
            //   200: aload_0        
            //   201: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   204: astore          12
            //   206: aload           12
            //   208: monitorenter   
            //   209: aload_0        
            //   210: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   213: getfield        ji/filter/dll/n3.j:Z
            //   216: ifeq            239
            //   219: aload_0        
            //   220: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   223: invokestatic    ji/filter/dll/n3.a:(Lji/filter/dll/n3;)Z
            //   226: ifne            239
            //   229: aload_0        
            //   230: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   233: invokevirtual   java/lang/Object.wait:()V
            //   236: goto            209
            //   239: jsr             288
            //   242: goto            300
            //   245: astore          13
            //   247: aload_0        
            //   248: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   251: invokevirtual   ji/filter/dll/n3.m:()Z
            //   254: ifeq            269
            //   257: aload_0        
            //   258: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   261: getfield        ji/filter/dll/n3.a:Ljava/lang/String;
            //   264: ldc             "CountPagesRunnable drawing interrupted"
            //   266: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   269: aload           13
            //   271: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //   274: jsr             288
            //   277: goto            300
            //   280: astore          14
            //   282: jsr             288
            //   285: aload           14
            //   287: athrow         
            //   288: astore          15
            //   290: aload_0        
            //   291: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   294: iconst_1       
            //   295: putfield        ji/filter/dll/n3.j:Z
            //   298: ret             15
            //   300: aload           12
            //   302: monitorexit    
            //   303: goto            310
            //   306: aload           12
            //   308: monitorexit    
            //   309: athrow         
            //   310: ret             11
            //   312: aload_0        
            //   313: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   316: aload_0        
            //   317: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   320: getfield        ji/filter/dll/n3.f:J
            //   323: iload           8
            //   325: iconst_1       
            //   326: iadd           
            //   327: invokevirtual   ji/filter/dll/n3._processPage:(JI)Z
            //   330: istore_1       
            //   331: aload_0        
            //   332: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   335: iconst_0       
            //   336: putfield        ji/filter/dll/n3.j:Z
            //   339: goto            384
            //   342: astore          8
            //   344: aload_0        
            //   345: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   348: invokevirtual   ji/filter/dll/n3.m:()Z
            //   351: ifeq            366
            //   354: aload_0        
            //   355: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   358: getfield        ji/filter/dll/n3.a:Ljava/lang/String;
            //   361: ldc             "CountPages process interrupted"
            //   363: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   366: aload           8
            //   368: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //   371: aload_0        
            //   372: invokevirtual   ji/filter/dll/n3$zn.a:()Z
            //   375: ifeq            384
            //   378: aload           7
            //   380: monitorexit    
            //   381: goto            465
            //   384: aload_0        
            //   385: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   388: invokevirtual   java/lang/Object.notifyAll:()V
            //   391: aload           7
            //   393: monitorexit    
            //   394: goto            401
            //   397: aload           7
            //   399: monitorexit    
            //   400: athrow         
            //   401: invokestatic    ji/util/d.b:()Z
            //   404: ifne            23
            //   407: invokestatic    java/lang/System.currentTimeMillis:()J
            //   410: lload_2        
            //   411: lsub           
            //   412: sipush          2000
            //   415: i2l            
            //   416: lcmp           
            //   417: ifgt            425
            //   420: iload           6
            //   422: ifeq            23
            //   425: iconst_0       
            //   426: istore          6
            //   428: aload_0        
            //   429: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   432: aload_0        
            //   433: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   436: getfield        ji/filter/dll/n3.f:J
            //   439: invokevirtual   ji/filter/dll/n3._highestPageNum:(J)I
            //   442: istore          4
            //   444: aload_0        
            //   445: getfield        ji/filter/dll/n3$zn.b:Z
            //   448: ifeq            458
            //   451: aload_0        
            //   452: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   455: invokevirtual   ji/filter/dll/n3.e:()V
            //   458: invokestatic    java/lang/System.currentTimeMillis:()J
            //   461: lstore_2       
            //   462: goto            23
            //   465: aload_0        
            //   466: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   469: invokevirtual   ji/filter/dll/n3.m:()Z
            //   472: ifeq            531
            //   475: aload_0        
            //   476: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   479: getfield        ji/filter/dll/n3.a:Ljava/lang/String;
            //   482: new             Ljava/lang/StringBuffer;
            //   485: dup            
            //   486: ldc             "jiDLLDoc count pages loop finished. isFinished="
            //   488: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   491: aload_0        
            //   492: invokevirtual   ji/filter/dll/n3$zn.a:()Z
            //   495: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
            //   498: ldc             ", success="
            //   500: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   503: iload_1        
            //   504: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
            //   507: ldc             ", hasMorePages="
            //   509: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   512: aload_0        
            //   513: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   516: invokevirtual   ji/filter/dll/n3.g:()Z
            //   519: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
            //   522: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   525: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   528: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   531: aload_0        
            //   532: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   535: invokestatic    ji/filter/dll/n3.b:(Lji/filter/dll/n3;)Lji/filter/dll/n3$zk;
            //   538: astore          7
            //   540: aload           7
            //   542: monitorenter   
            //   543: aload_0        
            //   544: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   547: invokestatic    ji/filter/dll/n3.b:(Lji/filter/dll/n3;)Lji/filter/dll/n3$zk;
            //   550: iconst_1       
            //   551: putfield        ji/filter/dll/n3$zk.a:Z
            //   554: aload           7
            //   556: monitorexit    
            //   557: goto            564
            //   560: aload           7
            //   562: monitorexit    
            //   563: athrow         
            //   564: aload_0        
            //   565: invokevirtual   ji/filter/dll/n3$zn.b:()V
            //   568: invokestatic    ji/util/d.b:()Z
            //   571: ifne            588
            //   574: aload_0        
            //   575: getfield        ji/filter/dll/n3$zn.b:Z
            //   578: ifeq            588
            //   581: aload_0        
            //   582: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   585: invokevirtual   ji/filter/dll/n3.f:()V
            //   588: aload_0        
            //   589: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   592: invokevirtual   ji/filter/dll/n3.m:()Z
            //   595: ifeq            648
            //   598: aload_0        
            //   599: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   602: getfield        ji/filter/dll/n3.a:Ljava/lang/String;
            //   605: new             Ljava/lang/StringBuffer;
            //   608: dup            
            //   609: ldc             "jiDLLDoc last page = "
            //   611: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   614: aload_0        
            //   615: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   618: invokevirtual   ji/filter/dll/n3.h:()I
            //   621: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
            //   624: ldc             " has more pages = "
            //   626: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   629: aload_0        
            //   630: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   633: invokevirtual   ji/filter/dll/n3.g:()Z
            //   636: invokevirtual   java/lang/StringBuffer.append:(Z)Ljava/lang/StringBuffer;
            //   639: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   642: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   645: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   648: jsr             662
            //   651: goto            723
            //   654: astore          16
            //   656: jsr             662
            //   659: aload           16
            //   661: athrow         
            //   662: astore          17
            //   664: aload_0        
            //   665: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   668: invokestatic    ji/filter/dll/n3.b:(Lji/filter/dll/n3;)Lji/filter/dll/n3$zk;
            //   671: astore          18
            //   673: aload           18
            //   675: monitorenter   
            //   676: aload_0        
            //   677: getfield        ji/filter/dll/n3$zn.c:Lji/filter/dll/n3;
            //   680: invokestatic    ji/filter/dll/n3.b:(Lji/filter/dll/n3;)Lji/filter/dll/n3$zk;
            //   683: iconst_1       
            //   684: putfield        ji/filter/dll/n3$zk.a:Z
            //   687: aload           18
            //   689: monitorexit    
            //   690: goto            697
            //   693: aload           18
            //   695: monitorexit    
            //   696: athrow         
            //   697: aload_0        
            //   698: astore          19
            //   700: aload           19
            //   702: monitorenter   
            //   703: aload_0        
            //   704: invokevirtual   ji/filter/dll/n3$zn.b:()V
            //   707: aload_0        
            //   708: invokevirtual   java/lang/Object.notifyAll:()V
            //   711: aload           19
            //   713: monitorexit    
            //   714: goto            721
            //   717: aload           19
            //   719: monitorexit    
            //   720: athrow         
            //   721: ret             17
            //   723: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  133    154    160    190    Ljava/lang/InterruptedException;
            //  133    190    190    198    Any
            //  209    239    245    280    Ljava/lang/InterruptedException;
            //  209    280    280    288    Any
            //  209    300    306    310    Any
            //  53     339    342    384    Ljava/lang/InterruptedException;
            //  53     391    397    401    Any
            //  543    554    560    564    Any
            //  8      654    654    662    Any
            //  676    687    693    697    Any
            //  703    711    717    721    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Inconsistent stack size at #0653 (coming from #0651).
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public synchronized boolean a() {
            return this.a;
        }
        
        public void b() {
            synchronized (this) {
                this.a = true;
            }
        }
    }
    
    final class zk
    {
        boolean a;
        
        zk(final n3 n3) {
            this.a = false;
        }
    }
    
    private class zm extends Thread
    {
        zn a;
        
        public zm(final boolean b2) {
            super("Page counter");
            this.a = new zn(b2);
        }
        
        public void run() {
            this.setPriority(1);
            if (this.a()) {
                return;
            }
            if (!ji.util.i.c(241)) {
                try {
                    if (!ji.util.d.b()) {
                        Thread.sleep(2000L);
                    }
                }
                catch (InterruptedException ex) {
                    if (n3.this.m()) {
                        ji.io.h.d(n3.this.a, "count pages run interrupted");
                    }
                }
            }
            this.a.run();
        }
        
        public synchronized boolean a() {
            return this.a.a();
        }
        
        public void b() {
            if (n3.this.m()) {
                ji.io.h.d(n3.this.a, "jiDLLDoc count pages finish() called ".concat(String.valueOf(String.valueOf(this.getName()))));
            }
            synchronized (this) {
                this.a.b();
                this.interrupt();
            }
        }
    }
    
    interface aeg
    {
    }
}
