// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;

public class JCItemEvent extends ItemEvent
{
    private static ItemSelectable selectable_item;
    
    public JCItemEvent(final Object source, final int n, final Object o, final int n2) {
        super(JCItemEvent.selectable_item, n, o, n2);
        super.source = source;
    }
    
    public Object getItem() {
        return super.getItem();
    }
    
    public int getStateChange() {
        return super.getStateChange();
    }
    
    static {
        JCItemEvent.selectable_item = new ItemSelectableObject();
    }
}
