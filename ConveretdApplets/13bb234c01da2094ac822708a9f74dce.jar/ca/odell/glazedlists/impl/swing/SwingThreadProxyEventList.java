// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.swing;

import javax.swing.SwingUtilities;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.impl.gui.ThreadProxyEventList;

public class SwingThreadProxyEventList extends ThreadProxyEventList
{
    public SwingThreadProxyEventList(final EventList list) {
        super(list);
    }
    
    protected void a(final Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        }
        else {
            SwingUtilities.invokeLater(runnable);
        }
    }
}
