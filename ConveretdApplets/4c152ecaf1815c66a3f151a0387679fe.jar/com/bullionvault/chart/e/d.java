// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.e;

import com.bullionvault.chart.c.e;
import java.util.Calendar;
import com.bullionvault.chart.f.c;
import java.io.IOException;
import com.bullionvault.chart.d.a;
import java.util.zip.GZIPInputStream;
import com.bullionvault.chart.resources.Resources;
import com.bullionvault.chart.a.s;
import com.bullionvault.chart.c.k;
import java.io.InputStream;
import java.util.Date;
import java.util.Vector;
import com.bullionvault.chart.a.t;

public final class d implements Runnable
{
    private h g;
    private t h;
    private boolean i;
    public Vector a;
    private int j;
    private b k;
    private String l;
    private String m;
    public float b;
    public float c;
    public Date d;
    public Date e;
    private int n;
    private String o;
    public int f;
    private InputStream p;
    private int q;
    private String r;
    private com.bullionvault.chart.d.b s;
    private d t;
    private String u;
    
    public d(final h g, final t h, final String m) {
        this.i = true;
        this.q = 60;
        this.r = "";
        this.u = null;
        this.g = g;
        this.h = h;
        this.m = m;
        this.l = com.bullionvault.chart.g.b.a.a();
        this.k = new b(this.l, m);
    }
    
    private d(final d d) {
        this.i = true;
        this.q = 60;
        this.r = "";
        this.u = null;
        this.g = d.g;
        this.h = d.h;
        this.m = d.m;
        this.l = com.bullionvault.chart.g.b.a.a();
        this.k = new b(this.l, this.m);
    }
    
    public final void a() {
        this.i = true;
        com.bullionvault.chart.c.k.a(this, "HLCreader");
        com.bullionvault.chart.c.h.e("HLCreader - startThread() - started");
    }
    
    public final void b() {
        this.i = false;
        com.bullionvault.chart.c.k.a(this);
        com.bullionvault.chart.c.h.e("HLCreader - stopThread() - stopped");
    }
    
    public final void run() {
        this.i = true;
        while (this.i && !Thread.interrupted()) {
            com.bullionvault.chart.c.h.e("Point Reader Thread sleeping for " + this.q + " seconds.");
            try {
                Thread.sleep(this.q * 1000);
            }
            catch (InterruptedException ex) {
                return;
            }
            com.bullionvault.chart.c.h.f("Running thread ...\n");
            if (this.a != null && this.a.size() == 0) {
                com.bullionvault.chart.c.h.e("setReader - no points received, so reading from HLCreader");
                try {
                    this.a("/Full");
                    this.g.a();
                }
                catch (s s) {
                    throw new RuntimeException(s.getMessage(), s);
                }
            }
            if (this.i && !Thread.interrupted()) {
                if (this.t == null) {
                    this.t = new d(this);
                }
                try {
                    this.t.a("/Update");
                    this.d();
                }
                catch (s s2) {
                    throw new RuntimeException(s2.getMessage(), s2);
                }
            }
            this.g.a();
        }
        com.bullionvault.chart.c.k.b(this);
    }
    
