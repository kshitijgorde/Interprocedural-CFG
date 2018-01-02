import java.net.URLEncoder;
import java.io.Reader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import java.io.BufferedReader;

// 
// Decompiled by Procyon v0.5.30
// 

public class l implements i
{
    private static int p;
    private boolean p;
    protected y p;
    private String p;
    private String d;
    private String a;
    private String n;
    private String v;
    private Thread p;
    private b p;
    private dx p;
    private dx d;
    private g p;
    protected BufferedReader p;
    protected boolean d;
    protected boolean a;
    boolean n;
    protected long p;
    protected long d;
    
    public l(final y p) {
        this.p = false;
        this.p = new b();
        this.d = false;
        this.a = true;
        this.n = true;
        this.p = System.currentTimeMillis();
        this.d = System.currentTimeMillis();
        this.p = p;
        this.p = new dx();
        this.d = new dx();
        this.d();
    }
    
    private final void d() {
        this.a = this.p.p().getParameter("nick");
        this.p = this.p.p().getParameter("xhost");
        this.d = ((this.p != null) ? this.p : this.p.p().getCodeBase().getHost());
        this.v = "LOGIN " + this.a + " " + this.p.p().getParameter("domain") + " " + this.p.p().getParameter("stamp") + " " + this.p.p().getParameter("browser") + " " + this.p.p().getParameter("IP") + " " + this.p.p().getParameter("hash") + " " + this.p.p().getParameter("room") + " " + this.p.p().getParameter("table") + " " + this.p.a();
        if (this.p != null) {
            this.v = "s" + this.v;
        }
    }
    
    public final String d() {
        return this.a;
    }
    
    public final void a(final String a) {
        this.a = a;
    }
    
    protected final void v(final String s) {
        if (s.toUpperCase().startsWith("ERROR")) {
            this.p.p().l("<4>***" + s);
            return;
        }
        this.p.p().l("***" + s);
    }
    
    public final void d(final String s) {
        if (this.d.p() > 100) {
            this.v("Error: Connection failure, unable to send data");
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
        this.d(s);
        this.n();
    }
    
    public final String p() {
        return (String)this.p.p();
    }
    
    private final Vector p(final String s) {
        final Vector<String> vector = new Vector<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openStream()));
            String line = null;
            try {
                line = bufferedReader.readLine();
            }
            catch (Exception ex2) {}
            if (line != null) {
                vector.addElement(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return vector;
    }
    
    private final boolean d() {
        this.p.p().l("***Attempt to connect on port 8080...");
        this.n = null;
        try {
            this.p = new BufferedReader(new InputStreamReader(new URL("http://" + this.d + ":8080/connect" + "?c=" + System.currentTimeMillis()).openStream()));
            final String line = this.p.readLine();
            if (line == null) {
                return false;
            }
            if (!line.startsWith("key=")) {
                return false;
            }
            this.n = line.substring(4);
            if (this.p != null) {
                this.p.p = true;
            }
            this.p = new g(this);
            return true;
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }
    
    private final void n() {
        if (this.n == null) {
            return;
        }
        int p = this.d.p();
        if (p == 0) {
            return;
        }
        if (p > 5) {
            p = 5;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("http://").append(this.d).append(":8080/send?key=").append(this.n).append("&ncmds=").append(p);
        for (int i = 0; i < p; ++i) {
            sb.append("&cmd").append(i).append("=").append(URLEncoder.encode(du.p((String)this.d.p())));
        }
        sb.append("&c=").append(l.p++);
        boolean b = false;
        final Vector p2 = this.p(sb.toString());
        for (int j = 0; j < p2.size(); ++j) {
            final String s = p2.elementAt(j);
            if (s.startsWith("PRESET") || s.startsWith("PERROR")) {
                this.p.p().l("<4>***Connection failed");
                this.d = false;
                break;
            }
            if (s.startsWith("PSUCCESS")) {
                b = true;
            }
        }
        if (!b) {
            this.d = false;
        }
    }
    
    public final void n(final String s) {
        if (s.startsWith("PONG")) {
            this.d = System.currentTimeMillis();
            return;
        }
        if (s.startsWith("PING")) {
            this.p.d();
            return;
        }
        this.p.p(s);
        if (this.n && !s.startsWith("LOGIN")) {
            this.p.p().l("<4>" + s);
            this.v("Error: Connection aborted");
            du.p(3000);
            this.p = true;
        }
        this.n = false;
        this.a = false;
    }
    
    public final void run() {
        while (!this.p && this.p.isValid()) {
            this.d = false;
            this.p.p();
            this.d.p();
            if (!this.d()) {
                if (this.a) {
                    this.v("Connection failure, change connection method...");
                    this.p.e();
                    break;
                }
                this.v("Unable to establish connection");
                du.p(8000);
            }
            else {
                final long currentTimeMillis = System.currentTimeMillis();
                this.d = currentTimeMillis;
                this.p = currentTimeMillis;
                this.d(this.v);
                this.n = true;
                this.d = true;
                while (!this.p && this.d) {
                    if (this.p <= this.d && this.p + 120000L < System.currentTimeMillis()) {
                        this.p = System.currentTimeMillis();
                        this.d("PING");
                    }
                    else if (this.p > this.d) {
                        final long n = (System.currentTimeMillis() - this.p) / 1000L;
                        if (n > 120L) {
                            this.v("ERROR your network lag too much");
                            this.d = false;
                            break;
                        }
                        this.p.p(n);
                    }
                    this.n();
                    du.p(500);
                }
                if (this.a) {
                    this.v("Connection failure, change connection method...");
                    this.p.e();
                    break;
                }
                continue;
            }
        }
        if (this.p && this.d) {
            this.p("LOGOUT");
        }
        this.p = true;
    }
    
    public final void a() {
        if (this.p == null) {
            (this.p = new Thread(this)).start();
        }
    }
    
    public final void p() {
        this.p = true;
    }
    
    public final boolean p() {
        return this.p != null && this.p.isAlive() && !this.p;
    }
}
