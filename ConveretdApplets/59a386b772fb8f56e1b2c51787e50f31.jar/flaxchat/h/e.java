// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.h;

import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.StringTokenizer;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import flaxchat.i.c;
import java.util.Hashtable;
import java.net.InetAddress;

public abstract class e implements f
{
    private int a;
    private i b;
    private h c;
    private String d;
    private InetAddress e;
    private String f;
    private int g;
    private String h;
    private flaxchat.f.f i;
    private long j;
    protected Hashtable k;
    protected Hashtable l;
    private boolean m;
    protected boolean n;
    protected boolean o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    int w;
    String x;
    public static boolean y;
    private static String[] z;
    
    public e() {
        final boolean y = flaxchat.h.e.y;
        this.a = 512;
        this.g = -1;
        this.i = new flaxchat.f.f();
        this.k = new Hashtable();
        this.l = new Hashtable();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = flaxchat.h.e.z[52];
        this.q = this.p;
        this.r = flaxchat.h.e.z[52];
        this.s = flaxchat.h.e.z[48];
        this.t = flaxchat.h.e.z[47];
        this.u = this.s;
        this.v = flaxchat.h.e.z[51];
        this.w = -1;
        this.x = String.valueOf(System.getProperty(flaxchat.h.e.z[50])) + " " + System.getProperty(flaxchat.h.e.z[49]);
        if (flaxchat.e.e.c) {
            flaxchat.h.e.y = !y;
        }
    }
    
