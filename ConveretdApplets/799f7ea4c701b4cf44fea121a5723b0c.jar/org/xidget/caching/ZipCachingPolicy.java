// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.caching;

import org.xmodel.external.ICache;
import org.xidget.Creator;

public class ZipCachingPolicy extends org.xmodel.caching.ZipCachingPolicy
{
    public ZipCachingPolicy() {
        this.addAssociation(Creator.getToolkit().getImageFileAssociation());
    }
    
    public ZipCachingPolicy(final ICache cache) {
        super(cache);
        this.addAssociation(Creator.getToolkit().getImageFileAssociation());
    }
}
