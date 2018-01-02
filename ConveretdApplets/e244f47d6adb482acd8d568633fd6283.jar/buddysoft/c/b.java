// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.c;

import java.awt.Event;
import java.awt.Cursor;
import java.awt.Canvas;

public class b extends Canvas implements c, e
{
    protected String if;
    protected String a;
    protected String for;
    protected boolean do;
    
    public b() {
        this.if = "BS Abstract Button";
        this.setCursor(new Cursor(12));
    }
    
    public String a() {
        if (this.a == null) {
            return this.if;
        }
        return this.a;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.do = true;
                this.postEvent(new Event(this, 1001, this.if));
                this.repaint();
                break;
            }
            case 502: {
                this.do = false;
                this.repaint();
                break;
            }
            case 505: {
                if (this.do) {
                    this.do = false;
                    this.repaint();
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public String if() {
        if (this.for == null) {
            return this.if;
        }
        return this.for;
    }
}
