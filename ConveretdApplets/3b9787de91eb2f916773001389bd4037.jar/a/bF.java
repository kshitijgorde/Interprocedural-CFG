// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.awt.Image;

public final class bF
{
    public int q;
    public int w;
    public int e;
    public int r;
    public int t;
    public int y;
    protected Image q;
    public String q;
    public String w;
    public String e;
    public int[] q;
    public int[] w;
    public int[] e;
    public int u;
    public boolean q;
    public boolean w;
    public boolean e;
    public boolean r;
    public boolean t;
    public boolean y;
    public boolean u;
    public boolean i;
    public int i;
    public int o;
    public boolean o;
    public int p;
    public bp q;
    public bz q;
    public int a;
    public int s;
    private int g;
    public boolean p;
    public boolean a;
    public boolean s;
    public boolean d;
    public int d;
    public int f;
    public boolean f;
    
    public final void q() {
        int n = 0;
        final String q = this.q;
        this.e = 0;
        while (this.e < 25) {
            int index = this.q.indexOf("://", n);
            final int index2;
            if (((index2 = this.q.indexOf("@", n)) < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final String q2 = this.q;
            final int n2 = index;
            final String s = q2;
            int lastIndex;
            if ((lastIndex = s.lastIndexOf(32, n2 - 1)) == -1) {
                lastIndex = 0;
            }
            else {
                ++lastIndex;
            }
            int n3;
            if ((n3 = s.indexOf(32, n2 + 1)) == -1) {
                n3 = s.length();
            }
            while (n3 > lastIndex && ".:,!;>\"'?".indexOf(this.q.charAt(n3 - 1)) != -1) {
                --n3;
            }
            final int n5;
            final int n4 = (n5 = lastIndex + (n3 << 16)) & 0xFFFF;
            final int n6 = n5 >>> 16;
            if (n4 < index && n6 > index + 3 && this.q.lastIndexOf(46, n6) > index) {
                this.w[2 * this.e] = n4;
                this.w[2 * this.e + 1] = n6;
                ++this.e;
            }
            n = n6 + 1;
        }
        this.e();
    }
    
    public final int q(final String s) {
        if (this.q != null) {
            final cq q;
            if ((q = this.q.q()).q() == 0) {
                return -1;
            }
            for (int i = 0; i < q.q(); ++i) {
                final bc bc;
                if ((bc = (bc)q.q(i)).w().trim().equals(s)) {
                    return bc.r();
                }
            }
        }
        return -1;
    }
    
    public final synchronized void w() {
        if (this.q == null) {
            return;
        }
        if (!((bH)this.q).l) {
            return;
        }
        final cq q = this.q.q();
        this.u = 0;
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int i = 0; i < q.q(); ++i) {
            final bc bc;
            final String trim = (bc = (bc)q.q(i)).w().trim();
            final int r = bc.r();
            boolean b;
            do {
                final int index;
                boolean b4 = false;
                boolean b3 = false;
                Label_0217: {
                    if ((index = this.q.indexOf(trim)) != -1 && !"".equals(trim) && r != 0) {
                        final String q2 = this.q;
                        final String s = trim;
                        final String s2 = q2;
                        final int index2;
                        final int n = (index2 = q2.indexOf(s)) + s.length();
                        boolean b2 = false;
                        Label_0209: {
                            if (index2 < 0 || n == s2.length()) {
                                b2 = false;
                            }
                            else {
                                if (index2 - 1 >= 0) {
                                    final char char1 = s2.charAt(n);
                                    final char char2 = s2.charAt(index2 - 1);
                                    if (Character.isLetterOrDigit(char1) || !Character.isWhitespace(char2)) {
                                        b2 = true;
                                        break Label_0209;
                                    }
                                }
                                b2 = false;
                            }
                        }
                        if (!b2) {
                            b3 = (b4 = true);
                            break Label_0217;
                        }
                    }
                    b3 = (b4 = false);
                }
                b = b4;
                if (b3) {
                    final int n2 = index + trim.length();
                    if ((a.a.q() && this.u >= ((bH)this.q).U) || (a.a.w() && this.u >= ((bH)this.q).A)) {
                        this.q = q(this.q, trim, index);
                        break;
                    }
                    if (r == 0) {
                        continue;
                    }
                    final String string = "" + (int)(Math.random() * 2.147483647E9);
                    hashtable.put(string, trim);
                    this.q = this.q.substring(0, index) + "<!!!_!!!!>" + string + "<!!!_!!!!>" + this.q.substring(n2);
                    ++this.u;
                }
            } while (b);
        }
        this.q = this.q(this.q, hashtable);
        this.e();
    }
    
    private String q(String s, final Hashtable hashtable) {
        this.u = 0;
        final String[] q = cl.q(s, "<!!!_!!!!>");
        int index;
        while ((index = s.indexOf("<!!!_!!!!>")) >= 0) {
            s = s.substring(0, index) + s.substring(index + "<!!!_!!!!>".length());
        }
        for (int i = 0; i < q.length; ++i) {
            final String s2 = q[i];
            final String s3;
            if ((s3 = hashtable.get(s2)) != null) {
                final int index2;
                final int n = (index2 = s.indexOf(s2)) + s2.length();
                this.e[2 * this.u] = index2;
                this.e[2 * this.u + 1] = index2 + s3.length();
                s = s.substring(0, index2) + s3 + s.substring(n);
                ++this.u;
            }
        }
        return s;
    }
    
    private static String q(String string, final String s, final int n) {
        int index;
        while ((index = string.indexOf(s, n)) >= 0) {
            string = string.substring(0, index) + string.substring(index + s.length(), string.length());
        }
        return string;
    }
    
    private void e() {
        final int u;
        if ((u = this.u) > 0) {
            for (int i = 0; i < this.e; ++i) {
                this.e[2 * (i + u)] = this.w[i * 2];
                this.e[2 * (i + u) + 1] = this.w[i * 2 + 1];
                ++this.u;
            }
        }
        else {
            this.e = this.w;
            this.u = this.e;
        }
        for (int j = 0; j < this.u; ++j) {
            final int n = this.e[j * 2];
            final int n2 = this.e[j * 2 + 1];
            int n3;
            for (n3 = j; n3 > 0 && this.e[2 * (n3 - 1)] > n; --n3) {
                this.e[n3 * 2] = this.e[2 * (n3 - 1)];
                this.e[n3 * 2 + 1] = this.e[2 * (n3 - 1) + 1];
            }
            this.e[n3 * 2] = n;
            this.e[n3 * 2 + 1] = n2;
        }
    }
    
    public final void q(final long n, int n2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[dd-MMM-yy hh:mma");
        final GregorianCalendar calendar;
        (calendar = new GregorianCalendar(new SimpleTimeZone(n2, ""))).setTime(new Date(n));
        simpleDateFormat.setCalendar(calendar);
        final String string = simpleDateFormat.format(calendar.getTime(), new StringBuffer(), new FieldPosition(0)).toString();
        final int n3 = n2 / 360000 % 10;
        n2 = n2 / 3600000 - n3;
        String s = "";
        if (n2 > 0) {
            s = "+";
        }
        if (n2 == 0) {
            s = " ";
        }
        this.e = string + " GMT" + s + n2 + ":" + (((n3 + "").length() != 1) ? (n3 + "") : (n3 + "0")) + "]";
    }
    
    public bF(final String s, final bp q, final boolean b, final boolean b2, final bj bj, final bz q2) {
        this(s, q.getName(), b, b2, bj, q.q(), q.q(62) || q.q(61), q.q(59), q.o, q.r());
        this.u = q.q(23);
        (this.q = q).a_();
        this.g = q.q();
        this.q = q2;
        if (this.q != null && q2 != null) {
            if (this.q.a_()) {
                this.a = ((bH)q2).T;
            }
            else {
                this.a = ((bH)q2).O;
            }
        }
        this.p = (this.g < -2147483638);
        this.a = (this.g == Integer.MIN_VALUE);
        this.s = (this.g == -2147483647);
        this.d = (this.g == -2147483646);
        this.w();
        if (this.t != -999 && this.q != null && this.p == 0) {
            if (this.q.q(23)) {
                this.o = true;
            }
            if (this.q.o > 0 || this.q.o < -999) {
                return;
            }
            if (this.q.q(62)) {
                this.p = 1000;
                return;
            }
            if (this.q.q(52)) {
                this.p = 1001;
                return;
            }
            if (this.q.q(59)) {
                this.p = 1003;
                return;
            }
            if (this.q.q(24)) {
                this.p = 1002;
                return;
            }
            if (this.q.q(61)) {
                this.p = 1004;
                return;
            }
            this.p = 0;
        }
    }
    
    public bF(final String s, final String w, final boolean e, final boolean r, final bj bj, final int t, final boolean w2, final boolean y, final int p10, final int i) {
        this.i = false;
        this.a = 1;
        this.s = 0;
        this.p = false;
        this.f = false;
        this.i = i;
        this.o = false;
        this.p = p10;
        this.e = 0;
        this.r = -1;
        this.y = -1;
        this.q = new int[31];
        this.w = new int[50];
        this.e = new int[1000];
        this.q[0] = 0;
        this.q = ((s != null) ? s : "");
        this.w = w;
        if (bj != null) {
            this.q = bj.q;
        }
        this.e = e;
        this.r = r;
        this.t = t;
        this.w = w2;
        this.y = y;
        this.q();
    }
    
    public final String toString() {
        return this.q;
    }
}