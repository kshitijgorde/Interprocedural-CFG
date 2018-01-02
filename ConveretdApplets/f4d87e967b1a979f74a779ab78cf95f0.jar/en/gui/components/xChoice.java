// 
// Decompiled by Procyon v0.5.30
// 

package en.gui.components;

import java.awt.event.ItemEvent;
import en.javafx.Parameter;
import java.awt.Choice;

public class xChoice extends Choice implements xGUI
{
    Parameter target;
    
    public xChoice() {
        this.enableEvents(512L);
    }
    
    public final void setTarget(final Parameter target) throws Exception {
        if (target == null) {
            System.out.println("xChoice: setTarget has null parameter");
            return;
        }
        this.target = target;
        if (!(target.target instanceof db)) {
            throw new Exception("xChoice: target parameter must be of xInteger type");
        }
        this.forceRepaint();
    }
    
    protected final void processItemEvent(final ItemEvent itemEvent) {
        if (this.target != null) {
            ((db)this.target.target).p(this.getSelectedIndex());
        }
        super.processItemEvent(itemEvent);
    }
    
    public final void forceRepaint() {
        if (this.target != null) {
            this.select(((db)this.target.target).p());
        }
        this.repaint();
    }
}
