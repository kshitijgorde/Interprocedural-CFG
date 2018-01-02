// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Vector;
import java.io.Serializable;

public class DefaultComboBoxModel extends AbstractListModel implements MutableComboBoxModel, Serializable
{
    Vector objects;
    Object selectedObject;
    
    public DefaultComboBoxModel() {
        this.objects = new Vector();
    }
    
    public DefaultComboBoxModel(final Vector objects) {
        this.objects = objects;
        if (this.getSize() > 0) {
            this.selectedObject = this.getElementAt(0);
        }
    }
    
    public DefaultComboBoxModel(final Object[] array) {
        (this.objects = new Vector()).ensureCapacity(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.objects.addElement(array[i]);
        }
        if (this.getSize() > 0) {
            this.selectedObject = this.getElementAt(0);
        }
    }
    
    public void addElement(final Object selectedItem) {
        this.objects.addElement(selectedItem);
        this.fireIntervalAdded(this, this.objects.size() - 1, this.objects.size() - 1);
        if (this.objects.size() == 1 && this.selectedObject == null && selectedItem != null) {
            this.setSelectedItem(selectedItem);
        }
    }
    
    public Object getElementAt(final int n) {
        if (n >= 0 && n < this.objects.size()) {
            return this.objects.elementAt(n);
        }
        return null;
    }
    
    public int getIndexOf(final Object o) {
        return this.objects.indexOf(o);
    }
    
    public Object getSelectedItem() {
        return this.selectedObject;
    }
    
    public int getSize() {
        return this.objects.size();
    }
    
    public void insertElementAt(final Object o, final int n) {
        this.objects.insertElementAt(o, n);
        this.fireIntervalAdded(this, n, n);
    }
    
    public void removeAllElements() {
        if (this.objects.size() > 0) {
            final int n = 0;
            final int n2 = this.objects.size() - 1;
            this.objects.removeAllElements();
            this.selectedObject = null;
            this.fireIntervalRemoved(this, n, n2);
        }
    }
    
    public void removeElement(final Object o) {
        final int index = this.objects.indexOf(o);
        if (index != -1) {
            this.removeElementAt(index);
        }
    }
    
    public void removeElementAt(final int n) {
        if (this.getElementAt(n) == this.selectedObject) {
            if (n == 0) {
                this.setSelectedItem((this.getSize() == 1) ? null : this.getElementAt(n + 1));
            }
            else {
                this.setSelectedItem(this.getElementAt(n - 1));
            }
        }
        this.objects.removeElementAt(n);
        this.fireIntervalRemoved(this, n, n);
    }
    
    public void setSelectedItem(final Object selectedObject) {
        if ((this.selectedObject != null && !this.selectedObject.equals(selectedObject)) || (this.selectedObject == null && selectedObject != null)) {
            this.selectedObject = selectedObject;
            this.fireContentsChanged(this, -1, -1);
        }
    }
}
