// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.IModelObject;

public class ChoiceCheck extends ConstraintCheck
{
    public ChoiceCheck(final IModelObject modelObject) {
        super(modelObject);
    }
    
    public boolean validateOnce(final IModelObject modelObject, final int index, final int n) {
        this.index = index;
        for (int i = 0; i < this.constraints.length; ++i) {
            final ConstraintCheck constraintCheck = this.constraints[i];
            if (constraintCheck.validateOnce(modelObject, this.index, n)) {
                this.index = constraintCheck.getIndex();
                return true;
            }
        }
        return false;
    }
}
