// 
// Decompiled by Procyon v0.5.30
// 

package jap;

import java.util.Enumeration;
import java.util.Vector;
import javax.swing.DefaultListModel;

public class JAPInfoServiceListModel extends DefaultListModel
{
    public void setData(final Vector vector) {
        synchronized (this) {
            final int size = this.size();
            this.removeAllElements();
            if (size > 0) {
                this.fireIntervalRemoved(this, 0, size - 1);
            }
            final Enumeration<E> elements = vector.elements();
            while (elements.hasMoreElements()) {
                this.addElement(elements.nextElement());
            }
            if (this.size() > 0) {
                this.fireIntervalAdded(this, 0, this.size() - 1);
            }
        }
    }
}
