// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import java.util.List;
import org.xmodel.IModelObject;

public interface ICheck
{
    boolean validate(final IModelObject p0);
    
    void getErrors(final List<SchemaError> p0);
    
    IModelObject getSchemaLocus();
}
