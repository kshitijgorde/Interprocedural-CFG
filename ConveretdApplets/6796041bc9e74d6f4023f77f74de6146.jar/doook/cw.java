// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Frame;
import java.awt.Event;

public final class cw extends bK
{
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    super.a.s();
                    return true;
                }
                if (event.key == 27) {
                    super.b.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == super.a) {
                    final String text = super.c.getText();
                    final String text2 = super.d.getText();
                    if (text.length() == 0) {
                        super.c.requestFocus();
                        this.c();
                    }
                    else if (text.length() < 3) {
                        super.c.requestFocus();
                        this.b();
                    }
                    else if (text.equals(text2)) {
                        final a a = new a(super.e.getText().trim());
                        final cD cd = new cD(68096, 1);
                        cd.a(0, 0, new a(text));
                        cd.a(0, 1, a);
                        cd.a(0, 0, -999);
                        cd.a(0, 1, -999);
                        super.a.o(cd);
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
    
    public cw(final Frame frame, final u u, final boolean b) {
        super(frame, u, b);
    }
}
