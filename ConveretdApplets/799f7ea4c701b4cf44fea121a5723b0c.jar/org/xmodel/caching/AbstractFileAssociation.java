// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.io.InputStream;
import org.xmodel.IModelObject;
import org.xmodel.external.CachingException;
import org.xmodel.external.ICachingPolicy;

public abstract class AbstractFileAssociation implements IFileAssociation
{
    @Override
    public ICachingPolicy getCachingPolicy(final ICachingPolicy cachingPolicy, final String s) throws CachingException {
        return null;
    }
    
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
    }
}
