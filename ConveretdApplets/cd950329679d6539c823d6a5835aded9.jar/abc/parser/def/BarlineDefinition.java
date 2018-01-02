// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class BarlineDefinition extends AutomataDefinition
{
    public BarlineDefinition() {
        final State state = new State(AbcTokenType.BARLINE, true);
        this.getStartingState().addTransition(new Transition(state, '|'));
        final State state2 = new State(AbcTokenType.BARLINE, true);
        final char[] chars = { '|', ']', ':' };
        Transition trans = new Transition(state2, chars);
        state.addTransition(trans);
        final State state3 = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state3, ':'));
        final State state4 = new State(AbcTokenType.BARLINE, true);
        final char[] chars2 = { ':', '|' };
        trans = new Transition(state4, chars2);
        state3.addTransition(trans);
        final State state5 = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state5, '['));
        final State state6 = new State(AbcTokenType.BARLINE, true);
        trans = new Transition(state6, '|');
        state5.addTransition(trans);
    }
}
