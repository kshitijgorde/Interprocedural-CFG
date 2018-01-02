// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ListDiffer extends AbstractListDiffer
{
    private List<Change> F;
    
    @Override
    public void diff(final List list, final List list2) {
        if (this.F != null) {
            this.F.clear();
        }
        super.diff(list, list2);
    }
    
    @Override
    public void notifyInsert(final List list, final int n, final int n2, final List list2, final int rIndex, final int count) {
        final Change change = new Change();
        change.lIndex = n + n2;
        change.rIndex = rIndex;
        change.count = count;
        if (this.F == null) {
            this.F = new ArrayList<Change>();
        }
        this.F.add(change);
    }
    
    @Override
    public void notifyRemove(final List list, final int n, final int n2, final List list2, final int count) {
        final Change change = new Change();
        change.lIndex = n + n2;
        change.rIndex = -1;
        change.count = count;
        if (this.F == null) {
            this.F = new ArrayList<Change>();
        }
        this.F.add(change);
    }
    
    public List<Change> getChanges() {
        if (this.F == null) {
            return Collections.emptyList();
        }
        return this.F;
    }
    
    public static class Change
    {
        public int lIndex;
        public int rIndex;
        public int count;
    }
}
