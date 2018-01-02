// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.text.FieldPosition;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.awt.Image;

public class bv
{
    public byte[] a;
    public int t;
    public int g;
    public int h;
    public int i;
    public int B;
    public int o;
    protected Image j;
    public String p;
    public String q;
    public String I;
    public int[] f;
    public int[] g;
    public boolean w;
    public boolean x;
    public boolean z;
    public boolean al;
    public boolean am;
    public boolean an;
    public boolean Z;
    public int aB;
    public int aC;
    public int aD;
    public int d;
    public String c;
    public String a;
    public boolean g;
    public boolean ao;
    public int aE;
    public Hashtable j;
    public boolean ap;
    public boolean aq;
    public boolean ar;
    
    public void a(final boolean al) {
        this.al = al;
    }
    
    private final int b(final String s, final int n) {
        int lastIndex = s.lastIndexOf(32, n - 1);
        if (lastIndex == -1) {
            lastIndex = 0;
        }
        else {
            ++lastIndex;
        }
        int n2 = s.indexOf(32, n + 1);
        if (n2 == -1) {
            n2 = s.length();
        }
        while (n2 > lastIndex && ".:,!;>\"'?".indexOf(this.p.charAt(n2 - 1)) != -1) {
            --n2;
        }
        return lastIndex + (n2 << 16);
    }
    
    public void f() {
        int n = 0;
        this.p.length();
        this.h = 0;
        while (this.h < 25) {
            int index = this.p.indexOf("://", n);
            final int index2 = this.p.indexOf("@", n);
            if ((index2 < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final int b = this.b(this.p, index);
            final int n2 = b & 0xFFFF;
            final int n3 = b >>> 16;
            if (n2 < index && n3 > index + 3 && this.p.lastIndexOf(46, n3) > index) {
                this.g[2 * this.h] = n2;
                this.g[2 * this.h + 1] = n3;
                ++this.h;
            }
            n = n3 + 1;
        }
    }
    
    public void a(final long n, final int n2) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[dd-MMM-yy hh:mma");
        final Date time = new Date(n);
        final GregorianCalendar calendar = new GregorianCalendar(new SimpleTimeZone(n2, ""));
        calendar.setTime(time);
        simpleDateFormat.setCalendar(calendar);
        final String string = simpleDateFormat.format(time, new StringBuffer(), new FieldPosition(0)).toString();
        final int n3 = n2 / 360000 % 10;
        final int n4 = n2 / 3600000 - n3;
        String s = "";
        if (n4 > 0) {
            s = "+";
        }
        if (n4 == 0) {
            s = " ";
        }
        this.I = string + " GMT" + s + n4 + ":" + (((n3 + "").length() != 1) ? (n3 + "") : (n3 + "0")) + "]";
    }
    
    public bv(final String s, final F f, final boolean b, final af af) {
        this(s, f.d(), b, af, f.e(), f.a(62) || f.a(61), f.a(59));
        this.an = f.a(23);
    }
    
    public bv(final String p7, final String q, final boolean z, final af af, final int b, final boolean x, final boolean am) {
        this.aB = -1;
        this.aC = 0;
        this.aD = -1;
        this.d = -1;
        this.c = "";
        this.a = "";
        this.g = false;
        this.ao = false;
        this.aE = 0;
        this.ap = false;
        this.aq = false;
        this.ar = true;
        this.a = null;
        this.h = 0;
        this.i = -1;
        this.o = -1;
        this.f = new int[31];
        this.g = new int[50];
        this.f[0] = 0;
        this.p = p7;
        this.q = q;
        if (af != null) {
            this.j = af.a;
        }
        this.z = z;
        this.B = b;
        this.x = x;
        this.am = am;
        this.f();
    }
}
