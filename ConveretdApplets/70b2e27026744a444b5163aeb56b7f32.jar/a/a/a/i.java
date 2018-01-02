// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

public class i extends AbstractAction
{
    private final j a;
    
    public i(final j a, final String s) {
        this.a = a;
        this.putValue("ActionCommandKey", s);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if ("do.search".equals(actionEvent.getActionCommand())) {
            if (j.f(this.a) == null && j.g(this.a) && !j.a(this.a).isPopupVisible()) {
                j.h(this.a);
            }
            else if (j.a(this.a).isPopupVisible()) {
                j.a(this.a).hidePopup();
            }
        }
    }
}
