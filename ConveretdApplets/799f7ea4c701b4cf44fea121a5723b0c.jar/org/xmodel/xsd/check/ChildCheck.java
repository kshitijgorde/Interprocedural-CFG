// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.List;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;

public class ChildCheck extends ConstraintCheck
{
    private String F;
    
    public ChildCheck(final IModelObject modelObject) {
        super(modelObject);
        this.F = Xlate.get(modelObject, "");
    }
    
    public boolean validateOnce(final IModelObject modelObject, final int index, final int n) {
        this.index = index;
        final IModelObject child = modelObject.getChild(this.index);
        if (child != null && child.isType(this.F)) {
            ++this.index;
            return true;
        }
        return false;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        if (this.errorLocus != null) {
            if (this.occurrences < this.getMinOccurrences()) {
                list.add(new SchemaError(SchemaError.Type.missingElement, this.getSchemaLocus(), this.errorLocus));
            }
            if (this.occurrences > this.getMaxOccurrences()) {
                list.add(new SchemaError(SchemaError.Type.illegalElement, this.getSchemaLocus(), this.errorLocus));
            }
        }
    }
}
