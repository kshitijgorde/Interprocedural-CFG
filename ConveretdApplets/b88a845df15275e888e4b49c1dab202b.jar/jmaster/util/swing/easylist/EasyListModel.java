// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.easylist;

import jmaster.util.C.B;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

public class EasyListModel extends AbstractListModel
{
    private static final long B = -922815820092950580L;
    protected List A;
    static /* synthetic */ Class class$java$lang$Object;
    
    public EasyListModel() {
        this.A = new ArrayList();
    }
    
    public int getSize() {
        return this.A.size();
    }
    
    public Object getElementAt(final int n) {
        return this.getItem(n);
    }
    
    public Object getItem(final int n) {
        return this.A.get(n);
    }
    
    public int indexOfItem(final Object o) {
        return this.A.indexOf(o);
    }
    
    public void addItem(final Object o) {
        this.addItem(-1, o);
    }
    
    public void addItem(int size, final Object o) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "addItem", new Class[] { Integer.TYPE, (EasyListModel.class$java$lang$Object == null) ? (EasyListModel.class$java$lang$Object = class$("java.lang.Object")) : EasyListModel.class$java$lang$Object }, new Object[] { new Integer(size), o });
            return;
        }
        if (size == -1) {
            size = this.A.size();
        }
        this.A.add(size, o);
        this.fireIntervalAdded(this, size, size);
    }
    
    public void removeItem(final Object o) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "removeItem", new Class[] { (EasyListModel.class$java$lang$Object == null) ? (EasyListModel.class$java$lang$Object = class$("java.lang.Object")) : EasyListModel.class$java$lang$Object }, new Object[] { o });
            return;
        }
        final int indexOfItem = this.indexOfItem(o);
        if (indexOfItem != -1) {
            this.A.remove(o);
            this.fireIntervalRemoved(this, indexOfItem, indexOfItem);
        }
    }
    
    public void updateItem(final Object o) {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "updateItem", new Class[] { (EasyListModel.class$java$lang$Object == null) ? (EasyListModel.class$java$lang$Object = class$("java.lang.Object")) : EasyListModel.class$java$lang$Object }, new Object[] { o });
            return;
        }
        final int indexOfItem = this.indexOfItem(o);
        if (indexOfItem != -1) {
            this.fireContentsChanged(this, indexOfItem, indexOfItem);
        }
    }
    
    public void clear() {
        if (!jmaster.util.C.B.A()) {
            jmaster.util.C.B.C(this, "clear");
            return;
        }
        final int size = this.getSize();
        if (size > 0) {
            this.A.clear();
            this.fireIntervalRemoved(this, 0, size - 1);
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
}
