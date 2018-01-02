// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Frame;

public final class db extends ca
{
    public String q;
    
    public db(final Frame frame, final cs cs, final cj cj) {
        super(frame, cs, cj);
        this.q = null;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target != this.q) {
                    break;
                }
                this.q = this.q.getText();
                if (new dD(this.q).equals(this.q.q)) {
                    this.q = true;
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
