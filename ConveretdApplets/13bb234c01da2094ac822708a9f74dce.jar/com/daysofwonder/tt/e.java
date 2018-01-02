// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import org.slf4j.LoggerFactory;
import java.util.Enumeration;
import com.daysofwonder.util.G;
import com.daysofwonder.util.t;
import com.daysofwonder.util.w;
import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Rectangle;
import java.util.Vector;
import com.daysofwonder.util.UIProperties;
import java.util.Hashtable;
import org.slf4j.Logger;
import com.daysofwonder.a.j;

public class e implements j
{
    private static final Logger e;
    protected Hashtable a;
    protected Hashtable b;
    protected int c;
    private String f;
    private UIProperties g;
    private Vector h;
    private Vector i;
    private Vector j;
    private Rectangle k;
    private Rectangle l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int A;
    private boolean B;
    private boolean C;
    private boolean D;
    private Hashtable E;
    private int F;
    private int G;
    private int H;
    private int I;
    private int J;
    private i[] K;
    public static final String[] d;
    
    public e() {
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = 0;
        this.h = new Vector();
        this.i = new Vector();
        this.j = new Vector();
        this.p = 45;
        this.w = 1;
        this.x = 2;
        this.y = 0;
        this.z = 3;
        this.A = 3;
        this.B = true;
        this.C = true;
        this.D = true;
        this.E = new Hashtable();
        this.F = 5;
        this.G = 3;
        this.I = 3;
        this.J = 0;
    }
    
    public f[] a() {
        final f[] array = new f[this.j.size()];
        for (int i = 0; i < this.j.size(); ++i) {
            array[i] = (f)this.j.elementAt(i);
        }
        return array;
    }
    
