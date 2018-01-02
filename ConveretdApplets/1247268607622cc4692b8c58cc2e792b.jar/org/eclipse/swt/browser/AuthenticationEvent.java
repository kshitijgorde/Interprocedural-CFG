// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.events.TypedEvent;

public class AuthenticationEvent extends TypedEvent
{
    public String location;
    public String user;
    public String password;
    public boolean doit;
    static final long serialVersionUID = -8322331206780057921L;
    
    public AuthenticationEvent(final Widget widget) {
        super(widget);
        this.doit = true;
    }
    
    public String toString() {
        final String string = super.toString();
        return String.valueOf(string.substring(0, string.length() - 1)) + " name=" + this.user + " password=" + this.password + " location=" + this.location + "}";
    }
}
