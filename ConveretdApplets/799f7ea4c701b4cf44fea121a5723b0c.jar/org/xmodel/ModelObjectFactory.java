// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.external.ExternalReference;
import org.xmodel.external.IExternalReference;
import org.xml.sax.Attributes;

public class ModelObjectFactory implements IModelObjectFactory
{
    @Override
    public IModelObject createObject(final IModelObject modelObject, final String s) {
        return new ModelObject(s);
    }
    
    @Override
    public IModelObject createObject(final IModelObject modelObject, final Attributes attributes, final String s) {
        return new ModelObject(s);
    }
    
    @Override
    public IModelObject createClone(final IModelObject modelObject) {
        final ModelObject modelObject2 = new ModelObject(modelObject.getType());
        ModelAlgorithms.copyAttributes(modelObject, modelObject2);
        return modelObject2;
    }
    
    @Override
    public IExternalReference createExternalObject(final IModelObject modelObject, final String s) {
        return new ExternalReference(s);
    }
}
