// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd;

import org.xmodel.ModelAlgorithms;
import org.xmodel.external.CachingException;
import java.net.URL;
import org.xmodel.IModelObject;
import org.xmodel.Xlate;
import org.xmodel.external.IExternalReference;
import org.xmodel.external.ICache;
import org.xmodel.external.ConfiguredCachingPolicy;

public class SchemaCachingPolicy extends ConfiguredCachingPolicy
{
    private boolean U;
    
    public SchemaCachingPolicy(final ICache cache) {
        super(cache);
        this.setStaticAttributes(new String[] { "id", "url", "unordered", "cachingPolicy" });
    }
    
    @Override
    protected void syncImpl(final IExternalReference externalReference) throws CachingException {
        final String value = Xlate.get((IModelObject)externalReference, "url", (String)null);
        if (value == null) {
            return;
        }
        URL url = null;
        try {
            url = new URL(value);
            final IModelObject cloneObject = externalReference.cloneObject();
            final Xsd xsd = new Xsd(url);
            this.U = Xlate.get(externalReference, "unordered", false);
            cloneObject.addChild(new SchemaTransform(this.U).transform(xsd.getRoot()));
            this.update(externalReference, cloneObject);
        }
        catch (Exception ex) {
            throw new CachingException("Unable to sync url: " + url, ex);
        }
    }
    
    @Override
    public void insert(final IExternalReference externalReference, final IModelObject modelObject, final int n, final boolean b) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void remove(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void update(final IExternalReference externalReference, final IModelObject modelObject) throws CachingException {
        externalReference.removeChildren();
        ModelAlgorithms.copyAttributes(modelObject, externalReference);
        ModelAlgorithms.moveChildren(modelObject, externalReference);
    }
}
