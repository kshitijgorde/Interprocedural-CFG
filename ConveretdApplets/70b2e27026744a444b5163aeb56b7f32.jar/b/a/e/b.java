// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class b extends MouseAdapter
{
    private final l a;
    
    b(final l a) {
        this.a = a;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.a.a) {
            this.a.a = false;
            this.a.setForeground(this.a.f);
            this.a.setText(this.a.c);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.a.a) {
            this.a.a = true;
            this.a.setForeground(this.a.e);
            this.a.setText(this.a.c);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a.a(mouseEvent.getModifiers());
    }
}
