// 
// Decompiled by Procyon v0.5.30
// 

package ABLwidgets;

import java.awt.Event;
import java.awt.Component;

public class timer extends Thread
{
    Component a;
    Object b;
    long c;
    boolean d;
    boolean e;
    
    public timer(final Component a, final String s) {
        super(s);
        this.d = false;
        this.e = false;
        this.a = a;
        this.setDaemon(true);
    }
    
    public void a(final long c, final Object b, final boolean e) {
        this.d = (c > 0L);
        this.c = c;
        this.e = e;
        if (this.d) {
            this.b = b;
        }
        else {
            this.b = null;
        }
        this.interrupt();
    }
    
    public void a(final boolean d) {
        this.d = d;
        this.interrupt();
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    final Object b = this.b;
                    do {
                        if (this.d) {
                            Thread.sleep(this.c);
                        }
                        else {
                            Thread.sleep(100000L);
                        }
                    } while (!this.d);
                    if (b != null) {
                        final Event event = new Event(this.a, 0, this.b);
                        event.arg = b;
                        try {
                            if (this.e) {
                                this.a(0L, null, this.e);
                            }
                            this.a.handleEvent(event);
                        }
                        catch (Exception ex) {
                            abljem.b("Error calling parent.handleEvent(): " + ex);
                        }
                    }
                }
            }
            catch (InterruptedException ex2) {
                continue;
            }
            break;
        }
    }
}
