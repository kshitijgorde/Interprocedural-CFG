// 
// Decompiled by Procyon v0.5.30
// 

package en.products;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class LightRaysGUI$1 extends MouseAdapter
{
    private final LightRaysGUI this$0;
    
    public LightRaysGUI$1(final LightRaysGUI this$0) {
        this.this$0 = this$0;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.this$0.htmlButton_mouseClicked(mouseEvent);
    }
}
