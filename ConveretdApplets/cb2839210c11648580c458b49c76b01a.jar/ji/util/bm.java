// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.io.h;
import java.awt.Event;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class bm extends ActionEvent
{
    public bm(final Object o, final Runnable runnable) {
        super(new Object(), 1001, "invocation");
        super.source = new sy(runnable);
    }
    
    public class sy extends Component
    {
        Runnable a;
        
        public sy(final bm bm, final Runnable a) {
            this.a = a;
        }
        
        public boolean action(final Event event, final Object o) {
            if (i.c(124)) {
                h.d("", String.valueOf(String.valueOf(Thread.currentThread().getName())).concat("Shutdown dialog: action"));
            }
            this.a.run();
            this.a = null;
            return true;
        }
    }
}
