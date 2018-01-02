import com.daysofwonder.applet.filters.TableFilter;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import com.daysofwonder.applet.ar;
import java.util.Map;
import javax.swing.JPopupMenu;
import ca.odell.glazedlists.matchers.Matcher;

// 
// Decompiled by Procyon v0.5.30
// 

abstract class aa implements Matcher
{
    private JPopupMenu a;
    private Map b;
    
    aa(final JPopupMenu a, final Map b) {
        this.a = a;
        this.b = b;
    }
    
    public boolean b(final ar ar) {
        boolean b = false;
        for (int i = 0; i < this.a.getComponentCount(); ++i) {
            final JComponent component = (JComponent)this.a.getComponent(i);
            if (component instanceof JCheckBoxMenuItem) {
                final JCheckBoxMenuItem checkBoxMenuItem = (JCheckBoxMenuItem)component;
                final boolean a = this.b.get(checkBoxMenuItem.getActionCommand()).a(ar);
                if (a && checkBoxMenuItem.getState()) {
                    b = true;
                }
                else if (a && !checkBoxMenuItem.getState()) {
                    return false;
                }
            }
        }
        return b;
    }
}
