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

public class Z
{
    public byte[] a;
    public int h;
    public int i;
    public int a;
    public int b;
    public int Z;
    public int ai;
    protected Image o;
    public String l;
    public String m;
    public String P;
    public int[] a;
    public int[] b;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean ab;
    public boolean ac;
    public boolean ad;
    public int aj;
    public int ak;
    public int al;
    public int d;
    public boolean g;
    public boolean ae;
    public String b;
    public String c;
    public int am;
    public Hashtable f;
    public boolean af;
    public boolean ag;
    public boolean ah;
    
    public void a(final boolean w) {
        this.w = w;
    }
    
    private final int a(final String s, final int n) {
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
        while (n2 > lastIndex && ".:,!;>\"'?".indexOf(this.l.charAt(n2 - 1)) != -1) {
            --n2;
        }
        return lastIndex + (n2 << 16);
    }
    
    public void a() {
        int n = 0;
        this.l.length();
        this.a = 0;
        while (this.a < 25) {
            int index = this.l.indexOf("://", n);
            final int index2 = this.l.indexOf("@", n);
            if ((index2 < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final int a = this.a(this.l, index);
            final int n2 = a & 0xFFFF;
            final int n3 = a >>> 16;
            if (n2 < index && n3 > index + 3 && this.l.lastIndexOf(46, n3) > index) {
                this.b[2 * this.a] = n2;
                this.b[2 * this.a + 1] = n3;
                ++this.a;
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
        this.P = string + " GMT" + s + n4 + ":" + (((n3 + "").length() != 1) ? (n3 + "") : (n3 + "0")) + "]";
    }
    
    public Z(final String s, final cG cg, final boolean b, final as as) {
        this(s, cg.f(), b, as, cg.h(), cg.d(62) || cg.d(61), cg.d(59));
        this.ac = cg.d(23);
    }
    
    public Z(final String l, final String m, final boolean v, final as as, final int z, final boolean u, final boolean ab) {
        this.aj = -1;
        this.ak = 0;
        this.al = -1;
        this.d = -1;
        this.g = false;
        this.ae = false;
        this.b = "";
        this.c = "";
        this.am = 0;
        this.af = false;
        this.ag = false;
        this.ah = true;
        this.a = null;
        this.a = 0;
        this.b = -1;
        this.ai = -1;
        this.a = new int[31];
        this.b = new int[50];
        this.a[0] = 0;
        this.l = l;
        this.m = m;
        if (as != null) {
            this.o = as.q;
        }
        this.v = v;
        this.Z = z;
        this.u = u;
        this.ab = ab;
        this.a();
    }
}
