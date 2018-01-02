// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Event;
import java.awt.TextField;

public class FocusTextField extends TextField
{
    MudFrame ma;
    
    public FocusTextField(final int i, final MudFrame ma) {
        super(i);
        this.ma = ma;
        this.setForeground(MudFrame.feforeground);
        this.setBackground(MudFrame.febackground);
    }
    
    public boolean keyDown(final Event e, final int key) {
        if (key == 9) {
            this.ma.nextOne();
            return true;
        }
        return false;
    }
}
