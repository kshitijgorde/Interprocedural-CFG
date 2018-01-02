// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.IOException;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Hashtable;
import java.util.Observable;

public class aE extends Observable implements Runnable
{
    private Hashtable a;
    private String b;
    private List c;
    private boolean d;
    private int e;
    private Object f;
    private static /* synthetic */ boolean g;
    
    public aE() {
        this.a = new Hashtable();
        this.c = new ArrayList();
        this.d = false;
        this.e = 1;
        this.f = new Object();
        final Thread thread;
        (thread = new Thread(this)).setDaemon(true);
        thread.start();
    }
    
    public final synchronized void a(final String s, final boolean b) {
        if (b) {
            if (!this.c.contains(s)) {
                this.c.add(s);
            }
        }
        else if (this.c.contains(s)) {
            this.c.remove(s);
        }
    }
    
    public final synchronized void a(final String s) {
        if (!aE.g && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        final String b = au.b(s);
        LinkedList<String> list;
        if (this.a.containsKey(b)) {
            list = this.a.get(b);
        }
        else {
            list = new LinkedList<String>();
            this.a.put(b, list);
        }
        list.addLast(s);
        this.notifyAll();
    }
    
    private synchronized String b() {
        final String c;
        if (this.e == 1 && (c = this.c()) != null) {
            return c;
        }
        if (this.c.size() > 0) {
            return this.c.remove(0);
        }
        return null;
    }
    
    private synchronized String c() {
        if (this.b == null) {
            return null;
        }
        if (!this.a.containsKey(this.b)) {
            return null;
        }
        final LinkedList<String> list;
        if ((list = this.a.get(this.b)).size() == 0) {
            return null;
        }
        return list.removeFirst();
    }
    
    private void a(final String s, final Exception ex) {
        this.setChanged();
        this.notifyObservers(new bc(this, s, ex));
    }
    
    public void run() {
        while (true) {
            final String b;
            if (!this.d && (b = this.b()) != null) {
                if (!aE.g && b.length() <= 0) {
                    throw new AssertionError();
                }
                try {
                    final String s = b;
                    if (!aE.g && (s == null || s.length() <= 0)) {
                        throw new AssertionError();
                    }
                    final a a;
                    if ((a = V.a(s)).a == null) {
                        throw new RuntimeException("null thumb!");
                    }
                    if (a.c.width == -1 || a.c.height == -1) {
                        throw new am();
                    }
                    if (a.b) {
                        aA.a(s, a.c);
                    }
                    else {
                        aA.a(s, new Dimension(a.a.getWidth(null), a.a.getHeight(null)));
                    }
                    final a a2 = a;
                    final String s2 = b;
                    final a a3 = a2;
                    final String s3 = s2;
                    this.setChanged();
                    this.notifyObservers(new j(this, s3, a3));
                }
                catch (IOException ex) {
                    this.a(b, ex);
                }
                catch (am am) {
                    this.a(b, am);
                }
                catch (M m) {
                    this.a(b, m);
                }
                catch (ah ah) {
                    System.out.println("TRAPPED memory exception: " + au.a(b));
                    this.a(b, ah);
                }
            }
            else {
                try {
                    synchronized (this.f) {
                        this.f.notifyAll();
                    }
                    if (this.d) {
                        return;
                    }
                    if (this.e == 2) {
                        this.d = true;
                        return;
                    }
                    synchronized (this) {
                        this.wait();
                    }
                }
                catch (Exception ex2) {}
                if (this.d) {
                    return;
                }
                continue;
            }
        }
    }
    
    public final synchronized void a() {
        this.d = true;
        this.notifyAll();
        System.out.println("disposed ImageLoader...");
    }
    
    public final synchronized void b(final String b) {
        if (!aE.g && (b == null || b.length() <= 0)) {
            throw new AssertionError();
        }
        this.b = b;
        this.notifyAll();
    }
    
    static {
        aE.g = !aE.class.desiredAssertionStatus();
    }
}
