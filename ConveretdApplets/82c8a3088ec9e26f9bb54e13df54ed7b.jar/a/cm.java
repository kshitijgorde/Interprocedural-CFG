// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Point;
import java.util.Vector;
import java.awt.Checkbox;
import java.awt.Dialog;

public final class cm extends Dialog
{
    private ai q;
    public static boolean q;
    public static Checkbox q;
    private co q;
    public X q;
    private Vector q;
    public M q;
    public M w;
    private Point q;
    
    public cm(final Frame frame, final ai q, final Point q2) {
        super(frame, ak.q("Select emotions"), false);
        cm.q = false;
        this.q = q;
        this.q = q2;
        this.q = (co)this.q.q();
        this.q = this.q.q.q();
    }
    
    public final synchronized void hide() {
        cm.q = false;
        super.hide();
    }
    
    public void q() {
        this.q.q();
        for (int i = 0; i < this.q.size(); ++i) {
            final Q q;
            if ((q = this.q.elementAt(i)).q(36)) {
                this.q.q(q);
            }
        }
    }
    
    public final synchronized void w() {
        cm.q = true;
        this.setLocation(this.q);
        this.doLayout();
        this.show(true);
        this.setVisible(true);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.q();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.q();
                    return true;
                }
                if (event.target == this.w) {
                    cs.q(" " + ((Q)this.q.q()).o, this.q.q());
                    return true;
                }
                if (event.target instanceof bn) {
                    try {
                        cs.q(" " + ((Q)this.q.q()).o, this.q.q());
                    }
                    catch (NoSuchElementException ex) {}
                    if (cm.q.getState()) {
                        this.q.q();
                    }
                }
                return true;
            }
            case 201: {
                this.q.q();
                break;
            }
        }
        return super.handleEvent(event);
    }
}