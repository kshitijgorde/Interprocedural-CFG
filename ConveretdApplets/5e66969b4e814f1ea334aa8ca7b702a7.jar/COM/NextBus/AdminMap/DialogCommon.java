// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;
import COM.NextBus.Applets.d;
import java.awt.Component;
import java.awt.Button;
import java.awt.Dialog;

public abstract class DialogCommon extends Dialog
{
    private static final long serialVersionUID = -6220313840769615194L;
    protected Button _closeButton;
    protected Component _parent;
    protected d _lookAndFeel;
    private boolean _populated;
    
    public DialogCommon(final Frame frame, final String s, final Component parent, final d lookAndFeel) {
        super(frame, s, false);
        this._populated = false;
        this.toFront();
        this._lookAndFeel = lookAndFeel;
        this._parent = parent;
        this.setBackground(lookAndFeel.x());
        this.enableEvents(64L);
        lookAndFeel.a(this._closeButton = new Button(a.b("Close")));
        this._closeButton.addActionListener(new c(this));
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.setVisible(false);
        }
        super.processWindowEvent(windowEvent);
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.e();
            this.requestFocus();
            this.toFront();
        }
    }
    
    public abstract void a();
    
    public void e() {
        if (!this._populated) {
            this._populated = true;
            this.a();
        }
    }
}
