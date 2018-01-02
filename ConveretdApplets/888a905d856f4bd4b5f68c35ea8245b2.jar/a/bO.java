// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Frame;
import java.awt.Event;

public final class bO extends af
{
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
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
                        final dD dd = new dD(super.e.getText().trim());
                        final dI di;
                        (di = new dI((this.getTitle().indexOf("FTP") < 0) ? 68096 : 68097, 1)).q(0, 0, new dD(text));
                        di.q(0, 1, dd);
                        super.q.o(di);
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
    
    public bO(final Frame frame, final String s, final ap ap, final boolean b) {
        super(frame, s, ap, b, true);
    }
}
