// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.C;

import java.util.ArrayList;
import jmaster.jumploader.model.api.common.IListSelectionListener;
import jmaster.util.log.B;
import jmaster.util.log.A;
import jmaster.jumploader.model.api.common.IListSelection;

public class E implements IListSelection
{
    protected A B;
    protected jmaster.util.C.A C;
    private int[] A;
    static /* synthetic */ Class class$jmaster$jumploader$model$api$upload$B;
    
    public E() {
        this.B = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.C = new jmaster.util.C.A((E.class$jmaster$jumploader$model$api$upload$B == null) ? (E.class$jmaster$jumploader$model$api$upload$B = class$("jmaster.jumploader.model.api.upload.B")) : E.class$jmaster$jumploader$model$api$upload$B);
        this.A = null;
    }
    
    public int[] getSelectedIndices() {
        return this.A;
    }
    
    public String[] getSelectedIndicesAsStrings() {
        final String[] array = new String[(this.A == null) ? 0 : this.A.length];
        for (int i = 0; i < array.length; ++i) {
            array[i] = "" + this.A[i];
        }
        return array;
    }
    
    public void setSelectedIndices(final int[] a) {
        final int n = (this.A == null) ? 0 : this.A.length;
        final int n2 = (a == null) ? 0 : a.length;
        boolean b = n != n2;
        if (b || n > 0 || n2 > 0) {
            if (!b) {
                for (int n3 = 0; !b && n3 < n; b = (this.A[n3] != a[n3]), ++n3) {}
            }
            if (b) {
                this.A = a;
                this.A();
            }
        }
    }
    
    public void addListener(final IListSelectionListener listSelectionListener) {
        this.C.C(listSelectionListener);
    }
    
    public void removeListener(final IListSelectionListener listSelectionListener) {
        this.C.A(listSelectionListener);
    }
    
    private void A() {
        for (int i = 0; i < this.C.C(); ++i) {
            try {
                ((IListSelectionListener)this.C.A(i)).listSelectionChanged(this);
            }
            catch (Exception ex) {
                this.B.E(ex, ex);
            }
        }
    }
    
    public int getSelectedItemCount() {
        return (this.A == null) ? 0 : this.A.length;
    }
    
    public int getSelectedItemIndexAt(final int n) {
        return this.A[n];
    }
    
    public int indexOf(final int n) {
        int n2 = -1;
        if (this.A != null) {
            for (int n3 = 0; n2 == -1 && n3 < this.A.length; ++n3) {
                if (this.A[n3] == n) {
                    n2 = n3;
                }
            }
        }
        return n2;
    }
    
    public boolean isIndexSelected(final int n) {
        return this.indexOf(n) != -1;
    }
    
    public void A(final int n) {
        if (this.A != null) {
            final ArrayList<Object> list = new ArrayList<Object>();
            for (int i = 0; i < this.A.length; ++i) {
                if (this.A[i] != n) {
                    list.add("" + this.A[i]);
                }
            }
            if (list.size() != this.A.length) {
                this.A = new int[list.size()];
                for (int j = 0; j < this.A.length; ++j) {
                    this.A[j] = Integer.parseInt(list.get(j));
                }
                this.A();
            }
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
