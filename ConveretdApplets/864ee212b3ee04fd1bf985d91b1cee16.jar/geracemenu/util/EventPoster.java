// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.util;

import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.EventQueue;

public class EventPoster
{
    protected static final EventQueue SystemEventQueue;
    
    public static void postEvent(final AWTEvent awtEvent) {
        if (EventPoster.SystemEventQueue != null) {
            EventPoster.SystemEventQueue.postEvent(awtEvent);
            return;
        }
        new SingleEventPostThread(awtEvent);
    }
    
    static {
        try {
            SystemEventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        }
        catch (SecurityException ex) {
            SystemEventQueue = null;
        }
    }
}