    public final void a(String s) {
        try {
            final d d = this;
            s = s;
            this = d;
            if (d.h != null) {
                this.h.b(Resources.b("poll.updating"));
            }
            if (this.g != null) {
                this.g.b();
            }
            final d d2 = this;
            final String s2 = s;
            final d d3 = d2;
            d2.k.a(s2);
            final d d4 = d3;
            final b k;
            if ((k = d3.k).a == null) {
                throw new RuntimeException(Resources.b("error.connection_not_established"));
            }
            InputStream inputStream;
            if (k.a.getContentEncoding() != null && k.a.getContentEncoding().equalsIgnoreCase("gzip")) {
                inputStream = new GZIPInputStream(k.a.getInputStream());
            }
            else {
                inputStream = k.a.getInputStream();
            }
            d4.p = inputStream;
            this.s = new a(this.p).a();
            try {
                this.p.close();
            }
            catch (IOException ex) {
                com.bullionvault.chart.c.h.d(ex + "HLCreader - read() - problem closing InputStream.");
            }
            com.bullionvault.chart.c.h.e(this.s.toString());
            final d d5 = this;
            com.bullionvault.chart.c.h.a = 0;
            final com.bullionvault.chart.d.b d6;
            final float b;
            if ((b = (d6 = d5.s.d("envelope").d("message")).b("version")) != 0.4f) {
                throw new RuntimeException("Incompatible file format [" + b + "] compared with program [" + 0.4f + "] - please upgrade.");
            }
            final String a;
            if ((a = d6.a("type")).equals("CHART_BAR_HLC_A")) {
                d5.j = 2;
            }
            else {
                if (!a.equals("CHART_LINE_A")) {
                    throw new RuntimeException("Unknown Message Type encountered: " + a);
                }
                d5.j = 1;
            }
            d5.e = new com.bullionvault.chart.d.d(d6.d("latest_timestamp")).a();
            com.bullionvault.chart.c.h.f("Latest Date: " + d5.e);
            d5.n = Integer.parseInt(d6.d("interval").a());
            d5.o = "seconds";
            final d d7 = d5;
            final String o;
            int f;
            if ((o = d5.o).equals("seconds")) {
                f = 13;
            }
            else if (o.equals("minutes")) {
                f = 12;
            }
            else if (o.equals("hours")) {
                f = 11;
            }
            else if (o.equals("days")) {
                f = 5;
            }
            else if (o.equals("weeks")) {
                f = 3;
            }
            else if (o.equals("months")) {
                f = 2;
            }
            else {
                if (!o.equals("years")) {
                    throw new RuntimeException("Invalid calendar units specified [" + o + "]");
                }
                f = 1;
            }
            com.bullionvault.chart.c.h.f("Calendar Units of [" + o + "] selected with value [" + f + "]");
            d7.f = f;
            if (d6.d("ticker") != null) {
                d5.u = d6.d("ticker").a();
            }
            if (d5.j == 1) {
                d5.b(d6.d("points"));
            }
            else {
                if (d5.j != 2) {
                    throw new RuntimeException("Unknown message type mode [" + d5.j + "]");
                }
                d5.a(d6.d("bars"));
            }
            this.e();
            final d d8;
            if ((d8 = this).f == 13) {
                if (d8.n >= 86400) {
                    com.bullionvault.chart.c.h.e("Setting interval to days");
                    d8.o = "days";
                    d8.n /= 86400;
                    d8.f = 5;
                }
                else if (d8.n >= 3600) {
                    com.bullionvault.chart.c.h.e("Setting interval to hours");
                    d8.o = "hours";
                    d8.n /= 3600;
                    d8.f = 11;
                }
                else if (d8.n >= 60) {
                    com.bullionvault.chart.c.h.e("Setting interval to minutes");
                    d8.o = "minutes";
                    d8.n /= 60;
                    d8.f = 12;
                }
            }
            this.f();
            this.g();
            final d d9;
            if ((d9 = this).f == 13) {
                d9.q = d9.n * 3;
            }
            else if (d9.f == 12) {
                d9.q = 60;
            }
            else if (d9.f == 11) {
                d9.q = 600;
            }
            else {
                if (d9.f != 5) {
                    throw new RuntimeException("Unhandled Interval Units [" + d9.f + "]");
                }
                d9.q = 3600;
            }
            com.bullionvault.chart.c.h.f("Sleep Seconds = " + d9.q);
            if (d9.q < 600) {
                d9.r = Resources.b("poll.next_update") + " " + d9.q + " " + Resources.b("poll.seconds");
            }
            else {
                d9.r = "";
            }
            if (this.h != null) {
                if (this.u != null) {
                    this.h.c(this.u);
                }
                this.h.b(Resources.b("poll.updated") + " " + this.r);
            }
            if (this.g != null) {
                this.g.c();
            }
        }
        catch (Exception ex2) {
            throw new s(Resources.b("error.unable_to_read_data") + ex2, ex2);
        }
    }
    
    private void d() {
        final int size = this.a.size();
        com.bullionvault.chart.c.h.e("Point count: " + size);
        final Vector a = this.t.a;
        com.bullionvault.chart.c.h.e("Last Date: " + this.e + " -- Update Date " + this.t.e);
        com.bullionvault.chart.c.h.e("Difference between update and last end_times: " + (this.t.e.getTime() - this.e.getTime()));
        c c = this.a.elementAt(0);
        com.bullionvault.chart.c.h.e("Removing any overlap points from 'old' list");
        com.bullionvault.chart.c.h.e("Update start_date; " + this.t.d + " BEFORE?...");
        com.bullionvault.chart.c.h.e("Previous end_date; " + c.e);
        while (this.t.d.before(c.e) || this.t.d.equals(c.e)) {
            this.a.removeElementAt(0);
            c = this.a.elementAt(0);
            com.bullionvault.chart.c.h.e("Removed overlapping point");
            com.bullionvault.chart.c.h.e("Update start_date; " + this.t.d);
            com.bullionvault.chart.c.h.e("Previous end_date; " + c.e);
        }
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(c.e);
        instance.add(this.f, this.n);
        com.bullionvault.chart.c.h.e("Inserting any blank points required");
        com.bullionvault.chart.c.h.e("Previous end_date; " + instance.getTime() + " BEFORE?... ");
        com.bullionvault.chart.c.h.e("Update start_date; " + this.t.d);
        while (instance.getTime().before(this.t.d)) {
            final c c2;
            (c2 = new c()).d = false;
            c2.e = instance.getTime();
            this.a.insertElementAt(c2, 0);
            com.bullionvault.chart.c.h.d("Missing data - inserted blank point for " + c2.e);
            com.bullionvault.chart.c.h.e("Vector size: " + this.a.size());
            instance.add(this.f, this.n);
            com.bullionvault.chart.c.h.e("Previous end_date; " + instance.getTime() + " BEFORE?... ");
            com.bullionvault.chart.c.h.e("Update start_date; " + this.t.d);
        }
        for (int i = a.size() - 1; i >= 0; --i) {
            com.bullionvault.chart.c.h.e("Copying in new data");
            this.a.insertElementAt(a.elementAt(i), 0);
        }
        while (this.a.size() > size) {
            com.bullionvault.chart.c.h.e("Removing old, oversized point");
            this.a.removeElementAt(this.a.size() - 1);
        }
        com.bullionvault.chart.c.h.e("Number of Points to Plot: " + this.a.size());
        this.h();
        this.e = this.t.e;
        this.d = this.a.elementAt(this.a.size() - 1).e;
        this.e();
        this.g();
    }
    
