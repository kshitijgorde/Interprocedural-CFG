// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.BoundedRangeModel;

final class bt implements Runnable
{
    private /* synthetic */ aO a;
    
    bt(final aO a) {
        this.a = a;
    }
    
    public final void run() {
        final BoundedRangeModel model = this.a.a.getModel();
        model.setValue(model.getMinimum());
    }
}
