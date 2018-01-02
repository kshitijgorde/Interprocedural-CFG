// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.a;

import java.awt.Frame;
import java.awt.Event;
import java.awt.Canvas;

public abstract class b extends Canvas implements c
{
    protected String a;
    protected boolean for;
    protected Object do;
    protected String if;
    
    public String a() {
        return this.if;
    }
    
    public b() {
        this.a = "BS Abstract Button";
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.for = true;
                this.repaint();
                break;
            }
            case 502: {
                this.postEvent(new Event(this, 1001, this.a));
                this.for = false;
                this.repaint();
                break;
            }
            case 504: {
                if (this.do != null) {
                    ((Frame)this.do).setCursor(12);
                    break;
                }
                break;
            }
            case 505: {
                if (this.do != null) {
                    ((Frame)this.do).setCursor(0);
                }
                if (this.for) {
                    this.for = false;
                    this.repaint();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a(final String if1) {
        this.if = if1;
    }
}
