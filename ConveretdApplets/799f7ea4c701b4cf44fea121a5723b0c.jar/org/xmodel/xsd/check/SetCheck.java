// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public class SetCheck extends ConstraintCheck
{
    private List<ConstraintCheck> D;
    
    public SetCheck(final IModelObject modelObject) {
        super(modelObject);
    }
    
    public boolean validateOnce(final IModelObject modelObject, final int index, final int n) {
        if (this.D != null) {
            this.D.clear();
        }
        final ArrayList list = new ArrayList<Object>();
        for (int i = 0; i < this.constraints.length; ++i) {
            list.add(this.constraints[i]);
        }
        this.index = index;
        while (list.size() > 0 && this.index < n) {
            final int a = this.A(list, modelObject, this.index, n);
            if (a < 0) {
                return false;
            }
            this.index = ((ConstraintCheck)list.remove(a)).getIndex();
        }
        for (int j = 0; j < list.size(); ++j) {
            if (((ConstraintCheck)list.get(j)).getMinOccurrences() > 0) {
                if (this.D == null) {
                    this.D = new ArrayList<ConstraintCheck>();
                }
                this.D.add((ConstraintCheck)list.get(j));
            }
        }
        return (this.D == null || this.D.size() <= 0) && (this.errored == null || this.errored.size() == 0);
    }
    
    private int A(final List<ConstraintCheck> list, final IModelObject modelObject, final int n, final int n2) {
        for (int i = 0; i < list.size(); ++i) {
            final ConstraintCheck constraintCheck = list.get(i);
            if (constraintCheck.validate(modelObject, n, n2) && constraintCheck.getOccurrences() > 0) {
                return i;
            }
            if (constraintCheck.getOccurrences() > 0) {
                this.addFailed(constraintCheck);
            }
        }
        return -1;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        super.getErrors(list);
        if (this.errorLocus != null) {
            list.add(new SchemaError(SchemaError.Type.illegalElement, this.getSchemaLocus(), this.errorLocus));
        }
        if (this.D != null) {
            final Iterator<ConstraintCheck> iterator = this.D.iterator();
            while (iterator.hasNext()) {
                list.add(new SchemaError(SchemaError.Type.missingElement, iterator.next().getSchemaLocus(), this.errorLocus));
            }
        }
    }
}
