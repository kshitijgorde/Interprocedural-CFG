import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWO extends Observable
{
    protected Object object;
    
    public Com2meFWO() {
        this.object = null;
    }
    
    public Com2meFWO(final Object object) {
        this.object = object;
    }
    
    public Object getObject() {
        return this.object;
    }
    
    public void setObject(final Object object) {
        this.object = object;
        this.setChanged();
        this.notifyObservers(this.object);
    }
    
    public void setObjectSilent(final Object object) {
        this.object = object;
        this.setChanged();
    }
}
