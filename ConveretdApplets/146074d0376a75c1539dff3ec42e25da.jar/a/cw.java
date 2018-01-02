// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.TimeZone;
import java.util.Calendar;
import java.awt.Color;
import java.util.Date;
import java.text.DateFormat;

public class cw extends cu
{
    private static DateFormat w;
    
    public final Object q(final String s) {
        if (eb.q("Max").equals(s)) {
            return String.valueOf(super.w);
        }
        if (eb.q("Current").equals(s)) {
            return String.valueOf(super.o);
        }
        if (eb.q("Hits").equals(s)) {
            return String.valueOf(super.e);
        }
        if (eb.q("Last Connection").equals(s)) {
            if (super.q == 0L) {
                return eb.q("N/A");
            }
            return cw.w.format(new Date(super.q));
        }
        else if (eb.q("Service End Date").equals(s)) {
            if (super.w() == 0L) {
                return eb.q("N/A");
            }
            return cw.w.format(new Date(super.w()));
        }
        else if (eb.q("Start In").equals(s)) {
            if (super.r == 0L) {
                return eb.q("N/A");
            }
            return cw.w.format(new Date(super.r));
        }
        else if (eb.q("Ends In").equals(s)) {
            if (super.w() == 0L) {
                return eb.q("N/A");
            }
            return cw.w.format(new Date(super.w()));
        }
        else {
            if (eb.q("Monthes").equals(s)) {
                return String.valueOf(T.q(super.s));
            }
            if (eb.q("Payment $").equals(s)) {
                return String.valueOf(super.d);
            }
            if (eb.q("Comment").equals(s)) {
                return super.r;
            }
            if (eb.q("Display").equals(s)) {
                return super.w;
            }
            return super.q(s);
        }
    }
    
    public cw(final int n, final String s) {
        super(n, s);
    }
    
    public String q() {
        return ec.q(eb.q("Click here to edit the site %1."), new String[] { this.toString() });
    }
    
    public final boolean e() {
        final boolean b;
        if (b = (this.w() > 0L && this.w() < new Date().getTime())) {
            this.t(Color.red.getRGB());
        }
        return b;
    }
    
    public final boolean r() {
        final Calendar instance;
        (instance = Calendar.getInstance()).add(6, 2);
        final boolean b;
        if (b = (this.w() > 0L && this.w() < instance.getTime().getDate())) {
            this.t(Color.yellow.getRGB());
        }
        return b;
    }
    
    static {
        (cw.w = DateFormat.getDateInstance()).setTimeZone(TimeZone.getDefault());
    }
}
