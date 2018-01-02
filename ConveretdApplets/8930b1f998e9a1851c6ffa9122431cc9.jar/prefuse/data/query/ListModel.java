// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.query;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import prefuse.util.collections.CopyOnWriteArrayList;
import java.util.ArrayList;
import javax.swing.MutableComboBoxModel;
import javax.swing.DefaultListSelectionModel;

public class ListModel extends DefaultListSelectionModel implements MutableComboBoxModel
{
    private ArrayList m_items;
    private CopyOnWriteArrayList m_lstnrs;
    
    public ListModel() {
        this.m_items = new ArrayList();
        this.m_lstnrs = new CopyOnWriteArrayList();
    }
    
    public ListModel(final Object[] array) {
        this.m_items = new ArrayList();
        this.m_lstnrs = new CopyOnWriteArrayList();
        for (int i = 0; i < array.length; ++i) {
            this.m_items.add(array[i]);
        }
    }
    
    private boolean isMultipleSelection() {
        return this.getMaxSelectionIndex() - this.getMinSelectionIndex() > 0;
    }
    
    public Object getSelectedItem() {
        final int minSelectionIndex = this.getMinSelectionIndex();
        return (minSelectionIndex == -1) ? null : this.m_items.get(minSelectionIndex);
    }
    
    public void setSelectedItem(final Object o) {
        final int index = this.m_items.indexOf(o);
        if (index < 0) {
            return;
        }
        if (!this.isMultipleSelection() && index == this.getMinSelectionIndex()) {
            return;
        }
        super.setSelectionInterval(index, index);
        this.fireDataEvent(this, 0, -1, -1);
    }
    
    public int getSize() {
        return this.m_items.size();
    }
    
    public Object getElementAt(final int n) {
        return this.m_items.get(n);
    }
    
    public void addElement(final Object selectedItem) {
        this.m_items.add(selectedItem);
        final int n = this.m_items.size() - 1;
        this.fireDataEvent(this, 1, n, n);
        if (n >= 0 && this.isSelectionEmpty() && selectedItem != null) {
            this.setSelectedItem(selectedItem);
        }
    }
    
    public void insertElementAt(final Object o, final int n) {
        this.m_items.add(n, o);
        this.fireDataEvent(this, 1, n, n);
    }
    
    public void removeElement(final Object o) {
        final int index = this.m_items.indexOf(o);
        if (index >= 0) {
            this.removeElementAt(index);
        }
    }
    
    public void removeElementAt(final int n) {
        if (!this.isMultipleSelection() && n == this.getMinSelectionIndex()) {
            final int n2 = (n == 0) ? ((this.getSize() == 1) ? -1 : (n + 1)) : (n - 1);
            this.setSelectedItem((n2 == -1) ? null : this.m_items.get(n2));
        }
        this.m_items.remove(n);
        this.fireDataEvent(this, 2, n, n);
    }
    
    public void addListDataListener(final ListDataListener listDataListener) {
        if (!this.m_lstnrs.contains(listDataListener)) {
            this.m_lstnrs.add(listDataListener);
        }
    }
    
    public void removeListDataListener(final ListDataListener listDataListener) {
        this.m_lstnrs.remove(listDataListener);
    }
    
    protected void fireDataEvent(final Object o, final int n, final int n2, final int n3) {
        final Object[] array = this.m_lstnrs.getArray();
        if (array.length > 0) {
            final ListDataEvent listDataEvent = new ListDataEvent(o, n, n2, n3);
            for (int i = 0; i < array.length; ++i) {
                ((ListDataListener)array[i]).contentsChanged(listDataEvent);
            }
        }
    }
}
