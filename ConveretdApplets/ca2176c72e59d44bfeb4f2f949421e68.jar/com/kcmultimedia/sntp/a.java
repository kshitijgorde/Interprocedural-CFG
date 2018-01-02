// 
// Decompiled by Procyon v0.5.30
// 

package com.kcmultimedia.sntp;

class a
{
    public static final boolean a = true;
    public static final String b = "\n*********************************************\n* SNTP API Evaluation - Version 1.1         *\n* Copyright (C) 1999 KC Multimedia & Design *\n* This package is for internal evaluation   *\n* purposes only. Licensing information is   *\n* available online at www.kcmultimedia.com  *\n*********************************************\n";
    public static final int c = 52;
    public static final int d = 32;
    public static final int e = 40;
    private final double f = 2415020.5;
    private long[] g;
    
    public a() {
        this.g = new long[8];
        for (int i = 0; i < 8; ++i) {
            this.g[i] = 0L;
        }
    }
    
    public a(final byte[] array, final int n) {
        this();
        this.setData(array, n);
    }
    
    public void setData(final byte[] array, final int n) {
        int n2 = n;
        for (int i = 0; i < 8; ++i) {
            this.g[i] = (array[n2] & 0xFF);
            ++n2;
        }
    }
    
    private long a() {
        long n = 0L;
        for (int i = 0, n2 = 3; i < 4; ++i, --n2) {
            n |= this.g[i] << n2 * 8;
        }
        return n;
    }
    
    private double b() {
        long n = 0L;
        for (int i = 4, n2 = 3; i < 8; ++i, --n2) {
            n |= this.g[i] << n2 * 8;
        }
        return n / 4.294967296E9;
    }
    
    public double getSeconds() {
        String.valueOf(this.a());
        return this.a() + this.b();
    }
    
    public double getJulianDate() {
        return 2415020.5 + this.getSeconds() / 86400.0;
    }
}
