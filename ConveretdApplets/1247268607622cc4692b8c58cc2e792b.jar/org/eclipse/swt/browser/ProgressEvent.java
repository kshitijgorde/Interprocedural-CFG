// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class ProgressEvent extends TypedEvent
{
    public int current;
    public int total;
    static final long serialVersionUID = 3977018427045393972L;
    
    public ProgressEvent(final Widget widget) {
        super(widget);
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " current=" + this.current + " total=" + this.total + "}";
    }
}
