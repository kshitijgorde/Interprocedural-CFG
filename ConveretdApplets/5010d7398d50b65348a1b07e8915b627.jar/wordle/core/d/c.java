// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JColorChooser;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

final class c extends MouseAdapter
{
    private /* synthetic */ g a;
    
    c(final g a) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final Color showDialog;
        if ((showDialog = JColorChooser.showDialog(this.a, "Select a color", this.a.a())) != null) {
            this.a.a(showDialog);
        }
    }
}
