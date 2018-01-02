// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.DispatchableEvent;

public abstract class ModelEvent extends DispatchableEvent
{
    public static final int CONTENT_CHANGED = 1;
    public static final int STRUCTURE_CHANGED = 2;
    private int id;
    
    public ModelEvent(final Object source, final int id) {
        super(source);
        this.id = (id & 0xF);
    }
    
    public int getID() {
        return this.id;
    }
    
    protected String paramString() {
        String idString = null;
        switch (this.id) {
            case 1: {
                idString = "CONTENT_CHANGED";
                break;
            }
            case 2: {
                idString = "STRUCTURE_CHANGED";
                break;
            }
            default: {
                idString = "<INVALID>";
                break;
            }
        }
        return String.valueOf("id=").concat(String.valueOf(idString));
    }
}
