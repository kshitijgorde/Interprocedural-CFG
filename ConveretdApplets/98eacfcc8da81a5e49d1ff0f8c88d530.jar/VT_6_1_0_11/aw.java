// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.event.ComponentListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JComponent;
import java.awt.event.ComponentAdapter;

public final class aw extends ComponentAdapter
{
    private JComponent a;
    private Set b;
    
    public aw(final JComponent a) {
        this.b = new HashSet();
        (this.a = a).addComponentListener(new E(this));
    }
    
    public final void a(final JComponent component) {
        this.b.add(component);
        component.setSize(this.a.getSize());
    }
    
    public final void b(final JComponent component) {
        this.b.remove(component);
    }
    
    static Set a(final aw aw) {
        return aw.b;
    }
}