    public void a(final InputStream inputStream) {
        int n = -1;
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = null;
        int n2 = 0;
        try {
            while ((s = bufferedReader.readLine()) != null) {
                s = s.trim();
                if (s.length() == 0) {
                    continue;
                }
                if (s.startsWith(";")) {
                    continue;
                }
                if (n == -1 && s.equalsIgnoreCase("[global]")) {
                    com.daysofwonder.tt.e.e.a("going to state globals");
                    n = 0;
                }
                else if (n == 0 && s.equalsIgnoreCase("[city]")) {
                    com.daysofwonder.tt.e.e.a("going to state city");
                    n = 1;
                }
                else if (n == 1 && s.equalsIgnoreCase("[route]")) {
                    com.daysofwonder.tt.e.e.a("going to state route");
                    n = 2;
                }
                else if (n == 2 && s.equalsIgnoreCase("[ticket]")) {
                    com.daysofwonder.tt.e.e.a("going to state ticket");
                    n = 3;
                }
                else {
                    if (n == 0) {
                        if (s.startsWith("guid")) {
                            final StringTokenizer stringTokenizer = new StringTokenizer(s, "=");
                            stringTokenizer.nextToken();
                            this.f = stringTokenizer.nextToken();
                        }
                        else if (s.startsWith("deck")) {
                            final StringTokenizer stringTokenizer2 = new StringTokenizer(s, "=");
                            stringTokenizer2.nextToken();
                            this.b(stringTokenizer2.nextToken());
                        }
                        else if (s.startsWith("largeboard")) {
                            final StringTokenizer stringTokenizer3 = new StringTokenizer(s, "=");
                            stringTokenizer3.nextToken();
                            this.k = com.daysofwonder.util.w.a(stringTokenizer3.nextToken());
                        }
                        else if (s.startsWith("smallboard")) {
                            final StringTokenizer stringTokenizer4 = new StringTokenizer(s, "=");
                            stringTokenizer4.nextToken();
                            this.l = com.daysofwonder.util.w.a(stringTokenizer4.nextToken());
                        }
                        else if (s.startsWith("medboard")) {
                            final StringTokenizer stringTokenizer5 = new StringTokenizer(s, "=");
                            stringTokenizer5.nextToken();
                            this.u = true;
                            this.l = com.daysofwonder.util.w.a(stringTokenizer5.nextToken());
                        }
                        else if (s.startsWith("trains")) {
                            final StringTokenizer stringTokenizer6 = new StringTokenizer(s, "=");
                            stringTokenizer6.nextToken();
                            this.p = Integer.parseInt(stringTokenizer6.nextToken());
                        }
                        else if (s.startsWith("stations")) {
                            final StringTokenizer stringTokenizer7 = new StringTokenizer(s, "=");
                            stringTokenizer7.nextToken();
                            com.daysofwonder.util.t.a("Map has station");
                            this.q = Integer.parseInt(stringTokenizer7.nextToken());
                        }
                        else if (s.startsWith("bonus")) {
                            final StringTokenizer stringTokenizer8 = new StringTokenizer(s, "=");
                            stringTokenizer8.nextToken();
                            com.daysofwonder.util.t.a("Map has bonus definition");
                            if (stringTokenizer8.hasMoreTokens()) {
                                final String nextToken = stringTokenizer8.nextToken();
                                if (nextToken != null && nextToken.length() > 0) {
                                    final StringTokenizer stringTokenizer9 = new StringTokenizer(nextToken, ",");
                                    while (stringTokenizer9.hasMoreTokens()) {
                                        final String nextToken2 = stringTokenizer9.nextToken();
                                        if (nextToken2.indexOf(58) > 0) {
                                            this.E.put(nextToken2.substring(0, nextToken2.indexOf(58)), Integer.parseInt(nextToken2.substring(nextToken2.indexOf(58) + 1)));
                                        }
                                        else {
                                            com.daysofwonder.util.t.a("Skipping malformed bonus token: " + nextToken2);
                                        }
                                    }
                                }
                            }
                        }
                        else if (s.startsWith("ferry")) {
                            final StringTokenizer stringTokenizer10 = new StringTokenizer(s, "=");
                            stringTokenizer10.nextToken();
                            this.r = stringTokenizer10.nextToken().equalsIgnoreCase("true");
                        }
                        else if (s.startsWith("recyclestarttickets")) {
                            final StringTokenizer stringTokenizer11 = new StringTokenizer(s, "=");
                            stringTokenizer11.nextToken();
                            this.B = stringTokenizer11.nextToken().equalsIgnoreCase("true");
                        }
                        else if (s.startsWith("recycletickets")) {
                            final StringTokenizer stringTokenizer12 = new StringTokenizer(s, "=");
                            stringTokenizer12.nextToken();
                            this.C = stringTokenizer12.nextToken().equalsIgnoreCase("true");
                        }
                        else if (s.startsWith("ticketminstart")) {
                            final StringTokenizer stringTokenizer13 = new StringTokenizer(s, "=");
                            stringTokenizer13.nextToken();
                            this.x = Integer.parseInt(stringTokenizer13.nextToken());
                            com.daysofwonder.util.t.a("Ticket Min Start: " + this.x);
                        }
                        else if (s.startsWith("ticketmin")) {
                            final StringTokenizer stringTokenizer14 = new StringTokenizer(s, "=");
                            stringTokenizer14.nextToken();
                            this.w = Integer.parseInt(stringTokenizer14.nextToken());
                        }
                        else if (s.startsWith("ticketstart")) {
                            final StringTokenizer stringTokenizer15 = new StringTokenizer(s, "=");
                            stringTokenizer15.nextToken();
                            final StringTokenizer stringTokenizer16 = new StringTokenizer(stringTokenizer15.nextToken(), ",");
                            this.y = Integer.parseInt(stringTokenizer16.nextToken());
                            this.z = Integer.parseInt(stringTokenizer16.nextToken());
                        }
                        else if (s.startsWith("ticketstring")) {
                            final StringTokenizer stringTokenizer17 = new StringTokenizer(s, "=");
                            stringTokenizer17.nextToken();
                            this.D = stringTokenizer17.nextToken().equalsIgnoreCase("top");
                        }
                        else if (s.startsWith("ticket") && !s.startsWith("tickets")) {
                            final StringTokenizer stringTokenizer18 = new StringTokenizer(s, "=");
                            stringTokenizer18.nextToken();
                            this.A = Integer.parseInt(stringTokenizer18.nextToken());
                        }
                        else if (s.startsWith("doubleroutes")) {
                            final StringTokenizer stringTokenizer19 = new StringTokenizer(s, "=");
                            stringTokenizer19.nextToken();
                            this.G = Integer.parseInt(stringTokenizer19.nextToken());
                        }
                        else if (s.startsWith("maxplayers")) {
                            final StringTokenizer stringTokenizer20 = new StringTokenizer(s, "=");
                            stringTokenizer20.nextToken();
                            this.F = Integer.parseInt(stringTokenizer20.nextToken());
                        }
                        else if (s.startsWith("locodiscard")) {
                            final StringTokenizer stringTokenizer21 = new StringTokenizer(s, "=");
                            stringTokenizer21.nextToken();
                            this.I = Integer.parseInt(stringTokenizer21.nextToken());
                        }
                        else if (s.startsWith("tunneldiscard")) {
                            final StringTokenizer stringTokenizer22 = new StringTokenizer(s, "=");
                            stringTokenizer22.nextToken();
                            this.J = Integer.parseInt(stringTokenizer22.nextToken());
                        }
                        else if (s.startsWith("tunnel")) {
                            final StringTokenizer stringTokenizer23 = new StringTokenizer(s, "=");
                            stringTokenizer23.nextToken();
                            this.s = stringTokenizer23.nextToken().equalsIgnoreCase("true");
                        }
                    }
                    else if (n == 1) {
                        final c c = new c(this);
                        if (c.a(s)) {
                            this.h.addElement(c);
                            c.b(this.n++);
                        }
                        else {
                            com.daysofwonder.tt.e.e.c("TTMap.load: [" + n2 + "] - Invalid city");
                        }
                    }
                    else if (n == 2) {
                        final o o = new o(this);
                        if (o.a(s)) {
                            this.i.addElement(o);
                            o.a(this.m++);
                        }
                        else {
                            com.daysofwonder.tt.e.e.c("TTMap.load: [" + n2 + "] - Invalid route");
                        }
                    }
                    else if (n == 3) {
                        final f f = new f(this);
                        if (f.a(s)) {
                            this.j.addElement(f);
                            f.b(this.o++);
                            if (f.d()) {
                                com.daysofwonder.util.t.a("TTMap: has long tickets");
                                ++this.H;
                                this.t = true;
                            }
                        }
                        else {
                            com.daysofwonder.tt.e.e.c("TTMap.load: [" + n2 + "] - Invalid ticket");
                        }
                    }
                    ++n2;
                }
            }
            for (int i = 0; i < this.i.size(); ++i) {
                final o o2 = this.i.elementAt(i);
                for (int j = i + 1; j < this.i.size(); ++j) {
                    final o o3 = this.i.elementAt(j);
                    if (o2.a((Object)o3) == 0) {
                        o2.a(o3);
                        o3.a(o2);
                    }
                }
            }
            if (n != 3) {
                com.daysofwonder.tt.e.e.c("TTMap.load: corrupt Map description: " + n);
            }
        }
        catch (Exception ex) {
            com.daysofwonder.tt.e.e.a("Error while loading map at line: " + n2, ex);
            if (s != null) {
                com.daysofwonder.tt.e.e.d("cullprit: " + s);
            }
        }
    }
    
