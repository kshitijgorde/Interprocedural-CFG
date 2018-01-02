// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp.transform;

import com.stonewall.cornerstone.dsp.loader.Loader;
import com.stonewall.cornerstone.entity.Entity;

public class EntityTransform<R extends Entity, E extends Entity>
{
    protected R result;
    protected Loader loader;
    
    protected EntityTransform(final R result, final Loader loader) {
        this.result = result;
        this.loader = loader;
    }
    
    public R run(final E entity) throws Exception {
        throw new UnsupportedOperationException();
    }
    
    public R run(final E entity, final R primer) throws Exception {
        throw new UnsupportedOperationException();
    }
}
