import java.awt.Toolkit;
import java.awt.Cursor;
import java.util.Observer;

// 
// Decompiled by Procyon v0.5.30
// 

public class gp extends Thread
{
    var xGb;
    g iGb;
    lp yGb;
    long zGb;
    volatile boolean AGb;
    volatile boolean BGb;
    boolean CGb;
    private volatile long DGb;
    private Zo EGb;
    
    public static synchronized gp _(final var var, final lp lp, final g g) {
        return new gp(var, lp, g);
    }
    
    private gp(final var xGb, final lp yGb, final g iGb) {
        this.zGb = 120L;
        this.AGb = false;
        this.BGb = true;
        this.CGb = false;
        this.DGb = System.currentTimeMillis();
        this.EGb = new Zo();
        this.xGb = xGb;
        this.iGb = iGb;
        this.yGb = yGb;
        this.EGb.g(false);
        if (xGb.FGb != null) {
            this.EGb.addObserver(xGb.FGb);
        }
        this.setDaemon(true);
    }
    
    public void start() {
        super.start();
    }
    
    public void Z(final int n) {
        if (n >= 0) {
            this.zGb = n * 1000;
        }
        else {
            this.zGb = 0L;
        }
    }
    
    public void h(final boolean cGb) {
        this.CGb = cGb;
    }
    
    public void w() {
        this.AGb = true;
    }
    
    public void a(final boolean bGb) {
        this.BGb = bGb;
    }
    
    public void _() {
        this.DGb = System.currentTimeMillis();
    }
    
    public void run() {
        while (!this.AGb) {
            if (!this.BGb) {
                if (this.zGb <= 0L) {
                    break;
                }
            }
            try {
                while (System.currentTimeMillis() - this.DGb < this.zGb) {
                    Thread.sleep(Math.min(this.zGb, this.zGb - (System.currentTimeMillis() - this.DGb)));
                }
            }
            catch (Exception ex) {
                continue;
            }
            if (!this.BGb) {
                String i = null;
                String k = null;
                String c = null;
                String d = null;
                String[] array = new String[0];
                String[] array2 = new String[0];
                Label_0329: {
                    synchronized (this.xGb) {
                        Label_0305: {
                            if (this.iGb._().i() != null) {
                                Label_0315: {
                                    try {
                                        this.xGb.setEnabled(false);
                                        this.xGb.setCursor(Cursor.getPredefinedCursor(3));
                                        this.xGb.b(this.iGb.b()._("msgRefreshingData"));
                                        i = this.iGb._().i();
                                        k = this.iGb._().k();
                                        c = this.iGb._().c();
                                        d = this.iGb._().d();
                                        final String[][] _ = this.iGb._()._();
                                        array = _[0];
                                        array2 = _[1];
                                        break Label_0315;
                                    }
                                    finally {
                                        this.xGb.setCursor(Cursor.getPredefinedCursor(0));
                                        this.xGb.setEnabled(true);
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
                this.EGb.b(i, c);
                this.EGb.b(k, d);
                for (int j = 0; j < array.length; ++j) {
                    this.EGb.b(array[j], array2[j]);
                }
                this.EGb.x();
                final long currentTimeMillis = System.currentTimeMillis();
                final _ a = this.EGb.a(i);
                final boolean m = this.EGb.m(i);
                final _ a2 = this.EGb.a(k);
                final boolean l = this.EGb.m(k);
                this.EGb.notifyObservers(new Integer(0));
                synchronized (this.xGb) {
                    if (this.AGb) {
                        break;
                    }
                    final _[] array3 = new _[array.length];
                    for (int n = 0; n < array.length; ++n) {
                        array3[n] = this.EGb.a(array[n]);
                    }
                    this.iGb._().b(array, array2, array3);
                    Label_0996: {
                        if (this.iGb._().i() != null && c != null && m && i != null && this.iGb._().i() != null && i.equals(this.iGb._().i()) && currentTimeMillis > this.DGb) {
                            try {
                                this.xGb.setEnabled(false);
                                this.xGb.setCursor(Cursor.getPredefinedCursor(3));
                                this.xGb.b(this.iGb.b()._("msgRefreshingData"));
                                this.iGb._()._(c, a, d, a2);
                                break Label_0996;
                            }
                            finally {
                                this.yGb.b();
                                this.xGb.b(this.iGb._().getMessage());
                                this.xGb.setCursor(Cursor.getPredefinedCursor(0));
                                this.xGb.setEnabled(true);
                                this.yGb.repaint();
                                this._();
                                if (this.CGb && ((m && a != null && a.L() > 0) || (l && a2 != null && a2.L() > 0))) {
                                    Toolkit.getDefaultToolkit().beep();
                                }
                            }
                        }
                        this.xGb.b(" ");
                    }
                    this._();
                }
                this.EGb.reset();
            }
            else {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex2) {}
            }
        }
        if (this.xGb.FGb != null) {
            this.EGb.deleteObservers();
        }
    }
}
