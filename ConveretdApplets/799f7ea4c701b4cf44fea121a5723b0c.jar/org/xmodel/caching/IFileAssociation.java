// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.io.InputStream;
import org.xmodel.IModelObject;
import org.xmodel.external.CachingException;
import org.xmodel.external.ICachingPolicy;

public interface IFileAssociation
{
    String[] getExtensions();
    
    ICachingPolicy getCachingPolicy(final ICachingPolicy p0, final String p1) throws CachingException;
    
    void apply(final IModelObject p0, final String p1, final InputStream p2) throws CachingException;
}
