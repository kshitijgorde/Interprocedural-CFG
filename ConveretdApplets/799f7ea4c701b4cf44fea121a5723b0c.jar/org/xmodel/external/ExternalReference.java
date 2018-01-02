// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModel;
import org.xmodel.F;
import org.xmodel.IModelObject;
import org.xmodel.ModelObject;

public class ExternalReference extends ModelObject implements IExternalReference
{
    private ICachingPolicy P;
    private boolean Q;
    
    public ExternalReference(final String s) {
        super(s);
        this.Q = false;
    }
    
    @Override
    public void setCachingPolicy(final ICachingPolicy p) {
        if (this.P != null) {
            try {
                this.clearCache();
            }
            catch (CachingException ex) {}
            final ICache cache = this.P.getCache();
            if (cache != null) {
                cache.remove(this);
            }
        }
        this.P = p;
    }
    
    @Override
    public String[] getStaticAttributes() {
        final ICachingPolicy cachingPolicy = this.getCachingPolicy();
        if (cachingPolicy == null) {
            return new String[0];
        }
        return cachingPolicy.getStaticAttributes();
    }
    
    @Override
    public void setDirty(final boolean q) {
        final boolean q2 = this.Q;
        this.Q = q;
        if (q2 != q) {
            if (q) {
                this.clearCache();
            }
            this.notifyDirty(q);
        }
    }
    
    @Override
    public boolean isDirty() {
        return this.Q;
    }
    
    @Override
    public ICachingPolicy getCachingPolicy() {
        return this.P;
    }
    
    @Override
    public void sync() throws CachingException {
        final ICachingPolicy cachingPolicy = this.getCachingPolicy();
        if (cachingPolicy == null) {
            throw new CachingException("No caching policy to sync entity: " + this);
        }
        this.setDirty(false);
        cachingPolicy.sync(this);
    }
    
    @Override
    public ITransaction transaction() {
        final ICachingPolicy cachingPolicy = this.getCachingPolicy();
        if (cachingPolicy == null) {
            throw new CachingException("No caching policy for this entity: " + this);
        }
        return cachingPolicy.transaction();
    }
    
    @Override
    protected void readAttributeAccess(final String s) {
        if (this.P != null) {
            this.P.readAttributeAccess(this, s);
        }
    }
    
    @Override
    protected void readChildrenAccess() {
        if (this.P != null) {
            this.P.readChildrenAccess(this);
        }
    }
    
    @Override
    protected void writeAttributeAccess(final String s) {
        if (this.P != null) {
            this.P.writeAttributeAccess(this, s);
        }
    }
    
    @Override
    protected void writeChildrenAccess() {
        if (this.P != null) {
            this.P.writeChildrenAccess(this);
        }
    }
    
    @Override
    public void clearCache() throws CachingException {
        final ICachingPolicy cachingPolicy = this.getCachingPolicy();
        if (cachingPolicy == null) {
            throw new CachingException("No caching policy to clear entity: " + this);
        }
        cachingPolicy.clear(this);
    }
    
    protected void notifyDirty(final boolean b) {
        final F modelListeners = this.getModelListeners();
        if (modelListeners != null) {
            modelListeners.notifyDirty(this, b);
        }
    }
    
    @Override
    public IModelObject createObject(final String s) {
        return new ExternalReference(s);
    }
    
    @Override
    public String toString(final String s) {
        final IModel model = this.getModel();
        final boolean syncLock = model.getSyncLock();
        model.setSyncLock(true);
        try {
            final ICachingPolicy cachingPolicy = this.getCachingPolicy();
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("&");
            sb.append(super.toString());
            sb.append(" + ");
            if (cachingPolicy != null) {
                sb.append(cachingPolicy.toString(String.valueOf(s) + "  "));
            }
            return sb.toString();
        }
        finally {
            model.setSyncLock(syncLock);
        }
    }
    
    @Override
    public String toString() {
        return this.toString("");
    }
}