    public boolean b() {
        return this.D;
    }
    
    public boolean c() {
        return this.q > 0;
    }
    
    public int d() {
        return this.G;
    }
    
    public int e() {
        return this.w;
    }
    
    public int f() {
        return this.x;
    }
    
    public boolean g() {
        return this.u;
    }
    
    public Rectangle a(final int n) {
        return (n == 0) ? this.k : this.l;
    }
    
    public c a(final String s) {
        for (int i = 0; i < this.h.size(); ++i) {
            final c c = this.h.elementAt(i);
            if (c.g().equals(s)) {
                return c;
            }
        }
        return null;
    }
    
    public int h() {
        return this.h.size();
    }
    
    public o b(final int n) {
        return this.i.elementAt(n);
    }
    
    public G a(final G g) {
        final G g2 = new G(g.a());
        for (int i = 0; i < g.a(); ++i) {
            g2.c(this.h.elementAt((int)g.b(i)));
        }
        return g2;
    }
    
    public c a(final c c) {
        return this.h.elementAt(c.f());
    }
    
    public o a(final o o) {
        return this.i.elementAt(o.g());
    }
    
    public G b(final G g) {
        final G g2 = new G(g.a());
        for (int i = 0; i < g.a(); ++i) {
            g2.c(this.i.elementAt((int)g.b(i)));
        }
        return g2;
    }
    
