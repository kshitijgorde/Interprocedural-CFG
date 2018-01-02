// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.IModel;
import org.xmodel.IModelObject;
import org.xmodel.Element;

public class ExternalElement extends Element implements IExternalReference
{
    private ICachingPolicy Y;
    private boolean Z;
    
    public ExternalElement(final String s) {
        super(s);
        this.Z = false;
    }
    
    @Override
    public void setCachingPolicy(final ICachingPolicy y) {
        if (this.Y != null) {
            try {
                this.clearCache();
            }
            catch (CachingException ex) {}
            final ICache cache = this.Y.getCache();
            if (cache != null) {
                cache.remove(this);
            }
        }
        this.Y = y;
    }
    
    @Override
    public String[] getStaticAttributes() {
        return this.getCachingPolicy().getStaticAttributes();
    }
    
    @Override
    public void setDirty(final boolean z) {
        this.Z = z;
    }
    
    @Override
    public boolean isDirty() {
        return this.Z;
    }
    
    @Override
    public ICachingPolicy getCachingPolicy() {
        return this.Y;
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
        if (this.Y != null) {
            this.Y.readAttributeAccess(this, s);
        }
    }
    
    @Override
    protected void readChildrenAccess() {
        if (this.Y != null) {
            this.Y.readChildrenAccess(this);
        }
    }
    
    @Override
    protected void writeAttributeAccess(final String s) {
        if (this.Y != null) {
            this.Y.writeAttributeAccess(this, s);
        }
    }
    
    @Override
    protected void writeChildrenAccess() {
        if (this.Y != null) {
            this.Y.writeChildrenAccess(this);
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
    
    @Override
    public IModelObject createObject(final String s) {
        return new ExternalElement(s);
    }
    
    @Override
    public String toString(final String s) {
        final IModel model = this.getModel();
        final boolean syncLock = model.getSyncLock();
        model.setSyncLock(true);
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.append("&");
            sb.append(super.toString());
            sb.append("\n");
            sb.append(this.getCachingPolicy().toString(String.valueOf(s) + "  "));
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
