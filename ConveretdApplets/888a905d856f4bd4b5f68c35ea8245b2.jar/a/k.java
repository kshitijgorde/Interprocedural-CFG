// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;

public final class k extends TextField
{
    public Component q;
    
    public k(final int n) {
        super(8);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1005: {
                if (event.target == this && this.q != null && !"".equals(this.getText())) {
                    this.q.setBackground(ds.q(this.getText()));
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
