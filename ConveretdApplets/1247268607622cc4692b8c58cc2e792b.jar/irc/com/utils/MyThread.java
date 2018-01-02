// 
// Decompiled by Procyon v0.5.30
// 

package irc.com.utils;

import java.util.Enumeration;
import java.util.Hashtable;

public class MyThread implements Runnable
{
    private static boolean DEBUG;
    protected static Hashtable list;
    protected Boolean DEAD;
    private Thread thread;
    private Runnable run;
    
    public static void displayThreads() {
        if (MyThread.DEBUG) {
            if (MyThread.DEBUG) {
                System.out.println("[d\u00e9but Threads actifs]");
            }
            final Thread[] array = new Thread[200];
            final int enumerate = Thread.enumerate(array);
            System.out.println("[tableau Thread actif]" + enumerate);
            for (int i = 0; i < enumerate; ++i) {
                System.out.println("[Thread actif]" + array[i].getClass() + "  " + array[i].getName());
            }
        }
    }
    
    public static void killall() {
        displayThreads();
        synchronized (MyThread.list) {
            final Enumeration<String> keys = MyThread.list.keys();
            while (keys.hasMoreElements()) {
                ((MyThread)MyThread.list.get(keys.nextElement())).kill();
            }
        }
    }
    
    public static void sleep(final long n) throws InterruptedException {
        if (MyThread.DEBUG) {
            System.out.println("[ Thread sleep]");
        }
        Thread.sleep(n);
        if (MyThread.DEBUG) {
            System.out.println("[sleep end]");
        }
    }
    
    public MyThread(final Runnable runnable, final String s) {
        this.DEAD = Boolean.FALSE;
        this.thread = null;
        this.run = null;
        this.thread = new Thread(runnable, s);
        if (MyThread.DEBUG) {
            System.out.println(" [constructor Thread ] " + s);
        }
        this.DEAD = Boolean.FALSE;
        synchronized (MyThread.list) {
            MyThread.list.put(s, this);
        }
    }
    
    public MyThread(final String s) {
        this.DEAD = Boolean.FALSE;
        this.thread = null;
        this.run = null;
        try {
            this.thread = new Thread(this, s);
        }
        catch (SecurityException ex) {
            throw new RuntimeException(ex.getMessage() + " - thread name:" + s);
        }
        if (MyThread.DEBUG) {
            System.out.println(" [constructor Thread ] " + s);
        }
        this.DEAD = Boolean.FALSE;
        synchronized (MyThread.list) {
            MyThread.list.put(s, this);
        }
    }
    
    public void destroy() {
        if (MyThread.DEBUG) {
            System.out.println("[destroy Thread]" + this.thread.getName());
        }
        this.thread.destroy();
    }
    
    public String getName() {
        return this.thread.getName();
    }
    
    public void interrupt() {
        if (MyThread.DEBUG) {
            System.out.println("[interrupt Thread]" + this.thread.getName());
        }
        this.thread.interrupt();
        if (MyThread.DEBUG) {
            System.out.println("[interrupt end Thread]" + this.thread.getName());
        }
    }
    
    public boolean isDead() {
        final boolean booleanValue;
        synchronized (this.DEAD) {
            booleanValue = this.DEAD;
        }
        return booleanValue;
    }
    
    public void kill() {
        synchronized (this.DEAD) {
            this.DEAD = Boolean.TRUE;
        }
    }
    
    @Override
    public void run() {
        if (this.run == null) {
            System.out.println("ERREUR DE THREAD !!!!!!");
        }
        else {
            this.run.run();
        }
    }
    
    public void setContextClassLoader(final ClassLoader contextClassLoader) {
        if (MyThread.DEBUG) {
            System.out.println("[setContextClassLoader Thread]" + this.thread.getName());
        }
        this.thread.setContextClassLoader(contextClassLoader);
        if (MyThread.DEBUG) {
            System.out.println("[setContextClassLoader end Thread]" + this.thread.getName());
        }
    }
    
    public void setPriority(final int priority) {
        this.thread.setPriority(priority);
    }
    
    public void setRunable(final Runnable run) {
        this.run = run;
    }
    
    public void start() {
        if (MyThread.DEBUG) {
            System.out.println("[start Thread]" + this.getClass() + "  " + this.thread.getName());
        }
        this.thread.start();
    }
    
    static {
        MyThread.DEBUG = false;
        MyThread.list = new Hashtable();
    }
}
