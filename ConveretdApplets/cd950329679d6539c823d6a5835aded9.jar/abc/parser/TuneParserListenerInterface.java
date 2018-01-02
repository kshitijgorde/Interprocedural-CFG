// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.notation.Tune;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;
import java.util.EventListener;

public interface TuneParserListenerInterface extends EventListener
{
    void tuneBegin();
    
    void invalidToken(final InvalidTokenEvent p0);
    
    void validToken(final TokenEvent p0);
    
    void invalidCharacter(final InvalidCharacterEvent p0);
    
    void tuneEnd(final Tune p0);
}
