// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class AccidentalDefinition extends AutomataDefinition
{
    public AccidentalDefinition() {
        State state = new State(AbcTokenType.ACCIDENTAL, true);
        Transition trans = new Transition(state, '^');
        this.getStartingState().addTransition(trans);
        State state2 = new State(AbcTokenType.ACCIDENTAL, true);
        trans = new Transition(state2, '^');
        state.addTransition(trans);
        state = new State(AbcTokenType.ACCIDENTAL, true);
        trans = new Transition(state, '_');
        this.getStartingState().addTransition(trans);
        state2 = new State(AbcTokenType.ACCIDENTAL, true);
        trans = new Transition(state2, '^');
        state.addTransition(trans);
        state = new State(AbcTokenType.ACCIDENTAL, true);
        trans = new Transition(state, '=');
        this.getStartingState().addTransition(trans);
    }
}
