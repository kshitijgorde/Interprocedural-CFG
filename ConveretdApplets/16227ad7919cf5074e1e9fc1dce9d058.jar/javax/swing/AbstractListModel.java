// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ListDataEvent;
import java.util.EventListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.EventListenerList;
import java.io.Serializable;

public abstract class AbstractListModel implements ListModel, Serializable
{
    protected EventListenerList listenerList;
    static /* synthetic */ Class class$javax$swing$event$ListDataListener;
    
    public AbstractListModel() {
        this.listenerList = new EventListenerList();
    }
    
    public void addListDataListener(final ListDataListener listDataListener) {
        this.listenerList.add((AbstractListModel.class$javax$swing$event$ListDataListener != null) ? AbstractListModel.class$javax$swing$event$ListDataListener : (AbstractListModel.class$javax$swing$event$ListDataListener = class$("javax.swing.event.ListDataListener")), listDataListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireContentsChanged(final Object o, final int n, final int n2) {
        final Object[] listenerList = this.listenerList.getListenerList();
        ListDataEvent listDataEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractListModel.class$javax$swing$event$ListDataListener != null) ? AbstractListModel.class$javax$swing$event$ListDataListener : (AbstractListModel.class$javax$swing$event$ListDataListener = class$("javax.swing.event.ListDataListener")))) {
                if (listDataEvent == null) {
                    listDataEvent = new ListDataEvent(o, 0, n, n2);
                }
                ((ListDataListener)listenerList[i + 1]).contentsChanged(listDataEvent);
            }
        }
    }
    
    protected void fireIntervalAdded(final Object o, final int n, final int n2) {
        final Object[] listenerList = this.listenerList.getListenerList();
        ListDataEvent listDataEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractListModel.class$javax$swing$event$ListDataListener != null) ? AbstractListModel.class$javax$swing$event$ListDataListener : (AbstractListModel.class$javax$swing$event$ListDataListener = class$("javax.swing.event.ListDataListener")))) {
                if (listDataEvent == null) {
                    listDataEvent = new ListDataEvent(o, 1, n, n2);
                }
                ((ListDataListener)listenerList[i + 1]).intervalAdded(listDataEvent);
            }
        }
    }
    
    protected void fireIntervalRemoved(final Object o, final int n, final int n2) {
        final Object[] listenerList = this.listenerList.getListenerList();
        ListDataEvent listDataEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractListModel.class$javax$swing$event$ListDataListener != null) ? AbstractListModel.class$javax$swing$event$ListDataListener : (AbstractListModel.class$javax$swing$event$ListDataListener = class$("javax.swing.event.ListDataListener")))) {
                if (listDataEvent == null) {
                    listDataEvent = new ListDataEvent(o, 2, n, n2);
                }
                ((ListDataListener)listenerList[i + 1]).intervalRemoved(listDataEvent);
            }
        }
    }
    
    public abstract Object getElementAt(final int p0);
    
    public abstract int getSize();
    
    public void removeListDataListener(final ListDataListener listDataListener) {
        this.listenerList.remove((AbstractListModel.class$javax$swing$event$ListDataListener != null) ? AbstractListModel.class$javax$swing$event$ListDataListener : (AbstractListModel.class$javax$swing$event$ListDataListener = class$("javax.swing.event.ListDataListener")), listDataListener);
    }
}
