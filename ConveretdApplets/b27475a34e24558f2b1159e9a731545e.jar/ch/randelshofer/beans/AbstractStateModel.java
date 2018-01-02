// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.beans;

import java.util.EventListener;
import ch.randelshofer.gui.event.ChangeListener;
import ch.randelshofer.gui.event.ChangeEvent;
import ch.randelshofer.gui.event.EventListenerList;

public class AbstractStateModel
{
    protected EventListenerList listenerList;
    protected ChangeEvent changeEvent;
    static /* synthetic */ Class class$ch$randelshofer$gui$event$ChangeListener;
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (this.listenerList == null) {
            this.listenerList = new EventListenerList();
        }
        this.listenerList.add((AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        if (this.listenerList == null) {
            this.listenerList = new EventListenerList();
        }
        this.listenerList.remove((AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    protected void fireStateChanged() {
        if (this.listenerList != null) {
            final Object[] listenerList = this.listenerList.getListenerList();
            for (int i = listenerList.length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == ((AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractStateModel.class$ch$randelshofer$gui$event$ChangeListener)) {
                    if (this.changeEvent == null) {
                        this.changeEvent = new ChangeEvent(this);
                    }
                    ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
                }
            }
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
