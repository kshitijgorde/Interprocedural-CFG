// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.IsDigitTransition;
import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class TupletSpecDefinition extends AutomataDefinition
{
    public TupletSpecDefinition() {
        final State state = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state, '('));
        final State state2 = new State(AbcTokenType.TUPLET_SPEC, true);
        state.addTransition(new IsDigitTransition(state2));
    }
}
