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

public final class dF extends Dialog
{
    private aC q;
    public static boolean q;
    public static Checkbox q;
    private dH q;
    public aq q;
    private Vector q;
    public ad q;
    public ad w;
    private Point q;
    
    public dF(final aC q, final Point q2) {
        super(new Frame(), be.w("Select emotions"), false);
        dF.q = false;
        this.q = q;
        this.q = q2;
        this.q = (dH)this.q.q();
        this.q = this.q.q.q();
    }
    
    public final synchronized void hide() {
        dF.q = false;
        super.hide();
    }
    
    public void q() {
        this.q.q();
        for (int i = 0; i < this.q.size(); ++i) {
            final aj aj;
            if ((aj = this.q.elementAt(i)).q(36)) {
                this.q.q(aj);
            }
        }
    }
    
    public final synchronized void w() {
        dF.q = true;
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
                    dN.q(" " + ((aj)this.q.q()).a, this.q.q());
                    return true;
                }
                if (event.target instanceof cq) {
                    try {
                        dN.q(" " + ((aj)this.q.q()).a, this.q.q());
                    }
                    catch (NoSuchElementException ex) {}
                    if (dF.q.getState()) {
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
