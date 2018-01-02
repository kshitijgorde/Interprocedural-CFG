// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class TitleEvent extends TypedEvent
{
    public String title;
    static final long serialVersionUID = 4121132532906340919L;
    
    public TitleEvent(final Widget widget) {
        super(widget);
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " title=" + this.title + "}";
    }
}
