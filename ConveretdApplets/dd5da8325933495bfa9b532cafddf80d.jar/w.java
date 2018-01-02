import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class w implements Runnable
{
    public static final int a;
    public static final int b = 1;
    public static final int c = 2;
    Vector d;
    private d e;
    
    public void a() {
        this.notify();
    }
    
    public w(final d e) {
        this.e = e;
        this.d = new Vector();
        final Thread thread;
        (thread = new Thread(this)).setPriority(10);
        thread.start();
    }
    
    public void run() {
        final boolean dj = p.dJ;
        while (true) {
            synchronized (this) {
                try {
                    this.wait();
                }
                catch (Exception ex) {}
            }
            while (true) {
                synchronized (this) {
                    Label_0062: {
                        if (this.d.size() == 0) {
                            if (dj) {
                                break Label_0062;
                            }
                            // monitorexit(this)
                            if (!dj) {
                                break;
                            }
                        }
                        this.d.firstElement();
                    }
                    final int n = ((int[])(Object)this)[0];
                    final int n2 = ((int[])this.d.firstElement())[1];
                    this.d.removeElementAt(0);
                    final int n3;
                    if ((n3 = p.dF[n]) == -1) {
                        continue;
                    }
                    if (n2 == 0) {
                        this.e.z(n3);
                        if (!dj) {
                            continue;
                        }
                    }
                    if (n2 == 1) {
                        this.e.d(n3, true);
                        if (!dj) {
                            continue;
                        }
                    }
                    if (n2 != 2) {
                        continue;
                    }
                    this.e.A(n3);
                }
            }
        }
    }
    
    public synchronized void a(final int n, final int n2) {
        this.d.addElement(new int[] { n, n2 });
        this.notify();
    }
}
