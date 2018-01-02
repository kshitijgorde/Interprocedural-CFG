// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser;

import abc.notation.Tune;
import scanner.InvalidCharacterEvent;
import scanner.TokenEvent;

public class TuneParserAdapter implements TuneParserListenerInterface
{
    public void tuneBegin() {
    }
    
    public void invalidToken(final InvalidTokenEvent evt) {
    }
    
    public void validToken(final TokenEvent evt) {
    }
    
    public void invalidCharacter(final InvalidCharacterEvent evt) {
    }
    
    public void tuneEnd(final Tune tune) {
    }
}
