// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.d;

import java.beans.PropertyChangeEvent;
import javax.swing.JDialog;
import java.beans.PropertyChangeListener;

final class a implements PropertyChangeListener
{
    private final /* synthetic */ JDialog a;
    
    a(final JDialog a) {
        this.a = a;
    }
    
    public final void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.a.pack();
    }
}
