// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.events.TypedEvent;

public class PaintObjectEvent extends TypedEvent
{
    public GC gc;
    public int x;
    public int y;
    public int ascent;
    public int descent;
    public StyleRange style;
    public Bullet bullet;
    public int bulletIndex;
    static final long serialVersionUID = 3906081274027192855L;
    
    public PaintObjectEvent(final StyledTextEvent styledTextEvent) {
        super(styledTextEvent);
        this.gc = styledTextEvent.gc;
        this.x = styledTextEvent.x;
        this.y = styledTextEvent.y;
        this.ascent = styledTextEvent.ascent;
        this.descent = styledTextEvent.descent;
        this.style = styledTextEvent.style;
        this.bullet = styledTextEvent.bullet;
        this.bulletIndex = styledTextEvent.bulletIndex;
    }
}
