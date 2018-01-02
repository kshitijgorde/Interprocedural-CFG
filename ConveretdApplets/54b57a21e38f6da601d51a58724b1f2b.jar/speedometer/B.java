// 
// Decompiled by Procyon v0.5.30
// 

package speedometer;

import java.net.MalformedURLException;
import java.util.Map;
import speedometer.A.E;
import speedometer.A.A;
import javax.swing.JProgressBar;
import java.io.InputStream;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class B implements ActionListener
{
    public static final String P = "ready";
    public static final String G = "uploading";
    public static final String C = "downloading";
    public static final int H = 256;
    public static final long O = 500L;
    protected String D;
    protected String M;
    protected int N;
    protected long J;
    protected long A;
    protected String E;
    protected int L;
    protected long K;
    protected B F;
    protected C I;
    protected boolean B;
    
    public B() {
        this.D = "ready";
        this.N = 10;
        this.J = 10485760L;
        this.L = 10;
        this.F = this;
    }
    
    public C C() {
        return this.I;
    }
    
    public void A(final C i) {
        if (this.I != null) {
            throw new RuntimeException("Controller.setMainView() already invoked");
        }
        this.I = i;
        i.M().addActionListener(this);
        i.I().addActionListener(this);
        i.F().addActionListener(this);
    }
    
    public boolean J() {
        return this.B;
    }
    
    public void A(final boolean b) {
        this.B = b;
    }
    
    public int H() {
        return this.L;
    }
    
    public void B(final int l) {
        this.L = l;
    }
    
    public String K() {
        return this.E;
    }
    
    public void A(final String e) {
        this.E = e;
    }
    
    public int G() {
        return this.N;
    }
    
    public void A(final int n) {
        this.N = n;
    }
    
    public String E() {
        return this.M;
    }
    
    public void C(final String m) {
        this.M = m;
    }
    
    public long D() {
        return this.J;
    }
    
    public void A(final long j) {
        this.J = j;
    }
    
    protected void B(final String s) {
        System.out.println(s);
    }
    
    public long A() {
        return System.currentTimeMillis();
    }
    
    private Thread I() {
        synchronized (this.D) {
            this.D = "downloading";
            this.I.B(false);
            final Thread thread = new Thread(new Runnable() {
                public void run() {
                    new _A().A();
                }
            });
            thread.start();
            // monitorexit(this.D)
            return thread;
        }
    }
    
    private Thread F() {
        synchronized (this.D) {
            this.D = "uploading";
            this.I.B(false);
            final Thread thread = new Thread(new Runnable() {
                public void run() {
                    new _B().D();
                }
            });
            thread.start();
            // monitorexit(this.D)
            return thread;
        }
    }
    
    private void B() {
        new Thread(new Runnable() {
            public void run() {
                final Thread access$0 = speedometer.B.this.F();
                try {
                    access$0.join();
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                final Thread access$2 = speedometer.B.this.I();
                try {
                    access$2.join();
                }
                catch (InterruptedException ex2) {
                    ex2.printStackTrace();
                }
            }
        }).start();
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(this.I.M())) {
            this.B();
        }
        else if (actionEvent.getSource().equals(this.I.I())) {
            this.F();
        }
        else if (actionEvent.getSource().equals(this.I.F())) {
            this.I();
        }
    }
    
    protected class _A
    {
        public void A() {
            synchronized (speedometer.B.this.D) {
                InputStream inputStream = null;
                try {
                    inputStream = new URL(speedometer.B.this.F.E).openConnection().getInputStream();
                    final byte[] array = new byte[256];
                    speedometer.B.this.A();
                    final long a = speedometer.B.this.A();
                    long n = 0L;
                    final long n2 = speedometer.B.this.F.L * 1000;
                    long n3 = 0L;
                    long n4 = 0L;
                    speedometer.B.this.K = 0L;
                    final JProgressBar c = speedometer.B.this.I.C();
                    c.setMinimum(0);
                    c.setMaximum((int)n2);
                    while (n < n2) {
                        final int read;
                        if ((read = inputStream.read(array)) == -1) {
                            break;
                        }
                        n3 += read;
                        final long a2 = speedometer.B.this.A();
                        n = a2 - a;
                        if (n <= 0L) {
                            continue;
                        }
                        speedometer.B.this.K = n3 * 1000L / n;
                        c.setValue((int)n);
                        if (a2 - n4 <= 500L) {
                            continue;
                        }
                        speedometer.B.this.I.H().setText(speedometer.B.this.K * 8L / 1024L + " Kbps");
                        n4 = a2;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                    }
                    speedometer.B.this.I.B(true);
                    speedometer.B.this.D = "ready";
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                }
                speedometer.B.this.I.B(true);
            }
            // monitorexit(this.this$0.D = "ready")
        }
    }
    
    protected class _B implements A
    {
        long L;
        long I;
        long J;
        JProgressBar K;
        E M;
        
        protected _B() {
            this.I = speedometer.B.this.F.N * 1000;
            this.J = 0L;
            this.K = speedometer.B.this.I.A();
            this.M = new E();
        }
        
        public void D() {
            synchronized (speedometer.B.this.D) {
                this.K.setMinimum(0);
                this.K.setMaximum((int)this.I);
                this.K.setValue(0);
                try {
                    final boolean a = this.M.A(speedometer.B.this.F.M, null, "file", speedometer.B.this.J, "file.bin", this, new StringBuffer(), new StringBuffer(), new StringBuffer());
                    if (a) {
                        final boolean b = !a;
                    }
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                finally {
                    speedometer.B.this.I.B(true);
                    speedometer.B.this.D = "ready";
                }
                speedometer.B.this.I.B(true);
            }
            // monitorexit(this.this$0.D = "ready")
        }
        
        public void A(final long n) {
            this.L = speedometer.B.this.A();
        }
        
        public void A(final long n, final long n2) {
            final long a = speedometer.B.this.A();
            final long n3 = a - this.L;
            if (n3 > 0L) {
                speedometer.B.this.A = n * 1000L / n3;
                this.K.setValue((int)n3);
                if (a - this.J > 500L) {
                    speedometer.B.this.I.G().setText(speedometer.B.this.A * 8L / 1024L + " Kbps");
                    this.J = a;
                }
            }
            if (n3 > this.I) {
                this.M.A(true);
            }
        }
        
        public void A() {
        }
        
        public void A(final String s) {
        }
    }
}
