// 
// Decompiled by Procyon v0.5.30
// 

package gravity.tools;

import java.awt.Component;
import java.awt.Container;

public class LayoutValidator
{
    private Container _container;
    
    public LayoutValidator(final Container container) {
        this._container = container;
    }
    
    public void validate(final Component component) {
        component.invalidate();
        this._container.validate();
    }
}
