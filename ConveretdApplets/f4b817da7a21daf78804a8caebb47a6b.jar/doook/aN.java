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

public class aN
{
    public byte[] a;
    public int f;
    public int a;
    public int b;
    public int c;
    public int d;
    public int g;
    protected Image d;
    public String d;
    public String e;
    public String f;
    public int[] e;
    public int[] f;
    public boolean l;
    public boolean y;
    public boolean p;
    public boolean n;
    public boolean z;
    public boolean A;
    public boolean o;
    public int z;
    public int A;
    public int B;
    public int x;
    public String y;
    public String z;
    public boolean B;
    public boolean C;
    public int C;
    public Hashtable d;
    public boolean D;
    public boolean E;
    public boolean F;
    
    public void a(final boolean n) {
        this.n = n;
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
        while (n2 > lastIndex && ".:,!;>\"'?".indexOf(this.d.charAt(n2 - 1)) != -1) {
            --n2;
        }
        return lastIndex + (n2 << 16);
    }
    
    public void c() {
        int n = 0;
        this.d.length();
        this.b = 0;
        while (this.b < 25) {
            int index = this.d.indexOf("://", n);
            final int index2 = this.d.indexOf("@", n);
            if ((index2 < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final int a = this.a(this.d, index);
            final int n2 = a & 0xFFFF;
            final int n3 = a >>> 16;
            if (n2 < index && n3 > index + 3 && this.d.lastIndexOf(46, n3) > index) {
                this.f[2 * this.b] = n2;
                this.f[2 * this.b + 1] = n3;
                ++this.b;
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
        this.f = string + " GMT" + s + n4 + ":" + (((n3 + "").length() != 1) ? (n3 + "") : (n3 + "0")) + "]";
    }
    
    public aN(final String s, final aI ai, final boolean b, final bh bh) {
        this(s, ai.g(), b, bh, ai.b(), ai.c(62) || ai.c(61), ai.c(59));
        this.A = ai.c(23);
    }
    
    public aN(final String d, final String e, final boolean p7, final bh bh, final int d2, final boolean y, final boolean z) {
        this.z = -1;
        this.A = 0;
        this.B = -1;
        this.x = -1;
        this.y = "";
        this.z = "";
        this.B = false;
        this.C = false;
        this.C = 0;
        this.D = false;
        this.E = false;
        this.F = true;
        this.a = null;
        this.b = 0;
        this.c = -1;
        this.g = -1;
        this.e = new int[31];
        this.f = new int[50];
        this.e[0] = 0;
        this.d = d;
        this.e = e;
        if (bh != null) {
            this.d = bh.b;
        }
        this.p = p7;
        this.d = d2;
        this.y = y;
        this.z = z;
        this.c();
    }
}
