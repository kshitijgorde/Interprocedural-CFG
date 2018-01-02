// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.resource;

import java.util.StringTokenizer;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public final class b
{
    private BufferedReader a;
    private static String b;
    private static int c;
    private static int d;
    private static int e;
    private static String f;
    private static String g;
    private static String h;
    private static String i;
    private static String j;
    
    public b() {
        this.c();
    }
    
    public final void a() {
        try {
            this.a = new BufferedReader(new InputStreamReader(com.iseemedia.apps.tourclients40.resource.a.a("resource.properties")));
        }
        catch (Exception ex) {
            System.err.println("FileInputHandler: Error opening file named \"resource.properties\" for reading: " + ex);
            throw new RuntimeException("FileInputHandler: Error opening file named \"resource.properties\" for reading");
        }
    }
    
    public final String b() {
        String line;
        try {
            line = this.a.readLine();
        }
        catch (Exception ex) {
            System.err.println("FileInputHandler: Error reading file named \"resource.properties\" for reading: " + ex);
            throw new RuntimeException("FileInputHandler: Error reading file named \"resource.properties\" for reading");
        }
        return line;
    }
    
    public final void c() {
        try {
            this.a();
            for (String s = this.b(); s != null; s = this.b()) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
                try {
                    while (stringTokenizer.hasMoreTokens()) {
                        final StringTokenizer stringTokenizer2;
                        final String trim = (stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken().trim(), "=")).nextToken().trim();
                        final String trim2 = stringTokenizer2.nextToken().trim();
                        try {
                            if (trim.startsWith("image.toolbar")) {
                                a(trim2);
                            }
                            else {
                                if (trim.startsWith("image.splash")) {
                                    continue;
                                }
                                if (trim.startsWith("button.toolbar.width")) {
                                    b(trim2);
                                }
                                else if (trim.startsWith("button.width")) {
                                    c(trim2);
                                }
                                else if (trim.startsWith("button.height")) {
                                    d(trim2);
                                }
                                else if (trim.startsWith("vt.vuforge")) {
                                    g(trim2);
                                }
                                else if (trim.startsWith("help.link")) {
                                    e(trim2);
                                }
                                else if (trim.startsWith("info.link")) {
                                    f(trim2);
                                }
                                else if (trim.startsWith("info.product.version")) {
                                    h(trim2);
                                }
                                else {
                                    if (!trim.startsWith("info.product.name")) {
                                        continue;
                                    }
                                    i(trim2);
                                }
                            }
                        }
                        catch (Exception ex) {
                            System.err.println("Error when attempting to open, write to, or close FileOutputDemo.txt");
                        }
                    }
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex3) {}
        this.j();
    }
    
    public static final void a(final String b) {
        if (b != null) {
            b.b = b;
        }
    }
    
    public static final void b(final String s) {
        if (s != null) {
            com.iseemedia.apps.tourclients40.resource.b.c = Integer.parseInt(s);
        }
    }
    
    public static final void c(final String s) {
        if (s != null) {
            com.iseemedia.apps.tourclients40.resource.b.e = Integer.parseInt(s);
        }
    }
    
    public static final void d(final String s) {
        if (s != null) {
            com.iseemedia.apps.tourclients40.resource.b.d = Integer.parseInt(s);
        }
    }
    
    public static final String d() {
        return com.iseemedia.apps.tourclients40.resource.b.b;
    }
    
    public static final int e() {
        return com.iseemedia.apps.tourclients40.resource.b.c;
    }
    
    public static final int f() {
        return com.iseemedia.apps.tourclients40.resource.b.e;
    }
    
    public static final int g() {
        return com.iseemedia.apps.tourclients40.resource.b.d;
    }
    
    public static final void e(final String s) {
        com.iseemedia.apps.tourclients40.resource.b.g = "http://" + s;
    }
    
    public static final String h() {
        return com.iseemedia.apps.tourclients40.resource.b.g;
    }
    
    public static final void f(final String s) {
        com.iseemedia.apps.tourclients40.resource.b.h = "http://" + s;
    }
    
    public static final String i() {
        return com.iseemedia.apps.tourclients40.resource.b.h;
    }
    
    public final void j() {
        try {
            this.a.close();
        }
        catch (Exception ex) {}
    }
    
    public static final void g(final String f) {
        com.iseemedia.apps.tourclients40.resource.b.f = f;
    }
    
    public static final String k() {
        return com.iseemedia.apps.tourclients40.resource.b.f;
    }
    
    public static final void h(final String i) {
        com.iseemedia.apps.tourclients40.resource.b.i = i;
    }
    
    public static final String l() {
        return com.iseemedia.apps.tourclients40.resource.b.i;
    }
    
    public static final void i(final String j) {
        com.iseemedia.apps.tourclients40.resource.b.j = j;
    }
    
    public static final String m() {
        return com.iseemedia.apps.tourclients40.resource.b.j;
    }
}
