// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.internal.SWTEventObject;

public class TypedEvent extends SWTEventObject
{
    public Display display;
    public Widget widget;
    public int time;
    public Object data;
    static final long serialVersionUID = 3257285846578377524L;
    
    public TypedEvent(final Object o) {
        super(o);
    }
    
    public TypedEvent(final Event event) {
        super(event.widget);
        this.display = event.display;
        this.widget = event.widget;
        this.time = event.time;
        this.data = event.data;
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    public String toString() {
        return String.valueOf(this.getName()) + "{" + this.widget + " time=" + this.time + " data=" + this.data + "}";
    }
}