    public final synchronized void a(final String f, final int g, final String h) throws IOException, a, b {
        final boolean y = flaxchat.h.e.y;
        this.a(flaxchat.i.c.a(flaxchat.h.e.z[34], new String[] { f, String.valueOf(g), this.g() }), false);
        this.f = f;
        this.g = g;
        this.h = h;
        if (this.i()) {
            throw new IOException(flaxchat.h.e.z[28]);
        }
        this.r();
        final Socket socket = new Socket(f, g);
        this.g(flaxchat.h.e.z[31]);
        this.e = socket.getLocalAddress();
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        if (this.o() != null) {
            inputStreamReader = new InputStreamReader(socket.getInputStream(), this.o());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream(), this.o());
        }
        else {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        }
        final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        final BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        if (h != null && !h.equals("")) {
            h.a(this, bufferedWriter, flaxchat.h.e.z[30] + h);
        }
        String s = this.f();
        h.a(this, bufferedWriter, flaxchat.h.e.z[40] + this.h() + flaxchat.h.e.z[37] + this.a());
        h.a(this, bufferedWriter, flaxchat.h.e.z[39] + s);
        this.a(flaxchat.i.c.a(flaxchat.h.e.z[29], new String[] { this.l(), String.valueOf(this.m()), this.g() }), false);
        this.b = new i(this, socket, bufferedReader, bufferedWriter);
        int n = 1;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            this.i(line);
            if (line.startsWith(flaxchat.h.e.z[24])) {
                h.a(this, bufferedWriter, flaxchat.h.e.z[36] + line.substring(5));
            }
            final int index = line.indexOf(" ");
            final int index2 = line.indexOf(" ", index + 1);
            Label_0686: {
                if (index2 >= 0) {
                    final String substring = line.substring(index + 1, index2);
                    if (substring.equals(flaxchat.h.e.z[33])) {
                        if (!y) {
                            break;
                        }
                        flaxchat.e.e.c = !flaxchat.e.e.c;
                    }
                    if (substring.equals(flaxchat.h.e.z[38])) {
                        if (this.m) {
                            ++n;
                            s = String.valueOf(this.f()) + n;
                            h.a(this, bufferedWriter, flaxchat.h.e.z[39] + s);
                            if (!y) {
                                break Label_0686;
                            }
                        }
                        socket.close();
                        this.b = null;
                        throw new b(line);
                    }
                    if (substring.startsWith("5") || substring.startsWith("4")) {
                        socket.close();
                        this.b = null;
                        throw new a(flaxchat.h.e.z[32] + line);
                    }
                }
            }
            this.n(s);
        }
        this.g(flaxchat.h.e.z[35]);
        socket.setSoTimeout(300000);
        this.b.start();
        if (this.c == null) {
            (this.c = new h(this, this.i)).start();
        }
        this.d();
    }
    
    protected void a(final String s, final boolean b) {
    }
    
    public String a() {
        return this.u;
    }
    
    public void a(final String u) {
        this.u = u;
    }
    
    public final synchronized void b() {
        this.c();
    }
    
    public final void b(final String s) {
        this.e(flaxchat.h.e.z[45] + s);
    }
    
    public final void c(final String s) {
        this.e(flaxchat.h.e.z[44] + s);
    }
    
    public final void c() {
        this.d("");
    }
    
    public final void d(final String s) {
        this.e(flaxchat.h.e.z[55] + s);
    }
    
    public final synchronized void e(final String s) {
        if (s.startsWith(flaxchat.h.e.z[43]) || s.startsWith(flaxchat.h.e.z[12])) {
            String s2 = s.substring(4);
            final int index = s2.indexOf(":");
            if (index != -1) {
                s2 = s2.substring(0, index);
            }
            this.s(s2.trim());
        }
        if (this.i()) {
            this.b.a(s);
        }
    }
    
    public final void a(final String s, final String s2) {
        this.i.a(flaxchat.h.e.z[5] + s + flaxchat.h.e.z[6] + s2);
    }
    
    public final void f(final String s) {
        this.e(flaxchat.h.e.z[8] + s);
    }
    
    public void g(final String s) {
        if (this.n) {
            System.out.println(String.valueOf(System.currentTimeMillis()) + " " + s);
        }
    }
    
    public void h(final String s) {
        if (this.o) {
            System.out.println(String.valueOf(System.currentTimeMillis()) + " " + s);
        }
    }
    
    protected void i(final String s) {
        try {
            this.j(s);
        }
        catch (RuntimeException ex) {
            System.out.println(flaxchat.h.e.z[27] + ex.getMessage());
            System.out.println(flaxchat.h.e.z[26] + s);
        }
    }
    
    protected void j(final String s) {
        final boolean y = flaxchat.h.e.y;
        this.g(s);
        if (s.startsWith(flaxchat.h.e.z[24])) {
            this.k(s.substring(5));
            return;
        }
        String s2 = "";
        String substring = "";
        String substring2 = "";
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        final String nextToken = stringTokenizer.nextToken();
        final String nextToken2 = stringTokenizer.nextToken();
        String s3 = null;
        final int index = nextToken.indexOf("!");
        final int index2 = nextToken.indexOf("@");
        Label_0236: {
            if (nextToken.startsWith(":")) {
                if (index > 0 && index2 > 0 && index < index2) {
                    s2 = nextToken.substring(1, index);
                    substring = nextToken.substring(index + 1, index2);
                    substring2 = nextToken.substring(index2 + 1);
                    if (!y) {
                        break Label_0236;
                    }
                }
                if (!stringTokenizer.hasMoreTokens()) {
                    this.l(s);
                    return;
                }
                final String s4 = nextToken2;
                int int1 = -1;
                try {
                    int1 = Integer.parseInt(s4);
                }
                catch (NumberFormatException ex) {}
                if (int1 != -1) {
                    this.a(int1, s.substring(s.indexOf(s4, nextToken.length()) + 4, s.length()));
                    return;
                }
                s2 = nextToken;
                s3 = s4;
            }
        }
        final String upperCase = nextToken2.toUpperCase();
        if (s2.startsWith(":")) {
            s2 = s2.substring(1);
        }
        if (s3 == null) {
            s3 = stringTokenizer.nextToken();
        }
        if (s3.startsWith(":")) {
            s3 = s3.substring(1);
        }
        if (upperCase.equals(flaxchat.h.e.z[14]) && s.indexOf(flaxchat.h.e.z[18]) > 0 && s.endsWith("\u0001")) {
            final String substring3 = s.substring(s.indexOf(flaxchat.h.e.z[18]) + 2, s.length() - 1);
            if (substring3.equals(flaxchat.h.e.z[22])) {
                this.s(s2, substring, substring2, s3);
                return;
            }
            if (substring3.startsWith(flaxchat.h.e.z[13])) {
                this.b(s2, substring, substring2, s3, substring3.substring(7));
                return;
            }
            if (substring3.startsWith(flaxchat.h.e.z[24])) {
                this.p(s2, substring, substring2, s3, substring3.substring(5));
                return;
            }
            if (substring3.equals(flaxchat.h.e.z[21])) {
                this.t(s2, substring, substring2, s3);
                return;
            }
            if (substring3.equals(flaxchat.h.e.z[23])) {
                this.u(s2, substring, substring2, s3);
                return;
            }
            final StringTokenizer stringTokenizer2;
            if ((stringTokenizer2 = new StringTokenizer(substring3)).countTokens() >= 5 && stringTokenizer2.nextToken().equals(flaxchat.h.e.z[25])) {
                this.l(s);
                return;
            }
            this.l(s);
        }
        else {
            if (upperCase.equals(flaxchat.h.e.z[14]) && this.v.indexOf(s3.charAt(0)) >= 0) {
                this.a(s3, s2, substring, substring2, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                return;
            }
            if (upperCase.equals(flaxchat.h.e.z[14])) {
                this.a(s2, substring, substring2, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                return;
            }
            if (upperCase.equals(flaxchat.h.e.z[10])) {
                final String s5 = s3;
                if (this.d(s5, s2) != null) {
                    return;
                }
                this.a(s5, new g("", s2));
                this.b(s5, s2, substring, substring2);
            }
            else {
                if (upperCase.equals(flaxchat.h.e.z[12])) {
                    this.e(s3, s2);
                    if (s2.equals(this.g())) {
                        this.s(s3);
                    }
                    this.c(s3, s2, substring, substring2);
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[16])) {
                    final String s6 = s3;
                    this.f(s2, s6);
                    if (s2.equals(this.g())) {
                        this.n(s6);
                        this.b(s2, s6);
                    }
                    this.d(s2, substring, substring2, s6);
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[9])) {
                    this.c(s2, substring, substring2, s3, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[11])) {
                    Label_0876: {
                        if (s2.equals(this.g())) {
                            this.r();
                            if (!y) {
                                break Label_0876;
                            }
                        }
                        this.r(s2);
                    }
                    this.e(s2, substring, substring2, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[20])) {
                    final String nextToken3 = stringTokenizer.nextToken();
                    if (nextToken3.equals(this.g())) {
                        this.s(s3);
                    }
                    this.e(s3, nextToken3);
                    this.a(s3, s2, substring, substring2, nextToken3, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[19])) {
                    String s7 = s.substring(s.indexOf(s3, 2) + s3.length() + 1);
                    if (s7.startsWith(":")) {
                        s7 = s7.substring(1);
                    }
                    this.d(s3, s2, substring, substring2, s7);
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[17])) {
                    this.a(s3, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2), s2, System.currentTimeMillis(), true);
                    return;
                }
                if (upperCase.equals(flaxchat.h.e.z[15])) {
                    this.o(s3, s2, substring, substring2, s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                    return;
                }
                this.l(s);
            }
        }
    }
    
    protected void d() {
    }
    
    protected void e() {
    }
    
    private final void a(final int w, final String s) {
        final boolean y = flaxchat.h.e.y;
        Label_0569: {
            if (w == 322) {
                final int index = s.indexOf(32);
                final int index2 = s.indexOf(32, index + 1);
                final int index3 = s.indexOf(32, index2 + 1);
                final int index4 = s.indexOf(58);
                final String substring = s.substring(index + 1, index2);
                int int1 = 0;
                try {
                    int1 = Integer.parseInt(s.substring(index2 + 1, index3));
                }
                catch (NumberFormatException ex) {}
                this.a(substring, this.w == 321, int1, s.substring(index4 + 1));
                if (!y) {
                    break Label_0569;
                }
            }
            if (w == 332) {
                final int index5 = s.indexOf(32);
                final int index6 = s.indexOf(32, index5 + 1);
                final int index7 = s.indexOf(58);
                final String substring2 = s.substring(index5 + 1, index6);
                final String substring3 = s.substring(index7 + 1);
                this.l.put(substring2, substring3);
                this.c(substring2, substring3);
                if (!y) {
                    break Label_0569;
                }
            }
            if (w == 333) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s);
                stringTokenizer.nextToken();
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                long n = 0L;
                try {
                    n = Long.parseLong(stringTokenizer.nextToken()) * 1000L;
                }
                catch (NumberFormatException ex2) {}
                final String s2 = this.l.get(nextToken);
                this.l.remove(nextToken);
                this.a(nextToken, s2, nextToken2, n, false);
                if (!y) {
                    break Label_0569;
                }
            }
            if (w == 353) {
                final int index8 = s.indexOf(flaxchat.h.e.z[6]);
                final String substring4 = s.substring(s.lastIndexOf(32, index8 - 1) + 1, index8);
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s.substring(s.indexOf(flaxchat.h.e.z[6]) + 2));
                while (true) {
                    Label_0512: {
                        if (!y) {
                            break Label_0512;
                        }
                        final String nextToken3 = stringTokenizer2.nextToken();
                        String s3 = "";
                        Label_0483: {
                            if (nextToken3.startsWith("@")) {
                                s3 = "@";
                                if (!y) {
                                    break Label_0483;
                                }
                            }
                            if (nextToken3.startsWith("+")) {
                                s3 = "+";
                                if (!y) {
                                    break Label_0483;
                                }
                            }
                            if (nextToken3.startsWith("~")) {
                                s3 = "~";
                                if (!y) {
                                    break Label_0483;
                                }
                            }
                            if (nextToken3.startsWith("&")) {
                                s3 = "&";
                                if (!y) {
                                    break Label_0483;
                                }
                            }
                            if (nextToken3.startsWith("%")) {
                                s3 = "%";
                                if (!y) {
                                    break Label_0483;
                                }
                            }
                            if (nextToken3.startsWith(".")) {
                                s3 = ".";
                            }
                        }
                        this.a(substring4, new g(s3, nextToken3.substring(s3.length())));
                    }
                    if (stringTokenizer2.hasMoreTokens()) {
                        continue;
                    }
                    break;
                }
                if (!y) {
                    break Label_0569;
                }
            }
            if (w == 366) {
                final String substring5 = s.substring(s.indexOf(32) + 1, s.indexOf(flaxchat.h.e.z[6]));
                this.a(substring5, this.q(substring5));
            }
        }
        this.b(this.w = w, s);
    }
    
    protected void b(final int n, final String s) {
    }
    
    protected void a(final String s, final g[] array) {
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void b(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void c(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void b(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void c(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void d(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void b(final String s, final String s2) {
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
    }
    
    protected void e(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void c(final String s, final String s2) {
    }
    
    protected void a(final String s, final String s2, final String s3, final long n, final boolean b) {
    }
    
    protected void a(final String s, final boolean b, final int n, final String s2) {
    }
    
    private final void d(final String s, final String s2, final String s3, final String s4, final String s5) {
        final boolean y = flaxchat.h.e.y;
        if (this.v.indexOf(s.charAt(0)) < 0) {
            this.f(s, s2, s3, s4, s5);
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s5);
        final String[] array = new String[stringTokenizer.countTokens()];
        int n = 0;
        while (true) {
            Label_0065: {
                if (!y) {
                    break Label_0065;
                }
                array[n] = stringTokenizer.nextToken();
                ++n;
            }
            if (stringTokenizer.hasMoreTokens()) {
                continue;
            }
            break;
        }
        int n2 = 32;
        int n3 = 1;
        int n4 = 0;
        while (true) {
            Label_0715: {
                if (!y) {
                    break Label_0715;
                }
                final char char1 = array[0].charAt(n4);
                Label_0712: {
                    if (char1 == '+' || char1 == '-') {
                        n2 = char1;
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'o') {
                        Label_0195: {
                            if (n2 == 43) {
                                this.b(s, 1, array[n3]);
                                this.g(s, s2, s3, s4, array[n3]);
                                if (!y) {
                                    break Label_0195;
                                }
                            }
                            this.b(s, 2, array[n3]);
                            this.h(s, s2, s3, s4, array[n3]);
                        }
                        ++n3;
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'v') {
                        Label_0276: {
                            if (n2 == 43) {
                                this.b(s, 3, array[n3]);
                                this.i(s, s2, s3, s4, array[n3]);
                                if (!y) {
                                    break Label_0276;
                                }
                            }
                            this.b(s, 4, array[n3]);
                            this.j(s, s2, s3, s4, array[n3]);
                        }
                        ++n3;
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'k') {
                        Label_0333: {
                            if (n2 == 43) {
                                this.k(s, s2, s3, s4, array[n3]);
                                if (!y) {
                                    break Label_0333;
                                }
                            }
                            this.l(s, s2, s3, s4, array[n3]);
                        }
                        ++n3;
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'l') {
                        if (n2 == 43) {
                            this.a(s, s2, s3, s4, Integer.parseInt(array[n3]));
                            ++n3;
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.f(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'b') {
                        Label_0445: {
                            if (n2 == 43) {
                                this.m(s, s2, s3, s4, array[n3]);
                                if (!y) {
                                    break Label_0445;
                                }
                            }
                            this.n(s, s2, s3, s4, array[n3]);
                        }
                        ++n3;
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 't') {
                        if (n2 == 43) {
                            this.g(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.h(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'n') {
                        if (n2 == 43) {
                            this.i(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.j(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'i') {
                        if (n2 == 43) {
                            this.k(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.l(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'm') {
                        if (n2 == 43) {
                            this.m(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.n(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 'p') {
                        if (n2 == 43) {
                            this.o(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.p(s, s2, s3, s4);
                        if (!y) {
                            break Label_0712;
                        }
                    }
                    if (char1 == 's') {
                        if (n2 == 43) {
                            this.q(s, s2, s3, s4);
                            if (!y) {
                                break Label_0712;
                            }
                        }
                        this.r(s, s2, s3, s4);
                    }
                }
                ++n4;
            }
            if (n4 >= array[0].length()) {
                this.e(s, s2, s3, s4, s5);
                return;
            }
            continue;
        }
    }
    
    protected void e(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void f(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void g(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void h(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void i(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void j(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void k(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void l(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, final int n) {
    }
    
    protected void f(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void m(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void n(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void g(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void h(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void i(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void j(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void k(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void l(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void m(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void n(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void o(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void p(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void q(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void r(final String s, final String s2, final String s3, final String s4) {
    }
    
    protected void o(final String s, final String s2, final String s3, final String s4, final String s5) {
    }
    
    protected void s(final String s, final String s2, final String s3, final String s4) {
        this.e(flaxchat.h.e.z[42] + s + flaxchat.h.e.z[41] + this.s + " " + this.x + "\u0001");
    }
    
    protected void p(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.e(flaxchat.h.e.z[42] + s + flaxchat.h.e.z[53] + s5 + "\u0001");
    }
    
    protected void k(final String s) {
        this.e(flaxchat.h.e.z[36] + s);
    }
    
    protected void t(final String s, final String s2, final String s3, final String s4) {
        this.e(flaxchat.h.e.z[42] + s + flaxchat.h.e.z[46] + new Date().toString() + "\u0001");
    }
    
    protected void u(final String s, final String s2, final String s3, final String s4) {
        this.e(flaxchat.h.e.z[42] + s + flaxchat.h.e.z[54] + this.t + "\u0001");
    }
    
    protected void l(final String s) {
    }
    
    public final void a(final boolean n) {
        this.n = n;
    }
    
    public final void b(final boolean o) {
        this.o = o;
    }
    
    public final void m(final String p) {
        this.p = p;
    }
    
    public final void n(final String q) {
        this.q = q;
    }
    
    public final void o(final String r) {
        this.r = r;
    }
    
    public final String f() {
        return this.p;
    }
    
    public String g() {
        return this.q;
    }
    
    public final String h() {
        return this.r;
    }
    
    public final synchronized boolean i() {
        return this.b != null && this.b.a();
    }
    
    public final long j() {
        return this.j;
    }
    
    public final void a(final int a) {
        this.a = a;
    }
    
    public int k() {
        return this.a;
    }
    
    public final String l() {
        return this.f;
    }
    
    public final int m() {
        return this.g;
    }
    
    public final String n() {
        return this.h;
    }
    
    public void p(final String d) throws UnsupportedEncodingException {
        "".getBytes(d);
        this.d = d;
    }
    
    public String o() {
        return this.d;
    }
    
    public boolean equals(final Object o) {
        return o instanceof e && o == this;
    }
    
    public int hashCode() {
        return super.hashCode();
    }
    
    public String toString() {
        return flaxchat.h.e.z[1] + this.s + "}" + flaxchat.h.e.z[3] + this.i() + "}" + flaxchat.h.e.z[4] + this.f + "}" + flaxchat.h.e.z[2] + this.g + "}" + flaxchat.h.e.z[0] + this.h + "}";
    }
    
    public final g[] q(String lowerCase) {
        final boolean y = flaxchat.h.e.y;
        lowerCase = lowerCase.toLowerCase();
        g[] array = new g[0];
        synchronized (this.k) {
            final Hashtable<Object, g> hashtable = this.k.get(lowerCase);
            if (hashtable != null) {
                array = new g[hashtable.size()];
                final Enumeration<g> elements = hashtable.elements();
                int n = 0;
                while (true) {
                    Label_0085: {
                        if (!y) {
                            break Label_0085;
                        }
                        array[n] = elements.nextElement();
                        ++n;
                    }
                    if (n < array.length) {
                        continue;
                    }
                    break;
                }
            }
        }
        // monitorexit(this.k)
        return array;
    }
    
    public final String[] p() {
        final boolean y = flaxchat.h.e.y;
        String[] array = new String[0];
        synchronized (this.k) {
            array = new String[this.k.size()];
            final Enumeration<String> keys = this.k.keys();
            int n = 0;
            while (true) {
                Label_0062: {
                    if (!y) {
                        break Label_0062;
                    }
                    array[n] = keys.nextElement();
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
        }
        // monitorexit(this.k)
        return array;
    }
    
    public synchronized void q() {
        this.c.a();
        this.b.b();
    }
    
    private final void a(String lowerCase, final g g) {
        lowerCase = lowerCase.toLowerCase();
        synchronized (this.k) {
            Hashtable<g, g> hashtable = this.k.get(lowerCase);
            if (hashtable == null) {
                hashtable = new Hashtable<g, g>();
                this.k.put(lowerCase, hashtable);
            }
            hashtable.put(g, g);
        }
        // monitorexit(this.k)
    }
    
    public final g d(String lowerCase, final String s) {
        lowerCase = lowerCase.toLowerCase();
        final Hashtable<Object, g> hashtable = this.k.get(lowerCase);
        if (hashtable == null) {
            return null;
        }
        return hashtable.get(new g("", s));
    }
    
    private final g e(String lowerCase, final String s) {
        lowerCase = lowerCase.toLowerCase();
        final g g = new g("", s);
        synchronized (this.k) {
            final Hashtable<Object, g> hashtable = this.k.get(lowerCase);
            if (hashtable != null) {
                // monitorexit(this.k)
                return hashtable.remove(g);
            }
        }
        // monitorexit(this.k)
        return null;
    }
    
    private final void r(final String s) {
        final boolean y = flaxchat.h.e.y;
        synchronized (this.k) {
            final Enumeration keys = this.k.keys();
            while (true) {
                Label_0046: {
                    if (!y) {
                        break Label_0046;
                    }
                    this.e(keys.nextElement(), s);
                }
                if (!keys.hasMoreElements()) {
                    // monitorexit(this.k)
                    return;
                }
                continue;
            }
        }
    }
    
    private final void f(final String s, final String s2) {
        final boolean y = flaxchat.h.e.y;
        synchronized (this.k) {
            final Enumeration keys = this.k.keys();
            while (true) {
                Label_0075: {
                    if (!y) {
                        break Label_0075;
                    }
                    final String s3 = keys.nextElement();
                    final g e = this.e(s3, s);
                    if (e != null) {
                        this.a(s3, new g(e.a(), s2));
                    }
                }
                if (!keys.hasMoreElements()) {
                    // monitorexit(this.k)
                    return;
                }
                continue;
            }
        }
    }
    
    private final void s(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        synchronized (this.k) {
            this.k.remove(lowerCase);
        }
        // monitorexit(this.k)
    }
    
    private final void r() {
        synchronized (this.k) {
        }
        // monitorexit(this.k = new Hashtable())
    }
    
    private final void b(String lowerCase, final int n, final String s) {
        final boolean y = flaxchat.h.e.y;
        lowerCase = lowerCase.toLowerCase();
        synchronized (this.k) {
            final Hashtable<Object, g> hashtable = this.k.get(lowerCase);
            if (hashtable == null) {
                // monitorexit(this.k)
                return;
            }
            final Enumeration<g> elements = hashtable.elements();
            while (true) {
                Label_0090: {
                    if (!y) {
                        break Label_0090;
                    }
                    final g g = elements.nextElement();
                    if (g.d().equalsIgnoreCase(s)) {
                        this.a(g, n, s);
                    }
                }
                if (!elements.hasMoreElements()) {
                    // monitorexit(this.k)
                    return;
                }
                continue;
            }
        }
    }
    
    private g a(final g g, final int n, final String s) {
        final boolean y = flaxchat.h.e.y;
        if (n == 1) {
            if (g.c()) {
                g.b(flaxchat.h.e.z[7]);
                if (!y) {
                    return g;
                }
            }
            g.b("@");
            if (!y) {
                return g;
            }
        }
        if (n == 2) {
            if (g.c()) {
                g.b("+");
                if (!y) {
                    return g;
                }
            }
            g.b("");
            if (!y) {
                return g;
            }
        }
        if (n == 3) {
            if (g.b()) {
                g.b(flaxchat.h.e.z[7]);
                if (!y) {
                    return g;
                }
            }
            g.b("+");
            if (!y) {
                return g;
            }
        }
        if (n == 4) {
            if (g.b()) {
                g.b("@");
                if (!y) {
                    return g;
                }
            }
            g.b("");
        }
        return g;
    }
    
    static {
        e.z = new String[] { z(z("yh\u0007ih.W\u0014~`")), z(z("\u000f]\u0014ir6V\u001d")), z(z("yh\tho\"")), z(z("y{\ttu<[\u0012\u007f\u007f\"")), z(z("yk\u0003hm<J\u001d")), z(z("\tj/LV\n\u007fF")), z(z("y\u0002")), z(z("\u0019\u0013")), z(z("\u0017q%QH\u001cj0:R\u001d}(NR\u001faF")), z(z("\u0017w2SX\u001c")), z(z("\u0013w/T")), z(z("\bm/N")), z(z("\ty4N")), z(z("\u0018{2ST\u0017\u0018")), z(z("\tj/LV\n\u007f")), z(z("\u0010v0SO\u001c")), z(z("\u0017q%Q")), z(z("\rw6SX")), z(z("c9")), z(z("\u0014w\"_")), z(z("\u0012q%Q")), z(z("\rq+_")), z(z("\u000f}4IR\u0016v")), z(z("\u001fq(]^\u000b")), z(z("\tq(];")), z(z("\u001d{%")), z(z("z\u0018#HI\u0016jFYU\u001d\u0018\\:")), z(z("z\u0018#HI\u0016jFWH\u001e\u0018\\:")), z(z("\rP\u0003:]5Y\u001eYs8LFshyY\nh~8\\\u001f:x6V\b\u007fx-]\u0002:o6\u0018\u0007t;\u0010j%:h<J\u0010\u007fiw\u0018F^r*[\ttu<[\u0012:}0J\u0015n5")), z(z("4K\u0001(")), z(z("\ty5I;")), z(z("s\u0012L:X6V\b\u007fx-]\u0002:o6\u0018\u0015\u007fi/]\u00144")), z(z("\u001aW\u0013v\u007fyV\tn;5W\u0001:r7L\t:o1]FSI\u001a\u0018\u0015\u007fi/]\u0014 ;")), z(z("i\bR")), z(z("4K\u0001+")), z(z("s\u0012L:W6_\u0001\u007f\u007fyW\bntyK\u0003hm<JH")), z(z("\tw(];")), z(z("y\tT-5i\u0016V4*y\u0012F ;")), z(z("m\u000bU")), z(z("\u0017q%Q;")), z(z("\fk#H;")), z(z("y\u0002gL^\u000bk/UUy")), z(z("\u0017w2SX\u001c\u0018")), z(z(")Y\u0014n")), z(z("\ty4N;")), z(z("\u0013w/T;")), z(z("y\u0002gNR\u0014}F")), z(z("\u001fT\u0007bX1Y\u0012:h6P\u0004\u007foyY\u0016jw<L\u000f:p,T\n{u0A\thn4\u0016F\\w8@%rz-\u0018W4#\u0006\bT*")), z(z("\u001fT\u0007bX1Y\u0012:*w\u00009*)i")), z(z("3Y\u0010{5/]\u0014ir6V")), z(z("3Y\u0010{5/]\b~t+")), z(z("z\u001eM;")), z(z("\u001fT\u0007bX1Y\u0012")), z(z("y\u0002gJR\u0017\u007fF")), z(z("y\u0002g\\R\u0017\u007f#H;")), z(z("\bm/N;c")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u001b';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = 'Y';
                    break;
                }
                case 1: {
                    c2 = '8';
                    break;
                }
                case 2: {
                    c2 = 'f';
                    break;
                }
                case 3: {
                    c2 = '\u001a';
                    break;
                }
                default: {
                    c2 = '\u001b';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
