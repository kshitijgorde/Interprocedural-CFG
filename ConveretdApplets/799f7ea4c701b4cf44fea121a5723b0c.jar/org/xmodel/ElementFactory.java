// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel;

import org.xmodel.external.ExternalElement;
import org.xmodel.external.IExternalReference;
import org.xml.sax.Attributes;

public class ElementFactory implements IModelObjectFactory
{
    @Override
    public IModelObject createObject(final IModelObject modelObject, final String s) {
        return new Element(s);
    }
    
    @Override
    public IModelObject createObject(final IModelObject modelObject, final Attributes attributes, final String s) {
        return new Element(s);
    }
    
    @Override
    public IModelObject createClone(final IModelObject modelObject) {
        final Element element = new Element(modelObject.getType());
        ModelAlgorithms.copyAttributes(modelObject, element);
        return element;
    }
    
    @Override
    public IExternalReference createExternalObject(final IModelObject modelObject, final String s) {
        return new ExternalElement(s);
    }
}
