// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet.support;

import ji.util.e;
import java.awt.Toolkit;
import java.util.Enumeration;
import java.util.EmptyStackException;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import ji.util.d;
import java.awt.Window;
import ji.applet.jiApplet;
import ji.io.h;
import ji.util.i;
import java.awt.AWTEvent;
import java.util.Hashtable;
import ji.ext.cb;
import java.awt.EventQueue;

public class ca extends EventQueue implements cb
{
    private boolean a;
    private boolean b;
    static boolean c;
    private int d;
    private Hashtable e;
    boolean f;
    
    public void postEvent(final AWTEvent awtEvent) {
        super.postEvent(awtEvent);
    }
    
    public synchronized void push(final EventQueue eventQueue) {
        if (!this.equals(eventQueue)) {
            if (i.c(124)) {
                h.d("--", "installing hangpatch onto eventqueue");
            }
            super.push(eventQueue);
        }
        else if (i.c(124)) {
            h.d("--", "hangpatch already installed for this eventqueue");
        }
    }
    
    public ca(final jiApplet jiApplet, final Window window) {
        this.a = false;
        this.b = false;
        this.d = 0;
        this.e = new Hashtable();
        this.f = false;
    }
    
    public void a(final String s, final Window window, final jiApplet jiApplet) {
        final z0 z0 = new z0(s, window, jiApplet);
        if (EventQueue.isDispatchThread()) {
            z0.run();
        }
        else {
            EventQueue.invokeLater(z0);
        }
        while (z0.a()) {
            ji.util.d.b(100, 654, s);
        }
        this.b = true;
    }
    
    public void a() {
        this.a = true;
    }
    
    protected void dispatchEvent(final AWTEvent awtEvent) {
        if (awtEvent instanceof ComponentEvent && !(awtEvent instanceof WindowEvent) && !(awtEvent instanceof MouseEvent) && !(awtEvent instanceof FocusEvent)) {
            if (!this.a) {
                ca.c = true;
                super.dispatchEvent(awtEvent);
                ca.c = false;
            }
            else if (i.c(124)) {
                h.d("--", "ignored paint");
            }
        }
        else {
            super.dispatchEvent(awtEvent);
        }
    }
    
    public void a(final Window window) {
        if (this.e.containsKey(window)) {
            synchronized (this) {
                ++this.d;
            }
            final z1 z1 = this.e.get(window);
            if (i.c(124)) {
                h.d(z1.c, "hangpatch window halting");
            }
            synchronized (z1.a.getTreeLock()) {
                this.a();
            }
            // monitorexit(z1.a(z1).getTreeLock())
            final Thread c = z1.b;
            if (EventQueue.isDispatchThread() && !Thread.currentThread().equals(c)) {
                while (ca.c) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
    }
    
    public void b(final Window window) {
        if (this.e.containsKey(window)) {
            synchronized (this) {
                --this.d;
            }
            final z1 z1 = this.e.remove(window);
            final String b = z1.c;
            z1.a = null;
            z1.b = null;
            z1.c = null;
            synchronized (this) {
                if (this.d == 0) {
                    if (i.c(124)) {
                        h.d(b, "hangpatch window releasing HALT");
                    }
                    this.a = false;
                }
                else if (i.c(124)) {
                    h.d(b, "hangpatch window NOT releasing halt, existing clients");
                }
                return;
            }
        }
        if (i.c(124)) {
            h.d("--", "hangpatch window wasn't in hash");
        }
    }
    
    public void b() {
        try {
            synchronized (jiApplet.EventProcessorLOCK) {
                if (this.e.isEmpty()) {
                    try {
                        this.c();
                        if (i.c(124)) {
                            h.d("--", "************* popping queue ".concat(String.valueOf(String.valueOf(this))));
                        }
                        this.pop();
                    }
                    catch (EmptyStackException ex2) {}
                }
            }
            // monitorexit(jiApplet.EventProcessorLOCK)
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void c() {
        if (i.c(124)) {
            h.d("--", "hangpatch resources released");
        }
        if (this.e != null) {
            final Enumeration<Object> keys = this.e.keys();
            while (keys.hasMoreElements()) {
                final z1 z1 = this.e.remove(keys.nextElement());
                if (z1 != null) {
                    z1.a = null;
                    z1.b = null;
                    z1.c = null;
                }
            }
        }
    }
    
    static {
        ca.c = false;
    }
    
    private class z1
    {
        private jiApplet a;
        private Thread b;
        private String c;
        
        private z1(final ca ca) {
        }
    }
    
    private class z0 implements Runnable
    {
        private Window a;
        private jiApplet b;
        private String c;
        private boolean d;
        
        public z0(final String c, final Window a, final jiApplet b) {
            this.d = true;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public void run() {
            final Thread currentThread = Thread.currentThread();
            final z1 z1 = new z1((ael)null);
            z1.a = this.b;
            z1.b = currentThread;
            z1.c = this.c;
            Thread.currentThread().setName(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Thread.currentThread().getName()))).append("-").append(this.c))));
            if (i.c(124)) {
                h.d(this.c, "inside dispatch: ".concat(String.valueOf(String.valueOf(Toolkit.getDefaultToolkit().getSystemEventQueue()))));
            }
            ca.this.e.put(this.a, z1);
            if (i.c(131)) {
                h.d(this.c, "discovered localDispatchThread: ".concat(String.valueOf(String.valueOf(currentThread))));
            }
            if (ji.util.e.a7()) {
                if (i.c(131)) {
                    h.d(this.c, "thread is real dispatch thread");
                }
            }
            else if (i.c(131)) {
                h.d(this.c, "thread is not real dispatch thread");
            }
            this.d = false;
        }
        
        public boolean a() {
            return this.d;
        }
    }
    
    interface ael
    {
    }
}
