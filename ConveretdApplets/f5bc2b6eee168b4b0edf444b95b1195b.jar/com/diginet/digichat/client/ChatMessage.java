// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.a1;
import com.diginet.digichat.common.User;
import java.text.FieldPosition;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Image;

public class ChatMessage
{
    public byte[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    protected Image h;
    public String i;
    public String j;
    public String k;
    public int[] l;
    public int[] m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    
    public final void a(final boolean q) {
        this.q = q;
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
        while (n2 > lastIndex && ".:,!;>\"'?".indexOf(this.i.charAt(n2 - 1)) != -1) {
            --n2;
        }
        return lastIndex + (n2 << 16);
    }
    
    public final void a() {
        int n = 0;
        this.i.length();
        this.d = 0;
        while (this.d < 25) {
            int index = this.i.indexOf("://", n);
            final int index2 = this.i.indexOf("@", n);
            if ((index2 < index || index == -1) && index2 != -1) {
                index = index2;
            }
            if (index == -1) {
                break;
            }
            final int a = this.a(this.i, index);
            final int n2 = a & 0xFFFF;
            final int n3 = a >>> 16;
            if (n2 < index && n3 > index + 3 && this.i.lastIndexOf(46, n3) > index) {
                this.m[2 * this.d] = n2;
                this.m[2 * this.d + 1] = n3;
                ++this.d;
            }
            n = n3 + 1;
        }
    }
    
    public final void a(final long n, final int n2) {
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
        this.k = string + " GMT" + s + n4 + ":" + (((n3 + "").length() == 1) ? (n3 + "0") : (n3 + "")) + "]";
    }
    
    public ChatMessage(final String s, final User user, final boolean b, final a1 a1) {
        this(s, user.getName(), b, a1, user.x(), user.u(62) || user.u(61), user.u(59));
        this.s = user.u(23);
    }
    
    public ChatMessage(final String i, final String j, final boolean p7, final a1 a1, final int f, final boolean o, final boolean r) {
        this.a = null;
        this.d = 0;
        this.e = -1;
        this.g = -1;
        this.l = new int[31];
        this.m = new int[50];
        this.l[0] = 0;
        this.i = i;
        this.j = j;
        if (a1 != null) {
            this.h = a1.a;
        }
        this.p = p7;
        this.f = f;
        this.o = o;
        this.r = r;
        this.a();
    }
}
