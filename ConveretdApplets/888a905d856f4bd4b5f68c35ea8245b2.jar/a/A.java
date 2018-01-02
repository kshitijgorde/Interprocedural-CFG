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

public final class A
{
    public int q;
    public byte[] q;
    public int w;
    public int e;
    public int r;
    public int t;
    public int y;
    public int u;
    protected Image q;
    public String q;
    public String w;
    public boolean q;
    public String e;
    public int[] q;
    public int[] w;
    public int[] e;
    public int i;
    public boolean w;
    public boolean e;
    public boolean r;
    public boolean t;
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    public boolean p;
    public int o;
    public int p;
    public boolean a;
    public int a;
    public p q;
    public cs q;
    public int s;
    public int d;
    public int f;
    public boolean s;
    public boolean d;
    public boolean f;
    public boolean g;
    public int g;
    public int h;
    public boolean h;
    
    public final void q(final String e) {
        this.e = e;
        this.q = true;
    }
    
    public final void q() {
        int n = 0;
        this.r = 0;
        while (this.r < 25) {
            int index = this.q.indexOf("://", n);
            final int index2;
            if (((index2 = this.q.indexOf("@", n)) < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final String q = this.q;
            final int n2 = index;
            final String s = q;
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
                this.w[2 * this.r] = n4;
                this.w[2 * this.r + 1] = n6;
                ++this.r;
            }
            n = n6 + 1;
        }
        this.e();
    }
    
    public final int q(final String s) {
        if (this.q != null) {
            final M e;
            if ((e = this.q.e()).q == 0) {
                return -1;
            }
            for (int i = 0; i < e.q; ++i) {
                final bw bw;
                if ((bw = (bw)e.q(i)).e().trim().equals(s)) {
                    return bw.w();
                }
            }
        }
        return -1;
    }
    
    public final synchronized void w() {
        if (this.q == null) {
            return;
        }
        if (!((dH)this.q).z) {
            return;
        }
        final M e = this.q.e();
        this.i = 0;
        final Hashtable<String, String> hashtable = new Hashtable<String, String>();
        for (int i = 0; i < e.q; ++i) {
            final String trim = ((bw)e.q(i)).e().trim();
            boolean b;
            do {
                final int index;
                boolean b4 = false;
                boolean b3 = false;
                Label_0205: {
                    if ((index = this.q.indexOf(trim)) != -1 && !"".equals(trim)) {
                        final String q = this.q;
                        final String s = trim;
                        final String s2 = q;
                        final int index2;
                        final int n = (index2 = q.indexOf(s)) + s.length();
                        boolean b2 = false;
                        Label_0197: {
                            if (index2 < 0 || n == s2.length()) {
                                b2 = false;
                            }
                            else {
                                if (index2 - 1 >= 0) {
                                    final char char1 = s2.charAt(n);
                                    final char char2 = s2.charAt(index2 - 1);
                                    if (Character.isLetterOrDigit(char1) || !Character.isWhitespace(char2)) {
                                        b2 = true;
                                        break Label_0197;
                                    }
                                }
                                b2 = false;
                            }
                        }
                        if (!b2) {
                            b3 = (b4 = true);
                            break Label_0205;
                        }
                    }
                    b3 = (b4 = false);
                }
                b = b4;
                if (b3) {
                    final int n2 = index + trim.length();
                    if ((dN.q() && this.i >= ((dH)this.q).V) || (dN.w() && this.i >= ((dH)this.q).aa)) {
                        this.q = q(this.q, trim, index);
                        break;
                    }
                    final String string = "" + (int)(Math.random() * 2.147483647E9);
                    hashtable.put(string, trim);
                    this.q = this.q.substring(0, index) + "<RPL_DELIM>" + string + "<RPL_DELIM>" + this.q.substring(n2);
                    ++this.i;
                }
            } while (b);
        }
        this.q = this.q(this.q, hashtable);
        this.e();
    }
    
    private String q(String s, final Hashtable hashtable) {
        this.i = 0;
        final String[] q = ds.q(s, "<RPL_DELIM>");
        int index;
        while ((index = s.indexOf("<RPL_DELIM>")) >= 0) {
            s = s.substring(0, index) + s.substring(index + "<RPL_DELIM>".length());
        }
        for (int i = 0; i < q.length; ++i) {
            final String s2 = q[i];
            final String s3;
            if ((s3 = hashtable.get(s2)) != null) {
                final int index2;
                final int n = (index2 = s.indexOf(s2)) + s2.length();
                this.e[2 * this.i] = index2;
                this.e[2 * this.i + 1] = index2 + s3.length();
                s = s.substring(0, index2) + s3 + s.substring(n);
                ++this.i;
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
        final int i;
        if ((i = this.i) > 0) {
            for (int j = 0; j < this.r; ++j) {
                this.e[2 * (j + i)] = this.w[j * 2];
                this.e[2 * (j + i) + 1] = this.w[j * 2 + 1];
                ++this.i;
            }
        }
        else {
            this.e = this.w;
            this.i = this.r;
        }
        for (int k = 0; k < this.i; ++k) {
            final int n = this.e[k * 2];
            final int n2 = this.e[k * 2 + 1];
            int n3;
            for (n3 = k; n3 > 0 && this.e[2 * (n3 - 1)] > n; --n3) {
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
    
    public A(final String s, final p q, final boolean b, final boolean b2, final aZ az, final cs q2) {
        this(s, q.a, b, b2, az, q.s, q.q(62) || q.q(61), q.q(59), q.t, q.w());
        this.i = q.q(23);
        (this.q = q).q(61);
        this.f = q.s;
        this.q = q2;
        if (this.q != null && q2 != null) {
            if (this.q.q(61)) {
                this.s = ((dH)q2).X;
            }
            else {
                this.s = ((dH)q2).N;
            }
        }
        this.s = (this.f < -2147483638);
        this.d = (this.f == Integer.MIN_VALUE);
        this.f = (this.f == -2147483647);
        this.g = (this.f == -2147483646);
        this.w();
    }
    
    public A(final String s, final String w, final boolean r, final boolean t, final aZ az, final int y, final boolean e, final boolean u, final int a, final int o) {
        this.p = false;
        this.s = 1;
        this.d = 0;
        this.s = false;
        this.h = false;
        this.o = o;
        this.a = false;
        this.a = a;
        this.q = null;
        this.r = 0;
        this.t = -1;
        this.u = -1;
        this.q = new int[31];
        this.w = new int[50];
        this.e = new int[1000];
        this.q[0] = 0;
        this.q = ((s != null) ? s : "");
        this.w = w;
        if (az != null) {
            this.q = az.q;
        }
        this.r = r;
        this.t = t;
        this.y = y;
        this.e = e;
        this.u = u;
        this.q();
        if (this.y != -999 && this.q != null) {
            if (this.q.t > 0 || this.q.t < -999) {
                return;
            }
            if (this.q.q(62)) {
                this.a = 1000;
            }
            else if (this.q.q(52)) {
                this.a = 1001;
            }
            else if (this.q.q(59)) {
                this.a = 1003;
            }
            else if (this.q.q(24)) {
                this.a = 1002;
            }
            else if (this.q.q(61)) {
                this.a = 1004;
            }
            else {
                this.a = 0;
            }
            if (this.q.q(23)) {
                this.a = true;
            }
        }
    }
    
    public final String toString() {
        return this.q;
    }
}
