// 
// Decompiled by Procyon v0.5.30
// 

package org.litecoinpool.miner;

import java.util.Observable;
import java.net.MalformedURLException;
import java.security.PrivilegedAction;
import java.security.AccessController;
import java.net.URL;
import java.util.Observer;
import java.applet.Applet;

public class EmbeddableMiner extends Applet implements Observer
{
    private static boolean a;
    private String b;
    private int c;
    private double d;
    private e e;
    private long f;
    private long g;
    private long h;
    private long i;
    
    static {
        EmbeddableMiner.a = false;
    }
    
    public EmbeddableMiner() {
        this.e = null;
        this.f = 0L;
        this.g = 0L;
    }
    
    @Override
    public void init() {
        try {
            this.c = Integer.parseInt(this.getParameter("threads"));
        }
        catch (Exception ex) {
            this.c = -1;
        }
        if (this.c <= 0) {
            this.c += Runtime.getRuntime().availableProcessors();
        }
        if (this.c <= 0) {
            return;
        }
        try {
            this.d = Double.parseDouble(this.getParameter("throttle"));
        }
        catch (Exception ex2) {
            this.d = 0.8;
        }
        this.b = this.getParameter("auth");
        if (this.b == null) {
            this.b = "guest.1:1";
        }
        final String parameter;
        if ((parameter = this.getParameter("autostart")) != null && parameter.equalsIgnoreCase("false")) {
            return;
        }
        this.startMining();
        try {
            final int int1;
            if ((int1 = Integer.parseInt(this.getParameter("autostop"))) > 0) {
                new a(this, int1).start();
            }
        }
        catch (NumberFormatException ex3) {}
    }
    
    @Override
    public void destroy() {
        this.stopMining();
    }
    
    public synchronized void startMining() {
        if (EmbeddableMiner.a) {
            return;
        }
        EmbeddableMiner.a = true;
        this.h = System.currentTimeMillis();
        this.i = 0L;
        try {
            this.e = new e(new URL("http://litecoinpool.org:9332/"), this.b, 5000L, 30000L, this.c, this.d);
            AccessController.doPrivileged((PrivilegedAction<Object>)new b(this));
            this.e.addObserver(this);
        }
        catch (MalformedURLException ex) {}
    }
    
    public synchronized void stopMining() {
        if (this.e != null) {
            this.e.b();
            this.e = null;
            EmbeddableMiner.a = false;
        }
    }
    
    public synchronized boolean isMining() {
        return this.e != null;
    }
    
    @Override
    public void update(final Observable observable, final Object o) {
        final g g;
        if ((g = (g)o) == org.litecoinpool.miner.g.j) {
            ++this.f;
            return;
        }
        if (g == org.litecoinpool.miner.g.k) {
            ++this.g;
        }
    }
    
    public long getValidCount() {
        return this.f;
    }
    
    public long getStaleCount() {
        return this.g;
    }
    
    public int getSpeed() {
        int n;
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            final long a = this.e.a();
            n = (int)(1000L * (a - this.i) / (currentTimeMillis - this.h));
            this.h = currentTimeMillis;
            this.i = a;
        }
        catch (Exception ex) {
            n = 0;
        }
        return n;
    }
}
