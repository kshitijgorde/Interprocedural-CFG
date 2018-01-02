import java.awt.Toolkit;
import java.awt.Cursor;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class gj extends Thread
{
    n jb;
    break kb;
    default lb;
    long mb;
    private int nb;
    volatile boolean ob;
    volatile boolean pb;
    boolean qb;
    private volatile long rb;
    private volatile int sb;
    private Uh tb;
    
    public static synchronized gj a(final n n, final default default1, final break break1) {
        return new gj(n, default1, break1);
    }
    
    private gj(final n jb, final default lb, final break kb) {
        this.mb = 120L;
        this.nb = Integer.MAX_VALUE;
        this.ob = false;
        this.pb = true;
        this.qb = false;
        this.rb = System.currentTimeMillis();
        this.sb = 0;
        this.tb = new Uh();
        this.jb = jb;
        this.kb = kb;
        this.lb = lb;
        this.tb.setUseCache(false);
        if (jb._ != null) {
            this.tb.addObserver(jb._);
        }
        this.setDaemon(true);
    }
    
    public void start() {
        super.start();
    }
    
    public void f(final int n) {
        if (n >= 0) {
            this.mb = n * 1000;
        }
        else {
            this.mb = 0L;
        }
    }
    
    public void g(final int nb) {
        if (nb >= 0) {
            this.nb = nb;
        }
        else {
            this.nb = Integer.MAX_VALUE;
        }
    }
    
    public void a(final boolean qb) {
        this.qb = qb;
    }
    
    public void c() {
        this.ob = true;
    }
    
    public void _(final boolean pb) {
        this.pb = pb;
    }
    
    public void a() {
        this.sb = this.nb;
        this.rb = System.currentTimeMillis();
    }
    
    public void d() {
        --this.sb;
        this.rb = System.currentTimeMillis();
    }
    
    public void e() {
        this.rb = System.currentTimeMillis();
    }
    
    public void run() {
        while (!this.ob) {
            if (!this.pb) {
                if (this.mb <= 0L) {
                    break;
                }
            }
            try {
                while (System.currentTimeMillis() - this.rb < this.mb) {
                    Thread.sleep(Math.min(this.mb, this.mb - (System.currentTimeMillis() - this.rb)));
                }
            }
            catch (Exception ex) {
                continue;
            }
            if (!this.pb && this.sb > 0) {
                String _ = null;
                String g = null;
                String h = null;
                String i = null;
                String[] array = new String[0];
                String[] array2 = new String[0];
                Label_0336: {
                    synchronized (this.jb) {
                        Label_0312: {
                            if (this.kb._()._() != null) {
                                Label_0322: {
                                    try {
                                        this.jb.setEnabled(false);
                                        this.jb.setCursor(Cursor.getPredefinedCursor(3));
                                        this.jb.b(this.kb.a().b("msgRefreshingData"));
                                        _ = this.kb._()._();
                                        g = this.kb._().g();
                                        h = this.kb._().h();
                                        i = this.kb._().i();
                                        final String[][] b = this.kb._().b();
                                        array = b[0];
                                        array2 = b[1];
                                        break Label_0322;
                                    }
                                    finally {
                                        this.jb.setCursor(Cursor.getPredefinedCursor(0));
                                        this.jb.setEnabled(true);
                                    }
                                    break Label_0312;
                                }
                                break Label_0336;
                            }
                        }
                        this.e();
                        continue;
                    }
                }
                this.tb.a(_, h);
                this.tb.a(g, i);
                for (int j = 0; j < array.length; ++j) {
                    this.tb.a(array[j], array2[j]);
                }
                this.tb.f();
                final long currentTimeMillis = System.currentTimeMillis();
                final q b2 = this.tb.b(_);
                final boolean c = this.tb.c(_);
                final q b3 = this.tb.b(g);
                final boolean c2 = this.tb.c(g);
                this.tb.notifyObservers(new Integer(0));
                synchronized (this.jb) {
                    if (this.ob) {
                        break;
                    }
                    final q[] array3 = new q[array.length];
                    for (int k = 0; k < array.length; ++k) {
                        array3[k] = this.tb.b(array[k]);
                    }
                    this.kb._().b(array, array2, array3);
                    Label_1003: {
                        if (this.kb._()._() != null && h != null && c && _ != null && this.kb._()._() != null && _.equals(this.kb._()._()) && currentTimeMillis > this.rb) {
                            try {
                                this.jb.setEnabled(false);
                                this.jb.setCursor(Cursor.getPredefinedCursor(3));
                                this.jb.b(this.kb.a().b("msgRefreshingData"));
                                this.kb._()._(h, b2, i, b3);
                                break Label_1003;
                            }
                            finally {
                                this.lb._();
                                this.jb.b(this.kb._().getMessage());
                                this.jb.setCursor(Cursor.getPredefinedCursor(0));
                                this.jb.setEnabled(true);
                                this.lb.repaint();
                                this.d();
                                if (this.qb && ((c && b2 != null && b2.l() > 0) || (c2 && b3 != null && b3.l() > 0))) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        }
                        this.jb.b(" ");
                    }
                    this.e();
                }
                this.tb.reset();
            }
            else {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        if (this.jb._ != null) {
            this.tb.deleteObservers();
        }
    }
}
