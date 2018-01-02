// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Frame;
import java.awt.Event;

public final class af extends j
{
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    super.a.c();
                    return true;
                }
                if (event.key == 27) {
                    super.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == super.a) {
                    final String trim = super.a.getText().trim();
                    final String trim2 = super.b.getText().trim();
                    if (super.c.getText().trim().length() == 0) {
                        super.c.requestFocus();
                        this.c();
                    }
                    else if (trim.length() == 0) {
                        super.a.requestFocus();
                        this.b();
                    }
                    else if (trim.length() < 3) {
                        super.a.requestFocus();
                        this.d();
                    }
                    else if (trim.equals(trim2)) {
                        final aV av = new aV(super.c.getText().trim());
                        final r r;
                        (r = new r(68096, 1)).a(0, 0, new aV(trim));
                        r.a(0, 1, av);
                        r.a(0, 0, -999);
                        r.a(0, 1, -999);
                        super.a.o(r);
                        this.dispose();
                    }
                    else {
                        this.a();
                    }
                }
                else if (event.target == super.b) {
                    this.dispose();
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public af(final Frame frame, final cx cx, final boolean b) {
        super(frame, cx, b);
    }
}
