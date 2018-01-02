// 
// Decompiled by Procyon v0.5.30
// 

package com.bitcoinplus.applet;

import java.util.Iterator;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.util.ArrayList;
import java.util.List;

public class MiningApplet extends d
{
    public static MiningApplet miningApplet;
    private List a;
    private e b;
    private a c;
    private b d;
    private boolean e;
    private int f;
    
    public MiningApplet() {
        this.a = new ArrayList();
        MiningApplet.miningApplet = this;
    }
    
    public void e() {
        final String parameter = this.getParameter("c");
        new StringBuilder().append("Server URL: ").append(parameter).toString();
        this.f = Integer.parseInt(this.getParameter("d"));
        new StringBuilder().append("Difficulty num zeros: ").append(this.f).toString();
        final com.bitcoinplus.applet.c.a a = new com.bitcoinplus.applet.c.a();
        final String parameter2 = this.getParameter("f");
        new StringBuilder().append("javascriptGetWorkMethod: ").append(parameter2).toString();
        this.b = new e(parameter, parameter2, this.f, a);
        final String parameter3 = this.getParameter("g");
        new StringBuilder().append("javascriptSubmitWorkMethod: ").append(parameter3).toString();
        final String parameter4 = this.getParameter("h");
        new StringBuilder().append("javascriptGotPayoutMethod: ").append(parameter4).toString();
        final String parameter5 = this.getParameter("i");
        new StringBuilder().append("clientType (a = coin generation page, b = api): ").append(parameter5).toString();
        final String parameter6 = this.getParameter("j");
        new StringBuilder().append("extraInfoForSubmitWork: ").append(parameter6).toString();
        this.c = new a(parameter, parameter3, parameter4, parameter5, parameter6, this.f, a);
        final String parameter7 = this.getParameter("b");
        new StringBuilder().append("paymentTargets: ").append(parameter7).toString();
        com.bitcoinplus.applet.a.a = parameter7;
        final String parameter8 = this.getParameter("e");
        new StringBuilder().append("javascriptInitMethod: ").append(parameter8).append(", calling it right now").toString();
        JSObject.getWindow((Applet)this).call(parameter8, new Object[0]);
        final String parameter9 = this.getParameter("a");
        new StringBuilder().append("Autostart mining: ").append(parameter9).toString();
        if (Boolean.parseBoolean(parameter9)) {
            this.a();
        }
    }
    
    public void f() {
        this.b();
    }
    
    public void a() {
        if (this.e) {
            return;
        }
        this.e = true;
        if (this.d == null) {
            this.d = new b(this.a);
        }
        for (int availableProcessors = Runtime.getRuntime().availableProcessors(), i = 0; i < availableProcessors; ++i) {
            final c c2;
            final c c;
            (c = (c2 = new c(this.f, this.b, this.c))).b = true;
            final Thread thread;
            (thread = new Thread(c)).setPriority(1);
            thread.start();
            this.a.add(c2);
        }
    }
    
    public void b() {
        final Iterator<c> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().b = false;
        }
        this.a.clear();
        this.e = false;
    }
    
    public int c() {
        if (this.d == null) {
            return 0;
        }
        return this.d.a;
    }
    
    public int d() {
        if (this.c == null) {
            return 0;
        }
        return this.c.a();
    }
    
    public void g(final String s) {
        new StringBuilder().append("applet gotWorkFromServer via js, workJSON: ").append(s).toString();
        this.b.a(s);
    }
    
    public void h(final String s) {
        new StringBuilder().append("applet submittedWorkToServer via js, workResponseJSON: ").append(s).toString();
        this.c.a(s);
    }
    
    public void i(final String a) {
        new StringBuilder().append("setting paymentTargets: ").append(a).toString();
        a.a = a;
    }
}
