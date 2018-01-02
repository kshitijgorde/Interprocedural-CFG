// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Frame;

public final class ae extends ad
{
    public String q;
    
    public ae(final Frame frame, final cM cm, final db db) {
        super(frame, cm, db);
        this.q = null;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target != this.q) {
                    break;
                }
                this.q = this.q.getText();
                if (new ep(this.q).equals(this.q.q)) {
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
