// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.EventListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import java.io.Serializable;

public class DefaultSingleSelectionModel implements SingleSelectionModel, Serializable
{
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList;
    private int index;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    public DefaultSingleSelectionModel() {
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
        this.index = -1;
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener != null) ? DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener : (DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void clearSelection() {
        this.setSelectedIndex(-1);
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener != null) ? DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener : (DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public int getSelectedIndex() {
        return this.index;
    }
    
    public boolean isSelected() {
        boolean b = false;
        if (this.getSelectedIndex() != -1) {
            b = true;
        }
        return b;
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener != null) ? DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener : (DefaultSingleSelectionModel.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void setSelectedIndex(final int index) {
        if (this.index != index) {
            this.index = index;
            this.fireStateChanged();
        }
    }
}
