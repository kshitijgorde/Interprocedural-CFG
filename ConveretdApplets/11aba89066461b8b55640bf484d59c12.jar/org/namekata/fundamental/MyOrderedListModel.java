// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import java.util.ResourceBundle;
import javax.swing.AbstractListModel;

public class MyOrderedListModel extends AbstractListModel
{
    private MyOrderedList myOrderedList;
    private int size;
    
    public MyOrderedListModel() {
        this.myOrderedList = new MyOrderedList();
        this.size = 0;
    }
    
    @Override
    public int getSize() {
        return this.size;
    }
    
    @Override
    public Object getElementAt(final int parm1) {
        return this.myOrderedList.getElementAt(parm1);
    }
    
    public void add(final Object obj) {
        final int ind = this.myOrderedList.add(obj);
        ++this.size;
        this.fireIntervalAdded(this, ind, ind);
    }
    
    public Object removeAt(final int ind) {
        final Object ret = this.myOrderedList.removeAt(ind);
        this.fireIntervalRemoved(this, 0, this.size--);
        return ret;
    }
    
    public void remove(final Object obj) {
        if (this.myOrderedList.remove(obj)) {
            this.fireIntervalRemoved(this, 0, this.size--);
        }
        else {
            System.err.println("MyOrderedListModel: " + obj + ResourceBundle.getBundle("org.namekata.fundamental/MyOrderedListModel").getString("FailToBeRemoved"));
        }
    }
    
    public void reviseAt(final Object obj, final int ind) {
        this.myOrderedList.reviseAt(obj, ind);
        this.fireContentsChanged(this, ind, ind);
    }
}
