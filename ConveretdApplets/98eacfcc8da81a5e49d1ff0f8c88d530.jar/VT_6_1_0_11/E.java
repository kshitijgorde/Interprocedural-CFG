// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Iterator;
import javax.swing.JComponent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;

final class E extends ComponentAdapter
{
    private final aw a;
    
    E(final aw a) {
        this.a = a;
    }
    
    public final void componentResized(final ComponentEvent componentEvent) {
        final Iterator<JComponent> iterator = aw.a(this.a).iterator();
        while (iterator.hasNext()) {
            final JComponent component;
            (component = iterator.next()).setSize(componentEvent.getComponent().getSize());
            component.revalidate();
        }
    }
}
