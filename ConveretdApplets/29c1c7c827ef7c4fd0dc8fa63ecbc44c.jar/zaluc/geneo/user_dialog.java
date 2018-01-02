// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

class user_dialog extends Dialog
{
    Button b_ok;
    Button b_cancel;
    
    user_dialog(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
        this.b_ok = new Button("OK");
        this.setLayout(new FlowLayout());
        this.add(this.b_ok);
        this.resize(200, 40);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.hide();
            this.dispose();
        }
        return true;
    }
}
