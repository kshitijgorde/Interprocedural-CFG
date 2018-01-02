// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.apps.tourclients40.iip;

import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Dimension;

public final class a implements Runnable
{
    private int e;
    public c[][] a;
    public Dimension b;
    public Dimension c;
    public int d;
    private byte[][] f;
    private com.iseemedia.apps.tourclients40.util.a g;
    private URL h;
    private String i;
    private b j;
    private StringBuffer k;
    private boolean l;
    private boolean m;
    private static boolean n;
    private static boolean o;
    private int p;
    
    public a(final int e, final Dimension b, final Dimension c, final com.iseemedia.apps.tourclients40.util.a g, final String i, final b j, final byte[][] f, final int p8) {
        this.e = e;
        this.a = new c[b.width][b.height];
        this.b = b;
        this.c = c;
        this.g = g;
        this.h = this.g.a();
        this.i = i;
        this.j = j;
        this.f = f;
        this.k = new StringBuffer(4096).append("fif=").append(this.i);
        this.d = 0;
        this.p = p8;
    }
    
    public final boolean a(final int n, final int n2, final int[] array) {
        return this.a[n][n2] != null && this.a[n][n2].a(this.h, array);
    }
    
    public final void a(final int n, final int n2, final int n3) {
        final int n4 = n * this.b.width;
        int n5 = -1;
        if (this.a[n2][n] == null && n2 == n3) {
            this.a(n4 + n2);
            return;
        }
        for (int i = n2; i <= n3; ++i) {
            if (this.a[i][n] == null && n5 == -1) {
                n5 = n4 + i;
            }
            if ((this.a[i][n] != null || i == n3) && n5 > -1) {
                int n6;
                if (i == n3 && this.a[i][n] == null) {
                    n6 = n4 + i;
                }
                else {
                    n6 = n4 + i - 1;
                }
                if (n6 > n5) {
                    this.b(n5, n6);
                }
                else {
                    this.a(n6);
                }
                n5 = -1;
            }
        }
    }
    
    private void b(final int n, final int n2) {
        this.k.append("&til=");
        this.k.append(this.e);
        this.k.append(',');
        this.k.append(n);
        this.k.append('-');
        this.k.append(n2);
        this.d += n2 - n + 1;
    }
    
    public final void a(final int n, final int n2) {
        final int n3 = n2 * this.b.width;
        if (this.a[n][n2] == null) {
            this.a(n3 + n);
        }
    }
    
    private void a(final int n) {
        this.k.append("&til=");
        this.k.append(this.e);
        this.k.append(',');
        this.k.append(n);
        ++this.d;
    }
    
    private DataInputStream f() {
        DataInputStream a = null;
        try {
            this.k.append("&obj=iip,1.0");
            a = this.g.a(this.k.toString());
            this.k.setLength(0);
            this.k.append("fif=").append(this.i);
        }
        catch (IOException ex) {
            System.err.println("Connection with IIP Server failed.");
        }
        if (a == null) {
            System.err.println("Authorization failed.");
        }
        return a;
    }
    
    private void a(final DataInputStream dataInputStream) throws Exception {
        final int d = this.d;
        if (this.p == 0) {
            for (int i = 1; i <= this.d; ++i) {
                if (this.l) {
                    break;
                }
                final c c;
                final int a = (c = new c(dataInputStream, this.f, this.e)).a();
                this.a[a % this.b.width][a / this.b.width] = c;
                this.j.a(100 * i / d);
            }
        }
        else {
            for (int n = 1; n <= this.d && !this.m; ++n) {
                final c c2;
                final int a2 = (c2 = new c(dataInputStream, this.f, this.e)).a();
                this.a[a2 % this.b.width][a2 / this.b.width] = c2;
                this.j.a(100 * n / d);
            }
        }
        this.j.a(100);
        this.d = 0;
        try {
            dataInputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public final void a() {
        if (!com.iseemedia.apps.tourclients40.iip.a.n && this.p == 0) {
            this.l = false;
            synchronized (this) {
                com.iseemedia.apps.tourclients40.iip.a.n = true;
            }
            new Thread(this, "Tile Upper Thread").start();
        }
        if (!com.iseemedia.apps.tourclients40.iip.a.o && this.p == 1) {
            this.m = false;
            synchronized (this) {
                com.iseemedia.apps.tourclients40.iip.a.o = true;
            }
            new Thread(this, "Tile Lower Thread").start();
        }
    }
    
    public final void b() {
        if (this.p == 0) {
            this.l = true;
            try {
                synchronized (this) {
                    while (com.iseemedia.apps.tourclients40.iip.a.n) {
                        this.wait(1L);
                    }
                }
                return;
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        this.m = true;
        try {
            synchronized (this) {
                while (com.iseemedia.apps.tourclients40.iip.a.o) {
                    this.wait(1L);
                }
            }
        }
        catch (InterruptedException ex2) {}
    }
    
    public final void c() {
        this.d = 0;
    }
    
    public final void run() {
        try {
            DataInputStream f = null;
            if (this.d > 0) {
                f = this.f();
            }
            if (f != null) {
                this.a(f);
            }
        }
        catch (Exception ex) {
            System.err.println("Exception in Level" + this.e);
        }
        this.d = 0;
        if (this.p == 0) {
            synchronized (this) {
                com.iseemedia.apps.tourclients40.iip.a.n = false;
                this.notifyAll();
                return;
            }
        }
        synchronized (this) {
            com.iseemedia.apps.tourclients40.iip.a.o = false;
            this.notifyAll();
        }
    }
    
    public static final boolean d() {
        return a.n;
    }
    
    public static final boolean e() {
        return a.o;
    }
}
