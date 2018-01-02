// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

public class q
{
    public static void a(final Thread thread, final String s) {
        a(thread, s, false);
    }
    
    public static void a(final Thread thread, final String s, final boolean b) {
        if (thread == null) {
            return;
        }
        if (thread.isAlive()) {
            if (!Thread.currentThread().equals(thread)) {
                com.screencastomatic.play.q.a("Waiting for thread: " + s);
                try {
                    if (!b) {
                        thread.join();
                    }
                    else {
                        thread.join(1000L);
                        if (thread.isAlive()) {
                            com.screencastomatic.play.q.a("Inturrupting thread: " + s);
                            try {
                                thread.interrupt();
                                thread.join();
                            }
                            catch (SecurityException ex2) {
                                com.screencastomatic.play.q.a("Security Exception while killing thread: " + s);
                            }
                        }
                    }
                }
                catch (InterruptedException ex) {
                    if (thread.isAlive()) {
                        com.screencastomatic.play.q.a("Inturrupted while waiting for thread: " + s);
                        ex.printStackTrace();
                    }
                }
                com.screencastomatic.play.q.a("Done waiting for thread: " + s);
            }
            else {
                com.screencastomatic.play.q.a("We are currently in thread so not waiting: " + s);
            }
        }
        else {
            com.screencastomatic.play.q.a("Thread is already done: " + s);
        }
    }
}
