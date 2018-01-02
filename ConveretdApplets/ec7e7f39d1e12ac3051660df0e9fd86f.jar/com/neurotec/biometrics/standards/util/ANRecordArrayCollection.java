// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards.util;

import com.neurotec.util.NCollection;
import com.neurotec.lang.NObject;
import com.neurotec.util.NArrayCollection;

public abstract class ANRecordArrayCollection<E, EBase> extends NArrayCollection<E, EBase>
{
    protected ANRecordArrayCollection(final NObject owner, final NCollection<EBase> baseCollection, final boolean isRemoveRange, final boolean isAdd, final boolean isAddWithIndex, final boolean isRemoveByObject) {
        super(owner, baseCollection, isRemoveRange, isAdd, isAddWithIndex, isRemoveByObject);
    }
}
