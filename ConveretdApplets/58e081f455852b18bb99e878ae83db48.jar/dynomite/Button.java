// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import sexy.gui.ButtonListener;
import sexy.gui.WidgetManager;
import sexy.gui.SexyGraphics;
import sexy.gui.Widget;
import sexy.gui.ButtonWidget;

public class Button extends ButtonWidget
{
    Widget mParent;
    
    public void MouseEnter() {
        super.MouseEnter();
        this.mParent.MarkDirty();
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        this.mParent.MarkDirty();
    }
    
    public Button(final Widget mParent, final WidgetManager widgetManager, final int n, final ButtonListener buttonListener) {
        super(widgetManager, n, buttonListener);
        this.mParent = mParent;
    }
    
    public void MouseLeave() {
        super.MouseLeave();
        this.mParent.MarkDirty();
    }
}
