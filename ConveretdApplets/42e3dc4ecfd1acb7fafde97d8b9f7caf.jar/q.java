import java.util.StringTokenizer;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class q implements r
{
    private s a;
    private boolean b;
    private String c;
    private int d;
    private long e;
    private String f;
    private a4 g;
    private a7 h;
    private Socket i;
    private boolean j;
    private String k;
    
    public q() {
        this.i = null;
        this.a = null;
        this.h = null;
        this.b = false;
        this.g = new a4();
        this.e = 1000L;
        this.d = 512;
        this.j = false;
        this.f = "RocketDogIRCBot";
        this.c = "RocketDogIRCBot";
        this.k = "RocketDogIRCBot 0.2.2 www.rocketdog.com.au";
    }
    
    public final void b(final String s, final String s2) {
        this.j("MODE " + s + " +b " + s2);
    }
    
    public final synchronized void a(final String s, final int n, final String s2, final int n2) throws IOException, a6, a5 {
        if (this.g()) {
            throw new IOException("The Bot is already connected to an IRC server.  Disconnect first.");
        }
        this.i = new Socket(s, n);
        this.e("*** Connected to server.");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(this.i.getInputStream(), n2)), n2);
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.i.getOutputStream()));
        if (s2 != null && !s2.equals("")) {
            bufferedWriter.write("PASS " + s2 + "\r\n");
        }
        bufferedWriter.write("NICK " + this.f + "\r\n");
        bufferedWriter.write("USER " + this.c + " 8 * :" + this.k + "\r\n");
        bufferedWriter.flush();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.e(line);
            if (line.startsWith("PING ")) {
                bufferedWriter.write("PONG " + line.substring(5) + "\r\n");
                bufferedWriter.flush();
            }
            else {
                final StringTokenizer stringTokenizer = new StringTokenizer(line);
                stringTokenizer.nextToken();
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.equals("004")) {
                    break;
                }
                if (nextToken.equals("433")) {
                    throw new a5(line);
                }
                if (nextToken.startsWith("5") || nextToken.startsWith("4")) {
                    throw new a6("Could not log into the IRC server: " + line);
                }
                continue;
            }
        }
        this.e("*** Logged onto server.");
        (this.a = new s(this, bufferedReader, bufferedWriter)).start();
        if (this.h == null) {
            (this.h = new a7(this, this.g)).start();
        }
        this.b = true;
    }
    
    public final synchronized void e() {
        this.b = false;
        this.e("*** Disconnected.");
        try {
            this.i.close();
        }
        catch (IOException ex) {}
        this.h();
    }
    
    public final int f() {
        return this.d;
    }
    
    public final void c(final String s) {
        this.e(s);
        if (s.startsWith("PING ")) {
            this.f(s.substring(5));
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String nextToken = stringTokenizer.nextToken();
        final int index = nextToken.indexOf("!");
        final int index2 = nextToken.indexOf("@");
        if (!nextToken.startsWith(":")) {
            this.g(s);
            return;
        }
        if (index > 0 && index2 > 0 && index < index2) {
            final String substring = nextToken.substring(1, index);
            final String substring2 = nextToken.substring(index + 1, index2);
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            if (nextToken2.equals("PRIVMSG") && (nextToken3.startsWith("#") || nextToken3.startsWith("&"))) {
                this.c(nextToken3, substring, substring2, s.substring(s.indexOf(" :") + 2));
                return;
            }
            if (nextToken2.equals("WALLOPS")) {
                this.a(substring, s.substring(s.indexOf(" :") + 12));
                return;
            }
            if (nextToken2.equals("PRIVMSG") && nextToken3.equalsIgnoreCase(this.f)) {
                this.c(substring, substring2, s.substring(s.indexOf(" :") + 2));
                return;
            }
            if (nextToken2.equals("JOIN")) {
                this.a(s.substring(s.indexOf(" :") + 2), substring, substring2);
                return;
            }
            if (nextToken2.equals("PART")) {
                if (s.indexOf(":") > 0) {
                    this.a(nextToken3, substring, substring2, s.substring(s.indexOf(" :") + 2));
                }
                else {
                    this.a(nextToken3, substring, substring2, "");
                }
                return;
            }
            if (nextToken2.equals("NOTICE")) {
                this.b(substring, substring2, nextToken3, s.substring(s.indexOf(" :") + 2));
                return;
            }
            if (nextToken2.equals("QUIT")) {
                this.b(substring, substring2, s.substring(s.indexOf(" :") + 2));
                return;
            }
            if (nextToken2.equals("KICK")) {
                this.a(nextToken3, substring, substring2, stringTokenizer.nextToken(), s.substring(s.indexOf(" :") + 2));
                return;
            }
            if (nextToken2.equals("MODE")) {
                this.d(nextToken3, substring, substring2, s.substring(s.indexOf(nextToken3) + nextToken3.length() + 1));
                return;
            }
            this.g(s);
        }
        else {
            if (!stringTokenizer.hasMoreTokens()) {
                this.g(s);
                return;
            }
            final String nextToken4 = stringTokenizer.nextToken();
            if (nextToken4.length() == 3) {
                int int1;
                try {
                    int1 = Integer.parseInt(nextToken4);
                }
                catch (NumberFormatException ex) {
                    this.g(s);
                    return;
                }
                this.a(int1, s.substring(s.indexOf(nextToken4, nextToken.length()) + 4, s.length()));
                return;
            }
            this.g(s);
        }
    }
    
    public final boolean g() {
        return this.b;
    }
    
    public final void c(final String s, final String s2) {
        this.d(s + " " + s2);
    }
    
    public final void d(final String s) {
        this.j("JOIN " + s);
    }
    
    public final void e(final String s, final String s2, final String s3) {
        this.j("KICK " + s + " " + s2 + " :" + s3);
    }
    
    public void e(final String s) {
        if (this.j) {
            System.out.println(System.currentTimeMillis() + " " + s);
        }
    }
    
    public void e(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void h() {
    }
    
    public void a(final String s, final String s2, final String s3) {
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    public void c(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void d(final String s, final String s2, final String s3, final String s4) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s4);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        int n2 = 32;
        int n3 = 1;
        for (int i = 0; i < array[0].length(); ++i) {
            final char char1 = array[0].charAt(i);
            if (char1 == '+' || char1 == '-') {
                n2 = char1;
            }
            else if (char1 == 'o') {
                if (n2 == 43) {
                    this.f(s, s2, s3, array[n3]);
                }
                else {
                    this.e(s, s2, s3, array[n3]);
                }
                ++n3;
            }
            else if (char1 == 'b') {
                if (n2 == 43) {
                    this.h(s, s2, s3, array[n3]);
                }
                else {
                    this.g(s, s2, s3, array[n3]);
                }
                ++n3;
            }
            else if (char1 == 't') {
                if (n2 == 43) {
                    this.i(s, s2, s3);
                }
                else {
                    this.g(s, s2, s3);
                }
            }
            else if (char1 == 'p') {
                if (n2 == 43) {
                    this.h(s, s2, s3);
                }
                else {
                    this.f(s, s2, s3);
                }
            }
        }
    }
    
    public void b(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void f(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void a(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void c(final String s, final String s2, final String s3) {
    }
    
    public void b(final String s, final String s2, final String s3) {
    }
    
    public void g(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void f(final String s, final String s2, final String s3) {
    }
    
    public void g(final String s, final String s2, final String s3) {
    }
    
    public void f(final String s) {
        this.j("PONG " + s);
    }
    
    public void a(final int n, final String s) {
    }
    
    public void h(final String s, final String s2, final String s3, final String s4) {
    }
    
    public void h(final String s, final String s2, final String s3) {
    }
    
    public void i(final String s, final String s2, final String s3) {
    }
    
    public void g(final String s) {
    }
    
    public void a(final String s, final String s2) {
    }
    
    public final void h(final String s) {
        this.j("PART " + s);
    }
    
    public final void d(final String s, final String s2) {
        this.j("PRIVMSG chanserv :access " + s + " add " + s2 + "!*@* 40");
        this.h(s, s2);
    }
    
    public final void e(final String s, final String s2) {
        this.j("PRIVMSG chanserv :access " + s + " del " + s2 + "!*@* 40");
        this.i(s, s2);
    }
    
    public final void i(final String s) {
        this.j("QUIT :" + s);
    }
    
    public final void i() {
        this.i("");
    }
    
    public final void f(final String s, final String s2) {
        this.g.a("PRIVMSG " + s + " :" + s2);
    }
    
    public final synchronized void j(final String s) {
        if (this.g()) {
            this.a.a(s);
        }
    }
    
    public final void k(final String c) {
        this.c = c;
    }
    
    public final void g(final String s, final String s2) {
        this.j("MODE " + s + " " + s2);
    }
    
    public final void l(final String f) {
        this.f = f;
    }
    
    public final void b(final boolean j) {
        this.j = j;
    }
    
    public final void m(final String k) {
        this.k = k;
    }
    
    public final void h(final String s, final String s2) {
        this.g(s, "+o " + s2);
    }
    
    public final void i(final String s, final String s2) {
        this.g(s, "-o " + s2);
    }
    
    public final void j(final String s, final String s2) {
        this.j("MODE " + s + " -b " + s2);
    }
}
