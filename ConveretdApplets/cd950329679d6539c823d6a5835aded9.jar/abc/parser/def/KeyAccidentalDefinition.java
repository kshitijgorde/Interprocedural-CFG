// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class KeyAccidentalDefinition extends AutomataDefinition
{
    public KeyAccidentalDefinition() {
        final State startingState = new State(AbcTokenType.KEY_ACCIDENTAL, true);
        final char[] chars = { '#', 'b' };
        final Transition trans = new Transition(startingState, chars);
        this.getStartingState().addTransition(trans);
    }
}
