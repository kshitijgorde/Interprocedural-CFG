// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.Collections;
import org.xmodel.IBoundChangeRecord;
import java.util.Comparator;
import org.xmodel.ChangeSet;

public class RegularChangeSet extends ChangeSet
{
    private Comparator<IBoundChangeRecord> C;
    
    public RegularChangeSet() {
        this.C = new Comparator<IBoundChangeRecord>() {
            public int A(final IBoundChangeRecord boundChangeRecord, final IBoundChangeRecord boundChangeRecord2) {
                final int type = boundChangeRecord.getType();
                final int type2 = boundChangeRecord2.getType();
                if (type == 0 || type == 1) {
                    if (type2 == 0 || type2 == 1) {
                        return 0;
                    }
                    return -1;
                }
                else {
                    if (type2 == 0 || type2 == 1) {
                        return 1;
                    }
                    if (type == 3) {
                        if (type2 == 3) {
                            return 0;
                        }
                        return -1;
                    }
                    else {
                        if (type2 == 3) {
                            return 1;
                        }
                        return 0;
                    }
                }
            }
        };
    }
    
    @Override
    public void applyChanges() {
        if (this.records == null) {
            return;
        }
        Collections.sort(this.records, this.C);
        super.applyChanges();
    }
}
