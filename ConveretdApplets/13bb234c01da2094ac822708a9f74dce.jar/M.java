import ca.odell.glazedlists.matchers.Matcher;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import java.util.Map;
import ca.odell.glazedlists.matchers.AbstractMatcherEditor;

// 
// Decompiled by Procyon v0.5.30
// 

public class M extends AbstractMatcherEditor
{
    private Map b;
    private boolean c;
    
    public M(final boolean c, final Map b) {
        this.b = b;
        this.c = c;
    }
    
    public void a(final JPopupMenu popupMenu) {
        boolean b = true;
        boolean b2 = false;
        for (int i = 0; i < popupMenu.getComponentCount(); ++i) {
            final JComponent component = (JComponent)popupMenu.getComponent(i);
            if (component instanceof JCheckBoxMenuItem) {
                final boolean state = ((JCheckBoxMenuItem)component).getState();
                b &= state;
                b2 |= state;
            }
        }
        if (b) {
            this.b();
        }
        else if (!b && !b2) {
            this.c();
        }
        else {
            this.a(this.a(popupMenu, this.b));
        }
    }
    
    public aa a(final JPopupMenu popupMenu, final Map map) {
        if (this.c) {
            return new aj(popupMenu, map, null);
        }
        return new a(popupMenu, map, null);
    }
}
