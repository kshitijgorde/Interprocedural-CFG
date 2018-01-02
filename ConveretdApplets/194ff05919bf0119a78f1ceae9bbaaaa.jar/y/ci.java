// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Container;
import java.io.IOException;

public final class ci implements Runnable, b
{
    private ew a;
    private dy a;
    private String a;
    private String b;
    private cn a;
    private dx a;
    private String c;
    private String d;
    private int a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private String g;
    public Thread a;
    public Object a;
    
    public ci(final String b, final String c, final cn a, final int a2, final int b2) {
        this.a = "WindowController" + System.currentTimeMillis();
        this.a = ew.a();
        (this.a = dy.a()).a(this);
        this.b = b;
        this.a = a;
        this.c = c;
        this.a = a2;
        this.b = b2;
        this.c = 0;
        this.a = new Object();
    }
    
    public final void a(final int d, final String e, final String f, final String g) {
        final long currentTimeMillis = System.currentTimeMillis();
        final StringBuffer sb = new StringBuffer(this.b);
        final StringBuffer sb2 = new StringBuffer("ygames_tableapplet_");
        this.d = dx.a(e, d, f, currentTimeMillis);
        sb2.append(e);
        sb2.append('_');
        sb2.append(d);
        sb.append('?');
        sb.append("rmn");
        sb.append('=');
        sb.append(this.a);
        sb.append('&');
        sb.append("tn");
        sb.append('=');
        sb.append(d);
        sb.append('&');
        sb.append("rn");
        sb.append('=');
        sb.append(e);
        sb.append('&');
        sb.append("lid");
        sb.append('=');
        sb.append(f);
        sb.append('&');
        sb.append("pid");
        sb.append('=');
        sb.append(g);
        sb.append('&');
        sb.append("nosignedcab");
        sb.append('=');
        sb.append(this.c);
        sb.append('&');
        sb.append("ts");
        sb.append('=');
        sb.append(currentTimeMillis);
        this.a.a(sb.toString(), sb2.toString());
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        (this.a = new Thread(this)).start();
    }
    
    public final String a() {
        return this.a;
    }
    
    public final Object b(final int n, final Object o) {
        try {
            if (null != this.a) {
                this.a.b("Sending cmd:" + n + " to window:" + this.a.a);
                this.a.a(this.a.a, n, o);
            }
            else {
                this.a.b("No slave window to send cmd:" + n);
            }
        }
        catch (IOException ex) {
            this.a.b("Cmd failed. cmd=" + n + " window=" + this.a.a, ex);
        }
        return null;
    }
    
    public final Object a(int n2, final Object o) {
        switch (n) {
            case 0: {
                final dx a = (dx)o;
                this.a.b("Got new window name=" + a.a);
                final String a2;
                if ((a2 = a.a).equals(this.d)) {
                    synchronized (this.a) {
                        (this.a = a).notifyAll();
                    }
                    final ci ci;
                    ci.a.i();
                    ci.d = null;
                    break;
                }
                this.a.c("Got unexpected slave window name=" + a2);
                this.a.c("Expected slave window name=" + this.d);
                final ci ci2 = this;
                n2 = (int)a2;
                this = ci2;
                try {
                    this.a.b("Sending cmd:7 to window:" + (String)n2);
                    this.a.a((String)n2, 7, null);
                }
                catch (IOException ex) {
                    this.a.b("Cmd failed. cmd=7 window=" + (String)n2, ex);
                }
                break;
            }
            case 1: {
                this.a.b("Got CMD_CLOSEDWINDOW=" + ((dx)o).a);
                this.a.h();
                synchronized (this.a) {
                    this.a = null;
                    break;
                }
            }
            case 6: {
                final Container container = (Container)o;
                this.a.b("Got CMD_SETAWTBRIDGECONTAINER");
                this.a.a(container);
                break;
            }
            case 8: {
                this.a.k();
                break;
            }
            default: {
                this.a.c("WindowController got unknown cmd:" + n);
                break;
            }
        }
        return null;
    }
    
    public final void run() {
        this.a.b("WindowController.run()");
        try {
            synchronized (this.a) {
                if (null == this.a) {
                    try {
                        this.a.wait(this.a * 1000);
                    }
                    catch (InterruptedException ex) {
                        this.a.a("Interrupted from waiting for slave window", ex);
                    }
                }
                if (null == this.a) {
                    if (Thread.currentThread() == this.a) {
                        if (this.c < this.b) {
                            ++this.c;
                            this.a.b("createnewwindow timeout. Retry #" + this.c);
                            this.a(this.d, this.e, this.f, this.g);
                        }
                        else {
                            this.a.b("Too many retries creating slave window. Quitting.");
                            this.a.j();
                        }
                    }
                    else {
                        this.a.b("Leaving abandoned retry thread");
                    }
                }
            }
        }
        catch (Exception ex2) {
            this.a.b("Exception in WindowController.run()", ex2);
        }
        this.a.b("Exiting WindowController.run()");
    }
}
