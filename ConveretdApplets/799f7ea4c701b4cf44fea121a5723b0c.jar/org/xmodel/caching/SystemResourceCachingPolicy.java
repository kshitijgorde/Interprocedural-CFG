// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.caching;

import java.net.URL;
import org.xmodel.xml.XmlException;
import org.xmodel.external.CachingException;
import org.xmodel.xml.XmlIO;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class SystemResourceCachingPolicy extends ConfiguredCachingPolicy
{
    public SystemResourceCachingPolicy(final ICache cache) {
        super(cache);
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        final URL systemResource = ClassLoader.getSystemResource(Xlate.get((IModelObject)externalReference, "path", "undefined"));
        try {
            final XmlIO xmlIO = new XmlIO();
            xmlIO.setFactory(this.getFactory());
            final IModelObject read = xmlIO.read(systemResource);
            if (read != null) {
                this.update(externalReference, read);
            }
        }
        catch (XmlException ex) {
            throw new CachingException("Unable to sync reference: " + externalReference, ex);
        }
    }
}
