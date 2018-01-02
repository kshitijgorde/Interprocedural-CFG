// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.EventListener;

public class SingletonModelEvent extends ModelEvent
{
    private SingletonModel model;
    
    public SingletonModelEvent(final SingletonModel model) {
        super(model, 1);
        this.model = model;
    }
    
    public SingletonModel getModel() {
        return this.model;
    }
    
    public void dispatch(final EventListener listener) {
        ((SingletonModelListener)listener).modelContentChanged(this);
    }
    
    protected String paramString() {
        return String.valueOf(String.valueOf(super.paramString()).concat(String.valueOf(",model="))).concat(String.valueOf(this.model));
    }
}
