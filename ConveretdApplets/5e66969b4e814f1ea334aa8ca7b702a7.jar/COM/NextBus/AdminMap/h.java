// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import COM.NextBus.AdminMap.Toolbar.b;
import java.util.Iterator;
import COM.NextBus.HttpMapClient.ConnectionException;
import java.util.Map;
import java.util.Vector;
import java.util.Timer;
import java.util.List;
import java.util.TimerTask;

final class h extends TimerTask
{
    private /* synthetic */ r a;
    
    h(final r a) {
        this.a = a;
    }
    
    public final void run() {
        try {
            boolean b = false;
            synchronized (this.a.h) {
                if (this.a.i) {
                    b = true;
                    r.a(this.a, false);
                }
            }
            if (b) {
                final h h;
                h.a.b(r.a);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
