// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;

final class bz extends ci
{
    private final ao q;
    
    public bz(final ao q, final W w) {
        this.q = q;
        super(w);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.target != super.q) {
                    break;
                }
                final int length;
                if ((length = super.q.getText().length()) > 256) {
                    super.q.setText(super.q.getText().substring(0, 256));
                    super.q.setCaretPosition(256);
                    return true;
                }
                ao.q(this.q).setText("" + length);
                break;
            }
        }
        return super.handleEvent(event);
    }
}
