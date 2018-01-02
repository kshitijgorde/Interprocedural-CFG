// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class NthRepeatDefinition extends AutomataDefinition
{
    public NthRepeatDefinition() {
        final State state = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state, '['));
        final State state2 = new State(AbcTokenType.NTH_REPEAT, true);
        final char[] chars = { '1', '2' };
        final Transition trans = new Transition(state2, chars);
        state.addTransition(trans);
        final State state3 = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state3, '|'));
        final State state4 = new State(AbcTokenType.NTH_REPEAT, true);
        state3.addTransition(new Transition(state4, '1'));
        final State state5 = new State(AbcTokenType.UNKNOWN, false);
        final State state6 = new State(AbcTokenType.UNKNOWN, false);
        final State state7 = new State(AbcTokenType.NTH_REPEAT, true);
        this.getStartingState().addTransition(new Transition(state5, ':'));
        state5.addTransition(new Transition(state6, '|'));
        state6.addTransition(new Transition(state7, '2'));
    }
}
