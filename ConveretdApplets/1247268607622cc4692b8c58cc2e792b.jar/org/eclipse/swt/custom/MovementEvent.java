// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.TypedEvent;

public class MovementEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int offset;
    public int newOffset;
    public int movement;
    static final long serialVersionUID = 3978765487853324342L;
    
    public MovementEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.lineOffset = styledTextEvent.detail;
        this.lineText = styledTextEvent.text;
        this.movement = styledTextEvent.count;
        this.offset = styledTextEvent.start;
        this.newOffset = styledTextEvent.end;
    }
}
