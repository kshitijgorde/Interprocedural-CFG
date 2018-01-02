// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.caching;

import java.io.File;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.CachingException;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class ResourceCachingPolicy extends ConfiguredCachingPolicy
{
    private FileSystemCachingPolicy fileCachingPolicy;
    private ZipCachingPolicy zipCachingPolicy;
    private Boolean useJar;
    
    public ResourceCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public ResourceCachingPolicy(final ICache cache) {
        super(cache);
        this.fileCachingPolicy = new FileSystemCachingPolicy(cache);
        this.zipCachingPolicy = new ZipCachingPolicy(cache);
        final String[] staticAttributes = this.fileCachingPolicy.getStaticAttributes();
        final String[] staticAttributes2 = this.zipCachingPolicy.getStaticAttributes();
        final String[] staticAttributes3 = new String[staticAttributes.length + staticAttributes2.length];
        System.arraycopy(staticAttributes, 0, staticAttributes3, 0, staticAttributes.length);
        System.arraycopy(staticAttributes2, 0, staticAttributes3, staticAttributes.length, staticAttributes2.length);
        this.setStaticAttributes(staticAttributes3);
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        if (this.useJar(externalReference)) {
            this.zipCachingPolicy.sync(externalReference);
        }
        else {
            this.fileCachingPolicy.sync(externalReference);
        }
    }
    
    @Override
    public void clear(final IExternalReference externalReference) throws CachingException {
        if (this.useJar(externalReference)) {
            this.zipCachingPolicy.clear(externalReference);
        }
        else {
            this.fileCachingPolicy.clear(externalReference);
        }
    }
    
    public void flushImpl(final IExternalReference externalReference) throws CachingException {
        if (this.useJar(externalReference)) {
            this.zipCachingPolicy.flush(externalReference);
        }
        else {
            this.fileCachingPolicy.flush(externalReference);
        }
    }
    
    private boolean useJar(final IExternalReference externalReference) {
        if (this.useJar == null) {
            final File file = new File(Xlate.get((IModelObject)externalReference, "path", "."));
            System.out.println("file=" + file);
            this.useJar = !file.exists();
        }
        return this.useJar;
    }
}
