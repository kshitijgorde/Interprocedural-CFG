// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.List;
import org.xmodel.IModelObject;

public class TypeCheck extends AbstractCheck
{
    private ICheck M;
    
    public TypeCheck(final IModelObject modelObject) {
        super(modelObject);
        if (modelObject.isType("boolean")) {
            this.M = new BooleanCheck(modelObject);
        }
        else if (modelObject.isType("number")) {
            this.M = new NumberCheck(modelObject);
        }
        else if (modelObject.isType("string")) {
            this.M = new StringCheck(modelObject);
        }
        else if (modelObject.isType("enum")) {
            this.M = new EnumCheck(modelObject);
        }
        else if (modelObject.isType("pattern")) {
            this.M = new PatternCheck(modelObject);
        }
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        if (!this.M.validate(modelObject)) {
            this.addFailed(this.M);
            return false;
        }
        return true;
    }
    
    @Override
    public void getErrors(final List<SchemaError> list) {
        if (this.errorLocus != null) {
            list.add(new SchemaError(SchemaError.Type.invalidValue, this.M.getSchemaLocus(), this.errorLocus));
        }
    }
}
