// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.io.InputStream;
import org.xmodel.external.CachingException;
import org.xmodel.IModelObject;
import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class ZipEntryCachingPolicy extends ConfiguredCachingPolicy
{
    public ZipEntryCachingPolicy() {
        this(new UnboundedCache());
    }
    
    public ZipEntryCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "entry" });
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        final ZipEntry zipEntry = (ZipEntry)externalReference.getAttribute("entry");
        if (zipEntry == null) {
            return;
        }
        final IExternalReference c = this.C(externalReference);
        final ZipFile zipFile = (ZipFile)c.getAttribute("zipFile");
        try {
            externalReference.removeChildren();
            final InputStream inputStream = zipFile.getInputStream(zipEntry);
            final String substring = zipEntry.getName().substring(zipEntry.getName().lastIndexOf("."));
            System.out.println("ext=" + substring);
            ((ZipCachingPolicy)c.getCachingPolicy()).getAssociation(substring).apply(externalReference, zipEntry.getName(), inputStream);
            inputStream.close();
        }
        catch (Exception ex) {
            throw new CachingException("Unable to load zip entry: " + zipEntry, ex);
        }
    }
    
    private IExternalReference C(IExternalReference externalReference) {
        for (IModelObject parent = externalReference; parent != null; parent = parent.getParent()) {
            if (parent instanceof IExternalReference) {
                externalReference = (IExternalReference)parent;
                if (externalReference.getCachingPolicy() instanceof ZipCachingPolicy) {
                    return externalReference;
                }
            }
        }
        return null;
    }
}
