// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.textarea;

import java.awt.event.MouseEvent;

public interface HyperlinkTextAreaEvent
{
    void autoAway();
    
    void Click();
    
    void doubleClick();
    
    void handleHyperlink(final String p0);
    
    void handleNick(final String p0);
    
    void hidePopupInfos();
    
    void MouseReleased(final MouseEvent p0);
    
    void openPrivate(final String p0);
    
    void popupCopyPaste(final MouseEvent p0);
    
    void popupInfos(final String p0, final String p1, final MouseEvent p2);
}
