// 
// Decompiled by Procyon v0.5.30
// 

package b.a.c;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class d extends c
{
    protected int b;
    
    public d(final int n) {
        super(n);
        this.b = -1;
    }
    
    public boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        return n >= 1900 && this.b(n, n2, n3, n4, n5, b).get(16) != 0;
    }
    
    public int a(final int n, final int n2) {
        if (this.b < 0) {
            this.b = 0;
            for (int n3 = 1; n3 <= 12 && this.b == 0; ++n3) {
                this.b = (int)(this.b(n, n3, 1, 0, 0, false).get(16) / 60000.0);
            }
        }
        return this.b;
    }
    
    protected Calendar b(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        final double a = k.a(1970, 1, 1, 0, 0, 0.0);
        final int n6 = b ? this.a(n, n2) : 0;
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(Math.round((k.a(n, n2, n3, n4, n5 - this.a + (this.a - gregorianCalendar.get(15) / 60000) - n6, 0.0) - a) * 8.64E7)));
        return gregorianCalendar;
    }
}
