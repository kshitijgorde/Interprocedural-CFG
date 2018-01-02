// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.ActionListener;

public class m implements ActionListener
{
    private ActionListener a;
    private Vector b;
    
    public void a(final f f) {
        this.b.addElement(f);
        f.a(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean j = i.j;
        final int size = this.b.size();
        int n = 0;
        while (true) {
            Label_0042: {
                if (!j) {
                    break Label_0042;
                }
                ((f)this.b.elementAt(n)).a(false);
                ++n;
            }
            if (n >= size) {
                ((f)actionEvent.getSource()).a(true);
                this.c(actionEvent.getActionCommand());
                return;
            }
            continue;
        }
    }
    
    public f a(final String s) {
        final boolean j = i.j;
        final int size = this.b.size();
        int n = 0;
        while (true) {
            Label_0056: {
                if (!j) {
                    break Label_0056;
                }
                final f f = this.b.elementAt(n);
                if (s.toLowerCase().equals(f.h().toLowerCase())) {
                    return f;
                }
                ++n;
            }
            if (n >= size) {
                return null;
            }
            continue;
        }
    }
    
    public f b(final String s) {
        final boolean j = i.j;
        final int size = this.b.size();
        f f = null;
        for (int i = 0; i < size; ++i) {
            final f f2 = this.b.elementAt(i);
            if (s.toLowerCase().equals(f2.h().toLowerCase())) {
                f = f2;
                if (!j) {
                    continue;
                }
            }
            f2.a(false);
        }
        if (f != null) {
            f.a(true);
        }
        return f;
    }
    
    private void c(final String s) {
        if (this.a == null) {
            return;
        }
        this.a.actionPerformed(new ActionEvent(this, 1001, s));
    }
    
    public void a(final ActionListener a) {
        this.a = a;
    }
    
    public void a() {
        this.b.removeAllElements();
    }
    
    public void b(final f f) {
        this.b.removeElement(f);
    }
    
    public m() {
        this.b = new Vector();
    }
}
