// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Dimension;
import java.awt.Choice;

class LabeledChoice$PreferredSizeChoice extends Choice
{
    private static final long serialVersionUID = 3150781707958743538L;
    final /* synthetic */ LabeledChoice this$0;
    
    LabeledChoice$PreferredSizeChoice(final LabeledChoice this$0) {
        this.this$0 = this$0;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(130, super.getPreferredSize().height);
    }
}
