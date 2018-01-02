// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import java.io.Serializable;

class UpdateCommand implements Runnable, Serializable, Comparable
{
    private final RNG obj_;
    final long cmpVal;
    
    public UpdateCommand(final RNG obj_) {
        this.obj_ = obj_;
        this.cmpVal = obj_.get();
    }
    
    public int compareTo(final Object o) {
        final UpdateCommand updateCommand = (UpdateCommand)o;
        if (this.cmpVal < updateCommand.cmpVal) {
            return -1;
        }
        if (this.cmpVal > updateCommand.cmpVal) {
            return 1;
        }
        return 0;
    }
    
    public void run() {
        this.obj_.update();
    }
}
