// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.bp;
import com.diginet.digichat.common.j;
import java.text.FieldPosition;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.Image;

public class bf
{
    public static final int TYPE_GEN = 0;
    public static final int TYPE_PRIV = 1;
    public static final int TYPE_WEBIM = 2;
    public static final int TYPE_BUDDY = 3;
    public static final int TYPE_INVITE = 4;
    public static final int CHECK_NONE = 0;
    public static final int CHECK_UNCHECKED = 1;
    public static final int CHECK_CHECKED = 2;
    public byte[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    protected Image h;
    protected Image imgStar;
    public String i;
    public String j;
    public String strTime;
    public String k;
    public String strLoc;
    public int[] l;
    public int[] m;
    public boolean n;
    public boolean o;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean fDouble;
    public boolean fCheck;
    public int nLoc;
    public int nId;
    protected int nY;
    public int nStyle;
    public int nType;
    public int nCheck;
    public Color clrName;
    public Color clrText;
    public Color clrBack;
    
    public void a(final boolean q) {
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
    
    public void a() {
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
        this.k = String.valueOf(string).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(" GMT").concat(String.valueOf(s))).concat(String.valueOf(n4))).concat(String.valueOf(":"))).concat(String.valueOf((String.valueOf(n3).concat(String.valueOf("")).length() != 1) ? String.valueOf(n3).concat(String.valueOf("")) : String.valueOf(n3).concat(String.valueOf("0"))))).concat(String.valueOf("]"))));
    }
    
    public bf(final String s, final j j, final int n, final bp bp, final i i) {
        this(s, j, n, bp, i, 0L);
    }
    
    public bf(final String s, final j j, final int n, final bp bp, final i i, final long n2) {
        this(s, j.x(), j.clrName, n, bp, null, j.w(), j.i(62) || j.i(61), j.i(59), n2);
        this.s = (j.i(23) || j.i(79));
        final bp star;
        if ((star = i.getStar(j.nStar)) != null) {
            this.imgStar = star.a;
        }
    }
    
    public bf(final String s, final String s2, final Color color, final int n, final bp bp, final Image image, final int n2, final boolean b, final boolean b2, final long n3) {
        this(s, s2, color, n, (bp == null) ? null : bp.a, image, n2, null, 0, b, b2, n3);
    }
    
    public bf(final String s, final String s2, final Color color, final int n, final Image image, final Image image2, final int n2, final String s3, final int n3, final boolean b, final boolean b2, final long n4) {
        this(s, s2, null, color, n, image, image2, n2, s3, n3, b, b2, n4, 0);
    }
    
    public bf(final String i, final String j, final String strTime, final Color clrName, final int nType, final Image h, final Image imgStar, final int f, final String strLoc, final int nLoc, final boolean o, final boolean r, final long n, final int nCheck) {
        this.a = null;
        this.d = 0;
        this.e = -1;
        this.clrName = clrName;
        this.l = new int[31];
        this.m = new int[50];
        this.l[0] = 0;
        this.i = i;
        this.j = j;
        this.strTime = strTime;
        this.h = h;
        this.imgStar = imgStar;
        this.nType = nType;
        this.f = f;
        this.strLoc = strLoc;
        this.nLoc = nLoc;
        this.o = o;
        this.r = r;
        this.nStyle = (((n & Long.MIN_VALUE) == 0x0) ? -1 : ((int)(n >> 57) & 0x3F));
        this.clrText = (((n & 0x1000000L) == 0x0) ? null : new Color((int)n & 0xFFFFFF));
        this.clrBack = (((n & 0x100000000000000L) == 0x0) ? null : new Color((int)(n >> 32) & 0xFFFFFF));
        this.nCheck = nCheck;
        final boolean b = false;
        this.fCheck = b;
        this.fDouble = b;
        this.a();
    }
}
