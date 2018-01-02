// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;

final class ap extends Q
{
    private final ao q;
    
    public ap(final ao q, final bI bi) {
        this.q = q;
        super(bi);
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
