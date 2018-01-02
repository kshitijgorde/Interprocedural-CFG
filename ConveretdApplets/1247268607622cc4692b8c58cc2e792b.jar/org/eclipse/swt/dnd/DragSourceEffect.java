// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.dnd;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

public class DragSourceEffect extends DragSourceAdapter
{
    Control control;
    
    public DragSourceEffect(final Control control) {
        this.control = null;
        if (control == null) {
            SWT.error(4);
        }
        this.control = control;
    }
    
    public Control getControl() {
        return this.control;
    }
}
