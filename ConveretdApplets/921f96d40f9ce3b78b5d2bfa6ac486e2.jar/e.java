import java.net.URLEncoder;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class e implements i
{
    private static int p;
    private boolean p;
    protected y p;
    private String p;
    private String d;
    private String a;
    private String n;
    private String v;
    private int d;
    private Thread p;
    private b p;
    private dx p;
    private dx d;
    protected boolean d;
    protected boolean a;
    boolean n;
    protected long p;
    protected long d;
    
    public e(final y p) {
        this.p = false;
        this.d = 2;
        this.p = new b();
        this.d = false;
        this.a = true;
        this.n = true;
        this.p = System.currentTimeMillis();
        this.d = System.currentTimeMillis();
        this.p = p;
        this.p = new dx();
        this.d = new dx();
        this.n();
    }
    
    private final void n() {
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
        this.d(s);
        this.d();
    }
    
    public final String p() {
        return (String)this.p.p();
    }
    
    private final Vector p(final String s) {
        final Vector<String> vector = new Vector<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openStream()));
            while (true) {
                String line = null;
                try {
                    line = bufferedReader.readLine();
                }
                catch (Exception ex2) {}
                if (line == null) {
                    break;
                }
                if (line.trim().equals("")) {
                    continue;
                }
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
        this.p.p().l("***Attempt to connect on port 8000...");
        this.n = null;
        final Vector p = this.p("http://" + this.d + ":8000/connect" + "?c=" + System.currentTimeMillis());
        if (p.size() == 0) {
            return false;
        }
        final String s = p.elementAt(0);
        if (!s.startsWith("key=")) {
            return false;
        }
        this.n = s.substring(4);
        return true;
    }
    
    private final void d() {
        if (this.n == null) {
            return;
        }
        int p = this.d.p();
        if (p > 5) {
            p = 5;
        }
        final StringBuffer sb = new StringBuffer();
        sb.append("http://").append(this.d).append(":8000/send?key=").append(this.n).append("&ncmds=").append(p);
        for (int i = 0; i < p; ++i) {
            sb.append("&cmd").append(i).append("=").append(URLEncoder.encode(du.p((String)this.d.p())));
        }
        sb.append("&c=").append(e.p++);
        final Vector p2 = this.p(sb.toString());
        for (int j = 0; j < p2.size(); ++j) {
            final String d = du.d(p2.elementAt(j));
            if (d.startsWith("PRESET") || d.startsWith("PERROR")) {
                this.p.p().l("<4>***Connection failed");
                this.d = false;
                return;
            }
            if (d.startsWith("PONG")) {
                this.d = System.currentTimeMillis();
            }
            else if (d.startsWith("PING")) {
                this.p.d();
            }
            else {
                this.p.p(d);
                if (this.n && !d.startsWith("LOGIN")) {
                    this.p.p().l("<4>" + d);
                    this.n("Error: Connection aborted");
                    du.p(3000);
                    this.p = true;
                }
                this.n = false;
                this.a = false;
            }
        }
    }
    
    public final void run() {
        int n = 0;
        while (!this.p && this.p.isValid()) {
            if (n++ >= 4) {
                this.n("Connection failure, please relogin");
                break;
            }
            this.d = false;
            this.p.p();
            this.d.p();
            if (!this.d()) {
                if (this.a) {
                    this.n("Connection failure, please talk to your network admin");
                    break;
                }
                this.n("Unable to establish connection");
                du.p(8000);
            }
            else {
                this.p.p().l("");
                this.p.p().l("<4>***You are connecting to the server using firewall option!  It is strongly recommended that you do NOT use this option if it is at all possible.  While this option makes it easy for you to make a connection to the server, the network performance and bandwidth usage may be disappointing.  In particular, you can expect that your network lag is much higher than normal!");
                this.p.p().l("");
                final long currentTimeMillis = System.currentTimeMillis();
                this.d = currentTimeMillis;
                this.p = currentTimeMillis;
                this.d = true;
                this.d(this.v);
                this.n = true;
                int n2 = 4;
                while (!this.p && this.d) {
                    if (this.p <= this.d && this.p + 10000L < System.currentTimeMillis()) {
                        this.p = System.currentTimeMillis();
                        this.d("PING");
                    }
                    else if (this.p > this.d) {
                        final long n3 = (System.currentTimeMillis() - this.p) / 1000L;
                        if (n3 > 120L) {
                            this.n("ERROR your network lag too much");
                            this.d = false;
                            break;
                        }
                        this.p.p(n3);
                    }
                    this.d();
                    if (this.p.p() > 0) {
                        n2 = 1 + n2 * 5 / 7;
                        if (n2 > 10) {
                            n2 = 10;
                        }
                    }
                    else {
                        n2 = n2 * 7 / 5;
                        if (n2 > 100) {
                            n2 = 100;
                        }
                    }
                    for (int i = 0; i < n2; ++i) {
                        du.p(500);
                        if (this.d.p() > 0) {
                            break;
                        }
                    }
                }
            }
        }
        if (this.p) {
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
