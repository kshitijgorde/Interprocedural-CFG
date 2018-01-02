// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class cu extends bZ
{
    public static DateFormat q;
    public int q;
    public int w;
    public int e;
    public int o;
    public int p;
    public int a;
    public long q;
    private long t;
    public long w;
    public long e;
    public String q;
    public String w;
    public String e;
    public long r;
    private long y;
    public int s;
    public int d;
    public String r;
    public String y;
    public String u;
    public String i;
    public String o;
    public String p;
    public String a;
    protected String[] q;
    private static SimpleDateFormat q;
    private static SimpleDateFormat w;
    
    private static String w(final String s) {
        try {
            return cu.w.format(cu.q.parse(s));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
    }
    
    public static String q(final String s) {
        try {
            return cu.q.format(cu.w.parse(s));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return s;
        }
    }
    
    public final void w(final String s) {
        if (s != null && s.length() > 0) {
            this.q = dV.q(s, ";");
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = w(this.q[i]);
            }
        }
    }
    
    public final String[] q() {
        return this.q;
    }
    
    public final long w() {
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(new Date(this.r));
        instance.add(6, this.s);
        return this.y = instance.getTime().getTime();
    }
    
    public Object q(final String s) {
        if (eb.q("Max").equals(s)) {
            return String.valueOf(this.w);
        }
        if (eb.q("Current").equals(s)) {
            return String.valueOf(this.o);
        }
        if (eb.q("Hits").equals(s)) {
            return String.valueOf(this.e);
        }
        if (eb.q("Last Connection").equals(s)) {
            return cu.q.format(new Date(this.q));
        }
        if (eb.q("Service End Date").equals(s)) {
            return cu.q.format(new Date(this.w()));
        }
        if (eb.q("Start In").equals(s)) {
            return cu.q.format(new Date(this.r));
        }
        if (eb.q("Ends In").equals(s)) {
            return cu.q.format(new Date(this.y));
        }
        if (eb.q("Days").equals(s)) {
            return String.valueOf(this.s);
        }
        if (eb.q("Payment $").equals(s)) {
            return String.valueOf(this.d);
        }
        if (eb.q("Comment").equals(s)) {
            return this.r;
        }
        if (eb.q("Overdue").equals(s)) {
            if (this.w() != 0L && this.w() < new Date().getTime()) {
                return "<*>";
            }
            return "";
        }
        else {
            if (eb.q("Display").equals(s)) {
                return this.w;
            }
            return super.q(s);
        }
    }
    
    public final int q(final aF af, final String s) {
        if (af instanceof cu) {
            final cu cu = (cu)af;
            if (eb.q("Last Connection").equals(s)) {
                return (int)(cu.q - this.q);
            }
            if (eb.q("Service End Date").equals(s)) {
                return 0;
            }
            if (eb.q("Max").equals(s)) {
                return cu.w - this.w;
            }
            if (eb.q("Current").equals(s)) {
                return cu.o - this.o;
            }
            if (eb.q("Hits").equals(s)) {
                return cu.e - this.e;
            }
        }
        return super.q(af, s);
    }
    
    public final void q(final boolean b) {
        this.q(1, b);
    }
    
    public final boolean q() {
        return this.q(1);
    }
    
    public final void w(final boolean b) {
        this.q(2, b);
    }
    
    public final boolean w() {
        return this.q(2);
    }
    
    public cu(final int n, final String s) {
        super(n, s);
        this.q = null;
        this.q = new String[0];
        this.w = 0;
        this.e = 0;
        this.o = 0;
        this.a = 1023;
        this.q = 0L;
        this.t = 0L;
        this.q = null;
        this.w = "";
        this.e = null;
        this.r = 0L;
        this.y = 0L;
        this.s = 0;
        this.d = 0;
        this.r = "";
        this.y = null;
        this.u = null;
        this.i = null;
        this.o = null;
        this.p = null;
        this.a = null;
    }
    
    public final int q(final cu cu) {
        final int q;
        if ((q = super.q(cu)) != 0) {
            return q;
        }
        if (this.q != cu.q) {
            return this.q - cu.q;
        }
        if (bZ.q((Object)this.q, cu.q)) {
            return 1;
        }
        if (!bZ.w(this.q, cu.q) && this.q.compareTo(cu.q) != 0) {
            return this.q.compareTo(cu.q);
        }
        if (this.w != null && this.w.compareTo(cu.w) != 0) {
            return this.w.compareTo(cu.w);
        }
        if (this.e != null && this.e.compareTo(cu.e) != 0) {
            return this.e.compareTo(cu.e);
        }
        if (this.r != cu.r) {
            return (int)(this.r - cu.r);
        }
        if (this.y != cu.y) {
            return (int)(this.y - cu.y);
        }
        if (this.s != cu.s) {
            return this.s - cu.s;
        }
        if (this.d != cu.d) {
            return this.d - cu.d;
        }
        if (this.r != null && this.r.compareTo(cu.r) != 0) {
            return this.r.compareTo(cu.r);
        }
        if (this.y != null && this.y.compareTo(cu.y) != 0) {
            return this.y.compareTo(cu.y);
        }
        if (this.u != null && this.u.compareTo(cu.u) != 0) {
            return this.u.compareTo(cu.u);
        }
        if (this.i != null && this.i.compareTo(cu.i) != 0) {
            return this.i.compareTo(cu.i);
        }
        if (bZ.q((Object)this.o, cu.o)) {
            return 1;
        }
        if (!bZ.w(this.o, cu.o) && this.o.compareTo(cu.o) != 0) {
            return this.o.compareTo(cu.o);
        }
        if (bZ.q((Object)this.p, cu.p)) {
            return 1;
        }
        if (!bZ.w(this.p, cu.p) && this.p.compareTo(cu.p) != 0) {
            return this.p.compareTo(cu.p);
        }
        if (this.a != null && this.a.compareTo(cu.a) != 0) {
            return this.a.compareTo(cu.a);
        }
        return 0;
    }
    
    static {
        cu.q = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        cu.w = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        (cu.q = DateFormat.getDateTimeInstance()).setTimeZone(TimeZone.getDefault());
    }
}
