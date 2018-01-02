// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class OctaveDefinition extends AutomataDefinition
{
    public OctaveDefinition() {
        State state = new State(AbcTokenType.OCTAVE, true);
        this.getStartingState().addTransition(new Transition(state, ','));
        state.addTransition(new Transition(state, ','));
        state = new State(AbcTokenType.OCTAVE, true);
        this.getStartingState().addTransition(new Transition(state, '\''));
        state.addTransition(new Transition(state, '\''));
    }
}
