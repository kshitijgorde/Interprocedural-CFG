// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class BrokenRhythmDefinition extends AutomataDefinition
{
    public BrokenRhythmDefinition() {
        State state = new State(AbcTokenType.BROKEN_RHYTHM, true);
        this.getStartingState().addTransition(new Transition(state, '<'));
        state.addTransition(new Transition(state, '<'));
        state = new State(AbcTokenType.BROKEN_RHYTHM, true);
        this.getStartingState().addTransition(new Transition(state, '>'));
        state.addTransition(new Transition(state, '>'));
    }
}
