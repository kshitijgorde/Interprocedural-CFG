// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.xpath.XPath;
import org.xmodel.IModelObject;
import org.xmodel.IPath;

public class SchemaCheck extends AbstractCheck
{
    private IPath N;
    
    public SchemaCheck(final IModelObject modelObject) {
        super(modelObject);
        this.N = XPath.createPath("element[ @global = 'true' and @name = $name]");
    }
    
    @Override
    protected boolean validateImpl(final IModelObject modelObject) {
        this.N.setVariable("name", modelObject.getType());
        final IModelObject queryFirst = this.N.queryFirst(this.getSchemaLocus());
        if (queryFirst == null) {
            this.addFailed(this);
            return false;
        }
        final ElementCheck elementCheck = new ElementCheck(queryFirst);
        if (!elementCheck.validate(modelObject)) {
            this.addFailed(elementCheck);
            return false;
        }
        return true;
    }
}
