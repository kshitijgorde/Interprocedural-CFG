// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import org.xmodel.external.CachingException;
import org.xmodel.external.ICachingPolicy;

public class ZipAssociation extends AbstractFileAssociation
{
    private static final String[] A;
    
    static {
        A = new String[] { ".zip" };
    }
    
    @Override
    public String[] getExtensions() {
        return ZipAssociation.A;
    }
    
    @Override
    public ICachingPolicy getCachingPolicy(final ICachingPolicy cachingPolicy, final String s) throws CachingException {
        return new ZipCachingPolicy();
    }
}
