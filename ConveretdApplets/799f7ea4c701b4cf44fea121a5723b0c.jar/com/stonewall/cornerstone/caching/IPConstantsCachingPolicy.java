// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.caching;

import org.xmodel.external.CachingException;
import com.stonewall.cornerstone.entity.util.IpPort;
import org.xmodel.IModelObject;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.UnboundedCache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class IPConstantsCachingPolicy extends ConfiguredCachingPolicy
{
    public IPConstantsCachingPolicy() {
        super(new UnboundedCache());
    }
    
    public IPConstantsCachingPolicy(final ICache cache) {
        super(cache);
    }
    
    @Override
    protected void syncImpl(final IExternalReference reference) throws CachingException {
        final IModelObject protocols = this.getFactory().createObject(reference, "protocols");
        String[] protocols2;
        for (int length = (protocols2 = IpPort.getProtocols()).length, i = 0; i < length; ++i) {
            final String protocol = protocols2[i];
            final IModelObject entry = this.getFactory().createObject(protocols, "protocol");
            entry.setValue(protocol);
            protocols.addChild(entry);
        }
        reference.addChild(protocols);
        final IModelObject services = this.getFactory().createObject(reference, "services");
        String[] services2;
        for (int length2 = (services2 = IpPort.getServices()).length, j = 0; j < length2; ++j) {
            final String service = services2[j];
            final IModelObject entry2 = this.getFactory().createObject(services, "service");
            entry2.setValue(service);
            services.addChild(entry2);
        }
        reference.addChild(services);
    }
}
