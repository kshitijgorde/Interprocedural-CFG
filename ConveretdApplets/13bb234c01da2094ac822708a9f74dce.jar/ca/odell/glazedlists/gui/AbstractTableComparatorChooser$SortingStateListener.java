// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class AbstractTableComparatorChooser$SortingStateListener implements PropertyChangeListener
{
    final /* synthetic */ AbstractTableComparatorChooser a;
    
    private AbstractTableComparatorChooser$SortingStateListener(final AbstractTableComparatorChooser a) {
        this.a = a;
    }
    
    public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
        this.a.b();
    }
}
