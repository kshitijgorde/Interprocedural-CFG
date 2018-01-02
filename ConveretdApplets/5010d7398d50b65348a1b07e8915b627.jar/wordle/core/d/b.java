// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import java.util.concurrent.atomic.AtomicBoolean;
import java.awt.event.ActionListener;

final class b implements ActionListener
{
    private final /* synthetic */ AtomicBoolean a;
    private final /* synthetic */ JDialog b;
    
    b(final AtomicBoolean a, final JDialog b) {
        this.a = a;
        this.b = b;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.a.set(actionEvent.getActionCommand().equals("OK"));
        this.b.setVisible(false);
    }
}
