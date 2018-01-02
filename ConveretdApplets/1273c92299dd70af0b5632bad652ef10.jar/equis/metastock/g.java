// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

import java.awt.Color;
import java.io.IOException;
import java.util.StringTokenizer;
import java.text.NumberFormat;
import java.util.Locale;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.Component;
import java.util.Date;

public class g
{
    public String a;
    public String b;
    public int[] c;
    public int[] d;
    public int[] e;
    public int[] f;
    public String[] g;
    public h h;
    public h i;
    public h j;
    public h k;
    public h l;
    public int m;
    public int n;
    private char o;
    public static Date p;
    public static Date q;
    
    g(final int n) {
        this.c = new int[n];
        this.d = new int[n];
        this.e = new int[n];
        this.f = new int[n];
        this.g = new String[n];
        this.h = new h(n);
        this.i = new h(n);
        this.j = new h(n);
        this.k = new h(n);
        this.l = new h(n);
        this.a();
    }
    
    public void a() {
        this.b = "";
        this.a = "";
        this.o = '\0';
        this.h.a();
        this.i.a();
        this.j.a();
        this.k.a();
        this.l.a();
        this.m = -1;
        this.n = -1;
    }
    
    public long a(final int n) {
        return (1900 + this.c[n]) * 10000 + this.d[n] * 100 + this.e[n];
    }
    
    public void a(final Date p) {
        equis.metastock.g.p = p;
    }
    
    public void b(final Date q) {
        equis.metastock.g.q = q;
    }
    
    public boolean b() {
        return this.o == MS4Java.bf[28].charAt(0);
    }
    
    public boolean c() {
        return this.o == MS4Java.bf[27].charAt(0);
    }
    
    public boolean d() {
        return this.o == MS4Java.bf[26].charAt(0);
    }
    
    public boolean e() {
        return this.o == MS4Java.bf[25].charAt(0);
    }
    
    public char f() {
        return this.o;
    }
    
    private String h() {
        String s;
        if (equis.metastock.g.p != null) {
            s = String.valueOf(equis.metastock.g.p.getMonth() + 1) + "-" + equis.metastock.g.p.getDate() + "-" + (1900 + equis.metastock.g.p.getYear());
            MS4Java.a("Overridden Start Date is " + s);
        }
        else {
            int n;
            if (this.c()) {
                n = 2;
            }
            else if (this.b()) {
                n = 5;
            }
            else {
                n = 1;
            }
            if (n > 0) {
                final Date date = new Date();
                final Date date2 = new Date(date.getYear() - n, date.getMonth(), date.getDate());
                s = String.valueOf(date2.getMonth() + 1) + "-" + date2.getDate() + "-" + (1900 + date2.getYear());
            }
            else {
                s = "Error: m_cPeriodicity uninitialized?";
            }
        }
        MS4Java.a("Start date is " + s);
        return s;
    }
    
    private String i() {
        String s;
        if (equis.metastock.g.q != null) {
            s = String.valueOf(equis.metastock.g.q.getMonth() + 1) + "-" + equis.metastock.g.q.getDate() + "-" + (1900 + equis.metastock.g.q.getYear());
            MS4Java.a("Overridden End Date is " + s);
        }
        else {
            final Date date = new Date();
            s = String.valueOf(date.getMonth() + 1) + "-" + date.getDate() + "-" + (1900 + date.getYear());
        }
        MS4Java.a("End date is " + s);
        return s;
    }
    
