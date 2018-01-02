// 
// Decompiled by Procyon v0.5.30
// 

public class MyEvent
{
    Object source;
    int id;
    
    public MyEvent(final Object source, final int id) {
        this.source = source;
        this.id = id;
    }
    
    public Object getSource() {
        return this.source;
    }
    
    public int getID() {
        return this.id;
    }
}
