import java.awt.Event;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class IRCQNetDialog extends Dialog
{
    Panel mParent;
    
    public IRCQNetDialog(final Frame frame, final String s, final boolean b, final Panel mParent) {
        super(frame, s, b);
        this.mParent = mParent;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
}
