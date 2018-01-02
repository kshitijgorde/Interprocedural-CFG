// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.IModelObject;
import java.util.List;

public class RootConstraint extends ConstraintCheck
{
    private List<IModelObject> E;
    
    public RootConstraint(final IModelObject modelObject) {
        super(modelObject);
    }
    
    @Override
    public boolean validate(final IModelObject modelObject, final int n, final int n2) {
        return this.validateOnce(modelObject, n, n2);
    }
    
    public boolean validateOnce(final IModelObject modelObject, final int n, final int n2) {
        if (this.E != null) {
            this.E.clear();
        }
        final ConstraintCheck constraintCheck = this.constraints[0];
        if (!constraintCheck.validate(modelObject, n, n2)) {
            this.addFailed(constraintCheck);
            return false;
        }
        this.index = constraintCheck.getIndex();
        if (this.index < n2) {
            for (int i = this.index; i < n2; ++i) {
                if (this.E == null) {
                    this.E = new ArrayList<IModelObject>();
                }
                this.E.add(modelObject.getChild(i));
            }
            return false;
        }
        return true;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        super.getErrors(list);
        if (this.errorLocus != null) {
            if (this.constraints[0].getOccurrences() < this.constraints[0].getMinOccurrences()) {
                list.add(new SchemaError(SchemaError.Type.missingElement, this.getSchemaLocus(), this.errorLocus));
            }
            else if (this.constraints[0].getOccurrences() > this.constraints[0].getMaxOccurrences()) {
                list.add(new SchemaError(SchemaError.Type.illegalElement, this.getSchemaLocus(), this.errorLocus));
            }
        }
        if (this.E != null) {
            final Iterator<IModelObject> iterator = this.E.iterator();
            while (iterator.hasNext()) {
                list.add(new SchemaError(SchemaError.Type.illegalElement, this.getSchemaLocus(), iterator.next()));
            }
        }
    }
}
