// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing;

import jmaster.util.C.B;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

public class ViewSwitch extends JPanel
{
    private static final long B = 929627916343598294L;
    protected Component A;
    static /* synthetic */ Class class$java$awt$Component;
    
    public ViewSwitch() {
        this.setLayout(new BorderLayout());
    }
    
    public Component getCurrentView() {
        return this.A;
    }
    
    public synchronized void setCurrentView(final Component a) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "setCurrentView", new Class[] { (ViewSwitch.class$java$awt$Component == null) ? (ViewSwitch.class$java$awt$Component = class$("java.awt.Component")) : ViewSwitch.class$java$awt$Component }, new Object[] { a });
            return;
        }
        if (this.A != null) {
            this.remove(this.A);
        }
        if ((this.A = a) != null) {
            this.add(a, "Center");
        }
        this.revalidate();
        this.validate();
        this.repaint();
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
