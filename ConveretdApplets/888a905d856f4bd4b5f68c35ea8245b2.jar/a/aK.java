// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.TimeZone;
import java.util.Calendar;
import java.awt.Color;
import java.util.Date;
import java.text.DateFormat;

public class aK extends L
{
    private static DateFormat w;
    
    public final Object q(final String s) {
        if (be.w("Max").equals(s)) {
            return String.valueOf(super.w);
        }
        if (be.w("Current").equals(s)) {
            return String.valueOf(super.r);
        }
        if (be.w("Hits").equals(s)) {
            return String.valueOf(super.e);
        }
        if (be.w("Last Connection").equals(s)) {
            if (super.q == 0L) {
                return be.w("N/A");
            }
            return aK.w.format(new Date(super.q));
        }
        else if (be.w("Service End Date").equals(s)) {
            if (super.q() == 0L) {
                return be.w("N/A");
            }
            return aK.w.format(new Date(super.q()));
        }
        else if (be.w("Start In").equals(s)) {
            if (super.w == 0L) {
                return be.w("N/A");
            }
            return aK.w.format(new Date(super.w));
        }
        else if (be.w("Ends In").equals(s)) {
            if (super.q() == 0L) {
                return be.w("N/A");
            }
            return aK.w.format(new Date(super.q()));
        }
        else {
            if (be.w("Monthes").equals(s)) {
                return String.valueOf(dx.q(super.y));
            }
            if (be.w("Payment $").equals(s)) {
                return String.valueOf(super.u);
            }
            if (be.w("Comment").equals(s)) {
                return super.r;
            }
            if (be.w("Display").equals(s)) {
                return super.w;
            }
            return super.q(s);
        }
    }
    
    public aK(final int n, final String s) {
        super(n, s);
    }
    
    public String q() {
        return B.q(be.w("Click here to edit the site %1."), new String[] { this.toString() });
    }
    
    public final boolean w() {
        final boolean b;
        if (b = (this.q() > 0L && this.q() < new Date().getTime())) {
            this.o(Color.red.getRGB());
        }
        return b;
    }
    
    public final boolean e() {
        final Calendar instance;
        (instance = Calendar.getInstance()).add(6, 2);
        final boolean b;
        if (b = (this.q() > 0L && this.q() < instance.getTime().getDate())) {
            this.o(Color.yellow.getRGB());
        }
        return b;
    }
    
    static {
        (aK.w = DateFormat.getDateInstance()).setTimeZone(TimeZone.getDefault());
    }
}
