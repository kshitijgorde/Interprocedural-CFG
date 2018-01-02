// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import ca.odell.glazedlists.impl.swing.SwingThreadProxyEventList;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.EventList;

public final class GlazedListsSwing
{
    private GlazedListsSwing() {
        throw new UnsupportedOperationException();
    }
    
    public static TransformedList a(final EventList list) {
        return new SwingThreadProxyEventList(list);
    }
    
    public static boolean b(final EventList list) {
        return list instanceof SwingThreadProxyEventList;
    }
}
