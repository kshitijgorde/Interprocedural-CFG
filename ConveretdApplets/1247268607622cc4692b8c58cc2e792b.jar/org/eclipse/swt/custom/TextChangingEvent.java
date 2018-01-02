// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.events.TypedEvent;

public class TextChangingEvent extends TypedEvent
{
    public int start;
    public String newText;
    public int replaceCharCount;
    public int newCharCount;
    public int replaceLineCount;
    public int newLineCount;
    static final long serialVersionUID = 3257290210114352439L;
    
    public TextChangingEvent(final StyledTextContent styledTextContent) {
        super(styledTextContent);
    }
    
    TextChangingEvent(final StyledTextContent styledTextContent, final StyledTextEvent styledTextEvent) {
        super(styledTextContent);
        this.start = styledTextEvent.start;
        this.replaceCharCount = styledTextEvent.replaceCharCount;
        this.newCharCount = styledTextEvent.newCharCount;
        this.replaceLineCount = styledTextEvent.replaceLineCount;
        this.newLineCount = styledTextEvent.newLineCount;
        this.newText = styledTextEvent.text;
    }
}
