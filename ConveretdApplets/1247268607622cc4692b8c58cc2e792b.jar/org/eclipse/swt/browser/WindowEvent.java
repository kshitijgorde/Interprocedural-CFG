// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.TypedEvent;

public class WindowEvent extends TypedEvent
{
    public boolean required;
    public Browser browser;
    public Point location;
    public Point size;
    public boolean addressBar;
    public boolean menuBar;
    public boolean statusBar;
    public boolean toolBar;
    static final long serialVersionUID = 3617851997387174969L;
    
    public WindowEvent(final Widget widget) {
        super(widget);
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " required=" + this.required + " browser=" + this.browser + " location=" + this.location + " size=" + this.size + " addressBar=" + this.addressBar + " menuBar=" + this.menuBar + " statusBar=" + this.statusBar + " toolBar=" + this.toolBar + "}";
    }
}
