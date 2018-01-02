// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Rectangle;
import javax.swing.JScrollBar;
import javax.swing.text.DefaultCaret;

public class vaw extends DefaultCaret
{
    public boolean a;
    
    public vaw() {
        this.a = true;
    }
    
    public void a(final JScrollBar scrollBar) {
        this.a = (scrollBar.getValue() + scrollBar.getVisibleAmount() == scrollBar.getMaximum());
    }
    
    public static void b(final JScrollBar scrollBar) {
        scrollBar.setValue(scrollBar.getMaximum() - scrollBar.getVisibleAmount());
    }
    
    public void adjustVisibility(final Rectangle rectangle) {
        if (this.a) {
            super.adjustVisibility(rectangle);
        }
    }
}