    public G c(final G g) {
        final G g2 = new G(g.a());
        for (int i = 0; i < g.a(); ++i) {
            g2.c(this.j.elementAt((int)g.b(i)));
        }
        return g2;
    }
    
    public G a(final int[] array) {
        final G g = new G(array.length);
        for (int i = 0; i < array.length; ++i) {
            g.c(this.j.elementAt(array[i]));
        }
        return g;
    }
    
    public int a(final f f, final G g) {
        return this.a(f, g, "");
    }
    
    public int a(final f f, final G g, final String s) {
        if (f.c()) {
            int n = -1;
            com.daysofwonder.tt.e.e.b(s + "Multidest: " + f);
            for (int i = 0; i < f.e(); ++i) {
                com.daysofwonder.tt.e.e.b(s + "dest: " + f.f() + " to " + f.a(i));
                if (this.a(f.a(i), f.f(), null, new Vector(), g)) {
                    com.daysofwonder.tt.e.e.b(s + "dest: " + f.f() + " to " + f.a(i) + " is done");
                    final int a = f.a(f.f(), f.a(i));
                    com.daysofwonder.tt.e.e.b(s + "dest: " + f.f() + " to " + f.a(i) + " is done for " + a + " / " + n);
                    if (a > n) {
                        n = a;
                    }
                }
            }
            if (n != -1) {
                com.daysofwonder.util.t.a(s + "dest: " + f + " found: " + n);
                return n;
            }
            com.daysofwonder.util.t.a(s + "dest: " + f + " not done");
            return -f.b();
        }
        else {
            if (this.a(f.g(), f.f(), null, new Vector(), g)) {
                return f.a();
            }
            return -f.a();
        }
    }
    
    public boolean a(final c c, final c c2, final o o, final Vector vector, final G g) {
        com.daysofwonder.tt.e.e.a("Analyzing city " + c2 + " coming from " + o);
        vector.addElement(c2);
        final G g2 = new G();
        for (int i = 0; i < g.a(); ++i) {
            final o o2 = (o)g.b(i);
            com.daysofwonder.tt.e.e.a("is route  candidate :" + o2);
            if (o2 != o && o2.a(c2)) {
                g2.c(o2);
            }
        }
        if (g2.a() == 0) {
            com.daysofwonder.tt.e.e.a("Sorry no candidate ending at " + c2);
            return false;
        }
        for (int j = 0; j < g2.a(); ++j) {
            final o o3 = (o)g2.b(j);
            com.daysofwonder.tt.e.e.a("-->Analysing route: " + o3);
            final c c3 = (o3.e() == c2) ? o3.f() : o3.e();
            if (c3 == c) {
                com.daysofwonder.tt.e.e.a("-->Found end at " + c3);
                return true;
            }
            if (vector.contains(c3)) {
                com.daysofwonder.tt.e.e.a("-->already did it " + c3);
            }
            else if (!c3.b()) {
                com.daysofwonder.tt.e.e.a("-->jump to analyse: " + c3);
                final boolean a = this.a(c, c3, o3, vector, g);
                if (a) {
                    return a;
                }
            }
        }
        com.daysofwonder.tt.e.e.a("all candidate finished for " + c2);
        return false;
    }
    
    public UIProperties i() {
        return this.g;
    }
    
    public void a(final UIProperties g) {
        this.g = g;
    }
    
    public Vector j() {
        return this.i;
    }
    
    public Enumeration k() {
        return this.h.elements();
    }
    
