// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.caching;

import org.xmodel.external.ICache;
import org.xidget.Creator;

public class FileSystemCachingPolicy extends org.xmodel.caching.FileSystemCachingPolicy
{
    public FileSystemCachingPolicy() {
        this.addAssociation(Creator.getToolkit().getImageFileAssociation());
    }
    
    public FileSystemCachingPolicy(final ICache cache) {
        super(cache);
        this.addAssociation(Creator.getToolkit().getImageFileAssociation());
    }
}
