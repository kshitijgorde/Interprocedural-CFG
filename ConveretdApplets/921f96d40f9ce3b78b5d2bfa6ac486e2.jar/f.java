import java.io.OutputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class f implements i
{
    private boolean p;
    protected y p;
    private String p;
    private int[] p;
    private String d;
    private String a;
    private int p;
    private Thread p;
    protected Socket p;
    private c p;
    private OutputStream p;
    private b p;
    private dx p;
    private dx d;
    private v p;
    protected String n;
    protected boolean d;
    protected boolean a;
    protected long p;
    protected long d;
    
    public f(final y p) {
        this.p = false;
        this.p = new b();
        this.d = false;
        this.a = true;
        this.p = System.currentTimeMillis();
        this.d = System.currentTimeMillis();
        this.p = p;
        this.p = new dx();
        this.d = new dx();
        this.n();
    }
    
    private final void n() {
        this.d = this.p.p().getParameter("nick");
        this.p = new int[du.p(this.p.p().getParameter("nports"), 10)];
        for (int i = 0; i < this.p.length; ++i) {
            this.p[i] = du.p(this.p.p().getParameter("port" + (i + 1)), 10);
        }
        this.n = this.p.p().getParameter("xhost");
        this.p = ((this.n != null) ? this.n : this.p.p().getCodeBase().getHost());
        this.a = "LOGIN " + this.d + " " + this.p.p().getParameter("domain") + " " + this.p.p().getParameter("stamp") + " " + this.p.p().getParameter("browser") + " " + this.p.p().getParameter("IP") + " " + this.p.p().getParameter("hash") + " " + this.p.p().getParameter("room") + " " + this.p.p().getParameter("table") + " " + this.p.a();
    }
    
    public final String d() {
        return this.d;
    }
    
    public final void a(final String d) {
        this.d = d;
    }
    
    protected final void n(final String s) {
        if (s.toUpperCase().startsWith("ERROR")) {
            this.p.p().l("<4>***" + s);
            return;
        }
        this.p.p().l("***" + s);
    }
    
    public final void d(final String s) {
        if (this.d.p() > 100) {
            this.n("Error: Connection failure, unable to send data");
            this.p = true;
            return;
        }
        this.d.p(s);
    }
    
    public final boolean p(final String s) {
        final boolean p = this.p.p();
        this.p.p(s);
        if (!this.p.p()) {
            this.d(s);
            return true;
        }
        if (!p) {
            this.p.p().l("<4>***Potential flood detected, messages will be ignored for 10m.");
        }
        else {
            this.p.p().l("<4>***Input message currently disable");
        }
        return false;
    }
    
    public final void p(final String s) {
        if (this.p != null) {
            final byte[] bytes = (String.valueOf(du.p(s)) + "\n").getBytes();
            try {
                this.p.write(bytes);
                this.p.flush();
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
    
    public final String p() {
        return (String)this.p.p();
    }
    
    private final boolean d() {
        final int n = this.a ? 1 : 20;
        int i = 0;
        while (i < n * this.p.length) {
            final int n2 = this.p[this.p];
            try {
                this.n("Open port " + n2);
                this.p = new Socket(this.p, n2);
                this.n("Connected to port " + n2 + "...");
                this.p = new c(this.p.getInputStream());
                this.p = this.p.getOutputStream();
                return true;
            }
            catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                ex.printStackTrace();
                this.n("Connection to port " + n2 + " failed");
                if (this.a) {
                    this.p = (this.p + 1) % this.p.length;
                }
                du.p(8000 + 4000 * i);
                ++i;
            }
        }
        this.p.p().l("<4>***Connection failure, bad connection or firewall problem");
        return false;
    }
    
    private final void d() {
        this.p = true;
        if (this.p != null) {
            this.p.p();
            this.p = null;
        }
        try {
            this.p.close();
        }
        catch (Exception ex) {}
    }
    
    public final void run() {
        int n = 0;
        if (this.n != null) {
            this.a = "s" + this.a;
        }
        while (!this.p && this.p.isValid()) {
            if (n++ >= 8) {
                this.n("Connection failure, please relogin");
                break;
            }
            this.d = false;
            this.p.p();
            this.d.p();
            if (!this.d()) {
                if (this.a) {
                    this.n("Connection failure, change connection method...");
                    this.p.e();
                    break;
                }
                break;
            }
            else {
                du.p(200);
                if (this.p != null) {
                    this.p.p();
                }
                final long currentTimeMillis = System.currentTimeMillis();
                this.d = currentTimeMillis;
                this.p = currentTimeMillis;
                this.p = new v(this, this.d, this.p);
                this.d = true;
                this.p(this.a);
                int n2 = 1;
                while (!this.p) {
                    try {
                        String p = null;
                        try {
                            p = this.p.p();
                        }
                        catch (Exception ex2) {}
                        if (p == null) {
                            if (!this.p) {
                                this.n("ERROR: Connection terminated.");
                                du.p(3000);
                                break;
                            }
                            break;
                        }
                        else {
                            final String d = du.d(p);
                            if (d.startsWith("PONG")) {
                                this.d = System.currentTimeMillis();
                            }
                            else if (d.startsWith("PING")) {
                                this.p.d();
                            }
                            else {
                                this.p.p(d);
                                if (n2 != 0 && !d.startsWith("LOGIN")) {
                                    this.p.p().l("<4>" + d);
                                    this.n("Error: Connection aborted");
                                    du.p(3000);
                                    this.p = true;
                                }
                                n2 = 0;
                                this.a = false;
                            }
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        ex.printStackTrace();
                    }
                }
            }
        }
        this.p = null;
        if (this.p || this.p <= this.d || System.currentTimeMillis() <= this.p + 10000L) {
            this.p.p("LOGOUT");
        }
        this.d();
    }
    
    public final void a() {
        if (this.p == null) {
            (this.p = new Thread(this)).start();
        }
    }
    
    public final void p() {
        this.d();
    }
    
    public final boolean p() {
        return this.p != null && this.p.isAlive() && !this.p;
    }
}
