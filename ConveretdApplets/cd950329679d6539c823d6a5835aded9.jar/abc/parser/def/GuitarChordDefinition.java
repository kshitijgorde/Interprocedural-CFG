// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class GuitarChordDefinition extends AutomataDefinition
{
    public GuitarChordDefinition() {
        final State state = new State(AbcTokenType.GUITAR_CHORD, true);
        this.getStartingState().addTransition(new Transition(state, '\"'));
    }
}
