// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import org.xmodel.external.CachingException;
import java.net.URL;
import org.xmodel.xml.XmlIO;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class XsdCachingPolicy extends ConfiguredCachingPolicy
{
    public XsdCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "id", "url" });
    }
    
    public void syncImpl(final IExternalReference externalReference) throws CachingException {
        final String value = Xlate.get((IModelObject)externalReference, "url", (String)null);
        if (value == null) {
            return;
        }
        new XmlIO().setFactory(this.getFactory());
        URL url = null;
        try {
            url = new URL(value);
            final IModelObject cloneObject = externalReference.cloneObject();
            cloneObject.addChild(new Xsd(url).getRoot());
            this.update(externalReference, cloneObject);
        }
        catch (Exception ex) {
            throw new CachingException("Unable to sync url: " + url, ex);
        }
    }
}
