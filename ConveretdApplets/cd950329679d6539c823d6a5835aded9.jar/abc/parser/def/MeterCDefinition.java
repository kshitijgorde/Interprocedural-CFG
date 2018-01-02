// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class MeterCDefinition extends AutomataDefinition
{
    public MeterCDefinition() {
        final State state = new State(AbcTokenType.C_METER, true);
        Transition trans = new Transition(state, 'C');
        this.getStartingState().addTransition(trans);
        final State state2 = new State(AbcTokenType.C_METER, true);
        trans = new Transition(state2, '|');
        state.addTransition(trans);
    }
}
