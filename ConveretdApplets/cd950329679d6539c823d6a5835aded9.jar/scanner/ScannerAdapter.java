// 
// Decompiled by Procyon v0.5.30
// 

package scanner;

import java.util.EventListener;

public class ScannerAdapter implements ScannerListenerInterface, EventListener
{
    public void tokenGenerated(final TokenEvent event) {
    }
    
    public void invalidCharacter(final InvalidCharacterEvent evt) {
    }
    
    public void lineProcessed(final String line) {
    }
}
