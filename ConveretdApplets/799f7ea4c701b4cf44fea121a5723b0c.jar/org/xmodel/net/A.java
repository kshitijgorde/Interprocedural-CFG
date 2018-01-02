// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.net;

import java.io.IOException;
import org.xmodel.external.CachingException;
import org.xmodel.external.IExternalReference;
import java.util.List;
import org.xmodel.diff.IXmlMatcher;
import org.xmodel.diff.DefaultXmlMatcher;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.external.AbstractCachingPolicy;

class A extends AbstractCachingPolicy
{
    private Protocol z;
    
    public A(final Protocol protocol) {
        this(new UnboundedCache(), protocol);
    }
    
    public A(final ICache cache, final Protocol z) {
        super(cache);
        this.z = z;
        this.setStaticAttributes(new String[] { "id" });
        this.getDiffer().setMatcher(new DefaultXmlMatcher(true));
    }
    
    public void A(final List<String> list) {
        this.setStaticAttributes(list.toArray(new String[0]));
    }
    
    @Override
    public void sync(final IExternalReference externalReference) throws CachingException {
        try {
            this.z.sendSyncRequest(externalReference);
        }
        catch (IOException ex) {
            throw new CachingException("Unable to sync reference: " + externalReference, ex);
        }
    }
    
    @Override
    public void flush(final IExternalReference externalReference) throws CachingException {
    }
}
