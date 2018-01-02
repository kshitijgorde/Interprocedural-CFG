// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik;

import java.util.EventListener;

public interface RubikListener extends EventListener
{
    void rubikTwisting(final RubikEvent p0);
    
    void rubikTwisted(final RubikEvent p0);
    
    void rubikPartRotated(final RubikEvent p0);
    
    void rubikChanged(final RubikEvent p0);
}
