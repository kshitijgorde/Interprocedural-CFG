// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.TypedEvent;

public final class ExtendedModifyEvent extends TypedEvent
{
    public int start;
    public int length;
    public String replacedText;
    static final long serialVersionUID = 3258696507027830832L;
    
    public ExtendedModifyEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.start = styledTextEvent.start;
        this.length = styledTextEvent.end - styledTextEvent.start;
        this.replacedText = styledTextEvent.text;
    }
}