    private void a(final com.bullionvault.chart.d.b b) {
        final com.bullionvault.chart.d.b[] c = b.c("bar");
        this.a = new Vector();
        for (int i = 0; i < c.length; ++i) {
            final com.bullionvault.chart.d.b b2 = c[i];
            final c c2 = new c();
            if (b2.a) {
                c2.a = b2.d("high").b();
                c2.b = b2.d("low").b();
                c2.c = b2.d("close").b();
                c2.d = true;
                com.bullionvault.chart.c.h.f("[" + this.a.size() + "] " + c2);
            }
            else {
                c2.d = false;
                com.bullionvault.chart.c.h.f("[" + this.a.size() + "] " + c2);
            }
            this.a.addElement(c2);
        }
    }
    
    private void b(final com.bullionvault.chart.d.b b) {
        final com.bullionvault.chart.d.b[] c = b.c("point");
        this.a = new Vector();
        for (int i = 0; i < c.length; ++i) {
            final com.bullionvault.chart.d.b b2 = c[i];
            final c c2 = new c();
            if (b2.a) {
                c2.c = b2.d("price").b();
                c2.d = true;
                com.bullionvault.chart.c.h.f("[" + this.a.size() + "] " + c2);
            }
            else {
                c2.d = false;
                com.bullionvault.chart.c.h.f("[" + this.a.size() + "] " + c2);
            }
            this.a.addElement(c2);
        }
    }
    
    private void e() {
        this.b = Float.MAX_VALUE;
        this.c = Float.MIN_VALUE;
        int n = -1;
        int n2 = -1;
        float b = 0.0f;
        float c = 0.0f;
        for (int i = 0; i < this.a.size(); ++i) {
            final c c2;
            if ((c2 = this.a.elementAt(i)).d) {
                if (this.j == 1) {
                    b = c2.c;
                    c = c2.c;
                }
                else if (this.j == 2) {
                    b = c2.b;
                    c = c2.a;
                }
                if (b < this.b) {
                    this.b = b;
                    n = i;
                }
                if (c > this.c) {
                    this.c = c;
                    n2 = i;
                }
            }
        }
        com.bullionvault.chart.c.h.e("Minimum HLC [" + n + "] = " + this.a.elementAt(n));
        com.bullionvault.chart.c.h.e("Maximum HLC [" + n2 + "] = " + this.a.elementAt(n2));
        com.bullionvault.chart.c.h.e("range: miny,maxy = " + this.b + " " + this.c);
    }
    
    private void f() {
        final e e;
        (e = new e(this.f)).c();
        e.a(this.e);
        c c = null;
        for (int i = 0; i < this.a.size(); ++i) {
            (c = (c)this.a.elementAt(i)).f = e.b();
            (c.e = e.a()).getTime();
            com.bullionvault.chart.c.h.g(i + " >> " + c.f + " :: " + e.b(16) + " --- LOCAL= " + c.e);
            e.a(this.f, -this.n);
        }
        this.d = c.e;
        com.bullionvault.chart.c.h.e("Time Range = " + this.d + " -> " + this.e);
    }
    
    private void g() {
        int n = 0;
        final e e;
        (e = new e(this.f)).a(this.e);
        int b = 0;
        for (int i = 0; i < this.a.size(); ++i) {
            this.a.elementAt(i);
            if (i > 0 && e.b(this.f) > b) {
                com.bullionvault.chart.c.h.g("Adding X Mark to Display " + e.b(this.f) + " > " + e.c(this.f));
                this.a.elementAt(i - 1);
                ++n;
            }
            b = e.b(this.f);
            e.a(this.f, -this.n);
        }
        if (n == 0) {
            com.bullionvault.chart.c.h.c("Error Generating Calendar Info - no X marks added, probably 'latest time' not set cleanly");
        }
    }
    
    public final int c() {
        if (this.a == null) {
            return -1;
        }
        return this.a.size();
    }
    
    private void h() {
        final Calendar instance;
        (instance = Calendar.getInstance()).setTime(this.a.elementAt(0).e);
        String string = "";
        for (int i = 0; i < this.a.size(); ++i) {
            final Date e = this.a.elementAt(i).e;
            string = string + "HLC [" + i + "] date --> " + e + " == should be => " + instance.getTime() + "\n";
            if (!instance.getTime().equals(e)) {
                com.bullionvault.chart.c.h.c(string);
                throw new RuntimeException("Update synchronisation FAILED.");
            }
            instance.add(this.f, -this.n);
        }
        com.bullionvault.chart.c.h.e("Update synchronisation matched ok.");
    }
}
