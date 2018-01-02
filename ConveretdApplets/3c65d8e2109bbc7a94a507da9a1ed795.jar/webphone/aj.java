// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class aj extends MouseAdapter
{
    private webphone a;
    
    aj(final webphone a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a.label7_mouseClicked(mouseEvent);
    }
}
