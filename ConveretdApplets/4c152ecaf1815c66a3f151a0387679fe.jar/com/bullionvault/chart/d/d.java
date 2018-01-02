// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.d;

import java.util.Calendar;
import java.util.TimeZone;
import com.bullionvault.chart.c.h;
import java.util.Date;

public final class d
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private Date i;
    
    public d() {
        this.g = 0;
        this.h = 0;
    }
    
    public d(final b b) {
        this.g = 0;
        this.h = 0;
        final String a = b.a();
        final d d = this;
        final String s = a;
        this = d;
        com.bullionvault.chart.c.h.e("Timestamp : " + s);
        final String substring = s.substring(0, 4);
        final String substring2 = s.substring(4, 6);
        final String substring3 = s.substring(6, 8);
        final String substring4 = s.substring(8, 10);
        final String substring5 = s.substring(10, 12);
        final String substring6 = s.substring(12, 14);
        com.bullionvault.chart.c.h.e("Timestamp : " + substring3 + "/" + substring2 + "/" + substring + " " + substring4 + ":" + substring5 + ":" + substring6);
        this.c = Integer.parseInt(substring);
        this.b = Integer.parseInt(substring2);
        this.a = Integer.parseInt(substring3);
        this.d = Integer.parseInt(substring4);
        this.e = Integer.parseInt(substring5);
        this.f = Integer.parseInt(substring6);
        com.bullionvault.chart.c.h.e("Timestamp : " + this.a + "/" + this.b + "/" + this.c + " " + this.d + ":" + this.e + ":" + this.f);
        if (s.length() == 21) {
            if (s.charAt(14) != '.') {
                throw new RuntimeException("Decimal Point missing from timestamp.\n");
            }
            this.g = Integer.parseInt(s.substring(15, 20));
            this.h = this.g / 1000;
        }
        this = this;
        final Calendar instance;
        (instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"))).set(this.c, this.b - 1, this.a, this.d, this.e, this.f);
        if (this.h != 0) {
            instance.set(14, this.h);
        }
        else {
            instance.set(14, 0);
        }
        this.i = instance.getTime();
        com.bullionvault.chart.c.h.e("Date (localtime) converted to: " + this.i);
    }
    
    public final Date a() {
        return this.i;
    }
}
