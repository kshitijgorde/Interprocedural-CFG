// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import java.util.Iterator;
import java.util.Properties;
import org.xmodel.IModelObject;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class EnvironmentCachingPolicy extends ConfiguredCachingPolicy
{
    public EnvironmentCachingPolicy(final ICache cache) {
        super(cache);
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        externalReference.removeChildren();
        final Properties properties = System.getProperties();
        for (final Object next : properties.keySet()) {
            final IModelObject object = this.getFactory().createObject(externalReference, "property");
            object.setAttribute("id", next);
            object.setValue(properties.get(next));
            externalReference.addChild(object);
        }
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean b) throws CachingException {
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
    }
}
