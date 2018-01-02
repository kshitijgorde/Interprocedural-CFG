// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Event;
import java.awt.TextField;

class MudTextField extends TextField
{
    InputHandler ih;
    
    MudTextField(final InputHandler ih) {
        this.ih = ih;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.target == this && e.id == 1004 && this.ih.ILselecting && this.ih.aselected) {
            this.selectAll();
        }
        return super.handleEvent(e);
    }
}
