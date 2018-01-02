// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import java.util.Iterator;
import java.io.IOException;
import java.util.StringTokenizer;
import com.bullionvault.chart.c.k;
import java.util.Vector;
import java.net.MalformedURLException;
import com.bullionvault.chart.c.h;
import java.util.HashMap;
import java.net.URLConnection;
import java.net.URL;
import com.bullionvault.chart.run.ChartApp;

public final class g implements Runnable
{
    private static int a;
    private static ChartApp b;
    private int c;
    private URL d;
    private URLConnection e;
    private String f;
    private int g;
    private int h;
    private String i;
    private String j;
    private Exception k;
    private HashMap l;
    private boolean m;
    
    public static void a(final ChartApp b) {
        g.b = b;
    }
    
    public g(final String s, final int g, final int h) {
        this.k = null;
        this.l = new HashMap();
        this.c = com.bullionvault.chart.e.g.a++;
        try {
            this.d = new URL(s);
        }
        catch (MalformedURLException ex) {
            com.bullionvault.chart.c.h.a("urlOpener [" + this.c + "] - Malformed URL: " + ex);
            throw new RuntimeException("Incorrect URL format: " + s, ex);
        }
        this.h = h;
        this.g = g;
        new Vector();
    }
    
    public final URLConnection a() {
        this.a(true, false);
        int n = 1;
        while (!this.a(false, false) && n <= this.g) {
            com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - Try [" + n + "/" + this.g + "] to open [" + this.d + "]");
            final Thread a = com.bullionvault.chart.c.k.a(this, "urlOpener");
            try {
                a.join(this.h);
            }
            catch (InterruptedException ex) {
                com.bullionvault.chart.c.h.d("urlOpener [" + this.c + "] - interrupted.");
            }
            if (!this.a(false, false)) {
                com.bullionvault.chart.c.k.a(this);
                com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - Try [" + n + "/" + this.g + "] to open [" + this.d + "] - FAILED");
                this.e = null;
                ++n;
            }
            else {
                com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - Try [" + n + "/" + this.g + "] to open [" + this.d + "] - SUCCEEDED");
            }
            com.bullionvault.chart.c.k.b(this);
        }
        if (this.e == null) {
            String s = "Unable to open URL after " + this.g + " attempts.";
            if (this.k != null) {
                s += this.k.getMessage();
            }
            throw new i(s, null);
        }
        return this.e;
    }
    
    public final void a(final String s, final String s2) {
        this.l.put(s, s2);
    }
    
    public final String b() {
        return this.j;
    }
    
    private synchronized boolean a(final boolean b, final boolean m) {
        if (b) {
            this.m = m;
        }
        return this.m;
    }
    
    public final void run() {
        try {
            (this.e = this.d.openConnection()).setAllowUserInteraction(false);
            this.e.setRequestProperty("Accept-Encoding", "gzip");
            com.bullionvault.chart.c.h.e("Connection user interaction: [" + this.e.getAllowUserInteraction() + "]");
            if (com.bullionvault.chart.e.g.b != null) {
                for (final String s : this.l.keySet()) {
                    com.bullionvault.chart.e.g.b.a(s, (String)this.l.get(s));
                }
            }
            com.bullionvault.chart.c.h.e("Request Headers: " + this.e.getRequestProperties().toString());
            com.bullionvault.chart.c.h.e("urlOpener - conn.connect()");
            this.e.connect();
            this.f = this.e.getClass().getName();
            com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - URLConnection is of type: " + this.f);
            com.bullionvault.chart.c.h.e("Reading response code string from connection...");
            this.i = this.e.getHeaderField(0);
            com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - Response Code String: " + this.i);
            if (this.i != null) {
                final StringTokenizer stringTokenizer;
                (stringTokenizer = new StringTokenizer(this.i, " ")).nextToken();
                this.j = stringTokenizer.nextToken();
                stringTokenizer.nextToken();
            }
            this.a(true, true);
            com.bullionvault.chart.c.h.e("urlOpener [" + this.c + "] - Opened URL [" + this.d + "]");
        }
        catch (IOException k) {
            this.k = k;
        }
    }
    
    static {
        g.a = 1;
    }
}
