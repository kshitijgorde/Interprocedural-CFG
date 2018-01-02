// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;

public class az extends Panel
{
    protected cr a;
    protected cr b;
    protected K a;
    protected cs a;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof aP) {
                    if (((aP)event.arg).c) {
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
                    this.a.a((aP)this.a.a());
                    return true;
                }
                if (event.target == this.b) {
                    this.a.i();
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
    
    public final boolean a(final aP ap) {
        return this.a.a((a)ap);
    }
    
    public final void a(final aP ap) {
        synchronized (this.a) {
            final int a;
            if ((a = this.a.a((a)ap)) == -1) {
                this.a.c(ap);
            }
            else {
                this.a.a(ap, a);
            }
            if (this.a.b() == a) {
                this.a.a(a);
            }
            this.a.a(ap, new Color(ap.e), Color.white, new Color(ap.h));
        }
    }
    
    public az() {
        this.a = new cr(aS.a(451));
        this.b = new cr(aS.a(452));
        this.a = new K();
    }
    
    static {
        new Color(153);
        new Color(10079487);
    }
}
