// 
// Decompiled by Procyon v0.5.30
// 

package irc.style;

import java.awt.event.MouseEvent;

public interface StyledListListener
{
    void channelEvent(final StyledList p0, final String p1, final MouseEvent p2);
    
    void URLEvent(final StyledList p0, final String p1, final MouseEvent p2);
    
    void nickEvent(final StyledList p0, final String p1, final MouseEvent p2);
    
    void copyEvent(final StyledList p0, final String p1, final MouseEvent p2);
    
    void virtualSizeChanged(final StyledList p0);
}
