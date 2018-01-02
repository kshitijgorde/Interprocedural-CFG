// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.TypedEvent;

public class CaretEvent extends TypedEvent
{
    public int caretOffset;
    static final long serialVersionUID = 3257846571587545489L;
    
    CaretEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.caretOffset = styledTextEvent.end;
    }
}
