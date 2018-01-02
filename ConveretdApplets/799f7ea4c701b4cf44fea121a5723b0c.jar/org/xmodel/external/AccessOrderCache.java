// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.external;

import org.xmodel.Xlate;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.List;

public class AccessOrderCache implements ICache
{
    private int A;
    private List<IExternalReference> B;
    
    public AccessOrderCache() {
        this(0);
    }
    
    public AccessOrderCache(final int a) {
        this.B = new ArrayList<IExternalReference>(a);
        this.A = a;
    }
    
    @Override
    public void configure(final IModelObject modelObject) {
        this.A = Xlate.get(modelObject, "capacity", 0);
    }
    
    @Override
    public void add(final IExternalReference externalReference) {
        final IModelObject parent = externalReference.getParent();
        while (parent != null) {
            if (parent instanceof IExternalReference) {
                this.memoryLock((IExternalReference)parent);
            }
        }
        if (this.B.size() >= this.A) {
            this.clearEntry(this.B.remove(0));
        }
        this.B.add(externalReference);
    }
    
    @Override
    public void remove(final IExternalReference externalReference) {
        if (this.B.remove(externalReference)) {
            final IModelObject parent = externalReference.getParent();
            while (parent != null) {
                if (parent instanceof IExternalReference) {
                    this.memoryUnlock((IExternalReference)parent);
                    break;
                }
            }
        }
    }
    
    @Override
    public void touch(final IExternalReference externalReference) {
        this.B.remove(externalReference);
        this.B.add(externalReference);
    }
    
    public void memoryLock(final IExternalReference externalReference) {
        this.remove(externalReference);
    }
    
    public void memoryUnlock(final IExternalReference externalReference) {
        this.add(externalReference);
    }
    
    @Override
    public int size() {
        return this.B.size();
    }
    
    @Override
    public int capacity() {
        return this.A;
    }
    
    protected void clearEntry(final IExternalReference externalReference) {
        if (externalReference.isDirty()) {
            return;
        }
        try {
            externalReference.clearCache();
        }
        catch (CachingException ex) {
            throw new IllegalStateException("Attempt to purge cache entry which has listeners.");
        }
    }
}
