// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class c extends MouseAdapter
{
    private final j a;
    
    c(final j a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2 && this.a.n() != null) {
            this.a.e();
        }
    }
}
