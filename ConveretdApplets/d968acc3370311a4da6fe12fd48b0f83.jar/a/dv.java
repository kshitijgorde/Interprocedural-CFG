// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.awt.Event;

public final class dv extends dw
{
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    super.q.r();
                    return true;
                }
                if (event.key == 27) {
                    super.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == super.q) {
                    final String text = super.q.getText();
                    final String text2 = super.w.getText();
                    if (text.length() == 0) {
                        super.q.requestFocus();
                        this.e();
                    }
                    else if (text.length() < 3) {
                        super.q.requestFocus();
                        this.r();
                    }
                    else if (text.equals(text2)) {
                        final ep ep = new ep(super.e.getText().trim());
                        final es es;
                        (es = new es(68096, 1)).q(0, 0, new ep(text));
                        es.q(0, 1, ep);
                        super.q.q(es);
                        this.dispose();
                    }
                    else {
                        this.q();
                    }
                }
                else if (event.target == super.w) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public dv(final Frame frame, final String s, final cV cv, final boolean b) {
        super(frame, s, cv, b, true);
    }
}