    public void a(final String a, final char o) throws Exception {
        boolean b = true;
        int i = 0;
        String s = "";
        final StringBuffer sb = new StringBuffer(40);
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int n7 = -1;
        int n8 = 0;
        MS4Java.a(MS4Java.k).setCursor(3);
        this.a();
        sb.append(MS4Java.bf[29]);
        sb.append(" \"");
        sb.append(a);
        sb.append("\" ");
        sb.append(MS4Java.bf[30]);
        sb.append("...");
        MS4Java.e.a(sb.toString());
        sb.setLength(0);
        this.o = o;
        String s2;
        if (this.b()) {
            s2 = "months";
        }
        else if (this.c()) {
            s2 = "weeks";
        }
        else if (this.d()) {
            s2 = "days";
        }
        else {
            s2 = "";
        }
        if (a.equals("CANNED!") && MS4Java.ad) {
            for (i = 0; i < 200; ++i) {
                this.h.c[i] = i - (int)(i * 0.05);
                this.i.c[i] = i + (int)(i * 0.1);
                this.j.c[i] = i - (int)(i * 0.1);
                this.k.c[i] = i;
                this.l.c[i] = i;
            }
            b = false;
            MS4Java.e.a("");
        }
        else {
            String s3;
            if (this.e()) {
                s3 = "Intraday:";
                sb.append(MS4Java.h().toString());
                sb.append("?SYMBOL=");
                sb.append(URLEncoder.encode(a));
            }
            else {
                s3 = "History:";
                sb.append(MS4Java.g().toString());
                sb.append("?SYMBOL=");
                sb.append(URLEncoder.encode(a));
                sb.append("&period=");
                sb.append(s2);
                sb.append("&ENDDATE=");
                sb.append(this.i());
                sb.append("&STARTDATE=");
                sb.append(this.h());
            }
            InputStream openStream;
            try {
                final URL url = new URL(sb.toString());
                sb.setLength(0);
                openStream = url.openStream();
            }
            catch (Exception ex) {
                sb.append(MS4Java.bf[45]);
                sb.append("\n");
                sb.append("(");
                sb.append(MS4Java.bf[29]);
                sb.append("\"");
                sb.append(a);
                sb.append("\" on \"");
                sb.append(MS4Java.n());
                sb.append("\".)");
                sb.append("\n");
                sb.append(MS4Java.bf[48]);
                sb.append(" \"");
                sb.append(ex.getMessage());
                sb.append("\"");
                sb.append("\n");
                sb.append(MS4Java.bf[47]);
                MS4Java.e.a(sb.toString());
                sb.setLength(0);
                sb.append(MS4Java.bf[43]);
                sb.append("GetData Exception: ");
                sb.append(ex.getMessage());
                MS4Java.b(sb.toString());
                sb.setLength(0);
                MS4Java.a(MS4Java.k).setCursor(0);
                throw ex;
            }
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(openStream, 4000));
            try {
                final NumberFormat instance = NumberFormat.getInstance(Locale.US);
                while (true) {
                    String s4 = dataInputStream.readLine();
                    if (MS4Java.a5) {
                        MS4Java.a("Host -> " + s4);
                    }
                    if (s4 == null) {
                        break;
                    }
                    if (s4.indexOf("Error") >= 0) {
                        if (s4.indexOf("DESCRIPTION") >= 0) {
                            s = String.valueOf(MS4Java.bf[46]) + " (\"" + a + "\")";
                            break;
                        }
                        if (!MS4Java.ba) {
                            continue;
                        }
                        s = s4;
                    }
                    else {
                        if (n8 == 0) {
                            ++n8;
                        }
                        if (n8 == 1) {
                            if (s4.indexOf(s3) == 0) {
                                s4 = s4.substring(s3.length(), s4.length());
                            }
                            else if (s4.indexOf("Meta:") != 0 && !s4.equals("")) {
                                MS4Java.a(String.valueOf(MS4Java.bf[43]) + "\"" + s3 + "\" or \"Meta:\" prefixes missing in iState 1");
                                continue;
                            }
                            if (s4.indexOf("DATE") < 0) {
                                continue;
                            }
                            ++n8;
                            String string = "";
                            for (int j = 0; j < s4.length(); ++j) {
                                if (s4.charAt(j) != ' ') {
                                    string = String.valueOf(string) + s4.charAt(j);
                                }
                            }
                            final StringTokenizer stringTokenizer = new StringTokenizer(string, ",");
                            int n9 = 0;
                            while (stringTokenizer.hasMoreTokens()) {
                                final String nextToken = stringTokenizer.nextToken();
                                if (nextToken.equals("DATE")) {
                                    n = n9;
                                }
                                else if (nextToken.equals("TIME")) {
                                    n2 = n9;
                                }
                                else if (nextToken.equals("OPEN")) {
                                    n3 = n9;
                                }
                                else if (nextToken.equals("HIGH")) {
                                    n4 = n9;
                                }
                                else if (nextToken.equals("LOW")) {
                                    n5 = n9;
                                }
                                else if (nextToken.equals("CLOSE") || nextToken.equals("PRICE")) {
                                    n6 = n9;
                                }
                                else if (nextToken.equals("VOLUME")) {
                                    n7 = n9;
                                }
                                ++n9;
                            }
                            if (n6 > 0) {
                                if (n3 < 0) {
                                    n3 = n6;
                                }
                                if (n4 < 0) {
                                    n4 = n6;
                                }
                                if (n5 >= 0) {
                                    continue;
                                }
                                n5 = n6;
                            }
                            else {
                                MS4Java.a(String.valueOf(MS4Java.bf[43]) + "Error parsing header.");
                            }
                        }
                        else if (n8 == 2) {
                            if (s4.length() > 1) {
                                continue;
                            }
                            ++n8;
                        }
                        else if (n8 == 3) {
                            if (s4.indexOf("Meta:") == 0) {
                                final String substring = s4.substring(5, s4.length());
                                if (substring.trim().equals("")) {
                                    MS4Java.a(String.valueOf(MS4Java.bf[43]) + "\"Meta:\" name missing in iState 3.");
                                    MS4Java.f.b("<Name Missing>");
                                }
                                else {
                                    ++n8;
                                    final StringTokenizer stringTokenizer2 = new StringTokenizer(substring, ",");
                                    String nextToken2;
                                    try {
                                        nextToken2 = stringTokenizer2.nextToken();
                                    }
                                    catch (Exception ex3) {
                                        MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Missing/invalid data.");
                                        nextToken2 = "";
                                    }
                                    this.a = a;
                                    if (!nextToken2.equalsIgnoreCase(a)) {
                                        MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Symbols don't match " + nextToken2);
                                    }
                                    String s5;
                                    try {
                                        s5 = stringTokenizer2.nextToken();
                                    }
                                    catch (Exception ex4) {
                                        MS4Java.b(String.valueOf(MS4Java.bf[43]) + "Missing/invalid data.");
                                        s5 = "";
                                    }
                                    if (s5.indexOf(32) > 0) {
                                        int n10;
                                        for (n10 = s5.length() - 1; n10 >= 0 && s5.charAt(n10) != ' '; --n10) {}
                                        s5 = s5.substring(0, n10);
                                    }
                                    this.b = s5.trim();
                                    MS4Java.f.b(this.b);
                                }
                            }
                            else {
                                MS4Java.a(String.valueOf(MS4Java.bf[43]) + "\"Meta:\" prefix missing in iState 3");
                            }
                        }
                        else {
                            if (n8 != 4) {
                                continue;
                            }
                            if (s4.length() == 0) {
                                break;
                            }
                            if (s4.indexOf(s3) == 0) {
                                final String substring2 = s4.substring(s3.length(), s4.length());
                                if (substring2.length() < 10) {
                                    continue;
                                }
                                this.h.c[i] = 0.0;
                                this.i.c[i] = 0.0;
                                this.j.c[i] = 0.0;
                                this.k.c[i] = 0.0;
                                this.l.c[i] = 0.0;
                                final StringTokenizer stringTokenizer3 = new StringTokenizer(substring2, ",");
                                int n11 = 0;
                                while (stringTokenizer3.hasMoreTokens()) {
                                    final String nextToken3 = stringTokenizer3.nextToken();
                                    if (n11 == n) {
                                        try {
                                            this.d[i] = Integer.valueOf(nextToken3.substring(0, 2));
                                            this.e[i] = Integer.valueOf(nextToken3.substring(3, 5));
                                            this.c[i] = Integer.valueOf(nextToken3.substring(8, 10));
                                        }
                                        catch (Exception ex5) {
                                            continue;
                                        }
                                    }
                                    if (n11 == n2) {
                                        if (nextToken3.length() < 8) {
                                            MS4Java.a(String.valueOf(MS4Java.bf[43]) + "Time token (\"\") too short");
                                            continue;
                                        }
                                        this.f[i] = Integer.valueOf(nextToken3.substring(0, 2));
                                        this.g[i] = nextToken3.substring(0, 5);
                                    }
                                    if (n11 == n3) {
                                        this.h.c[i] = instance.parse(nextToken3.trim()).doubleValue();
                                    }
                                    if (n11 == n4) {
                                        this.i.c[i] = instance.parse(nextToken3.trim()).doubleValue();
                                    }
                                    if (n11 == n5) {
                                        this.j.c[i] = instance.parse(nextToken3.trim()).doubleValue();
                                    }
                                    if (n11 == n6) {
                                        this.k.c[i] = instance.parse(nextToken3.trim()).doubleValue();
                                    }
                                    if (n11 == n7) {
                                        if (!this.e() || (this.e() && MS4Java.a0)) {
                                            this.l.c[i] = instance.parse(nextToken3.trim()).doubleValue();
                                        }
                                        else {
                                            this.l.c[i] = 0.0;
                                        }
                                    }
                                    ++n11;
                                }
                                b = (b && this.h.c[i] == this.i.c[i] && this.i.c[i] == this.j.c[i] && this.j.c[i] == this.k.c[i]);
                                if (++i >= this.k.g - 1) {
                                    break;
                                }
                                continue;
                            }
                            else {
                                MS4Java.a(String.valueOf(MS4Java.bf[43]) + "\"" + s3 + "\" prefix missing in iState 4");
                            }
                        }
                    }
                }
                MS4Java.e.a("");
            }
            catch (Exception ex2) {
                s = String.valueOf(MS4Java.bf[44]) + a + ".";
                MS4Java.a(String.valueOf(MS4Java.bf[43]) + "GetData execption: " + ex2.toString());
            }
            try {
                openStream.close();
            }
            catch (IOException ex6) {}
            MS4Java.a(MS4Java.k).setCursor(0);
        }
        final h h = this.h;
        final h k = this.i;
        final h l = this.j;
        final h m = this.k;
        final h l2 = this.l;
        final boolean a2 = false;
        l2.a = (a2 ? 1 : 0);
        m.a = (a2 ? 1 : 0);
        l.a = (a2 ? 1 : 0);
        k.a = (a2 ? 1 : 0);
        h.a = (a2 ? 1 : 0);
        final h h2 = this.h;
        final h i2 = this.i;
        final h j2 = this.j;
        final h k2 = this.k;
        final h l3 = this.l;
        final int b2 = i - 1;
        l3.b = b2;
        k2.b = b2;
        j2.b = b2;
        i2.b = b2;
        h2.b = b2;
        final h h3 = this.h;
        final h i3 = this.i;
        final h j3 = this.j;
        final h k3 = this.k;
        final Color w = MS4Java.w;
        k3.h = w;
        j3.h = w;
        i3.h = w;
        h3.h = w;
        this.l.h = MS4Java.x;
        this.n = this.k.b;
        this.m = Math.max(this.n - 100, this.k.a);
        if (MS4Java.ar) {
            if (b) {
                MS4Java.f.a(MS4Java.bf[3]);
            }
            else if (MS4Java.f.d.getSelectedItem().compareTo(MS4Java.bf[3]) == 0) {
                MS4Java.f.a(MS4Java.bf[0]);
            }
        }
        MS4Java.a("Records loaded: " + this.k.b());
        if (this.k.b() <= 0) {
            if (s.equals("")) {
                switch (n8) {
                    case 0: {
                        s = "Unable to access historical data applet.\n" + MS4Java.bf[47];
                        break;
                    }
                    case 1:
                    case 2:
                    case 3: {
                        s = String.valueOf(MS4Java.bf[60]) + "\n" + MS4Java.bf[47];
                        break;
                    }
                    case 4: {
                        s = "No data for " + a;
                        break;
                    }
                    default: {
                        s = "Data error at unknown location.";
                        break;
                    }
                }
            }
            MS4Java.e.a(s);
        }
    }
    
    public void g() {
        for (int i = this.k.a + 1; i <= this.k.b; ++i) {
            this.h.c[i] = this.h.c[i] / this.k.c[this.k.a] * 100.0 - 100.0;
            this.i.c[i] = this.i.c[i] / this.k.c[this.k.a] * 100.0 - 100.0;
            this.j.c[i] = this.j.c[i] / this.k.c[this.k.a] * 100.0 - 100.0;
            this.k.c[i] = this.k.c[i] / this.k.c[this.k.a] * 100.0 - 100.0;
            this.l.c[i] = 0.0;
        }
        this.h.c[this.k.a] = 0.0;
        this.i.c[this.k.a] = 0.0;
        this.j.c[this.k.a] = 0.0;
        this.k.c[this.k.a] = 0.0;
        this.l.c[this.k.a] = 0.0;
    }
    
    static {
        g.p = null;
        g.q = null;
    }
}
