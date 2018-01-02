// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;

public class aa extends Panel
{
    protected i a;
    protected i b;
    protected y a;
    protected bh a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof ai) {
                    if (((ai)event.arg).a) {
                        this.a.b();
                    }
                    else {
                        this.a.a();
                    }
                }
                return true;
            }
            case 702: {
                this.a.b();
                return true;
            }
            case 1001: {
                if (event.target == this.a || event.target == this.a) {
                    this.a.a((ai)this.a.a());
                    return true;
                }
                if (event.target == this.b) {
                    this.a.b();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    public final boolean a(final ai ai) {
        return this.a.a((a)ai);
    }
    
    public final void a(final ai ai) {
        synchronized (this.a) {
            final int a;
            if ((a = this.a.a((a)ai)) == -1) {
                this.a.c(ai);
            }
            else {
                this.a.a(ai, a);
            }
            if (this.a.b() == a) {
                this.a.a(a);
            }
            this.a.a(ai, new Color(ai.b), Color.white, new Color(ai.e));
        }
    }
    
    public aa() {
        this.a = new i(ak.a(451));
        this.b = new i(ak.a(452));
        this.a = new y();
    }
    
    static {
        new Color(153);
        new Color(10079487);
    }
}
