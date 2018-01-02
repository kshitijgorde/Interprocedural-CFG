// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.awt.Component;
import java.awt.AWTEvent;

class SingleEventPostThread extends Thread
{
    AWTEvent event;
    
    public SingleEventPostThread(final AWTEvent event) {
        this.event = event;
        this.start();
    }
    
    public void run() {
        try {
            final Object source = this.event.getSource();
            if (source instanceof Component) {
                ((Component)source).dispatchEvent(this.event);
            }
        }
        catch (ThreadDeath threadDeath) {}
        catch (Throwable t) {
            System.out.println("SingleEventPostThread, run(): exception dispatching event");
            t.printStackTrace();
        }
    }
}
