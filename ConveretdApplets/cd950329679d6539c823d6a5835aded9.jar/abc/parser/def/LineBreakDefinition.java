// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.TokenType;
import scanner.Transition;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class LineBreakDefinition extends AutomataDefinition
{
    public LineBreakDefinition() {
        final State state = new State(AbcTokenType.UNKNOWN, false);
        this.getStartingState().addTransition(new Transition(state, '!'));
        final State state2 = new State(AbcTokenType.LINE_BREAK, true);
        state.addTransition(new Transition(state2, '\n'));
    }
}
