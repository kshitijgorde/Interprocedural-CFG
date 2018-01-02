// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.events.TypedEvent;

public class LineBackgroundEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public Color lineBackground;
    static final long serialVersionUID = 3978711687853324342L;
    
    public LineBackgroundEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.lineOffset = styledTextEvent.detail;
        this.lineText = styledTextEvent.text;
        this.lineBackground = styledTextEvent.lineBackground;
    }
}
