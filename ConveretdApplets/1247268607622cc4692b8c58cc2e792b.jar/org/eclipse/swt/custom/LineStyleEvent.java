// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.events.TypedEvent;

public class LineStyleEvent extends TypedEvent
{
    public int lineOffset;
    public String lineText;
    public int[] ranges;
    public StyleRange[] styles;
    public int alignment;
    public int indent;
    public int wrapIndent;
    public boolean justify;
    public Bullet bullet;
    public int bulletIndex;
    public int[] tabStops;
    static final long serialVersionUID = 3906081274027192884L;
    
    public LineStyleEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.styles = styledTextEvent.styles;
        this.ranges = styledTextEvent.ranges;
        this.lineOffset = styledTextEvent.detail;
        this.lineText = styledTextEvent.text;
        this.alignment = styledTextEvent.alignment;
        this.justify = styledTextEvent.justify;
        this.indent = styledTextEvent.indent;
        this.wrapIndent = styledTextEvent.wrapIndent;
        this.bullet = styledTextEvent.bullet;
        this.bulletIndex = styledTextEvent.bulletIndex;
        this.tabStops = styledTextEvent.tabStops;
    }
}
