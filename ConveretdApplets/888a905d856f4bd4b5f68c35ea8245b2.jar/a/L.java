// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.TimeZone;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class L extends bp
{
    public static DateFormat q;
    public int q;
    public int w;
    public int e;
    public int r;
    public int t;
    public long q;
    private long r;
    public String q;
    public String w;
    public String e;
    public long w;
    private long t;
    public int y;
    public int u;
    public String r;
    public String t;
    public String y;
    public String u;
    public String i;
    public String o;
    public String p;
    protected String[] q;
    private static SimpleDateFormat q;
    private static SimpleDateFormat w;
    
    private static String w(final String s) {
        try {
            return L.w.format(L.q.parse(s));
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    public static String q(final String s) {
        try {
            return L.q.format(L.w.parse(s));
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    public final void q(final String s) {
        if (s != null && s.length() > 0) {
            this.q = ds.q(s, ";");
            for (int i = 0; i < this.q.length; ++i) {
                this.q[i] = w(this.q[i]);
            }
        }
    }
    
    public final String[] q() {
        return this.q;
    }
    
    public final long q() {
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(new Date(this.w));
        instance.add(6, this.y);
        return this.t = instance.getTime().getTime();
    }
    
    public Object q(final String s) {
        if (be.w("Max").equals(s)) {
            return String.valueOf(this.w);
        }
        if (be.w("Current").equals(s)) {
            return String.valueOf(this.r);
        }
        if (be.w("Hits").equals(s)) {
            return String.valueOf(this.e);
        }
        if (be.w("Last Connection").equals(s)) {
            return L.q.format(new Date(this.q));
        }
        if (be.w("Service End Date").equals(s)) {
            return L.q.format(new Date(this.q()));
        }
        if (be.w("Start In").equals(s)) {
            return L.q.format(new Date(this.w));
        }
        if (be.w("Ends In").equals(s)) {
            return L.q.format(new Date(this.t));
        }
        if (be.w("Days").equals(s)) {
            return String.valueOf(this.y);
        }
        if (be.w("Payment $").equals(s)) {
            return String.valueOf(this.u);
        }
        if (be.w("Comment").equals(s)) {
            return this.r;
        }
        if (be.w("Overdue").equals(s)) {
            if (this.q() != 0L && this.q() < new Date().getTime()) {
                return "<*>";
            }
            return "";
        }
        else {
            if (be.w("Display").equals(s)) {
                return this.w;
            }
            return super.q(s);
        }
    }
    
    public final int q(final bJ bj, final String s) {
        if (bj instanceof L) {
            final L l = (L)bj;
            if (be.w("Last Connection").equals(s)) {
                return (int)(l.q - this.q);
            }
            if (be.w("Service End Date").equals(s)) {
                return 0;
            }
            if (be.w("Max").equals(s)) {
                return l.w - this.w;
            }
            if (be.w("Current").equals(s)) {
                return l.r - this.r;
            }
            if (be.w("Hits").equals(s)) {
                return l.e - this.e;
            }
        }
        return super.q(bj, s);
    }
    
    public final void q(final boolean b) {
        this.q(1, b);
    }
    
    public final boolean q() {
        return this.q(1);
    }
    
    public L(final int n, final String s) {
        super(n, s);
        this.q = null;
        this.q = new String[0];
        this.w = 0;
        this.e = 0;
        this.r = 0;
        this.t = 1023;
        this.q = 0L;
        this.r = 0L;
        this.q = null;
        this.w = "";
        this.e = null;
        this.w = 0L;
        this.t = 0L;
        this.y = 0;
        this.u = 0;
        this.r = "";
        this.t = null;
        this.y = null;
        this.u = null;
        this.i = null;
        this.o = null;
        this.p = null;
    }
    
    public final int q(final L l) {
        final int q;
        if ((q = super.q(l)) != 0) {
            return q;
        }
        if (this.q != l.q) {
            return this.q - l.q;
        }
        if (bp.q((Object)this.q, l.q)) {
            return 1;
        }
        if (!bp.w(this.q, l.q) && this.q.compareTo(l.q) != 0) {
            return this.q.compareTo(l.q);
        }
        if (this.w != null && this.w.compareTo(l.w) != 0) {
            return this.w.compareTo(l.w);
        }
        if (this.e != null && this.e.compareTo(l.e) != 0) {
            return this.e.compareTo(l.e);
        }
        if (this.w != l.w) {
            return (int)(this.w - l.w);
        }
        if (this.t != l.t) {
            return (int)(this.t - l.t);
        }
        if (this.y != l.y) {
            return this.y - l.y;
        }
        if (this.u != l.u) {
            return this.u - l.u;
        }
        if (this.r != null && this.r.compareTo(l.r) != 0) {
            return this.r.compareTo(l.r);
        }
        if (this.t != null && this.t.compareTo(l.t) != 0) {
            return this.t.compareTo(l.t);
        }
        if (this.y != null && this.y.compareTo(l.y) != 0) {
            return this.y.compareTo(l.y);
        }
        if (this.u != null && this.u.compareTo(l.u) != 0) {
            return this.u.compareTo(l.u);
        }
        if (bp.q((Object)this.i, l.i)) {
            return 1;
        }
        if (!bp.w(this.i, l.i) && this.i.compareTo(l.i) != 0) {
            return this.i.compareTo(l.i);
        }
        if (bp.q((Object)this.o, l.o)) {
            return 1;
        }
        if (!bp.w(this.o, l.o) && this.o.compareTo(l.o) != 0) {
            return this.o.compareTo(l.o);
        }
        if (this.p != null && this.p.compareTo(l.p) != 0) {
            return this.p.compareTo(l.p);
        }
        return 0;
    }
    
    static {
        L.q = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        L.w = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        (L.q = DateFormat.getDateTimeInstance()).setTimeZone(TimeZone.getDefault());
    }
}
