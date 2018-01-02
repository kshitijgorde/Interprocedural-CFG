// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.util.EventListener;

public interface ScannerListenerInterface extends EventListener
{
    void tokenGenerated(final TokenEvent p0);
    
    void invalidCharacter(final InvalidCharacterEvent p0);
    
    void lineProcessed(final String p0);
}
