// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Callable;

class NextFunction implements Callable
{
    private final RNG obj_;
    
    public NextFunction(final RNG obj_) {
        this.obj_ = obj_;
    }
    
    public Object call() {
        return new Long(this.obj_.next());
    }
}
