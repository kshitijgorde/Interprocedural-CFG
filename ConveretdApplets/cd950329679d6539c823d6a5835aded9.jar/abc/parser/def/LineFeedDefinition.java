// 
// Decompiled by Procyon v0.5.30
// 

package abc.parser.def;

import scanner.Transition;
import scanner.TokenType;
import scanner.State;
import abc.parser.AbcTokenType;
import scanner.AutomataDefinition;

public class LineFeedDefinition extends AutomataDefinition
{
    public LineFeedDefinition() {
        final State state = new State(AbcTokenType.LINE_FEED, true);
        Transition trans = new Transition(state, '\n');
        this.getStartingState().addTransition(trans);
        final State state2 = new State(AbcTokenType.LINE_FEED, true);
        trans = new Transition(state2, '\r');
        this.getStartingState().addTransition(trans);
        trans = new Transition(state, '\n');
        state2.addTransition(trans);
    }
}
