// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.swing;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.util.ArrayList;
import ca.odell.glazedlists.EventList;
import java.util.List;
import ca.odell.glazedlists.TransformedList;
import ca.odell.glazedlists.ListSelection;
import javax.swing.ListSelectionModel;
import ca.odell.glazedlists.ListSelection$Listener;

class EventSelectionModel$SwingSelectionListener implements ListSelection$Listener
{
    final /* synthetic */ EventSelectionModel a;
    
    private EventSelectionModel$SwingSelectionListener(final EventSelectionModel a) {
        this.a = a;
    }
    
    public void a(final int n, final int n2) {
        this.a.a(n, n2);
    }
}
