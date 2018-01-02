// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import java.lang.reflect.Method;

public class AppletParams
{
    private boolean a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    
    public AppletParams() {
        this.j = 2;
        this.k = 85;
        this.l = 2400;
        this.m = 1800;
        this.n = Integer.MAX_VALUE;
        this.o = false;
    }
    
    public final void a() {
        System.out.println("------------ PARAMS -----------");
        Method[] methods;
        for (int length = (methods = this.getClass().getMethods()).length, i = 0; i < length; ++i) {
            final Method method;
            if ((method = methods[i]).getName().startsWith("get")) {
                try {
                    System.out.println(method.getName().replaceFirst("get", "") + " = " + method.invoke(this, new Object[0]));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        System.out.println("-------------------------------");
    }
    
    final void a(final boolean a) {
        this.a = a;
        System.out.println("@DEBUG = " + a);
    }
    
    boolean getDebug() {
        return this.a;
    }
    
    public final void a(final String b) {
        this.b = b;
    }
    
    public String getDebugLogFile() {
        return this.b;
    }
    
    public final void b(final String c) {
        this.c = c;
    }
    
    public String getLanguage() {
        return this.c;
    }
    
    public final void c(final String d) {
        this.d = d;
    }
    
    public String getSpeedTest() {
        return this.d;
    }
    
    public final void d(final String e) {
        this.e = e;
    }
    
    public String getBatchInitURL() {
        return this.e;
    }
    
    public final void e(final String f) {
        this.f = f;
    }
    
    public String getErrorLoggingURL() {
        return this.f;
    }
    
    public final void f(final String g) {
        this.g = g;
    }
    
    public String getAppContextID() {
        return this.g;
    }
    
    public final void g(final String h) {
        this.h = h;
    }
    
    public String getUserID() {
        return this.h;
    }
    
    public final void h(final String i) {
        this.i = i;
    }
    
    public String getReducePhotosDefault() {
        return this.i;
    }
    
    public final void a(final int j) {
        this.j = j;
    }
    
    public int getUploadMaxThreads() {
        return this.j;
    }
    
    public final void b(final int k) {
        this.k = k;
    }
    
    public int getImageDownscalingJpegQFactor() {
        return this.k;
    }
    
    public final void c(final int l) {
        this.l = l;
    }
    
    public int getImageDownscalingMinDimensionPx1() {
        return this.l;
    }
    
    public final void d(final int m) {
        this.m = m;
    }
    
    public int getImageDownscalingMinDimensionPx2() {
        return this.m;
    }
    
    public final int b() {
        return this.n;
    }
    
    public final void e(final int n) {
        this.n = n;
    }
    
    public final boolean c() {
        return this.o;
    }
    
    public final void b(final boolean o) {
        this.o = o;
    }
}
