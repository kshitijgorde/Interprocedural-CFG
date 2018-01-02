// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards.util;

import com.neurotec.biometrics.standards.ANRecord;
import com.neurotec.lang.NObject;
import com.neurotec.util.NSimpleCollection;

public abstract class ANRecordSimpleCollection<E> extends NSimpleCollection<E>
{
    private int fieldNumber;
    
    protected ANRecordSimpleCollection(final NObject owner, final int fieldNumber, final boolean isRemoveRange, final boolean isAddWithIndex, final boolean isRemoveByObject) {
        super(owner, isRemoveRange, isAddWithIndex, isRemoveByObject);
        this.fieldNumber = fieldNumber;
    }
    
    protected final void onInsertItem(final int index) {
        super.onInsertItem(index);
        if (this.size() == 1) {
            ((ANRecord)this.getOwner()).refresh();
        }
        else {
            ((ANRecord)this.getOwner()).getFieldByNumber(this.fieldNumber).refresh();
        }
    }
    
    protected final void onRemoveItemRange(final int index, final int count) {
        super.onRemoveItemRange(index, count);
        if (count != 0) {
            if (this.size() == 0) {
                ((ANRecord)this.getOwner()).refresh();
            }
            else {
                ((ANRecord)this.getOwner()).getFieldByNumber(this.fieldNumber).refresh();
            }
        }
    }
}
