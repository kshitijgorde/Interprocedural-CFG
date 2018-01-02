import java.awt.Event;
import java.awt.Choice;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_ChoiceOp extends Choice
{
    private PGC owner;
    
    public PGC_ChoiceOp(final PGC owner) {
        this.owner = owner;
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.id == 1001 && o != null) {
            final int opEvalChoiceLabel = PGC.OpEvalChoiceLabel(o.toString());
            if (this.owner != null) {
                this.owner.SetOp(opEvalChoiceLabel);
            }
            return true;
        }
        return super.action(event, o);
    }
}
