// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import netscape.javascript.JSObject;
import dk.midas.web.chart.a.b;
import java.util.Hashtable;
import java.applet.Applet;
import dk.midas.web.chart.applet.d.d;
import dk.midas.web.chart.a.e;

public class a implements Runnable, e, d
{
    public Applet aX;
    Hashtable aY;
    Hashtable a4;
    Thread a0;
    DataSource aU;
    DataSource a3;
    int aW;
    boolean a5;
    b a6;
    String a2;
    String aV;
    String aZ;
    int a1;
    
    public a(final Applet ax) {
        this.aY = new Hashtable();
        this.a4 = new Hashtable();
        this.aU = null;
        this.a3 = null;
        this.a5 = false;
        this.a6 = null;
        this.aV = "";
        this.aZ = "";
        this.aX = ax;
        this.a2 = ((AppletChart)ax).getProperties().getParameter("ServerStr");
        final char c = '#';
        final char c2 = '_';
        final z z = new z(ax);
        this.aV = z.if();
        this.aZ = z.a().toString();
        this.aZ = this.aZ.replace(c, c2);
        if (this.aZ.length() > 100) {
            this.aZ = this.aZ.substring(0, 99);
        }
        this.a0 = new Thread(this);
        this.a1 = ((AppletChart)ax).ao();
        this.a6 = null;
        this.a6 = new b(this, this.a2, this.aV, this.aZ);
        this.a0.start();
    }
    
    public synchronized void a(final DataSource dataSource) {
        if (((AppletChart)this.aX).f6) {
            if (this.a6 != null) {
                this.a6.null();
            }
            this.aU = dataSource;
            this.a3 = dataSource;
        }
    }
    
    public void for(final int n) {
        final Integer n2 = new Integer(n);
        if (this.a6 != null) {
            this.a6.null();
        }
    }
    
    public void do() {
        this.a5 = true;
        if (this.a6 != null) {
            this.a6.a();
        }
    }
    
    public void if() {
        if (this.a6 != null) {
            this.a6.g();
        }
    }
    
    public void for() {
        if (this.a6 != null) {
            this.a6.long();
        }
    }
    
    public void run() {
        while (!this.a5) {
            if (this.aU != null && this.aU.r().if() != null && !this.aU.C().do() && this.aU.B() > 0L) {
                this.a6.if((long)this.a1);
                if (a8.if) {
                    this.a6.a(this.aU.r().if(), (int)this.aU.C().int(), 1200);
                }
                else {
                    this.a6.a(this.aU.r().if(), (int)this.aU.C().int(), (int)this.aU.B());
                }
                this.aU = null;
                this.a6.b();
            }
            try {
                final Thread a0 = this.a0;
                Thread.sleep(200L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void do(final String ej) {
        System.out.println(ej);
        final AppletChart appletChart = (AppletChart)this.aX;
        appletChart.showStatus(ej);
        final be es = appletChart.f7.eS;
        be.ej = ej;
    }
    
    public void a(final b b, final int n, final int n2, final int n3, final int n4) {
        this.a3.o().do();
        try {
            this.a3.a(b, n, n2, n3, n4);
        }
        finally {
            this.a3.o().a();
        }
    }
    
    public void a(final b b, final int n, final int n2) {
        this.a3.o().do();
        try {
            this.a3.a(b, n, n2);
        }
        finally {
            this.a3.o().a();
        }
    }
    
    public void a(final b b, final int n, final boolean b2) {
        this.a3.o().do();
        try {
            this.a3.a(b, n, b2);
        }
        finally {
            this.a3.o().a();
        }
    }
    
    public void a(final b b, final int n) {
        this.a3.o().do();
        try {
            this.a3.a(b, n);
        }
        finally {
            this.a3.o().a();
        }
    }
    
    public void if(final String s) {
        System.out.println("CHART INFO : " + s);
    }
    
    public void do(final int n) {
        try {
            Thread.sleep(n * 1000);
        }
        catch (Exception ex) {}
        JSObject.getWindow(this.aX).eval("location.reload(true)");
    }
}
