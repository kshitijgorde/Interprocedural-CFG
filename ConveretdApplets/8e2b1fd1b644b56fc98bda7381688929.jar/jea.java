import java.awt.Toolkit;
import java.awt.Cursor;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class jea extends Thread
{
    super A;
    while e;
    nea B;
    long C;
    volatile boolean D;
    volatile boolean E;
    boolean F;
    private volatile long G;
    private implements H;
    
    public static synchronized jea a(final super super1, final nea nea, final while while1) {
        return new jea(super1, nea, while1);
    }
    
    private jea(final super a, final nea b, final while e) {
        this.C = 120L;
        this.D = false;
        this.E = true;
        this.F = false;
        this.G = System.currentTimeMillis();
        this.H = new implements();
        this.A = a;
        this.e = e;
        this.B = b;
        this.H._(false);
        if (a.I != null) {
            this.H.addObserver(a.I);
        }
        this.setDaemon(true);
    }
    
    public void start() {
        super.start();
    }
    
    public void y(final int n) {
        if (n >= 0) {
            this.C = n * 1000;
        }
        else {
            this.C = 0L;
        }
    }
    
    public void n(final boolean f) {
        this.F = f;
    }
    
    public void B() {
        this.D = true;
    }
    
    public void m(final boolean e) {
        this.E = e;
    }
    
    public void _() {
        this.G = System.currentTimeMillis();
    }
    
    public void run() {
        while (!this.D) {
            if (!this.E) {
                if (this.C <= 0L) {
                    break;
                }
            }
            try {
                while (System.currentTimeMillis() - this.G < this.C) {
                    Thread.sleep(Math.min(this.C, this.C - (System.currentTimeMillis() - this.G)));
                }
            }
            catch (Exception ex) {
                continue;
            }
            if (!this.E) {
                String _ = null;
                String n = null;
                String f = null;
                String g = null;
                String[] array = new String[0];
                String[] array2 = new String[0];
                Label_0329: {
                    synchronized (this.A) {
                        Label_0305: {
                            if (this.e._()._() != null) {
                                Label_0315: {
                                    try {
                                        this.A.setEnabled(false);
                                        this.A.setCursor(Cursor.getPredefinedCursor(3));
                                        this.A.a(this.e.a().a("msgRefreshingData"));
                                        _ = this.e._()._();
                                        n = this.e._().n();
                                        f = this.e._().f();
                                        g = this.e._().g();
                                        final String[][] a = this.e._().a();
                                        array = a[0];
                                        array2 = a[1];
                                        break Label_0315;
                                    }
                                    finally {
                                        this.A.setCursor(Cursor.getPredefinedCursor(0));
                                        this.A.setEnabled(true);
                                    }
                                    break Label_0305;
                                }
                                break Label_0329;
                            }
                        }
                        this._();
                        continue;
                    }
                }
                this.H.a(_, f);
                this.H.a(n, g);
                for (int i = 0; i < array.length; ++i) {
                    this.H.a(array[i], array2[i]);
                }
                this.H.C();
                final long currentTimeMillis = System.currentTimeMillis();
                final this b = this.H.b(_);
                final boolean k = this.H.k(_);
                final this b2 = this.H.b(n);
                final boolean j = this.H.k(n);
                this.H.notifyObservers(new Integer(0));
                synchronized (this.A) {
                    if (this.D) {
                        break;
                    }
                    final this[] array3 = new this[array.length];
                    for (int l = 0; l < array.length; ++l) {
                        array3[l] = this.H.b(array[l]);
                    }
                    this.e._().b(array, array2, array3);
                    Label_0996: {
                        if (this.e._()._() != null && f != null && k && _ != null && this.e._()._() != null && _.equals(this.e._()._()) && currentTimeMillis > this.G) {
                            try {
                                this.A.setEnabled(false);
                                this.A.setCursor(Cursor.getPredefinedCursor(3));
                                this.A.a(this.e.a().a("msgRefreshingData"));
                                this.e._().b(f, b, g, b2);
                                break Label_0996;
                            }
                            finally {
                                this.B.b();
                                this.A.a(this.e._().getMessage());
                                this.A.setCursor(Cursor.getPredefinedCursor(0));
                                this.A.setEnabled(true);
                                this.B.repaint();
                                this._();
                                if (this.F && ((k && b != null && b.K() > 0) || (j && b2 != null && b2.K() > 0))) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        }
                        this.A.a(" ");
                    }
                    this._();
                }
                this.H.reset();
            }
            else {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        if (this.A.I != null) {
            this.H.deleteObservers();
        }
    }
}