    public boolean a(final o o, final int n, final n n2) {
        boolean b = false;
        final int d = o.d();
        final int n3 = o.h() ? o.i() : 0;
        final int n4 = this.l() ? 10 : 9;
        int n5 = 1;
        int n6 = 0;
        int c = n4;
        if (n == n4) {
            b = true;
            if (o.c() == 9) {
                c = -1;
            }
            else {
                c = o.c();
            }
        }
        final boolean b2 = !this.l() || (o.j() && this.l());
        for (int i = 0; i < n2.d(); ++i) {
            if (((i)n2.a(i)).b() == n) {
                ++n5;
            }
        }
        for (int j = 0; j < n2.d(); ++j) {
            if (((i)n2.a(j)).b() == c) {
                ++n6;
            }
        }
        if (o.h()) {
            com.daysofwonder.tt.e.e.b("Ferry:" + o + " nbneed " + n3 + " p: " + n5 + " f " + n6);
            com.daysofwonder.tt.e.e.b("PrimaryisLoco: " + b + " nbcards " + d);
            if (n3 > 0 && n5 + n6 >= d && ((!b && n6 >= n3) || (b && n5 >= n3))) {
                int n7 = n3;
                int n8 = d - n7;
                final int n9 = b ? n6 : n5;
                final int n10 = b ? n5 : n6;
                if (n9 < n8) {
                    n7 += n8 - n9;
                    n8 = n9;
                }
                if (n3 == d && n10 >= n3) {
                    return n9 == 0;
                }
                if (n9 >= n8 && n10 >= n7) {
                    return true;
                }
            }
        }
        else {
            final int n11 = (d <= n5) ? d : n5;
            final int n12 = (d <= n5) ? 0 : (d - n5);
            if (!o.j() && this.l()) {
                if (b) {
                    n5 = 0;
                }
                else {
                    n6 = 0;
                }
            }
            if (n5 >= n11 && n6 >= n12) {
                return true;
            }
            if (c == -1 && o.j() && this.l()) {
                final int[] array = new int[11];
                this.a(array, n2);
                for (int k = 0; k < array.length; ++k) {
                    if (array[k] != n4 && array[k] >= n12) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void a(final int[] array, final n n) {
        for (int i = 0; i < n.d(); ++i) {
            final int b = ((i)n.a(i)).b();
            ++array[b];
        }
    }
    
    public void b(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        this.c = Integer.parseInt(stringTokenizer.nextToken());
        final G g = new G();
        final G g2 = new G();
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            g.c((Object)this.c(nextToken));
            g2.c((Object)int1);
            n += int1;
        }
        this.K = new i[n];
        int i = 0;
        int n2 = 0;
        while (i < g.a()) {
            final int intValue = (int)g.b(i);
            final int intValue2 = (int)g2.b(i);
            if (intValue == 10) {
                this.v = true;
            }
            for (int j = 0; j < intValue2; ++j) {
                this.K[n2++] = com.daysofwonder.tt.i.a(intValue);
            }
            ++i;
        }
        this.m();
    }
    
    public int c(final String s) {
        for (int i = 0; i < com.daysofwonder.tt.e.d.length; ++i) {
            if (s.equalsIgnoreCase(com.daysofwonder.tt.e.d[i])) {
                return i + 1;
            }
        }
        return -1;
    }
    
    public boolean l() {
        return this.v;
    }
    
    private void m() {
        int c = this.c;
        for (int i = 0; i < this.K.length; ++i) {
            this.K[i].a(this);
            this.K[i].c(c++);
        }
    }
    
    public synchronized void a(final com.daysofwonder.a.o o, final int n) {
        final Integer value = n;
        this.a.put(o, value);
        this.b.put(value, o);
    }
    
    public synchronized int a(final com.daysofwonder.a.o o) {
        return this.a.get(o);
    }
    
    public synchronized com.daysofwonder.a.o c(final int n) {
        if (n == -1) {
            return null;
        }
        return this.b.get(n);
    }
    
    public G d(final G g) {
        if (g == null) {
            return null;
        }
        for (int i = 0; i < g.a(); ++i) {
            g.a(i, this.b.get(g.b(i)));
        }
        return g;
    }
    
    static {
        e = LoggerFactory.a(e.class);
        d = new String[] { "purple", "white", "blue", "yellow", "brown", "black", "red", "green", "loco", "tunnel" };
    }
}
