// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.xpath.expression.StatefulContext;
import org.xmodel.xaction.XActionDocument;
import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import org.xmodel.xaction.ScriptAction;
import org.xmodel.xpath.expression.IContext;

public abstract class ConfiguredCachingPolicy extends AbstractCachingPolicy
{
    private IContext K;
    private ScriptAction J;
    private ScriptAction I;
    private ScriptAction L;
    
    protected ConfiguredCachingPolicy() {
        super(new UnboundedCache());
    }
    
    protected ConfiguredCachingPolicy(final ICache cache) {
        super(cache);
    }
    
    public void configure(final IContext k, final IModelObject modelObject) throws CachingException {
        this.K = k;
        final String childGet = Xlate.childGet(modelObject, "static", (String)null);
        if (childGet != null) {
            final String[] split = childGet.split(",");
            for (int i = 0; i < split.length; ++i) {
                split[i] = split[i].trim();
            }
            this.setStaticAttributes(split);
        }
        final XActionDocument xActionDocument = new XActionDocument(modelObject);
        this.J = xActionDocument.createChildScript("onSync", new String[0]);
        this.I = xActionDocument.createChildScript("onFlush", new String[0]);
        this.L = xActionDocument.createChildScript("onError", new String[0]);
    }
    
    @Override
    public void sync(final IExternalReference externalReference) throws CachingException {
        if (this.L == null) {
            this.syncImpl(externalReference);
            if (this.J != null) {
                this.onSync(externalReference);
            }
        }
        else {
            try {
                this.syncImpl(externalReference);
                this.onSync(externalReference);
            }
            catch (CachingException ex) {
                this.onError(externalReference, ex.getMessage());
            }
        }
    }
    
    @Override
    public final void flush(final IExternalReference externalReference) throws CachingException {
        if (this.L == null) {
            this.flushImpl(externalReference);
            if (this.I != null) {
                this.onFlush(externalReference);
            }
        }
        else {
            try {
                this.flushImpl(externalReference);
                this.onFlush(externalReference);
            }
            catch (CachingException ex) {
                this.onError(externalReference, ex.getMessage());
            }
        }
    }
    
    protected abstract void syncImpl(final IExternalReference p0) throws CachingException;
    
    protected void flushImpl(final IExternalReference externalReference) throws CachingException {
        throw new UnsupportedOperationException();
    }
    
    protected IContext getContext() {
        return this.K;
    }
    
    protected void onSync(final IExternalReference externalReference) {
        this.J.run(new StatefulContext(this.K, externalReference));
    }
    
    protected void onFlush(final IExternalReference externalReference) {
        this.I.run(new StatefulContext(this.K, externalReference));
    }
    
    protected void onError(final IExternalReference externalReference, final String s) {
        final StatefulContext statefulContext = new StatefulContext(this.K, externalReference);
        statefulContext.set("error", s);
        this.L.run(statefulContext);
    }
}
