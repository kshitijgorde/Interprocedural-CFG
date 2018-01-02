// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.TypedEvent;

public class BidiSegmentEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int[] segments;
    public char[] segmentsChars;
    static final long serialVersionUID = 3257846571587547957L;
    
    BidiSegmentEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.lineOffset = styledTextEvent.detail;
        this.lineText = styledTextEvent.text;
    }
}
