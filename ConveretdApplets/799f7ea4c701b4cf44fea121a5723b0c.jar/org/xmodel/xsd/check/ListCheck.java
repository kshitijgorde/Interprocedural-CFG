// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public class ListCheck extends ConstraintCheck
{
    private List<ConstraintCheck> H;
    
    public ListCheck(final IModelObject modelObject) {
        super(modelObject);
    }
    
    public boolean validateOnce(final IModelObject errorLocus, final int index, final int n) {
        if (this.H != null) {
            this.H.clear();
        }
        this.index = index;
        int n2;
        for (n2 = 0; n2 < this.constraints.length && this.index < n; ++n2) {
            ConstraintCheck constraintCheck = this.constraints[n2];
            if (!constraintCheck.validate(errorLocus, this.index, n)) {
                this.addFailed(constraintCheck);
                return false;
            }
            final int anyCount = constraintCheck.anyCount();
            if (anyCount >= 0) {
                boolean b = false;
                for (int n3 = ++n2; !b && n3 < this.constraints.length; ++n3) {
                    constraintCheck = this.constraints[n3];
                    int i = 0;
                    while (i < anyCount) {
                        if (constraintCheck.validate(errorLocus, this.index + i, n)) {
                            if (constraintCheck.getIndex() > this.index + i) {
                                b = true;
                                break;
                            }
                            break;
                        }
                        else {
                            ++i;
                        }
                    }
                }
                if (!b) {
                    this.index += anyCount;
                    this.addFailed(constraintCheck);
                    return false;
                }
            }
            this.index = constraintCheck.getIndex();
        }
        for (int j = n2; j < this.constraints.length; ++j) {
            if (this.constraints[j].getMinOccurrences() > 0) {
                if (this.H == null) {
                    this.H = new ArrayList<ConstraintCheck>();
                }
                this.H.add(this.constraints[j]);
            }
        }
        if (this.H != null && this.H.size() > 0) {
            this.errorLocus = errorLocus;
            return false;
        }
        return this.errored == null || this.errored.size() == 0;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        super.getErrors(list);
        if (this.H != null) {
            final Iterator<ConstraintCheck> iterator = this.H.iterator();
            while (iterator.hasNext()) {
                list.add(new SchemaError(SchemaError.Type.missingElement, iterator.next().getSchemaLocus(), this.errorLocus));
            }
        }
    }
}
